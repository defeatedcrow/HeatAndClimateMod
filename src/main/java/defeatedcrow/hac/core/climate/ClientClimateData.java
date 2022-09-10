package defeatedcrow.hac.core.climate;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.config.CoreConfigDC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.IFluidBlock;

@OnlyIn(Dist.CLIENT)
public class ClientClimateData {

	public static final ClientClimateData INSTANCE = new ClientClimateData();

	// 表示用データ
	private static IClimate climate = null;
	private static int tempTier = 0;
	private static int iconTier = 2;
	private static float heatPrev = 0;
	private static float coldPrev = 0;

	@SuppressWarnings("resource")
	public void updatePlayerClimate() {
		ClientLevel world = Minecraft.getInstance().level;
		LocalPlayer player = Minecraft.getInstance().player;
		if (world == null || player == null)
			return;
		/* 10Fごとに使用データを更新 */
		BlockPos pos = player.blockPosition();
		if (pos != null && world.isLoaded(pos)) {
			climate = ClimateAPI.calculator.getClimate(world, pos);
		}

		if (climate != null) {
			tempTier = climate.getHeat().getTier();
		}

		float conf_prev = 3F - CoreConfigDC.damageDifficulty;
		float damage = 0;
		boolean isCold = tempTier < 0;
		heatPrev = 0;
		coldPrev = 0;

		if (player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
			heatPrev += 4.0F;
		}
		// if (player.isPotionActive(DCInit.prevFreeze)) {
		// coldPrev += 4.0F;
		// }

		// 防具の計算
		// Iterable<ItemStack> items = player.getArmorSlots();
		// if (items != null) {
		// for (ItemStack item : items) {
		// if (item.isEmpty())
		// continue;
		//
		// heatPrev += DCUtil.getItemResistantData(item, false);
		// coldPrev += DCUtil.getItemResistantData(item, true);
		// }
		// }

		// // charm
		// NonNullList<ItemStack> charms = DCUtil.getPlayerCharm(player, CharmType.DEFFENCE);
		// DamageSource source = tempTier > 0 ? DamageSourceClimate.climateHeatDamage :
		// DamageSourceClimate.climateColdDamage;
		// for (ItemStack check : charms) {
		// IJewelCharm charm = (IJewelCharm) check.getItem();
		// if (isCold)
		// coldPrev += charm.reduceDamage(source, check);
		// else
		// heatPrev += charm.reduceDamage(source, check);
		// }

		// items = null;
		// charms = null;

		if (player.level.getDifficulty() != Difficulty.PEACEFUL || CoreConfigDC.peacefulDam) {
			if (isCold) {
				damage = (tempTier + conf_prev) * 2;
				damage += coldPrev;
				if (damage > 0F) {
					damage = 0F;
				}
			} else {
				damage = tempTier - conf_prev;
				damage -= heatPrev;
				if (damage < 0F) {
					damage = 0F;
				}
			}
		}

		iconTier = 2;
		if (damage > 0F) {
			if (damage >= 2F) {
				iconTier = 4;
			} else if (damage >= 1F) {
				iconTier = 3;
			}
		} else {
			if (damage <= -2F) {
				iconTier = 0;
			} else if (damage <= -1F) {
				iconTier = 1;
			}
		}

		// DCLogger.debugLog("=client climate info=");
		// DCLogger.debugLog("temp:" + tempTier + ", icon: " + iconTier);
		// DCLogger.debugLog("registance h:" + heatPrev + " c:" + coldPrev);
	}

	public int getTempTier() {
		return tempTier;
	}

	public int getIconTier() {
		return iconTier;
	}

	public float getArmorHeatPrev() {
		return heatPrev;
	}

	public float getArmorColdPrev() {
		return coldPrev;
	}

	public IClimate getClimate() {
		return climate == null ?
				ClimateAPI.helper.getClimateFromParam(DCHeatTier.NORMAL, DCHumidity.NORMAL, DCAirflow.FLOW) : climate;
	}

	private static DCHeatTier getBlockTemp(BlockState block, Level world, BlockPos pos) {
		if (ClimateAPI.registerBlock.isRegisteredHeat(block)) {
			return ClimateAPI.registerBlock.getHeatTier(block).orElse(DCHeatTier.NORMAL);
		} else if (block instanceof IHeatTile) {
			return ((IHeatTile) block).getHeatTier(world, pos, pos);
		} else if (block instanceof IFluidBlock) {
			Fluid f = ((IFluidBlock) block).getFluid();
			if (f != null) {
				return DCHeatTier.getTypeByTemperature(f.getFluidType().getTemperature());
			}
		}
		return DCHeatTier.NORMAL;
	}

}
