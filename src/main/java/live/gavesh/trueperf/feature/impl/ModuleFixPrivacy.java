package live.gavesh.trueperf.feature.impl;

import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;

public class ModuleFixPrivacy implements IFeature {

    @Override
    public String getName() {
        return "Fix Privacy";
    }

    @Override
    public String getDescription() {
        return "Removes any telemetry and other data related stuff";
    }

    @Override
    public FeatureRisk getRiskLevel() {
        return FeatureRisk.MEDIUM;
    }

    @Override
    public void onStart() throws Exception {
        PSExecutor ps = new PSExecutor();
        
        ps.executeCommand("Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows\\DataCollection' -Name 'AllowTelemetry' -Type DWord -Value 0");
        ps.executeCommand("Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Wow6432Node\\Policies\\Microsoft\\Windows\\DataCollection' -Name 'AllowTelemetry' -Type DWord -Value 0");
        Logger.info("Disabled Telemetry");
        
        
        ps.executeCommand("Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\CapabilityAccessManager\\ConsentStore\\location' -Name 'Value' -Type DWord -Value 0 -Force");
        Logger.info("Disabled Location Tracking");
        
        ps.executeCommand("if (!(Test-Path 'HKCU:\\SOFTWARE\\Microsoft\\Siuf\\Rules')) { New-Item -Path 'HKCU:\\SOFTWARE\\Microsoft\\Siuf\\Rules' -Force | Out-Null }");
        ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\SOFTWARE\\Microsoft\\Siuf\\Rules' -Name 'NumberOfSIUFInPeriod' -Type DWord -Value 0");
        ps.executeCommand("Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows\\DataCollection' -Name 'DoNotShowFeedbackNotifications' -Type DWord -Value 1");
        ps.executeCommand("Disable-ScheduledTask -TaskName 'Microsoft\\Windows\\Feedback\\Siuf\\DmClient' -ErrorAction SilentlyContinue | Out-Null");
        ps.executeCommand("Disable-ScheduledTask -TaskName 'Microsoft\\Windows\\Feedback\\Siuf\\DmClientOnScenarioDownload' -ErrorAction SilentlyContinue | Out-Null");
        Logger.info("Disabled Feedback.");
        
        ps.executeCommand("if (!(Test-Path 'HKCU:\\SOFTWARE\\Policies\\Microsoft\\Windows\\CloudContent')) { New-Item -Path 'HKCU:\\SOFTWARE\\Policies\\Microsoft\\Windows\\CloudContent' -Force | Out-Null }");
        ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\SOFTWARE\\Policies\\Microsoft\\Windows\\CloudContent' -Name 'DisableTailoredExperiencesWithDiagnosticData' -Type DWord -Value 1");
        Logger.info("Disabled Tailored Experiences");

        ps.executeCommand("Set-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows\\Windows Error Reporting' -Name 'Disabled' -Type DWord -Value 1");
        ps.executeCommand("Disable-ScheduledTask -TaskName 'Microsoft\\Windows\\Windows Error Reporting\\QueueReporting' | Out-Null");
        Logger.info("Disabled Error Reporting.");

        ps.executeCommand("Set-ItemProperty -Path 'HKCU:\\Control Panel\\International\\User Profile' -Name 'HttpAcceptLanguageOptOut' -Type DWord -Value 1");
        Logger.info("Disabled Website Access to Language List.");
        
        ps.executeCommand("Set-ItemProperty -Path 'HKLM:\\SYSTEM\\Maps' -Name 'AutoUpdateEnabled' -Type DWord -Value 0");
        Logger.info("Disabled automatic Maps updates.");
        
        ps.executeCommand("Stop-Service 'DiagTrack' -WarningAction SilentlyContinue");
        ps.executeCommand("Set-Service 'DiagTrack' -StartupType Disabled");
        Logger.info("Stopped and disabled Connected User Experiences and Telemetry Service.");
    }

    @Override
    public void onEnd() {
        // No implementation
    }
}