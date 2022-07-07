import javax.swing.*;
import java.util.*;
import java.awt.event.*;

class Converter{
	public static void converter(){

		    JFrame window=new JFrame("Base Conveter");

		    JButton bin_b,oct_b,dec_b,hex_b,res_b,close_b;
		    JTextField bin_field,oct_field,dec_field,hex_field;
		    JLabel bin_lab,oct_lab,dec_lab,hex_lab,name;


		    bin_b=new JButton("Convert");
		    oct_b=new JButton("Convert");
		    dec_b=new JButton("Convert");
		    hex_b=new JButton("Convert");
		    res_b=new JButton("Reset");
		    close_b=new JButton("Close");

		    bin_b.setBounds(250,105,100, 30);  
		    oct_b.setBounds(250,155,100, 30);  
		    dec_b.setBounds(250,205,100, 30);  
		    hex_b.setBounds(250,255,100, 30);  
		    res_b.setBounds(250,310,100, 40);
		    close_b.setBounds(100,310,100, 40);

			window.add(bin_b);window.add(oct_b);window.add(dec_b);window.add(hex_b);window.add(res_b);window.add(close_b);  


			bin_field=new JTextField();  
			bin_field.setBounds(100,105, 140,30);  
			oct_field=new JTextField();  
			oct_field.setBounds(100,155, 140,30);  
			dec_field=new JTextField();  
			dec_field.setBounds(100,205, 140,30);  
			hex_field=new JTextField();  
			hex_field.setBounds(100,255, 140,30);  

			window.add(bin_field); window.add(oct_field);  window.add(dec_field); window.add(hex_field); 
			

			bin_lab=new JLabel("Binary");  
		    bin_lab.setBounds(40,105, 100,30);  
		    oct_lab=new JLabel("Octal");  
		    oct_lab.setBounds(40,155, 100,30);  
			dec_lab=new JLabel("Decimal");  
		    dec_lab.setBounds(40,205, 100,30);  
		    hex_lab=new JLabel("Hex");  
		    hex_lab.setBounds(40,255, 100,30); 
		    name =new JLabel("Number Conversion");
		    name.setBounds(150,30, 150,30); 

		    window.add(bin_lab);window.add(oct_lab);window.add(dec_lab);window.add(hex_lab);window.add(name);

			window.setSize(450,450);
			window.setLayout(null);
			window.setVisible(true);

		    bin_b.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
		    			String bin = bin_field.getText();
		    			try{
							oct_field.setText(Integer.toString(Integer.parseInt(bin, 2),8));
							dec_field.setText(Integer.toString(Integer.parseInt(bin, 2),10));
							hex_field.setText(Integer.toString(Integer.parseInt(bin, 2),16));
						}catch(Exception a){
							oct_field.setText("Invalid input");
							dec_field.setText("Invalid input");
							hex_field.setText("Invalid input");
						}
				}
			});

		    oct_b.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){ 
		    			String oct = oct_field.getText();
		    			try{
							bin_field.setText(Integer.toString(Integer.parseInt(oct, 8),2));
							dec_field.setText(Integer.toString(Integer.parseInt(oct, 8),10));
							hex_field.setText(Integer.toString(Integer.parseInt(oct, 8),16));
						}catch(Exception a){
							bin_field.setText("Invalid input");
							dec_field.setText("Invalid input");
							hex_field.setText("Invalid input");
						}
				} 
			});

		    dec_b.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){ 
		    			String dec = dec_field.getText();
		    			try{
							bin_field.setText(Integer.toString(Integer.parseInt(dec, 10),2));
							oct_field.setText(Integer.toString(Integer.parseInt(dec, 10),8));
							hex_field.setText(Integer.toString(Integer.parseInt(dec, 10),16));
						}catch(Exception a){
							oct_field.setText("Invalid input");
							bin_field.setText("Invalid input");
							hex_field.setText("Invalid input");
						}
				} 
			});

		    hex_b.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){ 
		    			String hex = hex_field.getText();
		    			try{
							bin_field.setText(Integer.toString(Integer.parseInt(hex, 16),2));
							oct_field.setText(Integer.toString(Integer.parseInt(hex, 16),8));
							dec_field.setText(Integer.toString(Integer.parseInt(hex, 16),10));
						}catch(Exception a){
							oct_field.setText("Invalid input");
							dec_field.setText("Invalid input");
							bin_field.setText("Invalid input");
						}
				} 
			}); 

		    res_b.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){ 
						bin_field.setText("");
						hex_field.setText("");
						oct_field.setText("");
						dec_field.setText("");
				} 
			});

		    close_b.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){ 
						window.setVisible(false);
				} 
			});
		}
}

class Resistor{

