package main;

import hardware.components.ALU;
import hardware.components.ALUopr;
import hardware.components.Conditions;
import hardware.components.Register;
import hardware.components.units.MainMemory;
import hardware.exceptions.HardwareException;

public class Programs {

	MainMemory memoryblock;
	Register IR;
	ALU alu;

	public void MOV(Register one , Register two) throws HardwareException {
		memoryblock.setOutTo(IR);
		memoryblock.setInputBuffer(memoryblock.GetProgAttrSrc());
		alu.setSelect(ALUopr.A);
		alu.setOutTo(two);
	}

	public void MOV(int address , Register one) throws HardwareException {
		memoryblock.setReadWrite(false);
		memoryblock.setAddress(address);
		memoryblock.setOutTo(IR);
		alu.setSelect(ALUopr.A);
		alu.setOutTo(one);
	}

	public void ADD(int address , Register one ) throws HardwareException{
		memoryblock.setReadWrite(false);
		memoryblock.setAddress(address);
		memoryblock.setOutTo(IR);
		alu.setSelect(ALUopr.ADD);
		alu.setOutTo(one);
	}
	
	public void ADD (Register one , Register two) throws HardwareException {
		memoryblock.setOutTo(IR);
		memoryblock.setInputBuffer(memoryblock.GetProgAttrSrc());
		alu.setSelect(ALUopr.ADD);
		alu.setOutTo(two);
	}
	
	public void JMPUN(int add) {
		memoryblock.setReadWrite(false);
		
	}
	
	public void JMP(int add, Conditions condition) {
		
	}
}
