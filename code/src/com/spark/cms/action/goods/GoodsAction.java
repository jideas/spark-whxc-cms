package com.spark.cms.action.goods;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.utils.BeanUtils;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.utils.BeanCopy;
import com.spark.cms.common.Constant;
import com.spark.cms.common.Constant.Base.GoodsType;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.GoodsContentForm;
import com.spark.cms.services.form.GoodsForm;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.goods.GetEGoodsListKey;
import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.goods.GoodsAdvanceSearch;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goods.ModifyGoodsVoTask;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.image.ImageService;
import com.spark.cms.services.vo.EGoodsCategoryTree;
import com.spark.cms.services.vo.EGoodsVo;
import com.spark.cms.services.vo.GoodsContentVo;
import com.spark.cms.services.vo.GoodsPromotionVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.ImageInfoVo;
import com.spark.cms.services.vo.MemberChargeFlowVo;

@Controller
public class GoodsAction extends BaseAction {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsPromotionService promotionService;
	@Autowired
	private ImageService imageService;
	/**
	 * 获取商品列表
	 */
	@RequestMapping("/goods/getGoodsList")
	@ResponseBody
	public DataModel<GoodsVo> getGoodsList(String categoryId, String searchText, String isPublised, 
			String page, String rows, String advance) {
		DataModel<GoodsVo> dm = new DataModel<GoodsVo>();
		try {
			GetGoodsListKey key = new GetGoodsListKey();
			if (StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(rows)) {
				key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
				key.setPageSize(Integer.parseInt(rows));
			}
			key.setGoodsCategoryId(categoryId);
			if (StringUtil.isNotEmpty(searchText)) {
				key.setSearchText(searchText);
			}
			if (StringUtil.isNotEmpty(isPublised) && "1".equals(isPublised)) {
				key.setPublished(true);
			}
			key.setAdvanceSearch(converAdvance(advance));
			List<GoodsVo> goodsList = goodsService.getGoodsList(key);
			dm.setRows(goodsList);
			dm.setTotal(goodsService.getGoodsTotalCount(key));
			return dm;
		} catch (Exception e) {
			log.error("获取商品列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return dm;
		}
	}
	
	private GoodsAdvanceSearch converAdvance(String jsonStr) {
		if (StringUtil.isEmpty(jsonStr)) return null;
		JSONObject jsonAdvance = JSONObject.fromObject(jsonStr);
		GoodsAdvanceSearch advanceSearch = new GoodsAdvanceSearch();
		String goodsType = jsonAdvance.getString("goodsType");
		String goodsVantageType = jsonAdvance.getString("goodsVantageType");
		String isMostSale = jsonAdvance.getString("isMostSale");
		String isPopular = jsonAdvance.getString("isPopular");
		
		if (StringUtil.isNotEmpty(goodsType)) {
			advanceSearch.setGoodsType(goodsType);
		}
		if (StringUtil.isNotEmpty(goodsVantageType)) {
			advanceSearch.setGoodsVantageType(goodsVantageType);
		}
		if (StringUtil.isNotEmpty(isMostSale)) {
			if (isMostSale.trim().equals("1")) {
				advanceSearch.setIsMostSale(true);
			} else if (isMostSale.trim().equals("0")){
				advanceSearch.setIsMostSale(false);
			}
		}
		if (StringUtil.isNotEmpty(isPopular)) {
			if (isPopular.trim().equals("1")) {
				advanceSearch.setIsPopular(true);
			} else if (isPopular.trim().equals("0")){
				advanceSearch.setIsPopular(false);
			}
		}
		return advanceSearch;
	}
	
	/**
	 * 指定商品的基本信息
	 */
	@RequestMapping("/goods/getGoodsVo")
	@ResponseBody
	public ResponseEntity<GoodsVo> getGoodsVo(String goodsId) {
		try {
			GoodsVo goodsVo = goodsService.getGoodsVo(goodsId);
			return ResponseEntityUtil.getResponseEntity(goodsVo);
		} catch (Exception e) {
			log.error("获取商品列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改商品的基本信息（只能修改属性值）
	 */
	@RequestMapping("/goods/modifyGoodsVo")
	@ResponseBody
	public ResponseEntity<MessageModel> modifyGoodsVo(String dataString) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(dataString);
			String goodsId = jsonObj.getString("goodsId");
			ModifyGoodsVoTask task = new ModifyGoodsVoTask(goodsId);
			String property1 = jsonObj.getString("property0");
			String property2 = jsonObj.getString("property1");
			String property3 = jsonObj.getString("property2");
			String property4 = jsonObj.getString("property3");
			String property5 = jsonObj.getString("property4");
			
			String inventoryCountStr = jsonObj.getString("inventoryCount");
			double inventoryCount = 0;
			//if (StringUtil.isNumeric(inventoryCountStr)) {
				inventoryCount = DoubleUtil.strToDouble(inventoryCountStr);
			//}
			boolean isMostSales = jsonObj.getBoolean("isMostSales");
			boolean isPopular = jsonObj.getBoolean("isPopular");
			//double realprice = jsonObj.getDouble("realprice");
			String vantagestype = jsonObj.getString("vantagestype");
			boolean freedelivery = jsonObj.getBoolean("freedelivery");
			String goodsType = jsonObj.getString("goodsType");
			if (GoodsType.getType(goodsType) == null) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "商品类型不匹配，请重新输入"));
			}
			
			task.setProperty1(property1);
			task.setProperty2(property2);
			task.setProperty3(property3);
			task.setProperty4(property4);
			task.setProperty5(property5);
			task.setInventoryCount(inventoryCount);
			task.setMostSales(isMostSales);
			task.setPopular(isPopular);
			//task.setRealprice(realprice);
			task.setVantagestype(vantagestype);
			task.setFreedelivery(freedelivery);
			task.setGoodsType(GoodsType.getType(goodsType));
			//task.setBook(isBook);
			
			boolean result = goodsService.modifyGoodsVo(task);
			return ResponseEntityUtil.getResponseEntity(result ? Success : Failure);
		} catch (Exception e) {
			log.error("获取商品列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	@RequestMapping("/postAjax")
	public String postAjax(){
		return "/pub/ajaxpost";
	}
	
	/**
	 * 新增商品
	 */
	@RequestMapping("/goods/createGoods")
	@ResponseBody
	public ResponseEntity<MessageModel> addGoods(String categoryId, String[] eGoodsIds) {
		if (null == eGoodsIds) return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "数据有误"));
		try {
			goodsService.createGoodsFromERP(categoryId, eGoodsIds);
			return ResponseEntityUtil.getResponseEntity(new MessageModel(true, "商品已成功添加。"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("批量创建商品时异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "添加商品失败。"));
		}
		
		
	}
	/*
	public ResponseEntity<MessageModel> addGoods(String dataString) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(dataString);
			String categoryId = jsonObj.getString("categoryId");
			GoodsVo goodsVo = new GoodsVo();
			goodsVo.setRecid(jsonObj.getString("recid"));
			goodsVo.setGoodscode(jsonObj.getString("goodsCode"));
			goodsVo.setGoodsname(jsonObj.getString("goodsName"));
			goodsVo.setGoodsno(jsonObj.getString("goodsNo"));
			goodsVo.setGoodsspec(jsonObj.getString("goodsSpec"));
			goodsVo.setGoodsunit(jsonObj.getString("goodsUnit"));
			goodsVo.setOriginalprice(jsonObj.getDouble("originalPrice"));
			goodsVo.setVantagestype(jsonObj.getString("vantagestype"));
			goodsVo.setRealprice(jsonObj.getDouble("salePrice"));
			goodsVo.setFreedelivery(jsonObj.getBoolean("freedelivery"));
			
			String inventoryCountStr = jsonObj.getString("inventoryCount");
			double inventoryCount = 0;
			if (StringUtil.isNumeric(inventoryCountStr)) {
				inventoryCount = DoubleUtil.strToDouble(inventoryCountStr);
			}
			goodsVo.setInventoryCount(inventoryCount);
			goodsVo.setMostSales(jsonObj.getBoolean("isMostSales"));
			goodsVo.setPopular(jsonObj.getBoolean("isPopular"));
			String goodsType = jsonObj.getString("goodsType");
			if (GoodsType.getType(goodsType) == null) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "商品类型不匹配，请重新输入"));
			}
			goodsVo.setGoodsType(jsonObj.getString("goodsType"));
			String property1 = jsonObj.getString("property0");
			String property2 = jsonObj.getString("property1");
			String property3 = jsonObj.getString("property2");
			String property4 = jsonObj.getString("property3");
			String property5 = jsonObj.getString("property4");
			goodsVo.setPropertiy1(StringHelper.isEmpty(property1) ? null : property1);
			goodsVo.setPropertiy2(StringHelper.isEmpty(property2) ? null : property2);
			goodsVo.setPropertiy3(StringHelper.isEmpty(property3) ? null : property3);
			goodsVo.setPropertiy4(StringHelper.isEmpty(property4) ? null : property4);
			goodsVo.setPropertiy5(StringHelper.isEmpty(property5) ? null : property5);
			boolean isSuccess = goodsService.createGoods(categoryId, goodsVo);
			return ResponseEntityUtil.getResponseEntity(isSuccess ? Success : Failure);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增商品发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	*/
	@RequestMapping("/goods/deleteGoods")
	@ResponseBody
	public ResponseEntity<MessageModel> deleteGoods(String goodsId) {
		try {
			GoodsVo goods = goodsService.getGoodsVo(goodsId);
			if (goods.isPublished()) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "操作失败。原因：该商品已发布，不能进行删除操作。"));
			}
			boolean result = goodsService.deleteGoods(goodsId);
			return ResponseEntityUtil.getResponseEntity(result ? Success : Failure);
		} catch (Exception e) {
			log.error("获取商品列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	@RequestMapping(value = "/goods/getEGoodsList")
	@ResponseBody
	public DataModel<EGoodsVo> getEGoodsList(String categoryId, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false)String rows) {
		GetEGoodsListKey key = new GetEGoodsListKey();
		if (StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(rows)) {
			key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
			key.setCount(Integer.parseInt(rows));
		}
		if (StringHelper.isEmpty(categoryId)
				|| EGoodsCategoryTree.ERP_ROOT_ID_STR.equals(categoryId)) {
			key.setCategoryId(null);
		} else {
			key.setCategoryId(categoryId);
		}
		List<EGoodsVo> goodsList = goodsService.getEGoodsList(key);
		return new DataModel<EGoodsVo>(goodsList, goodsService.getEGoodsTotalCount(key));
	}
	
	@RequestMapping("/goods/getVantageList")
	@ResponseBody
	public List<DicItem> getVantageList() {
		try {
			List<DicItem> resultList = goodsService.getVantageList();
			return resultList;
		} catch (Exception e) {
			log.error("获取商品列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return new ArrayList<DicItem>();
		}
	}
	@RequestMapping("/goods/getGoodsTypeList")
	@ResponseBody
	public String getGoodsTypeList() {
		String jsonStr = "[" + GoodsType.Common.toJsonString() + ", " + GoodsType.Booking.toJsonString() + "]";
		return JSONArray.fromObject(jsonStr).toString();
	}
	
	/**
	 * 发布商品 -> 获取发布商品列表
	 */
	@RequestMapping("/goods/getPublishGoods")
	@ResponseBody
	public DataModel<GoodsVo> getPublishGoods(
			String categoryId,
			@RequestParam(value = "searchWord", required = false) String searchWord,
			@RequestParam(value = "ispublished", required = false) String ispublished,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false)	String rows, String advance) {
		DataModel<GoodsVo> dm = new DataModel<GoodsVo>();
		try {			
			GetGoodsListKey key = new GetGoodsListKey();
			searchWord = searchWord == null ? "" : searchWord;
			key.setSearchText(searchWord);
			key.setPageSize(Integer.parseInt(rows));
			key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
			boolean ispublished_ = "1".equals(ispublished);
			key.setPublished(ispublished_);
			if (StringUtil.isNotEmpty(categoryId)) {
				key.setGoodsCategoryId(categoryId);
			}
			key.setAdvanceSearch(converAdvance(advance));
			List<GoodsVo> gvList = goodsService.getGoodsList(key);			
			if(gvList != null && gvList.size() > 0){
				dm.setRows(gvList);
				dm.setTotal(goodsService.getGoodsTotalCount(key));
			}			
			return dm;
		} catch (Exception e) {
			log.error("获取发布商品列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return dm;
		}
	}
	
	/**
	 * 发布商品 -> 导出商品
	 */
	@RequestMapping("/goods/exportGoodsPublish")
	@ResponseBody
	public void exportGoodsPublish(HttpServletResponse response,
			@RequestParam(value = "categoryId", required = false) String categoryId,
			@RequestParam(value = "searchWord", required = false) String searchWord,
			@RequestParam(value = "ispublished", required = false) String ispublished) {
		try {
			//获取商品信息
			GetGoodsListKey key = new GetGoodsListKey();
			searchWord = searchWord == null ? "" : searchWord;
			key.setSearchText(searchWord);
			key.setPageSize(Integer.MAX_VALUE);
			key.setOffset(0);
			boolean ispublished_ = "1".equals(ispublished);
			key.setPublished(ispublished_);
			if (StringUtil.isNotEmpty(categoryId)) {
				key.setGoodsCategoryId(categoryId);
			}
			List<GoodsVo> gvList = goodsService.getGoodsList(key);			
			//导出设置
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=goods.xls");
			response.setContentType("application/msexcel");
			//excel设置
			WritableWorkbook wbook = jxl.Workbook.createWorkbook(os); // 建立excel文件    
		    WritableSheet wsheet = wbook.createSheet("商品信息", 0); // sheet名称
			//表头设置
		    WritableFont titleStyle = new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD);
		    WritableCellFormat titleFormat = new WritableCellFormat(titleStyle);
		    titleFormat.setBackground(Colour.GREY_25_PERCENT);
		    titleFormat.setAlignment(Alignment.CENTRE);
			wsheet.addCell(new Label(0, 0, "编号",titleFormat));
			wsheet.addCell(new Label(1, 0, "条码",titleFormat));
			wsheet.addCell(new Label(2, 0, "名称",titleFormat));
			wsheet.addCell(new Label(3, 0, "规格",titleFormat));
			wsheet.addCell(new Label(4, 0, "单位",titleFormat));
			wsheet.addCell(new Label(5, 0, "原价",titleFormat));
			wsheet.addCell(new Label(6, 0, "现价",titleFormat));
			wsheet.addCell(new Label(7, 0, "积分类别",titleFormat));
			wsheet.addCell(new Label(8, 0, "是否免费送货",titleFormat));
			wsheet.addCell(new Label(9, 0, "发布状态",titleFormat));
			//excel添加数据
			WritableFont contentStyle = new WritableFont(WritableFont.createFont("宋体"),12);
		    WritableCellFormat contentFormat = new WritableCellFormat(contentStyle);
		    WritableCellFormat contentCenterFormat = new WritableCellFormat(contentStyle);
		    contentCenterFormat.setAlignment(Alignment.CENTRE);
		    wsheet.setColumnView(0, 15);
		    wsheet.setColumnView(1, 18);
		    wsheet.setColumnView(2, 35);
		    wsheet.setColumnView(3, 20);
		    wsheet.setColumnView(4, 8);
		    wsheet.setColumnView(5, 10);
		    wsheet.setColumnView(6, 10);
		    wsheet.setColumnView(7, 15);
		    wsheet.setColumnView(8, 20);
		    wsheet.setColumnView(9, 15);
			for(int i = 0;gvList != null && i < gvList.size();i++){
				GoodsVo gv = gvList.get(i);
				String it = gv.getVantagestype();
				String scoreType = "";
				if("0".equals(it)){
					scoreType = "无积分";
				}else if("1".equals(it)){
					scoreType = "普通积分";
				}else if("2".equals(it)){
					scoreType = "双倍积分";
				}
				String isFree = gv.isFreedelivery() ? "免费" : "收费";
				String isPublished = gv.isPublished() ? "已发布" : "未发布";
			    wsheet.addCell(new Label(0, i+1, gv.getGoodscode(),contentFormat));
			    wsheet.addCell(new Label(1, i+1, gv.getGoodsno(),contentFormat));
			    wsheet.addCell(new Label(2, i+1, gv.getGoodsname(),contentFormat));
			    wsheet.addCell(new Label(3, i+1, gv.getGoodsspec(),contentFormat));
			    wsheet.addCell(new Label(4, i+1, gv.getGoodsunit(),contentCenterFormat));
			    wsheet.addCell(new Number(5, i+1, gv.getOriginalprice(),contentFormat));
			    wsheet.addCell(new Number(6, i+1, gv.getRealprice(),contentFormat));
			    wsheet.addCell(new Label(7, i+1, scoreType,contentCenterFormat));
			    wsheet.addCell(new Label(8, i+1, isFree,contentCenterFormat));
			    wsheet.addCell(new Label(9, i+1, isPublished,contentCenterFormat));
			}           
	        //资源回收
			wbook.write();
			wbook.close();
			os.close();
		} catch (Exception e) {
			log.error("导出发布商品列表发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
	}
	
	/**
	 * 发布商品 -> 修改商品 -> 根据商品ID获取商品事项
	 */
	@RequestMapping("/goods/getPublishGoodsEffacts")
	@ResponseBody
	public ResponseEntity<List<GoodsContentVo>> getPublishGoodsEffacts(@RequestParam(value = "goodsId", required = false) String goodsId) {
		try {
			return ResponseEntityUtil.getResponseEntity(goodsService.getGoodsContentList(goodsId));
		} catch (Exception e) {
			log.error("获取发布商品列表发生异常====" + e.getStackTrace());
			return null;
		}
	}
		
	/**
	 * 发布商品 -> 取消发布
	 */
	@RequestMapping("/goods/cancelPublished")
	@ResponseBody
	public ResponseEntity<MessageModel> cancelPublished(@RequestParam(value = "goodsId", required = false) String goodsId, HttpServletRequest request){
		try{
			if (request.getSession().getAttribute(Constant.LoginAdminUser) == null) return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "操作失败，请先登录!"));
			UserExtForm loginUser = (UserExtForm) request.getSession().getAttribute(Constant.LoginAdminUser);
			GoodsVo gv = goodsService.getGoodsVo(goodsId);
			if(gv != null){
				GoodsForm gf = BeanCopy.copy(GoodsForm.class, gv);
				BeanUtils.copyProperties1(gv, gf);
				gf.setPublished(false);
				goodsService.modifyGoods(gf, loginUser);
			}
			return ResponseEntityUtil.getResponseEntity(Success);
		}catch (ServiceMessage e) {
			log.error("取消商品发布发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}catch (Exception e) {
			log.error("取消商品发布发生异常====" + e.getStackTrace());
			e.printStackTrace();
		} 	
		return ResponseEntityUtil.getResponseEntity(Failure);
	}		
	/**
	 * 发布商品 -> 发布商品
	 */
	@RequestMapping("/goodsPublish/publishPublishGoods")
	@ResponseBody
	public ResponseEntity<MessageModel> publishPublishGoods(
			@RequestParam(value = "goodsContent", required = false) String goodsContent,
			@RequestParam(value = "isPublished", required = false) String isPublished,
			@RequestParam(value = "isVantagesGoods", required = false) String isVantagesGoods,
			GoodsVo goodsForm, HttpServletRequest request) {
		try {
			goodsContent = StringUtil.replaceBlank(goodsContent);
			//System.out.print(goodsContent);
			if (request.getSession().getAttribute(Constant.LoginAdminUser) == null) return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "操作失败，请先登录!"));
			UserExtForm loginUser = (UserExtForm) request.getSession().getAttribute(Constant.LoginAdminUser);
			if (goodsContent.startsWith(",")) {
				goodsContent = goodsContent.substring(1, goodsContent.length());
			}
			// goodsContent = URLDecoder.decode(goodsContent, "UTF-8");
			GoodsForm gf = BeanCopy.copy(GoodsForm.class, goodsForm);
			BeanUtils.copyProperties1(goodsForm, gf);
			
			boolean isPublised_ = "true".equals(isPublished) ? true : false;
			gf.setPublished(isPublised_) ;
			
			boolean isVantagesGoods_ = "on".equals(isVantagesGoods) ? true : false;
			gf.setVantagesGoods(isVantagesGoods_);			
			
			List<GoodsContentForm> gcfList = new ArrayList<GoodsContentForm>();
			gf.setGoodsContentForms(gcfList);
			JSONArray jsonArray = JSONArray.fromObject(goodsContent);
			for(int i = 0;i<jsonArray.size();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				GoodsContentForm gcf = new GoodsContentForm();
				
				gcf.setContenttitle(jsonObject.getString("name"));
				gcf.setGoodscontent(jsonObject.getString("content"));
				gcf.setOrdinal(Integer.parseInt(jsonObject.getString("ordinal")));
				gcf.setGoodsid(goodsForm.getRecid());
				
				gcfList.add(gcf);
			}
			goodsService.modifyGoods(gf, loginUser);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (ServiceMessage e) {
			log.error("发布商品发生异常====" + e.getStackTrace());
			e.printStackTrace();
		} catch (Exception e) {
			log.error("发布商品发生异常====" + e.getStackTrace());
			e.printStackTrace();
		}
		return ResponseEntityUtil.getResponseEntity(Failure);
	}

	/**
	 * 发布商品 -> 获取图片规格
	 */
	@RequestMapping("/goods/getPublishGoodsSpecial")
	@ResponseBody
	public ImageInfoVo getPublishGoodsSpecial(@RequestParam(value = "imgUrl", required = false) String imgUrl){
		try{
			ImageInfoVo imageInfoVo =  imageService.getImage(imgUrl);
			return imageInfoVo;		
		}catch(Exception e){
			log.error("获取图片规格发生异常====" + e.getStackTrace());
		}
		return null;
	}
	
	@RequestMapping("/goods/getGoodsPromotion")
	@ResponseBody
	public String getGoodsPromotionByIds(String goodsId) {
		if (null == goodsId) return null;
		GoodsVo goods = goodsService.getGoodsVo(goodsId);
		GoodsPromotionVo promotion = promotionService.findByGoodsId(goodsId);
		String promotionInfo = "";
		double price = 0.0;
		if (promotion.getDisrate() > 0 && promotion.getDisrate() < 1) {
			price = DoubleUtil.mul(goods.getRealprice(), promotion.getDisrate());
			promotionInfo = " " + (promotion.getDisrate() * 10) + "折";
		}
		if (goods.isFreedelivery()) {
			promotionInfo += " 免费送货上门";
		}
		if (goods.getVantagestype().equals("2")) {
			promotionInfo += " 双倍积分";
		}
		return JSONObject.fromObject("{'promotionInfo':'" + promotionInfo + "', 'price':'" + DoubleUtil.getRoundStr(price, 2) + "'}").toString();
	}
	
}
