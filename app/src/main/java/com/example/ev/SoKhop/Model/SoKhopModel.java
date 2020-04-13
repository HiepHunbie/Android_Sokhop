package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 11/2/2016.
 */

public class SoKhopModel {
    public String job_title,job_work_location,job_time_type,candidates,job_views_number,
            job_apply_number,job_updated_at,job_expired_time;
    public SoKhopModel(String job_title,String job_work_location,String job_time_type,
                          String candidates,String job_views_number,String job_apply_number,String job_updated_at,String job_expired_time){
        this.job_title = job_title;
        this.job_work_location = job_work_location;
        this.job_time_type = job_time_type;
        this.candidates = candidates;
        this.job_views_number = job_views_number;
        this.job_apply_number = job_apply_number;
        this.job_updated_at = job_updated_at;
        this.job_expired_time = job_expired_time;
    }
    public SoKhopModel() {

    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
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

    public String getCandidates() {
        return candidates;
    }

    public void setCandidates(String candidates) {
        this.candidates = candidates;
    }

    public String getJob_views_number() {
        return job_views_number;
    }

    public void setJob_views_number(String job_views_number) {
        this.job_views_number = job_views_number;
    }

    public String getJob_apply_number() {
        return job_apply_number;
    }

    public void setJob_apply_number(String job_apply_number) {
        this.job_apply_number = job_apply_number;
    }

    public String getJob_updated_at() {
        return job_updated_at;
    }

    public void setJob_updated_at(String job_updated_at) {
        this.job_updated_at = job_updated_at;
    }

    public String getJob_expired_time() {
        return job_expired_time;
    }

    public void setJob_expired_time(String job_expired_time) {
        this.job_expired_time = job_expired_time;
    }
}
