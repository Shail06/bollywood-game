package com.aricent.july;

import java.util.*;
import java.io.*;

class GuessTheMovie {

    String Check = "BOLLYWOOD";
    ArrayList<Character> TrackString = new ArrayList<Character>();
    ArrayList<Character> MovieChars = new ArrayList<Character>();
    ArrayList<Character> DisplayChars = new ArrayList<Character>();
    private String selectedMovie;

    public static void main(String[] args) {
        GuessTheMovie game = new GuessTheMovie();
        game.setup();
        game.play();
        game.result();
    }

    public void result() {
        System.out.println("Movie: "+selectedMovie);
    }

    public void play() {
        String userInput;
        try {
            while (!TrackString.isEmpty() && DisplayChars.contains('_')) {
                System.out.print("\n Your Guess : ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                userInput = br.readLine();

                char chUser = Character.toUpperCase(userInput.charAt(0));
                if (!MovieChars.contains(chUser)) {
                    TrackString.remove(0);
                } else {
                    int iter = 0;
                    for (char ch : MovieChars) {
                        if (ch == chUser) {
                            DisplayChars.set(iter, chUser);
                        }
                        iter++;
                    }
                }
                checkProgress();
            }
        } catch (IOException e) {
            System.out.println("Bad Input!");
        }
    }

    public void setup() {

        String[] Movies = {"Tanu Weds Manu", "Dil Dhadakne do", "mohabbattein", "Krrish", "Bahubali", "Piku"};
        int randomMovie = (int) (Math.random() * 5);
        selectedMovie = Movies[randomMovie].toUpperCase();

        for (char chmovie : selectedMovie.toCharArray()) {
            MovieChars.add(chmovie);
            if (chmovie == ' ') {
                DisplayChars.add(chmovie);
            } else {
                DisplayChars.add('_');
            }
        }

        for (char cTrace : Check.toCharArray()) {
            TrackString.add(cTrace);
        }
        checkProgress();
    }


    public void checkProgress() {
        // Display the Track-String and the display String
        System.out.print("\n\n\t");
        for (char chmovie : DisplayChars) {
            System.out.print(chmovie + " ");
        }
        System.out.print("\n\n\n\t");
        for (char c : TrackString) {
            System.out.print(c + "  ");
        }
        System.out.print("\n");
    }
}