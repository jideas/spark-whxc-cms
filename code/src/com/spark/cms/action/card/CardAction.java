/**
 * 
 */
package com.spark.cms.action.card;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.system.dic.DictionaryType;
import com.spark.base.common.system.dic.SparkDictionaryManager;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.constant.card.CardStatus;
import com.spark.cms.base.utils.ArrayUtils;
import com.spark.cms.base.utils.ExcelReadHelper;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.card.CardService;
import com.spark.cms.services.card.GetCardListKey;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.form.card.CardShowForm;
import com.spark.cms.services.form.card.CreateCardForm;
import com.spark.cms.services.form.station.StationListItem;
import com.spark.cms.services.station.GetStationListKey;
import com.spark.cms.services.station.StationService;
import com.spark.cms.services.vo.CardValueVo;
import com.spark.cms.services.vo.CardVo;
import com.spark.front.utils.CmsString;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

/**
 * @author Jideas
 * 
 */
@Controller
public class CardAction extends BaseAction {
	@Autowired
	private CardService cardService;
	@Autowired
	private StationService stationService;

	/**
	 * 上传excel
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/card/importCard")
	@ResponseBody
	public ResponseEntity<String> importCard(HttpServletRequest request,
			HttpServletResponse response) throws ServiceMessage {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFileMap = multipartHttpServletRequest
				.getFileMap();
		MultipartFile excel = multipartFileMap.get("excel");

		ExcelReadHelper reader = new ExcelReadHelper();
		try {
			reader.read(excel.getInputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (reader.getRowCount() == 0) {
			return ResponseEntityUtil.getResponseEntity(0 + "");
		}
		int count = 0;
		for (String[] array : reader.getData()) {
			if (CmsString.isEmpty(array[0]) || CmsString.isEmpty(array[1])
					|| CmsString.isEmpty(array[2])
					|| CmsString.isEmpty(array[3])
					|| CmsString.isEmpty(array[4])) {
				continue;
			}
			CardVo vo = new CardVo();
			vo.setOrdinal(DoubleUtil.getRoundStrWithOutQfw(DoubleUtil
					.strToDouble(array[0], 0), 0));
			try {
				vo.setCardno(DoubleUtil.getRoundStrWithOutQfw(DoubleUtil
						.strToDouble(array[1], 0), 0));
			} catch (Exception e) {
				vo.setCardno(array[1]);
			}
			try {
				vo.setPassword(DoubleUtil.getRoundStrWithOutQfw(DoubleUtil
						.strToDouble(array[2], 0), 0));
			} catch (Exception e) {
				vo.setPassword(array[2]);
			}
			vo.setAmount(DoubleUtil.strToDouble(array[3]));
			vo.setLastDate(DateUtil.convertStringToDate(array[4] + " 23:59:59",
					DateUtil.DATE_TIME_PATTERN2).getTime());
			try {
				this.cardService.exeImportExcel(vo);
				count++;
			} catch (Exception e) {
				break;
			}
		}
		return ResponseEntityUtil.getResponseEntity(count + "");
	}

	/**
	 * 批量获得面值卡，用于打印
	 */
	@RequestMapping("/card/getVoListByIds")
	@ResponseBody
	public ResponseEntity<MessageModel> getVoListByIds(HttpSession session,
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			Login login = new Login((UserExtForm) session
					.getAttribute(Constant.LoginAdminUser));
			if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
				return new ServiceMessage(false, "请先登录！").getMessageModel();
			}
			List<CardVo> list = this.cardService
					.getVoListPrintingByIds(ArrayUtils.arrayToList(ids));
			return new ServiceMessage(true, "", list).getMessageModel();
		} catch (Throwable e) {
			log.error("批量印发面值卡发生异常====" + e.getStackTrace());
			return new ServiceMessage(false, "获取面值卡信息出错！").getMessageModel();
		}
	}

	/**
	 * 批量印发面值卡
	 */
	@RequestMapping("/card/batchPrint")
	@ResponseBody
	public ResponseEntity<MessageModel> batchPrint(HttpSession session,
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			Login login = new Login((UserExtForm) session
					.getAttribute(Constant.LoginAdminUser));
			// TODO 这里添加打印的程序，最好下面的代码移到打印service
			try {
				this.cardService.exePrintCards(ArrayUtils.arrayToList(ids),
						login);
			} catch (ServiceMessage e) {
				return e.getMessageModel();
			}
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Throwable e) {
			log.error("批量印发面值卡发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 批量分发面值卡
	 */
	@RequestMapping("/card/batchDistribute")
	@ResponseBody
	public ResponseEntity<MessageModel> batchDistribute(HttpSession session,
			@RequestParam(value = "ids[]", required = true)
			String[] ids, @RequestParam(value = "stationId", required = true)
			String stationId,
			@RequestParam(value = "personName", required = true)
			String personName) {
		try {
			Login login = new Login((UserExtForm) session
					.getAttribute(Constant.LoginAdminUser));
			try {
				this.cardService.exeDistributeCards(
						ArrayUtils.arrayToList(ids), stationId, personName,
						login);
			} catch (ServiceMessage e) {
				e.printStackTrace();
				return e.getMessageModel();
			}
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("批量启用面值卡发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 获取站点列表
	 * 
	 * @return
	 */
	@RequestMapping("/card/getStationList")
	@ResponseBody
	public List<StationListItem> getStationList() {
		return this.stationService.getComboListItem(new GetStationListKey());
	}

	/**
	 * 新增面值卡
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/card/addCard")
	@ResponseBody
	public ResponseEntity<MessageModel> createCard(HttpSession session,
			CreateCardForm form) {
		try {
			Login login = new Login((UserExtForm) session
					.getAttribute(Constant.LoginAdminUser));
			Date lastDate = DateUtil.convertStringToDate(form.getLastDate());
			if (lastDate.getTime() < new Date().getTime()) {
				return new ServiceMessage(false, "有效期截至日期不能早于今天！")
						.getMessageModel();
			}
			cardService.createCards(form.getAmount(), form.getCount(), form
					.getSecretKey(), lastDate, login);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Throwable e) {
			log.error("新增面值卡发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 停用面值卡
	 */
	@RequestMapping("/card/cancelCard")
	@ResponseBody
	public ResponseEntity<MessageModel> cancelCard(HttpSession session,
			@RequestParam(value = "id", required = true)
			String id) {
		try {
			Login login = new Login((UserExtForm) session
					.getAttribute(Constant.LoginAdminUser));
			cardService.exeStopCard(id, login);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Throwable e) {
			log.error("停用面值卡发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 删除面值卡
	 */
	@RequestMapping("/card/deleteCard")
	@ResponseBody
	public ResponseEntity<MessageModel> deleteCard(
			@RequestParam(value = "id", required = true)
			String id) {
		try {
			cardService.delete(id);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Throwable e) {
			log.error("删除面值卡发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 查询面值卡列表（已分发）
	 * 
	 * @return
	 */
	@RequestMapping("/card/getDistributedList")
	@ResponseBody
	public DataModel<CardShowForm> getDistributedList(
			@RequestParam(value = "cardType", required = false)
			String cardType,
			@RequestParam(value = "beginDate", required = false)
			String beginDate,
			@RequestParam(value = "endDate", required = false)
			String endDate, @RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows,@RequestParam(value = "cardNO", required = false)
			String cardNO) {
		return getList(cardType, beginDate, endDate, page, rows,
				CardStatus.Distributed,cardNO);
	}

	/**
	 * 查询面值卡列表（已使用）
	 * 
	 * @return
	 */
	@RequestMapping("/card/getUsedList")
	@ResponseBody
	public DataModel<CardShowForm> getUsedList(
			@RequestParam(value = "cardType", required = false)
			String cardType,
			@RequestParam(value = "beginDate", required = false)
			String beginDate,
			@RequestParam(value = "endDate", required = false)
			String endDate, @RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows,@RequestParam(value = "cardNO", required = false)
			String cardNO) {
		return getList(cardType, beginDate, endDate, page, rows,
				CardStatus.Used,cardNO);
	}

	/**
	 * 查询面值卡列表（已停用）
	 * 
	 * @return
	 */
	@RequestMapping("/card/getStopedList")
	@ResponseBody
	public DataModel<CardShowForm> getStopedList(
			@RequestParam(value = "cardType", required = false)
			String cardType,
			@RequestParam(value = "beginDate", required = false)
			String beginDate,
			@RequestParam(value = "endDate", required = false)
			String endDate, @RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows,@RequestParam(value = "cardNO", required = false)
			String cardNO) {
		return getList(cardType, beginDate, endDate, page, rows,
				CardStatus.Canceled,cardNO);
	}

	/**
	 * 查询面值卡列表（已启用）
	 * 
	 * @return
	 */
	@RequestMapping("/card/getActivedList")
	@ResponseBody
	public DataModel<CardShowForm> getActivedList(
			@RequestParam(value = "cardType", required = false)
			String cardType,
			@RequestParam(value = "beginDate", required = false)
			String beginDate,
			@RequestParam(value = "endDate", required = false)
			String endDate, @RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows,@RequestParam(value = "cardNO", required = false)
			String cardNO) {
		return getList(cardType, beginDate, endDate, page, rows,
				CardStatus.Actived,cardNO);
	}

	/**
	 * 查询面值卡列表（已打印）
	 * 
	 * @return
	 */
	@RequestMapping("/card/getPrintedList")
	@ResponseBody
	public DataModel<CardShowForm> getPrintedList(
			@RequestParam(value = "cardType", required = false)
			String cardType,
			@RequestParam(value = "beginDate", required = false)
			String beginDate,
			@RequestParam(value = "endDate", required = false)
			String endDate, @RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows,@RequestParam(value = "cardNO", required = false)
			String cardNO) {
		return getList(cardType, beginDate, endDate, page, rows,
				CardStatus.Printed,cardNO);
	}

	/**
	 * 查询面值卡列表(未打印)
	 * 
	 * @return
	 */
	@RequestMapping("/card/getNewList")
	@ResponseBody
	public DataModel<CardShowForm> getNewList(
			@RequestParam(value = "cardType", required = false)
			String cardType,
			@RequestParam(value = "beginDate", required = false)
			String beginDate,
			@RequestParam(value = "endDate", required = false)
			String endDate, @RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows,@RequestParam(value = "cardNO", required = false)
			String cardNO) {
		return getList(cardType, beginDate, endDate, page, rows,
				CardStatus.Created,cardNO);
	}

	private DataModel<CardShowForm> getList(String cardType, String beginDate,
			String endDate, String page, String rows, CardStatus status,String cardNO) {
		GetCardListKey key = new GetCardListKey();
		key.setStatus(status);
		key.setPageSize(Integer.valueOf(rows));
		key.setOffset(Integer.valueOf(page));
		cardType = (CheckIsNull.isEmpty(cardType)) ? "0" : cardType;
		key.setAmount(Double.valueOf(cardType));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (CheckIsNull.isNotEmpty(beginDate)) {
			try {
				key.setBeginDate(sdf.parse(beginDate));
			} catch (Exception e) {
				log.error("开始时间转换发生异常====" + e.getStackTrace());
			}
		}
		if (CheckIsNull.isNotEmpty(endDate)) {
			try {
				key.setEndDate(sdf.parse(endDate));
			} catch (Exception e) {
				log.error("结束时间转换发生异常====" + e.getStackTrace());
			}
		}
		cardNO = cardNO == null ? "" : cardNO;
		key.setSearchText(cardNO);
		DataModel<CardVo> list = this.cardService.getList(key);
		List<CardShowForm> forms = new ArrayList<CardShowForm>();
		for (CardVo vo : list.getRows()) {
			forms.add(vo.toShowForm());
		}
		return new DataModel<CardShowForm>(forms, list.getTotal());
	}

	/**
	 * 批量启用面值卡
	 */
	@RequestMapping("/card/batchActive")
	@ResponseBody
	public ResponseEntity<MessageModel> batchActive(HttpSession session,
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			Login login = new Login((UserExtForm) session
					.getAttribute(Constant.LoginAdminUser));
			List<String> list = new ArrayList<String>();
			for (String id : ids) {
				list.add(id);
			}
			try {
				this.cardService.exeActiveCards(list, login);
			} catch (ServiceMessage e) {
				return e.getMessageModel();
			}
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("批量启用面值卡发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 批量启用面值卡
	 */
	@RequestMapping("/card/batchReActive")
	@ResponseBody
	public ResponseEntity<MessageModel> batchReActive(HttpSession session,
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			Login login = new Login((UserExtForm) session
					.getAttribute(Constant.LoginAdminUser));
			List<String> list = new ArrayList<String>();
			for (String id : ids) {
				list.add(id);
			}
			try {
				this.cardService.exeReActivingCards(list, login);
			} catch (ServiceMessage e) {
				return e.getMessageModel();
			}
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("批量启用面值卡发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	/**
	 * 面值 -> 新增面值
	 * 
	 * @return
	 */
	@RequestMapping("/card/addValue")
	@ResponseBody
	public ResponseEntity<MessageModel> addValue(HttpSession session,
			@RequestParam(value = "cardvalue", required = true)
			String cardvalue) {
		Login login = new Login((UserExtForm) session
				.getAttribute(Constant.LoginAdminUser));
		if(null == login){
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "请登录!"));
		}
		try{
			CardValueVo cardValueVo = new CardValueVo();
			cardValueVo.setCardvalue(Double.valueOf(cardvalue));
			cardValueVo.setCreatorid(login.getRecid());
			cardValueVo.setCreator(login.getUsername());
			this.cardService.createCardvalue(cardValueVo);
			return ResponseEntityUtil.getResponseEntity(new MessageModel(true, "新增面值成功!"));
		}catch(Exception e){
			return ResponseEntityUtil.getResponseEntity(new MessageModel(true, "新增面值失败!"));
		}
	}
	
	/**
	 * 面值 -> 修改面值
	 * 
	 * @return
	 */
	@RequestMapping("/card/updateValue")
	@ResponseBody
	public ResponseEntity<MessageModel> updateValue(@RequestParam(value = "recid", required = true)
			String recid,@RequestParam(value = "cardvalue", required = true)
			String cardvalue) {
		try{
			CardValueVo cardValueVo = new CardValueVo();
			cardValueVo.setRecid(recid);
			cardValueVo.setCardvalue(Double.valueOf(cardvalue));
			this.cardService.exeUpdateCardvalue(cardValueVo);
			return ResponseEntityUtil.getResponseEntity(new MessageModel(true, "修改面值成功!"));
		}catch(Exception e){
			return ResponseEntityUtil.getResponseEntity(new MessageModel(true, "修改面值失败!"));
		}
	}
	
	/**
	 * 面值 -> 删除面值
	 * 
	 * @return
	 */
	@RequestMapping("/card/deleteValue")
	@ResponseBody
	public ResponseEntity<MessageModel> deleteValue(@RequestParam(value = "recid", required = true)
			String recid) {
		try{
			this.cardService.deleteCardvalue(recid);
			return ResponseEntityUtil.getResponseEntity(new MessageModel(true, "删除面值成功!"));
		}catch(Exception e){
			return ResponseEntityUtil.getResponseEntity(new MessageModel(true, "删除面值失败!"));
		}
	}
	
	/**
	 * 面值 -> 获取面值卡类型
	 * 
	 * @return
	 */
	@RequestMapping("/card/getAmountList")
	@ResponseBody
	public List<CardValueVo> getAmountList() {
		List<CardValueVo> cvvList;
		try{
			cvvList = this.cardService.getCardvalues();
			return conventCardvalue(cvvList);
		}catch(Exception e){
			return new ArrayList<CardValueVo>();
		}
	}

	/**
	 * 面值 -> 获取面值卡类型
	 * 
	 * @return
	 */
	@RequestMapping("/card/getAmountSelectList")
	@ResponseBody
	public List<CardValueVo> getAmountSelectList() {
		List<CardValueVo> cvvList = new ArrayList<CardValueVo>();
		try{
			cvvList = this.cardService.getCardvalues();
			CardValueVo cvv = new CardValueVo();
			cvv.setCardvalue(0.0);
			cvvList.add(0,cvv);
			return conventCardvalue(cvvList);
		}catch(Exception e){
			return cvvList;
		}
	}
	
	private List<CardValueVo> conventCardvalue(List<CardValueVo> cvvList){
		List<CardValueVo> cvvList_temp = new ArrayList<CardValueVo>();
		for(int i = 0;cvvList != null && i < cvvList.size();i++){
			CardValueVo cvv = cvvList.get(i);
			if(cvv.getCardvalue() == 0){
				cvv.setTitle("全部");
			}else{
				cvv.setTitle(cvv.getCardvalue()+" 元");
			}
			cvvList_temp.add(cvv);
		}
		return cvvList_temp;
	}
	
}
