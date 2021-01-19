package com.example.schedule.po;

public class Task {
    private int id;
    private int is_finish; //是否完成
    private int is_importance; // 是否重要
    private int is_repeat; //是否重复
    private String remind_time; // 提醒时间
    private String remind_date;
    private String content; // 内容
    private String sort; // 分类
    private String create_time; //创建时间
    private int create_id; //创建人id

    public Task(int id, int is_finish, int is_importance, int is_repeat,
                String remind_time, String remind_date, String content, String sort, int create_id) {
        this.id = id;
        this.is_finish = is_finish;
        this.is_importance = is_importance;
        this.is_repeat = is_repeat;
        this.remind_time = remind_time;
        this.remind_date = remind_date;
        this.content = content;
        this.sort = sort;
        this.create_id = create_id;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_finish() {
        return is_finish;
    }

    public void setIs_finish(int is_finish) {
        this.is_finish = is_finish;
    }

    public int getIs_importance() {
        return is_importance;
    }

    public void setIs_importance(int is_importance) {
        this.is_importance = is_importance;
    }

    public int getIs_repeat() {
        return is_repeat;
    }

    public void setIs_repeat(int is_repeat) {
        this.is_repeat = is_repeat;
    }

    public String getRemind_time() {
        return remind_time;
    }

    public String getRemind_date() {
        return remind_date;
    }

    public void setRemind_date(String remind_date) {
        this.remind_date = remind_date;
    }

    public void setRemind_time(String remind_time) {
        this.remind_time = remind_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }
}
