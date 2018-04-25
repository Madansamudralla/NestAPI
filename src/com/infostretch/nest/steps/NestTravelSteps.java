
package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.providers.TravelEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.infostretch.nest.utils.TokenUtils.UserType;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.NotYetImplementedException;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

/**
 * @author Suryakant.Kale
 */
public class NestTravelSteps {

	public Response response;
	public JsonArray resultArray;
	public JsonObject jsonObjectResult;
	public JSONObject jsonObject;

	@QAFTestStep(description = "user can login as manager")
	public String userCanLoginAsManager() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user can verify the added and edited travels locations with token {0} for travel")
	public void userCanVerifyTheAddedAndEditedTravelsLocationsWithTokenForTravel(
			String token) {

		jsonObject = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("title", "Business");
		jsonObject1.put("status", "1");
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

	@QAFTestStep(description = "user can verify the deleted travel locations with token {0} for travel")
	public void userCanVerifyTheDeletedTravelLocationsWithTokenForTravel(String token) {

		jsonObject = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("trvl_travel_location_id", "19");
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
		resultArray = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonArray();

		for (int integer = 0; integer <= resultArray.size() - 1; integer++) {
			Validator.verifyThat(
					(resultArray.get(integer).getAsJsonObject())
							.get("trvl_travel_request_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(resultArray.get(integer).getAsJsonObject())
							.get("request_by_emp_number").toString(),
					Matchers.notNullValue());
		}

	}

	@QAFTestStep(description = "user can verify the booking details with token {0} for travel")
	public void userCanVerifyTheBookingDetailsWithTokenForTravel(String token) {

		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("travel_id", "2221");
		jsonObject.put("sort", "");
		jsonObject.put("order", "");
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

		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("trvl_booking_id", "22");
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

		jsonObject = new JSONObject();
		jsonObject.put("token", token);
		jsonObject.put("trvl_booking_id", "22");
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
		resultArray = CommonUtils.getValidatedResultArray(response);
		int jsonArraySize = resultArray.size();

		for (int index = 0; index < jsonArraySize; index++) {
			JsonObject jsonObject2 = resultArray.get(0).getAsJsonObject();
			CommonUtils.validateParameterInJsonObject(jsonObject2, "emp_number");
			CommonUtils.validateParameterInJsonObject(jsonObject2, "display_name");
		}

	}

	@QAFTestStep(description = "user get the mode of travel with id {0} and name {1} for travel")
	public void userGetTheModeOfTravelWithIdAndNameForTravel(String id, String ModeName) {

		ClientUtils.getWebResource(TravelEndPoints.GET_MODE_OF_TRAVEL_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log("--Verified--");
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		Validator.verifyThat((jsonObjectResult.getAsJsonObject()).get(id).toString(),
				Matchers.containsString(ModeName));

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
		resultArray = CommonUtils.getValidatedResultArray(response);

		for (int index = 0; index <= resultArray.size() - 1; index++) {
			Validator.verifyThat((resultArray.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((resultArray.get(index).getAsJsonObject())
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
		jsonObject = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("trvl_travel_request_id", "2262");
		jsonObject1.put("comment", "rejketc");
		jsonObject1.put("billable", "0");
		jsonObject1.put("status", "2");
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
		resultArray = CommonUtils.getValidatedResultArray(response);

		for (int index = 0; index <= resultArray.size() - 1; index++) {
			CommonUtils.validateParameterInJsonObject(
					resultArray.get(index).getAsJsonObject(), "erep_sup_emp_number");
			CommonUtils.validateParameterInJsonObject(
					resultArray.get(index).getAsJsonObject(), "emp_number");
			CommonUtils.validateParameterInJsonObject(
					resultArray.get(index).getAsJsonObject(), "employee_id");
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

	
	@QAFTestStep(description = "user can verify the deleted booking documents for travel")
	public void userCanVerifyTheDeletedBookingDocumentsForTravel() {
		
		
	}

}
