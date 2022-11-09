package defeatedcrow.hac.api.climate;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;

/**
 * 熱源の概念。<br>
 * バイオームの持つ温度と、範囲内のブロックのもつ温度のうち、最も高い温度を範囲内のHeatTierとする。<br>
 * (温度がマイナスのブロックがあると、上記によって算出したHeatTierを相殺したり、冷媒が必要なレシピに要求される。)<br>
 * <br>
 * ABSOLUTE: 絶対零度。自然には発生しない。<br>
 * CRYOGENIC: 極低温。自然には発生せず、マシンなどで作り出す。<br>
 * FROSTBITE: 凍傷。氷が硬い氷になる。スティーブがダメージを受ける。<br>
 * COLD: 寒冷バイオームの温度。氷が自然発生する。<br>
 * COOL: 冷涼バイオームの温度。氷や雪が溶けなくなる。<br>
 * NORMAL: 常温。熱源として利用は出来ないが、力学的エネルギーは"常温の熱量"として他MOD装置と互換性を持つ。<br>
 * WARM: 温暖バイオームの温度。植物の成長が促進される。発酵のレシピで使われる。<br>
 * HOT: 高温バイオームの温度。乾燥のレシピで使われる。<br>
 * BOIL: 沸騰の温度。お茶やゆで卵など。<br>
 * OVEN: 調理に適した温度帯。この温度帯から、防具なしではスティーブがダメージを受ける。<br>
 * KILN: 火ブロックや溶岩から得られる温度。草木の焼却、低い温度で出来る精錬のレシピに要求される。<br>
 * SMELT: 多くの金属精錬に要求される。得るためにはMOD装置が必要。<br>
 * UHT: 超高温炉。特殊なレシピにのみ要求される。<br>
 * INFERNO: 焦熱地獄。通常は発生しない。<br>
 * <br>
 * レシピに適合する温度は+1の幅がある。例えば、環境温度がKILNの場合、KILN要求レシピと、OVEN要求レシピの両方が稼働する。<br>
 * HOT以下のレシピはKILNでは高すぎて適応できない。また、SMELT以上のレシピは加熱不足で適応できない。
 */
public enum DCHeatTier {
	// absolute
	ABSOLUTE(-273, -5, 0, 0x0000A0, -6.0F),
	// extreme cooling
	CRYOGENIC(-150, -4, 1, 0x0020FF, -3.0F),
	// icecream making and cooling
	FROSTBITE(-50, -3, 2, 0x00A0FF, -1.0F),
	// cold climate biome
	COLD(-20, -2, 3, 0x00FFFF, -0.5F),
	// cool climate biome
	COOL(0, -1, 4, 0x70FFFF, 0F),
	// electric or mechanical energy require
	NORMAL(20, 0, 5, 0x00E115, 0.5F),
	// warm climate biome
	WARM(35, 1, 6, 0xA0FF00, 0.9F),
	// drying or brewing
	HOT(50, 2, 7, 0xFFE000, 1.3F),
	// boiling temperature
	BOIL(100, 3, 8, 0xFFA000, 2.1F),
	// cooking
	OVEN(220, 4, 9, 0xFF5000, 4.5F),
	// making charcoal, bronze, burn dust
	KILN(800, 5, 10, 0xFF0000, 16.0F),
	// making iron or another metal
	SMELTING(1500, 6, 11, 0xFF00FF, 30.0F),
	// special alloy
	UHT(3000, 7, 12, 0xFFA0FF, 60.0F),
	// only on data
	INFERNO(8000, 8, 13, 0x500000, 150.0F);

	private final int temp;
	private final int tier;
	private final int id;
	private final int color;
	private final float biomeTemp;

	private DCHeatTier(int t, int i, int n, int c, float b) {
		temp = t;
		tier = i;
		id = n;
		color = c;
		biomeTemp = b;
	}

	public static DCHeatTier getHeatEnum(int tier) {
		if (tier < -5)
			tier = -5;
		if (tier > 8)
			tier = 8;
		switch (tier) {
		case -5:
			return ABSOLUTE;
		case -4:
			return CRYOGENIC;
		case -3:
			return FROSTBITE;
		case -2:
			return COLD;
		case -1:
			return COOL;
		case 1:
			return WARM;
		case 2:
			return HOT;
		case 3:
			return BOIL;
		case 4:
			return OVEN;
		case 5:
			return KILN;
		case 6:
			return SMELTING;
		case 7:
			return UHT;
		case 8:
			return INFERNO;
		default:
			return NORMAL;
		}
	}

