/**
 * 
 */
package com.spark.front.action.goods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.GoodsForm;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.vo.GoodsPromotionVo;
import com.spark.front.utils.GoodsHtmlHelper;

/**
 * @author Jideas
 * 
 */
@Controller
public class GoodsQueryAction extends BaseAction {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsPromotionService goodsPmtService;

	/**
	 * 得到最近浏览商品信息
	 * 
	 * @return
	 */
	@RequestMapping("/getLastestGoodsViews")
	@ResponseBody
	public ResponseEntity<MessageModel> getLastestGoodsViews(@RequestParam(value = "ids[]", required = false)
	String[] ids, @RequestParam(value = "thisId", required = false)
	String goodsId, HttpServletRequest request) {
		try {
			List<String> gflist = new ArrayList<String>();
			if (null == ids || ids.length == 0) {
				return new ServiceMessage(false, "没找到").getMessageModel();
			}
			for (String id : ids) {
				if (null == id || id.equals("null")) {
					continue;
				}
				if (id.equals(goodsId) || gflist.size() == 3) {
					continue;
				}
				GoodsForm form = this.goodsService.getGoodsForm(id);
				if (null == form) {
					continue;
				}
				String price = DoubleUtil.getRoundStr(form.getRealprice());
				GoodsPromotionVo pmt = this.goodsPmtService.findByGoodsId(id);
				if (null != pmt && pmt.getDisrate() > 0 && pmt.getDisrate() < 1) {
					price = (DoubleUtil.getRoundStr(DoubleUtil.mul(form.getRealprice(), pmt.getDisrate())));
				}
				gflist.add(GoodsHtmlHelper.getSmallGoodsHtml(id, form.getPicturepath2(), form.getGoodsname(), form.getGoodsspec(),
						price,form.getGoodsunit(), request.getContextPath()));
			}
			return new ServiceMessage(true, "找到了", gflist).getMessageModel();
		} catch (Throwable e) {
			log.error("得到最近浏览商品发生异常====" + e.getStackTrace());
		}
		return new ServiceMessage(false, "没找到").getMessageModel();
	}
	
}
