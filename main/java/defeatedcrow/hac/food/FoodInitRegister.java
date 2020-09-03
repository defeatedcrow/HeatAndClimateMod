package defeatedcrow.hac.food;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCMaterialReg;
import defeatedcrow.hac.core.event.DispenseEntityItem;
import defeatedcrow.hac.food.block.BlockDish;
import defeatedcrow.hac.food.block.BlockPotteryPot;
import defeatedcrow.hac.food.block.BlockSilkwormBox;
import defeatedcrow.hac.food.block.BlockSkillet;
import defeatedcrow.hac.food.block.BlockSteelPot;
import defeatedcrow.hac.food.block.BlockTeaPot;
import defeatedcrow.hac.food.block.crop.BlockBean;
import defeatedcrow.hac.food.block.crop.BlockChili;
import defeatedcrow.hac.food.block.crop.BlockCoffee;
import defeatedcrow.hac.food.block.crop.BlockCotton;
import defeatedcrow.hac.food.block.crop.BlockGarlic;
import defeatedcrow.hac.food.block.crop.BlockGinger;
import defeatedcrow.hac.food.block.crop.BlockGrape;
import defeatedcrow.hac.food.block.crop.BlockHerb;
import defeatedcrow.hac.food.block.crop.BlockLeavesDates;
import defeatedcrow.hac.food.block.crop.BlockLeavesDatesCrop;
import defeatedcrow.hac.food.block.crop.BlockLeavesLemon;
import defeatedcrow.hac.food.block.crop.BlockLeavesMorus;
import defeatedcrow.hac.food.block.crop.BlockLeavesOlive;
import defeatedcrow.hac.food.block.crop.BlockLeavesTea;
import defeatedcrow.hac.food.block.crop.BlockLeavesWalnut;
import defeatedcrow.hac.food.block.crop.BlockLettuce;
import defeatedcrow.hac.food.block.crop.BlockLotus;
import defeatedcrow.hac.food.block.crop.BlockLotusN;
import defeatedcrow.hac.food.block.crop.BlockOnion;
import defeatedcrow.hac.food.block.crop.BlockRice;
import defeatedcrow.hac.food.block.crop.BlockSaplingDC;
import defeatedcrow.hac.food.block.crop.BlockSaplingDC2;
import defeatedcrow.hac.food.block.crop.BlockSeaweed;
import defeatedcrow.hac.food.block.crop.BlockSoy;
import defeatedcrow.hac.food.block.crop.BlockSpinach;
import defeatedcrow.hac.food.block.crop.BlockTomato;
import defeatedcrow.hac.food.block.crop.BlockWisteria;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.item.CakeItem;
import defeatedcrow.hac.food.item.ClubSandItem;
import defeatedcrow.hac.food.item.DeepFryItem;
import defeatedcrow.hac.food.item.DipSauceItem;
import defeatedcrow.hac.food.item.DishBigItem;
import defeatedcrow.hac.food.item.DishItem;
import defeatedcrow.hac.food.item.DrinkItem;
import defeatedcrow.hac.food.item.EmptyPlateItem;
import defeatedcrow.hac.food.item.IceCreamItem;
import defeatedcrow.hac.food.item.ItemDCCrops;
import defeatedcrow.hac.food.item.ItemDCSeeds;
import defeatedcrow.hac.food.item.ItemDairy;
import defeatedcrow.hac.food.item.ItemFluidDrop;
import defeatedcrow.hac.food.item.ItemFluidPack;
import defeatedcrow.hac.food.item.ItemLotusPetal;
import defeatedcrow.hac.food.item.ItemMeatMaterials;
import defeatedcrow.hac.food.item.ItemNoEntityFoods;
import defeatedcrow.hac.food.item.ItemPastry;
import defeatedcrow.hac.food.item.ItemSilverCup;
import defeatedcrow.hac.food.item.ItemTeaLeaves;
import defeatedcrow.hac.food.item.MochiItem;
import defeatedcrow.hac.food.item.PastaItem;
import defeatedcrow.hac.food.item.PlateMeatItem;
import defeatedcrow.hac.food.item.PlateSoupItem;
import defeatedcrow.hac.food.item.RiceBowlItem;
import defeatedcrow.hac.food.item.RoundBreadItem;
import defeatedcrow.hac.food.item.RoundPastryItem;
import defeatedcrow.hac.food.item.SaladItem;
import defeatedcrow.hac.food.item.SandwichItem;
import defeatedcrow.hac.food.item.SetMealItem;
import defeatedcrow.hac.food.item.SnackItem;
import defeatedcrow.hac.food.item.SquarePastryItem;
import defeatedcrow.hac.food.item.StewBowlItem;
import defeatedcrow.hac.food.item.StickFoodsItem;
import defeatedcrow.hac.food.item.UdonItem;
import defeatedcrow.hac.food.item.WagashiItem;
import defeatedcrow.hac.food.item.brewing.ItemAntibiotic;
import defeatedcrow.hac.food.item.brewing.ItemMedium;
import defeatedcrow.hac.food.item.brewing.ItemResidue;
import defeatedcrow.hac.food.item.brewing.ItemUnidentified;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.config.ModuleConfig;

