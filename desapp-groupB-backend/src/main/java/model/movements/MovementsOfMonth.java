package model.movements;

import java.util.ArrayList;
import java.util.List;

import model.email.MailBody;
import model.utils.Entity;

public class MovementsOfMonth extends Entity implements MailBody {

	private List<HistoryRecord> history;

	public MovementsOfMonth() {
		this.history = new ArrayList<HistoryRecord>();
	}

	public void addToHistory(String historyFragment) {
		this.history.add(new HistoryRecord(historyFragment + "\n"));
	}

	public void cleanHistory() {
		this.history = new ArrayList<HistoryRecord>();
	}

	/** Setters and Getters **/

	public String getAllHistory() {
		;
		return this.history.stream().map((HistoryRecord record) -> record.getDescription()).reduce("",
				(String result, String description) -> result + description);
	}

	public List<HistoryRecord> getHistory() {
		return this.history;

	}

	public void setHistory(List<HistoryRecord> records) {
		this.history = records;
	}

}
