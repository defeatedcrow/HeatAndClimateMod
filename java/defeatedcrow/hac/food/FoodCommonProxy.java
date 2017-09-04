package defeatedcrow.hac.food;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.entity.AppleTartEntity;
import defeatedcrow.hac.food.entity.BeefPlateEntity;
import defeatedcrow.hac.food.entity.BeefStickEntity;
import defeatedcrow.hac.food.entity.CakeButterEntity;
import defeatedcrow.hac.food.entity.CakeChocolateEntity;
import defeatedcrow.hac.food.entity.CakeCoffeeEntity;
import defeatedcrow.hac.food.entity.CakeLemonEntity;
import defeatedcrow.hac.food.entity.ChickenPlateEntity;
import defeatedcrow.hac.food.entity.ChocolatePieEntity;
import defeatedcrow.hac.food.entity.ClubSandwichREntity;
import defeatedcrow.hac.food.entity.ClubSandwichSEntity;
import defeatedcrow.hac.food.entity.EggSandwichEntity;
import defeatedcrow.hac.food.entity.EmptyPlateEntity;
import defeatedcrow.hac.food.entity.EntityRiceBowl;
import defeatedcrow.hac.food.entity.EntityRiceMushroom;
import defeatedcrow.hac.food.entity.EntitySandwich;
import defeatedcrow.hac.food.entity.EntityTeaCupSilver;
import defeatedcrow.hac.food.entity.EntityTeaCupWhite;
import defeatedcrow.hac.food.entity.EntityTumbler;
import defeatedcrow.hac.food.entity.FishPlateEntity;
import defeatedcrow.hac.food.entity.FishStickEntity;
import defeatedcrow.hac.food.entity.FruitPieEntity;
import defeatedcrow.hac.food.entity.LemonSandwichEntity;
import defeatedcrow.hac.food.entity.LemonTartEntity;
import defeatedcrow.hac.food.entity.MeatPieEntity;
import defeatedcrow.hac.food.entity.MooncakeEntity;
import defeatedcrow.hac.food.entity.MuttonStickEntity;
import defeatedcrow.hac.food.entity.PizzaTomatoEntity;
import defeatedcrow.hac.food.entity.PorkPlateEntity;
import defeatedcrow.hac.food.entity.PorkStickEntity;
import defeatedcrow.hac.food.entity.PotatoPlateEntity;
import defeatedcrow.hac.food.entity.PotatoQuicheEntity;
import defeatedcrow.hac.food.entity.RoundBreadEntity;
import defeatedcrow.hac.food.entity.SaladGreenEntity;
import defeatedcrow.hac.food.entity.SaladLotusrootEntity;
import defeatedcrow.hac.food.entity.SaladPotatoEntity;
import defeatedcrow.hac.food.entity.SaladSandwichEntity;
import defeatedcrow.hac.food.entity.SoupPlateEntity;
import defeatedcrow.hac.food.entity.SpinachQuicheEntity;
import defeatedcrow.hac.food.entity.SquareBreadEntity;
import defeatedcrow.hac.food.entity.SquidStickEntity;
import defeatedcrow.hac.food.entity.StewBorschtEntity;
import defeatedcrow.hac.food.entity.StewCongeeEntity;
import defeatedcrow.hac.food.entity.StewEggEntity;
import defeatedcrow.hac.food.entity.StewLotusrootEntity;
import defeatedcrow.hac.food.entity.StewMushroomEntity;
import defeatedcrow.hac.food.entity.StewPumpukinEntity;
import defeatedcrow.hac.food.entity.StewPurpleEntity;
import defeatedcrow.hac.food.entity.StewSquidEntity;
import defeatedcrow.hac.food.entity.StewTomatoEntity;
import defeatedcrow.hac.food.entity.StewVegiEntity;
import defeatedcrow.hac.food.entity.SugarPieEntity;
import defeatedcrow.hac.food.entity.ToastBreadEntity;
import defeatedcrow.hac.food.entity.YakitoriStickEntity;
import defeatedcrow.hac.main.ClimateMain;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoodCommonProxy {

	public static void loadTE() {
		GameRegistry.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot");
		GameRegistry.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot");
		GameRegistry.registerTileEntity(TileTeaPot.class, "dcs_te_tea_pot");
	}

	public static void loadEntity() {
		EntityRegistry.registerModEntity(RoundBreadEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.r_bread", 1,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SquareBreadEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.s_bread", 2,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(FishStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.fish_stick", 3,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(YakitoriStickEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.yakitori_stick", 4, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(PorkStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.pork_stick", 5,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(BeefStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.beef_stick", 6,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(EntityTeaCupSilver.class, ClimateCore.PACKAGE_BASE + "entity.food.cup_silver",
				7, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityTeaCupWhite.class, ClimateCore.PACKAGE_BASE + "entity.food.cup_white", 8,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(AppleTartEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.tart_apple", 9,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(LemonTartEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.tart_lemon", 10,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SpinachQuicheEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.quiche_spinach", 11, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(PotatoQuicheEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.quiche_potato", 12, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SugarPieEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.pie_sugar", 13,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(MeatPieEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.pie_meat", 14,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(ToastBreadEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.toast", 15,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntitySandwich.class, ClimateCore.PACKAGE_BASE + "entity.food.sandwich_apple",
				16, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EggSandwichEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.sandwich_egg",
				17, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(LemonSandwichEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.sandwich_lemon", 18, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SaladSandwichEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.sandwich_salad", 19, ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(EntityRiceBowl.class, ClimateCore.PACKAGE_BASE + "entity.food.rice_bowl", 20,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(EmptyPlateEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.empty_steakplate", 21, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(BeefPlateEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.plate_beef", 22,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(PorkPlateEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.plate_pork", 23,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(ChickenPlateEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.plate_chicken", 24, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(FishPlateEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.plate_fish", 25,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(PotatoPlateEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.plate_potato",
				26, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SoupPlateEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.soup_tomato",
				27, ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(ChocolatePieEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.pie_chocolate", 28, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(FruitPieEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.pie_fruit", 29,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(EntityTumbler.class, ClimateCore.PACKAGE_BASE + "entity.food.tumbler", 30,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(EntityRiceMushroom.class,
				ClimateCore.PACKAGE_BASE + "entity.food.rice_mushroom", 31, ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(ClubSandwichSEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.clubsand_square", 32, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(ClubSandwichREntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.clubsand_round", 33, ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(StewVegiEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.stew_vegi", 34,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewEggEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.stew_egg", 35,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewTomatoEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.stew_tomato",
				36, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewCongeeEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.stew_congee",
				37, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewPumpukinEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.stew_pumpkin", 38, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewBorschtEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.stew_borscht",
				39, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewMushroomEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.stew_mushroom", 40, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewPurpleEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.stew_purple",
				41, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(MuttonStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.mutton_stick",
				42, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SquidStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.squid_stick",
				43, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(MooncakeEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.moon_cake", 44,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewLotusrootEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.stew_lotusroot", 45, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(StewSquidEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.stew_squid", 46,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(PizzaTomatoEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.pizza_tomato",
				47, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SaladGreenEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.salad_green",
				48, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SaladLotusrootEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.salad_lotusroot", 49, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SaladPotatoEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.salad_potato",
				50, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(CakeButterEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.cake_butter",
				51, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(CakeChocolateEntity.class,
				ClimateCore.PACKAGE_BASE + "entity.food.cake_chocolate", 52, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(CakeCoffeeEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.cake_coffee",
				53, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(CakeLemonEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.cake_lemon", 54,
				ClimateMain.instance, 128, 5, true);
	}

}
