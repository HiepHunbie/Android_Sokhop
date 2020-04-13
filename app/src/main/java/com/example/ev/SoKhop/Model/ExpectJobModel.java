package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/15/2016.
 */

public class ExpectJobModel {
    public int expect_job,user_id,pos_id,province_id;
    public String salary_basic,bonus,allowance,objective,description,pos_name,pos_value,province_name,province_value,district,street,number,time_type;
    public ExpectJobModel(int expect_job,int user_id,int pos_id,int province_id,String salary_basic,String bonus,String allowance,String objective,
                          String description,String pos_name,String pos_value,String province_name,String province_value,String district,String street,String number,String time_type){
        this.expect_job = expect_job;
        this.user_id = user_id;
        this.pos_id = pos_id;
        this.province_id = province_id;
        this.salary_basic = salary_basic;
        this.bonus = bonus;
        this.allowance = allowance;
        this.objective = objective;
        this.description = description;
        this.pos_name = pos_name;
        this.pos_value = pos_value;
        this.province_name = province_name;
        this.province_value = province_value;
        this.district = district;
        this.street = street;
        this.number = number;
        this.time_type = time_type;
    }
    public ExpectJobModel() {

    }

    public String getTime_type() {
        return time_type;
    }

    public void setTime_type(String time_type) {
        this.time_type = time_type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getExpect_job() {
        return expect_job;
    }

    public void setExpect_job(int expect_job) {
        this.expect_job = expect_job;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPos_id() {
        return pos_id;
    }

    public void setPos_id(int pos_id) {
        this.pos_id = pos_id;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getSalary_basic() {
        return salary_basic;
    }

    public void setSalary_basic(String salary_basic) {
        this.salary_basic = salary_basic;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getAllowance() {
        return allowance;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_value() {
        return province_value;
    }

    public void setProvince_value(String province_value) {
        this.province_value = province_value;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
