package hardware.components.units;

import hardware.components.Register;
import hardware.exceptions.HardwareException;

public class MainMemory {

	private Register[] memoryBlocks;
	private boolean readWrite;
	private int select ;

	public MainMemory(int Memsize , int Regsize){
		memoryBlocks =new Register[Memsize];
		for(int i =0; i<Memsize;i++){
			memoryBlocks[i] = new Register(Regsize);
		}
		readWrite = false;
	}


	public void setReadWrite(boolean readWrite) {
		this.readWrite = readWrite;
		memoryBlocks[select].setEnable(readWrite);
	}

	public void setSelect(int newselect) throws HardwareException {

		if(newselect >= memoryBlocks.length) {
			throw new HardwareException("Out of bounds select!");
		}
		if(readWrite) {
			memoryBlocks[this.select].setEnable(false);
			memoryBlocks[newselect].setEnable(true);
		}
		this.select = newselect;
	}

	public void setSelect(boolean[] select) throws HardwareException {
		int value = 0;
		for(int i = 0; i < select.length; i++) {
			if(select[i]) {
				value += Math.pow(2, (double)i);
			}
		}
		if(value >= memoryBlocks.length) {
			throw new HardwareException("Out of bounds select!");
		}
		if(readWrite) {
			memoryBlocks[this.select].setEnable(false);
			memoryBlocks[value].setEnable(true);
		}
		this.select = value;
	}

	public void setOutoutTo(Register out) {
		out.setInputBuffer(memoryBlocks[select].getOutputBuffer());
	}

	public void setInputBuffer(boolean[] input) {
		memoryBlocks[select].setInputBuffer(input);
	}
	

}
