package hardware.components;

public class HLRegister extends Register {
	
	private Register high;
	private Register low;
	private boolean[] inputBuffer;
	
	public HLRegister(int inSize, int outSize) {
		super(outSize);
		high = new Register(inSize);
		low = new Register(inSize);
		inputBuffer = new boolean[inSize];
		high.setInputBuffer(inputBuffer);
		low.setInputBuffer(inputBuffer);
		setEnable(true);
	}
	
	public int getHighInt() {
		return high.getInt();
	}
	
	public String getHighHex() {
		return high.getHex();
	}
	
	public String getHighBits() {
		return high.getBits();
	}
	
	public int getLowInt() {
		return low.getInt();
	}
	
	public String getLowHex() {
		return low.getHex();
	}
	
	public String getLowBits() {
		return low.getBits();
	}
	
	public void setEnableHigh(boolean enable) {
		high.setEnable(enable);
	}
	
	public void setEnableLow(boolean enable) {
		low.setEnable(enable);
	}
	
	public void setInputBuffer(boolean[] input) {
		high.setInputBuffer(input);
		low.setInputBuffer(input);
	}
	
	public void setInputBuffer(int value) {
		high.setInputBuffer(value);
		low.setInputBuffer(value);
	}
	
	protected void clockCycle() {
		load();
	}
	
	protected void load() {
		high.clockCycle();
		low.clockCycle();
		high.updateOutputBuffer();
		low.updateOutputBuffer();
		boolean[] highbits = high.getOutputBuffer();
		boolean[] lowbits = low.getOutputBuffer();
		boolean[] newData = new boolean[this.getOutputBuffer().length];
		for(int i = 0; i < this.getOutputBuffer().length; i++) {
			if(i < lowbits.length) {
				newData[i] = lowbits[i];
			} else {
				newData[i] = highbits[i - lowbits.length];
			}
		}
		setData(newData);
	}

}
