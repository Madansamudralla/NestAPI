package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.LeaveEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
import com.sun.jersey.api.client.ClientResponse;

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
		
		
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonArray results =
				responseBody.get("response").getAsJsonObject().getAsJsonArray("results");
		int resultSize =responseBody.get("response").getAsJsonObject().getAsJsonArray("results").size();
		System.out.println(resultSize);
		for(int i=0;i<=resultSize-1;i++) {
		
		Validator.verifyThat((results.get(i).getAsJsonObject()).get("leaveid").toString(),
				Matchers.notNullValue());
		System.out.println(i+"verified id");
		Validator.verifyThat((results.get(i).getAsJsonObject()).get("reason").toString(),
				Matchers.notNullValue());
		System.out.println(i+"verified reason");
		}
	}

	@QAFTestStep(description = "user should get leave types by country")
	public void userShouldGetLeaveTypesByCountry() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_TYPES_BY_COUNTRY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	
		
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonArray results =
				responseBody.get("response").getAsJsonObject().getAsJsonArray("results");
		int resultSize =responseBody.get("response").getAsJsonObject().getAsJsonArray("results").size();
		System.out.println(resultSize);
		for(int i=1;i<=resultSize-1;i++) {
		//Validator.verifyThat(results.size(), Matchers.equalTo(3));
		Validator.verifyThat((results.get(i).getAsJsonObject()).get("countryFlag").toString(),
				Matchers.containsString("IN"));
		//System.out.println(i+"verified country flag");
		Validator.verifyThat((results.get(i).getAsJsonObject()).get("leave_type_id").toString(),
				Matchers.containsString("LTY"));
		//System.out.println(i+"verified id");
		Validator.verifyThat((results.get(i).getAsJsonObject()).get("leave_type_name").toString(),
				Matchers.notNullValue());
		//System.out.println(i+"verified name");

		}

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
		jsonObject = new JSONObject();
		jsonObject.put("start_date", "18-4-2018");
		jsonObject.put("end_date", "18-4-2019");
		jsonObject.put("token",TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.GET_ALL_HOLIDAY_DATELIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get all leave status")
	public void userShouldGetAllLeaveStatus() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_ALL_LEAVE_STATUS)
				.get(ClientResponse.class);
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get his leave list")
	public void userShouldGetHisLeaveList() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_USER_LEAVE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get all his leave list")
	public void userShouldGetAllHisLeaveList() {
		jsonObject = new JSONObject();
		jsonObject.put("date_type", "applieddate");
		jsonObject.put("from_date", "1-4-2018");
		jsonObject.put("leavetype", "");
		jsonObject.put("to_date", "31-3-2019");
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.GET_USER_ALL_LEAVE_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

}
