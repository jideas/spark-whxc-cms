/**
 * 
 */
package com.spark.cms.services.image.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.dao.po.ImageInfoPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.image.ImageService;
import com.spark.cms.services.vo.ImageInfoVo;
import com.spark.front.utils.CmsString;

/**
 * @author Jideas
 * 
 */
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private GenericDAO baseDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.image.ImageService#createImage(com.spark.cms.services.vo.ImageInfoVo)
	 */
	@Override
	public void createImage(ImageInfoVo vo) {
		vo.setRecid(GUID.randomID().toString());
		this.baseDAO.save(BeanCopy.copy(ImageInfoPo.class, vo));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.image.ImageService#getImage(java.util.String)
	 */
	@Override
	public ImageInfoVo getImage(String url) {
		String hql = "from ImageInfoPo as t where t.imgUrl=?";
		List<ImageInfoPo> polist = this.baseDAO.getGenericByHql(hql, url);
		if (null == polist) {
			return null;
		}
		return BeanCopy.copy(ImageInfoVo.class, polist.get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.image.ImageService#getImages(java.lang.String)
	 */
	@Override
	public List<ImageInfoVo> getImages(String folderId, String text) {
		String ss = "";
		List<Object> params = new ArrayList<Object>();
		if (CmsString.isNotEmpty(folderId)&&!"null".equals(folderId) && CmsString.isNotEmpty(text)) {
			ss = "from ImageInfoPo as t where t.folderId=? and t.imgName like ? order by t.imgName ,t.imgWidth";
			params.add(folderId);
			params.add(text);
		} else if (CmsString.isNotEmpty(folderId)&&!"null".equals(folderId)) {
			ss = "from ImageInfoPo as t where t.folderId=? order by t.imgName ,t.imgWidth";
			params.add(folderId);
		} else {
			ss = "from ImageInfoPo as t where t.imgName like ? order by t.imgName ,t.imgWidth";
			params.add("%"+text+"%");
		}
		List<ImageInfoPo> polist = this.baseDAO.getGenericByHql(ss, params.toArray());
		if (null == polist || polist.isEmpty()) {
			return null;
		}
		return BeanCopy.copys(ImageInfoVo.class, polist);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.image.ImageService#deleteImage(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void deleteImage(String url, HttpServletRequest request) throws ServiceMessage {
		String hql = "delete from ImageInfoPo as t where t.imgUrl=?";
		int count = this.baseDAO.execteBulk(hql, url.trim());
		if (count != 1) {
			throw new ServiceMessage(false, "É¾³ýÍ¼Æ¬Òì³££¡");
		}
		File file = new File(request.getRealPath("") + url);
		if (!file.exists()) {
			return;
		}
		file.delete();
	}

}
