package com.spark.cms.services.member.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.system.dic.DictionaryType;
import com.spark.base.common.system.dic.SparkDictionaryManager;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.hibernate.impl.BaseDAOImpl;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.SheetNumberType;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.base.utils.encrypt.EncryptionUtil;
import com.spark.cms.common.Constant;
import com.spark.cms.common.Constant.MemberEnum.VantagesType;
import com.spark.cms.dao.po.MemberAccountPo;
import com.spark.cms.dao.po.MemberAddressPo;
import com.spark.cms.dao.po.MemberDealingPo;
import com.spark.cms.dao.po.MemberDeliverLogPo;
import com.spark.cms.dao.po.MemberDeliveryPo;
import com.spark.cms.dao.po.MemberInfoPo;
import com.spark.cms.dao.po.MemberPo;
import com.spark.cms.dao.po.MemberVantagesPo;
import com.spark.cms.dao.po.OrderPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.member.MemberForm;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.member.key.GetDealingListKey;
import com.spark.cms.services.member.key.GetMemberListKey;
import com.spark.cms.services.member.key.GetVantagesListKey;
import com.spark.cms.services.serial.SerialNumberService;
import com.spark.cms.services.station.StationService;
import com.spark.cms.services.vo.MemberAccountVo;
import com.spark.cms.services.vo.MemberActiveVo;
import com.spark.cms.services.vo.MemberAddressVo;
import com.spark.cms.services.vo.MemberChargeFlowVo;
import com.spark.cms.services.vo.MemberDealingVo;
import com.spark.cms.services.vo.MemberDeliverLogVo;
import com.spark.cms.services.vo.MemberDeliveryVo;
import com.spark.cms.services.vo.MemberInfoVo;
import com.spark.cms.services.vo.MemberVantagesVo;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.StationVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	private BaseDAOImpl baseDao;

	@Autowired
	private SerialNumberService numberService;

	@Autowired
	private StationService stationService;

	@Override
	public ServiceMessage createMember(MemberVo v) {
		GUID id = GUID.randomID();
		MemberPo p = new MemberPo();
		try {
			p = BeanCopy.copy(MemberPo.class, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServiceMessage sMessage = hasDuplicate(v);
		if (!sMessage.isOperSuccess()) {
			return sMessage;
		}
		p.setRecid(id.toBytes());
		p.setCode(numberService.exeGetSheetNumber(SheetNumberType.MemberCode));
		p.setStatus(Constant.MemberEnum.MemberStatus.Actived.getCode());
		p.setPassword(EncryptionUtil.encryptMD5(v.getPassword()));
		baseDao.save(p);
		MemberAccountPo ap = new MemberAccountPo();
		ap.setRecid(p.getRecid());
		ap.setMoneybalance(0d);
		ap.setVantages(0d);
		this.baseDao.save(ap);
		v.setRecid(id.toString());
		v.setStatus(Constant.MemberEnum.MemberStatus.Actived.getName());
		return new ServiceMessage(true, "注册成功！");
	}
	
	@Override
	public ServiceMessage createMember(MemberForm v) {
		GUID id = GUID.randomID();
		MemberPo p = new MemberPo();
		try {
			p = BeanCopy.copy(MemberPo.class, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setRecid(id.toBytes());
		p.setCode(numberService.exeGetSheetNumber(SheetNumberType.MemberCode));
		p.setStatus(Constant.MemberEnum.MemberStatus.Actived.getCode());
		p.setPassword(EncryptionUtil.encryptMD5(v.getPassword()));
		baseDao.save(p);
		MemberAccountPo ap = new MemberAccountPo();
		ap.setRecid(p.getRecid());
		ap.setMoneybalance(v.getMoneybalance());
		ap.setVantages(v.getVantages());
		ap.setPaypassword(v.getPaypassword());
		this.baseDao.save(ap);
		MemberInfoPo info = new MemberInfoPo();
		info.setRecid(p.getRecid());
		info.setMembername(v.getMembername());
		info.setSex(v.getSex());
		info.setBirthday(v.getBirthday());
		info.setMobile(v.getMobile());
		info.setTelephone(v.getTelephone());
		info.setAddress(v.getAddress());
		this.baseDao.save(info);
		v.setRecid(id.toString());
		v.setStatus(Constant.MemberEnum.MemberStatus.Actived.getName());
		return new ServiceMessage(true, "注册成功！");
	}

	/**
	 * @param form
	 * @return
	 */
	private ServiceMessage hasDuplicate(MemberVo form) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from MemberPo as t ");
		hql.append(" where t.username =? ");
		hql.append(" or t.mobile=? ");
		hql.append(" or t.email=?");
		List<MemberPo> polist = this.baseDao.getGenericByHql(hql.toString(), form.getUsername(), form.getMobile(), form
				.getEmail());
		if (null == polist || polist.isEmpty()) {
			return new ServiceMessage(true, "");
		}
		for (MemberPo po : polist) {
			if (po.getEmail().equals(form.getEmail())) {
				return new ServiceMessage(false, "该邮箱已注册！", 1);
			}
			if (po.getMobile().equals(form.getMobile())) {
				return new ServiceMessage(false, "该手机已注册！", 2);
			}
			if (po.getUsername().equals(form.getUsername())) {
				return new ServiceMessage(false, "该用户名已存在！", 3);
			}
		}
		return new ServiceMessage(true, "");
	}

	@Override
	public void exeActivate(String id) throws ServiceMessage {
		StringBuffer hql = new StringBuffer();
		hql.append("update MemberPo mp ");
		hql.append(" set mp.status='");
		hql.append(Constant.MemberEnum.MemberStatus.Actived.getCode());
		hql.append("'");
		hql.append(" where mp.recid=?");
		if (genericDAO.execteBulk(hql.toString(), GUID.valueOf(id).toBytes()) < 1) {
			throw new ServiceMessage("您操作的数据已发生改变，请检查！");
		}

	}

	@Override
	public void modifyMemberStatus(String id, boolean stop) throws ServiceMessage {
		StringBuffer hql = new StringBuffer();
		hql.append("update MemberPo mp ");
		hql.append(" set mp.status='");
		if (stop) {
			hql.append(Constant.MemberEnum.MemberStatus.Stoped.getCode());
		} else
			hql.append(Constant.MemberEnum.MemberStatus.Actived.getCode());
		hql.append("'");
		hql.append(" where mp.recid=?");
		if (genericDAO.execteBulk(hql.toString(), GUID.valueOf(id).toBytes()) < 1) {
			throw new ServiceMessage("您操作的数据已发生改变，请检查！");
		}

	}

	@Override
	public void modifyPassword(String id, String oldPassword, String newPassword) throws ServiceMessage {
		StringBuffer hql = new StringBuffer();
		hql.append("update MemberPo mp ");
		hql.append(" set mp.password=?");
		hql.append(" where mp.recid=? and mp.password=?");
		if (genericDAO.execteBulk(hql.toString(), EncryptionUtil.encryptMD5(newPassword), GUID.valueOf(id).toBytes(),
				EncryptionUtil.encryptMD5(oldPassword)) < 1) {
			throw new ServiceMessage("当前密码输入错误，请检查！");
		}
	}

	@Override
	public void modifyPassword(String id, String newPassword) throws ServiceMessage {
		StringBuffer hql = new StringBuffer();
		hql.append("update MemberPo mp ");
		hql.append(" set mp.password=?");
		hql.append(" where mp.recid=?");
		if (genericDAO.execteBulk(hql.toString(), EncryptionUtil.encryptMD5(newPassword), GUID.valueOf(id).toBytes()) < 1) {
			throw new ServiceMessage("您操作的数据已发生改变，请检查！");
		}
	}

	@Override
	public MemberVo find(String id) {
		MemberPo p = genericDAO.get(MemberPo.class, GUID.valueOf(id).toBytes());
		MemberVo v = new MemberVo();
		copyMemberVo(v, p);
		return v;
	}

	@Override
	public List<MemberVo> getList(GetMemberListKey key) {
		List<MemberVo> rList = new ArrayList<MemberVo>();
		StringBuffer hsql = new StringBuffer();
		hsql.append("from MemberPo mp where 1=1");
		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			String searchText = key.getSearchText().trim();
			hsql.append(" and (mp.code like '%").append(searchText).append("%'");
			hsql.append(" or mp.membername like '%").append(searchText).append("%'");
			hsql.append(" or mp.username like '%").append(searchText).append("%'");
			hsql.append(" or mp.mobile like '%").append(searchText).append("%'");
			hsql.append(" or mp.email like '%").append(searchText).append("%')");

		}
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			hsql.append(" and SUBSTRING(mp.code,3,8) >= '").append(key.getBeginDate().replace("-", "")).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			hsql.append(" and SUBSTRING(mp.code,3,8) <= '").append(key.getEndDate().replace("-", "")).append("'");
		}
		if (CheckIsNull.isNotEmpty(key.getSortType())) {
			hsql.append(" order by mp.code ").append(key.getSortType());
		}
		List<MemberPo> list = new ArrayList<MemberPo>();
		if (key.getOffset() > 0) {
			list = genericDAO.getGenericByPosition(hsql.toString(), ((key.getOffset() - 1) * key.getPageSize()), key
					.getPageSize());
		} else {
			list = genericDAO.getGenericByHql(hsql.toString());
		}
		for (MemberPo p : list) {
			MemberVo v = new MemberVo();
			copyMemberVo(v, p);
			rList.add(v);
		}
		return rList;
	}

	@Override
	public int getCount(GetMemberListKey key) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select count(recid) from MemberPo mp where 1=1");
		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			String searchText = key.getSearchText().trim();
			hsql.append(" and (mp.code like '%").append(searchText).append("%'");
			hsql.append(" or mp.membername like '%").append(searchText).append("%'");
			hsql.append(" or mp.username like '%").append(searchText).append("%'");
			hsql.append(" or mp.mobile like '%").append(searchText).append("%'");
			hsql.append(" or mp.email like '%").append(searchText).append("%') ");

		}
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			hsql.append(" and SUBSTRING(mp.code,3,8) >= '").append(key.getBeginDate().replace("-", "")).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			hsql.append(" and SUBSTRING(mp.code,3,8) <= '").append(key.getEndDate().replace("-", "")).append("'");
		}
		if (CheckIsNull.isNotEmpty(key.getSortType())) {
			hsql.append(" order by mp.code ").append(key.getSortType());
		}
		return genericDAO.getGenericCountByHql(hsql.toString());
	}
	
	/**
	 * 获取会员金额
	 */
	@Override
	public double getSumMoney(GetMemberListKey key) {
		StringBuilder hsql = new StringBuilder();
		hsql.append(" select sum(map.moneybalance)");
		hsql.append(" from cms_member as mp,cms_member_account map");
		hsql.append(" where HEX(mp.recid) = HEX(map.recid)");
		if (CheckIsNull.isNotEmpty(key.getSearchText())) {
			String searchText = key.getSearchText().trim();
			hsql.append(" and (mp.code like '%").append(searchText).append("%'");
			hsql.append(" or mp.membername like '%").append(searchText).append("%'");
			hsql.append(" or mp.username like '%").append(searchText).append("%'");
			hsql.append(" or mp.mobile like '%").append(searchText).append("%'");
			hsql.append(" or mp.email like '%").append(searchText).append("%') ");

		}
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			hsql.append(" and SUBSTRING(mp.code,3,8) >= '").append(key.getBeginDate().replace("-", "")).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			hsql.append(" and SUBSTRING(mp.code,3,8) <= '").append(key.getEndDate().replace("-", "")).append("'");
		}
		List<BigDecimal> mapList = genericDAO.query(hsql.toString());
		if(mapList.get(0) == null) return 0;
		return mapList.get(0).doubleValue();
	}

	private void copyMemberVo(MemberVo v, MemberPo p) {
		v.setEmail(p.getEmail());
		v.setInvitationcode(p.getInvitationcode());
		v.setIsoffical(p.isIsoffical());
		v.setMembername(p.getMembername());
		v.setMobile(p.getMobile());
		v.setPassword(p.getPassword());
		v.setRecid(GUID.valueOf(p.getRecid()).toString());
		// Constant.MemberEnum.MemberStatus.getStatus(p.getStatus()).getName()
		v.setStatus(p.getStatus());
		v.setUsername(p.getUsername());
		v.setCode(p.getCode());
		v.setCheckedMobile(p.isCheckedMobile());
	}

	@Override
	public void modifyMemberInfo(MemberInfoVo v) {
		String del = "delete MemberInfoPo as t where t.recid=?";
		this.genericDAO.execteBulk(del, GUID.valueOf(v.getMemberid()).toBytes());
		MemberInfoPo p = new MemberInfoPo();
		try {
			p = BeanCopy.copy(MemberInfoPo.class, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setRecid(GUID.valueOf(v.getMemberid()).toBytes());
		this.genericDAO.save(p);

	}

	@Override
	public void modifyMember(MemberVo v) throws ServiceMessage {
		if (CheckIsNull.isEmpty(v.getRecid())) {
			return;
		}
		MemberPo p = this.genericDAO.get(MemberPo.class, GUID.valueOf(v.getRecid()).toBytes());
		if (null == p) {
			throw new ServiceMessage(false, "该会员不存在！");
		}
		try {
			BeanCopy.copyObject(v, p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.genericDAO.update(p);
	}

	@Override
	public void createAdress(MemberAddressVo v) {
		GUID id = GUID.randomID();
		MemberAddressPo p = new MemberAddressPo();
		try {
			p = BeanCopy.copy(MemberAddressPo.class, v);
			p.setMemberid(GUID.tryValueOf(v.getMemberid()).toBytes());
			if (CheckIsNull.isNotEmpty(v.getStationid()))
				p.setStationid(GUID.tryValueOf(v.getStationid()).toBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setRecid(id.toBytes());
		this.genericDAO.save(p);
		v.setRecid(id.toString());
	}

	@Override
	public void modifyAdress(MemberAddressVo v) {
		MemberAddressPo p = new MemberAddressPo();
		try {
			p = BeanCopy.copy(MemberAddressPo.class, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setRecid(GUID.valueOf(v.getRecid()).toBytes());

		this.genericDAO.update(p);
	}

	@Override
	public void deleteAdress(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from MemberAddressPo p ");
		hql.append(" where p.recid=?");
		this.genericDAO.delete(MemberAddressPo.class, GUID.valueOf(id).toBytes());

	}

	@Override
	public void exeDefaultAdress(String id) {
		MemberAddressVo v = this.findAdress(id);
		StringBuffer hql = new StringBuffer();
		hql.append(" update MemberAddressPo p ");
		hql.append(" set p.isdefault=?");
		hql.append(" where p.memberid=?");
		this.genericDAO.execteBulk(hql.toString(), false, GUID.valueOf(v.getMemberid()).toBytes());

		StringBuffer hql1 = new StringBuffer();
		hql1.append(" update MemberAddressPo p ");
		hql1.append(" set p.isdefault=?");
		hql1.append(" where p.recid=?");
		this.genericDAO.execteBulk(hql1.toString(), true, GUID.valueOf(id).toBytes());
	}

	@Override
	public void createDealing(MemberDealingVo v) throws ServiceMessage {
		GUID id = GUID.randomID();
		MemberDealingPo p = new MemberDealingPo();
		try {
			p = BeanCopy.copy(MemberDealingPo.class, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setRecid(id.toBytes());
		p.setMemberid(GUID.valueOf(v.getMemberid()).toBytes());
		p.setRelabillsid(GUID.valueOf(v.getRelabillsid()).toBytes());
		this.genericDAO.save(p);
		MemberAccountPo ap = this.genericDAO.get(MemberAccountPo.class, GUID.valueOf(v.getMemberid()).toBytes());
		if (null != ap) {
			ap.setMoneybalance(ap.getMoneybalance() + v.getAmount());
		} else {
			ap = new MemberAccountPo();
			ap.setMoneybalance(v.getAmount());
			ap.setRecid(GUID.valueOf(v.getMemberid()).toBytes());
		}
		if (ap.getMoneybalance() < 0) {
			throw new ServiceMessage(false, "余额不足，操作失败！");
		}
		this.baseDao.save(ap);
	}

	@Override
	public void createVantages(MemberVantagesVo v) throws ServiceMessage {
		GUID id = GUID.randomID();
		MemberVantagesPo p = new MemberVantagesPo();
		try {
			p = BeanCopy.copy(MemberVantagesPo.class, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setRecid(id.toBytes());
		p.setMemberid(GUID.valueOf(v.getMemberid()).toBytes());
		p.setRelabillsid(GUID.valueOf(v.getRelabillsid()).toBytes());
		this.genericDAO.save(p);
		MemberAccountPo ap = this.genericDAO.get(MemberAccountPo.class, GUID.valueOf(v.getMemberid()).toBytes());
		if (null != ap) {
			if (ap.getVantages() + v.getVantages() < 0) {
				throw new ServiceMessage("您的积分余额不足！");
			}
			ap.setVantages(ap.getVantages() + v.getVantages());
		} else {
			if (v.getVantages() < 0) {
				throw new ServiceMessage("您的积分余额不足！");
			}
			ap = new MemberAccountPo();
			ap.setVantages(v.getVantages());
			ap.setRecid(GUID.valueOf(v.getMemberid()).toBytes());

		}
		this.baseDao.save(ap);

	}

	@Override
	public void createDelivery(MemberDeliveryVo v) {
		String hql = "update MemberDeliveryPo as t set dcount=t.dcount+? where t.member=? and t.begindate=? and t.enddate=?";
		int count = 0;
		try {
			count = this.baseDao.execteBulk(hql, v.getDcount(),GUID.valueOf(v.getMember()).toBytes(), v.getBegindate(), v.getEnddate());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceMessage(false, "增加送货包月失败！");
		}
		if (count > 1) {
			throw new ServiceMessage(false, "增加送货包月失败！");
		} else if (count == 1) {
			return;
		}
		GUID id = GUID.randomID();
		MemberDeliveryPo p = BeanCopy.copy(MemberDeliveryPo.class, v);
		p.setRecid(id.toBytes());
		p.setMember(GUID.valueOf(v.getMember()).toBytes());
		this.genericDAO.save(p);

	}

	@Override
	public void createDeliveryLog(MemberDeliverLogVo v) throws ServiceMessage {
		GUID id = GUID.randomID();
		MemberDeliverLogPo p = new MemberDeliverLogPo();
		try {
			p = BeanCopy.copy(MemberDeliverLogPo.class, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.setRecid(id.toBytes());
		p.setMemberid(GUID.valueOf(v.getMemberid()).toBytes());
		p.setRelabillsid(GUID.valueOf(v.getRelabillsid()).toBytes());
		this.genericDAO.save(p);

		StringBuffer hsql = new StringBuffer();
		hsql.append(" from MemberDeliveryPo p ");
		hsql.append(" where p.member=? ");
		hsql.append(" and p.count>p.counted ");
		hsql.append(" and p.begindate<? and p.enddate>?");
		List<MemberDeliveryPo> list = this.genericDAO.getGenericByHql(hsql.toString(), GUID.valueOf(v.getMemberid())
				.toBytes(), System.currentTimeMillis(), System.currentTimeMillis());
		if (null != list && list.size() > 0) {
			MemberDeliveryPo dp = list.get(0);
			if (v.getCount() < 0 && (dp.getDcount() - dp.getCounted() + v.getCount()) < 0) {
				throw new ServiceMessage("余额不足，请充值！");
			}
			dp.setCounted(dp.getCounted() + v.getCount());
			this.baseDao.save(dp);
		} else {
			if (v.getCount() < 0) {
				throw new ServiceMessage("余额不足，请充值！");
			}
		}
	}

	@Override
	public MemberAddressVo findAdress(String id) {
		MemberAddressPo p = this.genericDAO.get(MemberAddressPo.class, GUID.valueOf(id).toBytes());
		MemberAddressVo v = new MemberAddressVo();
		try {
			v = BeanCopy.copy(MemberAddressVo.class, p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		v.setRecid(GUID.valueOf(p.getRecid()).toString());
		v.setMemberid(GUID.valueOf(p.getMemberid()).toString());
		v.setStationid(GUID.valueOf(p.getStationid()).toString());
		return v;
	}

	@Override
	public List<MemberAddressVo> getAdressList(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from MemberAddressPo p ");
		hql.append(" where p.memberid=?");
		List<MemberAddressPo> poList = this.genericDAO.getGenericByHql(hql.toString(), GUID.valueOf(id).toBytes());
		// List<MemberAddressVo> voList = BeanCopy.copys(MemberAddressVo.class,
		// poList);
		List<MemberAddressVo> voList = new ArrayList<MemberAddressVo>();
		for (MemberAddressPo po : poList) {
			MemberAddressVo vo = BeanCopy.copy(MemberAddressVo.class, po);
			if (CheckIsNull.isNotEmpty(vo.getStationid())) {
				StationVo station = stationService.find(vo.getStationid());
				vo.setStationName(station.getStationname());
			}

			DicItem province = SparkDictionaryManager.getItem(DictionaryType.Area, po.getProvince());
			vo.setProvinceTitle(province.getTitle());

			DicItem city = SparkDictionaryManager.getItem(DictionaryType.Area, po.getCity());
			vo.setCityTitle(city.getTitle());

			DicItem town = SparkDictionaryManager.getItem(DictionaryType.Area, po.getTown());
			vo.setTownTitle(town.getTitle());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public List<MemberDealingVo> getDealingList(GetDealingListKey key) throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from MemberDealingPo p ");
		hql.append(" where p.memberid=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(GUID.valueOf(key.getMemberId()).toBytes());
		if (CheckIsNull.isNotEmpty(key.getBeginDate())) {
			hql.append(" and p.occurdate>=? ");
			params.add(key.getBeginDate());
		}
		if (CheckIsNull.isNotEmpty(key.getEndDate())) {
			hql.append(" and p.occurdate<=? ");
			params.add(key.getEndDate());
		}
		hql.append(" order by p.occurdate desc ");
		List<MemberDealingPo> polist = null;
		if (key.getPageSize() > 0) {
			int offSet = (key.getOffset() - 1) * key.getPageSize();
			polist = this.genericDAO.getGenericByPosition(hql.toString(), offSet, key.getPageSize(), params
					.toArray(new Object[0]));
		} else {
			polist = this.genericDAO.getGenericByHql(hql.toString(), params.toArray(new Object[0]));
		}
		return BeanCopy.copys(MemberDealingVo.class, polist);
	}

	@Override
	public int getDealingCount(GetDealingListKey key) throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(recid) from MemberDealingPo p ");
		hql.append(" where p.memberid=?");
		List<Object> params = new ArrayList<Object>();
		params.add(GUID.valueOf(key.getMemberId()).toBytes());
		if (CheckIsNull.isNotEmpty(key.getBeginDate())) {
			hql.append(" and p.occurdate>=? ");
			params.add(key.getBeginDate());
		}
		if (CheckIsNull.isNotEmpty(key.getEndDate())) {
			hql.append(" and p.occurdate<=? ");
			params.add(key.getEndDate());
		}
		return this.genericDAO.getGenericCountByHql(hql.toString(), params.toArray(new Object[0]));

	}

	@Override
	public List<MemberDeliveryVo> getDeliveryList(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from MemberDeliveryPo p ");
		hql.append(" where p.member=? and p.begindate >=? order by p.begindate ");
		List<MemberDeliveryPo> polist = this.genericDAO.getGenericByHql(hql.toString(), GUID.valueOf(id).toBytes(),
				new Date(DateUtil.getDayStartTime(DateUtil.getThisMonth().getTime())));
		List<MemberDeliveryVo> volist = BeanCopy.copys(MemberDeliveryVo.class, polist);
		List<MemberDeliveryVo> list = new ArrayList<MemberDeliveryVo>();
		Set<Date> set = new HashSet<Date>();
		for (MemberDeliveryVo vo : volist) {
			if (set.add(vo.getBegindate())) {
				list.add(vo);
			} else {
				list.get(list.size() - 1).setDcount(list.get(list.size() - 1).getDcount() + vo.getDcount());
			}
		}
		return list;
	}

	@Override
	public List<MemberDeliverLogVo> getDeliveryLogList(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from MemberDeliverLogPo p ");
		hql.append(" where p.memberid=?");
		List<MemberDeliverLogPo> polist = this.genericDAO.getGenericByHql(hql.toString(), GUID.valueOf(id).toBytes());
		return BeanCopy.copys(MemberDeliverLogVo.class, polist);
	}

	@Override
	public int getVantagesCount(GetVantagesListKey key) throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(recid) from MemberVantagesPo p ");
		hql.append(" where p.memberid=?");
		List<Object> params = new ArrayList<Object>();
		params.add(GUID.valueOf(key.getMemberId()).toBytes());
		if (CheckIsNull.isNotEmpty(key.getBeginDate())) {
			hql.append(" and p.occurdate>=? ");
			params.add(new Date(DateUtil.getDayStartTime(key.getBeginDate().getTime())));
		}
		if (CheckIsNull.isNotEmpty(key.getEndDate())) {
			hql.append(" and p.occurdate<=? ");
			params.add(new Date(DateUtil.getDayEndTime(key.getEndDate().getTime())));
		}
		return this.genericDAO.getGenericCountByHql(hql.toString(), params.toArray(new Object[0]));
	}

	@Override
	public List<MemberVantagesVo> getVantagesList(GetVantagesListKey key) throws ServiceMessage {
		if (CheckIsNull.isEmpty(key.getMemberId())) {
			throw new ServiceMessage("memberId不能为空！");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from MemberVantagesPo p ");
		hql.append(" where p.memberid=?");
		List<Object> params = new ArrayList<Object>();
		params.add(GUID.valueOf(key.getMemberId()).toBytes());
		if (CheckIsNull.isNotEmpty(key.getBeginDate())) {
			hql.append(" and p.occurdate>=? ");
			params.add(new Date(DateUtil.getDayStartTime(key.getBeginDate().getTime())));
		}
		if (CheckIsNull.isNotEmpty(key.getEndDate())) {
			hql.append(" and p.occurdate<=? ");
			params.add(new Date(DateUtil.getDayEndTime(key.getEndDate().getTime())));
		}
		hql.append(" order by p.occurdate desc");
		List<MemberVantagesPo> polist = null;
		if (key.getPageSize() > 0) {
			polist = this.genericDAO.getGenericByPosition(hql.toString(), (key.getOffset() - 1) * key.getPageSize(),
					key.getPageSize(), params.toArray(new Object[0]));
		} else {
			polist = this.genericDAO.getGenericByHql(hql.toString(), GUID.valueOf(key.getMemberId()).toBytes(), params
					.toArray(new Object[0]));
		}
		return BeanCopy.copys(MemberVantagesVo.class, polist);
	}

	@Override
	public MemberAccountVo getMemberAccountVo(String id) {
		MemberAccountPo p = this.genericDAO.get(MemberAccountPo.class, GUID.valueOf(id).toBytes());
		if (null != p) {
			MemberAccountVo v = BeanCopy.copy(MemberAccountVo.class, p);
			return v;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.member.MemberService#getLogin(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public ServiceMessage getLogin(String user, String password) {
		MemberVo vo = this.getMemberByUsername(user);
		if (null == vo) {
			return new ServiceMessage(false, "用户不存在！", 1);
		}
		String pass = EncryptionUtil.encryptMD5(password);
		if (vo.getPassword().equals(pass)) {
			return new ServiceMessage(true, "登录成功！", vo);
		} else {
			return new ServiceMessage(false, "密码填写错误！", 2);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.member.MemberService#getMemberByUsername(java.lang.String)
	 */
	@Override
	public MemberVo getMemberByUsername(String user) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from MemberPo as t ");
		hql.append(" where t.username =? ");
		hql.append(" or t.mobile=? ");
		hql.append(" or t.email=?");
		List<MemberPo> polist = this.baseDao.getGenericByHql(hql.toString(), user, user, user);
		if (null == polist || polist.isEmpty()) {
			return null;
		}
		return BeanCopy.copy(MemberVo.class, polist.get(0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.member.MemberService#modifyEmail(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public ServiceMessage modifyEmail(String id, String email) {
		StringBuilder hql = new StringBuilder();
		hql.append(" update MemberPo as t ");
		hql.append(" set t.email=?");
		hql.append(" where t.recid =? ");
		int count = this.baseDao.execteBulk(hql.toString(), email, GUID.valueOf(id).toBytes());
		if (count == 1) {
			return new ServiceMessage(true, "");
		}
		return new ServiceMessage(false, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.member.MemberService#modifyMobiler(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public ServiceMessage modifyMobile(String id, String mobile) {
		StringBuilder hql = new StringBuilder();
		hql.append(" update MemberPo as t ");
		hql.append(" set t.mobile=?,t.checkedMobile=?");
		hql.append(" where t.recid =? ");
		int count = this.baseDao.execteBulk(hql.toString(), mobile, true, GUID.valueOf(id).toBytes());
		if (count == 1) {
			return new ServiceMessage(true, "");
		}
		return new ServiceMessage(false, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.member.MemberService#modifyPayPassword(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public ServiceMessage modifyPayPassword(String id, String password) {
		StringBuilder hql = new StringBuilder();
		hql.append(" update MemberAccountPo as t ");
		hql.append(" set t.paypassword=?");
		hql.append(" where t.recid =? ");
		int count = this.baseDao.execteBulk(hql.toString(), EncryptionUtil.encryptMD5(password), GUID.valueOf(id)
				.toBytes());
		if (count == 1) {
			return new ServiceMessage(true, "");
		}
		return new ServiceMessage(false, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.member.MemberService#findMemberInfo(java.lang.String)
	 */
	@Override
	public MemberInfoVo findMemberInfo(String id) {
		MemberInfoPo po = this.genericDAO.get(MemberInfoPo.class, GUID.valueOf(id).toBytes());
		if (null == po) {
			return null;
		}
		return BeanCopy.copy(MemberInfoVo.class, po);
	}

	@Override
	public int exeLockDeliveryBalance(String memberId, boolean isLokced,OrderPo order) {
		if(CheckIsNull.isEmpty(memberId))
		{
			return 0;
		}
		
		if(isLokced)
		{
			List<Object> params = new ArrayList<Object>();
			StringBuffer hql = new StringBuffer();
			hql.append(" update MemberDeliveryPo p ");
			hql.append(" set lockedDeliveryBalance = p.lockedDeliveryBalance+1 ");
			hql.append(" where p.member=? ");
			params.add(GUID.valueOf(memberId).toBytes());
			hql.append(" and p.begindate=? ");
			params.add(new Date(DateUtil.getDayStartTime(DateUtil.getThisMonth().getTime())));
			hql.append(" and p.enddate=? ");
			params.add(new Date(DateUtil.getDayEndTime(DateUtil.getLastDayOfThisMonth().getTime())));
			hql.append(" and (p.dcount-p.counted)>p.lockedDeliveryBalance ");
			int count = this.genericDAO.execteBulk(hql.toString(), params.toArray(new Object[0]));
			return count;
		}
		else
		{
			if(null==order)
			{
				throw new ServiceMessage("orderPo不能为空！");
			}
			MemberDeliveryPo p = this.getThisMonthDelivery(memberId);
			List<Object> params = new ArrayList<Object>();
			StringBuffer hql = new StringBuffer();
			hql.append(" update MemberDeliveryPo p ");
			hql.append(" set lockedDeliveryBalance = p.lockedDeliveryBalance-1 ");
			hql.append(" ,counted = p.counted+1 ");
			hql.append(" where p.member=? ");
			params.add(GUID.valueOf(memberId).toBytes());
			hql.append(" and p.begindate=? ");
			params.add(new Date(DateUtil.getDayStartTime(DateUtil.getThisMonth().getTime())));
			hql.append(" and p.enddate=? ");
			params.add(new Date(DateUtil.getDayEndTime(DateUtil.getLastDayOfThisMonth().getTime())));
			hql.append(" and p.lockedDeliveryBalance>0 and p.counted<p.dcount ");
			int count = this.genericDAO.execteBulk(hql.toString(), params.toArray(new Object[0]));
			if(count>0)
			{
				MemberDeliverLogVo v = new MemberDeliverLogVo();
				v.setCount(1l);
				v.setMemberid(GUID.valueOf(memberId).toString());
				v.setOccurdate(new Date(System.currentTimeMillis()));
				v.setRecid(GUID.randomID().toString());
				v.setRelabillsid(GUID.valueOf(order.getRecid()).toString());
				v.setRelabillsno(order.getBillsno());
				v.setDeliveryid(GUID.valueOf(p.getRecid()).toString());
				this.createDeliveryLog(v);
				return count;
			}
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.member.MemberService#exeClearVangetes(com.spark.cms.services.form.Login)
	 */
	@Override
	public void exeClearVangetes(Login login) {
		String hql = "from MemberAccountPo as t order by t.vantages";
		List<MemberAccountPo> polist = this.baseDao.getGenericByHql(hql);
		if (null == polist) {
			return;
		}
		List<MemberAccountVo> volist = BeanCopy.copys(MemberAccountVo.class, polist);
		for (MemberAccountVo vo : volist) {
			clearVangetes(vo, login);
		}
	}

	/**
	 * @param vo
	 */
	private void clearVangetes(MemberAccountVo vo, Login login) {
		String hql = "update MemberAccountPo as t set vantages=0 where recid=? and vantages=?";
		int count = this.baseDao.execteBulk(hql, GUID.valueOf(vo.getRecid()).toBytes(), vo.getVantages());
		if (count == 1) {
			if (vo.getVantages() == 0) {
				return;
			}
			MemberVantagesVo vtg = new MemberVantagesVo();
			vtg.setMemberid(vo.getRecid());
			vtg.setOccurdate(new Date());
			vtg.setRelabillsid(vo.getRecid());
			vtg.setRelabillsno("--");
			vtg.setVantages(-vo.getVantages());
			vtg.setVtype(VantagesType.Clear.getCode());
			vtg.setModifyPerson(login.getName());
			vtg.setModifyPersonId(login.getRecid());
			this.createVantages(vtg);
		} else if (count == 0) {
			vo = this.getMemberAccountVo(vo.getRecid());
			clearVangetes(vo, login);
		} else {
			throw new ServiceMessage(false, "账户余额清零失败，请稍后重试！");
		}
	}

	private MemberDeliveryPo getThisMonthDelivery(String memberId) {
		if(CheckIsNull.isEmpty(memberId))
		{
			return null;
		}
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append(" from MemberDeliveryPo p ");
		sb.append(" where p.member=? ");
		params.add(GUID.valueOf(memberId).toBytes());
		sb.append(" and p.begindate=? ");
		params.add(new Date(DateUtil.getDayStartTime(DateUtil.getThisMonth().getTime())));
		sb.append(" and p.enddate=? ");
		params.add(new Date(DateUtil.getDayEndTime(DateUtil.getLastDayOfThisMonth().getTime())));
		sb.append(" and (p.dcount-p.counted)>p.lockedDeliveryBalance ");
		List<MemberDeliveryPo> list = this.genericDAO.getGenericByHql(sb.toString(), params.toArray(new Object[0]));
		if(CheckIsNull.isNotEmpty(list))
		{
			return  list.get(0);
		}
		return null;
	}

	/**
	 * 活动会员 -> 获取活动会员列表
	 */
	@Override
	public List<MemberActiveVo> getActiveMemberList(GetMemberListKey key) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT cm.recid,cm.code,cm.username,cm.membername,cm.mobile,eso.stationname,");
		sb.append(" COUNT(eso.recid) as ordercount,SUM(eso.totalamount) as ordermoney,");
		sb.append(" COUNT(esr.billsno) as returncount,SUM(esr.amount) as returnmoney");
		sb.append(" FROM cms_member cm");
		sb.append(" LEFT JOIN erp_sales_onlineorder eso ON HEX(eso.memberid) = HEX(cm.recid)");
		sb.append(" LEFT JOIN psi_sales_onlinereturn esr ON esr.onlinebillsno = eso.billsno");
		sb.append(" where 1=1");
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			sb.append(" and eso.createdate >= '").append(key.getBeginDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			sb.append(" and eso.createdate <= '").append(key.getEndDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getSearchText())){
			String searchText = key.getSearchText().trim();
			sb.append(" and (cm.code like '%").append(searchText).append("%'");
			sb.append(" or cm.membername like '%").append(searchText).append("%'");
			sb.append(" or cm.username like '%").append(searchText).append("%'");
			sb.append(" or eso.stationname like '%").append(searchText).append("%'");
			sb.append(" or cm.mobile like '%").append(searchText).append("%')");
		}
		sb.append(" GROUP BY cm.CODE");
		sb.append(" order by cm.code");
		if(key.getOffset() > 0 && key.getPageSize() > 0){
			sb.append(" LIMIT ").append((key.getOffset()-1)*key.getPageSize()).append(",").append(key.getPageSize());
		}
		List<Object> objList = genericDAO.query(sb.toString());
		List<MemberActiveVo> mavList = convertToMemberActionVoList(objList);
		return mavList;
	}
	
	/**
	 * 活动会员 -> 获取活动会员列表 ->  数据转换
	 * @param objListList
	 * @return
	 */
	private List<MemberActiveVo> convertToMemberActionVoList(List<Object> objList){
		List<MemberActiveVo> mavList = new ArrayList<MemberActiveVo>();
		for(Object obj : objList){
			Object[] objs = (Object[])obj;
			MemberActiveVo mav = new MemberActiveVo();
			mav.setCode(objs[1].toString());
			mav.setUsername(objs[2].toString());
			if(CheckIsNull.isNotEmpty(objs[3])) mav.setMembername(objs[3].toString());
			mav.setMobile(objs[4].toString());
			if(CheckIsNull.isNotEmpty(objs[5])) mav.setStationname(objs[5].toString());
			Object ordercount = objs[6];
			if(CheckIsNull.isNotEmpty(ordercount)){
				mav.setOrdercount(Integer.valueOf(ordercount.toString()));
			}else{
				mav.setOrdercount(0);
			}
			
			Object ordermoney = objs[7];
			if(CheckIsNull.isNotEmpty(ordermoney)){
				mav.setOrdermoney(Double.valueOf(ordermoney.toString()));
			}else{
				mav.setOrdermoney(0.0);
			}
			
			Object returncount = objs[8];
			if(CheckIsNull.isNotEmpty(returncount)){
				mav.setReturncount(Integer.valueOf(returncount.toString()));
			}else{
				mav.setReturncount(0);
			}
			
			Object returnmoney = objs[9];
			if(CheckIsNull.isNotEmpty(returnmoney)){
				mav.setReturnmoney(Double.valueOf(returnmoney.toString()));
			}else{
				mav.setReturnmoney(0.0);
			}
			mavList.add(mav);
		}
		return mavList;
	}

	/**
	 * 活动会员 -> 信息统计
	 */
	@Override
	public List<MemberActiveVo> getActiveMemberTotal(GetMemberListKey key) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT SUM(ordercount),SUM(ordermoney),SUM(returncount),SUM(returnmoney)");
		sb.append(" FROM");
		sb.append(" (");
		sb.append(" SELECT");
		sb.append(" COUNT(eso.recid) AS ordercount,SUM(eso.totalamount) AS ordermoney,");
		sb.append(" COUNT(esr.billsno) AS returncount,SUM(esr.amount) AS returnmoney");
		sb.append(" FROM cms_member cm");
		sb.append(" LEFT JOIN erp_sales_onlineorder eso ON HEX(eso.memberid) = HEX(cm.recid)");
		sb.append(" LEFT JOIN psi_sales_onlinereturn esr ON esr.onlinebillsno = eso.billsno");
		sb.append(" WHERE 1=1");
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			sb.append(" and eso.createdate >= '").append(key.getBeginDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			sb.append(" and eso.createdate <= '").append(key.getEndDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getSearchText())){
			String searchText = key.getSearchText().trim();
			sb.append(" and (cm.code like '%").append(searchText).append("%'");
			sb.append(" or cm.membername like '%").append(searchText).append("%'");
			sb.append(" or cm.username like '%").append(searchText).append("%'");
			sb.append(" or eso.stationname like '%").append(searchText).append("%'");
			sb.append(" or cm.mobile like '%").append(searchText).append("%')");
		}
		sb.append(" GROUP BY CODE");
		sb.append(" )");
		sb.append(" tempTab");
		List<Object> objList = genericDAO.query(sb.toString());
		List<MemberActiveVo> mavList = new ArrayList<MemberActiveVo>();
		MemberActiveVo mav = new MemberActiveVo();
		Object[] objs = (Object[])objList.get(0);
		Object ordercount = objs[0];
		if(CheckIsNull.isNotEmpty(ordercount)){
			mav.setOrdercount(Integer.valueOf(ordercount.toString()));
		}else{
			mav.setOrdercount(0);
		}
		
		Object ordermoney = objs[1];
		if(CheckIsNull.isNotEmpty(ordermoney)){
			mav.setOrdermoney(Double.valueOf(ordermoney.toString()));
		}else{
			mav.setOrdermoney(0.0);
		}
		
		Object returncount = objs[2];
		if(CheckIsNull.isNotEmpty(returncount)){
			mav.setReturncount(Integer.valueOf(returncount.toString()));
		}else{
			mav.setReturncount(0);
		}
		
		Object returnmoney = objs[3];
		if(CheckIsNull.isNotEmpty(returnmoney)){
			mav.setReturnmoney(Double.valueOf(returnmoney.toString()));
		}else{
			mav.setReturnmoney(0.0);
		}
		mavList.add(mav);
		return mavList;
	}
	 	
	/**
	 * 活动会员 -> 获取活动会员列表
	 */
	@Override
	public int getActiveMemberCount(GetMemberListKey key) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(*) FROM");
		sb.append(" (");
		sb.append(" SELECT COUNT(cm.code)");
		sb.append(" FROM cms_member cm");
		sb.append(" LEFT JOIN erp_sales_onlineorder eso ON HEX(eso.memberid) = HEX(cm.recid)");
		sb.append(" LEFT JOIN psi_sales_onlinereturn esr ON esr.onlinebillsno = eso.billsno");
		sb.append(" WHERE 1=1");
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			sb.append(" and eso.createdate >= '").append(key.getBeginDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			sb.append(" and eso.createdate <= '").append(key.getEndDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getSearchText())){
			String searchText = key.getSearchText().trim();
			sb.append(" and (cm.code like '%").append(searchText).append("%'");
			sb.append(" or cm.membername like '%").append(searchText).append("%'");
			sb.append(" or cm.username like '%").append(searchText).append("%'");
			sb.append(" or eso.stationname like '%").append(searchText).append("%'");
			sb.append(" or cm.mobile like '%").append(searchText).append("%')");
		}
		sb.append(" GROUP BY CODE");
		sb.append(" )");
		sb.append(" tempTab1");
		List<Object> objList = genericDAO.query(sb.toString());
		Object obj = objList.get(0);
		int count = 0;
		if(obj != null) count = Integer.valueOf(obj.toString());
		return count;
	}

	/**
	 * 充值流水 -> 流水数量
	 */
	@Override
	public int getChargeFlowCount(GetMemberListKey key,String valueType,String chargeType) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT count(cm.code)");
		sb.append(" FROM cms_member_dealing cmd");
		sb.append(" LEFT JOIN cms_member cm ON HEX(cm.recid) = HEX(cmd.memberid)");
		sb.append(" WHERE 1=1");
		if(CheckIsNull.isNotEmpty(valueType) && Double.valueOf(valueType) > 0){
			sb.append(" and cmd.amount = ").append(Double.valueOf(valueType));
		}
		if(CheckIsNull.isNotEmpty(chargeType) && !"00".equals(chargeType)){
			sb.append(" and(cmd.dealtype = '").append(chargeType).append("')");
		}else{
			sb.append(" and(cmd.dealtype = '01' OR cmd.dealtype = '02')");
		}
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			sb.append(" and cmd.occurdate >= '").append(key.getBeginDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			sb.append(" and cmd.occurdate <= '").append(key.getEndDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getSearchText())){
			String searchText = key.getSearchText().trim();
			sb.append(" and (cm.code like '%").append(searchText).append("%'");
			sb.append(" or cm.membername like '%").append(searchText).append("%'");
			sb.append(" or cm.username like '%").append(searchText).append("%'");
			sb.append(" or cmd.relabillsno like '%").append(searchText).append("%'");
			sb.append(" or cm.mobile like '%").append(searchText).append("%')");
		}
		List<Object> objList = genericDAO.query(sb.toString());
		Object obj = objList.get(0);
		int count = 0;
		if(obj != null) count = Integer.valueOf(obj.toString());
		return count;
	}

	/**
	 * 充值流水 -> 流水列表
	 */
	@Override
	public List<MemberChargeFlowVo> getChargeFlows(GetMemberListKey key,String valueType,String chargeType) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT cm.code,cm.username,cm.membername,cm.mobile,cmd.dealtype,cmd.relabillsno,cmd.amount,cmd.occurdate");
		sb.append(" FROM cms_member_dealing cmd");
		sb.append(" LEFT JOIN cms_member cm ON HEX(cm.recid) = HEX(cmd.memberid)");
		sb.append(" WHERE 1=1");
		if(CheckIsNull.isNotEmpty(valueType) && Double.valueOf(valueType) > 0){
			sb.append(" and cmd.amount = ").append(Double.valueOf(valueType));
		}
		if(CheckIsNull.isNotEmpty(chargeType) && !"00".equals(chargeType)){
			sb.append(" and(cmd.dealtype = '").append(chargeType).append("')");
		}else{
			sb.append(" and(cmd.dealtype = '01' OR cmd.dealtype = '02')");
		}
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			sb.append(" and cmd.occurdate >= '").append(key.getBeginDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			sb.append(" and cmd.occurdate <= '").append(key.getEndDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getSearchText())){
			String searchText = key.getSearchText().trim();
			sb.append(" and (cm.code like '%").append(searchText).append("%'");
			sb.append(" or cm.membername like '%").append(searchText).append("%'");
			sb.append(" or cm.username like '%").append(searchText).append("%'");
			sb.append(" or cmd.relabillsno like '%").append(searchText).append("%'");
			sb.append(" or cm.mobile like '%").append(searchText).append("%')");
		}
		sb.append(" ORDER BY cmd.occurdate DESC");
		if(key.getOffset() > 0 && key.getPageSize() > 0){
			sb.append(" LIMIT ").append((key.getOffset()-1)*key.getPageSize()).append(",").append(key.getPageSize());
		}
		List<Object> objList = genericDAO.query(sb.toString());
		List<MemberChargeFlowVo> mcfList = convertToChargeFlowList(objList);
		return mcfList;
	}
	
	/**
	 * 充值流水 -> 流水列表 -> 数据转换
	 */
	private List<MemberChargeFlowVo> convertToChargeFlowList(List<Object> objList){
		List<MemberChargeFlowVo> mcfList = new ArrayList<MemberChargeFlowVo>();
		for(Object obj : objList){
			Object[] objs = (Object[])obj;
			MemberChargeFlowVo mcf = new MemberChargeFlowVo();
			if(CheckIsNull.isNotEmpty(objs[0])) mcf.setCode(objs[0].toString());
			if(CheckIsNull.isNotEmpty(objs[1])) mcf.setUsername(objs[1].toString());
			if(CheckIsNull.isNotEmpty(objs[2])) mcf.setMembername(objs[2].toString());
			if(CheckIsNull.isNotEmpty(objs[3])) mcf.setMobile(objs[3].toString());
			if(CheckIsNull.isNotEmpty(objs[4])) mcf.setChargetype(objs[4].toString());
			if(CheckIsNull.isNotEmpty(objs[5])) mcf.setOrderno(objs[5].toString());
			if(CheckIsNull.isNotEmpty(objs[6])) mcf.setAmount(objs[6].toString());
			if(CheckIsNull.isNotEmpty(objs[7])) mcf.setOccurdate(objs[7].toString());
			mcfList.add(mcf);
		}
		return mcfList;
	}
	
	/**
	 * 充值流水 -> 信息统计
	 */
	@Override
	public List<MemberChargeFlowVo> getChargeFlowTotal(GetMemberListKey key,String valueType,String chargeType) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT count(cm.code),sum(cmd.amount)");
		sb.append(" FROM cms_member_dealing cmd");
		sb.append(" LEFT JOIN cms_member cm ON HEX(cm.recid) = HEX(cmd.memberid)");
		sb.append(" WHERE 1=1");
		if(CheckIsNull.isNotEmpty(valueType) && Double.valueOf(valueType) > 0){
			sb.append(" and cmd.amount = ").append(Double.valueOf(valueType));
		}
		if(CheckIsNull.isNotEmpty(chargeType) && !"00".equals(chargeType)){
			sb.append(" and(cmd.dealtype = '").append(chargeType).append("')");
		}else{
			sb.append(" and(cmd.dealtype = '01' OR cmd.dealtype = '02')");
		}
		if(CheckIsNull.isNotEmpty(key.getBeginDate())){
			sb.append(" and cmd.occurdate >= '").append(key.getBeginDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getEndDate())){
			sb.append(" and cmd.occurdate <= '").append(key.getEndDate()).append("'");
		}
		if(CheckIsNull.isNotEmpty(key.getSearchText())){
			String searchText = key.getSearchText().trim();
			sb.append(" and (cm.code like '%").append(searchText).append("%'");
			sb.append(" or cm.membername like '%").append(searchText).append("%'");
			sb.append(" or cm.username like '%").append(searchText).append("%'");
			sb.append(" or cmd.relabillsno like '%").append(searchText).append("%'");
			sb.append(" or cm.mobile like '%").append(searchText).append("%')");
		}
		List<Object> objList = genericDAO.query(sb.toString());
		Object[] objs = (Object[])objList.get(0);
		List<MemberChargeFlowVo> mcfList = new ArrayList<MemberChargeFlowVo>();
		MemberChargeFlowVo mcf = new MemberChargeFlowVo();
		if(CheckIsNull.isNotEmpty(objs[0])) mcf.setOrderno(objs[0].toString());
		if(CheckIsNull.isNotEmpty(objs[1])) mcf.setAmount(objs[1].toString());
		mcf.setCode("统计信息");
		mcfList.add(mcf);
		return mcfList;
	}
	
}
