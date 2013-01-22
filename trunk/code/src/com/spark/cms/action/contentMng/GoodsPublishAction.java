package com.spark.cms.action.contentMng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.services.goods.GoodsService;

@Controller
public class GoodsPublishAction extends BaseAction {

	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 商品发布 ->  选择商品
	 */
	@RequestMapping("/goodsPublish/selectGoods")
	@ResponseBody
	public MessageModel selectGoods(@RequestParam(value = "ids[]", required = true) String[] ids) {
		try {
			
			return Success;
		} catch (Exception e) {
			log.error("选择商品发生异常====" + e.getStackTrace());
			return Failure;
		}
	}
	
}
