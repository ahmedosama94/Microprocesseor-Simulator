package memory;

import hardware.components.Register;

public class RegisterMemory {

	Register[] registers ;
	
	public RegisterMemory(Register[] rs){
		
		registers = rs;
		
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
