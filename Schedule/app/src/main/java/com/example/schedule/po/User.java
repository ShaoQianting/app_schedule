package com.example.schedule.po;

public class User {
    private int id;
    private String user_name;
    private String password;
    private String email;
    private String phone_num;
    private String item_sets;

    public User(int id, String user_name, String password, String email, String phone_num, String item_sets) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.phone_num = phone_num;
        this.item_sets = item_sets;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getItem_sets() {
        return item_sets;
    }

    public void setItem_sets(String item_sets) {
        this.item_sets = item_sets;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", phonenum='" + phonenum + '\'' +
//                '}';
//    }
}
