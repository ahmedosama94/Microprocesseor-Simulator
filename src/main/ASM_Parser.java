package main;

import java.util.Scanner;

import hardware.components.units.MainMemory;
import hardware.exceptions.HardwareException;

public class ASM_Parser {
	private String Instructions = "[ ,]+";
	private String[] tokens = new String[3];
	private String opCode;
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
			x = "1" + tokens[1].substring(1).trim();
			if(tokens[0].equalsIgnoreCase("MOV")){
				opCode="0001";
			}
			if(tokens[0].equalsIgnoreCase("ADD")){
				opCode="0011";
			}
			
		}
		else
		{
				switch(tokens[1].trim()){
				case "R0": x+="000000";break;
				case "R1": x+="000001";break;
				case "R2": x+="000010";break;
				case "R3": x+="000011";break;
				case "R4": x+="000100";break;
				case "R5": x+="000101";break;
				case "R6": x+="000110";break;
				case "R7": x+="000111";break;
				case "R8": x+="001000";break;
				case "R9": x+="001001";break;
				case "R10": x+="001010";break;
				case "R11": x+="001011";break;
				case "R12": x+="001100";break;
				case "R13": x+="001101";break;
				case "R14" : x+="001110";break;
				case "R15" : x+="001111";break;
			
				}
//			x = x + tokens[1].trim();
			
		}
		if(tokens[2].trim().charAt(0)=='$'){
			x = "1" + tokens[1].substring(1).trim();
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
				switch(tokens[2].trim()){
				case "R0": x+="000000";break;
				case "R1": x+="000001";break;
				case "R2": x+="000010";break;
				case "R3": x+="000011";break;
				case "R4": x+="000100";break;
				case "R5": x+="000101";break;
				case "R6": x+="000110";break;
				case "R7": x+="000111";break;
				case "R8": x+="001000";break;
				case "R9": x+="001001";break;
				case "R10": x+="001010";break;
				case "R11": x+="001011";break;
				case "R12": x+="001100";break;
				case "R13": x+="001101";break;
				case "R14" : x+="001110";break;
				case "R15" : x+="001111";break;
		//		default : System.out.println("error");
				}
			//	x = x + tokens[1].trim();

			}
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
