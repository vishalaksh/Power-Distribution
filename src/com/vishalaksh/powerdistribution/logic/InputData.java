package com.vishalaksh.powerdistribution.logic;

import java.util.ArrayList;

public class InputData {
	int phaseLoad;
	int phaseSupply;
	int windowsNumber;
	ArrayList<LoadWindows> alWindows;
	public InputData(int phaseLoad, int phaseSupply, int windowsNumber,
			ArrayList<LoadWindows> alWindows) {
		super();
		this.phaseLoad = phaseLoad;
		this.phaseSupply = phaseSupply;
		this.windowsNumber = windowsNumber;
		this.alWindows = alWindows;
	}
}
