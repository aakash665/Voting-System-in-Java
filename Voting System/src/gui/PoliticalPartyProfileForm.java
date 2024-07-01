package gui;

import model.PoliticalParty;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PoliticalPartyProfileForm extends JFrame {
    private JTextArea partyProfileArea;

    public PoliticalPartyProfileForm() {
        setTitle("Political Party Profiles");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        partyProfileArea = new JTextArea();
        partyProfileArea.setEditable(false);
        add(new JScrollPane(partyProfileArea), BorderLayout.CENTER);

        loadPartyProfiles();
    }

    private void loadPartyProfiles() {
        PoliticalParty party = new PoliticalParty();
        ResultSet rs = party.getAllParties();
        StringBuilder profiles = new StringBuilder();

        try {
            while (rs.next()) {
                profiles.append("Party ID: ").append(rs.getInt("party_id")).append("\n")
                        .append("Name: ").append(rs.getString("name")).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        partyProfileArea.setText(profiles.toString());
    }
}
