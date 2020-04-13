package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/17/2016.
 */

public class RecruitmentModel {
    public String job_id,job_company_id,job_owner_id,jca_id,job_position_id,pos_id,job_quantity,
            job_time_type,job_range_salary_id,job_gender,job_range_allowance_id,job_range_bonus_id,job_status
            ,job_isactive,lang_id;
    public String job_title,jca_name,jca_value,jca_tags,jca_description,jca_created_at,jca_updated_at
            ,pos_name,pos_value,job_views_number,job_apply_number,job_level_id,job_salary,job_work_location,job_description
            ,job_allowance,job_bonus,job_extra_desc,job_expired_time,job_language_profile,job_album_img_id,job_album_contract_id
            ,job_skills,job_year_exps,job_created_at,job_updated_at,lang_name,lang_code,lang_created_at,lang_updated_at
            ,job_exclude_condition,job_require_condition,job_location,job_welfare,job_contact
            ,owner_id,owner_avatar,owner_name,owner_website,owner_phone,is_user_saved,number_matching,is_user_apply;
    public RecruitmentModel(String job_id,String job_company_id,String job_owner_id,String jca_id,String job_position_id,String pos_id,String job_quantity,
                            String job_time_type,String job_range_salary_id,String job_gender,String job_range_allowance_id,String job_range_bonus_id,String job_status,String job_isactive,
                            String lang_id,String job_title,String jca_name,String jca_value,String jca_tags,String jca_description,String jca_created_at,String jca_updated_at
            ,String pos_name,String pos_value,String job_views_number,String job_apply_number,String job_level_id,String job_salary,String job_work_location
            ,String job_description,String job_allowance,String job_bonus,String job_extra_desc,String job_expired_time,String job_language_profile,String job_album_img_id
            ,String job_album_contract_id,String job_skills,String job_year_exps,String job_created_at,String job_updated_at,String lang_name,String lang_code
            ,String lang_created_at,String lang_updated_at,String job_exclude_condition,String job_require_condition,String job_location,String job_welfare,String job_contact
            ,String owner_id,String owner_avatar,String owner_name,String owner_website,String owner_phone,String is_user_saved,String number_matching
    ,String is_user_apply){
        this.number_matching = number_matching;
        this.is_user_apply = is_user_apply;
        this.is_user_saved = is_user_saved;
        this.owner_id = owner_id;
        this.owner_avatar = owner_avatar;
        this.owner_name = owner_name;
        this.owner_website = owner_website;
        this.owner_phone = owner_phone;
        this.job_contact = job_contact;
        this.job_welfare = job_welfare;
        this.job_allowance = job_allowance;
        this.job_bonus = job_bonus;
        this.job_extra_desc = job_extra_desc;
        this.job_expired_time = job_expired_time;
        this.job_language_profile = job_language_profile;
        this.job_album_img_id = job_album_img_id;
        this.job_album_contract_id = job_album_contract_id;
        this.job_level_id = job_level_id;
        this.job_salary = job_salary;
        this.job_work_location = job_work_location;
        this.job_description = job_description;
        this.job_skills = job_skills;
        this.job_year_exps = job_year_exps;
        this.job_created_at = job_created_at;
        this.job_updated_at = job_updated_at;
        this.lang_name = lang_name;
        this.lang_code = lang_code;
        this.lang_created_at = lang_created_at;
        this.lang_updated_at = lang_updated_at;
        this.job_exclude_condition = job_exclude_condition;
        this.job_require_condition = job_require_condition;
        this.job_location = job_location;
        this.job_id = job_id;
        this.job_company_id = job_company_id;
        this.job_owner_id = job_owner_id;
        this.jca_id = jca_id;
        this.job_position_id = job_position_id;
        this.pos_id = pos_id;
        this.job_quantity = job_quantity;
        this.job_time_type = job_time_type;
        this.job_range_salary_id = job_range_salary_id;
        this.job_gender = job_gender;
        this.job_range_allowance_id = job_range_allowance_id;
        this.job_range_bonus_id = job_range_bonus_id;
        this.job_status = job_status;
        this.job_isactive = job_isactive;
        this.lang_id = lang_id;
        this.job_title = job_title;
        this.jca_name = jca_name;
        this.jca_value = jca_value;
        this.jca_tags = jca_tags;
        this.jca_description = jca_description;
        this.jca_created_at = jca_created_at;
        this.jca_updated_at = jca_updated_at;
        this.pos_name = pos_name;
        this.pos_value = pos_value;
        this.job_views_number = job_views_number;
        this.job_apply_number = job_apply_number;
    }
    public RecruitmentModel() {

    }

    public String getIs_user_apply() {
        return is_user_apply;
    }

    public void setIs_user_apply(String is_user_apply) {
        this.is_user_apply = is_user_apply;
    }

    public String getNumber_matching() {
        return number_matching;
    }

    public void setNumber_matching(String number_matching) {
        this.number_matching = number_matching;
    }

    public String getIs_user_saved() {
        return is_user_saved;
    }

