package defeatedcrow.hac.core.recipe;

import java.util.Optional;
import java.util.function.Supplier;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

public class MaterialRecipes {

	public static void init() {}

	public static final Color WHITE = new Color("white", MagicColor.WHITE, CoreInit.ORE_WHITE, CoreInit.ORE_WHITE_DEEP, CoreInit.GEM_CRYSTAL, CoreInit.GEM_THUNDEREGG, CoreInit.GEM_CATSEYE,
			CoreInit.OREITEM_WHITE1, CoreInit.OREITEM_WHITE2, CoreInit.OREITEM_WHITE3, CoreInit.OREDUST_WHITE1, CoreInit.OREDUST_WHITE2, CoreInit.OREDUST_WHITE3);

	public static final Color BLUE = new Color("blue", MagicColor.BLUE, CoreInit.ORE_BLUE, CoreInit.ORE_BLUE_DEEP, () -> Items.LAPIS_LAZULI, CoreInit.GEM_CELESTITE, CoreInit.GEM_SAPPHIRE,
			CoreInit.OREITEM_BLUE1, CoreInit.OREITEM_BLUE2, CoreInit.OREITEM_BLUE3, CoreInit.OREDUST_BLUE1, CoreInit.OREDUST_BLUE2, CoreInit.OREDUST_BLUE3);

	public static final Color BLACK = new Color("black", MagicColor.BLACK, CoreInit.ORE_BLACK, CoreInit.ORE_BLACK_DEEP, CoreInit.GEM_VIVIANITE, CoreInit.GEM_FANG, CoreInit.GEM_OPAL,
			CoreInit.OREITEM_BLACK1, CoreInit.OREITEM_BLACK2, CoreInit.OREITEM_BLACK3,
			CoreInit.OREDUST_BLACK1, CoreInit.OREDUST_BLACK2, CoreInit.OREDUST_BLACK3);

	public static final Color RED = new Color("red", MagicColor.RED, CoreInit.ORE_RED, CoreInit.ORE_RED_DEEP, CoreInit.GEM_JASPER, CoreInit.GEM_ALMANDINE, CoreInit.GEM_RUBY, CoreInit.OREITEM_RED1,
			CoreInit.OREITEM_RED2, CoreInit.OREITEM_RED3, CoreInit.OREDUST_RED1, CoreInit.OREDUST_RED2, CoreInit.OREDUST_RED3);

	public static final Color GREEN = new Color("green", MagicColor.GREEN, CoreInit.ORE_GREEN, CoreInit.ORE_GREEN_DEEP, CoreInit.GEM_MALACHITE, CoreInit.GEM_OLIVINE, CoreInit.GEM_DEMANTOID,
			CoreInit.OREITEM_GREEN1, CoreInit.OREITEM_GREEN2, CoreInit.OREITEM_GREEN3, CoreInit.OREDUST_GREEN1, CoreInit.OREDUST_GREEN2, CoreInit.OREDUST_GREEN3);

	public static final Color[] COLOR_VARIANT = { WHITE, BLUE, BLACK, RED, GREEN };

	public static final Alloy BRASS = new Alloy("brass", Rarity.COMMON, CoreInit.METALBLOCK_BRASS, CoreInit.DUSTBLOCK_BRASS, CoreInit.INGOT_BRASS,
			() -> TagDC.ItemTag.DUST_COPPER, () -> TagDC.ItemTag.DUST_ZINC, () -> TagDC.ItemTag.DUST_ZINC);

	public static final Alloy BRONZE = new Alloy("bronze", Rarity.COMMON, CoreInit.METALBLOCK_BRONZE, CoreInit.DUSTBLOCK_BRONZE, CoreInit.INGOT_BRONZE,
			() -> TagDC.ItemTag.DUST_COPPER,
			() -> TagDC.ItemTag.DUST_TIN, () -> TagDC.ItemTag.DUST_TIN);

	public static final Alloy NICKEL_SILVER = new Alloy("nickel_silver", Rarity.UNCOMMON, CoreInit.METALBLOCK_NICKEL_SILVER, CoreInit.DUSTBLOCK_NICKEL_SILVER, CoreInit.INGOT_NICKEL_SILVER,
			() -> TagDC.ItemTag.DUST_COPPER, () -> TagDC.ItemTag.DUST_NICKEL, () -> TagDC.ItemTag.DUST_ZINC);

