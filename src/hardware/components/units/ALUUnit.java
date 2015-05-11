package hardware.components.units;

import java.util.ArrayList;

import hardware.components.ALUopr;
import hardware.components.HLRegister;
import hardware.components.Multiplexer;
import hardware.components.ALU;
import hardware.components.Register;
import hardware.exceptions.HardwareException;

public class ALUUnit {
	
	private boolean[] dataInputBus, dataOutputBus;
	private HLRegister DHDL;
	private Multiplexer dataMux, IRMux;
	private ALU ALU;
	private RegisterMemory RM;
	private Register RR, A, B, IR, RMselect;
	
	public ALUUnit() {
		dataInputBus = new boolean[16];
		dataOutputBus = new boolean[16];
		DHDL = new HLRegister(16, 32);
		ALU = new ALU(32);
		RM = new RegisterMemory(32, 8);
		RR = new Register(32);
		RMselect = new Register(3);
		Register.addToBuffers(RMselect);
		RR.addExtraBuffer(0, 15);
		RR.addExtraBuffer(16, 31);
		A = new Register(32);
		B = new Register(32);
		IR = new Register(32);
		Register temp1 = new Register(3);
		Register temp2 = new Register(3);
		IR.addExtraBuffer(3, 8);
		temp1.setInputBuffer(IR.getExtraBuffers().get(0));
		IR.addExtraBuffer(9, 15);
		temp2.setInputBuffer(IR.getExtraBuffers().get(1));
		ArrayList<Register> inputsList1 = new ArrayList<Register>();
		ArrayList<Register> inputsList2 = new ArrayList<Register>();
		inputsList1.add(RR);
		inputsList1.add(DHDL);
		inputsList2.add(temp1);
		inputsList2.add(temp2);
		Register.addToBuffers(temp1);
		Register.addToBuffers(temp2);
		RM.setOutTo(B);
		ALU.setOutTo(RR);
		dataOutputBus = RR.getOutputBuffer();
		try {
			dataMux = new Multiplexer(inputsList1);
			dataMux.addOutTo(A);
			dataMux.addOutTo(RM);
			IRMux = new Multiplexer(inputsList2);
			IRMux.addOutTo(RMselect);
		} catch(HardwareException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void setDataInputBus(boolean[] input) {
		dataInputBus = input;
		DHDL.setInputBuffer(dataInputBus);
		IR.setInputBuffer(dataInputBus);
	}
	
	public void setRR_HL(boolean HL) {
		if(!HL) {
			dataOutputBus = RR.getExtraBuffers().get(0);
		} else {
			dataOutputBus = RR.getExtraBuffers().get(1);
		}
	}
	
	public boolean[] getDataOutputBus() {
		return dataOutputBus;
	}
	
	public void setRSEL(boolean RSEL) throws HardwareException {
		if(!RSEL) {
			IRMux.setSelect(0);
		} else {
			IRMux.setSelect(1);
		}
	}
	
	public void setRMWrite(boolean Write) {
		RM.setReadWrite(Write);
	}
	
	public void setEnableB(boolean enable) {
		B.setEnable(enable);
	}
	
	public void setEnableA(boolean enable) {
		A.setEnable(enable);
	}
	
	public void setALUSelect(boolean[] selects) {
		ALU.setSelect(selects);
	}
	
	public void setALUCarry(boolean carry) {
		ALU.setCarry(carry);
	}
	
	public void setALUOperation(ALUopr operation) {
		ALU.setSelect(operation);
	}
	
	public void setEXT(boolean EXT) throws HardwareException {
		if(!EXT) {
			dataMux.setSelect(0);
		} else {
			dataMux.setSelect(1);
		}
	}
	
	public void setEDH(boolean EDH) {
		DHDL.setEnableHigh(EDH);
	}
	
	public void setEDL(boolean EDL) {
		DHDL.setEnableLow(EDL);
	}
	
	public void setERR(boolean ERR) {
		RR.setEnable(ERR);
	}
	
	public void setDOH(boolean DOH) {
		if(DOH) {
			setRR_HL(true);
		}
	}
	
	public void setDOL(boolean DOL) {
		if(DOL) {
			setRR_HL(false);
		}
	}
	
	public void setEIR(boolean EIR) {
		IR.setEnable(EIR);
	}

	public boolean[] getDataInputBus() {
		return dataInputBus;
	}

	public HLRegister getDHDL() {
		return DHDL;
	}

	public Multiplexer getDataMux() {
		return dataMux;
	}

	public Multiplexer getIRMux() {
		return IRMux;
	}

	public ALU getALU() {
		return ALU;
	}

	public RegisterMemory getRM() {
		return RM;
	}

	public Register getRR() {
		return RR;
	}

	public Register getA() {
		return A;
	}

	public Register getB() {
		return B;
	}

	public Register getIR() {
		return IR;
	}

	public Register getRMselect() {
		return RMselect;
	}

}
