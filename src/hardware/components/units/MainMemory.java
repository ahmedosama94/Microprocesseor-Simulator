package hardware.components.units;

import hardware.components.Register;

public class MainMemory {
	
	private Register[] memoryBlocks;
	private boolean readwrite;
	private boolean select;
	
	public MainMemory(int Memsize , int Regsize){
		memoryBlocks =new Register[Memsize];
		for(int i =0; i<Memsize;i++){
			memoryBlocks[i] = new Register(Regsize);
		}
	}
	 
	public void write(int i, boolean[] input){
		memoryBlocks[i].setInputBuffer(input);
	}
	
	public boolean[] read(int i){
		return memoryBlocks[i].getOutputBuffer();
		
	}
	
	
	
	
	
	
}
