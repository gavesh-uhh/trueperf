package live.gavesh.trueperf.feature.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;

public class ModuleDebloater implements IFeature {

    private final LinkedHashMap<String, String> packages = new LinkedHashMap<>();
    
    private int totalPackages = 0;
    private int successfulRemovals = 0;
    private int failedRemovals = 0;
    
    private static final String THIRD_PARTY = "third-party";
    private static final String MICROSOFT = "microsoft";
    
    public ModuleDebloater() {
        initializePackageList();
    }

    private void initializePackageList() {
        addPackage("2414FC7A.Viber", THIRD_PARTY);
        addPackage("41038Axilesoft.ACGMediaPlayer", THIRD_PARTY);
        addPackage("46928bounde.EclipseManager", THIRD_PARTY);
        addPackage("4DF9E0F8.Netflix", THIRD_PARTY);
        addPackage("64885BlueEdge.OneCalendar", THIRD_PARTY);
        addPackage("7EE7776C.LinkedInforWindows", THIRD_PARTY);
        addPackage("828B5831.HiddenCityMysteryofShadows", THIRD_PARTY);
        addPackage("89006A2E.AutodeskSketchBook", THIRD_PARTY);
        addPackage("9E2F88E3.Twitter", THIRD_PARTY);
        addPackage("A278AB0D.DisneyMagicKingdoms", THIRD_PARTY);
        addPackage("A278AB0D.DragonManiaLegends", THIRD_PARTY);
        addPackage("A278AB0D.MarchofEmpires", THIRD_PARTY);
        addPackage("ActiproSoftwareLLC.562882FEEB491", THIRD_PARTY);
        addPackage("AD2F1837.GettingStartedwithWindows8", THIRD_PARTY);
        addPackage("AD2F1837.HPJumpStart", THIRD_PARTY);
        addPackage("AD2F1837.HPRegistration", THIRD_PARTY);
        addPackage("AdobeSystemsIncorporated.AdobePhotoshopExpress", THIRD_PARTY);
        addPackage("Amazon.com.Amazon", THIRD_PARTY);
        addPackage("C27EB4BA.DropboxOEM", THIRD_PARTY);
        addPackage("CAF9E577.Plex", THIRD_PARTY);
        addPackage("CyberLinkCorp.hs.PowerMediaPlayer14forHPConsumerPC", THIRD_PARTY);
        addPackage("D52A8D61.FarmVille2CountryEscape", THIRD_PARTY);
        addPackage("D5EA27B7.Duolingo-LearnLanguagesforFree", THIRD_PARTY);
        addPackage("DB6EA5DB.CyberLinkMediaSuiteEssentials", THIRD_PARTY);
        addPackage("DolbyLaboratories.DolbyAccess", THIRD_PARTY);
        addPackage("Drawboard.DrawboardPDF", THIRD_PARTY);
        addPackage("Facebook.Facebook", THIRD_PARTY);
        addPackage("Fitbit.FitbitCoach", THIRD_PARTY);
        addPackage("flaregamesGmbH.RoyalRevolt2", THIRD_PARTY);
        addPackage("GAMELOFTSA.Asphalt8Airborne", THIRD_PARTY);
        addPackage("KeeperSecurityInc.Keeper", THIRD_PARTY);
        addPackage("king.com.BubbleWitch3Saga", THIRD_PARTY);
        addPackage("king.com.CandyCrushFriends", THIRD_PARTY);
        addPackage("king.com.CandyCrushSaga", THIRD_PARTY);
        addPackage("king.com.CandyCrushSodaSaga", THIRD_PARTY);
        addPackage("king.com.FarmHeroesSaga", THIRD_PARTY);
        addPackage("Nordcurrent.CookingFever", THIRD_PARTY);
        addPackage("PandoraMediaInc.29680B314EFC2", THIRD_PARTY);
        addPackage("PricelinePartnerNetwork.Booking.comBigsavingsonhot", THIRD_PARTY);
        addPackage("SpotifyAB.SpotifyMusic", THIRD_PARTY);
        addPackage("ThumbmunkeysLtd.PhototasticCollage", THIRD_PARTY);
        addPackage("WinZipComputing.WinZipUniversal", THIRD_PARTY);
        addPackage("XINGAG.XING", THIRD_PARTY);
        
        addPackage("Microsoft.3DBuilder", MICROSOFT);
        addPackage("Microsoft.AppConnector", MICROSOFT);
        addPackage("Microsoft.BingFinance", MICROSOFT);
        addPackage("Microsoft.BingFoodAndDrink", MICROSOFT);
        addPackage("Microsoft.BingHealthAndFitness", MICROSOFT);
        addPackage("Microsoft.BingMaps", MICROSOFT);
        addPackage("Microsoft.BingNews", MICROSOFT);
        addPackage("Microsoft.BingSports", MICROSOFT);
        addPackage("Microsoft.BingTranslator", MICROSOFT);
        addPackage("Microsoft.BingTravel", MICROSOFT);
        addPackage("Microsoft.BingWeather", MICROSOFT);
        addPackage("Microsoft.CommsPhone", MICROSOFT);
        addPackage("Microsoft.ConnectivityStore", MICROSOFT);
        addPackage("Microsoft.FreshPaint", MICROSOFT);
        addPackage("Microsoft.GetHelp", MICROSOFT);
        addPackage("Microsoft.Getstarted", MICROSOFT);
        addPackage("Microsoft.HelpAndTips", MICROSOFT);
        addPackage("Microsoft.Media.PlayReadyClient.2", MICROSOFT);
        addPackage("Microsoft.Messaging", MICROSOFT);
        addPackage("Microsoft.Microsoft3DViewer", MICROSOFT);
        addPackage("Microsoft.MicrosoftOfficeHub", MICROSOFT);
        addPackage("Microsoft.MicrosoftPowerBIForWindows", MICROSOFT);
        addPackage("Microsoft.MicrosoftSolitaireCollection", MICROSOFT);
        addPackage("Microsoft.MicrosoftStickyNotes", MICROSOFT);
        addPackage("Microsoft.MinecraftUWP", MICROSOFT);
        addPackage("Microsoft.MixedReality.Portal", MICROSOFT);
        addPackage("Microsoft.MoCamera", MICROSOFT);
        addPackage("Microsoft.MSPaint", MICROSOFT);
        addPackage("Microsoft.NetworkSpeedTest", MICROSOFT);
        addPackage("Microsoft.OfficeLens", MICROSOFT);
        addPackage("Microsoft.Office.OneNote", MICROSOFT);
        addPackage("Microsoft.Office.Sway", MICROSOFT);
        addPackage("Microsoft.OneConnect", MICROSOFT);
        addPackage("Microsoft.OutlookForWindows", MICROSOFT);
        addPackage("Microsoft.People", MICROSOFT);
        addPackage("Microsoft.Print3D", MICROSOFT);
        addPackage("Microsoft.Reader", MICROSOFT);
        addPackage("Microsoft.RemoteDesktop", MICROSOFT);
        addPackage("Microsoft.SkypeApp", MICROSOFT);
        addPackage("Microsoft.Todos", MICROSOFT);
        addPackage("Microsoft.Wallet", MICROSOFT);
        addPackage("Microsoft.WebMediaExtensions", MICROSOFT);
        addPackage("Microsoft.Whiteboard", MICROSOFT);
        addPackage("Microsoft.WindowsAlarms", MICROSOFT);
        addPackage("Microsoft.WindowsCamera", MICROSOFT);
        addPackage("microsoft.windowscommunicationsapps", MICROSOFT);
        addPackage("Microsoft.WindowsFeedbackHub", MICROSOFT);
        addPackage("Microsoft.WindowsMaps", MICROSOFT);
        addPackage("Microsoft.WindowsPhone", MICROSOFT);
        addPackage("Microsoft.Windows.Photos", MICROSOFT);
        addPackage("Microsoft.WindowsReadingList", MICROSOFT);
        addPackage("Microsoft.WindowsScan", MICROSOFT);
        addPackage("Microsoft.WindowsSoundRecorder", MICROSOFT);
        addPackage("Microsoft.WinJS.1.0", MICROSOFT);
        addPackage("Microsoft.WinJS.2.0", MICROSOFT);
        addPackage("Microsoft.YourPhone", MICROSOFT);
        addPackage("Microsoft.ZuneMusic", MICROSOFT);
        addPackage("Microsoft.ZuneVideo", MICROSOFT);
        addPackage("Microsoft.Advertising.Xaml", MICROSOFT);
    }

