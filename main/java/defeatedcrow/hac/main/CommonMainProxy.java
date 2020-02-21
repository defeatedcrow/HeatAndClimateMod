package defeatedcrow.hac.main;

import defeatedcrow.hac.food.FoodCommonProxy;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;
import defeatedcrow.hac.food.block.TileSilkwormBox;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;
import defeatedcrow.hac.food.gui.ContainerSilkwormBox;
import defeatedcrow.hac.food.gui.ContainerTeaPot;
import defeatedcrow.hac.food.gui.GuiFluidProcessor;
import defeatedcrow.hac.food.gui.GuiSilkwormBox;
import defeatedcrow.hac.food.gui.GuiTeaPot;
import defeatedcrow.hac.food.recipes.FoodRecipes;
import defeatedcrow.hac.machine.MachineCommonProxy;
import defeatedcrow.hac.machine.block.TileDieselEngine;
import defeatedcrow.hac.machine.block.TileHopperFilter;
import defeatedcrow.hac.machine.block.TileHopperFluid;
import defeatedcrow.hac.machine.block.TilePortalManager;
import defeatedcrow.hac.machine.block.TilePressMachine;
import defeatedcrow.hac.machine.block.TileReactor;
import defeatedcrow.hac.machine.block.TileRollerCrusher;
import defeatedcrow.hac.machine.block.TileSpinningMachine;
import defeatedcrow.hac.machine.block.TileStoneMill;
import defeatedcrow.hac.machine.entity.EntityScooter;
import defeatedcrow.hac.machine.gui.ContainerCrusher;
import defeatedcrow.hac.machine.gui.ContainerDieselEngine;
import defeatedcrow.hac.machine.gui.ContainerEntityScooter;
import defeatedcrow.hac.machine.gui.ContainerHopperFilter;
import defeatedcrow.hac.machine.gui.ContainerHopperFluid;
import defeatedcrow.hac.machine.gui.ContainerPortalManager;
import defeatedcrow.hac.machine.gui.ContainerPressMachine;
import defeatedcrow.hac.machine.gui.ContainerReactor;
import defeatedcrow.hac.machine.gui.ContainerSpinning;
import defeatedcrow.hac.machine.gui.ContainerStoneMill;
import defeatedcrow.hac.machine.gui.GuiCrusher;
import defeatedcrow.hac.machine.gui.GuiDieselEngine;
import defeatedcrow.hac.machine.gui.GuiEntityScooter;
import defeatedcrow.hac.machine.gui.GuiHopperFilter;
import defeatedcrow.hac.machine.gui.GuiHopperFluid;
import defeatedcrow.hac.machine.gui.GuiPortalManager;
import defeatedcrow.hac.machine.gui.GuiPressMachine;
import defeatedcrow.hac.machine.gui.GuiReactor;
import defeatedcrow.hac.machine.gui.GuiSpinning;
import defeatedcrow.hac.machine.gui.GuiStoneMill;
import defeatedcrow.hac.machine.recipes.MachineRecipes;
import defeatedcrow.hac.magic.MagicCommonProxy;
import defeatedcrow.hac.magic.client.gui.ContainerLivingDC;
import defeatedcrow.hac.magic.client.gui.ContainerVillagerDC;
import defeatedcrow.hac.magic.client.gui.GuiLivingDC;
import defeatedcrow.hac.magic.client.gui.GuiVillagerDC;
import defeatedcrow.hac.magic.event.MagicCommonEvent;
import defeatedcrow.hac.magic.recipe.MagicRecipeRegister;
import defeatedcrow.hac.main.block.build.TileBedDC;
import defeatedcrow.hac.main.block.build.TileBedDCFuton;
import defeatedcrow.hac.main.block.build.TileBedDCRattan;
import defeatedcrow.hac.main.block.build.TileBedDCWhite;
import defeatedcrow.hac.main.block.build.TileChandelierChal;
import defeatedcrow.hac.main.block.build.TileChandelierGypsum;
import defeatedcrow.hac.main.block.build.TileChandelierSalt;
import defeatedcrow.hac.main.block.build.TileLowChest;
import defeatedcrow.hac.main.block.build.TileMCClock_L;
import defeatedcrow.hac.main.block.build.TileMagnetChest;
import defeatedcrow.hac.main.block.build.TileMetalChest;
import defeatedcrow.hac.main.block.build.TileRealtimeClock;
import defeatedcrow.hac.main.block.build.TileRealtimeClock_L;
import defeatedcrow.hac.main.block.build.TileVillageChest;
import defeatedcrow.hac.main.block.device.TileAcvShield;
import defeatedcrow.hac.main.block.device.TileBellow;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import defeatedcrow.hac.main.block.device.TileFirestand;
import defeatedcrow.hac.main.block.device.TileGeyser;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.block.device.TilePail;
import defeatedcrow.hac.main.block.device.TileShitirin;
import defeatedcrow.hac.main.block.device.TileSink;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import defeatedcrow.hac.main.block.device.TileThermometer;
import defeatedcrow.hac.main.block.device.TileWindVane;
import defeatedcrow.hac.main.client.gui.ContainerFuelStove;
import defeatedcrow.hac.main.client.gui.ContainerLowChest;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;
import defeatedcrow.hac.main.client.gui.ContainerStevensonScreen;
import defeatedcrow.hac.main.client.gui.GuiFuelStove;
import defeatedcrow.hac.main.client.gui.GuiLowChest;
import defeatedcrow.hac.main.client.gui.GuiNormalChamber;
import defeatedcrow.hac.main.client.gui.GuiStevensonScreen;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.WorldGenConfig;
import defeatedcrow.hac.main.enchant.EnchantmentRobber;
import defeatedcrow.hac.main.enchant.EnchantmentVenom;
import defeatedcrow.hac.main.entity.EntityBigCushion;
import defeatedcrow.hac.main.entity.EntityBigCushionBrown;
import defeatedcrow.hac.main.entity.EntityCrowBalloon;
import defeatedcrow.hac.main.entity.EntityCrowBullet;
import defeatedcrow.hac.main.entity.EntityCution;
import defeatedcrow.hac.main.entity.EntityDynamite;
import defeatedcrow.hac.main.entity.EntityDynamiteBlue;
import defeatedcrow.hac.main.entity.EntityExtinctionBullet;
import defeatedcrow.hac.main.entity.EntityFlowerPot;
import defeatedcrow.hac.main.entity.EntityGhostBullet;
import defeatedcrow.hac.main.entity.EntityIronBolt;
import defeatedcrow.hac.main.entity.EntityIronBullet;
import defeatedcrow.hac.main.entity.EntityLightBullet;
import defeatedcrow.hac.main.entity.EntityShotgunBullet;
import defeatedcrow.hac.main.entity.EntitySilverBullet;
import defeatedcrow.hac.main.entity.EntityThrowingArrow;
import defeatedcrow.hac.main.event.AchievementEventDC;
import defeatedcrow.hac.main.event.AnvilMoldEvent;
import defeatedcrow.hac.main.event.CombatEvent;
import defeatedcrow.hac.main.event.DCLootEvent;
import defeatedcrow.hac.main.event.LivingMainEventDC;
import defeatedcrow.hac.main.event.OnCraftingDC;
import defeatedcrow.hac.main.event.OnDeathEventDC;
import defeatedcrow.hac.main.event.OnJumpEventDC;
import defeatedcrow.hac.main.event.OnMiningEventDC;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.potion.PotionBirdDC;
import defeatedcrow.hac.main.potion.PotionGravityDC;
import defeatedcrow.hac.main.potion.PotionHeavyBootsDC;
import defeatedcrow.hac.main.potion.PotionNimbleDC;
import defeatedcrow.hac.main.potion.PotionOceanDC;
import defeatedcrow.hac.main.recipes.ArmorDyesRecipeDC;
import defeatedcrow.hac.main.recipes.BasicRecipeRegister;
import defeatedcrow.hac.main.recipes.BlockContainerUtil;
import defeatedcrow.hac.main.recipes.MachineRecipeRegister;
import defeatedcrow.hac.main.util.DCRegistryUtil;
import defeatedcrow.hac.main.villager.HaCTrade;
import defeatedcrow.hac.main.villager.HaCTradeData;
import defeatedcrow.hac.main.villager.VillagerCreationHaCAgri;
import defeatedcrow.hac.main.worldgen.CaravanGenEvent;
import defeatedcrow.hac.main.worldgen.MazaiLakeGen;
import defeatedcrow.hac.main.worldgen.VeinTableJsonHelper;
import defeatedcrow.hac.main.worldgen.VeinTableRegister;
import defeatedcrow.hac.main.worldgen.WorldGenAltSkarn;
import defeatedcrow.hac.main.worldgen.WorldGenHotspring;
import defeatedcrow.hac.main.worldgen.WorldGenOres3;
import defeatedcrow.hac.main.worldgen.WorldGenSaplings;
import defeatedcrow.hac.main.worldgen.WorldGenWindmill;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.GameData;

