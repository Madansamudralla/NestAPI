package com.infostretch.nest.steps;
import javax.ws.rs.core.MediaType;
import org.hamcrest.Matchers;
import org.json.JSONArray;
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
	int integer;
	public static String leavePeriod;

	@QAFTestStep(description = "user should get the leave reasons")
	public void userShouldGetTheLeaveReasons() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_REASONS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (integer = 0; integer <= results.size() - 1; integer++) {
			Validator.verifyThat(
					(results.get(integer).getAsJsonObject()).get("leaveid").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(integer).getAsJsonObject()).get("reason").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get leave types by country")
	public void userShouldGetLeaveTypesByCountry() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_TYPES_BY_COUNTRY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (integer = 1; integer <= results.size() - 1; integer++) {
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("countryFlag").toString(), Matchers.containsString("IN"));
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("leave_type_id").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("leave_type_name").toString(), Matchers.notNullValue());
		}

	}

	@QAFTestStep(description = "user should get current leave period")
	public void userShouldGetCurrentLeavePeriod() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_CURRENT_LEAVE_PERIOD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "leave_period_id");
		CommonUtils.validateParameterInJsonObject(result, "leave_period_start_date");
		CommonUtils.validateParameterInJsonObject(result, "leave_period_end_date");
	}

	@QAFTestStep(description = "user should get leave balances")
	public void userShouldGetLeaveBalances() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_BALANCES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1reaults = result.get("regular").getAsJsonArray();
		JsonArray param2results = result.get("special").getAsJsonArray();

		for (integer = 0; integer <= param1reaults.size() - 1; integer++) {
			Validator.verifyThat((param1reaults.get(integer).getAsJsonObject())
					.get("leaveTypeId").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((param1reaults.get(integer).getAsJsonObject())
					.get("leaveType").toString(), Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(integer).getAsJsonObject())
					.get("number").toString(), Matchers.notNullValue());
		}

		for (integer = 0; integer <= param2results.size() - 1; integer++) {
			Validator.verifyThat((param2results.get(integer).getAsJsonObject())
					.get("leaveTypeId").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((param2results.get(integer).getAsJsonObject())
					.get("leaveType").toString(), Matchers.notNullValue());
			Validator.verifyThat((param2results.get(integer).getAsJsonObject())
					.get("number").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get all holiday datelist")
	public void userShouldGetAllHolidayDatelist() {
		jsonObject = new JSONObject();
		jsonObject.put("start_date", "19-4-2018");
		jsonObject.put("end_date", "19-4-2019");
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.GET_ALL_HOLIDAY_DATELIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);

		if (result.toString().contains("WO") && result.toString().contains("PH")
				&& result.toString().contains("FH")) {
			Reporter.log("Verified");
		}
	}

	@QAFTestStep(description = "user should get all leave status")
	public void userShouldGetAllLeaveStatus() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_ALL_LEAVE_STATUS)
				.get(ClientResponse.class);
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "-1");
		CommonUtils.validateParameterInJsonObject(result, "0");
		CommonUtils.validateParameterInJsonObject(result, "1");
	}

	@QAFTestStep(description = "user should get his leave list")
	public void userShouldGetHisLeaveList() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_USER_LEAVE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();

		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		if (responseBody.toString().contains("2210")) {
			JsonObject results = CommonUtils.getValidateResultObject(response);
			results = responseBody.get("response").getAsJsonObject().get("results")
					.getAsJsonObject().get("2210").getAsJsonObject();
			CommonUtils.validateParameterInJsonObject(results, "emp_initial");
			CommonUtils.validateParameterInJsonObject(results, "date");
			CommonUtils.validateParameterInJsonObject(results, "leave_request_id");
			CommonUtils.validateParameterInJsonObject(results, "leave_status");
		} else {
			JsonObject results = CommonUtils.getValidateResultObject(response);
			Reporter.log(results.toString());
		}

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
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (integer = 1; integer <= results.size() - 1; integer++) {
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("leave_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("leave_status").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("date_applied").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get location holiday list")
	public void userShouldGetLocationHolidayList() {
		jsonObject = new JSONObject();
		
		jsonObject.put("start_date", "19-4-2018");
		jsonObject.put("end_date", "19-4-2019");
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.GET_LOCATION_HOLIDAY_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (integer = 1; integer <= results.size() - 1; integer++) {
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("holiday_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("date").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("location").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get list of all leave types")
	public void userShouldGetListOfAllLeaveTypes() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LIST_OF_LEAVE_TYPES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (integer = 0; integer <= results.size() - 1; integer++) {
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("countryFlag").toString(), Matchers.containsString("IN"));
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("leave_type_id").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("leave_type_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get teams leave balance lists")
	public void userShouldGetTeamsLeaveBalanceLists() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_TEAMS_LEAVE_BALANCE_LISTS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (integer = 1; integer <= results.size() - 1; integer++) {
			Validator.verifyThat(
					(results.get(integer).getAsJsonObject()).get("user_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(integer).getAsJsonObject()).get("user_name").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject()).get("result")
					.getAsJsonArray().toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user requests for a leave")
	public void userRequestsForALeave() {
		jsonObject = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("leaveType", "LTY001");
		jsonObject3.put("leaveFromDate", "11-7-2018");
		jsonObject3.put("leaveToDate", "11-7-2018");
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(jsonObject3);
		jsonObject2.put("leave", jsonArray);
		jsonObject.put("leaveDetails", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.REQUEST_LEAVE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		if (result.toString().contains("leave_request_id")) {
			CommonUtils.validateParameterInJsonObject(result, "leave_request_id");
			
		}else if(result.toString().contains("You have already applied/taken for selected days.")) {
			Reporter.log("Verified");
		}
	}
}