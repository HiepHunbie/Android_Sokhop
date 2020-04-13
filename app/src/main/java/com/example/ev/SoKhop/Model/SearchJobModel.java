package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/27/2016.
 */

public class SearchJobModel {
    public String job_id,job_title,job_updated_at,province_value,job_salary,
            job_time_type;
    public SearchJobModel(String job_id,String job_title,String job_updated_at,
                              String province_value,String job_salary,String job_time_type){
        this.job_id = job_id;
        this.job_title = job_title;
        this.job_updated_at = job_updated_at;
        this.province_value = province_value;
        this.job_salary = job_salary;
        this.job_time_type = job_time_type;
    }
    public SearchJobModel() {

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

    public String getProvince_value() {
        return province_value;
    }

    public void setProvince_value(String province_value) {
        this.province_value = province_value;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public String getJob_time_type() {
        return job_time_type;
    }

    public void setJob_time_type(String job_time_type) {
        this.job_time_type = job_time_type;
    }
}
