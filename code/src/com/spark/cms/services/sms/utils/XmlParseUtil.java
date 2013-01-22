/**
 * 
 */
package com.spark.cms.services.sms.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml解析工具
 */
public class XmlParseUtil {

	private XmlParseUtil() {
	}

	private static XmlParseUtil instance;

	/**
	 * 获得解析工具对象(over)
	 */
	public static XmlParseUtil getInstance() {
		if (null == instance) {
			instance = new XmlParseUtil();
		}
		return instance;
	}

	/**
	 * 主调用接口(over)
	 */
	public ReturnMessage parseXml(String xml) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		return parseRoot(root);
	}

	private ReturnMessage parseRoot(Element root) {
		ReturnMessage msg = new ReturnMessage();
		Element result = root.element("result");
		if (null != result) {
			msg.setResult(result.getText());
		}
		Element spmid = root.element("spmid");
		if (null != spmid) {
			msg.setSpmid(spmid.getText());
		}
		Element description = root.element("description");
		if (null != description) {
			msg.setDescription(description.getText());
		}
		Element reserve1 = root.element("reserve1");
		if (null != reserve1) {
			msg.setReserve1(reserve1.getText());
		}
		Element reserve2 = root.element("reserve2");
		if (null != reserve2) {
			msg.setReserve2(reserve2.getText());
		}
		return msg;
	}

}
