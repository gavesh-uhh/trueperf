package live.gavesh.trueperf.feature.impl;

import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;
import live.gavesh.trueperf.util.WindowsUtils;

public class ModuleGamingOptimizations implements IFeature {

	@Override
	public String getName() {
		return "Gaming Optimizations";
	}

	@Override
	public String getDescription() {
		return "Enables Ultimate Performance Mode, ";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.MEDIUM;
	}

	@Override
	public void onStart() throws Exception {
		PSExecutor ps = new PSExecutor();
		ps.executeCommand("powercfg -duplicatescheme e9a42b02-d5df-448d-aa00-03f14749eb61");
		ps.executeCommand("powercfg -setactive e9a42b02-d5df-448d-aa00-03f14749eb61");
		Logger.info("Enabled Ultimate Performance Mode");
		ps.executeCommand(
				"reg add 'HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\BackgroundAccessApplications' /v GlobalUserDisabled /t REG_DWORD /d 1 /f\r\n");
		Logger.info("Disabled Background Apps");

		String[] commands = {
				"Set-ItemProperty -Path 'HKCU:\\Control Panel\\Desktop' -Name 'DragFullWindows' -Type String -Value 0",
				"Set-ItemProperty -Path 'HKCU:\\Control Panel\\Desktop' -Name 'MenuShowDelay' -Type String -Value 0",
				"Set-ItemProperty -Path 'HKCU:\\Control Panel\\Desktop' -Name 'UserPreferencesMask' -Type Binary -Value ([byte[]](144,18,3,128,16,0,0,0))",
				"Set-ItemProperty -Path 'HKCU:\\Control Panel\\Desktop\\WindowMetrics' -Name 'MinAnimate' -Type String -Value 0",
				"Set-ItemProperty -Path 'HKCU:\\Control Panel\\Keyboard' -Name 'KeyboardDelay' -Type DWord -Value 0",
				"Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced' -Name 'ListviewAlphaSelect' -Type DWord -Value 0",
				"Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced' -Name 'ListviewShadow' -Type DWord -Value 0",
				"Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced' -Name 'TaskbarAnimations' -Type DWord -Value 0",
				"Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects' -Name 'VisualFXSetting' -Type DWord -Value 3",
				"Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\DWM' -Name 'EnableAeroPeek' -Type DWord -Value 0" };

		for (String command : commands) {
			try {
				ps.executeCommand(command);
			} catch (Exception e) {
				Logger.error("Failed: " + command + " | Error: " + e.getMessage().trim());
			}
		}
		
		Logger.info("Adjusted visuals for better performance");
	}

	@Override
	public void onEnd() {
		WindowsUtils.restartExplorer();
	}

}
