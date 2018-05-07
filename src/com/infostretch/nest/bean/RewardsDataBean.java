package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;

public class RewardsDataBean extends BaseDataBean{
	
	@Randomizer(dataset = {"1"})
	private String category_id;

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	

}
