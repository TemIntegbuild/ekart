/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author rahulkhanna
 */
public class gameplay extends JPanel implements KeyListener, ActionListener{ // Unlike JFrame, JPanel needs to be inherited, as the use of paint() is needed
    
    private boolean play=false;
    private int score=0;
    private int totalbricks;
    
    private Timer timer;
    private int delay=1;
    
    private int sliderx=310;
    
    private int bxpos=120;
    private int bypos=350;
    private int xdir=-1;
    private int ydir=-2;
    private int ro;
    private int co;
  //  Random rr=new Random();
    private int chance=1;
    
   private mapgen map;
    
    public gameplay(){
    
        addKeyListener(this);
       // ro=rr.nextInt(10)+1;
        //co=rr.nextInt(10)+1;
		ro=10;co=10;
        //totalbricks=ro*co;
        map=new mapgen(ro,co);
		totalbricks=map.tot();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
        
    }
    
    public void paint(Graphics g){
    
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        
         g.setColor(Color.red);
        g.fillRect(0,0,3,592);// left border
        g.fillRect(0,0,692,3);// up border
        g.fillRect(691, 0, 3, 592);//right border
        
         g.setColor(Color.blue);
        g.fillRect(sliderx,550,100,8);
        
           g.setColor(Color.yellow);
        g.fillOval(bxpos,bypos,20,20);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,15));
        g.drawString("SCORES "+score, 570, 30);
		
		g.setFont(new Font("serif",Font.BOLD,15));
        g.drawString("CHANCE "+chance, 10, 30);
        
        map.draw((Graphics2D)g);
            
                if(totalbricks==0){
            play=false;
            xdir=0;   // innovation : give player 3 chances 
            ydir=0;
         g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD,50));
        g.drawString("YOU WON"+score, 190, 300);
        
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("PRESS ENTER TO RESTART !!", 170, 350);
        }
        
        if(bypos>570){
		
		if(chance>=3){
            play=false;
            xdir=0;   // innovation : give player 3 chances 
            ydir=0;
         g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString("GAME OVER, score is "+score, 190, 300);
        
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD,30));
        g.drawString("PRESS ENTER TO RESTART !!", 170, 350);
        }
		else{
		bypos=0;chance++;
		}
		}
        
        g.dispose();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    timer.start();
    
    if(play){
    
    if(new Rectangle(bxpos,bypos,20,20).intersects(new Rectangle(sliderx,550,100,8))){
    ydir=-ydir;}
    
    A: for(int i=0;i<ro;i++){
        for(int j=0;j<co;j++){
        if(map.ba[i][j]>0){
        int brx=j*map.bw+80;
        int bry=i*map.bh+50;
        int brw=map.bw;
        int brh=map.bh;
        
        Rectangle r1=new Rectangle(brx,bry,brw,brh);
        Rectangle r2=new Rectangle(bxpos,bypos,20,20);
        Rectangle r3=r1;
        
        if(r2.intersects(r3)){
        
        
        if(bxpos+19<=r3.x || bxpos+1>=r3.x+r3.width)xdir=-xdir;
        else ydir=-ydir;
        map.setbval(0,i,j);
        score++;
        totalbricks--;   
         break A;
        }
       
        
        }}}
    
        
    bxpos+=xdir;
    bypos+=ydir;
    if(bxpos<0)xdir=-xdir;
    if(bypos<0)ydir=-ydir;
    if(bxpos>670)xdir=-xdir;
    
    }
    
    repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     
        if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
            if(sliderx>=600)sliderx=600;
            else moveright();
        }
        
        if(ke.getKeyCode()==KeyEvent.VK_LEFT){
            if(sliderx<10)sliderx=10;
            else moveleft();
        }
        
        if(ke.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){play=true;
            xdir=-1;ydir=-2;
            bxpos=120;bypos=350;
            score=0;
            sliderx=310;
            chance=1;
             map=new mapgen(ro,co);
           totalbricks=map.tot();
           
            
            
            repaint();
            }
        }
    
    }

    @Override
    public void keyReleased(KeyEvent ke) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    public void moveright(){
    play=true;
    sliderx+=20;}

    public void moveleft(){
    play=true;
    sliderx-=20;}
    
}
