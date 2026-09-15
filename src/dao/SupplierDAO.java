/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
/**
 *
 * @author kenisha
 */


import database.DBConnection;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierDAO {

    // Add a new supplier
    public boolean addSupplier(Supplier supplier) {

        String sql = "INSERT INTO suppliers "
                + "(name, contact_person, phone, email, address) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getContactPerson());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getEmail());
            statement.setString(5, supplier.getAddress());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            System.out.println("Error adding supplier: " + e.getMessage());
            return false;
        }
    }
    public List<Supplier> getAllSuppliers() {

    List<Supplier> suppliers = new java.util.ArrayList<>();

    String sql = "SELECT * FROM suppliers ORDER BY name";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         java.sql.ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {

            Supplier supplier = new Supplier();

            supplier.setSupplierId(
                    resultSet.getInt("supplier_id"));

            supplier.setName(
                    resultSet.getString("name"));

            supplier.setContactPerson(
                    resultSet.getString("contact_person"));

            supplier.setPhone(
                    resultSet.getString("phone"));

            supplier.setEmail(
                    resultSet.getString("email"));

            supplier.setAddress(
                    resultSet.getString("address"));

            suppliers.add(supplier);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error loading suppliers: " + e.getMessage());
    }

    return suppliers;
}
    
    public boolean updateSupplier(Supplier supplier) {

    String sql = "UPDATE suppliers SET name = ?, contact_person = ?, phone = ?, email = ?, address = ? WHERE supplier_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, supplier.getName());
        stmt.setString(2, supplier.getContactPerson());
        stmt.setString(3, supplier.getPhone());
        stmt.setString(4, supplier.getEmail());
        stmt.setString(5, supplier.getAddress());
        stmt.setInt(6, supplier.getSupplierId());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
