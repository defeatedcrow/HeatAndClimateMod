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

	public static final Color WHITE = new Color("white", MagicColor.WHITE, () -> CoreInit.ORE_WHITE.get(), () -> CoreInit.ORE_WHITE_DEEP.get(), () -> CoreInit.GEM_CRYSTAL
		.get(), () -> CoreInit.GEM_THUNDEREGG
			.get(), () -> Items.DIAMOND, () -> CoreInit.OREITEM_WHITE1.get(), () -> CoreInit.OREITEM_WHITE2.get(), () -> CoreInit.OREITEM_WHITE3.get(), () -> CoreInit.OREDUST_WHITE1
				.get(), () -> CoreInit.OREDUST_WHITE2.get(), () -> CoreInit.OREDUST_WHITE3.get());

	public static final Color BLUE = new Color("blue", MagicColor.BLUE, () -> CoreInit.ORE_BLUE.get(), () -> CoreInit.ORE_BLUE_DEEP.get(), () -> Items.LAPIS_LAZULI, () -> CoreInit.GEM_CELESTITE
		.get(), () -> CoreInit.GEM_SAPPHIRE.get(), () -> CoreInit.OREITEM_BLUE1.get(), () -> CoreInit.OREITEM_BLUE2.get(), () -> CoreInit.OREITEM_BLUE3.get(), () -> CoreInit.OREDUST_BLUE1
			.get(), () -> CoreInit.OREDUST_BLUE2.get(), () -> CoreInit.OREDUST_BLUE3.get());

	public static final Color BLACK = new Color("black", MagicColor.BLACK, () -> CoreInit.ORE_BLACK.get(), () -> CoreInit.ORE_BLACK_DEEP.get(), () -> CoreInit.GEM_VIVIANITE
		.get(), () -> CoreInit.GEM_FANG
			.get(), () -> CoreInit.GEM_OPAL.get(), () -> CoreInit.OREITEM_BLACK1.get(), () -> CoreInit.OREITEM_BLACK2.get(), () -> CoreInit.OREITEM_BLACK3.get(), () -> CoreInit.OREDUST_BLACK1
				.get(), () -> CoreInit.OREDUST_BLACK2.get(), () -> CoreInit.OREDUST_BLACK3.get());

	public static final Color RED = new Color("red", MagicColor.RED, () -> CoreInit.ORE_RED.get(), () -> CoreInit.ORE_RED_DEEP.get(), () -> CoreInit.GEM_JASPER.get(), () -> CoreInit.GEM_ALMANDINE
		.get(), () -> CoreInit.GEM_RUBY.get(), () -> CoreInit.OREITEM_RED1.get(), () -> CoreInit.OREITEM_RED2.get(), () -> CoreInit.OREITEM_RED3.get(), () -> CoreInit.OREDUST_RED1
			.get(), () -> CoreInit.OREDUST_RED2.get(), () -> CoreInit.OREDUST_RED3.get());

	public static final Color GREEN = new Color("green", MagicColor.GREEN, () -> CoreInit.ORE_GREEN.get(), () -> CoreInit.ORE_GREEN_DEEP.get(), () -> CoreInit.GEM_MALACHITE
		.get(), () -> CoreInit.GEM_OLIVINE.get(), () -> Items.EMERALD, () -> CoreInit.OREITEM_GREEN1.get(), () -> CoreInit.OREITEM_GREEN2.get(), () -> CoreInit.OREITEM_GREEN3
			.get(), () -> CoreInit.OREDUST_GREEN1.get(), () -> CoreInit.OREDUST_GREEN2.get(), () -> CoreInit.OREDUST_GREEN3.get());

	public static final Color[] COLOR_VARIANT = { WHITE, BLUE, BLACK, RED, GREEN };

	public static final Alloy BRASS = new Alloy("brass", Rarity.COMMON, () -> CoreInit.METALBLOCK_BRASS.get(), () -> CoreInit.DUSTBLOCK_BRASS.get(), () -> CoreInit.INGOT_BRASS
		.get(), () -> TagDC.ItemTag.DUST_COPPER, () -> TagDC.ItemTag.DUST_ZINC, () -> TagDC.ItemTag.DUST_ZINC);

	public static final Alloy BRONZE = new Alloy("bronze", Rarity.COMMON, () -> CoreInit.METALBLOCK_BRONZE.get(), () -> CoreInit.DUSTBLOCK_BRONZE.get(), () -> CoreInit.INGOT_BRONZE
		.get(), () -> TagDC.ItemTag.DUST_COPPER, () -> TagDC.ItemTag.DUST_TIN, () -> TagDC.ItemTag.DUST_TIN);

	public static final Alloy NICKEL_SILVER = new Alloy("nickel_silver", Rarity.UNCOMMON, () -> CoreInit.METALBLOCK_NICKEL_SILVER.get(), () -> CoreInit.DUSTBLOCK_NICKEL_SILVER
		.get(), () -> CoreInit.INGOT_NICKEL_SILVER.get(), () -> TagDC.ItemTag.DUST_COPPER, () -> TagDC.ItemTag.DUST_NICKEL, () -> TagDC.ItemTag.DUST_ZINC);

	public static final Alloy STEEL = new Alloy("steel", Rarity.UNCOMMON, () -> CoreInit.METALBLOCK_STEEL.get(), () -> CoreInit.DUSTBLOCK_STEEL.get(), () -> CoreInit.INGOT_STEEL
		.get(), () -> TagDC.ItemTag.DUST_IRON, () -> TagDC.ItemTag.DUST_IRON, () -> TagDC.ItemTag.DUST_COAL);

	public static final Alloy ALUMINUM = new Alloy("aluminum", Rarity.UNCOMMON, () -> CoreInit.METALBLOCK_ALUMINUM.get(), () -> CoreInit.DUSTBLOCK_ALUMINUM.get(), () -> CoreInit.INGOT_ALUMINUM
		.get(), () -> TagDC.ItemTag.DUST_ALUMINUM, () -> TagDC.ItemTag.DUST_ALUMINUM, () -> TagDC.ItemTag.GEM_FLUORITE);

	public static final Alloy SILVER = new Alloy("silver", Rarity.COMMON, () -> CoreInit.METALBLOCK_SILVER.get(), () -> CoreInit.DUSTBLOCK_SILVER.get(), () -> CoreInit.INGOT_SILVER
		.get(), () -> TagDC.ItemTag.DUST_SILVER, () -> TagDC.ItemTag.DUST_SILVER, () -> TagDC.ItemTag.DUST_GOLD);

	public static final Alloy SUS = new Alloy("sus", Rarity.RARE, () -> CoreInit.METALBLOCK_SUS.get(), () -> CoreInit.DUSTBLOCK_SUS.get(), () -> CoreInit.INGOT_SUS
		.get(), () -> TagDC.ItemTag.DUST_IRON, () -> TagDC.ItemTag.DUST_CHROMIUM, () -> TagDC.ItemTag.DUST_NICKEL);

	public static final Alloy TITANIUM = new Alloy("titanium", Rarity.RARE, () -> CoreInit.METALBLOCK_TITANIUM.get(), () -> CoreInit.DUSTBLOCK_TITANIUM.get(), () -> CoreInit.INGOT_TITANIUM
		.get(), () -> TagDC.ItemTag.DUST_TITANIUM, () -> TagDC.ItemTag.DUST_ALUMINUM, () -> TagDC.ItemTag.DUST_CHROMIUM);

	public static final Alloy MAGNET = new Alloy("magnet", Rarity.RARE, () -> CoreInit.METALBLOCK_MAGNET.get(), () -> CoreInit.DUSTBLOCK_MAGNET.get(), () -> CoreInit.INGOT_MAGNET
		.get(), () -> TagDC.ItemTag.DUST_MAGNETITE, () -> TagDC.ItemTag.DUST_COAL, () -> TagDC.ItemTag.GEM_CELESTITE);

	public static final Alloy COBALT = new Alloy("cobalt_alloy", Rarity.RARE, () -> CoreInit.METALBLOCK_COBALT.get(), () -> CoreInit.DUSTBLOCK_COBALT.get(), () -> CoreInit.INGOT_COBALT
		.get(), () -> TagDC.ItemTag.DUST_COBALT, () -> TagDC.ItemTag.DUST_CHROMIUM, () -> TagDC.ItemTag.DUST_TUNGSTEN);

	public static final Alloy HASTELLOY = new Alloy("hastelloy", Rarity.RARE, () -> CoreInit.METALBLOCK_HASTELLOY.get(), () -> CoreInit.DUSTBLOCK_HASTELLOY.get(), () -> CoreInit.INGOT_HASTELLOY
		.get(), () -> TagDC.ItemTag.DUST_NICKEL, () -> TagDC.ItemTag.DUST_CHROMIUM, () -> TagDC.ItemTag.DUST_MOLYBDENUM);

	public static final Alloy BSCCO = new Alloy("bscco", Rarity.RARE, () -> CoreInit.METALBLOCK_BSCCO.get(), () -> CoreInit.DUSTBLOCK_BSCCO.get(), () -> CoreInit.INGOT_BSCCO
		.get(), () -> TagDC.ItemTag.DUST_COPPER, () -> TagDC.ItemTag.DUST_BISMUTH, () -> TagDC.ItemTag.GEM_CELESTITE);

	public static final Alloy[] ALLOY_VARIANT = { BRASS, BRONZE, NICKEL_SILVER, STEEL, ALUMINUM, SILVER, SUS, TITANIUM, MAGNET, COBALT, HASTELLOY, BSCCO };

	public static final Gem CHALCEDONY = new Gem("chalcedony", Rarity.COMMON, MagicColor.WHITE, () -> CoreInit.ORE_CHALCEDONY.get(), () -> CoreInit.GEM_CHALCEDONY.get());
	public static final Gem HELIODOR = new Gem("heliodor", Rarity.UNCOMMON, MagicColor.WHITE, () -> CoreInit.ORE_HELIODOR.get(), () -> CoreInit.GEM_HELIODOR.get());
	public static final Gem TOPAZ = new Gem("topaz", Rarity.RARE, MagicColor.WHITE, () -> CoreInit.ORE_TOPAZ.get(), () -> CoreInit.GEM_TOPAZ.get());
	public static final Gem FLUORITE = new Gem("fluorite", Rarity.COMMON, MagicColor.BLUE, () -> CoreInit.ORE_FLUORITE.get(), () -> CoreInit.GEM_FLUORITE.get());
	public static final Gem LARIMAR = new Gem("larimar", Rarity.UNCOMMON, MagicColor.BLUE, () -> CoreInit.ORE_LARIMAR.get(), () -> CoreInit.GEM_LARIMAR.get());
	public static final Gem AQUAMARINE = new Gem("aquamarine", Rarity.RARE, MagicColor.BLUE, () -> CoreInit.ORE_AQUAMARINE.get(), () -> CoreInit.GEM_AQUAMARINE.get());
	public static final Gem JET = new Gem("jet", Rarity.COMMON, MagicColor.BLACK, () -> CoreInit.ORE_JET.get(), () -> CoreInit.GEM_JET.get());
	public static final Gem IOLITE = new Gem("iolite", Rarity.UNCOMMON, MagicColor.BLACK, () -> CoreInit.ORE_IOLITE.get(), () -> CoreInit.GEM_IOLITE.get());
	public static final Gem OPAL = new Gem("opal", Rarity.RARE, MagicColor.BLACK, () -> CoreInit.ORE_OPAL.get(), () -> CoreInit.GEM_OPAL.get());
	public static final Gem DESERT_ROSE = new Gem("desert_rose", Rarity.COMMON, MagicColor.RED, () -> CoreInit.ORE_DESERTROSE.get(), () -> CoreInit.GEM_DESERTROSE.get());
	public static final Gem ROSINCA = new Gem("rosinca", Rarity.UNCOMMON, MagicColor.RED, () -> CoreInit.ORE_ROSINCA.get(), () -> CoreInit.GEM_ROSINCA.get());
	public static final Gem SPINEL = new Gem("spinel", Rarity.RARE, MagicColor.RED, () -> CoreInit.ORE_SPINEL.get(), () -> CoreInit.GEM_SPINEL.get());
	public static final Gem SERPENTINE = new Gem("serpentine", Rarity.COMMON, MagicColor.GREEN, () -> CoreInit.ORE_SERPENTINE.get(), () -> CoreInit.GEM_SERPENTINE.get());
	public static final Gem AMAZONITE = new Gem("amazonite", Rarity.UNCOMMON, MagicColor.GREEN, () -> CoreInit.ORE_AMAZONITE.get(), () -> CoreInit.GEM_AMAZONITE.get());
	public static final Gem JADEITE = new Gem("jadeite", Rarity.RARE, MagicColor.GREEN, () -> CoreInit.ORE_JADEITE.get(), () -> CoreInit.GEM_JADEITE.get());
	public static final Gem DRAGONSEYE = new Gem("dragonseye", Rarity.RARE, MagicColor.BLACK, () -> CoreInit.ORE_DRAGONSEYE.get(), () -> CoreInit.GEM_DRAGONSEYE.get());

	public static final Gem[] GEM_VARIANT = { CHALCEDONY, HELIODOR, TOPAZ, FLUORITE, LARIMAR, AQUAMARINE, JET, IOLITE, OPAL, DESERT_ROSE, ROSINCA, SPINEL, SERPENTINE, AMAZONITE, JADEITE, DRAGONSEYE };

	public static final Stone MUD = new Stone("mud", () -> CoreInit.STONE_MUD.get(), () -> BuildInit.BRICKS_MUD.get(), () -> BuildInit.PILLAR_MUD.get(), () -> BuildInit.CHISELED_MUD
		.get(), () -> BuildInit.STAIRS_MUD.get(), () -> BuildInit.SLAB_MUD.get(), () -> BuildInit.WALL_MUD.get());
	public static final Stone GYPSUM = new Stone("gypsum", () -> CoreInit.STONE_GYPSUM.get(), () -> BuildInit.BRICKS_GYPSUM.get(), () -> BuildInit.PILLAR_GYPSUM.get(), () -> BuildInit.CHISELED_GYPSUM
		.get(), () -> BuildInit.STAIRS_GYPSUM.get(), () -> BuildInit.SLAB_GYPSUM.get(), () -> BuildInit.WALL_GYPSUM.get());
	public static final Stone SERPENTINE_STONE = new Stone("serpentine", () -> CoreInit.STONE_SERPENTINE.get(), () -> BuildInit.BRICKS_SERPENTINE.get(), () -> BuildInit.PILLAR_SERPENTINE
		.get(), () -> BuildInit.CHISELED_SERPENTINE.get(), () -> BuildInit.STAIRS_SERPENTINE.get(), () -> BuildInit.SLAB_SERPENTINE.get(), () -> BuildInit.WALL_SERPENTINE.get());
	public static final Stone GREISEN = new Stone("greisen", () -> CoreInit.STONE_GREISEN.get(), () -> BuildInit.BRICKS_GREISEN.get(), () -> BuildInit.PILLAR_GREISEN.get(), () -> BuildInit.CHISELED_GREISEN
		.get(), () -> BuildInit.STAIRS_GREISEN.get(), () -> BuildInit.SLAB_GREISEN.get(), () -> BuildInit.WALL_GREISEN.get());
	public static final Stone SKARN = new Stone("skarn", () -> CoreInit.STONE_SKARN.get(), () -> BuildInit.BRICKS_SKARN.get(), () -> BuildInit.PILLAR_SKARN.get(), () -> BuildInit.CHISELED_SKARN
		.get(), () -> BuildInit.STAIRS_SKARN.get(), () -> BuildInit.SLAB_SKARN.get(), () -> BuildInit.WALL_SKARN.get());
	public static final Stone HORNFELS = new Stone("hornfels", () -> CoreInit.STONE_HORNFELS.get(), () -> BuildInit.BRICKS_HORNFELS.get(), () -> BuildInit.PILLAR_HORNFELS.get(), () -> BuildInit.CHISELED_HORNFELS
		.get(), () -> BuildInit.STAIRS_HORNFELS.get(), () -> BuildInit.SLAB_HORNFELS.get(), () -> BuildInit.WALL_HORNFELS.get());
	public static final Stone MARBLE = new Stone("marble", () -> CoreInit.STONE_MARBLE.get(), () -> BuildInit.BRICKS_MARBLE.get(), () -> BuildInit.PILLAR_MARBLE.get(), () -> BuildInit.CHISELED_MARBLE
		.get(), () -> BuildInit.STAIRS_MARBLE.get(), () -> BuildInit.SLAB_MARBLE.get(), () -> BuildInit.WALL_MARBLE.get());
	public static final Stone SCHIST_BLUE = new Stone("schist_blue", () -> CoreInit.STONE_SCHIST_BLUE.get(), () -> BuildInit.BRICKS_SCHIST_BLUE.get(), () -> BuildInit.PILLAR_SCHIST_BLUE
		.get(), () -> BuildInit.CHISELED_SCHIST_BLUE.get(), () -> BuildInit.STAIRS_SCHIST_BLUE.get(), () -> BuildInit.SLAB_SCHIST_BLUE.get(), () -> BuildInit.WALL_SCHIST_BLUE.get());
	public static final Stone SCHIST_NETHER = new Stone("schist_nether", () -> CoreInit.STONE_SCHIST_NETHER.get(), () -> BuildInit.BRICKS_SCHIST_NETHER.get(), () -> BuildInit.PILLAR_SCHIST_NETHER
		.get(), () -> BuildInit.CHISELED_SCHIST_NETHER.get(), () -> BuildInit.STAIRS_SCHIST_NETHER.get(), () -> BuildInit.SLAB_SCHIST_NETHER.get(), () -> BuildInit.WALL_SCHIST_NETHER.get());
	public static final Stone GRANITE = new Stone("granite", () -> CoreInit.STONE_GRANITE.get(), () -> BuildInit.BRICKS_GRANITE.get(), () -> BuildInit.PILLAR_GRANITE.get(), () -> BuildInit.CHISELED_GRANITE
		.get(), () -> BuildInit.STAIRS_GRANITE.get(), () -> BuildInit.SLAB_GRANITE.get(), () -> BuildInit.WALL_GRANITE.get());

	public static final Stone[] STONE_VARIANT = { MUD, GYPSUM, SERPENTINE_STONE, GREISEN, SKARN, HORNFELS, MARBLE, SCHIST_BLUE, SCHIST_NETHER, GRANITE };

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

	public static Optional<TagKey<Item>> getTagDC(Item item) {
		if (item instanceof IItemDC) {
			return Optional.of(((IItemDC) item).getPairTag());
		}
		return Optional.empty();
	}
}
