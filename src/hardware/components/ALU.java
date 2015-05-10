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
						if(carry){
							//00001
							operation =ALUopr.SET1;
						}
						else{
							//00000
							operation =ALUopr.CLEAR;
						}
					}
					else 
					{	
						if(carry){
							//00011
							operation =ALUopr.SUBBA;
						}
						else{
							//00010
							operation =ALUopr.SUBBADEC;
						}
					}
				}else{
					if(!select[3]){
						//00101
						if(carry){
							operation =ALUopr.SUBABDEC;
						}else{
							//00100
							operation =ALUopr.SUBAB;
						}
					}else{
						if(carry){
							//00111
							operation =ALUopr.ADDAB;
						}else{
							//00110
							operation =ALUopr.ADDINC;
						}

					}

				}
			}else{
				if(!select[2]){
					if(!select[3]){
						if(carry){
							//01001
							operation =ALUopr.SET1;
						}
						else{
							//01000
							operation =ALUopr.CLEAR;
						}
					}
					else 
					{	
						if(carry){
							//01011
							operation =ALUopr.SUBBA;
						}
						else{
							//01010
							operation =ALUopr.SUBBADEC;
						}
					}
				}else{
					if(!select[3]){
						//01101
						if(carry){
							operation =ALUopr.SUBABDEC;
						}else{
							//01100
							operation =ALUopr.SUBAB;
						}
					}else{
						if(carry){
							//01111
							operation =ALUopr.ADDAB;
						}else{
							//01110
							operation =ALUopr.ADDINC;
						}

					}
				}
			}
		}else{
			if(!select[1]){
				if(!select[2]){
					if(!select[3]){
						if(carry){
							//10001
							operation =ALUopr.SET1;
						}
						else{
							//10000
							operation =ALUopr.CLEAR;
						}
					}
					else 
					{	
						if(carry){
							//10011
							operation =ALUopr.SUBBA;
						}
						else{
							//10010
							operation =ALUopr.SUBBADEC;
						}
					}
				}else{
					if(!select[3]){
						//10101
						if(carry){
							operation =ALUopr.SUBABDEC;
						}else{
							//10100
							operation =ALUopr.SUBAB;
						}
					}else{
						if(carry){
							//10111
							operation =ALUopr.ADDAB;
						}else{
							//10110
							operation =ALUopr.ADDINC;
						}

					}

				}
			}else{
				if(!select[2]){
					if(!select[3]){
						if(carry){
							//11001
							operation =ALUopr.SET1;
						}
						else{
							//11000
							operation =ALUopr.CLEAR;
						}
					}
					else 
					{	
						if(carry){
							//11011
							operation =ALUopr.SUBBA;
						}
						else{
							//11010
							operation =ALUopr.SUBBADEC;
						}
					}
				}else{
					if(!select[3]){
						//11101
						if(carry){
							operation =ALUopr.SUBABDEC;
						}else{
							//11100
							operation =ALUopr.SUBAB;
						}
					}else{
						if(carry){
							//11111
							operation =ALUopr.ADDAB;
						}else{
							//11110
							operation =ALUopr.ADDINC;
						}

					}
				}
			
			}
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

	public void operation(){
		int result=0;
		if(carry){
			if(operation==ALUopr.CLEAR){
				outTo.setInputBuffer(0);
			}
			if(operation==ALUopr.SUBAB){
				outTo.setInputBuffer(a.getInt()- b.getInt());
			}
			if(operation==ALUopr.SUBBA){
				outTo.setInputBuffer(b.getInt()- a.getInt());
			}
			if(operation==ALUopr.ADDAB){
				outTo.setInputBuffer(a.getInt()+ b.getInt());
			}
			if(operation==ALUopr.NOTA){
				boolean[] not= new boolean[a.getOutputBuffer().length];
				for(int i=0;i<a.getOutputBuffer().length;i++){
					not[i] = !not[i];
				}
				outTo.setInputBuffer(not);
			}
			if(operation==ALUopr.NOTB){
				boolean[] not= new boolean[b.getOutputBuffer().length];
				for(int i=0;i<b.getOutputBuffer().length;i++){
					not[i] = !not[i];
				}
				outTo.setInputBuffer(not);
			}
			if(operation==ALUopr.IDA){
				outTo.setInputBuffer(a.getInt());
			}
			if(operation==ALUopr.IDB){
				outTo.setInputBuffer(b.getInt());
			}
		}else{
			if(operation==ALUopr.CLEAR){
				outTo.setInputBuffer(1);
			}
			if(operation==ALUopr.SUBAB){
				outTo.setInputBuffer(a.getInt()- b.getInt()-1);
			}
			if(operation==ALUopr.SUBBA){
				outTo.setInputBuffer(b.getInt()- a.getInt()-1);
			}
			if(operation==ALUopr.ADDAB){
				outTo.setInputBuffer(a.getInt()+ b.getInt()+1);
			}
		}
	}

	public void update(){
		operation();
	}



}
