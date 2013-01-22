package com.spark.cms.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spark.cms.common.MessageModel;

public class BaseAction {
	protected Logger log = LoggerFactory.getLogger(BaseAction.class);
	protected MessageModel Success = new MessageModel(true, "操作成功!");
	protected MessageModel Failure = new MessageModel(false, "操作失败!");
}
