package defeatedcrow.hac.food;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCMaterialReg;
import defeatedcrow.hac.core.event.DispenseEntityItem;
import defeatedcrow.hac.food.block.BlockDish;
import defeatedcrow.hac.food.block.BlockPotteryPot;
import defeatedcrow.hac.food.block.BlockSilkwormBox;
import defeatedcrow.hac.food.block.BlockSteelPot;
import defeatedcrow.hac.food.block.BlockTeaPot;
import defeatedcrow.hac.food.block.crop.BlockCoffee;
import defeatedcrow.hac.food.block.crop.BlockCotton;
import defeatedcrow.hac.food.block.crop.BlockHerb;
import defeatedcrow.hac.food.block.crop.BlockLeavesLemon;
import defeatedcrow.hac.food.block.crop.BlockLeavesMorus;
import defeatedcrow.hac.food.block.crop.BlockLeavesOlive;
import defeatedcrow.hac.food.block.crop.BlockLeavesTea;
import defeatedcrow.hac.food.block.crop.BlockLotus;
import defeatedcrow.hac.food.block.crop.BlockOnion;
import defeatedcrow.hac.food.block.crop.BlockRice;
import defeatedcrow.hac.food.block.crop.BlockSaplingDC;
import defeatedcrow.hac.food.block.crop.BlockSeaweed;
import defeatedcrow.hac.food.block.crop.BlockSoy;
import defeatedcrow.hac.food.block.crop.BlockSpinach;
import defeatedcrow.hac.food.block.crop.BlockTomato;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.item.CakeItem;
import defeatedcrow.hac.food.item.ClubSandItem;
import defeatedcrow.hac.food.item.EmptyPlateItem;
import defeatedcrow.hac.food.item.IceCreamItem;
import defeatedcrow.hac.food.item.ItemDCCrops;
import defeatedcrow.hac.food.item.ItemDCSeeds;
import defeatedcrow.hac.food.item.ItemDairy;
import defeatedcrow.hac.food.item.ItemFluidDrop;
import defeatedcrow.hac.food.item.ItemFluidPack;
import defeatedcrow.hac.food.item.ItemLotusPetal;
import defeatedcrow.hac.food.item.ItemMeatMaterials;
import defeatedcrow.hac.food.item.ItemPastry;
import defeatedcrow.hac.food.item.ItemSilverCup;
import defeatedcrow.hac.food.item.ItemTeaLeaves;
import defeatedcrow.hac.food.item.MochiItem;
import defeatedcrow.hac.food.item.PlateMeatItem;
import defeatedcrow.hac.food.item.PlateSoupItem;
import defeatedcrow.hac.food.item.RiceBowlItem;
import defeatedcrow.hac.food.item.RoundBreadItem;
import defeatedcrow.hac.food.item.RoundPastryItem;
import defeatedcrow.hac.food.item.SaladItem;
import defeatedcrow.hac.food.item.SandwichItem;
import defeatedcrow.hac.food.item.SquarePastryItem;
import defeatedcrow.hac.food.item.StewBowlItem;
import defeatedcrow.hac.food.item.StickFoodsItem;
import defeatedcrow.hac.food.item.WagashiItem;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.block.fluid.DCFluidBlockBase;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FoodInitRegister {

	private FoodInitRegister() {}

	public static void load() {
		DrinkCapabilityHandler.register();
		if (ModuleConfig.food) {
			loadBlocks();
			loadItems();
			loadFluids();
			loadFoods();

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
		DCMaterialReg.registerBlock(FoodInit.cropSpinach, ClimateCore.PACKAGE_BASE + "_crop_spinach",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropSpinach, "crop_spinach", 3);

		FoodInit.cropTomato = new BlockTomato(ClimateCore.PACKAGE_BASE + "_crop_tomato");
		DCMaterialReg.registerBlock(FoodInit.cropTomato, ClimateCore.PACKAGE_BASE + "_crop_tomato", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropTomato, "crop_tomato", 15);

		FoodInit.cropCoffee = new BlockCoffee(ClimateCore.PACKAGE_BASE + "_crop_coffee");
		DCMaterialReg.registerBlock(FoodInit.cropCoffee, ClimateCore.PACKAGE_BASE + "_crop_coffee", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropCoffee, "crop_coffee", 15);

		FoodInit.cropCotton = new BlockCotton(ClimateCore.PACKAGE_BASE + "_crop_cotton");
		DCMaterialReg.registerBlock(FoodInit.cropCotton, ClimateCore.PACKAGE_BASE + "_crop_cotton", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropCotton, "crop_cotton", 15);

		FoodInit.leavesLemon = new BlockLeavesLemon(ClimateCore.PACKAGE_BASE + "_leaves_lemon");
		DCMaterialReg.registerBlock(FoodInit.leavesLemon, ClimateCore.PACKAGE_BASE + "_leaves_lemon",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(FoodInit.leavesLemon, "leaves_lemon", 3);

		FoodInit.leavesOlive = new BlockLeavesOlive(ClimateCore.PACKAGE_BASE + "_leaves_olive");
		DCMaterialReg.registerBlock(FoodInit.leavesOlive, ClimateCore.PACKAGE_BASE + "_leaves_olive",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(FoodInit.leavesOlive, "leaves_olive", 3);

		FoodInit.leavesMorus = new BlockLeavesMorus(ClimateCore.PACKAGE_BASE + "_leaves_morus");
		DCMaterialReg.registerBlock(FoodInit.leavesMorus, ClimateCore.PACKAGE_BASE + "_leaves_morus",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(FoodInit.leavesMorus, "leaves_morus", 3);

		FoodInit.leavesTea = new BlockLeavesTea(ClimateCore.PACKAGE_BASE + "_leaves_tea");
		DCMaterialReg.registerBlock(FoodInit.leavesTea, ClimateCore.PACKAGE_BASE + "_leaves_tea", ClimateMain.MOD_ID);

		FoodInit.saplings = new BlockSaplingDC(ClimateCore.PACKAGE_BASE + "_crop_sapling");
		DCMaterialReg.registerBlock(FoodInit.saplings, ClimateCore.PACKAGE_BASE + "_crop_sapling", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.saplings, "crop_sapling", 3);

		FoodInit.potteryPot = new BlockPotteryPot(ClimateCore.PACKAGE_BASE + "_device_pottery_pot");
		DCMaterialReg.registerBlock(FoodInit.potteryPot, ClimateCore.PACKAGE_BASE + "_device_pottery_pot",
				ClimateMain.MOD_ID);

		FoodInit.steelPot = new BlockSteelPot(ClimateCore.PACKAGE_BASE + "_device_steel_pot");
		DCMaterialReg.registerBlock(FoodInit.steelPot, ClimateCore.PACKAGE_BASE + "_device_steel_pot",
				ClimateMain.MOD_ID);

		FoodInit.teaPot = new BlockTeaPot(ClimateCore.PACKAGE_BASE + "_device_tea_pot");
		DCMaterialReg.registerBlock(FoodInit.teaPot, ClimateCore.PACKAGE_BASE + "_device_tea_pot", ClimateMain.MOD_ID);

		FoodInit.dish = new BlockDish(ClimateCore.PACKAGE_BASE + "_build_dish", 1);
		DCMaterialReg.registerBlock(FoodInit.dish, ClimateCore.PACKAGE_BASE + "_build_dish", ClimateMain.MOD_ID);

		FoodInit.silkwormBox = new BlockSilkwormBox(ClimateCore.PACKAGE_BASE + "_device_silkworm_box");
		DCMaterialReg.registerBlock(FoodInit.silkwormBox, ClimateCore.PACKAGE_BASE + "_device_silkworm_box",
				ClimateMain.MOD_ID);

		FoodInit.cropLotus = new BlockLotus(ClimateCore.PACKAGE_BASE + "_crop_lotus", 15).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_crop_lotus");
		DCMaterialReg.registerBlock(FoodInit.cropLotus, ClimateCore.PACKAGE_BASE + "_crop_lotus", ClimateMain.MOD_ID);

		FoodInit.cropHerb = new BlockHerb(ClimateCore.PACKAGE_BASE + "_crop_herb");
		DCMaterialReg.registerBlock(FoodInit.cropHerb, ClimateCore.PACKAGE_BASE + "_crop_herb", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropHerb, "crop_herb", 3);

		FoodInit.cropSeaweed = new BlockSeaweed(ClimateCore.PACKAGE_BASE + "_crop_seaweed", 3);
		DCMaterialReg.registerBlock(FoodInit.cropSeaweed, ClimateCore.PACKAGE_BASE + "_crop_seaweed",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropSeaweed, "crop_seaweed", 3);

		FoodInit.cropSoy = new BlockSoy(ClimateCore.PACKAGE_BASE + "_crop_soy");
		DCMaterialReg.registerBlock(FoodInit.cropSoy, ClimateCore.PACKAGE_BASE + "_crop_soy", ClimateMain.MOD_ID);
		ClimateMain.proxy.addCropBlock(FoodInit.cropSoy, "crop_soy", 15);
	}

	static void loadItems() {

		FoodInit.crops = new ItemDCCrops(12).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_crops");
		DCMaterialReg.registerItem(FoodInit.crops, ClimateCore.PACKAGE_BASE + "_food_crops", ClimateMain.MOD_ID);

		FoodInit.seeds = new ItemDCSeeds(9).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_seeds");
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
	}

	static void loadFoods() {
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
		DCMaterialReg.registerItem(FoodInit.clubsandwich, ClimateCore.PACKAGE_BASE + "_food_clubsand",
				ClimateMain.MOD_ID);

		FoodInit.ricebowl = new RiceBowlItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_rice");
		DCMaterialReg.registerItem(FoodInit.ricebowl, ClimateCore.PACKAGE_BASE + "_food_rice", ClimateMain.MOD_ID);

		FoodInit.mochi = new MochiItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_mochi");
		DCMaterialReg.registerItem(FoodInit.mochi, ClimateCore.PACKAGE_BASE + "_food_mochi", ClimateMain.MOD_ID);

		FoodInit.cupSilver = new ItemSilverCup(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_teacup");
		DCMaterialReg.registerItem(FoodInit.cupSilver, ClimateCore.PACKAGE_BASE + "_food_teacup", ClimateMain.MOD_ID);

		FoodInit.steakplate = new EmptyPlateItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE +
				"_food_steakplate_item");
		DCMaterialReg.registerItem(FoodInit.steakplate, ClimateCore.PACKAGE_BASE + "_food_steakplate_item",
				ClimateMain.MOD_ID);

		FoodInit.plateMeal = new PlateMeatItem(true).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_plate_meat");
		DCMaterialReg.registerItem(FoodInit.plateMeal, ClimateCore.PACKAGE_BASE + "_food_plate_meat",
				ClimateMain.MOD_ID);

		FoodInit.plateSoup = new PlateSoupItem(false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_plate_soup");
		DCMaterialReg.registerItem(FoodInit.plateSoup, ClimateCore.PACKAGE_BASE + "_food_plate_soup",
				ClimateMain.MOD_ID);

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
	}

	public static void loadFluids() {
		// fluid
		FoodInit.oil = new Fluid("dcs.seed_oil", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/seedoil_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/seedoil_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".seed_oil");
		FluidRegistry.registerFluid(FoodInit.oil);
		FoodInit.oilBlock = new DCFluidBlockBase(FoodInit.oil, "seedoil_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_oil");
		DCMaterialReg.registerBlock(FoodInit.oilBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_oil",
				ClimateMain.MOD_ID);
		FoodInit.oil.setBlock(FoodInit.oilBlock);

		FoodInit.greenTea = new Fluid("dcs.green_tea", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/greentea_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/greentea_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".green_tea").setTemperature(350);
		FluidRegistry.registerFluid(FoodInit.greenTea);
		FoodInit.greenTeaBlock = new DCFluidBlockBase(FoodInit.greenTea, "greentea_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_greentea");
		DCMaterialReg.registerBlock(FoodInit.greenTeaBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_greentea",
				ClimateMain.MOD_ID);
		FoodInit.greenTea.setBlock(FoodInit.greenTeaBlock);

		FoodInit.blackTea = new Fluid("dcs.black_tea", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/blacktea_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/blacktea_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".black_tea").setTemperature(350);
		FluidRegistry.registerFluid(FoodInit.blackTea);
		FoodInit.blackTeaBlock = new DCFluidBlockBase(FoodInit.blackTea, "blacktea_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea");
		DCMaterialReg.registerBlock(FoodInit.blackTeaBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea",
				ClimateMain.MOD_ID);
		FoodInit.blackTea.setBlock(FoodInit.blackTeaBlock);

		FoodInit.coffee = new Fluid("dcs.black_coffee", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/coffee_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/coffee_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".black_coffee").setTemperature(350);
		FluidRegistry.registerFluid(FoodInit.coffee);
		FoodInit.coffeeBlock = new DCFluidBlockBase(FoodInit.coffee, "coffee_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_coffee");
		DCMaterialReg.registerBlock(FoodInit.coffeeBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_coffee",
				ClimateMain.MOD_ID);
		FoodInit.coffee.setBlock(FoodInit.coffeeBlock);

		FoodInit.cream = new Fluid("dcs.milk_cream", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/cream_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/cream_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".milk_cream");
		FluidRegistry.registerFluid(FoodInit.cream);
		FoodInit.creamBlock = new DCFluidBlockBase(FoodInit.cream, "cream_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_cream");
		DCMaterialReg.registerBlock(FoodInit.creamBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_cream",
				ClimateMain.MOD_ID);
		FoodInit.cream.setBlock(FoodInit.creamBlock);

		FoodInit.tomatoJuice = new Fluid("dcs.vegetable_juice", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/vegetable_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/vegetable_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".vegetable_juice");
		FluidRegistry.registerFluid(FoodInit.tomatoJuice);
		FoodInit.tomatoBlock = new DCFluidBlockBase(FoodInit.tomatoJuice, "vegetable_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable");
		DCMaterialReg.registerBlock(FoodInit.tomatoBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable",
				ClimateMain.MOD_ID);
		FoodInit.tomatoJuice.setBlock(FoodInit.tomatoBlock);

		FoodInit.stock = new Fluid("dcs.stock", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/stock_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/stock_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".stock").setTemperature(350);
		FluidRegistry.registerFluid(FoodInit.stock);
		FoodInit.stockBlock = new DCFluidBlockBase(FoodInit.stock, "stock_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_stock");
		DCMaterialReg.registerBlock(FoodInit.stockBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_stock",
				ClimateMain.MOD_ID);
		FoodInit.stock.setBlock(FoodInit.stockBlock);

		FoodInit.lemon = new Fluid("dcs.lemonade", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/lemon_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/lemon_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".lemonade");
		FluidRegistry.registerFluid(FoodInit.lemon);
		FoodInit.lemonBlock = new DCFluidBlockBase(FoodInit.lemon, "lemon_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade");
		DCMaterialReg.registerBlock(FoodInit.lemonBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade",
				ClimateMain.MOD_ID);
		FoodInit.lemon.setBlock(FoodInit.lemonBlock);

		FoodInit.blackLiquor = new Fluid("dcs.black_liquor", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/black_liquor_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/black_liquor_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".black_liquor");
		FluidRegistry.registerFluid(FoodInit.blackLiquor);
		FoodInit.blackLiquorBlock = new DCFluidBlockBase(FoodInit.blackLiquor, "black_liquor_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor");
		DCMaterialReg.registerBlock(FoodInit.blackLiquorBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor",
				ClimateMain.MOD_ID);
		FoodInit.blackLiquor.setBlock(FoodInit.blackLiquorBlock);

		FoodInit.hotSpring = new Fluid("dcs.hotspring", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/hotspring_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/hotspring_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".hotspring").setTemperature(350);
		FluidRegistry.registerFluid(FoodInit.hotSpring);
		FoodInit.hotSpringBlock = new DCFluidBlockBase(FoodInit.hotSpring, "hotspring_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring");
		DCMaterialReg.registerBlock(FoodInit.hotSpringBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring",
				ClimateMain.MOD_ID);
		FoodInit.hotSpring.setBlock(FoodInit.hotSpringBlock);

		FoodInit.mazai = new Fluid("dcs.mazai", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/mazai_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/mazai_still")) {
			@Override
			public boolean doesVaporize(FluidStack fluid) {
				return false;
			}
		}.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".mazai").setLuminosity(15).setViscosity(1200).setDensity(
				1200).setTemperature(270);
		FluidRegistry.registerFluid(FoodInit.mazai);
		FoodInit.mazaiBlock = new DCFluidBlockBase(FoodInit.mazai, "mazai_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_mazai");
		DCMaterialReg.registerBlock(FoodInit.mazaiBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_mazai",
				ClimateMain.MOD_ID);
		FoodInit.mazai.setBlock(FoodInit.mazaiBlock);

		FoodInit.soyMilk = new Fluid("dcs.soy_milk", new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/soy_milk_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/soy_milk_still")).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + ".soy_milk");
		FluidRegistry.registerFluid(FoodInit.soyMilk);
		FoodInit.soyMilkBlock = new DCFluidBlockBase(FoodInit.soyMilk, "soy_milk_still").setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk");
		DCMaterialReg.registerBlock(FoodInit.soyMilkBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk",
				ClimateMain.MOD_ID);
		FoodInit.soyMilk.setBlock(FoodInit.soyMilkBlock);

		// bucket
		FluidRegistry.addBucketForFluid(FoodInit.oil);
		FluidRegistry.addBucketForFluid(FoodInit.greenTea);
		FluidRegistry.addBucketForFluid(FoodInit.blackTea);
		FluidRegistry.addBucketForFluid(FoodInit.coffee);
		FluidRegistry.addBucketForFluid(FoodInit.cream);
		FluidRegistry.addBucketForFluid(FoodInit.tomatoJuice);
		FluidRegistry.addBucketForFluid(FoodInit.blackLiquor);
		FluidRegistry.addBucketForFluid(FoodInit.hotSpring);
		FluidRegistry.addBucketForFluid(FoodInit.stock);
		FluidRegistry.addBucketForFluid(FoodInit.lemon);
		FluidRegistry.addBucketForFluid(FoodInit.mazai);
		FluidRegistry.addBucketForFluid(FoodInit.soyMilk);

		// fluid item
		FoodInit.dropOil = new ItemFluidDrop("olive", "dcs.seed_oil").setUnlocalizedName(ClimateCore.PACKAGE_BASE +
				"_food_drop_oil");
		DCMaterialReg.registerItem(FoodInit.dropOil, ClimateCore.PACKAGE_BASE + "_food_drop_oil", ClimateMain.MOD_ID);

		FoodInit.dropCream = new ItemFluidDrop("cream", "dcs.milk_cream").setUnlocalizedName(ClimateCore.PACKAGE_BASE +
				"_food_drop_cream");
		DCMaterialReg.registerItem(FoodInit.dropCream, ClimateCore.PACKAGE_BASE + "_food_drop_cream",
				ClimateMain.MOD_ID);

		FoodInit.paperPack = new ItemFluidPack().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_pack");
		DCMaterialReg.registerItem(FoodInit.paperPack, ClimateCore.PACKAGE_BASE + "_food_pack", ClimateMain.MOD_ID);

		// heat tier
		ClimateAPI.registerBlock.registerHeatBlock(FoodInit.coffeeBlock, 32767, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(FoodInit.coffeeBlock, 32767, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(FoodInit.blackTeaBlock, 32767, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(FoodInit.blackTeaBlock, 32767, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(FoodInit.greenTeaBlock, 32767, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(FoodInit.greenTeaBlock, 32767, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(FoodInit.hotSpringBlock, 32767, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(FoodInit.hotSpringBlock, 32767, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(FoodInit.stockBlock, 32767, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(FoodInit.stockBlock, 32767, DCHumidity.UNDERWATER);

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

		FoodInit.cupSilver.setCreativeTab(ClimateMain.food);
		FoodInit.steakplate.setCreativeTab(ClimateMain.food);

		FoodInit.potteryPot.setCreativeTab(ClimateMain.food);
		FoodInit.steelPot.setCreativeTab(ClimateMain.food);
		FoodInit.teaPot.setCreativeTab(ClimateMain.food);
		FoodInit.silkwormBox.setCreativeTab(ClimateMain.cloth);

		FoodInit.cropRice.setCreativeTab(ClimateMain.food);
		FoodInit.cropOnion.setCreativeTab(ClimateMain.food);
		FoodInit.cropSpinach.setCreativeTab(ClimateMain.food);
		FoodInit.cropTomato.setCreativeTab(ClimateMain.food);
		FoodInit.cropCoffee.setCreativeTab(ClimateMain.food);
		FoodInit.cropCotton.setCreativeTab(ClimateMain.food);
		FoodInit.cropLotus.setCreativeTab(ClimateMain.food);
		FoodInit.cropHerb.setCreativeTab(ClimateMain.food);
		FoodInit.cropSeaweed.setCreativeTab(ClimateMain.food);
		FoodInit.cropSoy.setCreativeTab(ClimateMain.food);

		FoodInit.leavesOlive.setCreativeTab(ClimateMain.food);
		FoodInit.leavesLemon.setCreativeTab(ClimateMain.food);
		FoodInit.leavesTea.setCreativeTab(ClimateMain.food);
		FoodInit.leavesMorus.setCreativeTab(ClimateMain.food);
		FoodInit.saplings.setCreativeTab(ClimateMain.food);

		FoodInit.dish.setCreativeTab(ClimateMain.build);
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
	}

}
