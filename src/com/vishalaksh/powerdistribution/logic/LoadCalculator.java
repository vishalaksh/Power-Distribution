package com.vishalaksh.powerdistribution.logic;

import java.util.ArrayList;

public class LoadCalculator implements Constants {

	void function() {

		// get load phase
		int phaseLoad = 3;

		// get phase number
		int phase = 3;

		// get number of windows
		int windows = 4;

		// get va rating and corresponding time window
		ArrayList<LoadWindows> al = getwindows(windows);

		// fill time array
		int loadTimeArr[][] = new int[phase][time_total];

		if (phaseLoad == 1) {
			for (int i = 0; i < windows; i++) {
				LoadWindows lw = al.get(i);
				// if phase is -1 then auto else manual
				int currPhase = lw.phase_int;
				if (currPhase < 0) {
					// get the array with lowest peak element
					currPhase = getAutoPhase(loadTimeArr);
				}
				// end time is excluded
				for (int j = lw.time_start_mins; j < lw.time_end_mins; j++) {
					loadTimeArr[currPhase][j] += lw.rating;
				}
			}
		} else if (phaseLoad == 3) {
			// for 3 phase load, add equally in all 3 phases
			for (int i = 0; i < windows; i++) {
				LoadWindows lw = al.get(i);

				for (int currPhase = 0; currPhase < loadTimeArr.length; currPhase++) {
					// end time is excluded
					for (int j = lw.time_start_mins; j < lw.time_end_mins; j++) {
						loadTimeArr[currPhase][j] += (lw.rating / 3);
					}
				}
			}

		}

	}

	private int getAutoPhase(int[][] loadTimeArr) {
		int autoPhase = 0;
		int phases = loadTimeArr.length;
		int sum;
		int[] sumPhases = new int[phases];

		for (int i = 0; i < phases; i++) {
			sum = 0;
			for (int j = 0; j < time_total; j++) {
				sum += loadTimeArr[i][j];
			}
			sumPhases[i] = sum;
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < phases; i++) {
			if (min > sumPhases[i]) {
				min = sumPhases[i];
				autoPhase = i;
			}
		}
		return autoPhase;
	}

	private ArrayList<LoadWindows> getwindows(int windows) {
		ArrayList<LoadWindows> al = new ArrayList<>();

		for (int i = 0; i < windows; i++) {
			al.add(new LoadWindows(10, "10:00", "14:00", null));
		}

		return al;
	}

}
