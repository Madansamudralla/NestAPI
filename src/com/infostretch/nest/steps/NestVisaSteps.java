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
	 * 
	 *
	 */
	@QAFTestStep(description = "User login as manager")
	public static String userLoginAsManager() {

		return UserType.ManagerUser.doLogin();
	}

	@QAFTestStep(description = "User should get accecible menu list")
	public void userShouldGetAccecibleMenuList() {

		ClientUtils.getWebResource(VisaEndPoints.ACCESSIBLE_MENU_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

	@QAFTestStep(description = "User should get visa country list")
	public void userShouldGetVisaCountryList() {

		ClientUtils.getWebResource(VisaEndPoints.VISA_COUNTRY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));


	}

	@QAFTestStep(description = "User should select {0} and {1} to get visa request list for employee")
	public void userShouldSelectAndToGetVisaRequestListForEmployee(String startdate,
			String enddate) {

		JSONObject json = new JSONObject();

		json.put("token", TokenUtils.getTokenAsString());
		json.put("initiated_from", startdate);
		json.put("initiated_to", startdate);

		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_EMPLOYEE).type(MediaType.APPLICATION_JSON)
				.post(json.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	
	}

	@QAFTestStep(description = "user should get menu url")
	public void userShouldGetMenuUrl() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_MENU_URL)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
	}

	
	@QAFTestStep(description = "User should get supervisor name")
	public void userShouldGetSupervisorName() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_SUPERVISOR_NAME)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
	}

}
