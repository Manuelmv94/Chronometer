package cronómetro;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ventana extends JFrame{

	public ventana(){
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelReloj pR=new panelReloj();
		relojDigital rD=new relojDigital(pR);
		
		
		this.add(pR);
		this.add(rD,BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		ventana v=new ventana();

	}

}
