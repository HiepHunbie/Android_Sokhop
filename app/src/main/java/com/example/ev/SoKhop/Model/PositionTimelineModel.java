package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/13/2016.
 */

public class PositionTimelineModel {
    public int user_id,position_id;
    public String start_time,img_file_url,end_time,position_name,position_value,diff_time;
    public PositionTimelineModel(int user_id,int position_id,String start_time,String img_file_url
            ,String end_time,String position_name,String position_value,String diff_time){
        this.user_id = user_id;
        this.position_id = position_id;
        this.start_time = start_time;
        this.img_file_url = img_file_url;
        this.end_time = end_time;
        this.position_name = position_name;
        this.position_value = position_value;
        this.diff_time = diff_time;
    }
    public PositionTimelineModel() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getImg_file_url() {
        return img_file_url;
    }

    public void setImg_file_url(String img_file_url) {
        this.img_file_url = img_file_url;
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

    public String getDiff_time() {
        return diff_time;
    }

    public void setDiff_time(String diff_time) {
        this.diff_time = diff_time;
    }
}