	public static final Alloy STEEL = new Alloy("steel", Rarity.UNCOMMON, CoreInit.METALBLOCK_STEEL, CoreInit.DUSTBLOCK_STEEL, CoreInit.INGOT_STEEL,
			() -> TagDC.ItemTag.DUST_IRON, () -> TagDC.ItemTag.DUST_IRON, () -> TagDC.ItemTag.DUST_COAL);

	public static final Alloy ALUMINUM = new Alloy("aluminum", Rarity.UNCOMMON, CoreInit.METALBLOCK_ALUMINUM, CoreInit.DUSTBLOCK_ALUMINUM, CoreInit.INGOT_ALUMINUM,
			() -> TagDC.ItemTag.DUST_ALUMINUM, () -> TagDC.ItemTag.DUST_ALUMINUM, () -> TagDC.ItemTag.GEM_FLUORITE);

	public static final Alloy SILVER = new Alloy("silver", Rarity.COMMON, CoreInit.METALBLOCK_SILVER, CoreInit.DUSTBLOCK_SILVER, CoreInit.INGOT_SILVER,
			() -> TagDC.ItemTag.DUST_SILVER, () -> TagDC.ItemTag.DUST_SILVER, () -> TagDC.ItemTag.DUST_GOLD);

	public static final Alloy SUS = new Alloy("sus", Rarity.RARE, CoreInit.METALBLOCK_SUS, CoreInit.DUSTBLOCK_SUS, CoreInit.INGOT_SUS,
			() -> TagDC.ItemTag.DUST_IRON, () -> TagDC.ItemTag.DUST_CHROMIUM, () -> TagDC.ItemTag.DUST_NICKEL);

	public static final Alloy TITANIUM = new Alloy("titanium", Rarity.RARE, CoreInit.METALBLOCK_TITANIUM, CoreInit.DUSTBLOCK_TITANIUM, CoreInit.INGOT_TITANIUM,
			() -> TagDC.ItemTag.DUST_TITANIUM, () -> TagDC.ItemTag.DUST_ALUMINUM, () -> TagDC.ItemTag.DUST_CHROMIUM);

	public static final Alloy MAGNET = new Alloy("magnet", Rarity.RARE, CoreInit.METALBLOCK_MAGNET, CoreInit.DUSTBLOCK_MAGNET, CoreInit.INGOT_MAGNET,
			() -> TagDC.ItemTag.DUST_MAGNETITE, () -> TagDC.ItemTag.DUST_COAL, () -> TagDC.ItemTag.GEM_CELESTITE);

	public static final Alloy COBALT = new Alloy("cobalt_alloy", Rarity.RARE, CoreInit.METALBLOCK_COBALT, CoreInit.DUSTBLOCK_COBALT, CoreInit.INGOT_COBALT,
			() -> TagDC.ItemTag.DUST_COBALT, () -> TagDC.ItemTag.DUST_CHROMIUM, () -> TagDC.ItemTag.DUST_TUNGSTEN);

	public static final Alloy HASTELLOY = new Alloy("hastelloy", Rarity.RARE, CoreInit.METALBLOCK_HASTELLOY, CoreInit.DUSTBLOCK_HASTELLOY, CoreInit.INGOT_HASTELLOY,
			() -> TagDC.ItemTag.DUST_NICKEL, () -> TagDC.ItemTag.DUST_CHROMIUM, () -> TagDC.ItemTag.DUST_MOLYBDENUM);

	public static final Alloy BSCCO = new Alloy("bscco", Rarity.RARE, CoreInit.METALBLOCK_BSCCO, CoreInit.DUSTBLOCK_BSCCO, CoreInit.INGOT_BSCCO,
			() -> TagDC.ItemTag.DUST_COPPER, () -> TagDC.ItemTag.DUST_BISMUTH, () -> TagDC.ItemTag.GEM_CELESTITE);

	public static final Alloy[] ALLOY_VARIANT = { BRASS, BRONZE, NICKEL_SILVER, STEEL, ALUMINUM, SILVER, SUS, TITANIUM, MAGNET, COBALT, HASTELLOY, BSCCO };

