package com.example.identification;

public class User {
    private String username;
    private String password;
    private int age;
    private String mail;

    public User(String username, String password, int age, String mail) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getMail() {
        return mail;
    }
}
