package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;

public class TrainingBean extends BaseDataBean {
	@Randomizer(dataset = {"9"})
	private String trn_course_id;

	public String getTrn_course_id() {
		return trn_course_id;
	}

	public void setTrn_course_id(String trn_course_id) {
		this.trn_course_id = trn_course_id;
	}

	@Randomizer(dataset = {"2018-03-31T18:30:00.000Z"})
	private String date_from;
	@Randomizer(dataset = {"2018-04-29T18:30:00.000Z"})
	private String date_to;
	@Randomizer(dataset = {"890"})
	private String emp_number;
	@Randomizer(dataset = {"registration"})
	private String type;
	@Randomizer(dataset = {"2018-4-1"})
	private String fromDate;
	@Randomizer(dataset = {"2018-4-30"})
	private String toDate;

	@Randomizer(dataset = {"12"})
	private String trn_venue_id;
	@Randomizer(dataset = {"erewrerw"})
	private String title;
	@Randomizer(dataset = {"wdewfef"})
	private String description;
	@Randomizer(dataset = {"1"})
	private String status;
	@Randomizer(dataset = {"7e074975ebca9b4ff91d2a074c3d5102"})
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTrn_venue_id() {
		return trn_venue_id;
	}
	public void setTrn_venue_id(String trn_venue_id) {
		this.trn_venue_id = trn_venue_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getEmp_number() {
		return emp_number;
	}
	public void setEmp_number(String emp_number) {
		this.emp_number = emp_number;
	}
	public String getDate_from() {
		return date_from;
	}
	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}
	public String getDate_to() {
		return date_to;
	}
	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}
}
