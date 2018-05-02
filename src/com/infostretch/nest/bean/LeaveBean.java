package com.infostretch.nest.bean;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;
import com.qmetry.qaf.automation.util.RandomStringGenerator.RandomizerTypes;

public class LeaveBean extends BaseDataBean {
	
	@Randomizer(dataset = {"19-4-2018","1-4-2018"})
	private String start_date;
	
	@Randomizer(dataset = {"LTY001","LTY002","LTY003"})
	private String regularLeaveType;
	
	@Randomizer(dataset = {"LTY004"})
	private String floatingLeaveType;
	
	@Randomizer(dataset = {"LTY005","LTY006","LTY007"})
	private String specialLeaveType;
	
	@Randomizer(dataset = {"19-4-2019","31-3-2019"})
	private String end_date;
	
	@Randomizer(dataset = {"4-9-2018","3-9-2018"})
	private String leave_date;
	
	@Randomizer(dataset = {"9b3d1872fd6e59545402ea1040cd3dda"})
	private String externalToken;
	
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String leaveName;
	
	@Randomizer(dataset = {"Pune","Ahemdabad"})
	private String locationName;
	
	@Randomizer(dataset = {"1-6-2018","2-6-2018","27-11-2018","15-12-2018","17-8-2018","17-4-2019"})
	private String holidayDate;
	
	@Randomizer(type = RandomizerTypes.DIGITS_ONLY, length = 3)
	private String holidayId;
	
	@Randomizer(type = RandomizerTypes.LETTERS_ONLY, length = 6)
	private String comment;
	
	@Randomizer(type = RandomizerTypes.DIGITS_ONLY, length = 4)
	private String leaveRequestId;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(String leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	public String getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getExternalToken() {
		return externalToken;
	}

	public void setExternalToken(String externalToken) {
		this.externalToken = externalToken;
	}

	public String getRegularLeaveType() {
		return regularLeaveType;
	}

	public void setRegularLeaveType(String regularLeaveType) {
		this.regularLeaveType = regularLeaveType;
	}

	public String getFloatingLeaveType() {
		return floatingLeaveType;
	}

	public void setFloatingLeaveType(String floatingLeaveType) {
		this.floatingLeaveType = floatingLeaveType;
	}

	public String getSpecialLeaveType() {
		return specialLeaveType;
	}

	public void setSpecialLeaveType(String specialLeaveType) {
		this.specialLeaveType = specialLeaveType;
	}

	
	public String getLeave_date() {
		return leave_date;
	}

	public void setLeave_date(String leave_date) {
		this.leave_date = leave_date;
	}

	public String getStartDate() {
		return start_date;
	}

	public void setStartDate(String start_date) {
		this.start_date = start_date;
	}

	public String getEndDate() {
		return end_date;
	}

	public void setEndDate(String start_date) {
		this.end_date = start_date;
	}

}
