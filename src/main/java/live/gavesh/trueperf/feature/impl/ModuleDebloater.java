package live.gavesh.trueperf.feature.impl;

import java.util.LinkedList;
import live.gavesh.trueperf.feature.FeatureRisk;
import live.gavesh.trueperf.feature.IFeature;
import live.gavesh.trueperf.util.Logger;
import live.gavesh.trueperf.util.PSExecutor;

public class ModuleDebloater implements IFeature {

	private LinkedList<String> packages = new LinkedList<String>();

	public ModuleDebloater() {
		packages.add("2414FC7A.Viber");
		packages.add("41038Axilesoft.ACGMediaPlayer");
		packages.add("46928bounde.EclipseManager");
		packages.add("4DF9E0F8.Netflix");
		packages.add("64885BlueEdge.OneCalendar");
		packages.add("7EE7776C.LinkedInforWindows");
		packages.add("828B5831.HiddenCityMysteryofShadows");
		packages.add("89006A2E.AutodeskSketchBook");
		packages.add("9E2F88E3.Twitter");
		packages.add("A278AB0D.DisneyMagicKingdoms");
		packages.add("A278AB0D.DragonManiaLegends");
		packages.add("A278AB0D.MarchofEmpires");
		packages.add("ActiproSoftwareLLC.562882FEEB491");
		packages.add("AD2F1837.GettingStartedwithWindows8");
		packages.add("AD2F1837.HPJumpStart");
		packages.add("AD2F1837.HPRegistration");
		packages.add("AdobeSystemsIncorporated.AdobePhotoshopExpress");
		packages.add("Amazon.com.Amazon");
		packages.add("C27EB4BA.DropboxOEM");
		packages.add("CAF9E577.Plex");
		packages.add("CyberLinkCorp.hs.PowerMediaPlayer14forHPConsumerPC");
		packages.add("D52A8D61.FarmVille2CountryEscape");
		packages.add("D5EA27B7.Duolingo-LearnLanguagesforFree");
		packages.add("DB6EA5DB.CyberLinkMediaSuiteEssentials");
		packages.add("DolbyLaboratories.DolbyAccess");
		packages.add("Drawboard.DrawboardPDF");
		packages.add("Facebook.Facebook");
		packages.add("Fitbit.FitbitCoach");
		packages.add("flaregamesGmbH.RoyalRevolt2");
		packages.add("GAMELOFTSA.Asphalt8Airborne");
		packages.add("KeeperSecurityInc.Keeper");
		packages.add("king.com.BubbleWitch3Saga");
		packages.add("king.com.CandyCrushFriends");
		packages.add("king.com.CandyCrushSaga");
		packages.add("king.com.CandyCrushSodaSaga");
		packages.add("king.com.FarmHeroesSaga");
		packages.add("Nordcurrent.CookingFever");
		packages.add("PandoraMediaInc.29680B314EFC2");
		packages.add("PricelinePartnerNetwork.Booking.comBigsavingsonhot");
		packages.add("SpotifyAB.SpotifyMusic");
		packages.add("ThumbmunkeysLtd.PhototasticCollage");
		packages.add("WinZipComputing.WinZipUniversal");
		packages.add("XINGAG.XING");
		packages.add("Microsoft.3DBuilder");
		packages.add("Microsoft.AppConnector");
		packages.add("Microsoft.BingFinance");
		packages.add("Microsoft.BingFoodAndDrink");
		packages.add("Microsoft.BingHealthAndFitness");
		packages.add("Microsoft.BingMaps");
		packages.add("Microsoft.BingNews");
		packages.add("Microsoft.BingSports");
		packages.add("Microsoft.BingTranslator");
		packages.add("Microsoft.BingTravel");
		packages.add("Microsoft.BingWeather");
		packages.add("Microsoft.CommsPhone");
		packages.add("Microsoft.ConnectivityStore");
		packages.add("Microsoft.FreshPaint");
		packages.add("Microsoft.GetHelp");
		packages.add("Microsoft.Getstarted");
		packages.add("Microsoft.HelpAndTips");
		packages.add("Microsoft.Media.PlayReadyClient.2");
		packages.add("Microsoft.Messaging");
		packages.add("Microsoft.Microsoft3DViewer");
		packages.add("Microsoft.MicrosoftOfficeHub");
		packages.add("Microsoft.MicrosoftPowerBIForWindows");
		packages.add("Microsoft.MicrosoftSolitaireCollection");
		packages.add("Microsoft.MicrosoftStickyNotes");
		packages.add("Microsoft.MinecraftUWP");
		packages.add("Microsoft.MixedReality.Portal");
		packages.add("Microsoft.MoCamera");
		packages.add("Microsoft.MSPaint");
		packages.add("Microsoft.NetworkSpeedTest");
		packages.add("Microsoft.OfficeLens");
		packages.add("Microsoft.Office.OneNote");
		packages.add("Microsoft.Office.Sway");
		packages.add("Microsoft.OneConnect");
		packages.add("Microsoft.OutlookForWindows");
		packages.add("Microsoft.People");
		packages.add("Microsoft.Print3D");
		packages.add("Microsoft.Reader");
		packages.add("Microsoft.RemoteDesktop");
		packages.add("Microsoft.SkypeApp");
		packages.add("Microsoft.Todos");
		packages.add("Microsoft.Wallet");
		packages.add("Microsoft.WebMediaExtensions");
		packages.add("Microsoft.Whiteboard");
		packages.add("Microsoft.WindowsAlarms");
		packages.add("Microsoft.WindowsCamera");
		packages.add("microsoft.windowscommunicationsapps");
		packages.add("Microsoft.WindowsFeedbackHub");
		packages.add("Microsoft.WindowsMaps");
		packages.add("Microsoft.WindowsPhone");
		packages.add("Microsoft.Windows.Photos");
		packages.add("Microsoft.WindowsReadingList");
		packages.add("Microsoft.WindowsScan");
		packages.add("Microsoft.WindowsSoundRecorder");
		packages.add("Microsoft.WinJS.1.0");
		packages.add("Microsoft.WinJS.2.0");
		packages.add("Microsoft.YourPhone");
		packages.add("Microsoft.ZuneMusic");
		packages.add("Microsoft.ZuneVideo");
		packages.add("Microsoft.Advertising.Xaml");
	}

	@Override
	public String getName() {
		return "Debloater";
	}

	@Override
	public String getDescription() {
		return "Removes preinstalled bloatware/spyware";
	}

	@Override
	public FeatureRisk getRiskLevel() {
		return FeatureRisk.MEDIUM;
	}

	@Override
	public void onStart() throws Exception {
		PSExecutor ps = new PSExecutor();
		for (String string : packages) {
			try {
				ps.executeCommand("Get-AppxPackage '" + string + "'  | Remove-AppxPackage");
				Logger.info(string + " uninstalled!");
			} catch (Exception e) {
				Logger.error("Failed to uninstall " + string);
			}
		}
	}

	@Override
	public void onEnd() {

	}

}
