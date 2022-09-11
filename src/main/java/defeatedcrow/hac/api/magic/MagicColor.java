package defeatedcrow.hac.api.magic;

public enum MagicColor {

	RED(true, false, false, false, false),
	GREEN(false, true, false, false, false),
	BLUE(false, false, true, false, false),
	WHITE(false, false, false, true, false),
	BLACK(false, false, false, false, true),
	RED_GREEN(true, true, false, false, false),
	GREEN_WHITE(false, true, false, true, false),
	BLUE_BLACK(false, false, true, false, true),
	WHITE_BLUE(false, false, true, true, false),
	BLACK_RED(true, false, false, false, true),
	RED_BLUE(true, false, true, false, false),
	GREEN_BLACK(false, true, false, false, true),
	BLUE_GREEN(false, true, true, false, false),
	WHITE_RED(true, false, false, true, false),
	BLACK_WHITE(false, false, false, true, true),
	NONE(false, false, false, false, false);

	public final boolean isRed;
	public final boolean isGreen;
	public final boolean isBlue;
	public final boolean isWhite;
	public final boolean isBlack;

	MagicColor(boolean red, boolean green, boolean blue, boolean white, boolean black) {
		isRed = red;
		isGreen = green;
		isBlue = blue;
		isWhite = white;
		isBlack = black;
	}

}
