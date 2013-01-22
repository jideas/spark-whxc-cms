/**
 * 
 */
package com.spark.cms.services.station;

/**
 * @author Jideas
 * 
 */
public class GetStationListKey {

	private String addressCode;

	public GetStationListKey() {
	}

	public GetStationListKey(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getAddressCode() {
		return addressCode;
	}
}
