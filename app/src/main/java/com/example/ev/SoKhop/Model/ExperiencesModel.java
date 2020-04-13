package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/13/2016.
 */

public class ExperiencesModel {
    public int uex_user_id,pos_id,uex_company_id,upos_year_of_working;
    public String uex_skills,pos_name,pos_value,pos_description,uex_company_name,uex_img_url
            ,uex_video_url,uex_description,uex_start_time,uex_end_time,uex_id;
    public ExperiencesModel(int uex_user_id,int pos_id,int uex_company_id,int upos_year_of_working,String uex_skills,String pos_name,String pos_value,String pos_description
            ,String uex_company_name,String uex_img_url,String uex_video_url,String uex_description,String uex_start_time,String uex_end_time,String uex_id){
        this.uex_user_id = uex_user_id;
        this.pos_id = pos_id;
        this.uex_company_id = uex_company_id;
        this.upos_year_of_working = upos_year_of_working;
        this.uex_skills = uex_skills;
        this.pos_name = pos_name;
        this.pos_value = pos_value;
        this.pos_description = pos_description;
        this.uex_company_name = uex_company_name;
        this.uex_img_url = uex_img_url;
        this.uex_video_url = uex_video_url;
        this.uex_description = uex_description;
        this.uex_start_time = uex_start_time;
        this.uex_end_time = uex_end_time;
        this.uex_id = uex_id;
    }
    public ExperiencesModel() {

    }

    public String getUex_id() {
        return uex_id;
    }

    public void setUex_id(String uex_id) {
        this.uex_id = uex_id;
    }

    public int getUex_user_id() {
        return uex_user_id;
    }

    public void setUex_user_id(int uex_user_id) {
        this.uex_user_id = uex_user_id;
    }

    public int getPos_id() {
        return pos_id;
    }

    public void setPos_id(int pos_id) {
        this.pos_id = pos_id;
    }

    public int getUex_company_id() {
        return uex_company_id;
    }

    public void setUex_company_id(int uex_company_id) {
        this.uex_company_id = uex_company_id;
    }

    public int getUpos_year_of_working() {
        return upos_year_of_working;
    }

    public void setUpos_year_of_working(int upos_year_of_working) {
        this.upos_year_of_working = upos_year_of_working;
    }

    public String getUex_skills() {
        return uex_skills;
    }

    public void setUex_skills(String uex_skills) {
        this.uex_skills = uex_skills;
    }

    public String getPos_name() {
        return pos_name;
    }

    public void setPos_name(String pos_name) {
        this.pos_name = pos_name;
    }

    public String getPos_description() {
        return pos_description;
    }

    public void setPos_description(String pos_description) {
        this.pos_description = pos_description;
    }

    public String getPos_value() {
        return pos_value;
    }

    public void setPos_value(String pos_value) {
        this.pos_value = pos_value;
    }

    public String getUex_company_name() {
        return uex_company_name;
    }

    public void setUex_company_name(String uex_company_name) {
        this.uex_company_name = uex_company_name;
    }

    public String getUex_img_url() {
        return uex_img_url;
    }

    public void setUex_img_url(String uex_img_url) {
        this.uex_img_url = uex_img_url;
    }

    public String getUex_video_url() {
        return uex_video_url;
    }

    public void setUex_video_url(String uex_video_url) {
        this.uex_video_url = uex_video_url;
    }

    public String getUex_description() {
        return uex_description;
    }

    public void setUex_description(String uex_description) {
        this.uex_description = uex_description;
    }

    public String getUex_start_time() {
        return uex_start_time;
    }

    public void setUex_start_time(String uex_start_time) {
        this.uex_start_time = uex_start_time;
    }

    public String getUex_end_time() {
        return uex_end_time;
    }

    public void setUex_end_time(String uex_end_time) {
        this.uex_end_time = uex_end_time;
    }
}
