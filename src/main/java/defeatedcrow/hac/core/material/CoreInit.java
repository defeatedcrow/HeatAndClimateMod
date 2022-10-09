package defeatedcrow.hac.core.material;

import java.util.function.Supplier;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.block.LayerStoneBlock;
import defeatedcrow.hac.core.material.block.OreBlockGemDC;
import defeatedcrow.hac.core.material.item.ItemGemDC;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CoreInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ClimateCore.MOD_ID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ClimateCore.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ClimateCore.MOD_ID);

	public static final CreativeModeTab CORE = new CreativeTabClimate("core");

	public static void init() {
		FoodInit.init();
	}

	// item
	public static final RegistryObject<Item> OREITEM_WHITE1 = regItem("oreitem_white_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_white_1"));
	public static final RegistryObject<Item> OREITEM_WHITE2 = regItem("oreitem_white_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_white_2"));
	public static final RegistryObject<Item> OREITEM_WHITE3 = regItem("oreitem_white_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_white_3"));
	public static final RegistryObject<Item> OREITEM_BLUE1 = regItem("oreitem_blue_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_blue_1"));
	public static final RegistryObject<Item> OREITEM_BLUE2 = regItem("oreitem_blue_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_blue_2"));
	public static final RegistryObject<Item> OREITEM_BLUE3 = regItem("oreitem_blue_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_blue_3"));
	public static final RegistryObject<Item> OREITEM_BLACK1 = regItem("oreitem_black_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_black_1"));
	public static final RegistryObject<Item> OREITEM_BLACK2 = regItem("oreitem_black_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_black_2"));
	public static final RegistryObject<Item> OREITEM_BLACK3 = regItem("oreitem_black_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_black_3"));
	public static final RegistryObject<Item> OREITEM_RED1 = regItem("oreitem_red_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_red_1"));
	public static final RegistryObject<Item> OREITEM_RED2 = regItem("oreitem_red_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_red_2"));
	public static final RegistryObject<Item> OREITEM_RED3 = regItem("oreitem_red_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_red_3"));
	public static final RegistryObject<Item> OREITEM_GREEN1 = regItem("oreitem_green_1", () -> new ItemGemDC(Rarity.COMMON, "oreitem_green_1"));
	public static final RegistryObject<Item> OREITEM_GREEN2 = regItem("oreitem_green_2", () -> new ItemGemDC(Rarity.UNCOMMON, "oreitem_green_2"));
	public static final RegistryObject<Item> OREITEM_GREEN3 = regItem("oreitem_green_3", () -> new ItemGemDC(Rarity.RARE, "oreitem_green_3"));

	public static final RegistryObject<Item> GEM_CHALCEDONY = regItem("gem_chalcedony", () -> new ItemGemDC(Rarity.COMMON, "gem_chalcedony"));
	public static final RegistryObject<Item> GEM_CRYSTAL = regItem("gem_crystal", () -> new ItemGemDC(Rarity.COMMON, "gem_crystal"));
	public static final RegistryObject<Item> GEM_HELIODOR = regItem("gem_heliodor", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_heliodor"));
	public static final RegistryObject<Item> GEM_THUNDEREGG = regItem("gem_thunderegg", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_thunderegg"));
	public static final RegistryObject<Item> GEM_TOPAZ = regItem("gem_topaz", () -> new ItemGemDC(Rarity.RARE, "gem_topaz"));
	public static final RegistryObject<Item> GEM_FLUORITE = regItem("gem_fluorite", () -> new ItemGemDC(Rarity.COMMON, "gem_fluorite"));
	public static final RegistryObject<Item> GEM_LARIMAR = regItem("gem_larimar", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_larimar"));
	public static final RegistryObject<Item> GEM_CELESTITE = regItem("gem_celestite", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_celestite"));
	public static final RegistryObject<Item> GEM_AQUAMARINE = regItem("gem_aquamarine", () -> new ItemGemDC(Rarity.RARE, "gem_aquamarine"));
	public static final RegistryObject<Item> GEM_SAPPHIRE = regItem("gem_sapphire", () -> new ItemGemDC(Rarity.RARE, "gem_sapphire"));
	public static final RegistryObject<Item> GEM_JET = regItem("gem_jet", () -> new ItemGemDC(Rarity.COMMON, "gem_jet"));
	public static final RegistryObject<Item> GEM_VIVIANITE = regItem("gem_vivianite", () -> new ItemGemDC(Rarity.COMMON, "gem_vivianite"));
	public static final RegistryObject<Item> GEM_IOLITE = regItem("gem_iolite", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_iolite"));
	public static final RegistryObject<Item> GEM_SAKURA = regItem("gem_sakura", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_sakura"));
	public static final RegistryObject<Item> GEM_FANG = regItem("gem_fang", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_fang"));
	public static final RegistryObject<Item> GEM_OPAL = regItem("gem_opal", () -> new ItemGemDC(Rarity.RARE, "gem_opal"));
	public static final RegistryObject<Item> GEM_DRAGONSEYE = regItem("gem_dragonseye", () -> new ItemGemDC(Rarity.RARE, "gem_dragonseye"));
	public static final RegistryObject<Item> GEM_DESERTROSE = regItem("gem_desertrose", () -> new ItemGemDC(Rarity.COMMON, "gem_desertrose"));
	public static final RegistryObject<Item> GEM_JASPER = regItem("gem_jasper", () -> new ItemGemDC(Rarity.COMMON, "gem_jasper"));
	public static final RegistryObject<Item> GEM_ROSINCA = regItem("gem_rosinca", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_rosinca"));
	public static final RegistryObject<Item> GEM_ALMANDINE = regItem("gem_almandine", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_almandine"));
	public static final RegistryObject<Item> GEM_SPINEL = regItem("gem_spinel", () -> new ItemGemDC(Rarity.RARE, "gem_spinel"));
	public static final RegistryObject<Item> GEM_RUBY = regItem("gem_ruby", () -> new ItemGemDC(Rarity.RARE, "gem_ruby"));
	public static final RegistryObject<Item> GEM_SERPENTINE = regItem("gem_serpentine", () -> new ItemGemDC(Rarity.COMMON, "gem_serpentine"));
	public static final RegistryObject<Item> GEM_MALACHITE = regItem("gem_malachite", () -> new ItemGemDC(Rarity.COMMON, "gem_malachite"));
	public static final RegistryObject<Item> GEM_AMAZONITE = regItem("gem_amazonite", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_amazonite"));
	public static final RegistryObject<Item> GEM_OLIVINE = regItem("gem_olivine", () -> new ItemGemDC(Rarity.UNCOMMON, "gem_olivine"));
	public static final RegistryObject<Item> GEM_JADEITE = regItem("gem_jadeite", () -> new ItemGemDC(Rarity.RARE, "gem_jadeite"));

	public static final RegistryObject<Item> GEM_SALT = regItem("gem_salt", () -> new ItemGemDC(Rarity.COMMON, "gem_salt"));
	public static final RegistryObject<Item> GEM_NITER = regItem("gem_niter", () -> new ItemGemDC(Rarity.COMMON, "gem_niter"));
	public static final RegistryObject<Item> GEM_SULFUR = regItem("gem_sulfur", () -> new ItemGemDC(Rarity.COMMON, "gem_sulfur"));

	// block
	public static final RegistryObject<Block> STONE_QUARTZ = regBlock("stone_quartz", () -> new LayerStoneBlock("stone_quartz"));
	public static final RegistryObject<Block> ORE_CHALCEDONY = regBlock("ore_chalcedony", () -> new OreBlockGemDC(GEM_CHALCEDONY, "ore_chalcedony"));
	public static final RegistryObject<Block> STONE_MUD = regBlock("stone_mud", () -> new LayerStoneBlock("stone_mud"));
	public static final RegistryObject<Block> ORE_FLUORITE = regBlock("ore_fluorite", () -> new OreBlockGemDC(GEM_FLUORITE, "ore_fluorite"));
	public static final RegistryObject<Block> ORE_JET = regBlock("ore_jet", () -> new OreBlockGemDC(GEM_JET, "ore_jet"));
	public static final RegistryObject<Block> STONE_GYPSUM = regBlock("stone_gypsum", () -> new LayerStoneBlock("stone_gypsum"));
	public static final RegistryObject<Block> ORE_DESERTROSE = regBlock("ore_desertrose", () -> new OreBlockGemDC(GEM_DESERTROSE, "ore_desertrose"));
	public static final RegistryObject<Block> STONE_SERPENTINE = regBlock("stone_serpentine", () -> new LayerStoneBlock("stone_serpentine"));
	public static final RegistryObject<Block> ORE_SERPENTINE = regBlock("ore_serpentine", () -> new OreBlockGemDC(GEM_SERPENTINE, "ore_serpentine"));
	public static final RegistryObject<Block> STONE_GREISEN = regBlock("stone_greisen", () -> new LayerStoneBlock("stone_greisen"));
	public static final RegistryObject<Block> ORE_HELIODOR = regBlock("ore_heliodor", () -> new OreBlockGemDC(GEM_HELIODOR, "ore_heliodor"));
	public static final RegistryObject<Block> ORE_TOPAZ = regBlock("ore_topaz", () -> new OreBlockGemDC(GEM_TOPAZ, "ore_topaz").setTier(2));
	public static final RegistryObject<Block> STONE_SKARN = regBlock("stone_skarn", () -> new LayerStoneBlock("stone_skarn"));
	public static final RegistryObject<Block> ORE_LARIMAR = regBlock("ore_larimar", () -> new OreBlockGemDC(GEM_LARIMAR, "ore_larimar"));
	public static final RegistryObject<Block> ORE_AQUAMARINE = regBlock("ore_aquamarine", () -> new OreBlockGemDC(GEM_AQUAMARINE, "ore_aquamarine").setTier(2));
	public static final RegistryObject<Block> STONE_HORNFELS = regBlock("stone_hornfels", () -> new LayerStoneBlock("stone_hornfels"));
	public static final RegistryObject<Block> ORE_IOLITE = regBlock("ore_iolite", () -> new OreBlockGemDC(GEM_IOLITE, "ore_iolite"));
	public static final RegistryObject<Block> ORE_OPAL = regBlock("ore_opal", () -> new OreBlockGemDC(GEM_OPAL, "ore_opal").setTier(2));
	public static final RegistryObject<Block> STONE_MARBLE = regBlock("stone_marble", () -> new LayerStoneBlock("stone_marble"));
	public static final RegistryObject<Block> ORE_ROSINCA = regBlock("ore_rosinca", () -> new OreBlockGemDC(GEM_ROSINCA, "ore_rosinca"));
	public static final RegistryObject<Block> ORE_SPINEL = regBlock("ore_spinel", () -> new OreBlockGemDC(GEM_SPINEL, "ore_spinel").setTier(2));
	public static final RegistryObject<Block> STONE_SCHIST_BLUE = regBlock("stone_schist_blue", () -> new LayerStoneBlock("stone_schist_blue"));
	public static final RegistryObject<Block> ORE_AMAZONITE = regBlock("ore_amazonite", () -> new OreBlockGemDC(GEM_AMAZONITE, "ore_amazonite"));
	public static final RegistryObject<Block> ORE_JADEITE = regBlock("ore_jadeite", () -> new OreBlockGemDC(GEM_JADEITE, "ore_jadeite").setTier(2));
	public static final RegistryObject<Block> STONE_SCHIST_NETHER = regBlock("stone_schist_nether", () -> new LayerStoneBlock("stone_schist_nether"));
	public static final RegistryObject<Block> ORE_DRAGONSEYE = regBlock("ore_dragonseye", () -> new OreBlockGemDC(GEM_DRAGONSEYE, "ore_dragonseye").setTier(2));

	public static final RegistryObject<Block> STONE_GRANITE = regBlock("stone_granite", () -> new LayerStoneBlock("stone_granite"));
	public static final RegistryObject<Block> STONE_SALT = regBlock("stone_salt", () -> new LayerStoneBlock("stone_salt"));
	public static final RegistryObject<Block> STONE_GUANO = regBlock("stone_guano", () -> new LayerStoneBlock("stone_guano"));
	public static final RegistryObject<Block> STONE_SULFUR = regBlock("stone_sulfur", () -> new LayerStoneBlock("stone_sulfur"));
	public static final RegistryObject<Block> STONE_TRAVERTINE = regBlock("stone_travertine", () -> new LayerStoneBlock("stone_travertine"));

	public static final RegistryObject<Block> ORE_WHITE = regBlock("ore_white", () -> new OreBlockGemDC(OREITEM_WHITE1, "ore_white").setSecondary(GEM_CRYSTAL));
	public static final RegistryObject<Block> ORE_WHITE_DEEP = regBlock("ore_white_deep", () -> new OreBlockGemDC(OREITEM_WHITE2, "ore_white_deep")
			.setSecondary(GEM_THUNDEREGG).setTier(2));
	public static final RegistryObject<Block> ORE_BLUE = regBlock("ore_blue", () -> new OreBlockGemDC(OREITEM_BLUE1, "ore_blue") {
		@Override
		public ItemStack getSecondary() {
			return new ItemStack(Items.LAPIS_LAZULI);
		}
	});
	public static final RegistryObject<Block> ORE_BLUE_DEEP = regBlock("ore_blue_deep", () -> new OreBlockGemDC(OREITEM_BLUE2, "ore_blue_deep")
			.setSecondary(GEM_CELESTITE).setTier(2));
	public static final RegistryObject<Block> ORE_BLACK = regBlock("ore_black", () -> new OreBlockGemDC(OREITEM_BLACK1, "ore_black")
			.setSecondary(GEM_VIVIANITE));
	public static final RegistryObject<Block> ORE_BLACK_DEEP = regBlock("ore_black_deep", () -> new OreBlockGemDC(OREITEM_BLACK2, "ore_black_deep")
			.setSecondary(GEM_FANG).setTier(2));
	public static final RegistryObject<Block> ORE_RED = regBlock("ore_red", () -> new OreBlockGemDC(OREITEM_RED1, "ore_red").setSecondary(GEM_JASPER));
	public static final RegistryObject<Block> ORE_RED_DEEP = regBlock("ore_red_deep", () -> new OreBlockGemDC(OREITEM_RED2, "ore_red_deep")
			.setSecondary(GEM_ALMANDINE).setTier(2));
	public static final RegistryObject<Block> ORE_GREEN = regBlock("ore_green", () -> new OreBlockGemDC(OREITEM_GREEN1, "ore_green")
			.setSecondary(GEM_MALACHITE));
	public static final RegistryObject<Block> ORE_GREEN_DEEP = regBlock("ore_green_deep", () -> new OreBlockGemDC(OREITEM_GREEN2, "ore_green_deep")
			.setSecondary(GEM_OLIVINE).setTier(2));

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block) {
		RegistryObject<Block> obj = BLOCKS.register("main/" + name, block);
		regItem(name, () -> new BlockItem(obj.get(), new Item.Properties().tab(CORE)));
		return obj;
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return ITEMS.register("main/" + name, item);
	}

}
