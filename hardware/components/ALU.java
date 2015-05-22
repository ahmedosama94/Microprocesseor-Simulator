package hardware.components;

public class ALU {

	private Register outTo, a, b;
	private int select;
	private boolean carry;
	private ALUopr operation;

	public ALU(int size) {
		a = new ALURegister(size, this);
		b = new ALURegister(size, this);
		select = 0;
		carry=false;
	}

	public void setCarry(boolean newcarry) {
		carry = newcarry;
	}

	public void convertToOp() {
		int value = select;
		switch(value) {
		case 0:
			operation= (!carry? ALUopr.SET1 : ALUopr.CLEAR);break;
		case 1:
			operation= (carry? ALUopr.SUBBA : ALUopr.SUB_BA_DEC);break;
		case 2:
			operation= (carry? ALUopr.SUBAB : ALUopr.SUB_AB_DEC);break;
		case 3:
			operation= (carry? ALUopr.ADDINC : ALUopr.ADD);break;
		case 4:
			operation= (carry? ALUopr.INCB : ALUopr.B);break;
		case 5:
			operation= (carry? ALUopr.INCNOTB : ALUopr.NOTB);break;
		case 6:
			operation= (carry? ALUopr.INCA : ALUopr.A);break;
		case 7:
			operation= (carry? ALUopr.INCNOTA : ALUopr.NOTA);break;
		case 8:
			operation = ALUopr.OR;break;
		case 9:
			operation = ALUopr.XOR;break;
		case 10:
			operation = ALUopr.EQ;break;
		case 11:
			operation = ALUopr.NOT_A_OR_B;break;
		case 12:
			operation = ALUopr.NOT_B_OR_A;break;
		case 13:
			operation = ALUopr.AND;break;
		case 14:
			operation = ALUopr.NAND;break;
		}
		update();
	}

	public void update()  {
		if(operation == ALUopr.SET1) {
			outTo.setInputBuffer(1);
		}
		if(operation == ALUopr.CLEAR) {
			outTo.setInputBuffer(0);
		}
		if(operation == ALUopr.SUBAB) {
			outTo.setInputBuffer(a.getInt() - b.getInt());
		}
		if(operation == ALUopr.SUB_AB_DEC) {
			outTo.setInputBuffer(a.getInt() - b.getInt() - 1);
		}
		if(operation == ALUopr.SUBBA) {
			outTo.setInputBuffer(b.getInt() - a.getInt());
		}
		if(operation == ALUopr.SUB_BA_DEC) {
			outTo.setInputBuffer(b.getInt() - a.getInt() - 1);
		}
		if(operation == ALUopr.ADD) {
			outTo.setInputBuffer(a.getInt() + b.getInt());
		}
		if(operation == ALUopr.ADDINC) {
			outTo.setInputBuffer(a.getInt() + b.getInt() + 1);
		}
		if(operation == ALUopr.NOTA) {
			outTo.setInputBuffer(~a.getInt());
		}
		if(operation == ALUopr.NOTB) {
			outTo.setInputBuffer(~b.getInt());
		}
		if(operation == ALUopr.A) {
			outTo.setInputBuffer(a.getInt());
		}
		if(operation == ALUopr.B) {
			outTo.setInputBuffer(b.getInt());
		}
		if(operation == ALUopr.AND) {
			outTo.setInputBuffer(a.getInt() & b.getInt());
		}
		if(operation == ALUopr.OR) {
			outTo.setInputBuffer(a.getInt() | b.getInt());
		}
		if(operation == ALUopr.INCNOTA) {
			outTo.setInputBuffer(~a.getInt() + 1);
		}
		if(operation == ALUopr.INCNOTB) {
			outTo.setInputBuffer(~b.getInt() + 1);	
		}
		if(operation == ALUopr.XOR) {
			outTo.setInputBuffer(a.getInt() ^ b.getInt());
		}
		if(operation == ALUopr.EQ){
			outTo.setInputBuffer(~(a.getInt() ^ b.getInt()));
		}
		if(operation == ALUopr.INCA) {
			outTo.setInputBuffer(a.getInt() + 1);
		}
		if(operation == ALUopr.INCB) {
			outTo.setInputBuffer(b.getInt() + 1);
		}
		if(operation == ALUopr.NAND) {
			outTo.setInputBuffer(~(a.getInt() & b.getInt()));
		}
		if(operation == ALUopr.NOT_A_OR_B) {
			outTo.setInputBuffer(~a.getInt() | b.getInt());
		}
		if(operation == ALUopr.NOT_B_OR_A) {
			outTo.setInputBuffer(a.getInt() | ~b.getInt());
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
		select = Register.convertNormal(newselect);
		convertToOp();
	}
	
	public void setSelect(int newSelect) {
		select = newSelect;
		convertToOp();
	}

	public void setSelect(ALUopr operation) {
		int select = 0;
		switch(operation) {
		case SET1:
			select = 0;carry=false;break;
		case CLEAR:
			select = 0;carry=true;break;
		case SUB_BA_DEC:
			select = 1;carry=false;break;
		case SUBBA:
			select = 1;carry=true;break;
		case SUB_AB_DEC:
			select = 2;carry=false;break;
		case SUBAB:
			select = 2; carry=true;break;
		case ADD:
			select = 3;carry=false;break;
		case ADDINC:
			select = 3;carry=true;break;
		case B:
			select = 4;carry=false;break;
		case INCB:
			select = 4;carry=true;break;
		case NOTB:
			select = 5;carry=false;break;
		case INCNOTB:
			select =5;carry=true;break;
		case A:
			select = 6;carry=false;break;
		case INCA:
			select = 6;carry=true;break;
		case NOTA:
			select = 7;carry=false;break;
		case INCNOTA:
			select = 7;carry=true;break;
		case OR:
			select = 8;break;
		case XOR:
			select = 9;break;
		case EQ:
			select = 10;break;
		case NOT_A_OR_B:
			select = 11;break;
		case NOT_B_OR_A:
			select = 12;break;
		case AND:
			select = 13;break;
		case NAND:
			select = 14;break;
		}
		setSelect(select);
	}

}
