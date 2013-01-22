package com.spark.cms.action.contentMng;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.channel.ChannelService;
import com.spark.cms.services.channel.GetChannelContentListKey;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.ChannelContentVo;

@Controller
public class ContentAction extends BaseAction {

	@Autowired
	private ChannelService channelService;

	/**
	 * ��ȡ�����б�
	 */
	@RequestMapping("/content/getContents")
	@ResponseBody
	public DataModel<ChannelContentVo> getContents(@RequestParam(value = "id", required = true)
	String channelId, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows) {
		DataModel<ChannelContentVo> dm = new DataModel<ChannelContentVo>();
		try {
			GetChannelContentListKey key = new GetChannelContentListKey();
			key.setChannelId(channelId);
			List<ChannelContentVo> list = channelService.getChannelContentVos(key);
			if (StringUtil.isNotEmpty(page)) {
				key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
				key.setPageSize(Integer.parseInt(rows));
			}
			dm.setRows(list);
			dm.setTotal(channelService.getChannelContentCount(key));
			return dm;
		} catch (Exception e) {
			log.error("��ȡ�����б����쳣====" + e.getStackTrace());
		}
		return dm;
	}

	/**
	 * ��������
	 */
	@RequestMapping("/content/addContent")
	@ResponseBody
	public ResponseEntity<MessageModel> addContent(ChannelContentVo content,
			HttpSession session) {
		try {
			UserExtForm userExtForm = (UserExtForm) session
					.getAttribute(Constant.LoginAdminUser);
			content.setCreatedate(new Date());
			content.setCreatorid(userExtForm.getId());
			content.setCreator(userExtForm.getName());

			channelService.createChannelContent(content);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("�������ݷ����쳣====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * �޸�����
	 */
	@RequestMapping("/content/updateContent")
	@ResponseBody
	public ResponseEntity<MessageModel> updateContent(ChannelContentVo content) {
		try {
			channelService.modifyChannelContent(content);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("�޸����ݷ����쳣====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * ɾ������
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/deleteContent")
	@ResponseBody
	public MessageModel deleteContent(
			@RequestParam(value = "id", required = true)
			String id) {
		try {
			channelService.deleteChannelContent(id);
			return Success;
		} catch (Exception e) {
			log.error("ɾ�����ݷ����쳣====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * ��������
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/enabledContent")
	@ResponseBody
	public MessageModel enabledContent(
			@RequestParam(value = "recid", required = false)
			String recid) {
		try {
			channelService.updateChannelContentStatus(recid, true);
			return Success;
		} catch (Exception e) {
			log.error("�������ݷ����쳣====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * ��������
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/disabledContent")
	@ResponseBody
	public MessageModel disabledContent(
			@RequestParam(value = "recid", required = false)
			String recid) {
		try {
			channelService.updateChannelContentStatus(recid, false);
			return Success;
		} catch (Exception e) {
			log.error("�������ݷ����쳣====" + e.getStackTrace());
			return Failure;
		}
	}

}
