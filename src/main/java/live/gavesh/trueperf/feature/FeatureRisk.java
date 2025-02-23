package live.gavesh.trueperf.feature;

public enum FeatureRisk {

    LOW("#66ff66"),
    MEDIUM("#ffff66"),
    HIGH("#ff9900"),
    SEVERE("#ff3300"),
    CRITICAL("#ff0000");

    private final String colorHex;

    FeatureRisk(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getRiskLevel() {
        return name();
    }

    public String getColorHex() {
        return colorHex;
    }
    
}
