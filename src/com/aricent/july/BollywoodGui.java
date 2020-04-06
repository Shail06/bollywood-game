package com.aricent.july;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class BollywoodGui{
		JFrame frame;
		JPanel Npanel, Spanel;
		JLabel GuessLabel  = new JLabel();
		JLabel MovieLabel = new JLabel();
		JLabel ChoiceLabel = new JLabel("YOUR CHOICE");
	
		String Check = "B O L L Y W O O D";
		String DisplayString = "";
		ArrayList<Character> MovieChars = new ArrayList<Character>();
		ArrayList<Character> DisplayChars = new ArrayList<Character>();
	
	public static void main(String[] args){
		BollywoodGui game = new BollywoodGui();
		game.algoSetup();
		game.GUIsetup();
			
	}
	
	public void GUIsetup(){
		frame =  new JFrame("Bollywood");
		Npanel = new JPanel();
		Spanel = new JPanel();
		
		GuessLabel.setText(Check);  // Set BOLLYWOOD string
		MovieLabel.setText(DisplayString);
		ArrayList<JButton> KeyPad = new ArrayList<JButton>();
		
	
	frame.getContentPane().add(Npanel ,BorderLayout.NORTH);
	frame.getContentPane().add(Spanel ,BorderLayout.SOUTH);
	
	
	// Setting Labels in North Panel
	Npanel.setLayout(new BoxLayout(Npanel, BoxLayout.Y_AXIS));
	Npanel.add(MovieLabel);
	MovieLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	Npanel.add(GuessLabel);
	MovieLabel.setFont(new Font("Serif", Font.PLAIN, 20));
	GuessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	GuessLabel.setFont(new Font("Serif", Font.BOLD, 18));
	Npanel.add(ChoiceLabel);
	ChoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	ChoiceLabel.setFont(new Font("Courier New", Font.PLAIN, 25));
	
	
	//Setting Buttons In South Panel
	 char cValue = 'A';
	 while(cValue<='Y'){	
	 String buttonText = "";
		for(int i = 0 ; i< 3; i++){
			buttonText+=(cValue+" ");
			cValue++;	
		}
		KeyPad.add(new JButton(buttonText));
		cValue++;
	}
	
	Spanel.setLayout(new GridLayout(0,3));
	for(JButton bT : KeyPad){
		Spanel.add(bT);
		bT.addActionListener(new ButtonListener());
	}	
	
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300,300);
	frame.setVisible(true);
		
		
	}
	public void algoSetup(){
		
		String[] Movies = {"harry potter","bang bang","Mr India","Krrish", "kick"};
		int randomMovie = (int)(Math.random()*Movies.length);
		Movies[randomMovie] = Movies[randomMovie].toUpperCase();
			
		for(char chmovie : Movies[randomMovie].toCharArray()){
			MovieChars.add(chmovie);
			if(chmovie ==' '){
			DisplayChars.add(chmovie);
			}else{
				DisplayChars.add('_');
			}
		}
		
		// Setting the Movie Label
		StringBuilder MString = new StringBuilder(DisplayChars.size());
		for (Character c : DisplayChars) {
			MString.append(c+" ");
		}
	DisplayString = MString.toString();
	}
	
	
	class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{ 
			ChoiceLabel.setText("Button Clicked !");
		}
	}
}