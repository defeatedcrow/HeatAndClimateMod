package defeatedcrow.hac.core;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.InputConstants;

import defeatedcrow.hac.core.client.ClientRegisterInit;
import defeatedcrow.hac.core.client.ColorHandlerRegister;
import defeatedcrow.hac.core.client.DCTextureStitch;
import defeatedcrow.hac.core.client.event.AdvTooltipEvent;
import defeatedcrow.hac.core.client.event.BlockHitEffectsEvent;
import defeatedcrow.hac.core.client.event.ClimateHUDEvent;
import defeatedcrow.hac.core.client.event.RenderPlayerEventDC;
import defeatedcrow.hac.core.client.event.SoundMufflerEvent;
import defeatedcrow.hac.core.client.gui.DisplayCaseScreen_Owner;
import defeatedcrow.hac.core.client.gui.DisplayShelfScreen;
import defeatedcrow.hac.core.client.gui.DoubleInventoryScreen;
import defeatedcrow.hac.core.client.gui.SimpleInventoryScreen;
import defeatedcrow.hac.core.client.gui.UnlockedInventoryScreen;
import defeatedcrow.hac.core.climate.ClientClimateData;
import defeatedcrow.hac.core.config.ConfigClientBuilder;
import defeatedcrow.hac.core.event.CharmTriggerEvent;
import defeatedcrow.hac.core.event.ClientTickEventDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.machine.client.gui.BoilerBiomassScreen;
import defeatedcrow.hac.machine.client.gui.CoffeeMakerScreen;
import defeatedcrow.hac.machine.client.gui.ConveyorSorterScreen;
import defeatedcrow.hac.machine.client.gui.CookingPotScreen;
import defeatedcrow.hac.machine.client.gui.EnergyBatteryScreen;
import defeatedcrow.hac.machine.client.gui.EnergyGeneratorScreen;
import defeatedcrow.hac.machine.client.gui.FermentationJarScreen;
import defeatedcrow.hac.machine.client.gui.FluidChamberScreen;
import defeatedcrow.hac.machine.client.gui.HeatingChamberScreen;
import defeatedcrow.hac.machine.client.gui.HopperEXPScreen;
import defeatedcrow.hac.machine.client.gui.HopperFilterScreen;
import defeatedcrow.hac.machine.client.gui.KichenBenchScreen;
import defeatedcrow.hac.machine.client.gui.KichenOvenScreen;
import defeatedcrow.hac.machine.client.gui.KichenStoveScreen;
import defeatedcrow.hac.machine.client.gui.MillScreen;
import defeatedcrow.hac.machine.client.gui.MonitorAndonScreen;
import defeatedcrow.hac.machine.client.gui.PortableTankScreen;
import defeatedcrow.hac.machine.client.gui.RollCrusherScreen;
import defeatedcrow.hac.machine.client.gui.TeaPotScreen;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.magic.client.gui.BlackRodScreen;
import defeatedcrow.hac.magic.client.gui.BoringScreen;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClientProxyDC extends CommonProxyDC {

	@Override
	public void addListener(IEventBus bus) {
		bus.addListener(ClientRegisterInit::registerLayerDefinitions);
		bus.addListener(ClientRegisterInit::registerEntityRenderers);
		bus.addListener(ClientRegisterInit::registerClientReloadListeners);
		bus.addListener(ClientRegisterInit::registerParticle);
		bus.addListener(ColorHandlerRegister::registerBlockColorHandler);
		bus.addListener(ColorHandlerRegister::registerItemColorHandler);
	}

	@Override
	public void registerEvent() {
		super.registerEvent();
		MinecraftForge.EVENT_BUS.addListener(ClientTickEventDC::onClientTick);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::render);
		MinecraftForge.EVENT_BUS.addListener(ClimateHUDEvent::renderScreen);
		MinecraftForge.EVENT_BUS.addListener(RenderPlayerEventDC::renderWings);
		MinecraftForge.EVENT_BUS.addListener(DCTextureStitch::register);
		MinecraftForge.EVENT_BUS.addListener(AdvTooltipEvent::render);
		MinecraftForge.EVENT_BUS.addListener(CharmTriggerEvent::onPotionEffectColor);
		MinecraftForge.EVENT_BUS.addListener(SoundMufflerEvent::onClientPlaySound);
		MinecraftForge.EVENT_BUS.addListener(BlockHitEffectsEvent::onBlockHitEffects);
	}

	@Override
	public void commonInit() {
		super.commonInit();

		MenuScreens.register(CoreInit.SIMPLE_SINGLE.get(), SimpleInventoryScreen::new);
		MenuScreens.register(CoreInit.SIMPLE_DOUBLE.get(), DoubleInventoryScreen::new);
		MenuScreens.register(CoreInit.UNLOCKED_DOUBLE.get(), UnlockedInventoryScreen::new);
		MenuScreens.register(BuildInit.DISPLAY_SHELF_MENU.get(), DisplayShelfScreen::new);
		MenuScreens.register(BuildInit.DISPLAY_DOUBLE_SHELF_MENU.get(), DisplayShelfScreen::new);
		MenuScreens.register(BuildInit.DISPLAY_CASE_OWNER_MENU.get(), DisplayCaseScreen_Owner::new);
		MenuScreens.register(MachineInit.CHAMBER_MENU.get(), HeatingChamberScreen::new);
		MenuScreens.register(MachineInit.FLUID_CHAMBER_MENU.get(), FluidChamberScreen::new);
		MenuScreens.register(MachineInit.KICHEN_STOVE_MENU.get(), KichenStoveScreen::new);
		MenuScreens.register(MachineInit.KICHEN_OVEN_MENU.get(), KichenOvenScreen::new);
		MenuScreens.register(MachineInit.FLUID_MENU.get(), PortableTankScreen::new);
		MenuScreens.register(MachineInit.FLUID_MENU_LARGE.get(), PortableTankScreen::new);
		MenuScreens.register(MachineInit.POT_MENU.get(), CookingPotScreen::new);
		MenuScreens.register(MachineInit.TEA_POT_MENU.get(), TeaPotScreen::new);
		MenuScreens.register(MachineInit.COFFEE_MAKER_MENU.get(), CoffeeMakerScreen::new);
		MenuScreens.register(MachineInit.JAR_MENU.get(), FermentationJarScreen::new);
		MenuScreens.register(MachineInit.MILL_MENU.get(), MillScreen::new);
		MenuScreens.register(MachineInit.CRUSHER_MENU.get(), RollCrusherScreen::new);
		MenuScreens.register(MachineInit.BATTERY_MENU.get(), EnergyBatteryScreen::new);
		MenuScreens.register(MachineInit.GENERATOR_MENU.get(), EnergyGeneratorScreen::new);
		MenuScreens.register(MachineInit.BOILER_BIOMASS_MENU.get(), BoilerBiomassScreen::new);
		MenuScreens.register(MachineInit.HOPPER_FILTER_MENU.get(), HopperFilterScreen::new);
		MenuScreens.register(MachineInit.HOPPER_GOLD_MENU.get(), HopperFilterScreen::new);
		MenuScreens.register(MachineInit.HOPPER_FILTER_GOLD_MENU.get(), HopperFilterScreen::new);
		MenuScreens.register(MachineInit.HOPPER_EXP_MENU.get(), HopperEXPScreen::new);
		MenuScreens.register(MachineInit.CONVEYOR_SORTER_MENU.get(), ConveyorSorterScreen::new);
		MenuScreens.register(MachineInit.KICHEN_BENCH_MENU.get(), KichenBenchScreen::new);
		MenuScreens.register(MachineInit.MONITOR_ANDON_MENU.get(), MonitorAndonScreen::new);
		MenuScreens.register(MagicInit.BORING_SURVEY_MENU.get(), BoringScreen::new);
		MenuScreens.register(MagicInit.BLACK_ROD_MENU.get(), BlackRodScreen::new);

		ClientRegisterInit.registerRenderTypes();
	}

	@Override
	public void updatePlayerClimate() {
		ClientClimateData.INSTANCE.updatePlayerClimate();
	}

	@Override
	public boolean keyShiftPushed() {
		return InputConstants.isKeyDown(Minecraft.getInstance()
		    .getWindow()
		    .getWindow(), ConfigClientBuilder.INSTANCE.key_Desc.get());
	}

	@Override
	public boolean keyHUDPushed() {
		return InputConstants.isKeyDown(Minecraft.getInstance()
		    .getWindow()
		    .getWindow(), ConfigClientBuilder.INSTANCE.key_HUD.get());
	}

	@Override
	public boolean keyCharmPushed() {
		return InputConstants.isKeyDown(Minecraft.getInstance()
		    .getWindow()
		    .getWindow(), ConfigClientBuilder.INSTANCE.key_Charm.get());
	}

	@Override
	public boolean keyJumpPushed() {
		return Minecraft.getInstance().player.input.jumping;
	}

	@Override
	public boolean keySneakPushed() {
		return Minecraft.getInstance().player.input.shiftKeyDown;
	}

	@Override
	public boolean keyFowardPushed() {
		return Minecraft.getInstance().player.input.hasForwardImpulse();
	}

	@Override
	public Vec2 getClientFoward() {
		return Minecraft.getInstance().player.input.getMoveVector();
	}

	@Override
	public Optional<Level> getClientLevel() {
		return Optional.of(Minecraft.getInstance().level);
	}

	@Override
	public Optional<Player> getClientPlayer() {
		return Optional.of(Minecraft.getInstance().player);
	}

	@Override
	public boolean isOP(Player player) {
		return true;
	}

	/**
	 * @date 2026.4.18 @author Hiiragi283 Thanks!
	 */
	@Override
	public List<TagKey<Biome>> getBiomeTags(Level level, Biome biome) {
		List<TagKey<Biome>> list = Lists.newArrayList();
		if (getClientLevel().isPresent()) {
			Registry<Biome> registry = getClientLevel().get()
			    .registryAccess()
			    .registryOrThrow(Registries.BIOME);
			Optional<ResourceKey<Biome>> key = registry.getResourceKey(biome);
			key.ifPresent(k -> {
			    registry.getHolder(k)
			        .ifPresent(holder -> holder.getTagKeys()
			            .forEach(tag -> list.add(tag)));
			});
		}
		return list;
	}

	// @Override
	// public void triggerAdvancement(LivingEntity player, String res) {}
}
