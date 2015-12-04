package Objects;

public class ColorStyleHelper {
	private static final String FX_BG_COLOR = "-fx-background-color:#";
	private static final String FX_TEXT_FILL = "-fx-text-fill:#";
	public static String getBackgroundColorStyle(String color){
		return FX_BG_COLOR + color;
	}
	public static String getTextFillStyle(String color){
		return FX_TEXT_FILL + color;
	}
}
