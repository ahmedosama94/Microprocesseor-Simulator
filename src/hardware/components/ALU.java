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
		int value = outTo.convertToInt(select);
		switch(value){
		case 0:operation =ALUopr.CLEAR;break;
		case 1:operation =ALUopr.SUBBA;break;
		case 2:operation =ALUopr.SUBAB;break;
		case 3:operation =ALUopr.ADDAB;break;
		case 4:operation =ALUopr.IDB;break;
		case 5:operation =ALUopr.NOTB;break;
		case 6:operation =ALUopr.IDA;break;
		case 7:operation =ALUopr.NOTA;break;
		case 8:operation =ALUopr.OR;break;
		case 9:operation =ALUopr.XOR;break;
		case 10:operation =ALUopr.EQ;break;
		case 11:operation =ALUopr.ANORB;break;
		case 12:operation =ALUopr.BNORA;break;
		case 13:operation =ALUopr.AND;break;
		}
		

	}

	public void setOut(Register newOut){
		outTo = newOut;
	}


	public void operation(){
		if(operation==ALUopr.CLEAR){
			if(carry){
				outTo.setInputBuffer(0);
			}
			else{
				outTo.setInputBuffer(1);
			}
		}
		if(operation==ALUopr.SUBAB){
			if(carry){

				outTo.setInputBuffer(a.getInt()- b.getInt());
			}else{

				outTo.setInputBuffer(a.getInt()- b.getInt()-1);
			}
		}
		if(operation==ALUopr.SUBBA){
			if(carry){
				outTo.setInputBuffer(b.getInt()- a.getInt());
			}else{
				outTo.setInputBuffer(b.getInt()- a.getInt()-1);
			}
		}
		if(operation==ALUopr.ADDAB){
			if(carry){
				outTo.setInputBuffer(a.getInt()+ b.getInt()+1);
			}
			else{
				outTo.setInputBuffer(a.getInt()+ b.getInt());
			}
		}
		if(operation==ALUopr.NOTA){
			boolean[] not= new boolean[a.getOutputBuffer().length];
			for(int i=0;i<a.getOutputBuffer().length;i++){
				not[i] = !not[i];
			}
			if(carry){
				outTo.setInputBuffer(outTo.convertToInt(not)+1);
			}else{
				outTo.setInputBuffer(outTo.convertToInt(not));
			}

		}
		if(operation==ALUopr.NOTB){
			boolean[] not= new boolean[b.getOutputBuffer().length];
			for(int i=0;i<b.getOutputBuffer().length;i++){
				not[i] = !not[i];
			}				
			if(carry){
				outTo.setInputBuffer(outTo.convertToInt(not)+1);
			}else{
				outTo.setInputBuffer(outTo.convertToInt(not));
			}
		}
		if(operation==ALUopr.IDA){
			outTo.setInputBuffer(a.getInt());
		}
		if(operation==ALUopr.IDB){
			outTo.setInputBuffer(b.getInt());
		}

	}

	public void setSelect(boolean[] newselect){
		select =newselect;
	}

	public void update(){
		selectOp();
		operation();
	}

	

}
