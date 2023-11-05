package defeatedcrow.hac.core.material;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.block.LayerStoneBlock;
import defeatedcrow.hac.core.material.block.building.CabinetBlock;
import defeatedcrow.hac.core.material.block.building.CabinetTile;
import defeatedcrow.hac.core.material.block.building.ChainBlockDC;
import defeatedcrow.hac.core.material.block.building.ChandelierLamp;
import defeatedcrow.hac.core.material.block.building.ChandelierTile;
import defeatedcrow.hac.core.material.block.building.ConnectedGlassBlock;
import defeatedcrow.hac.core.material.block.building.FenceWoodDC;
import defeatedcrow.hac.core.material.block.building.LockerBlock;
import defeatedcrow.hac.core.material.block.building.LockerTile;
import defeatedcrow.hac.core.material.block.building.LuggageBlock;
import defeatedcrow.hac.core.material.block.building.LuggageTile;
import defeatedcrow.hac.core.material.block.building.PillarStoneDC;
import defeatedcrow.hac.core.material.block.building.SidedLightDC;
import defeatedcrow.hac.core.material.block.building.SimpleLightDC;
import defeatedcrow.hac.core.material.block.building.SlabStoneDC;
import defeatedcrow.hac.core.material.block.building.SlabWoodDC;
import defeatedcrow.hac.core.material.block.building.StairsStoneDC;
import defeatedcrow.hac.core.material.block.building.StairsWoodDC;
import defeatedcrow.hac.core.material.block.building.WallStoneDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

public class BuildInit {

	public static void init() {}

