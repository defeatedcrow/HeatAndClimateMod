package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SaplingCherry extends ClimateCropBaseBlock {

	public SaplingCherry(CropTier t) {
		super(t);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {

	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	}

	/* 苗木なのでコレ自体は成長しない */

	@Override
	public CropStage getCurrentStage(BlockState state) {
		return CropStage.SAPLING;
	}

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState();
	}

	@Override
	public BlockState getGrownState() {
		return this.defaultBlockState();
	}

	@Override
	public int getGrowingChance(Level world, BlockPos pos, BlockState thisState) {
		boolean clm = isSuitableForGrowing(world, pos, thisState);
		int ret = clm ? 8 : 50;
		BlockState under = world.getBlockState(pos.below());
		if (getFertile(world, pos.below(), under) > 5) {
			ret /= 2;
		}
		return ret;
	}

	@Override
	public boolean onGrow(Level world, BlockPos pos, BlockState state) {
		if (state.getBlock() instanceof IClimateCrop)
			onGrowingTree(world, pos, state, ((IClimateCrop) state.getBlock()).getTier());
		return true;
	}

	@Override
	public boolean canHarvest(BlockState thisState) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		ret.add(new ItemStack(this));
		return ret;
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/sapling_" + getFamily().toString() + "_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/tree/sapling_" + getFamily().toString() + "_" + getSpeciesName(cropTier))));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_" + getFamily().toString() + "_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.CHERRY;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return this;
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_CH_PLUM.get();
		case RARE:
			return FoodInit.CROP_CH_PEACH.get();
		default:
			return FoodInit.CROP_CH_WILD.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_CH_WILD.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_CH_PLUM.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_CH_PEACH.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("MOUNTAIN", "FOREST");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "plum";
		if (tier == CropTier.RARE)
			return "peach";
		return "wild";
	}

	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 高さ3~6、幅5
		level.random.nextInt(2);
		int h = 3 + level.random.nextInt(3);
		int r = 2;
		BlockState log = FoodInit.LOG_CH_WILD.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_CH_WILD.get().defaultBlockState().setValue(DCState.FLAG, true);
		if (t == CropTier.COMMON) {
			leaves = FoodInit.LEAVES_CH_PLUM.get().defaultBlockState().setValue(DCState.FLAG, true);
		} else if (t == CropTier.RARE) {
			leaves = FoodInit.LEAVES_CH_PEACH.get().defaultBlockState().setValue(DCState.FLAG, true);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			for (int i = 1; i < h; i++) {
				if (!level.getBlockState(pos.above(i)).getMaterial().isReplaceable())
					return;
			}

			// 幹
			for (int i = 0; i < h; i++) {
				level.setBlock(pos.above(i), log, 2);
			}

			// 葉
			level.setBlock(pos.above(h), leaves, 2);
			int h2 = 2;
			for (int j = (h - h2); j < h + 1; j++) {
				for (int k = -r; k <= r; k++) {
					for (int l = -r; l <= r; l++) {
						double lim = 6D;
						if (j == h) {
							lim = 3D;
						}
						BlockPos p1 = pos.offset(k, j, l);
						BlockPos p2 = pos.offset(0, j, 0);

						if (p1.equals(p2) || p1.distSqr(p2) > lim)
							continue;

						if (level.getBlockState(p1).getBlock() == Blocks.AIR) {
							level.setBlock(p1, leaves, 2);
						}

					}
				}
			}
		}
	}

}