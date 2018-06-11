package model.movements;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import model.email.MailBody;
import model.utils.Entity;

public class MovementsOfMonth extends Entity implements MailBody {

	private List<HistoryRecord> history;

	public MovementsOfMonth() {
		this.history = new ArrayList<HistoryRecord>();
	}

	public void addToHistory(String historyFragment) {
		this.history.add(new HistoryRecord(historyFragment + "\n", DateTime.now()));
	}

	public void cleanHistory() {
		this.history = new ArrayList<HistoryRecord>();
	}

	/** Setters and Getters **/
	@JsonIgnore
	public String allHistory() {

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
