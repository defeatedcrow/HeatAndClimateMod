package defeatedcrow.hac.core.event;

import java.util.Optional;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.event.DCBlockUpdateEvent;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.IHeatTreatment;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.building.GrassSlab;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.block.FertileBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;

public class BlockUpdateEventDC {

	public static void onCropUpdate(BlockEvent.CropGrowEvent.Pre event) {
		if (event.getLevel() instanceof Level && !event.getLevel().isClientSide()) {
			Level world = (Level) event.getLevel();
			BlockPos p = event.getPos();
			BlockState st = event.getState();
			Block block = st.getBlock();

			// for (Block b : CoreConfigDC.blackListBlock) {
			// if (block == b) {
			// return;
			// }
			// }

			ClimateSupplier clm = new ClimateSupplier(world, p);
			ClimateSupplier clm_down = new ClimateSupplier(world, p.below());

			if (ConfigCommonBuilder.INSTANCE.enVanillaCrop.get()) {
				// 寒冷地では成長しづらい
				if (clm.get().getHeat().isCold()) {
					int c = 5 + clm.get().getHeat().getTier();
					if (c < 2 || world.random.nextInt(c) == 0)
						event.setResult(Result.DENY);
				} else if ((clm.get().getHeat() == DCHeatTier.WARM || clm.get().getHeat() == DCHeatTier.HOT) && clm_down.get().getHumidity() == DCHumidity.WET) {
					// WETの参照posを真下に
					// WARMかつWETの場合に成長が促進される
					int fertile = FertileBlock.getFertile(world, p.below(), world.getBlockState(p.below()));
					if (world.random.nextInt(5 - fertile) == 0) {
						event.setResult(Result.ALLOW);
					} else {
						event.setResult(Result.DEFAULT);
					}
				}
				return;
			}

		}
	}

	public static void onBlockUpdate(DCBlockUpdateEvent event) {
		if (event.level != null && !event.level.isClientSide()) {
			Level world = event.level;
			BlockPos p = event.pos;
			BlockState st = event.state;
			Block block = st.getBlock();

			if (st.is(TagDC.BlockTag.FARMLAND) && st.getProperties().contains(BlockStateProperties.MOISTURE)) {
				ClimateSupplier clm = new ClimateSupplier(world, p);
				DCHumidity hum = ClimateAPI.calculator.getHumidity(world, p, 4, true);
				DCHumidity hum2 = clm.get().getHumidity();
				// 耕地はWET以上の湿度では湿る
				if (hum.getID() > 1 || hum2.getID() > 1) {
					BlockState next = st.setValue(BlockStateProperties.MOISTURE, Integer.valueOf(7));
					world.setBlock(p, next, 2);
					event.setCanceled(true);
				}
				return;
			}

			if (block instanceof SpreadingSnowyDirtBlock) {
				if (!world.isAreaLoaded(p, 3))
					return;
				if (event.rand.nextInt(3) != 0)
					return;
				if (world.getMaxLocalRawBrightness(p.above()) >= 9) {
					Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(world.getRandom());
					BlockPos p2 = p.relative(dir);
					if (world.getBlockState(p2).is(BuildInit.SLAB_DIRT.get()) && GrassSlab.canBeGrass(world, p2) && !world.getFluidState(p2).is(FluidTags.WATER)) {
						world.setBlockAndUpdate(p2, BuildInit.SLAB_GRASS.get().defaultBlockState().setValue(GrassSlab.SNOWY, Boolean.valueOf(DCState.getBool(st, SnowyDirtBlock.SNOWY))));
						event.setCanceled(true);
					} else {
						BlockPos p3 = p.relative(dir).above();
						if (world.getBlockState(p3).is(BuildInit.SLAB_DIRT.get()) && GrassSlab.canBeGrass(world, p3) && !world.getFluidState(p3).is(FluidTags.WATER)) {
							world.setBlockAndUpdate(p3, BuildInit.SLAB_GRASS.get().defaultBlockState().setValue(GrassSlab.SNOWY, Boolean.valueOf(DCState.getBool(st, SnowyDirtBlock.SNOWY))));
							event.setCanceled(true);
						}
					}
				}
			}

			if (SnowLayerBlock.class.isInstance(block)) {
				if (world.getBiome(p).get().warmEnoughToRain(p)) {
					ClimateSupplier clm = new ClimateSupplier(world, p);
					if (clm.get().getHeat().getTier() > DCHeatTier.COLD.getTier()) {
						SnowLayerBlock.dropResources(st, world, p);
						world.removeBlock(p, false);
					}
				}
			}

		}
	}

	// 気候精錬のスターター
	public static void onBlockPlacement(BlockEvent.EntityPlaceEvent event) {
		if (event.getEntity() instanceof Player && !event.getEntity().getLevel().isClientSide) {
			Player placer = (Player) event.getEntity();
			BlockState place = event.getPlacedBlock();
			BlockSnapshot snap = event.getBlockSnapshot();
			Optional<IClimateSmelting> recipe = DCRecipes.hasAnySmeltingRecipe(place.getBlock());
			recipe.ifPresent(ret -> {
				placer.getLevel().scheduleTick(snap.getPos(), place.getBlock(), ret.recipeFrequency());
			});
			Optional<IHeatTreatment> recipe2 = DCRecipes.hasAnyHeatTreatmentRecipe(place.getBlock());
			recipe2.ifPresent(ret -> {
				placer.getLevel().scheduleTick(snap.getPos(), place.getBlock(), ret.getHeatingTime());
			});
		}
	}

}
