package cronómetro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.math.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class panelReloj extends JPanel {
	private int sxf,
				syf,
				mxf,
				myf,
				hxf,
				hyf;
	

	private Image fondo;
	
	public panelReloj(){
		super();
		this.setBackground(Color.ORANGE);
		this.setPreferredSize(new Dimension(400,400));
		

		this.sxf=200;
		this.syf=80;
		this.mxf=200;
		this.myf=100;
		this.hxf=200;
		this.hyf=125;
		
		this.fondo=new ImageIcon("madera.jpg").getImage();
		
	}
	
	public void setColor(Color color){
		this.setBackground(color);
	}
	
	public void setSec(int s){
		
		this.sxf=(int)(200+120*Math.sin(((2*Math.PI)/60)*s));
		this.syf=(int)(200-120*Math.cos(((2*Math.PI)/60)*s));
		
		this.repaint();
		
	}
	
	public void setMin(int m){
		this.mxf=(int)(200+100*Math.sin(((2*Math.PI)/60)*m));
		this.myf=(int)(200-100*Math.cos(((2*Math.PI)/60)*m));
		
		this.repaint();
	}
	
	public void setHr(int h){
		this.hxf=(int)(200+75*Math.sin(((2*Math.PI)/12)*h));
		this.hyf=(int)(200-75*Math.cos(((2*Math.PI)/12)*h));
		
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(fondo, 0, 0,this.getWidth(),this.getHeight(), this);
		
		g.setColor(Color.BLACK);
		g.fillOval(50, 50, 300,300);
		
		g.setColor(Color.WHITE);
		g.fillOval(75,75,250,250);
		
		g.drawString("0", 197, 61);
		g.drawString("3", 342, 204);
		g.drawString("6",197,349);
		g.drawString("9", 55, 204);
		g.setFont(new Font("Arial",Font.BOLD,20));

		g.drawString("FOSSIL", 230, 150);
		
		for(int i=0;i<12;i++){
			g.drawLine(200,200, (int)(200+137*Math.sin(((2*Math.PI)/12)*i)),(int)(200-137*Math.cos(((2*Math.PI)/12)*i)));
		}
		
		for(int i=0;i<60;i++){
			g.drawLine(200,200, (int)(200+132*Math.sin(((2*Math.PI)/60)*i)),(int)(200-132*Math.cos(((2*Math.PI)/60)*i)));
		}
		
		g.setColor(Color.GRAY);
		g.fillOval(195, 195, 10, 10);
		
		g.setColor(Color.BLACK);
		g.drawString("FOSSIL", 220, 180);
		g.drawLine(200,200, sxf, syf);
		g.drawLine(200,200, mxf, myf);
		g.drawLine(200,200, hxf, hyf);
		
		g.fillOval(198, 198, 4, 4);
		
		
		
		//g.fillRect(0, 390, 400, 10);
	}
	
}
