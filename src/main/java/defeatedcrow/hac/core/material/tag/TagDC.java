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
		public static final TagKey<Block> ORES_NITER = blockTag("ores/niter");
		public static final TagKey<Block> ORES_SULFUR = blockTag("ores/sulfur");
		public static final TagKey<Block> ORES_GYPSUM = blockTag("ores/gipsum");
		public static final TagKey<Block> ORES_TRAVERTINE = blockTag("ores/travertine");
		public static final TagKey<Block> ORES_LIME = blockTag("ores/lime");

		public static final TagKey<Block> FARMLAND = blockTag("farmland");
		public static final TagKey<Block> MUD = blockTag("mud");

		public static final TagKey<Block> DUSTBLOCK_BRASS = blockTag("dust_blocks/brass");
		public static final TagKey<Block> DUSTBLOCK_BRONZE = blockTag("dust_blocks/bronze");
		public static final TagKey<Block> DUSTBLOCK_NICKEL_SILVER = blockTag("dust_blocks/nickel_silver");
		public static final TagKey<Block> DUSTBLOCK_STEEL = blockTag("dust_blocks/steel");
		public static final TagKey<Block> DUSTBLOCK_SUS = blockTag("dust_blocks/sus");
		public static final TagKey<Block> DUSTBLOCK_TOOL_STEEL = blockTag("dust_blocks/tool_steel");
		public static final TagKey<Block> DUSTBLOCK_TITANIUM = blockTag("dust_blocks/titanium");
		public static final TagKey<Block> DUSTBLOCK_MAGNET = blockTag("dust_blocks/magnet");
		public static final TagKey<Block> DUSTBLOCK_MANGALLOY = blockTag("dust_blocks/mangalloy");
		public static final TagKey<Block> DUSTBLOCK_BSCCO = blockTag("dust_blocks/bscco");

		public static final TagKey<Block> METALBLOCK_BRASS = blockTag("storage_blocks/brass");
		public static final TagKey<Block> METALBLOCK_BRONZE = blockTag("storage_blocks/bronze");
		public static final TagKey<Block> METALBLOCK_NICKEL_SILVER = blockTag("storage_blocks/nickel_silver");
		public static final TagKey<Block> METALBLOCK_STEEL = blockTag("storage_blocks/steel");
		public static final TagKey<Block> METALBLOCK_SUS = blockTag("storage_blocks/sus");
		public static final TagKey<Block> METALBLOCK_TOOL_STEEL = blockTag("storage_blocks/tool_steel");
		public static final TagKey<Block> METALBLOCK_TITANIUM = blockTag("storage_blocks/titanium");
		public static final TagKey<Block> METALBLOCK_MAGNET = blockTag("storage_blocks/magnet");
		public static final TagKey<Block> METALBLOCK_MANGALLOY = blockTag("storage_blocks/mangalloy");
		public static final TagKey<Block> METALBLOCK_BSCCO = blockTag("storage_blocks/bscco");

		// for vanilla
		public static final TagKey<Block> CROP_PUMPKIN = blockTag("crops/pumpkin");
		public static final TagKey<Block> CROP_MELON = blockTag("crops/melon");

		public static final TagKey<Block> CROP_GREEN_MANURES = blockTag("crops/green_manures");

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
		public static final TagKey<Item> ORES_NITER = itemTag("ores/niter");
		public static final TagKey<Item> ORES_SULFUR = itemTag("ores/sulfur");
		public static final TagKey<Item> ORES_GYPSUM = itemTag("ores/gipsum");
		public static final TagKey<Item> ORES_TRAVERTINE = itemTag("ores/travertine");
		public static final TagKey<Item> ORES_LIME = itemTag("ores/lime");

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

		public static final TagKey<Item> DUST_COPPER = itemTag("dusts/copper");
		public static final TagKey<Item> DUST_GOLD = itemTag("dusts/gold");
		public static final TagKey<Item> DUST_MOLYBDENUM = itemTag("dusts/molybdenum");
		public static final TagKey<Item> DUST_ZINC = itemTag("dusts/zinc");
		public static final TagKey<Item> DUST_BISMUTH = itemTag("dusts/bismuth");
		public static final TagKey<Item> DUST_COBALT = itemTag("dusts/cobalt");
		public static final TagKey<Item> DUST_MAGNETITE = itemTag("dusts/magnetite");
		public static final TagKey<Item> DUST_SILVER = itemTag("dusts/silver");
		public static final TagKey<Item> DUST_TITANIUM = itemTag("dusts/titanium");
		public static final TagKey<Item> DUST_IRON = itemTag("dusts/iron");
		public static final TagKey<Item> DUST_ALUMINUM = itemTag("dusts/aluminum");
		public static final TagKey<Item> DUST_MANGANESE = itemTag("dusts/manganese");
		public static final TagKey<Item> DUST_TIN = itemTag("dusts/tin");
		public static final TagKey<Item> DUST_NICKEL = itemTag("dusts/nickel");
		public static final TagKey<Item> DUST_CHROMIUM = itemTag("dusts/chromium");

		public static final TagKey<Item> DUST_COAL = itemTag("dusts/coal");
		public static final TagKey<Item> DUST_NITER = itemTag("dusts/niter");
		public static final TagKey<Item> DUST_SULFUR = itemTag("dusts/sulfur");
		public static final TagKey<Item> DUST_CRYSTAL = itemTag("dusts/crystal");
		public static final TagKey<Item> DUST_LIME = itemTag("dusts/lime");

		public static final TagKey<Item> INGOT_BRASS = itemTag("ingots/brass");
		public static final TagKey<Item> INGOT_BRONZE = itemTag("ingots/bronze");
		public static final TagKey<Item> INGOT_ALUMINUM = itemTag("ingots/aluminum");
		public static final TagKey<Item> INGOT_SILVER = itemTag("ingots/silver");
		public static final TagKey<Item> INGOT_NICKEL_SILVER = itemTag("ingots/nickel_silver");
		public static final TagKey<Item> INGOT_STEEL = itemTag("ingots/steel");
		public static final TagKey<Item> INGOT_SUS = itemTag("ingots/sus");
		public static final TagKey<Item> INGOT_TOOL_STEEL = itemTag("ingots/tool_steel");
		public static final TagKey<Item> INGOT_TITANIUM = itemTag("ingots/titanium");
		public static final TagKey<Item> INGOT_MAGNET = itemTag("ingots/magnet");
		public static final TagKey<Item> INGOT_COBALT = itemTag("ingots/cobalt");
		public static final TagKey<Item> INGOT_MANGALLOY = itemTag("ingots/mangalloy");
		public static final TagKey<Item> INGOT_BSCCO = itemTag("ingots/bscco");

		public static final TagKey<Item> DUSTBLOCK_BRASS = itemTag("dust_blocks/brass");
		public static final TagKey<Item> DUSTBLOCK_BRONZE = itemTag("dust_blocks/bronze");
		public static final TagKey<Item> DUSTBLOCK_NICKEL_SILVER = itemTag("dust_blocks/nickel_silver");
		public static final TagKey<Item> DUSTBLOCK_STEEL = itemTag("dust_blocks/steel");
		public static final TagKey<Item> DUSTBLOCK_SUS = itemTag("dust_blocks/sus");
		public static final TagKey<Item> DUSTBLOCK_TOOL_STEEL = itemTag("dust_blocks/tool_steel");
		public static final TagKey<Item> DUSTBLOCK_TINANIUM = itemTag("dust_blocks/titanium");
		public static final TagKey<Item> DUSTBLOCK_MAGNET = itemTag("dust_blocks/magnet");
		public static final TagKey<Item> DUSTBLOCK_MANGALLOY = itemTag("dust_blocks/mangalloy");
		public static final TagKey<Item> DUSTBLOCK_BSCCO = itemTag("dust_blocks/bscco");

		public static final TagKey<Item> METALBLOCK_BRASS = itemTag("storage_blocks/brass");
		public static final TagKey<Item> METALBLOCK_BRONZE = itemTag("storage_blocks/bronze");
		public static final TagKey<Item> METALBLOCK_NICKEL_SILVER = itemTag("storage_blocks/nickel_silver");
		public static final TagKey<Item> METALBLOCK_STEEL = itemTag("storage_blocks/steel");
		public static final TagKey<Item> METALBLOCK_SUS = itemTag("storage_blocks/sus");
		public static final TagKey<Item> METALBLOCK_TOOL_STEEL = itemTag("storage_blocks/tool_steel");
		public static final TagKey<Item> METALBLOCK_TINANIUM = itemTag("storage_blocks/titanium");
		public static final TagKey<Item> METALBLOCK_MAGNET = itemTag("storage_blocks/magnet");
		public static final TagKey<Item> METALBLOCK_MANGALLOY = itemTag("storage_blocks/mangalloy");
		public static final TagKey<Item> METALBLOCK_BSCCO = itemTag("storage_blocks/bscco");

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

		public static final TagKey<Item> GEM_SALT = itemTag("gems/salt");
		public static final TagKey<Item> GEM_NITER = itemTag("gems/niter");
		public static final TagKey<Item> GEM_SULFUR = itemTag("gems/sulfur");
		public static final TagKey<Item> GEM_COAL = itemTag("gems/coal");

		public static final TagKey<Item> CROP_CHIVES = itemTag("crops/chives");
		public static final TagKey<Item> CROP_ONION = itemTag("crops/onion");
		public static final TagKey<Item> CROP_GARLIC = itemTag("crops/garlic");
		public static final TagKey<Item> CROP_GOOSEFOOT = itemTag("crops/goosefoot");
		public static final TagKey<Item> CROP_GLASSWORT = itemTag("crops/glasswort");
		public static final TagKey<Item> CROP_SPINACH = itemTag("crops/spinach");
		public static final TagKey<Item> CROP_FENNEL = itemTag("crops/fennel");
		public static final TagKey<Item> CROP_CELERY = itemTag("crops/celery");
		public static final TagKey<Item> CROP_PARSNIP = itemTag("crops/parsnip");
		public static final TagKey<Item> CROP_RAPESEED = itemTag("crops/rapeseed");
		public static final TagKey<Item> CROP_NAPA = itemTag("crops/napa");
		public static final TagKey<Item> CROP_CABAGGE = itemTag("crops/cabagge");
		public static final TagKey<Item> CROP_RADISH = itemTag("crops/radish");
		public static final TagKey<Item> CROP_CHILI = itemTag("crops/chili");
		public static final TagKey<Item> CROP_PAPRIKA = itemTag("crops/paprika");
		public static final TagKey<Item> CROP_BELL = itemTag("crops/bellpepper");
		public static final TagKey<Item> CROP_OAT = itemTag("crops/oat");
		public static final TagKey<Item> CROP_RYE = itemTag("crops/rye");
		public static final TagKey<Item> CROP_BARLEY = itemTag("crops/barley");
		public static final TagKey<Item> CROP_GINGER = itemTag("crops/ginger");
		public static final TagKey<Item> CROP_CARDAMOM = itemTag("crops/cardamom");
		public static final TagKey<Item> CROP_TURMERIC = itemTag("crops/turmeric");
		public static final TagKey<Item> CROP_MINT = itemTag("crops/mint");
		public static final TagKey<Item> CROP_BASIL = itemTag("crops/basil");
		public static final TagKey<Item> CROP_PERILLA = itemTag("crops/perilla");
		public static final TagKey<Item> CROP_LAVENDER = itemTag("crops/lavender");
		public static final TagKey<Item> CROP_SORREL = itemTag("crops/sorrel");
		public static final TagKey<Item> CROP_BUCKWHEAT = itemTag("crops/buckwheat");
		public static final TagKey<Item> CROP_INDIGO = itemTag("crops/indigo");
		public static final TagKey<Item> CROP_JUTE = itemTag("crops/jute");
		public static final TagKey<Item> CROP_COTTON = itemTag("crops/cotton");
		public static final TagKey<Item> CROP_BLUE_MALLOW = itemTag("crops/blue_mallow");
		public static final TagKey<Item> CROP_BINDWEED = itemTag("crops/bindweed");
		public static final TagKey<Item> CROP_WATER_SPINACH = itemTag("crops/water_spinach");
		public static final TagKey<Item> CROP_SWEET_POTATO = itemTag("crops/sweet_potato");
		public static final TagKey<Item> CROP_MORNING_GLORY = itemTag("crops/morning_glory");
		public static final TagKey<Item> CROP_GREEN_PEAS = itemTag("crops/pea");
		public static final TagKey<Item> CROP_GARBANZO = itemTag("crops/garbanzo");
		public static final TagKey<Item> CROP_SOY = itemTag("crops/soy");
		public static final TagKey<Item> CROP_ADZUKI = itemTag("crops/adzuki");
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

		// for vanilla
		public static final TagKey<Item> CROP_APPLE = itemTag("crops/apple");
		public static final TagKey<Item> CROP_PUMPKIN = itemTag("crops/pumpkin");
		public static final TagKey<Item> CROP_MELON = itemTag("crops/melon");
		public static final TagKey<Item> CROP_COCOA = itemTag("crops/cocoa");

		public static final TagKey<Item> CROP_CEREALS = itemTag("crops/cereals");
		public static final TagKey<Item> CROP_MILLETS = itemTag("crops/millets");
		public static final TagKey<Item> CROP_PSEUDOCEREALS = itemTag("crops/preudo_cereals");
		public static final TagKey<Item> CROP_GREEN_LEAFS = itemTag("crops/green_leafs");
		public static final TagKey<Item> CROP_BEANS = itemTag("crops/beans");
		public static final TagKey<Item> CROP_SPICES = itemTag("crops/spices");
		public static final TagKey<Item> CROP_VEGETABLES = itemTag("crops/vegetables");
		public static final TagKey<Item> CROP_FLOWERS = itemTag("crops/flowers");
		public static final TagKey<Item> CROP_FLUITS = itemTag("crops/fluits");
		public static final TagKey<Item> CROP_GREEN_MANURES = itemTag("crops/green_manures");
		public static final TagKey<Item> CROP_STRAWS = itemTag("crops/straws");
		public static final TagKey<Item> CROP_STICKS = itemTag("crops/sticks");
		public static final TagKey<Item> CROP_OILS = itemTag("crops/oils");

		public static final TagKey<Item> SEED_CHIVES = itemTag("seeds/chives");
		public static final TagKey<Item> SEED_ONION = itemTag("seeds/onion");
		public static final TagKey<Item> SEED_GARLIC = itemTag("seeds/garlic");
		public static final TagKey<Item> SEED_GOOSEFOOT = itemTag("seeds/goosefoot");
		public static final TagKey<Item> SEED_GLASSWORT = itemTag("seeds/glasswort");
		public static final TagKey<Item> SEED_SPINACH = itemTag("seeds/spinach");
		public static final TagKey<Item> SEED_FENNEL = itemTag("seeds/fennel");
		public static final TagKey<Item> SEED_CELERY = itemTag("seeds/celery");
		public static final TagKey<Item> SEED_PARSNIP = itemTag("seeds/parsnip");
		public static final TagKey<Item> SEED_RAPESEED = itemTag("seeds/rapeseed");
		public static final TagKey<Item> SEED_NAPA = itemTag("seeds/napa");
		public static final TagKey<Item> SEED_CABAGGE = itemTag("seeds/cabagge");
		public static final TagKey<Item> SEED_RADISH = itemTag("seeds/radish");
		public static final TagKey<Item> SEED_CHILI = itemTag("seeds/chili");
		public static final TagKey<Item> SEED_PAPRIKA = itemTag("seeds/paprika");
		public static final TagKey<Item> SEED_BELL = itemTag("seeds/bellpepper");
		public static final TagKey<Item> SEED_OAT = itemTag("seeds/oat");
		public static final TagKey<Item> SEED_RYE = itemTag("seeds/rye");
		public static final TagKey<Item> SEED_BARLEY = itemTag("seeds/barley");
		public static final TagKey<Item> SEED_GINGER = itemTag("seeds/ginger");
		public static final TagKey<Item> SEED_CARDAMOM = itemTag("seeds/cardamom");
		public static final TagKey<Item> SEED_TURMERIC = itemTag("seeds/turmeric");
		public static final TagKey<Item> SEED_MINT = itemTag("seeds/mint");
		public static final TagKey<Item> SEED_BASIL = itemTag("seeds/basil");
		public static final TagKey<Item> SEED_PERILLA = itemTag("seeds/perilla");
		public static final TagKey<Item> SEED_LAVENDER = itemTag("seeds/lavender");
		public static final TagKey<Item> SEED_SORREL = itemTag("seeds/sorrel");
		public static final TagKey<Item> SEED_BUCKWHEAT = itemTag("seeds/buckwheat");
		public static final TagKey<Item> SEED_INDIGO = itemTag("seeds/indigo");
		public static final TagKey<Item> SEED_JUTE = itemTag("seeds/jute");
		public static final TagKey<Item> SEED_COTTON = itemTag("seeds/cotton");
		public static final TagKey<Item> SEED_BLUE_MALLOW = itemTag("seeds/blue_mallow");
		public static final TagKey<Item> SEED_BINDWEED = itemTag("seeds/bindweed");
		public static final TagKey<Item> SEED_WATER_SPINACH = itemTag("seeds/water_spinach");
		public static final TagKey<Item> SEED_SWEET_POTATO = itemTag("seeds/sweet_potato");
		public static final TagKey<Item> SEED_MORNING_GLORY = itemTag("seeds/morning_glory");
		public static final TagKey<Item> SEED_GREEN_PEAS = itemTag("seeds/green_pea");
		public static final TagKey<Item> SEED_GARBANZO = itemTag("seeds/garbanzo");
		public static final TagKey<Item> SEED_SOY = itemTag("seeds/soy");
		public static final TagKey<Item> SEED_ADZUKI = itemTag("seeds/adzuki");
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

		public static final TagKey<Item> FERTILIZER = itemTag("fertilizer");

		public static final TagKey<Item> CRAFT_MORTAR = itemTag("crafting_tools/mortar");
		public static final TagKey<Item> CRAFT_SPINDLE = itemTag("crafting_tools/spindle");

		public static final TagKey<Item> DUMMY = ItemTags.create(new ResourceLocation("dcs_climate", "dummy"));

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
