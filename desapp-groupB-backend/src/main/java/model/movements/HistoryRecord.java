package model.movements;

import model.utils.Entity;

public class HistoryRecord extends Entity{
	
	String description;
	public HistoryRecord() {
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HistoryRecord(String aDescription) {
		description = aDescription;
	}

}
