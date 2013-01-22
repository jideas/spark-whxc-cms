/**
 * 
 */
package com.spark.front.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spark.cms.base.constant.card.CMS;
import com.spark.front.form.payment.AliSubmitForm;

/**
 * @author Jideas
 * 
 */
public class PayFormCreator {

	public static String getUnionPay(String merId, String orderId, String amount, String bgRetUrl, String pageRetUrl,
			String gateId, String relaId, String realPath) {
		String checkValue = UnionPayChkValue.getPayMerPrKValue(merId, orderId, amount, "156", orderId.substring(0, 8),
				"0001", realPath, relaId);
		StringBuilder html = new StringBuilder();
		html.append("<form method=\"post\" action='" + CMS.PayInfo.UnionPayUrl + "' id=\"paymentForm\">\n");
		html.append(getInput("MerId", merId));
		html.append(getInput("OrdId", orderId));
		html.append(getInput("TransAmt", amount));
		html.append(getInput("CuryId", "156"));
		html.append(getInput("TransDate", orderId.substring(0, 8)));
		html.append(getInput("TransType", "0001"));
		html.append(getInput("Version", "20070129"));
		html.append(getInput("BgRetUrl", bgRetUrl));
		html.append(getInput("PageRetUrl", pageRetUrl));
		html.append(getInput("GateId", gateId));
		html.append(getInput("Priv1", relaId));
		html.append(getInput("ChkValue", checkValue));
		html.append("</form>\n<script type='text/javascript'>");
		html.append("document.getElementById('paymentForm').submit();");
		html.append("</script>");
		return html.toString();
	}

	public static String getAliPay(AliSubmitForm form) {
		initSign(form);
		StringBuilder html = new StringBuilder();
		html.append("<form method=\"post\" action='" + CMS.PayInfo.AliPayUrl + "' id=\"paymentForm\">\n");
		html.append(getInput("service", form.getService()));
		html.append(getInput("partner", form.getPartner()));
		html.append(getInput("_input_charset", form.get_input_charset()));
		html.append(getInput("sign_type", form.getSign_type()));
		html.append(getInput("anti_phishing_key", form.getAnti_phishing_key()));
		html.append(getInput("error_notify_url", form.getError_notify_url()));
		html.append(getInput("exter_invoke_ip", form.getExter_invoke_ip()));
		html.append(getInput("need_ctu_check", form.getNeed_ctu_check()));
		html.append(getInput("notify_url", form.getNotify_url()));
		html.append(getInput("out_trade_no", form.getOut_trade_no()));
		html.append(getInput("payment_type", form.getPayment_type()));
		html.append(getInput("paymethod", form.getPaymethod()));
		html.append(getInput("return_url", form.getReturn_url()));
		html.append(getInput("seller_email", form.getSeller_email()));
		html.append(getInput("subject", form.getSign()));
		html.append(getInput("sign", form.getSubject()));
		html.append(getInput("total_fee", form.getTotal_fee())); 
		html.append(getInput("extra_common_param", form.getExtra_common_param())); 
		html.append("</form>\n");
		html.append("<script type='text/javascript'>");
		html.append("document.getElementById('paymentForm').submit();");
		html.append("</script>");
		return html.toString();
	}

	/**
	 * @param form
	 */
	private static void initSign(AliSubmitForm form) {
		String mysign = null;
		String prestr = getPrestr(form);// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		prestr = prestr + CMS.PayInfo.AliPaySafekey;
		mysign = AlipayMd5Encrypt.md5(prestr);
		form.setSign(mysign);
	}

	private static String getPrestr(AliSubmitForm form) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("_input_charset", form.get_input_charset());
		map.put("anti_phishing_key", form.getAnti_phishing_key());
		map.put("error_notify_url", form.getError_notify_url());
		map.put("exter_invoke_ip", form.getExter_invoke_ip());
		map.put("extra_common_param", form.getExtra_common_param());
		map.put("need_ctu_check", form.getNeed_ctu_check());
		map.put("notify_url", form.getNotify_url());
		map.put("out_trade_no", form.getOut_trade_no());
		map.put("partner", form.getPartner());
		map.put("payment_type", form.getPayment_type());
		map.put("paymethod", form.getPaymethod());
		map.put("return_url", form.getReturn_url());
		map.put("seller_email", form.getSeller_email());
		map.put("service", form.getService());
		map.put("sign", form.getSign());
		map.put("sign_type", form.getSign_type());
		map.put("subject", form.getSubject());
		map.put("total_fee", form.getTotal_fee());
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	private static String getInput(String name, String value) {
		StringBuilder html = new StringBuilder();
		html.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\">");
		html.append("\n");
		return html.toString();
	}
}
