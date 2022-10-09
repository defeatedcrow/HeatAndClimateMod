package defeatedcrow.hac.core.material.tag;

import org.jetbrains.annotations.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.material.block.IBlockDC.ToolType;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

		TagsProvider.TagAppender<Block> toolTier1 = this.tag(BlockTags.NEEDS_STONE_TOOL);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolTier() == 1)
			.map(RegistryObject::get).forEach(toolTier1::add);

		TagsProvider.TagAppender<Block> toolTier2 = this.tag(BlockTags.NEEDS_IRON_TOOL);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolTier() == 2)
			.map(RegistryObject::get).forEach(toolTier2::add);

		TagsProvider.TagAppender<Block> toolTier3 = this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
		CoreInit.BLOCKS.getEntries().stream().filter(block -> block.get() instanceof IBlockDC && ((IBlockDC) block.get()).getToolTier() == 3)
			.map(RegistryObject::get).forEach(toolTier3::add);

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

		tag(TagDC.BlockTag.FARMLAND).add(Blocks.FARMLAND, FoodInit.FERTILE.get());
		tag(TagDC.BlockTag.MUD).add(Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS);
	}

}
