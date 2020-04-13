package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/14/2016.
 */

public class PositionJobModel {
    public int pos_id,pos_is_active;
    public String pos_name,pos_value,pos_description,pos_created_at,pos_updated_at;
    public PositionJobModel(int pos_id,int pos_is_active,String pos_name,String pos_value
            ,String pos_description,String pos_created_at,String pos_updated_at){
        this.pos_id = pos_id;
        this.pos_is_active = pos_is_active;
        this.pos_name = pos_name;
        this.pos_value = pos_value;
        this.pos_description = pos_description;
        this.pos_created_at = pos_created_at;
        this.pos_updated_at = pos_updated_at;
    }
    public PositionJobModel() {

    }

    public int getPos_id() {
        return pos_id;
    }

    public void setPos_id(int pos_id) {
        this.pos_id = pos_id;
    }

    public int getPos_is_active() {
        return pos_is_active;
    }

    public void setPos_is_active(int pos_is_active) {
        this.pos_is_active = pos_is_active;
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

    public String getPos_description() {
        return pos_description;
    }

    public void setPos_description(String pos_description) {
        this.pos_description = pos_description;
    }

    public String getPos_created_at() {
        return pos_created_at;
    }

    public void setPos_created_at(String pos_created_at) {
        this.pos_created_at = pos_created_at;
    }

    public String getPos_updated_at() {
        return pos_updated_at;
    }

    public void setPos_updated_at(String pos_updated_at) {
        this.pos_updated_at = pos_updated_at;
    }
}
