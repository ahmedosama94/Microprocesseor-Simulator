package hardware.components.units;

import hardware.components.Register;
import hardware.exceptions.HardwareException;

public class MainMemory {

	private Register[] memoryBlocks;
	private boolean readWrite;
	private Register outTo;
	private int address;

	public MainMemory(int Memsize , int Regsize){
		memoryBlocks = new Register[Memsize];
		for(int i = 0; i < Memsize; i++){
			memoryBlocks[i] = new Register(Regsize);
		}
	}

	public void setReadWrite(boolean readWrite) {
		this.readWrite = readWrite;
		memoryBlocks[address].setEnable(readWrite);
	}

	public void setAddress(boolean[] address) throws HardwareException {
		int value = 0;
		for(int i = 0; i < address.length; i++) {
			if(address[i]) {
				value += Math.pow(2, (double)i);
			}
		}
		if(value >= memoryBlocks.length) {
			throw new HardwareException("Out of bounds address!");
		}
		if(readWrite) {
			memoryBlocks[this.address].setEnable(false);
			memoryBlocks[value].setEnable(true);
		}
		this.address = value;
		if(outTo != null) {
			outTo.setInputBuffer(memoryBlocks[value].getOutputBuffer());
		}
	}

	public void setAddress(int address) throws HardwareException {
		if(address >= memoryBlocks.length) {
			throw new HardwareException("Out of bounds select!");
		}
		if(readWrite) {
			memoryBlocks[this.address].setEnable(false);
			memoryBlocks[address].setEnable(true);
		}
		this.address = address;
		if(outTo != null) {
			outTo.setInputBuffer(memoryBlocks[address].getOutputBuffer());
		}
	}

	public void setOutTo(Register out) {
		outTo = out;
		outTo.setInputBuffer(memoryBlocks[address].getOutputBuffer());
	}

	public void setInputBuffer(boolean[] input) {
		for(int i = 0; i < memoryBlocks.length; i++) {
			memoryBlocks[i].setInputBuffer(input);
		}
	}

	public void setInputBuffer(int value) {
		for(int i = 0; i < memoryBlocks.length; i++) {
			memoryBlocks[i].setInputBuffer(value);
		}
	}

	public int getNumberOfBlocks() {
		return memoryBlocks.length;
	}	

}