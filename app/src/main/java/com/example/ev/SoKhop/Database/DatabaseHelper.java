package com.example.ev.SoKhop.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ev.SoKhop.Model.CandidateJobModel;
import com.example.ev.SoKhop.Model.EducationModel;
import com.example.ev.SoKhop.Model.ExcludesModel;
import com.example.ev.SoKhop.Model.ExpectJobModel;
import com.example.ev.SoKhop.Model.ExperiencesModel;
import com.example.ev.SoKhop.Model.Image;
import com.example.ev.SoKhop.Model.LanguageModel;
import com.example.ev.SoKhop.Model.LocationModel;
import com.example.ev.SoKhop.Model.NotificationModel;
import com.example.ev.SoKhop.Model.PositionJobModel;
import com.example.ev.SoKhop.Model.PositionTimelineModel;
import com.example.ev.SoKhop.Model.QuanLyUngVienModel;
import com.example.ev.SoKhop.Model.RecruiterModel;
import com.example.ev.SoKhop.Model.RecruitmentModel;
import com.example.ev.SoKhop.Model.SearchJobModel;
import com.example.ev.SoKhop.Model.SoKhopModel;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by hiep on 10/12/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "image.sqlite";
    private static final String TABLE_WORD = "image";
    private String ImageLink;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTablePoint(db);
        createTableEducation(db);
        createTableExperiences(db);
        createTablePositionTimeline(db);
        createTablePositionJobs(db);
        createTableLocation(db);
        createTableLanguage(db);
        createTableExpectJob(db);
        createTableRecruitment(db);
        createTableCandidateJob(db);
        createTableRecruiter(db);
        createTableQuanLyUngVien(db);
        createTableJobsSearch(db);
        createTableNotification(db);
        createTableSoKhop(db);
        createTableExcludesJob(db);
    }

    private void createTableExcludesJob(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "excludesjob(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "jexca_id TEXT ,"+
                "jexca_name TEXT ,"+
                "jexca_description TEXT , " +
                "jexca_created_at TEXT,"+
                "jexca_updated_at TEXT)";
        db.execSQL(createQuery);
    }
    private void createTableSoKhop(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "sokhop(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "job_title TEXT ,"+
                "job_work_location TEXT ,"+
                "job_time_type TEXT , " +
                "candidates TEXT,"+
                "job_views_number TEXT,"+
                "job_apply_number TEXT,"+
                "job_updated_at TEXT,"+
                "job_expired_time TEXT)";
        db.execSQL(createQuery);
    }
    private void createTableNotification(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "notification(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "notify_title TEXT ,"+
                "notify_content TEXT ,"+
                "notify_image_url TEXT , " +
                "notify_is_read TEXT,"+
                "notify_owner_id TEXT,"+
                "notify_type TEXT,"+
                "notify_created_at TEXT,"+
                "notiusr_user_id TEXT,"+
                "notify_id TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableJobsSearch(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "jobSearch(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "job_id TEXT ,"+
                "job_title TEXT ,"+
                "job_updated_at TEXT , " +
                "province_value TEXT,"+
                "job_salary TEXT,"+
                "job_time_type TEXT)";
        db.execSQL(createQuery);
    }

    private void createTablePoint(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "image(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "img_album_id INTEGER ," +
                "img_height INTEGER ," +
                "img_id INTEGER ," +
                "img_owner_id INTEGER ," +
                "img_position INTEGER ," +
                "img_width INTEGER ," +
                "total_img_same_al INTEGER ," +
                "img_created_at TEXT ,"+
                "img_file_url TEXT ,"+
                "img_tags TEXT , " +
                "img_updated_at TEXT,"+
                "alb_name TEXT,"+
                "img_uuid TEXT)";
        db.execSQL(createQuery);
    }

    private void createTablePositionTimeline(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "positiontime(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER ," +
                "position_id INTEGER ," +
                "start_time TEXT ,"+
                "end_time TEXT ,"+
                "position_name TEXT ,"+
                "position_value TEXT , " +
                "diff_time TEXT)";
        db.execSQL(createQuery);
    }

    private void createTablePositionJobs(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "positionjobs(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pos_id INTEGER ," +
                "pos_is_active INTEGER ," +
                "pos_name TEXT ,"+
                "pos_value TEXT ,"+
                "pos_description TEXT ,"+
                "pos_created_at TEXT , " +
                "pos_updated_at TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableLocation(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "location(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "locat_id INTEGER ," +
                "locat_is_active INTEGER ," +
                "locat_name TEXT ,"+
                "locat_value TEXT ,"+
                "locat_description TEXT ,"+
                "locat_created_at TEXT , " +
                "locat_updated_at TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableLanguage(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "language(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "lang_id INTEGER ," +
                "lang_name TEXT ,"+
                "lang_code TEXT ,"+
                "lang_created_at TEXT ,"+
                "lang_updated_at TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableEducation(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "education(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "schm_id INTEGER ," +
                "upe_user_id INTEGER ," +
                "upe_degree TEXT ," +
                "upe_id INTEGER ," +
                "maj_name TEXT ,"+
                "sch_name TEXT ,"+
                "upe_video_url TEXT , " +
                "upe_img_url TEXT,"+
                "upe_start_time TEXT,"+
                "upe_end_time TEXT,"+
                "upe_description TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableExperiences(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "experiences(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "uex_user_id INTEGER ," +
                "pos_id INTEGER ," +
                "uex_company_id INTEGER ," +
                "upos_year_of_working INTEGER ," +
                "uex_skills TEXT ,"+
                "pos_name TEXT ,"+
                "pos_value TEXT , " +
                "pos_description TEXT,"+
                "uex_company_name TEXT,"+
                "uex_img_url TEXT,"+
                "uex_video_url TEXT,"+
                "uex_description TEXT,"+
                "uex_start_time TEXT,"+
                "uex_end_time TEXT,"+
                "uex_id TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableRecruitment(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "recruitment(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "job_id TEXT ," +
                "job_company_id TEXT ," +
                "job_owner_id TEXT ," +
                "jca_id TEXT ," +
                "job_position_id TEXT ," +
                "pos_id TEXT ," +
                "job_quantity TEXT ," +
                "job_time_type TEXT ," +
                "job_range_salary_id TEXT ," +
                "job_gender TEXT ," +
                "job_range_allowance_id TEXT ," +
                "job_range_bonus_id TEXT ," +
                "job_status TEXT ," +
                "job_isactive TEXT ," +
                "lang_id TEXT ," +
                "job_title TEXT ,"+
                "jca_name TEXT ,"+
                "jca_value TEXT , " +
                "jca_tags TEXT,"+
                "jca_description TEXT,"+
                "jca_created_at TEXT,"+
                "jca_updated_at TEXT,"+
                "pos_name TEXT,"+
                "pos_value TEXT,"+
                "job_views_number TEXT ,"+
                "job_apply_number TEXT ,"+
                "job_level_id TEXT , " +
                "job_salary TEXT,"+
                "job_work_location TEXT,"+
                "job_description TEXT,"+
                "job_allowance TEXT,"+
                "job_bonus TEXT,"+
                "job_extra_desc TEXT,"+
                "job_expired_time TEXT ,"+
                "job_language_profile TEXT ,"+
                "job_album_img_id TEXT , " +
                "job_album_contract_id TEXT,"+
                "job_skills TEXT,"+
                "job_year_exps TEXT,"+
                "job_created_at TEXT,"+
                "job_updated_at TEXT,"+
                "lang_name TEXT,"+
                "lang_code TEXT,"+
                "lang_created_at TEXT,"+
                "lang_updated_at TEXT,"+
                "job_exclude_condition TEXT,"+
                "job_require_condition TEXT,"+
                "job_location TEXT,"+
                "job_welfare TEXT,"+
                "job_contact TEXT,"+
                "owner_id TEXT,"+
                "owner_avatar TEXT,"+
                "owner_name TEXT,"+
                "owner_website TEXT,"+
                "owner_phone TEXT,"+
                "is_user_saved TEXT,"+
                "number_matching TEXT,"+
                "is_user_apply TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableExpectJob(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "expectjob(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "expect_job INTEGER ," +
                "pos_id INTEGER ," +
                "province_id INTEGER ," +
                "user_id INTEGER ," +
                "salary_basic TEXT ," +
                "bonus TEXT ," +
                "allowance TEXT ,"+
                "objective TEXT ,"+
                "description TEXT , " +
                "pos_name TEXT,"+
                "pos_value TEXT,"+
                "province_name TEXT,"+
                "province_value TEXT,"+
                "district TEXT,"+
                "street TEXT,"+
                "number TEXT,"+
                "time_type TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableCandidateJob(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "candidatejob(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "job_position TEXT ," +
                "job_salary TEXT ," +
                "com_avatar_url TEXT ,"+
                "job_work_location TEXT , " +
                "job_time_type TEXT,"+
                "com_name TEXT,"+
                "canjob_candidate_id TEXT,"+
                "canjob_result_status TEXT,"+
                "canjob_is_apply TEXT ," +
                "job_id TEXT ," +
                "job_title TEXT ," +
                "job_updated_at TEXT ," +
                "is_user_saved TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableRecruiter(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "recruiter(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "first_name TEXT ," +
                "last_name TEXT ," +
                "avatar_url TEXT ,"+
                "address TEXT ,"+
                "pos_name TEXT , " +
                "pos_value TEXT,"+
                "update_time TEXT,"+
                "recruiter_id TEXT,"+
                "user_id TEXT,"+
                "exp_year TEXT ," +
                "time_type TEXT ," +
                "candidate_is_saved TEXT)";
        db.execSQL(createQuery);
    }

    private void createTableQuanLyUngVien(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " +
                "quanlyungvien(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "upb_address TEXT ," +
                "upb_avatar_url TEXT ," +
                "canjob_test_result TEXT ,"+
                "canjob_is_apply TEXT ,"+
                "canjob_result_status TEXT , " +
                "canjob_updated_at TEXT,"+
                "canjob_candidate_id TEXT,"+
                "canjob_job_id TEXT,"+
                "canjob_test_score TEXT,"+
                "user_id TEXT ," +
                "start_time TEXT ," +
                "end_time TEXT ," +
                "position_name TEXT ,"+
                "position_value TEXT ,"+
                "position_id TEXT , " +
                "diff_time TEXT,"+
                "uej_time_type TEXT,"+
                "u_email TEXT,"+
                "u_first_name TEXT,"+
                "u_last_name TEXT ," +
                "u_middle_name TEXT ," +
                "u_full_name TEXT ," +
                "u_phone TEXT,"+
                "canjob_final_result TEXT)";
        db.execSQL(createQuery);
    }
    //    private void addColumsStatus(SQLiteDatabase db) {
//        String createQuery = "ALTER TABLE tbl_video ADD upload TEXT DEFAULT 0 ";
//        db.execSQL(createQuery);
//    }
    public void insertAExcludesJob(String jexca_id,String jexca_name,String jexca_description,String jexca_created_at,String jexca_updated_at) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into excludesjob(jexca_id,jexca_name,jexca_description,jexca_created_at,jexca_updated_at) values " +
                "( "
                +"'"+ jexca_id +"', "
                +"'"+  jexca_name + "', "
                +"'"+  jexca_description + "', "
                +"'"+  jexca_created_at + "', "
                +"'"+  jexca_updated_at + "');";
        db.execSQL(query);
        close();
    }

    public void insertAQuanLyUngVien(String upb_address,String upb_avatar_url,String canjob_test_result,
                                     String canjob_is_apply,String canjob_result_status,String canjob_updated_at
            ,String canjob_candidate_id, String canjob_job_id,String canjob_test_score,
                                     String user_id,String start_time,String end_time,String position_name,
                                     String position_value,String position_id,String diff_time,String uej_time_type,
                                     String u_email,String u_first_name,
                                     String u_last_name,String u_middle_name,String u_full_name,String u_phone,String canjob_final_result) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into quanlyungvien(upb_address,upb_avatar_url,canjob_test_result,canjob_is_apply,\n" +
                "            canjob_result_status,canjob_updated_at,canjob_candidate_id,canjob_job_id,canjob_test_score,\n" +
                "            user_id,start_time,end_time,position_name,position_value,\n" +
                "            position_id,diff_time,uej_time_type,u_email,u_first_name,\n" +
                "            u_last_name,u_middle_name,u_full_name,u_phone,canjob_final_result) values " +
                "( "
                +"'"+ upb_address +"', "
                +"'"+  upb_avatar_url + "', "
                +"'"+  canjob_test_result + "', "
                +"'"+  canjob_is_apply + "', "
                +"'"+  canjob_result_status + "', "
                +"'"+  canjob_updated_at + "',"
                +"'"+  canjob_candidate_id + "',"
                +"'"+  canjob_job_id + "',"
                +"'"+  canjob_test_score + "',"
                +"'"+  user_id + "',"
                +"'"+ start_time +"', "
                +"'"+  end_time + "', "
                +"'"+  position_name + "', "
                +"'"+  position_value + "', "
                +"'"+  position_id + "', "
                +"'"+  diff_time + "',"
                +"'"+  uej_time_type + "',"
                +"'"+  u_email + "',"
                +"'"+  u_first_name + "',"
                +"'"+  u_last_name + "',"
                +"'"+  u_middle_name + "',"
                +"'"+  u_full_name + "',"
                +"'"+  u_phone + "',"
                +"'"+  canjob_final_result + "');";
        db.execSQL(query);
        close();
    }

    public void insertAJobSearch(String job_id, String job_title,String job_updated_at, String province_value,
                                 String job_salary,String job_time_type) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into jobSearch(job_id,job_title,job_updated_at,province_value,job_salary" +
                ",job_time_type) values " +
                "( "
                +"'"+ job_id +"', "
                +"'"+  job_title + "', "
                +"'"+  job_updated_at + "', "
                +"'"+  province_value + "', "
                +"'"+  job_salary + "', "
                +"'"+  job_time_type + "');";
        db.execSQL(query);
        close();
    }
    public void insertARecruiter(String first_name, String last_name,String avatar_url, String address,
                                    String pos_name,String pos_value,String update_time,
                                    String recruiter_id,String user_id,String exp_year,
                                    String time_type,String candidate_is_saved) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into recruiter(first_name,last_name,avatar_url,address,pos_name" +
                ",pos_value,update_time,recruiter_id,user_id,exp_year,time_type,candidate_is_saved) values " +
                "( "
                +"'"+ first_name +"', "
                +"'"+  last_name + "', "
                +"'"+  avatar_url + "', "
                +"'"+  address + "', "
                +"'"+  pos_name + "', "
                +"'"+  pos_value + "',"
                +"'"+  update_time + "',"
                +"'"+  recruiter_id + "',"
                +"'"+  user_id + "',"
                +"'"+  exp_year + "',"
                +"'"+  time_type + "',"
                +"'"+  candidate_is_saved + "');";
        db.execSQL(query);
        close();
    }
    public void insertACandidateJob(String job_position, String job_salary,String com_avatar_url,
                                 String job_work_location,String job_time_type,String com_name,
                                    String canjob_candidate_id,String canjob_result_status,String canjob_is_apply,
                                    String job_id, String job_title, String job_updated_at,String is_user_saved) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into candidatejob(job_position,job_salary,com_avatar_url,job_work_location" +
                ",job_time_type,com_name,canjob_candidate_id,canjob_result_status,canjob_is_apply,job_id,job_title,job_updated_at,is_user_saved) values " +
                "( "
                +"'"+ job_position +"', "
                +"'"+  job_salary + "', "
                +"'"+  com_avatar_url + "', "
                +"'"+  job_work_location + "', "
                +"'"+  job_time_type + "',"
                +"'"+  com_name + "',"
                +"'"+  canjob_candidate_id + "',"
                +"'"+  canjob_result_status + "',"
                +"'"+  canjob_is_apply + "',"
                +"'"+  job_id + "',"
                +"'"+  job_title + "',"
                +"'"+  job_updated_at + "',"
                +"'"+  is_user_saved + "');";
        db.execSQL(query);
        close();
    }

    public void insertASoKhop(String job_title,String job_work_location,String job_time_type,
                              String candidates,String job_views_number,String job_apply_number,String job_updated_at,String job_expired_time) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into sokhop(job_title,job_work_location,job_time_type,candidates,job_views_number,\n" +
                "            job_apply_number,job_updated_at,job_expired_time) values " +
                "( "
                +"'"+ job_title +"', "
                +"'"+  job_work_location + "', "
                +"'"+  job_time_type + "', "
                +"'"+  candidates + "', "
                +"'"+  job_views_number + "',"
                +"'"+  job_apply_number + "',"
                +"'"+  job_updated_at + "',"
                +"'"+  job_expired_time + "');";
        db.execSQL(query);
        close();
    }

    public void insertANotification(String notify_title,String notify_content,String notify_image_url,
                                    String notify_is_read,String notify_owner_id,String notify_type,String notify_created_at,
                                    String notiusr_user_id,String notify_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into notification(notify_title,notify_content,notify_image_url,notify_is_read,\n" +
                "            notify_owner_id,notify_type,notify_created_at,notiusr_user_id,notify_id) values " +
                "( "
                +"'"+ notify_title +"', "
                +"'"+  notify_content + "', "
                +"'"+  notify_image_url + "', "
                +"'"+  notify_is_read + "', "
                +"'"+  notify_owner_id + "',"
                +"'"+  notify_type + "',"
                +"'"+  notify_created_at + "',"
                +"'"+  notiusr_user_id + "',"
                +"'"+  notify_id + "');";
        db.execSQL(query);
        close();
    }

    public void insertAExpectJob(int expect_job,int user_id,int pos_id,int province_id,String salary_basic,
                             String bonus, String allowance,String objective, String description,
                                 String pos_name,String pos_value,String province_name,String province_value,String district,String street,String number,String time_type) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into expectjob(expect_job,user_id,pos_id,province_id,salary_basic,bonus,allowance,objective" +
                ",description,pos_name,pos_value,province_name,province_value,district,street,number,time_type) values " +
                "( "
                +"'"+ expect_job +"', "
                +"'"+  user_id + "', "
                +"'"+  pos_id + "', "
                +"'"+  province_id + "', "
                +"'"+  salary_basic + "', "
                +"'"+  bonus + "',"
                +"'"+  allowance + "',"
                +"'"+  objective + "',"
                +"'"+  description + "',"
                +"'"+  pos_name + "',"
                +"'"+  pos_value + "',"
                +"'"+  province_name + "',"
                +"'"+  province_value + "',"
                +"'"+  district + "',"
                +"'"+  street + "',"
                +"'"+  number + "',"
                +"'"+  time_type + "');";
        db.execSQL(query);
        close();
    }
    public void insertARecruitment(String job_id,String job_company_id,String job_owner_id,String jca_id,String job_position_id,String pos_id,String job_quantity,
                                   String job_time_type,String job_range_salary_id,String job_gender,String job_range_allowance_id,String job_range_bonus_id,String job_status,String job_isactive,
                                   String lang_id,String job_title,String jca_name,String jca_value,String jca_tags,String jca_description,String jca_created_at,String jca_updated_at
            ,String pos_name,String pos_value,String job_views_number,String job_apply_number,String job_level_id,String job_salary,String job_work_location
            ,String job_description,String job_allowance,String job_bonus,String job_extra_desc,String job_expired_time,String job_language_profile,String job_album_img_id
            ,String job_album_contract_id,String job_skills,String job_year_exps,String job_created_at,String job_updated_at,String lang_name,String lang_code
            ,String lang_created_at,String lang_updated_at,String job_exclude_condition,String job_require_condition,String job_location,String job_welfare,String job_contact
            ,String owner_id,String owner_avatar,String owner_name,String owner_website,String owner_phone,String is_user_saved,String number_matching,String is_user_apply) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into recruitment(job_id, job_company_id, job_owner_id, jca_id, job_position_id, pos_id, job_quantity,\n" +
                "                             job_time_type, job_range_salary_id, job_gender, job_range_allowance_id, job_range_bonus_id, job_status, job_isactive,\n" +
                "                             lang_id, job_title, jca_name, jca_value, jca_tags, jca_description, jca_created_at, jca_updated_at\n" +
                "            , pos_name, pos_value, job_views_number, job_apply_number, job_level_id, job_salary, job_work_location\n" +
                "            , job_description, job_allowance, job_bonus, job_extra_desc, job_expired_time, job_language_profile, job_album_img_id\n" +
                "            , job_album_contract_id, job_skills, job_year_exps, job_created_at, job_updated_at, lang_name, lang_code\n" +
                "            , lang_created_at, lang_updated_at, job_exclude_condition, job_require_condition, job_location,job_welfare,job_contact" +
                ",owner_id,owner_avatar,owner_name,owner_website,owner_phone,is_user_saved,number_matching,is_user_apply) values " +
                "( "
                +"'"+ job_id +"', "
                +"'"+  job_company_id + "', "
                +"'"+  job_owner_id + "', "
                +"'"+  jca_id + "', "
                +"'"+  job_position_id + "', "
                +"'"+  pos_id + "',"
                +"'"+  job_quantity + "',"
                +"'"+  job_time_type + "',"
                +"'"+  job_range_salary_id + "',"
                +"'"+  job_gender + "',"
                +"'"+  job_range_allowance_id + "',"
                +"'"+  job_range_bonus_id + "',"
                +"'"+  job_status + "',"
                +"'"+  job_isactive + "',"
                +"'"+  lang_id + "',"
                +"'"+ job_title +"', "
                +"'"+  jca_name + "', "
                +"'"+  jca_value + "', "
                +"'"+  jca_tags + "', "
                +"'"+  jca_description + "', "
                +"'"+  jca_created_at + "',"
                +"'"+  jca_updated_at + "',"
                +"'"+  pos_name + "',"
                +"'"+  pos_value + "',"
                +"'"+  job_views_number + "',"
                +"'"+  job_apply_number + "',"
                +"'"+  job_level_id + "',"
                +"'"+  job_salary + "',"
                +"'"+ job_work_location +"', "
                +"'"+  job_description + "', "
                +"'"+  job_allowance + "', "
                +"'"+  job_bonus + "', "
                +"'"+  job_extra_desc + "', "
                +"'"+  job_expired_time + "',"
                +"'"+  job_language_profile + "',"
                +"'"+  job_album_img_id + "',"
                +"'"+  job_album_contract_id + "',"
                +"'"+  job_skills + "',"
                +"'"+  job_year_exps + "',"
                +"'"+  job_created_at + "',"
                +"'"+  job_updated_at + "',"
                +"'"+  lang_name + "',"
                +"'"+  lang_code + "',"
                +"'"+  lang_created_at + "',"
                +"'"+  lang_updated_at + "',"
                +"'"+  job_exclude_condition + "',"
                +"'"+  job_require_condition + "',"
                +"'"+  job_location + "',"
                +"'"+  job_welfare + "',"
                +"'"+  job_contact + "',"
                +"'"+  owner_id + "',"
                +"'"+  owner_avatar + "',"
                +"'"+  owner_name + "',"
                +"'"+  owner_website + "',"
                +"'"+  owner_phone + "',"
                +"'"+  is_user_saved + "',"
                +"'"+  number_matching + "',"
                +"'"+  is_user_apply + "');";
        db.execSQL(query);
        close();
    }
//    public void updateARecruitment(String job_id,String job_company_id,String job_owner_id,String jca_id,String job_position_id,String pos_id,String job_quantity,
//                                   String job_time_type,String job_range_salary_id,String job_gender,String job_range_allowance_id,String job_range_bonus_id,String job_status,String job_isactive,
//                                   String lang_id,String job_title,String jca_name,String jca_value,String jca_tags,String jca_description,String jca_created_at,String jca_updated_at
//            ,String pos_name,String pos_value,String job_views_number,String job_apply_number,String job_level_id,String job_salary,String job_work_location
//            ,String job_description,String job_allowance,String job_bonus,String job_extra_desc,String job_expired_time,String job_language_profile,String job_album_img_id
//            ,String job_album_contract_id,String job_skills,String job_year_exps,String job_created_at,String job_updated_at,String lang_name,String lang_code
//            ,String lang_created_at,String lang_updated_at,String job_exclude_condition,String job_require_condition,String job_location,String job_welfare,String job_contact
//            ,String owner_id,String owner_avatar,String owner_name,String owner_website,String owner_phone,String is_user_saved,String number_matching) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String query = "UPDATE recruitment SET "+
//                "job_company_id="+"'"+ job_company_id +"', "+
//                "job_owner_id="+"'"+ job_owner_id +"', "+
//                "jca_id="+"'"+ jca_id +"', "+
//                "job_position_id="+"'"+ job_position_id +"', "+
//                "pos_id="+"'"+ pos_id +"', "+
//                "job_quantity="+"'"+ job_quantity +"', "+
//                "job_time_type="+"'"+ job_time_type +"', " +
//                "job_range_salary_id="+"'"+ job_range_salary_id +"', "+
//                "job_gender="+"'"+ job_gender +"', "+
//                "job_range_allowance_id="+"'"+ job_range_allowance_id +"', "+
//                "job_range_bonus_id="+"'"+ job_range_bonus_id +"', "+
//                "job_status="+"'"+ job_status +"', "+
//                "job_isactive="+"'"+ job_isactive +"', "+
//                "lang_id="+"'"+ lang_id +"', " +
//                "job_title="+"'"+ job_title +"', "+
//                "jca_name="+"'"+ jca_name +"', "+
//                "jca_value="+"'"+ jca_value +"', "+
//                "jca_tags="+"'"+ jca_tags +"', "+
//                "jca_description="+"'"+ jca_description +"', "+
//                "jca_created_at="+"'"+ jca_created_at +"', "+
//                "jca_updated_at="+"'"+ jca_updated_at +"', " +
//                "pos_name="+"'"+ pos_name +"', "+
//                "pos_value="+"'"+ pos_value +"', "+
//                "job_views_number="+"'"+ job_views_number +"', "+
//                "job_apply_number="+"'"+ job_apply_number +"', "+
//                "job_level_id="+"'"+ job_level_id +"', "+
//                "job_salary="+"'"+ job_salary +"', "+
//                "job_work_location="+"'"+ job_work_location +"', " +
//                "job_description="+"'"+ job_description +"', "+
//                "job_allowance="+"'"+ job_allowance +"', "+
//                "job_bonus="+"'"+ job_bonus +"', "+
//                "job_extra_desc="+"'"+ job_extra_desc +"', "+
//                "job_expired_time="+"'"+ job_expired_time +"', "+
//                "job_language_profile="+"'"+ job_language_profile +"', "+
//                "job_album_img_id="+"'"+ job_album_img_id +"', " +
//                "job_album_contract_id="+"'"+ job_album_contract_id +"', "+
//                "job_skills="+"'"+ job_skills +"', "+
//                "job_year_exps="+"'"+ job_year_exps +"', "+
//                "job_created_at="+"'"+ job_created_at +"', "+
//                "job_updated_at="+"'"+ job_updated_at +"', "+
//                "lang_name="+"'"+ lang_name +"', "+
//                "lang_code="+"'"+ lang_code +"', " +
//                "lang_created_at="+"'"+ lang_created_at +"', "+
//                "lang_updated_at="+"'"+ lang_updated_at +"', "+
//                "job_exclude_condition="+"'"+ job_exclude_condition +"', "+
//                "job_require_condition="+"'"+ job_require_condition +"', "+
//                "job_location="+"'"+ job_location +"', "+
//                "job_welfare="+"'"+ job_welfare +"', "+
//                "job_contact="+"'"+ job_contact +"', " +
//                "owner_id="+"'"+ owner_id +"', "+
//                "owner_avatar="+"'"+ owner_avatar +"', "+
//                "owner_name="+"'"+ owner_name +"', "+
//                "owner_website="+"'"+ owner_website +"', "+
//                "owner_phone="+"'"+ owner_phone +"', "+
//                "is_user_saved="+"'"+ is_user_saved +"', "+
//                "number_matching="+"'"+ number_matching +"' "+
//                "WHERE job_id="+"'"+ job_id +"';";
//        Log.d("Sssquery","ss"+query);
//        db.execSQL(query);
//        close();
//    }
    public void insertAImage(int img_album_id,int img_height,int img_id,int img_owner_id,int img_position,int img_width,int total_img_same_al,String img_created_at,
                             String img_tags, String img_updated_at,String img_uuid, String img_file_url,String alb_name) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into image(img_album_id,img_height,img_id,img_owner_id,img_position,img_width,total_img_same_al,img_created_at,img_tags,img_updated_at,img_uuid,alb_name,img_file_url) values " +
                "( "
                +"'"+ img_album_id +"', "
                +"'"+  img_height + "', "
                +"'"+  img_id + "', "
                +"'"+  img_owner_id + "', "
                +"'"+  img_position + "', "
                +"'"+  img_width + "',"
                +"'"+  total_img_same_al + "',"
                +"'"+  img_created_at + "',"
                +"'"+  img_tags + "',"
                +"'"+  img_updated_at + "',"
                +"'"+  img_uuid + "',"
                +"'"+  alb_name + "',"
                +"'"+  img_file_url + "');";
        db.execSQL(query);
        close();
    }
    public void insertAPositionTimeline(int user_id,int position_id,
                                        String start_time, String end_time,String position_name, String position_value,String diff_time) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into positiontime(user_id,position_id,start_time,end_time,position_name,position_value,diff_time) values " +
                "( "
                +"'"+ user_id +"', "
                +"'"+  position_id + "', "
                +"'"+  start_time + "', "
                +"'"+  end_time + "', "
                +"'"+  position_name + "', "
                +"'"+  position_value + "',"
                +"'"+  diff_time + "');";
        db.execSQL(query);
        close();
    }
    public void insertAPositionJobs(int pos_id,int pos_is_active, String pos_name,
                                    String pos_value,String pos_description, String pos_created_at,String pos_updated_at) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into positionjobs(pos_id,pos_is_active,pos_name,pos_value,pos_description,pos_created_at,pos_updated_at) values " +
                "( "
                +"'"+ pos_id +"', "
                +"'"+  pos_is_active + "', "
                +"'"+  pos_name + "', "
                +"'"+  pos_value + "', "
                +"'"+  pos_description + "', "
                +"'"+  pos_created_at + "',"
                +"'"+  pos_updated_at + "');";
        db.execSQL(query);
        close();
    }
    public void insertALocation(int locat_id,int locat_is_active,
                                        String locat_name, String locat_value,String locat_description, String locat_created_at,String locat_updated_at) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into location(locat_id,locat_is_active,locat_name,locat_value,locat_description,locat_created_at,locat_updated_at) values " +
                "( "
                +"'"+ locat_id +"', "
                +"'"+  locat_is_active + "', "
                +"'"+  locat_name + "', "
                +"'"+  locat_value + "', "
                +"'"+  locat_description + "', "
                +"'"+  locat_created_at + "',"
                +"'"+  locat_updated_at + "');";
        db.execSQL(query);
        close();
    }
    public void insertALanguage(int lang_id,
                                        String lang_name, String lang_code,String lang_created_at, String lang_updated_at) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into language(lang_id,lang_name,lang_code,lang_created_at,lang_updated_at) values " +
                "( "
                +"'"+ lang_id +"', "
                +"'"+  lang_name + "', "
                +"'"+  lang_code + "', "
                +"'"+  lang_created_at + "', "
                +"'"+  lang_updated_at + "');";
        db.execSQL(query);
        close();
    }
    public void insertAEducation(int schm_id,int upe_user_id,String upe_degree,int upe_id
            ,String maj_name,String sch_name, String upe_video_url,String upe_img_url, String upe_start_time,String upe_end_time,String upe_description) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into education(schm_id,upe_user_id,upe_degree,upe_id,maj_name,sch_name,upe_video_url,upe_img_url,upe_start_time,upe_end_time,upe_description) values " +
                "( "
                +"'"+ schm_id +"', "
                +"'"+  upe_user_id + "', "
                +"'"+  upe_degree + "', "
                +"'"+  upe_id + "', "
                +"'"+  maj_name + "', "
                +"'"+  sch_name + "',"
                +"'"+  upe_video_url + "',"
                +"'"+  upe_img_url + "',"
                +"'"+  upe_start_time + "',"
                +"'"+  upe_end_time + "',"
                +"'"+  upe_description + "');";
        db.execSQL(query);
        close();
    }

    public void insertAExperiences(int uex_user_id,int pos_id,int uex_company_id,int upos_year_of_working
            ,String uex_skills,String pos_name, String pos_value,String pos_description, String uex_company_name
            ,String uex_img_url,String uex_video_url,String uex_description,String uex_start_time,String uex_end_time,String uex_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Insert into experiences(uex_user_id,pos_id,uex_company_id,upos_year_of_working" +
                ",uex_skills,pos_name,pos_value,pos_description,uex_company_name,uex_img_url,uex_video_url" +
                ",uex_description,uex_start_time,uex_end_time,uex_id) values " +
                "( "
                +"'"+ uex_user_id +"', "
                +"'"+  pos_id + "', "
                +"'"+  uex_company_id + "', "
                +"'"+  upos_year_of_working + "', "
                +"'"+  uex_skills + "', "
                +"'"+  pos_name + "',"
                +"'"+  pos_value + "',"
                +"'"+  pos_description + "',"
                +"'"+  uex_company_name + "',"
                +"'"+  uex_img_url + "',"
                +"'"+  uex_video_url + "',"
                +"'"+  uex_description + "',"
                +"'"+  uex_start_time + "',"
                +"'"+  uex_end_time + "',"
                +"'"+  uex_id + "');";
        db.execSQL(query);
        close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        onCreate(db);
    }

//    public ArrayList<String> getAllImage(){
//        ArrayList<String> arrayList = new ArrayList<>();
//        String sql = "Select img_file_url from image";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql,null);
//        try {
//            if (cursor.moveToFirst()){
//                do {
//                    arrayList.add(cursor.getString(0));
//                }while (cursor.moveToNext());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            cursor.close();
//        }
//        close();
//        return arrayList;
//    }

    public List<ExcludesModel> getAllExcludesJob() {
        ArrayList<ExcludesModel> excludesModels = new ArrayList<ExcludesModel>();
        // Select All Query
        String selectQuery = "Select * from excludesjob ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    ExcludesModel excludesModel = new ExcludesModel();
                    excludesModel.setJexca_id(cursor.getString(1));
                    excludesModel.setJexca_name(cursor.getString(2));
                    excludesModel.setJexca_description(cursor.getString(3));
                    excludesModel.setJexca_created_at(cursor.getString(4));
                    excludesModel.setJexca_updated_at(cursor.getString(5));
                    // Adding contact to list
                    excludesModels.add(excludesModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return excludesModels;
    }

    public List<SoKhopModel> getAllSoKhop() {
        ArrayList<SoKhopModel> soKhopModels = new ArrayList<SoKhopModel>();
        // Select All Query
        String selectQuery = "Select * from sokhop ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    SoKhopModel soKhopModel = new SoKhopModel();
                    soKhopModel.setJob_title(cursor.getString(1));
                    soKhopModel.setJob_work_location(cursor.getString(2));
                    soKhopModel.setJob_time_type(cursor.getString(3));
                    soKhopModel.setCandidates(cursor.getString(4));
                    soKhopModel.setJob_views_number(cursor.getString(5));
                    soKhopModel.setJob_apply_number(cursor.getString(6));
                    soKhopModel.setJob_updated_at(cursor.getString(7));
                    soKhopModel.setJob_expired_time(cursor.getString(8));
                    // Adding contact to list
                    soKhopModels.add(soKhopModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return soKhopModels;
    }

    public List<NotificationModel> getAllNotification() {
        ArrayList<NotificationModel> notificationModels = new ArrayList<NotificationModel>();
        // Select All Query
        String selectQuery = "Select * from notification ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    NotificationModel notificationModel = new NotificationModel();
                    notificationModel.setNotify_title(cursor.getString(1));
                    notificationModel.setNotify_content(cursor.getString(2));
                    notificationModel.setNotify_image_url(cursor.getString(3));
                    notificationModel.setNotify_is_read(cursor.getString(4));
                    notificationModel.setNotify_owner_id(cursor.getString(5));
                    notificationModel.setNotify_type(cursor.getString(6));
                    notificationModel.setNotify_created_at(cursor.getString(7));
                    notificationModel.setNotiusr_user_id(cursor.getString(8));
                    notificationModel.setNotify_id(cursor.getString(9));
                    // Adding contact to list
                    notificationModels.add(notificationModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return notificationModels;
    }

    public List<RecruiterModel> getAllRecruiter() {
        ArrayList<RecruiterModel> recruiterModels = new ArrayList<RecruiterModel>();
        // Select All Query
        String selectQuery = "Select * from recruiter ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    RecruiterModel recruiterModel = new RecruiterModel();
                    recruiterModel.setFirst_name(cursor.getString(1));
                    recruiterModel.setLast_name(cursor.getString(2));
                    recruiterModel.setAvatar_url(cursor.getString(3));
                    recruiterModel.setAddress(cursor.getString(4));
                    recruiterModel.setPos_name(cursor.getString(5));
                    recruiterModel.setPos_value(cursor.getString(6));
                    recruiterModel.setUpdate_time(cursor.getString(7));
                    recruiterModel.setRecruiter_id(cursor.getString(8));
                    recruiterModel.setUser_id(cursor.getString(9));
                    recruiterModel.setExp_year(cursor.getString(10));
                    recruiterModel.setTime_type(cursor.getString(11));
                    recruiterModel.setCandidate_is_saved(cursor.getString(12));
                    // Adding contact to list
                    recruiterModels.add(recruiterModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return recruiterModels;
    }
public List<RecruitmentModel> getAllRecruitment() {
    ArrayList<RecruitmentModel> recruitmentModels = new ArrayList<RecruitmentModel>();
    // Select All Query
    String selectQuery = "Select * from recruitment ORDER BY _id DESC ";

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
    // looping through all rows and adding to list
    try {
        if (cursor.moveToFirst()) {
            do {
                RecruitmentModel recruitmentModel = new RecruitmentModel();
                recruitmentModel.setJob_id(cursor.getString(1));
                recruitmentModel.setJob_company_id(cursor.getString(2));
                recruitmentModel.setJob_owner_id(cursor.getString(3));
                recruitmentModel.setJca_id(cursor.getString(4));
                recruitmentModel.setJob_position_id(cursor.getString(5));
                recruitmentModel.setPos_id(cursor.getString(6));
                recruitmentModel.setJob_quantity(cursor.getString(7));
                recruitmentModel.setJob_time_type(cursor.getString(8));
                recruitmentModel.setJob_range_salary_id(cursor.getString(9));
                recruitmentModel.setJob_gender(cursor.getString(10));
                recruitmentModel.setJob_range_allowance_id(cursor.getString(11));
                recruitmentModel.setJob_range_bonus_id(cursor.getString(12));
                recruitmentModel.setJob_status(cursor.getString(13));
                recruitmentModel.setJob_isactive(cursor.getString(14));
                recruitmentModel.setLang_id(cursor.getString(15));
                recruitmentModel.setJob_title(cursor.getString(16));
                recruitmentModel.setJca_name(cursor.getString(17));
                recruitmentModel.setJca_value(cursor.getString(18));
                recruitmentModel.setJca_tags(cursor.getString(19));
                recruitmentModel.setJca_description(cursor.getString(20));
                recruitmentModel.setJca_created_at(cursor.getString(21));
                recruitmentModel.setJca_updated_at(cursor.getString(22));
                recruitmentModel.setPos_name(cursor.getString(23));
                recruitmentModel.setPos_value(cursor.getString(24));
                recruitmentModel.setJob_views_number(cursor.getString(25));
                recruitmentModel.setJob_apply_number(cursor.getString(26));
                recruitmentModel.setJob_level_id(cursor.getString(27));
                recruitmentModel.setJob_salary(cursor.getString(28));
                recruitmentModel.setJob_work_location(cursor.getString(29));
                recruitmentModel.setJob_description(cursor.getString(30));
                recruitmentModel.setJob_allowance(cursor.getString(31));
                recruitmentModel.setJob_bonus(cursor.getString(32));
                recruitmentModel.setJob_extra_desc(cursor.getString(33));
                recruitmentModel.setJob_expired_time(cursor.getString(34));
                recruitmentModel.setJob_language_profile(cursor.getString(35));
                recruitmentModel.setJob_album_img_id(cursor.getString(36));
                recruitmentModel.setJob_album_contract_id(cursor.getString(37));
                recruitmentModel.setJob_skills(cursor.getString(38));
                recruitmentModel.setJob_year_exps(cursor.getString(39));
                recruitmentModel.setJob_created_at(cursor.getString(40));
                recruitmentModel.setJob_updated_at(cursor.getString(41));
                recruitmentModel.setLang_name(cursor.getString(42));
                recruitmentModel.setLang_code(cursor.getString(43));
                recruitmentModel.setLang_created_at(cursor.getString(44));
                recruitmentModel.setLang_updated_at(cursor.getString(45));
                recruitmentModel.setJob_exclude_condition(cursor.getString(46));
                recruitmentModel.setJob_require_condition(cursor.getString(47));
                recruitmentModel.setJob_location(cursor.getString(48));
                recruitmentModel.setJob_welfare(cursor.getString(49));
                recruitmentModel.setJob_contact(cursor.getString(50));
                recruitmentModel.setOwner_id(cursor.getString(51));
                recruitmentModel.setOwner_avatar(cursor.getString(52));
                recruitmentModel.setOwner_name(cursor.getString(53));
                recruitmentModel.setOwner_website(cursor.getString(54));
                recruitmentModel.setOwner_phone(cursor.getString(55));
                recruitmentModel.setIs_user_saved(cursor.getString(56));
                recruitmentModel.setNumber_matching(cursor.getString(57));
                recruitmentModel.setIs_user_apply(cursor.getString(58));
                // Adding contact to list
                recruitmentModels.add(recruitmentModel);
            } while (cursor.moveToNext());
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        cursor.close();
    }
    close();
    return recruitmentModels;
}

    public List<RecruitmentModel> getARecruitment(int job_status) {
        ArrayList<RecruitmentModel> recruitmentModels = new ArrayList<RecruitmentModel>();
        // Select All Query
        String selectQuery = "Select * from recruitment WHERE job_status = " + job_status + " ORDER BY _id DESC ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    RecruitmentModel recruitmentModel = new RecruitmentModel();

                    recruitmentModel.setJob_id(cursor.getString(1));
                    recruitmentModel.setJob_company_id(cursor.getString(2));
                    recruitmentModel.setJob_owner_id(cursor.getString(3));
                    recruitmentModel.setJca_id(cursor.getString(4));
                    recruitmentModel.setJob_position_id(cursor.getString(5));
                    recruitmentModel.setPos_id(cursor.getString(6));
                    recruitmentModel.setJob_quantity(cursor.getString(7));
                    recruitmentModel.setJob_time_type(cursor.getString(8));
                    recruitmentModel.setJob_range_salary_id(cursor.getString(9));
                    recruitmentModel.setJob_gender(cursor.getString(10));
                    recruitmentModel.setJob_range_allowance_id(cursor.getString(11));
                    recruitmentModel.setJob_range_bonus_id(cursor.getString(12));
                    recruitmentModel.setJob_status(cursor.getString(13));
                    recruitmentModel.setJob_isactive(cursor.getString(14));
                    recruitmentModel.setLang_id(cursor.getString(15));
                    recruitmentModel.setJob_title(cursor.getString(16));
                    recruitmentModel.setJca_name(cursor.getString(17));
                    recruitmentModel.setJca_value(cursor.getString(18));
                    recruitmentModel.setJca_tags(cursor.getString(19));
                    recruitmentModel.setJca_description(cursor.getString(20));
                    recruitmentModel.setJca_created_at(cursor.getString(21));
                    recruitmentModel.setJca_updated_at(cursor.getString(22));
                    recruitmentModel.setPos_name(cursor.getString(23));
                    recruitmentModel.setPos_value(cursor.getString(24));
                    recruitmentModel.setJob_views_number(cursor.getString(25));
                    recruitmentModel.setJob_apply_number(cursor.getString(26));
                    recruitmentModel.setJob_level_id(cursor.getString(27));
                    recruitmentModel.setJob_salary(cursor.getString(28));
                    recruitmentModel.setJob_work_location(cursor.getString(29));
                    recruitmentModel.setJob_description(cursor.getString(30));
                    recruitmentModel.setJob_allowance(cursor.getString(31));
                    recruitmentModel.setJob_bonus(cursor.getString(32));
                    recruitmentModel.setJob_extra_desc(cursor.getString(33));
                    recruitmentModel.setJob_expired_time(cursor.getString(34));
                    recruitmentModel.setJob_language_profile(cursor.getString(35));
                    recruitmentModel.setJob_album_img_id(cursor.getString(36));
                    recruitmentModel.setJob_album_contract_id(cursor.getString(37));
                    recruitmentModel.setJob_skills(cursor.getString(38));
                    recruitmentModel.setJob_year_exps(cursor.getString(39));
                    recruitmentModel.setJob_created_at(cursor.getString(40));
                    recruitmentModel.setJob_updated_at(cursor.getString(41));
                    recruitmentModel.setLang_name(cursor.getString(42));
                    recruitmentModel.setLang_code(cursor.getString(43));
                    recruitmentModel.setLang_created_at(cursor.getString(44));
                    recruitmentModel.setLang_updated_at(cursor.getString(45));
                    recruitmentModel.setJob_exclude_condition(cursor.getString(46));
                    recruitmentModel.setJob_require_condition(cursor.getString(47));
                    recruitmentModel.setJob_location(cursor.getString(48));
                    recruitmentModel.setJob_welfare(cursor.getString(49));
                    recruitmentModel.setJob_contact(cursor.getString(50));
                    recruitmentModel.setOwner_id(cursor.getString(51));
                    recruitmentModel.setOwner_avatar(cursor.getString(52));
                    recruitmentModel.setOwner_name(cursor.getString(53));
                    recruitmentModel.setOwner_website(cursor.getString(54));
                    recruitmentModel.setOwner_phone(cursor.getString(55));
                    recruitmentModel.setIs_user_saved(cursor.getString(56));
                    recruitmentModel.setNumber_matching(cursor.getString(57));
                    recruitmentModel.setIs_user_apply(cursor.getString(58));
                    // Adding contact to list
                    recruitmentModels.add(recruitmentModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return recruitmentModels;
    }
    public List<RecruitmentModel> getRecruitmentByJobId(int job_id) {
        ArrayList<RecruitmentModel> recruitmentModels = new ArrayList<RecruitmentModel>();
        // Select All Query
        String selectQuery = "Select * from recruitment WHERE job_id = " + job_id + " ORDER BY _id DESC ;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    RecruitmentModel recruitmentModel = new RecruitmentModel();
                    recruitmentModel.setJob_id(cursor.getString(1));
                    recruitmentModel.setJob_company_id(cursor.getString(2));
                    recruitmentModel.setJob_owner_id(cursor.getString(3));
                    recruitmentModel.setJca_id(cursor.getString(4));
                    recruitmentModel.setJob_position_id(cursor.getString(5));
                    recruitmentModel.setPos_id(cursor.getString(6));
                    recruitmentModel.setJob_quantity(cursor.getString(7));
                    recruitmentModel.setJob_time_type(cursor.getString(8));
                    recruitmentModel.setJob_range_salary_id(cursor.getString(9));
                    recruitmentModel.setJob_gender(cursor.getString(10));
                    recruitmentModel.setJob_range_allowance_id(cursor.getString(11));
                    recruitmentModel.setJob_range_bonus_id(cursor.getString(12));
                    recruitmentModel.setJob_status(cursor.getString(13));
                    recruitmentModel.setJob_isactive(cursor.getString(14));
                    recruitmentModel.setLang_id(cursor.getString(15));
                    recruitmentModel.setJob_title(cursor.getString(16));
                    recruitmentModel.setJca_name(cursor.getString(17));
                    recruitmentModel.setJca_value(cursor.getString(18));
                    recruitmentModel.setJca_tags(cursor.getString(19));
                    recruitmentModel.setJca_description(cursor.getString(20));
                    recruitmentModel.setJca_created_at(cursor.getString(21));
                    recruitmentModel.setJca_updated_at(cursor.getString(22));
                    recruitmentModel.setPos_name(cursor.getString(23));
                    recruitmentModel.setPos_value(cursor.getString(24));
                    recruitmentModel.setJob_views_number(cursor.getString(25));
                    recruitmentModel.setJob_apply_number(cursor.getString(26));
                    recruitmentModel.setJob_level_id(cursor.getString(27));
                    recruitmentModel.setJob_salary(cursor.getString(28));
                    recruitmentModel.setJob_work_location(cursor.getString(29));
                    recruitmentModel.setJob_description(cursor.getString(30));
                    recruitmentModel.setJob_allowance(cursor.getString(31));
                    recruitmentModel.setJob_bonus(cursor.getString(32));
                    recruitmentModel.setJob_extra_desc(cursor.getString(33));
                    recruitmentModel.setJob_expired_time(cursor.getString(34));
                    recruitmentModel.setJob_language_profile(cursor.getString(35));
                    recruitmentModel.setJob_album_img_id(cursor.getString(36));
                    recruitmentModel.setJob_album_contract_id(cursor.getString(37));
                    recruitmentModel.setJob_skills(cursor.getString(38));
                    recruitmentModel.setJob_year_exps(cursor.getString(39));
                    recruitmentModel.setJob_created_at(cursor.getString(40));
                    recruitmentModel.setJob_updated_at(cursor.getString(41));
                    recruitmentModel.setLang_name(cursor.getString(42));
                    recruitmentModel.setLang_code(cursor.getString(43));
                    recruitmentModel.setLang_created_at(cursor.getString(44));
                    recruitmentModel.setLang_updated_at(cursor.getString(45));
                    recruitmentModel.setJob_exclude_condition(cursor.getString(46));
                    recruitmentModel.setJob_require_condition(cursor.getString(47));
                    recruitmentModel.setJob_location(cursor.getString(48));
                    recruitmentModel.setJob_welfare(cursor.getString(49));
                    recruitmentModel.setJob_contact(cursor.getString(50));
                    recruitmentModel.setOwner_id(cursor.getString(51));
                    recruitmentModel.setOwner_avatar(cursor.getString(52));
                    recruitmentModel.setOwner_name(cursor.getString(53));
                    recruitmentModel.setOwner_website(cursor.getString(54));
                    recruitmentModel.setOwner_phone(cursor.getString(55));
                    recruitmentModel.setIs_user_saved(cursor.getString(56));
                    recruitmentModel.setNumber_matching(cursor.getString(57));
                    recruitmentModel.setIs_user_apply(cursor.getString(58));
                    // Adding contact to list
                    recruitmentModels.add(recruitmentModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return recruitmentModels;
    }
    public List<EducationModel> getAllEducation() {
        ArrayList<EducationModel> educationModels = new ArrayList<EducationModel>();
        // Select All Query
        String selectQuery = "Select * from education ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    EducationModel educationModel = new EducationModel();
                    educationModel.setSchm_id(cursor.getInt(1));
                    educationModel.setUpe_user_id(cursor.getInt(2));
                    educationModel.setUpe_degree(cursor.getString(3));
                    educationModel.setUpe_id(cursor.getInt(4));
                    educationModel.setMaj_name(cursor.getString(5));
                    educationModel.setSch_name(cursor.getString(6));
                    educationModel.setUpe_video_url(cursor.getString(7));
                    educationModel.setUpe_img_url(cursor.getString(8));
                    educationModel.setUpe_start_time(cursor.getString(9));
                    educationModel.setUpe_end_time(cursor.getString(10));
                    educationModel.setUpe_description(cursor.getString(11));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    educationModels.add(educationModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return educationModels;
    }

    public List<QuanLyUngVienModel> getAllQuanLyUngVien() {
        ArrayList<QuanLyUngVienModel> quanLyUngVienModels = new ArrayList<QuanLyUngVienModel>();
        // Select All Query
        String selectQuery = "Select * from quanlyungvien ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    QuanLyUngVienModel quanLyUngVienModel = new QuanLyUngVienModel();
                    quanLyUngVienModel.setUpb_address(cursor.getString(1));
                    quanLyUngVienModel.setUpb_avatar_url(cursor.getString(2));
                    quanLyUngVienModel.setCanjob_test_result(cursor.getString(3));
                    quanLyUngVienModel.setCanjob_is_apply(cursor.getString(4));
                    quanLyUngVienModel.setCanjob_result_status(cursor.getString(5));
                    quanLyUngVienModel.setCanjob_updated_at(cursor.getString(6));
                    quanLyUngVienModel.setCanjob_candidate_id(cursor.getString(7));
                    quanLyUngVienModel.setCanjob_job_id(cursor.getString(8));
                    quanLyUngVienModel.setCanjob_test_score(cursor.getString(9));
                    quanLyUngVienModel.setUser_id(cursor.getString(10));
                    quanLyUngVienModel.setStart_time(cursor.getString(11));
                    quanLyUngVienModel.setEnd_time(cursor.getString(12));
                    quanLyUngVienModel.setPosition_name(cursor.getString(13));
                    quanLyUngVienModel.setPosition_value(cursor.getString(14));
                    quanLyUngVienModel.setPosition_id(cursor.getString(15));
                    quanLyUngVienModel.setDiff_time(cursor.getString(16));
                    quanLyUngVienModel.setUej_time_type(cursor.getString(17));
                    quanLyUngVienModel.setU_email(cursor.getString(18));
                    quanLyUngVienModel.setU_first_name(cursor.getString(19));
                    quanLyUngVienModel.setU_last_name(cursor.getString(20));
                    quanLyUngVienModel.setU_middle_name(cursor.getString(21));
                    quanLyUngVienModel.setU_full_name(cursor.getString(22));
                    quanLyUngVienModel.setU_phone(cursor.getString(23));
                    quanLyUngVienModel.setCanjob_final_result(cursor.getString(24));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    quanLyUngVienModels.add(quanLyUngVienModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return quanLyUngVienModels;
    }

    public List<EducationModel> getAEducationByEduId(String upe_id) {
        ArrayList<EducationModel> educationModels = new ArrayList<EducationModel>();
        // Select All Query
        String selectQuery = "Select * from education WHERE job_id = " + upe_id + "ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    EducationModel educationModel = new EducationModel();
                    educationModel.setSchm_id(cursor.getInt(1));
                    educationModel.setUpe_user_id(cursor.getInt(2));
                    educationModel.setUpe_degree(cursor.getString(3));
                    educationModel.setUpe_id(cursor.getInt(4));
                    educationModel.setMaj_name(cursor.getString(5));
                    educationModel.setSch_name(cursor.getString(6));
                    educationModel.setUpe_video_url(cursor.getString(7));
                    educationModel.setUpe_img_url(cursor.getString(8));
                    educationModel.setUpe_start_time(cursor.getString(9));
                    educationModel.setUpe_end_time(cursor.getString(10));
                    educationModel.setUpe_description(cursor.getString(11));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    educationModels.add(educationModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return educationModels;
    }
    public List<ExperiencesModel> getAExpByExpId(String uex_user_id) {
        ArrayList<ExperiencesModel> experiencesModels = new ArrayList<ExperiencesModel>();
        // Select All Query
        String selectQuery = "Select * from experiences WHERE uex_user_id = " + uex_user_id + "ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    ExperiencesModel experiencesModel = new ExperiencesModel();
                    experiencesModel.setUex_user_id(cursor.getInt(1));
                    experiencesModel.setPos_id(cursor.getInt(2));
                    experiencesModel.setUex_company_id(cursor.getInt(3));
                    experiencesModel.setUpos_year_of_working(cursor.getInt(4));
                    experiencesModel.setUex_skills(cursor.getString(5));
                    experiencesModel.setPos_name(cursor.getString(6));
                    experiencesModel.setPos_value(cursor.getString(7));
                    experiencesModel.setPos_description(cursor.getString(8));
                    experiencesModel.setUex_company_name(cursor.getString(9));
                    experiencesModel.setUex_img_url(cursor.getString(10));
                    experiencesModel.setUex_video_url(cursor.getString(11));
                    experiencesModel.setUex_description(cursor.getString(12));
                    experiencesModel.setUex_start_time(cursor.getString(13));
                    experiencesModel.setUex_end_time(cursor.getString(14));
                    experiencesModel.setUex_id(cursor.getString(15));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    experiencesModels.add(experiencesModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return experiencesModels;
    }
    public List<ExpectJobModel> getAExpectJobById(String pos_id) {
        ArrayList<ExpectJobModel> expectJobModels = new ArrayList<ExpectJobModel>();
        // Select All Query
        String selectQuery = "Select * from expectjob WHERE pos_id = " + pos_id + "ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    ExpectJobModel expectJobModel = new ExpectJobModel();
                    expectJobModel.setExpect_job(cursor.getInt(1));
                    expectJobModel.setUser_id(cursor.getInt(2));
                    expectJobModel.setPos_id(cursor.getInt(3));
                    expectJobModel.setProvince_id(cursor.getInt(4));
                    expectJobModel.setSalary_basic(cursor.getString(5));
                    expectJobModel.setBonus(cursor.getString(6));
                    expectJobModel.setAllowance(cursor.getString(7));
                    expectJobModel.setObjective(cursor.getString(8));
                    expectJobModel.setDescription(cursor.getString(9));
                    expectJobModel.setPos_name(cursor.getString(10));
                    expectJobModel.setPos_value(cursor.getString(11));
                    expectJobModel.setProvince_name(cursor.getString(12));
                    expectJobModel.setProvince_value(cursor.getString(13));
                    expectJobModel.setDistrict(cursor.getString(14));
                    expectJobModel.setStreet(cursor.getString(15));
                    expectJobModel.setNumber(cursor.getString(16));
                    expectJobModel.setTime_type(cursor.getString(17));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    expectJobModels.add(expectJobModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return expectJobModels;
    }

    public List<ExperiencesModel> getAllExperices() {
        ArrayList<ExperiencesModel> experiencesModels = new ArrayList<ExperiencesModel>();
        // Select All Query
        String selectQuery = "Select * from experiences ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    ExperiencesModel experiencesModel = new ExperiencesModel();
                    experiencesModel.setUex_user_id(cursor.getInt(1));
                    experiencesModel.setPos_id(cursor.getInt(2));
                    experiencesModel.setUex_company_id(cursor.getInt(3));
                    experiencesModel.setUpos_year_of_working(cursor.getInt(4));
                    experiencesModel.setUex_skills(cursor.getString(5));
                    experiencesModel.setPos_name(cursor.getString(6));
                    experiencesModel.setPos_value(cursor.getString(7));
                    experiencesModel.setPos_description(cursor.getString(8));
                    experiencesModel.setUex_company_name(cursor.getString(9));
                    experiencesModel.setUex_img_url(cursor.getString(10));
                    experiencesModel.setUex_video_url(cursor.getString(11));
                    experiencesModel.setUex_description(cursor.getString(12));
                    experiencesModel.setUex_start_time(cursor.getString(13));
                    experiencesModel.setUex_end_time(cursor.getString(14));
                    experiencesModel.setUex_id(cursor.getString(15));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    experiencesModels.add(experiencesModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return experiencesModels;
    }

    public List<ExpectJobModel> getAllExpectJob() {
        ArrayList<ExpectJobModel> expectJobModels = new ArrayList<ExpectJobModel>();
        // Select All Query
        String selectQuery = "Select * from expectjob ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    ExpectJobModel expectJobModel = new ExpectJobModel();
                    expectJobModel.setExpect_job(cursor.getInt(1));
                    expectJobModel.setUser_id(cursor.getInt(2));
                    expectJobModel.setPos_id(cursor.getInt(3));
                    expectJobModel.setProvince_id(cursor.getInt(4));
                    expectJobModel.setSalary_basic(cursor.getString(5));
                    expectJobModel.setBonus(cursor.getString(6));
                    expectJobModel.setAllowance(cursor.getString(7));
                    expectJobModel.setObjective(cursor.getString(8));
                    expectJobModel.setDescription(cursor.getString(9));
                    expectJobModel.setPos_name(cursor.getString(10));
                    expectJobModel.setPos_value(cursor.getString(11));
                    expectJobModel.setProvince_name(cursor.getString(12));
                    expectJobModel.setProvince_value(cursor.getString(13));
                    expectJobModel.setDistrict(cursor.getString(14));
                    expectJobModel.setStreet(cursor.getString(15));
                    expectJobModel.setNumber(cursor.getString(16));
                    expectJobModel.setTime_type(cursor.getString(17));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    expectJobModels.add(expectJobModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return expectJobModels;
    }


    public List<Image> getAllImage() {
        ArrayList<Image> images = new ArrayList<Image>();
        // Select All Query
        String selectQuery = "Select * from image ORDER BY img_album_id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    Image image = new Image();
                    image.setImg_album_id(cursor.getInt(1));
                    image.setImg_height(cursor.getInt(2));
                    image.setImg_id(cursor.getInt(3));
                    image.setImg_owner_id(cursor.getInt(4));
                    image.setImg_position(cursor.getInt(5));
                    image.setImg_width(cursor.getInt(6));
                    image.setTotal_img_same_al(cursor.getInt(7));
                    image.setImg_created_at(cursor.getString(8));
                    image.setImg_file_url(cursor.getString(9));
                    image.setImg_tags(cursor.getString(10));
                    image.setImg_updated_at(cursor.getString(11));
                    image.setAlb_name(cursor.getString(12));
                    image.setImg_uuid(cursor.getString(13));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    images.add(image);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return images;
    }
    public List<PositionJobModel> getAllPositionJobs() {
        ArrayList<PositionJobModel> positionJobModels = new ArrayList<PositionJobModel>();
        // Select All Query
        String selectQuery = "Select * from positionjobs ORDER BY pos_name DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    PositionJobModel positionJobModel = new PositionJobModel();
                    positionJobModel.setPos_id(cursor.getInt(1));
                    positionJobModel.setPos_is_active(cursor.getInt(2));
                    positionJobModel.setPos_name(cursor.getString(3));
                    positionJobModel.setPos_value(cursor.getString(4));
                    positionJobModel.setPos_description(cursor.getString(5));
                    positionJobModel.setPos_created_at(cursor.getString(6));
                    positionJobModel.setPos_updated_at(cursor.getString(7));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    positionJobModels.add(positionJobModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return positionJobModels;
    }

    public List<LocationModel> getAllLocation() {
        ArrayList<LocationModel> locationModels = new ArrayList<LocationModel>();
        // Select All Query
        String selectQuery = "Select * from location ORDER BY locat_name DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    LocationModel locationModel = new LocationModel();
                    locationModel.setLocat_id(cursor.getInt(1));
                    locationModel.setLocat_is_active(cursor.getInt(2));
                    locationModel.setLocat_name(cursor.getString(3));
                    locationModel.setLocat_value(cursor.getString(4));
                    locationModel.setLocat_description(cursor.getString(5));
                    locationModel.setLocat_created_at(cursor.getString(6));
                    locationModel.setLocat_updated_at(cursor.getString(7));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    locationModels.add(locationModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return locationModels;
    }

    public List<LanguageModel> getAllLanguage() {
        ArrayList<LanguageModel> languageModels = new ArrayList<LanguageModel>();
        // Select All Query
        String selectQuery = "Select * from language ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    LanguageModel languageModel = new LanguageModel();
                    languageModel.setLang_id(cursor.getInt(1));
                    languageModel.setLang_name(cursor.getString(2));
                    languageModel.setLang_code(cursor.getString(3));
                    languageModel.setLang_created_at(cursor.getString(4));
                    languageModel.setLang_updated_at(cursor.getString(5));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    languageModels.add(languageModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return languageModels;
    }

    public List<PositionTimelineModel> getAllPositionTimeline() {
        ArrayList<PositionTimelineModel> positionTimelineModels = new ArrayList<PositionTimelineModel>();
        // Select All Query
        String selectQuery = "Select * from positiontime ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    PositionTimelineModel positionTimelineModel = new PositionTimelineModel();
                    positionTimelineModel.setUser_id(cursor.getInt(1));
                    positionTimelineModel.setPosition_id(cursor.getInt(2));
                    positionTimelineModel.setStart_time(cursor.getString(3));
                    positionTimelineModel.setEnd_time(cursor.getString(4));
                    positionTimelineModel.setPosition_name(cursor.getString(5));
                    positionTimelineModel.setPosition_value(cursor.getString(6));
                    positionTimelineModel.setDiff_time(cursor.getString(7));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    positionTimelineModels.add(positionTimelineModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return positionTimelineModels;
    }

    public List<CandidateJobModel> getAllCandidateJob() {
        ArrayList<CandidateJobModel> candidateJobModels = new ArrayList<CandidateJobModel>();
        // Select All Query
        String selectQuery = "Select * from candidatejob ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    CandidateJobModel candidateJobModel = new CandidateJobModel();
                    candidateJobModel.setJob_position(cursor.getString(1));
                    candidateJobModel.setJob_salary(cursor.getString(2));
                    candidateJobModel.setCom_avatar_url(cursor.getString(3));
                    candidateJobModel.setJob_work_location(cursor.getString(4));
                    candidateJobModel.setJob_time_type(cursor.getString(5));
                    candidateJobModel.setCom_name(cursor.getString(6));
                    candidateJobModel.setCanjob_candidate_id(cursor.getString(7));
                    candidateJobModel.setCanjob_result_status(cursor.getString(8));
                    candidateJobModel.setCanjob_is_apply(cursor.getString(9));
                    candidateJobModel.setJob_id(cursor.getString(10));
                    candidateJobModel.setJob_title(cursor.getString(11));
                    candidateJobModel.setJob_updated_at(cursor.getString(12));
                    candidateJobModel.setIs_user_saved(cursor.getString(13));
                    candidateJobModels.add(candidateJobModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return candidateJobModels;
    }

    public List<SearchJobModel> getAllJobSearch() {
        ArrayList<SearchJobModel> searchJobModels = new ArrayList<SearchJobModel>();
        // Select All Query
        String selectQuery = "Select * from jobSearch ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    SearchJobModel searchJobModel = new SearchJobModel();
                    searchJobModel.setJob_id(cursor.getString(1));
                    searchJobModel.setJob_title(cursor.getString(2));
                    searchJobModel.setJob_updated_at(cursor.getString(3));
                    searchJobModel.setProvince_value(cursor.getString(4));
                    searchJobModel.setJob_salary(cursor.getString(5));
                    searchJobModel.setJob_time_type(cursor.getString(6));
                    searchJobModels.add(searchJobModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return searchJobModels;
    }
    public List<CandidateJobModel> getAllCandidateJobByStatus(String canjob_result_status) {
        ArrayList<CandidateJobModel> candidateJobModels = new ArrayList<CandidateJobModel>();
        // Select All Query
        String selectQuery = "Select * from candidatejob WHERE canjob_result_status = " + canjob_result_status + "ORDER BY _id DESC ";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    CandidateJobModel candidateJobModel = new CandidateJobModel();
                    candidateJobModel.setJob_position(cursor.getString(1));
                    candidateJobModel.setJob_salary(cursor.getString(2));
                    candidateJobModel.setCom_avatar_url(cursor.getString(3));
                    candidateJobModel.setJob_work_location(cursor.getString(4));
                    candidateJobModel.setJob_time_type(cursor.getString(5));
                    candidateJobModel.setCom_name(cursor.getString(6));
                    candidateJobModel.setCanjob_candidate_id(cursor.getString(7));
                    candidateJobModel.setCanjob_result_status(cursor.getString(8));
                    candidateJobModel.setCanjob_is_apply(cursor.getString(9));
                    candidateJobModel.setJob_id(cursor.getString(10));
                    candidateJobModel.setJob_title(cursor.getString(11));
                    candidateJobModel.setJob_updated_at(cursor.getString(12));
                    candidateJobModel.setIs_user_saved(cursor.getString(13));
                    candidateJobModels.add(candidateJobModel);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return candidateJobModels;
    }

    public List<Image> getImageInAlbum(int img_album_id) {
        ArrayList<Image> images = new ArrayList<Image>();
        // Select All Query
        String selectQuery = "Select * from image WHERE img_album_id = " + img_album_id + " ORDER BY _id DESC ;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    Image image = new Image();
                    image.setImg_album_id(cursor.getInt(1));
                    image.setImg_height(cursor.getInt(2));
                    image.setImg_id(cursor.getInt(3));
                    image.setImg_owner_id(cursor.getInt(4));
                    image.setImg_position(cursor.getInt(5));
                    image.setImg_width(cursor.getInt(6));
                    image.setTotal_img_same_al(cursor.getInt(7));
                    image.setImg_created_at(cursor.getString(8));
                    image.setImg_file_url(cursor.getString(9));
                    image.setImg_tags(cursor.getString(10));
                    image.setImg_updated_at(cursor.getString(11));
                    image.setAlb_name(cursor.getString(12));
                    image.setImg_uuid(cursor.getString(13));
//                videoActivity.setImage(cursor.getString(5));
                    // Adding contact to list
                    images.add(image);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return images;
    }

    //    public void insertAVideo(String name, String path_internal, String path_external, String thumb,
//                             int duration, int size, int status, int create_date) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "Insert into video(name,path_internal,path_external,thumb,duration,size,status,create_date) values " +
//                "( "
//                + name + ", "
//                + path_internal + ", "
//                + path_external + ", "
//                + thumb + ", "
//                + duration + ", "
//                + size + ","
//                + status + ","
//                + create_date + ");";
//        db.execSQL(query);
//        close();
//    }
//
//    public void updateUpload(int video_id, int status) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "UPDATE video SET status = " + status + " WHERE video_id = " + video_id + ";";
//        db.execSQL(query);
//        close();
//    }
//
//
    public int getTotalImage(int album_id) {
        ArrayList<Integer> imageId = new ArrayList<>();
        // Select All Query
        String selectQuery = "Select img_id from image where img_album_id = "+album_id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    int a = cursor.getInt(0);
                    imageId.add(a);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        close();
        return imageId.size();
    }
    //
//    public void deleteAVideo(int video_id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "Delete from video Where video_id = " + video_id + ";";
//        db.execSQL(query);
//        close();
//    }
    public void deleteAllAlbum(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from image";
        db.execSQL(sql);
        close();
    }
    public void deleteAllRecruitment(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from recruitment";
        db.execSQL(sql);
        close();
    }
    public void deleteARecruitmentByPosId(String job_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from recruitment WHERE job_id = " + job_id + ";";
        db.execSQL(sql);
        close();
    }
    public void deleteAllEducation(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from education";
        db.execSQL(sql);
        close();
    }
    public void deleteAllExperices(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from experiences";
        db.execSQL(sql);
        close();
    }
    public void deleteAllPositionTimeline(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from positiontime";
        db.execSQL(sql);
        close();
    }
    public void deleteAllPositionJobs(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from positionjobs";
        db.execSQL(sql);
        close();
    }
    public void deleteAllLocation(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from location";
        db.execSQL(sql);
        close();
    }
    public void deleteAllLanguage(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from language";
        db.execSQL(sql);
        close();
    }
    public void deleteAllExpectJob(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from expectjob";
        db.execSQL(sql);
        close();
    }
    public void deleteAllCandicateJob(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from candidatejob";
        db.execSQL(sql);
        close();
    }
    public void deleteAllRecruiter(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from recruiter";
        db.execSQL(sql);
        close();
    }
    public void deleteAllQuanLyUngVien(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from quanlyungvien";
        db.execSQL(sql);
        close();
    }
    public void deleteAllJobSearch(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from jobSearch";
        db.execSQL(sql);
        close();
    }
    public void deleteAllNotification(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from notification";
        db.execSQL(sql);
        close();
    }
    public void deleteAllSoKhop(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from sokhop";
        db.execSQL(sql);
        close();
    }
    public void deleteAlbum(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from image where img_album_id=" + id;
        db.execSQL(sql);
        close();
    }
    public void deleteImageById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from image where img_id=" + id;
        db.execSQL(sql);
        close();
    }
    public void deleteAllExcludesJob() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from excludesjob";
        db.execSQL(sql);
        close();
    }
//
//    public String SelectImageAVideo() {
//        String query = "Select * from video where video_id = (SELECT Max(video_id) FROM video)";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
////        String ImageLink = cursor.getString(4);
////        Log.e("ImageLink",ImageLink);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                ImageLink = new String();
//                ImageLink = cursor.getString(4);
//            } while (cursor.moveToNext());
//        }
//        return ImageLink;
//    }
//
//    public int SelectStatusVideo(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        int status = 0;
//        String selectQuery = "Select status from video where video_id =" + id;
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        try {
//            if (cursor.moveToFirst()) {
//                do {
//                    status = cursor.getInt(0);
//                } while (cursor.moveToNext());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            cursor.close();
//        }
//        close();
//        return status;
//    }

}
