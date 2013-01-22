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
import com.spark.cms.services.channel.GetChannelAdvertisingKey;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.ChannelAdvertisingVo;

@Controller
public class AdvertisingAction extends BaseAction {

	@Autowired
	private ChannelService channelService;
	/**
	 * 获取广告列表
	 */
	@RequestMapping("/advertising/getAdvertisings")
	@ResponseBody
	public DataModel<ChannelAdvertisingVo> getAdvertisings(
			@RequestParam(value = "id", required = true) String channelId, 
			@RequestParam(value = "page", required = false) String page, 
			@RequestParam(value = "rows", required = false) String rows) {
		DataModel<ChannelAdvertisingVo> dm = new DataModel<ChannelAdvertisingVo>();
		try {			
			GetChannelAdvertisingKey key = new GetChannelAdvertisingKey();
			key.setChannelId(channelId);
			if (StringUtil.isNotEmpty(page)) {
				key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
				key.setPageSize(Integer.parseInt(rows));
			}
			List<ChannelAdvertisingVo> list = channelService
					.getChannelAdvertisingVos(key);
			dm.setRows(list);
			dm.setTotal(channelService.getChannelAdvertisingCount(key));
			return dm;
		} catch (Exception e) {
			log.error("获取广告列表发生异常====" + e.getStackTrace());
		}
		return dm;
	}

	/**
	 * 新增广告
	 */
	@RequestMapping("/advertising/addAdvertising")
	@ResponseBody
	public ResponseEntity<MessageModel> addAdvertising(ChannelAdvertisingVo advertising,HttpSession session) {
		try {
			UserExtForm userExtForm = (UserExtForm)session.getAttribute(Constant.LoginAdminUser);
			advertising.setCreatedate(new Date());
			advertising.setCreatorid(userExtForm.getId());
			advertising.setCreator(userExtForm.getName());
			
			channelService.createChannelAdvertising(advertising);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("新增广告发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 修改广告
	 */
	@RequestMapping("/advertising/updateAdvertising")
	@ResponseBody
	public ResponseEntity<MessageModel> updateAdvertising(ChannelAdvertisingVo advertising) {
		try {
			channelService.modifyChannelAdvertising(advertising);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("修改广告发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 删除广告
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/advertising/deleteAdvertising")
	@ResponseBody
	public MessageModel deleteAdvertising(
			@RequestParam(value = "id", required = true)
			String id) {
		try {
			channelService.deleteChannelAdvertising(id);
			return Success;
		} catch (Exception e) {
			log.error("删除广告发生异常====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * 启用广告
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/advertising/enabledAdvertising")
	@ResponseBody
	public MessageModel enabledAdvertising(@RequestParam(value = "recid", required = false) String recid) {
		try {
			channelService.updateChannelAdvertisingStatus(recid, true);
			return Success;
		} catch (Exception e) {
			log.error("启用广告发生异常====" + e.getStackTrace());
			e.getStackTrace();
			return Failure;
		}
	}

	/**
	 * 禁用广告
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/advertising/disabledAdvertising")
	@ResponseBody
	public MessageModel disabledAdvertising(@RequestParam(value = "recid", required = false) String recid) {
		try {
			channelService.updateChannelAdvertisingStatus(recid, false);
			return Success;
		} catch (Exception e) {
			log.error("禁用广告发生异常====" + e.getStackTrace());
			return Failure;
		}
	}

}
