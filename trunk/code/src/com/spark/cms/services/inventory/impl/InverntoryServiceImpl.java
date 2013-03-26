package com.spark.cms.services.inventory.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.hibernate.GenericDAO;
import com.spark.cms.dao.po.PsiInventoryLogPo;
import com.spark.cms.dao.po.SaReportStoStdbookPo;
import com.spark.cms.services.inventory.InventoryService;

@Service
public class InverntoryServiceImpl implements InventoryService{
	@Autowired
	private GenericDAO genericDAO;
	
	/**
	 * ��Ʒ�����ˮ
	 * 1�������Ʒ�����ˮId
	 * 2����Ʒ���Ա�׼�ɱ���⣬���� 
	 */
	public void modifyGoodsSerialData (){
		String getmaterailsql = "SELECT stockId FROM psi_inventory_log  where inventoryType='02' GROUP BY stockId ORDER BY stockid";
		StringBuffer sb = new StringBuffer(" FROM PsiInventoryLogPo l   WHERE l.stockid = ?  AND l.inventorytype='02' ORDER BY createddate ");
		String goods = "SELECT standardcost FROM psi_base_goodsitem i WHERE i.recid = ?";
		String updategoodsAvg = "UPDATE psi_base_goodsitem SET avgcost  = ? WHERE recid = ?";
		List<byte[]> list = genericDAO.query(getmaterailsql);
		for(byte[] stockkey:list){
			 List<BigDecimal> standardCost = genericDAO.query(goods,stockkey);
			 
			 List<PsiInventoryLogPo> listpo =  genericDAO.getGenericByHql(sb.toString(), stockkey);
			 BigDecimal allStoreMoney = new BigDecimal(0);
			 double avgStoreMoney = 0;
			 double allStoreNum = 0;
			 
			 for(PsiInventoryLogPo po:listpo){
				 /**
				  * ������
				  */
				 if(isInstore(po.getLogtype())){
					 double innum = Double.valueOf(po.getInstocount()).doubleValue();
					 double inStoAccount = getDoubleScale(standardCost.get(0).multiply(new BigDecimal(innum)));
					 po.setInstoamount(inStoAccount);
					 genericDAO.saveOrUpdate(po);
				 
					 allStoreNum = allStoreNum + innum;
					 allStoreMoney = allStoreMoney.add(new BigDecimal(po.getInstoamount()));
					 avgStoreMoney = getDoubleScale(allStoreMoney.divide(new BigDecimal(allStoreNum),2,BigDecimal.ROUND_HALF_UP));
				 
				 
				}else{
					 /**
					  * ������Կ��ɱ����⣬��Ҫ�������ݿ�
					  */
					 double outnum = Double.valueOf(po.getOutstocount());
					 double outAccount = getDoubleScale((new BigDecimal(avgStoreMoney).multiply(new BigDecimal(outnum))));
					 po.setOutstoamount(outAccount);
					 genericDAO.saveOrUpdate(po);
					 }
				 }
			 /**
			  * ����ƽ�����
			  */
			 genericDAO.execteNativeBulk(updategoodsAvg, avgStoreMoney,stockkey);
		}
	}
	
	
	
	
	/**
	 * ��������̨��
	 * 1����òֿ���Ϣ
	 * 2������ÿ���ͳ����Ϣ
	 * 3�������ڳ���ĩ 
	 * 4�����¼�¼
	 */
	public void modifyStatisticalLedger(String type){
		String storesql = "SELECT storeid FROM psi_inventory_log l WHERE inventoryType='"+type+"' GROUP BY storeid";
		String getmaterailsql = "SELECT stockId FROM psi_inventory_log  where inventoryType='"+type+"'  AND storeid = ? GROUP BY stockId ORDER BY stockid";
		
		List<byte[]> list = genericDAO.query(storesql);
		StringBuffer sb = new StringBuffer("SELECT CAST(DATE_FORMAT(createdDate,'%Y%m%d') AS UNSIGNED) dateno ,storeid as storeguid,stockid as goodsguid ,SUM(instocount) instocount,SUM(instoamount) instoamount,SUM(outstocount) outstocount ,SUM(outstoamount) outstoamount");
			sb.append(" FROM psi_inventory_log l WHERE inventoryType='"+type+"' ");
			sb.append(" AND storeid = ? AND stockId = ? GROUP BY dateno,storeid,stockid ORDER BY stockid,dateno,storeid");
			
		String getSaReportStoStdbookPo = " from SaReportStoStdbookPo p where p.storeguid = ? and p.goodsguid = ? and dateno =? and inventoryType='"+type+"'";
		for(byte[] storeid :list){
			
			List<byte[]> listStockId = genericDAO.query(getmaterailsql.toString(), storeid);
			
			for(byte[] stockid:listStockId){
				List<Object[]> listpo = genericDAO.query(sb.toString(), storeid,stockid);
				double beginstorecount = 0;
				double beginstoremoney = 0;
				
				double endstorecount = 0;
				double endstoremoney = 0;
				
				for(Object[] pobjects:listpo){
					Vo po = new Vo(pobjects);
					List<SaReportStoStdbookPo> polist = genericDAO.getGenericByHql(getSaReportStoStdbookPo,po.getStoreguid(),po.getGoodsguid(),po.getDateno());
					beginstorecount = endstorecount;
					beginstoremoney = endstoremoney;
					endstorecount = beginstorecount+po.getInstocount()-po.getOutstocount();
					endstoremoney = beginstoremoney+po.getInstoamount()-po.getOutstoamount();
					SaReportStoStdbookPo bookpo = polist.get(0);
					bookpo.setBeginstorecount(beginstorecount);
					bookpo.setBeginstoremoney(beginstoremoney);
					bookpo.setInstoamount(po.getInstoamount());
					bookpo.setOutstoamount(po.getOutstoamount());
					bookpo.setEndstorecount(endstorecount);
					bookpo.setEndstoremoney(endstoremoney);
					genericDAO.saveOrUpdate(bookpo);
				}
			}
			}
	}
	
