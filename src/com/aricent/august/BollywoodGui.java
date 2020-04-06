package com.aricent.august;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.event.*;
import java.io.*;

public class BollywoodGui {

    JFrame frame = new JFrame();
    JPanel Npanel = new JPanel();
    JPanel Spanel = new JPanel();
    JPanel Cpanel = new JPanel();
    JLabel Tracklabel = new JLabel();
    JLabel MovieLabel = new JLabel();
    JLabel ChoiceLabel = new JLabel(" ");
    JLabel TimerLabel = new JLabel();
    JButton GoButton = new JButton("Send");
    ArrayList<JButton> KeyPad = new ArrayList<JButton>();

    int min;
    int sec;
    boolean StopTheTimer = false;
    int score;
    int countToFailure = 0;
    int countToSuccess = 0;
    int numOfMovieChars = 0;
    String GuessType = " ";
    JLabel MessageLabel = new JLabel(GuessType);
    StringBuilder TrackString;

    String DisplayString = "";
    ArrayList<Character> MovieChars = new ArrayList<Character>();
    ArrayList<Character> DisplayChars = new ArrayList<Character>();

    public static void main(String[] args) {
        BollywoodGui game = new BollywoodGui();
        game.algoSetup();
        game.GUIsetup();

    }

    //Running A timer
    public class TimerFunction implements Runnable {

        public void run() {
            min = 2;
            sec = 0;
            String x = "";
            try {
                while (min >= 0 && !StopTheTimer) {
                    Thread.sleep(1000);
                    if (sec < 10) {
                        x = "0";
                    } else {
                        x = "";
                    }
                    String countDown = "Time left " + "0" + min + ":" + x + sec;
                    TimerLabel.setText(countDown);

                    if (sec == 0) {
                        sec = 60;
                        min--;
                    }
                    sec--;
                }
                if (min < 0) {
                    EndTheGame("Time Up , AALSI !\n PLAY AGAIN ??");
                }
            } catch (Exception e) {
            }
        }
    }

    public void RePlay() {
        MovieChars.clear();
        DisplayChars.clear();
        countToFailure = 0;
        countToSuccess = 0;
        numOfMovieChars = 0;
        StopTheTimer = false;
        ChoiceLabel.setText(" ");
        GuessType = " ";
        GoButton.setEnabled(false);
        algoSetup();
    }

    public void EndTheGame(String FinalMessage) {
        showDisplay();
        JOptionPane Result = new JOptionPane();
        int Restart = Result.showConfirmDialog(frame, FinalMessage, "Bollywood", JOptionPane.YES_NO_OPTION);
        if (Restart == Result.YES_OPTION) {
            RePlay();
        } else {
            System.exit(0);
        }
    }

    public void playGame() {

        String userInput = ChoiceLabel.getText();
        char chUser = userInput.charAt(0);

        if (!MovieChars.contains(chUser)) {
            GuessType = "Galat hai ye!!";
            TrackString.setCharAt(countToFailure, ' ');
            countToFailure += 2;
            if (countToFailure >= TrackString.length()) {
                StopTheTimer = true;
                String MovieName = "";
                for (char c : MovieChars) {
                    MovieName = MovieName + c;
                }
                EndTheGame("Shame on you , LOSER ! \n Its " + MovieName + "\n PLAY AGAIN ??");
            }
        } else if (!DisplayChars.contains(chUser)) {
            int iter = 0;
            for (char ch : MovieChars) {
                if (ch == chUser) {
                    DisplayChars.set(iter, chUser);
                    countToSuccess++;
                }
                iter++;
            }

            GuessType = (numOfMovieChars - countToSuccess) + " MORE TO GO!";

            if (countToSuccess == numOfMovieChars) {
                StopTheTimer = true;
                score = min * 60 + sec;
                GuessType = "YOUR SCORE IS " + score;
                EndTheGame(GuessType + "\n PLAY AGAIN ??");
            }
        }
        showDisplay();

    }

    public void showDisplay() {

        // Setting the Movie Label
        StringBuilder MString = new StringBuilder(DisplayChars.size());
        for (Character c : DisplayChars) {
            MString.append(c + " ");
        }


        DisplayString = MString.toString();

        Tracklabel.setText(TrackString.toString());
        MovieLabel.setText(DisplayString);
        Npanel.repaint();
        Npanel.revalidate();

        MessageLabel.setText(GuessType);

        Cpanel.repaint();
    }

