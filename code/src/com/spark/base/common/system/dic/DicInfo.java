package com.spark.base.common.system.dic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicInfo {

	private DictionaryType type;

	private Map<String, List<DicItem>> lists;

	private Map<String, DicItem> items;

	public DicInfo(DictionaryType type, List<DicItem> list) {
		this.type = type;
		if (null == items) {
			items = new HashMap<String, DicItem>();
		}
		if (null == lists) {
			lists = new HashMap<String, List<DicItem>>();
		}
		for (DicItem d : list) {
			items.put(d.getCode(), d);
			if (null == lists) {
				lists = new HashMap<String, List<DicItem>>();
			}
			List<DicItem> suns = lists.get(d.getParentcode());
			if (null == suns) {
				suns = new ArrayList<DicItem>();
			}
			suns.add(d);
			lists.put(d.getParentcode(), suns);
		}
	}

	public DictionaryType getType() {
		return type;
	}

	public List<DicItem> getAllItems() {
		return new ArrayList<DicItem>(items.values());
	}

	public List<DicItem> getAllSuns(String parent) {
		return new ArrayList<DicItem>(lists.get(parent));
	}

	public DicItem getItem(String code) {
		if (null == items) {
			return null;
		}
		return items.get(code);
	}

}
