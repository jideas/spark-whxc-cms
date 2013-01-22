/**
 * 
 */
package com.spark.cms.services.form.card;

/**
 * @author Jideas
 * Éú³É¿¨Æ¬
 */
public class CreateCardForm {
	private double amount;
	private int count;
	private String lastDate;
	private String secretKey;
	private String reSecretKey;

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public double getAmount() {
		return amount;
	}

	public int getCount() {
		return count;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getReSecretKey() {
		return reSecretKey;
	}

	public void setReSecretKey(String reSecretKey) {
		this.reSecretKey = reSecretKey;
	}

}
