package br.edu.ifba.inf008.entities;

public class User {
    
    private int id;
    private String email;
    private String name;

    public User(int id, String name, String email) {
        this.email = email;
        this.id = id;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
