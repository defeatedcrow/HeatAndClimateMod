package defeatedcrow.hac.food;

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
import defeatedcrow.hac.main.worldgen.DCRegistryUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FoodCommonProxy {

	public static void loadTE() {
		GameRegistry.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot");
		GameRegistry.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot");
		GameRegistry.registerTileEntity(TileTeaPot.class, "dcs_te_tea_pot");
	}

	public static void loadEntity() {
		DCRegistryUtil.addEntity(RoundBreadEntity.class, "food", "r_bread");
		DCRegistryUtil.addEntity(SquareBreadEntity.class, "food", "s_bread");

		DCRegistryUtil.addEntity(FishStickEntity.class, "food", "fish_stick");
		DCRegistryUtil.addEntity(YakitoriStickEntity.class, "food", "yakitori_stick");
		DCRegistryUtil.addEntity(PorkStickEntity.class, "food", "pork_stick");
		DCRegistryUtil.addEntity(BeefStickEntity.class, "food", "beef_stick");

		DCRegistryUtil.addEntity(EntityTeaCupSilver.class, "food", "cup_silver");
		DCRegistryUtil.addEntity(EntityTeaCupWhite.class, "food", "cup_white");
		DCRegistryUtil.addEntity(EntityTumbler.class, "food", "tumbler");

		DCRegistryUtil.addEntity(AppleTartEntity.class, "food", "tart_apple");
		DCRegistryUtil.addEntity(LemonTartEntity.class, "food", "tart_lemon");
		DCRegistryUtil.addEntity(SpinachQuicheEntity.class, "food", "quiche_spinach");
		DCRegistryUtil.addEntity(PotatoQuicheEntity.class, "food", "quiche_potato");
		DCRegistryUtil.addEntity(SugarPieEntity.class, "food", "pie_sugar");
		DCRegistryUtil.addEntity(MeatPieEntity.class, "food", "pie_meat");
		DCRegistryUtil.addEntity(ChocolatePieEntity.class, "food", "pie_chocolate");
		DCRegistryUtil.addEntity(FruitPieEntity.class, "food", "pie_fruit");
		DCRegistryUtil.addEntity(PizzaTomatoEntity.class, "food", "pizza_tomato");

		DCRegistryUtil.addEntity(ToastBreadEntity.class, "food", "toast");
		DCRegistryUtil.addEntity(EntitySandwich.class, "food", "sandwich_apple");
		DCRegistryUtil.addEntity(EggSandwichEntity.class, "food", "sandwich_egg");
		DCRegistryUtil.addEntity(LemonSandwichEntity.class, "food", "sandwich_lemon");
		DCRegistryUtil.addEntity(SaladSandwichEntity.class, "food", "sandwich_salad");
		DCRegistryUtil.addEntity(ClubSandwichSEntity.class, "food", "clubsand_square");
		DCRegistryUtil.addEntity(ClubSandwichREntity.class, "food", "clubsand_round");

		DCRegistryUtil.addEntity(EntityRiceBowl.class, "food", "rice_bowl");
		DCRegistryUtil.addEntity(EntityRiceMushroom.class, "food", "rice_mushroom");

		DCRegistryUtil.addEntity(EmptyPlateEntity.class, "food", "empty_steakplate");
		DCRegistryUtil.addEntity(BeefPlateEntity.class, "food", "plate_beef");
		DCRegistryUtil.addEntity(PorkPlateEntity.class, "food", "plate_pork");
		DCRegistryUtil.addEntity(ChickenPlateEntity.class, "food", "plate_chicken");
		DCRegistryUtil.addEntity(FishPlateEntity.class, "food", "plate_fish");
		DCRegistryUtil.addEntity(PotatoPlateEntity.class, "food", "plate_potato");
		DCRegistryUtil.addEntity(SoupPlateEntity.class, "food", "soup_tomato");

		DCRegistryUtil.addEntity(StewVegiEntity.class, "food", "stew_vegi");
		DCRegistryUtil.addEntity(StewEggEntity.class, "food", "stew_egg");
		DCRegistryUtil.addEntity(StewTomatoEntity.class, "food", "stew_tomato");
		DCRegistryUtil.addEntity(StewCongeeEntity.class, "food", "stew_congee");
		DCRegistryUtil.addEntity(StewPumpukinEntity.class, "food", "stew_pumpkin");
		DCRegistryUtil.addEntity(StewBorschtEntity.class, "food", "stew_borscht");
		DCRegistryUtil.addEntity(StewMushroomEntity.class, "food", "stew_mushroom");
		DCRegistryUtil.addEntity(StewPurpleEntity.class, "food", "stew_purple");
		DCRegistryUtil.addEntity(MuttonStickEntity.class, "food", "mutton_stick");
		DCRegistryUtil.addEntity(SquidStickEntity.class, "food", "squid_stick");
		DCRegistryUtil.addEntity(MooncakeEntity.class, "food", "moon_cake");
		DCRegistryUtil.addEntity(StewLotusrootEntity.class, "food", "stew_lotusroot");
		DCRegistryUtil.addEntity(StewSquidEntity.class, "food", "stew_squid");

		DCRegistryUtil.addEntity(SaladGreenEntity.class, "food", "salad_green");
		DCRegistryUtil.addEntity(SaladLotusrootEntity.class, "food", "salad_lotusroot");
		DCRegistryUtil.addEntity(SaladPotatoEntity.class, "food", "salad_potato");
		DCRegistryUtil.addEntity(CakeButterEntity.class, "food", "cake_butter");
		DCRegistryUtil.addEntity(CakeChocolateEntity.class, "food", "cake_chocolate");
		DCRegistryUtil.addEntity(CakeCoffeeEntity.class, "food", "cake_coffee");
		DCRegistryUtil.addEntity(CakeLemonEntity.class, "food", "cake_lemon");
	}

}
