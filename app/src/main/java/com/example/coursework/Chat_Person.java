package com.example.coursework;

public class Chat_Person {
    private String name;
    private String photo;
    private boolean isLiked;
    private String last_message;
    private String last_date;
    public Chat_Person(String name, String photo, String last_message, String last_date){
        this.name = name;
        this.photo = photo;
        this.isLiked = false;
        this.last_date = last_date;
        this.last_message = last_message;
    }

    public void setLast_data(String last_date) {
        this.last_date = last_date;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getLast_date() {
        return last_date;
    }

    public String getLast_message() {
        return last_message;
    }

    @Override
    public String toString() {
        return "Chat_Person{" +
                "name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", isLiked=" + isLiked +
                ", last_message='" + last_message + '\'' +
                ", last_data='" + last_date + '\'' +
                '}';
    }
}
