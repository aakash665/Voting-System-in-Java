package gui;

import model.Vote;

import javax.swing.*;
import java.awt.*;

public class VoteCountForm extends JFrame {
    private JTextArea voteCountArea;

    public VoteCountForm() {
        setTitle("Vote Count Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        voteCountArea = new JTextArea();
        voteCountArea.setEditable(false);
        add(new JScrollPane(voteCountArea), BorderLayout.CENTER);

        loadVoteCounts();
    }

    private void loadVoteCounts() {
        Vote vote = new Vote();
        StringBuilder voteCounts = new StringBuilder();

        vote.countVotes(voteCounts::append);

        voteCountArea.setText(voteCounts.toString());
    }
}
