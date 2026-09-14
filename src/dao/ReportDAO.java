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

public class ReportDAO {

    public double getTotalSales() {

        String sql = "SELECT COALESCE(SUM(total_amount), 0) AS total_sales "
                + "FROM sales";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getDouble("total_sales");
            }

        } catch (SQLException e) {
            System.out.println("Error getting total sales: "
                    + e.getMessage());
        }

        return 0.0;
    }
    
    public int getNumberOfSales() {

    String sql = "SELECT COUNT(*) AS number_of_sales FROM sales";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        if (resultSet.next()) {
            return resultSet.getInt("number_of_sales");
        }

    } catch (SQLException e) {
        System.out.println("Error getting number of sales: "
                + e.getMessage());
    }

    return 0;
}
    public int getMedicinesSold() {

    String sql = "SELECT COALESCE(SUM(quantity_sold), 0) AS medicines_sold "
            + "FROM sale_items";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        if (resultSet.next()) {
            return resultSet.getInt("medicines_sold");
        }

    } catch (SQLException e) {
        System.out.println("Error getting medicines sold: "
                + e.getMessage());
    }

    return 0;
}
    
    public int getLowStockItems() {

    String sql = "SELECT COUNT(*) AS low_stock "
            + "FROM medicines "
            + "WHERE quantity_in_stock <= reorder_level";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        if (resultSet.next()) {
            return resultSet.getInt("low_stock");
        }

    } catch (SQLException e) {
        System.out.println("Error getting low stock items: "
                + e.getMessage());
    }

    return 0;
}
    
    public ResultSet getAllSales() {

    String sql = "SELECT sale_id, sale_date, total_amount, user_id "
            + "FROM sales "
            + "ORDER BY sale_date DESC";

    try {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(sql);

        return statement.executeQuery();

    } catch (SQLException e) {
        System.out.println("Error getting sales: "
                + e.getMessage());
    }

    return null;
}
    
}