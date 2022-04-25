package defeatedcrow.hac.food;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonBakery;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.food.block.TileAgingBarrel;
import defeatedcrow.hac.food.block.TileBrewingTankUnder;
import defeatedcrow.hac.food.block.TileBrewingTankUpper;
import defeatedcrow.hac.food.block.TileBrewingTankWood;
import defeatedcrow.hac.food.block.TileIncubator;
import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.block.TileSilkwormBox;
import defeatedcrow.hac.food.block.TileSkillet;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.block.TileStillPot;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.block.crop.BlockLotusN;
import defeatedcrow.hac.food.block.crop.TileEntityLotus;
import defeatedcrow.hac.food.client.*;
import defeatedcrow.hac.food.entity.*;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.ClientMainProxy;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FoodClientProxy {
	public static void loadConst() {
		JsonBakery.instance.addTex(getTexList());
	}

	public static void loadEntity() {
		ClientMainProxy.registRender(BreadRoundEntity.class, RoundBreadRenderer.class);
		ClientMainProxy.registRender(BreadSquareEntity.class, SquareBreadRenderer.class);
		ClientMainProxy.registRender(BreadToastEntity.class, ToastRenderer.class);
		ClientMainProxy.registRender(BreadRoundCreamEntity.class, RoundBreadCreamRenderer.class);
		ClientMainProxy.registRender(PizzaTomatoEntity.class, PizzaTomatoRenderer.class);
		ClientMainProxy.registRender(BreadToastFrenchEntity.class, ToastFrenchRenderer.class);
		ClientMainProxy.registRender(BreadToastGarlicEntity.class, ToastGarlicRenderer.class);
		ClientMainProxy.registRender(BreadPitaEntity.class, PitaBreadRenderer.class);
		ClientMainProxy.registRender(BreadPancakeEntity.class, PancakeRenderer.class);
		ClientMainProxy.registRender(BreadWalnutEntity.class, WalnutBreadRenderer.class);
		ClientMainProxy.registRender(BreadGingermanEntity.class, GingermanRenderer.class);
		ClientMainProxy.registRender(BreadTortillaEntity.class, TortillaRenderer.class);
		ClientMainProxy.registRender(BreadRaisinEntity.class, RaisinBreadRenderer.class);
		ClientMainProxy.registRender(BreadSausageEntity.class, SausageBreadRenderer.class);
		ClientMainProxy.registRender(BreadGrahamCrackerEntity.class, GrahamCrackerRenderer.class);
		ClientMainProxy.registRender(BreadCinnamonEntity.class, CinnamonRollRenderer.class);
		ClientMainProxy.registRender(BreadAnkoEntity.class, AnkoBreadRenderer.class);
		ClientMainProxy.registRender(PizzaRoeEntity.class, PizzaRoeRenderer.class);
		ClientMainProxy.registRender(PizzaTeriyakiEntity.class, PizzaTeriyakiRenderer.class);
		ClientMainProxy.registRender(CookieCrowEntity.class, CookieRenderer.class);
		ClientMainProxy.registRender(CookieUnagiEntity.class, CookieRenderer.class);
		ClientMainProxy.registRender(StickFishEntity.class, FishStickRenderer.class);
		ClientMainProxy.registRender(StickYakitoriEntity.class, YakitoriStickRenderer.class);
		ClientMainProxy.registRender(StickPorkEntity.class, PorkStickRenderer.class);
		ClientMainProxy.registRender(StickBeefEntity.class, BeefStickRenderer.class);
		ClientMainProxy.registRender(StickMuttonEntity.class, MuttonStickRenderer.class);
		ClientMainProxy.registRender(StickSquidEntity.class, SquidStickRenderer.class);
		ClientMainProxy.registRender(StickGoheiEntity.class, GoheiStickRenderer.class);
		ClientMainProxy.registRender(StewMisosoupEntity.class, StewMisosoupRenderer.class);
		ClientMainProxy.registRender(StewMotsuEntity.class, StewMotsuRenderer.class);
		ClientMainProxy.registRender(StickMarshmallowEntity.class, MarshmallowStickRenderer.class);
		ClientMainProxy.registRender(StickMotuEntity.class, MotuStickRenderer.class);
		ClientMainProxy.registRender(EntityTeaCupSilver.class, CupSilverRenderer.class);
		ClientMainProxy.registRender(EntityTeaCupWhite.class, CupWhiteRenderer.class);
		ClientMainProxy.registRender(EntityTumbler.class, TumblerRenderer.class);
		ClientMainProxy.registRender(TartAppleEntity.class, AppleTartRenderer.class);
		ClientMainProxy.registRender(TartLemonEntity.class, LemonTartRenderer.class);
		ClientMainProxy.registRender(QuicheSpinachEntity.class, SpinachQuicheRenderer.class);
		ClientMainProxy.registRender(QuichePotatoEntity.class, PotatoQuicheRenderer.class);
		ClientMainProxy.registRender(TartCrostataEntity.class, CrostataTartRenderer.class);
		ClientMainProxy.registRender(TartChocolateEntity.class, ChocolateTartRenderer.class);
		ClientMainProxy.registRender(TartCustardEntity.class, CustardTartRenderer.class);
		ClientMainProxy.registRender(TartLiverEntity.class, LiverTartRenderer.class);
		ClientMainProxy.registRender(PieSugarEntity.class, SugarPieRenderer.class);
		ClientMainProxy.registRender(PieMeatEntity.class, MeatPieRenderer.class);
		ClientMainProxy.registRender(PieChocolateEntity.class, ChocolatePieRenderer.class);
		ClientMainProxy.registRender(PieFruitEntity.class, FruitPieRenderer.class);
		ClientMainProxy.registRender(PieMooncakeEntity.class, MooncakeRenderer.class);
		ClientMainProxy.registRender(PieCustardEntity.class, CustardPieRenderer.class);
		ClientMainProxy.registRender(SandwichEntity.class, SandwichAppleRenderer.class);
		ClientMainProxy.registRender(SandwichEggEntity.class, SandwichEggRenderer.class);
		ClientMainProxy.registRender(SandwichLemonEntity.class, SandwichAppleRenderer.class);
		ClientMainProxy.registRender(SaladSandwichEntity.class, SandwichSaladRenderer.class);
		ClientMainProxy.registRender(SandwichLiverEntity.class, SandwichLiverRenderer.class);
		ClientMainProxy.registRender(SandwichSalmonEntity.class, SandwichSalmonRenderer.class);
		ClientMainProxy.registRender(ClubSandwichSEntity.class, ClubSandwichSRenderer.class);
		ClientMainProxy.registRender(ClubSandwichREntity.class, ClubSandwichRRenderer.class);
		ClientMainProxy.registRender(EntityRiceBowl.class, RiceBowlRenderer.class);
		ClientMainProxy.registRender(EntityRiceMushroom.class, RiceMushroomRenderer.class);
		ClientMainProxy.registRender(EntityRiceBall.class, RiceBallRenderer.class);
		ClientMainProxy.registRender(EntityRiceBallSeaweed.class, RiceBallSeaweedRenderer.class);
		ClientMainProxy.registRender(EntityRiceBallMiso.class, RiceBallMisoRenderer.class);
		ClientMainProxy.registRender(EntityRiceBallRoe.class, RiceBallRoeRenderer.class);
		ClientMainProxy.registRender(EntityRiceSekihan.class, RiceBowlSekihanRenderer.class);
		ClientMainProxy.registRender(EntityRiceShiruko.class, RiceBowlShirukoRenderer.class);
		ClientMainProxy.registRender(EntityRiceTyadukeSalmon.class, RiceBowlTyadukeSalmonRenderer.class);
		ClientMainProxy.registRender(EntityRiceTyadukeRoe.class, RiceBowlTyadukeRoeRenderer.class);
		ClientMainProxy.registRender(MochiEntity.class, MochiRenderer.class);
		ClientMainProxy.registRender(EmptyPlateEntity.class, EmptyPlateRenderer.class);
		ClientMainProxy.registRender(PlateBeefEntity.class, PlateBeefRenderer.class);
		ClientMainProxy.registRender(PlatePorkEntity.class, PlatePorkRenderer.class);
		ClientMainProxy.registRender(PlateChickenEntity.class, PlateChickenRenderer.class);
		ClientMainProxy.registRender(PlateFishEntity.class, PlateFishRenderer.class);
		ClientMainProxy.registRender(PlatePotatoEntity.class, PlatePotatoRenderer.class);
		ClientMainProxy.registRender(PlateSoupEntity.class, PlateTomatoRenderer.class);
		ClientMainProxy.registRender(PlateBigGarlicEntity.class, PlateBigGarlicRenderer.class);
		ClientMainProxy.registRender(PlateGratinEntity.class, PlateGratinRenderer.class);
		ClientMainProxy.registRender(PlatePaellaEntity.class, PlatePaellaRenderer.class);
		ClientMainProxy.registRender(PlateMeatPaellaEntity.class, PlateMeatPaellaRenderer.class);
		ClientMainProxy.registRender(PlateBigChickenEntity.class, PlateBigChickenRenderer.class);
		ClientMainProxy.registRender(PlateGyozaEntity.class, PlateGyozaRenderer.class);
		ClientMainProxy.registRender(StewVegiEntity.class, StewVegiRenderer.class);
		ClientMainProxy.registRender(StewEggEntity.class, StewEggRenderer.class);
		ClientMainProxy.registRender(StewCongeeEntity.class, StewCongeeRenderer.class);
		ClientMainProxy.registRender(StewTomatoEntity.class, StewTomatoRenderer.class);
		ClientMainProxy.registRender(StewPumpukinEntity.class, StewPumpkinRenderer.class);
		ClientMainProxy.registRender(StewBorschtEntity.class, StewBorschtRenderer.class);
		ClientMainProxy.registRender(StewMushroomEntity.class, StewMushroomRenderer.class);
		ClientMainProxy.registRender(StewPurpleEntity.class, StewPurpleRenderer.class);
		ClientMainProxy.registRender(StewLotusrootEntity.class, StewLotusrootRenderer.class);
		ClientMainProxy.registRender(StewSquidEntity.class, StewSquidRenderer.class);
		ClientMainProxy.registRender(StewSeaweedEntity.class, StewSeaweedRenderer.class);
		ClientMainProxy.registRender(StewChiliEntity.class, StewChiliRenderer.class);
		ClientMainProxy.registRender(StewGarlicOilEntity.class, StewGarlicOilRenderer.class);
		ClientMainProxy.registRender(StewLazijiEntity.class, StewLazijiRenderer.class);
		ClientMainProxy.registRender(SaladGreenEntity.class, SaladGreenRenderer.class);
		ClientMainProxy.registRender(SaladPotatoEntity.class, SaladPotatoRenderer.class);
		ClientMainProxy.registRender(SaladLotusrootEntity.class, SaladLotusrootRenderer.class);
		ClientMainProxy.registRender(SimmeredSoyEntity.class, SimmeredSoyRenderer.class);
		ClientMainProxy.registRender(SimmeredGomokuEntity.class, SimmeredGomokuRenderer.class);
		ClientMainProxy.registRender(SimmeredSpinachEntity.class, SimmeredSpinachRenderer.class);
		ClientMainProxy.registRender(SimmeredBeansEntity.class, SimmeredBeansRenderer.class);
		ClientMainProxy.registRender(SimmeredNattoEntity.class, SimmeredNattoRenderer.class);
		ClientMainProxy.registRender(SimmeredPumpkinEntity.class, SimmeredPumpkinRenderer.class);
		ClientMainProxy.registRender(SaladSalmonEntity.class, SaladSalmonRenderer.class);
		ClientMainProxy.registRender(SaladTofuEntity.class, SaladTofuRenderer.class);
		ClientMainProxy.registRender(SaladWalnutEntity.class, SaladWalnutRenderer.class);
		ClientMainProxy.registRender(SaladWatermelonEntity.class, SaladWatermelonRenderer.class);
		ClientMainProxy.registRender(CakeButterEntity.class, CakeButterRenderer.class);
		ClientMainProxy.registRender(CakeChocolateEntity.class, CakeChocolateRenderer.class);
		ClientMainProxy.registRender(CakeCoffeeEntity.class, JellyCoffeeRenderer.class);
		ClientMainProxy.registRender(CakeLemonEntity.class, JellyLemonRenderer.class);
		ClientMainProxy.registRender(CakeCreamEntity.class, JellyCreamRenderer.class);
		ClientMainProxy.registRender(CakeBerryEntity.class, JellyBerryRenderer.class);
		ClientMainProxy.registRender(CakeKuzuEntity.class, JellyKuzuRenderer.class);
		ClientMainProxy.registRender(CakeCocotteEntity.class, CakeCocotteRenderer.class);
		ClientMainProxy.registRender(CakeToffeeEntity.class, CakeToffeeRenderer.class);
		ClientMainProxy.registRender(CakeTowerEntity.class, PancakeTowerRenderer.class);
		ClientMainProxy.registRender(CakeGrapeEntity.class, JellyGrapeRenderer.class);
		ClientMainProxy.registRender(CakeOnionEntity.class, CakeOnionRenderer.class);
		ClientMainProxy.registRender(CakeSmoreEntity.class, CakeSmoreRenderer.class);
		ClientMainProxy.registRender(CakeRaisinEntity.class, CakeRaisinRenderer.class);
		ClientMainProxy.registRender(CakeYogurtEntity.class, CakeYogurtRenderer.class);
		ClientMainProxy.registRender(CakeGreenteaEntity.class, CakeGreenteaRenderer.class);
		ClientMainProxy.registRender(CakeAppleEntity.class, CakeAppleRenderer.class);
		ClientMainProxy.registRender(IceCreamEntity.class, IcecreamMilkRenderer.class);
		ClientMainProxy.registRender(IceCreamKinakoEntity.class, IcecreamKinakoRenderer.class);
		ClientMainProxy.registRender(IceCreamBerryEntity.class, IcecreamBerryRenderer.class);
		ClientMainProxy.registRender(IceCreamLemonEntity.class, IcecreamLemonRenderer.class);
		ClientMainProxy.registRender(IceCreamCookieEntity.class, IcecreamCookieRenderer.class);
		ClientMainProxy.registRender(IceCreamCocoaEntity.class, IcecreamCocoaRenderer.class);
		ClientMainProxy.registRender(ParfaitBerryEntity.class, ParfaitBerryRenderer.class);
		ClientMainProxy.registRender(ParfaitCitrusEntity.class, ParfaitCitrusRenderer.class);
		ClientMainProxy.registRender(ParfaitWatermelonEntity.class, ParfaitWatermelonRenderer.class);
		ClientMainProxy.registRender(ParfaitAnkoEntity.class, ParfaitAnkoRenderer.class);
		ClientMainProxy.registRender(WagashiKinakoEntity.class, WagashiKinakoRenderer.class);
		ClientMainProxy.registRender(WagashiIsobeEntity.class, WagashiIsobeRenderer.class);
		ClientMainProxy.registRender(WagashiZundaEntity.class, WagashiZundaRenderer.class);
		ClientMainProxy.registRender(WagashiButterEntity.class, WagashiButterRenderer.class);
		ClientMainProxy.registRender(WagashiStrawberryEntity.class, WagashiStrawberryRenderer.class);
		ClientMainProxy.registRender(WagashiKurumiEntity.class, WagashiKurumiRenderer.class);
		ClientMainProxy.registRender(WagashiKurimanjuEntity.class, WagashiKurimanjuRenderer.class);
		ClientMainProxy.registRender(WagashiNerikiriEntity.class, WagashiNerikiriRenderer.class);
		ClientMainProxy.registRender(WagashiAbekawaEntity.class, WagashiAbekawaRenderer.class);
		ClientMainProxy.registRender(WagashiBotamochiEntity.class, WagashiBotamochiRenderer.class);
		ClientMainProxy.registRender(WagashiYatsuhashiEntity.class, WagashiYatsuhashiRenderer.class);
		ClientMainProxy.registRender(WagashiYoukanEntity.class, WagashiYoukanRenderer.class);
		ClientMainProxy.registRender(MealFriedPotatoEntity.class, MealFriedPotatoRenderer.class);
		ClientMainProxy.registRender(MealFishAndChipsEntity.class, MealFishAndChipsRenderer.class);
		ClientMainProxy.registRender(MealCroquettePotatoEntity.class, MealCroquettePotatoRenderer.class);
		ClientMainProxy.registRender(MealCroquettePumpkinEntity.class, MealCroquettePumpkinRenderer.class);
		ClientMainProxy.registRender(MealShawarmaEntity.class, MealShawarmaRenderer.class);
		ClientMainProxy.registRender(MealFalafelSandEntity.class, MealFalafelSandwichRenderer.class);
		ClientMainProxy.registRender(FriedPorkEntity.class, FriedPorkRenderer.class);
		ClientMainProxy.registRender(FriedChickenEntity.class, FriedChickenRenderer.class);
		ClientMainProxy.registRender(FriedFishEntity.class, FriedFishRenderer.class);
		ClientMainProxy.registRender(FriedFalafelEntity.class, FriedFalafelRenderer.class);
		ClientMainProxy.registRender(FriedFishcakeEntity.class, FriedFishcakeRenderer.class);
		ClientMainProxy.registRender(FriedPorkGingerEntity.class, FriedPorkgingerRenderer.class);
		ClientMainProxy.registRender(FriedPrawnEntity.class, FriedPrawnRenderer.class);
		ClientMainProxy.registRender(MealBreakfastBEntity.class, SetmealBreakfastRenderer.class);
		ClientMainProxy.registRender(MealBreakfastJEntity.class, SetmealBreakfastJRenderer.class);
		ClientMainProxy.registRender(MealBreakfastJ2Entity.class, SetmealBreakfastJ2Renderer.class);
		ClientMainProxy.registRender(DishOmericeEntity.class, DishOmericeRenderer.class);
		ClientMainProxy.registRender(DishTacoEntity.class, DishTacoRenderer.class);
		ClientMainProxy.registRender(DishTacoriceEntity.class, DishTacoriceRenderer.class);
		ClientMainProxy.registRender(DishNachosEntity.class, DishNachosRenderer.class);
		ClientMainProxy.registRender(DishIkameshiEntity.class, DishIkameshiRenderer.class);
		ClientMainProxy.registRender(DishBiriyaniEntity.class, DishBiriyaniRenderer.class);
		ClientMainProxy.registRender(DishChickenRiceEntity.class, DishChickenRiceRenderer.class);
		ClientMainProxy.registRender(DishTomyumEntity.class, DishTomyumRenderer.class);
		ClientMainProxy.registRender(DishCapreseEntity.class, DishCapreseRenderer.class);
		ClientMainProxy.registRender(DishBruschettaEntity.class, DishBruschettaRenderer.class);
		ClientMainProxy.registRender(DishSalmonEntity.class, DishSalmonRenderer.class);
		ClientMainProxy.registRender(DishSushiEntity.class, DishSushiRenderer.class);
		ClientMainProxy.registRender(DishSashimiEntity.class, DishSashimiRenderer.class);
		ClientMainProxy.registRender(DishMisoniEntity.class, DishMisoniRenderer.class);
		ClientMainProxy.registRender(DishTamagoEntity.class, DishTamagoRenderer.class);
		ClientMainProxy.registRender(DishYakkoEntity.class, DishYakkoRenderer.class);
		ClientMainProxy.registRender(DishMaboEntity.class, DishMaboRenderer.class);
		ClientMainProxy.registRender(DrinkEntity.class, DrinkRenderer.class);
		ClientMainProxy.registRender(UdonMeatEntity.class, UdonMeatRenderer.class);
		ClientMainProxy.registRender(UdonSeaweedEntity.class, UdonSeaweedRenderer.class);
		ClientMainProxy.registRender(UdonEggEntity.class, UdonEggRenderer.class);
		ClientMainProxy.registRender(SauceLiverEntity.class, SauceLiverRenderer.class);
		ClientMainProxy.registRender(SauceRaisinEntity.class, SauceRaisinRenderer.class);
		ClientMainProxy.registRender(SauceSalsaEntity.class, SauceSalsaRenderer.class);
		ClientMainProxy.registRender(PastaOilEntity.class, PastaOilRenderer.class);
		ClientMainProxy.registRender(PastaBasilEntity.class, PastaBasilRenderer.class);
		ClientMainProxy.registRender(PastaTomatoEntity.class, PastaTomatoRenderer.class);
		ClientMainProxy.registRender(PastaPrawnEntity.class, PastaPrawnRenderer.class);
		ClientMainProxy.registRender(PastaCodEntity.class, PastaCodRenderer.class);
		ClientMainProxy.registRender(PastaSquidEntity.class, PastaSquidRenderer.class);
		ClientMainProxy.registRender(PastaBeefEntity.class, PastaBeefRenderer.class);
		ClientMainProxy.registRender(PastaRoeEntity.class, PastaRoeRenderer.class);
		ClientMainProxy.registRender(YogurtPlainEntity.class, YogurtPlainRenderer.class);
		ClientMainProxy.registRender(YogurtSPEntity.class, YogurtSPRenderer.class);
		ClientMainProxy.registRender(LiquorBottleEntity.class, BottleLiquorRenderer.class);
		ClientMainProxy.registRender(WaterBottleEntity.class, BottleWaterRenderer.class);
		ClientMainProxy.registRender(CurryVegiEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CurryBeansEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CurryFishEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CurrySpinachEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CurryButterChickenEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CurryVindalooEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CurryGreenEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CurryRiceEntity.class, CurryRenderer.class);
		ClientMainProxy.registRender(CutleryChopsticksEntity.class, CutleryRenderer.class);
		ClientMainProxy.registRender(CutlerySpoonEntity.class, CutleryRenderer.class);
		ClientMainProxy.registRender(CutleryForkEntity.class, CutleryRenderer.class);
	}

	public static void loadTE() {
		ClientMainProxy.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot", new TESRPotteryPot());
		ClientMainProxy.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot", new TESRSteelPot());
		ClientMainProxy.registerTileEntity(TileTeaPot.class, "dcs_te_tea_pot", new TESRTeaPot());
		GameRegistry.registerTileEntity(TileSilkwormBox.class, "dcs_te_silkworm_box");
		ClientMainProxy.registerTileEntity(TileSkillet.class, "dcs_te_skillet", new TESRSkillet());
		ClientMainProxy.registerTileEntity(TileEntityLotus.class, "dcs_te_crop_lotus", new TESRLotusCrop());
		ClientMainProxy.registerTileEntity(TileIncubator.class, "dcs_te_incubator", new TESRIncubator());
		ClientMainProxy.registerTileEntity(TileBrewingTankWood.class, "dcs_te_brewing_wood", new TESRBrewingTankWood());
		ClientMainProxy.registerTileEntity(TileBrewingTankUnder.class, "dcs_te_brewing_under", new TESRBrewingTankUnder());
		GameRegistry.registerTileEntity(TileBrewingTankUpper.class, "dcs_te_brewing_upper");
		ClientMainProxy.registerTileEntity(TileStillPot.class, "dcs_te_distillator", new TESRStill());
		ClientMainProxy.registerTileEntity(TileAgingBarrel.class, "dcs_te_aging_barrel", new TESRAgingBarrel());
	}

	public static void regJson(JsonRegisterHelper instance) {
		// item
		instance.regSimpleItem(FoodInit.teaLeaves, ClimateCore.PACKAGE_ID, "dcs_food_leaves", "food", 2);
		instance.regSimpleItem(FoodInit.dropOil, ClimateCore.PACKAGE_ID, "dcs_food_drop_oil", "food", 0);
		instance.regSimpleItem(FoodInit.dropCream, ClimateCore.PACKAGE_ID, "dcs_food_drop_cream", "food", 0);
		instance.regSimpleItem(FoodInit.bread, ClimateCore.PACKAGE_ID, "dcs_round_bread", "food", 31);
		instance.regSimpleItem(FoodInit.pizza, ClimateCore.PACKAGE_ID, "dcs_food_pizza", "food", 3);
		instance.regSimpleItem(FoodInit.cookie, ClimateCore.PACKAGE_ID, "dcs_food_cookie", "food", 2);
		instance.regSimpleItem(FoodInit.sticks, ClimateCore.PACKAGE_ID, "dcs_stick_foods", "food", 17);
		instance.regSimpleItem(FoodInit.crops, ClimateCore.PACKAGE_ID, "dcs_crops", "food", 20);
		instance.regSimpleItem(FoodInit.seeds, ClimateCore.PACKAGE_ID, "dcs_seeds", "food", 16);
		instance.regSimpleItem(FoodInit.petals, ClimateCore.PACKAGE_ID, "dcs_petals", "food", 1);
		instance.regSimpleItem(FoodInit.cupSilver, ClimateCore.PACKAGE_ID, "dcs_food_teacup", "food", 2);
		instance.regSimpleItem(FoodInit.dairy, ClimateCore.PACKAGE_ID, "dcs_food_dairy", "food", 4);
		instance.regSimpleItem(FoodInit.meat, ClimateCore.PACKAGE_ID, "dcs_food_meat", "food", 11);
		instance.regSimpleItem(FoodInit.pastry, ClimateCore.PACKAGE_ID, "dcs_food_pastry", "food", 1);
		instance.regSimpleItem(FoodInit.spices, ClimateCore.PACKAGE_ID, "dcs_food_spice", "food", 2);
		instance.regSimpleItem(FoodInit.cutlery, ClimateCore.PACKAGE_ID, "dcs_food_cutlery", "food", 2);
		instance.regSimpleItem(FoodInit.pastryRound, ClimateCore.PACKAGE_ID, "dcs_food_tart", "food", 15);
		instance.regSimpleItem(FoodInit.pastrySquare, ClimateCore.PACKAGE_ID, "dcs_food_pie", "food", 11);
		instance.regSimpleItem(FoodInit.sandwich, ClimateCore.PACKAGE_ID, "dcs_food_sandwich", "food", 5);
		instance.regSimpleItem(FoodInit.clubsandwich, ClimateCore.PACKAGE_ID, "dcs_food_clubsand", "food", 1);
		instance.regSimpleItem(FoodInit.ricebowl, ClimateCore.PACKAGE_ID, "dcs_food_rice_bowl", "food", 9);
		instance.regSimpleItem(FoodInit.steakplate, ClimateCore.PACKAGE_ID, "dcs_food_empty_plate", "food", 0);
		instance.regSimpleItem(FoodInit.plateMeal, ClimateCore.PACKAGE_ID, "dcs_food_plate_meat", "food", 11);
		instance.regSimpleItem(FoodInit.plateSoup, ClimateCore.PACKAGE_ID, "dcs_food_plate_potato", "food", 11);
		instance.regSimpleItem(FoodInit.bowlSoup, ClimateCore.PACKAGE_ID, "dcs_food_bowl_stew", "food", 15);
		instance.regSimpleItem(FoodInit.salad, ClimateCore.PACKAGE_ID, "dcs_food_salad", "food", 12);
		instance.regSimpleItem(FoodInit.cake, ClimateCore.PACKAGE_ID, "dcs_food_cake", "food", 19);
		instance.regSimpleItem(FoodInit.icecream, ClimateCore.PACKAGE_ID, "dcs_food_icecream", "food", 9);
		instance.regSimpleItem(FoodInit.mochi, ClimateCore.PACKAGE_ID, "dcs_food_mochi", "food", 1);
		instance.regSimpleItem(FoodInit.wagashi, ClimateCore.PACKAGE_ID, "dcs_food_wagashi", "food", 11);
		instance.regSimpleItem(FoodInit.nonEntity, ClimateCore.PACKAGE_ID, "dcs_food_noentity", "food", 7);
		instance.regSimpleItem(FoodInit.snack, ClimateCore.PACKAGE_ID, "dcs_food_snack", "food", 5);
		instance.regSimpleItem(FoodInit.deepFry, ClimateCore.PACKAGE_ID, "dcs_food_frying", "food", 6);
		instance.regSimpleItem(FoodInit.setMeal, ClimateCore.PACKAGE_ID, "dcs_food_setmeal", "food", 2);
		instance.regSimpleItem(FoodInit.dishSq, ClimateCore.PACKAGE_ID, "dcs_food_dish_sq", "food", 7);
		instance.regSimpleItem(FoodInit.dishBig, ClimateCore.PACKAGE_ID, "dcs_food_dish_big", "food", 8);
		instance.regSimpleItem(FoodInit.udon, ClimateCore.PACKAGE_ID, "dcs_food_udon", "food", 2);
		instance.regSimpleItem(FoodInit.pasta, ClimateCore.PACKAGE_ID, "dcs_food_pasta", "food", 7);
		instance.regSimpleItem(FoodInit.drink, ClimateCore.PACKAGE_ID, "dcs_food_drink", "food", 8);
		instance.regSimpleItem(FoodInit.dip, ClimateCore.PACKAGE_ID, "dcs_food_dipsauce", "food", 2);
		instance.regSimpleItem(FoodInit.yogurt, ClimateCore.PACKAGE_ID, "dcs_food_yogurt", "food", 1);
		instance.regSimpleItem(FoodInit.curry, ClimateCore.PACKAGE_ID, "dcs_food_curry", "food", 7);
		instance.regSimpleItem(FoodInit.paperPack, ClimateCore.PACKAGE_ID, "dcs_food_pack", "food", 21);
		instance.regSimpleItem(FoodInit.medium, ClimateCore.PACKAGE_ID, "dcs_food_medium", "food", 4);
		instance.regSimpleItem(FoodInit.broth, ClimateCore.PACKAGE_ID, "dcs_food_broth", "food", 1);
		instance.regSimpleItem(FoodInit.residue, ClimateCore.PACKAGE_ID, "dcs_food_residue", "food", 6);
		instance.regSimpleItem(FoodInit.antibiotic, ClimateCore.PACKAGE_ID, "dcs_food_antibiotic", "food", 9);
		instance.regSimpleItem(FoodInit.unidentified, ClimateCore.PACKAGE_ID, "dcs_food_unidentified", "food", 4);
		instance.regSimpleItem(FoodInit.bacillus, ClimateCore.PACKAGE_ID, "dcs_food_microbe_bacillus", "food", 2);
		instance.regSimpleItem(FoodInit.coliformes, ClimateCore.PACKAGE_ID, "dcs_food_microbe_coliformes", "food", 2);
		instance.regSimpleItem(FoodInit.lab, ClimateCore.PACKAGE_ID, "dcs_food_microbe_lab", "food", 2);
		instance.regSimpleItem(FoodInit.skin, ClimateCore.PACKAGE_ID, "dcs_food_microbe_skin", "food", 2);
		instance.regSimpleItem(FoodInit.cyano, ClimateCore.PACKAGE_ID, "dcs_food_microbe_cyano", "food", 2);
		instance.regSimpleItem(FoodInit.beerYeast, ClimateCore.PACKAGE_ID, "dcs_food_microbe_yeast", "food", 2);
		instance.regSimpleItem(FoodInit.oryzae, ClimateCore.PACKAGE_ID, "dcs_food_microbe_oryzae", "food", 2);
		instance.regSimpleItem(FoodInit.nether, ClimateCore.PACKAGE_ID, "dcs_food_microbe_nether", "food", 2);
		instance.regSimpleItem(FoodInit.blueMold, ClimateCore.PACKAGE_ID, "dcs_food_microbe_blue", "food", 2);
		instance.regSimpleItem(FoodInit.slimeMold, ClimateCore.PACKAGE_ID, "dcs_food_microbe_slime", "food", 2);
		instance.regSimpleItem(FoodInit.mushroom, ClimateCore.PACKAGE_ID, "dcs_food_microbe_musuroom", "food", 2);
		instance.regSimpleItem(FoodInit.methanogen, ClimateCore.PACKAGE_ID, "dcs_food_microbe_methanogen", "food", 2);
		instance.regSimpleItem(FoodInit.chickInEgg, ClimateCore.PACKAGE_ID, "dcs_food_chick", "food", 0);
		instance.regSimpleItem(FoodInit.liquorBottle, ClimateCore.PACKAGE_ID, "dcs_liquor_bottle", "food", 17);
		instance.regSimpleItem(FoodInit.roseWaterBottle, ClimateCore.PACKAGE_ID, "dcs_water_bottle", "food", 3);
		instance.regSimpleItem(FoodInit.essentialOil, ClimateCore.PACKAGE_ID, "dcs_food_essential_oil", "food", 5);
		instance.regSimpleItem(FoodInit.inoculum, ClimateCore.PACKAGE_ID, "dcs_food_inoculum", "food", 1);
		// block
		instance.regSimpleBlock(FoodInit.leavesLemon, ClimateCore.PACKAGE_ID, "dcs_leaves_lemon", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesOlive, ClimateCore.PACKAGE_ID, "dcs_leaves_olive", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesTea, ClimateCore.PACKAGE_ID, "dcs_leaves_tea", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesMorus, ClimateCore.PACKAGE_ID, "dcs_leaves_morus", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesWalnut, ClimateCore.PACKAGE_ID, "dcs_leaves_walnut", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesDates, ClimateCore.PACKAGE_ID, "dcs_leaves_dates", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesDatesCrop, ClimateCore.PACKAGE_ID, "dcs_leaves_datescrop", "crop", 3);
		instance.regSimpleBlock(FoodInit.dish, ClimateCore.PACKAGE_ID, "dcs_build_dish", "build", 1);
		instance.regStateAndBlock(FoodInit.potteryPot, ClimateCore.PACKAGE_ID, "dcs_device_pottery_pot", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.skillet, ClimateCore.PACKAGE_ID, "dcs_device_skillet", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.steelPot, ClimateCore.PACKAGE_ID, "dcs_device_steel_pot", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.teaPot, ClimateCore.PACKAGE_ID, "dcs_device_tea_pot", "machine", 0, true);
		instance.regSimpleBlock(FoodInit.silkwormBox, ClimateCore.PACKAGE_ID, "dcs_device_silkworm_box", "device", 0);
		instance.regStateAndBlock(FoodInit.incubator, ClimateCore.PACKAGE_ID, "dcs_device_incubator", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.brewingTankWood, ClimateCore.PACKAGE_ID, "dcs_device_brewing_wood", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.brewingTank, ClimateCore.PACKAGE_ID, "dcs_device_brewing_under", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.brewingTankUpper, ClimateCore.PACKAGE_ID, "dcs_device_brewing_upper", "machine", 0, true);
		instance.regSimpleBlock(FoodInit.baseFertilizer, ClimateCore.PACKAGE_ID, "dcs_crop_fertilizer", "crop", 0);
		instance.regStateAndBlock(FoodInit.distillator, ClimateCore.PACKAGE_ID, "dcs_device_distillator", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.barrel, ClimateCore.PACKAGE_ID, "dcs_device_aging_barrel", "machine", 0, true);
		instance.regStateAndBlock(FoodInit.cropSeaweed, ClimateCore.PACKAGE_ID, "dcs_crop_seaweed", "crop", 3, false);
		instance.regStateAndBlock(FoodInit.cropGrape, ClimateCore.PACKAGE_ID, "dcs_crop_grape", "crop", 3, false);
		instance.regStateAndBlock(FoodInit.cropWisteria, ClimateCore.PACKAGE_ID, "dcs_crop_wisteria", "crop", 3, false);
		// lotus
		ModelLoader.setCustomStateMapper(FoodInit.cropLotusN, (new StateMap.Builder()).ignore(DCState.STAGE8, BlockLotusN.BLACK).build());
		ModelBakery.registerItemVariants(Item.getItemFromBlock(FoodInit.cropLotusN), new ModelResourceLocation("dcs_climate:dcs_crop_lotus2"));
		for (int i = 0; i < 16; i++) {
			int m = i & 7;
			int m2 = 0;
			if (m == 1) {
				m2 = 0;
			} else if (m == 2 || m == 3) {
				m2 = 1;
			} else if (m == 4 || m == 5) {
				m2 = 2;
			} else {
				m2 = 3;
			}
			ModelLoader.setCustomModelResourceLocation(Item
					.getItemFromBlock(FoodInit.cropLotusN), i, new ModelResourceLocation("dcs_climate:crop/dcs_crop_lotus2_" + m2, "inventory"));
		}
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(FoodInit.netherWineBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nether", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		if (ModuleConfig.food_advanced) {
			ModelLoader.setCustomStateMapper(FoodInit.netherWineBlock, new StateMapperBase() {
				final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nether", "fluid");

				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					return fluidModel;
				}
			});
		}
		// leaves color
		MinecraftForge.EVENT_BUS.register(new LeavesColorsDC());
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/fluid/";
		list.add(b + "beer_still");
		list.add(b + "wine_still");
		list.add(b + "akvavit_still");
		list.add(b + "brandy_still");
		list.add(b + "chorus_liquor_still");
		list.add(b + "clear_liquor_still");
		list.add(b + "rose_water_still");
		list.add(b + "rum_still");
		list.add(b + "whisky_still");
		list.add(b + "wine_still");
		list.add(b + "moromi_black_still");
		list.add(b + "cola_still");
		list.add(b + "tonic_water_still");
		list.add(b + "lemon_squash_still");
		list.add(b + "cider_still");
		return list;
	}
}
