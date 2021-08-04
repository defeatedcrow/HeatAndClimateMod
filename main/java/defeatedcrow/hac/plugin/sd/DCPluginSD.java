package defeatedcrow.hac.plugin.sd;

import com.charles445.simpledifficulty.api.SDBlocks;
import com.charles445.simpledifficulty.api.SDCapabilities;
import com.charles445.simpledifficulty.api.SDItems;
import com.charles445.simpledifficulty.api.config.JsonConfig;
import com.charles445.simpledifficulty.api.config.json.JsonItemIdentity;
import com.charles445.simpledifficulty.api.temperature.ITemperatureCapability;
import com.charles445.simpledifficulty.block.BlockCampfire;
import com.charles445.simpledifficulty.block.BlockTemperature;

import defeatedcrow.hac.api.climate.BlockHeatTierEvent;
import defeatedcrow.hac.api.climate.BlockHeatTierEvent.EventType;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;
import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DCPluginSD {

	public static final DCPluginSD INSTANCE = new DCPluginSD();

	public static void loadInit() {
		// linen
		register(MainInit.linenUnder, -1.0F);
		register(MainInit.linenShirt, -1.0F);
		register(MainInit.linenJacket, -1.0F);
		register(MainInit.linenBottom, -1.0F);
		register(MainInit.linenCoat, -1.0F);

		// cotton
		register(MainInit.clothUnder, 1.0F);
		register(MainInit.clothShirt, 1.0F);
		register(MainInit.clothJacket, 2.0F);
		register(MainInit.clothBottom, 1.0F);
		register(MainInit.clothCoat, 2.0F);
		register(MainInit.clothSkirt, 1.0F);
		register(MainInit.cottonHat, 1.0F);

		register(MainInit.workerSuit, -1.0F);

		// lether
		register(MainInit.leatherHat, -1.0F);

		// wool
		register(MainInit.woolBoots, 2.0F);
		register(MainInit.woolJacket, 2.0F);
		register(MainInit.woolWear, 3.0F);
		register(MainInit.peaCoat, 3.0F);
		register(MainInit.modsCoat, 3.0F);
		register(MainInit.hoodie, 2.0F);
		register(MainInit.furWear, 3.0F);
		register(MainInit.furCape, 2.0F);

		// silk
		register(MainInit.silkDress, -2.0F);
		register(MainInit.silkCape, -2.0F);
		register(MainInit.silkSkirt, -2.0F);
		register(MainInit.blackCoat, -1.0F);
		register(MainInit.blackSuit, -1.0F);

		register(MainInit.magicUnder, 1.0F);
		register(MainInit.magicCoat, 2.0F);

		// synthetic
		register(MainInit.trackSuit, -2.0F);
		register(MainInit.combatDress, -1.0F);

		// sd materials
		DamageAPI.armorRegister.registerMaterial(SDItems.iceArmorMaterial, 4.0F, 0.0F);
		DamageAPI.armorRegister.registerMaterial(SDItems.woolArmorMaterial, 1.0F, 3.0F);

		MinecraftForge.EVENT_BUS.register(INSTANCE);
	}

	private static void register(Item item, float data) {
		if (item != null) {
			JsonConfig.registerArmorTemperature(item.getRegistryName().toString(), data, new JsonItemIdentity(-1));
		}
	}

	@SubscribeEvent
	public void onHeatDamage(LivingHurtEvent event) {
		if (event.getSource() instanceof DamageSourceClimate && event.getEntityLiving() instanceof EntityPlayer & !event
				.getEntityLiving().getEntityWorld().isRemote) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (player.hasCapability(SDCapabilities.TEMPERATURE, null)) {
				ITemperatureCapability cap = player.getCapability(SDCapabilities.TEMPERATURE, null);
				if (event.getSource() == DamageSourceClimate.climateHeatDamage)
					cap.addTemperatureLevel(1);
				if (event.getSource() == DamageSourceClimate.climateColdDamage)
					cap.addTemperatureLevel(-1);
			}

		}
	}

	@SubscribeEvent
	public void onBlockTempEvent(BlockHeatTierEvent event) {
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		if (world != null && pos != null) {
			IBlockState state = world.getBlockState(pos);

			if (state.getBlock() == SDBlocks.campfire && state.getValue(BlockCampfire.BURNING))
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.OVEN.getTier()) {
					event.setNewClimate(DCHeatTier.OVEN);
					event.setResult(Result.ALLOW);
				}

			if (state.getBlock() == SDBlocks.heater && state.getValue(BlockTemperature.ENABLED))
				if (event.getType() == EventType.COLD || event.currentClimate().getTier() < DCHeatTier.KILN.getTier()) {
					event.setNewClimate(DCHeatTier.KILN);
					event.setResult(Result.ALLOW);
				}

			if (state.getBlock() == SDBlocks.chiller && state.getValue(BlockTemperature.ENABLED))
				if (event.getType() == EventType.HOT || event.currentClimate().getTier() > DCHeatTier.COLD.getTier()) {
					event.setNewClimate(DCHeatTier.COLD);
					event.setResult(Result.ALLOW);
				}
		}
	}

}
