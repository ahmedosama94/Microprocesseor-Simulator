package hardware.components;

import java.util.ArrayList;

public class Register {

	private boolean[] data;
	private boolean enable;
	private boolean[] inputBuffer;
	private boolean[] outputBuffer;
	private static ArrayList<Register> allRegisters = new ArrayList<Register>();

	public Register(int size) {
		data = new boolean[size];
		enable = false;
		inputBuffer = new boolean[size];
		outputBuffer = new boolean[size];
		allRegisters.add(this);
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getInt() {
		int value = Register.convertToInt(data);
		return value;
	}

	public String getHex() {
		String value = "";
		int n = getInt();
		if(n == 0) {
			value = "0";
		} else {
			while(n > 0) {
				int current = n % 16;
				switch(current) {
				case 0:case 1:case 2:
				case 3:case 4:case 5:
				case 6:case 7:case 8:
				case 9:
					value = current + value;break;
				case 10:
					value = "A" + value;break;
				case 11:
					value = "B" + value;break;
				case 12:
					value = "C" + value;break;
				case 13:
					value = "D" + value;break;
				case 14:
					value = "E" + value;break;
				case 15:
					value = "F" + value;break;
				}
				n /= 16;
			}
		}
		value = "0x" + value;
		return value;
	}

	public String getBits() {
		String value = "";
		for(int i = 0; i < data.length; i++) {
			if(data[i]) {
				value = "1" + value;
			} else {
				value = "0" + value;
			}
		}
		return value;
	}

	public void setInputBuffer(boolean[] input) {
		inputBuffer = input;
	}

	public void setInputBuffer(int value) {
		boolean[] input = new boolean[inputBuffer.length];
		boolean negative = false;
		if(value < 0) {
			negative = true;
		}
		for(int i = 0; i < input.length; i++) {
			if(Math.abs(value)%2 == 1 ^ negative) {
				input[i] = true;
			} else {
				input[i] = false;
			}
			value /= 2;
		}
		if(negative) {
			for(int i = 0; i < input.length; i++) {
				if(input[i]) {
					input[i] = false;
				} else {
					input[i] = true;
					break;
				}
			}
		}
		inputBuffer = input;
	}

	public boolean[] getOutputBuffer() {
		return outputBuffer;
	}

	public void updateOutputBuffer() {
		for(int i = 0; i < outputBuffer.length; i++) {
			outputBuffer[i] = data[i];
		}
	}

	protected void clockCycle() {
		if(enable) {
			load();
		}
	}

	protected void load() {
		for(int i = 0; i < data.length; i++) {
			data[i] = inputBuffer[i];
		}
	}

	protected void setData(boolean[] newData) {
		data = newData;
	}

	public static int convertToInt(boolean[] buffer) {
		int value = 0;
		for(int i = 0; i < buffer.length - 1; i++) {
			if(!buffer[buffer.length - 1]) {
				if(buffer[i]) {
					value += Math.pow(2, (double)i);
				}
			} else {
				if(!buffer[i]) {
					value += Math.pow(2, (double)i);
				}
			}
		}
		if(buffer[buffer.length - 1]) {
			value += 1;
			value = -value;
		}
		return value;
	}
	
	public static int convertNormal(boolean[] buffer) {
		int value = 0;
		for(int i = 0; i < buffer.length; i++) {
			if(buffer[i]) {
				value += Math.pow(2, (double)i);
			}
		}
		return value;
	}

	public static boolean[] convertToBool(int value, int size) {
		boolean[] output = new boolean[size];
		for(int i = 0; i < output.length; i++) {
			if(value%2 == 1) {
				output[i] = true;
			} else {
				output[i] = false;
			}
			value /= 2;
		}
		return output;
	}

	public static void clockCycleAll() {
		for(int i = 0; i < allRegisters.size(); i++) {
			allRegisters.get(i).clockCycle();
		}
		updateAllOutputs();
	}

	private static void updateAllOutputs() {
		for(int i = 0; i < allRegisters.size(); i++) {
			allRegisters.get(i).updateOutputBuffer();
		}
	}

}
