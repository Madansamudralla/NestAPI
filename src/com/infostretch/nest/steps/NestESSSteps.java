package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.infostretch.nest.providers.ESSEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.ws.Response;

public class NestESSSteps {

	@QAFTestStep(description = "user should get-basic-details")
	public void userShouldGetBasicDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", "113");//ConfigurationManager.getBundle().getPropertyValue("emp_id");
		ClientUtils.getWebResource(ESSEndPoints.GET_BASIC_DETAILS).type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		if(CommonUtils.validateResult(response)) {
			Reporter.log(response.getMessageBody(), MessageTypes.Info);
		}
	}

	@QAFTestStep(description = "user should get-personal-details")
	public void userShouldGetPersonalDetails() {
		JSONObject obj = new JSONObject();
		obj.put("token", TokenUtils.getTokenAsStr());
		obj.put("emp_number", "113");//ConfigurationManager.getBundle().getPropertyValue("emp_id");
		ClientUtils.getWebResource(ESSEndPoints.GET_PERSONAL_DETAILS).type(MediaType.APPLICATION_JSON).post(obj.toString());
		Response response = ClientUtils.getResponse();
		Reporter.log("Emp Number "+CommonUtils.validateResultParameter(response, "emp_number"));
	}
}
