package gui;

import model.Voter;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoterProfileForm extends JFrame {
    private JTextArea voterProfileArea;

    public VoterProfileForm() {
        setTitle("Voter Profiles");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        voterProfileArea = new JTextArea();
        voterProfileArea.setEditable(false);
        add(new JScrollPane(voterProfileArea), BorderLayout.CENTER);

        loadVoterProfiles();
    }

    private void loadVoterProfiles() {
        Voter voter = new Voter();
        ResultSet rs = voter.getAllVoters();
        StringBuilder profiles = new StringBuilder();

        try {
            while (rs.next()) {
                profiles.append("Voter ID: ").append(rs.getInt("voter_id")).append("\n")
                        .append("Name: ").append(rs.getString("name")).append("\n")
                        .append("Age: ").append(rs.getInt("age")).append("\n")
                        .append("Voter Key: ").append(rs.getString("voter_key")).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        voterProfileArea.setText(profiles.toString());
    }
}
