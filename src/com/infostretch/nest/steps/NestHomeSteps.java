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
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestHomeSteps {
	JSONObject jsonObject;
	JsonObject result,responseBody;
	Response response;
	JsonArray results;
	int index;

	@QAFTestStep(description = "user should get accessible menu list")
	public void verifyAccessibleMenuList() {
		ClientUtils.getWebResource(EndPoints.ACCESSIBLE_MENU_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();

		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		JsonArray param1reaults =
				results.get(0).getAsJsonObject().get("manager").getAsJsonArray();
		Validator.verifyThat(
				(param1reaults.get(0).getAsJsonObject()).get("name").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(param1reaults.get(0).getAsJsonObject()).get("Admin").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				param1reaults.get(0).getAsJsonObject().get("Admin").getAsJsonObject()
						.get("0").getAsJsonObject().get("url").toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user should get all leave types")
	public void userShouldGetAllLeaveTypes() {
		ClientUtils.getWebResource(EndPoints.GET_ALL_LEAVE_TYPES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("IN").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "PTO");
		CommonUtils.validateParameterInJsonObject(result, "Comp Off");
		CommonUtils.validateParameterInJsonObject(result, "LWP");

	}

	@QAFTestStep(description = "user should get menu urls")
	public void userShouldGetMenuUrls() {
		ClientUtils.getWebResource(EndPoints.GET_MENU_URLS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		CommonUtils.getValidatedResultArray(response);

	}

	@QAFTestStep(description = "user should get release data")
	public void userShouldGetReleaseData() {
		ClientUtils.getWebResource(EndPoints.GET_RELEASE_DATA)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("release_note_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("release_date").toString(),
					Matchers.notNullValue());
		}

	}
	@QAFTestStep(description = "user should get my leave list")
	public void userShouldGetMyLeaveList() {

		ClientUtils.getWebResource(EndPoints.GET_USER_LEAVE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("2232").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "emp_initial");
		CommonUtils.validateParameterInJsonObject(result, "date");
		CommonUtils.validateParameterInJsonObject(result, "leave_request_id");
		CommonUtils.validateParameterInJsonObject(result, "leave_status");
	}

	@QAFTestStep(description = "user should get leave balances")
	public void userShouldGetLeaveBalances() {
		ClientUtils.getWebResource(EndPoints.GET_LEAVE_BALANCES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		JsonArray param1results = result.get("regular").getAsJsonArray();
		JsonArray param2results = result.get("special").getAsJsonArray();

		for (index = 0; index <= param1results.size() - 1; index++) {
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("leaveTypeId").toString(), Matchers.containsString("LTY"));
			Validator.verifyThat((param1results.get(index).getAsJsonObject())
					.get("leaveType").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1results.get(index).getAsJsonObject()).get("number").toString(),
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
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(0).getAsJsonObject())
					.get("floating_holiday_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("description").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get holiday list")
	public void userShouldGetHolidayList() {
		ClientUtils.getWebResource(EndPoints.GET_HOLIDAY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

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
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		JsonArray param1reaults = result.get("Friday").getAsJsonArray();

		for (index = 0; index <= param1reaults.size() - 1; index++) {
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("trn_course_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(param1reaults.get(index).getAsJsonObject()).get("title").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((param1reaults.get(index).getAsJsonObject())
					.get("department_id").toString(), Matchers.notNullValue());
		}
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
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "main_location_name");
	}

	@QAFTestStep(description = "User should get event list")
	public void userShouldGetEventList() {
		ClientUtils.getWebResource(EndPoints.GET_EVENT_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

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
