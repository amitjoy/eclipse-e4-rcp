package com.amitinside.e4.rcp.todo.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import com.google.common.base.Objects;

public class Todo {

	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public static final String FIELD_ID = "id";
	public static final String FIELD_SUMMARY = "summary";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_DONE = "done";
	public static final String FIELD_DUEDATE = "dueDate";
	public static final String FIELD_NOTE = "note";

	public long id;
	private String summary;
	private String description;
	private boolean done;
	private Date dueDate;
	private String note;

	public Todo() {
	}

	public Todo(long i) {
		id = i;
	}

	public Todo(long i, String summary, String description, boolean b,
			Date date, String note) {
		this.id = i;
		this.summary = summary;
		this.description = description;
		this.done = b;
		this.dueDate = date;
		this.note = note;
	}

	public long getId() {
		return id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		changes.firePropertyChange(FIELD_SUMMARY, this.summary,
				this.summary = summary);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		changes.firePropertyChange(FIELD_NOTE, this.note, this.note = note);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		changes.firePropertyChange(FIELD_DESCRIPTION, this.description,
				this.description = description);
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean isDone) {
		changes.firePropertyChange(FIELD_DONE, this.done, this.done = isDone);
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		changes.firePropertyChange(FIELD_DUEDATE, this.dueDate,
				this.dueDate = dueDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(getClass()).add(FIELD_ID, id)
				.add(FIELD_SUMMARY, summary).add(FIELD_NOTE, note).toString();
	}

	public Todo copy() {
		return new Todo(id, summary, description, done, dueDate, note);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}
}