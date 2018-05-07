package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;

public class TravelDataBean extends BaseDataBean{

	@Randomizer(dataset = {"790"})
	private String emp_number;
	@Randomizer(dataset = {"1"})
	private String book_membership;
	@Randomizer(dataset = {"23,25"})
	private String trvl_travel_category_id ;
	@Randomizer(dataset = {"sdasftergfdgsd", "fmMfGyjNdKwCmXPPzVMb", "Business"})
	private String title;
	@Randomizer(dataset = {"0","1","2"})
	private String status;
	@Randomizer(dataset = {"eqw","fmMfGyjNdKwCmXPPzVMb"})
	private String description;
	@Randomizer(dataset = {"6","1"})
	private String trvl_mode_of_travel_id;
	@Randomizer(dataset = {"108"})
	private String job_title_id;
	@Randomizer(dataset = {"2"})
	private String level;
	@Randomizer(dataset = {"IlntCDAzJ Test"})
	private String agent_name;
	@Randomizer(dataset = {"IlntCDAzJsa"})
	private String contact_person;
	@Randomizer(dataset = {"0123654789"})
	private String contact_number;
	@Randomizer(dataset = {"14"})
	private String trvl_agent_id;
	@Randomizer(dataset = {"2262"})
	private String trvl_travel_request_id;
	@Randomizer(dataset = {"rejketc"})
	private String comment;
	@Randomizer(dataset = {"0"})
	private String billable;
	@Randomizer(dataset = {"2221"})
	private String travel_id;
	public String getTravel_id() {
		return travel_id;
	}
	public void setTravel_id(String travel_id) {
		this.travel_id = travel_id;
	}
	public String getTrvl_booking_id() {
		return trvl_booking_id;
	}
	public void setTrvl_booking_id(String trvl_booking_id) {
		this.trvl_booking_id = trvl_booking_id;
	}
	@Randomizer(dataset = {"22"})
	private String trvl_booking_id;
	
	public String getEmp_number() {
		return emp_number;
	}
	public void setEmp_number(String emp_number) {
		this.emp_number = emp_number;
	}
	public String getBook_membership() {
		return book_membership;
	}
	public void setBook_membership(String book_membership) {
		this.book_membership = book_membership;
	}
	public String getTrvl_travel_category_id() {
		return trvl_travel_category_id;
	}
	public void setTrvl_travel_category_id(String trvl_travel_category_id) {
		this.trvl_travel_category_id = trvl_travel_category_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrvl_mode_of_travel_id() {
		return trvl_mode_of_travel_id;
	}
	public void setTrvl_mode_of_travel_id(String trvl_mode_of_travel_id) {
		this.trvl_mode_of_travel_id = trvl_mode_of_travel_id;
	}
	public String getJob_title_id() {
		return job_title_id;
	}
	public void setJob_title_id(String job_title_id) {
		this.job_title_id = job_title_id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getTrvl_agent_id() {
		return trvl_agent_id;
	}
	public void setTrvl_agent_id(String trvl_agent_id) {
		this.trvl_agent_id = trvl_agent_id;
	}
	public String getTrvl_travel_request_id() {
		return trvl_travel_request_id;
	}
	public void setTrvl_travel_request_id(String trvl_travel_request_id) {
		this.trvl_travel_request_id = trvl_travel_request_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getBillable() {
		return billable;
	}
	public void setBillable(String billable) {
		this.billable = billable;
	}
	
	
}
