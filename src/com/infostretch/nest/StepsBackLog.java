/**
 * 
 */
package com.infostretch.nest;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;

import com.infostretch.nest.providers.EndPoints;
import com.infostretch.nest.utils.ClientUtils;
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
	
		ClientUtils.getWebResource(EndPoints.GET_TRAVEL_ADMIN_LOC_EMPLOYEES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	
	@QAFTestStep(description = "user get the mode of travel for travel")
	public void userGetModeOfTravel() {
	
		ClientUtils.getWebResource(EndPoints.GET_MODE_OF_TRAVEL_DD)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "user can view the details of travel")
	public void userViewtravelDetails() {
		
		ClientUtils.getWebResource(EndPoints.VIEW_TRAVEL_DETAILS)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "user can get the employee designation list for travel")
	public void userEmployeeDesignationList() {
		
		ClientUtils.getWebResource(EndPoints.GET_EMPLOYEE_DESIGNATION_LIST_DD)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}

}
