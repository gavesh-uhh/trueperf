package live.gavesh.trueperf.feature;

import javax.swing.JOptionPane;

public interface IFeature {
	
	String getName();
	String getDescription();
	FeatureRisk getRiskLevel();
	void onStart() throws Exception ;
	void onEnd();
	
	public default void onReEnable() {}
	
	public default void showDialog(String message) {
		JOptionPane.showMessageDialog(null, message, this.getName(), JOptionPane.WARNING_MESSAGE);
	}
	
}
