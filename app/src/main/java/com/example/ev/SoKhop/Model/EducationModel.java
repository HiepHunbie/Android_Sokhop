package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/12/2016.
 */

public class EducationModel {
    public int schm_id,upe_user_id,upe_id;
    public String maj_name,sch_name,upe_video_url,upe_img_url,upe_start_time,upe_end_time,upe_description,upe_degree;
    public EducationModel(int schm_id,int upe_user_id,String upe_degree,int upe_id,String maj_name,String sch_name,String upe_video_url,String upe_img_url,String upe_start_time,String upe_end_time,String upe_description){
        this.schm_id = schm_id;
        this.upe_user_id = upe_user_id;
        this.upe_degree = upe_degree;
        this.upe_id = upe_id;
        this.maj_name = maj_name;
        this.sch_name = sch_name;
        this.upe_video_url = upe_video_url;
        this.upe_img_url = upe_img_url;
        this.upe_start_time = upe_start_time;
        this.upe_end_time = upe_end_time;
        this.upe_description = upe_description;
    }
    public EducationModel() {

    }

    public String getUpe_description() {
        return upe_description;
    }

    public void setUpe_description(String upe_description) {
        this.upe_description = upe_description;
    }

    public int getSchm_id() {
        return schm_id;
    }

    public void setSchm_id(int schm_id) {
        this.schm_id = schm_id;
    }

    public int getUpe_user_id() {
        return upe_user_id;
    }

    public void setUpe_user_id(int upe_user_id) {
        this.upe_user_id = upe_user_id;
    }

    public String getUpe_degree() {
        return upe_degree;
    }

    public void setUpe_degree(String upe_degree) {
        this.upe_degree = upe_degree;
    }

    public int getUpe_id() {
        return upe_id;
    }

    public void setUpe_id(int upe_id) {
        this.upe_id = upe_id;
    }

    public String getMaj_name() {
        return maj_name;
    }

    public void setMaj_name(String maj_name) {
        this.maj_name = maj_name;
    }

    public String getUpe_video_url() {
        return upe_video_url;
    }

    public void setUpe_video_url(String upe_video_url) {
        this.upe_video_url = upe_video_url;
    }

    public String getSch_name() {
        return sch_name;
    }

    public void setSch_name(String sch_name) {
        this.sch_name = sch_name;
    }

    public String getUpe_img_url() {
        return upe_img_url;
    }

    public void setUpe_img_url(String upe_img_url) {
        this.upe_img_url = upe_img_url;
    }

    public String getUpe_start_time() {
        return upe_start_time;
    }

    public void setUpe_start_time(String upe_start_time) {
        this.upe_start_time = upe_start_time;
    }

    public String getUpe_end_time() {
        return upe_end_time;
    }

    public void setUpe_end_time(String upe_end_time) {
        this.upe_end_time = upe_end_time;
    }
}
