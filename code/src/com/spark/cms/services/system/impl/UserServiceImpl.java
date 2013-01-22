package com.spark.cms.services.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.utils.BeanUtils;
import com.spark.base.common.utils.ListUtils;
import com.spark.base.common.utils.RandomGUID;
import com.spark.base.common.utils.SparkLib;
import com.spark.base.common.utils.StringUtil;
import com.spark.base.hibernate.GenericDAO;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.dao.po.Resource;
import com.spark.cms.dao.po.User;
import com.spark.cms.dao.po.UserRole;
import com.spark.cms.services.form.ResourceExtForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.system.UserService;
import com.spark.cms.services.vo.UserVo;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private GenericDAO genericDAO;
	
	@Override
	public boolean addUser(UserExtForm userExtForm) {
		if(this.checkUseEmailExisted(userExtForm.getId(), userExtForm.getEmail())){
			return false;
		}else if(this.checkUserAccountExisted(userExtForm.getId(), userExtForm.getAccount())){
			return false;
		}else if(this.checkUserMobileExisted(userExtForm.getId(), userExtForm.getMobile())){
			return false;
		}
		
		User cmsUser = BeanUtils.copyProperties(User.class, userExtForm);
		cmsUser.setId(RandomGUID.newGuid());
		cmsUser.setPwd(SparkLib.encodePassword(userExtForm.getPwd(), Constant.MAX_PASSWORD));
		genericDAO.save(cmsUser);
		return true;
	}
	
	@Override
	public boolean checkUserAccountExisted(String id,String account){
		String sql =""; 
		boolean isexists = false;
		if(StringUtil.isNotEmpty(id)){
			sql = "select count(u.id) from User u where u.account ='"+account+"' and u.id <> '"+id+"' ";
		}else{
			sql = "select count(u.id)  from User u where u.account ='"+account+"'";
		}
		
		int count = genericDAO.getGenericCountByHql(sql);
		if(count>0) isexists = true;
		return isexists;
	}
	
	@Override
	public boolean checkUserMobileExisted(String id,String mobile){
		String sql =""; 
		boolean isexists = false;
		if(StringUtil.isNotEmpty(id)){
			sql = "select count(u.id)  from User u where u.mobile ='"+mobile+"' and u.id <> '"+id+"' ";
		}else{
			sql = "select count(u.id)  from User u where u.mobile ='"+mobile+"'";
		}
		
		int count = genericDAO.getGenericCountByHql(sql);
		if(count>0) isexists = true;
		return isexists;
	}
	
	@Override
	public boolean checkUseEmailExisted(String id,String email){
		String sql =""; 
		boolean isexists = false;
		if(StringUtil.isNotEmpty(id)){
			sql = "select count(u.id)  from User u where u.email ='"+email+"' and u.id <> '"+id+"' ";
		}else{
			sql = "select count(u.id)  from User u where u.email ='"+email+"'";
		}
		
		int count = genericDAO.getGenericCountByHql(sql);
		if(count>0) isexists = true;
		return isexists;
	}
	 
	@Override
	public void exeAssignRoles(String userId,String []roleIds) {
		/**
		 * 删除用户具有的角
		 */
		String sql  = "delete from UserRole r where r.userId ='"+userId+"'";
		genericDAO.execteBulk(sql);
		UserRole role =null;
		for(String roleId:roleIds){
			role = new UserRole();
			role.setId(RandomGUID.newGuid());
			role.setUserId(userId);
			role.setRoleId(roleId);
			genericDAO.save(role);
		}
		
	}

	@Override
	public void deleteUser(String userId) {
		//删除用户，要删除用户所具有的角色
		String sql = "delete from UserRole ur where ur.userId='"+userId+"'";
		genericDAO.execteBulk(sql);
		genericDAO.delete(User.class,userId);
	}

	@Override
	public List<UserRole> getRolesByUserId(String userId) {
		String sql =  "from UserRole ur where  ur.userId='"+userId+"'";
		List<UserRole> list = genericDAO.getGenericByHql(sql);
		return list;
	}
	

	@Override
	public UserExtForm getUserById(String userId) {
		  User cmsUser = genericDAO.get(User.class,userId);
		  cmsUser.setPwd(SparkLib.decodePassword(cmsUser.getPwd()));
		  if(cmsUser!=null){
			  return BeanUtils.copyProperties(UserExtForm.class, cmsUser);
		  }
		return null;
	}

	@Override
	public DataModel getUsers(String... args) {
		DataModel model = new DataModel();
		model.setRows(ListUtils.transform(genericDAO.listAll(User.class), UserExtForm.class));
		return model;
	}

	@Override
	public boolean updateUser(UserExtForm userExtForm) {
		if(this.checkUseEmailExisted(userExtForm.getId(), userExtForm.getEmail())){
			return false;
		}else if(this.checkUserAccountExisted(userExtForm.getId(), userExtForm.getAccount())){
			return false;
		}else if(this.checkUserMobileExisted(userExtForm.getId(), userExtForm.getMobile())){
			return false;
		}
		User cmsUser =  genericDAO.get(User.class,userExtForm.getId());
		cmsUser.setEmail(userExtForm.getEmail());
		//cmsUser.setName(userExtForm.getName());
		cmsUser.setIsEnabled(userExtForm.getIsEnabled());
		cmsUser.setTele(userExtForm.getTele());
		cmsUser.setMobile(userExtForm.getMobile());
		cmsUser.setPwd(SparkLib.encodePassword(userExtForm.getPwd(), Constant.MAX_PASSWORD));
		genericDAO.saveOrUpdate(cmsUser);
		return true;
	}
	
	@Override
	public void updateUserState(String state,String ...ids){
		for(String id:ids ){
			User cmsUser =  genericDAO.get(User.class,id);
			cmsUser.setIsEnabled(state);
			genericDAO.saveOrUpdate(cmsUser);
		}
		
	}
	
	@Override
	public List<ResourceExtForm> getUserResource(String userId){
		/**
		 * 获取用户资源
		 */
		StringBuffer sb = new StringBuffer("select rs from UserRole ur,Role r,Resource rs,RoleResource rr ");
					 sb.append(" where r.id=ur.roleId and rr.roleId=r.id and rr.resourceId=rs.id ");
					 sb.append(" and ur.userId = '").append(userId).append("' group by rs.id order by rs.code ");
		List<Resource> resouceList = this.genericDAO.getGenericByHql(sb.toString());
		return ListUtils.transform(resouceList,ResourceExtForm.class);
	}

	@Override
	public String getLoginInfo(String loginName, String password) {
		String name = loginName.trim();
		String hsql ="from User u where (u.mobile='"+name+"' or u.account ='"+name+"' or u.email='"+name+"' ) and  isEnabled='1' ";
		List<User> list = this.genericDAO.getGenericByHql(hsql);
		if(list!=null&&list.size()==1){
			User cmsUser = list.get(0);
			if(SparkLib.decodePassword(cmsUser.getPwd()).equals(password)){
				return cmsUser.getId();
			}
		}
		return null;
	}
	
	public UserVo getUserInfoByLoginNameAndPassword(String loginName, String password) {
		String name = loginName.trim();
		String hsql ="from User u where (u.mobile='"+name+"' or u.account ='"+name+"' or u.email='"+name+"' )";
		List<User> list = this.genericDAO.getGenericByHql(hsql);
		if(list!=null&&list.size()==1){
			User cmsUser = list.get(0);
			if(SparkLib.decodePassword(cmsUser.getPwd()).equals(password)){
				return BeanCopy.copy(UserVo.class, cmsUser);
			}
		}
		return null;
	}
	
	@Override
	public boolean exeResetPassWord(String userid,String password, String newspassword){
		boolean ret = true;
		User cmsUser = genericDAO.get(User.class,userid);
		if(SparkLib.decodePassword(cmsUser.getPwd()).equals(password)){
			cmsUser.setPwd(SparkLib.encodePassword(newspassword,Constant.MAX_PASSWORD));
			this.genericDAO.update(cmsUser);
		}else{
			ret  = false;
		}
		return ret;
	}
	
}
