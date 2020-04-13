package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/26/2016.
 */

public class QuanLyUngVienModel {
    public String upb_address,upb_avatar_url,canjob_test_result,canjob_is_apply,
            canjob_result_status,canjob_updated_at,canjob_candidate_id,canjob_job_id,canjob_test_score,
            user_id,start_time,end_time,position_name,position_value,
            position_id,diff_time,uej_time_type,u_email,u_first_name,
            u_last_name,u_middle_name,u_full_name,u_phone,canjob_final_result;
    public QuanLyUngVienModel(String upb_address,String upb_avatar_url,String canjob_test_result,
                             String canjob_is_apply,String canjob_result_status,String canjob_updated_at
                            ,String canjob_candidate_id, String canjob_job_id,String canjob_test_score,
                             String user_id,String start_time,String end_time,String position_name,
                              String position_value,String position_id,String diff_time,String uej_time_type,
                              String u_email,String u_first_name,
                              String u_last_name,String u_middle_name,String u_full_name,String u_phone,String canjob_final_result){
        this.upb_address = upb_address;
        this.upb_avatar_url = upb_avatar_url;
        this.canjob_test_result = canjob_test_result;
        this.canjob_is_apply = canjob_is_apply;
        this.canjob_result_status = canjob_result_status;
        this.canjob_updated_at = canjob_updated_at;
        this.canjob_candidate_id = canjob_candidate_id;
        this.canjob_job_id = canjob_job_id;
        this.canjob_test_score = canjob_test_score;
        this.user_id = user_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.position_name = position_name;
        this.position_value = position_value;
        this.position_id = position_id;
        this.diff_time = diff_time;
        this.uej_time_type = uej_time_type;
        this.u_email = u_email;
        this.u_first_name = u_first_name;
        this.u_last_name = u_last_name;
        this.u_middle_name = u_middle_name;
        this.u_full_name = u_full_name;
        this.u_phone = u_phone;
        this.canjob_final_result = canjob_final_result;
    }
    public QuanLyUngVienModel() {

    }

    public String getCanjob_test_result() {
        return canjob_test_result;
    }

    public void setCanjob_test_result(String canjob_test_result) {
        this.canjob_test_result = canjob_test_result;
    }

    public String getCanjob_test_score() {
        return canjob_test_score;
    }

    public void setCanjob_test_score(String canjob_test_score) {
        this.canjob_test_score = canjob_test_score;
    }

    public String getCanjob_final_result() {
        return canjob_final_result;
    }

    public void setCanjob_final_result(String canjob_final_result) {
        this.canjob_final_result = canjob_final_result;
    }

    public String getUpb_address() {
        return upb_address;
    }

    public void setUpb_address(String upb_address) {
        this.upb_address = upb_address;
    }

    public String getUpb_avatar_url() {
        return upb_avatar_url;
    }

    public void setUpb_avatar_url(String upb_avatar_url) {
        this.upb_avatar_url = upb_avatar_url;
    }


    public String getCanjob_is_apply() {
        return canjob_is_apply;
    }

    public void setCanjob_is_apply(String canjob_is_apply) {
        this.canjob_is_apply = canjob_is_apply;
    }

    public String getCanjob_result_status() {
        return canjob_result_status;
    }

    public void setCanjob_result_status(String canjob_result_status) {
        this.canjob_result_status = canjob_result_status;
    }

    public String getCanjob_updated_at() {
        return canjob_updated_at;
    }

    public void setCanjob_updated_at(String canjob_updated_at) {
        this.canjob_updated_at = canjob_updated_at;
    }

    public String getCanjob_candidate_id() {
        return canjob_candidate_id;
    }

    public void setCanjob_candidate_id(String canjob_candidate_id) {
        this.canjob_candidate_id = canjob_candidate_id;
    }

    public String getCanjob_job_id() {
        return canjob_job_id;
    }

    public void setCanjob_job_id(String canjob_job_id) {
        this.canjob_job_id = canjob_job_id;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPosition_value() {
        return position_value;
    }

    public void setPosition_value(String position_value) {
        this.position_value = position_value;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getDiff_time() {
        return diff_time;
    }

    public void setDiff_time(String diff_time) {
        this.diff_time = diff_time;
    }

    public String getUej_time_type() {
        return uej_time_type;
    }

    public void setUej_time_type(String uej_time_type) {
        this.uej_time_type = uej_time_type;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_first_name() {
        return u_first_name;
    }

    public void setU_first_name(String u_first_name) {
        this.u_first_name = u_first_name;
    }

    public String getU_last_name() {
        return u_last_name;
    }

    public void setU_last_name(String u_last_name) {
        this.u_last_name = u_last_name;
    }

    public String getU_middle_name() {
        return u_middle_name;
    }

    public void setU_middle_name(String u_middle_name) {
        this.u_middle_name = u_middle_name;
    }

    public String getU_full_name() {
        return u_full_name;
    }

    public void setU_full_name(String u_full_name) {
        this.u_full_name = u_full_name;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }
}
