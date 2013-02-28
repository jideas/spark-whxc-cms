package com.spark.cms.services.order;

import java.util.List;

import com.spark.cms.common.Constant.OrderEnum.PayType;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.order.key.GetEffectedOrderListKey;
import com.spark.cms.services.order.key.GetUnEffectedOrderListKey;
import com.spark.cms.services.vo.OrderDetVo;
import com.spark.cms.services.vo.OrderLogVo;
import com.spark.front.form.order.OrderInfo;

public interface OrderService {

	/**
	 * 查询已生效订单列表
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	List<OrderInfo> getList(GetEffectedOrderListKey key) throws ServiceMessage;

	/**
	 * 查询已生效订单总条数
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	int getCount(GetEffectedOrderListKey key) throws ServiceMessage;

	/**
	 * 查询已生效订单明细
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	List<OrderDetVo> getEffectedOrderDetList(String orderId) throws ServiceMessage;
	
	/**
	 * 查询已生效订单详情
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	OrderInfo getEffectedOrder(String orderId) throws ServiceMessage;
	
	/**
	 * 查询未生效订单列表
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	List<OrderInfo> getList(GetUnEffectedOrderListKey key) throws ServiceMessage;

	/**
	 * 查询未生效订单总条数
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	int getCount(GetUnEffectedOrderListKey key) throws ServiceMessage;
	/**
	 * 查询未生效订单详情
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	OrderInfo getUnEffectedOrder(String orderId) throws ServiceMessage;
	/**
	 * 查询未生效订单明细
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	List<OrderDetVo> getUnEffectedOrderDetList(String orderId) throws ServiceMessage;
	
	/**
	 * 新增订单
	 * @param info
	 * @throws Throwable
	 */
	String createOrder(OrderInfo info) throws ServiceMessage;
	
	/**
	 * 新增订单入口
	 */
	List<String> createOrders(List<OrderInfo> list) throws ServiceMessage;
	
	/**
	 * 订单生效
	 * @param info
	 * @throws Throwable
	 */
	void exeEffectiveOrder(String orderId,PayType payType) throws ServiceMessage;
	/**
	 * 删除无效订单
	 */
	void deleteOrders() throws ServiceMessage;
	/**
	 * 订单追踪
	 */
	List<OrderLogVo> getLogs(String orderId);
}
