package defeatedcrow.hac.core.worldgen.vein;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

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
	NETHER(10, MagicColor.BLACK_RED, BiomeTags.IS_NETHER);

	public static final EnumVein[] VALUES = { RED, GREEN, BLUE, WHITE, BLACK, WHITE_BLUE, BLUE_BLACK, BLACK_RED, RED_GREEN, GREEN_WHITE, NETHER };

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
		for (EnumVein ret : VALUES) {
			if (ret.name().equalsIgnoreCase(name)) {
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
