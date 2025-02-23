package live.gavesh.trueperf.feature.impl;

import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;
import live.gavesh.trueperf.util.WindowsUtils;

public class ModuleOneDrive implements IFeature {

	@Override
	public String getName() {
		return "Uninstall One-Drive";
	}

	@Override
	public String getDescription() {
		return "Completly gets rid of One-Drive";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.LOW;
	}

	public void onStart() throws Exception {
	    PSExecutor ps = new PSExecutor();
	    if (WindowsUtils.serviceExists("OneDrive")) {
	    	String stopOneDriveCommand = "Stop-Process -Name 'OneDrive' -Force -ErrorAction SilentlyContinue";
		    ps.executeCommand(stopOneDriveCommand);
	    } else {
	    	Logger.error("One-Drive does not exist");
	    } 
	}


	@Override
	public void onEnd() {
		WindowsUtils.restartExplorer();
	}

}
