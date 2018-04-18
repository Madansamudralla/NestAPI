package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;


import com.infostretch.nest.providers.LeaveEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;


public class Leave {
	JSONObject jsonObject;
	
	public static String leavePeriod;
	@QAFTestStep(description = "user should get the leave reasons")
	public void userShouldGetTheLeaveReasons() {

		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_REASONS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get leave types by country")
	public void userShouldGetLeaveTypesByCountry() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_TYPES_BY_COUNTRY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get current leave period")
	public void userShouldGetCurrentLeavePeriod() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_CURRENT_LEAVE_PERIOD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get leave balances")
	public void userShouldGetLeaveBalances() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_BALANCES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	
	@QAFTestStep(description = "user should get all holiday datelist")
	public void userShouldGetAllHolidayDatelist() {
		jsonObject=new JSONObject();
		jsonObject.put("start_date","18-4-2018");
		jsonObject.put("end_date","18-4-2018");
		jsonObject.put("token","05b4b6bad9d544f502c8aab3a47e604c");
		ClientUtils.getWebResource(LeaveEndPoints.GET_ALL_HOLIDAY_DATELIST)
		.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
	}
	
}