public class FoodInitRegister {

	private FoodInitRegister() {}

	public static void load() {
		DrinkCapabilityHandler.register();
		if (ModuleConfig.food) {
			loadBlocks();
			loadItems();
			loadFluids();
			loadFoods();

			if (ModuleConfig.food_advanced) {
				loadAdvanced();
			}

			loadCreativeTab();
			registerDispense();

		}
	}

	static void loadBlocks() {
		FoodInit.cropRice = new BlockRice(ClimateCore.PACKAGE_BASE + "_crop_rice");
		DCMaterialReg.registerBlock(FoodInit.cropRice, ClimateCore.PACKAGE_BASE + "_crop_rice", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropRice, "crop_rice", 3);

		FoodInit.cropOnion = new BlockOnion(ClimateCore.PACKAGE_BASE + "_crop_onion");
		DCMaterialReg.registerBlock(FoodInit.cropOnion, ClimateCore.PACKAGE_BASE + "_crop_onion", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropOnion, "crop_onion", 3);

		FoodInit.cropSpinach = new BlockSpinach(ClimateCore.PACKAGE_BASE + "_crop_spinach");
		DCMaterialReg
				.registerBlock(FoodInit.cropSpinach, ClimateCore.PACKAGE_BASE + "_crop_spinach", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropSpinach, "crop_spinach", 3);

		FoodInit.cropBean = new BlockBean(ClimateCore.PACKAGE_BASE + "_crop_bean");
		DCMaterialReg.registerBlock(FoodInit.cropBean, ClimateCore.PACKAGE_BASE + "_crop_bean", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropBean, "crop_bean", 3);

		FoodInit.cropChili = new BlockChili(ClimateCore.PACKAGE_BASE + "_crop_chili");
		DCMaterialReg.registerBlock(FoodInit.cropChili, ClimateCore.PACKAGE_BASE + "_crop_chili", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropChili, "crop_chili", 3);

		FoodInit.cropGarlic = new BlockGarlic(ClimateCore.PACKAGE_BASE + "_crop_garlic");
		DCMaterialReg.registerBlock(FoodInit.cropGarlic, ClimateCore.PACKAGE_BASE + "_crop_garlic", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropGarlic, "crop_garlic", 3);

		FoodInit.cropLettuce = new BlockLettuce(ClimateCore.PACKAGE_BASE + "_crop_lettuce");
		DCMaterialReg
				.registerBlock(FoodInit.cropLettuce, ClimateCore.PACKAGE_BASE + "_crop_lettuce", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropLettuce, "crop_lettuce", 3);

		FoodInit.cropTomato = new BlockTomato(ClimateCore.PACKAGE_BASE + "_crop_tomato");
		DCMaterialReg.registerBlock(FoodInit.cropTomato, ClimateCore.PACKAGE_BASE + "_crop_tomato", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropTomato, "crop_tomato", 15);

		FoodInit.cropCoffee = new BlockCoffee(ClimateCore.PACKAGE_BASE + "_crop_coffee");
		DCMaterialReg.registerBlock(FoodInit.cropCoffee, ClimateCore.PACKAGE_BASE + "_crop_coffee", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropCoffee, "crop_coffee", 15);

		FoodInit.cropCotton = new BlockCotton(ClimateCore.PACKAGE_BASE + "_crop_cotton");
		DCMaterialReg.registerBlock(FoodInit.cropCotton, ClimateCore.PACKAGE_BASE + "_crop_cotton", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropCotton, "crop_cotton", 15);

		FoodInit.cropLotus = new BlockLotus(ClimateCore.PACKAGE_BASE + "_crop_lotus", 15)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_crop_lotus");
		DCMaterialReg.registerBlock(FoodInit.cropLotus, ClimateCore.PACKAGE_BASE + "_crop_lotus", ClimateMain.MOD_ID);

		FoodInit.cropLotusN = new BlockLotusN(ClimateCore.PACKAGE_BASE + "_crop_lotus2", 15)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_crop_lotus2");
		DCMaterialReg.registerBlock(FoodInit.cropLotusN, ClimateCore.PACKAGE_BASE + "_crop_lotus2", ClimateMain.MOD_ID);

		FoodInit.cropHerb = new BlockHerb(ClimateCore.PACKAGE_BASE + "_crop_herb");
		DCMaterialReg.registerBlock(FoodInit.cropHerb, ClimateCore.PACKAGE_BASE + "_crop_herb", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropHerb, "crop_herb", 3);

		FoodInit.cropSeaweed = new BlockSeaweed(ClimateCore.PACKAGE_BASE + "_crop_seaweed", 3);
		DCMaterialReg
				.registerBlock(FoodInit.cropSeaweed, ClimateCore.PACKAGE_BASE + "_crop_seaweed", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropSeaweed, "crop_seaweed", 3);

		FoodInit.cropSoy = new BlockSoy(ClimateCore.PACKAGE_BASE + "_crop_soy");
		DCMaterialReg.registerBlock(FoodInit.cropSoy, ClimateCore.PACKAGE_BASE + "_crop_soy", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropSoy, "crop_soy", 15);

		FoodInit.cropWisteria = new BlockWisteria(ClimateCore.PACKAGE_BASE + "_crop_wisteria");
		DCMaterialReg
				.registerBlock(FoodInit.cropWisteria, ClimateCore.PACKAGE_BASE + "_crop_wisteria", ClimateMain.MOD_ID);

		FoodInit.cropGinger = new BlockGinger(ClimateCore.PACKAGE_BASE + "_crop_ginger");
		DCMaterialReg.registerBlock(FoodInit.cropGinger, ClimateCore.PACKAGE_BASE + "_crop_ginger", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropGinger, "crop_ginger", 3);

		FoodInit.cropGrape = new BlockGrape(ClimateCore.PACKAGE_BASE + "_crop_grape");
		DCMaterialReg.registerBlock(FoodInit.cropGrape, ClimateCore.PACKAGE_BASE + "_crop_grape", ClimateMain.MOD_ID);

		FoodInit.leavesLemon = new BlockLeavesLemon(ClimateCore.PACKAGE_BASE + "_leaves_lemon");
		DCMaterialReg
				.registerBlock(FoodInit.leavesLemon, ClimateCore.PACKAGE_BASE + "_leaves_lemon", ClimateMain.MOD_ID);

		FoodInit.leavesOlive = new BlockLeavesOlive(ClimateCore.PACKAGE_BASE + "_leaves_olive");
		DCMaterialReg
				.registerBlock(FoodInit.leavesOlive, ClimateCore.PACKAGE_BASE + "_leaves_olive", ClimateMain.MOD_ID);

		FoodInit.leavesMorus = new BlockLeavesMorus(ClimateCore.PACKAGE_BASE + "_leaves_morus");
		DCMaterialReg
				.registerBlock(FoodInit.leavesMorus, ClimateCore.PACKAGE_BASE + "_leaves_morus", ClimateMain.MOD_ID);

		FoodInit.leavesWalnut = new BlockLeavesWalnut(ClimateCore.PACKAGE_BASE + "_leaves_walnut");
		DCMaterialReg
				.registerBlock(FoodInit.leavesWalnut, ClimateCore.PACKAGE_BASE + "_leaves_walnut", ClimateMain.MOD_ID);

		FoodInit.leavesDates = new BlockLeavesDates(ClimateCore.PACKAGE_BASE + "_leaves_dates");
		DCMaterialReg
				.registerBlock(FoodInit.leavesDates, ClimateCore.PACKAGE_BASE + "_leaves_dates", ClimateMain.MOD_ID);

		FoodInit.leavesDatesCrop = new BlockLeavesDatesCrop(ClimateCore.PACKAGE_BASE + "_leaves_datescrop");
		DCMaterialReg
				.registerBlock(FoodInit.leavesDatesCrop, ClimateCore.PACKAGE_BASE + "_leaves_datescrop", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.leavesDatesCrop, "leaves_datescrop", 3);

		FoodInit.leavesTea = new BlockLeavesTea(ClimateCore.PACKAGE_BASE + "_leaves_tea");
		DCMaterialReg.registerBlock(FoodInit.leavesTea, ClimateCore.PACKAGE_BASE + "_leaves_tea", ClimateMain.MOD_ID);

		FoodInit.saplings = new BlockSaplingDC(ClimateCore.PACKAGE_BASE + "_crop_sapling");
		DCMaterialReg.registerBlock(FoodInit.saplings, ClimateCore.PACKAGE_BASE + "_crop_sapling", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.saplings, "crop_sapling", 3);

		FoodInit.saplings2 = new BlockSaplingDC2(ClimateCore.PACKAGE_BASE + "_crop_sapling2");
		DCMaterialReg
				.registerBlock(FoodInit.saplings2, ClimateCore.PACKAGE_BASE + "_crop_sapling2", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.saplings2, "crop_sapling2", 1);

		FoodInit.potteryPot = new BlockPotteryPot(ClimateCore.PACKAGE_BASE + "_device_pottery_pot");
		DCMaterialReg
				.registerBlock(FoodInit.potteryPot, ClimateCore.PACKAGE_BASE + "_device_pottery_pot", ClimateMain.MOD_ID);

		FoodInit.skillet = new BlockSkillet(ClimateCore.PACKAGE_BASE + "_device_skillet");
		DCMaterialReg.registerBlock(FoodInit.skillet, ClimateCore.PACKAGE_BASE + "_device_skillet", ClimateMain.MOD_ID);

		FoodInit.steelPot = new BlockSteelPot(ClimateCore.PACKAGE_BASE + "_device_steel_pot");
		DCMaterialReg
				.registerBlock(FoodInit.steelPot, ClimateCore.PACKAGE_BASE + "_device_steel_pot", ClimateMain.MOD_ID);

		FoodInit.teaPot = new BlockTeaPot(ClimateCore.PACKAGE_BASE + "_device_tea_pot");
		DCMaterialReg.registerBlock(FoodInit.teaPot, ClimateCore.PACKAGE_BASE + "_device_tea_pot", ClimateMain.MOD_ID);

		FoodInit.dish = new BlockDish(ClimateCore.PACKAGE_BASE + "_build_dish", 1);
		DCMaterialReg.registerBlock(FoodInit.dish, ClimateCore.PACKAGE_BASE + "_build_dish", ClimateMain.MOD_ID);

		FoodInit.silkwormBox = new BlockSilkwormBox(ClimateCore.PACKAGE_BASE + "_device_silkworm_box");
		DCMaterialReg
				.registerBlock(FoodInit.silkwormBox, ClimateCore.PACKAGE_BASE + "_device_silkworm_box", ClimateMain.MOD_ID);

	}

