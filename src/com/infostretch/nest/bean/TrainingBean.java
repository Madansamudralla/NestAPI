package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;

public class TrainingBean extends BaseDataBean{
	@Randomizer(dataset = {"9"})
	private String trn_course_id;

	public String getTrn_course_id() {
		return trn_course_id;
	}

	public void setTrn_course_id(String trn_course_id) {
		this.trn_course_id = trn_course_id;
	}

}
