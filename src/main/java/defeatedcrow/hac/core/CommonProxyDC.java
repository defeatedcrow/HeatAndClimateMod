package defeatedcrow.hac.core;

import java.util.Optional;

import defeatedcrow.hac.core.config.ConfigLoadEventDC;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.event.AnvilEventDC;
import defeatedcrow.hac.core.event.BiomeBaseTempEventDC;
import defeatedcrow.hac.core.event.BlockEventDC;
import defeatedcrow.hac.core.event.BlockUpdateEventDC;
import defeatedcrow.hac.core.event.CharmTriggerEvent;
import defeatedcrow.hac.core.event.LivingEventDC;
import defeatedcrow.hac.core.event.LivingTickEventDC;
import defeatedcrow.hac.core.event.ServerTickEventDC;
import defeatedcrow.hac.core.event.ThunderstruckEventDC;
import defeatedcrow.hac.core.event.WandererTradeEventDC;
import defeatedcrow.hac.core.json.JsonInit;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.recipe.device.DeviceRecipeConfig;
import defeatedcrow.hac.core.recipe.device.DeviceRecipeList;
import defeatedcrow.hac.core.recipe.fuel.FuelConfig;
import defeatedcrow.hac.core.recipe.fuel.FuelList;
import defeatedcrow.hac.core.recipe.metal.HeatTreatmentList;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmeltingConfig;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmeltingList;
import defeatedcrow.hac.food.FoodProxy;
import defeatedcrow.hac.food.event.FishingEventDC;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class CommonProxyDC {

	public void addListener(IEventBus bus) {}

	public void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(BiomeBaseTempEventDC::onTemp);
		MinecraftForge.EVENT_BUS.addListener(ServerTickEventDC::onTickEvent);
		MinecraftForge.EVENT_BUS.addListener(LivingTickEventDC::onLivingTick);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onBlockUpdate);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onCropUpdate);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onBlockPlacement);
		MinecraftForge.EVENT_BUS.addListener(ConfigLoadEventDC::onLoad);
		MinecraftForge.EVENT_BUS.addListener(ConfigLoadEventDC::onFileChange);
		MinecraftForge.EVENT_BUS.addListener(LivingEventDC::onDrop);
		MinecraftForge.EVENT_BUS.addListener(LivingEventDC::onSpawnCheck);
		MinecraftForge.EVENT_BUS.addListener(BlockEventDC::onFarmLand);
		MinecraftForge.EVENT_BUS.addListener(BlockEventDC::onDig);
		MinecraftForge.EVENT_BUS.addListener(BlockEventDC::onBreakBlock);
		MinecraftForge.EVENT_BUS.addListener(BlockEventDC::onClickBlock);
		MinecraftForge.EVENT_BUS.addListener(CharmTriggerEvent::onAttack);
		MinecraftForge.EVENT_BUS.addListener(CharmTriggerEvent::onHurt);
		MinecraftForge.EVENT_BUS.addListener(CharmTriggerEvent::onDeath);
		MinecraftForge.EVENT_BUS.addListener(CharmTriggerEvent::onDig);
		MinecraftForge.EVENT_BUS.addListener(CharmTriggerEvent::onPotionEffectColor);
		MinecraftForge.EVENT_BUS.addListener(CharmTriggerEvent::onXpPickup);
		MinecraftForge.EVENT_BUS.addListener(FishingEventDC::onFishing);
		MinecraftForge.EVENT_BUS.addListener(AnvilEventDC::onEvent);
		MinecraftForge.EVENT_BUS.addListener(WandererTradeEventDC::onLoadingTrade);
		MinecraftForge.EVENT_BUS.addListener(ThunderstruckEventDC::onLodUpdate);

		FoodProxy.registerEvent();
	}

	public void commonInit() {
		JsonInit.init();
		CoreConfigDC.loadConfig();

		FoodProxy.commonInit();
	}

	public void updatePlayerClimate() {}

	public void registerRecipes() {
		DCRecipes.INSTANCE.init();

		ClimateSmeltingList.init();
		ClimateSmeltingConfig.initFile();

		HeatTreatmentList.init();

		DeviceRecipeList.init();
		DeviceRecipeConfig.initFile();

		FuelList.init();
		FuelConfig.initFile();
	};

	public boolean keyShiftPushed() {
		return false;
	}

	public boolean keyHUDPushed() {
		return false;
	}

	public boolean keyCharmPushed() {
		return false;
	}

	public boolean keyJumpPushed() {
		return false;
	}

	public boolean keySneakPushed() {
		return false;
	}

	public boolean keyFowardPushed() {
		return false;
	}

	public Vec2 getClientFoward() {
		return Vec2.ZERO;
	}

	public Optional<Level> getClientLevel() {
		return Optional.empty();
	}

	public void triggerAdvancement(LivingEntity player, String res) {
		if (player instanceof ServerPlayer) {
			ServerPlayer serverplayer = (ServerPlayer) player;
			Advancement adv = serverplayer.server.getAdvancements().getAdvancement(new ResourceLocation(ClimateCore.MOD_ID + ":" + res));
			if (adv != null)
				((ServerPlayer) player).getAdvancements().award(adv, "impossible");
		}
	}

	public boolean isAdvancementDone(LivingEntity player, String res) {
		if (player instanceof ServerPlayer) {
			ServerPlayer serverplayer = (ServerPlayer) player;
			Advancement adv = serverplayer.server.getAdvancements().getAdvancement(new ResourceLocation(ClimateCore.MOD_ID + ":" + res));
			if (adv != null && adv.getDisplay() != null) {
				PlayerAdvancements advs = serverplayer.server.getPlayerList().getPlayerAdvancements(serverplayer);
				return advs != null && advs.getOrStartProgress(adv).isDone();
			}
		}
		return false;
	}

	public boolean isOP(Player player) {
		if (player == null)
			return false;
		return player.getLevel().getServer().getPlayerList().isOp(player.getGameProfile());
	}

	public Player getPlayer(ServerLevel level, String name) {
		if (name == null)
			return null;
		return level.getServer().getPlayerList().getPlayerByName(name);
	}

}
