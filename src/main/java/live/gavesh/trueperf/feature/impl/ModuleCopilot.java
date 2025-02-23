package live.gavesh.trueperf.feature.impl;


import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;


public class ModuleCopilot implements IFeature {

	@Override
	public String getName() {
		return "Uninstall Copilot";
	}

	@Override
	public String getDescription() {
		return "Completly gets rid of AI slopware";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.LOW;
	}

	public void onStart() throws Exception {
		PSExecutor ps = new PSExecutor();
		try {
			ps.executeCommand(
					"reg add 'HKEY_CURRENT_USER\\SOFTWARE\\Policies\\Microsoft\\Windows\\Windows\\WindowsCopilot' /v 'TurnOffWindowsCopilot' /t REG_DWORD /d 1 /f");
			ps.executeCommand(
					"reg add 'HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced' /v 'ShowCopilotButton' /t REG_DWORD /d 0 /f");
			ps.executeCommand(
					"reg add 'HKEY_LOCAL_MACHINE\\SOFTWARE\\Policies\\Microsoft\\Windows\\Windows\\WindowsCopilot' /v 'TurnOffWindowsCopilot' /t REG_DWORD /d 1 /f");
			ps.executeCommand(
					"reg add 'HKEY_LOCAL_MACHINE\\SOFTWARE\\Policies\\Microsoft\\Edge' /v 'HubsSidebarEnabled' /t REG_DWORD /d 0 /f");
			ps.executeCommand(
					"reg add 'HKEY_CURRENT_USER\\SOFTWARE\\Policies\\Microsoft\\Windows\\Explorer' /v 'DisableSearchBoxSuggestions' /t REG_DWORD /d 1 /f");
			ps.executeCommand(
					"reg add 'HKEY_LOCAL_MACHINE\\SOFTWARE\\Policies\\Microsoft\\Windows\\Explorer' /v 'DisableSearchBoxSuggestions' /t REG_DWORD /d 1 /f");
			ps.executeCommand(
					"reg add 'HKEY_CURRENT_USER\\Software\\Policies\\Microsoft\\Windows\\WindowsAI' /v 'DisableAIDataAnalysis' /t REG_DWORD /d 1 /f");
			ps.executeCommand(
					"reg add 'HKEY_LOCAL_MACHINE\\Software\\Policies\\Microsoft\\Windows\\WindowsAI' /v 'DisableAIDataAnalysis' /t REG_DWORD /d 1 /f");
			ps.executeCommand(
					"Get-AppxPackage -AllUsers | Where-Object {$_.Name -Like '*Microsoft.Copilot*'} | Remove-AppxPackage -AllUsers -ErrorAction Continue");
			Logger.warn("Copilot Uninstalled Succesfully");
		} catch (Exception e) {
			Logger.error("Copilot Uninstall Failed!");
		}
	}

	@Override
	public void onEnd() {

	}

}
