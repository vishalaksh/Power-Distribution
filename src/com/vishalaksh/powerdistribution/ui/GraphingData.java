package com.vishalaksh.powerdistribution.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.vishalaksh.powerdistribution.logic.Constants;
import com.vishalaksh.powerdistribution.logic.InputData;
import com.vishalaksh.powerdistribution.logic.LoadCalculator;
import com.vishalaksh.powerdistribution.logic.LoadWindows;

public class GraphingData extends JPanel implements Constants {
	// thanks to
	// http://www.java-forums.org/new-java/7995-how-plot-graph-java-given-samples.html
	// TODO fetch data from loadcalculator

	int data[][];
	int phasesLoad;

	LoadCalculator lc;
	final int PAD = 20;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();

		try {
			lc = new LoadCalculator(getData());
			lc.function();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = lc.getLoadTimeArr();
		phasesLoad = lc.phaseLoad;
		// Draw ordinate.
		g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
		// Draw abcissa.
		g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));

		// Draw labels.
		Font font = g2.getFont();
		FontRenderContext frc = g2.getFontRenderContext();
		LineMetrics lm = font.getLineMetrics("0", frc);
		float sh = lm.getAscent() + lm.getDescent();

		// Ordinate label.
		String s = "data";
		float sy = PAD + ((h - 2 * PAD) - s.length() * sh) / 2 + lm.getAscent();
		for (int i = 0; i < s.length(); i++) {
			String letter = String.valueOf(s.charAt(i));
			float sw = (float) font.getStringBounds(letter, frc).getWidth();
			float sx = (PAD - sw) / 2;
			g2.drawString(letter, sx, sy);
			sy += sh;
		}

		// Abcissa label.
		s = "x axis";
		sy = h - PAD + (PAD - sh) / 2 + lm.getAscent();
		float sw = (float) font.getStringBounds(s, frc).getWidth();
		float sx = (w - sw) / 2;
		g2.drawString(s, sx, sy);

		// set uniform scale for all the phases
		double xInc = (double) (w - 2 * PAD) / (time_total);
		double scale = (double) (h - 2 * PAD) / getMax(data);
		
		//System.out.println("getMax(data):"+getMax(data));

		// Draw lines.
		for (int currPhase = 0; currPhase < lc.phaseSupply; currPhase++) {

			// set line color acc. to the phase
			g2.setPaint(getPaint(currPhase));
		
		//	System.out.println(currPhase+":"+Arrays.toString(data[currPhase]));
			for (int i = 0; i < (data[currPhase].length - 1); i++) {
				
				double x1 = PAD + i * xInc;
				double y1 = h - PAD - scale * data[currPhase][i];
				double x2 = PAD + (i + 1) * xInc;
				double y2 = h - PAD - scale * data[currPhase][i + 1];
			//	System.out.println(String.format("i=%d from(%.2f,%.2f) to(%.2f,%.2f)",i,x1,y1,x2,y2));
				g2.draw(new Line2D.Double(x1, y1, x2, y2));
			}
			
		}

	}

	private Paint getPaint(int a) {
		Paint mPaint;
		switch (a) {
		case 0:
			mPaint = Color.green.darker();
			break;
		case 1:
			mPaint = Color.blue.darker();
			break;
		case 2:
			mPaint = Color.red.darker();
			break;
		default:
			mPaint = Color.black.darker();
		}

		return mPaint;
	}

	private int getMax(int[][] data) {
		int max = -Integer.MAX_VALUE;

		for (int i = 0; i < data.length; i++) {
			
			for (int j = 0; j < data[i].length; j++) {

				if (data[i][j] > max) {
					max = data[i][j];
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws FileNotFoundException {

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			f.add(new GraphingData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.setVisible(true);
	}

	private static InputData getData() throws IOException {
		File file = new File(fileInput);

		if (!file.exists()) {
			file.createNewFile();
		}
		ArrayList<LoadWindows> arrlist = new ArrayList<>();

		// TODO if exception is thrown, then show alert that file is empty; you
		// can also fill the file with fields here; you can automatically open
		// the file
		String phaseLoadString = MyFileReader.readLineFromFile(file,
				LineNumberphaseLoad);
		String phaseSupplyString = MyFileReader.readLineFromFile(file,
				LineNumberphaseSupply);
		String windowsNumberString = MyFileReader.readLineFromFile(file,
				LineNumberwindowsNumber);

		Scanner scn = MyFileReader.getScannerAtLine(new Scanner(file),
				LineNumberWindows);

		int windowsNumberInt = Integer.parseInt(windowsNumberString);
		for (int i = 0; i < windowsNumberInt; i++) {
			String s = scn.nextLine();
			Scanner scn1 = new Scanner(s);

			String rating = scn1.next();
			String start = scn1.next();
			String end = scn1.next();

			// phase to which load is connected should be written in last
			String phase = null;
			if (scn1.hasNext()) {
				phase = scn1.next();
			}

			arrlist.add(new LoadWindows(Integer.parseInt(rating), start, end,
					phase));
		}

		return new InputData(Integer.parseInt(phaseLoadString),
				Integer.parseInt(phaseSupplyString), windowsNumberInt, arrlist);

	}
}