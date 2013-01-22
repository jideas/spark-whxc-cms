/**
 * 
 */
package com.spark.front.action.joinus;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.joinus.GetJoinSevenBillsKey;
import com.spark.cms.services.joinus.JoinusService;
import com.spark.cms.services.vo.JoinSevenBillVo;

/**
 * @author Jideas
 * 
 */
@Controller
public class JoinSevenAction extends BaseAction {

	@Autowired
	private JoinusService joinusService;

	/**
	 * 申请加盟
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/JoinSeven")
	@ResponseBody
	public ResponseEntity<MessageModel> JoinSeven(HttpServletRequest request, JoinSevenBillVo form) {
		try {
			if (null == form) {
				return new ServiceMessage(false, "申请失败，请稍后重试", 0).getMessageModel();
			}
			return this.joinusService.createBill(form).getMessageModel();
		} catch (Throwable e) {
			log.error("申请加盟发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		return new ServiceMessage(false, "个别信息长度过大，请检查", 0).getMessageModel();
	}

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@RequestMapping("/join/getBillsList")
	@ResponseBody
	public DataModel<JoinSevenBillVo> getBillsList(@RequestParam(value = "billStatus", required = false)
	String billStatus, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows) {
		GetJoinSevenBillsKey key = new GetJoinSevenBillsKey();
		if (CheckIsNull.isNotEmpty(billStatus) && !"0".equals(billStatus)) {
			key.setOnlyOpered(Boolean.parseBoolean(billStatus));
		}
		key.setOffset(Integer.parseInt(page));
		key.setPageSize(Integer.parseInt(rows));
		return this.joinusService.getVolist(key);
	}

	/**
	 * 获取申请单状态
	 * 
	 * @return
	 */
	@RequestMapping("/join/getStatusList")
	@ResponseBody
	public List<DicItem> getStatusList() {
		List<DicItem> list = new ArrayList<DicItem>();
		list.add(new DicItem("0", "全部"));
		list.add(new DicItem("false", "未处理"));
		list.add(new DicItem("true", "已处理"));
		return list;
	}

	/**
	 * 处理申请单
	 */
	@RequestMapping("/bill/activeBill")
	@ResponseBody
	public ResponseEntity<MessageModel> activeBill(HttpSession session, @RequestParam(value = "id", required = true)
	String id) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			return this.joinusService.exeOperedBill(id, login).getMessageModel();
		} catch (Throwable e) {
			log.error("处理申请单发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	@RequestMapping("/bill/findBill")
	@ResponseBody
	public JoinSevenBillVo findBill(@RequestParam(value = "id", required = true)
	String id) {
		JoinSevenBillVo vo = this.joinusService.find(id);
		return vo;
	}
}
