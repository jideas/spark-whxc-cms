/**
 * 
 */
package com.spark.cms.services.folder.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.common.system.resource.SystemResourceManager;
import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.SheetNumberType;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.dao.po.FolderPo;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.folder.FolderService;
import com.spark.cms.services.serial.SerialNumberService;
import com.spark.cms.services.vo.FolderVo;

/**
 * @author Jideas
 * 
 */
@Service
public class FolderServiceImpl implements FolderService {

	@Autowired
	private GenericDAO baseDAO;
	@Autowired
	private SystemResourceManager srManager;
	@Autowired
	private SerialNumberService snumManager;

	/*
	 * (non-Javadoc)新增
	 * 
	 * @see com.spark.cms.services.folder.FolderService#createFolder(com.spark.cms.services.vo.FolderVo)
	 */
	@Override
	public FolderVo createFolder(FolderVo folder) {
		folder.setRecid(GUID.randomID().toString());
		folder.setCode(snumManager.exeGetSheetNumberWithOutDate(SheetNumberType.FolderCode));
		if (!this.checkFolderIsNotHere(folder)) {
			return null;
		}
		this.baseDAO.save(BeanCopy.copy(FolderPo.class, folder));
		this.srManager.insert(folder);
		return folder;
	}

	/*
	 * (non-Javadoc) 删除文件夹
	 * 
	 * @see com.spark.cms.services.folder.FolderService#deleteFolder(java.lang.String)
	 */
	@Override
	public ServiceMessage deleteFolder(String id) {
		this.baseDAO.delete(FolderPo.class, GUID.valueOf(id).toBytes());
		this.srManager.delete(FolderVo.class, id);
		return new ServiceMessage(true, "删除文件夹成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.folder.FolderService#getFoldersByParentId(java.lang.String)
	 */
	@Override
	public List<FolderVo> getFoldersByParentId(String recid) {
		return srManager.getListByKey(FolderVo.class, recid);
	}

	/*
	 * (non-Javadoc)更新文件夹信息
	 * 
	 * @see com.spark.cms.services.folder.FolderService#updateFolder(com.spark.cms.services.vo.FolderVo)
	 */
	@Override
	public FolderVo updateFolder(FolderVo folder) {
		if (!this.checkFolderIsNotHere(folder)) {
			return null;
		}
		StringBuilder ss = new StringBuilder();
		ss.append(" update FolderPo as t ");
		ss.append(" set title=?");
		ss.append(" where t.recid=?");
		this.baseDAO.execteBulk(ss.toString(), folder.getTitle(), GUID.valueOf(folder.getRecid()).toBytes());
		this.srManager.update(folder);
		return folder;
	}

	@Override
	public boolean checkFolderIsNotHere(FolderVo folder) { 
		List<FolderVo> volist = this.getFoldersByParentId(folder.getParentId());
		for (FolderVo vo : volist) {
			if (vo.getRecid().equals(folder.getRecid())) {
				continue;
			}
			if (vo.getTitle().equals(folder.getTitle())) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.folder.FolderService#getAllFolders()
	 */
	@Override
	public List<FolderVo> getAllFolders() {
		StringBuilder ss = new StringBuilder();
		ss.append("from FolderPo as t ");
		List<FolderPo> polist = this.baseDAO.getGenericByHql(ss.toString());
		return BeanCopy.copys(FolderVo.class, polist);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.cms.services.folder.FolderService#findFolderById(java.lang.String)
	 */
	@Override
	public FolderVo findFolderById(String recid) {
		return BeanCopy.copy(FolderVo.class, this.baseDAO.get(FolderPo.class, GUID.valueOf(recid).toBytes()));
	}

}
