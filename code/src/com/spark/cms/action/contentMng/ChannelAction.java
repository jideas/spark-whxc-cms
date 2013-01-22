package com.spark.cms.action.contentMng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.spark.cms.base.constant.ChannelType;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.ResponseEntityUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel; 
import com.spark.cms.common.Constant.MainMenu;
import com.spark.cms.common.Constant.ChannelEnum.PageType;
import com.spark.cms.common.tree.ChannelTree;
import com.spark.cms.common.tree.ChannelTree.AttributesModel;
import com.spark.cms.services.channel.ChannelService; 
import com.spark.cms.services.channel.ChannelThrowable;
import com.spark.cms.services.channel.GetChannelGoodsListKey;
import com.spark.cms.services.channel.GetChannelListKey;
import com.spark.cms.services.channel.info.FloorInfo;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.goods.GetGoodsListKey;
import com.spark.cms.services.goods.GoodsCategoryService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.vo.ChannelGoodsVo;
import com.spark.cms.services.vo.ChannelVo;
import com.spark.cms.services.vo.FloorAdvertisingVo;
import com.spark.cms.services.vo.FloorVo;
import com.spark.cms.services.vo.GoodsCategoryVo;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.PopularKeyWordsVo;

@Controller
public class ChannelAction extends BaseAction {

