/**
 * 
 */
package com.spark.cms.services.image;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.vo.ImageInfoVo;

/**
 * @author Jideas
 * 
 */
public interface ImageService {

	void createImage(ImageInfoVo vo);

	List<ImageInfoVo> getImages(String folderId,String text);

	void deleteImage(String url,HttpServletRequest request) throws ServiceMessage;

	/**
	 * ����ͼƬ��ַ��ѯͼƬ��Ϣ
	 * @param url
	 */
	ImageInfoVo getImage(String url);
}
