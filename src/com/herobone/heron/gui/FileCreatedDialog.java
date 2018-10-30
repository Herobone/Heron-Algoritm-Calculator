package com.herobone.heron.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileCreatedDialog extends JDialog {
	private static final ResourceBundle LANG = ResourceBundle.getBundle("lang.messages"); //$NON-NLS-1$

	/**
	 * 
	 */
	private static final long serialVersionUID = 277149678184473353L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public FileCreatedDialog() {
		setTitle(LANG.getString("HeronApp.FileCreatedDialog.title.text")); //$NON-NLS-1$
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			TextArea textArea = new TextArea();
			textArea.setEditable(false);
			textArea.setText(LANG.getString("HeronApp.FileCreatedDialog.label.succes.text")); //$NON-NLS-1$
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(LANG.getString("HeronApp.FileCreatedDialog.okButton.text")); //$NON-NLS-1$
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand(LANG.getString("HeronApp.FileCreatedDialog.okButton.actionCommand")); //$NON-NLS-1$
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
