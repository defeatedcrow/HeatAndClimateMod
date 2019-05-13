package defeatedcrow.hac.food;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.block.TileSilkwormBox;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.client.*;
import defeatedcrow.hac.food.entity.*;
import defeatedcrow.hac.main.client.ClientMainProxy;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FoodClientProxy {

	public static void loadConst() {

	}

	public static void loadEntity() {
		ClientMainProxy.registRender(RoundBreadEntity.class, RoundBreadRenderer.class);
		ClientMainProxy.registRender(SquareBreadEntity.class, SquareBreadRenderer.class);
		ClientMainProxy.registRender(FishStickEntity.class, FishStickRenderer.class);
		ClientMainProxy.registRender(YakitoriStickEntity.class, YakitoriStickRenderer.class);
		ClientMainProxy.registRender(PorkStickEntity.class, PorkStickRenderer.class);
		ClientMainProxy.registRender(BeefStickEntity.class, BeefStickRenderer.class);
		ClientMainProxy.registRender(EntityTeaCupSilver.class, CupSilverRenderer.class);
		ClientMainProxy.registRender(EntityTeaCupWhite.class, CupWhiteRenderer.class);
		ClientMainProxy.registRender(AppleTartEntity.class, AppleTartRenderer.class);
		ClientMainProxy.registRender(LemonTartEntity.class, LemonTartRenderer.class);
		ClientMainProxy.registRender(SpinachQuicheEntity.class, SpinachQuicheRenderer.class);
		ClientMainProxy.registRender(PotatoQuicheEntity.class, PotatoQuicheRenderer.class);
		ClientMainProxy.registRender(SugarPieEntity.class, SugarPieRenderer.class);
		ClientMainProxy.registRender(MeatPieEntity.class, MeatPieRenderer.class);
		ClientMainProxy.registRender(ToastBreadEntity.class, ToastRenderer.class);
		ClientMainProxy.registRender(EntitySandwich.class, SandwichAppleRenderer.class);
		ClientMainProxy.registRender(EggSandwichEntity.class, SandwichEggRenderer.class);
		ClientMainProxy.registRender(LemonSandwichEntity.class, SandwichAppleRenderer.class);
		ClientMainProxy.registRender(SaladSandwichEntity.class, SandwichSaladRenderer.class);
		ClientMainProxy.registRender(EntityRiceBowl.class, RiceBowlRenderer.class);
		ClientMainProxy.registRender(EmptyPlateEntity.class, EmptyPlateRenderer.class);
		ClientMainProxy.registRender(BeefPlateEntity.class, PlateBeefRenderer.class);
		ClientMainProxy.registRender(PorkPlateEntity.class, PlatePorkRenderer.class);
		ClientMainProxy.registRender(ChickenPlateEntity.class, PlateChickenRenderer.class);
		ClientMainProxy.registRender(FishPlateEntity.class, PlateFishRenderer.class);
		ClientMainProxy.registRender(PotatoPlateEntity.class, PlatePotatoRenderer.class);
		ClientMainProxy.registRender(SoupPlateEntity.class, PlateTomatoRenderer.class);
		ClientMainProxy.registRender(ChocolatePieEntity.class, ChocolatePieRenderer.class);
		ClientMainProxy.registRender(FruitPieEntity.class, FruitPieRenderer.class);
		ClientMainProxy.registRender(EntityTumbler.class, TumblerRenderer.class);
		ClientMainProxy.registRender(EntityRiceMushroom.class, RiceMushroomRenderer.class);
		ClientMainProxy.registRender(ClubSandwichSEntity.class, ClubSandwichSRenderer.class);
		ClientMainProxy.registRender(ClubSandwichREntity.class, ClubSandwichRRenderer.class);
		ClientMainProxy.registRender(StewVegiEntity.class, StewVegiRenderer.class);
		ClientMainProxy.registRender(StewEggEntity.class, StewEggRenderer.class);
		ClientMainProxy.registRender(StewCongeeEntity.class, StewCongeeRenderer.class);
		ClientMainProxy.registRender(StewTomatoEntity.class, StewTomatoRenderer.class);
		ClientMainProxy.registRender(StewPumpukinEntity.class, StewPumpkinRenderer.class);
		ClientMainProxy.registRender(StewBorschtEntity.class, StewBorschtRenderer.class);
		ClientMainProxy.registRender(StewMushroomEntity.class, StewMushroomRenderer.class);
		ClientMainProxy.registRender(StewPurpleEntity.class, StewPurpleRenderer.class);
		ClientMainProxy.registRender(MuttonStickEntity.class, MuttonStickRenderer.class);
		ClientMainProxy.registRender(SquidStickEntity.class, SquidStickRenderer.class);
		ClientMainProxy.registRender(MooncakeEntity.class, MooncakeRenderer.class);
		ClientMainProxy.registRender(StewLotusrootEntity.class, StewLotusrootRenderer.class);
		ClientMainProxy.registRender(StewSquidEntity.class, StewSquidRenderer.class);
		ClientMainProxy.registRender(PizzaTomatoEntity.class, PizzaTomatoRenderer.class);
		ClientMainProxy.registRender(SaladGreenEntity.class, SaladGreenRenderer.class);
		ClientMainProxy.registRender(SaladPotatoEntity.class, SaladPotatoRenderer.class);
		ClientMainProxy.registRender(SaladLotusrootEntity.class, SaladLotusrootRenderer.class);
		ClientMainProxy.registRender(CakeButterEntity.class, CakeButterRenderer.class);
		ClientMainProxy.registRender(CakeChocolateEntity.class, CakeChocolateRenderer.class);
		ClientMainProxy.registRender(CakeCoffeeEntity.class, JellyCoffeeRenderer.class);
		ClientMainProxy.registRender(CakeLemonEntity.class, JellyLemonRenderer.class);
		ClientMainProxy.registRender(CakeCreamEntity.class, JellyCreamRenderer.class);
		ClientMainProxy.registRender(CustardPieEntity.class, CustardPieRenderer.class);
		ClientMainProxy.registRender(RoundBreadCreamEntity.class, RoundBreadCreamRenderer.class);
		ClientMainProxy.registRender(StewSeaweedEntity.class, StewSeaweedRenderer.class);
		ClientMainProxy.registRender(CrostataTartEntity.class, CrostataTartRenderer.class);
		ClientMainProxy.registRender(CakeBerryEntity.class, JellyBerryRenderer.class);
		ClientMainProxy.registRender(CakeKuzuEntity.class, JellyKuzuRenderer.class);
		ClientMainProxy.registRender(CakeCocotteEntity.class, CakeCocotteRenderer.class);
		ClientMainProxy.registRender(EntityRiceBall.class, RiceBallRenderer.class);
		ClientMainProxy.registRender(SimmeredSoyEntity.class, SimmeredSoyRenderer.class);
		ClientMainProxy.registRender(SimmeredGomokuEntity.class, SimmeredGomokuRenderer.class);
		ClientMainProxy.registRender(EntityRiceBallSeaweed.class, RiceBallSeaweedRenderer.class);
		ClientMainProxy.registRender(IceCreamEntity.class, IcecreamMilkRenderer.class);
		ClientMainProxy.registRender(IceCreamKinakoEntity.class, IcecreamKinakoRenderer.class);
		ClientMainProxy.registRender(IceCreamBerryEntity.class, IcecreamBerryRenderer.class);
		ClientMainProxy.registRender(IceCreamLemonEntity.class, IcecreamLemonRenderer.class);
		ClientMainProxy.registRender(IceCreamCookieEntity.class, IcecreamCookieRenderer.class);
		ClientMainProxy.registRender(EntityRiceBallMiso.class, RiceBallMisoRenderer.class);
		ClientMainProxy.registRender(MochiEntity.class, MochiRenderer.class);
		ClientMainProxy.registRender(GoheiStickEntity.class, GoheiStickRenderer.class);
		ClientMainProxy.registRender(StewMisosoupEntity.class, StewMisosoupRenderer.class);
		ClientMainProxy.registRender(StewMotsuEntity.class, StewMotsuRenderer.class);
		ClientMainProxy.registRender(SimmeredSpinachEntity.class, SimmeredSpinachRenderer.class);
		ClientMainProxy.registRender(WagashiKinakoEntity.class, WagashiKinakoRenderer.class);
		ClientMainProxy.registRender(WagashiIsobeEntity.class, WagashiIsobeRenderer.class);
		ClientMainProxy.registRender(WagashiZundaEntity.class, WagashiZundaRenderer.class);
		ClientMainProxy.registRender(WagashiButterEntity.class, WagashiButterRenderer.class);
		ClientMainProxy.registRender(WagashiStrawberryEntity.class, WagashiStrawberryRenderer.class);
		ClientMainProxy.registRender(WagashiKurumiEntity.class, WagashiKurumiRenderer.class);
		ClientMainProxy.registRender(WagashiKurimanjuEntity.class, WagashiKurimanjuRenderer.class);
		ClientMainProxy.registRender(WagashiNerikiriEntity.class, WagashiNerikiriRenderer.class);
		ClientMainProxy.registRender(MarshmallowStick.class, MarshmallowStickRenderer.class);
		ClientMainProxy.registRender(ToastFrenchEntity.class, ToastFrenchRenderer.class);
		ClientMainProxy.registRender(ToastGarlicEntity.class, ToastGarlicRenderer.class);
		ClientMainProxy.registRender(PitaBreadEntity.class, PitaBreadRenderer.class);
		ClientMainProxy.registRender(PancakeEntity.class, PancakeRenderer.class);
		ClientMainProxy.registRender(StewChiliEntity.class, StewChiliRenderer.class);
		ClientMainProxy.registRender(StewGarlicOilEntity.class, StewGarlicOilRenderer.class);
		ClientMainProxy.registRender(StewLazijiEntity.class, StewLazijiRenderer.class);
		ClientMainProxy.registRender(SimmeredBeansEntity.class, SimmeredBeansRenderer.class);
		ClientMainProxy.registRender(SimmeredNattoEntity.class, SimmeredNattoRenderer.class);
		ClientMainProxy.registRender(CakeToffeeEntity.class, CakeToffeeRenderer.class);
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
		ClientMainProxy.registRender(MealBreakfastBEntity.class, SetmealBreakfastRenderer.class);
		ClientMainProxy.registRender(MealBreakfastJEntity.class, SetmealBreakfastJRenderer.class);
		ClientMainProxy.registRender(BigGarlicPlateEntity.class, PlateBigGarlicRenderer.class);
	}

	public static void loadTE() {
		ClientMainProxy.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot", new TESRPotteryPot());
		ClientMainProxy.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot", new TESRSteelPot());
		ClientMainProxy.registerTileEntity(TileTeaPot.class, "dcs_te_tea_pot", new TESRTeaPot());
		GameRegistry.registerTileEntity(TileSilkwormBox.class, "dcs_te_silkworm_box");
	}

	public static void regJson(JsonRegisterHelper instance) {
		// item

		instance.regSimpleItem(FoodInit.teaLeaves, ClimateCore.PACKAGE_ID, "dcs_food_leaves", "food", 2);
		instance.regSimpleItem(FoodInit.dropOil, ClimateCore.PACKAGE_ID, "dcs_food_drop_oil", "food", 0);
		instance.regSimpleItem(FoodInit.dropCream, ClimateCore.PACKAGE_ID, "dcs_food_drop_cream", "food", 0);
		instance.regSimpleItem(FoodInit.bread, ClimateCore.PACKAGE_ID, "dcs_round_bread", "food", 15);
		instance.regSimpleItem(FoodInit.sticks, ClimateCore.PACKAGE_ID, "dcs_stick_foods", "food", 15);
		instance.regSimpleItem(FoodInit.crops, ClimateCore.PACKAGE_ID, "dcs_crops", "food", 18);
		instance.regSimpleItem(FoodInit.seeds, ClimateCore.PACKAGE_ID, "dcs_seeds", "food", 14);
		instance.regSimpleItem(FoodInit.petals, ClimateCore.PACKAGE_ID, "dcs_petals", "food", 1);
		instance.regSimpleItem(FoodInit.cupSilver, ClimateCore.PACKAGE_ID, "dcs_food_teacup", "food", 2);
		instance.regSimpleItem(FoodInit.dairy, ClimateCore.PACKAGE_ID, "dcs_food_dairy", "food", 4);
		instance.regSimpleItem(FoodInit.meat, ClimateCore.PACKAGE_ID, "dcs_food_meat", "food", 8);
		instance.regSimpleItem(FoodInit.pastry, ClimateCore.PACKAGE_ID, "dcs_food_pastry", "food", 0);
		instance.regSimpleItem(FoodInit.pastryRound, ClimateCore.PACKAGE_ID, "dcs_food_tart", "food", 9);
		instance.regSimpleItem(FoodInit.pastrySquare, ClimateCore.PACKAGE_ID, "dcs_food_pie", "food", 11);
		instance.regSimpleItem(FoodInit.sandwich, ClimateCore.PACKAGE_ID, "dcs_food_sandwich", "food", 3);
		instance.regSimpleItem(FoodInit.clubsandwich, ClimateCore.PACKAGE_ID, "dcs_food_clubsand", "food", 1);
		instance.regSimpleItem(FoodInit.ricebowl, ClimateCore.PACKAGE_ID, "dcs_food_rice_bowl", "food", 4);
		instance.regSimpleItem(FoodInit.steakplate, ClimateCore.PACKAGE_ID, "dcs_food_empty_plate", "food", 0);
		instance.regSimpleItem(FoodInit.plateMeal, ClimateCore.PACKAGE_ID, "dcs_food_plate_meat", "food", 9);
		instance.regSimpleItem(FoodInit.plateSoup, ClimateCore.PACKAGE_ID, "dcs_food_plate_potato", "food", 3);
		instance.regSimpleItem(FoodInit.bowlSoup, ClimateCore.PACKAGE_ID, "dcs_food_bowl_stew", "food", 15);
		instance.regSimpleItem(FoodInit.salad, ClimateCore.PACKAGE_ID, "dcs_food_salad", "food", 7);
		instance.regSimpleItem(FoodInit.cake, ClimateCore.PACKAGE_ID, "dcs_food_cake", "food", 10);
		instance.regSimpleItem(FoodInit.icecream, ClimateCore.PACKAGE_ID, "dcs_food_icecream", "food", 4);
		instance.regSimpleItem(FoodInit.mochi, ClimateCore.PACKAGE_ID, "dcs_food_mochi", "food", 1);
		instance.regSimpleItem(FoodInit.wagashi, ClimateCore.PACKAGE_ID, "dcs_food_wagashi", "food", 7);
		instance.regSimpleItem(FoodInit.nonEntity, ClimateCore.PACKAGE_ID, "dcs_food_noentity", "food", 2);
		instance.regSimpleItem(FoodInit.snack, ClimateCore.PACKAGE_ID, "dcs_food_snack", "food", 5);
		instance.regSimpleItem(FoodInit.deepFry, ClimateCore.PACKAGE_ID, "dcs_food_frying", "food", 3);
		instance.regSimpleItem(FoodInit.setMeal, ClimateCore.PACKAGE_ID, "dcs_food_setmeal", "food", 1);

		instance.regSimpleItem(FoodInit.paperPack, ClimateCore.PACKAGE_ID, "dcs_food_pack", "food", 13);

		// block

		instance.regSimpleBlock(FoodInit.leavesLemon, ClimateCore.PACKAGE_ID, "dcs_leaves_lemon", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesOlive, ClimateCore.PACKAGE_ID, "dcs_leaves_olive", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesTea, ClimateCore.PACKAGE_ID, "dcs_leaves_tea", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesMorus, ClimateCore.PACKAGE_ID, "dcs_leaves_morus", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesWalnut, ClimateCore.PACKAGE_ID, "dcs_leaves_walnut", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesDates, ClimateCore.PACKAGE_ID, "dcs_leaves_dates", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesDatesCrop, ClimateCore.PACKAGE_ID, "dcs_leaves_datescrop", "crop", 3);
		instance.regSimpleBlock(FoodInit.dish, ClimateCore.PACKAGE_ID, "dcs_build_dish", "build", 1);

		instance.regTEBlock(FoodInit.potteryPot, ClimateCore.PACKAGE_ID, "dcs_device_pottery_pot", "machine", 0);
		instance.regTEBlock(FoodInit.steelPot, ClimateCore.PACKAGE_ID, "dcs_device_steel_pot", "machine", 0);
		instance.regTEBlock(FoodInit.teaPot, ClimateCore.PACKAGE_ID, "dcs_device_tea_pot", "machine", 0);
		instance.regSimpleBlock(FoodInit.silkwormBox, ClimateCore.PACKAGE_ID, "dcs_device_silkworm_box", "device", 0);

		ModelLoader.setCustomStateMapper(FoodInit.cropLotus, (new StateMap.Builder()).ignore(BlockFluidBase.LEVEL)
				.build());
		ModelBakery.registerItemVariants(Item.getItemFromBlock(FoodInit.cropLotus), new ModelResourceLocation(
				"dcs_climate:dcs_crop_lotus"));
		for (int i = 0; i < 16; i++) {
			ModelLoader.setCustomModelResourceLocation(Item
					.getItemFromBlock(FoodInit.cropLotus), i, new ModelResourceLocation(
							"dcs_climate:crop/dcs_crop_lotus", "inventory"));
		}

		ModelLoader.setCustomStateMapper(FoodInit.cropSeaweed, (new StateMap.Builder()).ignore(BlockFluidBase.LEVEL)
				.build());
		ModelBakery.registerItemVariants(Item.getItemFromBlock(FoodInit.cropSeaweed), new ModelResourceLocation(
				"dcs_climate:dcs_crop_seaweed"));

		for (int i = 0; i < 3; i++) {
			ModelLoader.setCustomModelResourceLocation(Item
					.getItemFromBlock(FoodInit.cropWisteria), i, new ModelResourceLocation(
							"dcs_climate:crop/dcs_crop_wisteria" + i, "inventory"));
		}

		// leaves color
		MinecraftForge.EVENT_BUS.register(new LeavesColorsDC());
	}

}
