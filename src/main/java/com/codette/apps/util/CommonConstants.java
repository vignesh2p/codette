package com.codette.apps.util;

public class CommonConstants {

	//Set session variable
	public static final String SESSION_TOKEN = "_stk";
	public static final String SESSION_ORG_ID="_orgId";
	public static final String XORG_ID= "X-Org-ID";
	public static final String X_AUTH_TOKEN = "X-Auth-Token";
	
	public static final String LOCALE_EN_US="en-US";
	public static final String ISO_DATE_FORMAT="yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String DATE_FORMAT="yyyy-MM-dd";
	public static final String DATE_TIME= "yyyy-dd-MM";
	public static final String DATE_DD_MMMM_YYYY = "dd MMMM, yyyy";
	public static final String DATE_DD_MMMM_YYYY_HH_MM_AM = "dd MMMM, yyyy hh:mm a";
	public static final String TIMESTAMP_DD_MMMM_YYYY_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
	//Login variable
	public static final String TIME_FORMAT = "hh:mm a";
	public static final String LOGIN_PAGE = "index";
	
	//Organization URL
	public static final String ORGANIZATIONS_BASE_URL = "/organizations";
	public static final String USERS_BASE_URL = "/users";
	public static final String SESSION_BASE_URL = "/session";
	public static final String USER_BASE_URL = "/user";
	public static final String ORGANIZATION_BASE_URL = "/organization";
	public static final String LMS_BASE_URL = "/leaveManagement";
	
	//Session variable
	public static final String SESSION_PERSON_ID="_pId";
	public static final String SESSION_USER_ID = "userId";
	public static final String SESSION_FIRSTNAME="_fn";
	public static final String SESSION_LASTNAME="_ln";
	public static final String SESSION_USERROLE="_rn";
	public static final String SESSION_TIMEZONE="_tz";
	public static final String SESSION_USERNAME="_una";
	public static final String SLASH="/";
	
	
	//Json file name
	public final static String ROLE_BASED_NAVIGATION_MENU = "role-based-menu.json";
	public static final String RELATIONSHIP_TYPE = "relationshipType";
	public static final String RELATIONSHIPS_BASE_URL = "/relationships";
	public static final String TYPE_KEY = "typeKey";
	public static final String PERSONS_BASE_URL = "/persons";
	public static final String ROLE_TYPE = "roleType";
	public static final String ROLE_BASE_URL = "/role";
	public static final String STAFF_URL = "/staff";
	
	String toAddr = "viki19nesh@gmail.com";
	public static final String FROMADDRESS = "viki19nesh@gmail.com";

	// email subject
	public static final String  CREDENTIALS = "School Days Credentials";
	public static final String  USERNAME ="Your Login Id is "; 
	public static final String  PASSWORD ="Your password is "; 
	// email body
	public static final String FOOTER = "There you go.. You got an account in schooldays.. Let's understand details on how schooldays works, if"
			+ "you have any further doubts fee free to contact us -- By Support Team";
	public static final String CREATE_USERS_BASEURL = "/createUser";
	public static final String SESSION_EMAILADDRESS = "emailAddress";
	public static final String STATUS_PENDING = "PENDING";
	public static final String STATUS_DECLINED = "DECLINED";
	public static final String STATUS_APPROVED = "APPROVED";
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_T_STAFF = "TEACHING STAFF";
	public static final String ROLE_NT_STAFF = "NON TEACHING STAFF";
	public static final String STATUS_HISTORY = "HISTORY";
	public static final String CREATE_LEAVE_REQUEST = "/createRequest";
	public static final String UPDATE_REQUEST = "/updateRequest";
	public static final String EMAIL_VERIFICATION = "/email-verification";
	public static final String COMMON_BASE_URL = "/common-base";
	public static final String PASSWORD_RESET_SUBJECT = "School Days password reset";
	public static final String PASSWORD_RESET_CONTENT = "To log in and access your account, please click on the link below. http://192.168.1.3:8080/#/resetPassword "
			+ "You will now be able to view and modify your profile as required, check the status of your application and apply for jobs."
			+ "Yours sincerely,"
			+ "School Name";
	public static final String STUDENTS_BASE_URL = "/students";
	public static final String STANDARD_URL = "/standard";
	public static final String SECTION_URL = "/section";
	public static final String CLASSES_URL = "/classes";
	public static final String MALE = "MALE";
	public static final String FEMALE = "FEMALE";
	public static final String GENDER = "GENDER";
	public static final String ROLE = "ROLE";
	public static final String RELIGION = "RELIGION";
	public static final String COMMUNITY = "COMMUNITY";
	public static final String DESIGNATION = "DESIGNATION";
	public static final String LIST_TYPE = "type";
	public static final String IS_DELETED = "0";
	public static final String GET_USER = "/getUser";
	public static final String UPDATE_USER = "/updateUser";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
	public static final String PENDING = "PENDING";
	public static final String STANDARD = "STANDARD";
	public static final String SECTION = "SECTION";
	public static final String RELATION = "STUDENT_RELATION";
	public static final String CREATE = "/create";
	public static final String STAFF_CLASSES_URL = "/staffClasses";
	public static final String TEACHING_STAFF = "TEACHING STAFF";
}
