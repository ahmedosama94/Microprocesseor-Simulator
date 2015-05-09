package hardware.components.units;

import hardware.components.Register;
import hardware.exceptions.HardwareException;

public class RegisterMemory {
	
	private Register[] registers;
	private Register outTo;
	
	public RegisterMemory(int sizeOfRegister, int numberOfRegisters) {
		registers = new Register[numberOfRegisters];
		for(int i = 0; i < registers.length; i++) {
			registers[i] = new Register(sizeOfRegister);
		}
		setSelect(0);
	}
	
	public void setReadWrite(boolean readWrite) {
		for(int i = 0; i < registers.length; i++) {
			registers[i].setEnable(readWrite);
		}
	}
	
	public void setSelect(boolean[] select) throws HardwareException {
		int value = 0;
		for(int i = 0; i < select.length; i++) {
			if(select[i]) {
				value += Math.pow(2, (double)i);
			}
		}
		if(value >= select.length) {
			throw new HardwareException("Out of bounds select!");
		}
		outTo.setInputBuffer(registers[value].getOutputBuffer());
	}
	
	public void setSelect(int select) {
		outTo.setInputBuffer(registers[select].getOutputBuffer());
	}
	
	public void setOutTo(Register out) {
		outTo = out;
	}
	
	public void setInputBuffer(boolean[] input) {
		for(int i = 0; i < registers.length; i++) {
			registers[i].setInputBuffer(input);
		}
	}
	
}
