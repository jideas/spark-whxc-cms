package com.spark.cms.services.card.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.SheetNumberType;
import com.spark.cms.base.constant.card.CMS;
import com.spark.cms.base.constant.card.CardStatus;
import com.spark.cms.base.constant.gift.GiftReason;
import com.spark.cms.base.constant.gift.GiftStatus;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.base.utils.encrypt.EncryptionUtil;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.Constant.MemberEnum.DealingsType;
import com.spark.cms.common.Constant.MemberEnum.VantagesType;
import com.spark.cms.dao.po.CardDistributeLogPo;
import com.spark.cms.dao.po.CardPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.CardPromotion.CardPmtService;
import com.spark.cms.services.card.CardService;
import com.spark.cms.services.card.GetCardListKey;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.gift.GiftService;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.serial.SerialNumberService;
import com.spark.cms.services.station.StationService;
import com.spark.cms.services.vo.CardPromotionVo;
import com.spark.cms.services.vo.CardVo;
import com.spark.cms.services.vo.GiftVo;
import com.spark.cms.services.vo.MemberAccountVo;
import com.spark.cms.services.vo.MemberDealingVo;
import com.spark.cms.services.vo.MemberVantagesVo;
import com.spark.cms.services.vo.StationVo;
import com.spark.front.utils.CmsString;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private GenericDAO baseDAO;

	@Autowired
	private SerialNumberService sheetNumberService;

	@Autowired
	private StationService stationService;

	@Autowired
	private CardPmtService cardPmtService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private GiftService giftService;

	/*
	 * (non-Javadoc) 查询卡片列表
	 * 
	 * @see com.spark.cms.services.card.CardService#getList(com.spark.cms.services
	 *      .card.GetCardListKey)
	 */
	@Override
	public DataModel<CardVo> getList(GetCardListKey key) {
		List<CardPo> list = null;
		StringBuilder hql = new StringBuilder();
		hql.append("from CardPo as t");
		hql.append(" where ");
		hql.append(" t.status");
		hql.append(" ='");
		hql.append(key.getStatus().getCode());
		hql.append("' ");
		List<Object> params = new ArrayList<Object>();
		String dateColumn = "createdate";
		if (key.getStatus() == CardStatus.Created
				|| key.getStatus() == CardStatus.Printed) {
			dateColumn = "createdate";
		} else if (key.getStatus() == CardStatus.Actived) {
			dateColumn = "activedate";
		} else if (key.getStatus() == CardStatus.Distributed) {
			dateColumn = "distributedate";
		} else if (key.getStatus() == CardStatus.Used) {
			dateColumn = "useddate";
		} else if (key.getStatus() == CardStatus.Canceled) {
			dateColumn = "stopdate";
		}
		if (key.getBeginDate() != null) {
			hql.append(" and ");
			hql.append(" t." + dateColumn + ">=? ");
			params.add(key.getBeginDate());
		}
		if (key.getEndDate() != null) {
			hql.append(" and ");
			hql.append(" t." + dateColumn + "<=? ");
			params.add(key.getEndDate());
		}
		if (key.getAmount() > 0) {
			hql.append(" and ");
			hql.append(" t.amount= " + key.getAmount() + " ");
		}
		hql.append(" order by ");
		hql.append(" t." + key.getSortField().getField() + " "
				+ key.getSortType().name());
		int count = Integer.parseInt(this.baseDAO.getGenericByHql(
				"select count(t.recid) " + hql.toString(), params.toArray())
				.get(0)
				+ "");
		if (key.getPageSize() == 0) {
			list = this.baseDAO.getGenericByHql(hql.toString(), params
					.toArray());
		} else {
			list = this.baseDAO.getGenericByPosition(hql.toString(), (key
					.getOffset() - 1)
					* key.getPageSize(), key.getPageSize(), params.toArray());
		}
		return new DataModel<CardVo>(BeanCopy.copys(CardVo.class, list), count);
	}

	/*
	 * (non-Javadoc) 根据id查询卡片
	 * 
	 * @see com.spark.cms.services.card.CardService#find(java.lang.String)
	 */
	@Override
	public CardVo find(String recid) {
		return BeanCopy.copy(CardVo.class, this.baseDAO.get(CardVo.class, GUID
				.valueOf(recid).toBytes()));
	}

	/*
	 * (non-Javadoc) 修改卡片信息
	 * 
	 * @see com.spark.cms.services.card.CardService#modfiy(com.spark.cms.service.
	 *      vo.CardVo)
	 */
	@Override
	public void modify(CardVo vo, Login login) {
		baseDAO.update(BeanCopy.copy(CardPo.class, vo));
	}

	/*
	 * (non-Javadoc) 批量生成卡片
	 * 
	 * @see com.spark.cms.services.card.CardService#createCards(double, int,
	 *      java.lang.String)
	 */
	@Override
	public List<CardVo> createCards(double amount, int count, String secretKey,
			Date lastDate, Login login) {
		if (login == null) {
			return null;
		}
		if (count == 0) {
			return null;
		}
		if (null == secretKey) {
			return null;
		}
		if (amount == 0) {
			return null;
		}
		String saveKey;
		try {
			saveKey = EncryptionUtil.encryptAES(secretKey, CMS.CommonSecretKey);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String cardNo = sheetNumberService.exeGetSheetNumber(
				SheetNumberType.CardNumber, true);
		cardNo = cardNo.substring(9);
		List<String> list = makeSixteenStr(count);
		List<CardVo> volist = new ArrayList<CardVo>();
		for (int i = 0; i < count; i++) {
			CardVo vo = new CardVo();
			vo.setRecid(GUID.randomID().toString());
			vo.setAmount(amount);
			vo.setCreatedate(new Date());
			vo.setCreator(login.getName());
			vo.setCreatorid(login.getRecid());
			vo.setLastDate(lastDate.getTime() + 24 * 3600000 - 1);
			vo.setSecretkey(saveKey);
			String sheetNo = sheetNumberService.exeGetSheetNumber(
					SheetNumberType.CardOrdinal, true);
			vo.setOrdinal(sheetNo);
			vo.setCardno(CMS.CardNoPrefix + cardNo + list.get(i));
			try {
				String pass = list.get(i + count);
				// System.err.println("面值卡卡号：" + vo.getCardno() + ",密码：" +
				// pass);
				vo.setPassword(EncryptionUtil.encryptAES(EncryptionUtil
						.encryptAES(pass, secretKey), CMS.CommonSecretKey));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			vo.setOrdinal(sheetNo);
			vo.setStatus(CardStatus.Created.getCode());
			CardPo po = null;
			try {
				po = BeanCopy.copy(CardPo.class, vo);
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			this.baseDAO.save(po);
			volist.add(vo);
		}
		return volist;
	}

	private List<String> makeSixteenStr(int count) {
		Set<String> set = new HashSet<String>();
		int size = 0;
		while (size < count * 2) {
			double i = Math.random();
			String s = DoubleUtil.getRoundStrWithOutQfw(i, 8);
			s = s.replace("4", "8");
			if (set.add(s.substring(s.indexOf(".") + 1))) {
				size++;
			}
		}
		return new ArrayList<String>(set);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#delete(java.util.List,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public void deleteAll(List<String> cardIds) throws ServiceMessage {
		StringBuilder ss = new StringBuilder("delete CardPo as t ");
		ss.append(" where ");
		ss.append(" t.recid in (");
		List<Object> params = new ArrayList<Object>();
		int i = 0;
		for (String s : cardIds) {
			if (i > 0) {
				ss.append(",");
			}
			params.add(GUID.valueOf(s).toBytes());
			i++;
			ss.append("?");
		}
		ss.append(")");
		ss.append(" and ( ");
		ss.append(" t.status =");
		ss.append("'");
		ss.append(CardStatus.Created.getCode());
		ss.append("' or t.status='" + CardStatus.Printed.getCode() + "'");
		int count = this.baseDAO.execteBulk(ss.toString(), params.toArray());
		if (count != cardIds.size()) {
			throw new ServiceMessage("部分数据不能删除，请检查！");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#delete(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public void delete(String cardId) throws ServiceMessage {
		StringBuilder ss = new StringBuilder("delete CardPo as t ");
		ss.append(" where ");
		ss.append(" t.recid = ?");
		ss.append(" and ( ");
		ss.append(" t.status =");
		ss.append("'");
		ss.append(CardStatus.Created.getCode());
		ss.append("' or t.status='" + CardStatus.Printed.getCode() + "')");
		int count = this.baseDAO.execteBulk(ss.toString(), GUID.valueOf(cardId)
				.toBytes());
		if (count != 1) {
			throw new ServiceMessage("部分数据不能删除，请检查！");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#exeActiveCards(java.util.List,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public void exeActiveCards(List<String> cardIds, Login login)
			throws ServiceMessage {
		if (null == login) {
			return;
		}
		List<Object> params = new ArrayList<Object>();
		StringBuilder ss = new StringBuilder("update CardPo as t set");
		ss.append(" status=");
		ss.append("'" + CardStatus.Actived.getCode() + "'");
		ss.append(",");
		ss.append("activedate=?");
		params.add(new Date());
		ss.append(",");
		ss.append("activeperson=");
		ss.append("'" + login.getName() + "'");
		ss.append(",activepersonid=");
		ss.append(" ? ");
		params.add(GUID.valueOf(login.getRecid()).toBytes());
		ss.append(" where ");
		ss.append(" t.recid in (");
		int i = 0;
		for (String s : cardIds) {
			if (i > 0) {
				ss.append(",");
			}
			params.add(GUID.valueOf(s).toBytes());
			i++;
			ss.append("?");
		}
		ss.append(")");
		ss.append(" and ");
		ss.append(" t.status =");
		ss.append("'");
		ss.append(CardStatus.Printed.getCode());
		ss.append("'");
		int count = this.baseDAO.execteBulk(ss.toString(), params.toArray());
		if (count != cardIds.size()) {
			throw new ServiceMessage("部分数据操作失败，请检查！");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#exeReActivingCards(java.util.
	 *      List, com.spark.cms.services.form.Login)
	 */
	@Override
	public void exeReActivingCards(List<String> cardIds, Login login)
			throws ServiceMessage {
		List<Object> params = new ArrayList<Object>();
		StringBuilder ss = new StringBuilder("update CardPo as t set");
		ss.append(" status=");
		ss.append("'" + CardStatus.Actived.getCode() + "'");
		ss.append(",");
		ss.append("activedate=?");
		params.add(new Date());
		ss.append(",");
		ss.append("activeperson=");
		ss.append("'" + login.getName() + "'");
		ss.append(",activepersonid=");
		params.add(GUID.valueOf(login.getRecid()).toBytes());
		ss.append(" ? ");
		ss.append(" where ");
		ss.append(" t.recid in (");
		int i = 0;
		for (String s : cardIds) {
			if (i > 0) {
				ss.append(",");
			}
			params.add(GUID.valueOf(s).toBytes());
			i++;
			ss.append("?");
		}
		ss.append(")");
		ss.append(" and ");
		ss.append(" t.status =");
		ss.append("'");
		ss.append(CardStatus.Canceled.getCode());
		ss.append("'");
		int count = this.baseDAO.execteBulk(ss.toString(), params.toArray());
		if (count != cardIds.size()) {
			throw new ServiceMessage("部分数据操作失败，请检查！");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#exePrintCards(java.util.List,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public void exePrintCards(List<String> cardIds, Login login)
			throws ServiceMessage {
		StringBuilder ss = new StringBuilder("update CardPo as t set");
		ss.append(" status=");
		ss.append("'" + CardStatus.Printed.getCode() + "'");
		ss.append(" where ");
		ss.append(" t.recid in (");
		List<Object> params = new ArrayList<Object>();
		int i = 0;
		for (String s : cardIds) {
			if (i > 0) {
				ss.append(",");
			}
			params.add(GUID.valueOf(s).toBytes());
			i++;
			ss.append("?");
		}
		ss.append(")");
		ss.append(" and ");
		ss.append(" t.status =");
		ss.append("'");
		ss.append(CardStatus.Created.getCode());
		ss.append("'");
		int count = this.baseDAO.execteBulk(ss.toString(), params.toArray());
		if (count != cardIds.size()) {
			throw new ServiceMessage("部分数据操作失败，请检查！");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#exeDistributeCards(java.util.
	 *      List, java.lang.String, com.spark.cms.services.form.Login)
	 */
	@Override
	public void exeDistributeCards(List<String> cardIds, String stationId,
			String personName, Login login) throws ServiceMessage {
		if (null == login) {
			return;
		}
		List<Object> params = new ArrayList<Object>();
		StringBuilder ss = new StringBuilder("update CardPo as t set");
		ss.append(" status=");
		ss.append("'" + CardStatus.Distributed.getCode() + "'");
		ss.append(",distributepersonid=?");
		params.add(GUID.valueOf(login.getRecid()).toBytes());
		ss.append(",distributeperson=?");
		params.add(login.getName());
		ss.append(",distributedate=?");
		params.add(new Date());
		ss.append(",responsibleperson=?");
		params.add(personName);
		ss.append(",stationId=?");
		params.add(GUID.valueOf(stationId).toBytes());
		ss.append(",stationname=?");
		StationVo station = stationService.find(stationId);
		params.add(station.getStationname());
		ss.append(" where ");
		ss.append(" t.recid in (");
		int i = 0;
		for (String s : cardIds) {
			if (i > 0) {
				ss.append(",");
			}
			i++;
			ss.append("?");
			params.add(GUID.valueOf(s).toBytes());
		}
		ss.append(")");
		ss.append(" and (");
		ss.append(" t.status =");
		ss.append("'");
		ss.append(CardStatus.Actived.getCode());
		ss.append("' or t.status ='" + CardStatus.Distributed.getCode() + "')");
		int count = this.baseDAO.execteBulk(ss.toString(), params
				.toArray(new Object[params.size()]));
		if (count != cardIds.size()) {
			throw new ServiceMessage("部分数据操作失败，请检查！");
		} else {
			for (String s : cardIds) {
				CardDistributeLogPo dis = new CardDistributeLogPo();
				dis.setCardid(GUID.valueOf(s).toBytes());
				dis.setRecid(GUID.randomID().toBytes());
				dis.setDistributepersonid(GUID.valueOf(login.getRecid())
						.toBytes());
				dis.setDistributeperson(login.getName());
				dis.setDistributedate(new Date());
				dis.setResponsibleperson(personName);
				this.baseDAO.save(dis);
			}
		}
	}

	private CardVo getCardVoByCardNo(String cardNo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from CardPo as t");
		hql.append(" where t.cardno=?");
		List<CardPo> polist = this.baseDAO.getGenericByHql(hql.toString(),
				cardNo);
		if (polist == null || polist.isEmpty()) {
			return null;
		}
		CardVo vo = BeanCopy.copy(CardVo.class, polist.get(0));
		return vo;
	}

	private int doUseCard(CardVo vo, Login login) {
		StringBuilder ss = new StringBuilder("update CardPo as t set");
		ss.append(" status=");
		ss.append("'" + CardStatus.Used.getCode() + "'");
		ss.append(",");
		ss.append("useddate=?");
		ss.append(",");
		ss.append("usedperson=");
		ss.append("'"
				+ (CheckIsNull.isNotEmpty(login.getName()) ? login.getName()
						: login.getUsername()) + "'");
		ss.append(",usedpersonid=");
		ss.append(" ? ");
		ss.append(" where ");
		ss.append(" t.recid =");
		ss.append(" ? ");
		ss.append(" and ");
		ss.append(" t.status in (");
		ss.append("'");
		ss.append(CardStatus.Actived.getCode());
		ss.append("',");
		ss.append("'");
		ss.append(CardStatus.Distributed.getCode());
		ss.append("'");
		ss.append(")");
		int count = this.baseDAO.execteBulk(ss.toString(), new Date(), GUID
				.valueOf(login.getRecid()).toBytes(), GUID.valueOf(
				vo.getRecid()).toBytes());
		return count;
	}

	/*
	 * (non-Javadoc)充值
	 * 
	 * @see com.spark.cms.services.card.CardService#exeUseCards(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public ServiceMessage exeUseCard(String cardNo, String password, Login login)
			throws ServiceMessage {
		if (CheckIsNull.isEmpty(cardNo)) {
			return new ServiceMessage(false, "卡号不可以为空！");
		}
		if (CheckIsNull.isEmpty(password)) {
			return new ServiceMessage(false, "密码不可以为空！");
		}
		CardVo vo = this.getCardVoByCardNo(cardNo);
		if (vo == null) {
			return new ServiceMessage(false, "卡号不存在，请检查！");
		}
		if (!vo.getStatus().equals(CardStatus.Actived.getCode())
				&& !vo.getStatus().equals(CardStatus.Distributed.getCode())) {
			return new ServiceMessage(false, "该面值卡状态异常，请联系客服！");
		}
		if (vo.getLastDate() <= new Date().getTime()) {
			return new ServiceMessage(false, "该面值卡已过期，请检查！");
		}
		String saveKey;
		try {
			saveKey = EncryptionUtil.decryptAES(vo.getSecretkey(),
					CMS.CommonSecretKey);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String dataPass = null;
		try {
			dataPass = EncryptionUtil.encryptAES(EncryptionUtil.encryptAES(
					password, saveKey), CMS.CommonSecretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!dataPass.equals(vo.getPassword())) {
			return new ServiceMessage(false, "充值密码错误，请重试！");
		}

		int count = doUseCard(vo, login);
		if (count != 1) {
			throw new ServiceMessage("部分数据操作失败，请检查！");
		} else {
			doWriteDealing(vo, login);
			CardPromotionVo pmt = this.cardPmtService.findByCardAmount(vo
					.getAmount());
			MemberAccountVo balance = this.memberService
					.getMemberAccountVo(login.getRecid());
			if (null == pmt) {
				return new ServiceMessage(true, "充值成功，您的余额为："
						+ DoubleUtil.getRoundStr(balance.getMoneybalance())
						+ "元。");
			}
			if (pmt.getVantages() > 0) {
				doWriteVtg(vo, login, pmt.getVantages());
			} else if (pmt.getExtraAmount() > 0) {
				doWriteDealingPmt(vo, pmt, login);
			} else {
				doWriteGift(vo, login, pmt);
			}
			balance = this.memberService.getMemberAccountVo(login.getRecid());
			return new ServiceMessage(true, "充值成功，您的余额为："
					+ DoubleUtil.getRoundStr(balance.getMoneybalance())
					+ "元,积分余额："
					+ DoubleUtil.getRoundStr(balance.getVantages(), 0) + "分。");
		}

	}

	/**
	 * @param pmt
	 * @param login
	 * @throws ServiceMessage
	 */
	private void doWriteDealingPmt(CardVo vo, CardPromotionVo pmt, Login login)
			throws ServiceMessage {
		MemberDealingVo deal = new MemberDealingVo();
		deal.setRecid(GUID.randomID().toString());
		deal.setAmount(pmt.getExtraAmount());
		deal.setDealtype(DealingsType.Card.getCode());
		deal.setMemberid(login.getRecid());
		deal.setOccurdate(new Date());
		deal.setRelabillsid(vo.getRecid());
		deal.setRelabillsno(vo.getCardno());
		this.memberService.createDealing(deal);

	}

	/**
	 * 充值有礼
	 */
	private void doWriteGift(CardVo vo, Login login, CardPromotionVo pmt) {
		GiftVo gift = new GiftVo();
		gift.setRecid(GUID.randomID().toString());
		gift.setStatus(GiftStatus.Created.getCode());
		gift.setGoodscount(pmt.getGoodsCount());
		gift.setGoodsid(pmt.getGoodsId());
		gift.setMemberid(login.getRecid());
		gift.setReason(GiftReason.Charge.getCode());
		gift.setLastdate(new Date(new Date().getTime() + CMS.GiftLifeLength));
		this.giftService.createGift(gift);
	}

	/**
	 * 充值送积分
	 * 
	 * @throws ServiceMessage
	 */
	private void doWriteVtg(CardVo vo, Login login, double vantages)
			throws ServiceMessage {
		MemberVantagesVo vtg = new MemberVantagesVo();
		vtg.setRecid(GUID.randomID().toString());
		vtg.setMemberid(login.getRecid());
		vtg.setOccurdate(new Date());
		vtg.setRelabillsid(vo.getRecid());
		vtg.setRelabillsno(vo.getCardno());
		vtg.setVantages(vantages);
		vtg.setVtype(VantagesType.Card.getCode());
		this.memberService.createVantages(vtg);
	}

	/**
	 * 充值改变余额
	 * 
	 * @param vo
	 * @param login
	 * @throws ServiceMessage
	 */
	private void doWriteDealing(CardVo vo, Login login) throws ServiceMessage {
		MemberDealingVo deal = new MemberDealingVo();
		deal.setRecid(GUID.randomID().toString());
		deal.setAmount(vo.getAmount());
		deal.setDealtype(DealingsType.Card.getCode());
		deal.setMemberid(login.getRecid());
		deal.setOccurdate(new Date());
		deal.setRelabillsid(vo.getRecid());
		deal.setRelabillsno(vo.getCardno());
		this.memberService.createDealing(deal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#exeStopCard(java.lang.String,
	 *      com.spark.cms.services.form.Login)
	 */
	@Override
	public void exeStopCard(String cardId, Login login) throws ServiceMessage {
		if (null == login) {
			return;
		}
		StringBuilder ss = new StringBuilder("update CardPo as t set");
		ss.append(" status=");
		ss.append("'" + CardStatus.Canceled.getCode() + "'");
		ss.append(",");
		ss.append("stopdate=?");
		ss.append(",t.usedperson='" + login.getName() + "'");
		ss.append(" where ");
		ss.append(" t.recid =");
		ss.append(" ? ");
		ss.append(" and ");
		ss.append(" t.status <>");
		ss.append("'");
		ss.append(CardStatus.Created.getCode());
		ss.append("'");
		int count = this.baseDAO.execteBulk(ss.toString(), new Date(), GUID
				.valueOf(cardId).toBytes());
		if (count != 1) {
			throw new ServiceMessage("部分数据操作失败，请检查！");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#getVoListByIds(java.util.List)
	 */
	@Override
	public List<CardVo> getVoListPrintingByIds(List<String> cardIds) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from CardPo as t");
		hql.append(" where ");
		hql.append(" t.status = ? ");
		params.add(CardStatus.Created.getCode());
		hql.append(" and t.recid in (");
		int index = 0;
		for (String id : cardIds) {
			if (index > 0) {
				hql.append(",");
			}
			hql.append("?");
			index++;
			params.add(GUID.valueOf(id).toBytes());
		}
		hql.append(")");
		hql.append(" order by t.cardno");
		List<CardPo> polist = this.baseDAO.getGenericByHql(hql.toString(),
				params.toArray());
		List<CardVo> volist = BeanCopy.copys(CardVo.class, polist);
		for (int i = 0; i < volist.size(); i++) {
			CardVo vo = volist.get(i);
			String password = vo.getPassword();
			try {
				password = EncryptionUtil.decryptAES(password,
						CMS.CommonSecretKey);
				password = EncryptionUtil.decryptAES(password, EncryptionUtil
						.decryptAES(vo.getSecretkey(), CMS.CommonSecretKey));
				vo.setPassword(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return volist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.card.CardService#importExcel(com.spark.cms.services.vo.CardVo)
	 */
	@Override
	public void exeImportExcel(CardVo vo) throws Exception {
		if (null == vo) {
			throw new ServiceMessage(false, "");
		}
		vo.setRecid(GUID.randomID().toString());
		vo.setCreatedate(new Date());
		vo.setSecretkey(CmsString.getRandomNumStr(4));
		String password = vo.getPassword();
		password = EncryptionUtil.encryptAES(password, vo.getSecretkey());
		password = EncryptionUtil.encryptAES(password, CMS.CommonSecretKey);
		vo.setPassword(password);
		vo.setSecretkey(EncryptionUtil.encryptAES(vo.getSecretkey(),
				CMS.CommonSecretKey));
		vo.setStatus(CardStatus.Distributed.getCode());
		this.baseDAO.save(BeanCopy.copy(CardPo.class, vo));
	}
}
