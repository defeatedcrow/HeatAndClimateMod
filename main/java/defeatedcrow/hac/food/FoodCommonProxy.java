package defeatedcrow.hac.food;

import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.block.TileSilkwormBox;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.entity.*;
import defeatedcrow.hac.main.util.DCRegistryUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoodCommonProxy {

	public static void loadTE() {
		GameRegistry.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot");
		GameRegistry.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot");
		GameRegistry.registerTileEntity(TileTeaPot.class, "dcs_te_tea_pot");
		GameRegistry.registerTileEntity(TileSilkwormBox.class, "dcs_te_silkworm_box");
	}

	public static void loadEntity() {
		DCRegistryUtil.addEntity(RoundBreadEntity.class, "food", "r_bread");
		DCRegistryUtil.addEntity(SquareBreadEntity.class, "food", "s_bread");

		DCRegistryUtil.addEntity(FishStickEntity.class, "food", "fish_stick");
		DCRegistryUtil.addEntity(YakitoriStickEntity.class, "food", "yakitori_stick");
		DCRegistryUtil.addEntity(PorkStickEntity.class, "food", "pork_stick");
		DCRegistryUtil.addEntity(BeefStickEntity.class, "food", "beef_stick");
		DCRegistryUtil.addEntity(MuttonStickEntity.class, "food", "mutton_stick");
		DCRegistryUtil.addEntity(SquidStickEntity.class, "food", "squid_stick");
		DCRegistryUtil.addEntity(GoheiStickEntity.class, "food", "gohei_stick");
		DCRegistryUtil.addEntity(MarshmallowStick.class, "food", "marshmallow_stick");
		DCRegistryUtil.addEntity(MotuStickEntity.class, "food", "motu_stick");

		DCRegistryUtil.addEntity(EntityTeaCupSilver.class, "food", "cup_silver");
		DCRegistryUtil.addEntity(EntityTeaCupWhite.class, "food", "cup_white");
		DCRegistryUtil.addEntity(EntityTumbler.class, "food", "tumbler");

		DCRegistryUtil.addEntity(AppleTartEntity.class, "food", "tart_apple");
		DCRegistryUtil.addEntity(LemonTartEntity.class, "food", "tart_lemon");
		DCRegistryUtil.addEntity(SpinachQuicheEntity.class, "food", "quiche_spinach");
		DCRegistryUtil.addEntity(PotatoQuicheEntity.class, "food", "quiche_potato");
		DCRegistryUtil.addEntity(CrostataTartEntity.class, "food", "tart_crostata");

		DCRegistryUtil.addEntity(SugarPieEntity.class, "food", "pie_sugar");
		DCRegistryUtil.addEntity(MeatPieEntity.class, "food", "pie_meat");
		DCRegistryUtil.addEntity(ChocolatePieEntity.class, "food", "pie_chocolate");
		DCRegistryUtil.addEntity(FruitPieEntity.class, "food", "pie_fruit");
		DCRegistryUtil.addEntity(PizzaTomatoEntity.class, "food", "pizza_tomato");
		DCRegistryUtil.addEntity(RoundBreadCreamEntity.class, "food", "r_bread_cream");
		DCRegistryUtil.addEntity(CustardPieEntity.class, "food", "pie_custard");
		DCRegistryUtil.addEntity(MooncakeEntity.class, "food", "moon_cake");

		DCRegistryUtil.addEntity(ToastBreadEntity.class, "food", "toast");
		DCRegistryUtil.addEntity(EntitySandwich.class, "food", "sandwich_apple");
		DCRegistryUtil.addEntity(EggSandwichEntity.class, "food", "sandwich_egg");
		DCRegistryUtil.addEntity(LemonSandwichEntity.class, "food", "sandwich_lemon");
		DCRegistryUtil.addEntity(SaladSandwichEntity.class, "food", "sandwich_salad");
		DCRegistryUtil.addEntity(ClubSandwichSEntity.class, "food", "clubsand_square");
		DCRegistryUtil.addEntity(ClubSandwichREntity.class, "food", "clubsand_round");
		DCRegistryUtil.addEntity(ToastFrenchEntity.class, "food", "toast_french");
		DCRegistryUtil.addEntity(ToastGarlicEntity.class, "food", "toast_garlic");
		DCRegistryUtil.addEntity(PitaBreadEntity.class, "food", "pita_bread");
		DCRegistryUtil.addEntity(PancakeEntity.class, "food", "pancake");
		DCRegistryUtil.addEntity(WalnutBreadEntity.class, "food", "walnut_bread");
		DCRegistryUtil.addEntity(GingermanEntity.class, "food", "gingerman");
		DCRegistryUtil.addEntity(TortillaEntity.class, "food", "tortilla");

		DCRegistryUtil.addEntity(EntityRiceBowl.class, "food", "rice_bowl");
		DCRegistryUtil.addEntity(EntityRiceMushroom.class, "food", "rice_mushroom");
		DCRegistryUtil.addEntity(EntityRiceBall.class, "food", "rice_ball");
		DCRegistryUtil.addEntity(EntityRiceBallSeaweed.class, "food", "rice_ball_seaweed");
		DCRegistryUtil.addEntity(EntityRiceBallMiso.class, "food", "rice_ball_miso");
		DCRegistryUtil.addEntity(MochiEntity.class, "food", "mochi");

		DCRegistryUtil.addEntity(EmptyPlateEntity.class, "food", "empty_steakplate");
		DCRegistryUtil.addEntity(BeefPlateEntity.class, "food", "plate_beef");
		DCRegistryUtil.addEntity(PorkPlateEntity.class, "food", "plate_pork");
		DCRegistryUtil.addEntity(ChickenPlateEntity.class, "food", "plate_chicken");
		DCRegistryUtil.addEntity(FishPlateEntity.class, "food", "plate_fish");
		DCRegistryUtil.addEntity(BigGarlicPlateEntity.class, "food", "plate_big_garlic");
		DCRegistryUtil.addEntity(PotatoPlateEntity.class, "food", "plate_potato");
		DCRegistryUtil.addEntity(SoupPlateEntity.class, "food", "soup_tomato");
		DCRegistryUtil.addEntity(PlateGratinEntity.class, "food", "plate_gratin");
		DCRegistryUtil.addEntity(PlatePaellaEntity.class, "food", "plate_paella");

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

		DCRegistryUtil.addEntity(IceCreamEntity.class, "food", "icecream_milk");
		DCRegistryUtil.addEntity(IceCreamKinakoEntity.class, "food", "icecream_kinako");
		DCRegistryUtil.addEntity(IceCreamBerryEntity.class, "food", "icecream_berry");
		DCRegistryUtil.addEntity(IceCreamLemonEntity.class, "food", "icecream_lemon");
		DCRegistryUtil.addEntity(IceCreamCookieEntity.class, "food", "icecream_cookie");

		DCRegistryUtil.addEntity(WagashiKinakoEntity.class, "food", "wagashi_kinako");
		DCRegistryUtil.addEntity(WagashiIsobeEntity.class, "food", "wagashi_isobe");
		DCRegistryUtil.addEntity(WagashiZundaEntity.class, "food", "wagashi_zunda");
		DCRegistryUtil.addEntity(WagashiButterEntity.class, "food", "wagashi_butter");
		DCRegistryUtil.addEntity(WagashiStrawberryEntity.class, "food", "wagashi_strawberry");
		DCRegistryUtil.addEntity(WagashiKurumiEntity.class, "food", "wagashi_kurumi");
		DCRegistryUtil.addEntity(WagashiKurimanjuEntity.class, "food", "wagashi_kurimanju");
		DCRegistryUtil.addEntity(WagashiNerikiriEntity.class, "food", "wagashi_nerikiri");
		DCRegistryUtil.addEntity(WagashiAbekawaEntity.class, "food", "wagashi_abekawa");

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

		DCRegistryUtil.addEntity(DrinkGingerEntity.class, "food", "drink_ginger");
		DCRegistryUtil.addEntity(DrinkKuzuEntity.class, "food", "drink_kuzu");
		DCRegistryUtil.addEntity(DrinkTomatoEntity.class, "food", "drink_tomato");

		DCRegistryUtil.addEntity(UdonMeatEntity.class, "food", "udon_meat");
		DCRegistryUtil.addEntity(UdonSeaweedEntity.class, "food", "udon_seaweed");
		DCRegistryUtil.addEntity(UdonEggEntity.class, "food", "udon_egg");

	}

}
