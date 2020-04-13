package com.example.ev.SoKhop.Api;

public class APIConstants {

    public static final int CONECTION_TIMEOUT = 10 * 1000;
    public static final int WRITE_TIMEOUT = 10 * 1000;
    public static final int READ_TIMEOUT = 30 * 1000;

    public static String API_VERSION = "1";

    //Change server release or staging
    public static final boolean RELEASE = false;
//
    public static final String BASE_URL = "http://test.sokhop.vn/auth/";
    public static final String BASE_URL_UPLOAD = "http://test.sokhop.vn/file/";
    public static final String BASE_URL_PROFILE = "http://test.sokhop.vn/profile/";
    public static final String BASE_URL_MATCHING = "http://test.sokhop.vn/search/";
    public static final String BASE_URL_NOTIFICATION = "http://test.sokhop.vn/notification/";

//    public static final String BASE_URL = "http://192.168.1.192:3000/";
//    public static final String BASE_URL_UPLOAD = "http://192.168.1.192:3004/";
//    public static final String BASE_URL_PROFILE = "http://192.168.1.192:3001/";
//    public static final String BASE_URL_MATCHING = "http://192.168.1.192:3002/";
//    public static final String BASE_URL_NOTIFICATION = "http://192.168.1.192:3003/";

    public static final String REGISTER_URL = BASE_URL+"api/v1/users/register";
    public static final String LOGIN_EMAIL_URL = BASE_URL+"api/v1/users/login";
    public static final String LOGIN_BY_GOOGLE_URL = BASE_URL+"api/v1/users/login_with_google";
    public static final String FORGOT_PASSWORD_URL = BASE_URL+"api/v1/users/reset_password";
    public static final String CONFIRM_FORGOT_PASSWORD_URL = BASE_URL+"api/v1/users/confirm_reset_password";
    public static final String CHANGE_PASSWORD_URL = BASE_URL+"api/v1/users/change_password";
    public static final String LOGOUT_URL = BASE_URL+"api/v1/users/logout";
    public static final String LOGIN_FACEBOOK_URL = BASE_URL+"api/v1/users/login_with_facebook";
    public static final String CHECK_TOKEN_URL = BASE_URL+"api/v1/users/getCurrentUserByToken?";
    public static final String UPLOAD_IMAGE_URL = BASE_URL_UPLOAD+"api/v1/files/upload";
    public static final String UPDATE_AVATAR_URL = BASE_URL_PROFILE+"api/v1/profiles/basic/avatar";
    public static final String UPDATE_COVER_URL = BASE_URL_PROFILE+"api/v1/profiles/basic/cover";
    public static final String UPDATE_EDUCATION_URL = BASE_URL_PROFILE+"api/v1/profiles/education/create_education";
    public static final String GET_EDUCATION_URL = BASE_URL_PROFILE+"api/v1/profiles/education/get_education";

    public static final String GET_ALL_CANDIDATE_MATCHING_JOB_URL = BASE_URL_MATCHING+"api/v1/profile/matching";
    public static final String GET_ALL_MATCHING_JOB_URL = BASE_URL_MATCHING+"api/v1/jobs/matching";
    public static final String GET_ALL_SAVED_JOB_URL = BASE_URL_PROFILE+"api/v1/user/candidate_job/get_all_job_saved";
    public static final String GET_ALL_APPLY_JOB_URL = BASE_URL_PROFILE+"api/v1/user/candidate_job/get_all_job_apply";
    public static final String UPDATE_EXPERICES_URL = BASE_URL_PROFILE+"api/v1/profiles/user/experiences/create";
    public static final String GET_EXPERICES_URL = BASE_URL_PROFILE+"api/v1/profiles/user/experiences/get_experiences";
    public static final String GET_POSITION_TIMELINE_URL = BASE_URL_PROFILE+"api/v1/profiles/user/experiences/get_user_position_timeline";
    public static final String GET_POSITION_JOB_URL = BASE_URL_PROFILE+"api/v1/profiles/positions";
    public static final String GET_LANGUAGE_URL = BASE_URL_PROFILE+"api/v1/profiles/languages";
    public static final String GET_LOCATION_URL = BASE_URL_PROFILE+"api/v1/profiles/locations";
    public static final String ADD_NEW_RECRUITMENT_URL = BASE_URL_PROFILE+"api/v1/profiles/recruitment/jobs/new";
    public static final String GET_JOB_EXPECT_URL = BASE_URL_PROFILE+"api/v1/profiles/user/job_expect/details";
    public static final String ADD_NEW_JOB_EXPECT_URL = BASE_URL_PROFILE+"api/v1/profiles/user/job_expect/create";

