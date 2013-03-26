package com.spark.cms.action.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.inventory.InventoryService;

@Controller
public class InventoryAction extends BaseAction{
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping("/inventoryService/modifyMaterialStatisticalLedger")
	@ResponseBody
	public ResponseEntity<MessageModel> modifyMaterialStatisticalLedger() throws ServiceMessage {
		try {
			this.inventoryService.modifyStatisticalLedger("01");
			return new ServiceMessage(true, "").getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修正台帐数据异常 ====" + e.getStackTrace());
		}
		return null;
	}
	
	@RequestMapping("/inventoryService/modifyGoodsStatisticalLedger")
	@ResponseBody
	public ResponseEntity<MessageModel> modifyGoodsStatisticalLedger() throws ServiceMessage {
		try {
			this.inventoryService.modifyStatisticalLedger("02");
			return new ServiceMessage(true, "").getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修正台帐数据异常 ====" + e.getStackTrace());
		}
		return null;
	}
	
	@RequestMapping("/inventoryService/modifyGoodsSerialData")
	@ResponseBody
	public ResponseEntity<MessageModel> modifyGoodsSerialData() throws ServiceMessage {
		try {
			this.inventoryService.modifyGoodsSerialData();
			return new ServiceMessage(true, "ok").getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修正库存流水数据异常 ====" + e.getStackTrace());
		}
		return null;
	}
	@RequestMapping("/inventoryService/modifyMaterialSerialData")
	@ResponseBody
	public ResponseEntity<MessageModel> modifyMaterialSerialData() throws ServiceMessage {
		try {
			this.inventoryService.modifyMaterialSerialData();
			return new ServiceMessage(true, "ok").getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修正库存流水数据异常 ====" + e.getStackTrace());
		}
		return null;
	}
	
	
}
