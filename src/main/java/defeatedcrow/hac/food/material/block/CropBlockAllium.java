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
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.tag.TagUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class CropBlockAllium extends ClimateCropBaseBlock {

	final CropTier tier;

	public CropBlockAllium(CropTier t) {
		super();
		tier = t;
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE5, Integer.valueOf(0)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE5);
	}

	@Override
	public ItemStack getSeedItem(BlockState thisState) {
		return new ItemStack(this);
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		if (tier == CropTier.WILD) {
			return ImmutableList.of(new ItemStack(FoodInit.CROP_AL_WILD.get()));
		} else if (tier == CropTier.COMMON) {
			return ImmutableList.of(new ItemStack(FoodInit.CROP_AL_ONION.get()));
		} else if (tier == CropTier.RARE) {
			return ImmutableList.of(new ItemStack(FoodInit.CROP_AL_GARLIC.get()));
		} else {
			return Lists.newArrayList();
		}
	}

	@Override
	public boolean isSuitablePlace(BlockGetter world, BlockPos pos, BlockState state) {
		if (TagUtil.matchTag("farmland", state).isPresent())
			return true;
		return tier == CropTier.WILD && TagUtil.matchTag("dirt", state).isPresent();
	}

	@Override
	public boolean onMutation(Level level, BlockPos pos, BlockState state, RandomSource random) {
		int c1 = getMutationChance(level, pos, state);
		if (c1 > 0) {
			int r = random.nextInt(c1);
			if (r > (99 - CropTier.RARE.getMutationChance())) {
				BlockState next = FoodInit.BLOCK_AL_GARLIC.get().defaultBlockState();
				return level.setBlock(pos, next, 3);
			} else if (r > (99 - CropTier.COMMON.getMutationChance())) {
				BlockState next = FoodInit.BLOCK_AL_ONION.get().defaultBlockState();
				return level.setBlock(pos, next, 3);
			}
		}
		return false;
	}

	@Override
	public CropStage getCurrentStage(BlockState state) {
		int stage = DCState.getInt(state, DCState.STAGE5);
		if (stage == 0) {
			return CropStage.GROUND;
		} else if (stage == 3) {
			return CropStage.GROWN;
		} else if (stage == 4)
			return CropStage.FLOWER;
		return CropStage.YOUNG;
	}

	@Override
	public BlockState getGrownState() {
		return this.defaultBlockState().setValue(DCState.STAGE5, Integer.valueOf(4));
	}

	@Override
	public int getGrowingChance(Level world, BlockPos pos, BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		if (stage != CropStage.FLOWER && stage != CropStage.DEAD) {
			boolean clm = isSuitableForGrowing(world, pos, thisState);
			int ret = clm ? 8 : 50;
			BlockState under = world.getBlockState(pos.below());
			if (getFertile(world, pos.below(), under) > 5) {
				ret /= 2;
			}
			return ret;
		}
		return 0;
	}

	@Override
	public boolean onGrow(Level world, BlockPos pos, BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		if (stage == CropStage.DEAD) {
			return false;
		} else if (stage == CropStage.FLOWER) {
			return false;
		} else {
			int age = DCState.getInt(thisState, DCState.STAGE5);
			if (age >= 0 && age < 4) {
				age++;
				BlockState next = thisState.setValue(DCState.STAGE5, age);
				return world.setBlock(pos, next, 3);
			}
		}
		return false;
	}

	@Override
	public boolean canHarvest(BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		return stage == CropStage.GROWN || stage == CropStage.FLOWER;
	}

	@Override
	public List<DCHeatTier> getSuitableTemp() {
		if (tier == CropTier.WILD) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		} else if (tier == CropTier.COMMON) {
			return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		} else if (tier == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		} else {
			return Lists.newArrayList();
		}
	}

	@Override
	public List<DCHumidity> getSuitableHum() {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir() {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public CropTier getTier() {
		return tier;
	}

	@Override
	public CropType getFamily() {
		return CropType.ALLIUM;
	}

	@Override
	public CropGrowType getGrowType() {
		return CropGrowType.SINGLE;
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_allium_" + tier.toString();
	}

	private String getTexName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "onion";
		if (tier == CropTier.RARE)
			return "garlic";
		return "wild";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/allium_0")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/allium_1")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/allium_" + getTexName(tier) + "_2")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/allium_" + getTexName(tier) + "_c")),
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/allium_" + getTexName(tier) + "_f")));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_allium_" + getTexName(tier)));
	}

}
