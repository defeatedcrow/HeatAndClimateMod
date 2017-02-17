package defeatedcrow.hac.food;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonBakery;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.block.crop.BlockCoffee;
import defeatedcrow.hac.food.block.crop.BlockCotton;
import defeatedcrow.hac.food.block.crop.BlockLeavesLemon;
import defeatedcrow.hac.food.block.crop.BlockLeavesOlive;
import defeatedcrow.hac.food.block.crop.BlockOnion;
import defeatedcrow.hac.food.block.crop.BlockRice;
import defeatedcrow.hac.food.block.crop.BlockSaplingDC;
import defeatedcrow.hac.food.block.crop.BlockSpinach;
import defeatedcrow.hac.food.block.crop.BlockTomato;
import defeatedcrow.hac.food.client.AppleTartRenderer;
import defeatedcrow.hac.food.client.BeefStickRenderer;
import defeatedcrow.hac.food.client.ChocolatePieRenderer;
import defeatedcrow.hac.food.client.CupSilverRenderer;
import defeatedcrow.hac.food.client.CupWhiteRenderer;
import defeatedcrow.hac.food.client.EmptyPlateRenderer;
import defeatedcrow.hac.food.client.FishStickRenderer;
import defeatedcrow.hac.food.client.FruitPieRenderer;
import defeatedcrow.hac.food.client.LemonTartRenderer;
import defeatedcrow.hac.food.client.MeatPieRenderer;
import defeatedcrow.hac.food.client.PlateBeefRenderer;
import defeatedcrow.hac.food.client.PlateChickenRenderer;
import defeatedcrow.hac.food.client.PlateFishRenderer;
import defeatedcrow.hac.food.client.PlatePorkRenderer;
import defeatedcrow.hac.food.client.PlatePotatoRenderer;
import defeatedcrow.hac.food.client.PlateTomatoRenderer;
import defeatedcrow.hac.food.client.PorkStickRenderer;
import defeatedcrow.hac.food.client.PotatoQuicheRenderer;
import defeatedcrow.hac.food.client.RiceBowlRenderer;
import defeatedcrow.hac.food.client.RoundBreadRenderer;
import defeatedcrow.hac.food.client.SandwichAppleRenderer;
import defeatedcrow.hac.food.client.SandwichEggRenderer;
import defeatedcrow.hac.food.client.SandwichSaladRenderer;
import defeatedcrow.hac.food.client.SpinachQuicheRenderer;
import defeatedcrow.hac.food.client.SquareBreadRenderer;
import defeatedcrow.hac.food.client.SugarPieRenderer;
import defeatedcrow.hac.food.client.TESRPotteryPot;
import defeatedcrow.hac.food.client.TESRSteelPot;
import defeatedcrow.hac.food.client.TESRTeaPot;
import defeatedcrow.hac.food.client.ToastRenderer;
import defeatedcrow.hac.food.client.TumblerRenderer;
import defeatedcrow.hac.food.client.YakitoriStickRenderer;
import defeatedcrow.hac.food.entity.AppleTartEntity;
import defeatedcrow.hac.food.entity.BeefPlateEntity;
import defeatedcrow.hac.food.entity.BeefStickEntity;
import defeatedcrow.hac.food.entity.ChickenPlateEntity;
import defeatedcrow.hac.food.entity.ChocolatePieEntity;
import defeatedcrow.hac.food.entity.EggSandwichEntity;
import defeatedcrow.hac.food.entity.EmptyPlateEntity;
import defeatedcrow.hac.food.entity.EntityRiceBowl;
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
import defeatedcrow.hac.food.entity.PorkPlateEntity;
import defeatedcrow.hac.food.entity.PorkStickEntity;
import defeatedcrow.hac.food.entity.PotatoPlateEntity;
import defeatedcrow.hac.food.entity.PotatoQuicheEntity;
import defeatedcrow.hac.food.entity.RoundBreadEntity;
import defeatedcrow.hac.food.entity.SaladSandwichEntity;
import defeatedcrow.hac.food.entity.SoupPlateEntity;
import defeatedcrow.hac.food.entity.SpinachQuicheEntity;
import defeatedcrow.hac.food.entity.SquareBreadEntity;
import defeatedcrow.hac.food.entity.SugarPieEntity;
import defeatedcrow.hac.food.entity.ToastBreadEntity;
import defeatedcrow.hac.food.entity.YakitoriStickEntity;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.ClientMainProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FoodClientProxy {

	public static void loadConst() {
		JsonBakery.instance.addTex(BlockRice.getTexList());
		JsonBakery.instance.addTex(BlockOnion.getTexList());
		JsonBakery.instance.addTex(BlockSpinach.getTexList());
		JsonBakery.instance.addTex(BlockTomato.getTexList());
		JsonBakery.instance.addTex(BlockCotton.getTexList());
		JsonBakery.instance.addTex(BlockCoffee.getTexList());
		JsonBakery.instance.addTex(BlockLeavesLemon.getTexList());
		JsonBakery.instance.addTex(BlockLeavesOlive.getTexList());
		JsonBakery.instance.addTex(BlockSaplingDC.getTexList());
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
	}

	public static void loadTE() {
		ClientRegistry.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot", new TESRPotteryPot());
		ClientRegistry.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot", new TESRSteelPot());
		ClientRegistry.registerTileEntity(TileTeaPot.class, "dcs_te_tea_pot", new TESRTeaPot());
	}

	public static void regJson(JsonRegisterHelper instance) {
		// item

		instance.regSimpleItem(FoodInit.teaLeaves, ClimateCore.PACKAGE_ID, "dcs_food_leaves", "food", 2);
		instance.regSimpleItem(FoodInit.dropOil, ClimateCore.PACKAGE_ID, "dcs_food_drop_oil", "food", 0);
		instance.regSimpleItem(FoodInit.dropCream, ClimateCore.PACKAGE_ID, "dcs_food_drop_cream", "food", 0);
		instance.regSimpleItem(FoodInit.bread, ClimateCore.PACKAGE_ID, "dcs_round_bread", "food", 5);
		instance.regSimpleItem(FoodInit.sticks, ClimateCore.PACKAGE_ID, "dcs_stick_foods", "food", 7);
		instance.regSimpleItem(FoodInit.crops, ClimateCore.PACKAGE_ID, "dcs_crops", "food", 9);
		instance.regSimpleItem(FoodInit.seeds, ClimateCore.PACKAGE_ID, "dcs_seeds", "food", 5);
		instance.regSimpleItem(FoodInit.cupSilver, ClimateCore.PACKAGE_ID, "dcs_food_teacup", "food", 2);
		instance.regSimpleItem(FoodInit.dairy, ClimateCore.PACKAGE_ID, "dcs_food_dairy", "food", 2);
		instance.regSimpleItem(FoodInit.meat, ClimateCore.PACKAGE_ID, "dcs_food_meat", "food", 1);
		instance.regSimpleItem(FoodInit.pastry, ClimateCore.PACKAGE_ID, "dcs_food_pastry", "food", 0);
		instance.regSimpleItem(FoodInit.pastryRound, ClimateCore.PACKAGE_ID, "dcs_food_tart", "food", 7);
		instance.regSimpleItem(FoodInit.pastrySquare, ClimateCore.PACKAGE_ID, "dcs_food_pie", "food", 7);
		instance.regSimpleItem(FoodInit.sandwich, ClimateCore.PACKAGE_ID, "dcs_food_sandwich", "food", 3);
		instance.regSimpleItem(FoodInit.ricebowl, ClimateCore.PACKAGE_ID, "dcs_food_rice_bowl", "food", 0);
		instance.regSimpleItem(FoodInit.steakplate, ClimateCore.PACKAGE_ID, "dcs_food_empty_plate", "food", 0);
		instance.regSimpleItem(FoodInit.plateMeal, ClimateCore.PACKAGE_ID, "dcs_food_plate_meat", "food", 7);
		instance.regSimpleItem(FoodInit.plateSoup, ClimateCore.PACKAGE_ID, "dcs_food_plate_potato", "food", 3);

		instance.regSimpleItem(FoodInit.paperPack, ClimateCore.PACKAGE_ID, "dcs_food_pack", "food", 4);

		// block

		instance.regSimpleBlock(FoodInit.leavesLemon, ClimateCore.PACKAGE_ID, "dcs_leaves_lemon", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesOlive, ClimateCore.PACKAGE_ID, "dcs_leaves_olive", "crop", 3);
		instance.regSimpleBlock(FoodInit.leavesTea, ClimateCore.PACKAGE_ID, "dcs_leaves_tea", "crop", 3);
		instance.regSimpleBlock(FoodInit.dish, ClimateCore.PACKAGE_ID, "dcs_build_dish", "build", 1);

		instance.regTEBlock(FoodInit.potteryPot, ClimateCore.PACKAGE_ID, "dcs_device_pottery_pot", "machine", 0);
		instance.regTEBlock(FoodInit.steelPot, ClimateCore.PACKAGE_ID, "dcs_device_steel_pot", "machine", 0);
		instance.regTEBlock(FoodInit.teaPot, ClimateCore.PACKAGE_ID, "dcs_device_tea_pot", "machine", 0);

		// fluid

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(FoodInit.oilBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel_Oil = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oil", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel_Oil;
			}
		});
		ModelLoader.setCustomStateMapper(FoodInit.oilBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel_Oil = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oil", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel_Oil;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(FoodInit.greenTeaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_greentea", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(FoodInit.greenTeaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_greentea", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(FoodInit.blackTeaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(FoodInit.blackTeaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(FoodInit.coffeeBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_coffee", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(FoodInit.coffeeBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_coffee", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(FoodInit.creamBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_cream", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(FoodInit.creamBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_cream", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(FoodInit.tomatoBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(FoodInit.tomatoBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
	}

}
