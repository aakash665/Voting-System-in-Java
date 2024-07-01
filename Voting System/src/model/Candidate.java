package model;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Candidate {
    public void registerCandidate(String name, int partyId) {
        String query = "INSERT INTO Candidates (name, party_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, partyId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCandidateProfile(int candidateId) {
        String query = "SELECT c.*, p.name AS party_name FROM Candidates c " +
                       "JOIN PoliticalParties p ON c.party_id = p.party_id " +
                       "WHERE c.candidate_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, candidateId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Candidate ID: " + rs.getInt("candidate_id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Party: " + rs.getString("party_name"));
                } else {
                    System.out.println("Candidate not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllCandidates() {
        String query = "SELECT c.*, p.name AS party_name FROM Candidates c " +
                       "JOIN PoliticalParties p ON c.party_id = p.party_id";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
