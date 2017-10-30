/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author rahulkhanna
 */
public class mapgen {
    
    public int bw;
    public int bh;
    public int ba[][];
    Random r =new Random();
    private int cho;
	private int snum=0;;
    
    public mapgen(int row, int col){
    
        ba=new int[row][col];
        for(int i=0;i<row;i++)for(int j=0;j<col;j++){
            cho = r.nextInt(3)+1;
            if(cho==1){
            ba[i][j]=1;
		snum++;}}
        
        bw=540/col;
        bh=150/row;
    }
    
    public void draw(Graphics2D g){
    
        for(int i=0;i<ba.length;i++)for(int j=0;j<ba[0].length;j++)
            if(ba[i][j]>0){
            
                 g.setColor(Color.white);
                  g.fillRect(j*bw+80,i*bh+50,bw,bh); // as width is calculated in accordance to columns
                                                     // height is calculated in accordance to rows
                 g.setStroke(new BasicStroke(3));
                 g.setColor(Color.black);
                  g.drawRect(j*bw+80,i*bh+50,bw,bh);                                     
                                                     
            }
    
    }
    
    public void setbval(int val,int r,int c){
    ba[r][c]=val;}
	
	public int tot(){return snum;}
    
}
