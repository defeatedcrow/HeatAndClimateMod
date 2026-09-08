package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class CropBlockPedalia extends ClimateCropBaseBlock {

	public CropBlockPedalia(CropTier t) {
		super(t);
		this.registerDefaultState(this.stateDefinition.any().setValue(DCState.STAGE6, 0).setValue(DCState.WILD, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {
		def.add(DCState.STAGE6, DCState.WILD);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/cropblock_pedalia_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/pedalia_0")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/pedalia_" + getSpeciesName(cropTier) + "_1")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/pedalia_" + getSpeciesName(cropTier) + "_2")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/pedalia_" + getSpeciesName(cropTier) + "_f")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/pedalia_" + getSpeciesName(cropTier) + "_c")),
		    new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/crop/pedalia_" + getSpeciesName(cropTier) + "_d")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("0", "1", "2", "3", "4", "5");
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("stage6=0", "stage6=1", "stage6=2", "stage6=3", "stage6=4", "stage6=5");
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_pedalia_" + getSpeciesName(cropTier)));
	}

	/* Damage */

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (getCurrentStage(state) == CropStage.GROWN && !level.isClientSide() && entity instanceof LivingEntity living && living.getHealth() > 1.0F) {
			living.hurt(living.level().damageSources().cactus(), 1.0F);
		}
	}

	/* IClimateCrop */

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState().setValue(DCState.STAGE6, 2).setValue(DCState.WILD, true);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.PEDALIA;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public BlockState getFlowerState(BlockState state) {
		return state.setValue(DCState.STAGE6, 3);
	}

	@Override
	public int getContinuousRegistance(CropTier t) {
		return switch (t) {
		case RARE -> 5;
		default -> 3;
		};
	}

	@Override
	public float wildCropSpreadChance() {
		return 0.03F;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.BLOCK_PD_SESAMI.get();
		case RARE -> FoodInit.BLOCK_PD_DEVILSCLAW.get();
		default -> FoodInit.BLOCK_PD_ROGERIA.get();
		};
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_PD_SESAMI.get();
		case RARE -> FoodInit.CROP_PD_DEVILSCLAW.get();
		default -> FoodInit.CROP_PD_ROGERIA.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_PD_ROGERIA.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_PD_SESAMI.get());
		case RARE -> Optional.of(FoodInit.BLOCK_PD_DEVILSCLAW.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL, DCHeatTier.OVEN);
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
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("SANDY", "SAVANNA");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("COLD", "WET");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "sesami";
		if (tier == CropTier.RARE)
			return "devilsclaw";
		return "rogeria";
	}

}
