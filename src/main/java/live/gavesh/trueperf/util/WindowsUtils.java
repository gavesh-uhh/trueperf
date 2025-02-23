package live.gavesh.trueperf.util;

public class WindowsUtils {

	public static void restartExplorer() {
		PSExecutor ps = new PSExecutor();
		try {
			ps.executeCommand("taskkill /F /IM explorer.exe");
			ps.executeCommand("start explorer.exe");
			Logger.info("Restarted Explorer.exe");
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
	
	public static boolean isAdministrator() {
		PSExecutor ps = new PSExecutor();
		try {
			String response = ps.executeCommand("[bool](([System.Security.Principal.WindowsIdentity]::GetCurrent()).groups -match 'S-1-5-32-544') | ConvertTo-JSON");
			if (response.equals("true")) return true;
			else return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void shutdownComputer() {
		PSExecutor ps = new PSExecutor();
		try {
			ps.executeCommand("shutdown /s /t 0");
			Logger.info("Shutting down computer...");
		} catch (Exception e) {
			Logger.error("Failed to shut down computer: " + e.getMessage());
		}
	}
	
	public static boolean serviceExists(String serviceName) {
		String serviceString = String.format("(Get-Service -Name %s -ErrorAction SilentlyContinue).length -eq 1 | ConvertTo-JSON", serviceName);
		PSExecutor ps = new PSExecutor();
		try {
			String response = ps.executeCommand(serviceString);
			if (response.trim().equalsIgnoreCase("true")) return true;
			else return false;
		} catch (Exception e) {
			Logger.error("Failed to shut down computer: " + e.getMessage());
			return false;
		}
	}

}
