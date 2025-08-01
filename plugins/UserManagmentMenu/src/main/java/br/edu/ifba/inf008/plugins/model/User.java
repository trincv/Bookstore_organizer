package br.edu.ifba.inf008.plugins.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "email can't be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
             message = "Invalid email formatation, must follow the example: exemplo@dominio.com")
    private String email;

    @NotBlank(message = "Name can't be null")
    @Size(min = 3, max = 100, message = "The name must have between {min} and {max} characters")
    @Pattern(regexp = "^[\\p{L}\\s'-]+$", message = "The name must contain just charracters, spaces and accents")
    private String name;

    private int id;

    public User(int id, String name, String email) {
        this.email = email;
        this.id = id;
        this.name = name;
    }

    public User(String name, String email) {
        this.email = email;
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
