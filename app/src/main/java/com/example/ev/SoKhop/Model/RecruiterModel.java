package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/24/2016.
 */

public class RecruiterModel {
    public String first_name,last_name,avatar_url,address,pos_name,
            pos_value,update_time,recruiter_id,user_id,exp_year,
            time_type,candidate_is_saved;
    public RecruiterModel(String first_name,String last_name,String avatar_url,String address,
                             String pos_name,String pos_value,String update_time,String recruiter_id,
                             String user_id,String exp_year,
                             String time_type,String candidate_is_saved){
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar_url = avatar_url;
        this.address = address;
        this.pos_name = pos_name;
        this.pos_value = pos_value;
        this.update_time = update_time;
        this.recruiter_id = recruiter_id;
        this.user_id = user_id;
        this.exp_year = exp_year;
        this.time_type = time_type;
        this.candidate_is_saved = candidate_is_saved;
    }
    public RecruiterModel() {

    }

    public String getCandidate_is_saved() {
        return candidate_is_saved;
    }

    public void setCandidate_is_saved(String candidate_is_saved) {
        this.candidate_is_saved = candidate_is_saved;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPos_name() {
        return pos_name;
    }

    public void setPos_name(String pos_name) {
        this.pos_name = pos_name;
    }

    public String getPos_value() {
        return pos_value;
    }

    public void setPos_value(String pos_value) {
        this.pos_value = pos_value;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getExp_year() {
        return exp_year;
    }

    public void setExp_year(String exp_year) {
        this.exp_year = exp_year;
    }

    public String getTime_type() {
        return time_type;
    }

    public void setTime_type(String time_type) {
        this.time_type = time_type;
    }
}
