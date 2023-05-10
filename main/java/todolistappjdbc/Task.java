package todolistappjdbc;

import java.util.Date;

public class Task {
	
	private String task;
	private String task_details;
	private Date start_date;
	private Date start_time;
	private Date end_date;
	private Date end_time;
	
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(String task, String task_details, Date start_date, Date start_time, Date end_date, Date end_time) {
		super();
		this.task = task;
		this.task_details = task_details;
		this.start_date = start_date;
		this.start_time = start_time;
		this.end_date = end_date;
		this.end_time = end_time;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getTask_details() {
		return task_details;
	}
	public void setTask_details(String task_details) {
		this.task_details = task_details;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	
}