    private void addPackage(String packageName, String category) {
        packages.put(packageName, category);
        totalPackages++;
    }
    
    @Override
    public String getName() {
        return "Bloatware Removal";
    }

    @Override
    public String getDescription() {
        return "Removes preinstalled bloatware/spyware for improved system performance";
    }

    @Override
    public FeatureRisk getRiskLevel() {
        return FeatureRisk.MEDIUM;
    }

    @Override
    public void onStart() throws Exception {
        PSExecutor ps = new PSExecutor();
        
        successfulRemovals = 0;
        failedRemovals = 0;
        
        for (Map.Entry<String, String> entry : packages.entrySet()) {
            String packageName = entry.getKey();
            String category = entry.getValue();
            
            try {
                String checkResult = ps.executeCommand("Get-AppxPackage '" + packageName + "' -ErrorAction SilentlyContinue");      
                if (checkResult != null && !checkResult.trim().isEmpty()) {
                    ps.executeCommand("Get-AppxPackage '" + packageName + "'  | Remove-AppxPackage -ErrorAction SilentlyContinue");
                    Logger.info("[" + category + "] " + packageName + " uninstalled!");
                    successfulRemovals++;
                } else {
                    Logger.info("[" + category + "] " + packageName + " not found, skipping.");
                }
            } catch (Exception e) {
                Logger.error("[" + category + "] Failed to uninstall " + packageName + ": " + e.getMessage());
                failedRemovals++;
            }
        }
        
        Logger.info("Bloatware removal completed: " + successfulRemovals + " packages removed, " + 
                   failedRemovals + " failed, " + (totalPackages - successfulRemovals - failedRemovals) + " not found.");
    }

    @Override
    public void onEnd() {
       
    }
    
    public void removeByCategory(String category) throws Exception {
        PSExecutor ps = new PSExecutor();
        
        for (Map.Entry<String, String> entry : packages.entrySet()) {
            if (entry.getValue().equals(category)) {
                String packageName = entry.getKey();
                try {
                    ps.executeCommand("Get-AppxPackage '" + packageName + "'  | Remove-AppxPackage");
                    Logger.info(packageName + " uninstalled!");
                    successfulRemovals++;
                } catch (Exception e) {
                    Logger.error("Failed to uninstall " + packageName);
                    failedRemovals++;
                }
            }
        }
    }
   
}