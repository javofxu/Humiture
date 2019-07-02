package com.example.humiture.data;

import java.io.Serializable;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec:登录实体类
 */
public class LoginData implements Serializable {

    private int status;
    private String msg;
    private Data data;
    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public class Data {

        private int user_id;
        private String username;
        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
        public int getUser_id() {
            return user_id;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }

    }

}
