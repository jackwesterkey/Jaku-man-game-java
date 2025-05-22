package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 JFrame window = new JFrame ();
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 
         window.setResizable(false); // Corrected typo here
         window.setTitle(" Ja'ku man");
         
         GamePan GamePan = new GamePan();
         window.add(GamePan);
         
         window.pack();
     
         window.setLocationRelativeTo(null);
         window.setVisible(true);
         
         GamePan.startGameThread();
	}

}
