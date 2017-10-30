/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author rahulkhanna
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         JFrame frame=new JFrame(); // JFrame is not inherited, it just gets in library by importing the class; similar to Scanner class
         gameplay gm=new gameplay();
         frame.setVisible(true);
         frame.setBounds(10,10 ,700, 600);
         frame.setTitle("Brick Breaker");
         frame.setResizable(false);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setBackground(Color.gray);
         frame.add(gm);
    }
    
}
