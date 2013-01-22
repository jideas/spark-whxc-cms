/**
 * 
 */
package  com.spark.cms.services.form.station;

import java.io.Serializable;

/**
 * @author Jideas
 * 
 */
@SuppressWarnings("serial")
public class StationListItem implements Serializable {
	public StationListItem(String recid, String stationName) {
		this.recid = recid;
		this.stationName = stationName;
	}

	private String recid;
	private String stationName;

	public String getRecid() {
		return recid;
	}

	public String getStationName() {
		return stationName;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}
