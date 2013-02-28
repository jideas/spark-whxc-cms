/**
 * 
 */
package com.spark.cms.services.payment;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.payment.PayingBillStatus;
import com.spark.cms.base.constant.payment.PayingBillType;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.Constant.MemberEnum.DealingsType;
import com.spark.cms.common.Constant.OrderEnum.PayType;
import com.spark.cms.dao.po.PayingBillsPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.order.OrderService;
import com.spark.cms.services.vo.MemberDealingVo;
import com.spark.cms.services.vo.PayingBillsVo;

/**
 * @author Jideas
 * 
 */
@Service
public class PayingBillsServiceImpl implements PayingBillsService {

	@Autowired
	private GenericDAO baseDAO;

	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.payment.PayingBillsService#createPayingBill(com.spark.cms.services.vo.PayingBillsVo)
	 */
	@Override
	public boolean createPayingBill(PayingBillsVo vo) {
		Object id = this.baseDAO.save(BeanCopy.copy(PayingBillsPo.class, vo));
		if (null == id) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.payment.PayingBillsService#deletePayingBill(java.lang.String)
	 */
	@Override
	public boolean deleteOldBill() {
		StringBuilder ss = new StringBuilder();
		ss.append(" delete PayingBillsPo as t ");
		ss.append(" where createTime<?");
		this.baseDAO.execteBulk(ss.toString(), new Date().getTime() - 48 * 3600000);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.payment.PayingBillsService#exeUpdateStatus(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public boolean exeUpdateStatus(PayingBillsVo vo, String status, boolean must) throws Throwable {
		StringBuilder hql = new StringBuilder();
		hql.append(" update PayingBillsPo as t set ");
		hql.append(" status=?");
		hql.append(" where recid=? ");
		int i = 0;
		if (!must) {
			hql.append(" and status=?");
			i = this.baseDAO.execteBulk(hql.toString(), status, GUID.valueOf(vo.getRecid()).toBytes(),
					PayingBillStatus.Paying.getCode());
		} else {
			i = this.baseDAO.execteBulk(hql.toString(), status, GUID.valueOf(vo.getRecid()).toBytes());
		}
		if (i != 1) {
			throw new ServiceMessage(false, "");
		}
		if (status.equals(PayingBillStatus.Failure.getCode())) {
			return true;
		}
		if (PayingBillType.UnionCharge.getCode().equals(vo.getPayType())) {
			chargeSuccess(vo);
		} else if (PayingBillType.UnionOrder.getCode().equals(vo.getPayType())) {
			orderSuccess(vo);
		}
		return true;
	}

	/**
	 * @param vo
	 * @return
	 * @throws Throwable
	 */
	private boolean orderSuccess(PayingBillsVo vo) throws Throwable {
		String ids[] = vo.getRelaBillsId().split(",");
		for (String orderId : ids) {
			this.orderService.exeEffectiveOrder(orderId, PayType.getPayType(vo.getPayType()));
		}
		return true;
	}

	/**
	 * @param vo
	 * @return
	 * @throws ServiceMessage
	 */
	private boolean chargeSuccess(PayingBillsVo vo) throws ServiceMessage {
		MemberDealingVo deal = new MemberDealingVo();
		deal.setAmount(DoubleUtil.div(DoubleUtil.strToDouble(vo.getAmount()), 100));
		deal.setDealtype(DealingsType.Charge.getCode());
		deal.setMemberid(vo.getRelaBillsId());
		deal.setOccurdate(new Date());
		deal.setRelabillsid(vo.getRecid());
		deal.setRelabillsno(vo.getOrderId());
		this.memberService.createDealing(deal);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.payment.PayingBillsService#findPayingBill(java.lang.String)
	 */
	@Override
	public PayingBillsVo findPayingBill(String recid) {
		PayingBillsPo po = this.baseDAO.get(PayingBillsPo.class, GUID.valueOf(recid).toBytes());
		if (null == po) {
			return null;
		}
		return BeanCopy.copy(PayingBillsVo.class, po);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.payment.PayingBillsService#getAllList()
	 */
	@Override
	public List<PayingBillsVo> getAllList() {
		StringBuilder hql = new StringBuilder();
		hql.append(" from PayingBillsPo as t ");
		hql.append(" where t.status = ?");
		hql.append(" and t.createTime>?");
		long thatTime = new Date().getTime() - 72 * 3600000;
		List<PayingBillsPo> polist = this.baseDAO.getGenericByHql(hql.toString(), PayingBillStatus.Paying.getCode(),
				thatTime);
		if (null == polist || polist.isEmpty()) {
			return null;
		}
		return BeanCopy.copys(PayingBillsVo.class, polist);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.payment.PayingBillsService#findPayingBillByOrderNo(java.lang.String)
	 */
	@Override
	public PayingBillsVo findPayingBillByOrderNo(String orderNo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from PayingBillsPo as t ");
		hql.append(" where t.status = ? and t.orderId=? ");
		List<PayingBillsPo> polist = this.baseDAO.getGenericByHql(hql.toString(), PayingBillStatus.Paying.getCode(),
				orderNo);
		if(polist==null&&polist.isEmpty()){
			return null;
		} 
		return BeanCopy.copy(PayingBillsVo.class, polist.get(0));
	}

}
