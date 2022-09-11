package defeatedcrow.hac.core.climate;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

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
		IItemHandler handler =
				player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.NORTH).orElse(null);
		if (handler != null) {
			for (int s = 0; s < handler.getSlots(); s++) {
				ItemStack item = handler.getStackInSlot(s);
				if (item.isEmpty())
					continue;

				float p = DCItemUtil.getItemResistantData(item, false);
				heatPrev += p;
				float p2 = DCItemUtil.getItemResistantData(item, true);
				coldPrev += p2;
			}
		}

		// charm
		NonNullList<ItemStack> charms = DCItemUtil.getCharms(player, CharmType.ALL);
		DamageSource source = tempTier > 0 ? DamageSourceClimate.climateHeatDamage :
				DamageSourceClimate.climateColdDamage;
		for (ItemStack check : charms) {
			IJewelCharm charm = (IJewelCharm) check.getItem();
			if (isCold)
				coldPrev += charm.reduceDamage(player, DamageSourceClimate.climateColdDamage, damage, check);
			else
				heatPrev += charm.reduceDamage(player, DamageSourceClimate.climateHeatDamage, damage, check);
		}
		charms.clear();

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
