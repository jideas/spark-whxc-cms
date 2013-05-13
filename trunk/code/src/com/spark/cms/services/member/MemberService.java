package com.spark.cms.services.member;

import java.util.List;

import com.spark.cms.dao.po.MemberDeliveryPo;
import com.spark.cms.dao.po.OrderPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.member.MemberForm;
import com.spark.cms.services.member.key.GetDealingListKey;
import com.spark.cms.services.member.key.GetMemberListKey;
import com.spark.cms.services.member.key.GetVantagesListKey;
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

public interface MemberService {

	/**
	 * 注册
	 * @param mv
	 */
	
	ServiceMessage createMember(MemberVo v);
	/**
	 * 激活
	 * @throws Throwable 
	 * 
	 */
	void exeActivate(String id) throws ServiceMessage;
	/**
	 * 启用/停用
	 * @param id
	 * @throws Throwable 
	 */
	void modifyMemberStatus(String id,boolean stop) throws ServiceMessage;
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @throws Throwable 
	 */
	void modifyPassword(String id,String oldPassword,String newPassword) throws ServiceMessage;
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	MemberVo find(String id);
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	MemberInfoVo findMemberInfo(String id);
	
	/**
	 * 查询列表
	 * @param id
	 * @return
	 */
	List<MemberVo> getList(GetMemberListKey key);
	
	/**
	 * 查询 总数
	 * @param id
	 * @return
	 */
	int getCount(GetMemberListKey key);
	
	/**
	 * 修改会员详情
	 * @param miv
	 * @return
	 */
	void modifyMemberInfo(MemberInfoVo v);
	/**
	 * 新增地址
	 * @param mav
	 * @return
	 */
	void createAdress(MemberAddressVo v);
	/**
	 * 修改地址
	 */
	void modifyAdress(MemberAddressVo v);
	/**
	 * 删除地址
	 */
	void deleteAdress(String id);
	/**
	 * 设为默认地址
	 */
	void exeDefaultAdress(String id);
	/**
	 * 查找一条地址
	 * @param id
	 */
	MemberAddressVo findAdress(String id);
	/**
	 * 查找会员所有地址
	 */
	List<MemberAddressVo> getAdressList(String id);
	/**
	 * 查询会员账户余额
	 */
	MemberAccountVo getMemberAccountVo(String id);
	/**
	 * 新增往来记录
	 * @throws ServiceMessage 
	 */
	void createDealing(MemberDealingVo v) throws ServiceMessage;
	 
	/**
	 * 新增积分记录
	 * @throws ServiceMessage 
	 */
	void createVantages(MemberVantagesVo v) throws ServiceMessage;
	/**
	 * 查询所有积分记录
	 * @throws Throwable 
	 */
	List<MemberDealingVo> getDealingList(GetDealingListKey key) throws ServiceMessage;
	/**
	 * 积分记录总条数
	 * @throws Throwable 
	 */
	int getDealingCount(GetDealingListKey key) throws ServiceMessage;
	/**
	 * 新增送货充值记录
	 */
	void createDelivery(MemberDeliveryVo v);
	/**
	 * 锁定/解锁上门送货包月余额
	 */
	int exeLockDeliveryBalance(String memberId,boolean isLokced,OrderPo order);
	/**
	 * 查询所有送货充值记录
	 */
	List<MemberDeliveryVo> getDeliveryList(String id);
	/**
	 * 新增送货抵减
	 * @throws ServiceMessage 
	 * @throws ServiceMessage 
	 */
	void createDeliveryLog(MemberDeliverLogVo v) throws ServiceMessage;
	/**
	 * 查找所有送货抵减记录
	 */
	List<MemberDeliverLogVo> getDeliveryLogList(String id);
	/**
	 * 
	 * 积分记录总条数
	 * @throws Throwable 
	 */
	int getVantagesCount(GetVantagesListKey key) throws ServiceMessage;
	/**
	 * 积分记录列表
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	List<MemberVantagesVo> getVantagesList(GetVantagesListKey key) throws ServiceMessage; 
	
	/**
	 * 登录
	 * @param user
	 * @param password
	 * @return
	 */
	ServiceMessage getLogin(String user,String password);
	
	MemberVo getMemberByUsername(String user);
	/**
	 * 修改密码
	 * @param id
	 * @param newPassword
	 * @throws Throwable
	 */
	void modifyPassword(String id, String newPassword) throws ServiceMessage;

	ServiceMessage modifyEmail(String id, String email);
	ServiceMessage modifyMobile(String id, String mobile);
	ServiceMessage modifyPayPassword(String id,String password);
	/**
	 * @param v
	 * @throws ServiceMessage 
	 */
	void modifyMember(MemberVo v) throws ServiceMessage;
	/**
	 * @param login 
	 * 
	 */
	void exeClearVangetes(Login login);
	/**
	 * 导入
	 * @param v
	 * @return
	 */
	ServiceMessage createMember(MemberForm v);
	
	/**
	 * 获取会员总金额
	 * @param key
	 * @return
	 */
	double getSumMoney(GetMemberListKey key);
	
	/**
	 * 活动会员 -> 获取活动会员列表
	 * @param key
	 * @return
	 */
	List<MemberActiveVo> getActiveMemberList(GetMemberListKey key);
	
	/**
	 * 活动会员 -> 信息统计
	 * @param key
	 * @return
	 */
	List<MemberActiveVo> getActiveMemberTotal(GetMemberListKey key);

	/**
	 * 活动会员 -> 获取活动会员数量
	 * @param key
	 * @return
	 */
	int getActiveMemberCount(GetMemberListKey key);
	
	/**
	 * 充值流水 -> 流水列表
	 * @param key
	 * @return
	 */
	List<MemberChargeFlowVo> getChargeFlows(GetMemberListKey key,String valueType,String chargeType);
	
	/**
	 * 充值流水 -> 信息统计
	 * @param key
	 * @return
	 */
	List<MemberChargeFlowVo> getChargeFlowTotal(GetMemberListKey key,String valueType,String chargeType);
	
	/**
	 * 充值流水 -> 流水数量
	 * @param key
	 * @return
	 */
	int getChargeFlowCount(GetMemberListKey key,String valueType,String chargeType);
}
