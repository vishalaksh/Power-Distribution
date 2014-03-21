package com.vishalaksh.powerdistribution.logic;

public class LoadWindows implements Constants {

	int rating;

	String time_start_string;
	String time_end_string;
	String phase_string;

	int time_start_mins;
	int time_end_mins;
	int phase_int;

	/**
	 * @param rating
	 * @param time_start_string
	 *            format can be hhmm or hh:mm
	 * @param time_end_string
	 *            format can be hhmm or hh:mm
	 */
	public LoadWindows(int rating, String time_start_string,
			String time_end_string, String phase_String) {
		super();
		this.rating = rating;
		this.time_start_string = time_start_string;
		this.time_end_string = time_end_string;
		this.phase_string = phase_String;

		this.time_start_mins = Integer.parseInt(time_start_string.substring(0,
				2))
				+ Integer.parseInt(time_start_string.substring(
						time_start_string.length() - 2,
						time_start_string.length()));

		this.time_end_mins = Integer.parseInt(time_end_string.substring(0, 2))
				+ Integer
						.parseInt(time_end_string.substring(
								time_end_string.length() - 2,
								time_end_string.length()));

		if (this.phase_string != null) {
			if (this.phase_string.equalsIgnoreCase(phase_A)) {
				phase_int = 0;
			} else if (this.phase_string.equalsIgnoreCase(phase_B)) {
				phase_int = 1;
			} else if (this.phase_string.equalsIgnoreCase(phase_C)) {
				phase_int = 2;
			} else {
				phase_int = -1;
			}
		} else {
			phase_int = -1;
		}
	}
}
