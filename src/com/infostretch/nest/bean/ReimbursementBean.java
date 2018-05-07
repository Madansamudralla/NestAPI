package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;

public class ReimbursementBean extends BaseDataBean {
	
	@Randomizer(dataset = {"8396"})
	private String reimbursement_id;
	@Randomizer(dataset = {"netsuite_category_title"})
	private String sort;
	@Randomizer(dataset = {"desc"})
	private String order;
	@Randomizer(dataset = {"12"})
	private String netsuite_category_id;
	@Randomizer(dataset = {"0,1"})
	private String status;
	@Randomizer(dataset = {"xbucDs"})
	private String netsuite_category_title;
	@Randomizer(dataset = {"476720"})
	private String internal_id;
	@Randomizer(dataset = {"2018-04-10 13:40:15"})
	private String created_date;
	@Randomizer(dataset = {"2018-04-10 13:40:15"})
	private String updated_date;
	@Randomizer(dataset = {"310"})
	private String id;
	@Randomizer(dataset = {"1157"})
	private String emp_number;
	@Randomizer(dataset = {"12122"})
	private String employee_id;
	@Randomizer(dataset = {"4190"})
	private String trvl_travel_request_id;
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String category_title;
	
	public String getEmp_number() {
		return emp_number;
	}
	public void setEmp_number(String emp_number) {
		this.emp_number = emp_number;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getTrvl_travel_request_id() {
		return trvl_travel_request_id;
	}
	public void setTrvl_travel_request_id(String trvl_travel_request_id) {
		this.trvl_travel_request_id = trvl_travel_request_id;
	}
	public String getCategory_title() {
		return category_title;
	}
	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	public String getActual() {
		return actual;
	}
	public void setActual(String actual) {
		this.actual = actual;
	}
	public String getMax_elligible_amount() {
		return max_elligible_amount;
	}
	public void setMax_elligible_amount(String max_elligible_amount) {
		this.max_elligible_amount = max_elligible_amount;
	}
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String category_description;
	@Randomizer(dataset = {"yes"})
	private String actual;
	@Randomizer(length = 6, type = RandomizerTypes.DIGITS_ONLY)
	private String max_elligible_amount;
	
	
	public String getReimbursement_id() {
		return reimbursement_id;
	}
	public void setReimbursement_id(String reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getNetsuite_category_id() {
		return netsuite_category_id;
	}
	public void setNetsuite_category_id(String netsuite_category_id) {
		this.netsuite_category_id = netsuite_category_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNetsuite_category_title() {
		return netsuite_category_title;
	}
	public void setNetsuite_category_title(String netsuite_category_title) {
		this.netsuite_category_title = netsuite_category_title;
	}
	public String getInternal_id() {
		return internal_id;
	}
	public void setInternal_id(String internal_id) {
		this.internal_id = internal_id;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}