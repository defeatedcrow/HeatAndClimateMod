package defeatedcrow.hac.magic.item;

public enum JewelType {

	CHAL_BLUE("chalcedony_blue", "chal_blue"),
	CHAL_RED("chalcedony_red", "chal_red"),
	CHAL_WHITE("chalcedony_white", "chal_white"),
	CRYSTAL("crystal", "crystal"),
	SAPPHIRE("crystal", "crystal"),
	MALACHITE("crystal", "crystal"),
	CELESTITE("crystal", "crystal"),
	NACRE("nacre", "nacre"),
	LAPIS("lapis_lazli", "lapis"),
	DIAMOND("diamond", "diamond"),
	SCHORL("schorl", "schorl");

	private final String name;
	private final String tex;

	private JewelType(String s1, String s2) {
		name = s1;
		tex = s2;
	}

}
