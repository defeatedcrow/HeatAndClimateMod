package defeatedcrow.hac.core.material.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class TagDC {

	public static void init() {
		BlockTag.init();
		ItemTag.init();
		BiomeTag.init();
	}

	public static class BlockTag {

		private static void init() {}

		public static final TagKey<Block> ORES_WHITE = blockTag("ores/white");
		public static final TagKey<Block> ORES_BLUE = blockTag("ores/blue");
		public static final TagKey<Block> ORES_BLACK = blockTag("ores/black");
		public static final TagKey<Block> ORES_RED = blockTag("ores/red");
		public static final TagKey<Block> ORES_GREEN = blockTag("ores/green");

		public static final TagKey<Block> ORES_WHITE_DEEP = blockTag("ores/deep_white");
		public static final TagKey<Block> ORES_BLUE_DEEP = blockTag("ores/deep_blue");
		public static final TagKey<Block> ORES_BLACK_DEEP = blockTag("ores/deep_black");
		public static final TagKey<Block> ORES_RED_DEEP = blockTag("ores/deep_red");
		public static final TagKey<Block> ORES_GREEN_DEEP = blockTag("ores/deep_green");

		public static final TagKey<Block> ORES_CHALCEDONY = blockTag("ores/chalcedony");
		public static final TagKey<Block> ORES_FLUORITE = blockTag("ores/fluorite");
		public static final TagKey<Block> ORES_JET = blockTag("ores/jet");
		public static final TagKey<Block> ORES_DESERTROSE = blockTag("ores/desertrose");
		public static final TagKey<Block> ORES_SERPENTINE = blockTag("ores/serpentine");
		public static final TagKey<Block> ORES_HELIODOR = blockTag("ores/heliodor");
		public static final TagKey<Block> ORES_TOPAZ = blockTag("ores/topaz");
		public static final TagKey<Block> ORES_LARIMAR = blockTag("ores/larimar");
		public static final TagKey<Block> ORES_AQUAMARINE = blockTag("ores/aquamarine");
		public static final TagKey<Block> ORES_IOLITE = blockTag("ores/iolite");
		public static final TagKey<Block> ORES_OPAL = blockTag("ores/opal");
		public static final TagKey<Block> ORES_ROSINCA = blockTag("ores/rosinca");
		public static final TagKey<Block> ORES_SPINEL = blockTag("ores/spinel");
		public static final TagKey<Block> ORES_AMAZONITE = blockTag("ores/amazonite");
		public static final TagKey<Block> ORES_JADEITE = blockTag("ores/jadeite");
		public static final TagKey<Block> ORES_DRAGONSEYE = blockTag("ores/dragons_eye");

		public static final TagKey<Block> ORES_SALT = blockTag("ores/salt");
		public static final TagKey<Block> ORES_GUANO = blockTag("ores/guano");
		public static final TagKey<Block> ORES_SULFUR = blockTag("ores/sulfur");

		public static final TagKey<Block> FARMLAND = blockTag("farmland");
		public static final TagKey<Block> MUD = blockTag("mud");

		private static TagKey<Block> blockTag(String name) {
			return BlockTags.create(new ResourceLocation("forge", name));
		}

	}

	public static class ItemTag {

		private static void init() {}

		public static final TagKey<Item> ORES_WHITE = itemTag("ores/white");
		public static final TagKey<Item> ORES_BLUE = itemTag("ores/blue");
		public static final TagKey<Item> ORES_BLACK = itemTag("ores/black");
		public static final TagKey<Item> ORES_RED = itemTag("ores/red");
		public static final TagKey<Item> ORES_GREEN = itemTag("ores/green");

		public static final TagKey<Item> ORES_WHITE_DEEP = itemTag("ores/deep_white");
		public static final TagKey<Item> ORES_BLUE_DEEP = itemTag("ores/deep_blue");
		public static final TagKey<Item> ORES_BLACK_DEEP = itemTag("ores/deep_black");
		public static final TagKey<Item> ORES_RED_DEEP = itemTag("ores/deep_red");
		public static final TagKey<Item> ORES_GREEN_DEEP = itemTag("ores/deep_green");

		public static final TagKey<Item> ORES_CHALCEDONY = itemTag("ores/chalcedony");
		public static final TagKey<Item> ORES_FLUORITE = itemTag("ores/fluorite");
		public static final TagKey<Item> ORES_JET = itemTag("ores/jet");
		public static final TagKey<Item> ORES_DESERTROSE = itemTag("ores/desertrose");
		public static final TagKey<Item> ORES_SERPENTINE = itemTag("ores/serpentine");
		public static final TagKey<Item> ORES_HELIODOR = itemTag("ores/heliodor");
		public static final TagKey<Item> ORES_TOPAZ = itemTag("ores/topaz");
		public static final TagKey<Item> ORES_LARIMAR = itemTag("ores/larimar");
		public static final TagKey<Item> ORES_AQUAMARINE = itemTag("ores/aquamarine");
		public static final TagKey<Item> ORES_IOLITE = itemTag("ores/iolite");
		public static final TagKey<Item> ORES_OPAL = itemTag("ores/opal");
		public static final TagKey<Item> ORES_ROSINCA = itemTag("ores/rosinca");
		public static final TagKey<Item> ORES_SPINEL = itemTag("ores/spinel");
		public static final TagKey<Item> ORES_AMAZONITE = itemTag("ores/amazonite");
		public static final TagKey<Item> ORES_JADEITE = itemTag("ores/jadeite");
		public static final TagKey<Item> ORES_DRAGONSEYE = itemTag("ores/dragons_eye");

		public static final TagKey<Item> ORES_SALT = itemTag("ores/salt");
		public static final TagKey<Item> ORES_GUANO = itemTag("ores/guano");
		public static final TagKey<Item> ORES_SULFUR = itemTag("ores/sulfur");

		public static final TagKey<Item> RAW_MOLYBDENUM = itemTag("raw_materials/molybdenum");
		public static final TagKey<Item> RAW_ZINC = itemTag("raw_materials/zinc");
		public static final TagKey<Item> RAW_BISMUTH = itemTag("raw_materials/bismuth");
		public static final TagKey<Item> RAW_COBALT = itemTag("raw_materials/cobalt");
		public static final TagKey<Item> RAW_MAGNETITE = itemTag("raw_materials/magnetite");
		public static final TagKey<Item> RAW_SILVER = itemTag("raw_materials/silver");
		public static final TagKey<Item> RAW_TITANIUM = itemTag("raw_materials/titanium");
		public static final TagKey<Item> RAW_ALUMINUM = itemTag("raw_materials/aluminum");
		public static final TagKey<Item> RAW_MANGANESE = itemTag("raw_materials/manganese");
		public static final TagKey<Item> RAW_TIN = itemTag("raw_materials/tin");
		public static final TagKey<Item> RAW_NICKEL = itemTag("raw_materials/nickel");
		public static final TagKey<Item> RAW_CHROMIUM = itemTag("raw_materials/chromium");

		public static final TagKey<Item> GEM_CHALCEDONY = itemTag("gems/chalcedony");
		public static final TagKey<Item> GEM_CRYSTAL = itemTag("gems/crystal");
		public static final TagKey<Item> GEM_THUNDEREGG = itemTag("gems/thunder_egg");
		public static final TagKey<Item> GEM_CELESTITE = itemTag("gems/celestite");
		public static final TagKey<Item> GEM_SAPPHIRE = itemTag("gems/sapphire");
		public static final TagKey<Item> GEM_VIVIANITE = itemTag("gems/vivianite");
		public static final TagKey<Item> GEM_FANG = itemTag("gems/fang");
		public static final TagKey<Item> GEM_DRAGONSEYE = itemTag("gems/dragons_eye");
		public static final TagKey<Item> GEM_JASPER = itemTag("gems/jasper");
		public static final TagKey<Item> GEM_ALMANDINE = itemTag("gems/almandine");
		public static final TagKey<Item> GEM_RUBY = itemTag("gems/ruby");
		public static final TagKey<Item> GEM_MALACHITE = itemTag("gems/malachite");
		public static final TagKey<Item> GEM_OLIVINE = itemTag("gems/olivine");
		public static final TagKey<Item> GEM_FLUORITE = itemTag("gems/fluorite");
		public static final TagKey<Item> GEM_JET = itemTag("gems/jet");
		public static final TagKey<Item> GEM_DESERTROSE = itemTag("gems/desertrose");
		public static final TagKey<Item> GEM_SERPENTINE = itemTag("gems/serpentine");
		public static final TagKey<Item> GEM_HELIODOR = itemTag("gems/heliodor");
		public static final TagKey<Item> GEM_TOPAZ = itemTag("gems/topaz");
		public static final TagKey<Item> GEM_LARIMAR = itemTag("gems/larimar");
		public static final TagKey<Item> GEM_AQUAMARINE = itemTag("gems/aquamarine");
		public static final TagKey<Item> GEM_IOLITE = itemTag("gems/iolite");
		public static final TagKey<Item> GEM_OPAL = itemTag("gems/opal");
		public static final TagKey<Item> GEM_ROSINCA = itemTag("gems/rosinca");
		public static final TagKey<Item> GEM_SPINEL = itemTag("gems/spinel");
		public static final TagKey<Item> GEM_AMAZONITE = itemTag("gems/amazonite");
		public static final TagKey<Item> GEM_JADEITE = itemTag("gems/jadeite");

		public static final TagKey<Item> GEM_GARNET = itemTag("gems/garnet");
		public static final TagKey<Item> GEM_PERIDOT = itemTag("gems/peridot");

		public static final TagKey<Item> FERTILIZER = itemTag("fertilizer");

		private static TagKey<Item> itemTag(String name) {
			return ItemTags.create(new ResourceLocation("forge", name));
		}

	}

	public static class BiomeTag {

		private static void init() {}

		public static final TagKey<Biome> WHITE_BIOME = biomeTag("is_white");
		public static final TagKey<Biome> BLUE_BIOME = biomeTag("is_blue");
		public static final TagKey<Biome> BLACK_BIOME = biomeTag("is_black");
		public static final TagKey<Biome> RED_BIOME = biomeTag("is_red");
		public static final TagKey<Biome> GREEN_BIOME = biomeTag("is_green");
		public static final TagKey<Biome> NETHER_BIOME = biomeTag("is_nether");

		public static final TagKey<Biome> GEN = biomeTag("ore_target");

		private static TagKey<Biome> biomeTag(String name) {
			return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge", name));
		}

	}

}
