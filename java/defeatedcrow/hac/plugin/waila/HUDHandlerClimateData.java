package defeatedcrow.hac.plugin.waila;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ClimateDoubleCropBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import mcp.mobius.waila.addons.core.HUDHandlerBlocks;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.SpecialChars;
import mcp.mobius.waila.cbcore.LangUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// climate data
public class HUDHandlerClimateData extends HUDHandlerBlocks {

	static final List<BlockSet> targetHeatList = Lists.newArrayList();
	static final List<BlockSet> targetHumList = Lists.newArrayList();
	static final List<BlockSet> targetAirList = Lists.newArrayList();

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		Block block = accessor.getBlock();
		if (block == null)
			return ItemStack.EMPTY;

		if (ModuleConfig.food && config.getConfig("dcs_climate.showcrop")) {
			if (block == FoodInit.cropRice)
				return new ItemStack(FoodInit.crops, 1, 0);
			else if (block == FoodInit.cropOnion)
				return new ItemStack(FoodInit.crops, 1, 1);
			else if (block == FoodInit.cropSpinach)
				return new ItemStack(FoodInit.crops, 1, 2);
			else if (block == FoodInit.cropTomato)
				return new ItemStack(FoodInit.crops, 1, 3);
			else if (block == FoodInit.cropCoffee)
				return new ItemStack(FoodInit.crops, 1, 4);
			else if (block == FoodInit.cropCotton)
				return new ItemStack(FoodInit.crops, 1, 5);
			else if (block == FoodInit.leavesLemon)
				return new ItemStack(FoodInit.crops, 1, 6);
			else if (block == FoodInit.leavesOlive)
				return new ItemStack(FoodInit.crops, 1, 7);
			else if (block == FoodInit.leavesTea)
				return new ItemStack(FoodInit.crops, 1, 8);
			else if (block == FoodInit.cropLotus)
				return new ItemStack(FoodInit.crops, 1, 10);
			else if (block == FoodInit.saplings) {
				int meta = accessor.getMetadata();
				switch (meta) {
				case 0:
					return new ItemStack(FoodInit.saplings, 1, 0);
				case 1:
					return new ItemStack(FoodInit.saplings, 1, 1);
				case 2:
					return new ItemStack(FoodInit.saplings, 1, 2);
				default:
					return new ItemStack(FoodInit.saplings, 1, 3);
				}
			}
		}

		return ItemStack.EMPTY;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		Block block = accessor.getBlock();
		int meta = accessor.getMetadata();

		if (block == null)
			return currenttip;

		BlockSet set = new BlockSet(block, meta);

		if (config.getConfig("dcs_climate.showclimate")) {

			if (targetHeatList.contains(set)) {
				DCHeatTier heat = ClimateAPI.registerBlock.getHeatTier(block, meta);
				if (heat.getTier() < 0) {
					currenttip.add(String.format("Temperature: %s", SpecialChars.AQUA + heat));
				} else {
					currenttip.add(String.format("Temperature: %s", SpecialChars.GOLD + heat));
				}
			}

			if (targetHumList.contains(set)) {
				DCHumidity hum = ClimateAPI.registerBlock.getHumidity(block, meta);
				currenttip.add(String.format("Humidity: %s", SpecialChars.AQUA + hum));
			}

			if (targetAirList.contains(set)) {
				DCAirflow air = ClimateAPI.registerBlock.getAirflow(block, meta);
				currenttip.add(String.format("Airflow: %s", SpecialChars.GREEN + air));
			}
		}

		if (config.getConfig("dcs_climate.showcrop")) {

			if (ClimateCropBase.class.isInstance(block) && block != FoodInit.saplings) {
				ClimateCropBase crop = (ClimateCropBase) block;
				int stage = meta & 3;
				float growthValue = (stage / (float) crop.getGrownMetadata()) * 100.0F;
				if (growthValue < 100.0) {
					currenttip.add(String.format("%s : %.0f %%", LangUtil.translateG("hud.msg.growth"), growthValue));
				} else {
					currenttip.add(String.format("%s : %s", LangUtil.translateG("hud.msg.growth"), LangUtil.translateG(
							"hud.msg.mature")));
				}
			} else if (ClimateDoubleCropBase.class.isInstance(block)) {
				ClimateDoubleCropBase crop = (ClimateDoubleCropBase) block;
				int stage = meta & 7;
				float growthValue = (stage / (float) crop.getGrownMetadata()) * 100.0F;
				if (growthValue < 100.0) {
					currenttip.add(String.format("%s : %.0f %%", LangUtil.translateG("hud.msg.growth"), growthValue));
				} else {
					currenttip.add(String.format("%s : %s", LangUtil.translateG("hud.msg.growth"), LangUtil.translateG(
							"hud.msg.mature")));
				}
			}
		}

		if (config.getConfig("dcs_climate.showvanillafarmland")) {
			if (BlockFarmland.class.isInstance(block)) {
				int stage = meta & 7;
				float wetValue = (stage / 7.0F) * 100.0F;
				currenttip.add(String.format("%s : %.0f %%", LangUtil.translateG("Moisture"), wetValue));
			}
		}

		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return te.writeToNBT(tag);
	}

	public static void register(IWailaRegistrar registrar) {
		registrar.addConfig("HeatAndClimate", "dcs_climate.showclimate", true);
		registrar.addConfig("HeatAndClimate", "dcs_climate.showcrops", true);
		registrar.addConfig("HeatAndClimate", "dcs_climate.showvanillafarmland", true);

		HUDHandlerClimateData provider = new HUDHandlerClimateData();

		registrar.registerStackProvider(provider, FoodInit.cropRice.getClass());
		registrar.registerStackProvider(provider, FoodInit.cropOnion.getClass());
		registrar.registerStackProvider(provider, FoodInit.cropSpinach.getClass());
		registrar.registerStackProvider(provider, FoodInit.cropTomato.getClass());
		registrar.registerStackProvider(provider, FoodInit.cropCoffee.getClass());
		registrar.registerStackProvider(provider, FoodInit.cropCotton.getClass());
		registrar.registerStackProvider(provider, FoodInit.leavesLemon.getClass());
		registrar.registerStackProvider(provider, FoodInit.leavesOlive.getClass());
		registrar.registerStackProvider(provider, FoodInit.leavesTea.getClass());
		registrar.registerStackProvider(provider, FoodInit.leavesMorus.getClass());
		registrar.registerStackProvider(provider, FoodInit.saplings.getClass());

		registrar.registerBodyProvider(provider, ClimateCropBase.class);
		registrar.registerBodyProvider(provider, ClimateDoubleCropBase.class);

		registrar.registerBodyProvider(provider, BlockFarmland.class);

		targetHeatList.addAll(ClimateAPI.registerBlock.getHeatList().keySet());
		if (!targetHeatList.isEmpty()) {
			for (BlockSet set : targetHeatList) {
				if (set != null && set.block != null) {
					registrar.registerBodyProvider(provider, set.block.getClass());
				}
			}
		}

		targetHumList.addAll(ClimateAPI.registerBlock.getHumList().keySet());
		if (!targetHumList.isEmpty()) {
			for (BlockSet set : targetHumList) {
				if (set != null && set.block != null) {
					registrar.registerBodyProvider(provider, set.block.getClass());
				}
			}
		}

		targetAirList.addAll(ClimateAPI.registerBlock.getAirList().keySet());
		if (!targetAirList.isEmpty()) {
			for (BlockSet set : targetAirList) {
				if (set != null && set.block != null) {
					registrar.registerBodyProvider(provider, set.block.getClass());
				}
			}
		}
	}
}
