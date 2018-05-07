package com.infostretch.nest.providers;

public interface LeaveEndPoints {
	public final static String GET_LEAVE_REASONS = "/get-leave-reasons";
	public final static String GET_LEAVE_TYPES_BY_COUNTRY = "/get-leave-types-by-country";
	public final static String GET_CURRENT_LEAVE_PERIOD = "/get-current-leave-period";
	public final static String GET_LEAVE_BALANCES = "/get-leave-balances";
	public final static String GET_ALL_HOLIDAY_DATELIST = "/get-all-holiday-datelist";
	public final static String GET_ALL_LEAVE_STATUS = "/get-all-leave-status";
	public final static String GET_USER_LEAVE_LIST = "/get-my-leave-list";
	public final static String GET_USER_ALL_LEAVE_LIST = "/get-all-my-leave-list";
	public final static String GET_LOCATION_HOLIDAY_LIST = "/get-location-holidays-list";
	public final static String GET_LIST_OF_LEAVE_TYPES = "/get-list-of-leave-types";
	public final static String REQUEST_LEAVE = "/request-leave";
	public final static String GET_TEAMS_LEAVE_BALANCE_LISTS = "/get-teams-leave-balance-lists";
	public final static String GET_TEAMS_LEAVE_LISTS = "/get-teams-leave-list";
	public final static String REQUEST_SPECIAL_LEAVE = "/request-special-leave";
	public final static String GET_LIST_OF_GROUPS = "/get-list-of-groups";
	public final static String GET_LEAVE_EMPLOYEE_BALANCE_ON_LEAVE_TYPE ="/get-leave-employee-balance-on-leave-type";
	public final static String GET_LOCATION_FLOATING_HOLIDAY_LIST = "/get-location-floating-holidays-list";
	public final static String ADD_HOLIDAYS = "/add-holidays";
	public final static String ADD_FLOATING_HOLIDAYS = "/add-floating-holidays";
	public final static String DELETE_HOLIDAYS="/delete-holidays";
	public final static String DELETE_FLOATING_HOLIDAYS ="/delete-floating-holidays";
	public final static String POST_ASSIGN_LEAVE ="/post-assign-leave";
	public final static String GET_EMPLOYEE_LIST_FOR_LEAVE_BALANCE="/get-employee-list-for-leave-balance";
	public final static String GET_TOTAL_LEAVE_PERIOD_IDS="/get-total-leave-period-ids";
	public final static String CANCEL_LEAVE_EMPLOYEE_REQUEST="/cancel-leave-employee-request";
	public final static String GET_TEAMS_PENDING_LEAVE_REQUEST="/get-teams-pending-leave-requests";
	public final static String GET_TEAMS_PLANNED_LEAVES_LIST="/get-teams-planned-leaves-list";

}