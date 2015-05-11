package hardware.components.units;

import hardware.components.Register;
import hardware.exceptions.HardwareException;

public class MainMemory {

	private Register[] memoryBlocks;
	private boolean readWrite;
	private Register outTo;
	private int address ;
	private int src , prg ,dest;

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

	public void SetProAtt(int x, int y, int z) {
		prg =x;
		src=y;
		dest=z;
	}

	public int GetProgAttrP() {
		return prg;
	}

	public int GetProgAttrSrc() {
		return src;
	}

	public int GetProgAttrDest() {
		return dest;
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

	public void decode(boolean[] instruction){
		boolean[] program = new boolean[4];
		boolean[] destination = new boolean[6];
		boolean[] source = new boolean[6];
		int j =0;
		int m=0;
		for(int i=0;i<instruction.length;i++){
			if(i<4){
				program[i] =instruction[i];
			}
			else if(i<10){
				source[j] =instruction[i];
				j++;
			}
			else {
				destination[m]=instruction[i];
				m++;
			}
		}
	}
	
	public void MicroProgram(int add) {
		decode(memoryBlocks[add].getOutputBuffer());
		
	}
	
	
	

}