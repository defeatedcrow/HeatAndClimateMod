package defeatedcrow.hac.core;

import java.io.File;
import java.nio.file.Path;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.core.advancement.AdvancementProviderDC;
import defeatedcrow.hac.core.advancement.trigger.TriggersDC;
import defeatedcrow.hac.core.climate.ClimateCalculator;
import defeatedcrow.hac.core.climate.ClimateHelper;
import defeatedcrow.hac.core.climate.register.ArmorItemRegister;
import defeatedcrow.hac.core.climate.register.ArmorMaterialRegister;
import defeatedcrow.hac.core.climate.register.BiomeClimateRegister;
import defeatedcrow.hac.core.climate.register.BlockClimateRegister;
import defeatedcrow.hac.core.climate.register.MobResistanceRegister;
import defeatedcrow.hac.core.config.ConfigClientBuilder;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.config.ConfigServerBuilder;
import defeatedcrow.hac.core.json.TileNBTFunction;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.recipe.MaterialRecipes;
import defeatedcrow.hac.core.recipe.vanilla.VanillaRecipeProvider;
import defeatedcrow.hac.core.tag.BiomeTagProviderDC;
import defeatedcrow.hac.core.tag.BlockTagProviderDC;
import defeatedcrow.hac.core.tag.FluidTagProviderDC;
import defeatedcrow.hac.core.tag.ItemTagProviderDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.worldgen.FeatureInit;
import defeatedcrow.hac.food.recipe.FoodRecipeProvider;
import defeatedcrow.hac.machine.recipe.MachineRecipeProvider;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.CrowTurretEntity;
import defeatedcrow.hac.magic.recipe.MagicRecipeProvider;
import defeatedcrow.hac.plugin.PluginDC;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(ClimateCore.MOD_ID)
public class ClimateCore {
	public static final String MOD_ID = "dcs_climate";

	public static final CommonProxyDC proxy = DistExecutor.unsafeRunForDist(() -> ClientProxyDC::new, () -> CommonProxyDC::new);

	public static File configDir = null;
	public static File assetsDir = null;
	public static File dataDir = null;

	public static boolean isDebug = false;

	public ClimateCore() {
		initAPI();
		configDir = new File(FMLPaths.CONFIGDIR.get().toFile() + "/heat_and_climate");
		isDebug = DCLogger.checkDebugModePass();

		if (isDebug) {
			Path path = FMLPaths.GAMEDIR.get();
			if (path.toString().contains("run")) {
				assetsDir = new File(path.toFile().getParent() + "/src/main/resources/assets/dcs_climate/");
				dataDir = new File(path.toFile().getParent() + "/src/main/resources/data/dcs_climate/");
				DCLogger.debugInfoLog("path test1: " + assetsDir.getPath());
				DCLogger.debugInfoLog("path test2: " + dataDir.getPath());
			}
		}

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigClientBuilder.CONFIG_CLIENT);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigCommonBuilder.CONFIG_COMMON);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigServerBuilder.CONFIG_SERVER);

		TagDC.init();
		CoreInit.init();
		FeatureInit.init();
		TriggersDC.init();

		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		CoreInit.BLOCKS.register(bus);
		CoreInit.BLOCK_ENTITIES.register(bus);
		CoreInit.ITEMS.register(bus);
		CoreInit.FLUID_TYPES.register(bus);
		CoreInit.FLUIDS.register(bus);
		CoreInit.EFFECTS.register(bus);
		CoreInit.POTIONS.register(bus);
		CoreInit.ENTITIES.register(bus);
		CoreInit.RECIPE_TYPE.register(bus);
		CoreInit.RECIPE_SEREALIZER.register(bus);
		CoreInit.MENU_TYPE.register(bus);
		CoreInit.PARTICLE_TYPE.register(bus);

		FeatureInit.FEATURES.register(bus);

		TagDC.init();
		MaterialRecipes.init();

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);
		bus.addListener(this::attributeRegister);

		proxy.addListener(bus);

		proxy.registerEvent();

		// forge milk
		ForgeMod.enableMilkFluid();

		PluginDC.init();
	}

	void initAPI() {
		ClimateAPI.calculator = new ClimateCalculator();
		ClimateAPI.helper = new ClimateHelper();
		ClimateAPI.registerBiome = BiomeClimateRegister.INSTANCE;
		ClimateAPI.registerBlock = new BlockClimateRegister();
		ClimateAPI.registerMaterial = new ArmorMaterialRegister();
		ClimateAPI.registerArmor = new ArmorItemRegister();
		ClimateAPI.registerMob = new MobResistanceRegister();
	}

	public void commonSetup(FMLCommonSetupEvent event) {

		proxy.commonInit();
		proxy.registerRecipes();

		DCPacket.INSTANCE.init();
		event.enqueueWork(TileNBTFunction::init);
	}

	public void attributeRegister(EntityAttributeCreationEvent event) {

		event.put(MagicInit.CROW_TURRET.get(), CrowTurretEntity.createAttributes().build());
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		BlockTagProviderDC blockTag = new BlockTagProviderDC(generator, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTag);
		generator.addProvider(event.includeServer(), new ItemTagProviderDC(generator, blockTag, existingFileHelper));
		generator.addProvider(event.includeServer(), new BiomeTagProviderDC(generator, existingFileHelper));
		generator.addProvider(event.includeServer(), new FluidTagProviderDC(generator, existingFileHelper));

		generator.addProvider(event.includeServer(), new VanillaRecipeProvider(generator));
		generator.addProvider(event.includeServer(), new FoodRecipeProvider(generator));
		generator.addProvider(event.includeServer(), new MagicRecipeProvider(generator));
		generator.addProvider(event.includeServer(), new MachineRecipeProvider(generator));

		generator.addProvider(event.includeServer(), new AdvancementProviderDC(generator, existingFileHelper));
	}

	public void clientSetup(FMLClientSetupEvent event) {

	}
}