public class CommonMainProxy implements IGuiHandler {

	public void loadConst() {}

	public void loadMaterial() {
		MainMaterialRegister.load();
		// GameRegistry.registerFuelHandler(new DCFuelHandler());
		MainCreativeTabRegister.load();
	}

	public void loadPotion() {
		MainInit.gravity = new PotionGravityDC();
		DCRegistryUtil.addPotion(MainInit.gravity, MainInit.gravityType, "gravity");

		MainInit.bird = new PotionBirdDC();
		DCRegistryUtil.addPotion(MainInit.bird, MainInit.birdType, "bird");

		MainInit.ocean = new PotionOceanDC();
		DCRegistryUtil.addPotion(MainInit.ocean, MainInit.oceanType, "ocean");

		MainInit.heavyboots = new PotionHeavyBootsDC();
		DCRegistryUtil.addPotion(MainInit.heavyboots, MainInit.heavybootsType, "heavyboots");

		MainInit.nimble = new PotionNimbleDC();
		DCRegistryUtil.addPotion(MainInit.nimble, MainInit.nimbleType, "nimble");
	}

	public void loadEnchantment() {
		MainInit.venom = new EnchantmentVenom();
		ForgeRegistries.ENCHANTMENTS.register(MainInit.venom
				.setRegistryName(ClimateMain.MOD_ID, "dcs.enchantment.venom"));

		MainInit.robber = new EnchantmentRobber();
		ForgeRegistries.ENCHANTMENTS.register(MainInit.robber
				.setRegistryName(ClimateMain.MOD_ID, "dcs.enchantment.robber"));

	}

