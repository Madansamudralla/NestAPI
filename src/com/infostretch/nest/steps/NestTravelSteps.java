
package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.TravelDataBean;
import com.infostretch.nest.providers.TravelEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.infostretch.nest.utils.TokenUtils.UserType;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

/**
 * @author Suryakant.Kale
 */
public class NestTravelSteps {

	public Response response;
	public JsonArray jsonArrayResult;
	public JsonObject jsonObjectResult;
	public JSONObject jsonObject, jsonObject1;
	public int index;
	TravelDataBean travelDataBean = new TravelDataBean();

	@QAFTestStep(description = "user can login as manager")
	public String userCanLoginAsManager() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user can verify the added and edited travels locations with token {0} for travel")
	public void userCanVerifyTheAddedAndEditedTravelsLocationsWithTokenForTravel(
			String token) {

		travelDataBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("title", travelDataBean.getTitle());
		jsonObject1.put("status", travelDataBean.getStatus());
		jsonObject.put("token", token);
		jsonObject.put("trvlocation", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.ADD_EDIT_TRAVEL_LOCATIONS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can verify the deleted travel locations with token {0} and travel location id {1} for travel")
	public void userCanVerifyTheDeletedTravelLocationsWithTokenAndTravelLocationIdForTravel(
			String token, String trvl_travel_location_id) {

		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("trvl_travel_location_id", trvl_travel_location_id);
		jsonObject.put("token", token);
		jsonObject.put("trvlocation", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.DELETE_TRAVEL_LOCATIONS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"trvl_travel_location_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can verify the travel requests for employees for travel")
	public void userCanVerifyTheTravelRequestsForEmployeesForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_REQUESTS_FOR_EMPLOYEE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonArrayResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonArray();

		for (int integer = 0; integer <= jsonArrayResult.size() - 1; integer++) {
			Validator.verifyThat(
					(jsonArrayResult.get(integer).getAsJsonObject())
							.get("trvl_travel_request_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(integer).getAsJsonObject())
							.get("request_by_emp_number").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user can verify the booking details with token {0} for travel")
	public void userCanVerifyTheBookingDetailsWithTokenForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("travel_id", travelDataBean.getTravel_id());
		ClientUtils.getWebResource(TravelEndPoints.GET_BOOKING_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "booking_cnt");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "booking_submited");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_loc_code");
	}

	@QAFTestStep(description = "user can verify the deleted booking details with token {0} for travel")
	public void userCanVerifyTheDeletedBookingDetailsWithTokenForTravel(String token) {

		ClientUtils.getWebResource(TravelEndPoints.DELETE_BOOKING_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		Validator.verifyThat(
				(jsonObjectResult.getAsJsonObject()).get("trvl_booking_id").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(jsonObjectResult.getAsJsonObject()).get("action_message").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(jsonObjectResult.getAsJsonObject()).get("response_type").toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user can verify the booking documents with token {0} for travel")
	public void userCanVerifyTheBookingDocumentsWithTokenForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("trvl_booking_id", travelDataBean.getTrvl_booking_id());
		ClientUtils.getWebResource(TravelEndPoints.GET_BOOKING_DOCUMENTS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("permission").getAsJsonObject();
		JsonObject results1 =
				responseBody.get("response").getAsJsonObject().get("results")
						.getAsJsonObject().get("mngr_permission").getAsJsonObject();
		Validator.verifyThat((jsonObjectResult.getAsJsonObject())
				.get("get_booking_documents").toString(), Matchers.notNullValue());
		Validator.verifyThat(
				(results1.getAsJsonObject()).get("get_booking_documents").toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user get travel admin location for travel")
	public void userGetTravelAdminLocation() {

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_ADMIN_LOC_EMPLOYEES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		int jsonArraySize = jsonArrayResult.size();

		for (index = 0; index < jsonArraySize; index++) {
			JsonObject jsonObject2 = jsonArrayResult.get(0).getAsJsonObject();
			CommonUtils.validateParameterInJsonObject(jsonObject2, "emp_number");
			CommonUtils.validateParameterInJsonObject(jsonObject2, "display_name");
		}
	}

	@QAFTestStep(description = "user get the mode of travel with mode_id {0} and mode_name {1} for travel")
	public void userGetTheModeOfTravelWithMode_idAndMode_nameForTravel(String id,
			String modeName) {

		ClientUtils.getWebResource(TravelEndPoints.GET_MODE_OF_TRAVEL_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log("--Verified--");
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		Validator.verifyThat((jsonObjectResult.getAsJsonObject()).get(id).toString(),
				Matchers.containsString(modeName));
	}

	@QAFTestStep(description = "user can view the details of travel")
	public void userViewtravelDetails() {

		ClientUtils.getWebResource(TravelEndPoints.VIEW_TRAVEL_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		jsonObjectResult = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		Validator.verifyThat(
				(jsonObjectResult.getAsJsonObject()).get("type_name").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(jsonObjectResult.getAsJsonObject()).get("trvl_statusLog").toString(),
				Matchers.notNullValue());
	}

	@QAFTestStep(description = "user can get the employee designation list for travel")
	public void userEmployeeDesignationList() {

		ClientUtils.getWebResource(TravelEndPoints.GET_EMPLOYEE_DESIGNATION_LIST_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "108");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "25");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "111");
	}

	@QAFTestStep(description = "user can get the all employee list for expense for travel")
	public void userGetEmployeeListForExpense() {

		ClientUtils.getWebResource(TravelEndPoints.GET_ALL_EMPLOYEE_LIST_EXPENSE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user can verify the added and edited booking details for travel")
	public void userVerifyAddEditBookingDetails() {

		ClientUtils.getWebResource(TravelEndPoints.ADD_EDIT_BOOKING_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "trvl_booking_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can approve reject travel request with token {0} for travel")
	public void userCanApproveRejectTravelRequestWithTokenForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("trvl_travel_request_id",
				travelDataBean.getTrvl_travel_request_id());
		jsonObject1.put("comment", travelDataBean.getComment());
		jsonObject1.put("billable", travelDataBean.getBillable());
		jsonObject1.put("status", travelDataBean.getStatus());
		jsonObject.put("token", token);
		jsonObject.put("travelrequest", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.APPROVE_REJECT_TRAVEL_REQUEST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can verify the managers dd  for travel")
	public void userCanVerifyManagersDDForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_MANAGERS_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "erep_sup_emp_number");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "emp_number");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "employee_id");
		}
	}

	@QAFTestStep(description = "user can verify the booking types for travel")
	public void userCanVerifyTheBookingTypesForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_BOOKING_TYPES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonArray results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonArray();

		CommonUtils.validateParameterInJsonObject(results.get(0).getAsJsonObject(), "id");
		CommonUtils.validateParameterInJsonObject(results.get(0).getAsJsonObject(),
				"name");
	}

	@QAFTestStep(description = "user can verify the travel locations for travel")
	public void userCanVerifyTheTravelLocationsForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_LOCATIONS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(),
					"trvl_travel_location_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "status");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "created_date");
		}
	}

	@QAFTestStep(description = "user can verify the deleted travel agent with token {0} and travel agent id {1}for travel")
	public void userCanVerifyTheDeletedTravelAgentWithTokenAndTravelAgentIdForTravel(
			String token, String trvl_agent_id) {

		jsonObject1 = new JSONObject();
		jsonObject1.put("trvl_agent_id", trvl_agent_id);
		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("trvagent", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.DELETE_TRAVEL_AGENT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "trvl_agent_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can verify the added and edited travel agent with token {0} for travel")
	public void userCanVerifyTheAddedAndEditedTravelAgentForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject1 = new JSONObject();
		jsonObject1.put("agent_name", travelDataBean.getAgent_name());
		jsonObject1.put("contact_person", travelDataBean.getContact_person());
		jsonObject1.put("contact_number", travelDataBean.getContact_number());
		jsonObject1.put("status", travelDataBean.getStatus());
		jsonObject1.put("trvl_agent_id", travelDataBean.getTrvl_agent_id());
		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("trvagent", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.ADD_EDIT_TRAVEL_AGENT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "trvl_agent_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can add and edit the travel categories with token {0} for travel")
	public void userCanAddAndEditTheTravelCategoriesWithTokenForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject1 = new JSONObject();
		jsonObject1.put("trvl_travel_category_id",
				travelDataBean.getTrvl_travel_category_id());
		jsonObject1.put("title", travelDataBean.getTitle());
		jsonObject1.put("level", "2");
		jsonObject1.put("level", travelDataBean.getLevel());
		jsonObject1.put("description", travelDataBean.getDescription());
		jsonObject1.put("job_title_id", travelDataBean.getJob_title_id());
		jsonObject1.put("trvl_mode_of_travel_id",
				travelDataBean.getTrvl_mode_of_travel_id());
		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("trvcat_details", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.ADD_EDIT_TRAVEL_CATEGORIES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can get the mode of travel for travel")
	public void userCanDeleteTheModeOfTravelForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_MODE_OF_TRAVEL)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(),
					"trvl_mode_of_travel_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "title");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "created_date");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "updated_date");
		}
	}

	@QAFTestStep(description = "user can get travel agent for travel")
	public void userCanVerifyTheDeletedCategoriesForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_AGENT)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "trvl_agent_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "agent_name");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "contact_person");
		}
	}

	@QAFTestStep(description = "user verify the deleted mode of travel with token {0} for travel")
	public void userVerifyTheDeletedModeOfTravelWithTokenForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject1 = new JSONObject();
		jsonObject = new JSONObject();
		jsonObject1.put("trvl_mode_of_travel_id",
				travelDataBean.getTrvl_mode_of_travel_id());
		jsonObject.put("token", token);
		jsonObject.put("trvmode_details", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.DELETE_MODE_OF_TRAVEL)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can verify added and edited mode of travel with token {0} for travel")
	public void userCanVerifyAddedAndEditedModeOfTravelWithTokenForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject1 = new JSONObject();
		jsonObject = new JSONObject();
		jsonObject1.put("title", travelDataBean.getTitle());
		jsonObject1.put("status", travelDataBean.getStatus());
		jsonObject1.put("description", travelDataBean.getDescription());
		jsonObject.put("token", token);
		jsonObject.put("trvmode_details", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.ADD_EDIT_MODE_OF_TRAVEL)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user verify the deleted categories with token {0} for travel")
	public void userVerifyTheDeletedCategoriesWithTokenForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject1 = new JSONObject();
		jsonObject = new JSONObject();
		jsonObject1.put("trvl_travel_category_id",
				travelDataBean.getTrvl_travel_category_id());
		jsonObject.put("token", token);
		jsonObject.put("trvcat_details", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.DELETE_TRAVEL_CATEGORIES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"trvl_travel_category_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

	@QAFTestStep(description = "user can verify the travel categories for travel")
	public void userCanVerifyTheTravelCategoriesForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_CATEGORIES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(),
					"trvl_travel_category_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "level");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "created_date");
		}
	}

