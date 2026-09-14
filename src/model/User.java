/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kenisha
 */


public class User {

    private int userId;
    private String username;
    private String password;
    private String role;
    private String fullName;

    // Empty constructor
    public User() {
    }

    // Constructor
    public User(int userId, String username, String password,
                String role, String fullName) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }

    // Get user ID
    public int getUserId() {
        return userId;
    }

    // Set user ID
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Get username
    public String getUsername() {
        return username;
    }

    // Set username
    public void setUsername(String username) {
        this.username = username;
    }

    // Get password
    public String getPassword() {
        return password;
    }

    // Set password
    public void setPassword(String password) {
        this.password = password;
    }

    // Get role
    public String getRole() {
        return role;
    }

    // Set role
    public void setRole(String role) {
        this.role = role;
    }

    // Get full name
    public String getFullName() {
        return fullName;
    }

    // Set full name
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}