	static void loadItems() {

		FoodInit.crops = new ItemDCCrops(20).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_crops");
		DCMaterialReg.registerItem(FoodInit.crops, ClimateCore.PACKAGE_BASE + "_food_crops", ClimateMain.MOD_ID);

		FoodInit.seeds = new ItemDCSeeds(16).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_seeds");
		DCMaterialReg.registerItem(FoodInit.seeds, ClimateCore.PACKAGE_BASE + "_food_seeds", ClimateMain.MOD_ID);

		FoodInit.teaLeaves = new ItemTeaLeaves(2).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_leaves");
		DCMaterialReg.registerItem(FoodInit.teaLeaves, ClimateCore.PACKAGE_BASE + "_food_leaves", ClimateMain.MOD_ID);

		FoodInit.petals = new ItemLotusPetal().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_petals");
		DCMaterialReg.registerItem(FoodInit.petals, ClimateCore.PACKAGE_BASE + "_food_petals", ClimateMain.MOD_ID);

		FoodInit.dairy = new ItemDairy().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_dairy");
		DCMaterialReg.registerItem(FoodInit.dairy, ClimateCore.PACKAGE_BASE + "_food_dairy", ClimateMain.MOD_ID);

		FoodInit.meat = new ItemMeatMaterials().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_meat");
		DCMaterialReg.registerItem(FoodInit.meat, ClimateCore.PACKAGE_BASE + "_food_meat", ClimateMain.MOD_ID);

		FoodInit.pastry = new ItemPastry().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_pastry");
		DCMaterialReg.registerItem(FoodInit.pastry, ClimateCore.PACKAGE_BASE + "_food_pastry", ClimateMain.MOD_ID);

		FoodInit.residue = new ItemResidue().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_residue");
		DCMaterialReg.registerItem(FoodInit.residue, ClimateCore.PACKAGE_BASE + "_food_residue", ClimateMain.MOD_ID);
	}

