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
	 * ע��
	 * @param mv
	 */
	
	ServiceMessage createMember(MemberVo v);
	/**
	 * ����
	 * @throws Throwable 
	 * 
	 */
	void exeActivate(String id) throws ServiceMessage;
	/**
	 * ����/ͣ��
	 * @param id
	 * @throws Throwable 
	 */
	void modifyMemberStatus(String id,boolean stop) throws ServiceMessage;
	
	/**
	 * �޸�����
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @throws Throwable 
	 */
	void modifyPassword(String id,String oldPassword,String newPassword) throws ServiceMessage;
	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	MemberVo find(String id);
	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	MemberInfoVo findMemberInfo(String id);
	
	/**
	 * ��ѯ�б�
	 * @param id
	 * @return
	 */
	List<MemberVo> getList(GetMemberListKey key);
	
	/**
	 * ��ѯ ����
	 * @param id
	 * @return
	 */
	int getCount(GetMemberListKey key);
	
	/**
	 * �޸Ļ�Ա����
	 * @param miv
	 * @return
	 */
	void modifyMemberInfo(MemberInfoVo v);
	/**
	 * ������ַ
	 * @param mav
	 * @return
	 */
	void createAdress(MemberAddressVo v);
	/**
	 * �޸ĵ�ַ
	 */
	void modifyAdress(MemberAddressVo v);
	/**
	 * ɾ����ַ
	 */
	void deleteAdress(String id);
	/**
	 * ��ΪĬ�ϵ�ַ
	 */
	void exeDefaultAdress(String id);
	/**
	 * ����һ����ַ
	 * @param id
	 */
	MemberAddressVo findAdress(String id);
	/**
	 * ���һ�Ա���е�ַ
	 */
	List<MemberAddressVo> getAdressList(String id);
	/**
	 * ��ѯ��Ա�˻����
	 */
	MemberAccountVo getMemberAccountVo(String id);
	/**
	 * ����������¼
	 * @throws ServiceMessage 
	 */
	void createDealing(MemberDealingVo v) throws ServiceMessage;
	 
	/**
	 * �������ּ�¼
	 * @throws ServiceMessage 
	 */
	void createVantages(MemberVantagesVo v) throws ServiceMessage;
	/**
	 * ��ѯ���л��ּ�¼
	 * @throws Throwable 
	 */
	List<MemberDealingVo> getDealingList(GetDealingListKey key) throws ServiceMessage;
	/**
	 * ���ּ�¼������
	 * @throws Throwable 
	 */
	int getDealingCount(GetDealingListKey key) throws ServiceMessage;
	/**
	 * �����ͻ���ֵ��¼
	 */
	void createDelivery(MemberDeliveryVo v);
	/**
	 * ����/���������ͻ��������
	 */
	int exeLockDeliveryBalance(String memberId,boolean isLokced,OrderPo order);
	/**
	 * ��ѯ�����ͻ���ֵ��¼
	 */
	List<MemberDeliveryVo> getDeliveryList(String id);
	/**
	 * �����ͻ��ּ�
	 * @throws ServiceMessage 
	 * @throws ServiceMessage 
	 */
	void createDeliveryLog(MemberDeliverLogVo v) throws ServiceMessage;
	/**
	 * ���������ͻ��ּ���¼
	 */
	List<MemberDeliverLogVo> getDeliveryLogList(String id);
	/**
	 * 
	 * ���ּ�¼������
	 * @throws Throwable 
	 */
	int getVantagesCount(GetVantagesListKey key) throws ServiceMessage;
	/**
	 * ���ּ�¼�б�
	 * @param key
	 * @return
	 * @throws Throwable 
	 */
	List<MemberVantagesVo> getVantagesList(GetVantagesListKey key) throws ServiceMessage; 
	
	/**
	 * ��¼
	 * @param user
	 * @param password
	 * @return
	 */
	ServiceMessage getLogin(String user,String password);
	
	MemberVo getMemberByUsername(String user);
	/**
	 * �޸�����
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
	 * ����
	 * @param v
	 * @return
	 */
	ServiceMessage createMember(MemberForm v);
	
	/**
	 * ��ȡ��Ա�ܽ��
	 * @param key
	 * @return
	 */
	double getSumMoney(GetMemberListKey key);
	
	/**
	 * ���Ա -> ��ȡ���Ա�б�
	 * @param key
	 * @return
	 */
	List<MemberActiveVo> getActiveMemberList(GetMemberListKey key);
	
	/**
	 * ���Ա -> ��Ϣͳ��
	 * @param key
	 * @return
	 */
	List<MemberActiveVo> getActiveMemberTotal(GetMemberListKey key);

	/**
	 * ���Ա -> ��ȡ���Ա����
	 * @param key
	 * @return
	 */
	int getActiveMemberCount(GetMemberListKey key);
	
	/**
	 * ��ֵ��ˮ -> ��ˮ�б�
	 * @param key
	 * @return
	 */
	List<MemberChargeFlowVo> getChargeFlows(GetMemberListKey key,String valueType,String chargeType);
	
	/**
	 * ��ֵ��ˮ -> ��Ϣͳ��
	 * @param key
	 * @return
	 */
	List<MemberChargeFlowVo> getChargeFlowTotal(GetMemberListKey key,String valueType,String chargeType);
	
	/**
	 * ��ֵ��ˮ -> ��ˮ����
	 * @param key
	 * @return
	 */
	int getChargeFlowCount(GetMemberListKey key,String valueType,String chargeType);
}
