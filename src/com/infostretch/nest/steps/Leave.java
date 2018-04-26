package com.infostretch.nest.steps;
import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.LeaveBean;
import com.infostretch.nest.providers.LeaveEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
import com.sun.jersey.api.client.ClientResponse;

public class Leave {
	JSONObject jsonObject, jsonObject2, jsonObject3;
	JSONArray jsonArray;
	JsonArray results, newResult;
	Response response;
	int index, index2;
	JsonObject result, responseBody, NwwResults;
	public static String leavePeriod;
	LeaveBean leaveBean;

	@QAFTestStep(description = "user should get the leave reasons")
	public void userShouldGetTheLeaveReasons() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_REASONS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("leaveid").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("reason").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get leave types by country")
	public void userShouldGetLeaveTypesByCountry() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_TYPES_BY_COUNTRY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 1; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("countryFlag").toString(),
					Matchers.containsString("IN"));
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("leave_type_id").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("leave_type_name").toString(), Matchers.notNullValue());
		}

	}

	@QAFTestStep(description = "user should get current leave period")
	public void userShouldGetCurrentLeavePeriod() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_CURRENT_LEAVE_PERIOD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "leave_period_id");
		CommonUtils.validateParameterInJsonObject(result, "leave_period_start_date");
		CommonUtils.validateParameterInJsonObject(result, "leave_period_end_date");
	}

	@QAFTestStep(description = "user should get leave balances")
	public void userShouldGetLeaveBalances() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LEAVE_BALANCES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("regular").getAsJsonArray();

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("leaveTypeId").toString(),
					Matchers.containsString("LTY"));
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("leaveType").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("number").toString(),
					Matchers.notNullValue());
		}
		results = result.get("special").getAsJsonArray();
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("leaveTypeId").toString(),
					Matchers.containsString("LTY"));
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("leaveType").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("number").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get all holiday datelist")
	public void userShouldGetAllHolidayDatelist() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("start_date", leaveBean.getStartDate());
		jsonObject.put("end_date", leaveBean.getEndDate());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.GET_ALL_HOLIDAY_DATELIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);

		if (result.toString().contains("WO") && result.toString().contains("PH")
				&& result.toString().contains("FH")) {
			Reporter.log("Verified");
		}
	}

	@QAFTestStep(description = "user should get all leave status")
	public void userShouldGetAllLeaveStatus() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_ALL_LEAVE_STATUS)
				.get(ClientResponse.class);
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "-1");
		CommonUtils.validateParameterInJsonObject(result, "0");
		CommonUtils.validateParameterInJsonObject(result, "1");
	}

	@QAFTestStep(description = "user should get his leave list")
	public void userShouldGetHisLeaveList() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_USER_LEAVE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		if (responseBody.toString().contains("2210")) {
			result = CommonUtils.getValidateResultObject(response);
			result = responseBody.get("response").getAsJsonObject().get("results")
					.getAsJsonObject().get("2210").getAsJsonObject();
			CommonUtils.validateParameterInJsonObject(result, "emp_initial");
			CommonUtils.validateParameterInJsonObject(result, "date");
			CommonUtils.validateParameterInJsonObject(result, "leave_request_id");
			CommonUtils.validateParameterInJsonObject(result, "leave_status");
		} else {
			JsonObject result = CommonUtils.getValidateResultObject(response);
			Reporter.log(result.toString());
		}

	}

	@QAFTestStep(description = "user should get all his leave list")
	public void userShouldGetAllHisLeaveList() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("from_date", leaveBean.getStartDate());
		jsonObject.put("to_date", leaveBean.getEndDate());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.GET_USER_ALL_LEAVE_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("leave_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("leave_status").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("date_applied").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("display_name").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get location holiday list")
	public void userShouldGetLocationHolidayList() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("start_date", leaveBean.getStartDate());
		jsonObject.put("end_date", leaveBean.getEndDate());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.GET_LOCATION_HOLIDAY_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("holiday_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("date").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("location").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get list of all leave types")
	public void userShouldGetListOfAllLeaveTypes() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LIST_OF_LEAVE_TYPES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 1; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("countryFlag").toString(),
					Matchers.containsString("IN"));
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("leave_type_id").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("leave_type_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get teams leave balance lists")
	public void userShouldGetTeamsLeaveBalanceLists() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_TEAMS_LEAVE_BALANCE_LISTS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("user_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("user_name").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject()).get("result")
					.getAsJsonArray().toString(), Matchers.notNullValue());
			
			NwwResults = results.get(index).getAsJsonObject();
			newResult = NwwResults.get("result").getAsJsonArray();

			for (index2 = 0; index2 <= newResult.size() - 1; index2++) {
				Validator.verifyThat((newResult.get(index2).getAsJsonObject())
						.get("leave_type_id").toString(), Matchers.notNullValue());
				Validator.verifyThat((newResult.get(index2).getAsJsonObject())
						.get("leave_name").toString(), Matchers.notNullValue());
				Validator.verifyThat((newResult.get(index2).getAsJsonObject())
						.get("leave_bal").toString(), Matchers.notNullValue());
			}

		}
	}

	@QAFTestStep(description = "user requests for a leave")
	public void userRequestsForALeave() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject3 = new JSONObject();
		jsonObject3.put("leaveType", leaveBean.getRegularLeaveType());
		jsonObject3.put("leaveFromDate", leaveBean.getLeave_date());
		jsonObject3.put("leaveToDate", leaveBean.getLeave_date());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject3);
		jsonObject2.put("leave", jsonArray);
		jsonObject.put("leaveDetails", jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(LeaveEndPoints.REQUEST_LEAVE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		if (result.toString().contains("leave_request_id")) {
			CommonUtils.validateParameterInJsonObject(result, "leave_request_id");

		} else if ((result.toString()
				.contains("You have already applied/taken for selected days."))
				|| (result.toString()
						.contains("Applying leaves are week off or holidays"))) {
			Reporter.log("Verified");
		}
	}

	@QAFTestStep(description = "user should get teams leave lists.")
	public void userShouldGetTeamsLeaveLists() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_TEAMS_LEAVE_LISTS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("leave_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("applied_leave_date").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject()).get("leaves")
					.getAsJsonArray().toString(), Matchers.notNullValue());

			NwwResults = results.get(index).getAsJsonObject();
			newResult = NwwResults.get("leaves").getAsJsonArray();

			for (index2 = 0; index2 <= newResult.size() - 1; index2++) {
				Validator.verifyThat((newResult.get(index2).getAsJsonObject())
						.get("leave_type_id").toString(), Matchers.containsString("LTY"));
				Validator.verifyThat((newResult.get(index2).getAsJsonObject())
						.get("leave_id").toString(), Matchers.notNullValue());
				Validator.verifyThat((newResult.get(index2).getAsJsonObject())
						.get("is_reqcancel").toString(), Matchers.notNullValue());
			}
		}
	}

	@QAFTestStep(description = "user should request special leave.")
	public void userShouldRequestSpecialLeave() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", leaveBean.getExternalToken());
		jsonObject2 = new JSONObject();
		jsonObject3 = new JSONObject();
		jsonObject3.put("leaveType", leaveBean.getSpecialLeaveType());
		jsonObject3.put("leaveFromDate", leaveBean.getLeave_date());
		jsonObject3.put("leaveToDate", leaveBean.getLeave_date());
		jsonObject.put("leaveDetails", jsonObject3);
		ClientUtils.getWebResource(LeaveEndPoints.REQUEST_SPECIAL_LEAVE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		if (result.toString().contains("leave_request_id")) {
			CommonUtils.validateParameterInJsonObject(result, "leave_request_id");
		} else if ((result.toString()
				.contains("You have already applied/taken for selected days."))
				|| (result.toString()
						.contains("Applying leaves are week off or holidays"))) {
			Reporter.log("Verified");
		}
	}

	@QAFTestStep(description = "user should get list of groups")
	public void userShouldGetListOfGroups() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LIST_OF_GROUPS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 1; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("name").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get leave employee balance on leave type.")
	public void userShouldGetLeaveEmployeeBalanceOnLeaveType() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("emp_number",
				ConfigurationManager.getBundle().getProperty("emp_id"));
		jsonObject.put("leavetype", leaveBean.getRegularLeaveType());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(LeaveEndPoints.GET_LEAVE_EMPLOYEE_BALANCE_ON_LEAVE_TYPE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("leaveType").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("number").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get location floating holidays list")
	public void userShouldGetLocationFloatingHolidaysList() {
		ClientUtils.getWebResource(LeaveEndPoints.GET_LOCATION_FLOATING_HOLIDAY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("floating_holiday_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("description").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("location").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("date").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should add holidays")
	public void userShouldAddHolidays() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("location_name", leaveBean.getLocationName());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("leave_name", leaveBean.getLeaveName());
		jsonObject.put("holiday_date", leaveBean.getHolidayDate());
		jsonObject.put("locations", jsonArray);
		ClientUtils.getWebResource(LeaveEndPoints.ADD_HOLIDAYS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);

		if ((result.toString().contains("Holiday added successfully"))
				|| (result.toString().contains(
						"This date is already exist in Holiday/Floating holiday"))) {
			Reporter.log("Verified");
		}
	}

	@QAFTestStep(description = "user should add floating holidays")
	public void userShouldAddFloatingHolidays() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject2 = new JSONObject();
		jsonObject2.put("location_name", leaveBean.getLocationName());
		jsonArray = new JSONArray();
		jsonArray.put(jsonObject2);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("leave_name", leaveBean.getLeaveName());
		jsonObject.put("holiday_date", leaveBean.getHolidayDate());
		jsonObject.put("locations", jsonArray);
		ClientUtils.getWebResource(LeaveEndPoints.ADD_FLOATING_HOLIDAYS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);

		if ((result.toString().contains("Floating holiday added successfully"))
				|| (result.toString().contains(
						"This date is already exist in Holiday/Floating holiday"))) {
			Reporter.log("Verified");
		}
	}

	@QAFTestStep(description = "user should delete holidays")
	public void userShouldDeleteHolidays() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("holiday_id", leaveBean.getHolidayId());
		ClientUtils.getWebResource(LeaveEndPoints.DELETE_HOLIDAYS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);

		if (result.toString().contains("Holiday(s) deleted successfully")) {
			Reporter.log("Verified");
		}
	}

	@QAFTestStep(description = "user should delete floating holidays")
	public void userShouldDeleteFloatingHolidays() {
		leaveBean = new LeaveBean();
		leaveBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject.put("holiday_id", leaveBean.getHolidayId());
		ClientUtils.getWebResource(LeaveEndPoints.DELETE_FLOATING_HOLIDAYS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);

		if (result.toString().contains("Floating holiday(s) deleted successfully")) {
			Reporter.log("Verified");
		}
	}

}