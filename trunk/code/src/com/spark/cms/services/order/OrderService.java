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
	 * ��ѯ����Ч�����б�
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	List<OrderInfo> getList(GetEffectedOrderListKey key) throws ServiceMessage;

	/**
	 * ��ѯ����Ч����������
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	int getCount(GetEffectedOrderListKey key) throws ServiceMessage;

	/**
	 * ��ѯ����Ч������ϸ
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	List<OrderDetVo> getEffectedOrderDetList(String orderId) throws ServiceMessage;
	
	/**
	 * ��ѯ����Ч��������
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	OrderInfo getEffectedOrder(String orderId) throws ServiceMessage;
	
	/**
	 * ��ѯδ��Ч�����б�
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	List<OrderInfo> getList(GetUnEffectedOrderListKey key) throws ServiceMessage;

	/**
	 * ��ѯδ��Ч����������
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	int getCount(GetUnEffectedOrderListKey key) throws ServiceMessage;
	/**
	 * ��ѯδ��Ч��������
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	OrderInfo getUnEffectedOrder(String orderId) throws ServiceMessage;
	/**
	 * ��ѯδ��Ч������ϸ
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	List<OrderDetVo> getUnEffectedOrderDetList(String orderId) throws ServiceMessage;
	
	/**
	 * ��������
	 * @param info
	 * @throws Throwable
	 */
	String createOrder(OrderInfo info) throws ServiceMessage;
	
	/**
	 * �����������
	 */
	List<String> createOrders(List<OrderInfo> list) throws ServiceMessage;
	
	/**
	 * ������Ч
	 * @param info
	 * @throws Throwable
	 */
	void exeEffectiveOrder(String orderId,PayType payType) throws ServiceMessage;
	/**
	 * ɾ����Ч����
	 */
	void deleteOrders() throws ServiceMessage;
	/**
	 * ����׷��
	 */
	List<OrderLogVo> getLogs(String orderId);
}
