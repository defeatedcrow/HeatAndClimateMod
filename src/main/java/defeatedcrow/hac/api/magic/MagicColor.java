package defeatedcrow.hac.api.magic;

import net.minecraft.ChatFormatting;

public enum MagicColor {

	RED(true, false, false, false, false, ChatFormatting.RED, true),
	GREEN(false, true, false, false, false, ChatFormatting.GREEN, true),
	BLUE(false, false, true, false, false, ChatFormatting.BLUE, true),
	WHITE(false, false, false, true, false, ChatFormatting.YELLOW, true),
	BLACK(false, false, false, false, true, ChatFormatting.GRAY, true),
	RED_GREEN(true, true, false, false, false, ChatFormatting.RED, false),
	GREEN_WHITE(false, true, false, true, false, ChatFormatting.GREEN, false),
	BLUE_BLACK(false, false, true, false, true, ChatFormatting.BLUE, false),
	WHITE_BLUE(false, false, true, true, false, ChatFormatting.YELLOW, false),
	BLACK_RED(true, false, false, false, true, ChatFormatting.GRAY, false),
	RED_BLUE(true, false, true, false, false, ChatFormatting.RED, false),
	GREEN_BLACK(false, true, false, false, true, ChatFormatting.GREEN, false),
	BLUE_GREEN(false, true, true, false, false, ChatFormatting.BLUE, false),
	WHITE_RED(true, false, false, true, false, ChatFormatting.YELLOW, false),
	BLACK_WHITE(false, false, false, true, true, ChatFormatting.GRAY, false),
	NONE(false, false, false, false, false, ChatFormatting.WHITE, false);

	public final ChatFormatting chatColor;
	public final boolean isRed;
	public final boolean isGreen;
	public final boolean isBlue;
	public final boolean isWhite;
	public final boolean isBlack;
	public final boolean isBasic;

	MagicColor(boolean red, boolean green, boolean blue, boolean white, boolean black, ChatFormatting c, boolean basic) {
		isRed = red;
		isGreen = green;
		isBlue = blue;
		isWhite = white;
		isBlack = black;
		chatColor = c;
		isBasic = basic;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public static MagicColor[] BASIC_COLOERS = { RED, GREEN, BLUE, WHITE, BLACK };
}
