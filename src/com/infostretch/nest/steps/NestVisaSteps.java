package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.infostretch.nest.providers.VisaEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.infostretch.nest.utils.TokenUtils.UserType;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.NotYetImplementedException;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestVisaSteps {

	/**
	 * Palash Saxena
	 * VISA module API's
	 */
	@QAFTestStep(description = "user login as manager")
	public static String UserLoginAsManager() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "user should get accecible menu list")
	public void UserShouldGetAccecibleMenuList() {

		ClientUtils.getWebResource(VisaEndPoints.ACCESSIBLE_MENU_LIST)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "user should get visa country list")
	public void UserShouldGetVisaCountryList() {

		ClientUtils.getWebResource(VisaEndPoints.VISA_COUNTRY_LIST)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should select {0} and {1} to get visa request list for employee")
	public void UserShouldSelectAndToGetVisaRequestListForEmployee(String startdate,
			String enddate) {

		JSONObject json = new JSONObject();

		json.put("token", TokenUtils.getTokenAsStr());
		json.put("initiated_from", startdate);
		json.put("initiated_to", startdate);

		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_EMPLOYEE)
		.type(MediaType.APPLICATION_JSON).post(json.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get menu url")
	public void UserShouldGetMenuUrl() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_MENU_URL)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get supervisor name")
	public void UserShouldGetSupervisorName() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_SUPERVISOR_NAME)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	@QAFTestStep(description = "user should get visa type for dd")
	public void UserShouldGetVisaTypeForDd() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_TYPE_FOR_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));

	}

	
	@QAFTestStep(description = "user should get all employee list of expense")
	public void UserShouldGetAllEmployeeListOfExpense() {
		
		ClientUtils.getWebResource(VisaEndPoints.VISA_ALL_EMPLOYEE_LIST_EXPENSE)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response= ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

}
