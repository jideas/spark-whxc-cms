package com.spark.cms.services.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.SheetNumberType;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.Constant;
import com.spark.cms.common.Constant.Order.OnlineOrderStatus;
import com.spark.cms.common.Constant.OrderEnum.PayType;
import com.spark.cms.dao.po.EffectedOrderDetPo;
import com.spark.cms.dao.po.EffectedOrderPo;
import com.spark.cms.dao.po.OrderDetPo;
import com.spark.cms.dao.po.OrderLogPo;
import com.spark.cms.dao.po.OrderPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.order.OrderService;
import com.spark.cms.services.order.key.GetEffectedOrderListKey;
import com.spark.cms.services.order.key.GetUnEffectedOrderListKey;
import com.spark.cms.services.serial.SerialNumberService;
import com.spark.cms.services.vo.MemberVantagesVo;
import com.spark.cms.services.vo.OrderDetVo;
import com.spark.cms.services.vo.OrderLogVo;
import com.spark.front.form.order.OrderInfo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	private SerialNumberService numberService;
	@Autowired
	private MemberService memberService;

	@Override
	public List<OrderInfo> getList(GetEffectedOrderListKey key)
			throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from EffectedOrderPo p ");
		hql.append(" where p.memberid=?");
		List<Object> params = new ArrayList<Object>();
		params.add(GUID.valueOf(key.getMemberId()).toBytes());
		if (key.isLog()) {
			hql.append(" and p.status=?");
			params.add(OnlineOrderStatus.Finished.getCode());
		} else {
			hql.append(" and p.status<>?");
			params.add(OnlineOrderStatus.Finished.getCode());
		}
		Date todayOfLastMonth = new Date(DateUtil.getDayStartTime(DateUtil
				.getTodayOfLastMonth(new Date(System.currentTimeMillis()))
				.getTime()));
		if (key.isThisMonth()) {
			hql.append(" and p.createdate>=? ");
			params.add(todayOfLastMonth);
		} else {
			hql.append(" and p.createdate<? ");
			params.add(todayOfLastMonth);
		}
		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			String searchString = key.getSearchText().trim();
			hql.append(" and (p.billsno like '%" + searchString + "%' ");
			hql.append(" or p.consignee like '%" + searchString + "%' ");
			hql.append(")");
		}
		hql.append(" order by p.createdate desc ");

		List<EffectedOrderPo> list = new ArrayList<EffectedOrderPo>();
		if (key.getPageSize() > 0) {

			list = this.genericDAO.getGenericByPosition(hql.toString(), (key
					.getOffset() - 1)
					* key.getPageSize(), key.getPageSize(), params
					.toArray(new Object[0]));
		} else {

			list = this.genericDAO.getGenericByHql(hql.toString(), params
					.toArray(new Object[0]));
		}
		List<OrderInfo> rList = new ArrayList<OrderInfo>();
		for (EffectedOrderPo p : list) {
			OrderInfo info = BeanCopy.copy(OrderInfo.class, p);
			List<OrderDetVo> dets = this.getEffectedOrderDetList(GUID.valueOf(
					p.getRecid()).toString());
			info.setDets(dets);
			rList.add(info);
		}
		return rList;
	}

	@Override
	public int getCount(GetEffectedOrderListKey key) throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(recid) from EffectedOrderPo p ");
		hql.append(" where p.memberid=?");
		List<Object> params = new ArrayList<Object>();
		params.add(GUID.valueOf(key.getMemberId()).toBytes());
		if (key.isLog()) {
			hql.append(" and p.status=?");
			params.add(OnlineOrderStatus.Finished.getCode());
		} else {
			hql.append(" and p.status<>?");
			params.add(OnlineOrderStatus.Finished.getCode());
		}
		Date todayOfLastMonth = new Date(DateUtil.getDayStartTime(DateUtil
				.getTodayOfLastMonth(new Date(System.currentTimeMillis()))
				.getTime()));
		if (key.isThisMonth()) {
			hql.append(" and p.createdate>=? ");
			params.add(todayOfLastMonth);
		} else {
			hql.append(" and p.createdate<? ");
			params.add(todayOfLastMonth);
		}
		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			String searchString = key.getSearchText().trim();
			hql.append(" and (p.billsno like '%" + searchString + "%' ");
			hql.append(" or p.consignee like '%" + searchString + "%' ");
			hql.append(")");
		}

		return this.genericDAO.getGenericCountByHql(hql.toString(), params
				.toArray(new Object[0]));
	}

	@Override
	public List<OrderDetVo> getEffectedOrderDetList(String orderId)
			throws ServiceMessage {
		if (CheckIsNull.isEmpty(orderId)) {
			throw new ServiceMessage("orderId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from EffectedOrderDetPo p ");
		hql.append(" where p.billsid=?");

		List<EffectedOrderDetPo> list = this.genericDAO.getGenericByHql(hql
				.toString(), GUID.valueOf(orderId).toBytes());
		List<OrderDetVo> rList = new ArrayList<OrderDetVo>();
		for (EffectedOrderDetPo p : list) {
			rList.add(BeanCopy.copy(OrderDetVo.class, p));
		}
		return rList;
	}

	@Override
	public List<OrderDetVo> getUnEffectedOrderDetList(String orderId)
			throws ServiceMessage {
		if (CheckIsNull.isEmpty(orderId)) {
			throw new ServiceMessage("orderId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from OrderDetPo p ");
		hql.append(" where p.billsid=?");

		List<OrderDetPo> list = this.genericDAO.getGenericByHql(hql.toString(),
				GUID.valueOf(orderId).toBytes());
		List<OrderDetVo> rList = new ArrayList<OrderDetVo>();
		for (OrderDetPo p : list) {
			rList.add(BeanCopy.copy(OrderDetVo.class, p));
		}
		return rList;
	}

	@Override
	public List<OrderInfo> getList(GetUnEffectedOrderListKey key)
			throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from OrderPo p ");
		hql.append(" where p.memberid=?");
		if (key.isThisMonth()) {
			hql.append(" and p.createdate>=? ");
		} else {
			hql.append(" and p.createdate<? ");
		}
		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			String searchString = key.getSearchText().trim();
			hql.append(" and (p.billsno like '%" + searchString + "%' ");
			hql.append(" or p.consignee like '%" + searchString + "%' ");
			hql.append(")");
		}
		hql.append(" order by p.createdate desc ");
		Date todayOfLastMonth = new Date(DateUtil.getDayStartTime(DateUtil
				.getTodayOfLastMonth(new Date(System.currentTimeMillis()))
				.getTime()));
		List<OrderPo> list = new ArrayList<OrderPo>();
		if (key.getPageSize() > 0) {

			list = this.genericDAO.getGenericByPosition(hql.toString(), (key
					.getOffset() - 1)
					* key.getPageSize(), key.getPageSize(), GUID.valueOf(
					key.getMemberId()).toBytes(), todayOfLastMonth);
		} else {
			list = this.genericDAO.getGenericByHql(hql.toString(), GUID
					.valueOf(key.getMemberId()).toBytes(), todayOfLastMonth);
		}
		List<OrderInfo> rList = new ArrayList<OrderInfo>();
		for (OrderPo p : list) {
			OrderInfo info = BeanCopy.copy(OrderInfo.class, p);
			List<OrderDetVo> dets = this.getUnEffectedOrderDetList(GUID
					.valueOf(p.getRecid()).toString());
			info.setDets(dets);
			rList.add(info);
		}
		return rList;
	}

	@Override
	public int getCount(GetUnEffectedOrderListKey key) throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(recid) from OrderPo p ");
		hql.append(" where p.memberid=?");
		if (key.isThisMonth()) {
			hql.append(" and p.createdate>=? ");
		} else {
			hql.append(" and p.createdate<? ");
		}
		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			String searchString = key.getSearchText().trim();
			hql.append(" and (p.billsno like '%" + searchString + "%' ");
			hql.append(" or p.consignee like '%" + searchString + "%' ");
			hql.append(")");
		}
		Date todayOfLastMonth = new Date(DateUtil.getDayStartTime(DateUtil
				.getTodayOfLastMonth(new Date(System.currentTimeMillis()))
				.getTime()));
		return this.genericDAO.getGenericCountByHql(hql.toString(), GUID
				.valueOf(key.getMemberId()).toBytes(), todayOfLastMonth);
	}

	@Override
	public String createOrder(OrderInfo info) throws ServiceMessage {
		if (CheckIsNull.isEmpty(info.getDets())) {
			throw new ServiceMessage("订单明细不能为空！");
		}
		GUID id = GUID.randomID();
		if (info.isVantagesCostOrder()) {
			EffectedOrderPo ep = new EffectedOrderPo();
			try {
				// BeanUtils.copyProperties1(info, ep);
				ep = BeanCopy.copy(EffectedOrderPo.class, info);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			ep.setRecid(id.toBytes());
			ep.setBillsno(numberService
					.exeGetSheetNumber(SheetNumberType.OnlineOrder));
			ep.setStatus(OnlineOrderStatus.Effected.getCode());

			this.genericDAO.save(ep);
			for (OrderDetVo dp : info.getDets()) {
				EffectedOrderDetPo edp = new EffectedOrderDetPo();
				try {
					edp = BeanCopy.copy(EffectedOrderDetPo.class, dp);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				edp.setRecid(GUID.randomID().toBytes());
				edp.setBillsid(id.toBytes());
				this.genericDAO.save(edp);
			}
			MemberVantagesVo v = new MemberVantagesVo();
			v.setMemberid(info.getMemberid());
			v.setOccurdate(new Date(System.currentTimeMillis()));
			v.setRecid(GUID.randomID().toString());
			v.setRelabillsid(id.toString());
			v.setRelabillsno(ep.getBillsno());
			v.setVantages(0 - info.getVantagesCost());
			v.setVtype(Constant.MemberEnum.VantagesType.Consume.getCode());
			this.memberService.createVantages(v);
			saveOrderLog(GUID.valueOf(ep.getRecid()), "您提交了订单，等待系统确认", "系统");
			saveOrderLog(GUID.valueOf(ep.getRecid()), "您的订单已付款", "系统");
			return id.toString();
		}

		OrderPo p = BeanCopy.copy(OrderPo.class, info);
		p.setRecid(id.toBytes());
		p.setBillsno(numberService
				.exeGetSheetNumber(SheetNumberType.OnlineOrder));
		p.setStatus(OnlineOrderStatus.Paying.getCode());
		this.genericDAO.save(p);
		saveOrderDet(info, id);
		saveOrderLog(id, "您提交了订单，等待付款", "客户");
		return id.toString();
	}

	private void saveOrderLog(GUID id, String message, String operator) {
		OrderLogPo p = new OrderLogPo();
		p.setRecid(GUID.randomID().toBytes());
		p.setBillsId(id.toBytes());
		p.setMessage(message);
		p.setOperator(operator);
		p.setProcessingTime(new Date(System.currentTimeMillis()));
		this.genericDAO.save(p);
	}

	private void saveOrderDet(OrderInfo info, GUID id) {
		for (OrderDetVo v : info.getDets()) {
			OrderDetPo p = BeanCopy.copy(OrderDetPo.class, v);
			p.setRecid(GUID.randomID().toBytes());
			p.setBillsid(id.toBytes());
			this.genericDAO.save(p);
		}

	}

	@Override
	public void exeEffectiveOrder(String orderId,PayType payType) throws ServiceMessage {
		if (CheckIsNull.isEmpty(orderId)) {
			throw new ServiceMessage("orderId不能为空！");
		}
		if (CheckIsNull.isEmpty(payType)) {
			throw new ServiceMessage("付款方式不能为空！");
		}
		OrderPo p = this.genericDAO.get(OrderPo.class, GUID.valueOf(orderId)
				.toBytes());
		if (null == p) {
			throw new ServiceMessage("单据不存在！");
		}
		if (p.isToDoor() && p.getLockedDeliveryBalance() > 0) {
			/**
			 * 执行解锁上门送货包月
			 */
			this.memberService.exeLockDeliveryBalance(GUID.valueOf(
					p.getMemberid()).toString(), false, p);
		}
		EffectedOrderPo ep = new EffectedOrderPo();
		ep = BeanCopy.copy(EffectedOrderPo.class, p);
		ep.setStatus(OnlineOrderStatus.Effected.getCode());
		ep.setPayType(payType.getCode());
		this.genericDAO.save(ep);
		this.genericDAO.delete(OrderPo.class, GUID.valueOf(orderId).toBytes());
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from OrderDetPo p ");
		hsql.append(" where billsid=? ");
		List<OrderDetPo> list = this.genericDAO.getGenericByHql(
				hsql.toString(), GUID.valueOf(orderId).toBytes());
		for (OrderDetPo dp : list) {
			EffectedOrderDetPo edp = new EffectedOrderDetPo();
			edp = BeanCopy.copy(EffectedOrderDetPo.class, dp);
			this.genericDAO.save(edp);
			this.genericDAO.delete(OrderDetPo.class, dp.getRecid());
		}
		saveOrderLog(GUID.valueOf(p.getRecid()), "您的订单已付款", "系统");
	}

	@Override
	public OrderInfo getEffectedOrder(String orderId) throws ServiceMessage {
		if (CheckIsNull.isEmpty(orderId)) {
			throw new ServiceMessage("id不能为空！");
		}
		EffectedOrderPo p = this.genericDAO.get(EffectedOrderPo.class, GUID
				.valueOf(orderId).toBytes());
		if (null == p) {
			return null;
		}
		OrderInfo info = BeanCopy.copy(OrderInfo.class, p);
		List<OrderDetVo> dets = this.getEffectedOrderDetList(orderId);
		info.setDets(dets);
		List<OrderLogVo> logs = this.getLogs(orderId);
		info.setLogs(logs);
		return info;
	}

	@Override
	public OrderInfo getUnEffectedOrder(String orderId) throws ServiceMessage {
		if (CheckIsNull.isEmpty(orderId)) {
			throw new ServiceMessage("id不能为空！");
		}
		OrderPo p = this.genericDAO.get(OrderPo.class, GUID.valueOf(orderId)
				.toBytes());
		if (null == p) {
			return null;
		}
		OrderInfo info = BeanCopy.copy(OrderInfo.class, p);
		List<OrderDetVo> dets = this.getUnEffectedOrderDetList(orderId);
		info.setDets(dets);
		List<OrderLogVo> logs = this.getLogs(orderId);
		info.setLogs(logs);
		return info;
	}

	@Override
	public List<OrderLogVo> getLogs(String orderId) {
		if (CheckIsNull.isEmpty(orderId)) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from OrderLogPo p ");
		hql.append(" where billsId=?");
		hql.append(" order by processingTime asc ");

		List<OrderLogPo> list = this.genericDAO.getGenericByHql(hql.toString(),
				GUID.valueOf(orderId).toBytes());
		List<OrderLogVo> rlist = BeanCopy.copys(OrderLogVo.class, list);
		return rlist;
	}

	@Override
	public List<String> createOrders(List<OrderInfo> list)
			throws ServiceMessage {
		List<String> l = new ArrayList<String>();
		if (CheckIsNull.isEmpty(list)) {
			throw new ServiceMessage("订单信息不能为空！");
		}
		for (OrderInfo o : list) {
			if (o.isVantagesCostOrder()) {
				this.createOrder(o);
			} else {
				l.add(this.createOrder(o));
			}
		}
		return l;
	}

	@Override
	public void deleteOrders() throws ServiceMessage {
		StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		hql.append(" from OrderPo p ");
		hql.append(" where p.createdate<? ");
		params.add(new Date(DateUtil.getHalfHourAgo(null)));
		List<OrderPo> list = this.genericDAO.getGenericByHql(hql.toString(),
				params.toArray(new Object[0]));
		for (OrderPo p : list) {
			if (p.isToDoor() && p.getLockedDeliveryBalance() > 0) {
				StringBuffer sb = new StringBuffer();
				List<Object> params1 = new ArrayList<Object>();
				sb.append(" update MemberDeliveryPo p ");
				sb
						.append(" set lockedDeliveryBalance=(p.lockedDeliveryBalance-1) ");
				sb.append(" where p.member=? ");
				params1.add(p.getMemberid());
				sb.append(" and p.lockedDeliveryBalance>0 ");
				int count = this.genericDAO.execteBulk(sb.toString(), params1
						.toArray(new Object[0]));
				if(count>0)
				{
					StringBuffer hsql = new StringBuffer();
					hsql.append(" delete from OrderDetPo p ");
					hsql.append(" where p.billsid=? ");
					this.genericDAO.execteBulk(hsql.toString(), p.getRecid());
					StringBuffer lsql = new StringBuffer();
					lsql.append(" delete from OrderLogPo p ");
					lsql.append(" where p.billsId=? ");
					this.genericDAO.execteBulk(lsql.toString(), p.getRecid());
					this.genericDAO.delete(OrderPo.class, p.getRecid());
				}
			}
			else
			{
				StringBuffer hsql = new StringBuffer();
				hsql.append(" delete from OrderDetPo p ");
				hsql.append(" where p.billsid=? ");
				this.genericDAO.execteBulk(hsql.toString(), p.getRecid());
				StringBuffer lsql = new StringBuffer();
				lsql.append(" delete from OrderLogPo p ");
				lsql.append(" where p.billsId=? ");
				this.genericDAO.execteBulk(lsql.toString(), p.getRecid());
				this.genericDAO.delete(OrderPo.class, p.getRecid());
			}
		}
	}

}
