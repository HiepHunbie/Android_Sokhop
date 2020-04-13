package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/14/2016.
 */

public class LocationModel {
    public int locat_id,locat_is_active;
    public String locat_name,locat_value,locat_description,locat_created_at,locat_updated_at;
    public LocationModel(int locat_id,int locat_is_active,String locat_name,String locat_value
            ,String locat_description,String locat_created_at,String locat_updated_at){
        this.locat_id = locat_id;
        this.locat_is_active = locat_is_active;
        this.locat_name = locat_name;
        this.locat_value = locat_value;
        this.locat_description = locat_description;
        this.locat_created_at = locat_created_at;
        this.locat_updated_at = locat_updated_at;
    }
    public LocationModel() {

    }

    public int getLocat_id() {
        return locat_id;
    }

    public void setLocat_id(int locat_id) {
        this.locat_id = locat_id;
    }

    public int getLocat_is_active() {
        return locat_is_active;
    }

    public void setLocat_is_active(int locat_is_active) {
        this.locat_is_active = locat_is_active;
    }

    public String getLocat_name() {
        return locat_name;
    }

    public void setLocat_name(String locat_name) {
        this.locat_name = locat_name;
    }

    public String getLocat_value() {
        return locat_value;
    }

    public void setLocat_value(String locat_value) {
        this.locat_value = locat_value;
    }

    public String getLocat_description() {
        return locat_description;
    }

    public void setLocat_description(String locat_description) {
        this.locat_description = locat_description;
    }

    public String getLocat_created_at() {
        return locat_created_at;
    }

    public void setLocat_created_at(String locat_created_at) {
        this.locat_created_at = locat_created_at;
    }

    public String getLocat_updated_at() {
        return locat_updated_at;
    }

    public void setLocat_updated_at(String locat_updated_at) {
        this.locat_updated_at = locat_updated_at;
    }
}
