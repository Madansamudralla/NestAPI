/**
 * 
 */
package com.infostretch.nest;


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

/**
 * @author Suryakant.Kale
 */
public class StepsBackLog {

	@QAFTestStep(description = "user can login as manager")
	public String userCanLoginAsManager() {
		
		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user get travel admin location for travel")
	public void userGetTravelAdminLocation() {
		
		JSONObject	jsonObject = new JSONObject();
		
		 jsonObject.put("token","ef39ddc87141023c9928cb41f260c384");
		 
		ClientUtils.getWebResource(TravelEndPoints.GET_TRAVEL_ADMIN_LOC_EMPLOYEES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post(jsonObject.toString());
		
		Response response = ClientUtils.getResponse();
		Reporter.log("--Verified--");
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
		CommonUtils.getValidatedResultArray(response);
		
	}
	
	
	@QAFTestStep(description = "user get the mode of travel for travel")
	public void userGetModeOfTravel() {
		
		JSONObject	jsonObject = new JSONObject();
		
		 jsonObject.put("token","1cfb0b27326b884dcf38a781bdb4b15f");
		
		ClientUtils.getWebResource(TravelEndPoints.GET_MODE_OF_TRAVEL_DD)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post(jsonObject.toString());
		
		Response response = ClientUtils.getResponse();
		
		Reporter.log("--Verified--");	
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
		JsonObject responseBody = new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		JsonObject results = responseBody.get("response").getAsJsonObject().get("results").getAsJsonObject();
		Validator.verifyThat((results.getAsJsonObject()).get("2").toString(),Matchers.containsString("yrryrty"));
		
		
	}
	
	@QAFTestStep(description = "user can view the details of travel")
	public void userViewtravelDetails() {
		
		JSONObject	jsonObject = new JSONObject();
		
		 jsonObject.put("token","d9380881f1a4a349a440eea64937eee6");
		 jsonObject.put("trvl_travel_request_id","2255");
		
		 ClientUtils.getWebResource(TravelEndPoints.VIEW_TRAVEL_DETAILS)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post(jsonObject.toString());
		 
		Response response = ClientUtils.getResponse();
		
			Reporter.log("--Verified--");
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		
		
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "user can get the employee designation list for travel")
	public void userEmployeeDesignationList() {
		
		
		 ClientUtils.getWebResource(TravelEndPoints.GET_EMPLOYEE_DESIGNATION_LIST_DD)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		
		Reporter.log("--TRUE--");
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "user can get the all employee list for expense for travel")
	public void userGetEmployeeListForExpense() {
		
		ClientUtils.getWebResource(TravelEndPoints.GET_ALL_EMPLOYEE_LIST_EXPENSE)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "user can check the approval and reject of travel request for travel")
	public void userVerifyApproveAndRejectOfRequest() {
		
		ClientUtils.getWebResource(TravelEndPoints.APPROVE_REJECT_TRAVEL_REQUEST)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "user can verify the managers dd for travel")
	public void userVerifyManagersDD() {
		JSONObject	jsonObject = new JSONObject();
		
		 jsonObject.put("token","c89544bfc2d316781862d7f75747e53c");
//		7ecdc9ab75e740420c81cbe79026cc83
		ClientUtils.getWebResource(TravelEndPoints.APPROVE_REJECT_TRAVEL_REQUEST)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "user can verify the added and edited booking details for travel")
	public void userVerifyAddEditBookingDetails() {
		JSONObject	jsonObject = new JSONObject();
		
		 jsonObject.put("token","c89544bfc2d316781862d7f75747e53c");

		ClientUtils.getWebResource(TravelEndPoints.ADD_EDIT_BOOKING_DETAILS)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post(jsonObject.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

}