	/**
	 * ���� ���Ͽ����ˮ 
	 * 1�����Ȳֿ��У����в�������
	 * 2�����ݲ���׷һ��ѯ��ˮ���ݣ��������С�
	 * 3�����ݳ�������ͣ�����
	 * 4�����³�������ϵ�λ����������
	 */
	public void modifyMaterialSerialData (){
		String getmaterailsql = "SELECT stockId FROM psi_inventory_log  where inventoryType='01' GROUP BY stockId ORDER BY stockid";
		
		BigDecimal storetotalMoney = new BigDecimal(0);
		double storeNum = 0;
		double avgStroeMoney = 0;
		
		List<byte[]> list = genericDAO.query(getmaterailsql);
		StringBuffer sb = new StringBuffer(" FROM PsiInventoryLogPo l   WHERE l.stockid = ?  AND l.inventorytype='01' ORDER BY createddate ");
		String updateMaterial = " UPDATE psi_base_materialitem SET AVGBUYPRICE  = ? WHERE recid = ? ";
		for(byte[] stockkey:list){
			
			 List<PsiInventoryLogPo> listpo =  genericDAO.getGenericByHql(sb.toString(), stockkey);
			 storetotalMoney = new BigDecimal(0);
			 storeNum = 0;
			 avgStroeMoney = 0;
			 for(PsiInventoryLogPo po:listpo){
				 /**
				  * ������
				  */
				 if(isInstore(po.getLogtype())){
					 if(isInstoreAvg(po.getLogtype())){
						 /**
						  * ����ǲ������ϳ���
						  */
						 if(InventoryLogType.getEnum(po.getLogtype()).equals(InventoryLogType.MaterialsCheckin)){
							 
							 double innum = Double.valueOf(po.getOutstocount()).doubleValue();
							 double inStoAccount = getDoubleScale((new BigDecimal(avgStroeMoney).multiply(new BigDecimal(innum))));
							 po.setOutstoamount(inStoAccount);
							 genericDAO.saveOrUpdate(po);
							 storeNum = storeNum-innum;
							 storetotalMoney = storetotalMoney.subtract(new BigDecimal(inStoAccount));
							 
						 }else{
							 double innum = Double.valueOf(po.getInstocount()).doubleValue();
							 double inStoAccount = getDoubleScale((new BigDecimal(avgStroeMoney).multiply(new BigDecimal(innum))));
							 po.setInstoamount(inStoAccount);
							 genericDAO.saveOrUpdate(po);
							 storeNum = storeNum+innum;
							 storetotalMoney = storetotalMoney.add(new BigDecimal(inStoAccount));
						 }
					 }else{
						 /**
						  * ������Ҫ������ɱ���
						  */
						 storetotalMoney = storetotalMoney.add(new BigDecimal(po.getInstoamount()));
						 storeNum = storeNum+Double.valueOf(po.getInstocount());
						 avgStroeMoney = getDoubleScale(storetotalMoney.divide(new BigDecimal(storeNum),2,BigDecimal.ROUND_HALF_UP));
					 }
				 }else{
					 /**
					  * ������Կ��ɱ����⣬��Ҫ�������ݿ�
					  */
					 double outnum = Double.valueOf(po.getOutstocount());
					 double outAccount = getDoubleScale((new BigDecimal(avgStroeMoney).multiply(new BigDecimal(outnum))));
					 po.setOutstoamount(outAccount);
					 
					 genericDAO.saveOrUpdate(po);
					 
					 storeNum = storeNum-outnum;
					 if(storeNum==0){
						 /**
						  * ǿ������4���������������
						  */
						 storetotalMoney = new BigDecimal(0);
					 }else{
						 storetotalMoney = storetotalMoney.subtract(new BigDecimal(outAccount));
					 }
					
				 }
			 }
			 /**
			  * ��ƽ�����ɱ���¼ �� ���ϵ� ƽ���ɹ��۸���
			  */
			 genericDAO.execteNativeBulk(updateMaterial, avgStroeMoney,stockkey);
		}
	}
	private double getDoubleScale(BigDecimal bg){
		  double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		  return f1;

	}
	/**
	 * ������ƽ�����ɱ����
	 */
	private boolean isInstoreAvg(String code){
			boolean typeEnum = false;
			int codeI = Integer.parseInt(code);
			switch(codeI){
			case 24:
				typeEnum = true;
				break;
			case 20:
				typeEnum = true;
				break;
			case 9:
				typeEnum = true;
				break;
			}
			return typeEnum;
	}
	
