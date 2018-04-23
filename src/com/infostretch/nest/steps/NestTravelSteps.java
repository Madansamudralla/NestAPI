/**
 * 
 */
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
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author Suryakant.Kale
 */
public class NestTravelSteps {

	
	
	
	@QAFTestStep(description = "user can login as manager")
	public String userCanLoginAsManager() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user get travel admin location for travel")
	public void userGetTravelAdminLocation() {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("token", "ef39ddc87141023c9928cb41f260c384");

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_ADMIN_LOC_EMPLOYEES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		//jsonObject.toString()
		Response response = ClientUtils.getResponse();

		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonArray results = CommonUtils.getValidatedResultArray(response);

		int jsonArraySize = results.size();
		for (int index = 0; index < jsonArraySize; index++) {
			JsonObject jsonObject2 = results.get(0).getAsJsonObject();
			CommonUtils.validateParameterInJsonObject(jsonObject2, "emp_number");
			CommonUtils.validateParameterInJsonObject(jsonObject2, "display_name");
		}

	}

	@QAFTestStep(description = "user get the mode of travel for travel")
	public void userGetModeOfTravel() {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("token", "1cfb0b27326b884dcf38a781bdb4b15f");

		ClientUtils.getWebResource(TravelEndPoints.GET_MODE_OF_TRAVEL_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
//		jsonObject.toString()
		Response response = ClientUtils.getResponse();

		Reporter.log("--Verified--");
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject results = CommonUtils.getValidateResultObject(response);

		Validator.verifyThat((results.getAsJsonObject()).get("2").toString(),
				Matchers.containsString("yrryrty"));

	}

	@QAFTestStep(description = "user can view the details of travel")
	public void userViewtravelDetails() {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("token", "d9380881f1a4a349a440eea64937eee6");
		jsonObject.put("trvl_travel_request_id", "2255");

		ClientUtils.getWebResource(TravelEndPoints.VIEW_TRAVEL_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();

		Response response = ClientUtils.getResponse();

		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();

		Validator.verifyThat((results.getAsJsonObject())
				.get("type_name").toString(), Matchers.notNullValue());
		
		Validator.verifyThat((results.getAsJsonObject())
				.get("trvl_statusLog").toString(), Matchers.notNullValue());


	}

	@QAFTestStep(description = "user can get the employee designation list for travel")
	public void userEmployeeDesignationList() {

		ClientUtils.getWebResource(TravelEndPoints.GET_EMPLOYEE_DESIGNATION_LIST_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();

		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject jsonObject2 = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObject2, "108");
		CommonUtils.validateParameterInJsonObject(jsonObject2, "25");
		CommonUtils.validateParameterInJsonObject(jsonObject2, "111");

	}

	@QAFTestStep(description = "user can get the all employee list for expense for travel")
	public void userGetEmployeeListForExpense() {

		ClientUtils.getWebResource(TravelEndPoints.GET_ALL_EMPLOYEE_LIST_EXPENSE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonArray results = CommonUtils.getValidatedResultArray(response);

		for (int integer = 0; integer <= results.size() - 1; integer++) {
			Validator.verifyThat(
					(results.get(integer).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user can verify the added and edited booking details for travel")
	public void userVerifyAddEditBookingDetails() {

		ClientUtils.getWebResource(TravelEndPoints.ADD_EDIT_BOOKING_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject jsonObject2 = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObject2, "trvl_booking_id");
		CommonUtils.validateParameterInJsonObject(jsonObject2, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObject2, "response_type");

	}

	@QAFTestStep(description = "user can verify the export of travel report for travel")
	public void userVerifyExportTravelReport() {

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();

		jsonObject1.put("travel_date", "11-2-2018");
		jsonObject1.put("travel_date_to", "11-8-2018");
		jsonObject1.put("date_type", "trvldate");

		jsonObject.put("token", "d043b482b11bd1e401810cee71df055e");
		jsonObject.put("travelReport", jsonObject1);

		ClientUtils.getWebResource(TravelEndPoints.EXPORT_BOOKING_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());

		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user can verify travel reports for travel")
	public void userVerifyTravelReport() {

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_REPORTS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);

		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user can verify the booking documents for travel")
	public void userVerifyBookingDocuments() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", "97caf9317647c2d694b3650d6cf13992");
		jsonObject.put("trvl_booking_id", "22");

		ClientUtils.getWebResource(TravelEndPoints.GET_BOOKING_DOCUMENTS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());

		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("permission").getAsJsonObject();
		JsonObject results1 =
				responseBody.get("response").getAsJsonObject().get("results")
						.getAsJsonObject().get("mngr_permission").getAsJsonObject();

		Validator.verifyThat(
				(results.getAsJsonObject()).get("get_booking_documents").toString(),
				Matchers.notNullValue());
		Validator.verifyThat(
				(results1.getAsJsonObject()).get("get_booking_documents").toString(),
				Matchers.notNullValue());

	}

	@QAFTestStep(description = "user can verify the deleted booking details for travel")
	public void userVerifyDeletedBookingDetails() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", "97caf9317647c2d694b3650d6cf13992");
		jsonObject.put("trvl_booking_id", "22");

		ClientUtils.getWebResource(TravelEndPoints.DELETE_BOOKING_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();

		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject results = CommonUtils.getValidateResultObject(response);

		Validator.verifyThat(
				(results.getAsJsonObject()).get("trvl_booking_id").toString(),
				Matchers.notNullValue());

		Validator.verifyThat((results.getAsJsonObject()).get("action_message").toString(),
				Matchers.notNullValue());

		Validator.verifyThat((results.getAsJsonObject()).get("response_type").toString(),
				Matchers.notNullValue());

	}

	@QAFTestStep(description = "user can verify the booking details for travel")
	public void userVerifyBookingDetails() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", "029822145261264687eb04cc24f20ba6");
		jsonObject.put("travel_id", "2221");
		jsonObject.put("sort", "");
		jsonObject.put("order", "");

		ClientUtils.getWebResource(TravelEndPoints.GET_BOOKING_DETAILS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());

		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject jsonObject2 = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObject2, "booking_cnt");
		CommonUtils.validateParameterInJsonObject(jsonObject2, "booking_submited");
		CommonUtils.validateParameterInJsonObject(jsonObject2, "emp_loc_code");
	}

	@QAFTestStep(description = "user can verify the travel requests for employees for travel")
	public void userCanVerifyTheTravelRequestsForEmployeesForTravel() {

		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_REQUESTS_FOR_EMPLOYEE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();

		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

		JsonObject responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonArray results = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonArray();
		for (int integer = 0; integer <= results.size() - 1; integer++) {

			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("trvl_travel_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(integer).getAsJsonObject())
					.get("request_by_emp_number").toString(), Matchers.notNullValue());
		}
	}

	
	
	

}
