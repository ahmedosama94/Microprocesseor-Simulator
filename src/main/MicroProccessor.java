package main;

import hardware.components.ALU;
import hardware.components.ALUopr;
import hardware.components.Register;
import hardware.components.units.ALUUnit;
import hardware.components.units.AddressUnit;
import hardware.components.units.RegisterMemory;

public class MicroProccessor {
	boolean RSEL,Write,EB,EA,EXT,EDH,EDL,DOH,DOL,EIR,AOH,AOL,CAD,ESP,EPC,EOAR,NAD,EAH,EAL,ERR;
	ALUUnit alu;
	AddressUnit addunit;
	
	public MicroProccessor(){
		
	}
	
	public void ADD(){
		RSEL=true;
		EDH = true;
		EDL =true;
		EB=true;
	
		Register.clockCycleAll();
		
		alu.getALU().setSelect(ALUopr.B);
		ERR =true;
		DOH=true;
		DOL=true;
		RSEL=true;
		
		Register.clockCycleAll();
	
	}
	
	public void ADDtomemory(){
		EDL=true;
		EDH=true;
		
	}
	
}
