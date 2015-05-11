package main;

import java.util.Scanner;

import hardware.components.Register;
import hardware.components.units.MainMemory;
import hardware.exceptions.HardwareException;

public class ASM_Parser {
	private String Instructions = "[ ,]+";
	private String[] tokens = new String[3];
	private String opCode;
	private boolean addressed1 , addressed2;
	private MainMemory memoryBlock;

	public ASM_Parser(MainMemory mm) {
		memoryBlock=mm;
		opCode="";
	}

	public void getOperands(String Instruction){
		tokens = Instruction.split(Instructions);
		if(tokens[0].equalsIgnoreCase("MOV")){
			opCode="0000";
		}
		if(tokens[0].equalsIgnoreCase("ADD")){
			opCode="0010";
		}
		if(tokens[0].equalsIgnoreCase("JMP")){
			opCode="0100";
		}

		String x ="";
		if(tokens[1].trim().charAt(0)=='$'){
			x = x + tokens[1].substring(1).trim();
			if(tokens[0].equalsIgnoreCase("MOV")){
				opCode="0001";
			}
			if(tokens[0].equalsIgnoreCase("ADD")){
				opCode="0011";
			}
		}
		else
		{
			x = x + tokens[1].trim();
		}
		opCode = opCode + x;
		if(tokens[1].trim().charAt(0)=='$'){
			x = x + tokens[1].substring(1).trim();
			if(tokens[0].equalsIgnoreCase("MOV")){
				opCode="0001";
			}
			if(tokens[0].equalsIgnoreCase("ADD")){
				opCode="0011";
			}
			if(tokens[0].equalsIgnoreCase("JMP") && tokens[2]!=null){
				opCode="0101";
			}
		}
		else
		{
			x = x + tokens[2].trim();
		}
		x="";
		x = x + tokens[2].substring(1).trim();
		opCode = opCode + x;
	}

	public void print() {
		System.out.println("the instruction is: " + tokens[0] +", the operands are: " + tokens[1] + " , " +tokens[2]);
		System.out.println(opCode);
	}

	public static void main(String[]args) {

		ASM_Parser x = new ASM_Parser(new MainMemory(32, 16));
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		x.getOperands(in);
		input.close();
		x.print();

	}

	public boolean[] convertOP(){
		boolean[] result = new boolean[opCode.length()];
		for(int i=0;i<opCode.length();i++){
			if(opCode.charAt(i)=='0'){
				result[i] = false;
			} 
			else {
				result[i] = true;
			}
		}
		return result;
	}

	public void writeOPcodeToMem(int address) throws HardwareException{
		memoryBlock.setAddress(address);
		memoryBlock.setReadWrite(true);
		memoryBlock.setInputBuffer(convertOP());
	}

}