	public static final Gem CHALCEDONY = new Gem("chalcedony", Rarity.COMMON, MagicColor.WHITE, CoreInit.ORE_CHALCEDONY, CoreInit.GEM_CHALCEDONY);
	public static final Gem HELIODOR = new Gem("heliodor", Rarity.UNCOMMON, MagicColor.WHITE, CoreInit.ORE_HELIODOR, CoreInit.GEM_HELIODOR);
	public static final Gem TOPAZ = new Gem("topaz", Rarity.RARE, MagicColor.WHITE, CoreInit.ORE_TOPAZ, CoreInit.GEM_TOPAZ);
	public static final Gem FLUORITE = new Gem("fluorite", Rarity.COMMON, MagicColor.BLUE, CoreInit.ORE_FLUORITE, CoreInit.GEM_FLUORITE);
	public static final Gem LARIMAR = new Gem("larimar", Rarity.UNCOMMON, MagicColor.BLUE, CoreInit.ORE_LARIMAR, CoreInit.GEM_LARIMAR);
	public static final Gem AQUAMARINE = new Gem("aquamarine", Rarity.RARE, MagicColor.BLUE, CoreInit.ORE_AQUAMARINE, CoreInit.GEM_AQUAMARINE);
	public static final Gem JET = new Gem("jet", Rarity.COMMON, MagicColor.BLACK, CoreInit.ORE_JET, CoreInit.GEM_JET);
	public static final Gem IOLITE = new Gem("iolite", Rarity.UNCOMMON, MagicColor.BLACK, CoreInit.ORE_IOLITE, CoreInit.GEM_IOLITE);
	public static final Gem OPAL = new Gem("opal", Rarity.RARE, MagicColor.BLACK, CoreInit.ORE_OPAL, CoreInit.GEM_OPAL);
	public static final Gem DESERT_ROSE = new Gem("desert_rose", Rarity.COMMON, MagicColor.RED, CoreInit.ORE_DESERTROSE, CoreInit.GEM_DESERTROSE);
	public static final Gem ROSINCA = new Gem("rosinca", Rarity.UNCOMMON, MagicColor.RED, CoreInit.ORE_ROSINCA, CoreInit.GEM_ROSINCA);
	public static final Gem SPINEL = new Gem("spinel", Rarity.RARE, MagicColor.RED, CoreInit.ORE_SPINEL, CoreInit.GEM_SPINEL);
	public static final Gem SERPENTINE = new Gem("serpentine", Rarity.COMMON, MagicColor.GREEN, CoreInit.ORE_SERPENTINE, CoreInit.GEM_SERPENTINE);
	public static final Gem AMAZONITE = new Gem("amazonite", Rarity.UNCOMMON, MagicColor.GREEN, CoreInit.ORE_AMAZONITE, CoreInit.GEM_AMAZONITE);
	public static final Gem JADEITE = new Gem("jadeite", Rarity.RARE, MagicColor.GREEN, CoreInit.ORE_JADEITE, CoreInit.GEM_JADEITE);
	public static final Gem DRAGONSEYE = new Gem("dragonseye", Rarity.RARE, MagicColor.BLACK, CoreInit.ORE_DRAGONSEYE, CoreInit.GEM_DRAGONSEYE);

	public static final Gem[] GEM_VARIANT = { CHALCEDONY, HELIODOR, TOPAZ, FLUORITE, LARIMAR, AQUAMARINE, JET, IOLITE, OPAL, DESERT_ROSE, ROSINCA, SPINEL, SERPENTINE, AMAZONITE, JADEITE, DRAGONSEYE };

