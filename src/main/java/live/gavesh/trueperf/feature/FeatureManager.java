package live.gavesh.trueperf.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import live.gavesh.trueperf.feature.impl.*;

public class FeatureManager {

	private ArrayList<IFeature> features = new ArrayList<IFeature>();
	
	public FeatureManager() {
		this.features.add(new ModuleGeneral());
		this.features.add(new ModuleDebloater());
		this.features.add(new ModuleServiceDisabler());
		this.features.add(new ModuleFixPrivacy());
		this.features.add(new ModuleEdge());
		this.features.add(new ModuleOneDrive());
		this.features.add(new ModuleCopilot());
	}
	
	public List<IFeature> getFeaturesByRisk(FeatureRisk risk) {
        return features.stream()
                       .filter(feature -> feature.getRiskLevel() == risk)
                       .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
	
	public ArrayList<IFeature> getFeatures() {
		return features;
	}
	
}