    public static final String UPDATE_COMPANY_AVATAR_URL = BASE_URL_PROFILE+"api/v1/profiles/organization/basic/avatar";
    public static final String UPDATE_COMPANY_COVER_URL = BASE_URL_PROFILE+"api/v1/profiles/organization/basic/cover";
    public static final String GET_PROFILE_USER_URL = BASE_URL_PROFILE+"api/v1/profiles/basic";
    public static final String GET_PROFILE_COMPANY_URL = BASE_URL_PROFILE+"api/v1/profiles/organization/basic";
    public static final String UPDATE_PROFILE_USER_URL = BASE_URL_PROFILE+"api/v1/profiles/basic/update?access_token=";
    public static final String UPDATE_PROFILE_COMPANY_URL = BASE_URL_PROFILE+"api/v1/profiles/organization/basic/update?access_token=";
    public static final String GET_INFO_EXTENDS_URL = BASE_URL_PROFILE+"api/v1/profiles/basic/list/user_infor_extends";
    public static final String GET_EXCLUDES_JOB_URL = BASE_URL_PROFILE+"api/v1/profiles/job_excludes";

    public static final String GET_IMAGE_ALBUM_URL = BASE_URL_UPLOAD+"api/v1/files/albums";
    public static final String ADD_NEW_ALBUM_URL = BASE_URL_UPLOAD+"api/v1/files/albums";
    public static final String DELETE_ALBUM_URL = BASE_URL_UPLOAD+"api/v1/files/albums";
    public static final String DELETE_IMAGE_URL = BASE_URL_UPLOAD+"api/v1/files/images";

    public static final String GET_RECRUITMENT_JOB_URL = BASE_URL_PROFILE+"api/v1/profiles/recruitment/jobs";
    public static final String GET_RECRUITMENT_BY_JOB_ID_URL = BASE_URL_PROFILE+"api/v1/profiles/recruitment/jobs";
    public static final String UPDATE_RECRUITMENT_URL = BASE_URL_PROFILE+"api/v1/profiles/recruitment/jobs/update?access_token=";
    public static final String DELETE_RECRUITMENT_URL = BASE_URL_PROFILE+"api/v1/profiles/recruitment/jobs/update_active?access_token=";
    public static final String UPDATE_RECRUITMENT_STATUS_URL = BASE_URL_PROFILE+"api/v1/profiles/recruitment/jobs/update_status?access_token=";
    public static final String UPDATE_EDUCATION_FIX_URL = BASE_URL_PROFILE+"api/v1/profiles/education/update_education?access_token=";
    public static final String UPDATE_EXP_FIX_URL = BASE_URL_PROFILE+"api/v1/profiles/user/experiences/update?access_token=";
    public static final String UPDATE_EXPECT_JOB_FIX_URL = BASE_URL_PROFILE+"api/v1/profiles/user/job_expect/update?access_token=";
    public static final String UPDATE_EMAIL_URL = BASE_URL_PROFILE+"api/v1/profiles/basic/update_email?access_token=";
    public static final String UPDATE_SAVE_JOB_URL = BASE_URL_PROFILE+"api/v1/user/candidate_job/save_job?access_token=";
    public static final String UPDATE_APPLY_JOB_URL = BASE_URL_PROFILE+"api/v1/user/candidate_job/apply_job?access_token=";
    public static final String GET_RECRUITER_URL = BASE_URL_PROFILE+"api/v1/user/candidate_job/get_all_candidate_by_recruiter";
    public static final String GET_CANDICATE_BY_STATUS_URL = BASE_URL_PROFILE+"api/v1/user/candidate_job/get_all_candidate_by_status";

    public static final String GET_SEARCH_JOBS_URL = BASE_URL_MATCHING+"api/v1/jobs/get_jobs";
    public static final String GET_SEARCH_USERS_URL = BASE_URL_MATCHING+"api/v1/candidate/search";
    public static final String GET_SEARCH_ACCOUNTS_URL = BASE_URL_MATCHING+"api/v1/profile";
    public static final String UPDATE_SAVED_USERS_URL = BASE_URL_PROFILE+"api/v1/user/candidate_job/save_candidate_by_recruiter?access_token=";

    public static final String GET_NOTIFICATION_URL = BASE_URL_NOTIFICATION+"api/v1/notification/get_notification_for_user";
    public static final String GET_COUNT_NOTIFICATION_URL = BASE_URL_NOTIFICATION+"api/v1/notification/unread/count";
    public static final String UPDATE_IS_READ_NOTIFICATION_URL = BASE_URL_NOTIFICATION+"api/v1/notification/mark_notification_is_read?access_token=";
    public static final String UPDATE_TYPE_NOTIFICATION_URL = BASE_URL_NOTIFICATION+"api/v1/notification/update_notifications_type?access_token=";
    public static final String DELETE_NOTIFICATION_URL = BASE_URL_NOTIFICATION+"api/v1/notification/delete_notifications_user";
    public static final String POST_NOTIFICATION_REGISTER_DEVICE_URL = BASE_URL_NOTIFICATION+"api/v1/notification/register_device";
    public static final String DELETE_ONESIGNAL_ID_URL = BASE_URL_NOTIFICATION+"api/v1/notification/unsubscribe";
}