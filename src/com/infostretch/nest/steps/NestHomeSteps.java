package com.infostretch.nest.steps;
import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.EndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestHomeSteps {
	JSONObject jsonObject;
	int index;

	@QAFTestStep(description = "user should get accessible menu list")
	public void verifyAccessibleMenuList() {
		ClientUtils.getWebResource(EndPoints.ACCESSIBLE_MENU_LIST)
				.entity(TokenUtils.getTokenAsJsonStr())
				.type(MediaType.APPLICATION_JSON).post();

		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));

	}

	@QAFTestStep(description = "user should get all leave types")
	public void userShouldGetAllLeaveTypes() {
		ClientUtils.getWebResource(EndPoints.GET_ALL_LEAVE_TYPES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject results = CommonUtils.getValidateResultObject(response);
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("IN").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(results, "PTO");
		CommonUtils.validateParameterInJsonObject(results, "Comp Off");
		CommonUtils.validateParameterInJsonObject(results, "LWP");

	}

	@QAFTestStep(description = "user should get menu urls")
	public void userShouldGetMenuUrls() {
		ClientUtils.getWebResource(EndPoints.GET_MENU_URLS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);
		Validator.verifyThat(results.size(), Matchers.greaterThan(0));

	}

	@QAFTestStep(description = "user should get release data")
	public void userShouldGetReleaseData() {
		ClientUtils.getWebResource(EndPoints.GET_RELEASE_DATA)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}
	@QAFTestStep(description = "user should get my leave list")
	public void userShouldGetMyLeaveList() {

		ClientUtils.getWebResource(EndPoints.GET_MY_LEAVE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject results = CommonUtils.getValidateResultObject(response);
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("2177").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(results, "emp_initial");
		CommonUtils.validateParameterInJsonObject(results, "date");
		CommonUtils.validateParameterInJsonObject(results, "leave_request_id");
		CommonUtils.validateParameterInJsonObject(results, "leave_status");

	}

	@QAFTestStep(description = "user should get leave balances")
	public void userShouldGetLeaveBalances() {
		ClientUtils.getWebResource(EndPoints.GET_LEAVE_BALANCES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		JsonArray param1reaults = result.get("regular").getAsJsonArray();
		JsonArray param2results = result.get("special").getAsJsonArray();

		for (index = 0; index <= param1reaults.size() - 1; index++) {
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("leaveTypeId").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("leaveType").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1reaults.get(index).getAsJsonObject()).get("number").toString(),
					Matchers.notNullValue());
		}

		for (index = 0; index <= param2results.size() - 1; index++) {
			Validator.verifyThat((param2results.get(index).getAsJsonObject())
					.get("leaveTypeId").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((param2results.get(index).getAsJsonObject())
					.get("leaveType").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param2results.get(index).getAsJsonObject()).get("number").toString(),
					Matchers.notNullValue());
		}

	}

	@QAFTestStep(description = "user should get floating holiday list")
	public void userShouldGetFloatingHolidayList() {
		ClientUtils.getWebResource(EndPoints.GET_FLOATING_HOLIDAY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(0).getAsJsonObject())
					.get("floating_holiday_id").toString(),
					Matchers.containsString("233"));
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("description").toString(),
					Matchers.containsString("fdsf"));
		}
	}

	@QAFTestStep(description = "user should get holiday list")
	public void userShouldGetHolidayList() {
		ClientUtils.getWebResource(EndPoints.GET_HOLIDAY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("holiday_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("description").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("date").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("location").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get training calendar list")
	public void userShouldGetTrainingCalendarList() {
		jsonObject = new JSONObject();
		jsonObject.put("start_date", "18-4-2018");
		jsonObject.put("end_date", "29-4-2018");
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(EndPoints.GET_TRAINING_CALENDAR_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject result = CommonUtils.getValidateResultObject(response);
		Reporter.log(result.toString());
	}

	@QAFTestStep(description = "User should get upcoming events")
	public void userShouldGetUpcomingEvents() {
		jsonObject = new JSONObject();
		jsonObject.put("event_id", "");
		jsonObject.put("eventlist", "1");
		jsonObject.put("order", "asc");
		jsonObject.put("sort", "start_date");
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(EndPoints.GET_UPCOMING_EVENTS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		JsonObject results = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(results, "main_location_name");
	}

	@QAFTestStep(description = "User should get event list")
	public void userShouldGetEventList() {
		ClientUtils.getWebResource(EndPoints.GET_EVENT_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("link").toString(),
					Matchers.notNullValue());
		}
	}
}

