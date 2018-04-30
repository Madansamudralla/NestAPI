package com.infostretch.nest.providers;

public interface TrainingEndPoints {
	public final static String Training_Calendar = "/get-training-calendar";
	public final static String Training_Detail = "/view-training-detail";
	public static final String I_AM_Attending = "/get-i-am-attending";
	public static final String I_HAVE_ATTENDED = "/get-i-have-attended";
	public static final String I_HAVE_TAUGHT = "/get-i-have-taught";
	public static final String GET_I_AM_TEACHING="/get-i-am-teaching";
	public static final String GET_FEEDBACK_QUESTIONS_LIST="/get-feedback-questions-list";
	public static final String GET_TRAINER_LIST_DD="/get-trainer-list-dd";	
	public static final String GET_ACTIVE_VENUE_LIST_DD="/get-active-venue-list-dd";
	public static final String GET_ACTIVE_BUSINESS_UNIT="/get-active-business-unit";
	public static final String GET_TRAINING_MEMBERS="/get-training-members";
	public static final String GET_EDIT_TRAINING_MEMBERS="/add-edit-traning-members";
	public static final String GET_EMPLIST_FOR_TRAINING_NOMINATION_DD="/get-emplist-for-training-nomination-dd";
	public static final String EXPORT_TRAINING_FEEDBACK_REPORT="/export-training-feedback-report";
}