	public DCHeatTier addTier(int i) {
		int ret = tier + i;
		return getHeatEnum(ret);
	}

	public int getTemp() {
		return temp;
	}

	public int getTier() {
		return tier;
	}

	public int getID() {
		return id;
	}

	public boolean isCold() {
		return tier < 0;
	}

	public float getBiomeTemp() {
		return biomeTemp;
	}

	public static DCHeatTier getTypeByID(int id) {
		Mth.clamp(id, 0, 13);
		for (DCHeatTier e : values()) {
			if (id == e.id)
				return e;
		}
		return NORMAL;
	}

	public DCHeatTier getAverageTemp(DCHeatTier another) {
		if (another != null) {
			DCHeatTier nT = this;
			if (isCold() != another.isCold()) {
				int i = getTier() + another.getTier();
				nT = DCHeatTier.getHeatEnum(i);
			} else if (Math.abs(getTier()) < Math.abs(another.getTier())) {
				nT = another;
			}
			return nT;
		}
		return this;
	}

	public int[] getColor() {
		int r = (color >> 16) & 255;
		int g = (color >> 8) & 255;
		int b = color & 255;
		return new int[] { r, g, b };
	}

	public int getColorInt() {
		return color;
	}

	public MutableComponent localize() {
		return Component.translatable("dcs.enum.heat." + toString());
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public static List<DCHeatTier> createList() {
		List<DCHeatTier> tiers = new ArrayList<DCHeatTier>();

		for (DCHeatTier t : DCHeatTier.values()) {
			tiers.add(t);
		}

		return tiers;
	}

	public static DCHeatTier getTypeByTemperature(int temp) {
		if (temp >= 5300)
			return DCHeatTier.INFERNO;
		else if (temp >= 3300)
			return DCHeatTier.UHT;
		else if (temp >= 1500)
			return DCHeatTier.SMELTING;
		else if (temp >= 1000)
			return DCHeatTier.KILN;
		else if (temp >= 500)
			return DCHeatTier.OVEN;
		else if (temp >= 390)
			return DCHeatTier.BOIL;
		else if (temp >= 350)
			return DCHeatTier.HOT;
		else if (temp >= 320)
			return DCHeatTier.WARM;
		else if (temp >= 290)
			return DCHeatTier.NORMAL;
		else if (temp >= 250)
			return DCHeatTier.COOL;
		else if (temp >= 200)
			return DCHeatTier.COLD;
		else if (temp >= 130)
			return DCHeatTier.FROSTBITE;
		else if (temp >= 40)
			return DCHeatTier.CRYOGENIC;
		else
			return DCHeatTier.ABSOLUTE;
	}

	public static DCHeatTier getTypeByBiomeTemp(float temp) {
		if (temp > 99.5F)
			return DCHeatTier.INFERNO;
		else if (temp > 59.5F)
			return DCHeatTier.UHT;
		else if (temp > 29.5F)
			return DCHeatTier.SMELTING;
		else if (temp > 15.5F)
			return DCHeatTier.KILN;
		else if (temp > 3.9F)
			return DCHeatTier.OVEN;
		else if (temp > 2.1F)
			return DCHeatTier.BOIL;
		else if (temp > 1.4F)
			return DCHeatTier.HOT;
		else if (temp > 0.9F)
			return DCHeatTier.WARM;
		else if (temp > 0.4F)
			return DCHeatTier.NORMAL;
		else if (temp > 0.0F)
			return DCHeatTier.COOL;
		else if (temp > -0.5F)
			return DCHeatTier.COLD;
		else if (temp > -1.0F)
			return DCHeatTier.FROSTBITE;
		else if (temp > -3.0F)
			return DCHeatTier.CRYOGENIC;
		else
			return DCHeatTier.ABSOLUTE;
	}

	public static DCHeatTier getFromName(String name) {
		if (name != null)
			for (DCHeatTier t : DCHeatTier.values()) {
				if (t.name().equalsIgnoreCase(name)) {
					return t;
				}
			}
		return NORMAL;
	}

	public static DCHeatTier getFromNameOrNull(String name) {
		if (name != null)
			for (DCHeatTier t : DCHeatTier.values()) {
				if (t.name().equalsIgnoreCase(name)) {
					return t;
				}
			}
		return null;
	}

	public static MutableComponent basename() {
		return Component.translatable("dcs.enum.heat_name");
	}

	public static MutableComponent basename2() {
		return Component.translatable("dcs.enum.heat_name2");
	}
}
