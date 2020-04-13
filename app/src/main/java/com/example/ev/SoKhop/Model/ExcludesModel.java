package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 11/7/2016.
 */

public class ExcludesModel {
    public String jexca_id,jexca_name,jexca_description,jexca_created_at,jexca_updated_at;
    public ExcludesModel(String jexca_id,String jexca_name,String jexca_description,String jexca_created_at,String jexca_updated_at){
        this.jexca_id = jexca_id;
        this.jexca_name = jexca_name;
        this.jexca_description = jexca_description;
        this.jexca_created_at = jexca_created_at;
        this.jexca_updated_at = jexca_updated_at;
    }
    public ExcludesModel() {

    }

    public String getJexca_id() {
        return jexca_id;
    }

    public void setJexca_id(String jexca_id) {
        this.jexca_id = jexca_id;
    }

    public String getJexca_name() {
        return jexca_name;
    }

    public void setJexca_name(String jexca_name) {
        this.jexca_name = jexca_name;
    }

    public String getJexca_description() {
        return jexca_description;
    }

    public void setJexca_description(String jexca_description) {
        this.jexca_description = jexca_description;
    }

    public String getJexca_created_at() {
        return jexca_created_at;
    }

    public void setJexca_created_at(String jexca_created_at) {
        this.jexca_created_at = jexca_created_at;
    }

    public String getJexca_updated_at() {
        return jexca_updated_at;
    }

    public void setJexca_updated_at(String jexca_updated_at) {
        this.jexca_updated_at = jexca_updated_at;
    }
}
