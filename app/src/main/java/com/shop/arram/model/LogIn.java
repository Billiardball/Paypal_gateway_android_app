package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogIn {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("cookie")
    @Expose
    public String cookie;
    @SerializedName("cookie_name")
    @Expose
    public String cookieName;
    @SerializedName("user")
    @Expose
    public User user;

    public LogIn withStatus(String status) {
        this.status = status;
        return this;
    }

    public LogIn withCookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public LogIn withCookieName(String cookieName) {
        this.cookieName = cookieName;
        return this;
    }

    public LogIn withUser(User user) {
        this.user = user;
        return this;
    }

    public class User {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("nicename")
        @Expose
        public String nicename;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("registered")
        @Expose
        public String registered;
        @SerializedName("displayname")
        @Expose
        public String displayname;
        @SerializedName("firstname")
        @Expose
        public String firstname;
        @SerializedName("lastname")
        @Expose
        public String lastname;
        @SerializedName("nickname")
        @Expose
        public String nickname;
        @SerializedName("description")
        @Expose
        public String description;


        public User withId(int id) {
            this.id = id;
            return this;
        }

        public User withUsername(String username) {
            this.username = username;
            return this;
        }

        public User withNicename(String nicename) {
            this.nicename = nicename;
            return this;
        }

        public User withEmail(String email) {
            this.email = email;
            return this;
        }

        public User withUrl(String url) {
            this.url = url;
            return this;
        }

        public User withRegistered(String registered) {
            this.registered = registered;
            return this;
        }

        public User withDisplayname(String displayname) {
            this.displayname = displayname;
            return this;
        }

        public User withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public User withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public User withNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public User withDescription(String description) {
            this.description = description;
            return this;
        }


    }
}