package com.spark.cms.common.generate;

import java.util.List;

import com.spark.cms.services.form.CategoryPropertiesForm;
import com.spark.cms.services.form.CategoryPvaluesForm;
import com.spark.cms.services.form.GoodsCategoryForm;
import com.spark.cms.services.vo.GoodsCategoryVo;

public class SearchGen {
	
	
	public static String genCategorySearch(List<GoodsCategoryVo> items){
		String str = genSearchHead("���");
		str += "<a color=\"#076A00\" style=\"cursor: hand\" class=\"current\"  onclick=\"serachCategory('"+null+"')\">ȫ��</a>";
		
		for(GoodsCategoryVo item:items){
			str += genSerachCategoryItem(item.getRecid(), item.getCategoryname());
		}
		
		str += genSearchEnd();
		return str;
	}
	
	public static String genDefaultPriceSearch(){
		String str = genSearchHead("�۸�");
		
		str += "<a color=\"#076A00\" style=\"cursor: hand\" class=\"current\" onclick=\"serachPrice('0','10000')\">ȫ��</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('0','5')\">0-5Ԫ</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('5','10')\">5-10Ԫ</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('10','20')\">10-20Ԫ</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('20','50')\">20-50Ԫ</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('50','100')\">50-100Ԫ</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('100','200')\">100-200Ԫ</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('200','500')\">200-500Ԫ</a>";
		str += "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachPrice('500','10000')\">500Ԫ����</a>";
		
		str += genSearchEnd();
		return str;
	}
	
	private static String genSerachCategoryItem(String id,String name){
		return "<a color=\"#076A00\" style=\"cursor: hand\"  onclick=\"serachCategory('"+id+"')\">"+name+"</a>";
	}
	
	private static String genSearchHead(String title){
		return "<div class=\"categoryPagecontantRight1_contant\">"+title;
	}

	private static String genSearchEnd(){
		return "</div>";
	}
	
	public static String genSearchProp(){
		String str = "";
		
		return str;
	}
	
	private static String genSerachPropItem(String cid,String rid,String pid,String name){
		return "<a color=\"#076A00\" style=\"cursor: hand\" onclick=\"serachProp('"+cid+"','"+rid+"','"+pid+"','"+name+"')\">"+name+"</a>";
	}
	
	public static  String getGoodsCategoryPropHtml(GoodsCategoryForm categoryInfo){

		String str = "";
		CategoryPropertiesForm forms[] = categoryInfo.getPropertiesForm();
		for(CategoryPropertiesForm form:forms){
			if(form.getValues()!=null&&form.getValues().length>0){
				CategoryPvaluesForm vForms[] = form.getValues();
				str += genSearchHead(form.getPropertiyname());
				for(CategoryPvaluesForm vForm:vForms){
					
					str += genSerachPropItem(vForm.getCategoryid(),vForm.getRecid(),vForm.getPropertyid(),vForm.getPvalue());
				}
				str += genSearchEnd();
			}
		}
		return str;
	}
	


}
