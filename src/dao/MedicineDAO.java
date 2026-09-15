/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author kenisha
 */


import database.DBConnection;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineDAO {

    // Add a new medicine
    public boolean addMedicine(Medicine medicine) {

        String sql = "INSERT INTO medicines "
                + "(name, company, medicine_type, price, quantity_in_stock, "
                + "reorder_level, expiry_date, supplier_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getCompany());
            statement.setString(3, medicine.getMedicineType());
            statement.setDouble(4, medicine.getPrice());
            statement.setInt(5, medicine.getQuantityInStock());
            statement.setInt(6, medicine.getReorderLevel());
            statement.setString(7, medicine.getExpiryDate());
            statement.setInt(8, medicine.getSupplierId());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error adding medicine: " + e.getMessage());
            return false;
        }
    }
    
    // Get all medicines for the POS dropdown
    public java.util.List<Medicine> getAllMedicines() {

    java.util.List<Medicine> medicines = new java.util.ArrayList<>();

    String sql = "SELECT * FROM medicines ORDER BY name";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         java.sql.ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {

            Medicine medicine = new Medicine();

            medicine.setMedicineId(resultSet.getInt("medicine_id"));
            medicine.setName(resultSet.getString("name"));
            medicine.setCompany(resultSet.getString("company"));
            medicine.setMedicineType(resultSet.getString("medicine_type"));
            medicine.setPrice(resultSet.getDouble("price"));
            medicine.setQuantityInStock(resultSet.getInt("quantity_in_stock"));
            medicine.setReorderLevel(resultSet.getInt("reorder_level"));
            medicine.setExpiryDate(resultSet.getString("expiry_date"));
            medicine.setSupplierId(resultSet.getInt("supplier_id"));

            medicines.add(medicine);
        }

    } catch (SQLException e) {

        System.out.println("Error loading medicines: " + e.getMessage());
    }

    return medicines;
}
    public boolean reduceStock(int medicineId, int quantitySold) {

    String sql = "UPDATE medicines "
            + "SET quantity_in_stock = quantity_in_stock - ? "
            + "WHERE medicine_id = ? "
            + "AND quantity_in_stock >= ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, quantitySold);
        statement.setInt(2, medicineId);
        statement.setInt(3, quantitySold);

        int rowsUpdated = statement.executeUpdate();

        return rowsUpdated > 0;

    } catch (SQLException e) {
        System.out.println("Error reducing stock: " + e.getMessage());
        return false;
    }
}
    public boolean updateMedicine(Medicine medicine) {

    String sql = "UPDATE medicines SET name = ?, company = ?, medicine_type = ?, price = ?, quantity_in_stock = ?, reorder_level = ?, expiry_date = ?, supplier_id = ? WHERE medicine_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, medicine.getName());
        stmt.setString(2, medicine.getCompany());
        stmt.setString(3, medicine.getMedicineType());
        stmt.setDouble(4, medicine.getPrice());
        stmt.setInt(5, medicine.getQuantityInStock());
        stmt.setInt(6, medicine.getReorderLevel());
        stmt.setString(7, medicine.getExpiryDate());
        stmt.setInt(8, medicine.getSupplierId());
        stmt.setInt(9, medicine.getMedicineId());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
