package defeatedcrow.hac.food.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.IFertileBlock;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class FertileBlock extends FarmBlock implements EntityBlock, IFertileBlock, IBlockDC, IJsonDataDC {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final IntegerProperty FERTILE = DCState.FERTILE;

	public FertileBlock() {
		super(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
		    .randomTicks()
		    .strength(1.0F, 1.0F)
		    .noOcclusion());
		this.registerDefaultState(this.stateDefinition.any()
		    .setValue(MOISTURE, 0)
		    .setValue(FERTILE, 0));
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int water = DCState.getInt(state, MOISTURE);
		if (DCState.getBool(state, WATERLOGGED)) {
			water = 7;
			level.setBlockAndUpdate(pos, state.setValue(MOISTURE, water));
		}
		if (DCState.getInt(state, FERTILE) <= 0) {
			level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState()
			    .setValue(MOISTURE, water));
		}
		super.tick(state, level, pos, random);
	}

	// 乾燥による破壊なし
	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int i = state.getValue(MOISTURE);
		if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
			if (i > 0) {
				level.setBlock(pos, state.setValue(MOISTURE, i - 1), 2);
			}
		} else if (i < 7) {
			level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
		}
	}

	private static boolean isNearWater(ServerLevel level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
			if (state.canBeHydrated(level, pos, level.getFluidState(blockpos), blockpos)) {
				return true;
			}
		}
		ClimateSupplier supplier = new ClimateSupplier(level, pos);
		if (DCHumidity.wet()
		    .contains(supplier.get()
		        .getHumidity())) {
			return true;
		}

		return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(level, pos);
	}

	/* return 1 ~ 4 */
	public static int getFertile(Level world, BlockPos pos, BlockState state) {
		int ret = 0;
		if (state.getBlock() instanceof IFertileBlock) {
			return ((IFertileBlock) state.getBlock()).getFertile(world, pos, state) + 1;
		}
		return ret;
	}

	public static BlockState fertileSoil(int i, BlockState state) {
		if (state.getBlock() instanceof IFertileBlock) {
			return ((IFertileBlock) state.getBlock()).fertileTargetSoil(i, state);
		} else if (state.is(TagDC.BlockTag.FARMLAND) || state.is(BlockTags.DIRT)) {
			return ((IFertileBlock) FoodInit.FERTILE.get()).fertileTargetSoil(i, FoodInit.FERTILE.get()
			    .defaultBlockState());
		}
		return state;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(MOISTURE, FERTILE);
	}

	@Override
	public int getFertile(BlockGetter level, BlockPos pos, BlockState state) {
		return DCState.getInt(state, FERTILE);
	}

	@Override
	public BlockState fertileTargetSoil(int fertile, BlockState state) {
		int f = Mth.clamp(fertile, 0, 3);
		return state.setValue(FERTILE, f)
		    .setValue(MOISTURE, 7);
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(Blocks.DIRT);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "food/fertile";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:block/template_farmland", ImmutableMap.of("dirt", "minecraft:block/dirt", "top", "dcs_climate:block/crop/fertile_dry")),
		    new JsonModelDC("minecraft:block/template_farmland", ImmutableMap.of("dirt", "minecraft:block/dirt", "top", "dcs_climate:block/crop/fertile_moist")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("dry", "moist");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_dry");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.SHOVEL;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.fertile.fertile");
		MutableComponent tex2 = Component.translatable("dcs.tip.fertile.mutation.on")
		    .withStyle(ChatFormatting.GREEN);
		if (ClimateCore.proxy.keyShiftPushed()) {
			list.add(tex1);
			list.add(tex2);
		} else {
			list.add(Component.translatable("dcs.tip.shift"));
		}
		super.appendHoverText(stack, level, list, flag);
	}

	/* EntityBlock */

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FertileBlockTile(pos, state);
	}

}