	@Autowired
	private ChannelService channelService;
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private GoodsService goodsService;
	
	
	/**
	 * ��Ŀ -> ��ȡ��Ŀ��
	 */
	@RequestMapping("/channel/getChannels")
	@ResponseBody
	public List<ChannelTree> getChannels(@RequestParam(value = "id", required = false) String id) {
		try {
			List<ChannelTree> r = new ArrayList<ChannelTree>();
			//��ʼ����
			if(CheckIsNull.isEmpty(id)){
				initTree(r);
				return r;
			}		
			//���˵�
			if(Constant.ChannelEnum.PageType.MainMenu.getCode().equals(id)) {
				List<ChannelVo> menuList = channelService.getMainMenuList();
				for(ChannelVo v : menuList){
					ChannelTree ct = new ChannelTree();
					convertChannelvoToChannelTree(v,ct);
					r.add(ct);					
				}
			} else {
				List<ChannelVo> list = null;
				GetChannelListKey key = new GetChannelListKey();
				if (PageType.SecondPage.getCode().equals(id)) {
					key.setPageType(PageType.SecondPage);
					list = channelService.getChannelList(key);
				} else if (PageType.HomePage.getCode().equals(id)) {
					key.setPageType(PageType.HomePage);
					list = channelService.getChannelList(key);
				} else {
					key.setFloorId(id);
					list = channelService.getChannelList(key);
				}
				if(CheckIsNull.isNotEmpty(list)){
					for(ChannelVo v:list){
						ChannelTree ct = new ChannelTree();
						convertChannelvoToChannelTree(v,ct);
						r.add(ct);					
					}
				}
			}
			if(Constant.ChannelEnum.PageType.HomePage.getCode().equals(id))
			{
				//���˵�
				ChannelTree homePage = new ChannelTree();
				
				AttributesModel pageAm = homePage.new AttributesModel();
				pageAm.setCode("");
				pageAm.setFloorid("");
				pageAm.setPagetype("");
				pageAm.setChanneltype("");		
				
				homePage.setId(Constant.ChannelEnum.PageType.MainMenu.getCode());
				homePage.setText(Constant.ChannelEnum.PageType.MainMenu.getName());
				homePage.setAttributes(pageAm);
				
				r.add(homePage);
				
				//��ȡ¥��
				List<FloorVo> fList = channelService.getFloorList(id);
				if(CheckIsNull.isNotEmpty(fList)){
					for(FloorVo f :fList){
						ChannelTree ct = new ChannelTree();
						convertFloorVoToChannelTree(f,ct);
						r.add(ct);					
					}
				}
			}
			return r;
		} catch (Exception e) {
			log.error("��ȡ��Ŀ�������쳣====" + e.getStackTrace());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * ��Ŀ -> ��ȡ��Ŀ�� -> ת��ChannelVoToChannelTree
	 */
	private void convertChannelvoToChannelTree(ChannelVo v,ChannelTree ct){
		AttributesModel am = ct.new AttributesModel();
		am.setCode(v.getCode());
		am.setFloorid(v.getFloorid());
		am.setPagetype(v.getPageType());
		am.setChanneltype(v.getChanneltype());
		am.setMainMenu(v.isMainMenu());
		if (MainMenu.joinus.getChannelId().equals(v.getRecid())
				|| MainMenu.oneyuan.getChannelId().equals(v.getRecid())
				|| MainMenu.vantagemall.getChannelId().equals(v.getRecid())) {
			am.setAutoContent(true);
		}
		
		ct.setId(v.getRecid());
		ct.setText(v.getName());
		ct.setAttributes(am);
		
		//if(CheckIsNull.isEmpty(v.getFloorid())){
		ct.setState("true");
		//}
	}
	
	/**
	 * ��Ŀ -> ��ȡ��Ŀ�� -> ת��FloorVoToChannelTree
	 */
	private void convertFloorVoToChannelTree(FloorVo f,ChannelTree ct){
		AttributesModel am = ct.new AttributesModel();
		am.setOrdinal(""+f.getOrdinal());
		am.setFloortype(f.getFloortype());
		am.setTheme(f.getTheme());
		am.setIsfloor(true);
		
		ct.setId(f.getRecid());
		ct.setText(f.getTitle());
		ct.setAttributes(am);
		//ct.setState("open");
	}
	
	/**
	 * ��Ŀ -> ��ȡ��Ŀ�� -> ��ʼ����
	 */
	private void initTree(List<ChannelTree> r){
		ChannelTree homePage = new ChannelTree();
		
		AttributesModel pageAm = homePage.new AttributesModel();
		pageAm.setCode("");
		pageAm.setFloorid("");
		pageAm.setPagetype("");
		pageAm.setChanneltype("");		
		
		homePage.setId(Constant.ChannelEnum.PageType.HomePage.getCode());
		homePage.setText(Constant.ChannelEnum.PageType.HomePage.getName());
		homePage.setAttributes(pageAm);
		
		ChannelTree secondPage = new ChannelTree();
		
		AttributesModel secondAm = secondPage.new AttributesModel();
		secondAm.setCode("");
		secondAm.setFloorid("");
		secondAm.setPagetype("");
		secondAm.setChanneltype("");
		
		secondPage.setId(Constant.ChannelEnum.PageType.SecondPage.getCode());
		secondPage.setText(Constant.ChannelEnum.PageType.SecondPage.getName());
		secondPage.setAttributes(secondAm);
		
		r.add(homePage);
		r.add(secondPage);
	}
	
	/**
	 * ��Ŀ -> ��ȡ��Ŀ����
	 * 
	 * @return
	 */
	@RequestMapping("/channel/getChannelTypeList")
	@ResponseBody
	public List<DicItem> getActiveStatusList() {
		List<DicItem> list = new ArrayList<DicItem>();
		list.add(new DicItem(Constant.ChannelEnum.ChannelType.Goods.getCode(), Constant.ChannelEnum.ChannelType.Goods.getName()));
		list.add(new DicItem(Constant.ChannelEnum.ChannelType.Advertise.getCode(), Constant.ChannelEnum.ChannelType.Advertise.getName()));
		list.add(new DicItem(Constant.ChannelEnum.ChannelType.Content.getCode(), Constant.ChannelEnum.ChannelType.Content.getName()));
		return list;
	}
	
	/**
	 * ��Ŀ -> ����
	 */
	@RequestMapping("/channel/addChannel")
	@ResponseBody
	public MessageModel saveChinnel(String dataString) {
		ChannelVo channelVo = new ChannelVo();
		try {
			JSONObject info = JSONObject.fromObject(dataString);
			//channelVo.setRecid(info.getString("recid"));
			channelVo.setCode(info.getString("code"));
			channelVo.setName(info.getString("name"));
			channelVo.setChanneltype(info.getString("channeltype"));
			channelVo.setMainMenu(true);
			channelService.createChannel(channelVo);
			return Success;
		} catch (Exception e) {
			log.error("���������쳣====" + e.getStackTrace());
			return Failure;
		}
	}
	
	/**
	 * ��Ŀ -> �޸�
	 */
	@RequestMapping("/channel/updateChannel")
	@ResponseBody
	public MessageModel updateChannel(String dataString) {
		ChannelVo channelVo = new ChannelVo();
		try {
			JSONObject info = JSONObject.fromObject(dataString);
			channelVo.setRecid(info.getString("recid"));
			channelVo.setCode(info.getString("code"));
			channelVo.setName(info.getString("name"));
			channelVo.setChanneltype(info.getString("channeltype"));
			channelService.modifyChannel(channelVo);
			return Success;
		} catch (Exception e) {
			log.error("�޸���Ŀ�����쳣====" + e.getStackTrace());
			e.printStackTrace();
			return Failure;
		}
	}
	
	/**
	 * ��Ŀ -> ɾ��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/channel/deleteChannel")
	@ResponseBody
	public MessageModel deleteChinnel(@RequestParam(value = "id", required = true)
			String id) {
		try {
			channelService.delete(id);
			return Success;
		} catch (Exception e) {
			log.error("ɾ����Ŀ�����쳣====" + e.getStackTrace());
			return Failure;
		}
	}
	
	/**
	 * ¥�� -> ��ȡ¥������
	 * 
	 * @return
	 */
	@RequestMapping("/channel/getFloorTypeList")
	@ResponseBody
	public List<DicItem> getFloorTypeList() {
		List<DicItem> list = new ArrayList<DicItem>();
		list.add(new DicItem(Constant.ChannelEnum.FloorType.Advertise.getCode(), Constant.ChannelEnum.FloorType.Advertise.getName()));
		list.add(new DicItem(Constant.ChannelEnum.FloorType.Goods.getCode(), Constant.ChannelEnum.FloorType.Goods.getName()));
//		list.add(new DicItem(Constant.ChannelEnum.FloorType.Others.getCode(), Constant.ChannelEnum.FloorType.Others.getName()));
		return list;
	}
	
	/**
	 * ¥��/��¥ -> ��ȡ¥��theme
	 */
	@RequestMapping("/channel/getThemeList")
	@ResponseBody
	public List<DicItem> getThemeList() {
		List<DicItem> list = new ArrayList<DicItem>();
		list.add(new DicItem(Constant.ChannelEnum.Theme.Crazyred.getCode(), Constant.ChannelEnum.Theme.Crazyred.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Darkorange.getCode(), Constant.ChannelEnum.Theme.Darkorange.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Forestgreen.getCode(), Constant.ChannelEnum.Theme.Forestgreen.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Freshgreen.getCode(), Constant.ChannelEnum.Theme.Freshgreen.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Goldenrod.getCode(), Constant.ChannelEnum.Theme.Goldenrod.getName()));
		
		list.add(new DicItem(Constant.ChannelEnum.Theme.Mediumblue.getCode(), Constant.ChannelEnum.Theme.Mediumblue.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Mediumseagreen.getCode(), Constant.ChannelEnum.Theme.Mediumseagreen.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Orange.getCode(), Constant.ChannelEnum.Theme.Orange.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Peru.getCode(), Constant.ChannelEnum.Theme.Peru.getName()));
		list.add(new DicItem(Constant.ChannelEnum.Theme.Royalblue.getCode(), Constant.ChannelEnum.Theme.Royalblue.getName()));
		
		list.add(new DicItem(Constant.ChannelEnum.Theme.Yellow.getCode(), Constant.ChannelEnum.Theme.Yellow.getName()));		
		return list;
	}
	
	/**
	 * ¥�� -> ����
	 */
	@RequestMapping("/channel/addFloor")
	@ResponseBody
	public ResponseEntity<MessageModel> addFloor(String dataString) {
		try {
			// dataString = java.net.URLDecoder.decode(dataString,"UTF-8"); 
			JSONObject info = JSONObject.fromObject(dataString);
			if (info == null) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "������Ч��ԭ�����ݲ���Ϊ�ա�"));
			}
			FloorInfo floorInfo = new FloorInfo();
			floorInfo.setOrdinal(info.getInt("ordinal"));
			floorInfo.setFloortype(info.getString("floortype"));
			floorInfo.setGoodsCategoryId(info.getString("goodsCategoryId"));
			floorInfo.setTheme(info.getString("theme"));
			floorInfo.setTitle(info.getString("title"));
			if (info.getString("channelVos") != null) {
				JSONArray jsonValues = info.getJSONArray("channelVos");
				ChannelVo[] channelVos = new ChannelVo[jsonValues.size()];
				String[] channelIds = new String[jsonValues.size()];
				for (int vIndex = 0; vIndex < jsonValues.size(); vIndex++) {
					ChannelVo channelVo = new ChannelVo();
					JSONObject jsonValue = JSONObject.fromObject(jsonValues.get(vIndex));
					channelVo.setChanneltype(ChannelType.GoodsChannel.getCode());
					channelVo.setRecid(jsonValue.getString("recid"));
					channelVo.setName(jsonValue.getString("name"));
					channelVo.setCode(jsonValue.getString("code"));
					
					channelIds[vIndex] = channelVo.getRecid();
					channelVos[vIndex] = channelVo;
				}
				if(channelService.isChannelExist(channelIds)) {
					return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "������Ч��ԭ����ѡ���������Ѵ�������¥���У�������ѡ��"));
				}
				floorInfo.setChannelVos(channelVos);
			}
			if (StringUtil.isNotEmpty(info.getString("floorAdvertisingVo"))) {
				JSONArray jsonValues = info.getJSONArray("floorAdvertisingVo");
				FloorAdvertisingVo[] ads = new FloorAdvertisingVo[jsonValues.size()];
				for (int vIndex = 0; vIndex < jsonValues.size(); vIndex++) {
					FloorAdvertisingVo ad = new FloorAdvertisingVo();
					JSONObject jsonValue = JSONObject.fromObject(jsonValues.get(vIndex));
					ad.setImageurl(jsonValue.getString("imageurl"));
					ad.setTitle(jsonValue.getString("title"));
					ad.setUrl(jsonValue.getString("url"));
					ads[vIndex] = ad;
				}
				floorInfo.setAdvertisings(ads);
			}
			channelService.createFloor(floorInfo);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (ChannelThrowable e) {
			log.error("����¥�㷢���쳣====" + e.getMessage());
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, e.getMessage()));
		} 
