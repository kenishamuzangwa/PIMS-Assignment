/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author kenisha
 */

import model.User;

public class TestUserDAO {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        // Test Admin login
        User user = userDAO.login("admin", "admin123");

        if (user != null) {

            System.out.println("Login successful!");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Name: " + user.getFullName());
            System.out.println("Role: " + user.getRole());

        } else {

            System.out.println("Invalid username or password.");
        }
    }
}