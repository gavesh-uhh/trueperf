package live.gavesh.trueperf.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.formdev.flatlaf.FlatClientProperties;
import live.gavesh.trueperf.event.impl.LogEvent;
import live.gavesh.trueperf.event.listeners.LogListener;
import live.gavesh.trueperf.feature.FeatureManager;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;

import java.awt.Component;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppFrame extends JFrame implements LogListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane textBoxLoggingPane;
	private FeatureManager featureManager;
	private HTMLEditorKit editorKit;
	private HTMLDocument htmlDocument;
	private JButton btnRestorePoint;

	public AppFrame() {
		featureManager = new FeatureManager();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnStartOptimizations = new JButton("Start Optimizations");
		btnStartOptimizations.setBounds(356, 399, 360, 29);
		btnStartOptimizations.addActionListener(e -> startOptimizations());
		contentPane.add(btnStartOptimizations);

		editorKit = new HTMLEditorKit();
		htmlDocument = new HTMLDocument();
		textBoxLoggingPane = new JEditorPane();
		textBoxLoggingPane.setContentType("text/html");
		textBoxLoggingPane.setEditorKit(editorKit);
		textBoxLoggingPane.setDocument(htmlDocument);
		textBoxLoggingPane.setBounds(356, 53, 360, 339);
		contentPane.add(textBoxLoggingPane);

		JLabel lblFeatureSelection = new JLabel("Select Fixes:");
		lblFeatureSelection.setBounds(14, 25, 180, 20);
		contentPane.add(lblFeatureSelection);

		JPanel featurePanel = new JPanel();
		featurePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
		featurePanel.setBounds(14, 53, 328, 353);

		for (IFeature feature : featureManager.getFeatures()) {
			JCheckBox checkBox = new JCheckBox(feature.getName());
			checkBox.setName("module_" + feature.getName());
			checkBox.putClientProperty(FlatClientProperties.STYLE,
					"icon.checkmarkColor:" + feature.getRiskLevel().getColorHex());
			checkBox.setToolTipText(
					"<Risk Level: " + feature.getRiskLevel().getRiskLevel() + "> " + feature.getDescription());
			featurePanel.add(checkBox);
		}

		JScrollPane scrollPane = new JScrollPane(featurePanel);
		scrollPane.setName("comp_window");
		scrollPane.setBounds(14, 53, 335, 375);
		contentPane.add(scrollPane);
		
		JButton btnClearLogs = new JButton("Clear");
		btnClearLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBoxLoggingPane.setText("");
			}
		});
		btnClearLogs.setBounds(631, 10, 85, 36);
		contentPane.add(btnClearLogs);
		
		btnRestorePoint = new JButton("Create Restore Point");
		btnRestorePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rpName = "trueperf_RP_" + LocalDateTime.now();
					String formatted = String.format("Checkpoint-Computer -Description '%s' -RestorePointType 'MODIFY_SETTINGS'", rpName);
					new PSExecutor().executeCommand(formatted);
					JOptionPane.showMessageDialog(btnRestorePoint, "Restore Point Created as " + rpName);
					Logger.info("Restore Point (" + rpName + ") created!");
				} catch (Exception e1) {
					Logger.error(e1.getMessage());
					JOptionPane.showMessageDialog(btnRestorePoint, "Restore Point Creation Failed! Please Create One Manually Before Procceeding", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRestorePoint.setBounds(356, 10, 265, 36);
		contentPane.add(btnRestorePoint);

		Logger.setLogListener(this);
	}

	private void startOptimizations() {
		if (JOptionPane.showConfirmDialog(this,
				"Warning: This action cannot be undone. Are you sure you want to proceed?") != 0) {
			Logger.error("Process was canceled by the user.");
			return;
		}
		JScrollPane scrollPane = (JScrollPane) getComponentByName("comp_window");
		JPanel featurePanel = (JPanel) scrollPane.getViewport().getView();
		for (Component comp : featurePanel.getComponents()) {
			if (!(comp instanceof JCheckBox))
				continue;
			JCheckBox checkBox = (JCheckBox) comp;
			if (!checkBox.isSelected())
				continue;
			String featureName = checkBox.getName().replaceAll("module_", "");
			featureManager.getFeatures().stream().filter(feature -> feature.getName().equals(featureName))
					.forEach(this::executeFeature);
		}
	}

	private void executeFeature(IFeature feature) {
		new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				Logger.info(feature.getName() + " : Started Execution");
				feature.onStart();
				return null;
			}

			@Override
			protected void process(List<String> chunks) {
				for (String logMessage : chunks) {
					appendToEditorPane(logMessage);
				}
			}

			@Override
			protected void done() {
				feature.onEnd();
				Logger.info("Finished Running : " + feature.getName());
			}
		}.execute();
	}

	private void appendToEditorPane(String logMessage) {
		SwingUtilities.invokeLater(() -> {
			try {
				editorKit.insertHTML(htmlDocument, htmlDocument.getLength(), logMessage, 0, 0, null);
				textBoxLoggingPane.setCaretPosition(textBoxLoggingPane.getDocument().getLength());
			} catch (BadLocationException | IOException e) {
				e.printStackTrace();
			}
		});
	}

	private Component getComponentByName(String name) {
		for (Component comp : contentPane.getComponents()) {
			if (comp.getName() == null)
				continue;
			if (comp.getName().equals(name))
				return comp;
		}
		return null;
	}

	@Override
	public void onLogMessage(LogEvent event) {
		String logMessage = event.getLogMessage();
		String level = event.getLogLevel();
		String color = "white";

		switch (level) {
		case "INFO":
			color = "lime";
			break;
		case "WARNING":
			color = "yellow";
			break;
		case "ERROR":
			color = "red";
			break;
		}

		String timeStr = String.format("[%s] ", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		String formattedMessage = "<h1 style='color: " + color + ";'>" + timeStr + logMessage + "</h1>";
		appendToEditorPane(formattedMessage);
	}
}
