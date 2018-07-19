
import javax.swing.*;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;



public class master implements ActionListener {
	LineBilder fn=new LineBilder();
	int a;
	JTextField t1;
	JButton b1,b2,b3,b4,b5;
	JFrame f= new JFrame();
	JFrame f2=new JFrame();
	GUI gui=new GUI();
	long time;
	
	master(){
		
		b1=new JButton("DDALine");
        b1.setBounds(0,500,80,30);
        
        b2=new JButton("BresenhamLine");
        b2.setBounds(80,500,80,30);
        
        b3=new JButton("Br_Circle");
        b3.setBounds(160,500,80,30);
       
        b4=new JButton("VyLine");
        b4.setBounds(240, 500,80,30);
        
        b5=new JButton("Circle");
        b5.setBounds(320,500,80,30);
        
        t1=new JTextField("Time");
        t1.setBounds(0,300,200,100);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(t1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600,600);
		f.setLayout(null);
        f.setVisible(true);
        
	}
	
	public class GUI extends JPanel{
		
		public void paintComponent(Graphics g) {
			this.setBackground(Color.WHITE);
//			g.drawLine(1,123,23, 2332);
			//dasdqw a
			g.setColor(Color.BLACK);		
			if(a==0) {
			fn.DDALine(0,0, 500,300 ,g);}else {
				if(a==1) {fn.drawBresenhamLine(0,0, 500,300, g);}else {
					if(a==2) {fn.Dr_Circle(200,200,100, g);}else {
						if(a==3) {fn.drawXiaolinLine(0,0, 500,300 ,g);}else if (a==4) {fn.Circle(200,200,100, g);}
					}
				}
			}
			
		}
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{	
		f2.setLocation(600,0);
		
		f2.setSize(600,600);
		
        f2.setVisible(true);
        
		if(e.getSource()==b1){
			a=0;
            f2.add(gui);
            t1.setText("Time nanosecond "+fn.time);
			fn.time=0;
            
        }
		if(e.getSource()==b2) {
			a=1;
			f2.add(gui);
			t1.setText("Time nanosecond "+fn.time);
			fn.time=0;
		}
		if(e.getSource()==b3) {
			a=2;
			f2.add(gui); 
			t1.setText("Time nanosecond "+fn.time);
			fn.time=0;
		}
		if(e.getSource()==b4) {
			a=3;
			f2.add(gui);
			t1.setText("Time nanosecond "+fn.time);
			fn.time=0;
		}
		if(e.getSource()==b5) {
			a=4;
			f2.add(gui);
			t1.setText("Time nanosecond "+fn.time);
			fn.time=0;
		}
		
	}	
	
	public static void main(String[] args)
	   {
		new master();
	   }	
}
