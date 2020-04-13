package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 10/11/2016.
 */

public class Image {
    public int img_album_id,img_height,img_id,img_owner_id,img_position,img_width,total_img_same_al;
    public String img_created_at,img_file_url,img_tags,img_updated_at,img_uuid,alb_name;
    public Image(int img_album_id,int img_height,int img_id,int img_owner_id,int img_position,int img_width,int total_img_same_al,String img_created_at,String img_file_url,String img_tags,String img_updated_at,String img_uuid,String alb_name){
        this.img_album_id = img_album_id;
        this.img_height = img_height;
        this.img_id = img_id;
        this.img_owner_id = img_owner_id;
        this.img_position = img_position;
        this.img_width = img_width;
        this.img_created_at = img_created_at;
        this.img_file_url = img_file_url;
        this.img_tags = img_tags;
        this.img_updated_at = img_updated_at;
        this.img_uuid = img_uuid;
        this.alb_name = alb_name;
        this.total_img_same_al = total_img_same_al;
    }
    public Image() {

    }

    public int getTotal_img_same_al() {
        return total_img_same_al;
    }

    public void setTotal_img_same_al(int total_img_same_al) {
        this.total_img_same_al = total_img_same_al;
    }

    public String getAlb_name() {
        return alb_name;
    }

    public void setAlb_name(String alb_name) {
        this.alb_name = alb_name;
    }

    public int getImg_album_id() {
        return img_album_id;
    }

    public void setImg_album_id(int img_album_id) {
        this.img_album_id = img_album_id;
    }

    public int getImg_height() {
        return img_height;
    }

    public void setImg_height(int img_height) {
        this.img_height = img_height;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getImg_owner_id() {
        return img_owner_id;
    }

    public void setImg_owner_id(int img_owner_id) {
        this.img_owner_id = img_owner_id;
    }

    public int getImg_position() {
        return img_position;
    }

    public void setImg_position(int img_position) {
        this.img_position = img_position;
    }

    public int getImg_width() {
        return img_width;
    }

    public void setImg_width(int img_width) {
        this.img_width = img_width;
    }

    public String getImg_created_at() {
        return img_created_at;
    }

    public void setImg_created_at(String img_created_at) {
        this.img_created_at = img_created_at;
    }

    public String getImg_file_url() {
        return img_file_url;
    }

    public void setImg_file_url(String img_file_url) {
        this.img_file_url = img_file_url;
    }

    public String getImg_tags() {
        return img_tags;
    }

    public void setImg_tags(String img_tags) {
        this.img_tags = img_tags;
    }

    public String getImg_updated_at() {
        return img_updated_at;
    }

    public void setImg_updated_at(String img_updated_at) {
        this.img_updated_at = img_updated_at;
    }

    public String getImg_uuid() {
        return img_uuid;
    }

    public void setImg_uuid(String img_uuid) {
        this.img_uuid = img_uuid;
    }
}
