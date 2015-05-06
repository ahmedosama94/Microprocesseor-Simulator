package hardware.components;

public class Register {
	
	private boolean[] current;
	private boolean[] connected;
	private boolean[] connectedNextCycle;
	private boolean enable;
	private boolean clock;
	private Register to;
	
	public Register(int size) {
		current = new boolean[size];
		connected = new boolean[size];
		connectedNextCycle = new boolean[size];
		clock = false;
		enable = false;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public void flipClock() {
		clock = !clock;
		if(clock) {
			if(to != null) {
			to.connect(current);
			}
			loadIfEnable();
			passConnected();
		}
	}
	
	private void passConnected() {
		for(int i = 0; i < connected.length; i++) {
			connected[i] = connectedNextCycle[i];
		}
	}
	
	private void loadIfEnable(){
		if(enable) {
			for(int i = 0; i < current.length; i++) {
				current[i] = connected[i];
			}
		}
	}
	
	public void connect(boolean[] newConnected) {
		for(int i = 0; i < connectedNextCycle.length; i++) {
			connectedNextCycle[i] = newConnected[i];
		}
	}
	
	public void connect(int newConnected) {
		boolean[] newValue = new boolean[connectedNextCycle.length];
		for(int i = 0; i < newValue.length; i++) {
			if(newConnected%2 == 1) {
				newValue[i] = true;
			} else {
				newValue[i] = false;
			}
			newConnected /= 2;
		}
		connectedNextCycle = newValue;
	}
	
	public int getInt() {
		int value = 0;
		for(int i = 0; i < current.length; i++) {
			if(current[i]) {
				value += Math.pow(2, (double)i);
			}
		}
		return value;
	}
	
	public String getHex() {
		String value = "";
		int n = getInt();
		if(n == 0) {
			value = "0";
		} else {
			while(n > 0) {
				switch(n%16) {
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
		for(int i = 0; i < current.length; i++) {
			if(current[i]) {
				value = "1" + value;
			} else {
				value = "0" + value;
			}
		}
		return value;
	}

}
