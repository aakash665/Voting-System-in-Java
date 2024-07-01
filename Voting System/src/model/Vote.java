package model;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Vote {
    public void castVote(int voterId, int candidateId) {
        String query = "INSERT INTO Votes (voter_id, candidate_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, voterId);
            stmt.setInt(2, candidateId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void countVotes() {
        String query = "SELECT c.name AS candidate_name, COUNT(v.candidate_id) AS vote_count " +
                       "FROM Votes v JOIN Candidates c ON v.candidate_id = c.candidate_id " +
                       "GROUP BY v.candidate_id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Candidate: " + rs.getString("candidate_name") + ", Votes: " + rs.getInt("vote_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
