package com.spark.cms.action.goods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.form.CategoryPropertiesForm;
import com.spark.cms.services.form.CategoryPvaluesForm;
import com.spark.cms.services.form.GoodsCategoryForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.vo.EGoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryTree;
import com.spark.cms.services.vo.GoodsCategoryVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.LightGoodsCategoryTree;

@Controller
public class GoodsCategoryAction extends BaseAction {
	
	@Autowired
	private GoodsCategoryService categoryService;
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/goods/getCategoryTree")
	@ResponseBody
	@Deprecated
	public ResponseEntity<List<GoodsCategoryTree>> getGoodsCategoryTree() {
		GoodsCategoryTree categoryTree = categoryService.getGoodsCategoryTree();
		categoryTree.setId(GoodsCategoryTree.Root_ID);
		categoryTree.setText(GoodsCategoryTree.Root_Text);
		List<GoodsCategoryTree> list = new ArrayList<GoodsCategoryTree>();
		list.add(categoryTree);
		return ResponseEntityUtil.getResponseEntity(list);
	} 
	
	@RequestMapping(value = "/goods/getLightGoodsCategoryTree")
	@ResponseBody
	public String getLightGoodsCategoryTree() {
		LightGoodsCategoryTree categoryTree = categoryService.getLightGoodsCategoryTree();
		if (null == categoryTree) return "";
		return JSONArray.fromObject(categoryTree).toString();
	}
	