    public void setIs_user_saved(String is_user_saved) {
        this.is_user_saved = is_user_saved;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_avatar() {
        return owner_avatar;
    }

    public void setOwner_avatar(String owner_avatar) {
        this.owner_avatar = owner_avatar;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_website() {
        return owner_website;
    }

    public void setOwner_website(String owner_website) {
        this.owner_website = owner_website;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getJob_contact() {
        return job_contact;
    }

    public void setJob_contact(String job_contact) {
        this.job_contact = job_contact;
    }

    public String getJob_welfare() {
        return job_welfare;
    }

    public void setJob_welfare(String job_welfare) {
        this.job_welfare = job_welfare;
    }

    public String getJob_company_id() {
        return job_company_id;
    }

    public void setJob_company_id(String job_company_id) {
        this.job_company_id = job_company_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_owner_id() {
        return job_owner_id;
    }

    public void setJob_owner_id(String job_owner_id) {
        this.job_owner_id = job_owner_id;
    }

    public String getJca_id() {
        return jca_id;
    }

    public void setJca_id(String jca_id) {
        this.jca_id = jca_id;
    }

    public String getJob_position_id() {
        return job_position_id;
    }

    public void setJob_position_id(String job_position_id) {
        this.job_position_id = job_position_id;
    }

    public String getPos_id() {
        return pos_id;
    }

    public void setPos_id(String pos_id) {
        this.pos_id = pos_id;
    }

    public String getJob_quantity() {
        return job_quantity;
    }

    public void setJob_quantity(String job_quantity) {
        this.job_quantity = job_quantity;
    }

    public String getJob_time_type() {
        return job_time_type;
    }

    public void setJob_time_type(String job_time_type) {
        this.job_time_type = job_time_type;
    }

    public String getJob_range_salary_id() {
        return job_range_salary_id;
    }

    public void setJob_range_salary_id(String job_range_salary_id) {
        this.job_range_salary_id = job_range_salary_id;
    }

    public String getJob_gender() {
        return job_gender;
    }

    public void setJob_gender(String job_gender) {
        this.job_gender = job_gender;
    }

    public String getJob_range_allowance_id() {
        return job_range_allowance_id;
    }

    public void setJob_range_allowance_id(String job_range_allowance_id) {
        this.job_range_allowance_id = job_range_allowance_id;
    }

    public String getJob_range_bonus_id() {
        return job_range_bonus_id;
    }

    public void setJob_range_bonus_id(String job_range_bonus_id) {
        this.job_range_bonus_id = job_range_bonus_id;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }

    public String getJob_isactive() {
        return job_isactive;
    }

    public void setJob_isactive(String job_isactive) {
        this.job_isactive = job_isactive;
    }

    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJca_name() {
        return jca_name;
    }

    public void setJca_name(String jca_name) {
        this.jca_name = jca_name;
    }

    public String getJca_value() {
        return jca_value;
    }

    public void setJca_value(String jca_value) {
        this.jca_value = jca_value;
    }

    public String getJca_tags() {
        return jca_tags;
    }

    public void setJca_tags(String jca_tags) {
        this.jca_tags = jca_tags;
    }

    public String getJca_description() {
        return jca_description;
    }

    public void setJca_description(String jca_description) {
        this.jca_description = jca_description;
    }

    public String getJca_created_at() {
        return jca_created_at;
    }

    public void setJca_created_at(String jca_created_at) {
        this.jca_created_at = jca_created_at;
    }

    public String getJca_updated_at() {
        return jca_updated_at;
    }

    public void setJca_updated_at(String jca_updated_at) {
        this.jca_updated_at = jca_updated_at;
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

    public String getJob_level_id() {
        return job_level_id;
    }

    public void setJob_level_id(String job_level_id) {
        this.job_level_id = job_level_id;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public String getJob_work_location() {
        return job_work_location;
    }

    public void setJob_work_location(String job_work_location) {
        this.job_work_location = job_work_location;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_allowance() {
        return job_allowance;
    }

    public void setJob_allowance(String job_allowance) {
        this.job_allowance = job_allowance;
    }

    public String getJob_bonus() {
        return job_bonus;
    }

    public void setJob_bonus(String job_bonus) {
        this.job_bonus = job_bonus;
    }

    public String getJob_extra_desc() {
        return job_extra_desc;
    }

    public void setJob_extra_desc(String job_extra_desc) {
        this.job_extra_desc = job_extra_desc;
    }

    public String getJob_expired_time() {
        return job_expired_time;
    }

    public void setJob_expired_time(String job_expired_time) {
        this.job_expired_time = job_expired_time;
    }

    public String getJob_language_profile() {
        return job_language_profile;
    }

    public void setJob_language_profile(String job_language_profile) {
        this.job_language_profile = job_language_profile;
    }

    public String getJob_album_img_id() {
        return job_album_img_id;
    }

    public void setJob_album_img_id(String job_album_img_id) {
        this.job_album_img_id = job_album_img_id;
    }

    public String getJob_album_contract_id() {
        return job_album_contract_id;
    }

    public void setJob_album_contract_id(String job_album_contract_id) {
        this.job_album_contract_id = job_album_contract_id;
    }

    public String getJob_skills() {
        return job_skills;
    }

    public void setJob_skills(String job_skills) {
        this.job_skills = job_skills;
    }

    public String getJob_year_exps() {
        return job_year_exps;
    }

    public void setJob_year_exps(String job_year_exps) {
        this.job_year_exps = job_year_exps;
    }

    public String getJob_created_at() {
        return job_created_at;
    }

    public void setJob_created_at(String job_created_at) {
        this.job_created_at = job_created_at;
    }

    public String getJob_updated_at() {
        return job_updated_at;
    }

    public void setJob_updated_at(String job_updated_at) {
        this.job_updated_at = job_updated_at;
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

    public String getJob_exclude_condition() {
        return job_exclude_condition;
    }

    public void setJob_exclude_condition(String job_exclude_condition) {
        this.job_exclude_condition = job_exclude_condition;
    }

    public String getJob_require_condition() {
        return job_require_condition;
    }

    public void setJob_require_condition(String job_require_condition) {
        this.job_require_condition = job_require_condition;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }
}
