package defeatedcrow.hac.core.event;

import java.util.Optional;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.event.DCBlockUpdateEvent;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
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

			for (Block b : CoreConfigDC.blackListBlock) {
				if (block == b) {
					return;
				}
			}

			ClimateSupplier clm = new ClimateSupplier(world, p);
			ClimateSupplier clm_down = new ClimateSupplier(world, p.below());

			if (CoreConfigDC.enableVanillaCrop) {
				// 寒冷地では枯れる
				if (clm.get().getHeat().isCold()) {
					if (CoreConfigDC.harderVanilla && clm.get().getHeat().getTier() < DCHeatTier.FROSTBITE.getTier()) {
						world.setBlockAndUpdate(p, Blocks.AIR.defaultBlockState());
					} else {
						int c = 5 + clm.get().getHeat().getTier();
						if (c > 0 && world.random.nextInt(c) > 0)
							event.setResult(Result.DENY);
					}
				} else if ((clm.get().getHeat() == DCHeatTier.WARM || clm.get().getHeat() == DCHeatTier.HOT) && clm_down.get().getHumidity() == DCHumidity.WET) {
					// WETの参照posを真下に
					// WARMかつWETの場合に成長が促進される
					if (world.random.nextInt(5) == 0) {
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

			for (Block b : CoreConfigDC.blackListBlock) {
				if (block == b) {
					return;
				}
			}

			ClimateSupplier clm = new ClimateSupplier(world, p);

			if (CoreConfigDC.enableFarmland && FarmBlock.class.isInstance(block) && st.getProperties().contains(BlockStateProperties.MOISTURE)) {
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

		}
	}

	public static void onBlockPlacement(BlockEvent.EntityPlaceEvent event) {
		if (event.getEntity() instanceof Player && !event.getEntity().getLevel().isClientSide) {
			Player placer = (Player) event.getEntity();
			BlockState place = event.getPlacedBlock();
			BlockSnapshot snap = event.getBlockSnapshot();
			Optional<IClimateSmelting> recipe = DCRecipes.hasAnySmeltingRecipe(place.getBlock());
			recipe.ifPresent(ret -> {
				placer.getLevel().scheduleTick(snap.getPos(), place.getBlock(), ret.recipeFrequency());
			});
		}
	}

}
