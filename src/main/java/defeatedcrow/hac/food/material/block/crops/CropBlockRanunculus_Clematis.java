package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CropBlockRanunculus_Clematis extends CropBaseVine {

	public CropBlockRanunculus_Clematis(CropTier t) {
		super(t);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_ranunculus_rare";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/ranunculus_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/ranunculus_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/ranunculus_clematis_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/ranunculus_clematis_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/ranunculus_clematis_c")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/vine/ranunculus_clematis_d")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/ranunculus_clematis_2")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/ranunculus_clematis_f")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/ranunculus_clematis_c")),
		    new JsonModelDC("dcs_climate:block/dcs_crop_side", ImmutableMap.of("crop", "dcs_climate:block/crop/vine/ranunculus_clematis_d")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "f", "c", "d", "side", "side_f", "side_c", "side_d");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_ranunculus_clematis"));
	}

	/* IClimateCrop */

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		List<ItemStack> list = Lists.newArrayList();
		CropStage stage = this.getCurrentStage(state);
		if (stage == CropStage.GROWN) {
			int i = DCUtil.rand.nextInt(fortune + 2);
			if (i > 0) {
				ItemStack ret = new ItemStack(FoodInit.VINE.get(), i);
				list.add(ret);
			}
		}
		list.add(new ItemStack(getCropItem(getTier())));
		return list;
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.RANUNCULUS;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return 5;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_RA_CLEMATIS.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_RA_CLEMATIS.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_RA_ANEMONE.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_RA_DELPHINIUM.get());
		case RARE -> Optional.of(FoodInit.BLOCK_RA_CLEMATIS.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_RA_MONKSHOOD.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier tier) {
		return switch (tier) {
		case WILD -> ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		default -> ImmutableList.of(SoilType.FARMLAND);
		};
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
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
		return Lists.newArrayList();
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "cucumber";
		if (tier == CropTier.RARE)
			return "cantaloup";
		return "calabash";
	}

}
