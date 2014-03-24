package com.vishalaksh.powerdistribution.logic;

public interface Constants {

	int rating_trans_per_phase = 500;
	int time_total = 60*24;
	
	String phase_A="A";
	String phase_B="B";
	String phase_C="C";
	
	String keyPanelOne="panelOne";
	String keyPanelTwo="panelTwo";
	String keyPanelThree="panelThree";
	
	String fileInput="Input_Data.txt";
	
	int LineNumberphaseLoad=1;
	int LineNumberphaseSupply=2;
	int LineNumberwindowsNumber=3;
	int LineNumberWindows=4;
	
	int xSpacingConst=2;
	
	float ordinateIncScale=100.0f;
	float abcissaIncScale=80.0f;
}
