package model.movements;

import org.joda.time.DateTime;

import model.utils.Entity;

public class HistoryRecord extends Entity{
	
	private String description;
	private DateTime date;
	public HistoryRecord() {
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public HistoryRecord(String aDescription, DateTime aDate) {
		description = aDescription;
		date = aDate;
	}

}
