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
	@Randomizer(dataset = {"25"})
	private String visa_country_id;
	@Randomizer(dataset = {"1701,1702"})
	private String visa_trvl_request_id;
	@Randomizer(dataset = {"7"})
	private String visa_trvl_type_id;
	@Randomizer(dataset = {"11,1"})
	private String visa_type_id;
	@Randomizer(dataset = {"business visa 1"})
	private String visa_type_name;
	


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
