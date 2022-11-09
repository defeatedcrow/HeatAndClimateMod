package defeatedcrow.hac.core.material;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.block.LayerStoneBlock;
import defeatedcrow.hac.core.material.block.building.FenceWoodDC;
import defeatedcrow.hac.core.material.block.building.PillarStoneDC;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
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
	public static final RegistryObject<Block> FLINTBRICKS_BRACK = regBlock("flintbricks_black", () -> new LayerStoneBlock("flintbricks_black").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> FLINTBRICKS_RED = regBlock("flintbricks_red", () -> new LayerStoneBlock("flintbricks_red").setDomain("build").setTexDir("build"), null);

	public static final RegistryObject<Block> MOSAIC_BLACK = regBlock("mosaic_black", () -> new LayerStoneBlock("mosaic_black").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> MOSAIC_BLUE = regBlock("mosaic_blue", () -> new LayerStoneBlock("mosaic_blue").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> MOSAIC_YELLOW = regBlock("mosaic_yellow", () -> new LayerStoneBlock("mosaic_yellow").setDomain("build").setTexDir("build"), null);
	public static final RegistryObject<Block> MOSAIC_RED = regBlock("mosaic_red", () -> new LayerStoneBlock("mosaic_red").setDomain("build").setTexDir("build"), null);

	public static final RegistryObject<Block> STARIS_BEECH = regBlock("stairs_beech_common", () -> new StairsWoodDC("beech_common", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_BH_COMMON), null);
	public static final RegistryObject<Block> SLAB_BEECH = regBlock("slab_beech_common", () -> new SlabWoodDC("beech_common", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), null);
	public static final RegistryObject<Block> FENCE_BEECH = regBlock("fence_beech_common", () -> new FenceWoodDC("beech_common"), ItemTags.FENCES);

	public static final RegistryObject<Block> STARIS_WALNUT = regBlock("stairs_beech_walnut", () -> new StairsWoodDC("beech_walnut", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_BH_WALNUT), null);
	public static final RegistryObject<Block> SLAB_WALNUT = regBlock("slab_beech_walnut", () -> new SlabWoodDC("beech_walnut", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), null);
	public static final RegistryObject<Block> FENCE_WALNUT = regBlock("fence_beech_walnut", () -> new FenceWoodDC("beech_walnut"), ItemTags.FENCES);

	public static final RegistryObject<Block> STARIS_SWEET = regBlock("stairs_beech_sweet", () -> new StairsWoodDC("beech_sweet", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), FoodInit.PLANK_BH_SWEET), null);
	public static final RegistryObject<Block> SLAB_SWEET = regBlock("slab_beech_sweet", () -> new SlabWoodDC("beech_sweet", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), null);
	public static final RegistryObject<Block> FENCE_SWEET = regBlock("fence_beech_sweet", () -> new FenceWoodDC("beech_sweet"), ItemTags.FENCES);

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("build/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.BUILD), tag));
		return obj;
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("build/" + name, item);
	}

}
