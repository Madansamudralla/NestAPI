package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;

import com.infostretch.nest.providers.EndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestHomeSteps {

	@QAFTestStep(description = "User should get accessible menu list")
	public void verifyAccessibleMenuList() {
		ClientUtils.getWebResource(EndPoints.ACCESSIBLE_MENU_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
	}
	@QAFTestStep(description = "User should get all leave types")
	public void userShouldGetAllLeaveTypes() {
		ClientUtils.getWebResource(EndPoints.GET_ALL_LEAVE_TYPES).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	
	@QAFTestStep(description = "User should get menu urls")
	public void userShouldGetMenuUrls() {
		ClientUtils.getWebResource(EndPoints.GET_MENU_URLS).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
	}
	
	@QAFTestStep(description = "User should get release data")
	public void userShouldGetReleaseData() {
		ClientUtils.getWebResource(EndPoints.GET_RELEASE_DATA).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
		
			
	}
	@QAFTestStep(description = "User should get my leave list")
	public void userShouldGetMyLeaveList() {
		ClientUtils.getWebResource(EndPoints.GET_MY_LEAVE_LIST).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
	}
	@QAFTestStep(description = "User should get leave balances")
	public void userShouldGetLeaveBalances() {
		ClientUtils.getWebResource(EndPoints.GET_LEAVE_BALANCES).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));	
	}
	
	@QAFTestStep(description = "User should get floating holiday list")
	public void userShouldGetFloatingHolidayList() {
		ClientUtils.getWebResource(EndPoints.GET_FLOATING_HOLIDAY_LIST).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));		
	}
	
	@QAFTestStep(description = "User should get holiday list")
	public void userShouldGetHolidayList() {
		ClientUtils.getWebResource(EndPoints.GET_HOLIDAY_LIST).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));	
	}
	
	@QAFTestStep(description = "User should get training calendar list")
	public void userShouldGetTrainingCalendarList() {
		ClientUtils.getWebResource(EndPoints.GET_TRAINING_CALENDAR_LIST).entity(TokenUtils.getTokenAsJsonStr())
		.type(MediaType.APPLICATION_JSON).post();
		Response response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));	
	}

}
	
	