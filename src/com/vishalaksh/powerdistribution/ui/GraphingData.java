package com.vishalaksh.powerdistribution.ui;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

import com.vishalaksh.powerdistribution.logic.Constants;

public class GraphingData extends JPanel implements Constants {
	// thanks to
	// http://www.java-forums.org/new-java/7995-how-plot-graph-java-given-samples.html
	// TODO fetch data from loadcalculator
	int data[][] = new int[3][time_total];
	int phases = 3;

	/*
	 * int[] data = { 21, 14, 18, 03, 86, 88, 74, 87, 54, 77, 61, 55, 48, 60,
	 * 49, 36, 38, 27, 20, 18 };
	 */
	final int PAD = 20;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();

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

		// Draw lines.
		for (int currPhase = 0; currPhase < phases; currPhase++) {

			// set line color acc. to the phase
			g2.setPaint(getPaint(currPhase));
			for (int i = 0; i < data[currPhase].length - 1; i++) {
				double x1 = PAD + i * xInc;
				double y1 = h - PAD - scale * data[currPhase][i];
				double x2 = PAD + (i + 1) * xInc;
				double y2 = h - PAD - scale * data[currPhase][i + 1];
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
			for (int j = 0; j < data[i].length; i++) {
				if (data[i][j] > max)
					max = data[i][j];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new GraphingData());
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}