	public static final Stone MUD = new Stone("mud", CoreInit.STONE_MUD, BuildInit.BRICKS_MUD, BuildInit.PILLAR_MUD, BuildInit.CHISELED_MUD, BuildInit.STAIRS_MUD, BuildInit.SLAB_MUD,
			BuildInit.WALL_MUD);
	public static final Stone GYPSUM = new Stone("gypsum", CoreInit.STONE_GYPSUM, BuildInit.BRICKS_GYPSUM, BuildInit.PILLAR_GYPSUM, BuildInit.CHISELED_GYPSUM, BuildInit.STAIRS_GYPSUM,
			BuildInit.SLAB_GYPSUM, BuildInit.WALL_GYPSUM);
	public static final Stone SERPENTINE_STONE = new Stone("serpentine", CoreInit.STONE_SERPENTINE, BuildInit.BRICKS_SERPENTINE, BuildInit.PILLAR_SERPENTINE, BuildInit.CHISELED_SERPENTINE,
			BuildInit.STAIRS_SERPENTINE, BuildInit.SLAB_SERPENTINE, BuildInit.WALL_SERPENTINE);
	public static final Stone GREISEN = new Stone("greisen", CoreInit.STONE_GREISEN, BuildInit.BRICKS_GREISEN, BuildInit.PILLAR_GREISEN, BuildInit.CHISELED_GREISEN, BuildInit.STAIRS_GREISEN,
			BuildInit.SLAB_GREISEN, BuildInit.WALL_GREISEN);
	public static final Stone SKARN = new Stone("skarn", CoreInit.STONE_SKARN, BuildInit.BRICKS_SKARN, BuildInit.PILLAR_SKARN, BuildInit.CHISELED_SKARN, BuildInit.STAIRS_SKARN, BuildInit.SLAB_SKARN,
			BuildInit.WALL_SKARN);
	public static final Stone HORNFELS = new Stone("hornfels", CoreInit.STONE_HORNFELS, BuildInit.BRICKS_HORNFELS, BuildInit.PILLAR_HORNFELS, BuildInit.CHISELED_HORNFELS, BuildInit.STAIRS_HORNFELS,
			BuildInit.SLAB_HORNFELS, BuildInit.WALL_HORNFELS);
	public static final Stone MARBLE = new Stone("marble", CoreInit.STONE_MARBLE, BuildInit.BRICKS_MARBLE, BuildInit.PILLAR_MARBLE, BuildInit.CHISELED_MARBLE, BuildInit.STAIRS_MARBLE,
			BuildInit.SLAB_MARBLE, BuildInit.WALL_MARBLE);
	public static final Stone SCHIST_BLUE = new Stone("schist_blue", CoreInit.STONE_SCHIST_BLUE, BuildInit.BRICKS_SCHIST_BLUE, BuildInit.PILLAR_SCHIST_BLUE, BuildInit.CHISELED_SCHIST_BLUE,
			BuildInit.STAIRS_SCHIST_BLUE, BuildInit.SLAB_SCHIST_BLUE, BuildInit.WALL_SCHIST_BLUE);
	public static final Stone SCHIST_NETHER = new Stone("schist_nether", CoreInit.STONE_SCHIST_NETHER, BuildInit.BRICKS_SCHIST_NETHER, BuildInit.PILLAR_SCHIST_NETHER, BuildInit.CHISELED_SCHIST_NETHER,
			BuildInit.STAIRS_SCHIST_NETHER, BuildInit.SLAB_SCHIST_NETHER, BuildInit.WALL_SCHIST_NETHER);
	public static final Stone GRANITE = new Stone("granite", CoreInit.STONE_GRANITE, BuildInit.BRICKS_GRANITE, BuildInit.PILLAR_GRANITE, BuildInit.CHISELED_GRANITE, BuildInit.STAIRS_GRANITE,
			BuildInit.SLAB_GRANITE, BuildInit.WALL_GRANITE);
	public static final Stone MORTAR = new Stone("mortar", BuildInit.MORTAR, BuildInit.BRICKS_MORTAR, BuildInit.PILLAR_MORTAR, BuildInit.CHISELED_MORTAR, BuildInit.STAIRS_MORTAR,
			BuildInit.SLAB_MORTAR, BuildInit.WALL_MORTAR);

	public static final Stone[] STONE_VARIANT = { MUD, GYPSUM, SERPENTINE_STONE, GREISEN, SKARN, HORNFELS, MARBLE, SCHIST_BLUE, SCHIST_NETHER, GRANITE, MORTAR };