	/**
	 * ������
	 * @param code
	 * @return
	 */
	private boolean isInstore(String code){
		boolean typeEnum = false;
		int codeI = Integer.parseInt(code);
		switch(codeI){
		case 1:
			typeEnum = true;
			break;
		case 2:
			typeEnum = false;
			break;
		case 3:
			typeEnum = false;
			break;
		case 4:
			typeEnum = true;
			break;
		case 5:
			typeEnum = false;
			break;
		case 6:
			typeEnum = false;
			break;
		case 7:
			typeEnum = false;
			break;
		case 8:
			typeEnum = true;
			break;
		case 9:
			typeEnum = false;
			break;
		case 10:
			typeEnum = false;
			break;
		case 11:
			typeEnum = true;
			break;
		case 12:
			typeEnum = false;
			break;
		case 13:
			typeEnum = true;
			break;
		case 14:
			typeEnum = false;
			break;
		case 15:
			typeEnum = false;
			break;
		case 16:
			typeEnum =true;
			break;
		case 17:
			typeEnum = true;
			break;
		case 18:
			typeEnum = false;
			break;
		case 19:
			typeEnum = false;
			break;
		case 20:
			typeEnum = true;
			break;
		case 21:
			typeEnum = false;
			break;
		case 22:
			typeEnum =true;
			break;
		case 23:
			typeEnum = false;
			break;
		case 24:
			typeEnum = true;
			break;
		}
		return typeEnum;
}
	public class Vo{
		private byte[] storeguid;
		private byte[] goodsguid;
		private Double instocount;
		private Double instoamount;
		private Double outstocount;
		private Double outstoamount;
		private Integer dateno;
		public byte[] getStoreguid() {
			return storeguid;
		}
		public void setStoreguid(byte[] storeguid) {
			this.storeguid = storeguid;
		}
		public byte[] getGoodsguid() {
			return goodsguid;
		}
		public void setGoodsguid(byte[] goodsguid) {
			this.goodsguid = goodsguid;
		}
		public Double getInstocount() {
			return instocount;
		}
		public void setInstocount(Double instocount) {
			this.instocount = instocount;
		}
		public Double getInstoamount() {
			return instoamount;
		}
		public void setInstoamount(Double instoamount) {
			this.instoamount = instoamount;
		}
		public Double getOutstocount() {
			return outstocount;
		}
		public void setOutstocount(Double outstocount) {
			this.outstocount = outstocount;
		}
		public Double getOutstoamount() {
			return outstoamount;
		}
		public void setOutstoamount(Double outstoamount) {
			this.outstoamount = outstoamount;
		}
		public Integer getDateno() {
			return dateno;
		}
		public void setDateno(Integer dateno) {
			this.dateno = dateno;
		}
		Vo(Object[] obj){
			dateno = (Integer)obj[0];
			storeguid = (byte[])obj[1];
			goodsguid = (byte[])obj[2];
			instocount = ((BigDecimal)obj[3]).doubleValue();
			instoamount = ((BigDecimal)obj[4]).doubleValue();
			outstocount = ((BigDecimal)obj[5]).doubleValue();
			outstoamount =((BigDecimal) obj[6]).doubleValue();
			
		}
	}
	

}
