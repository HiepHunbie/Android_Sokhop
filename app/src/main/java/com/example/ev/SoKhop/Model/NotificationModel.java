package com.example.ev.SoKhop.Model;

/**
 * Created by MSI on 11/1/2016.
 */

public class NotificationModel {
    public String notify_title,notify_content,notify_image_url,notify_is_read,
            notify_owner_id,notify_type,notify_created_at,notiusr_user_id,notify_id;
    public NotificationModel(String notify_title,String notify_content,String notify_image_url,
                             String notify_is_read,String notify_owner_id,String notify_type,String notify_created_at,String notify_id,
                             String notiusr_user_id){
        this.notify_title = notify_title;
        this.notify_content = notify_content;
        this.notify_image_url = notify_image_url;
        this.notify_is_read = notify_is_read;
        this.notify_owner_id = notify_owner_id;
        this.notify_type = notify_type;
        this.notify_created_at = notify_created_at;
        this.notiusr_user_id = notiusr_user_id;
        this.notify_id = notify_id;
    }
    public NotificationModel() {

    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getNotify_title() {
        return notify_title;
    }

    public void setNotify_title(String notify_title) {
        this.notify_title = notify_title;
    }

    public String getNotify_content() {
        return notify_content;
    }

    public void setNotify_content(String notify_content) {
        this.notify_content = notify_content;
    }

    public String getNotify_image_url() {
        return notify_image_url;
    }

    public void setNotify_image_url(String notify_image_url) {
        this.notify_image_url = notify_image_url;
    }

    public String getNotify_is_read() {
        return notify_is_read;
    }

    public void setNotify_is_read(String notify_is_read) {
        this.notify_is_read = notify_is_read;
    }

    public String getNotify_owner_id() {
        return notify_owner_id;
    }

    public void setNotify_owner_id(String notify_owner_id) {
        this.notify_owner_id = notify_owner_id;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_created_at() {
        return notify_created_at;
    }

    public void setNotify_created_at(String notify_created_at) {
        this.notify_created_at = notify_created_at;
    }

    public String getNotiusr_user_id() {
        return notiusr_user_id;
    }

    public void setNotiusr_user_id(String notiusr_user_id) {
        this.notiusr_user_id = notiusr_user_id;
    }
}