	public static final GemBlock B_CHALCEDONY = new GemBlock("chalcedony", CoreInit.GEMBLOCK_CHALCEDONY, CoreInit.GEM_CHALCEDONY, () -> TagDC.ItemTag.GEM_CHALCEDONY);
	public static final GemBlock B_HELIODOR = new GemBlock("heliodor", CoreInit.GEMBLOCK_HELIODOR, CoreInit.GEM_HELIODOR, () -> TagDC.ItemTag.GEM_HELIODOR);
	public static final GemBlock B_TOPAZ = new GemBlock("topaz", CoreInit.GEMBLOCK_TOPAZ, CoreInit.GEM_TOPAZ, () -> TagDC.ItemTag.GEM_TOPAZ);
	public static final GemBlock B_FLUORITE = new GemBlock("fluorite", CoreInit.GEMBLOCK_FLUORITE, CoreInit.GEM_FLUORITE, () -> TagDC.ItemTag.GEM_FLUORITE);
	public static final GemBlock B_LARIMAR = new GemBlock("larimar", CoreInit.GEMBLOCK_LARIMAR, CoreInit.GEM_LARIMAR, () -> TagDC.ItemTag.GEM_LARIMAR);
	public static final GemBlock B_AQUAMARINE = new GemBlock("aquamarine", CoreInit.GEMBLOCK_AQUAMARINE, CoreInit.GEM_AQUAMARINE, () -> TagDC.ItemTag.GEM_AQUAMARINE);
	public static final GemBlock B_JET = new GemBlock("jet", CoreInit.GEMBLOCK_JET, CoreInit.GEM_JET, () -> TagDC.ItemTag.GEM_JET);
	public static final GemBlock B_IOLITE = new GemBlock("iolite", CoreInit.GEMBLOCK_IOLITE, CoreInit.GEM_IOLITE, () -> TagDC.ItemTag.GEM_IOLITE);
	public static final GemBlock B_SAKURA = new GemBlock("sakura", CoreInit.GEMBLOCK_SAKURA, CoreInit.GEM_SAKURA, () -> TagDC.ItemTag.GEM_SAKURA);
	public static final GemBlock B_OPAL = new GemBlock("opal", CoreInit.GEMBLOCK_OPAL, CoreInit.GEM_OPAL, () -> TagDC.ItemTag.GEM_OPAL);
	public static final GemBlock B_DESERT_ROSE = new GemBlock("desert_rose", CoreInit.GEMBLOCK_DESERTROSE, CoreInit.GEM_DESERTROSE, () -> TagDC.ItemTag.GEM_DESERTROSE);
	public static final GemBlock B_ROSINCA = new GemBlock("rosinca", CoreInit.GEMBLOCK_ROSINCA, CoreInit.GEM_ROSINCA, () -> TagDC.ItemTag.GEM_ROSINCA);
	public static final GemBlock B_SPINEL = new GemBlock("spinel", CoreInit.GEMBLOCK_SPINEL, CoreInit.GEM_SPINEL, () -> TagDC.ItemTag.GEM_SPINEL);
	public static final GemBlock B_SERPENTINE = new GemBlock("serpentine", CoreInit.GEMBLOCK_SERPENTINE, CoreInit.GEM_SERPENTINE, () -> TagDC.ItemTag.GEM_SERPENTINE);
	public static final GemBlock B_AMAZONITE = new GemBlock("amazonite", CoreInit.GEMBLOCK_AMAZONITE, CoreInit.GEM_AMAZONITE, () -> TagDC.ItemTag.GEM_AMAZONITE);
	public static final GemBlock B_JADEITE = new GemBlock("jadeite", CoreInit.GEMBLOCK_JADEITE, CoreInit.GEM_JADEITE, () -> TagDC.ItemTag.GEM_JADEITE);
	public static final GemBlock B_DRAGONSEYE = new GemBlock("dragonseye", CoreInit.GEMBLOCK_DRAGONSEYE, CoreInit.GEM_DRAGONSEYE, () -> TagDC.ItemTag.GEM_DRAGONSEYE);
	public static final GemBlock B_CRYSTAL = new GemBlock("crystal", CoreInit.GEMBLOCK_CRYSTAL, CoreInit.GEM_CRYSTAL, () -> TagDC.ItemTag.GEM_CRYSTAL);
	public static final GemBlock B_THUNDEREGG = new GemBlock("thunderegg", CoreInit.GEMBLOCK_THUNDEREGG, CoreInit.GEM_THUNDEREGG, () -> TagDC.ItemTag.GEM_THUNDEREGG);
	public static final GemBlock B_CATSEYE = new GemBlock("catseye", CoreInit.GEMBLOCK_CATSEYE, CoreInit.GEM_CATSEYE, () -> TagDC.ItemTag.GEM_CATSEYE);
	public static final GemBlock B_CELESTITE = new GemBlock("celestite", CoreInit.GEMBLOCK_CELESTITE, CoreInit.GEM_CELESTITE, () -> TagDC.ItemTag.GEM_CELESTITE);
	public static final GemBlock B_SAPPHIRE = new GemBlock("sapphire", CoreInit.GEMBLOCK_SAPPHIRE, CoreInit.GEM_SAPPHIRE, () -> TagDC.ItemTag.GEM_SAPPHIRE);
	public static final GemBlock B_VIVIANITE = new GemBlock("vivianite", CoreInit.GEMBLOCK_VIVIANITE, CoreInit.GEM_VIVIANITE, () -> TagDC.ItemTag.GEM_VIVIANITE);
	public static final GemBlock B_FANG = new GemBlock("fang", CoreInit.GEMBLOCK_FANG, CoreInit.GEM_FANG, () -> TagDC.ItemTag.GEM_FANG);
	public static final GemBlock B_KUNZITE = new GemBlock("kunzite", CoreInit.GEMBLOCK_KUNZITE, CoreInit.GEM_KUNZITE, () -> TagDC.ItemTag.GEM_KUNZITE);
	public static final GemBlock B_JASPER = new GemBlock("jasper", CoreInit.GEMBLOCK_JASPER, CoreInit.GEM_JASPER, () -> TagDC.ItemTag.GEM_JASPER);
	public static final GemBlock B_ALMANDINE = new GemBlock("almandine", CoreInit.GEMBLOCK_ALMANDINE, CoreInit.GEM_ALMANDINE, () -> TagDC.ItemTag.GEM_ALMANDINE);
	public static final GemBlock B_RUBY = new GemBlock("ruby", CoreInit.GEMBLOCK_RUBY, CoreInit.GEM_RUBY, () -> TagDC.ItemTag.GEM_RUBY);
	public static final GemBlock B_MALACHITE = new GemBlock("malachite", CoreInit.GEMBLOCK_MALACHITE, CoreInit.GEM_MALACHITE, () -> TagDC.ItemTag.GEM_MALACHITE);
	public static final GemBlock B_OLIVINE = new GemBlock("olivine", CoreInit.GEMBLOCK_OLIVINE, CoreInit.GEM_OLIVINE, () -> TagDC.ItemTag.GEM_OLIVINE);
	public static final GemBlock B_DEMANTOID = new GemBlock("demantoid", CoreInit.GEMBLOCK_DEMANTOID, CoreInit.GEM_DEMANTOID, () -> TagDC.ItemTag.GEM_DEMANTOID);