	@RequestMapping(value = "/goods/getTreeChildrenNode")
	@ResponseBody
	public String getGoodsCategoryTree(String id) {
		GoodsCategoryTree categoryTree = categoryService.getGoodsCategoryTreeNode(id);
		if (GoodsCategoryTree.Root_ID.equals(id) || null == id) {
			return JSONArray.fromObject(categoryTree).toString();
		} else {
			if (categoryTree.getChildren() == null) return "";
			return JSONArray.fromObject(categoryTree.getChildren()).toString();
		}
	}
	
	
//	getGoodsCategoryTreeNode
	@RequestMapping(value = "/goods/modifyCategory")
	public ResponseEntity<MessageModel> modify(HttpSession session, String categoryId, String categoryNo, String categoryName) {
		if (StringUtil.isEmpty(categoryId) || StringUtil.isEmpty(categoryName) || StringUtil.isEmpty(categoryNo)) {
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "提供信息不正确"));
		}
		categoryName = categoryName.trim();
		categoryNo = categoryNo.trim();
		GoodsCategoryVo goodsCategory = categoryService.getGoodsCategorySimpleInfoById(categoryId);
		if (null == goodsCategory) {
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "该分类已经不存在"));
		}
		try {
			int existResult = categoryService.isCategoryExist(goodsCategory.getParentid() == null ? GoodsCategoryTree.Root_ID : goodsCategory.getParentid(), categoryNo, categoryName, categoryId);
			if (Constant.NOT_EXIST == existResult) {
				UserExtForm loginUser = (UserExtForm) session.getAttribute(Constant.LoginAdminUser);
				boolean result = categoryService.modifyGoodsCategoryName(loginUser, categoryId, categoryNo, categoryName);
				return ResponseEntityUtil.getResponseEntity(result ? Success : Failure);
			} else if (Constant.CATEGORYNO_EXIST == existResult) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "分类编号已存在，请重新填写。"));
			} else if (Constant.CATEGORYNAME_EXIST == existResult) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "分类名称已存在，请重新填写。"));
			} else {
				return ResponseEntityUtil.getResponseEntity(Failure);
			}
			
		} catch (Exception e) {
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	@RequestMapping(value = "/goods/deleteCategory")
	public ResponseEntity<MessageModel> delete(String categoryId) {
		try {
			GetGoodsListKey key = new GetGoodsListKey();
			key.setGoodsCategoryId(categoryId);
			List<GoodsVo> goodsList = goodsService.getGoodsList(key);
			if (null != goodsList && goodsList.size() > 0) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "操作无效，原因：该分类下已存在商品，不能再删除该分类。"));
			}
			List<GoodsCategoryVo> chlidrenList = categoryService.getChildrenCategoryList(categoryId);
			if (null != chlidrenList && chlidrenList.size() > 1) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "操作无效，原因：该分类下还有子分类，不能再删除该分类。"));
			}
			boolean result = categoryService.deleteGoodsCategory(categoryId);
			return ResponseEntityUtil.getResponseEntity(result ? Success : Failure);
		} catch (Exception e) {
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/goods/modifyProperties")
	public ResponseEntity<MessageModel> modifyCategoryProperties(HttpSession session, String propertiesStr) {
		try {
			// propertiesStr = java.net.URLDecoder.decode(propertiesStr,"UTF-8");  
			JSONArray proArray = JSONArray.fromObject(propertiesStr);
			if (proArray.size() < 1) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "操作无效，原因：分类属性不能为空。"));
			}
			CategoryPropertiesForm[] properties = new CategoryPropertiesForm[proArray.size()];
			for(int index = 0; index < proArray.size(); index++) {
				JSONObject jsonObj = JSONObject.fromObject(proArray.get(index));
				CategoryPropertiesForm property = new CategoryPropertiesForm();
				String categoryId = jsonObj.getString("categoryid");
				String propertiyname = jsonObj.getString("propertiyname");
				int ordinal = jsonObj.getInt("ordinal");
				property.setCategoryid(categoryId);
				property.setPropertiyname(propertiyname);
				property.setOrdinal(ordinal);
				JSONArray jsonValues = jsonObj.getJSONArray("values");
				CategoryPvaluesForm[] values = new CategoryPvaluesForm[jsonValues.size()];
				for (int vIndex = 0; vIndex < jsonValues.size(); vIndex++) {
					CategoryPvaluesForm value = new CategoryPvaluesForm();
					JSONObject jsonValue = JSONObject.fromObject(jsonValues.get(vIndex));
					value.setCategoryid(categoryId);
					value.setPvalue(jsonValue.getString("pvalue"));
					value.setOrdinal(jsonValue.getInt("ordinal"));
					values[vIndex] = value;
				}
				property.setValues(values);
				properties[index] = property;
			}
			GetGoodsListKey key = new GetGoodsListKey();
			key.setGoodsCategoryId(properties[0].getCategoryid());
			List<GoodsVo> goodsList = goodsService.getGoodsList(key);
			if (null != goodsList && goodsList.size() > 0) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "操作无效，原因：该分类下已存在商品，不能再修改分类属性。"));
			} else {
				boolean result = categoryService.modifyGoodsCategoryProperties(null, properties);
				return ResponseEntityUtil.getResponseEntity(result ? Success : Failure);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	@RequestMapping(value = "/goods/getCategoryInfo")
	@ResponseBody
	public ResponseEntity<GoodsCategoryForm> getGoodsCategory(String categoryId) {
		GoodsCategoryForm categoryInfo = categoryService.getGoodsCategoryById(categoryId);
		return ResponseEntityUtil.getResponseEntity(categoryInfo);
		//return categoryInfo;
	}
	
	@RequestMapping(value = "/goods/createGoodsCategory")
	public ResponseEntity<MessageModel> createGoodsCategory(HttpSession session, String categoryNo, String categoryName, String parentId) {
		UserExtForm loginUser = (UserExtForm) session.getAttribute(Constant.LoginAdminUser);
		try {
			if (StringUtil.isEmpty(categoryNo) || StringUtil.isEmpty(categoryName)) return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "分类编号和名称都不能为空"));
			categoryNo = categoryNo.trim();
			categoryName = categoryName.trim();
			int existResult = categoryService.isCategoryExist(parentId, categoryNo, categoryName, null);
			if (Constant.NOT_EXIST == existResult) {
				categoryService.createGoodsCategory(loginUser, categoryNo, categoryName, parentId);
				return ResponseEntityUtil.getResponseEntity(Success);
			} else if (Constant.CATEGORYNO_EXIST == existResult) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "分类编号已存在，请重新填写。"));
			} else if (Constant.CATEGORYNAME_EXIST == existResult) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "分类名称已存在，请重新填写。"));
			} else {
				return ResponseEntityUtil.getResponseEntity(Failure);
			}
			
		} catch (Exception e) {
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	@RequestMapping(value = "/goods/getETreeChildrenNode")
	@ResponseBody
	public String getEGoodsCategoryTree(String id) {
		EGoodsCategoryTree categoryTree = categoryService.getEGoodsCategoryTreeNode(id);
		if (categoryTree.getChildren() == null) return "";
		if (EGoodsCategoryTree.ERP_ROOT_ID_STR.equals(id) || null == id) {
			return JSONArray.fromObject(categoryTree).toString();
		} else {
			return JSONArray.fromObject(categoryTree.getChildren()).toString();
		}
		// @RequestParam(value = "page", required = false)
	}
	
	@RequestMapping(value = "/goods/getDirectChildrenCategory")
	@ResponseBody
	public DataModel<GoodsCategoryVo> getChildrenCategoryList(String categoryId) {
		try {
			List<GoodsCategoryVo> childrenList = categoryService.getChildrenCategoryList(categoryId);
			return new DataModel<GoodsCategoryVo>(childrenList, childrenList.size());
		} catch (Exception e) {
			e.printStackTrace();
			return new DataModel<GoodsCategoryVo>(new ArrayList<GoodsCategoryVo>(), 0);
		}
	}
	
}
