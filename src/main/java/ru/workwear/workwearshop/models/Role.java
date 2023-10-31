package ru.workwear.workwearshop.models;

public enum Role {
    ROLE_ADMIN("Administration","ROLE_ADMIN"),
    ROLE_USER("User","ROLE_USER");

    private String title;
    private String role;

    Role(String title,String role){
        this.title = title;
        this.role = role;
    }

    public String getTitle(){
        return title;
    }

    public String getRole(){
        return role;
    }
}
