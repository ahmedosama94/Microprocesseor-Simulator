package main;

import hardware.components.ALUopr;
import hardware.components.Register;
import hardware.components.units.ALUUnit;
import hardware.components.units.AddressUnit;

public class MicroProccessor {
	
	private boolean RSEL, Write, EB,
		EA, EXT, EDH, EDL, DOH,
		DOL, EIR, AOH, AOL, CAD,
		ESP, EPC, EOAR, NAD, EAH,
		EAL, ERR;
	private ALUUnit ALU;
	private AddressUnit addrUnit;
	
	public MicroProccessor() {
		ALU = new ALUUnit();
		addrUnit = new AddressUnit();
	}
	
	public void ADD() {
		RSEL=true;
		EDH = true;
		EDL =true;
		EB=true;
	
		Register.clockCycleAll();
		
		ALU.getALU().setSelect(ALUopr.B);
		ERR =true;
		DOH=true;
		DOL=true;
		RSEL=true;
		
		Register.clockCycleAll();
		
		RSEL=true;
		EB=true;
		
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
	
	public void setFlags() {
		
	}

}
