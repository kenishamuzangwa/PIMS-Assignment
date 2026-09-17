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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDAO {

    // Create a new sale and return the generated sale ID
    public int createSale(double totalAmount, int userId) {

        String sql = "INSERT INTO sales (sale_date, total_amount, user_id) "
                + "VALUES (NOW(), ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setDouble(1, totalAmount);
            statement.setInt(2, userId);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {

                ResultSet keys = statement.getGeneratedKeys();

                if (keys.next()) {
                    return keys.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error creating sale: " + e.getMessage());
        }

        return -1;
    }
        // Add an item to a sale
    public boolean addSaleItem(int saleId, int medicineId,
            int quantitySold, double priceAtSale) {

        String sql = "INSERT INTO sale_items "
                + "(sale_id, medicine_id, quantity_sold, price_at_sale) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, saleId);
            statement.setInt(2, medicineId);
            statement.setInt(3, quantitySold);
            statement.setDouble(4, priceAtSale);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            System.out.println("Error adding sale item: "
                    + e.getMessage());

            return false;
        }
    }
    
    public String getSaleDate(int saleId) {

    String sql = "SELECT sale_date FROM sales WHERE sale_id = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, saleId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("sale_date");
        }

    } catch (SQLException e) {
        System.out.println("Error getting sale date: " + e.getMessage());
    }

    return "";
}
    public ResultSet getSaleItems(int saleId) {

    String sql = "SELECT m.name, si.quantity_sold, "
            + "si.price_at_sale, "
            + "(si.quantity_sold * si.price_at_sale) AS total "
            + "FROM sale_items si "
            + "JOIN medicines m ON si.medicine_id = m.medicine_id "
            + "WHERE si.sale_id = ?";

    try {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, saleId);

        return statement.executeQuery();

    } catch (SQLException e) {
        System.out.println("Error getting sale items: " + e.getMessage());
    }

    return null;
}
    
    public ResultSet getMonthlySales() {

    String sql = "SELECT DATE_FORMAT(sale_date, '%Y-%m') AS month, "
            + "SUM(total_amount) AS total_sales "
            + "FROM sales "
            + "GROUP BY DATE_FORMAT(sale_date, '%Y-%m') "
            + "ORDER BY month";

    try {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        return statement.executeQuery();

    } catch (SQLException e) {
        System.out.println("Error getting monthly sales: " + e.getMessage());
    }

    return null;
}
    
    public ResultSet getTopSellingMedicines() {

    String sql = "SELECT m.name, SUM(si.quantity_sold) AS total_sold "
            + "FROM sale_items si "
            + "JOIN medicines m ON si.medicine_id = m.medicine_id "
            + "GROUP BY m.medicine_id, m.name "
            + "ORDER BY total_sold DESC "
            + "LIMIT 5";

    try {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        return statement.executeQuery();

    } catch (SQLException e) {
        System.out.println("Error getting top-selling medicines: " + e.getMessage());
    }

    return null;
}
}
