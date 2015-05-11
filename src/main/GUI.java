package main;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class GUI extends JFrame{
	JComboBox<String> instruction;
	JTextField operand1,operand2;
	//JLabel op1,op2;
	
	public GUI(){
		setSize(500, 500);
		setLayout(null);
		
		String[] ins ={"MOV","JMP","ADD"};
		instruction = new JComboBox<String>(ins);
		instruction.setBounds(5, 5, 50, 25);
		this.add(instruction);
		
		operand1=new JTextField();
		operand2=new JTextField();
		operand1.setBounds(60, 5, 50, 25);
		this.add(operand1);
		this.add(operand2);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[]args){
		GUI x = new GUI();
	}
}
