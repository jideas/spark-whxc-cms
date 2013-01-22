/**
 * 
 */
package com.spark.cms.services.folder;

import java.util.List;

import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.vo.FolderVo;

/**
 * @author Jideas
 */
public interface FolderService {

	/**
	 * @param emptyID
	 * @return
	 */
	List<FolderVo> getFoldersByParentId(String recid);
	/**
	 * @param emptyID
	 * @return
	 */
	List<FolderVo> getAllFolders();

	/**
	 * @param folder
	 * @return
	 */
	FolderVo createFolder(FolderVo folder);

	/**
	 * @param id
	 */
	ServiceMessage deleteFolder(String id);

	/**
	 * @param folder
	 * @return
	 */
	FolderVo updateFolder(FolderVo folder);

	/**
	 * @param folder
	 * @return
	 */
	boolean checkFolderIsNotHere(FolderVo folder);

	FolderVo findFolderById(String recid);
}
