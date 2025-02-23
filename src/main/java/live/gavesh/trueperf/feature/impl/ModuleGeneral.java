package live.gavesh.trueperf.feature.impl;

import java.util.LinkedList;
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
		return "Enables Dark Mode, Disable Tips";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.LOW;
	}

	@Override
	public void onStart() throws Exception{
		Logger.warn("General Optimizations : Started Running...");
		PSExecutor ps = new PSExecutor();
		ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize' -Name 'SystemUsesLightTheme' -Value 0");
		ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize' -Name 'AppsUseLightTheme' -Value 0");
		Logger.info("Turn on Darked Mode!");
		
		ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Taskband' -Name 'Favorites' -Type Binary -Value (255)");
		ps.executeCommand("Remove-ItemProperty -Path 'HKCU:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Taskband' -Name 'FavoritesResolve' -ErrorAction SilentlyContinue");
		Logger.info("Unpinned All Tiles!");
		
		ps.executeCommand("powercfg -setactive SCHEME_MIN");
		Logger.info("Enabled High-Performance");	
	}

	@Override
	public void onEnd() {
		WindowsUtils.restartExplorer();
	}

}