	@QAFTestStep(description = "user can get the id proof attachments for travel")
	public void userCanGetTheIdProofAttachmentsForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_ID_PROOF_ATTACHMENTS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_loc");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "qryCount");

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "trvl_emp_proof_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(),
					"trvl_id_proof_filename");
		}
	}

	@QAFTestStep(description = "user can get the employee membership with {0} for travel")
	public void userCanGetTheEmployeeMembershipWithForTravel(String token) {

		travelDataBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("emp_number", travelDataBean.getEmp_number());
		jsonObject.put("book_membership", travelDataBean.getBook_membership());
		ClientUtils.getWebResource(TravelEndPoints.GET_EMPLOYEE_MEMBERSHIPS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "membership_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "airline_title");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "membership_number");
		}
	}

	@QAFTestStep(description = "user can verify the employee travel list for travel")
	public void userCanVerifyTheEmployeeTravelListForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_EMPLOYEE_TRAVEL_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(),
					"trvl_travel_request_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "emp_number");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(),
					"request_by_emp_number");
		}
	}

	@QAFTestStep(description = "user can get the travel locations for employee with token {0} for travel")
	public void userCanGetTheTravelLocationsForEmployeeWithTokenForTravel(String token) {

		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_LOCATIONS_MYVIEW)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(),
					"trvl_travel_location_id");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "status");
			CommonUtils.validateParameterInJsonObject(
					jsonArrayResult.get(index).getAsJsonObject(), "created_date");
		}
	}

	@QAFTestStep(description = "user verify the deleted employee memberships with the token {0} for travel")
	public void userVerifyTheDeletedEmployeeMembershipsWithTheTokenForTravel(
			String token) {
		travelDataBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("membership_id", travelDataBean.getMembership_id());
		jsonObject.put("token", token);
		jsonObject.put("membershipDetails", jsonObject1);
		ClientUtils.getWebResource(TravelEndPoints.DELETE_EMPLOYEE_MEMBERSHIPS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "status");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "response_type");
	}

}
