package com.spark.base.common.system.dic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterConfig;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public abstract class SparkDictionaryManager {

	private static Map<DictionaryType, DicInfo> map;

	/**
	 * 得到一个枚举的所有条目
	 */
	public static List<DicItem> getDicItemsList(DictionaryType dicType) {
		DicInfo info = map.get(dicType);
		if (null == info) {
			return null;
		}
		return info.getAllItems();
	}

	/**
	 * 得到一个枚举的所有条目
	 */
	public static List<DicItem> getDicItemsList(DictionaryType dicType, String parent) {
		DicInfo info = map.get(dicType);
		if (null == info) {
			return null;
		}
		return info.getAllSuns(parent);
	}

	/**
	 * 得到一个枚举条目
	 */
	public static DicItem getItem(DictionaryType dicType, String code) {
		DicInfo info = map.get(dicType);
		if (null == info) {
			return null;
		}
		return info.getItem(code);
	}

	public static void init(FilterConfig arg0) {
		DictionaryType[] dicTypes = DictionaryType.values();
		if (null == dicTypes) {
			return;
		}
		if (null == map) {
			map = new HashMap<DictionaryType, DicInfo>();
		}
		for (DictionaryType type : dicTypes) {
			DicInfo info = doParseXml(arg0, type);
			map.put(info.getType(), info);
		}
	}

	@SuppressWarnings("unchecked")
	private static DicInfo doParseXml(FilterConfig arg0, DictionaryType type) {
		String path = arg0.getServletContext().getRealPath("") + "/xml/dic/" + type.getFileName() + ".xml";
		File file = new File(path);
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<DicItem> items = new ArrayList<DicItem>();
		// 遍历根结点的所有孩子节点
		for (Iterator<Element> iter = root.elementIterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			// 获取person节点的age属性的值
			Attribute codeAttr = element.attribute("code");
			Attribute nameAttr = element.attribute("name");
			Attribute parentAttr = element.attribute("parent");
			if (null == parentAttr) {
				items.add(new DicItem(codeAttr.getValue(), nameAttr.getValue()));
			} else {
				DicItem item = new DicItem(codeAttr.getValue(), nameAttr.getValue(), parentAttr.getValue());
				items.add(item);
			}
		}
		return new DicInfo(type, items);
	}
}
