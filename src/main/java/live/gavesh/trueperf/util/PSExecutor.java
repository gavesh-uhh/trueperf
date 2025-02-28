package live.gavesh.trueperf.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class PSExecutor {

    public String executeCommand(String command) throws Exception {
        return executeProcess(new ProcessBuilder(("pwsh.exe -NoProfile -NoLogo -Command " + command).split(" ")));
    }

    public String executeCommand(List<String> commands) throws Exception {
        return executeProcess(new ProcessBuilder(commands));
    }

    public boolean isPowerShellAvailable() {
        try {
            executeCommand("Write-Output 'Ahoy Powershell?'");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String executeProcess(ProcessBuilder processBuilder) throws Exception {
        Process process = processBuilder.start();
        BufferedReader successReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = successReader.readLine()) != null) output.append(line).append("\n");
        while ((line = errorReader.readLine()) != null) output.append("ERROR: ").append(line).append("\n");
        if (process.waitFor() == 0) return output.toString().trim();
        else {
        	Logger.error("Error (Exit Code " + process.exitValue() + "):\n" + output);
        	return "Error: (Exit Code " + process.exitValue() + "):\n" + output;
        }
    }
}