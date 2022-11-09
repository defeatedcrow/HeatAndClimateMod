package defeatedcrow.hac.core.material;

import java.util.function.Supplier;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.block.AlloyDustBlockDC;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.block.LayerStoneBlock;
import defeatedcrow.hac.core.material.block.MetalBlockDC;
import defeatedcrow.hac.core.material.block.OreBlockGemDC;
import defeatedcrow.hac.core.material.item.ItemGemDC;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.material.item.MetalItemDC;
import defeatedcrow.hac.core.material.item.NullItemDC;
import defeatedcrow.hac.core.material.item.tool.AgateMortarItem;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Building;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Machine;
import defeatedcrow.hac.core.material.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CoreInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ClimateCore.MOD_ID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ClimateCore.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ClimateCore.MOD_ID);

	public static final CreativeModeTab CORE = new CreativeTabClimate("core");
	public static final CreativeModeTab BUILD = new CreativeTabClimate_Building("build");
	public static final CreativeModeTab MACHINE = new CreativeTabClimate_Machine("machine");

	public static void init() {
		FoodInit.init();
		BuildInit.init();
	}

	// item
	public static final RegistryObject<Item> OREITEM_WHITE1 = regItem("oreitem_white_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_white_1", Tags.Items.RAW_MATERIALS_COPPER));
	public static final RegistryObject<Item> OREITEM_WHITE2 = regItem("oreitem_white_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_white_2", Tags.Items.RAW_MATERIALS_GOLD));
	public static final RegistryObject<Item> OREITEM_WHITE3 = regItem("oreitem_white_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_white_3", TagDC.ItemTag.RAW_MOLYBDENUM));
	public static final RegistryObject<Item> OREITEM_BLUE1 = regItem("oreitem_blue_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_blue_1", TagDC.ItemTag.RAW_ZINC));
	public static final RegistryObject<Item> OREITEM_BLUE2 = regItem("oreitem_blue_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_blue_2", TagDC.ItemTag.RAW_BISMUTH));
	public static final RegistryObject<Item> OREITEM_BLUE3 = regItem("oreitem_blue_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_blue_3", TagDC.ItemTag.RAW_COBALT));
	public static final RegistryObject<Item> OREITEM_BLACK1 = regItem("oreitem_black_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_black_1", TagDC.ItemTag.RAW_MAGNETITE));
	public static final RegistryObject<Item> OREITEM_BLACK2 = regItem("oreitem_black_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_black_2", TagDC.ItemTag.RAW_SILVER));
	public static final RegistryObject<Item> OREITEM_BLACK3 = regItem("oreitem_black_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_black_3", TagDC.ItemTag.RAW_TITANIUM));
	public static final RegistryObject<Item> OREITEM_RED1 = regItem("oreitem_red_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_red_1", Tags.Items.RAW_MATERIALS_IRON));
	public static final RegistryObject<Item> OREITEM_RED2 = regItem("oreitem_red_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_red_2", TagDC.ItemTag.RAW_ALUMINUM));
	public static final RegistryObject<Item> OREITEM_RED3 = regItem("oreitem_red_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_red_3", TagDC.ItemTag.RAW_MANGANESE));
	public static final RegistryObject<Item> OREITEM_GREEN1 = regItem("oreitem_green_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_green_1", TagDC.ItemTag.RAW_TIN));
	public static final RegistryObject<Item> OREITEM_GREEN2 = regItem("oreitem_green_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_green_2", TagDC.ItemTag.RAW_NICKEL));
	public static final RegistryObject<Item> OREITEM_GREEN3 = regItem("oreitem_green_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_green_3", TagDC.ItemTag.RAW_CHROMIUM));

	public static final RegistryObject<Item> OREDUST_WHITE1 = regItem("oredust_white_1", () -> new MetalItemDC(Rarity.COMMON, "oredust_white_1", TagDC.ItemTag.DUST_COPPER));
	public static final RegistryObject<Item> OREDUST_WHITE2 = regItem("oredust_white_2", () -> new MetalItemDC(Rarity.UNCOMMON, "oredust_white_2", TagDC.ItemTag.DUST_GOLD));
	public static final RegistryObject<Item> OREDUST_WHITE3 = regItem("oredust_white_3", () -> new MetalItemDC(Rarity.RARE, "oredust_white_3", TagDC.ItemTag.DUST_MOLYBDENUM));
	public static final RegistryObject<Item> OREDUST_BLUE1 = regItem("oredust_blue_1", () -> new MetalItemDC(Rarity.COMMON, "oredust_blue_1", TagDC.ItemTag.DUST_ZINC));
	public static final RegistryObject<Item> OREDUST_BLUE2 = regItem("oredust_blue_2", () -> new MetalItemDC(Rarity.UNCOMMON, "oredust_blue_2", TagDC.ItemTag.DUST_BISMUTH));
	public static final RegistryObject<Item> OREDUST_BLUE3 = regItem("oredust_blue_3", () -> new MetalItemDC(Rarity.RARE, "oredust_blue_3", TagDC.ItemTag.DUST_COBALT));
	public static final RegistryObject<Item> OREDUST_BLACK1 = regItem("oredust_black_1", () -> new MetalItemDC(Rarity.COMMON, "oredust_black_1", TagDC.ItemTag.DUST_MAGNETITE));
	public static final RegistryObject<Item> OREDUST_BLACK2 = regItem("oredust_black_2", () -> new MetalItemDC(Rarity.UNCOMMON, "oredust_black_2", TagDC.ItemTag.DUST_SILVER));
	public static final RegistryObject<Item> OREDUST_BLACK3 = regItem("oredust_black_3", () -> new MetalItemDC(Rarity.RARE, "oredust_black_3", TagDC.ItemTag.RAW_TITANIUM));
	public static final RegistryObject<Item> OREDUST_RED1 = regItem("oredust_red_1", () -> new MetalItemDC(Rarity.COMMON, "oredust_red_1", TagDC.ItemTag.DUST_IRON));
	public static final RegistryObject<Item> OREDUST_RED2 = regItem("oredust_red_2", () -> new MetalItemDC(Rarity.UNCOMMON, "oredust_red_2", TagDC.ItemTag.DUST_ALUMINUM));
	public static final RegistryObject<Item> OREDUST_RED3 = regItem("oredust_red_3", () -> new MetalItemDC(Rarity.RARE, "oredust_red_3", TagDC.ItemTag.DUST_MANGANESE));
	public static final RegistryObject<Item> OREDUST_GREEN1 = regItem("oredust_green_1", () -> new MetalItemDC(Rarity.COMMON, "oredust_green_1", TagDC.ItemTag.DUST_TIN));
	public static final RegistryObject<Item> OREDUST_GREEN2 = regItem("oredust_green_2", () -> new MetalItemDC(Rarity.UNCOMMON, "oredust_green_2", TagDC.ItemTag.DUST_NICKEL));
	public static final RegistryObject<Item> OREDUST_GREEN3 = regItem("oredust_green_3", () -> new MetalItemDC(Rarity.RARE, "oredust_green_3", TagDC.ItemTag.DUST_CHROMIUM));

	// public static final RegistryObject<Item> INGOT_WHITE1 = regItem("ingot_white_1", () -> new MetalItemDC(Rarity.COMMON, "ingot_white_1", TagDC.ItemTag.INGOT_COPPER));
	// public static final RegistryObject<Item> INGOT_WHITE2 = regItem("ingot_white_2", () -> new MetalItemDC(Rarity.UNCOMMON, "ingot_white_2", TagDC.ItemTag.INGOT_GOLD));
	// public static final RegistryObject<Item> INGOT_WHITE3 = regItem("ingot_white_3", () -> new MetalItemDC(Rarity.RARE, "ingot_white_3", TagDC.ItemTag.INGOT_MOLYBDENUM));
	// public static final RegistryObject<Item> INGOT_BLUE1 = regItem("ingot_blue_1", () -> new MetalItemDC(Rarity.COMMON, "ingot_blue_1", TagDC.ItemTag.INGOT_ZINC));
	// public static final RegistryObject<Item> INGOT_BLUE2 = regItem("ingot_blue_2", () -> new MetalItemDC(Rarity.UNCOMMON, "ingot_blue_2", TagDC.ItemTag.INGOT_BISMUTH));
	// public static final RegistryObject<Item> INGOT_BLUE3 = regItem("ingot_blue_3", () -> new MetalItemDC(Rarity.RARE, "ingot_blue_3", TagDC.ItemTag.INGOT_COBALT));
	// public static final RegistryObject<Item> INGOT_BLACK1 = regItem("ingot_black_1", () -> new MetalItemDC(Rarity.COMMON, "ingot_black_1", TagDC.ItemTag.INGOT_MAGNETITE));
	// public static final RegistryObject<Item> INGOT_BLACK2 = regItem("ingot_black_2", () -> new MetalItemDC(Rarity.UNCOMMON, "ingot_black_2", TagDC.ItemTag.INGOT_SILVER));
	// public static final RegistryObject<Item> INGOT_BLACK3 = regItem("ingot_black_3", () -> new MetalItemDC(Rarity.RARE, "ingot_black_3", TagDC.ItemTag.INGOT_TITANIUM));
	// public static final RegistryObject<Item> INGOT_RED1 = regItem("ingot_red_1", () -> new MetalItemDC(Rarity.COMMON, "ingot_red_1", TagDC.ItemTag.INGOT_IRON));
	// public static final RegistryObject<Item> INGOT_RED2 = regItem("ingot_red_2", () -> new MetalItemDC(Rarity.UNCOMMON, "ingot_red_2", TagDC.ItemTag.INGOT_ALUMINUM));
	// public static final RegistryObject<Item> INGOT_RED3 = regItem("ingot_red_3", () -> new MetalItemDC(Rarity.RARE, "ingot_red_3", TagDC.ItemTag.INGOT_MANGANESE));
	// public static final RegistryObject<Item> INGOT_GREEN1 = regItem("ingot_green_1", () -> new MetalItemDC(Rarity.COMMON, "ingot_green_1", TagDC.ItemTag.INGOT_TIN));
	// public static final RegistryObject<Item> INGOT_GREEN2 = regItem("ingot_green_2", () -> new MetalItemDC(Rarity.UNCOMMON, "ingot_green_2", TagDC.ItemTag.INGOT_NICKEL));
	// public static final RegistryObject<Item> INGOT_GREEN3 = regItem("ingot_green_3", () -> new MetalItemDC(Rarity.RARE, "ingot_green_3", TagDC.ItemTag.INGOT_CHROMIUM));

	public static final RegistryObject<Item> INGOT_BRASS = regItem("ingot_brass", () -> new MetalItemDC(Rarity.COMMON, "ingot_brass", TagDC.ItemTag.INGOT_BRASS));
	public static final RegistryObject<Item> INGOT_BRONZE = regItem("ingot_bronze", () -> new MetalItemDC(Rarity.COMMON, "ingot_bronze", TagDC.ItemTag.INGOT_BRONZE));
	public static final RegistryObject<Item> INGOT_NICKEL_SILVER = regItem("ingot_nickel_silver", () -> new MetalItemDC(Rarity.UNCOMMON, "ingot_nickel_silver", TagDC.ItemTag.INGOT_NICKEL_SILVER));
	public static final RegistryObject<Item> INGOT_STEEL = regItem("ingot_steel", () -> new MetalItemDC(Rarity.UNCOMMON, "ingot_steel", TagDC.ItemTag.INGOT_STEEL));
	public static final RegistryObject<Item> INGOT_SUS = regItem("ingot_sus", () -> new MetalItemDC(Rarity.RARE, "ingot_sus", TagDC.ItemTag.INGOT_SUS));
	public static final RegistryObject<Item> INGOT_TOOL_STEEL = regItem("ingot_tool_steel", () -> new MetalItemDC(Rarity.RARE, "ingot_tool_steel", TagDC.ItemTag.INGOT_TOOL_STEEL));
	public static final RegistryObject<Item> INGOT_TITANIUM = regItem("ingot_titanium", () -> new MetalItemDC(Rarity.RARE, "ingot_titanium", TagDC.ItemTag.INGOT_TITANIUM));
	public static final RegistryObject<Item> INGOT_MAGNET = regItem("ingot_magnet", () -> new MetalItemDC(Rarity.RARE, "ingot_magnet", TagDC.ItemTag.INGOT_MAGNET));
	public static final RegistryObject<Item> INGOT_MANGALLOY = regItem("ingot_mangalloy", () -> new MetalItemDC(Rarity.RARE, "ingot_mangalloy", TagDC.ItemTag.INGOT_MANGALLOY));
	public static final RegistryObject<Item> INGOT_BSCCO = regItem("ingot_bscco", () -> new MetalItemDC(Rarity.RARE, "ingot_bscco", TagDC.ItemTag.INGOT_BSCCO));
	// TODO silver925, aluminum, cobalt

	public static final RegistryObject<Item> GEM_CHALCEDONY = regItem("gem_chalcedony", () -> new ItemGemDC(Rarity.COMMON, "gem_chalcedony", TagDC.ItemTag.GEM_CHALCEDONY));
	public static final RegistryObject<Item> GEM_CRYSTAL = regItem("gem_crystal", () -> new ItemGemDC(Rarity.COMMON, "gem_crystal", TagDC.ItemTag.GEM_CRYSTAL));
	public static final RegistryObject<Item> GEM_HELIODOR = regItem("gem_heliodor", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_heliodor", TagDC.ItemTag.GEM_HELIODOR));
	public static final RegistryObject<Item> GEM_THUNDEREGG = regItem("gem_thunderegg", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_thunderegg", TagDC.ItemTag.GEM_THUNDEREGG));
	public static final RegistryObject<Item> GEM_TOPAZ = regItem("gem_topaz", () -> new ItemGemDC(Rarity.RARE, "gem_topaz", TagDC.ItemTag.GEM_TOPAZ));
	public static final RegistryObject<Item> GEM_FLUORITE = regItem("gem_fluorite", () -> new ItemGemDC(Rarity.COMMON, "gem_fluorite", TagDC.ItemTag.GEM_FLUORITE));
	public static final RegistryObject<Item> GEM_LARIMAR = regItem("gem_larimar", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_larimar", TagDC.ItemTag.GEM_LARIMAR));
	public static final RegistryObject<Item> GEM_CELESTITE = regItem("gem_celestite", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_celestite", TagDC.ItemTag.GEM_CELESTITE));
	public static final RegistryObject<Item> GEM_AQUAMARINE = regItem("gem_aquamarine", () -> new ItemGemDC(Rarity.RARE, "gem_aquamarine", TagDC.ItemTag.GEM_AQUAMARINE));
	public static final RegistryObject<Item> GEM_SAPPHIRE = regItem("gem_sapphire", () -> new ItemGemDC(Rarity.RARE, "gem_sapphire", TagDC.ItemTag.GEM_SAPPHIRE));
	public static final RegistryObject<Item> GEM_JET = regItem("gem_jet", () -> new ItemGemDC(Rarity.COMMON, "gem_jet", TagDC.ItemTag.GEM_JET));
	public static final RegistryObject<Item> GEM_VIVIANITE = regItem("gem_vivianite", () -> new ItemGemDC(Rarity.COMMON, "gem_vivianite", TagDC.ItemTag.GEM_VIVIANITE));
	public static final RegistryObject<Item> GEM_IOLITE = regItem("gem_iolite", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_iolite", TagDC.ItemTag.GEM_IOLITE));
	public static final RegistryObject<Item> GEM_SAKURA = regItem("gem_sakura", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_sakura", TagDC.ItemTag.GEM_SAKURA));
	public static final RegistryObject<Item> GEM_FANG = regItem("gem_fang", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_fang", TagDC.ItemTag.GEM_FANG));
	public static final RegistryObject<Item> GEM_OPAL = regItem("gem_opal", () -> new ItemGemDC(Rarity.RARE, "gem_opal", TagDC.ItemTag.GEM_OPAL));
	public static final RegistryObject<Item> GEM_DRAGONSEYE = regItem("gem_dragonseye", () -> new ItemGemDC(Rarity.RARE, "gem_dragonseye", TagDC.ItemTag.GEM_DRAGONSEYE));
	public static final RegistryObject<Item> GEM_DESERTROSE = regItem("gem_desertrose", () -> new ItemGemDC(Rarity.COMMON, "gem_desertrose", TagDC.ItemTag.GEM_DESERTROSE));
	public static final RegistryObject<Item> GEM_JASPER = regItem("gem_jasper", () -> new ItemGemDC(Rarity.COMMON, "gem_jasper", TagDC.ItemTag.GEM_JASPER));
	public static final RegistryObject<Item> GEM_ROSINCA = regItem("gem_rosinca", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_rosinca", TagDC.ItemTag.GEM_ROSINCA));
	public static final RegistryObject<Item> GEM_ALMANDINE = regItem("gem_almandine", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_almandine", TagDC.ItemTag.GEM_ALMANDINE));
	public static final RegistryObject<Item> GEM_SPINEL = regItem("gem_spinel", () -> new ItemGemDC(Rarity.RARE, "gem_spinel", TagDC.ItemTag.GEM_SPINEL));
	public static final RegistryObject<Item> GEM_RUBY = regItem("gem_ruby", () -> new ItemGemDC(Rarity.RARE, "gem_ruby", TagDC.ItemTag.GEM_RUBY));
	public static final RegistryObject<Item> GEM_SERPENTINE = regItem("gem_serpentine", () -> new ItemGemDC(Rarity.COMMON, "gem_serpentine", TagDC.ItemTag.GEM_SERPENTINE));
	public static final RegistryObject<Item> GEM_MALACHITE = regItem("gem_malachite", () -> new ItemGemDC(Rarity.COMMON, "gem_malachite", TagDC.ItemTag.GEM_MALACHITE));
	public static final RegistryObject<Item> GEM_AMAZONITE = regItem("gem_amazonite", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_amazonite", TagDC.ItemTag.GEM_AMAZONITE));
	public static final RegistryObject<Item> GEM_OLIVINE = regItem("gem_olivine", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_olivine", TagDC.ItemTag.GEM_OLIVINE));
	public static final RegistryObject<Item> GEM_JADEITE = regItem("gem_jadeite", () -> new ItemGemDC(Rarity.RARE, "gem_jadeite", TagDC.ItemTag.GEM_JADEITE));

	public static final RegistryObject<Item> GEM_SALT = regItem("gem_salt", () -> new ItemGemDC(Rarity.COMMON, "gem_salt", TagDC.ItemTag.GEM_SALT));
	public static final RegistryObject<Item> GEM_NITER = regItem("gem_niter", () -> new ItemGemDC(Rarity.COMMON, "gem_niter", TagDC.ItemTag.GEM_NITER));
	public static final RegistryObject<Item> GEM_SULFUR = regItem("gem_sulfur", () -> new ItemGemDC(Rarity.COMMON, "gem_sulfur", TagDC.ItemTag.GEM_SULFUR));

	public static final RegistryObject<Item> DUST_COAL = regItem("dust_coal", () -> new MaterialItemDC("dust_coal", TagDC.ItemTag.DUST_COAL));
	public static final RegistryObject<Item> DUST_NITER = regItem("dust_niter", () -> new MaterialItemDC("dust_niter", TagDC.ItemTag.DUST_NITER));
	public static final RegistryObject<Item> DUST_SULFUR = regItem("dust_sulfur", () -> new MaterialItemDC("dust_sulfur", TagDC.ItemTag.DUST_SULFUR));
	public static final RegistryObject<Item> DUST_CRYSTAL = regItem("dust_crystal", () -> new MaterialItemDC("dust_crystal", TagDC.ItemTag.DUST_CRYSTAL));
	public static final RegistryObject<Item> DUST_LIME = regItem("dust_lime", () -> new MaterialItemDC("dust_lime", TagDC.ItemTag.DUST_LIME));

	public static final RegistryObject<Item> NULL_ITEM = regItem("null_item", () -> new NullItemDC("null_item"));

	public static final RegistryObject<Item> MORTAR = regItem("agate_mortar", () -> new AgateMortarItem("agate_mortar"));

	// block
	public static final RegistryObject<Block> STONE_MUD = regBlock("stone_mud", () -> new LayerStoneBlock("stone_mud"), null);
	public static final RegistryObject<Block> STONE_GYPSUM = regBlock("stone_gypsum", () -> new LayerStoneBlock("stone_gypsum"), TagDC.ItemTag.ORES_GYPSUM);
	public static final RegistryObject<Block> STONE_SERPENTINE = regBlock("stone_serpentine", () -> new LayerStoneBlock("stone_serpentine"), null);
	public static final RegistryObject<Block> STONE_GREISEN = regBlock("stone_greisen", () -> new LayerStoneBlock("stone_greisen"), null);
	public static final RegistryObject<Block> STONE_SKARN = regBlock("stone_skarn", () -> new LayerStoneBlock("stone_skarn"), null);
	public static final RegistryObject<Block> STONE_HORNFELS = regBlock("stone_hornfels", () -> new LayerStoneBlock("stone_hornfels"), null);
	public static final RegistryObject<Block> STONE_MARBLE = regBlock("stone_marble", () -> new LayerStoneBlock("stone_marble"), null);
	public static final RegistryObject<Block> STONE_SCHIST_BLUE = regBlock("stone_schist_blue", () -> new LayerStoneBlock("stone_schist_blue"), null);
	public static final RegistryObject<Block> STONE_SCHIST_NETHER = regBlock("stone_schist_nether", () -> new LayerStoneBlock("stone_schist_nether"), null);
	public static final RegistryObject<Block> STONE_GRANITE = regBlock("stone_granite", () -> new LayerStoneBlock("stone_granite"), null);

	public static final RegistryObject<Block> ORE_CHALCEDONY = regBlock("ore_chalcedony", () -> new OreBlockGemDC(GEM_CHALCEDONY, "ore_chalcedony"), TagDC.ItemTag.ORES_CHALCEDONY);
	public static final RegistryObject<Block> ORE_FLUORITE = regBlock("ore_fluorite", () -> new OreBlockGemDC(GEM_FLUORITE, "ore_fluorite"), TagDC.ItemTag.ORES_FLUORITE);
	public static final RegistryObject<Block> ORE_JET = regBlock("ore_jet", () -> new OreBlockGemDC(GEM_JET, "ore_jet"), TagDC.ItemTag.ORES_JET);
	public static final RegistryObject<Block> ORE_DESERTROSE = regBlock("ore_desertrose", () -> new OreBlockGemDC(GEM_DESERTROSE, "ore_desertrose"), TagDC.ItemTag.ORES_DESERTROSE);
	public static final RegistryObject<Block> ORE_SERPENTINE = regBlock("ore_serpentine", () -> new OreBlockGemDC(GEM_SERPENTINE, "ore_serpentine"), TagDC.ItemTag.ORES_SERPENTINE);
	public static final RegistryObject<Block> ORE_HELIODOR = regBlock("ore_heliodor", () -> new OreBlockGemDC(GEM_HELIODOR, "ore_heliodor"), TagDC.ItemTag.ORES_HELIODOR);
	public static final RegistryObject<Block> ORE_TOPAZ = regBlock("ore_topaz", () -> new OreBlockGemDC(GEM_TOPAZ, "ore_topaz").setTier(2), TagDC.ItemTag.ORES_TOPAZ);
	public static final RegistryObject<Block> ORE_LARIMAR = regBlock("ore_larimar", () -> new OreBlockGemDC(GEM_LARIMAR, "ore_larimar"), TagDC.ItemTag.ORES_LARIMAR);
	public static final RegistryObject<Block> ORE_AQUAMARINE = regBlock("ore_aquamarine", () -> new OreBlockGemDC(GEM_AQUAMARINE, "ore_aquamarine").setTier(2), TagDC.ItemTag.ORES_AQUAMARINE);
	public static final RegistryObject<Block> ORE_IOLITE = regBlock("ore_iolite", () -> new OreBlockGemDC(GEM_IOLITE, "ore_iolite"), TagDC.ItemTag.ORES_IOLITE);
	public static final RegistryObject<Block> ORE_OPAL = regBlock("ore_opal", () -> new OreBlockGemDC(GEM_OPAL, "ore_opal").setTier(2), TagDC.ItemTag.ORES_OPAL);
	public static final RegistryObject<Block> ORE_ROSINCA = regBlock("ore_rosinca", () -> new OreBlockGemDC(GEM_ROSINCA, "ore_rosinca"), TagDC.ItemTag.ORES_ROSINCA);
	public static final RegistryObject<Block> ORE_SPINEL = regBlock("ore_spinel", () -> new OreBlockGemDC(GEM_SPINEL, "ore_spinel").setTier(2), TagDC.ItemTag.ORES_SPINEL);
	public static final RegistryObject<Block> ORE_AMAZONITE = regBlock("ore_amazonite", () -> new OreBlockGemDC(GEM_AMAZONITE, "ore_amazonite"), TagDC.ItemTag.ORES_AMAZONITE);
	public static final RegistryObject<Block> ORE_JADEITE = regBlock("ore_jadeite", () -> new OreBlockGemDC(GEM_JADEITE, "ore_jadeite").setTier(2), TagDC.ItemTag.ORES_JADEITE);
	public static final RegistryObject<Block> ORE_DRAGONSEYE = regBlock("ore_dragonseye", () -> new OreBlockGemDC(GEM_DRAGONSEYE, "ore_dragonseye").setTier(2), TagDC.ItemTag.ORES_DRAGONSEYE);

	public static final RegistryObject<Block> STONE_QUARTZ = regBlock("stone_quartz", () -> new LayerStoneBlock("stone_quartz"), null);
	public static final RegistryObject<Block> STONE_SALT = regBlock("stone_salt", () -> new LayerStoneBlock("stone_salt"), TagDC.ItemTag.ORES_SALT);
	public static final RegistryObject<Block> STONE_GUANO = regBlock("stone_guano", () -> new LayerStoneBlock("stone_guano"), TagDC.ItemTag.ORES_NITER);
	public static final RegistryObject<Block> STONE_SULFUR = regBlock("stone_sulfur", () -> new LayerStoneBlock("stone_sulfur"), TagDC.ItemTag.ORES_SULFUR);
	public static final RegistryObject<Block> STONE_TRAVERTINE = regBlock("stone_travertine", () -> new LayerStoneBlock("stone_travertine"), TagDC.ItemTag.ORES_TRAVERTINE);

	public static final RegistryObject<Block> ORE_WHITE = regBlock("ore_white", () -> new OreBlockGemDC(OREITEM_WHITE1, "ore_white").setSecondary(GEM_CRYSTAL), TagDC.ItemTag.ORES_WHITE);
	public static final RegistryObject<Block> ORE_WHITE_DEEP = regBlock("ore_white_deep", () -> new OreBlockGemDC(OREITEM_WHITE2, "ore_white_deep")
		.setSecondary(GEM_THUNDEREGG).setTier(2), TagDC.ItemTag.ORES_WHITE_DEEP);
	public static final RegistryObject<Block> ORE_BLUE = regBlock("ore_blue", () -> new OreBlockGemDC(OREITEM_BLUE1, "ore_blue") {
		@Override
		public ItemStack getSecondary() {
			return new ItemStack(Items.LAPIS_LAZULI);
		}
	}, TagDC.ItemTag.ORES_BLUE);
	public static final RegistryObject<Block> ORE_BLUE_DEEP = regBlock("ore_blue_deep", () -> new OreBlockGemDC(OREITEM_BLUE2, "ore_blue_deep")
		.setSecondary(GEM_CELESTITE).setTier(2), TagDC.ItemTag.ORES_BLUE_DEEP);
	public static final RegistryObject<Block> ORE_BLACK = regBlock("ore_black", () -> new OreBlockGemDC(OREITEM_BLACK1, "ore_black")
		.setSecondary(GEM_VIVIANITE), TagDC.ItemTag.ORES_BLACK);
	public static final RegistryObject<Block> ORE_BLACK_DEEP = regBlock("ore_black_deep", () -> new OreBlockGemDC(OREITEM_BLACK2, "ore_black_deep")
		.setSecondary(GEM_FANG).setTier(2), TagDC.ItemTag.ORES_BLACK_DEEP);
	public static final RegistryObject<Block> ORE_RED = regBlock("ore_red", () -> new OreBlockGemDC(OREITEM_RED1, "ore_red").setSecondary(GEM_JASPER), TagDC.ItemTag.ORES_RED);
	public static final RegistryObject<Block> ORE_RED_DEEP = regBlock("ore_red_deep", () -> new OreBlockGemDC(OREITEM_RED2, "ore_red_deep")
		.setSecondary(GEM_ALMANDINE).setTier(2), TagDC.ItemTag.ORES_RED_DEEP);
	public static final RegistryObject<Block> ORE_GREEN = regBlock("ore_green", () -> new OreBlockGemDC(OREITEM_GREEN1, "ore_green")
		.setSecondary(GEM_MALACHITE), TagDC.ItemTag.ORES_GREEN);
	public static final RegistryObject<Block> ORE_GREEN_DEEP = regBlock("ore_green_deep", () -> new OreBlockGemDC(OREITEM_GREEN2, "ore_green_deep")
		.setSecondary(GEM_OLIVINE).setTier(2), TagDC.ItemTag.ORES_GREEN_DEEP);

	public static final RegistryObject<Block> DUSTBLOCK_BRASS = regBlock("dustblock_brass", () -> new AlloyDustBlockDC("dustblock_brass"), TagDC.ItemTag.DUSTBLOCK_BRASS);
	public static final RegistryObject<Block> DUSTBLOCK_BRONZE = regBlock("dustblock_bronze", () -> new AlloyDustBlockDC("dustblock_bronze"), TagDC.ItemTag.DUSTBLOCK_BRONZE);
	public static final RegistryObject<Block> DUSTBLOCK_NICKEL_SILVER = regBlock("dustblock_nickel_silver", () -> new AlloyDustBlockDC("dustblock_nickel_silver"),
		TagDC.ItemTag.DUSTBLOCK_NICKEL_SILVER);
	public static final RegistryObject<Block> DUSTBLOCK_STEEL = regBlock("dustblock_steel", () -> new AlloyDustBlockDC("dustblock_steel"), TagDC.ItemTag.DUSTBLOCK_STEEL);
	public static final RegistryObject<Block> DUSTBLOCK_SUS = regBlock("dustblock_sus", () -> new AlloyDustBlockDC("dustblock_sus"), TagDC.ItemTag.DUSTBLOCK_SUS);
	public static final RegistryObject<Block> DUSTBLOCK_TOOL_STEEL = regBlock("dustblock_tool_steel", () -> new AlloyDustBlockDC("dustblock_tool_steel"), TagDC.ItemTag.DUSTBLOCK_TOOL_STEEL);
	public static final RegistryObject<Block> DUSTBLOCK_TITANIUM = regBlock("dustblock_titanium", () -> new AlloyDustBlockDC("dustblock_titanium"), TagDC.ItemTag.DUSTBLOCK_TINANIUM);
	public static final RegistryObject<Block> DUSTBLOCK_MAGNET = regBlock("dustblock_magnet", () -> new AlloyDustBlockDC("dustblock_magnet"), TagDC.ItemTag.DUSTBLOCK_MAGNET);
	public static final RegistryObject<Block> DUSTBLOCK_MANGALLOY = regBlock("dustblock_mangalloy", () -> new AlloyDustBlockDC("dustblock_mangalloy"), TagDC.ItemTag.DUSTBLOCK_MANGALLOY);
	public static final RegistryObject<Block> DUSTBLOCK_BSCCO = regBlock("dustblock_bscco", () -> new AlloyDustBlockDC("dustblock_bscco"), TagDC.ItemTag.DUSTBLOCK_BSCCO);

	public static final RegistryObject<Block> METALBLOCK_BRASS = regBlock("metalblock_brass", () -> new MetalBlockDC("metalblock_brass"), TagDC.ItemTag.METALBLOCK_BRASS);
	public static final RegistryObject<Block> METALBLOCK_BRONZE = regBlock("metalblock_bronze", () -> new MetalBlockDC("metalblock_bronze"), TagDC.ItemTag.METALBLOCK_BRONZE);
	public static final RegistryObject<Block> METALBLOCK_NICKEL_SILVER = regBlock("metalblock_nickel_silver", () -> new MetalBlockDC("metalblock_nickel_silver"),
		TagDC.ItemTag.METALBLOCK_NICKEL_SILVER);
	public static final RegistryObject<Block> METALBLOCK_STEEL = regBlock("metalblock_steel", () -> new MetalBlockDC("metalblock_steel"), TagDC.ItemTag.METALBLOCK_STEEL);
	public static final RegistryObject<Block> METALBLOCK_SUS = regBlock("metalblock_sus", () -> new MetalBlockDC("metalblock_sus"), TagDC.ItemTag.METALBLOCK_SUS);
	public static final RegistryObject<Block> METALBLOCK_TOOL_STEEL = regBlock("metalblock_tool_steel", () -> new MetalBlockDC("metalblock_tool_steel"), TagDC.ItemTag.METALBLOCK_TOOL_STEEL);
	public static final RegistryObject<Block> METALBLOCK_TITANIUM = regBlock("metalblock_titanium", () -> new MetalBlockDC("metalblock_titanium"), TagDC.ItemTag.METALBLOCK_TINANIUM);
	public static final RegistryObject<Block> METALBLOCK_MAGNET = regBlock("metalblock_magnet", () -> new MetalBlockDC("metalblock_magnet"), TagDC.ItemTag.METALBLOCK_MAGNET);
	public static final RegistryObject<Block> METALBLOCK_MANGALLOY = regBlock("metalblock_mangalloy", () -> new MetalBlockDC("metalblock_mangalloy"), TagDC.ItemTag.METALBLOCK_MANGALLOY);
	public static final RegistryObject<Block> METALBLOCK_BSCCO = regBlock("metalblock_bscco", () -> new MetalBlockDC("metalblock_bscco"), TagDC.ItemTag.METALBLOCK_BSCCO);

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = BLOCKS.register("main/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(CORE), tag));
		return obj;
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return ITEMS.register("main/" + name, item);
	}

}