	public static void resistor(){

		JFrame window = new JFrame("Resistor - Color code");

	    String colors[]={"0 Black","1 Brown","2 Red","3 Orange","4 Yellow","5 Green","6 Blue","7 Violet","8 Gray","9 White"};        
	    String tolerance[]={"None","Silver","Gold","Red","Brown"};        
	    
	    JComboBox<String> color_cb1 =new JComboBox<>(colors);    
	    color_cb1.setBounds(30,210,80,20);
	    JComboBox<String> color_cb2 =new JComboBox<>(colors);    
	    color_cb2.setBounds(130,210,80,20);	    
	    JComboBox<String> color_cb3 =new JComboBox<>(colors);    
	    color_cb3.setBounds(220,210,80,20);
	    JComboBox<String> toler_cb1 =new JComboBox<>(tolerance);    
	    toler_cb1.setBounds(310,210,70,20);

	    window.add(color_cb1);window.add(color_cb2);window.add(color_cb3);window.add(toler_cb1);

	    JLabel resistor_op,toler_op,capcode,cap_op,sep;

	    resistor_op=new JLabel("0  Ohm");
	    toler_op=new JLabel("~ 0%"); 
	    capcode=new JLabel("Ceramic Capacitor : "); 
	    cap_op=new JLabel("0 pF"); 
	    sep=new JLabel("-----------------------------------------------------------------------------------------------"); 


	    resistor_op.setBounds(150,240, 100,30);
	    toler_op.setBounds(250,240,50,30);
	    capcode.setBounds(30,300,120,30);
	    cap_op.setBounds(270,300,100,30);
	    sep.setBounds(10,275,440,10);

	    window.add(resistor_op);window.add(toler_op);window.add(capcode);window.add(cap_op);window.add(sep);

	    JButton res_img,close_b,cap_img;

	    res_img = new JButton(new ImageIcon("icon.png"));
	    res_img.setBounds(30,20,350,180);

	    cap_img = new JButton(new ImageIcon("icon_cap.png"));
	    cap_img.setBounds(220,303,25,25);

	    close_b=new JButton("Close");
		close_b.setBounds(300,350,80, 20);

	    window.add(res_img);window.add(cap_img);window.add(close_b);

	    JTextField cap_field;
		cap_field = new JTextField();  
		cap_field.setBounds(150,305,50,20);

		window.add(cap_field);

		window.setSize(420,420);
		window.setLayout(null);
		window.setVisible(true);

	    color_cb1.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
			String cb1 = color_cb1.getItemAt(color_cb1.getSelectedIndex());
	    	String cb2 = color_cb2.getItemAt(color_cb2.getSelectedIndex());
	    	String cb3 = color_cb3.getItemAt(color_cb3.getSelectedIndex());

	    	int first = Character.getNumericValue(cb1.charAt(0));
	    	int second = Character.getNumericValue(cb2.charAt(0));
	    	int third = Character.getNumericValue(cb3.charAt(0));
	    	double ans = second*(1*Math.pow(10,third));
			resistor_op.setText(first + "" + (int)ans  + " Ohm"  );
			}
		});

	    color_cb2.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
			String cb1 = color_cb1.getItemAt(color_cb1.getSelectedIndex());
	    	String cb2 = color_cb2.getItemAt(color_cb2.getSelectedIndex());
	    	String cb3 = color_cb3.getItemAt(color_cb3.getSelectedIndex());

	    	int first = Character.getNumericValue(cb1.charAt(0));
	    	int second = Character.getNumericValue(cb2.charAt(0));
	    	int third = Character.getNumericValue(cb3.charAt(0));
	    	double ans = second*(1*Math.pow(10,third));
			resistor_op.setText(first + "" + (int)ans  + " Ohm"  );
			}
		});

	    color_cb3.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
			String cb1 = color_cb1.getItemAt(color_cb1.getSelectedIndex());
	    	String cb2 = color_cb2.getItemAt(color_cb2.getSelectedIndex());
	    	String cb3 = color_cb3.getItemAt(color_cb3.getSelectedIndex());

	    	int first = Character.getNumericValue(cb1.charAt(0));
	    	int second = Character.getNumericValue(cb2.charAt(0));
	    	int third = Character.getNumericValue(cb3.charAt(0));
	    	double ans = second*(1*Math.pow(10,third));
			resistor_op.setText(first + "" + (int)ans  + " Ohm"  );
			}
		});
		toler_cb1.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				String tr = toler_cb1.getItemAt(toler_cb1.getSelectedIndex());
				switch(tr){
					case "None":
						toler_op.setText("~ 20%");
						break;
					case "Silver":
						toler_op.setText("~ 10%");
						break;
					case "Gold":
						toler_op.setText("~ 5%");
						break;
					case "Red":
						toler_op.setText("~ 2%");
						break;
					case "Brown":
						toler_op.setText("~ 1%");
						break;
					default:
						toler_op.setText("~ 20%");
				}
			}
		});
		cap_img.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				int code = Integer.parseInt(cap_field.getText());
				if(code>0 && code<999){
					int last_digit = code%10;
					double rem = code/10;
					if(last_digit>=0 && last_digit<=6){
						rem =rem*Math.pow(10,last_digit);
						int res = (int)rem;
						cap_op.setText(res + " pF");
					}
					else{
						cap_op.setText("Invalid");
					}
				}else{
						cap_op.setText("Invalid");
				}
			}catch(Exception a){
				cap_op.setText("Invalid");
			}
			}
		});
	  	close_b.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
					window.setVisible(false);
			} 
		});
	}
}

public class Main{

	public static void intro(){

		JFrame window=new JFrame("Lab Basic Tool");

		JButton converter_b,resistor_b;

		converter_b = new JButton("Base Converter");
		resistor_b = new JButton("Resistor ColorCode");

	    converter_b.setBounds(150,100,150, 40);  
	    resistor_b.setBounds(150,150,150, 40);  

	    window.add(converter_b);window.add(resistor_b);

		window.setSize(450,450);
		window.setLayout(null);
		window.setVisible(true);

	    converter_b.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){ 
						Converter.converter();
	    		} 
		});

		resistor_b.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){ 
						Resistor.resistor();
	    		} 
		});

	}

	public static void main(String[] args){

		Scanner scan = new Scanner(System.in);
		intro();
		scan.nextLine();
		System.exit(0);
	}
}   