	public static final GemBlock[] GEMBLOCK_VARIANT = { B_CHALCEDONY, B_HELIODOR, B_TOPAZ, B_FLUORITE, B_LARIMAR, B_AQUAMARINE, B_JET, B_IOLITE, B_SAKURA, B_OPAL,
		B_DRAGONSEYE, B_DESERT_ROSE, B_ROSINCA, B_SPINEL, B_SERPENTINE, B_AMAZONITE, B_JADEITE, B_CRYSTAL, B_THUNDEREGG, B_CATSEYE,
		B_CELESTITE, B_SAPPHIRE, B_VIVIANITE, B_FANG, B_KUNZITE, B_JASPER, B_ALMANDINE, B_RUBY, B_MALACHITE, B_OLIVINE, B_DEMANTOID };

	public record Color(
			String name,
			MagicColor color,
			Supplier<Block> block,
			Supplier<Block> blockDeep,
			Supplier<Item> gemPri,
			Supplier<Item> gemSec,
			Supplier<Item> gemTert,
			Supplier<Item> orePri,
			Supplier<Item> oreSec,
			Supplier<Item> oreTert,
			Supplier<Item> dustPri,
			Supplier<Item> dustSec,
			Supplier<Item> dustTert) {

		public TagKey<Item> getTag(String str) {
			return ItemTags.create(new ResourceLocation("forge", str + "/" + name));
		}

	}

	public record Alloy(
			String name, Rarity rarity,
			Supplier<Block> metalBlock,
			Supplier<Block> dustBlock,
			Supplier<Item> ingotItem,
			Supplier<TagKey<Item>> dustPrimary,
			Supplier<TagKey<Item>> dustSecondary,
			Supplier<TagKey<Item>> dustTertiary) {

		public TagKey<Item> getTag(String str) {
			return ItemTags.create(new ResourceLocation("forge", str + "/" + name));
		}
	}

	public record Gem(
			String name, Rarity rarity,
			MagicColor color,
			Supplier<Block> ore,
			Supplier<Item> gem) {};

	public record Stone(
			String name,
			Supplier<Block> stoneBlock,
			Supplier<Block> bricksBlock,
			Supplier<Block> pillarBlock,
			Supplier<Block> chiseledBlock,
			Supplier<Block> stairsBlock,
			Supplier<Block> slabBlock,
			Supplier<Block> wallBlock) {}

	public record GemBlock(
			String name,
			Supplier<Block> block,
			Supplier<Item> gem,
			Supplier<TagKey<Item>> tag) {};

	public static Optional<TagKey<Item>> getTagDC(Item item) {
		if (item instanceof IItemDC) {
			return Optional.of(((IItemDC) item).getPairTag());
		}
		return Optional.empty();
	}
}
