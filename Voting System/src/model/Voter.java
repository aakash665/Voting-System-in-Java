package model;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Voter {
    public void registerVoter(String name, int age, String voterKey) {
        String query = "INSERT INTO Voters (name, age, voter_key) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, voterKey);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewVoterProfile(int voterId) {
        String query = "SELECT * FROM Voters WHERE voter_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, voterId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Voter ID: " + rs.getInt("voter_id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Age: " + rs.getInt("age"));
                    System.out.println("Voter Key: " + rs.getString("voter_key"));
                } else {
                    System.out.println("Voter not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean voterExists(String voterKey) {
        String query = "SELECT voter_id FROM Voters WHERE voter_key = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, voterKey);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



public ResultSet getAllVoters() {
    String query = "SELECT * FROM Voters";
    try {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public int voterIdByKey(String voterKey) {
    String query = "SELECT voter_id FROM Voters WHERE voter_key = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, voterKey);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("voter_id");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Invalid voter key
}

public ResultSet getAllVoters() {
    String query = "SELECT * FROM Voters";
    try {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public int voterIdByKey(String voterKey) {
    String query = "SELECT voter_id FROM Voters WHERE voter_key = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, voterKey);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("voter_id");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Invalid voter key
}
