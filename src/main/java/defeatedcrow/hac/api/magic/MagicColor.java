package defeatedcrow.hac.api.magic;

import net.minecraft.ChatFormatting;

public enum MagicColor {

	RED(true, false, false, false, false, ChatFormatting.RED),
	GREEN(false, true, false, false, false, ChatFormatting.GREEN),
	BLUE(false, false, true, false, false, ChatFormatting.BLUE),
	WHITE(false, false, false, true, false, ChatFormatting.YELLOW),
	BLACK(false, false, false, false, true, ChatFormatting.GRAY),
	RED_GREEN(true, true, false, false, false, ChatFormatting.RED),
	GREEN_WHITE(false, true, false, true, false, ChatFormatting.GREEN),
	BLUE_BLACK(false, false, true, false, true, ChatFormatting.BLUE),
	WHITE_BLUE(false, false, true, true, false, ChatFormatting.YELLOW),
	BLACK_RED(true, false, false, false, true, ChatFormatting.GRAY),
	RED_BLUE(true, false, true, false, false, ChatFormatting.RED),
	GREEN_BLACK(false, true, false, false, true, ChatFormatting.GREEN),
	BLUE_GREEN(false, true, true, false, false, ChatFormatting.BLUE),
	WHITE_RED(true, false, false, true, false, ChatFormatting.YELLOW),
	BLACK_WHITE(false, false, false, true, true, ChatFormatting.GRAY),
	NONE(false, false, false, false, false, ChatFormatting.WHITE);

	public final ChatFormatting chatColor;
	public final boolean isRed;
	public final boolean isGreen;
	public final boolean isBlue;
	public final boolean isWhite;
	public final boolean isBlack;

	MagicColor(boolean red, boolean green, boolean blue, boolean white, boolean black, ChatFormatting c) {
		isRed = red;
		isGreen = green;
		isBlue = blue;
		isWhite = white;
		isBlack = black;
		chatColor = c;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
