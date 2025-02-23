package live.gavesh.trueperf;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import live.gavesh.trueperf.ui.AppFrame;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;
import live.gavesh.trueperf.util.WindowsUtils;

public class App {

	public static void main(String[] args) throws Exception {

		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		PSExecutor ps = new PSExecutor();
		if (!ps.isPowerShellAvailable()) {
			JOptionPane.showMessageDialog(null, "Please Install PowerShell", "Powershell Required",
					JOptionPane.ERROR_MESSAGE);
			Logger.error("Please install PowerShell first!");
			return;
		}
		if (!WindowsUtils.isAdministrator()) {
			JOptionPane.showMessageDialog(null, "Restart the application again 'As Administrator'",
					"Adminstrator Privilages Required", JOptionPane.ERROR_MESSAGE);
			Logger.error("Restart the application again Administrator");
			return;
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();
					frame.setResizable(false);
					frame.setTitle("truePerf 1.0");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
