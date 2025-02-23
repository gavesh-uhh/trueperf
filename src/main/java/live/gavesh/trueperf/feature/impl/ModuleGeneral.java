package live.gavesh.trueperf.feature.impl;


import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;
import live.gavesh.trueperf.util.WindowsUtils;

public class ModuleGeneral implements IFeature {

	@Override
	public String getName() {
		return "General Modifications";
	}

	@Override
	public String getDescription() {
		return "Enables Dark Mode, Disable Tips, Enable High Performance";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.LOW;
	}

	@Override
	public void onStart() throws Exception{
		PSExecutor ps = new PSExecutor();
		ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize' -Name 'SystemUsesLightTheme' -Value 0");
		ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize' -Name 'AppsUseLightTheme' -Value 0");
		Logger.info("Turn on Darked Mode!");
				
		try {
			ps.executeCommand("powercfg -setactive SCHEME_MIN");
			Logger.info("Enabled High-Performance");	
		} catch (Exception e) {
			Logger.error("Cannot enable High-Performance mode");
		}
	}

	@Override
	public void onEnd() {
		WindowsUtils.restartExplorer();
	}

}
