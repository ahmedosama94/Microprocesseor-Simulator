package hardware.components;

import hardware.exceptions.HardwareException;

public class ALU {

	private Register outTo, a, b;
	private boolean[] select;
	private boolean carry;
	private ALUopr operation;

	public ALU(int size) {
		a = new ALURegister(size, this);
		b = new ALURegister(size, this);
		select = new boolean[4];
		carry=false;
	}

	public void convertToOp() {
		int value = Register.convertToInt(select);
		switch(value) {
		case 0:
			operation = ALUopr.CLEAR;break;
		case 1:
			operation = ALUopr.SUBBA;break;
		case 2:
			operation = ALUopr.SUBAB;break;
		case 3:
			operation = ALUopr.ADD;break;
		case 4:
			operation = ALUopr.IDB;break;
		case 5:
			operation = ALUopr.NOTB;break;
		case 6:
			operation = ALUopr.IDA;break;
		case 7:
			operation = ALUopr.NOTA;break;
		case 8:
			operation = ALUopr.OR;break;
		case 9:
			operation = ALUopr.XOR;break;
		case 10:
			operation = ALUopr.EQ;break;
		case 11:
			operation = ALUopr.ANORB;break;
		case 12:
			operation = ALUopr.BNORA;break;
		case 13:
			operation = ALUopr.AND;break;
		}


	}

	public void update()  {
		if(operation == ALUopr.CLEAR) {
			if(carry) {
				outTo.setInputBuffer(0);
			} else {
				outTo.setInputBuffer(1);
			}
		}
		if(operation == ALUopr.SUBAB) {
			if(carry) {

				outTo.setInputBuffer(a.getInt() - b.getInt());
			} else {
				outTo.setInputBuffer(a.getInt() - b.getInt() - 1);
			}
		}
		if(operation == ALUopr.SUBBA) {
			if(carry) {
				outTo.setInputBuffer(b.getInt() - a.getInt());
			} else {
				outTo.setInputBuffer(b.getInt() - a.getInt() - 1);
			}
		}
		if(operation == ALUopr.ADD) {
			if(carry) {
				outTo.setInputBuffer(a.getInt() + b.getInt() + 1);
			} else {
				outTo.setInputBuffer(a.getInt() + b.getInt());
			}
		}
		if(operation == ALUopr.NOTA) {
			boolean[] not= new boolean[a.getOutputBuffer().length];
			for(int i = 0; i < a.getOutputBuffer().length; i++){
				not[i] = !not[i];
			}
			if(carry) {
				outTo.setInputBuffer(Register.convertToInt(not) + 1);
			} else {
				outTo.setInputBuffer(Register.convertToInt(not));
			}

		}
		if(operation == ALUopr.NOTB) {
			boolean[] not = new boolean[b.getOutputBuffer().length];
			for(int i = 0; i < b.getOutputBuffer().length; i++) {
				not[i] = !not[i];
			}				
			if(carry) {
				outTo.setInputBuffer(Register.convertToInt(not) + 1);
			} else {
				outTo.setInputBuffer(Register.convertToInt(not));
			}
		}
		if(operation == ALUopr.IDA) {
			outTo.setInputBuffer(a.getInt());
		}
		if(operation == ALUopr.IDB) {
			outTo.setInputBuffer(b.getInt());
		}

	}
	
	public Register getA() {
		return a;
	}
	
	public Register getB() {
		return b;
	}
	
	public void setOutTo(Register out) {
		outTo = out;
		update();
	}

	public void setSelect(boolean[] newselect){
		select = newselect;
		convertToOp();
	}
	
	public void setSelect(ALUopr operation) throws HardwareException {
		int select = 0;
		switch(operation) {
		case CLEAR:
			select = 0;break;
		case SUBBA:
			select = 1;break;
		case SUBAB:
			select = 2;break;
		case ADD:
			select = 3;break;
		case IDB:
			select = 4;break;
		case NOTB:
			select = 5;break;
		case IDA:
			select = 6;break;
		case NOTA:
			select = 7;break;
		case OR:
			select = 8;break;
		case XOR:
			select = 9;break;
		case EQ:
			select = 10;break;
		case ANORB:
			select = 11;break;
		case BNORA:
			select = 12;break;
		case AND:
			select = 13;break;
		default:
			throw new HardwareException("No such Operation.");
		}
		setSelect(Register.convertToBool(select, this.select.length));
	}

}
