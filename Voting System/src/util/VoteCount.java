import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class VoteCount {
    public void countVotes() {
        String query = "SELECT c.name, COUNT(v.candidate_id) AS vote_count " +
                       "FROM Votes v JOIN Candidates c ON v.candidate_id = c.candidate_id " +
                       "GROUP BY v.candidate_id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Candidate: " + rs.getString("name") + ", Votes: " + rs.getInt("vote_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
