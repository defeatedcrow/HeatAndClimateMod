package defeatedcrow.hac.core.client.entity;

import defeatedcrow.hac.core.client.entity.model.BlockChandelier2Model;
import defeatedcrow.hac.core.client.entity.model.BlockChandelierModel;
import defeatedcrow.hac.core.client.entity.model.ChairBindModel;
import defeatedcrow.hac.core.client.entity.model.ModelMagicFin;
import defeatedcrow.hac.core.client.entity.model.ModelMagicWing;
import defeatedcrow.hac.core.client.entity.model.ModelThinArmor;
import defeatedcrow.hac.core.client.entity.renderer.RenderHarpoon;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererChandelier;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.tile.ChandelierTile;
import defeatedcrow.hac.core.material.item.tool.HarpoonItem;
import defeatedcrow.hac.food.client.entity.RenderBreadCream;
import defeatedcrow.hac.food.client.entity.RenderBreadSausage;
import defeatedcrow.hac.food.client.entity.RenderBreadSquare;
import defeatedcrow.hac.food.client.entity.RenderFoodBase;
import defeatedcrow.hac.food.client.entity.RenderPlateChicken;
import defeatedcrow.hac.food.client.entity.RenderPlateFish;
import defeatedcrow.hac.food.client.entity.RenderPlateGarlic;
import defeatedcrow.hac.food.client.entity.RenderPlateLegs;
import defeatedcrow.hac.food.client.entity.RenderPlateMeat;
import defeatedcrow.hac.food.client.entity.RenderPlateSteak;
import defeatedcrow.hac.food.client.entity.RenderSalad;
import defeatedcrow.hac.food.client.entity.RenderSandwich;
import defeatedcrow.hac.food.client.entity.RenderSoup;
import defeatedcrow.hac.food.client.entity.RenderStickBeef;
import defeatedcrow.hac.food.client.entity.RenderStickMeat;
import defeatedcrow.hac.food.client.model.BreadCreamModel;
import defeatedcrow.hac.food.client.model.BreadRoundModel;
import defeatedcrow.hac.food.client.model.BreadSausageModel;
import defeatedcrow.hac.food.client.model.BreadSquareModel;
import defeatedcrow.hac.food.client.model.PlateChickenModel;
import defeatedcrow.hac.food.client.model.PlateFishModel;
import defeatedcrow.hac.food.client.model.PlateGarlicModel;
import defeatedcrow.hac.food.client.model.PlateLegsModel;
import defeatedcrow.hac.food.client.model.PlateMeatModel;
import defeatedcrow.hac.food.client.model.PlateSteakModel;
import defeatedcrow.hac.food.client.model.SaladModel;
import defeatedcrow.hac.food.client.model.SandwichModel;
import defeatedcrow.hac.food.client.model.SoupModel;
import defeatedcrow.hac.food.client.model.StickBeefModel;
import defeatedcrow.hac.food.client.model.StickChickenModel;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.entity.BreadCreamItem;
import defeatedcrow.hac.food.material.entity.BreadRoundItem;
import defeatedcrow.hac.food.material.entity.BreadSausageItem;
import defeatedcrow.hac.food.material.entity.BreadSquareItem;
import defeatedcrow.hac.food.material.entity.PlateBeefItem;
import defeatedcrow.hac.food.material.entity.PlateChickenItem;
import defeatedcrow.hac.food.material.entity.PlateFishItem;
import defeatedcrow.hac.food.material.entity.PlateGarlicItem;
import defeatedcrow.hac.food.material.entity.PlateLegsItem;
import defeatedcrow.hac.food.material.entity.PlateMeatItem;
import defeatedcrow.hac.food.material.entity.PorridgeItem;
import defeatedcrow.hac.food.material.entity.SaladItem;
import defeatedcrow.hac.food.material.entity.SandwichItem;
import defeatedcrow.hac.food.material.entity.StickBeefItem;
import defeatedcrow.hac.food.material.entity.StickMeatItem;
import defeatedcrow.hac.magic.client.entity.RenderBindPlant;
import defeatedcrow.hac.magic.client.entity.RenderColorArrow;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class EntityClientRegister {
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		// TESR
		event.registerLayerDefinition(ChandelierTile.CHALCEDONY.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.FLUORITE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.JET.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.DESERTROSE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.SERPENTINE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.IRON.getLayerLocation(), BlockChandelier2Model::createBodyLayer);

		// Entity
		event.registerLayerDefinition(HarpoonItem.FLINT.getLayerLocation(), TridentModel::createLayer);
		event.registerLayerDefinition(HarpoonItem.STEEL.getLayerLocation(), TridentModel::createLayer);

		event.registerLayerDefinition(EntityModelLoader.INSTANCE.BOOTS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.45F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.LEGGINS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.35F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.SHIRT.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.45F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.JACKET.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.5F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.SUITS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.4F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.HAT.getLayerLocation(), () -> ModelThinArmor.createHatMesh());
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.LONG.getLayerLocation(), () -> ModelThinArmor.createLongMesh());
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.WING.getLayerLocation(), () -> ModelMagicWing.createWingMesh());
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.FIN.getLayerLocation(), () -> ModelMagicFin.createFinMesh());

		event.registerLayerDefinition(RenderBindPlant.PLANT.getLayerLocation(), ChairBindModel::createBodyLayer);

		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_NUTS_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_NUTS_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_CINNAMON_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_CINNAMON_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ANKO_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ANKO_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadSquareItem.BREAD_SQUARE_RAW.getLayerLocation(), BreadSquareModel::createBodyLayer);
		event.registerLayerDefinition(BreadSquareItem.BREAD_SQUARE_BAKED.getLayerLocation(), BreadSquareModel::createBodyLayer);
		event.registerLayerDefinition(BreadCreamItem.BREAD_CREAM_RAW.getLayerLocation(), BreadCreamModel::createBodyLayer);
		event.registerLayerDefinition(BreadCreamItem.BREAD_CREAM_BAKED.getLayerLocation(), BreadCreamModel::createBodyLayer);
		event.registerLayerDefinition(BreadSausageItem.BREAD_SAUSAGE_RAW.getLayerLocation(), BreadSausageModel::createBodyLayer);
		event.registerLayerDefinition(BreadSausageItem.BREAD_SAUSAGE_BAKED.getLayerLocation(), BreadSausageModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.FRUIT.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.MARMALADE.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.EGG.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.SALAD.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.SALMON.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.BEEF_RAW.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.BEEF_COOKED.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.PORK_RAW.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.PORK_COOKED.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.MUTTON_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.MUTTON_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.CHICKEN_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.CHICKEN_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.OFFAL_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.OFFAL_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(PlateBeefItem.BEEF_RAW.getLayerLocation(), PlateSteakModel::createBodyLayer);
		event.registerLayerDefinition(PlateBeefItem.BEEF_COOKED.getLayerLocation(), PlateSteakModel::createBodyLayer);
		event.registerLayerDefinition(PlateMeatItem.MEAT_RAW.getLayerLocation(), PlateMeatModel::createBodyLayer);
		event.registerLayerDefinition(PlateMeatItem.MEAT_COOKED.getLayerLocation(), PlateMeatModel::createBodyLayer);
		event.registerLayerDefinition(PlateLegsItem.LEGS_RAW.getLayerLocation(), PlateLegsModel::createBodyLayer);
		event.registerLayerDefinition(PlateLegsItem.LEGS_COOKED.getLayerLocation(), PlateLegsModel::createBodyLayer);
		event.registerLayerDefinition(PlateGarlicItem.GARLIC_RAW.getLayerLocation(), PlateGarlicModel::createBodyLayer);
		event.registerLayerDefinition(PlateGarlicItem.GARLIC_COOKED.getLayerLocation(), PlateGarlicModel::createBodyLayer);
		event.registerLayerDefinition(PlateChickenItem.CHICKEN_BIG_RAW.getLayerLocation(), PlateChickenModel::createBodyLayer);
		event.registerLayerDefinition(PlateChickenItem.CHICKEN_BIG_COOKED.getLayerLocation(), PlateChickenModel::createBodyLayer);
		event.registerLayerDefinition(PlateFishItem.FISH_RAW.getLayerLocation(), PlateFishModel::createBodyLayer);
		event.registerLayerDefinition(PlateFishItem.FISH_COOKED.getLayerLocation(), PlateFishModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.GREEN.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.POTATO.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.NUTS.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.MELON.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE.getLayerLocation(), SoupModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE.getOuterLocation(), SoupModel::createOuterLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_MILK.getLayerLocation(), SoupModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_MILK.getOuterLocation(), SoupModel::createOuterLayer);
		event.registerLayerDefinition(PorridgeItem.MUESLI.getLayerLocation(), SoupModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.MUESLI.getOuterLocation(), SoupModel::createOuterLayer);
	}

	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		// TESR
		event.registerBlockEntityRenderer(BuildInit.CHANDELIER_TILE.get(), TileRendererChandelier::new);

		// Entity
		event.registerEntityRenderer(CoreInit.HARPOON.get(), RenderHarpoon::new);

		event.registerEntityRenderer(FoodInit.BREAD_ROUND.get(), RenderFoodBase::new);
		event.registerEntityRenderer(FoodInit.BREAD_SQUARE.get(), RenderBreadSquare::new);
		event.registerEntityRenderer(FoodInit.BREAD_CREAM.get(), RenderBreadCream::new);
		event.registerEntityRenderer(FoodInit.BREAD_SAUSAGE.get(), RenderBreadSausage::new);
		event.registerEntityRenderer(FoodInit.SANDWICH.get(), RenderSandwich::new);
		event.registerEntityRenderer(FoodInit.STICK_BEEF.get(), RenderStickBeef::new);
		event.registerEntityRenderer(FoodInit.STICK_MEAT.get(), RenderStickMeat::new);
		event.registerEntityRenderer(FoodInit.PLATE_STEAK.get(), RenderPlateSteak::new);
		event.registerEntityRenderer(FoodInit.PLATE_MEAT.get(), RenderPlateMeat::new);
		event.registerEntityRenderer(FoodInit.PLATE_LEGS.get(), RenderPlateLegs::new);
		event.registerEntityRenderer(FoodInit.PLATE_BIG_STEAK.get(), RenderPlateGarlic::new);
		event.registerEntityRenderer(FoodInit.PLATE_CHICKEN.get(), RenderPlateChicken::new);
		event.registerEntityRenderer(FoodInit.PLATE_FISH.get(), RenderPlateFish::new);
		event.registerEntityRenderer(FoodInit.SALAD.get(), RenderSalad::new);
		event.registerEntityRenderer(FoodInit.SOUP.get(), RenderSoup::new);

		event.registerEntityRenderer(MagicInit.ARROW_WHITE_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BLUE_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BLACK_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_RED_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_GREEN_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_ROBBER_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BIND_ENTITY.get(), RenderColorArrow::new);

		event.registerEntityRenderer(MagicInit.BIND_PLANT_ENTITY.get(), RenderBindPlant::new);
	}

	public static void registerLayers(EntityRenderersEvent.AddLayers event) {

	}
}
