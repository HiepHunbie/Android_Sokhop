package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/20/2016.
 */

public class CandidateJobModel {
    public String job_position,job_salary,com_avatar_url,job_work_location,
    job_time_type,com_name,canjob_candidate_id,canjob_result_status,canjob_is_apply,
            job_id,job_title,job_updated_at,is_user_saved;
    public CandidateJobModel(String job_position,String job_salary,String com_avatar_url,
                             String job_work_location,String job_time_type,String com_name,String canjob_candidate_id,
                             String canjob_result_status,String canjob_is_apply,
                             String job_id,String job_title,String job_updated_at,String is_user_saved){
        this.job_position = job_position;
        this.job_salary = job_salary;
        this.com_avatar_url = com_avatar_url;
        this.job_work_location = job_work_location;
        this.job_time_type = job_time_type;
        this.com_name = com_name;
        this.canjob_candidate_id = canjob_candidate_id;
        this.canjob_result_status = canjob_result_status;
        this.canjob_is_apply = canjob_is_apply;
        this.job_id = job_id;
        this.job_title = job_title;
        this.job_updated_at = job_updated_at;
        this.is_user_saved = is_user_saved;
    }
    public CandidateJobModel() {

    }

    public String getIs_user_saved() {
        return is_user_saved;
    }

    public void setIs_user_saved(String is_user_saved) {
        this.is_user_saved = is_user_saved;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_updated_at() {
        return job_updated_at;
    }

    public void setJob_updated_at(String job_updated_at) {
        this.job_updated_at = job_updated_at;
    }

    public String getJob_position() {
        return job_position;
    }

    public void setJob_position(String job_position) {
        this.job_position = job_position;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public String getCom_avatar_url() {
        return com_avatar_url;
    }

    public void setCom_avatar_url(String com_avatar_url) {
        this.com_avatar_url = com_avatar_url;
    }

    public String getJob_work_location() {
        return job_work_location;
    }

    public void setJob_work_location(String job_work_location) {
        this.job_work_location = job_work_location;
    }

    public String getJob_time_type() {
        return job_time_type;
    }

    public void setJob_time_type(String job_time_type) {
        this.job_time_type = job_time_type;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getCanjob_candidate_id() {
        return canjob_candidate_id;
    }

    public void setCanjob_candidate_id(String canjob_candidate_id) {
        this.canjob_candidate_id = canjob_candidate_id;
    }

    public String getCanjob_result_status() {
        return canjob_result_status;
    }

    public void setCanjob_result_status(String canjob_result_status) {
        this.canjob_result_status = canjob_result_status;
    }

    public String getCanjob_is_apply() {
        return canjob_is_apply;
    }

    public void setCanjob_is_apply(String canjob_is_apply) {
        this.canjob_is_apply = canjob_is_apply;
    }
}
