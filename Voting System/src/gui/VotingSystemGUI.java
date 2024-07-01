package gui;

import model.Candidate;
import model.Voter;
import model.Vote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotingSystemGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Voting System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Voter Registration
        JLabel userLabel = new JLabel("Name");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 50, 80, 25);
        panel.add(ageLabel);

        JTextField ageText = new JTextField(20);
        ageText.setBounds(100, 50, 165, 25);
        panel.add(ageText);

        JLabel keyLabel = new JLabel("Voter Key");
        keyLabel.setBounds(10, 80, 80, 25);
        panel.add(keyLabel);

        JTextField keyText = new JTextField(20);
        keyText.setBounds(100, 80, 165, 25);
        panel.add(keyText);

        JButton registerButton = new JButton("Register Voter");
        registerButton.setBounds(10, 110, 150, 25);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = userText.getText();
                int age = Integer.parseInt(ageText.getText());
                String voterKey = keyText.getText();
                new Voter().registerVoter(name, age, voterKey);
                JOptionPane.showMessageDialog(panel, "Voter Registered!");
            }
        });

        // Candidate Registration
        JLabel candidate
