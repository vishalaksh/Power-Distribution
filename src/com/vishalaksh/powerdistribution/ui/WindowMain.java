package com.vishalaksh.powerdistribution.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.vishalaksh.powerdistribution.logic.Constants;
import com.vishalaksh.powerdistribution.logic.LoadCalculator;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class WindowMain implements Constants {

	LoadCalculator mLoadCalculator;
	private JFrame frame;
	CardLayout card;
	Container mContainer;
	JPanel panelOne;
	JPanel panelTwo;
	GraphingData panelThree;
	private JTextField txtPhase;
	private JTextField textField;
	private JTextField txtLoadPhase;
	private JTextField txtNoOfWindows;
	private JButton btnNext;
	private Box verticalBox;
	private Box verticalBox_1;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowMain window = new WindowMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	//	 mLoadCalculator=new LoadCalculator();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelOne = new JPanel();
		panelTwo = new JPanel();
		//panelThree = new GraphingData();

		card = new CardLayout(0, 0);
		mContainer = frame.getContentPane();
		mContainer.setLayout(card);
		mContainer.add(panelOne, keyPanelOne);
		mContainer.add(panelTwo, keyPanelTwo);
		panelOne.setLayout(new GridLayout(0, 2, 0, 0));
		//mContainer.add(panelThree, keyPanelThree);

		panelOne.add(new JLabel("first panel"));
		JButton btn1 = new JButton("next");
		panelOne.add(btn1);
		
		txtPhase = new JTextField();
		txtPhase.setText("phase");
		panelOne.add(txtPhase);
		txtPhase.setColumns(10);
		
		txtLoadPhase = new JTextField();
		txtLoadPhase.setText("load phase");
		panelOne.add(txtLoadPhase);
		txtLoadPhase.setColumns(10);
		
		txtNoOfWindows = new JTextField();
		txtNoOfWindows.setText("no. of windows");
		panelOne.add(txtNoOfWindows);
		txtNoOfWindows.setColumns(10);
		
		textField = new JTextField();
		panelOne.add(textField);
		textField.setColumns(10);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				mLoadCalculator.phaseSupply=Integer.parseInt(txtPhase.getText());
				mLoadCalculator.phaseLoad=Integer.parseInt(txtLoadPhase.getText());
				mLoadCalculator.windowsNumber=Integer.parseInt(txtNoOfWindows.getText());

				for(int i=0; i<mLoadCalculator.windowsNumber; i++){
					
				}
				
				card.show(mContainer, keyPanelTwo); // shows panelTwo
			}
		});
		panelTwo.setLayout(new GridLayout(0, 1));
		
		label_1 = new JLabel("");
		panelTwo.add(label_1);
		
		verticalBox = Box.createVerticalBox();
		panelTwo.add(verticalBox);
		
				JLabel label = new JLabel("second panel");
				verticalBox.add(label);
				JButton btn2 = new JButton("Back");
				verticalBox.add(btn2);
				
				btnNext = new JButton("next");
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						//init graphing data
						panelThree = new GraphingData();
						mContainer.add(panelThree, keyPanelThree);
						card.show(mContainer, keyPanelThree); // shows panelTwo
					}
				});
				verticalBox.add(btnNext);
				btn2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						card.show(mContainer, keyPanelOne); // shows panelOne
					}
				});
		
		
		verticalBox_1 = Box.createVerticalBox();
		panelTwo.add(verticalBox_1);

	}

}
