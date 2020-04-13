package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/14/2016.
 */

public class LanguageModel {
    public int lang_id;
    public String lang_name,lang_code,lang_created_at,lang_updated_at;
    public LanguageModel(int lang_id,String lang_name,String lang_code
            ,String lang_created_at,String lang_updated_at){
        this.lang_id = lang_id;
        this.lang_name = lang_name;
        this.lang_code = lang_code;
        this.lang_created_at = lang_created_at;
        this.lang_updated_at = lang_updated_at;
    }
    public LanguageModel() {

    }

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    public String getLang_name() {
        return lang_name;
    }

    public void setLang_name(String lang_name) {
        this.lang_name = lang_name;
    }

    public String getLang_code() {
        return lang_code;
    }

    public void setLang_code(String lang_code) {
        this.lang_code = lang_code;
    }

    public String getLang_created_at() {
        return lang_created_at;
    }

    public void setLang_created_at(String lang_created_at) {
        this.lang_created_at = lang_created_at;
    }

    public String getLang_updated_at() {
        return lang_updated_at;
    }

    public void setLang_updated_at(String lang_updated_at) {
        this.lang_updated_at = lang_updated_at;
    }
}
