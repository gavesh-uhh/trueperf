package live.gavesh.trueperf.feature.impl;

import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;

public class ModuleServiceDisabler implements IFeature{

	String sevices[] = new String [] {
		"dmwappushsvc",
		"dmwappushservice",
		"PeerDistSvc"
	};
	
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
		return FeatureRisk.MEDIUM;
	}

	@Override
	public void onStart() throws Exception {
		Logger.warn("Service Disable Started...");
	}

	@Override
	public void onEnd() {
		
	}

}