	public void loadVillager() {

		VillagerCreationHaCAgri.registerVillageComponents();
		VillagerCreationHaCAgri villageHandler = new VillagerCreationHaCAgri();
		VillagerRegistry.instance().registerVillageCreationHandler(villageHandler);

		MainInit.agri = new VillagerProfession("dcs_climate:agri_researcher",
				"dcs_climate:textures/models/agri_researcher.png",
				"dcs_climate:textures/models/zombie_agri_researcher.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(MainInit.agri);

		MainInit.engineer = new VillagerProfession("dcs_climate:engineer",
				"dcs_climate:textures/models/agri_researcher.png",
				"dcs_climate:textures/models/zombie_agri_researcher.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(MainInit.engineer);

		MainInit.trader = new VillagerProfession("dcs_climate:trader", "dcs_climate:textures/models/trader.png",
				"dcs_climate:textures/models/zombie_trader.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(MainInit.trader);

		MainInit.tailor = new VillagerProfession("dcs_climate:tailor", "dcs_climate:textures/models/tailor.png",
				"dcs_climate:textures/models/zombie_tailor.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(MainInit.tailor);

		HaCTradeData.init();

		// HaCCrops
		VillagerCareer agriList = new VillagerCareer(MainInit.agri, "dcs_agri_researcher");

		agriList.addTrade(1, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI1, new PriceInfo(1, 3)) });

		agriList.addTrade(2, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI2, new PriceInfo(1, 3)) });

		agriList.addTrade(3, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI2, new PriceInfo(1, 3)) });

		agriList.addTrade(4, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI2, new PriceInfo(1, 3)) });

		agriList.addTrade(5, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.AGRI2, new PriceInfo(1, 3)) });

		// Gems, Clothes, Furnitures
		VillagerCareer traderList = new VillagerCareer(MainInit.trader, "dcs_trader");

		traderList.addTrade(1, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE1, new PriceInfo(1, 3)) });

		traderList.addTrade(2, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE2, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE1, new PriceInfo(1, 3)) });

		traderList.addTrade(3, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE2, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE3, new PriceInfo(1, 3)) });

		traderList.addTrade(4, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE2, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE3, new PriceInfo(1, 3)) });

		traderList.addTrade(5, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE3, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE4, new PriceInfo(1, 3)) });

		traderList.addTrade(6, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE4, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TRADE4, new PriceInfo(1, 3)) });

		VillagerCareer machineList = new VillagerCareer(MainInit.engineer, "dcs_engineer");

		machineList.addTrade(1, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE1, new PriceInfo(1, 3)) });

		machineList.addTrade(2, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE2, new PriceInfo(1, 3)) });

		machineList.addTrade(3, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE2, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE2, new PriceInfo(1, 3)) });

		machineList.addTrade(4, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE3, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE2, new PriceInfo(1, 3)) });

		machineList.addTrade(5, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE3, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE3, new PriceInfo(1, 3)) });

		machineList.addTrade(6, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE2, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.MACHINE3, new PriceInfo(1, 3)) });

		VillagerCareer tailorList = new VillagerCareer(MainInit.tailor, "dcs_tailor");

		tailorList.addTrade(1, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR1, new PriceInfo(1, 3)) });

		tailorList.addTrade(2, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR1, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR2, new PriceInfo(1, 3)) });

		tailorList.addTrade(3, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR2, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR2, new PriceInfo(1, 3)) });

		tailorList.addTrade(4, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR3, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR2, new PriceInfo(1, 3)) });

		tailorList.addTrade(5, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR3, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR3, new PriceInfo(1, 3)) });

		tailorList.addTrade(6, new ITradeList[] {
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR2, new PriceInfo(1, 3)),
			HaCTrade.INSTANCE.new Get(HaCTradeData.TAILOR3, new PriceInfo(1, 3)) });

	}

	public void loadRecipes() {

		BasicRecipeRegister.load();
		MachineRecipeRegister.load();

		if (ModuleConfig.food) {
			FoodRecipes.load();
		}

		if (ModuleConfig.machine) {
			MachineRecipes.load();
		}

		if (ModuleConfig.magic) {
			MagicRecipeRegister.load();
		}

		BlockContainerUtil.INS.init();
		GameData.register_impl(new ArmorDyesRecipeDC().setRegistryName(new ResourceLocation(ClimateMain.MOD_ID,
				"armorcolor")));
	}

	public void loadEntity() {
		DCRegistryUtil.addEntity(EntityCution.class, "main", "cution");

		DCRegistryUtil.addEntity(EntityIronBolt.class, "main", "bullet_bolt", 1);

		DCRegistryUtil.addEntity(EntityIronBullet.class, "main", "bullet_iron", 1);

		DCRegistryUtil.addEntity(EntitySilverBullet.class, "main", "bullet_silver", 1);

		DCRegistryUtil.addEntity(EntityShotgunBullet.class, "main", "bullet_shot", 1);

		DCRegistryUtil.addEntity(EntityGhostBullet.class, "main", "bullet_ghost", 1);

		DCRegistryUtil.addEntity(EntityDynamite.class, "main", "dynamite_red");

		DCRegistryUtil.addEntity(EntityDynamiteBlue.class, "main", "dynamite_blue");

		DCRegistryUtil.addEntity(EntityLightBullet.class, "main", "bullet_light", 1);

		DCRegistryUtil.addEntity(EntityExtinctionBullet.class, "main", "bullet_extinction", 1);

		DCRegistryUtil.addEntity(EntityCrowBullet.class, "main", "bullet_balloon", 1);

		DCRegistryUtil.addEntity(EntityCrowBalloon.class, "main", "balloon_crow");

		DCRegistryUtil.addEntity(EntityFlowerPot.class, "main", "flowerpot");

		DCRegistryUtil.addEntity(EntityBigCushion.class, "main", "big_cushion");

		DCRegistryUtil.addEntity(EntityBigCushionBrown.class, "main", "big_cushion_brown");

		DCRegistryUtil.addEntity(EntityThrowingArrow.class, "main", "bullet_arrow", 1);

		if (ModuleConfig.food)
			FoodCommonProxy.loadEntity();

		if (ModuleConfig.machine)
			MachineCommonProxy.loadEntity();

		if (ModuleConfig.magic)
			MagicCommonProxy.loadEntity();
	}

	public void loadTE() {
		GameRegistry.registerTileEntity(TileNormalChamber.class, "dcs_te_chamber_normal");
		GameRegistry.registerTileEntity(TileShitirin.class, "dcs_te_shitirin");
		GameRegistry.registerTileEntity(TileCookingStove.class, "dcs_te_fuel_stove");
		GameRegistry.registerTileEntity(TileStevensonScreen.class, "dcs_te_stevenson_screen");
		GameRegistry.registerTileEntity(TileLowChest.class, "dcs_te_lowchest");
		GameRegistry.registerTileEntity(TileMetalChest.class, "dcs_te_metalchest");
		GameRegistry.registerTileEntity(TileMagnetChest.class, "dcs_te_magnetchest");
		GameRegistry.registerTileEntity(TileVillageChest.class, "dcs_te_villagechest");
		GameRegistry.registerTileEntity(TileSink.class, "dcs_te_sink");
		GameRegistry.registerTileEntity(TileBellow.class, "dcs_te_bellow");
		GameRegistry.registerTileEntity(TileThermometer.class, "dcs_te_thermometer");
		GameRegistry.registerTileEntity(TileWindVane.class, "dcs_te_windvane");
		GameRegistry.registerTileEntity(TileAcvShield.class, "dcs_te_acv_shield");
		GameRegistry.registerTileEntity(TileChandelierGypsum.class, "dcs_te_chandelier_gypsum");
		GameRegistry.registerTileEntity(TileChandelierSalt.class, "dcs_te_chandelier_salt");
		GameRegistry.registerTileEntity(TileChandelierChal.class, "dcs_te_chandelier_chal");
		GameRegistry.registerTileEntity(TileRealtimeClock.class, "dcs_te_realtime_clock");
		GameRegistry.registerTileEntity(TileRealtimeClock_L.class, "dcs_te_realtime_clock_l");
		GameRegistry.registerTileEntity(TileMCClock_L.class, "dcs_te_mc_clock_l");
		GameRegistry.registerTileEntity(TilePail.class, "dcs_te_pail");
		GameRegistry.registerTileEntity(TileBedDC.class, "dcs_te_bed_iron");
		GameRegistry.registerTileEntity(TileBedDCWhite.class, "dcs_te_bed_white");
		GameRegistry.registerTileEntity(TileBedDCRattan.class, "dcs_te_bed_rattan");
		GameRegistry.registerTileEntity(TileBedDCFuton.class, "dcs_te_bed_futon");
		GameRegistry.registerTileEntity(TileGeyser.class, "dcs_te_geyser");
		GameRegistry.registerTileEntity(TileFirestand.class, "dcs_te_firestand");

		if (ModuleConfig.food)
			FoodCommonProxy.loadTE();

		if (ModuleConfig.machine)
			MachineCommonProxy.loadTE();

		if (ModuleConfig.magic)
			MagicCommonProxy.loadTE();
	}

	public void loadWorldGen() {
		// gen
		if (ModuleConfig.world) {
			GameRegistry.registerWorldGenerator(new WorldGenOres3(), 2);
			if (WorldGenConfig.skarnGen > 0) {
				GameRegistry.registerWorldGenerator(new WorldGenAltSkarn(false), 3);
			}
			if (ModuleConfig.machine) {
				WorldGenWindmill.initLoot();
				GameRegistry.registerWorldGenerator(new WorldGenWindmill(false), 10);
			}
			if (WorldGenConfig.mazaiLake && MainInit.mazaiBlock != null) {
				GameRegistry.registerWorldGenerator(new MazaiLakeGen(), 5);
			}
			if (ModuleConfig.food) {
				GameRegistry.registerWorldGenerator(new WorldGenSaplings(false), 5);
			}
			if (WorldGenConfig.hotspringGen > 0) {
				GameRegistry.registerWorldGenerator(new WorldGenHotspring(), 3);
			}
		}
	}

	public void loadWorldGenPost() {
		if (ModuleConfig.world) {
			VeinTableRegister.INSTANCE.registerVeins();
			VeinTableJsonHelper.pre();
			VeinTableJsonHelper.post();
		}
	}

	public void addSidedBlock(Block block, String name, int max) {}

	public void addCropBlock(Block block, String name, int max) {}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {}

	/**
	 * TEの向きのみ対応させたJsonタイプモデル
	 */
	public void regTEJson(Block block, String domein, String name, String dir) {}

	public void loadInit() {
		MinecraftForge.EVENT_BUS.register(new OnMiningEventDC());
		MinecraftForge.EVENT_BUS.register(new OnDeathEventDC());
		MinecraftForge.EVENT_BUS.register(new OnJumpEventDC());
		MinecraftForge.EVENT_BUS.register(new AchievementEventDC());
		MinecraftForge.EVENT_BUS.register(new LivingMainEventDC());
		MinecraftForge.EVENT_BUS.register(new AnvilMoldEvent());
		MinecraftForge.EVENT_BUS.register(new CombatEvent());
		MinecraftForge.EVENT_BUS.register(new DCLootEvent());
		if (ModuleConfig.magic) {
			MinecraftForge.EVENT_BUS.register(new OnCraftingDC());
			MinecraftForge.EVENT_BUS.register(new MagicCommonEvent());
		}
		if (ModuleConfig.world) {
			MinecraftForge.EVENT_BUS.register(new CaravanGenEvent());
		}

		DCMainPacket.init();
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID > 0) {
			Entity entity = world.getEntityByID(ID);
			if (entity instanceof EntityScooter)
				return new ContainerEntityScooter((EntityScooter) entity, player);
			if (entity instanceof EntityVillager)
				return new ContainerVillagerDC((EntityVillager) entity, player);
			if (entity instanceof EntityLiving)
				return new ContainerLivingDC((EntityLiving) entity, player);
		}
		BlockPos pos = new BlockPos(x, y, z);
		if (!world.isBlockLoaded(pos))
			return null;
		TileEntity tile = world.getTileEntity(pos);
		if (tile == null)
			return null;
		if (tile instanceof TileNormalChamber)
			return new ContainerNormalChamber((TileNormalChamber) tile, player.inventory);
		if (tile instanceof TileStevensonScreen)
			return new ContainerStevensonScreen((TileStevensonScreen) tile, player);
		if (tile instanceof TileStoneMill)
			return new ContainerStoneMill((TileStoneMill) tile, player.inventory);
		if (tile instanceof TileTeaPot)
			return new ContainerTeaPot((TileTeaPot) tile, player.inventory);
		if (tile instanceof TileFluidProcessorBase)
			return new ContainerFluidProcessor((TileFluidProcessorBase) tile, player.inventory);
		if (tile instanceof TileLowChest)
			return new ContainerLowChest((TileLowChest) tile, player);
		if (tile instanceof TileCookingStove)
			return new ContainerFuelStove((TileCookingStove) tile, player.inventory);
		if (tile instanceof TilePressMachine)
			return new ContainerPressMachine((TilePressMachine) tile, player.inventory);
		if (tile instanceof TileHopperFilter)
			return new ContainerHopperFilter((TileHopperFilter) tile, player);
		if (tile instanceof TileHopperFluid)
			return new ContainerHopperFluid((TileHopperFluid) tile, player);
		if (tile instanceof TileReactor)
			return new ContainerReactor((TileReactor) tile, player.inventory);
		if (tile instanceof TileSpinningMachine)
			return new ContainerSpinning((TileSpinningMachine) tile, player.inventory);
		if (tile instanceof TilePortalManager)
			return new ContainerPortalManager((TilePortalManager) tile, player);
		if (tile instanceof TileDieselEngine)
			return new ContainerDieselEngine((TileDieselEngine) tile, player.inventory);
		if (tile instanceof TileRollerCrusher)
			return new ContainerCrusher((TileRollerCrusher) tile, player.inventory);
		if (tile instanceof TileSilkwormBox)
			return new ContainerSilkwormBox((TileSilkwormBox) tile, player.inventory);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID > 0) {
			Entity entity = world.getEntityByID(ID);
			if (entity instanceof EntityScooter)
				return new GuiEntityScooter((EntityScooter) entity, player);
			if (entity instanceof EntityVillager)
				return new GuiVillagerDC((EntityVillager) entity, player);
			if (entity instanceof EntityLiving)
				return new GuiLivingDC((EntityLiving) entity, player);
		}
		BlockPos pos = new BlockPos(x, y, z);
		if (!world.isBlockLoaded(pos))
			return null;
		TileEntity tile = world.getTileEntity(pos);
		if (tile == null)
			return null;
		if (tile instanceof TileNormalChamber)
			return new GuiNormalChamber((TileNormalChamber) tile, player.inventory);
		if (tile instanceof TileStevensonScreen)
			return new GuiStevensonScreen((TileStevensonScreen) tile, player);
		if (tile instanceof TileStoneMill)
			return new GuiStoneMill((TileStoneMill) tile, player.inventory);
		if (tile instanceof TileTeaPot)
			return new GuiTeaPot((TileTeaPot) tile, player.inventory);
		if (tile instanceof TileFluidProcessorBase)
			return new GuiFluidProcessor((TileFluidProcessorBase) tile, player.inventory);
		if (tile instanceof TileLowChest)
			return new GuiLowChest((TileLowChest) tile, player);
		if (tile instanceof TileCookingStove)
			return new GuiFuelStove((TileCookingStove) tile, player.inventory);
		if (tile instanceof TilePressMachine)
			return new GuiPressMachine((TilePressMachine) tile, player.inventory);
		if (tile instanceof TileHopperFilter)
			return new GuiHopperFilter((TileHopperFilter) tile, player);
		if (tile instanceof TileHopperFluid)
			return new GuiHopperFluid((TileHopperFluid) tile, player);
		if (tile instanceof TileReactor)
			return new GuiReactor((TileReactor) tile, player.inventory);
		if (tile instanceof TileSpinningMachine)
			return new GuiSpinning((TileSpinningMachine) tile, player.inventory);
		if (tile instanceof TilePortalManager)
			return new GuiPortalManager((TilePortalManager) tile, player);
		if (tile instanceof TileDieselEngine)
			return new GuiDieselEngine((TileDieselEngine) tile, player.inventory);
		if (tile instanceof TileRollerCrusher)
			return new GuiCrusher((TileRollerCrusher) tile, player.inventory);
		if (tile instanceof TileSilkwormBox)
			return new GuiSilkwormBox((TileSilkwormBox) tile, player.inventory);
		return null;
	}

	public net.minecraft.client.model.ModelBiped getArmorModel(int slot) {
		return null;
	}

	public boolean isForwardKeyDown() {
		return false;
	}

	public boolean isSneakKeyDown() {
		return false;
	}

	public int getParticleCount() {
		return 0;
	}

}
