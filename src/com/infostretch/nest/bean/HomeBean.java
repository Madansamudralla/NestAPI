package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.util.Randomizer;
import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;

public class HomeBean extends BaseDataBean{
	
	@Randomizer(dataset = {"18-4-2018"})
	private String start_date;
	
	@Randomizer(dataset = {"29-4-2018"})
	private String end_date;
	
	@Randomizer(length = 2, type = RandomizerTypes.DIGITS_ONLY)
	private String eventlist;
	
	@Randomizer(dataset = {"asc"})
	private String order;
	
	@Randomizer(dataset = {"start_date"})
	private String sort;
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getEventlist() {
		return eventlist;
	}
	public void setEventlist(String eventlist) {
		this.eventlist = eventlist;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	

}
