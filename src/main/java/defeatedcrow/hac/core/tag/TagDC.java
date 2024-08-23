package defeatedcrow.hac.core.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TagDC {

	public static void init() {
		BlockTag.init();
		ItemTag.init();
		BiomeTag.init();
		FluidTag.init();
	}

	public static class BlockTag {

		private static void init() {}

		public static final TagKey<Block> ORES_WHITE = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/white"));
		public static final TagKey<Block> ORES_BLUE = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/blue"));
		public static final TagKey<Block> ORES_BLACK = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/black"));
		public static final TagKey<Block> ORES_RED = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/red"));
		public static final TagKey<Block> ORES_GREEN = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/green"));

		public static final TagKey<Block> ORES_WHITE_DEEP = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_white"));
		public static final TagKey<Block> ORES_BLUE_DEEP = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_blue"));
		public static final TagKey<Block> ORES_BLACK_DEEP = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_black"));
		public static final TagKey<Block> ORES_RED_DEEP = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_red"));
		public static final TagKey<Block> ORES_GREEN_DEEP = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_green"));

		public static final TagKey<Block> ORES_COLOR = BlockTags.create(new ResourceLocation("dcs_climate", "color_ores"));

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
		public static final TagKey<Block> ORES_NITER = blockTag("ores/niter");
		public static final TagKey<Block> ORES_SULFUR = blockTag("ores/sulfur");
		public static final TagKey<Block> ORES_GYPSUM = blockTag("ores/gipsum");
		public static final TagKey<Block> ORES_TRAVERTINE = blockTag("ores/travertine");
		public static final TagKey<Block> ORES_LIME = blockTag("ores/lime");
		public static final TagKey<Block> ORES_NATRON = blockTag("ores/natron");

		public static final TagKey<Block> MUTABLE_FARMLAND = blockTag("mutable_farmland");
		public static final TagKey<Block> FARMLAND = blockTag("farmland");
		public static final TagKey<Block> MUD = blockTag("mud");
		public static final TagKey<Block> MAGMA = blockTag("magma");
		public static final TagKey<Block> WEED = blockTag("weed");

		public static final TagKey<Block> DUSTBLOCK_BRASS = blockTag("dust_blocks/brass");
		public static final TagKey<Block> DUSTBLOCK_BRONZE = blockTag("dust_blocks/bronze");
		public static final TagKey<Block> DUSTBLOCK_NICKEL_SILVER = blockTag("dust_blocks/nickel_silver");
		public static final TagKey<Block> DUSTBLOCK_STEEL = blockTag("dust_blocks/steel");
		public static final TagKey<Block> DUSTBLOCK_ALUMINUM = blockTag("dust_blocks/aluminum");
		public static final TagKey<Block> DUSTBLOCK_SILVER = blockTag("dust_blocks/silver");
		public static final TagKey<Block> DUSTBLOCK_SUS = blockTag("dust_blocks/sus");
		public static final TagKey<Block> DUSTBLOCK_TITANIUM = blockTag("dust_blocks/titanium");
		public static final TagKey<Block> DUSTBLOCK_MAGNET = blockTag("dust_blocks/magnet");
		public static final TagKey<Block> DUSTBLOCK_COBALT = blockTag("dust_blocks/cobalt_alloy");
		public static final TagKey<Block> DUSTBLOCK_HASTELLOY = blockTag("dust_blocks/hastelloy");
		public static final TagKey<Block> DUSTBLOCK_BSCCO = blockTag("dust_blocks/bscco");
		public static final TagKey<Block> DUSTBLOCK_RUBBER = blockTag("dust_blocks/rubber");

		public static final TagKey<Block> METALBLOCK_BRASS = blockTag("storage_blocks/brass");
		public static final TagKey<Block> METALBLOCK_BRONZE = blockTag("storage_blocks/bronze");
		public static final TagKey<Block> METALBLOCK_NICKEL_SILVER = blockTag("storage_blocks/nickel_silver");
		public static final TagKey<Block> METALBLOCK_STEEL = blockTag("storage_blocks/steel");
		public static final TagKey<Block> METALBLOCK_ALUMINUM = blockTag("storage_blocks/aluminum");
		public static final TagKey<Block> METALBLOCK_SILVER = blockTag("storage_blocks/silver");
		public static final TagKey<Block> METALBLOCK_SUS = blockTag("storage_blocks/sus");
		public static final TagKey<Block> METALBLOCK_TITANIUM = blockTag("storage_blocks/titanium");
		public static final TagKey<Block> METALBLOCK_MAGNET = blockTag("storage_blocks/magnet");
		public static final TagKey<Block> METALBLOCK_COBALT = blockTag("storage_blocks/cobalt_alloy");
		public static final TagKey<Block> METALBLOCK_HASTELLOY = blockTag("storage_blocks/hastelloy");
		public static final TagKey<Block> METALBLOCK_BSCCO = blockTag("storage_blocks/bscco");
		public static final TagKey<Block> BLOCK_RUBBER = blockTag("storage_blocks/rubber");

		public static final TagKey<Block> CONT_LEAVES = blockTag("storage_blocks/leaves");
		public static final TagKey<Block> CONT_LOGS = blockTag("storage_blocks/logs");
		public static final TagKey<Block> CONT_CHARCOAL = blockTag("storage_blocks/charcoal");
		public static final TagKey<Block> CONT_CROPS = blockTag("storage_blocks/crops");
		public static final TagKey<Block> CONT_WAX = blockTag("storage_blocks/wax");

		// for vanilla
		public static final TagKey<Block> CROP_PUMPKIN = blockTag("crops/pumpkin");
		public static final TagKey<Block> CROP_MELON = blockTag("crops/melon");
		public static final TagKey<Block> CROP_CACTUS = blockTag("crops/cactus");
		public static final TagKey<Block> CROP_TALL = blockTag("crops/tall_plants");

		public static final TagKey<Block> CROP_GREEN_MANURES = BlockTags.create(new ResourceLocation("dcs_climate", "green_manures"));

		public static final TagKey<Block> SCYTHE_BREAKABLE = BlockTags.create(new ResourceLocation("dcs_climate", "scythe_breakable"));

		public static final TagKey<Block> BEE_FLOWERS = BlockTags.create(new ResourceLocation("dcs_climate", "flowers_for_bee"));

		public static final TagKey<Block> LOG_SWEET = BlockTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/sweet_sap"));
		public static final TagKey<Block> LOG_RESIN = BlockTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/resin"));
		public static final TagKey<Block> LOG_LATEX = BlockTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/latex"));
		public static final TagKey<Block> LOG_LACQUER = BlockTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/lacquer"));
		public static final TagKey<Block> LOG_SAP = BlockTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap"));

		public static final TagKey<Block> BUILDING_STONE = BlockTags.create(new ResourceLocation("dcs_climate", "building_stones"));
		public static final TagKey<Block> BUILDING_BRICKS = BlockTags.create(new ResourceLocation("dcs_climate", "building_stones/bricks"));
		public static final TagKey<Block> BUILDING_PILLAR = BlockTags.create(new ResourceLocation("dcs_climate", "building_stones/pillar"));
		public static final TagKey<Block> BUILDING_CHISELED = BlockTags.create(new ResourceLocation("dcs_climate", "building_stones/chiseled"));

		public static final TagKey<Block> DIRT_SLABS = BlockTags.create(new ResourceLocation("dcs_climate", "dirt_slab"));

		public static final TagKey<Block> LEAKAGE_MACHINE = BlockTags.create(new ResourceLocation("dcs_climate", "leakage_machine"));

		public static final TagKey<Block> FLUID_PIPE = BlockTags.create(new ResourceLocation("dcs_climate", "pipe_fluid"));
		public static final TagKey<Block> ENERGY_CABLE = BlockTags.create(new ResourceLocation("dcs_climate", "cable_energy"));

		public static final TagKey<Block> HOPPER = BlockTags.create(new ResourceLocation("dcs_climate", "hoppers"));
		public static final TagKey<Block> HOPPER_FILTER = BlockTags.create(new ResourceLocation("dcs_climate", "hoppers/filter_hopper"));

		// public static final TagKey<Block> HEAT_TIER = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier"));
		// public static final TagKey<Block> ABSOLUTE = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/absolute"));
		// public static final TagKey<Block> CRYOGENIC = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/cryogenic"));
		// public static final TagKey<Block> FROSTBITE = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/frostbite"));
		// public static final TagKey<Block> COLD = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/cold"));
		// public static final TagKey<Block> COOL = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/cool"));
		// public static final TagKey<Block> NORMAL = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/normal"));
		// public static final TagKey<Block> WARM = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/warm"));
		// public static final TagKey<Block> HOT = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/hot"));
		// public static final TagKey<Block> BOIL = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/boil"));
		// public static final TagKey<Block> OVEN = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/oven"));
		// public static final TagKey<Block> KILN = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/kiln"));
		// public static final TagKey<Block> SMELTING = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/smelting"));
		// public static final TagKey<Block> UHT = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/uht"));
		// public static final TagKey<Block> INFERNO = BlockTags.create(new ResourceLocation("dcs_climate", "climate_heat_tier/inferno"));
		//
		// public static final TagKey<Block> HUMIDITY = BlockTags.create(new ResourceLocation("dcs_climate", "climate_humidity"));
		// public static final TagKey<Block> DRY = BlockTags.create(new ResourceLocation("dcs_climate", "climate_humidity/dry"));
		// public static final TagKey<Block> NORMAL_HUM = BlockTags.create(new ResourceLocation("dcs_climate", "climate_humidity/normal"));
		// public static final TagKey<Block> WET = BlockTags.create(new ResourceLocation("dcs_climate", "climate_humidity/wet"));
		// public static final TagKey<Block> UNDERWATER = BlockTags.create(new ResourceLocation("dcs_climate", "climate_humidity/underwater"));
		//
		// public static final TagKey<Block> AIRFLOW = BlockTags.create(new ResourceLocation("dcs_climate", "climate_airflow"));
		// public static final TagKey<Block> TIGHT = BlockTags.create(new ResourceLocation("dcs_climate", "climate_airflow/tight"));
		// public static final TagKey<Block> NORMAL_AIR = BlockTags.create(new ResourceLocation("dcs_climate", "climate_airflow/normal"));
		// public static final TagKey<Block> FLOW = BlockTags.create(new ResourceLocation("dcs_climate", "climate_airflow/flow"));
		// public static final TagKey<Block> WIND = BlockTags.create(new ResourceLocation("dcs_climate", "climate_airflow/wind"));

		private static TagKey<Block> blockTag(String name) {
			return BlockTags.create(new ResourceLocation("forge", name));
		}

	}

	public static class ItemTag {

		private static void init() {}

		public static final TagKey<Item> ORES_WHITE = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/white"));
		public static final TagKey<Item> ORES_BLUE = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/blue"));
		public static final TagKey<Item> ORES_BLACK = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/black"));
		public static final TagKey<Item> ORES_RED = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/red"));
		public static final TagKey<Item> ORES_GREEN = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/green"));

		public static final TagKey<Item> ORES_WHITE_DEEP = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_white"));
		public static final TagKey<Item> ORES_BLUE_DEEP = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_blue"));
		public static final TagKey<Item> ORES_BLACK_DEEP = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_black"));
		public static final TagKey<Item> ORES_RED_DEEP = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_red"));
		public static final TagKey<Item> ORES_GREEN_DEEP = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores/deep_green"));

		public static final TagKey<Item> ORES_COLOR = ItemTags.create(new ResourceLocation("dcs_climate", "color_ores"));

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
		public static final TagKey<Item> ORES_NITER = itemTag("ores/niter");
		public static final TagKey<Item> ORES_SULFUR = itemTag("ores/sulfur");
		public static final TagKey<Item> ORES_GYPSUM = itemTag("ores/gipsum");
		public static final TagKey<Item> ORES_TRAVERTINE = itemTag("ores/travertine");
		public static final TagKey<Item> ORES_LIME = itemTag("ores/lime");
		public static final TagKey<Item> ORES_NATRON = itemTag("ores/natron");

		public static final TagKey<Item> RAW_TUNGSTEN = itemTag("raw_materials/tungsten");
		public static final TagKey<Item> RAW_ZINC = itemTag("raw_materials/zinc");
		public static final TagKey<Item> RAW_BISMUTH = itemTag("raw_materials/bismuth");
		public static final TagKey<Item> RAW_COBALT = itemTag("raw_materials/cobalt");
		public static final TagKey<Item> RAW_MAGNETITE = itemTag("raw_materials/magnetite");
		public static final TagKey<Item> RAW_SILVER = itemTag("raw_materials/silver");
		public static final TagKey<Item> RAW_MOLYBDENUM = itemTag("raw_materials/molybdenum");
		public static final TagKey<Item> RAW_ALUMINUM = itemTag("raw_materials/aluminum");
		public static final TagKey<Item> RAW_TITANIUM = itemTag("raw_materials/titanium");
		public static final TagKey<Item> RAW_TIN = itemTag("raw_materials/tin");
		public static final TagKey<Item> RAW_NICKEL = itemTag("raw_materials/nickel");
		public static final TagKey<Item> RAW_CHROMIUM = itemTag("raw_materials/chromium");

		public static final TagKey<Item> RAW_MATERIALS_COLOR = ItemTags.create(new ResourceLocation("dcs_climate", "color_raw_materials"));

		public static final TagKey<Item> DUST_COPPER = itemTag("dusts/copper");
		public static final TagKey<Item> DUST_GOLD = itemTag("dusts/gold");
		public static final TagKey<Item> DUST_TUNGSTEN = itemTag("dusts/tungsten");
		public static final TagKey<Item> DUST_ZINC = itemTag("dusts/zinc");
		public static final TagKey<Item> DUST_BISMUTH = itemTag("dusts/bismuth");
		public static final TagKey<Item> DUST_COBALT = itemTag("dusts/cobalt");
		public static final TagKey<Item> DUST_MAGNETITE = itemTag("dusts/magnetite");
		public static final TagKey<Item> DUST_SILVER = itemTag("dusts/silver");
		public static final TagKey<Item> DUST_MOLYBDENUM = itemTag("dusts/molybdenum");
		public static final TagKey<Item> DUST_IRON = itemTag("dusts/iron");
		public static final TagKey<Item> DUST_ALUMINUM = itemTag("dusts/aluminum");
		public static final TagKey<Item> DUST_TITANIUM = itemTag("dusts/titanium");
		public static final TagKey<Item> DUST_TIN = itemTag("dusts/tin");
		public static final TagKey<Item> DUST_NICKEL = itemTag("dusts/nickel");
		public static final TagKey<Item> DUST_CHROMIUM = itemTag("dusts/chromium");

		public static final TagKey<Item> DUST_COLOR = ItemTags.create(new ResourceLocation("dcs_climate", "dusts"));

		public static final TagKey<Item> DUST_COAL = itemTag("dusts/coal");
		public static final TagKey<Item> DUST_SALT = itemTag("dusts/salt");
		public static final TagKey<Item> DUST_NITER = itemTag("dusts/niter");
		public static final TagKey<Item> DUST_SULFUR = itemTag("dusts/sulfur");
		public static final TagKey<Item> DUST_CRYSTAL = itemTag("dusts/crystal");
		public static final TagKey<Item> DUST_LIME = itemTag("dusts/lime");
		public static final TagKey<Item> DUST_TRONA = itemTag("dusts/trona");
		public static final TagKey<Item> DUST_ASH = itemTag("dusts/ash");
		public static final TagKey<Item> DUST_WOOD = itemTag("dusts/wood");
		public static final TagKey<Item> DUST_PLANT = itemTag("dusts/plant");
		public static final TagKey<Item> DUST_SUGAR = itemTag("dusts/sugar");

		public static final TagKey<Item> DUST_ALKALI = itemTag("dusts/alkali");

		public static final TagKey<Item> INGOT_BRASS = itemTag("ingots/brass");
		public static final TagKey<Item> INGOT_BRONZE = itemTag("ingots/bronze");
		public static final TagKey<Item> INGOT_ALUMINUM = itemTag("ingots/aluminum");
		public static final TagKey<Item> INGOT_SILVER = itemTag("ingots/silver");
		public static final TagKey<Item> INGOT_NICKEL_SILVER = itemTag("ingots/nickel_silver");
		public static final TagKey<Item> INGOT_STEEL = itemTag("ingots/steel");
		public static final TagKey<Item> INGOT_SUS = itemTag("ingots/sus");
		public static final TagKey<Item> INGOT_TITANIUM = itemTag("ingots/titanium");
		public static final TagKey<Item> INGOT_MAGNET = itemTag("ingots/magnet");
		public static final TagKey<Item> INGOT_COBALT = itemTag("ingots/cobalt_alloy");
		public static final TagKey<Item> INGOT_HASTELLOY = itemTag("ingots/hastelloy");
		public static final TagKey<Item> INGOT_BSCCO = itemTag("ingots/bscco");

		public static final TagKey<Item> DUSTBLOCK_BRASS = itemTag("dust_blocks/brass");
		public static final TagKey<Item> DUSTBLOCK_BRONZE = itemTag("dust_blocks/bronze");
		public static final TagKey<Item> DUSTBLOCK_NICKEL_SILVER = itemTag("dust_blocks/nickel_silver");
		public static final TagKey<Item> DUSTBLOCK_STEEL = itemTag("dust_blocks/steel");
		public static final TagKey<Item> DUSTBLOCK_ALUMINUM = itemTag("dust_blocks/aluminum");
		public static final TagKey<Item> DUSTBLOCK_SILVER = itemTag("dust_blocks/silver");
		public static final TagKey<Item> DUSTBLOCK_SUS = itemTag("dust_blocks/sus");
		public static final TagKey<Item> DUSTBLOCK_TITANIUM = itemTag("dust_blocks/titanium");
		public static final TagKey<Item> DUSTBLOCK_MAGNET = itemTag("dust_blocks/magnet");
		public static final TagKey<Item> DUSTBLOCK_COBALT = itemTag("dust_blocks/cobalt_alloy");
		public static final TagKey<Item> DUSTBLOCK_HASTELLOY = itemTag("dust_blocks/hastelloy");
		public static final TagKey<Item> DUSTBLOCK_BSCCO = itemTag("dust_blocks/bscco");
		public static final TagKey<Item> DUSTBLOCK_RUBBER = itemTag("dust_blocks/rubber");

		public static final TagKey<Item> METALBLOCK_BRASS = itemTag("storage_blocks/brass");
		public static final TagKey<Item> METALBLOCK_BRONZE = itemTag("storage_blocks/bronze");
		public static final TagKey<Item> METALBLOCK_NICKEL_SILVER = itemTag("storage_blocks/nickel_silver");
		public static final TagKey<Item> METALBLOCK_STEEL = itemTag("storage_blocks/steel");
		public static final TagKey<Item> METALBLOCK_ALUMINUM = itemTag("storage_blocks/aluminum");
		public static final TagKey<Item> METALBLOCK_SILVER = itemTag("storage_blocks/silver");
		public static final TagKey<Item> METALBLOCK_SUS = itemTag("storage_blocks/sus");
		public static final TagKey<Item> METALBLOCK_TITANIUM = itemTag("storage_blocks/titanium");
		public static final TagKey<Item> METALBLOCK_MAGNET = itemTag("storage_blocks/magnet");
		public static final TagKey<Item> METALBLOCK_COBALT = itemTag("storage_blocks/cobalt_alloy");
		public static final TagKey<Item> METALBLOCK_HASTELLOY = itemTag("storage_blocks/hastelloy");
		public static final TagKey<Item> METALBLOCK_BSCCO = itemTag("storage_blocks/bscco");
		public static final TagKey<Item> BLOCK_RUBBER = itemTag("storage_blocks/rubber");

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
		public static final TagKey<Item> GEM_SAKURA = itemTag("gems/sakura");
		public static final TagKey<Item> GEM_OPAL = itemTag("gems/opal");
		public static final TagKey<Item> GEM_ROSINCA = itemTag("gems/rosinca");
		public static final TagKey<Item> GEM_SPINEL = itemTag("gems/spinel");
		public static final TagKey<Item> GEM_AMAZONITE = itemTag("gems/amazonite");
		public static final TagKey<Item> GEM_JADEITE = itemTag("gems/jadeite");

		public static final TagKey<Item> GEM_GARNET = itemTag("gems/garnet");
		public static final TagKey<Item> GEM_PERIDOT = itemTag("gems/peridot");
		public static final TagKey<Item> GEM_AGATES = itemTag("gems/agates");

		public static final TagKey<Item> GEM_SALT = itemTag("gems/salt");
		public static final TagKey<Item> GEM_NITER = itemTag("gems/niter");
		public static final TagKey<Item> GEM_SULFUR = itemTag("gems/sulfur");
		public static final TagKey<Item> GEM_COAL = itemTag("gems/coal");
		public static final TagKey<Item> GEM_FLINT = itemTag("gems/flint");

		public static final TagKey<Item> ALL_WILD = ItemTags.create(new ResourceLocation("dcs_climate", "crops/wild"));
		public static final TagKey<Item> ALL_COMMON = ItemTags.create(new ResourceLocation("dcs_climate", "crops/common"));
		public static final TagKey<Item> ALL_RARE = ItemTags.create(new ResourceLocation("dcs_climate", "crops/rare"));
		public static final TagKey<Item> ALL_EPIC = ItemTags.create(new ResourceLocation("dcs_climate", "crops/epic"));
		public static final TagKey<Item> CROP_WILD = ItemTags.create(new ResourceLocation("dcs_climate", "crops/plant_wild"));
		public static final TagKey<Item> CROP_COMMON = ItemTags.create(new ResourceLocation("dcs_climate", "crops/plant_common"));
		public static final TagKey<Item> CROP_RARE = ItemTags.create(new ResourceLocation("dcs_climate", "crops/plant_rare"));
		public static final TagKey<Item> CROP_EPIC = ItemTags.create(new ResourceLocation("dcs_climate", "crops/plant_epic"));
		public static final TagKey<Item> TREE_WILD = ItemTags.create(new ResourceLocation("dcs_climate", "crops/tree_wild"));
		public static final TagKey<Item> TREE_COMMON = ItemTags.create(new ResourceLocation("dcs_climate", "crops/tree_common"));
		public static final TagKey<Item> TREE_RARE = ItemTags.create(new ResourceLocation("dcs_climate", "crops/tree_rare"));
		public static final TagKey<Item> TREE_EPIC = ItemTags.create(new ResourceLocation("dcs_climate", "crops/tree_epic"));
		public static final TagKey<Item> ALL_CROPS = ItemTags.create(new ResourceLocation("dcs_climate", "crops"));

		public static final TagKey<Item> CROP_GREEN_MANURES = ItemTags.create(new ResourceLocation("dcs_climate", "green_manures"));

		public static final TagKey<Item> CROP_CHIVES = itemTag("crops/chives");
		public static final TagKey<Item> CROP_ONION = itemTag("crops/onion");
		public static final TagKey<Item> CROP_GARLIC = itemTag("crops/garlic");
		public static final TagKey<Item> CROP_GOOSEFOOT = itemTag("crops/goosefoot");
		public static final TagKey<Item> CROP_GLASSWORT = itemTag("crops/glasswort");
		public static final TagKey<Item> CROP_SPINACH = itemTag("crops/spinach");
		public static final TagKey<Item> CROP_FENNEL = itemTag("crops/fennel");
		public static final TagKey<Item> CROP_CELERY = itemTag("crops/celery");
		public static final TagKey<Item> CROP_PARSNIP = itemTag("crops/parsnip");
		public static final TagKey<Item> CROP_CORIANDER = itemTag("crops/coriander");
		public static final TagKey<Item> CROP_RAPESEED = itemTag("crops/rapeseed");
		public static final TagKey<Item> CROP_NAPA = itemTag("crops/napa");
		public static final TagKey<Item> CROP_CABBAGE = itemTag("crops/cabbage");
		public static final TagKey<Item> CROP_RADISH = itemTag("crops/radish");
		public static final TagKey<Item> CROP_CHILI = itemTag("crops/chili");
		public static final TagKey<Item> CROP_PAPRIKA = itemTag("crops/paprika");
		public static final TagKey<Item> CROP_BELL = itemTag("crops/bellpepper");
		public static final TagKey<Item> CROP_OAT = itemTag("crops/oat");
		public static final TagKey<Item> CROP_RYE = itemTag("crops/rye");
		public static final TagKey<Item> CROP_BARLEY = itemTag("crops/barley");
		public static final TagKey<Item> CROP_ARTEMISIA = itemTag("crops/artemisia");
		public static final TagKey<Item> CROP_LETTUCE = itemTag("crops/lettuce");
		public static final TagKey<Item> CROP_PYRETHRUM = itemTag("crops/pyrethrum");
		public static final TagKey<Item> CROP_CHRYSANTHEMUM = itemTag("crops/chrysanthemum");
		public static final TagKey<Item> CROP_GINGER = itemTag("crops/ginger");
		public static final TagKey<Item> CROP_CARDAMOM = itemTag("crops/cardamom");
		public static final TagKey<Item> CROP_TURMERIC = itemTag("crops/turmeric");
		public static final TagKey<Item> CROP_CALABASH = itemTag("crops/calabash");
		public static final TagKey<Item> CROP_CUCUMBER = itemTag("crops/cucumber");
		public static final TagKey<Item> CROP_CANTALOUP = itemTag("crops/cantaloup");
		public static final TagKey<Item> CROP_WILD_GRAPE = itemTag("crops/wild_grape");
		public static final TagKey<Item> CROP_RED_GRAPE = itemTag("crops/red_grape");
		public static final TagKey<Item> CROP_WHITE_GRAPE = itemTag("crops/white_grape");
		public static final TagKey<Item> CROP_MINT = itemTag("crops/mint");
		public static final TagKey<Item> CROP_BASIL = itemTag("crops/basil");
		public static final TagKey<Item> CROP_PERILLA = itemTag("crops/perilla");
		public static final TagKey<Item> CROP_LAVENDER = itemTag("crops/lavender");
		public static final TagKey<Item> CROP_CROCUS = itemTag("crops/crocus");
		public static final TagKey<Item> CROP_SAFFRON = itemTag("crops/saffron");
		public static final TagKey<Item> CROP_IRIS = itemTag("crops/iris");
		public static final TagKey<Item> CROP_SORREL = itemTag("crops/sorrel");
		public static final TagKey<Item> CROP_BUCKWHEAT = itemTag("crops/buckwheat");
		public static final TagKey<Item> CROP_INDIGO = itemTag("crops/indigo");
		public static final TagKey<Item> CROP_JUTE = itemTag("crops/jute");
		public static final TagKey<Item> CROP_COTTON = itemTag("crops/cotton");
		public static final TagKey<Item> CROP_BLUE_MALLOW = itemTag("crops/blue_mallow");
		public static final TagKey<Item> CROP_TROPICAL = itemTag("crops/hibiscus");
		public static final TagKey<Item> CROP_BINDWEED = itemTag("crops/bindweed");
		public static final TagKey<Item> CROP_WATER_SPINACH = itemTag("crops/water_spinach");
		public static final TagKey<Item> CROP_SWEET_POTATO = itemTag("crops/sweet_potato");
		public static final TagKey<Item> CROP_MORNING_GLORY = itemTag("crops/morning_glory");
		public static final TagKey<Item> CROP_GUAVA = itemTag("crops/guava");
		public static final TagKey<Item> CROP_CLOVE = itemTag("crops/clove");
		public static final TagKey<Item> CROP_SPIRANTHES = itemTag("crops/spiranthes");
		public static final TagKey<Item> CROP_CYMBIDIUM = itemTag("crops/cymbidium");
		public static final TagKey<Item> CROP_VANILLA = itemTag("crops/vanilla");
		public static final TagKey<Item> CROP_CATTLEYA = itemTag("crops/cattleya");
		public static final TagKey<Item> CROP_GREEN_PEAS = itemTag("crops/pea");
		public static final TagKey<Item> CROP_GARBANZO = itemTag("crops/garbanzo");
		public static final TagKey<Item> CROP_SOY = itemTag("crops/soy");
		public static final TagKey<Item> CROP_ADZUKI = itemTag("crops/adzuki");
		public static final TagKey<Item> CROP_ROGERIA = itemTag("crops/rogeria");
		public static final TagKey<Item> CROP_SESAMI = itemTag("crops/sesami");
		public static final TagKey<Item> CROP_DEVILSCLAW = itemTag("crops/devilsclaw");
		public static final TagKey<Item> CROP_ANEMONE = itemTag("crops/anemone");
		public static final TagKey<Item> CROP_DELPHINIUM = itemTag("crops/delphinium");
		public static final TagKey<Item> CROP_CLEMATIS = itemTag("crops/clematis");
		public static final TagKey<Item> CROP_MONKSHOOD = itemTag("crops/monkshood");
		public static final TagKey<Item> CROP_REED = itemTag("crops/reed");
		public static final TagKey<Item> CROP_SORGHUM = itemTag("crops/sorghum");
		public static final TagKey<Item> CROP_CORN = itemTag("crops/corn");
		public static final TagKey<Item> CROP_WILD_RICE = itemTag("crops/zizania");
		public static final TagKey<Item> CROP_RICE = itemTag("crops/rice");
		public static final TagKey<Item> CROP_AROMA_RICE = itemTag("crops/aroma_rice");
		public static final TagKey<Item> CROP_NIGHTSHADE = itemTag("crops/nightshade");
		public static final TagKey<Item> CROP_EGGPLANT = itemTag("crops/egg_plant");
		public static final TagKey<Item> CROP_TOMATO = itemTag("crops/tomato");
		public static final TagKey<Item> CROP_LANTERN = itemTag("crops/lantern");

		public static final TagKey<Item> CROP_BEECH = itemTag("crops/beech");
		public static final TagKey<Item> CROP_WALNUT = itemTag("crops/walnut");
		public static final TagKey<Item> CROP_ACORN = itemTag("crops/acorn");
		public static final TagKey<Item> CROP_CHERRY = itemTag("crops/cherry");
		public static final TagKey<Item> CROP_PLUM = itemTag("crops/plum");
		public static final TagKey<Item> CROP_PEACH = itemTag("crops/peach");
		public static final TagKey<Item> CROP_ALMOND = itemTag("crops/almond");
		public static final TagKey<Item> CROP_CAMELLIA = itemTag("crops/camellia");
		public static final TagKey<Item> CROP_SCHIMA = itemTag("crops/schima");
		public static final TagKey<Item> CROP_TEA = itemTag("crops/tea");
		public static final TagKey<Item> CROP_CINNAMON = itemTag("crops/cinnamon");
		public static final TagKey<Item> CROP_AVOCADO = itemTag("crops/avocado");
		public static final TagKey<Item> CROP_POMELO = itemTag("crops/pomelo");
		public static final TagKey<Item> CROP_MANDARIN = itemTag("crops/mandarin");
		public static final TagKey<Item> CROP_LEMON = itemTag("crops/lemon");
		public static final TagKey<Item> CROP_SICHUAN_PEPPER = itemTag("crops/sichuan_pepper");
		public static final TagKey<Item> CROP_HEATH = itemTag("crops/heath");
		public static final TagKey<Item> CROP_RHODODENDRON = itemTag("crops/rhododendron");
		public static final TagKey<Item> CROP_BLUEBERRY = itemTag("crops/blueberry");
		public static final TagKey<Item> CROP_MULBERRY = itemTag("crops/mulberry");
		public static final TagKey<Item> CROP_KAJI = itemTag("crops/kaji");
		public static final TagKey<Item> CROP_RUBBER = itemTag("crops/rubber");
		public static final TagKey<Item> CROP_ASH = itemTag("crops/ash");
		public static final TagKey<Item> CROP_OLIVE = itemTag("crops/olive");
		public static final TagKey<Item> CROP_OSUMANTHUS = itemTag("crops/osmanthus");
		public static final TagKey<Item> CROP_COCONUT = itemTag("crops/coconut");
		public static final TagKey<Item> CROP_DATE = itemTag("crops/date");
		public static final TagKey<Item> CROP_OIL_PALM = itemTag("crops/oil_palm");
		public static final TagKey<Item> CROP_RUGOSA = itemTag("crops/rugosa");
		public static final TagKey<Item> CROP_RASPBERRY = itemTag("crops/raspberry");
		public static final TagKey<Item> CROP_DAMASCHENA = itemTag("crops/damaschena");
		public static final TagKey<Item> CROP_LACQUER = itemTag("crops/lacquer");
		public static final TagKey<Item> CROP_MANGO = itemTag("crops/mango");
		public static final TagKey<Item> CROP_CASHEW = itemTag("crops/cashew");
		public static final TagKey<Item> CROP_PISTACHIO = itemTag("crops/pistachio");

		public static final TagKey<Item> CAMELLIA_FLOWER = itemTag("crops/camellia_flower");
		public static final TagKey<Item> SCHIMA_FLOWER = itemTag("crops/schima_flower");

		public static final TagKey<Item> CROP_CEREALS = itemTag("crops/cereals");
		public static final TagKey<Item> CROP_MILLETS = itemTag("crops/millets");
		public static final TagKey<Item> CROP_PSEUDOCEREALS = itemTag("crops/preudo_cereals");
		public static final TagKey<Item> CROP_GREEN_LEAFS = itemTag("crops/green_leafs");
		public static final TagKey<Item> CROP_BEANS = itemTag("crops/beans");
		public static final TagKey<Item> CROP_NUTS = itemTag("crops/nuts");
		public static final TagKey<Item> CROP_SPICES = itemTag("crops/spices");
		public static final TagKey<Item> CROP_SPICY_VEGI = itemTag("crops/spicy_vegetables");
		public static final TagKey<Item> CROP_FLAVORED = itemTag("crops/flavored");
		public static final TagKey<Item> CROP_ONIONS = itemTag("crops/onions");
		public static final TagKey<Item> CROP_HERBS = itemTag("crops/herbs");
		public static final TagKey<Item> CROP_EDIBLE_RAW_VEGGIE = itemTag("crops/edible_raw_vegetables");
		public static final TagKey<Item> CROP_VEGETABLES = itemTag("crops/vegetables");
		public static final TagKey<Item> CROP_FLOWERS = itemTag("crops/flowers");
		public static final TagKey<Item> CROP_FRUITS = itemTag("crops/fruits");
		public static final TagKey<Item> CROP_CITRUS = itemTag("crops/citrus");
		public static final TagKey<Item> CROP_RED_GRAPES = itemTag("crops/red_grapes");
		public static final TagKey<Item> CROP_ALL_GRAPES = itemTag("crops/all_grapes");
		public static final TagKey<Item> CROP_TSUKEMONO = itemTag("crops/tsukemono_vegi");

		public static final TagKey<Item> CROP_STRAWS = ItemTags.create(new ResourceLocation("dcs_climate", "crops/straws"));
		public static final TagKey<Item> CROP_STICKS = ItemTags.create(new ResourceLocation("dcs_climate", "crops/sticks"));
		public static final TagKey<Item> CROP_OILS = ItemTags.create(new ResourceLocation("dcs_climate", "crops/oils"));

		public static final TagKey<Item> SEED_CHIVES = itemTag("seeds/chives");
		public static final TagKey<Item> SEED_ONION = itemTag("seeds/onion");
		public static final TagKey<Item> SEED_GARLIC = itemTag("seeds/garlic");
		public static final TagKey<Item> SEED_GOOSEFOOT = itemTag("seeds/goosefoot");
		public static final TagKey<Item> SEED_GLASSWORT = itemTag("seeds/glasswort");
		public static final TagKey<Item> SEED_SPINACH = itemTag("seeds/spinach");
		public static final TagKey<Item> SEED_FENNEL = itemTag("seeds/fennel");
		public static final TagKey<Item> SEED_CELERY = itemTag("seeds/celery");
		public static final TagKey<Item> SEED_PARSNIP = itemTag("seeds/parsnip");
		public static final TagKey<Item> SEED_CORIANDER = itemTag("seeds/coriander");
		public static final TagKey<Item> SEED_RAPESEED = itemTag("seeds/rapeseed");
		public static final TagKey<Item> SEED_NAPA = itemTag("seeds/napa");
		public static final TagKey<Item> SEED_CABBAGE = itemTag("seeds/cabbage");
		public static final TagKey<Item> SEED_RADISH = itemTag("seeds/radish");
		public static final TagKey<Item> SEED_CHILI = itemTag("seeds/chili");
		public static final TagKey<Item> SEED_PAPRIKA = itemTag("seeds/paprika");
		public static final TagKey<Item> SEED_BELL = itemTag("seeds/bellpepper");
		public static final TagKey<Item> SEED_OAT = itemTag("seeds/oat");
		public static final TagKey<Item> SEED_RYE = itemTag("seeds/rye");
		public static final TagKey<Item> SEED_BARLEY = itemTag("seeds/barley");
		public static final TagKey<Item> SEED_ARTEMISIA = itemTag("seeds/artemisia");
		public static final TagKey<Item> SEED_LETTUCE = itemTag("seeds/lettuce");
		public static final TagKey<Item> SEED_PYRETHRUM = itemTag("seeds/pyrethrum");
		public static final TagKey<Item> SEED_CHRYSANTHEMUM = itemTag("seeds/chrysanthemum");
		public static final TagKey<Item> SEED_GINGER = itemTag("seeds/ginger");
		public static final TagKey<Item> SEED_CARDAMOM = itemTag("seeds/cardamom");
		public static final TagKey<Item> SEED_TURMERIC = itemTag("seeds/turmeric");
		public static final TagKey<Item> SEED_CALABASH = itemTag("seeds/calabash");
		public static final TagKey<Item> SEED_CUCUMBER = itemTag("seeds/cucumber");
		public static final TagKey<Item> SEED_CANTALOUP = itemTag("seeds/cantaloup");
		public static final TagKey<Item> SEED_WILD_GRAPE = itemTag("seeds/wild_grape");
		public static final TagKey<Item> SEED_RED_GRAPE = itemTag("seeds/red_grape");
		public static final TagKey<Item> SEED_WHITE_GRAPE = itemTag("seeds/white_grape");
		public static final TagKey<Item> SEED_MINT = itemTag("seeds/mint");
		public static final TagKey<Item> SEED_BASIL = itemTag("seeds/basil");
		public static final TagKey<Item> SEED_PERILLA = itemTag("seeds/perilla");
		public static final TagKey<Item> SEED_LAVENDER = itemTag("seeds/lavender");
		public static final TagKey<Item> SEED_CROCUS = itemTag("seeds/crocus");
		public static final TagKey<Item> SEED_SAFFRON = itemTag("seeds/saffron");
		public static final TagKey<Item> SEED_IRIS = itemTag("seeds/iris");
		public static final TagKey<Item> SEED_SORREL = itemTag("seeds/sorrel");
		public static final TagKey<Item> SEED_BUCKWHEAT = itemTag("seeds/buckwheat");
		public static final TagKey<Item> SEED_INDIGO = itemTag("seeds/indigo");
		public static final TagKey<Item> SEED_JUTE = itemTag("seeds/jute");
		public static final TagKey<Item> SEED_COTTON = itemTag("seeds/cotton");
		public static final TagKey<Item> SEED_BLUE_MALLOW = itemTag("seeds/blue_mallow");
		public static final TagKey<Item> SEED_TROPICAL = itemTag("seeds/hibiscus");
		public static final TagKey<Item> SEED_BINDWEED = itemTag("seeds/bindweed");
		public static final TagKey<Item> SEED_WATER_SPINACH = itemTag("seeds/water_spinach");
		public static final TagKey<Item> SEED_SWEET_POTATO = itemTag("seeds/sweet_potato");
		public static final TagKey<Item> SEED_MORNING_GLORY = itemTag("seeds/morning_glory");
		public static final TagKey<Item> SEED_SPIRANTHES = itemTag("seeds/spiranthes");
		public static final TagKey<Item> SEED_CYMBIDIUM = itemTag("seeds/cymbidium");
		public static final TagKey<Item> SEED_VANILLA = itemTag("seeds/vanilla");
		public static final TagKey<Item> SEED_CATTLEYA = itemTag("seeds/cattleya");
		public static final TagKey<Item> SEED_ROGERIA = itemTag("seeds/rogeria");
		public static final TagKey<Item> SEED_SESAMI = itemTag("seeds/sesami");
		public static final TagKey<Item> SEED_DEVILSCLAW = itemTag("seeds/devilsclaw");
		public static final TagKey<Item> SEED_GREEN_PEAS = itemTag("seeds/green_pea");
		public static final TagKey<Item> SEED_GARBANZO = itemTag("seeds/garbanzo");
		public static final TagKey<Item> SEED_SOY = itemTag("seeds/soy");
		public static final TagKey<Item> SEED_ADZUKI = itemTag("seeds/adzuki");
		public static final TagKey<Item> SEED_ANEMONE = itemTag("seeds/anemone");
		public static final TagKey<Item> SEED_DELPHINIUM = itemTag("seeds/delphinium");
		public static final TagKey<Item> SEED_CLEMATIS = itemTag("seeds/clematis");
		public static final TagKey<Item> SEED_MONKSHOOD = itemTag("seeds/monkshood");
		public static final TagKey<Item> SEED_REED = itemTag("seeds/reed");
		public static final TagKey<Item> SEED_SORGHUM = itemTag("seeds/sorghum");
		public static final TagKey<Item> SEED_CORN = itemTag("seeds/corn");
		public static final TagKey<Item> SEED_WILD_RICE = itemTag("seeds/zizania");
		public static final TagKey<Item> SEED_RICE = itemTag("seeds/rice");
		public static final TagKey<Item> SEED_AROMA_RICE = itemTag("seeds/aroma_rice");
		public static final TagKey<Item> SEED_NIGHTSHADE = itemTag("seeds/nightshade");
		public static final TagKey<Item> SEED_EGGPLANT = itemTag("seeds/egg_plant");
		public static final TagKey<Item> SEED_TOMATO = itemTag("seeds/tomato");
		public static final TagKey<Item> SEED_LANTERN = itemTag("seeds/lantern");

		public static final TagKey<Item> SEEDS = itemTag("seeds");
		public static final TagKey<Item> SEED_SPROUT = itemTag("seeds/edible_sprout");
		public static final TagKey<Item> SEED_APIUM = itemTag("seeds/apium_seed");

		public static final TagKey<Item> FERTILIZER = itemTag("fertilizer");
		public static final TagKey<Item> FERTILIZER_ADV = itemTag("fertilizer_advanced");

		public static final TagKey<Item> CRAFT_MORTAR = itemTag("crafting_tools/mortar");
		public static final TagKey<Item> CRAFT_SIEVE = itemTag("crafting_tools/sieve");
		public static final TagKey<Item> CRAFT_SPINDLE = itemTag("crafting_tools/spindle");
		public static final TagKey<Item> CRAFT_SEEDING_POT = itemTag("crafting_tools/seeding_pot");
		public static final TagKey<Item> CRAFT_CALABASH = itemTag("crafting_tools/calabash_bottle");
		public static final TagKey<Item> CRAFT_DRIVER = itemTag("crafting_tools/screwdriver");
		public static final TagKey<Item> CRAFT_ALTIMETER = itemTag("crafting_tools/altimeter");
		public static final TagKey<Item> CRAFT_TEMPMETER = itemTag("crafting_tools/tempmeter");
		public static final TagKey<Item> CRAFT_FLOWMETER = itemTag("crafting_tools/flowmeter");
		public static final TagKey<Item> CRAFT_ENERGYMETER = itemTag("crafting_tools/energymeter");

		public static final TagKey<Item> SCYTHES = itemTag("tools/scythes");
		public static final TagKey<Item> HARPOON = itemTag("tools/harpoon");
		public static final TagKey<Item> CUTLERY = itemTag("tools/cutlery");
		public static final TagKey<Item> FIRESTARTER = itemTag("tools/firestarter");

		public static final TagKey<Item> DUST_AMARANTH = itemTag("ingredients/amaranth");
		public static final TagKey<Item> DUST_OAT = itemTag("ingredients/oat");
		public static final TagKey<Item> DUST_RYE = itemTag("ingredients/rye");
		public static final TagKey<Item> DUST_BARLEY = itemTag("ingredients/barley");
		public static final TagKey<Item> DUST_BUCKWHEAT = itemTag("ingredients/buckwheat");
		public static final TagKey<Item> DUST_SORGHUM = itemTag("ingredients/sorghum");
		public static final TagKey<Item> DUST_MASA = itemTag("ingredients/masa");
		public static final TagKey<Item> DUST_ZIZANIA = itemTag("ingredients/zizania");
		public static final TagKey<Item> DUST_RICE = itemTag("ingredients/rice");
		public static final TagKey<Item> DUST_AROMA_RICE = itemTag("ingredients/aroma_rice");
		public static final TagKey<Item> DUST_WHEAT = itemTag("ingredients/wheat");
		public static final TagKey<Item> DUST_STARCH = itemTag("ingredients/starch");

		public static final TagKey<Item> AGAR = itemTag("ingredients/agar");
		public static final TagKey<Item> MAKOMOTAKE = itemTag("ingredients/makomotake");
		public static final TagKey<Item> PALM_FLOWER = itemTag("ingredients/palm_flower");
		public static final TagKey<Item> MALLOW_CALYCES = itemTag("ingredients/mallow_calyces");
		public static final TagKey<Item> TEA_LEAVES_GREEN = itemTag("ingredients/tea_leaves_green");
		public static final TagKey<Item> TEA_LEAVES_OOLONG = itemTag("ingredients/tea_leaves_oolong");
		public static final TagKey<Item> TEA_LEAVES_BLACK = itemTag("ingredients/tea_leaves_black");
		public static final TagKey<Item> VANILLA_CURED = itemTag("ingredients/vanilla_cured");
		public static final TagKey<Item> CASHEW_NUTS = itemTag("ingredients/cashew_nuts");
		public static final TagKey<Item> ALMOND_NUTS = itemTag("ingredients/almond_nuts");
		public static final TagKey<Item> SPROUT = itemTag("ingredients/sprout");

		public static final TagKey<Item> DUST_BREAD_GRAINS = itemTag("ingredients/bread_grains");
		public static final TagKey<Item> DUST_CEREALS = itemTag("ingredients/cereals");
		public static final TagKey<Item> DUST_MILLETS = itemTag("ingredients/millets");
		public static final TagKey<Item> DUST_PSEUDOCEREALS = itemTag("ingredients/preudo_cereals");
		public static final TagKey<Item> DUST_RICES = itemTag("ingredients/rices");

		public static final TagKey<Item> BUTTER = itemTag("ingredients/butter");
		public static final TagKey<Item> MARGARINE = itemTag("ingredients/margarine");
		public static final TagKey<Item> FOOD_FAT = itemTag("ingredients/fat");
		public static final TagKey<Item> CHEESE = itemTag("ingredients/cheese");
		public static final TagKey<Item> YOGULT = itemTag("ingredients/yogult");

		public static final TagKey<Item> FROG = itemTag("ingredients/frog");

		public static final TagKey<Item> MACKEREL = itemTag("ingredients/mackerel");
		public static final TagKey<Item> SARDINE = itemTag("ingredients/sardine");
		public static final TagKey<Item> ROCKFISH = itemTag("ingredients/rockfish");
		public static final TagKey<Item> FLATHEAD = itemTag("ingredients/flathead");
		public static final TagKey<Item> GROUPER = itemTag("ingredients/grouper");
		public static final TagKey<Item> GURNARD = itemTag("ingredients/gurnard");
		public static final TagKey<Item> SEABREAM = itemTag("ingredients/seabream");
		public static final TagKey<Item> FLOUNDER = itemTag("ingredients/flounder");
		public static final TagKey<Item> MULLET = itemTag("ingredients/mullet");
		public static final TagKey<Item> SMELT = itemTag("ingredients/smelt");
		public static final TagKey<Item> TUNA = itemTag("ingredients/tuna");
		public static final TagKey<Item> TROUT = itemTag("ingredients/trout");
		public static final TagKey<Item> CARP = itemTag("ingredients/carp");
		public static final TagKey<Item> CRAB = itemTag("ingredients/crab");
		public static final TagKey<Item> PRAWN = itemTag("ingredients/prawn");
		public static final TagKey<Item> KRILL = itemTag("ingredients/krill");
		public static final TagKey<Item> SQUID = itemTag("ingredients/squid");

		public static final TagKey<Item> FISH_BLUE = itemTag("ingredients/blue_fishes");
		public static final TagKey<Item> FISH_WHITE = itemTag("ingredients/white_fishes");
		public static final TagKey<Item> FISH_WITH_ROE = itemTag("ingredients/fishes_with_roe");
		public static final TagKey<Item> ROE = itemTag("ingredients/roe");

		public static final TagKey<Item> OFFAL = itemTag("ingredients/offal");
		public static final TagKey<Item> GELATINE = itemTag("ingredients/geratine");
		public static final TagKey<Item> RENNET = itemTag("ingredients/rennet");

		public static final TagKey<Item> BONE_COW = itemTag("ingredients/bone_cow");
		public static final TagKey<Item> BONE_PIG = itemTag("ingredients/bone_pig");
		public static final TagKey<Item> BONE_CHICKEN = itemTag("ingredients/bone_chicken");

		public static final TagKey<Item> BASESOUP = itemTag("ingredients/basesoup");
		public static final TagKey<Item> PASTA = itemTag("ingredients/pasta");
		public static final TagKey<Item> NOODLE = itemTag("ingredients/noodle");
		public static final TagKey<Item> PASTRY = itemTag("ingredients/pastry");

		public static final TagKey<Item> SOYSAUCE = itemTag("ingredients/soysauce");
		public static final TagKey<Item> MISO = itemTag("ingredients/miso");
		public static final TagKey<Item> KETCHUP = itemTag("ingredients/ketchup");
		public static final TagKey<Item> MAYONNAISE = itemTag("ingredients/mayonnaise");
		public static final TagKey<Item> HOT_SAUSE = itemTag("ingredients/hot_sause");
		public static final TagKey<Item> HERB_SALT = itemTag("ingredients/herb_salt");
		public static final TagKey<Item> MIXED_SPICES = itemTag("ingredients/mixed_spices");
		public static final TagKey<Item> HUMMUS = itemTag("ingredients/hummus");
		public static final TagKey<Item> SALSA = itemTag("ingredients/salsa");
		public static final TagKey<Item> VINEGAR = itemTag("ingredients/vinegar");

		public static final TagKey<Item> SUGARS = itemTag("ingredients/sugars");
		public static final TagKey<Item> JAM = itemTag("ingredients/fluit_jam");
		public static final TagKey<Item> MARMALADE = itemTag("ingredients/marmalade");
		public static final TagKey<Item> CUSTARD = itemTag("ingredients/custard");
		public static final TagKey<Item> ANKO = itemTag("ingredients/anko");
		public static final TagKey<Item> TOFU = itemTag("ingredients/tofu");

		public static final TagKey<Item> COW_MILK = itemTag("ingredients/cow_milk");
		public static final TagKey<Item> SOY_MILK = itemTag("ingredients/soy_milk");
		public static final TagKey<Item> COCONUT_MILK = itemTag("ingredients/coconut_milk");
		public static final TagKey<Item> ALMOND_MILK = itemTag("ingredients/almond_milk");
		public static final TagKey<Item> MILKS = itemTag("ingredients/milks");
		public static final TagKey<Item> CREAM = itemTag("ingredients/milk_cream");
		public static final TagKey<Item> WHIP = itemTag("ingredients/plant_cream");
		public static final TagKey<Item> CREAMS = itemTag("ingredients/creams");
		public static final TagKey<Item> HONEY = itemTag("ingredients/honey");
		public static final TagKey<Item> SYRUP = itemTag("ingredients/syrup");
		public static final TagKey<Item> WATER = itemTag("ingredients/water");
		public static final TagKey<Item> SPARKLING = itemTag("ingredients/sparkling");
		public static final TagKey<Item> PLANT_OIL = itemTag("ingredients/plant_oil");
		public static final TagKey<Item> EMPTY_PACK = itemTag("ingredients/empty_pack");

		public static final TagKey<Item> VINE = itemTag("ingredients/vine");
		public static final TagKey<Item> FIBER_PLANT = itemTag("ingredients/fiber_plant");
		public static final TagKey<Item> FIBER_WOOD = itemTag("ingredients/fiber_wood");
		public static final TagKey<Item> STICK_SORGHUM = itemTag("rods/sorghum");
		public static final TagKey<Item> STICK_BAMBOO = itemTag("rods/bamboo");
		public static final TagKey<Item> FOOD_BEESWAX = itemTag("ingredients/beeswax");
		public static final TagKey<Item> FOOD_TREEWAX = itemTag("ingredients/treewax");
		public static final TagKey<Item> FOOD_WAX = itemTag("ingredients/wax");
		public static final TagKey<Item> CAMPHOR = itemTag("ingredients/camphor");

		public static final TagKey<Item> SAP_SWEET = itemTag("saps/sweet_sap");
		public static final TagKey<Item> SAP_RESIN = itemTag("saps/resin");
		public static final TagKey<Item> SAP_LATEX = itemTag("saps/latex");
		public static final TagKey<Item> SAP_LACQUER = itemTag("saps/lacquer");
		public static final TagKey<Item> SAPS = itemTag("saps");

		public static final TagKey<Item> STRING_PLANT = itemTag("strings/plant");
		public static final TagKey<Item> STRING_COTTON = itemTag("strings/cotton");
		public static final TagKey<Item> STRING_WOOL = itemTag("strings/wool");

		public static final TagKey<Item> CLOTH_PLANT = itemTag("clothes/plant");
		public static final TagKey<Item> CLOTH_COTTON = itemTag("clothes/cotton");
		public static final TagKey<Item> CLOTH_WOOL = itemTag("clothes/wool");
		public static final TagKey<Item> CLOTH_RUBBER = itemTag("clothes/rubber");
		public static final TagKey<Item> CLOTHS = itemTag("clothes");

		public static final TagKey<Item> FISH_POWDER = itemTag("ingredients/fish_powder");
		public static final TagKey<Item> DEFATTED_SOY = itemTag("ingredients/defatted_soy");
		public static final TagKey<Item> PRESS_CAKE = itemTag("ingredients/press_cake");
		public static final TagKey<Item> LEAF_MOLD = itemTag("ingredients/leaf_mold");
		public static final TagKey<Item> BRAN = itemTag("ingredients/bran");
		public static final TagKey<Item> BAGASSE = itemTag("ingredients/bagasse");
		public static final TagKey<Item> RESIDUES = itemTag("ingredients/residues");

		public static final TagKey<Item> FEED_HAY = itemTag("animal_feeds/hay");
		public static final TagKey<Item> FEED_STRAW = itemTag("animal_feeds/straw");
		public static final TagKey<Item> FEED_COMPOUND = itemTag("animal_feeds/compound");
		public static final TagKey<Item> FEEDS = itemTag("animal_feeds");

		public static final TagKey<Item> SOAP_OIL = itemTag("soaps/olive");
		public static final TagKey<Item> SOAP_MAGIC = itemTag("soaps/magic");
		public static final TagKey<Item> SOAPS = itemTag("soaps");

		public static final TagKey<Item> DOUGH = itemTag("foods/dough");
		public static final TagKey<Item> BREAD = itemTag("foods/bread");
		public static final TagKey<Item> BOILED_RICE = itemTag("foods/boiled_rice");

		public static final TagKey<Item> RAW_SAUSAGE = itemTag("ingredients/raw_sausage");
		public static final TagKey<Item> COOKED_SAUSAGE = itemTag("ingredients/cooked_sausage");

		public static final TagKey<Item> LIQUOR = itemTag("foods/liquor");
		public static final TagKey<Item> DRINK = itemTag("foods/drinks");

		public static final TagKey<Item> HAC_SEASONING = ItemTags.create(new ResourceLocation("dcs_climate", "seasoning"));

		public static final TagKey<Item> HAC_UNSAFE_FOODS = ItemTags.create(new ResourceLocation("dcs_climate", "unsafe_foods"));

		public static final TagKey<Item> HAC_MEALS = ItemTags.create(new ResourceLocation("dcs_climate", "meals"));
		public static final TagKey<Item> HAC_BREAD = ItemTags.create(new ResourceLocation("dcs_climate", "meals/bread"));
		public static final TagKey<Item> HAC_BREAD_PLANE = ItemTags.create(new ResourceLocation("dcs_climate", "meals/bread/plane"));
		public static final TagKey<Item> HAC_BREAD_SANDWICH = ItemTags.create(new ResourceLocation("dcs_climate", "meals/bread/sandwich"));
		public static final TagKey<Item> HAC_SOUP = ItemTags.create(new ResourceLocation("dcs_climate", "meals/soup"));
		public static final TagKey<Item> HAC_CURRY = ItemTags.create(new ResourceLocation("dcs_climate", "meals/curry"));
		public static final TagKey<Item> HAC_PORRIDGE = ItemTags.create(new ResourceLocation("dcs_climate", "meals/porridge"));
		public static final TagKey<Item> HAC_CASSEROLE = ItemTags.create(new ResourceLocation("dcs_climate", "meals/casserole"));
		public static final TagKey<Item> HAC_FISH_MEAL = ItemTags.create(new ResourceLocation("dcs_climate", "meals/fish_meal"));
		public static final TagKey<Item> HAC_MEAT_MEAL = ItemTags.create(new ResourceLocation("dcs_climate", "meals/meat_meal"));
		public static final TagKey<Item> HAC_RICE_MEAL = ItemTags.create(new ResourceLocation("dcs_climate", "meals/rice_meal"));
		public static final TagKey<Item> HAC_NOODLE = ItemTags.create(new ResourceLocation("dcs_climate", "meals/noodle"));
		public static final TagKey<Item> HAC_SALAD = ItemTags.create(new ResourceLocation("dcs_climate", "meals/salad"));
		public static final TagKey<Item> HAC_SKEWERED = ItemTags.create(new ResourceLocation("dcs_climate", "meals/skewered"));
		public static final TagKey<Item> HAC_PLATE_MEAL = ItemTags.create(new ResourceLocation("dcs_climate", "meals/plate_meal"));
		public static final TagKey<Item> HAC_TART = ItemTags.create(new ResourceLocation("dcs_climate", "meals/tart"));
		public static final TagKey<Item> HAC_SWEETS = ItemTags.create(new ResourceLocation("dcs_climate", "meals/sweets"));

		public static final TagKey<Item> HAC_LIQUOR = ItemTags.create(new ResourceLocation("dcs_climate", "meals/liquor"));
		public static final TagKey<Item> HAC_DRINK = ItemTags.create(new ResourceLocation("dcs_climate", "meals/drinks"));
		public static final TagKey<Item> HAC_DRINK_HOT = ItemTags.create(new ResourceLocation("dcs_climate", "meals/drinks/hot"));
		public static final TagKey<Item> HAC_DRINK_COLD = ItemTags.create(new ResourceLocation("dcs_climate", "meals/drinks/cold"));

		public static final TagKey<Item> HAC_ANIMAL = ItemTags.create(new ResourceLocation("dcs_climate", "ingredient/animal_base"));

		public static final TagKey<Item> FUEL_BIOMASS = ItemTags.create(new ResourceLocation("dcs_climate", "fuels/biomass"));
		public static final TagKey<Item> HAC_FUELS = ItemTags.create(new ResourceLocation("dcs_climate", "fuels"));

		// vanilla
		public static final TagKey<Item> CROP_APPLE = itemTag("crops/apple");
		public static final TagKey<Item> CROP_PUMPKIN = itemTag("crops/pumpkin");
		public static final TagKey<Item> CROP_MELON = itemTag("crops/melon");
		public static final TagKey<Item> CROP_COCOA = itemTag("crops/cocoa");
		public static final TagKey<Item> CROP_BERRY = itemTag("crops/berries");
		public static final TagKey<Item> CROP_SUGAR = itemTag("crops/sugarcane");
		public static final TagKey<Item> CROP_CACTUS = itemTag("crops/cactus");

		public static final TagKey<Item> RAW_BEEF = itemTag("ingredients/raw_beef");
		public static final TagKey<Item> RAW_PORK = itemTag("ingredients/raw_pork");
		public static final TagKey<Item> RAW_CHICKEN = itemTag("ingredients/raw_chicken");
		public static final TagKey<Item> RAW_MUTTON = itemTag("ingredients/raw_mutton");
		public static final TagKey<Item> RAW_RABBIT = itemTag("ingredients/raw_rabbit");
		public static final TagKey<Item> RAW_MEAT = itemTag("ingredients/raw_meats");
		public static final TagKey<Item> RAW_ROTTEN = itemTag("ingredients/raw_rotten_flesh");
		public static final TagKey<Item> RAW_PLANT_MEAT = itemTag("ingredients/raw_plantbase_meats");

		public static final TagKey<Item> COOKED_BEEF = itemTag("foods/cooked_beef");
		public static final TagKey<Item> COOKED_PORK = itemTag("foods/cooked_pork");
		public static final TagKey<Item> COOKED_CHICKEN = itemTag("foods/cooked_chicken");
		public static final TagKey<Item> COOKED_MUTTON = itemTag("foods/cooked_mutton");
		public static final TagKey<Item> COOKED_RABBIT = itemTag("foods/cooked_rabbit");
		public static final TagKey<Item> COOKED_MEAT = itemTag("foods/cooked_meats");

		public static final TagKey<Item> RAW_COD = itemTag("ingredients/raw_cod");
		public static final TagKey<Item> RAW_SALMON = itemTag("ingredients/raw_salmon");
		public static final TagKey<Item> RAW_EDIBLE_FISH = itemTag("ingredients/raw_fishes_edible");
		public static final TagKey<Item> RAW_ALL_FISH = itemTag("ingredients/raw_fishes");

		public static final TagKey<Item> COOKED_COD = itemTag("foods/cooked_cod");
		public static final TagKey<Item> COOKED_SALMON = itemTag("foods/cooked_salmon");
		public static final TagKey<Item> COOKED_FISH = itemTag("foods/cooked_fishes");

		public static final TagKey<Item> MAGMA = itemTag("magma");
		public static final TagKey<Item> DRIPSTONES = itemTag("dripstones");
		public static final TagKey<Item> WEED = itemTag("weed");

		public static final TagKey<Item> RAW_FOOD = ItemTags.create(new ResourceLocation("dcs_climate", "raw_foods"));

		// magic
		public static final TagKey<Item> SEED_WHITE = ItemTags.create(new ResourceLocation("dcs_climate", "color_seeds/white"));
		public static final TagKey<Item> SEED_BLUE = ItemTags.create(new ResourceLocation("dcs_climate", "color_seeds/blue"));
		public static final TagKey<Item> SEED_BLACK = ItemTags.create(new ResourceLocation("dcs_climate", "color_seeds/black"));
		public static final TagKey<Item> SEED_RED = ItemTags.create(new ResourceLocation("dcs_climate", "color_seeds/red"));
		public static final TagKey<Item> SEED_GREEN = ItemTags.create(new ResourceLocation("dcs_climate", "color_seeds/green"));

		// fish
		public static final TagKey<Item> FISH_ALL = ItemTags.create(new ResourceLocation("dcs_climate", "fishes"));
		public static final TagKey<Item> FISH_VANILLA = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/vanilla"));
		public static final TagKey<Item> FISH_HAC = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/hac"));
		public static final TagKey<Item> FISH_LOD = ItemTags.create(new ResourceLocation("dcs_climate", "can_fish_with_lod"));

		public static final TagKey<Item> FISH_RIVER = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/river"));
		public static final TagKey<Item> FISH_BEACH = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/beach"));
		public static final TagKey<Item> FISH_MANGROVE = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/mangrove"));
		public static final TagKey<Item> FISH_OCEAN = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/ocean"));
		public static final TagKey<Item> FISH_DEEP_OCEAN = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/deep_ocean"));

		public static final TagKey<Item> FISH_COLD_WATER = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/cold_water_only"));
		public static final TagKey<Item> FISH_TROPICAL = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/tropical_only"));

		public static final TagKey<Item> FISH_NIGHT = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/night"));
		public static final TagKey<Item> FISH_DAY = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/day"));

		public static final TagKey<Item> FISH_SHALLOW = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/shallow"));
		public static final TagKey<Item> FISH_FLOOR = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/floor"));

		public static final TagKey<Item> FISH_SMALL = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/small_size"));
		public static final TagKey<Item> FISH_MIDDLE = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/middle_size"));
		public static final TagKey<Item> FISH_LARGE = ItemTags.create(new ResourceLocation("dcs_climate", "fishes/large_size"));

		// container
		public static final TagKey<Item> CONT_LEAVES = itemTag("storage_blocks/leaves");
		public static final TagKey<Item> CONT_WASTE = itemTag("storage_blocks/wastes");
		public static final TagKey<Item> CONT_LOGS = itemTag("storage_blocks/logs");
		public static final TagKey<Item> CONT_CHARCOAL = itemTag("storage_blocks/charcoal");
		public static final TagKey<Item> CONT_CROPS = itemTag("storage_blocks/crops");
		public static final TagKey<Item> CONT_WAX = itemTag("storage_blocks/wax");

		public static final TagKey<Item> CONT_APPLE = itemTag("storage_blocks/crops/apple");
		public static final TagKey<Item> CONT_CARROT = itemTag("storage_blocks/crops/carrot");
		public static final TagKey<Item> CONT_POTATO = itemTag("storage_blocks/crops/potato");
		public static final TagKey<Item> CONT_BEET = itemTag("storage_blocks/crops/beet");
		public static final TagKey<Item> CONT_PUMPKIN = itemTag("storage_blocks/crops/pumpkin");
		public static final TagKey<Item> CONT_MELON = itemTag("storage_blocks/crops/melon");
		public static final TagKey<Item> CONT_CACTUS = itemTag("storage_blocks/crops/cactus");
		public static final TagKey<Item> CONT_SUGARCANE = itemTag("storage_blocks/crops/sugarcane");
		public static final TagKey<Item> CONT_COCOA = itemTag("storage_blocks/crops/cocoa");
		public static final TagKey<Item> CONT_BUSHBERRY = itemTag("storage_blocks/crops/bushberry");

		public static final TagKey<Item> CONT_BEESWAX = itemTag("storage_blocks/beeswax");
		public static final TagKey<Item> CONT_TREEWAX = itemTag("storage_blocks/treewax");

		// magic
		public static final TagKey<Item> COLOR_DROPS = ItemTags.create(new ResourceLocation("dcs_climate", "color_drops"));
		public static final TagKey<Item> DROP_WHITE = ItemTags.create(new ResourceLocation("dcs_climate", "color_drops/white"));
		public static final TagKey<Item> DROP_BLUE = ItemTags.create(new ResourceLocation("dcs_climate", "color_drops/blue"));
		public static final TagKey<Item> DROP_BLACK = ItemTags.create(new ResourceLocation("dcs_climate", "color_drops/black"));
		public static final TagKey<Item> DROP_RED = ItemTags.create(new ResourceLocation("dcs_climate", "color_drops/red"));
		public static final TagKey<Item> DROP_GREEN = ItemTags.create(new ResourceLocation("dcs_climate", "color_drops/green"));

		public static final TagKey<Item> COLOR_EXTRACTS = ItemTags.create(new ResourceLocation("dcs_climate", "color_extracts"));
		public static final TagKey<Item> EXTRACT_WHITE = ItemTags.create(new ResourceLocation("dcs_climate", "color_extracts/white"));
		public static final TagKey<Item> EXTRACT_BLUE = ItemTags.create(new ResourceLocation("dcs_climate", "color_extracts/blue"));
		public static final TagKey<Item> EXTRACT_BLACK = ItemTags.create(new ResourceLocation("dcs_climate", "color_extracts/black"));
		public static final TagKey<Item> EXTRACT_RED = ItemTags.create(new ResourceLocation("dcs_climate", "color_extracts/red"));
		public static final TagKey<Item> EXTRACT_GREEN = ItemTags.create(new ResourceLocation("dcs_climate", "color_extracts/green"));

		public static final TagKey<Item> COLOR_PIGMENTS = ItemTags.create(new ResourceLocation("dcs_climate", "color_pigments"));
		public static final TagKey<Item> PIGMENT_WHITE = ItemTags.create(new ResourceLocation("dcs_climate", "color_pigments/white"));
		public static final TagKey<Item> PIGMENT_BLUE = ItemTags.create(new ResourceLocation("dcs_climate", "color_pigments/blue"));
		public static final TagKey<Item> PIGMENT_BLACK = ItemTags.create(new ResourceLocation("dcs_climate", "color_pigments/black"));
		public static final TagKey<Item> PIGMENT_RED = ItemTags.create(new ResourceLocation("dcs_climate", "color_pigments/red"));
		public static final TagKey<Item> PIGMENT_GREEN = ItemTags.create(new ResourceLocation("dcs_climate", "color_pigments/green"));

		public static final TagKey<Item> COLOR_ELEMENTS = ItemTags.create(new ResourceLocation("dcs_climate", "color_elements"));
		public static final TagKey<Item> ELEMENT_WHITE = ItemTags.create(new ResourceLocation("dcs_climate", "color_elements/white"));
		public static final TagKey<Item> ELEMENT_BLUE = ItemTags.create(new ResourceLocation("dcs_climate", "color_elements/blue"));
		public static final TagKey<Item> ELEMENT_BLACK = ItemTags.create(new ResourceLocation("dcs_climate", "color_elements/black"));
		public static final TagKey<Item> ELEMENT_RED = ItemTags.create(new ResourceLocation("dcs_climate", "color_elements/red"));
		public static final TagKey<Item> ELEMENT_GREEN = ItemTags.create(new ResourceLocation("dcs_climate", "color_elements/green"));

		public static final TagKey<Item> COLOR_GEMS = ItemTags.create(new ResourceLocation("dcs_climate", "gems"));
		public static final TagKey<Item> GEM_WHITE = ItemTags.create(new ResourceLocation("dcs_climate", "gems/white"));
		public static final TagKey<Item> GEM_BLUE = ItemTags.create(new ResourceLocation("dcs_climate", "gems/blue"));
		public static final TagKey<Item> GEM_BLACK = ItemTags.create(new ResourceLocation("dcs_climate", "gems/black"));
		public static final TagKey<Item> GEM_RED = ItemTags.create(new ResourceLocation("dcs_climate", "gems/red"));
		public static final TagKey<Item> GEM_GREEN = ItemTags.create(new ResourceLocation("dcs_climate", "gems/green"));

		public static final TagKey<Item> MANA_DROPS = ItemTags.create(new ResourceLocation("dcs_climate", "mana_drops"));
		public static final TagKey<Item> MANA_EXTRACT = ItemTags.create(new ResourceLocation("dcs_climate", "mana_extracts"));
		public static final TagKey<Item> MANA_ELEMENT = ItemTags.create(new ResourceLocation("dcs_climate", "mana_elements"));

		public static final TagKey<Item> ELEMENT_INERT = ItemTags.create(new ResourceLocation("dcs_climate", "color_elements/inert"));

		public static final TagKey<Item> MAGIC_CARD = ItemTags.create(new ResourceLocation("dcs_climate", "magic_cards"));

		public static final TagKey<Item> MAGIC_ARROW = ItemTags.create(new ResourceLocation("dcs_climate", "magic_arrows"));

		public static final TagKey<Item> MAGIC_JEWEL = ItemTags.create(new ResourceLocation("dcs_climate", "jewels"));
		public static final TagKey<Item> MAGIC_RING = ItemTags.create(new ResourceLocation("dcs_climate", "jewels/magic_rings"));
		public static final TagKey<Item> MAGIC_PENDANT = ItemTags.create(new ResourceLocation("dcs_climate", "jewels/magic_pendants"));
		public static final TagKey<Item> MAGIC_BADGE = ItemTags.create(new ResourceLocation("dcs_climate", "jewels/magic_badges"));

		public static final TagKey<Item> MAGIC_BOOSTER = ItemTags.create(new ResourceLocation("dcs_climate", "magic_boost_item"));

		public static final TagKey<Item> MAGIC_ALL = ItemTags.create(new ResourceLocation("dcs_climate", "magic"));
		public static final TagKey<Item> MAGIC_TIER1 = ItemTags.create(new ResourceLocation("dcs_climate", "magic/tier1"));
		public static final TagKey<Item> MAGIC_TIER2 = ItemTags.create(new ResourceLocation("dcs_climate", "magic/tier2"));
		public static final TagKey<Item> MAGIC_TIER3 = ItemTags.create(new ResourceLocation("dcs_climate", "magic/tier3"));

		public static final TagKey<Item> LOG_SWEET = ItemTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/sweet_sap"));
		public static final TagKey<Item> LOG_RESIN = ItemTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/resin"));
		public static final TagKey<Item> LOG_LATEX = ItemTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/latex"));
		public static final TagKey<Item> LOG_LACQUER = ItemTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap/lacquer"));
		public static final TagKey<Item> LOG_SAP = ItemTags.create(new ResourceLocation("dcs_climate", "logs_can_collect_sap"));

		public static final TagKey<Item> BUILDING_STONE = ItemTags.create(new ResourceLocation("dcs_climate", "building_stones"));
		public static final TagKey<Item> BUILDING_BRICKS = ItemTags.create(new ResourceLocation("dcs_climate", "building_stones/bricks"));
		public static final TagKey<Item> BUILDING_PILLAR = ItemTags.create(new ResourceLocation("dcs_climate", "building_stones/pillar"));
		public static final TagKey<Item> BUILDING_CHISELED = ItemTags.create(new ResourceLocation("dcs_climate", "building_stones/chiseled"));

		public static final TagKey<Item> ALMINUM_ROOFS = ItemTags.create(new ResourceLocation("dcs_climate", "metal_roofs"));

		public static final TagKey<Item> DIRT_SLABS = ItemTags.create(new ResourceLocation("dcs_climate", "dirt_slab"));

		public static final TagKey<Item> DUMMY = ItemTags.create(new ResourceLocation("dcs_climate", "dummy"));

		// machine
		public static final TagKey<Item> MOTORS = ItemTags.create(new ResourceLocation("dcs_climate", "motors"));
		public static final TagKey<Item> MOTOR_T1 = ItemTags.create(new ResourceLocation("dcs_climate", "motors/small"));
		public static final TagKey<Item> MOTOR_T2 = ItemTags.create(new ResourceLocation("dcs_climate", "motors/middle"));

		public static final TagKey<Item> IMPELLERS = ItemTags.create(new ResourceLocation("dcs_climate", "impellers"));
		public static final TagKey<Item> IMPELLER_T1 = ItemTags.create(new ResourceLocation("dcs_climate", "impellers/small"));
		public static final TagKey<Item> IMPELLER_T2 = ItemTags.create(new ResourceLocation("dcs_climate", "impellers/middle"));

		public static final TagKey<Item> BATTERY_T1 = ItemTags.create(new ResourceLocation("dcs_climate", "battery_items/small"));

		public static final TagKey<Item> LEAKAGE_MACHINE = ItemTags.create(new ResourceLocation("dcs_climate", "leakage_machine"));

		public static final TagKey<Item> FLUID_PIPE = ItemTags.create(new ResourceLocation("dcs_climate", "pipe_fluid"));
		public static final TagKey<Item> ENERGY_CABLE = ItemTags.create(new ResourceLocation("dcs_climate", "cable_energy"));

		public static final TagKey<Item> HOPPER = ItemTags.create(new ResourceLocation("dcs_climate", "hoppers"));
		public static final TagKey<Item> HOPPER_FILTER = ItemTags.create(new ResourceLocation("dcs_climate", "hoppers/filter_hopper"));

		// for soap
		public static final TagKey<Item> COLORED_WOOL = ItemTags.create(new ResourceLocation("dcs_climate", "colored/wool"));
		public static final TagKey<Item> COLORED_TERRACOTTA = ItemTags.create(new ResourceLocation("dcs_climate", "colored/terracotta"));
		public static final TagKey<Item> COLORED_GLASS = ItemTags.create(new ResourceLocation("dcs_climate", "colored/glass"));
		public static final TagKey<Item> COLORED_GLASS_PLATE = ItemTags.create(new ResourceLocation("dcs_climate", "colored/glass_panel"));
		public static final TagKey<Item> COLORED_CANDLE = ItemTags.create(new ResourceLocation("dcs_climate", "colored/candle"));

		public static final TagKey<Item> HAC_LANTERN = ItemTags.create(new ResourceLocation("dcs_climate", "lanterns"));
		public static final TagKey<Item> HAC_ANDON = ItemTags.create(new ResourceLocation("dcs_climate", "andons"));
		public static final TagKey<Item> HAC_CARPET = ItemTags.create(new ResourceLocation("dcs_climate", "carpets"));
		public static final TagKey<Item> HAC_CHAIR = ItemTags.create(new ResourceLocation("dcs_climate", "chairs"));
		public static final TagKey<Item> HAC_SOFA = ItemTags.create(new ResourceLocation("dcs_climate", "sofas"));
		public static final TagKey<Item> HAC_TABLE = ItemTags.create(new ResourceLocation("dcs_climate", "tables"));
		public static final TagKey<Item> HAC_LUGGAGE = ItemTags.create(new ResourceLocation("dcs_climate", "luggages"));
		public static final TagKey<Item> HAC_LOCKER = ItemTags.create(new ResourceLocation("dcs_climate", "lockers"));
		public static final TagKey<Item> HAC_CABINET = ItemTags.create(new ResourceLocation("dcs_climate", "cabinets"));

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

	public static class FluidTag {

		private static void init() {}

		public static final TagKey<Fluid> BRINE = biomeTag("brine");
		public static final TagKey<Fluid> HOT_SPRING = biomeTag("hotspring");
		public static final TagKey<Fluid> SPARKLING = biomeTag("sparkling");
		public static final TagKey<Fluid> ALL_MILK = biomeTag("milks");
		public static final TagKey<Fluid> ALL_WATER = biomeTag("waters");
		public static final TagKey<Fluid> DRINK_WATER = biomeTag("drink_waters");
		public static final TagKey<Fluid> PLANT_OIL = biomeTag("plant_oil");
		public static final TagKey<Fluid> FUEL = biomeTag("fuel_oil");
		public static final TagKey<Fluid> AIR = biomeTag("air");

		private static TagKey<Fluid> biomeTag(String name) {
			return TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation("forge", name));
		}

	}

}
