package com.infostretch.nest.providers;

public interface TrainingEndPoints {
	public static final String GET_FEEDBACK_QUESTIONS_LIST =
			"/get-feedback-questions-list";
	public static final String POST_TRAINING_FEEDBACK = "/post-training-feedback";

	public final static String Training_Calendar = "/get-training-calendar";
	public final static String Training_Detail = "/view-training-detail";
	public static final String I_AM_Attending = "/get-i-am-attending";
	public static final String I_HAVE_ATTENDED = "/get-i-have-attended";
	public static final String I_HAVE_TAUGHT = "/get-i-have-taught";
	public static final String GET_I_AM_TEACHING = "/get-i-am-teaching";
	public static final String GET_TRAINER_LIST_DD = "/get-trainer-list-dd";
	public static final String GET_ACTIVE_VENUE_LIST_DD = "/get-active-venue-list-dd";
	public static final String GET_ACTIVE_BUSINESS_UNIT = "/get-active-business-unit";
	public static final String GET_TRAINING_MEMBERS = "/get-training-members";
	public static final String GET_EDIT_TRAINING_MEMBERS = "/add-edit-traning-members";
	public static final String GET_EMPLIST_FOR_TRAINING_NOMINATION_DD =
			"/get-emplist-for-training-nomination-dd";
	public static final String EXPORT_TRAINING_FEEDBACK_REPORT =
			"/export-training-feedback-report";
	public static final String GET_COURSE_LIST = "/get-course-list";
	public static final String GET_STATISTICS = "/get-statistics";
	public static final String GET_TRAINING_TYPE_DD = "/get-training-type-dd";
	public static final String GET_INDIVIDUAL_EMP_STATISTICS =
			"/get-individual-emp-statistics";
	public static final String GET_VENUE_LIST = "/get-venue-list";
	public static final String ADD_EDIT_VENUE = "/add-edit-venue";
	public static final String GET_TRAINING_DATES_DD = "/get-training-dates-dd";
	public static final String GET_TRAINING_MEMBERS_FOR_ATTENDENCE =
			"/get-training-members-for-attendance";
	public static final String GET_REGISTERED_EMPLOYEE_LIST_DD =
			"/get-registered-employee-list-dd";
	public final static String EDIT_VENUE_STATUS = "/edit-venue-status";
	public final static String GET_TRAINING_LIST_DD = "/get-training-list-dd";
	public final static String GET_SUMMARY_REPORT = "/get-summary-report";
	public final static String EXPORT_SUMMARY_REPORT = "/export-summary-report";
	public final static String DELETE_VENUE = "/delete-venue";

	public final static String GET_FEEDBACKS_DETAILS = "/get-feedbacks-details";
	public final static String ADD_EDIT_COURSE_LIST = "/add-edit-course-list";

	public final static String GET_DETAILED_REPORT = "/get-detailed-report";
	public final static String GET_CUMULATIVE_FEEDBACK_REPORT =
			"/get-cumulative-feedback-report";

}