	public static final RegistryObject<Block> BRICKS_MUD = regBlock("bricks_mud", () -> new LayerStoneBlock("bricks_mud").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_MUD = regBlock("pillar_mud", () -> new PillarStoneDC("mud").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_MUD = regBlock("chiseled_mud", () -> new LayerStoneBlock("chiseled_mud").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_MUD = regBlock("stairs_mud", () -> new StairsStoneDC("mud", CoreInit.STONE_MUD), null);
	public static final RegistryObject<Block> SLAB_MUD = regBlock("slab_mud", () -> new SlabStoneDC("mud"), null);
	public static final RegistryObject<Block> WALL_MUD = regBlock("wall_mud", () -> new WallStoneDC("mud"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_GYPSUM = regBlock("bricks_gypsum", () -> new LayerStoneBlock("bricks_gypsum").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_GYPSUM = regBlock("pillar_gypsum", () -> new PillarStoneDC("gypsum").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_GYPSUM = regBlock("chiseled_gypsum", () -> new LayerStoneBlock("chiseled_gypsum").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_GYPSUM = regBlock("stairs_gypsum", () -> new StairsStoneDC("gypsum", CoreInit.STONE_GYPSUM), null);
	public static final RegistryObject<Block> SLAB_GYPSUM = regBlock("slab_gypsum", () -> new SlabStoneDC("gypsum"), null);
	public static final RegistryObject<Block> WALL_GYPSUM = regBlock("wall_gypsum", () -> new WallStoneDC("gypsum"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_SERPENTINE = regBlock("bricks_serpentine", () -> new LayerStoneBlock("bricks_serpentine").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_SERPENTINE = regBlock("pillar_serpentine", () -> new PillarStoneDC("serpentine").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_SERPENTINE = regBlock("chiseled_serpentine", () -> new LayerStoneBlock("chiseled_serpentine").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_SERPENTINE = regBlock("stairs_serpentine", () -> new StairsStoneDC("serpentine", CoreInit.STONE_SERPENTINE), null);
	public static final RegistryObject<Block> SLAB_SERPENTINE = regBlock("slab_serpentine", () -> new SlabStoneDC("serpentine"), null);
	public static final RegistryObject<Block> WALL_SERPENTINE = regBlock("wall_serpentine", () -> new WallStoneDC("serpentine"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_GREISEN = regBlock("bricks_greisen", () -> new LayerStoneBlock("bricks_greisen").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_GREISEN = regBlock("pillar_greisen", () -> new PillarStoneDC("greisen").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_GREISEN = regBlock("chiseled_greisen", () -> new LayerStoneBlock("chiseled_greisen").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_GREISEN = regBlock("stairs_greisen", () -> new StairsStoneDC("greisen", CoreInit.STONE_GREISEN), null);
	public static final RegistryObject<Block> SLAB_GREISEN = regBlock("slab_greisen", () -> new SlabStoneDC("greisen"), null);
	public static final RegistryObject<Block> WALL_GREISEN = regBlock("wall_greisen", () -> new WallStoneDC("greisen"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_SKARN = regBlock("bricks_skarn", () -> new LayerStoneBlock("bricks_skarn").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_SKARN = regBlock("pillar_skarn", () -> new PillarStoneDC("skarn").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_SKARN = regBlock("chiseled_skarn", () -> new LayerStoneBlock("chiseled_skarn").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_SKARN = regBlock("stairs_skarn", () -> new StairsStoneDC("skarn", CoreInit.STONE_SKARN), null);
	public static final RegistryObject<Block> SLAB_SKARN = regBlock("slab_skarn", () -> new SlabStoneDC("skarn"), null);
	public static final RegistryObject<Block> WALL_SKARN = regBlock("wall_skarn", () -> new WallStoneDC("skarn"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_HORNFELS = regBlock("bricks_hornfels", () -> new LayerStoneBlock("bricks_hornfels").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_HORNFELS = regBlock("pillar_hornfels", () -> new PillarStoneDC("hornfels").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_HORNFELS = regBlock("chiseled_hornfels", () -> new LayerStoneBlock("chiseled_hornfels").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_HORNFELS = regBlock("stairs_hornfels", () -> new StairsStoneDC("hornfels", CoreInit.STONE_HORNFELS), null);
	public static final RegistryObject<Block> SLAB_HORNFELS = regBlock("slab_hornfels", () -> new SlabStoneDC("hornfels"), null);
	public static final RegistryObject<Block> WALL_HORNFELS = regBlock("wall_hornfels", () -> new WallStoneDC("hornfels"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_MARBLE = regBlock("bricks_marble", () -> new LayerStoneBlock("bricks_marble").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_MARBLE = regBlock("pillar_marble", () -> new PillarStoneDC("marble").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_MARBLE = regBlock("chiseled_marble", () -> new LayerStoneBlock("chiseled_marble").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_MARBLE = regBlock("stairs_marble", () -> new StairsStoneDC("marble", CoreInit.STONE_MARBLE), null);
	public static final RegistryObject<Block> SLAB_MARBLE = regBlock("slab_marble", () -> new SlabStoneDC("marble"), null);
	public static final RegistryObject<Block> WALL_MARBLE = regBlock("wall_marble", () -> new WallStoneDC("marble"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_SCHIST_BLUE = regBlock("bricks_schist_blue", () -> new LayerStoneBlock("bricks_schist_blue").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_SCHIST_BLUE = regBlock("pillar_schist_blue", () -> new PillarStoneDC("schist_blue").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_SCHIST_BLUE = regBlock("chiseled_schist_blue", () -> new LayerStoneBlock("chiseled_schist_blue").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_SCHIST_BLUE = regBlock("stairs_schist_blue", () -> new StairsStoneDC("schist_blue", CoreInit.STONE_SCHIST_BLUE), null);
	public static final RegistryObject<Block> SLAB_SCHIST_BLUE = regBlock("slab_schist_blue", () -> new SlabStoneDC("schist_blue"), null);
	public static final RegistryObject<Block> WALL_SCHIST_BLUE = regBlock("wall_schist_blue", () -> new WallStoneDC("schist_blue"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_SCHIST_NETHER = regBlock("bricks_schist_nether", () -> new LayerStoneBlock("bricks_schist_nether").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_SCHIST_NETHER = regBlock("pillar_schist_nether", () -> new PillarStoneDC("schist_nether").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_SCHIST_NETHER = regBlock("chiseled_schist_nether", () -> new LayerStoneBlock("chiseled_schist_nether").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> STAIRS_SCHIST_NETHER = regBlock("stairs_schist_nether", () -> new StairsStoneDC("schist_nether", CoreInit.STONE_SCHIST_NETHER), null);
	public static final RegistryObject<Block> SLAB_SCHIST_NETHER = regBlock("slab_schist_nether", () -> new SlabStoneDC("schist_nether"), null);
	public static final RegistryObject<Block> WALL_SCHIST_NETHER = regBlock("wall_schist_nether", () -> new WallStoneDC("schist_nether"), ItemTags.WALLS);

	public static final RegistryObject<Block> BRICKS_GRANITE = regBlock("bricks_granite", () -> new LayerStoneBlock("bricks_granite").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> PILLAR_GRANITE = regBlock("pillar_granite", () -> new PillarStoneDC("granite").setDomain("build"), null);
	public static final RegistryObject<Block> CHISELED_GRANITE = regBlock("chiseled_granite", () -> new LayerStoneBlock("chiseled_granite").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> SLAB_GRANITE = regBlock("slab_granite", () -> new SlabStoneDC("granite"), null);
	public static final RegistryObject<Block> STAIRS_GRANITE = regBlock("stairs_granite", () -> new StairsStoneDC("granite", CoreInit.STONE_GRANITE), null);
	public static final RegistryObject<Block> WALL_GRANITE = regBlock("wall_granite", () -> new WallStoneDC("granite"), ItemTags.WALLS);

	public static final RegistryObject<Block> FLINTBRICKS = regBlock("flintbricks", () -> new LayerStoneBlock("flintbricks").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> FLINTBRICKS_WHITE = regBlock("flintbricks_white", () -> new LayerStoneBlock("flintbricks_white").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> FLINTBRICKS_BLACK = regBlock("flintbricks_black", () -> new LayerStoneBlock("flintbricks_black").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> FLINTBRICKS_RED = regBlock("flintbricks_red", () -> new LayerStoneBlock("flintbricks_red").setDomain("build").setTexDir("build"), null);

	public static final RegistryObject<Block> MOSAIC_BLACK = regBlock("mosaic_black", () -> new LayerStoneBlock("mosaic_black").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> MOSAIC_BLUE = regBlock("mosaic_blue", () -> new LayerStoneBlock("mosaic_blue").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> MOSAIC_YELLOW = regBlock("mosaic_yellow", () -> new LayerStoneBlock("mosaic_yellow").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> MOSAIC_RED = regBlock("mosaic_red", () -> new LayerStoneBlock("mosaic_red").setDomain("build").setTexDir("build"), null);

	public static final RegistryObject<Block> STAIRS_BEECH = regBlock("stairs_beech_common", () -> new StairsWoodDC("beech_common", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_BH_COMMON),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_BEECH = regBlock("slab_beech_common", () -> new SlabWoodDC("beech_common", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_BEECH = regBlock("fence_beech_common", () -> new FenceWoodDC("beech_common"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STARIS_WALNUT = regBlock("stairs_beech_walnut", () -> new StairsWoodDC("beech_walnut", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_BH_WALNUT),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_WALNUT = regBlock("slab_beech_walnut", () -> new SlabWoodDC("beech_walnut", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_WALNUT = regBlock("fence_beech_walnut", () -> new FenceWoodDC("beech_walnut"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_SWEET = regBlock("stairs_beech_sweet", () -> new StairsWoodDC("beech_sweet", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_BH_SWEET),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_SWEET = regBlock("slab_beech_sweet", () -> new SlabWoodDC("beech_sweet", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_SWEET = regBlock("fence_beech_sweet", () -> new FenceWoodDC("beech_sweet"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_CHERRY = regBlock("stairs_cherry_wild", () -> new StairsWoodDC("cherry_wild", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_CH_WILD),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_CHERRY = regBlock("slab_cherry_wild", () -> new SlabWoodDC("cherry_wild", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_CHERRY = regBlock("fence_cherry_wild", () -> new FenceWoodDC("cherry_wild"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_CAMPHOR = regBlock("stairs_cinnamon_camphor", () -> new StairsWoodDC("cinnamon_camphor", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_CN_CAMPHOR),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_CAMPHOR = regBlock("slab_cinnamon_camphor", () -> new SlabWoodDC("cinnamon_camphor", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_CAMPHOR = regBlock("fence_cinnamon_camphor", () -> new FenceWoodDC("cinnamon_camphor"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_CITRUS = regBlock("stairs_citrus_pomelo", () -> new StairsWoodDC("citrus_pomelo", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_CT_POMELO),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_CITRUS = regBlock("slab_citrus_pomelo", () -> new SlabWoodDC("citrus_pomelo", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_CITRUS = regBlock("fence_citrus_pomelo", () -> new FenceWoodDC("citrus_pomelo"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_MORUS = regBlock("stairs_morus_mulberry", () -> new StairsWoodDC("morus_mulberry", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_MR_MULBERRY),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_MORUS = regBlock("slab_morus_mulberry", () -> new SlabWoodDC("morus_mulberry", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_MORUS = regBlock("fence_morus_mulberry", () -> new FenceWoodDC("morus_mulberry"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_ASH = regBlock("stairs_olive_ash", () -> new StairsWoodDC("olive_ash", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_OL_ASH),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_ASH = regBlock("slab_olive_ash", () -> new SlabWoodDC("olive_ash", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_ASH = regBlock("fence_olive_ash", () -> new FenceWoodDC("olive_ash"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_PALM = regBlock("stairs_palm_coconut", () -> new StairsWoodDC("palm_coconut", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_PL_COCONUT),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_PALM = regBlock("slab_palm_coconut", () -> new SlabWoodDC("palm_coconut", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_PALM = regBlock("fence_palm_coconut", () -> new FenceWoodDC("palm_coconut"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_SUMAC = regBlock("stairs_sumac_lacquer", () -> new StairsWoodDC("sumac_lacquer", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_SU_LACQUER),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_SUMAC = regBlock("slab_sumac_lacquer", () -> new SlabWoodDC("sumac_lacquer", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_SUMAC = regBlock("fence_sumac_lacquer", () -> new FenceWoodDC("sumac_lacquer"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_SORGHUM = regBlock("stairs_reed_sorghum", () -> new StairsWoodDC("reed_sorghum", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_RE_SORGHUM),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_SORGHUM = regBlock("slab_reed_sorghum", () -> new SlabWoodDC("reed_sorghum", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_SORGHUM = regBlock("fence_reed_sorghum", () -> new FenceWoodDC("reed_sorghum"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> STAIRS_LACQUER = regBlock("stairs_lacquerware", () -> new StairsWoodDC("lacquerware", BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS), FoodInit.PLANK_LACQUERWARE),
		ItemTags.WOODEN_STAIRS);
	public static final RegistryObject<Block> SLAB_LACQUER = regBlock("slab_lacquerware", () -> new SlabWoodDC("lacquerware", BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)), ItemTags.WOODEN_SLABS);
	public static final RegistryObject<Block> FENCE_LACQUER = regBlock("fence_lacquerware", () -> new FenceWoodDC("lacquerware"), ItemTags.WOODEN_FENCES);

	public static final RegistryObject<Block> GLASS_CRYSTAL = regBlock("glass_crystal", () -> new ConnectedGlassBlock("crystal", 0), Tags.Items.GLASS);
	public static final RegistryObject<Block> GLASS_LIGHT = regBlock("glass_light", () -> new ConnectedGlassBlock("crystal", 15), null);
	public static final RegistryObject<Block> GLASS_DARK = regBlock("glass_dark", () -> new ConnectedGlassBlock("crystal"), null);

	public static final RegistryObject<Block> CHAL_LAMP = regBlock("chal_lamp", () -> new SimpleLightDC("chal_lamp"), null);
	public static final RegistryObject<Block> CHAL_LAMP_GLASS = regBlock("chal_lamp_glass", () -> new SimpleLightDC("chal_lamp_glass"), null);
	public static final RegistryObject<Block> CHAL_LAMP_TABLE = regBlock("chal_lamp_table", () -> new SidedLightDC("chal_lamp_table"), null);

	public static final RegistryObject<Block> CHAL_LAMP_FLUORITE = regBlock("chal_lamp_fluorite", () -> new SidedLightDC("chal_lamp_fluorite"), null);
	public static final RegistryObject<Block> CHAL_LAMP_JET = regBlock("chal_lamp_jet", () -> new SidedLightDC("chal_lamp_jet"), null);
	public static final RegistryObject<Block> CHAL_LAMP_DESERTROSE = regBlock("chal_lamp_desertrose", () -> new SidedLightDC("chal_lamp_desertrose"), null);
	public static final RegistryObject<Block> CHAL_LAMP_SERPENTINE = regBlock("chal_lamp_serpentine", () -> new SidedLightDC("chal_lamp_serpentine"), null);

	public static final RegistryObject<Block> CHANDELIER_IRON = regBlock("chandelier_iron", () -> new ChandelierLamp("iron"), null);
	public static final RegistryObject<Block> CHANDELIER_LAMP = regBlock("chandelier_crystal", () -> new ChandelierLamp("crystal"), null);
	public static final RegistryObject<Block> CHANDELIER_FLUORITE = regBlock("chandelier_fluorite", () -> new ChandelierLamp("fluorite"), null);
	public static final RegistryObject<Block> CHANDELIER_JET = regBlock("chandelier_jet", () -> new ChandelierLamp("jet"), null);
	public static final RegistryObject<Block> CHANDELIER_DESERTROSE = regBlock("chandelier_desertrose", () -> new ChandelierLamp("desertrose"), null);
	public static final RegistryObject<Block> CHANDELIER_SERPENTINE = regBlock("chandelier_serpentine", () -> new ChandelierLamp("serpentine"), null);

	public static final RegistryObject<Block> CHAIN_GOLD = regBlock("chain_gold", () -> new ChainBlockDC("gold"), null);

	public static final RegistryObject<Block> LUGGAGE_NORMAL = regBlock("luggage_normal", () -> new LuggageBlock("luggage_normal"), null);
	public static final RegistryObject<Block> LUGGAGE_WHITE = regBlock("luggage_white", () -> new LuggageBlock("luggage_white"), null);
	public static final RegistryObject<Block> LUGGAGE_BLUE = regBlock("luggage_blue", () -> new LuggageBlock("luggage_blue"), null);
	public static final RegistryObject<Block> LUGGAGE_BLACK = regBlock("luggage_black", () -> new LuggageBlock("luggage_black"), null);
	public static final RegistryObject<Block> LUGGAGE_RED = regBlock("luggage_red", () -> new LuggageBlock("luggage_red"), null);
	public static final RegistryObject<Block> LUGGAGE_GREEN = regBlock("luggage_green", () -> new LuggageBlock("luggage_green"), null);

	public static final RegistryObject<Block> LOCKER_NORMAL = regBlock("locker_normal", () -> new LockerBlock("locker_normal"), null);
	public static final RegistryObject<Block> LOCKER_WHITE = regBlock("locker_white", () -> new LockerBlock("locker_white"), null);
	public static final RegistryObject<Block> LOCKER_BLUE = regBlock("locker_blue", () -> new LockerBlock("locker_blue"), null);
	public static final RegistryObject<Block> LOCKER_BLACK = regBlock("locker_black", () -> new LockerBlock("locker_black"), null);
	public static final RegistryObject<Block> LOCKER_RED = regBlock("locker_red", () -> new LockerBlock("locker_red"), null);
	public static final RegistryObject<Block> LOCKER_GREEN = regBlock("locker_green", () -> new LockerBlock("locker_green"), null);

	public static final RegistryObject<Block> CABINET_NORMAL = regBlock("cabinet_normal", () -> new CabinetBlock("cabinet_normal"), null);
	public static final RegistryObject<Block> CABINET_WHITE = regBlock("cabinet_white", () -> new CabinetBlock("cabinet_white"), null);
	public static final RegistryObject<Block> CABINET_BLUE = regBlock("cabinet_blue", () -> new CabinetBlock("cabinet_blue"), null);
	public static final RegistryObject<Block> CABINET_BLACK = regBlock("cabinet_black", () -> new CabinetBlock("cabinet_black"), null);
	public static final RegistryObject<Block> CABINET_RED = regBlock("cabinet_red", () -> new CabinetBlock("cabinet_red"), null);
	public static final RegistryObject<Block> CABINET_GREEN = regBlock("cabinet_green", () -> new CabinetBlock("cabinet_green"), null);

	// TileEntity
	public static final RegistryObject<BlockEntityType<ChandelierTile>> CHANDELIER_TILE = CoreInit.BLOCK_ENTITIES.register("chandelier_crystal_tile",
		() -> BlockEntityType.Builder.of(ChandelierTile::new, new Block[] { CHANDELIER_LAMP.get(), CHANDELIER_FLUORITE.get(), CHANDELIER_JET.get(), CHANDELIER_DESERTROSE.get(), CHANDELIER_SERPENTINE.get(),
			CHANDELIER_IRON.get() }).build(null));

	public static final RegistryObject<BlockEntityType<LuggageTile>> LUGGAGE_TILE = CoreInit.BLOCK_ENTITIES.register("luggage_tile",
		() -> BlockEntityType.Builder.of(LuggageTile::new, new Block[] { LUGGAGE_NORMAL.get(), LUGGAGE_WHITE.get(), LUGGAGE_BLUE.get(), LUGGAGE_BLACK.get(), LUGGAGE_RED.get(), LUGGAGE_GREEN.get() }).build(null));

	public static final RegistryObject<BlockEntityType<LockerTile>> LOCKER_TILE = CoreInit.BLOCK_ENTITIES.register("locker_tile",
		() -> BlockEntityType.Builder.of(LockerTile::new, new Block[] { LOCKER_NORMAL.get(), LOCKER_WHITE.get(), LOCKER_BLUE.get(), LOCKER_BLACK.get(), LOCKER_RED.get(), LOCKER_GREEN.get() }).build(null));

	public static final RegistryObject<BlockEntityType<CabinetTile>> CABINET_TILE = CoreInit.BLOCK_ENTITIES.register("cabinet_tile",
		() -> BlockEntityType.Builder.of(CabinetTile::new, new Block[] { CABINET_NORMAL.get(), CABINET_WHITE.get(), CABINET_BLUE.get(), CABINET_BLACK.get(), CABINET_RED.get(), CABINET_GREEN.get() }).build(null));

	// Menu

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("build/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.BUILD), tag));
		return obj;
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("build/" + name, item);
	}

}
