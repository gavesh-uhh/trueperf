package live.gavesh.trueperf.feature.impl;

import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;
import live.gavesh.trueperf.util.WindowsUtils;

public class ModuleServiceDisabler implements IFeature {

	String services[] = new String[] { "DiagTrack", "dmwappushservice", "PeerDistSvc", "XblAuthManager", "XblGameSave",
			"XboxNetApiSvc", "RemoteRegistry", "Retail Demo", "HomeGroupListener", "HomeGroupProvider", "sysmain",
			"WMPNetworkSvc", "RemoteAccess", "SSDPSRV", "TabletInputService", "WwanSvc" };

	@Override
	public String getName() {
		return "Disable Services";
	}

	@Override
	public String getDescription() {
		return "Disables useless services";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.HIGH;
	}

	@Override
	public void onStart() throws Exception {
		PSExecutor ps = new PSExecutor();
		for (String string : services) {
			try {
				if (!WindowsUtils.serviceExists(string))
					return;
				ps.executeCommand(String.format("Stop-Service -Name '%s' -Force", string));
				ps.executeCommand(String.format("Set-Service -Name '%s' -StartupType Disabled", string));
				Logger.info(string + " succesfully disabled");
			} catch (Exception e) {
				Logger.error(e.getMessage().trim());
			}
		}
	}

	@Override
	public void onEnd() {

	}

}
