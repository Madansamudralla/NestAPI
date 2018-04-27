package com.infostretch.nest.providers;

public interface VisaEndPoints {
	public final static String VISA_COUNTRY_LIST = "/get-visa-country-list";
	public final static String VISA_REQUEST_LIST_FOR_EMPLOYEE = "/get-visa-request-list-for-employee";
	public final static String VISA_MENU_URL= "/get-menu-urls";
	public final static String VISA_SUPERVISOR_NAME= "/get-Supervisors-Name";
	public final static String VISA_TYPE_FOR_DD= "/get-visa-type-for-dd";
	public final static String VISA_ALL_EMPLOYEE_LIST_EXPENSE= "/get-all-employee-list-for-expense";
	public final static String VISA_REQUEST_LIST_FOR_APPROVING_AUTH= "/get-visa-request-list-for-approving-auth";
	public final static String VISA_EXPORT_REPORT_FOR_VISA_REQUEST= "/get-export-report-for-visa-request";
	public final static String VISA_REQUEST_LIST_FOR_HR_ADMIN= "/get-visa-request-list-for-hr-admin";
	public final static String VISA_TYPES ="/get-visa-types";
	public final static String VISA_ADD_NEW_COUNTRY= "/add-new-country-for-visa";
	public final static String VISA_SET_COUNTRY_STATUS= "/set-visa-country-status";
	public final static String VISA_CHECKLIST_DETAILS_FOR_TRAVEL_VISA_REQUEST= "/get-checklist-details-for-travel-visa-request";
	public final static String VISA_ADD_EDIT_VISA_TYPES= "add-edit-visa-types";
}