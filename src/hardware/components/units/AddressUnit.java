package hardware.components.units;

import java.util.ArrayList;

import hardware.components.HLRegister;
import hardware.components.Multiplexer;
import hardware.components.Register;
import hardware.exceptions.HardwareException;

public class AddressUnit {
	
	private boolean[] addressInputBus, addressOutputBus;
	private HLRegister AHL;
	private Register PC, OAR, SP;
	private Multiplexer addressMux;
	
	public AddressUnit() {
		addressInputBus = new boolean[16];
		addressOutputBus = new boolean[16];
		AHL = new HLRegister(16, 32);
		PC = new Register(32);
		OAR = new Register(32);
		SP = new Register(32);
		ArrayList<Register> inputsList = new ArrayList<Register>();
		inputsList.add(PC);
		inputsList.add(OAR);
		inputsList.add(SP);
		PC.setInputBuffer(AHL.getOutputBuffer());
		OAR.setInputBuffer(AHL.getOutputBuffer());
		SP.setInputBuffer(AHL.getOutputBuffer());
		try {
			addressMux = new Multiplexer(inputsList);
			Register temp = new Register(32);
			Register.addToBuffers(temp);
			addressMux.addOutTo(temp);
			addressOutputBus = temp.getOutputBuffer();
		} catch(HardwareException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void setEAL(boolean EAL) {
		AHL.setEnableLow(EAL);
	}
	
	public void setEAH(boolean EAH) {
		AHL.setEnableHigh(EAH);
	}
	
	public void setESP(boolean ESP) {
		SP.setEnable(ESP);
	}
	
	public void setEOAR(boolean EOAR) {
		OAR.setEnable(EOAR);
	}
	
	public void setEPC(boolean EPC) {
		PC.setEnable(EPC);
	}
	
	public void setCAD(boolean[] CAD) throws HardwareException {
		addressMux.setSelect(CAD);
	}
	
	public void setCAD(int CAD) throws HardwareException {
		addressMux.setSelect(CAD);
	}
	
	public void incPC() {
		PC.setInputBuffer(PC.getInt() + 1);
	}
	
	public void incOAR() {
		OAR.setInputBuffer(OAR.getInt() + 1);
	}
	
	public void setAddressInputBus(boolean[] input) {
		addressInputBus = input;
		AHL.setInputBuffer(addressInputBus);
	}
	
	public boolean[] getAddressOutputBus() {
		return addressOutputBus;
	}

}