//		catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "����ʧ�ܣ��������Ա��ϵ��"));
//		}
	}
	
	/**
	 * ¥�� -> �޸�
	 */
	@RequestMapping("/channel/updateFloor")
	@ResponseBody
	public ResponseEntity<MessageModel> updateFloor(String dataString) {
		try {
			// dataString = java.net.URLDecoder.decode(dataString,"UTF-8"); 
			JSONObject info = JSONObject.fromObject(dataString);
			if (info == null) {
				return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "������Ч��ԭ�����ݲ���Ϊ�ա�"));
			}
			FloorInfo floorInfo = new FloorInfo();
			floorInfo.setRecid(info.getString("recid"));
			floorInfo.setOrdinal(info.getInt("ordinal"));
			floorInfo.setFloortype(info.getString("floortype"));
			floorInfo.setGoodsCategoryId(info.getString("goodsCategoryId"));
			floorInfo.setTheme(info.getString("theme"));
			floorInfo.setTitle(info.getString("title"));
			floorInfo.setImageUrl(info.getString("imageUrl"));
			floorInfo.setUrl(info.getString("imageUrl"));
			if (info.getString("channelVos") != null) {
				JSONArray jsonValues = info.getJSONArray("channelVos");
				ChannelVo[] channelVos = new ChannelVo[jsonValues.size()];
				for (int vIndex = 0; vIndex < jsonValues.size(); vIndex++) {
					ChannelVo channelVo = new ChannelVo();
					JSONObject jsonValue = JSONObject.fromObject(jsonValues.get(vIndex));
					channelVo.setChanneltype(ChannelType.GoodsChannel.getCode());
					channelVo.setRecid(jsonValue.getString("recid"));
					channelVo.setName(jsonValue.getString("name"));
					channelVo.setCode(jsonValue.getString("code"));
					channelVos[vIndex] = channelVo;
				}
				floorInfo.setChannelVos(channelVos);
			}
			if (StringUtil.isNotEmpty(info.getString("floorAdvertisingVo"))) {
				JSONArray jsonValues = info.getJSONArray("floorAdvertisingVo");
				FloorAdvertisingVo[] ads = new FloorAdvertisingVo[jsonValues.size()];
				for (int vIndex = 0; vIndex < jsonValues.size(); vIndex++) {
					FloorAdvertisingVo ad = new FloorAdvertisingVo();
					JSONObject jsonValue = JSONObject.fromObject(jsonValues.get(vIndex));
					ad.setImageurl(jsonValue.getString("imageurl"));
					ad.setTitle(jsonValue.getString("title"));
					ad.setUrl(jsonValue.getString("url"));
					ads[vIndex] = ad;
				}
				floorInfo.setAdvertisings(ads);
			}
			channelService.modifyFloor(floorInfo);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (DataIntegrityViolationException dumplicateException) {
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "������Ч���������������з������������¥���С�"));
		} catch (ChannelThrowable e) {
			log.error("����¥�㷢���쳣====" + e.getMessage());
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "����ʧ�ܣ��������Ա��ϵ��"));
		} 
	}
	
	/**
	 * ¥�� -> ɾ��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/channel/deleteFloor")
	@ResponseBody
	public MessageModel deleteFloor(@RequestParam(value = "id", required = true)
			String id) {
		try {
			channelService.deleteFloor(id);
			return Success;
		} catch (Exception e) {
			log.error("ɾ����Ŀ�����쳣====" + e.getStackTrace());
			return Failure;
		}
	}
	
	/**
	 * ¥�� -> ɾ��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/channel/getFloorInfo")
	@ResponseBody
	public ResponseEntity<FloorInfo> getFloorInfo(@RequestParam(value = "id", required = true)
			String id) {
		try {
			FloorInfo floorInfo = channelService.findFloorInfo(id);
			return ResponseEntityUtil.getResponseEntity(floorInfo);
		} catch (Exception e) {
			log.error("ɾ����Ŀ�����쳣====" + e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * ��¥ -> ��ȡ��������
	 */
	@RequestMapping("/channel/getSecondLeverOfGoodsCategory")
	@ResponseBody
	public List<GoodsCategoryVo> getSecondLeverOfGoodsCategory(){
		try{
			return goodsCategoryService.getGoodsCateogryListByLevel(1);
		}catch(Exception e){
			log.error("��¥ -> ��ȡһ�������쳣====" + e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * ��¥ -> ��ȡ��������
	 */
	@RequestMapping("/channel/getThirdLeverOfGoodsCategory")
	@ResponseBody
	public List<ChannelTree> getThirdLeverOfGoodsCategory(@RequestParam(value = "categoryId", required = false) String categoryId){
		try{
			List<GoodsCategoryVo> categoryList = goodsCategoryService.getChildrenCategoryList(categoryId);
			List<ChannelTree> channelTreeList = new ArrayList<ChannelTree>();
			if(categoryList == null){
				return channelTreeList;
			}
			for(GoodsCategoryVo c : categoryList){
				ChannelTree ct = new ChannelTree();
				ct.setId(c.getRecid());
				ct.setText(c.getCategoryname());
				ct.setState("open");
				channelTreeList.add(ct);
			}
			return channelTreeList;
		}catch(Exception e){
			log.error("��¥ -> ��ȡ������������쳣====" + e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * �������� -> ����
	 */
	@RequestMapping("/channel/modifyPopularKeyWords")
	@ResponseBody
	public MessageModel modifyPopularKeyWords(PopularKeyWordsVo popularKeyWordsVo) {
		try {
			channelService.modifyPopularKeyWords(popularKeyWordsVo);
			return Success;
		} catch (Exception e) {
			log.error("�����쳣====" + e.getStackTrace());
			return Success;
		}
	}
	
	/**
	 * �������� -> ��ȡ
	 */
	@RequestMapping("/channel/getPopularKeyWords")
	@ResponseBody
	public PopularKeyWordsVo getPopularKeyWords() {
		try {
			return channelService.getPopularKeyWordsVo();
		} catch (Exception e) {
			log.error("�����쳣====" + e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * ѡ����Ʒ -> ������ĿID��ȡ��Ʒ
	 */
	@RequestMapping("/channel/getGoodsesByChannelId")
	@ResponseBody
	public DataModel<ChannelGoodsVo> getGoodsesByChannelId(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "page", required = false) String page, 
			@RequestParam(value = "rows", required = false) String rows) {
		DataModel<ChannelGoodsVo> dm = new DataModel<ChannelGoodsVo>();
		try{
			GetChannelGoodsListKey key = new GetChannelGoodsListKey();
			key.setChannelId(id);
			if (StringUtil.isNotEmpty(page)) {
				key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
				key.setPageSize(Integer.parseInt(rows));
			}
			List<ChannelGoodsVo> gvList = channelService.getChannelGoodsVos(key);
			dm.setRows(gvList);
			dm.setTotal(channelService.getChannelGoodsCount(key));
			return dm;
		} catch (Exception e) {
			log.error("������ĿID��ȡ��Ʒ====" + e.getStackTrace());
			return dm;
		}
	}
	
	/**
	 * ѡ����Ʒ -> ȷ��ѡ����Ʒ
	 */
	@RequestMapping("/channel/sureSelectGoods")
	@ResponseBody
	public ResponseEntity<MessageModel> sureSelectGoods(@RequestParam(value = "id", required = false) String channelId,
			@RequestParam(value = "ids[]", required = false) String[] ids,HttpSession session) {
		try{
			UserExtForm userExtForm = (UserExtForm)session.getAttribute(Constant.LoginAdminUser);
			for(String goodsId : ids){
				ChannelGoodsVo c = new ChannelGoodsVo();
				c.setChannelid(channelId);
				c.setCreatedate(new Date());
				c.setCreator(userExtForm.getName());
				c.setCreatorid(userExtForm.getId());
				c.setGoodsid(goodsId);
				channelService.createChannelGoods(c);
			}			
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("ȷ��ѡ����Ʒ====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	/**
	 * ѡ����Ʒ -> ������ĿIDɾ����Ʒ
	 */
	@RequestMapping("/channel/deleteGoodsByChannelId")
	@ResponseBody
	public ResponseEntity<MessageModel> deleteGoodsByChannelId(@RequestParam(value = "channelId", required = false) String channelId,
			@RequestParam(value = "goodsId", required = false) String goodsId) {
		try{
			channelService.deleteChannelGoods(channelId, goodsId);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (ChannelThrowable e) {
			log.error("������ĿIDɾ����Ʒ====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	/**
	 * ѡ����Ʒ -> ������Ŀ��Ʒ
	 */
	@RequestMapping("/channel/enabledGoodsOfChannelId")
	@ResponseBody
	public ResponseEntity<MessageModel> enabledGoodsOfChannelId(@RequestParam(value = "recid", required = false) String recid) {
		try{
			channelService.updateChannelGoodsStatus(recid, true);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("������Ŀ��Ʒ====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	/**
	 * ѡ����Ʒ -> ������Ŀ��Ʒ
	 */
	@RequestMapping("/channel/disabledGoodsOfChannelId")
	@ResponseBody
	public ResponseEntity<MessageModel> disabledGoodsOfChannelId(@RequestParam(value = "recid", required = false) String recid) {
		try{
			channelService.updateChannelGoodsStatus(recid, false);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("������Ŀ��Ʒ====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	/**
	 * ��ȡ��Ʒ�б�
	 */
	@RequestMapping("/channel/getGoodsForChannelSel")
	@ResponseBody
	public DataModel<GoodsVo> getGoodsList(String channelId, String categoryId, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false)String rows) {
		DataModel<GoodsVo> dm = new DataModel<GoodsVo>();
		try {
			String[] channelGoodsIds = channelService.getChannelGoodsIds(channelId);
			GetGoodsListKey key = new GetGoodsListKey();
			if (StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(rows)) {
				key.setOffset((Integer.parseInt(page) - 1) * Integer.parseInt(rows));
				key.setPageSize(Integer.parseInt(rows));
			}
			if (CheckIsNull.isNotEmpty(channelGoodsIds) && channelGoodsIds.length > 0) {
				key.setFilterIds(channelGoodsIds);
			}
			key.setPublished(true);
			key.setGoodsCategoryId(categoryId);
			List<GoodsVo> goodsList = goodsService.getGoodsList(key);
			dm.setRows(goodsList);
			dm.setTotal(goodsService.getGoodsTotalCount(key));
			return dm;
		} catch (Exception e) {
			log.error("��ȡ��Ʒ�б����쳣====" + e.getStackTrace());
			e.printStackTrace();
			return dm;
		}
	}
}
