package gui;

import model.Candidate;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateProfileForm extends JFrame {
    private JTextArea candidateProfileArea;

    public CandidateProfileForm() {
        setTitle("Candidate Profiles");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        candidateProfileArea = new JTextArea();
        candidateProfileArea.setEditable(false);
        add(new JScrollPane(candidateProfileArea), BorderLayout.CENTER);

        loadCandidateProfiles();
    }

    private void loadCandidateProfiles() {
        Candidate candidate = new Candidate();
        ResultSet rs = candidate.getAllCandidates();
        StringBuilder profiles = new StringBuilder();

        try {
            while (rs.next()) {
                profiles.append("Candidate ID: ").append(rs.getInt("candidate_id")).append("\n")
                        .append("Name: ").append(rs.getString("name")).append("\n")
                        .append("Party: ").append(rs.getString("party_name")).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        candidateProfileArea.setText(profiles.toString());
    }
}
