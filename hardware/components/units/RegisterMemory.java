package hardware.components.units;

import hardware.components.Register;
import hardware.exceptions.HardwareException;

public class RegisterMemory {
	
	private Register[] registers;
	private Register outTo;
	private int select;
	private boolean readWrite;
	
	public RegisterMemory(int sizeOfRegister, int numberOfRegisters) {
		registers = new Register[numberOfRegisters];
		for(int i = 0; i < registers.length; i++) {
			registers[i] = new Register(sizeOfRegister);
		}
	}
	
	public void setReadWrite(boolean readWrite) {
		this.readWrite = readWrite;
		registers[select].setEnable(readWrite);
	}
	
	public void setSelect(boolean[] select) throws HardwareException {
		int value = 0;
		for(int i = 0; i < select.length; i++) {
			if(select[i]) {
				value += Math.pow(2, (double)i);
			}
		}
		if(value >= registers.length) {
			throw new HardwareException("Out of bounds select!");
		}
		if(readWrite) {
			registers[this.select].setEnable(false);
			registers[value].setEnable(true);
		}
		this.select = value;
		if(outTo != null) {
			outTo.setInputBuffer(registers[value].getOutputBuffer());
		}
	}
	
	public void setSelect(int select) throws HardwareException {
		if(select >= registers.length) {
			throw new HardwareException("Out of bounds select!");
		}
		if(readWrite) {
			registers[this.select].setEnable(false);
			registers[select].setEnable(true);
		}
		this.select = select;
		if(outTo != null) {
			outTo.setInputBuffer(registers[select].getOutputBuffer());
		}
	}
	
	public void setOutTo(Register out) {
		outTo = out;
		outTo.setInputBuffer(registers[select].getOutputBuffer());
	}
	
	public void setInputBuffer(boolean[] input) {
		for(int i = 0; i < registers.length; i++) {
			registers[i].setInputBuffer(input);
		}
	}
	
	public void setInputBuffer(int value) {
		for(int i = 0; i < registers.length; i++) {
			registers[i].setInputBuffer(value);
		}
	}
	
	public int getNumberOfRegisters() {
		return registers.length;
	}
	
	public int getSizeOfRegisters() {
		return registers[0].getOutputBuffer().length;
	}
	
}
