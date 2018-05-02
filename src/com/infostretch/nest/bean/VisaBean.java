package com.infostretch.nest.bean;
import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;
import com.qmetry.qaf.automation.util.Randomizer;
/**
 * @author Bhakti.Kulkarni
 */
public class VisaBean extends BaseDataBean {

	@Randomizer(dataset = {"1-4-2018"})
	private String initiated_from;
	@Randomizer(dataset = {"30-4-2018"})
	private String initiated_to;
	@Randomizer(dataset = {"YE"})
	private String country_id;
	@Randomizer(length = 2, type = RandomizerTypes.DIGITS_ONLY)
	private String status;
	@Randomizer(dataset = {"Yemen"})
	private String country_name;
	@Randomizer(dataset = {"2"})
	private String visa_country_id;
	@Randomizer(dataset = {"1701,1702"})
	private String visa_trvl_request_id;
	@Randomizer(dataset = {"7"})
	private String visa_trvl_type_id;
	@Randomizer(dataset = {"11,1,20"})
	private String visa_type_id;
	@Randomizer(dataset = {"business visa 1"})
	private String visa_type_name;
	@Randomizer(dataset = {"890,792"})
	private String det_emp_number;
	@Randomizer(dataset = {"1"})
	private String is_req;
	@Randomizer(dataset = {"294,296"})
	private String visa_request_id;
	@Randomizer(dataset = {"etg"})
	private String comments;
	@Randomizer(dataset = {"17"})
	private String journey_from;
	@Randomizer(dataset = {"17"})
	private String journey_to;
	@Randomizer(dataset = {"maxico"})
	private String client_address;
	@Randomizer(dataset = {"9-6-2018"})
	private String end_date;
	@Randomizer(dataset = {"0"})
	private String hotel_booking_req;
	@Randomizer(dataset = {"Mehul Kagrathra"})
	private String onsite_account_mngr;
	@Randomizer(dataset = {"Abhilasha Deolikar"})
	private String onsite_delivery_mngr;
	@Randomizer(dataset = {"businesss meeting "})
	private String purpose_of_visa_trvl;
	@Randomizer(dataset = {"2"})
	private String req_save;
	@Randomizer(dataset = {"20-5-2018"})
	private String start_date;
	@Randomizer(dataset = {"0"})
	private String trvl_cost_by_client;
	@Randomizer(dataset = {"0"})
	private String trvl_expense;
	@Randomizer(dataset = {"0"})
	private String trvl_fees;
	@Randomizer(dataset = {"0"})
	private String trvl_insurance;
	@Randomizer(dataset = {"hgkjlhkj"})
	private String visa_client_msa;
	@Randomizer(dataset = {"ytdf"})
	private String visa_client_sow;
	@Randomizer(dataset = {"dsfg"})
	private String visa_trvl_client_name;
	@Randomizer(dataset = {"66 Day(s)"})
	private String visa_trvl_duration;

	public String getJourney_from() {
		return journey_from;
	}

	public void setJourney_from(String journey_from) {
		this.journey_from = journey_from;
	}

	public String getJourney_to() {
		return journey_to;
	}

	public void setJourney_to(String journey_to) {
		this.journey_to = journey_to;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getHotel_booking_req() {
		return hotel_booking_req;
	}

	public void setHotel_booking_req(String hotel_booking_req) {
		this.hotel_booking_req = hotel_booking_req;
	}

	public String getOnsite_account_mngr() {
		return onsite_account_mngr;
	}

	public void setOnsite_account_mngr(String onsite_account_mngr) {
		this.onsite_account_mngr = onsite_account_mngr;
	}

	public String getOnsite_delivery_mngr() {
		return onsite_delivery_mngr;
	}

	public void setOnsite_delivery_mngr(String onsite_delivery_mngr) {
		this.onsite_delivery_mngr = onsite_delivery_mngr;
	}

	public String getPurpose_of_visa_trvl() {
		return purpose_of_visa_trvl;
	}

	public void setPurpose_of_visa_trvl(String purpose_of_visa_trvl) {
		this.purpose_of_visa_trvl = purpose_of_visa_trvl;
	}

	public String getReq_save() {
		return req_save;
	}

	public void setReq_save(String req_save) {
		this.req_save = req_save;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getTrvl_cost_by_client() {
		return trvl_cost_by_client;
	}

	public void setTrvl_cost_by_client(String trvl_cost_by_client) {
		this.trvl_cost_by_client = trvl_cost_by_client;
	}

	public String getTrvl_expense() {
		return trvl_expense;
	}

	public void setTrvl_expense(String trvl_expense) {
		this.trvl_expense = trvl_expense;
	}

	public String getTrvl_fees() {
		return trvl_fees;
	}

	public void setTrvl_fees(String trvl_fees) {
		this.trvl_fees = trvl_fees;
	}

	public String getTrvl_insurance() {
		return trvl_insurance;
	}

	public void setTrvl_insurance(String trvl_insurance) {
		this.trvl_insurance = trvl_insurance;
	}

	public String getVisa_client_msa() {
		return visa_client_msa;
	}

	public void setVisa_client_msa(String visa_client_msa) {
		this.visa_client_msa = visa_client_msa;
	}

	public String getVisa_client_sow() {
		return visa_client_sow;
	}

	public void setVisa_client_sow(String visa_client_sow) {
		this.visa_client_sow = visa_client_sow;
	}

	public String getVisa_trvl_client_name() {
		return visa_trvl_client_name;
	}

	public void setVisa_trvl_client_name(String visa_trvl_client_name) {
		this.visa_trvl_client_name = visa_trvl_client_name;
	}

	public String getVisa_trvl_duration() {
		return visa_trvl_duration;
	}

	public void setVisa_trvl_duration(String visa_trvl_duration) {
		this.visa_trvl_duration = visa_trvl_duration;
	}

	public String getDet_emp_number() {
		return det_emp_number;
	}

	public void setDet_emp_number(String det_emp_number) {
		this.det_emp_number = det_emp_number;
	}

	public String getIs_req() {
		return is_req;
	}

	public void setIs_req(String is_req) {
		this.is_req = is_req;
	}

	public String getVisa_request_id() {
		return visa_request_id;
	}

	public void setVisa_request_id(String visa_request_id) {
		this.visa_request_id = visa_request_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getVisa_type_name() {
		return visa_type_name;
	}

	public void setVisa_type_name(String visa_type_name) {
		this.visa_type_name = visa_type_name;
	}

	public String getVisa_type_id() {
		return visa_type_id;
	}

	public void setVisa_type_id(String visa_type_id) {
		this.visa_type_id = visa_type_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getVisa_country_id() {
		return visa_country_id;
	}

	public void setVisa_country_id(String visa_country_id) {
		this.visa_country_id = visa_country_id;
	}

	public String getVisa_trvl_request_id() {
		return visa_trvl_request_id;
	}

	public void setVisa_trvl_request_id(String visa_trvl_request_id) {
		this.visa_trvl_request_id = visa_trvl_request_id;
	}

	public String getVisa_trvl_type_id() {
		return visa_trvl_type_id;
	}

	public void setVisa_trvl_type_id(String visa_trvl_type_id) {
		this.visa_trvl_type_id = visa_trvl_type_id;
	}

	public String getInitiated_from() {
		return initiated_from;
	}

	public void setInitiated_from(String initiated_from) {
		this.initiated_from = initiated_from;
	}

	public String getInitiated_to() {
		return initiated_to;
	}

	public void setInitiated_to(String initiated_to) {
		this.initiated_to = initiated_to;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
}
