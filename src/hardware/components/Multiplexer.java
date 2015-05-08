package hardware.components;

import hardware.exceptions.HardwareException;

import java.util.ArrayList;

public class Multiplexer {
	
	private boolean[][] inputs;
	private Register outTo;
	private int select;
	
	public Multiplexer(ArrayList<Register> inputsList) throws HardwareException {
		if(inputsList.isEmpty()) {
			throw new HardwareException("A multiplexer must be connected to an input!");
		}
		for(int i = 0; i < inputsList.size(); i++) {
			if(inputsList.get(i) == null) {
				throw new HardwareException("An input to a multiplexer cannot be null!");
			}
			if(inputsList.get(i).getOutputBuffer().length != 
					inputsList.get(0).getOutputBuffer().length) {
				throw new HardwareException("All inputs to the multiplexer must be"
						+ " of the same size!");
			}
		}
		inputs = new boolean[inputsList.size()][inputsList.get(0).getOutputBuffer().length];
		for(int i = 0; i < inputsList.size(); i++) {
			inputs[i] = inputsList.get(i).getOutputBuffer();
		}
	}
	
	public void setSelect(boolean[] selectBits) throws HardwareException {
		select = 0;
		for(int i = 0; i < selectBits.length; i++) {
			if(selectBits[i]) {
				select += Math.pow(2, (double)i);
			}
		}
		if(select >= inputs.length) {
			throw new HardwareException("Multiplexer select out of bounds!");
		}
		outTo.setInputBuffer(inputs[select]);
	}
	
	public void setSelect(int select) throws HardwareException {
		if(select >= inputs.length) {
			throw new HardwareException("Multiplexer select out of bounds!");
		}
		outTo.setInputBuffer(inputs[select]);
	}
	
	public void setOutTo(Register out) {
		outTo = out;
	}

}