	static void loadFoods() {
		FoodInit.nonEntity = new ItemNoEntityFoods().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_noentity");
		DCMaterialReg.registerItem(FoodInit.nonEntity, ClimateCore.PACKAGE_BASE + "_food_noentity", ClimateMain.MOD_ID);

		FoodInit.bread = new RoundBreadItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_bread");
		DCMaterialReg.registerItem(FoodInit.bread, ClimateCore.PACKAGE_BASE + "_food_bread", ClimateMain.MOD_ID);

		FoodInit.sticks = new StickFoodsItem(true).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_stick");
		DCMaterialReg.registerItem(FoodInit.sticks, ClimateCore.PACKAGE_BASE + "_food_stick", ClimateMain.MOD_ID);

		FoodInit.pastryRound = new RoundPastryItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_tart");
		DCMaterialReg.registerItem(FoodInit.pastryRound, ClimateCore.PACKAGE_BASE + "_food_tart", ClimateMain.MOD_ID);

		FoodInit.pastrySquare = new SquarePastryItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_pie");
		DCMaterialReg.registerItem(FoodInit.pastrySquare, ClimateCore.PACKAGE_BASE + "_food_pie", ClimateMain.MOD_ID);

		FoodInit.sandwich = new SandwichItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_sandwich");
		DCMaterialReg.registerItem(FoodInit.sandwich, ClimateCore.PACKAGE_BASE + "_food_sandwich", ClimateMain.MOD_ID);

		FoodInit.clubsandwich = new ClubSandItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_clubsand");
		DCMaterialReg
				.registerItem(FoodInit.clubsandwich, ClimateCore.PACKAGE_BASE + "_food_clubsand", ClimateMain.MOD_ID);

		FoodInit.ricebowl = new RiceBowlItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_rice");
		DCMaterialReg.registerItem(FoodInit.ricebowl, ClimateCore.PACKAGE_BASE + "_food_rice", ClimateMain.MOD_ID);

		FoodInit.mochi = new MochiItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_mochi");
		DCMaterialReg.registerItem(FoodInit.mochi, ClimateCore.PACKAGE_BASE + "_food_mochi", ClimateMain.MOD_ID);

		FoodInit.cupSilver = new ItemSilverCup(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_teacup");
		DCMaterialReg.registerItem(FoodInit.cupSilver, ClimateCore.PACKAGE_BASE + "_food_teacup", ClimateMain.MOD_ID);

		FoodInit.steakplate = new EmptyPlateItem(false)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_steakplate_item");
		DCMaterialReg
				.registerItem(FoodInit.steakplate, ClimateCore.PACKAGE_BASE + "_food_steakplate_item", ClimateMain.MOD_ID);

		FoodInit.plateMeal = new PlateMeatItem(true).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_plate_meat");
		DCMaterialReg
				.registerItem(FoodInit.plateMeal, ClimateCore.PACKAGE_BASE + "_food_plate_meat", ClimateMain.MOD_ID);

		FoodInit.plateSoup = new PlateSoupItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_plate_soup");
		DCMaterialReg
				.registerItem(FoodInit.plateSoup, ClimateCore.PACKAGE_BASE + "_food_plate_soup", ClimateMain.MOD_ID);

		FoodInit.bowlSoup = new StewBowlItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_bowl_stew");
		DCMaterialReg.registerItem(FoodInit.bowlSoup, ClimateCore.PACKAGE_BASE + "_food_bowl_stew", ClimateMain.MOD_ID);

		FoodInit.salad = new SaladItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_salad");
		DCMaterialReg.registerItem(FoodInit.salad, ClimateCore.PACKAGE_BASE + "_food_salad", ClimateMain.MOD_ID);

		FoodInit.cake = new CakeItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_cake");
		DCMaterialReg.registerItem(FoodInit.cake, ClimateCore.PACKAGE_BASE + "_food_cake", ClimateMain.MOD_ID);

		FoodInit.icecream = new IceCreamItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_icecream");
		DCMaterialReg.registerItem(FoodInit.icecream, ClimateCore.PACKAGE_BASE + "_food_icecream", ClimateMain.MOD_ID);

		FoodInit.wagashi = new WagashiItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_wagashi");
		DCMaterialReg.registerItem(FoodInit.wagashi, ClimateCore.PACKAGE_BASE + "_food_wagashi", ClimateMain.MOD_ID);

		FoodInit.snack = new SnackItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_snack");
		DCMaterialReg.registerItem(FoodInit.snack, ClimateCore.PACKAGE_BASE + "_food_snack", ClimateMain.MOD_ID);

		FoodInit.deepFry = new DeepFryItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_frying");
		DCMaterialReg.registerItem(FoodInit.deepFry, ClimateCore.PACKAGE_BASE + "_food_frying", ClimateMain.MOD_ID);

		FoodInit.dishSq = new DishItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_dish_sq");
		DCMaterialReg.registerItem(FoodInit.dishSq, ClimateCore.PACKAGE_BASE + "_food_dish_sq", ClimateMain.MOD_ID);

		FoodInit.dishBig = new DishBigItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_dish_big");
		DCMaterialReg.registerItem(FoodInit.dishBig, ClimateCore.PACKAGE_BASE + "_food_dish_big", ClimateMain.MOD_ID);

		FoodInit.udon = new UdonItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_udon");
		DCMaterialReg.registerItem(FoodInit.udon, ClimateCore.PACKAGE_BASE + "_food_udon", ClimateMain.MOD_ID);

		FoodInit.pasta = new PastaItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_pasta");
		DCMaterialReg.registerItem(FoodInit.pasta, ClimateCore.PACKAGE_BASE + "_food_pasta", ClimateMain.MOD_ID);

		FoodInit.setMeal = new SetMealItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_setmeal");
		DCMaterialReg.registerItem(FoodInit.setMeal, ClimateCore.PACKAGE_BASE + "_food_setmeal", ClimateMain.MOD_ID);

		FoodInit.drink = new DrinkItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_drink");
		DCMaterialReg.registerItem(FoodInit.drink, ClimateCore.PACKAGE_BASE + "_food_drink", ClimateMain.MOD_ID);

		FoodInit.dip = new DipSauceItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_dipsauce");
		DCMaterialReg.registerItem(FoodInit.dip, ClimateCore.PACKAGE_BASE + "_food_dipsauce", ClimateMain.MOD_ID);
	}

	public static void loadFluids() {

		// fluid item
		FoodInit.dropOil = new ItemFluidDrop("olive", "dcs.seed_oil")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_drop_oil");
		DCMaterialReg.registerItem(FoodInit.dropOil, ClimateCore.PACKAGE_BASE + "_food_drop_oil", ClimateMain.MOD_ID);

		FoodInit.dropCream = new ItemFluidDrop("cream", "dcs.milk_cream")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_drop_cream");
		DCMaterialReg
				.registerItem(FoodInit.dropCream, ClimateCore.PACKAGE_BASE + "_food_drop_cream", ClimateMain.MOD_ID);

		FoodInit.paperPack = new ItemFluidPack().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_pack");
		DCMaterialReg.registerItem(FoodInit.paperPack, ClimateCore.PACKAGE_BASE + "_food_pack", ClimateMain.MOD_ID);

	}

	public static void loadAdvanced() {

		FoodInit.medium = new ItemMedium().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_medium");
		DCMaterialReg.registerItem(FoodInit.medium, ClimateCore.PACKAGE_BASE + "_food_medium", ClimateMain.MOD_ID);

		FoodInit.antibiotic = new ItemAntibiotic().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_antibiotic");
		DCMaterialReg
				.registerItem(FoodInit.antibiotic, ClimateCore.PACKAGE_BASE + "_food_antibiotic", ClimateMain.MOD_ID);

		FoodInit.unidentified = new ItemUnidentified()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_unidentified");
		DCMaterialReg
				.registerItem(FoodInit.unidentified, ClimateCore.PACKAGE_BASE + "_food_unidentified", ClimateMain.MOD_ID);
	}

	static void loadCreativeTab() {
		FoodInit.bread.setCreativeTab(ClimateMain.food);
		FoodInit.sticks.setCreativeTab(ClimateMain.food);
		FoodInit.pastryRound.setCreativeTab(ClimateMain.food);
		FoodInit.pastrySquare.setCreativeTab(ClimateMain.food);
		FoodInit.sandwich.setCreativeTab(ClimateMain.food);
		FoodInit.clubsandwich.setCreativeTab(ClimateMain.food);

		FoodInit.ricebowl.setCreativeTab(ClimateMain.food);
		FoodInit.mochi.setCreativeTab(ClimateMain.food);

		FoodInit.plateMeal.setCreativeTab(ClimateMain.food);
		FoodInit.plateSoup.setCreativeTab(ClimateMain.food);
		FoodInit.bowlSoup.setCreativeTab(ClimateMain.food);
		FoodInit.salad.setCreativeTab(ClimateMain.food);
		FoodInit.cake.setCreativeTab(ClimateMain.food);
		FoodInit.icecream.setCreativeTab(ClimateMain.food);
		FoodInit.wagashi.setCreativeTab(ClimateMain.food);
		FoodInit.snack.setCreativeTab(ClimateMain.food);
		FoodInit.deepFry.setCreativeTab(ClimateMain.food);
		FoodInit.dishBig.setCreativeTab(ClimateMain.food);
		FoodInit.dishSq.setCreativeTab(ClimateMain.food);
		FoodInit.setMeal.setCreativeTab(ClimateMain.food);
		FoodInit.udon.setCreativeTab(ClimateMain.food);
		FoodInit.pasta.setCreativeTab(ClimateMain.food);
		FoodInit.drink.setCreativeTab(ClimateMain.food);
		FoodInit.dip.setCreativeTab(ClimateMain.food);

		FoodInit.nonEntity.setCreativeTab(ClimateMain.food);

		FoodInit.crops.setCreativeTab(ClimateMain.food);
		FoodInit.seeds.setCreativeTab(ClimateMain.food);
		FoodInit.teaLeaves.setCreativeTab(ClimateMain.food);
		FoodInit.petals.setCreativeTab(ClimateMain.food);

		FoodInit.dropOil.setCreativeTab(ClimateMain.food);
		FoodInit.dropCream.setCreativeTab(ClimateMain.food);
		FoodInit.paperPack.setCreativeTab(ClimateMain.food);

		FoodInit.meat.setCreativeTab(ClimateMain.food);
		FoodInit.dairy.setCreativeTab(ClimateMain.food);
		FoodInit.pastry.setCreativeTab(ClimateMain.food);
		FoodInit.residue.setCreativeTab(ClimateMain.food);

		FoodInit.cupSilver.setCreativeTab(ClimateMain.food);
		FoodInit.steakplate.setCreativeTab(ClimateMain.food);

		FoodInit.potteryPot.setCreativeTab(ClimateMain.food);
		FoodInit.skillet.setCreativeTab(ClimateMain.food);
		FoodInit.steelPot.setCreativeTab(ClimateMain.food);
		FoodInit.teaPot.setCreativeTab(ClimateMain.food);
		FoodInit.silkwormBox.setCreativeTab(ClimateMain.cloth);

		FoodInit.cropRice.setCreativeTab(ClimateMain.food);
		FoodInit.cropOnion.setCreativeTab(ClimateMain.food);
		FoodInit.cropSpinach.setCreativeTab(ClimateMain.food);
		FoodInit.cropTomato.setCreativeTab(ClimateMain.food);
		FoodInit.cropCoffee.setCreativeTab(ClimateMain.food);
		FoodInit.cropCotton.setCreativeTab(ClimateMain.food);
		FoodInit.cropLotusN.setCreativeTab(ClimateMain.food);
		FoodInit.cropHerb.setCreativeTab(ClimateMain.food);
		FoodInit.cropSeaweed.setCreativeTab(ClimateMain.food);
		FoodInit.cropSoy.setCreativeTab(ClimateMain.food);
		FoodInit.cropBean.setCreativeTab(ClimateMain.food);
		FoodInit.cropChili.setCreativeTab(ClimateMain.food);
		FoodInit.cropGarlic.setCreativeTab(ClimateMain.food);
		FoodInit.cropLettuce.setCreativeTab(ClimateMain.food);
		FoodInit.cropWisteria.setCreativeTab(ClimateMain.food);
		FoodInit.cropGinger.setCreativeTab(ClimateMain.food);
		FoodInit.cropGrape.setCreativeTab(ClimateMain.food);

		FoodInit.leavesOlive.setCreativeTab(ClimateMain.food);
		FoodInit.leavesLemon.setCreativeTab(ClimateMain.food);
		FoodInit.leavesTea.setCreativeTab(ClimateMain.food);
		FoodInit.leavesMorus.setCreativeTab(ClimateMain.food);
		FoodInit.leavesWalnut.setCreativeTab(ClimateMain.food);
		FoodInit.leavesDates.setCreativeTab(ClimateMain.food);
		FoodInit.leavesDatesCrop.setCreativeTab(ClimateMain.food);
		FoodInit.saplings.setCreativeTab(ClimateMain.food);
		FoodInit.saplings2.setCreativeTab(ClimateMain.food);

		FoodInit.dish.setCreativeTab(ClimateMain.build);

		if (ModuleConfig.food_advanced) {
			FoodInit.medium.setCreativeTab(ClimateMain.food);
			FoodInit.antibiotic.setCreativeTab(ClimateMain.food);
			FoodInit.unidentified.setCreativeTab(ClimateMain.food);
		}
	}

	static void registerDispense() {
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.bowlSoup);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.bread);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.cake);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.clubsandwich);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.cupSilver);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.pastrySquare);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.pastryRound);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.plateMeal);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.plateSoup);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.salad);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.ricebowl);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.sandwich);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.steakplate);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.sticks);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.deepFry);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.dishBig);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.dishSq);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.drink);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.icecream);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.mochi);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.setMeal);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.snack);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.udon);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.wagashi);
		DispenseEntityItem.getInstance().dispenceList.add(FoodInit.dip);
	}

}
