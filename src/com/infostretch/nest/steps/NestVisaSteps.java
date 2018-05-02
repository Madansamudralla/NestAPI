package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infostretch.nest.bean.VisaBean;
import com.infostretch.nest.providers.VisaEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestVisaSteps {
	JSONObject jsonObject, jsonObject1;
	Response response;
	JsonObject result, responseBody;
	JsonArray results;
	int index;
	VisaBean visaBean = new VisaBean();

	@QAFTestStep(description = "user should get visa country list")
	public void UserShouldGetVisaCountryList() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_COUNTRY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		Response response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_country_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("country_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("created_date").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("created_by_emp_number").toString(), Matchers.notNullValue());
		}

	}
	@QAFTestStep(description = "user should get supervisor name")
	public void UserShouldGetSupervisorName() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_SUPERVISOR_NAME)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(0).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());

		}
	}

	@QAFTestStep(description = "user should get visa request list for employee")
	public void userShouldGetVisaRequestListForEmployee() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", visaBean.getInitiated_from());
		jsonObject1.put("initiated_to", visaBean.getInitiated_to());
		jsonObject.put("search", jsonObject1);
		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_EMPLOYEE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("user_type").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "isAdminAuthority");
		CommonUtils.validateParameterInJsonObject(result, "isApprovalAdmin");

	}

	@QAFTestStep(description = "user should get visa type for dd")
	public void UserShouldGetVisaTypeForDd() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_TYPE_FOR_DD)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		Reporter.log(response.getMessageBody(), MessageTypes.Info);
		Validator.assertThat(response.getStatus().getStatusCode(), Matchers.equalTo(200));
  }

	@QAFTestStep(description = "user should get all employee list of expense")
	public void UserShouldGetAllEmployeeListOfExpense() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_ALL_EMPLOYEE_LIST_EXPENSE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("display_name").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			}
	}
	
	@QAFTestStep(description = "user should get visa request list for approvingauth")
	public void userShouldGetVisaRequestListForApprovingauth() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_APPROVING_AUTH)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("reporting_mngr").toString(), Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should get export report for visa request")
	public void userShouldGetExportReportForVisaRequest() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", visaBean.getInitiated_from());
		jsonObject1.put("initiated_to", visaBean.getInitiated_to());
		jsonObject.put("search", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_EXPORT_REPORT_FOR_VISA_REQUEST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "Download_URL");
	}
	
	@QAFTestStep(description = "user should get visa request list for hr admin")
	public void userShouldGetVisaRequestListForHrAdmin() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", visaBean.getInitiated_from());
		jsonObject1.put("initiated_to", visaBean.getInitiated_from());
		jsonObject.put("search", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_HR_ADMIN)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("initiated_date").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should get visa types")
	public void userShouldGetVisaTypes() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_TYPES)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		result = results.get(0).getAsJsonObject();
		results = result.get("visa_type").getAsJsonArray();

		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("visa_type_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_type_name").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("country_name").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_country_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_country_id").toString(), Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should add new country for visa")
	public void userShouldAddNewCountryForVisa() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("country_id", visaBean.getCountry_id());
		jsonObject1.put("status", visaBean.getStatus());
		jsonObject1.put("country_name", visaBean.getCountry_name());
		jsonObject.put("visaCountry", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_ADD_NEW_COUNTRY)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
	}
	
	@QAFTestStep(description = "user should set visa country status")
	public void userShouldSetVisaCountryStatus() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("visa_country_id", visaBean.getVisa_country_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_SET_COUNTRY_STATUS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
	}

	@QAFTestStep(description = "user should get checklist details for travel visa request")
	public void userShouldGetChecklistDetailsForTravelVisaRequest() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("visa_trvl_request_id", visaBean.getVisa_trvl_request_id());
		jsonObject.put("visa_trvl_type_id", visaBean.getVisa_trvl_type_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(
						VisaEndPoints.VISA_CHECKLIST_DETAILS_FOR_TRAVEL_VISA_REQUEST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "travel_data_submitted");
		CommonUtils.validateParameterInJsonObject(result, "TotalTravelChecklist");
	}
	
	@QAFTestStep(description = "user should add edit visa types")
	public void userShouldAddEditVisaTypes() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("visa_type_id", visaBean.getVisa_type_id());
		jsonObject1.put("visa_type_name", visaBean.getVisa_type_name());
		jsonObject1.put("visa_country_id", visaBean.getVisa_country_id());
		jsonObject.put("visaType", "jsonObject1");
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_ADD_EDIT_VISA_TYPES)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
	}
	
	@QAFTestStep(description = "user should delete-visa-type")
	public void userShouldDeleteVisaType() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("visa_type_id", visaBean.getVisa_type_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_DELETE_VISA_TYPE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
	}
	
	@QAFTestStep(description = "user should get visa request list for manager")
	public void userShouldGetVisaRequestListForManager() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", visaBean.getInitiated_from());
		jsonObject1.put("initiated_to", visaBean.getInitiated_to());
		jsonObject.put("search", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_LIST_FOR_MANAGER)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_request_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("initiated_date").toString(), Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should get visa trvl list for admins")
	public void userShouldGetVisaTrvlListForAdmins() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", visaBean.getInitiated_from());
		jsonObject1.put("initiated_to", visaBean.getInitiated_to());
		jsonObject.put("search", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_TRVL_LIST_FOR_ADMINS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_trvl_req_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("requester_emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_trvl_client_name").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_trvl_duration").toString(), Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should get delivery managers")
	public void userShouldGetDeliveryManagers() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_DILIVERY_MANAGERS)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		 response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("emp_number").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(results.get(index).getAsJsonObject()).get("ename").toString(),
					Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should visa travel request details")
	public void userShouldVisaTravelRequestDetails() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("visa_trvl_request_id", visaBean.getVisa_trvl_request_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_TRAVEL_REQUEST_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "visa_trvl_initiated_date");
		CommonUtils.validateParameterInJsonObject(result, "approving_date");
		CommonUtils.validateParameterInJsonObject(result, "status_value");
		CommonUtils.validateParameterInJsonObject(result, "visa_type_name");
		result = result.get("basic_details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "ename");
		CommonUtils.validateParameterInJsonObject(result, "employee_id");
	}
	
	@QAFTestStep(description = "user should get visa trvl list for employee")
	public void userShouldGetVisaTrvlListForEmployee() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", visaBean.getInitiated_from());
		jsonObject1.put("initiated_to", visaBean.getInitiated_to());
		jsonObject.put("search", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_TRVL_LIST_FOR_EMPLOYEE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		responseBody =
				new JsonParser().parse(response.getMessageBody()).getAsJsonObject();
		result = responseBody.get("response").getAsJsonObject().get("results")
				.getAsJsonObject().get("user_type").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "isApprovalAdmin");
	}	
	
	@QAFTestStep(description = "user should get visa trvl list for manager")
	public void userShouldGetVisaTrvlListForManager() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("initiated_from", visaBean.getInitiated_from());
		jsonObject1.put("initiated_to", visaBean.getInitiated_to());
		jsonObject.put("search", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_TRVL_LIST_FOR_MANAGER)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_trvl_req_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("visa_trvl_client_name").toString(), Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should visa request details")
	public void userShouldVisaRequestDetails() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("visa_request_id", visaBean.getVisa_request_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_REQUEST_DETAILS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		result = result.get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "visa_request_id");
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "ename");
		}
		
	@QAFTestStep(description="user should get account managers")
	public void userShouldGetAccountManagers() {
			ClientUtils.getWebResource(VisaEndPoints.VISA_GET_ACCOUNT_MANAGERS)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		results = result.get("details").getAsJsonArray();
		
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("ename").toString(), Matchers.notNullValue());
		}
	}
	
	@QAFTestStep(description = "user should approve reject visa request")
	public void userShouldApproveRejectVisaRequest() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("status", visaBean.getStatus());
		jsonObject1.put("comments", visaBean.getComments());
		jsonObject1.put("visa_request_id", visaBean.getVisa_request_id());
		jsonObject.put("approveDetails", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_APPROVE_REJECT_VISA_REQUEST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(result, "action_message");
		CommonUtils.validateParameterInJsonObject(result, "status");
		}

	@QAFTestStep(description = "user should get duration for visa")
	public void userShouldGetDurationForVisa() {
		ClientUtils.getWebResource(VisaEndPoints.VISA_GET_DURATION)
		.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
		.post();
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		if (results.toString().contains("Day(s)") && results.toString().contains("Week(s)")
				&& results.toString().contains("Month(s)")&& results.toString().contains("Year(s)")) {
			Reporter.log("Verified");
		
		}
	}
	
	@QAFTestStep(description = "user should get employees under manager dd")
	public void userShouldGetEmployeesUnderManagerDd() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("is_req", visaBean.getIs_req());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_GET_EMPLOYEES_UNDER_MANAGER_DD)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		results = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= results.size() - 1; index++) {
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
			Validator.verifyThat((results.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			}
		}
	
	@QAFTestStep(description = "user should get basic employee details on initiate")
	public void userShouldGetBasicEmployeeDetailsOnInitiate() {
		visaBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("det_emp_number", visaBean.getDet_emp_number());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(VisaEndPoints.VISA_GET_BASIC_EMPLOYEE_DETAILS_ON_INITIATE)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		result = CommonUtils.getValidateResultObject(response);
		result = result.get("details").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(result, "emp_initial");
		CommonUtils.validateParameterInJsonObject(result, "emp_number");
		CommonUtils.validateParameterInJsonObject(result, "loc_code");
		CommonUtils.validateParameterInJsonObject(result, "loc_city");
	}
}



