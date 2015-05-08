package memory;

import hardware.components.Register;

public class RegisterMemory {

	private boolean[] inputBuffer;
	private boolean[] outputBuffer;
	private Register[] registers;
	private boolean readWrite;
	private int select;
	
	public RegisterMemory(int rSize, int numberOfRegisters){
		registers = new Register[numberOfRegisters];
		inputBuffer = new boolean[rSize];
		for(int i = 0; i < numberOfRegisters; i++) {
			registers[i] = new Register(rSize);
			registers[i].setInputBuffer(inputBuffer);
		}
		readWrite = false;
		select = 0;
	}
	
	public void setInputBuffer(boolean[] input) {
		inputBuffer = input;
		for(int i = 0; i < registers.length; i++) {
			registers[i].setInputBuffer(inputBuffer);
		}
	}
	
	public boolean[] read(Register register) {
			boolean[] result = new boolean[32] ;
			for(int i =0 ; i< registers.length;i++){
				if(registers[i]==register){
					result = registers[i].getOutputBuffer();
					}
				}
		return result ;
	}
	
	public void write (boolean[] newBits , Register register) {
		for(int i =0 ; i< registers.length;i++){
			if(registers[i]==register){
				registers[i].setInputBuffer(newBits);
			}
		}
	}
	
	
}
