package gui;

import model.Candidate;
import model.Vote;
import model.Voter;
import util.Validation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VotingForm extends JFrame {
    private JComboBox<String> candidateComboBox;
    private JTextField voterKeyField;

    public VotingForm() {
        setTitle("Voting Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel voterKeyLabel = new JLabel("Voter Key:");
        voterKeyLabel.setBounds(20, 20, 100, 25);
        add(voterKeyLabel);

        voterKeyField = new JTextField();
        voterKeyField.setBounds(150, 20, 200, 25);
        add(voterKeyField);

        JLabel candidateLabel = new JLabel("Select Candidate:");
        candidateLabel.setBounds(20, 60, 100, 25);
        add(candidateLabel);

        candidateComboBox = new JComboBox<>();
        candidateComboBox.setBounds(150, 60, 200, 25);
        add(candidateComboBox);
        loadCandidates();

        JButton voteButton = new JButton("Vote");
        voteButton.setBounds(150, 100, 100, 25);
        add(voteButton);

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                castVote();
            }
        });
    }

    private void loadCandidates() {
        Candidate candidate = new Candidate();
        ResultSet rs = candidate.getAllCandidates();
        try {
            while (rs.next()) {
                candidateComboBox.addItem(rs.getInt("candidate_id") + ": " + rs.getString("name") + " - " + rs.getString("party_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void castVote() {
        String voterKey = voterKeyField.getText();
        if (!Validation.isValidVoterKey(voterKey)) {
            JOptionPane.showMessageDialog(this, "Invalid Voter Key!");
            return;
        }

        Voter voter = new Voter();
        if (!voter.voterExists(voterKey)) {
            JOptionPane.showMessageDialog(this, "Voter not registered!");
            return;
        }

        String selectedItem = (String) candidateComboBox.getSelectedItem();
        int candidateId = Integer.parseInt(selectedItem.split(":")[0]);

        Vote vote = new Vote();
        vote.castVote(voter.voterIdByKey(voterKey), candidateId);

        JOptionPane.showMessageDialog(this, "Vote cast successfully!");
    }
}
