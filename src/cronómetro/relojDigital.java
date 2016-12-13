package cronómetro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class relojDigital extends JPanel implements Runnable, ActionListener {
	private int s,
				m,
				h;
	private String mensaje,
				   linea,
				   hr,
				   min,
				   sec;
	
	private JButton bStart;
	
	private JRadioButton color1,
						 color2,
						 color3;
	private ButtonGroup bg;
	
	private Image madera,placa;
	
	private BufferedReader reader;
	private PrintWriter writer;
	

	private boolean St;
	
	private panelReloj pR;
	
	public relojDigital(panelReloj pR){
		
		super();
		this.pR=pR;
		
		this.setPreferredSize(new Dimension(400,100));
		((FlowLayout)this.getLayout()).setAlignment(FlowLayout.CENTER);
		this.setBackground(Color.BLACK);
		
		this.madera=new ImageIcon("madera.jpg").getImage();
		this.placa= new ImageIcon("placa.jpg").getImage();
		
		
		Thread hilo=new Thread(this);
		
		this.St=false;
		this.s=0;
		this.m=0;
		this.h=0;
		
		this.hr="00";
		this.min="00";
		this.sec="00";
		

		
		this.bStart=new JButton("Start/Stop");
		this.bStart.addActionListener(this);
		this.color1=new JRadioButton("Naranja");
		this.color1.setSelected(true);
		
		this.color2=new JRadioButton("Azul");
		this.color3=new JRadioButton("verde");
		
		this.bg=new ButtonGroup();
		this.bg.add(this.color1);
		this.bg.add(this.color2);
		this.bg.add(this.color3);
		
		this.color1.addActionListener(this);
		this.color2.addActionListener(this);
		this.color3.addActionListener(this);
		
		this.add(this.bStart);
	
		this.add(this.color1);
		this.add(this.color2);
		this.add(this.color3);

		
		this.mensaje=this.hr+":"+this.min+":"+this.sec;
		hilo.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(madera, 0, 0,this.getWidth(),this.getHeight(), this);
		g.drawImage(placa,40,0,320,90,this);
		
		g.setColor(Color.GRAY);
		g.fillRect(145,35, 110, 50);
		g.setColor(Color.BLACK);
		g.fillRect(150, 40, 100, 40);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.setColor(Color.GREEN);
		g.drawString(this.mensaje, 160, 65);
	}

	@Override
	public void run() {
		try{

			while(true){
				Thread.sleep(1000);
				if(this.St){
					this.s++;
					if(this.s==60){
						this.s=0;
						this.m++;
					}
					if(this.m==60){
						this.m=0;
						this.h++;
					}
					
					if(this.s<10){
						this.sec="0"+this.s;
					}else{
						this.sec=Integer.toString(this.s);
					}
					if(this.m<10){
						this.min="0"+this.m;
					}else{
						this.min=Integer.toString(this.m);
					}
					if(this.h<10){
						this.hr="0"+this.h;
					}else{
						this.hr=Integer.toString(this.h);
					}
					
					
					this.mensaje=this.hr+":"+this.min+":"+this.sec;
					this.repaint();
				}else{
					this.s=0;
					this.m=0;
					this.h=0;
					this.hr="00";
					this.min="00";
					this.sec="00";
					this.mensaje=this.hr+":"+this.min+":"+this.sec;
					this.repaint();
				}
				this.pR.setSec(this.s);
				this.pR.setMin(this.m);
				this.pR.setHr(this.h);
				
				
			}
			
		} catch (InterruptedException e) {
			System.out.println("interrumpido");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.bStart){
			if(this.St==false){
				this.St=true;
			}else{
				this.St=false;
				
				
				
				
			
				try {
					this.writer=new PrintWriter(new FileWriter("doc.txt"));
				} catch (IOException e1) {
					
				}
				writer.println(""+this.mensaje);
				writer.close();
				
				try {
					this.reader=new BufferedReader(new FileReader("doc.txt"));
					while((linea=reader.readLine())!=null){
						System.out.println(linea);
					}
					reader.close();
					
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		}else{
			if(this.color1.isSelected()){
				this.pR.setColor(Color.ORANGE);
			}else if(this.color2.isSelected()){
				this.pR.setColor(Color.BLUE);
			}else{
				this.pR.setColor(Color.GREEN);
			}
		}
		
	}
	
	
	
}
