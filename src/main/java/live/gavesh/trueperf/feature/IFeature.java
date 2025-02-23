package live.gavesh.trueperf.feature;

public interface IFeature {
	
	String getName();
	String getDescription();
	FeatureRisk getRiskLevel();
	void onStart() throws Exception ;
	void onEnd();
	
}
