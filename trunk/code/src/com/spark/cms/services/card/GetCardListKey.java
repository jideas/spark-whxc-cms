/**
 * 
 */
package com.spark.cms.services.card;

import java.util.Date;

import com.spark.base.common.key.LimitKey;
import com.spark.cms.base.constant.card.CardStatus;

/**
 * @author Jideas
 * 
 */
public class GetCardListKey extends LimitKey {

	private CardStatus status;

	private Date beginDate, endDate;
	
	/**
	 * ÃæÖµ
	 */
	private double amount;
	
	private SortField sortField = SortField.CreateDate;

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public SortField getSortField() {
		return sortField;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public enum SortField {
		CreateDate("createdate"), ;

		private String field;

		private SortField(String field) {
			this.field = field;
		}

		public String getField() {
			return this.field;
		}

		public void setField(String field) {
			this.field = field;
		}
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
