package defeatedcrow.hac.core.worldgen.vein;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public enum EnumVein {

	WHITE(0, MagicColor.WHITE, TagDC.BiomeTag.WHITE_BIOME),
	BLUE(1, MagicColor.BLUE, TagDC.BiomeTag.BLUE_BIOME),
	BLACK(2, MagicColor.BLACK, TagDC.BiomeTag.BLACK_BIOME),
	RED(3, MagicColor.RED, TagDC.BiomeTag.RED_BIOME),
	GREEN(4, MagicColor.GREEN, TagDC.BiomeTag.GREEN_BIOME),
	WHITE_BLUE(5, MagicColor.WHITE_BLUE, TagDC.BiomeTag.WHITE_BIOME, TagDC.BiomeTag.BLUE_BIOME),
	BLUE_BLACK(6, MagicColor.BLUE_BLACK, TagDC.BiomeTag.BLUE_BIOME, TagDC.BiomeTag.BLACK_BIOME),
	BLACK_RED(7, MagicColor.BLACK_RED, TagDC.BiomeTag.BLACK_BIOME, TagDC.BiomeTag.RED_BIOME),
	RED_GREEN(8, MagicColor.RED_GREEN, TagDC.BiomeTag.RED_BIOME, TagDC.BiomeTag.GREEN_BIOME),
	GREEN_WHITE(9, MagicColor.GREEN_WHITE, TagDC.BiomeTag.GREEN_BIOME, TagDC.BiomeTag.WHITE_BIOME),
	WHITE_RED(10, MagicColor.WHITE_RED, TagDC.BiomeTag.WHITE_BIOME, TagDC.BiomeTag.RED_BIOME),
	BLUE_GREEN(11, MagicColor.BLUE_GREEN, TagDC.BiomeTag.BLUE_BIOME, TagDC.BiomeTag.GREEN_BIOME),
	BLACK_WHITE(12, MagicColor.BLACK_WHITE, TagDC.BiomeTag.BLACK_BIOME, TagDC.BiomeTag.WHITE_BIOME),
	RED_BLUE(13, MagicColor.RED_BLUE, TagDC.BiomeTag.RED_BIOME, TagDC.BiomeTag.BLUE_BIOME),
	GREEN_BLACK(14, MagicColor.GREEN_BLACK, TagDC.BiomeTag.GREEN_BIOME, TagDC.BiomeTag.BLACK_BIOME),
	NETHER(15, MagicColor.BLACK_RED, BiomeTags.IS_NETHER);

	public static final EnumVein[] VALUES = { RED, GREEN, BLUE, WHITE, BLACK, WHITE_BLUE, BLUE_BLACK, BLACK_RED, RED_GREEN,
		GREEN_WHITE, WHITE_RED, BLUE_GREEN, BLACK_WHITE, RED_BLUE, GREEN_BLACK, NETHER };

	public final int id;
	public final MagicColor color;
	public final List<TagKey<Biome>> biomes = Lists.newArrayList();

	EnumVein(int i, MagicColor c, TagKey<Biome>... tags) {
		id = i;
		color = c;
		if (tags != null) {
			for (TagKey<Biome> tag : tags) {
				biomes.add(tag);
			}
		}
	}

	public static EnumVein getType(String name) {
		String sub = name;
		if (name.contains("_")) {
			// 逆順
			String[] n2 = name.split("_");
			if (n2 != null) {
				if (n2.length > 1 && n2[1].length() > 2) {
					sub = n2[1] + "_" + n2[0];
				} else {
					sub = n2[0];
				}

			}
		}
		for (EnumVein ret : VALUES) {
			if (ret.name().equalsIgnoreCase(name) || ret.name().equalsIgnoreCase(sub)) {
				return ret;
			}
		}
		return WHITE;
	}

	public boolean is(Holder<Biome> biome) {
		for (TagKey<Biome> tag : biomes) {
			if (biome.containsTag(tag))
				return true;
		}
		return false;
	}
}
