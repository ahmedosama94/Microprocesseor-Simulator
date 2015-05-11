package main;

import hardware.components.Register;

public class MicroProccessor {
	boolean RSEL,Write,EB,EA,EXT,EDH,EDL,DOH,DOL,EIR,AOH,AOL,CAD,ESP,EPC,EOAR,NAD,EAH,EAL;
	public MicroProccessor(){
		
	}
	
	public void ADD(){
		RSEL=true;
		EDH = true;
		EDL =true;
		EB=true;
		Register.clockCycleAll();
		
	}
	
}
