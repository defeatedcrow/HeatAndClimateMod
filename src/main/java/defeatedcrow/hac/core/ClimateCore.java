package defeatedcrow.hac.core;

import java.io.File;
import java.nio.file.Path;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.core.climate.ClimateCalculator;
import defeatedcrow.hac.core.climate.ClimateHelper;
import defeatedcrow.hac.core.climate.register.ArmorItemRegister;
import defeatedcrow.hac.core.climate.register.ArmorMaterialRegister;
import defeatedcrow.hac.core.climate.register.BiomeClimateRegister;
import defeatedcrow.hac.core.climate.register.BlockClimateRegister;
import defeatedcrow.hac.core.climate.register.MobResistanceRegister;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.json.JsonInit;
import defeatedcrow.hac.core.json.TileNBTFunction;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.tag.BiomeTagProviderDC;
import defeatedcrow.hac.core.material.tag.BlockTagProviderDC;
import defeatedcrow.hac.core.material.tag.ItemTagProviderDC;
import defeatedcrow.hac.core.material.tag.TagDC;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.worldgen.FeatureInit;
import defeatedcrow.hac.plugin.jei.PluginRecipeListDC;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(ClimateCore.MOD_ID)
public class ClimateCore {
	public static final String MOD_ID = "dcs_climate";

	public static final CommonProxyDC proxy = DistExecutor.unsafeRunForDist(() -> ClientProxyDC::new, () -> CommonProxyDC::new);

	public static File configDir = null;
	public static File assetsDir = null;
	public static File dataDir = null;

	public static boolean isDebug = true;

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

	public ClimateCore() {
		initAPI();
		configDir = new File(FMLPaths.CONFIGDIR.get().toFile() + "/heat_and_climate");
		if (isDebug) {
			Path path = FMLPaths.GAMEDIR.get();
			if (path.toString().contains("run")) {
				assetsDir = new File(path.toFile().getParent() + "/src/main/resources/assets/dcs_climate/");
				dataDir = new File(path.toFile().getParent() + "/src/main/resources/data/dcs_climate/");
				DCLogger.debugInfoLog("path test1: " + assetsDir.getPath());
				DCLogger.debugInfoLog("path test2: " + dataDir.getPath());
			}
		}

		CoreInit.init();
		FeatureInit.init();

		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		CoreInit.BLOCKS.register(bus);
		CoreInit.ITEMS.register(bus);
		CoreInit.FLUIDS.register(bus);
		ENTITIES.register(bus);
		FeatureInit.FEATURES.register(bus);

		TagDC.init();

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::gatherData);

		proxy.registerEvent();
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
		JsonInit.init();
		CoreConfigDC.loadConfig();
		DCPacket.INSTANCE.init();
		event.enqueueWork(TileNBTFunction::init);

		PluginRecipeListDC.init();
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		BlockTagProviderDC blockTag = new BlockTagProviderDC(generator, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTag);
		generator.addProvider(event.includeServer(), new ItemTagProviderDC(generator, blockTag, existingFileHelper));
		generator.addProvider(event.includeServer(), new BiomeTagProviderDC(generator, existingFileHelper));
	}

	public void clientSetup(FMLClientSetupEvent event) {

	}
}
