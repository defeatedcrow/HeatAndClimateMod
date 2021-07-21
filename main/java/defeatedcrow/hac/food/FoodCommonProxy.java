package defeatedcrow.hac.food;

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
import defeatedcrow.hac.food.block.crop.TileEntityLotus;
import defeatedcrow.hac.food.entity.*;
import defeatedcrow.hac.main.util.DCRegistryUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoodCommonProxy {

	public static void loadTE() {
		GameRegistry.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot");
		GameRegistry.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot");
		GameRegistry.registerTileEntity(TileTeaPot.class, "dcs_te_tea_pot");
		GameRegistry.registerTileEntity(TileSilkwormBox.class, "dcs_te_silkworm_box");
		GameRegistry.registerTileEntity(TileSkillet.class, "dcs_te_skillet");
		GameRegistry.registerTileEntity(TileEntityLotus.class, "dcs_te_crop_lotus");
		GameRegistry.registerTileEntity(TileIncubator.class, "dcs_te_incubator");
		GameRegistry.registerTileEntity(TileBrewingTankWood.class, "dcs_te_brewing_wood");
		GameRegistry.registerTileEntity(TileBrewingTankUnder.class, "dcs_te_brewing_under");
		GameRegistry.registerTileEntity(TileBrewingTankUpper.class, "dcs_te_brewing_upper");
		GameRegistry.registerTileEntity(TileStillPot.class, "dcs_te_distillator");
		GameRegistry.registerTileEntity(TileAgingBarrel.class, "dcs_te_aging_barrel");
	}

	public static void loadEntity() {
		DCRegistryUtil.addEntity(BreadRoundEntity.class, "food", "r_bread");
		DCRegistryUtil.addEntity(BreadSquareEntity.class, "food", "s_bread");

		DCRegistryUtil.addEntity(StickFishEntity.class, "food", "fish_stick");
		DCRegistryUtil.addEntity(StickYakitoriEntity.class, "food", "yakitori_stick");
		DCRegistryUtil.addEntity(StickPorkEntity.class, "food", "pork_stick");
		DCRegistryUtil.addEntity(StickBeefEntity.class, "food", "beef_stick");
		DCRegistryUtil.addEntity(StickMuttonEntity.class, "food", "mutton_stick");
		DCRegistryUtil.addEntity(StickSquidEntity.class, "food", "squid_stick");
		DCRegistryUtil.addEntity(StickGoheiEntity.class, "food", "gohei_stick");
		DCRegistryUtil.addEntity(StickMarshmallowEntity.class, "food", "marshmallow_stick");
		DCRegistryUtil.addEntity(StickMotuEntity.class, "food", "motu_stick");

		DCRegistryUtil.addEntity(EntityTeaCupSilver.class, "food", "cup_silver");
		DCRegistryUtil.addEntity(EntityTeaCupWhite.class, "food", "cup_white");
		DCRegistryUtil.addEntity(EntityTumbler.class, "food", "tumbler");

		DCRegistryUtil.addEntity(TartAppleEntity.class, "food", "tart_apple");
		DCRegistryUtil.addEntity(TartLemonEntity.class, "food", "tart_lemon");
		DCRegistryUtil.addEntity(QuicheSpinachEntity.class, "food", "quiche_spinach");
		DCRegistryUtil.addEntity(QuichePotatoEntity.class, "food", "quiche_potato");
		DCRegistryUtil.addEntity(TartCrostataEntity.class, "food", "tart_crostata");
		DCRegistryUtil.addEntity(TartChocolateEntity.class, "food", "tart_chocolate");
		DCRegistryUtil.addEntity(TartCustardEntity.class, "food", "tart_custard");
		DCRegistryUtil.addEntity(TartLiverEntity.class, "food", "tart_liver");

		DCRegistryUtil.addEntity(PieSugarEntity.class, "food", "pie_sugar");
		DCRegistryUtil.addEntity(PieMeatEntity.class, "food", "pie_meat");
		DCRegistryUtil.addEntity(PieChocolateEntity.class, "food", "pie_chocolate");
		DCRegistryUtil.addEntity(PieFruitEntity.class, "food", "pie_fruit");
		DCRegistryUtil.addEntity(PizzaTomatoEntity.class, "food", "pizza_tomato");
		DCRegistryUtil.addEntity(BreadRoundCreamEntity.class, "food", "r_bread_cream");
		DCRegistryUtil.addEntity(PieCustardEntity.class, "food", "pie_custard");
		DCRegistryUtil.addEntity(PieMooncakeEntity.class, "food", "moon_cake");

		DCRegistryUtil.addEntity(PizzaRoeEntity.class, "food", "pizza_roe");
		DCRegistryUtil.addEntity(PizzaTeriyakiEntity.class, "food", "pizza_teriyaki");

		DCRegistryUtil.addEntity(CookieCrowEntity.class, "food", "cookie_crow");
		DCRegistryUtil.addEntity(CookieUnagiEntity.class, "food", "cookie_unagi");

		DCRegistryUtil.addEntity(BreadToastEntity.class, "food", "toast");
		DCRegistryUtil.addEntity(SandwichEntity.class, "food", "sandwich_apple");
		DCRegistryUtil.addEntity(SandwichEggEntity.class, "food", "sandwich_egg");
		DCRegistryUtil.addEntity(SandwichLemonEntity.class, "food", "sandwich_lemon");
		DCRegistryUtil.addEntity(SaladSandwichEntity.class, "food", "sandwich_salad");
		DCRegistryUtil.addEntity(SandwichLiverEntity.class, "food", "sandwich_liver");
		DCRegistryUtil.addEntity(SandwichSalmonEntity.class, "food", "sandwich_salmon");
		DCRegistryUtil.addEntity(ClubSandwichSEntity.class, "food", "clubsand_square");
		DCRegistryUtil.addEntity(ClubSandwichREntity.class, "food", "clubsand_round");
		DCRegistryUtil.addEntity(BreadToastFrenchEntity.class, "food", "toast_french");
		DCRegistryUtil.addEntity(BreadToastGarlicEntity.class, "food", "toast_garlic");
		DCRegistryUtil.addEntity(BreadPitaEntity.class, "food", "pita_bread");
		DCRegistryUtil.addEntity(BreadPancakeEntity.class, "food", "pancake");
		DCRegistryUtil.addEntity(BreadWalnutEntity.class, "food", "walnut_bread");
		DCRegistryUtil.addEntity(BreadGingermanEntity.class, "food", "gingerman");
		DCRegistryUtil.addEntity(BreadTortillaEntity.class, "food", "tortilla");
		DCRegistryUtil.addEntity(BreadRaisinEntity.class, "food", "raisin_bread");
		DCRegistryUtil.addEntity(BreadSausageEntity.class, "food", "sausage_bread");
		DCRegistryUtil.addEntity(BreadGrahamCrackerEntity.class, "food", "graham_cracker");
		DCRegistryUtil.addEntity(BreadCinnamonEntity.class, "food", "cinnamon_roll");
		DCRegistryUtil.addEntity(BreadAnkoEntity.class, "food", "anko");

		DCRegistryUtil.addEntity(EntityRiceBowl.class, "food", "rice_bowl");
		DCRegistryUtil.addEntity(EntityRiceMushroom.class, "food", "rice_mushroom");
		DCRegistryUtil.addEntity(EntityRiceBall.class, "food", "rice_ball");
		DCRegistryUtil.addEntity(EntityRiceBallSeaweed.class, "food", "rice_ball_seaweed");
		DCRegistryUtil.addEntity(EntityRiceBallMiso.class, "food", "rice_ball_miso");
		DCRegistryUtil.addEntity(EntityRiceBallRoe.class, "food", "rice_ball_roe");
		DCRegistryUtil.addEntity(EntityRiceTyadukeSalmon.class, "food", "rice_tyaduke_salmon");
		DCRegistryUtil.addEntity(EntityRiceTyadukeRoe.class, "food", "rice_tyaduke_roe");
		DCRegistryUtil.addEntity(EntityRiceSekihan.class, "food", "rice_sekihan");
		DCRegistryUtil.addEntity(EntityRiceShiruko.class, "food", "rice_shiruko");
		DCRegistryUtil.addEntity(MochiEntity.class, "food", "mochi");

		DCRegistryUtil.addEntity(EmptyPlateEntity.class, "food", "empty_steakplate");
		DCRegistryUtil.addEntity(PlateBeefEntity.class, "food", "plate_beef");
		DCRegistryUtil.addEntity(PlatePorkEntity.class, "food", "plate_pork");
		DCRegistryUtil.addEntity(PlateChickenEntity.class, "food", "plate_chicken");
		DCRegistryUtil.addEntity(PlateFishEntity.class, "food", "plate_fish");
		DCRegistryUtil.addEntity(PlateBigGarlicEntity.class, "food", "plate_big_garlic");
		DCRegistryUtil.addEntity(PlateBigChickenEntity.class, "food", "plate_big_chicken");
		DCRegistryUtil.addEntity(PlatePotatoEntity.class, "food", "plate_potato");
		DCRegistryUtil.addEntity(PlateSoupEntity.class, "food", "soup_tomato");
		DCRegistryUtil.addEntity(PlateGratinEntity.class, "food", "plate_gratin");
		DCRegistryUtil.addEntity(PlatePaellaEntity.class, "food", "plate_paella");
		DCRegistryUtil.addEntity(PlateMeatPaellaEntity.class, "food", "plate_meat_paella");
		DCRegistryUtil.addEntity(PlateGyozaEntity.class, "food", "plate_meat_gyoza");

		DCRegistryUtil.addEntity(StewVegiEntity.class, "food", "stew_vegi");
		DCRegistryUtil.addEntity(StewEggEntity.class, "food", "stew_egg");
		DCRegistryUtil.addEntity(StewTomatoEntity.class, "food", "stew_tomato");
		DCRegistryUtil.addEntity(StewCongeeEntity.class, "food", "stew_congee");
		DCRegistryUtil.addEntity(StewPumpukinEntity.class, "food", "stew_pumpkin");
		DCRegistryUtil.addEntity(StewBorschtEntity.class, "food", "stew_borscht");
		DCRegistryUtil.addEntity(StewMushroomEntity.class, "food", "stew_mushroom");
		DCRegistryUtil.addEntity(StewPurpleEntity.class, "food", "stew_purple");
		DCRegistryUtil.addEntity(StewLotusrootEntity.class, "food", "stew_lotusroot");
		DCRegistryUtil.addEntity(StewSquidEntity.class, "food", "stew_squid");
		DCRegistryUtil.addEntity(StewSeaweedEntity.class, "food", "stew_seaweed");
		DCRegistryUtil.addEntity(StewMisosoupEntity.class, "food", "stew_misosoup");
		DCRegistryUtil.addEntity(StewMotsuEntity.class, "food", "stew_motsu");
		DCRegistryUtil.addEntity(StewChiliEntity.class, "food", "stew_chili");
		DCRegistryUtil.addEntity(StewGarlicOilEntity.class, "food", "stew_garlic_oil");
		DCRegistryUtil.addEntity(StewLazijiEntity.class, "food", "stew_laziji");

		DCRegistryUtil.addEntity(SaladGreenEntity.class, "food", "salad_green");
		DCRegistryUtil.addEntity(SaladLotusrootEntity.class, "food", "salad_lotusroot");
		DCRegistryUtil.addEntity(SaladPotatoEntity.class, "food", "salad_potato");
		DCRegistryUtil.addEntity(SimmeredSoyEntity.class, "food", "simmered_soy");
		DCRegistryUtil.addEntity(SimmeredGomokuEntity.class, "food", "simmered_gomoku");
		DCRegistryUtil.addEntity(SimmeredSpinachEntity.class, "food", "simmered_spinach");
		DCRegistryUtil.addEntity(SimmeredBeansEntity.class, "food", "simmered_beans");
		DCRegistryUtil.addEntity(SimmeredNattoEntity.class, "food", "simmered_natto");
		DCRegistryUtil.addEntity(SimmeredPumpkinEntity.class, "food", "simmered_pumpkin");
		DCRegistryUtil.addEntity(SaladSalmonEntity.class, "food", "salad_salmon");
		DCRegistryUtil.addEntity(SaladTofuEntity.class, "food", "salad_tofu");
		DCRegistryUtil.addEntity(SaladWalnutEntity.class, "food", "salad_walnut");
		DCRegistryUtil.addEntity(SaladWatermelonEntity.class, "food", "salad_watermelon");

		DCRegistryUtil.addEntity(MealFriedPotatoEntity.class, "food", "fried_potato");
		DCRegistryUtil.addEntity(MealFishAndChipsEntity.class, "food", "fish_and_chips");
		DCRegistryUtil.addEntity(MealCroquettePotatoEntity.class, "food", "croquette_potato");
		DCRegistryUtil.addEntity(MealCroquettePumpkinEntity.class, "food", "croquette_pumpkin");
		DCRegistryUtil.addEntity(MealShawarmaEntity.class, "food", "shawarma");
		DCRegistryUtil.addEntity(MealFalafelSandEntity.class, "food", "falafel_sandwich");

		DCRegistryUtil.addEntity(FriedPorkEntity.class, "food", "fried_pork");
		DCRegistryUtil.addEntity(FriedChickenEntity.class, "food", "fried_chicken");
		DCRegistryUtil.addEntity(FriedFishEntity.class, "food", "fried_fish");
		DCRegistryUtil.addEntity(FriedFalafelEntity.class, "food", "fried_falafel");
		DCRegistryUtil.addEntity(FriedFishcakeEntity.class, "food", "fried_fishcake");
		DCRegistryUtil.addEntity(FriedPorkGingerEntity.class, "food", "fried_porkginger");
		DCRegistryUtil.addEntity(FriedPrawnEntity.class, "food", "fried_prawn");

		DCRegistryUtil.addEntity(MealBreakfastBEntity.class, "food", "breakfast_b");
		DCRegistryUtil.addEntity(MealBreakfastJEntity.class, "food", "breakfast_j");
		DCRegistryUtil.addEntity(MealBreakfastJ2Entity.class, "food", "breakfast_j2");

		DCRegistryUtil.addEntity(CakeButterEntity.class, "food", "cake_butter");
		DCRegistryUtil.addEntity(CakeChocolateEntity.class, "food", "cake_chocolate");
		DCRegistryUtil.addEntity(CakeCoffeeEntity.class, "food", "cake_coffee");
		DCRegistryUtil.addEntity(CakeLemonEntity.class, "food", "cake_lemon");
		DCRegistryUtil.addEntity(CakeCreamEntity.class, "food", "cake_bakedcream");
		DCRegistryUtil.addEntity(CakeBerryEntity.class, "food", "cake_berry");
		DCRegistryUtil.addEntity(CakeKuzuEntity.class, "food", "cake_kuzu");
		DCRegistryUtil.addEntity(CakeCocotteEntity.class, "food", "cake_cocotte");
		DCRegistryUtil.addEntity(CakeToffeeEntity.class, "food", "cake_toffee");
		DCRegistryUtil.addEntity(CakeTowerEntity.class, "food", "cake_tower");
		DCRegistryUtil.addEntity(CakeGrapeEntity.class, "food", "cake_grape");
		DCRegistryUtil.addEntity(CakeOnionEntity.class, "food", "cake_onion_soup");
		DCRegistryUtil.addEntity(CakeSmoreEntity.class, "food", "cake_smore");
		DCRegistryUtil.addEntity(CakeRaisinEntity.class, "food", "cake_raisin");
		DCRegistryUtil.addEntity(CakeYogurtEntity.class, "food", "cake_yogurt");
		DCRegistryUtil.addEntity(CakeGreenteaEntity.class, "food", "cake_greentea");

		DCRegistryUtil.addEntity(YogurtPlainEntity.class, "food", "yogurt_plain");
		DCRegistryUtil.addEntity(YogurtSPEntity.class, "food", "yogurt_special");

		DCRegistryUtil.addEntity(IceCreamEntity.class, "food", "icecream_milk");
		DCRegistryUtil.addEntity(IceCreamKinakoEntity.class, "food", "icecream_kinako");
		DCRegistryUtil.addEntity(IceCreamBerryEntity.class, "food", "icecream_berry");
		DCRegistryUtil.addEntity(IceCreamLemonEntity.class, "food", "icecream_lemon");
		DCRegistryUtil.addEntity(IceCreamCookieEntity.class, "food", "icecream_cookie");
		DCRegistryUtil.addEntity(IceCreamCocoaEntity.class, "food", "icecream_cocoa");
		DCRegistryUtil.addEntity(ParfaitBerryEntity.class, "food", "parfait_berry");
		DCRegistryUtil.addEntity(ParfaitCitrusEntity.class, "food", "parfait_citrus");
		DCRegistryUtil.addEntity(ParfaitWatermelonEntity.class, "food", "parfait_watermelon");
		DCRegistryUtil.addEntity(ParfaitAnkoEntity.class, "food", "parfait_anko");

		DCRegistryUtil.addEntity(WagashiKinakoEntity.class, "food", "wagashi_kinako");
		DCRegistryUtil.addEntity(WagashiIsobeEntity.class, "food", "wagashi_isobe");
		DCRegistryUtil.addEntity(WagashiZundaEntity.class, "food", "wagashi_zunda");
		DCRegistryUtil.addEntity(WagashiButterEntity.class, "food", "wagashi_butter");
		DCRegistryUtil.addEntity(WagashiStrawberryEntity.class, "food", "wagashi_strawberry");
		DCRegistryUtil.addEntity(WagashiKurumiEntity.class, "food", "wagashi_kurumi");
		DCRegistryUtil.addEntity(WagashiKurimanjuEntity.class, "food", "wagashi_kurimanju");
		DCRegistryUtil.addEntity(WagashiNerikiriEntity.class, "food", "wagashi_nerikiri");
		DCRegistryUtil.addEntity(WagashiAbekawaEntity.class, "food", "wagashi_abekawa");
		DCRegistryUtil.addEntity(WagashiYoukanEntity.class, "food", "wagashi_youkan");
		DCRegistryUtil.addEntity(WagashiYatsuhashiEntity.class, "food", "wagashi_yatsuhashi");
		DCRegistryUtil.addEntity(WagashiBotamochiEntity.class, "food", "wagashi_botamochi");

		DCRegistryUtil.addEntity(DishCapreseEntity.class, "food", "dish_caprese");
		DCRegistryUtil.addEntity(DishBruschettaEntity.class, "food", "dish_bruschetta");
		DCRegistryUtil.addEntity(DishSalmonEntity.class, "food", "dish_salmon");
		DCRegistryUtil.addEntity(DishSushiEntity.class, "food", "dish_sushi");
		DCRegistryUtil.addEntity(DishSashimiEntity.class, "food", "dish_sashimi");
		DCRegistryUtil.addEntity(DishMisoniEntity.class, "food", "dish_misoni");
		DCRegistryUtil.addEntity(DishTamagoEntity.class, "food", "dish_tamago");
		DCRegistryUtil.addEntity(DishYakkoEntity.class, "food", "dish_yakko");

		DCRegistryUtil.addEntity(DishMaboEntity.class, "food", "dish_mabo");
		DCRegistryUtil.addEntity(DishOmericeEntity.class, "food", "dish_omerice");
		DCRegistryUtil.addEntity(DishTacoEntity.class, "food", "dish_taco");
		DCRegistryUtil.addEntity(DishTacoriceEntity.class, "food", "dish_tacorice");
		DCRegistryUtil.addEntity(DishNachosEntity.class, "food", "dish_nachos");
		DCRegistryUtil.addEntity(DishIkameshiEntity.class, "food", "dish_ikameshi");
		DCRegistryUtil.addEntity(DishBiriyaniEntity.class, "food", "dish_biriyani");
		DCRegistryUtil.addEntity(DishChickenRiceEntity.class, "food", "dish_chicken_rice");
		DCRegistryUtil.addEntity(DishTomyumEntity.class, "food", "dish_tomyum");

		DCRegistryUtil.addEntity(CurryVegiEntity.class, "food", "curry_vegi");
		DCRegistryUtil.addEntity(CurryBeansEntity.class, "food", "curry_beans");
		DCRegistryUtil.addEntity(CurryFishEntity.class, "food", "curry_fish");
		DCRegistryUtil.addEntity(CurrySpinachEntity.class, "food", "curry_spinach");
		DCRegistryUtil.addEntity(CurryButterChickenEntity.class, "food", "curry_butter_chicken");
		DCRegistryUtil.addEntity(CurryVindalooEntity.class, "food", "curry_vindaloo");
		DCRegistryUtil.addEntity(CurryGreenEntity.class, "food", "curry_green");
		DCRegistryUtil.addEntity(CurryRiceEntity.class, "food", "curry_rice");

		DCRegistryUtil.addEntity(DrinkGingerEntity.class, "food", "drink_ginger");
		DCRegistryUtil.addEntity(DrinkKuzuEntity.class, "food", "drink_kuzu");
		DCRegistryUtil.addEntity(DrinkTomatoEntity.class, "food", "drink_tomato");

		DCRegistryUtil.addEntity(SauceLiverEntity.class, "food", "sauce_liver");
		DCRegistryUtil.addEntity(SauceRaisinEntity.class, "food", "sauce_raisin");
		DCRegistryUtil.addEntity(SauceSalsaEntity.class, "food", "sauce_salsa");

		DCRegistryUtil.addEntity(UdonMeatEntity.class, "food", "udon_meat");
		DCRegistryUtil.addEntity(UdonSeaweedEntity.class, "food", "udon_seaweed");
		DCRegistryUtil.addEntity(UdonEggEntity.class, "food", "udon_egg");

		DCRegistryUtil.addEntity(PastaOilEntity.class, "food", "pasta_oil");
		DCRegistryUtil.addEntity(PastaBasilEntity.class, "food", "pasta_basil");
		DCRegistryUtil.addEntity(PastaTomatoEntity.class, "food", "pasta_tomato");
		DCRegistryUtil.addEntity(PastaPrawnEntity.class, "food", "pasta_prawn");
		DCRegistryUtil.addEntity(PastaCodEntity.class, "food", "pasta_cod");
		DCRegistryUtil.addEntity(PastaSquidEntity.class, "food", "pasta_squid");
		DCRegistryUtil.addEntity(PastaBeefEntity.class, "food", "pasta_beef");
		DCRegistryUtil.addEntity(PastaRoeEntity.class, "food", "pasta_roe");

		DCRegistryUtil.addEntity(LiquorBottleEntity.class, "food", "liquor_bottle");
		DCRegistryUtil.addEntity(WaterBottleEntity.class, "food", "water_bottle");

		DCRegistryUtil.addEntity(CutleryChopsticksEntity.class, "food", "cutlery_chopsticks");
		DCRegistryUtil.addEntity(CutlerySpoonEntity.class, "food", "cutlery_spoon");
		DCRegistryUtil.addEntity(CutleryForkEntity.class, "food", "cutlery_fork");

	}

}
