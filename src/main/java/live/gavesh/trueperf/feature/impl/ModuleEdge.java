package live.gavesh.trueperf.feature.impl;

import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;

public class ModuleEdge implements IFeature{

	@Override
	public String getName() {
		return "Uninstall Edge";
	}

	@Override
	public String getDescription() {
		return "Completly gets rid of Microsoft Edge";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.LOW;
	}

	@Override
	public void onStart() throws Exception  {
		
	}

	@Override
	public void onEnd() {
		
	}

}
