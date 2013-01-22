package com.spark.cms.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReadHelper {

	protected String[] head;
	protected List<String[]> data;

	public void read(InputStream input, boolean hasHead) throws Exception {
		HSSFWorkbook book = null;
		try {
			book = new HSSFWorkbook(input);
		} catch (IOException e) {
			throw new Exception("读取文件错误，请确认传入的是正确的excel格式!");
		}
		HSSFSheet sheet = book.getSheetAt(0); // 读取第一个工作薄
		int index = 0;
		if (hasHead) {
			readHead(sheet); // 读取表头
			index++;
		}
		readData(sheet, index); // 读取数据
	}

	private void readData(HSSFSheet sheet, int rowIndex) {
		data = new ArrayList<String[]>();
		for (; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			HSSFRow row = sheet.getRow(rowIndex);
			String[] str = new String[head.length];
			for (int cellIndex = 0; cellIndex < head.length; cellIndex++) {
				HSSFCell cell = row.getCell(cellIndex);
				if (cell == null) {
					str[cellIndex] = "";
				} else {
					str[cellIndex] = cell.toString();
				}
			}
			data.add(str);
		}
	}

	private void readHead(HSSFSheet sheet) throws Exception {
		HSSFRow headRow = sheet.getRow(0); // 读取第一行 默认认为第一个为表头
		head = new String[headRow.getLastCellNum()];
		for (int i = 0; i < head.length; i++) {
			HSSFCell cell = headRow.getCell(i);
			if (cell == null)
				throw new Exception("excel格式错误，表头字段不能为空！");
			head[i] = cell.toString();
		}
	}

	/**
	 * 返回读出来的总行数
	 * 
	 * @return int
	 */
	public int getRowCount() {
		return data.size();
	}

	/**
	 * 获得指定行
	 * 
	 * @param index
	 * @return String[]
	 */
	public String[] getRow(int index) {
		if (index < 0 || index > data.size() - 1)
			throw new IllegalArgumentException("index:" + index + " 越界");
		return data.get(index);
	}

	/**
	 * 获得表头名称
	 * 
	 * @return String[]
	 */
	public String[] getHead() {
		return head;
	}

	public List<String[]> getData() {
		return data;
	}
}
