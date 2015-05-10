package hardware.components;

import java.util.ArrayList;


public class ALU  {

	private Register outTo, a, b;
	boolean[] select;
	boolean carry;
	ALUopr operation;
	public ALU(int size){
		a = new ALURegister(size,this);
		b = new ALURegister(size,this);
		select = new boolean[4];
		carry=false;
	}

	public void selectOp(){
		String instruction="";
		for(int i=0;i<4;i++){
			instruction += select[i];
		}
		if(instruction=="0000"){
			operation=ALUopr.CLEAR;
		}
		if(instruction=="0001"){
			operation=ALUopr.SUBBA;
		}
		if(instruction=="0010"){
			operation=ALUopr.SUBAB;
		}
		if(instruction=="0011"){
			operation=ALUopr.ADDAB;
		}
		if(instruction=="0100"){
			operation=ALUopr.IDB;
		}
		if(instruction=="0101"){
			operation=ALUopr.NOTB;
		}
		if(instruction=="0110"){
			operation=ALUopr.IDA;
		}
		if(instruction=="0111"){
			operation=ALUopr.NOTA;
		}
		if(instruction=="1000"){
			operation=ALUopr.OR;
		}
		if(instruction=="1001"){
			operation=ALUopr.XOR;
		}
		if(instruction=="1010"){
			operation=ALUopr.EQ;
		}
		if(instruction=="1011"){
			operation=ALUopr.NAND;
		}
		if(instruction=="1100"){
			operation=ALUopr.ANORB;
		}
		if(instruction=="1101"){
			operation=ALUopr.BNORA;
		}
	}

	public int Add(){
		return (a.getInt()+b.getInt());
	}

	public int sub(){
		return (a.getInt()-b.getInt());
	}

	public void setOutTo(Register newOut){
		outTo = newOut;
	}
	
	public int operation(){
		int result=0;
		if(carry){
			if(operation==ALUopr.CLEAR){
				return 0;
			}
			if(operation==ALUopr.SUBAB){
				return a.getInt()- b.getInt();
			}
			if(operation==ALUopr.SUBBA){
				return b.getInt()- a.getInt();
			}
			if(operation==ALUopr.ADDAB){
				return a.getInt()+ b.getInt();
			}
			if(operation==ALUopr.NOTA){
				boolean[] not= new boolean[a.getOutputBuffer().length];
				for(int i=0;i<a.getOutputBuffer().length;i++){
					not[i] = !not[i];
				}
				for(int i = 0; i < not.length; i++) {
					if(not[i]) {
						result += Math.pow(2, (double)i);
					}
				}
			}
		}
		return result;
	}
	public void update(){
		
	}



}
