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
		if(!select[0]){
			if(!select[1]){
				if(!select[2]){
					if(!select[3]){
						//00000
						operation =ALUopr.CLEAR;
					}
					else 
					{	
						//00010
						operation =ALUopr.SUBBA;

					}
				}else{
					if(!select[3]){
						//00100
						operation =ALUopr.SUBAB;

					}else{
						//00110
						operation =ALUopr.ADDAB;
					}

				}
			}else{
				if(!select[2]){
					if(!select[3]){
						//01000
						operation =ALUopr.IDB;
					}
					else 
					{	//01010
						operation =ALUopr.NOTB;
					}
				}else{
					if(!select[3]){
						//01101
						//01100
						operation =ALUopr.IDA;
					}else{//01110
						operation =ALUopr.NOTA;
						//10001
						operation =ALUopr.OR;
						//10011
						operation =ALUopr.XOR;
						//10101
						operation =ALUopr.EQ;
						//10111
						operation =ALUopr.ANORB;
						//10110
						//11001
						operation =ALUopr.BNORA;
						//11011
						operation =ALUopr.AND;

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
