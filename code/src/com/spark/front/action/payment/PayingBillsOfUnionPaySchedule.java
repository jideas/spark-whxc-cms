/**
 * 
 */
package com.spark.front.action.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spark.base.common.filter.SystemCommonFilter;
import com.spark.cms.base.constant.card.CMS;
import com.spark.cms.base.constant.payment.PayingBillStatus;
import com.spark.cms.services.payment.PayingBillsService;
import com.spark.cms.services.vo.PayingBillsVo;
import com.spark.front.form.payment.UnionPayForm;
import com.spark.front.utils.UnionPayChkValue;

/**
 * @author Jideas
 * 
 */
@Component
public class PayingBillsOfUnionPaySchedule {

	@Autowired
	private PayingBillsService service;

	@Scheduled(fixedDelay = 300000)
	public void reQueryPayResultAndUpdate() {

		System.out.println("准备查询银联交易记录。。。。。。");
		List<PayingBillsVo> list = this.service.getAllList();
		if (null == list || list.isEmpty()) {
			System.out.println("没有未处理的付款。。。。。。");
			return;
		}
		for (PayingBillsVo vo : list) {
			if (vo.getCreateTime() > (new Date().getTime() - 300000)) {
				break;
			}
			System.out.println("开始查询请求。。。。。。");
			URL urls = null;
			String str = CMS.PayInfo.UnionPayUserNo + vo.getTransDate() + vo.getOrderId() + "0001";
			String checkValue = UnionPayChkValue.getPayQueryChkValue(SystemCommonFilter.getContextRealPath(), str);
			try {
				String path = CMS.PayInfo.UnionPayQueryUrl + "?MerId=" + CMS.PayInfo.UnionPayUserNo
				+ "&TransType=0001&OrdId=" + vo.getOrderId() + "&TransDate=" + vo.getTransDate()
				+ "&Version=20060831&Resv=" + vo.getRecid() + "&ChkValue=" + checkValue;
				System.out.println(path);
				urls = new URL(path);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				continue;
			}
			System.out.println("开始连接。。。。。。");
			HttpURLConnection uc = null;
			try {
				uc = (HttpURLConnection) urls.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			try {
				uc.setRequestMethod("POST");
			} catch (ProtocolException e) {
				e.printStackTrace();
				continue;
			}
			uc.setDoInput(true);
			uc.setReadTimeout(10000);
			uc.setConnectTimeout(10000);
			uc.getURL();
			System.out.println("连接成功。。。。。。");
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "gbk"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				continue;
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			String readLine = "";
			StringBuffer sb = new StringBuffer();
			try {
				while ((readLine = in.readLine()) != null) {
					sb.append(readLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			String xml = sb.toString().substring(sb.toString().indexOf("<body>") + 6, sb.toString().indexOf("</body>"));
			System.out.println("返回结果：" + xml);
			String params[] = xml.split("&");
			Map<String, String> map = new HashMap<String, String>();
			for (String param : params) {
				String two[] = param.split("=");
				map.put(two[0], two[1]);
			}
			if ("0".equals(map.get("ResponeseCode")) && vo.getAmount().equals(map.get("amount"))
					&& "1001".equals("status")) {
				UnionPayForm form = new UnionPayForm();
				form.setAmount(map.get("amount"));
				form.setCheckvalue(map.get("checkvalue"));
				form.setCurrencycode(map.get("currencycode"));
				form.setGateId(map.get("GateId"));
				form.setMerid(map.get("merid"));
				form.setOrderno(map.get("orderno"));
				form.setPriv1(map.get("Priv1"));
				form.setStatus(map.get("status"));
				form.setTransdate(map.get("transdate"));
				form.setTranstype(map.get("transdate"));
				if (!UnionPayChkValue.checkVerifyTransResponse(form, SystemCommonFilter.getContextRealPath())) {
					System.out.println("未通过验证");
					continue;
				}
				try {
					System.out.println("开始更新相关数据。。。。");
					this.service.exeUpdateStatus(vo, PayingBillStatus.Success.getCode(), true);
					System.out.println("本条数据完成。");
				} catch (Throwable e) {
					e.printStackTrace();
					continue;
				}
			}
			try {
				Thread.sleep(30000);
				System.out.println("休眠30秒。。。。。。。");
			} catch (InterruptedException e) {
				System.out.println("休眠30秒失败！！！！！！！");
			}
		}
	}

	@Scheduled(fixedDelay = 24 * 3600000)
	public void deleteOldPayingLog() {
		this.service.deleteOldBill();
	}
}
