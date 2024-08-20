package defeatedcrow.hac.core.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.material.block.IBlockDC.ToolType;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BlockTagProviderDC extends BlockTagsProvider {

	public BlockTagProviderDC(DataGenerator gen, @Nullable ExistingFileHelper helper) {
		super(gen, "dcs_climate", helper);
	}

	@Override
	protected void addTags() {

		TagsProvider.TagAppender<Block> pickaxe = this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolType() == ToolType.PICKAXE)
				.map(RegistryObject::get).forEach(pickaxe::add);

		TagsProvider.TagAppender<Block> axe = this.tag(BlockTags.MINEABLE_WITH_AXE);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolType() == ToolType.AXE)
				.map(RegistryObject::get).forEach(axe::add);

		TagsProvider.TagAppender<Block> toolTier1 = this.tag(BlockTags.NEEDS_STONE_TOOL);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolTier() == 1)
				.map(RegistryObject::get).forEach(toolTier1::add);

		TagsProvider.TagAppender<Block> toolTier2 = this.tag(BlockTags.NEEDS_IRON_TOOL);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolTier() == 2)
				.map(RegistryObject::get).forEach(toolTier2::add);

		TagsProvider.TagAppender<Block> toolTier3 = this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolTier() == 3)
				.map(RegistryObject::get).forEach(toolTier3::add);

		TagsProvider.TagAppender<Block> crops = this.tag(BlockTags.CROPS);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof ClimateCropBaseBlock)
				.map(RegistryObject::get).forEach(crops::add);

		tag(TagDC.BlockTag.ORES_WHITE).add(CoreInit.ORE_WHITE.get());
		tag(TagDC.BlockTag.ORES_BLUE).add(CoreInit.ORE_BLUE.get());
		tag(TagDC.BlockTag.ORES_BLACK).add(CoreInit.ORE_BLACK.get());
		tag(TagDC.BlockTag.ORES_RED).add(CoreInit.ORE_RED.get());
		tag(TagDC.BlockTag.ORES_GREEN).add(CoreInit.ORE_GREEN.get());

		tag(TagDC.BlockTag.ORES_WHITE_DEEP).add(CoreInit.ORE_WHITE_DEEP.get());
		tag(TagDC.BlockTag.ORES_BLUE_DEEP).add(CoreInit.ORE_BLUE_DEEP.get());
		tag(TagDC.BlockTag.ORES_BLACK_DEEP).add(CoreInit.ORE_BLACK_DEEP.get());
		tag(TagDC.BlockTag.ORES_RED_DEEP).add(CoreInit.ORE_RED_DEEP.get());
		tag(TagDC.BlockTag.ORES_GREEN_DEEP).add(CoreInit.ORE_GREEN_DEEP.get());

		tag(TagDC.BlockTag.ORES_COLOR).addTags(TagDC.BlockTag.ORES_WHITE, TagDC.BlockTag.ORES_BLUE,
				TagDC.BlockTag.ORES_BLACK, TagDC.BlockTag.ORES_RED, TagDC.BlockTag.ORES_GREEN,
				TagDC.BlockTag.ORES_WHITE_DEEP, TagDC.BlockTag.ORES_BLUE_DEEP,
				TagDC.BlockTag.ORES_BLACK_DEEP, TagDC.BlockTag.ORES_RED_DEEP, TagDC.BlockTag.ORES_GREEN_DEEP);

		tag(Tags.Blocks.ORES).addTags(TagDC.BlockTag.ORES_WHITE, TagDC.BlockTag.ORES_BLUE,
				TagDC.BlockTag.ORES_BLACK, TagDC.BlockTag.ORES_RED, TagDC.BlockTag.ORES_GREEN,
				TagDC.BlockTag.ORES_WHITE_DEEP, TagDC.BlockTag.ORES_BLUE_DEEP,
				TagDC.BlockTag.ORES_BLACK_DEEP, TagDC.BlockTag.ORES_RED_DEEP, TagDC.BlockTag.ORES_GREEN_DEEP);

		tag(TagDC.BlockTag.ORES_CHALCEDONY).add(CoreInit.ORE_CHALCEDONY.get());
		tag(TagDC.BlockTag.ORES_HELIODOR).add(CoreInit.ORE_HELIODOR.get());
		tag(TagDC.BlockTag.ORES_TOPAZ).add(CoreInit.ORE_TOPAZ.get());
		tag(TagDC.BlockTag.ORES_FLUORITE).add(CoreInit.ORE_FLUORITE.get());
		tag(TagDC.BlockTag.ORES_LARIMAR).add(CoreInit.ORE_LARIMAR.get());
		tag(TagDC.BlockTag.ORES_AQUAMARINE).add(CoreInit.ORE_AQUAMARINE.get());
		tag(TagDC.BlockTag.ORES_JET).add(CoreInit.ORE_JET.get());
		tag(TagDC.BlockTag.ORES_IOLITE).add(CoreInit.ORE_IOLITE.get());
		tag(TagDC.BlockTag.ORES_OPAL).add(CoreInit.ORE_OPAL.get());
		tag(TagDC.BlockTag.ORES_DRAGONSEYE).add(CoreInit.ORE_DRAGONSEYE.get());
		tag(TagDC.BlockTag.ORES_DESERTROSE).add(CoreInit.ORE_DESERTROSE.get());
		tag(TagDC.BlockTag.ORES_ROSINCA).add(CoreInit.ORE_ROSINCA.get());
		tag(TagDC.BlockTag.ORES_SPINEL).add(CoreInit.ORE_SPINEL.get());
		tag(TagDC.BlockTag.ORES_SERPENTINE).add(CoreInit.ORE_SERPENTINE.get());
		tag(TagDC.BlockTag.ORES_AMAZONITE).add(CoreInit.ORE_AMAZONITE.get());
		tag(TagDC.BlockTag.ORES_JADEITE).add(CoreInit.ORE_JADEITE.get());

		tag(TagDC.BlockTag.ORES_SALT).add(CoreInit.STONE_SALT.get());
		tag(TagDC.BlockTag.ORES_NITER).add(CoreInit.STONE_GUANO.get());
		tag(TagDC.BlockTag.ORES_SULFUR).add(CoreInit.STONE_SULFUR.get());
		tag(TagDC.BlockTag.ORES_GYPSUM).add(CoreInit.STONE_GYPSUM.get());
		tag(TagDC.BlockTag.ORES_TRAVERTINE).add(CoreInit.STONE_TRAVERTINE.get());
		tag(TagDC.BlockTag.ORES_LIME).add(CoreInit.STONE_MARBLE.get(), Blocks.CALCITE);

		tag(TagDC.BlockTag.DUSTBLOCK_BRASS).add(CoreInit.DUSTBLOCK_BRASS.get());
		tag(TagDC.BlockTag.DUSTBLOCK_BRONZE).add(CoreInit.DUSTBLOCK_BRONZE.get());
		tag(TagDC.BlockTag.DUSTBLOCK_NICKEL_SILVER).add(CoreInit.DUSTBLOCK_NICKEL_SILVER.get());
		tag(TagDC.BlockTag.DUSTBLOCK_STEEL).add(CoreInit.DUSTBLOCK_STEEL.get());
		tag(TagDC.BlockTag.DUSTBLOCK_ALUMINUM).add(CoreInit.DUSTBLOCK_ALUMINUM.get());
		tag(TagDC.BlockTag.DUSTBLOCK_SILVER).add(CoreInit.DUSTBLOCK_SILVER.get());
		tag(TagDC.BlockTag.DUSTBLOCK_SUS).add(CoreInit.DUSTBLOCK_SUS.get());
		tag(TagDC.BlockTag.DUSTBLOCK_TITANIUM).add(CoreInit.DUSTBLOCK_TITANIUM.get());
		tag(TagDC.BlockTag.DUSTBLOCK_MAGNET).add(CoreInit.DUSTBLOCK_MAGNET.get());
		tag(TagDC.BlockTag.DUSTBLOCK_COBALT).add(CoreInit.DUSTBLOCK_COBALT.get());
		tag(TagDC.BlockTag.DUSTBLOCK_HASTELLOY).add(CoreInit.DUSTBLOCK_HASTELLOY.get());
		tag(TagDC.BlockTag.DUSTBLOCK_BSCCO).add(CoreInit.DUSTBLOCK_BSCCO.get());

		tag(TagDC.BlockTag.METALBLOCK_BRASS).add(CoreInit.METALBLOCK_BRASS.get());
		tag(TagDC.BlockTag.METALBLOCK_BRONZE).add(CoreInit.METALBLOCK_BRONZE.get());
		tag(TagDC.BlockTag.METALBLOCK_NICKEL_SILVER).add(CoreInit.METALBLOCK_NICKEL_SILVER.get());
		tag(TagDC.BlockTag.METALBLOCK_STEEL).add(CoreInit.METALBLOCK_STEEL.get());
		tag(TagDC.BlockTag.METALBLOCK_ALUMINUM).add(CoreInit.METALBLOCK_ALUMINUM.get());
		tag(TagDC.BlockTag.METALBLOCK_STEEL).add(CoreInit.METALBLOCK_STEEL.get());
		tag(TagDC.BlockTag.METALBLOCK_SUS).add(CoreInit.METALBLOCK_SUS.get());
		tag(TagDC.BlockTag.METALBLOCK_TITANIUM).add(CoreInit.METALBLOCK_TITANIUM.get());
		tag(TagDC.BlockTag.METALBLOCK_MAGNET).add(CoreInit.METALBLOCK_MAGNET.get());
		tag(TagDC.BlockTag.METALBLOCK_COBALT).add(CoreInit.METALBLOCK_COBALT.get());
		tag(TagDC.BlockTag.METALBLOCK_HASTELLOY).add(CoreInit.METALBLOCK_HASTELLOY.get());
		tag(TagDC.BlockTag.METALBLOCK_BSCCO).add(CoreInit.METALBLOCK_BSCCO.get());

		tag(TagDC.BlockTag.MUTABLE_FARMLAND).add(FoodInit.FERTILE.get());
		tag(TagDC.BlockTag.FARMLAND).add(Blocks.FARMLAND, FoodInit.FERTILE.get(), FoodInit.FERTILE_PLANTER.get());
		tag(TagDC.BlockTag.MUD).add(Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS);

		tag(TagDC.BlockTag.WEED).add(Blocks.GRASS, Blocks.TALL_GRASS, Blocks.DEAD_BUSH, Blocks.FERN, Blocks.LARGE_FERN, Blocks.SEAGRASS);

		tag(TagDC.BlockTag.CROP_PUMPKIN).add(Blocks.PUMPKIN);
		tag(TagDC.BlockTag.CROP_MELON).add(Blocks.MELON);
		tag(TagDC.BlockTag.CROP_CACTUS).add(Blocks.CACTUS);
		tag(TagDC.BlockTag.CROP_TALL).add(Blocks.BAMBOO, Blocks.CACTUS, Blocks.SUGAR_CANE);

		tag(TagDC.BlockTag.CROP_GREEN_MANURES).add(FoodInit.BLOCK_BR_RAPESEED.get(), FoodInit.BLOCK_BR_GREEN.get(),
				FoodInit.BLOCK_BR_CABBAGE.get(), FoodInit.BLOCK_BR_RADISH.get(), FoodInit.BLOCK_CR_OAT.get(),
				FoodInit.BLOCK_CR_RYE.get(), FoodInit.BLOCK_CR_BARLEY.get(), FoodInit.BLOCK_PE_GREEN.get(),
				FoodInit.BLOCK_PE_GARBANZO.get(), FoodInit.BLOCK_PE_SOY.get(), FoodInit.BLOCK_RE_SORGHUM.get())
				.addTag(TagDC.BlockTag.WEED);

		tag(BlockTags.WOODEN_FENCES).add(BuildInit.FENCE_BEECH.get(), BuildInit.FENCE_WALNUT.get(), BuildInit.FENCE_SWEET.get(),
				BuildInit.FENCE_CHERRY.get(), BuildInit.FENCE_CAMPHOR.get(), BuildInit.FENCE_CITRUS.get(), BuildInit.FENCE_ASH.get(),
				BuildInit.FENCE_PALM.get(), BuildInit.FENCE_SORGHUM.get(), BuildInit.FENCE_SUMAC.get(), BuildInit.FENCE_LACQUER.get(),
				BuildInit.FENCE_EUCALYPTUS.get());

		tag(BlockTags.WOODEN_DOORS).add(BuildInit.DOOR_BEECH.get(), BuildInit.DOOR_WALNUT.get(), BuildInit.DOOR_SWEET.get(),
				BuildInit.DOOR_CHERRY.get(), BuildInit.DOOR_CAMPHOR.get(), BuildInit.DOOR_CITRUS.get(), BuildInit.DOOR_ASH.get(),
				BuildInit.DOOR_SUMAC.get(), BuildInit.DOOR_LACQUER.get(), BuildInit.DOOR_EUCALYPTUS.get());

		tag(BlockTags.WOODEN_TRAPDOORS).add(BuildInit.TRAPDOOR_BEECH.get(), BuildInit.TRAPDOOR_WALNUT.get(), BuildInit.DOOR_SWEET.get(),
				BuildInit.TRAPDOOR_CHERRY.get(), BuildInit.TRAPDOOR_CAMPHOR.get(), BuildInit.TRAPDOOR_CITRUS.get(), BuildInit.TRAPDOOR_ASH.get(),
				BuildInit.TRAPDOOR_SUMAC.get(), BuildInit.TRAPDOOR_LACQUER.get(), BuildInit.TRAPDOOR_EUCALYPTUS.get());

		tag(BlockTags.FENCES).add(BuildInit.FENCE_METAL.get());

		tag(BlockTags.CLIMBABLE).add(BuildInit.LADDER_METAL.get());

		tag(BlockTags.WALLS).add(BuildInit.WALL_MUD.get(), BuildInit.WALL_GYPSUM.get(), BuildInit.WALL_SERPENTINE.get(),
				BuildInit.WALL_GREISEN.get(), BuildInit.WALL_SKARN.get(), BuildInit.WALL_HORNFELS.get(),
				BuildInit.WALL_MARBLE.get(), BuildInit.WALL_SCHIST_BLUE.get(), BuildInit.WALL_SCHIST_NETHER.get(), BuildInit.WALL_GRANITE.get(),
				BuildInit.WALL_MORTAR.get(), BuildInit.WALL_ADOBE.get());

		tag(TagDC.BlockTag.BEE_FLOWERS).add(FoodInit.BLOCK_BR_RAPESEED.get(), FoodInit.BLOCK_HB_LAVENDER.get(), FoodInit.BLOCK_ML_BLUE.get(),
				FoodInit.BLOCK_ML_TROPICAL.get(), FoodInit.BLOCK_MO_BINDWEED.get(), FoodInit.BLOCK_MO_FLOWER.get(), FoodInit.BLOCK_PD_ROGERIA.get(),
				FoodInit.BLOCK_SL_NIGHTSHADE.get(), FoodInit.BLOCK_SL_LANTERN.get(), FoodInit.BLOCK_IR_CROCUS.get(),
				FoodInit.BLOCK_IR_IRIS.get(), FoodInit.BLOCK_IR_SAFFRON.get(), FoodInit.BLOCK_OR_SPIRANTHES.get(), FoodInit.BLOCK_OR_CYMBIDIUM.get(),
				FoodInit.BLOCK_OR_CATTLEYA.get(), FoodInit.BLOCK_RA_ANEMONE.get(), FoodInit.BLOCK_RA_DELPHINIUM.get(),
				FoodInit.BLOCK_RA_CLEMATIS.get(), FoodInit.BLOCK_OR_CYMBIDIUM.get(), FoodInit.CROPBLOCK_PL_COCONUT.get(),
				FoodInit.CROPBLOCK_PL_DATE.get(), FoodInit.CROPBLOCK_PL_OIL.get(), FoodInit.LEAVES_CM_OIL.get(),
				FoodInit.LEAVES_CM_SCHIMA.get(), FoodInit.LEAVES_CH_WILD.get(), FoodInit.LEAVES_CH_PLUM.get(), FoodInit.LEAVES_CH_ALMOND.get(),
				FoodInit.LEAVES_CH_PEACH.get(), FoodInit.LEAVES_CT_POMELO.get(), FoodInit.LEAVES_CT_MANDARIN.get(),
				FoodInit.LEAVES_CT_LEMON.get(), FoodInit.LEAVES_ER_HEATH.get(), FoodInit.LEAVES_ER_RHODODENDRON.get(),
				FoodInit.LEAVES_ER_BLUEBERRY.get(), FoodInit.LEAVES_OL_ASH.get(), FoodInit.LEAVES_OL_OLIVE.get(), FoodInit.LEAVES_MY_EUCALYPTUS.get(),
				FoodInit.LEAVES_OL_OSMANTHUS.get(), FoodInit.LEAVES_RO_RUGOSA.get(), FoodInit.LEAVES_RO_DAMASCHENA.get());

		tag(BlockTags.FLOWERS).addTags(TagDC.BlockTag.BEE_FLOWERS);

		tag(BlockTags.LEAVES).add(FoodInit.LEAVES_BH_COMMON.get(), FoodInit.LEAVES_BH_WALNUT.get(), FoodInit.LEAVES_BH_SWEET.get(),
				FoodInit.LEAVES_CH_WILD.get(), FoodInit.LEAVES_CH_PLUM.get(), FoodInit.LEAVES_CH_PEACH.get(), FoodInit.LEAVES_CH_ALMOND.get(),
				FoodInit.LEAVES_CM_TEA.get(), FoodInit.LEAVES_CM_OIL.get(), FoodInit.LEAVES_CM_SCHIMA.get(),
				FoodInit.LEAVES_CN_CAMPHOR.get(), FoodInit.LEAVES_CN_CINNAMON.get(), FoodInit.LEAVES_CN_AVOCADO.get(),
				FoodInit.LEAVES_CT_POMELO.get(), FoodInit.LEAVES_CT_MANDARIN.get(), FoodInit.LEAVES_CT_LEMON.get(), FoodInit.LEAVES_CT_PEPPER.get(),
				FoodInit.LEAVES_ER_HEATH.get(), FoodInit.LEAVES_ER_RHODODENDRON.get(), FoodInit.LEAVES_ER_BLUEBERRY.get(),
				FoodInit.LEAVES_MR_MULBERRY.get(), FoodInit.LEAVES_MR_PAPER.get(), FoodInit.LEAVES_MR_RUBBER.get(),
				FoodInit.LEAVES_OL_ASH.get(), FoodInit.LEAVES_OL_OLIVE.get(), FoodInit.LEAVES_OL_OSMANTHUS.get(),
				FoodInit.LEAVES_PL_COMMON.get(), FoodInit.LEAVES_SU_LACQUER.get(), FoodInit.LEAVES_SU_MANGO.get(),
				FoodInit.LEAVES_SU_CASHEW.get(), FoodInit.LEAVES_SU_PISTACHIO.get(), FoodInit.LEAVES_MY_EUCALYPTUS.get(),
				FoodInit.LEAVES_MY_GUAVA.get(), FoodInit.LEAVES_MY_CLOVE.get());

		tag(BlockTags.LOGS_THAT_BURN).add(FoodInit.LOG_BH_COMMON.get(), FoodInit.LOG_BH_WALNUT.get(), FoodInit.LOG_BH_SWEET.get(),
				FoodInit.LOG_CH_WILD.get(), FoodInit.LOG_CN_CAMPHOR.get(), FoodInit.LOG_CN_CINNAMON.get(), FoodInit.LOG_CT_POMELO.get(),
				FoodInit.LOG_MR_MULBERRY.get(), FoodInit.LOG_MR_PAPER.get(), FoodInit.LOG_MR_RUBBER.get(), FoodInit.LOG_MY_EUCALYPTUS.get(),
				FoodInit.LOG_OL_ASH.get(), FoodInit.LOG_PL_COCONUT.get(), FoodInit.LOG_SU_LACQUER.get(), FoodInit.LOG_SU_MANGO.get());

		tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(FoodInit.LOG_BH_COMMON.get(), FoodInit.LOG_BH_WALNUT.get(), FoodInit.LOG_BH_SWEET.get(),
				FoodInit.LOG_CH_WILD.get(), FoodInit.LOG_CN_CAMPHOR.get(), FoodInit.LOG_CN_CINNAMON.get(), FoodInit.LOG_CT_POMELO.get(),
				FoodInit.LOG_MR_MULBERRY.get(), FoodInit.LOG_MR_PAPER.get(), FoodInit.LOG_MR_RUBBER.get(), FoodInit.LOG_MY_EUCALYPTUS.get(),
				FoodInit.LOG_OL_ASH.get(), FoodInit.LOG_PL_COCONUT.get(), FoodInit.LOG_SU_LACQUER.get(), FoodInit.LOG_SU_MANGO.get());

		tag(BlockTags.PLANKS).add(FoodInit.PLANK_BH_COMMON.get(), FoodInit.PLANK_BH_WALNUT.get(), FoodInit.PLANK_BH_SWEET.get(),
				FoodInit.PLANK_CH_WILD.get(), FoodInit.PLANK_CN_CAMPHOR.get(), FoodInit.PLANK_CT_POMELO.get(),
				FoodInit.PLANK_MR_MULBERRY.get(), FoodInit.PLANK_OL_ASH.get(), FoodInit.PLANK_PL_COCONUT.get(),
				FoodInit.PLANK_RE_SORGHUM.get(), FoodInit.PLANK_SU_LACQUER.get(), FoodInit.PLANK_MY_EUCALYPTUS.get());

		tag(TagDC.BlockTag.LOG_SWEET).add(FoodInit.LOG_BH_WALNUT.get(), FoodInit.LOG_BH_SWEET.get(), FoodInit.LOG_PL_COCONUT.get(), Blocks.BIRCH_LOG);
		tag(TagDC.BlockTag.LOG_RESIN).add(Blocks.ACACIA_LOG, Blocks.SPRUCE_LOG, FoodInit.LOG_MY_EUCALYPTUS.get());
		tag(TagDC.BlockTag.LOG_LATEX).add(FoodInit.LOG_MR_RUBBER.get());
		tag(TagDC.BlockTag.LOG_LACQUER).add(FoodInit.LOG_SU_LACQUER.get());
		tag(TagDC.BlockTag.LOG_SAP).addTags(TagDC.BlockTag.LOG_SWEET, TagDC.BlockTag.LOG_RESIN, TagDC.BlockTag.LOG_LATEX, TagDC.BlockTag.LOG_LACQUER);

		tag(TagDC.BlockTag.SCYTHE_BREAKABLE).addTags(BlockTags.LEAVES, BlockTags.WOOL);

		tag(TagDC.BlockTag.SCYTHE_BREAKABLE).add(Blocks.COBWEB, Blocks.DEAD_BUSH, Blocks.HANGING_ROOTS, Blocks.FERN,
				Blocks.VINE, Blocks.TRIPWIRE, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS, Blocks.PUMPKIN,
				Blocks.MELON, Blocks.BAMBOO, Blocks.SUGAR_CANE);

		tag(TagDC.BlockTag.CONT_LEAVES).add(FoodInit.CONT_LEAVES.get());

		tag(TagDC.BlockTag.CONT_LOGS).add(
				FoodInit.CONT_LOG_OAK.get(), FoodInit.CONT_LOG_BIRCH.get(), FoodInit.CONT_LOG_SPRUCE.get(), FoodInit.CONT_LOG_JUNGLE.get(),
				FoodInit.CONT_LOG_ACACIA.get(), FoodInit.CONT_LOG_DARKOAK.get(), FoodInit.CONT_LOG_MANGROVE.get(), FoodInit.CONT_LOG_CRIMSON.get(), FoodInit.CONT_LOG_WARPED.get(),
				FoodInit.CONT_LOG_BH_COMMON.get(), FoodInit.CONT_LOG_BH_WALNUT.get(), FoodInit.CONT_LOG_BH_SWEET.get(), FoodInit.CONT_LOG_CH_WILD.get(), FoodInit.CONT_LOG_CN_CAMPHOR.get(),
				FoodInit.CONT_LOG_CN_CINNAMON.get(), FoodInit.CONT_LOG_CT_POMELO.get(), FoodInit.CONT_LOG_MR_MULBERRY.get(), FoodInit.CONT_LOG_MR_PAPER.get(), FoodInit.CONT_LOG_MR_RUBBER.get(),
				FoodInit.CONT_LOG_OL_ASH.get(), FoodInit.CONT_LOG_PL_COCONUT.get(), FoodInit.CONT_LOG_SU_LACQUER.get(), FoodInit.CONT_LOG_SU_MANGO.get(), FoodInit.CONT_LOG_CHARCOAL.get(),
				FoodInit.CONT_LOG_MY_EUCALYPTUS.get());

		tag(TagDC.BlockTag.CONT_CHARCOAL).add(FoodInit.CONT_LOG_CHARCOAL.get());

		tag(TagDC.BlockTag.CONT_CROPS).add(FoodInit.CONT_CROP_APPLE.get(), FoodInit.CONT_CROP_CARROT.get(), FoodInit.CONT_CROP_POTATO.get(),
				FoodInit.CONT_CROP_BEET.get(), FoodInit.CONT_CROP_PUMPKIN.get(), FoodInit.CONT_CROP_MELON.get(), FoodInit.CONT_CROP_CACTUS.get(),
				FoodInit.CONT_CROP_COCOA.get(), FoodInit.CONT_CROP_SUGARCANE.get(), FoodInit.CONT_CROP_BUSHBERRY.get());

		tag(TagDC.BlockTag.BUILDING_BRICKS).add(BuildInit.BRICKS_MUD.get(), BuildInit.BRICKS_GYPSUM.get(), BuildInit.BRICKS_SERPENTINE.get(),
				BuildInit.BRICKS_GREISEN.get(), BuildInit.BRICKS_SKARN.get(), BuildInit.BRICKS_HORNFELS.get(), BuildInit.BRICKS_MARBLE.get(),
				BuildInit.BRICKS_SCHIST_BLUE.get(), BuildInit.BRICKS_SCHIST_NETHER.get(), BuildInit.BRICKS_GRANITE.get(), BuildInit.BRICKS_MORTAR.get(),
				BuildInit.ADOBE_BRICKS.get());

		tag(TagDC.BlockTag.BUILDING_PILLAR).add(BuildInit.PILLAR_MUD.get(), BuildInit.PILLAR_GYPSUM.get(), BuildInit.PILLAR_SERPENTINE.get(),
				BuildInit.PILLAR_GREISEN.get(), BuildInit.PILLAR_SKARN.get(), BuildInit.PILLAR_HORNFELS.get(), BuildInit.PILLAR_MARBLE.get(),
				BuildInit.PILLAR_SCHIST_BLUE.get(), BuildInit.PILLAR_SCHIST_NETHER.get(), BuildInit.PILLAR_GRANITE.get(), BuildInit.PILLAR_MORTAR.get());

		tag(TagDC.BlockTag.BUILDING_CHISELED).add(BuildInit.CHISELED_MUD.get(), BuildInit.CHISELED_GYPSUM.get(), BuildInit.CHISELED_SERPENTINE.get(),
				BuildInit.CHISELED_GREISEN.get(), BuildInit.CHISELED_SKARN.get(), BuildInit.CHISELED_HORNFELS.get(), BuildInit.CHISELED_MARBLE.get(),
				BuildInit.CHISELED_SCHIST_BLUE.get(), BuildInit.CHISELED_SCHIST_NETHER.get(), BuildInit.CHISELED_GRANITE.get(), BuildInit.CHISELED_MORTAR.get());

		tag(TagDC.BlockTag.BUILDING_STONE).add(BuildInit.FLINTBRICKS.get(), BuildInit.FLINTBRICKS_WHITE.get(), BuildInit.FLINTBRICKS_BLACK.get(),
				BuildInit.FLINTBRICKS_RED.get(), BuildInit.MOSAIC_BLACK.get(), BuildInit.MOSAIC_BLUE.get(), BuildInit.MOSAIC_YELLOW.get(),
				BuildInit.MOSAIC_RED.get(), BuildInit.MORTAR.get());

		tag(TagDC.BlockTag.DIRT_SLABS).add(BuildInit.SLAB_DIRT.get(), BuildInit.SLAB_GRASS.get(), BuildInit.SLAB_PATH.get());

		tag(TagDC.BlockTag.BUILDING_STONE).addTags(TagDC.BlockTag.BUILDING_BRICKS, TagDC.BlockTag.BUILDING_PILLAR, TagDC.BlockTag.BUILDING_CHISELED);

		tag(TagDC.BlockTag.HOPPER_FILTER).add(MachineInit.HOPPER_FILTER.get(), MachineInit.HOPPER_FILTER_GOLD.get());

		tag(TagDC.BlockTag.HOPPER).addTag(TagDC.BlockTag.HOPPER_FILTER).add(Blocks.HOPPER, MachineInit.HOPPER_GOLD.get());

		tag(TagDC.BlockTag.LEAKAGE_MACHINE).add(MachineInit.CABLE_COPPER.get());

		tag(TagDC.BlockTag.FLUID_PIPE).add(MachineInit.PIPE_BRASS.get());

		tag(TagDC.BlockTag.ENERGY_CABLE).add(MachineInit.CABLE_COPPER.get(), MachineInit.CABLE_COPPER_COATED.get());

		// // climate
		//
		// tag(TagDC.BlockTag.HEAT_TIER).addTags(TagDC.BlockTag.ABSOLUTE, TagDC.BlockTag.CRYOGENIC, TagDC.BlockTag.FROSTBITE, TagDC.BlockTag.COLD,
		// TagDC.BlockTag.COOL, TagDC.BlockTag.NORMAL, TagDC.BlockTag.WARM, TagDC.BlockTag.HOT, TagDC.BlockTag.BOIL,
		// TagDC.BlockTag.OVEN, TagDC.BlockTag.KILN, TagDC.BlockTag.SMELTING, TagDC.BlockTag.UHT, TagDC.BlockTag.INFERNO);
		//
		// tag(TagDC.BlockTag.HUMIDITY).addTags(TagDC.BlockTag.DRY, TagDC.BlockTag.NORMAL_HUM, TagDC.BlockTag.WET, TagDC.BlockTag.UNDERWATER);
		//
		// tag(TagDC.BlockTag.AIRFLOW).addTags(TagDC.BlockTag.TIGHT, TagDC.BlockTag.NORMAL_AIR, TagDC.BlockTag.FLOW, TagDC.BlockTag.WIND);

		// plugins

		tag(TagDC.BlockTag.CROP_GREEN_MANURES).addOptional(new ResourceLocation("biomesoplenty", "clover"));
		tag(TagDC.BlockTag.CROP_GREEN_MANURES).addOptional(new ResourceLocation("biomesoplenty", "dune_grass"));

	}

}
