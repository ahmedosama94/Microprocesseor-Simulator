package main;

import hardware.components.ALUopr;
import hardware.components.Register;
import hardware.components.units.ALUUnit;
import hardware.components.units.AddressUnit;
import hardware.components.units.MainMemory;
import hardware.exceptions.HardwareException;

public class MicroProccessor {
	
	private ALUUnit ALU;
	private AddressUnit addrUnit;
	private MainMemory MM;
	
	public MicroProccessor() {
		ALU = new ALUUnit();
		addrUnit = new AddressUnit();
		MM = new MainMemory(200, 16);
		Register buffer = new Register(16);
		Register.addToBuffers(buffer);
		ALU.setDataInputBus(buffer.getOutputBuffer());
		addrUnit.setAddressInputBus(buffer.getOutputBuffer());
		MM.setInputBuffer(ALU.getDataOutputBus());
	}
	
	public void MOV$() throws HardwareException {
		MM.setAddress(addrUnit.getAddressOutputBus());
		MM.setReadWrite(false);
		ALU.setRSEL(false);
		ALU.setRMWrite(false);
		ALU.setEnableB(false);
		ALU.setEnableA(false);
		ALU.setALUSelect(0);
		ALU.setALUCarry(false);
		ALU.setEXT(false);
		ALU.setEDH(false);
		ALU.setEDL(false);
		ALU.setDOH(false);
		ALU.setDOL(false);
		ALU.setERR(false);
		addrUnit.setEAL(true);
		addrUnit.setEAH(false);
		addrUnit.setESP(false);
		addrUnit.setEOAR(false);
		addrUnit.setEPC(false);
		addrUnit.setCAD(0);
	}
	
	public void ADD() {
		
		
		
	}
	
	public void ADDtomemory(){
		ALU.setEDH(true);
		Register.clockCycleAll();
		ALU.setEDL(true);
		Register.clockCycleAll();
		ALU.setEnableA(true);
		ALU.setALUOperation(ALUopr.A);
		Register.clockCycleAll();
		ALU.setEnableB(true);
		Register.clockCycleAll();
		ALU.setERR(true);
		ALU.setALUOperation(ALUopr.ADD);
	}
	
	public void MOV() {
		
	}

}
