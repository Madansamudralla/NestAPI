package com.infostretch.nest.steps;

import javax.ws.rs.core.MediaType;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infostretch.nest.bean.ReimbursementBean;
import com.infostretch.nest.providers.ReimbursementEndPoints;
import com.infostretch.nest.utils.ClientUtils;
import com.infostretch.nest.utils.CommonUtils;
import com.infostretch.nest.utils.TokenUtils;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;

public class NestReimbursementSteps {
	int index;
	JSONObject jsonObject, jsonObject1;
	Response response;
	JsonObject jsonObjectResult;
	JsonArray jsonArrayResult;
	ReimbursementBean reimbursementBean = new ReimbursementBean();

	@QAFTestStep(description = "user should get all his expense list")
	public void userShouldGetAllHisExpenseList() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_ALL_HIS_EXPENSE_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("reimbursement_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("department_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get expense details list")
	public void userShouldGetExpenseDetailsList() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("reimbursement_id", reimbursementBean.getReimbursement_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_DETAILS_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "reimbursement_id");
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("reimbursementotherdata").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("reimbursement_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("title").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("requested_date").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("payment_date").toString(), Matchers.notNullValue());
		}
  }

	@QAFTestStep(description = "user should get expense travel request")
	public void userShouldGetExpenseTravelRequest() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_TRVAVEL_REQUEST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
	}

	@QAFTestStep(description = "user should get expense currency list")
	public void userShouldGetExpenseCurrencyList() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_CURRENCY_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("currency_type_id").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("base_currency").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("symbol").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("title").toString(), Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get expense user project list")
	public void userShouldGetExpenseUserProjectList() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_EXPENSE_USER_PROJECT_LIST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should get list of location category")
	public void userShouldGetListOfLocationCategory() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_LIST_OF_LOCATION_CATEGORY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);
		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("category_description").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("updated_date").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("location").toString(), Matchers.notNullValue());
		}

	}
	@QAFTestStep(description = "user should get netsuite expense category list")
	public void userShouldGetNetsuiteExpenseCategoryList() {
		jsonObject = new JSONObject();
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(ReimbursementEndPoints.GET_NETSUITE_EXPENSE_CATEGOTY_LIST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonArrayResult = jsonObjectResult.get("details").getAsJsonArray();

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("netsuite_category_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("netsuite_category_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("netsuite_category_title").toString(),
					Matchers.notNullValue());
		}
	}

	@QAFTestStep(description = "user should edit netsuite expense category status")
	public void userShouldEditNetsuiteExpenseCategoryStatus() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("netsuite_category_id",
				reimbursementBean.getNetsuite_category_id());
		jsonObject.put("status", reimbursementBean.getStatus());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(
						ReimbursementEndPoints.EDIT_NETSUITE_EXPENSE_CATEGORY_STATUS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"netsuite_category_id");
	}
	@QAFTestStep(description = "user should addedit netsuite expense category")
	public void userShouldAddeditNetsuiteExpenseCategory() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("netsuite_category_id",
				reimbursementBean.getNetsuite_category_id());
		jsonObject1.put("netsuite_category_title",
				reimbursementBean.getNetsuite_category_title());
		jsonObject1.put("internal_id", reimbursementBean.getInternal_id());
		jsonObject1.put("created_date", reimbursementBean.getCreated_date());
		jsonObject1.put("updated_date", reimbursementBean.getUpdated_date());
		jsonObject1.put("status", reimbursementBean.getStatus());
		jsonObject.put("categoryDetails", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(ReimbursementEndPoints.AddEDIT_NETSUITE_EXPENSE_CATEGORY)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"netsuite_category_id");

	}
	@QAFTestStep(description = "user should delete netsuite expense category")
	public void userShouldDeleteNetsuiteExpenseCategory() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("netsuite_category_id",
				reimbursementBean.getNetsuite_category_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils
				.getWebResource(ReimbursementEndPoints.DELETE_NETSUITE_EXPENSE_CATEGORY)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "action_message");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"netsuite_category_id");
	}
	@QAFTestStep(description = "user should edit expense category status")
	public void userShouldEditExpenseCategoryStatus() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("id", reimbursementBean.getId());
		jsonObject.put("status", reimbursementBean.getStatus());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ReimbursementEndPoints.EDIT_EXPENSE_CATEGORY_STATUS)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "category_id");
	}
	@QAFTestStep(description = "user should list expense category")
	public void userShouldListExpenseCategory() {
		ClientUtils.getWebResource(ReimbursementEndPoints.LIST_EXPENSE_CATEGORY)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("category_description").toString(),
					Matchers.notNullValue());
		}
	}
	@QAFTestStep(description = "user should list expense category by id")
	public void userShouldListExpenseCategoryById() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("id", reimbursementBean.getId());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ReimbursementEndPoints.LIST_EXPENSE_CATEGORY_BY_ID)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "updated_date");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "category_title");
	}
	@QAFTestStep(description = "user should get location list by finance")
	public void userShouldGetLocationListByFinance() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_LOCATION_LIST_BY_FINANCE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject()).get("Id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("Location").toString(), Matchers.notNullValue());
		}
	}
	@QAFTestStep(description = "user should add expense category")
	public void userShouldAddExpenseCategory() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject1 = new JSONObject();
		jsonObject1.put("category_title", reimbursementBean.getCategory_title());
		jsonObject1.put("category_description",
				reimbursementBean.getCategory_description());
		jsonObject1.put("actual", reimbursementBean.getActual());
		jsonObject1.put("status", reimbursementBean.getStatus());
		jsonObject1.put("max_elligible_amount",
				reimbursementBean.getMax_elligible_amount());
		jsonObject.put("categoryDetails", jsonObject1);
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ReimbursementEndPoints.ADD_EXPENSE_CATEGORY)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "category_id");
	}
	@QAFTestStep(description = "user should get view cash advance request")
	public void userShouldGetViewCashAdvanceRequest() {
		reimbursementBean.fillRandomData();
		jsonObject = new JSONObject();
		jsonObject.put("employee_id", reimbursementBean.getEmployee_id());
		jsonObject.put("emp_number", reimbursementBean.getEmp_number());
		jsonObject.put("trvl_travel_request_id",
				reimbursementBean.getTrvl_travel_request_id());
		jsonObject.put("token", TokenUtils.getTokenAsStr());
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_VIEW_CASH_ADVANCE_REQUEST)
				.type(MediaType.APPLICATION_JSON).post(jsonObject.toString());
		response = ClientUtils.getResponse();
		jsonObjectResult = CommonUtils.getValidateResultObject(response);
		jsonObjectResult = jsonObjectResult.get("detail").getAsJsonObject();
		CommonUtils.validateParameterInJsonObject(jsonObjectResult,
				"trvl_travel_request_id");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "emp_number");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "updated_date");
		CommonUtils.validateParameterInJsonObject(jsonObjectResult, "requested_name");
	}
	@QAFTestStep(description = "user should get all employee list for expense")
	public void userShouldGetAllEmployeeListForExpense() {
		ClientUtils
				.getWebResource(ReimbursementEndPoints.GET_ALL_EMPLOYEE_LIST_FOR_EXPENSE)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("display_name").toString(), Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
		}
	}
	@QAFTestStep(description = "user should get cash advance request")
	public void userShouldGetCashAdvanceRequest() {
		ClientUtils.getWebResource(ReimbursementEndPoints.GET_CASH_ADVANCE_REQUEST)
				.entity(TokenUtils.getTokenAsJsonStr()).type(MediaType.APPLICATION_JSON)
				.post();
		response = ClientUtils.getResponse();
		jsonArrayResult = CommonUtils.getValidatedResultArray(response);

		for (index = 0; index <= jsonArrayResult.size() - 1; index++) {
			Validator.verifyThat(
					(jsonArrayResult.get(index).getAsJsonObject())
							.get("trvl_travel_request_id").toString(),
					Matchers.notNullValue());
			Validator.verifyThat((jsonArrayResult.get(index).getAsJsonObject())
					.get("emp_number").toString(), Matchers.notNullValue());
		}
	}
}