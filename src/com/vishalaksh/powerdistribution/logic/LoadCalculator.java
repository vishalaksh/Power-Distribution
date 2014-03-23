package com.vishalaksh.powerdistribution.logic;

import java.util.ArrayList;

public class LoadCalculator implements Constants {

	// get load phase
	public int phaseLoad = 3;

	// get phase number
	public	int phase = 3;

	// get number of windows
	public	int windows = 4;

	void function() {

		// get va rating and corresponding time window
		ArrayList<LoadWindows> al = getwindows(windows);

		// fill time array
		int loadTimeArr[][] = new int[phase][time_total];

		if (phaseLoad == 1) {

			// for each load window
			for (int i = 0; i < windows; i++) {
				LoadWindows lw = al.get(i);

				// decide/get the phase in which load is to be added
				int currPhase = getCurrPhase(lw.phase_int, loadTimeArr);

				// assign a new phase if overloading is there
				while (isOverLoading(
						loadTimeArr[currPhase][lw.time_start_mins], lw.rating)) {
					// change phase
					// auto: try next phase
					currPhase = (currPhase + 1) % phase;

					// update phase
					lw.phase_int = currPhase;

				}

				// for the time span, add the load
				for (int j = lw.time_start_mins; j < lw.time_end_mins; j++) {

					loadTimeArr[currPhase][j] += lw.rating;

				}

			}
		} else if (phaseLoad == 3) {
			// TODO check for overloading in 3 phase load

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

	private int getCurrPhase(int phase_int, int[][] loadTimeArr) {
		// if phase is -1 then auto
		int currPhase = phase_int;
		if (currPhase < 0) {
			// get the array with lowest peak element
			currPhase = getAutoPhase(loadTimeArr);
		}
		return currPhase;
	}

	private boolean isOverLoading(int currentLoad, int additionalLoad) {
		int finalLoad = currentLoad + additionalLoad;

		if (finalLoad > rating_trans_per_phase) {
			return true;
		}
		return false;
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
