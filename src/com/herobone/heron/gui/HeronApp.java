package com.herobone.heron.gui;

import com.herobone.heron.AdvancedStringBuilder;
import com.herobone.heron.GuiAlgorithm;
import com.herobone.heron.util.URLUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Formatter;
import java.util.ResourceBundle;

public class HeronApp extends Thread {
	public static ResourceBundle LANG = ResourceBundle.getBundle("lang.messages");
	
	public static final StringBuilder strBui = new StringBuilder();

	public JFrame frame;
	private JTextField inputFld;

	/**
	 * Create the application.
	 */
	public HeronApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(HeronApp.class.getResource("/img/root3.png")));
		frame.setTitle(LANG.getString("HeronApp.title.text")); //$NON-NLS-1$
		frame.setBounds(100, 100, 691, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputFld = new JTextField();
		inputFld.setToolTipText(LANG.getString("HeronApp.input.input.tooltip.text")); //$NON-NLS-1$
		inputFld.setColumns(10);
		
		JLabel inputLbl = new JLabel(LANG.getString("HeronApp.label.input.text")); //$NON-NLS-1$
		
		JCheckBox stepsCbx = new JCheckBox(LANG.getString("HeronApp.checkbox.allsteps.text")); //$NON-NLS-1$
		stepsCbx.setSelected(true);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JTextPane outputPne = new JTextPane();
		outputPne.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		outputPne.setText(LANG.getString("HeronApp.textPane.output.text")); //$NON-NLS-1$
		outputPne.setEditable(false);
		scrollPane.setViewportView(outputPne);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(5, 3, 50, 1));
		
		JCheckBox fileCbx = new JCheckBox(LANG.getString("HeronApp.checkbox.file.text")); //$NON-NLS-1$
		
		JButton calculateBtn = new JButton(LANG.getString("HeronApp.button.calculate.text")); //$NON-NLS-1$
		calculateBtn.addActionListener(e -> {
			double in;

			strBui.delete(0, strBui.length());

			System.out.println("CLICKED");
			try {
				in = Double.parseDouble(inputFld.getText());
				System.out.println(in);

				if (stepsCbx.isSelected()) {
					System.out.println("ALL STEPS");
				} else {
					System.out.println("ONLY END");
				}
				printInformation(in, (int) spinner.getValue(), stepsCbx.isSelected());

				outputPne.setText(strBui.toString());

				if (fileCbx.isSelected()) {
					Formatter f = new Formatter("root.txt");
					f.format("%s", strBui.toString());
					f.close();

					try {
						FileCreatedDialog dialog = new FileCreatedDialog();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}

			} catch (NumberFormatException exeption) {
				System.err.println("Input is not a valid number!");
			} catch (FileNotFoundException exeption) {
				System.err.println("Error with creating or writing the file");
				exeption.printStackTrace(System.err);
			}
		});
		
		JLabel creditsLbl = new JLabel(LANG.getString("HeronApp.label.credits.text")); //$NON-NLS-1$
		creditsLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					URLUtil.openWebpage(new URL("https://herobone.de").toURI());
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel repetitionsLbl = new JLabel(LANG.getString("HeronApp.label.repetitions.text"));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(inputLbl)
									.addGap(18)
									.addComponent(inputFld, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
								.addComponent(stepsCbx))
							.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(repetitionsLbl)
								.addComponent(fileCbx))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(calculateBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
						.addComponent(creditsLbl, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(inputLbl)
						.addComponent(inputFld, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(calculateBtn)
						.addComponent(fileCbx))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stepsCbx)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(repetitionsLbl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(creditsLbl)
					.addContainerGap())
		);
		
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private void printInformation(double dbl, int rep, boolean bool) {
		AdvancedStringBuilder.append(strBui, LANG.getString("HeronApp.misc.parsedDbl.text") + " " + dbl + "\n");
		
		if (bool) {
			AdvancedStringBuilder.append(strBui, "|------------------------------------------|");
			
			AdvancedStringBuilder.append(strBui, "|AAA L   GGG OOO RRR Y Y TTT H H EEE M   M |");
			AdvancedStringBuilder.append(strBui, "|A A L   G   O O R R  Y   T  HHH E   MM MM |");
			AdvancedStringBuilder.append(strBui, "|AAA L   G G O O RR   Y   T  H H EE  M M M |");
			AdvancedStringBuilder.append(strBui, "|A A LLL GGG OOO R R  Y   T  H H EEE M   M |");
			
			AdvancedStringBuilder.append(strBui, "|------------------------------------------| \n");
			
			AdvancedStringBuilder.append(strBui, LANG.getString("HeronApp.misc.calculatedIn.text") + " " + rep + " " + LANG.getString("HeronApp.misc.rounds.text") + ": " + GuiAlgorithm.repetitions(dbl, rep));
		} else {
			AdvancedStringBuilder.append(strBui, LANG.getString("HeronApp.misc.calculatedIn.text") + " " + rep + " " + LANG.getString("HeronApp.misc.rounds.text") + ": " + GuiAlgorithm.repetitionsNoOut(dbl, rep));
		}
		AdvancedStringBuilder.append(strBui, LANG.getString("HeronApp.misc.squareRt.text") + ": " + Math.sqrt(dbl) + "\n");
	}
}
