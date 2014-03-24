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
	
	int LineNumberphaseLoad=12;
	int LineNumberphaseSupply=14;
	//int LineNumberwindowsNumber=3;
	int LineNumberWindows=17;
	
	int xSpacingConst=2;
	
	float ordinateIncScale=100.0f;
	float abcissaIncScale=80.0f;
	
	String defaultText="NOTE:\n1. PLEASE DONT MODIFY THE FILE EXCEPT THE INPUT DATA\n2. 3 PHASES ARE NAMED AS 'A', 'B' AND 'C'\n3. TIME FORMAT IS hh:mm:\n4. IT IS NOT NECESSARY TO SPECIFY THE CONNECTED PHASE OF LOAD\n5. LOAD DETAILS ARE SEPERATED BY SPACES\n6. PER PHASE RATING OF TRANSFORMER IS SET AS 500\n-------------------------------------------------------------------\n-------------------------------------------------------------------\n-------------------------------------------------------------------\nEnter the number of phases of load:\n1\nEnter the number of phases of supply:\n3\nEnter the details of load in following format:\nLoad start_time end_time connected_phase\n100 10:00 16:00 A\n200 12:00 18:00 B\n300 13:00 18:00 C\n100 13:00 18:00 C\n200 08:00 12:00 A";
}