    public void GUIsetup() {

        // Setting Labels in North Panel
        Npanel.setLayout(new BoxLayout(Npanel, BoxLayout.Y_AXIS));
        Npanel.add(MovieLabel);
        MovieLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        MovieLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        Npanel.add(Tracklabel);
        Tracklabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Tracklabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        Tracklabel.setForeground(Color.blue);
        Npanel.add(ChoiceLabel);
        ChoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ChoiceLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        Cpanel.setLayout(new GridLayout(0, 1));

        GoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Npanel.add(GoButton);
        TimerLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        Cpanel.add(TimerLabel);
        GoButton.setEnabled(false);
        GoButton.addActionListener(new SendButton());
        Cpanel.add(MessageLabel);
        MessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        MessageLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
        MessageLabel.setForeground(Color.RED);
        //Setting Buttons In South Panel

        //ImageIcon blackButtons = new ImageIcon(getClass().getResource("bg1.jpg"));
        char cValue = 'A';
        while (cValue <= 'Z') {
            String buttonText = "";
            for (int i = 0; i < 3; i++) {
                if (Character.isLetter(cValue)) {
                    buttonText += (cValue + " ");
                    cValue++;
                }
            }
            KeyPad.add(new JButton(buttonText));

        }

        GridLayout grdLayout = new GridLayout(0, 3);
        grdLayout.setHgap(5);
        grdLayout.setVgap(5);
        Spanel.setLayout(grdLayout);
        Spanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (JButton bT : KeyPad) {
            Spanel.add(bT);
            bT.addActionListener(new ButtonListener());
            bT.setPreferredSize(new Dimension(35, 40));
            bT.setBackground(Color.WHITE);
            bT.setForeground(Color.BLACK);
            bT.setFocusPainted(false);
            bT.setFont(new Font("Tahoma", Font.BOLD, 13));
        }

        JPanel background = new JPanel(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.getContentPane().add(background);
        background.add(Npanel, BorderLayout.NORTH);
        background.add(Spanel, BorderLayout.SOUTH);
        background.add(Cpanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setVisible(true);

        //showDisplay();
    }

    public void algoSetup() {
	
		
	/*  -----Movies List from the Array-----
	
		String[] Movies = {"Tanu Weds Manu","Dil Dhadakne do","The vertical Limit","Lakshya"};
		int randomMovie = (int)(Math.random()*Movies.length);
		Movies[randomMovie] = Movies[randomMovie];
		
	*/
        try {
            File file = new File("src/com/aricent/august/MoviesList.txt");
            String Movie = getRandomMovie(file).toUpperCase();

            for (char chmovie : Movie.toCharArray()) {
                MovieChars.add(chmovie);
                if (chmovie == ' ') {
                    DisplayChars.add(chmovie);
                } else {
                    DisplayChars.add('_');
                    numOfMovieChars++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TrackString = new StringBuilder("B O L L Y W O O D");
        showDisplay();
        Thread t = new Thread(new TimerFunction());
        t.start();
    }

    public String getRandomMovie(File inputFile) {

        String film = null;
        Random rand = new Random();
        int n = 0;
        try {
            for (Scanner sc = new Scanner(inputFile); sc.hasNext(); ) {
                ++n;
                String line = sc.nextLine();
                if (rand.nextInt(n) == 0) {
                    film = line;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return film;
    }


    Object previous = null;

    class ButtonListener implements ActionListener {
        int countForButtons;

        public void actionPerformed(ActionEvent e) {
            Object current = e.getSource();

            if (current != previous) {
                countForButtons = 0;
            }
            MessageLabel.setText("");
            String[] SelectedText = {};
            Object o = e.getSource();
            JButton b1 = null;
            if (o instanceof JButton) {
                b1 = (JButton) o;
            }
            String btText = b1.getText();
            SelectedText = btText.split(" ");

            if (countForButtons < SelectedText.length) {
                ChoiceLabel.setText(SelectedText[countForButtons]);
            } else {
                countForButtons = 0;
                ChoiceLabel.setText(SelectedText[countForButtons]);
            }
            countForButtons++;
            previous = current;
            GoButton.setEnabled(true);
        }
    }

    class SendButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MessageLabel.setText("");
            playGame();
        }
    }
}