/**
 * 
 */
package com.spark.cms.action.folder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.StringUtil;
import com.spark.base.type.GUID;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.common.tree.TreeModel.AttributesModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.folder.FolderService;
import com.spark.cms.services.form.folder.FolderTree;
import com.spark.cms.services.vo.FolderVo;

/**
 * @author Jideas
 * 
 */
@Controller
public class FolderAction extends BaseAction {

	@Autowired
	private FolderService folderService;

	/**
	 * 获取文件夹列表
	 */
	@RequestMapping("/folder/getFolders")
	@ResponseBody
	public List<TreeModel> getFolders(@RequestParam(value = "id", required = false)
	String id) {
		try {
			if (id == null || GUID.emptyID.toString().equals(id)) {
				TreeModel treeModel = new TreeModel();
				treeModel.setId(GUID.emptyID.toString());
				treeModel.setText("图片文件夹");
				treeModel.setState("open");
				AttributesModel attributesModel = treeModel.new AttributesModel();
				attributesModel.setCode("2");
				treeModel.setAttributes(attributesModel);
				List<TreeModel> list = new ArrayList<TreeModel>();
				list.add(treeModel);
				List<FolderVo> folderList = folderService.getFoldersByParentId(GUID.emptyID.toString());
				if (folderList == null || folderList.isEmpty()) {
					return list;
				}
				folderConventerToTreeModel(folderList);
				treeModel.setChildren(folderConventerToTreeModel(folderList).toArray(new TreeModel[folderList.size()]));
				return list;
			} else {
				List<FolderVo> folderList = folderService.getFoldersByParentId(id);
				return folderConventerToTreeModel(folderList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取文件夹发生异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 新增或更新文件夹
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/folder/saveFolder")
	@ResponseBody
	public ResponseEntity<FolderTree> saveFolder(FolderVo folder) throws ServiceMessage {
		FolderVo ref = null;
		try {
			if (StringUtil.isEmpty(folder.getRecid())) {
				ref = folderService.createFolder(folder);
			} else {
				ref = folderService.updateFolder(folder);
			}
			if (ref == null) {
				return ResponseEntityUtil.getResponseEntity(new FolderTree(false, "文件夹名称重复！", null));
			}
			List<FolderVo> list = new ArrayList<FolderVo>();
			list.add(ref);
			return ResponseEntityUtil.getResponseEntity(new FolderTree(true, "文件夹保存成功！",
					folderConventerToTreeModel(list)));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增文件夹发生异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/folder/deleteFolder")
	@ResponseBody
	public ResponseEntity<MessageModel> deleteFolder(@RequestParam(value = "id", required = false)
	String id) {
		try {
			List<FolderVo> list = this.folderService.getFoldersByParentId(id);
			if (list != null && !list.isEmpty()) {
				return new ServiceMessage(false, "该文件夹不为空，不能删除！").getMessageModel();
			}
			return folderService.deleteFolder(id).getMessageModel();
		} catch (Exception e) {
			log.error("删除文件夹发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 将文件夹转换为对应的TreeModel
	 */
	private List<TreeModel> folderConventerToTreeModel(List<FolderVo> folderList) {
		List<TreeModel> treeModelList = new ArrayList<TreeModel>();
		FolderVo folder;
		TreeModel treeModel;
		for (int i = 0; null != folderList && i < folderList.size(); i++) {
			folder = folderList.get(i);
			treeModel = new TreeModel();
			treeModel.setId(folder.getRecid());
			treeModel.setText(folder.getTitle());
			treeModel.setState(isHasNodes(folder.getRecid()));
			AttributesModel am = treeModel.new AttributesModel();
			am.setParentId(folder.getParentId());
			am.setCode(folderService.getFoldersByParentId(folder.getRecid()).size() < 1 ? "0" : "1");
			treeModel.setAttributes(am);
			treeModelList.add(treeModel);
		}
		return treeModelList;
	}

	/**
	 * 判断节点是否有子节点
	 */
	private String isHasNodes(String id) {
		if (folderService.getFoldersByParentId(id).size() < 1) {
			return "open";
		}
		return "closed";
	}
}
