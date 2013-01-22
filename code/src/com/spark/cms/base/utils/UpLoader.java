package com.spark.cms.base.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UpLoader {
	public enum uploaderState {
		Success("Success"), NOFILE("δ�����ļ��ϴ���"), TYPE("��������ļ���ʽ"), SIZE("�ļ���С��������"), ENTYPE("��������ENTYPE����"), REQUEST(
				"�ϴ������쳣"), IO("�ļ�Ŀ¼����"), UNKNOWN("δ֪����");
		String value;

		uploaderState(String value) {
			this.value = value;
		}
	}

	public UpLoader(HttpServletRequest request) {
		this.request = request;
	}

	public void upload(MultipartFile multipartFile) throws Exception {
		try {
			setOriginalName(multipartFile.getOriginalFilename());
			setTitle(request.getParameter("pictitle"));
			setSize(multipartFile.getSize());
			setType(multipartFile.getContentType());
			if (!checkFileType(this.originalName)) {
				this.state = uploaderState.TYPE.value;
			} else {
				String savePath = getFolder(this.savePath,false);
				this.fileName = getName(this.originalName);
				this.type = getFileExt(this.fileName);
				this.url = (savePath + "/" + this.fileName);
				multipartFile.transferTo(new File(getPhysicalPath(this.url)));
				this.state = uploaderState.Success.value;
			}
		} catch (Exception e) {
			this.state = uploaderState.UNKNOWN.value;
		}

	}

	public String upload(MultipartFile multipartFile, boolean withOutDate) throws Exception {
		try {
			setOriginalName(multipartFile.getOriginalFilename());
			setTitle(request.getParameter("pictitle"));
			setSize(multipartFile.getSize());
			setType(multipartFile.getContentType());
			if (!checkFileType(this.originalName)) {
				this.state = uploaderState.TYPE.value;
			} else {
				String savePath = getFolder(this.savePath, withOutDate);
				this.fileName = getName(this.originalName);
				this.type = getFileExt(this.fileName);
				this.url = (savePath + "/" + this.fileName);
				multipartFile.transferTo(new File(getPhysicalPath(this.url)));
				this.state = uploaderState.Success.value;
				return url;
			}
		} catch (Exception e) {
			this.state = uploaderState.UNKNOWN.value;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private boolean checkFileType(String fileName) {
		Iterator type = Arrays.asList(this.allowFiles).iterator();
		while (type.hasNext()) {
			String ext = (String) type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = random.nextInt(10000) + System.currentTimeMillis() + getFileExt(fileName);
	}

	private String getFolder(String path, boolean withOutDate) {
		if (!withOutDate) {
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			path = path + "/" + formater.format(new Date());
		}
		File dir = new File(getPhysicalPath(path));
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				this.state = uploaderState.IO.value;
				return "";
			}
		}
		return path;
	}

	private String getPhysicalPath(String path) {
		String servletPath = this.request.getServletPath();
		String realPath = this.request.getSession().getServletContext().getRealPath(servletPath);
		return new File(realPath).getParent() + "/" + path;
	}

	public String getRealPath(String path) {
		ServletContext application = request.getSession().getServletContext();
		String str = application.getRealPath(request.getServletPath());
		return new File(str).getParent();
	}

	public void getFiles(String realpath, List<File> files) {
		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files);
				} else {
					if (!getFileType(file.getName()).equals("")) {
						files.add(file);
					}
				}
			}
		}
	}

	public String getFileType(String fileName) {
		Iterator<String> type = Arrays.asList(getAllowFiles()).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return t;
			}
		}
		return "";
	}

	HttpServletRequest request;

	/**
	 * �����ϴ��ļ�url
	 */
	private String url = "";

	/**
	 * �ϴ��ļ���
	 */
	private String fileName = "";

	/**
	 * �ϴ�״̬
	 */
	private String state = "";

	/**
	 * �ļ�����
	 */
	private String type = "";

	/**
	 * �ļ��ϴ�ǰ����
	 */
	private String originalName = "";

	/**
	 * �ļ���С
	 */
	private long size = 0;

	/**
	 * �ļ��ϴ�����
	 */
	private String title = "";

	/**
	 * �ļ��ϴ��洢·��
	 */
	private String savePath = "upload";

	/**
	 * �ļ��ϴ���׺������
	 */
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png",
			".jpg", ".jpeg", ".bmp" };

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String[] getAllowFiles() {
		return allowFiles;
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

}
