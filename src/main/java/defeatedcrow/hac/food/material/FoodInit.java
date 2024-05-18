package defeatedcrow.hac.food.material;

import java.util.function.Supplier;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Agri;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Cont;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Food;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.block.containers.CropContBlock;
import defeatedcrow.hac.food.material.block.containers.LeavesContBlock;
import defeatedcrow.hac.food.material.block.containers.LogContBlock;
import defeatedcrow.hac.food.material.block.crops.CropBlockAllium;
import defeatedcrow.hac.food.material.block.crops.CropBlockAmaranth;
import defeatedcrow.hac.food.material.block.crops.CropBlockApium;
import defeatedcrow.hac.food.material.block.crops.CropBlockAster;
import defeatedcrow.hac.food.material.block.crops.CropBlockBrassica;
import defeatedcrow.hac.food.material.block.crops.CropBlockCapsicum;
import defeatedcrow.hac.food.material.block.crops.CropBlockCereals;
import defeatedcrow.hac.food.material.block.crops.CropBlockGinger;
import defeatedcrow.hac.food.material.block.crops.CropBlockGourd;
import defeatedcrow.hac.food.material.block.crops.CropBlockGrape;
import defeatedcrow.hac.food.material.block.crops.CropBlockHerb;
import defeatedcrow.hac.food.material.block.crops.CropBlockIris;
import defeatedcrow.hac.food.material.block.crops.CropBlockKnotweed;
import defeatedcrow.hac.food.material.block.crops.CropBlockMallow;
import defeatedcrow.hac.food.material.block.crops.CropBlockMorningGlory;
import defeatedcrow.hac.food.material.block.crops.CropBlockOrchid;
import defeatedcrow.hac.food.material.block.crops.CropBlockOrchid_Epiphyte;
import defeatedcrow.hac.food.material.block.crops.CropBlockPalm;
import defeatedcrow.hac.food.material.block.crops.CropBlockPeas;
import defeatedcrow.hac.food.material.block.crops.CropBlockPedalia;
import defeatedcrow.hac.food.material.block.crops.CropBlockRanunculus;
import defeatedcrow.hac.food.material.block.crops.CropBlockRanunculus_Clematis;
import defeatedcrow.hac.food.material.block.crops.CropBlockRanunculus_Delphinium;
import defeatedcrow.hac.food.material.block.crops.CropBlockReed;
import defeatedcrow.hac.food.material.block.crops.CropBlockRice;
import defeatedcrow.hac.food.material.block.crops.CropBlockRice_Zizania;
import defeatedcrow.hac.food.material.block.crops.CropBlockSolanum;
import defeatedcrow.hac.food.material.block.crops.FertileBlock;
import defeatedcrow.hac.food.material.block.crops.LeavesAsh;
import defeatedcrow.hac.food.material.block.crops.LeavesAvocado;
import defeatedcrow.hac.food.material.block.crops.LeavesBeech;
import defeatedcrow.hac.food.material.block.crops.LeavesBlueberry;
import defeatedcrow.hac.food.material.block.crops.LeavesCamellia;
import defeatedcrow.hac.food.material.block.crops.LeavesCamphor;
import defeatedcrow.hac.food.material.block.crops.LeavesCashew;
import defeatedcrow.hac.food.material.block.crops.LeavesCherry;
import defeatedcrow.hac.food.material.block.crops.LeavesCinnamon;
import defeatedcrow.hac.food.material.block.crops.LeavesClove;
import defeatedcrow.hac.food.material.block.crops.LeavesDamaschena;
import defeatedcrow.hac.food.material.block.crops.LeavesEucalyptus;
import defeatedcrow.hac.food.material.block.crops.LeavesGuava;
import defeatedcrow.hac.food.material.block.crops.LeavesHeath;
import defeatedcrow.hac.food.material.block.crops.LeavesLacquer;
import defeatedcrow.hac.food.material.block.crops.LeavesLemon;
import defeatedcrow.hac.food.material.block.crops.LeavesMandarin;
import defeatedcrow.hac.food.material.block.crops.LeavesMango;
import defeatedcrow.hac.food.material.block.crops.LeavesMulberry;
import defeatedcrow.hac.food.material.block.crops.LeavesOlive;
import defeatedcrow.hac.food.material.block.crops.LeavesOsmanthus;
import defeatedcrow.hac.food.material.block.crops.LeavesPalm;
import defeatedcrow.hac.food.material.block.crops.LeavesPaper;
import defeatedcrow.hac.food.material.block.crops.LeavesPeach;
import defeatedcrow.hac.food.material.block.crops.LeavesPistachio;
import defeatedcrow.hac.food.material.block.crops.LeavesPlum;
import defeatedcrow.hac.food.material.block.crops.LeavesPomelo;
import defeatedcrow.hac.food.material.block.crops.LeavesRaspberry;
import defeatedcrow.hac.food.material.block.crops.LeavesRhododendron;
import defeatedcrow.hac.food.material.block.crops.LeavesRubber;
import defeatedcrow.hac.food.material.block.crops.LeavesRugosa;
import defeatedcrow.hac.food.material.block.crops.LeavesSchima;
import defeatedcrow.hac.food.material.block.crops.LeavesSichuanPepper;
import defeatedcrow.hac.food.material.block.crops.LeavesSweet;
import defeatedcrow.hac.food.material.block.crops.LeavesTea;
import defeatedcrow.hac.food.material.block.crops.LeavesWalnut;
import defeatedcrow.hac.food.material.block.crops.LogBlockDC;
import defeatedcrow.hac.food.material.block.crops.PlankBlockDC;
import defeatedcrow.hac.food.material.block.crops.SaplingBeech;
import defeatedcrow.hac.food.material.block.crops.SaplingCamellia;
import defeatedcrow.hac.food.material.block.crops.SaplingCherry;
import defeatedcrow.hac.food.material.block.crops.SaplingCinnamon;
import defeatedcrow.hac.food.material.block.crops.SaplingCitrus;
import defeatedcrow.hac.food.material.block.crops.SaplingErica;
import defeatedcrow.hac.food.material.block.crops.SaplingMorus;
import defeatedcrow.hac.food.material.block.crops.SaplingMyrtle;
import defeatedcrow.hac.food.material.block.crops.SaplingOlive;
import defeatedcrow.hac.food.material.block.crops.SaplingPalm;
import defeatedcrow.hac.food.material.block.crops.SaplingRose;
import defeatedcrow.hac.food.material.block.crops.SaplingSumac;
import defeatedcrow.hac.food.material.entity.BottleBeerItem;
import defeatedcrow.hac.food.material.entity.BottleWineItem;
import defeatedcrow.hac.food.material.entity.BreadCreamItem;
import defeatedcrow.hac.food.material.entity.BreadRoundItem;
import defeatedcrow.hac.food.material.entity.BreadSausageItem;
import defeatedcrow.hac.food.material.entity.BreadSquareItem;
import defeatedcrow.hac.food.material.entity.CakeItem;
import defeatedcrow.hac.food.material.entity.CasseroleItem;
import defeatedcrow.hac.food.material.entity.ChazukeItem;
import defeatedcrow.hac.food.material.entity.CookedSweetpotatoItem;
import defeatedcrow.hac.food.material.entity.DrinkColdItem;
import defeatedcrow.hac.food.material.entity.DrinkCupItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
import defeatedcrow.hac.food.material.entity.PlateBeefItem;
import defeatedcrow.hac.food.material.entity.PlateChickenItem;
import defeatedcrow.hac.food.material.entity.PlateFishItem;
import defeatedcrow.hac.food.material.entity.PlateGarlicItem;
import defeatedcrow.hac.food.material.entity.PlateLegsItem;
import defeatedcrow.hac.food.material.entity.PlateMeatItem;
import defeatedcrow.hac.food.material.entity.SaladItem;
import defeatedcrow.hac.food.material.entity.SandwichItem;
import defeatedcrow.hac.food.material.entity.SquareFishItem;
import defeatedcrow.hac.food.material.entity.SquareSashimiItem;
import defeatedcrow.hac.food.material.entity.StickBeefItem;
import defeatedcrow.hac.food.material.entity.StickFishItem;
import defeatedcrow.hac.food.material.entity.StickMeatItem;
import defeatedcrow.hac.food.material.entity.TartItem;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Fish;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Meat;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Rice;
import defeatedcrow.hac.food.material.entity.potfoods.CurryItem_Sashimi;
import defeatedcrow.hac.food.material.entity.potfoods.PorridgeItem;
import defeatedcrow.hac.food.material.entity.potfoods.RiceBowlItem;
import defeatedcrow.hac.food.material.entity.potfoods.SoupItem;
import defeatedcrow.hac.food.material.item.EdibleMaterialItem;
import defeatedcrow.hac.food.material.item.EmptyPackItem;
import defeatedcrow.hac.food.material.item.FertilizerItemDC;
import defeatedcrow.hac.food.material.item.FoodMaterialItemDC;
import defeatedcrow.hac.food.material.item.ItemCropDC;
import defeatedcrow.hac.food.material.item.RawFishItem;
import defeatedcrow.hac.food.material.item.SeedItemDC;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

public class FoodInit {

	public static final CreativeModeTab FOOD = new CreativeTabClimate_Food("food");
	public static final CreativeModeTab AGRI = new CreativeTabClimate_Agri("agri");
	public static final CreativeModeTab CONT = new CreativeTabClimate_Cont("container");

	public static void init() {}

	// foods
	public static final RegistryObject<EntityType<FoodEntityBase>> BREAD_ROUND = CoreInit.ENTITIES.register("bread_round", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.2F).updateInterval(5).build("bread_round"));
	public static final RegistryObject<EntityType<FoodEntityBase>> BREAD_SQUARE = CoreInit.ENTITIES.register("bread_square", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.4F).updateInterval(5).build("bread_square"));
	public static final RegistryObject<EntityType<FoodEntityBase>> BREAD_CREAM = CoreInit.ENTITIES.register("bread_cream", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.2F).updateInterval(5).build("bread_cream"));
	public static final RegistryObject<EntityType<FoodEntityBase>> BREAD_SAUSAGE = CoreInit.ENTITIES.register("bread_sausage", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.2F).updateInterval(5).build("bread_sausage"));
	public static final RegistryObject<EntityType<FoodEntityBase>> SANDWICH = CoreInit.ENTITIES.register("sandwich", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).updateInterval(5).build("sandwich"));

	public static final RegistryObject<EntityType<FoodEntityBase>> TART = CoreInit.ENTITIES.register("tart", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.75F, 0.5F).updateInterval(5).build("tart"));

	public static final RegistryObject<EntityType<FoodEntityBase>> CAKE = CoreInit.ENTITIES.register("cake", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).updateInterval(5).build("cake"));

	public static final RegistryObject<EntityType<FoodEntityBase>> SWEETPOTATO = CoreInit.ENTITIES.register("cooked_sweetpotato", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.5F).updateInterval(5).build("cooked_sweetpotato"));

	public static final RegistryObject<EntityType<FoodEntityBase>> STICK_BEEF = CoreInit.ENTITIES.register("stick_beef", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.5F).updateInterval(5).build("stick_beef"));
	public static final RegistryObject<EntityType<FoodEntityBase>> STICK_MEAT = CoreInit.ENTITIES.register("stick_meat", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.5F).updateInterval(5).build("stick_meat"));
	public static final RegistryObject<EntityType<FoodEntityBase>> STICK_FISH = CoreInit.ENTITIES.register("stick_fish", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.5F).updateInterval(5).build("stick_fish"));

	public static final RegistryObject<EntityType<FoodEntityBase>> PLATE_STEAK = CoreInit.ENTITIES.register("plate_steak", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.75F, 0.4F).updateInterval(5).build("plate_steak"));
	public static final RegistryObject<EntityType<FoodEntityBase>> PLATE_MEAT = CoreInit.ENTITIES.register("plate_meat", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.75F, 0.4F).updateInterval(5).build("plate_meat"));
	public static final RegistryObject<EntityType<FoodEntityBase>> PLATE_LEGS = CoreInit.ENTITIES.register("plate_legs", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.75F, 0.4F).updateInterval(5).build("plate_legs"));
	public static final RegistryObject<EntityType<FoodEntityBase>> PLATE_BIG_STEAK = CoreInit.ENTITIES.register("plate_big_steak", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.75F, 0.5F).updateInterval(5).build("plate_big_steak"));
	public static final RegistryObject<EntityType<FoodEntityBase>> PLATE_CHICKEN = CoreInit.ENTITIES.register("plate_chicken", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.75F, 0.5F).updateInterval(5).build("plate_chicken"));
	public static final RegistryObject<EntityType<FoodEntityBase>> PLATE_FISH = CoreInit.ENTITIES.register("plate_fish", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.75F, 0.4F).updateInterval(5).build("plate_fish"));

	public static final RegistryObject<EntityType<FoodEntityBase>> CASSEROLE = CoreInit.ENTITIES.register("casserole", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.25F).updateInterval(5).build("casserole"));

	public static final RegistryObject<EntityType<FoodEntityBase>> SALAD = CoreInit.ENTITIES.register("salad", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).updateInterval(5).build("salad"));

	public static final RegistryObject<EntityType<FoodEntityBase>> STEW = CoreInit.ENTITIES.register("stew", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.25F).updateInterval(5).build("stew"));

	public static final RegistryObject<EntityType<FoodEntityBase>> SOUP = CoreInit.ENTITIES.register("soup", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.25F).updateInterval(5).build("soup"));

	public static final RegistryObject<EntityType<FoodEntityBase>> CURRY_BASE = CoreInit.ENTITIES.register("curry_base_model", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.25F).updateInterval(5).build("curry_base_model"));
	public static final RegistryObject<EntityType<FoodEntityBase>> CURRY_FISH_MODEL = CoreInit.ENTITIES.register("curry_fish_model", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.25F).updateInterval(5).build("curry_fish_model"));
	public static final RegistryObject<EntityType<FoodEntityBase>> CURRY_MEAT_MODEL = CoreInit.ENTITIES.register("curry_meat_model", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.25F).updateInterval(5).build("curry_meat_model"));
	public static final RegistryObject<EntityType<FoodEntityBase>> CURRY_RICE_MODEL = CoreInit.ENTITIES.register("curry_rice_model", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.25F).updateInterval(5).build("curry_rice_model"));
	public static final RegistryObject<EntityType<FoodEntityBase>> CURRY_SASHIMI_MODEL = CoreInit.ENTITIES.register("curry_sashimi_model", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new,
		MobCategory.MISC).sized(0.5F, 0.25F).updateInterval(5).build("curry_sashimi_model"));

	public static final RegistryObject<EntityType<FoodEntityBase>> SQUARE_SASHIMI = CoreInit.ENTITIES.register("square_sashimi", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.4F, 0.2F).updateInterval(5).build("square_sashimi"));
	public static final RegistryObject<EntityType<FoodEntityBase>> SQUARE_FISH = CoreInit.ENTITIES.register("square_fish", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.4F, 0.2F).updateInterval(5).build("square_fish"));

	public static final RegistryObject<EntityType<FoodEntityBase>> RICE = CoreInit.ENTITIES.register("rice", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.375F).updateInterval(5).build("rice"));

	public static final RegistryObject<EntityType<FoodEntityBase>> CHAZUKE = CoreInit.ENTITIES.register("chazuke", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.375F).updateInterval(5).build("chazuke"));

	public static final RegistryObject<EntityType<FoodEntityBase>> BOTTLE_BEERTYPE = CoreInit.ENTITIES.register("bottle_beer", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.25F, 0.5F).updateInterval(5).build("bottle_beer"));

	public static final RegistryObject<EntityType<FoodEntityBase>> BOTTLE_WINETYPE = CoreInit.ENTITIES.register("bottle_wine", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.25F, 0.5F).updateInterval(5).build("bottle_wine"));

	public static final RegistryObject<EntityType<FoodEntityBase>> CUP = CoreInit.ENTITIES.register("cup", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.25F, 0.25F).updateInterval(5).build("cup"));

	public static final RegistryObject<EntityType<FoodEntityBase>> GLASS = CoreInit.ENTITIES.register("glass", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.25F, 0.3F).updateInterval(5).build("glass"));

	// 串焼き
	public static final RegistryObject<Item> STICK_BEEF_RAW = regItem("stick_beef_raw", () -> new StickBeefItem("stick_beef_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_BEEF_COOKED = regItem("stick_beef_cooked", () -> new StickBeefItem("stick_beef_cooked", 10, 0.4F, TagDC.ItemTag.HAC_SKEWERED));
	public static final RegistryObject<Item> STICK_PORK_RAW = regItem("stick_pork_raw", () -> new StickBeefItem("stick_pork_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_PORK_COOKED = regItem("stick_pork_cooked", () -> new StickBeefItem("stick_pork_cooked", 10, 0.4F, TagDC.ItemTag.HAC_SKEWERED));
	public static final RegistryObject<Item> STICK_MUTTON_RAW = regItem("stick_mutton_raw", () -> new StickMeatItem("stick_mutton_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_MUTTON_COOKED = regItem("stick_mutton_cooked", () -> new StickMeatItem("stick_mutton_cooked", 8, 0.3F, TagDC.ItemTag.HAC_SKEWERED));
	public static final RegistryObject<Item> STICK_CHICKEN_RAW = regItem("stick_chicken_raw", () -> new StickMeatItem("stick_chicken_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_CHICKEN_COOKED = regItem("stick_chicken_cooked", () -> new StickMeatItem("stick_chicken_cooked", 8, 0.3F, TagDC.ItemTag.HAC_SKEWERED));
	public static final RegistryObject<Item> STICK_OFFAL_RAW = regItem("stick_offal_raw", () -> new StickMeatItem("stick_offal_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_OFFAL_COOKED = regItem("stick_offal_cooked", () -> new StickMeatItem("stick_offal_cooked", 8, 0.3F, TagDC.ItemTag.HAC_SKEWERED));
	public static final RegistryObject<Item> STICK_FISH_RAW = regItem("stick_fish_raw", () -> new StickFishItem("stick_fish_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_FISH_COOKED = regItem("stick_fish_cooked", () -> new StickFishItem("stick_fish_cooked", 6, 0.3F, TagDC.ItemTag.HAC_SKEWERED));

	// 鉄板焼
	public static final RegistryObject<Item> PLATE_BEEF_RAW = regItem("plate_beef_raw", () -> new PlateBeefItem("plate_beef_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_BEEF_COOKED = regItem("plate_beef_cooked", () -> new PlateBeefItem("plate_beef_cooked", 14, 1.0F, TagDC.ItemTag.HAC_PLATE_MEAL));
	public static final RegistryObject<Item> PLATE_MEAT_RAW = regItem("plate_meat_raw", () -> new PlateMeatItem("plate_meat_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_MEAT_COOKED = regItem("plate_meat_cooked", () -> new PlateMeatItem("plate_meat_cooked", 12, 0.8F, TagDC.ItemTag.HAC_PLATE_MEAL));
	public static final RegistryObject<Item> PLATE_LEGS_RAW = regItem("plate_legs_raw", () -> new PlateLegsItem("plate_legs_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_LEGS_COOKED = regItem("plate_legs_cooked", () -> new PlateLegsItem("plate_legs_cooked", 10, 0.6F, TagDC.ItemTag.HAC_PLATE_MEAL));
	public static final RegistryObject<Item> PLATE_GARLIC_RAW = regItem("plate_big_garlic_raw", () -> new PlateGarlicItem("plate_big_garlic_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_GARLIC_COOKED = regItem("plate_big_garlic_cooked", () -> new PlateGarlicItem("plate_big_garlic_cooked", 16, 0.8F, TagDC.ItemTag.HAC_PLATE_MEAL));
	public static final RegistryObject<Item> PLATE_CHICKEN_BIG_RAW = regItem("plate_big_chicken_raw", () -> new PlateChickenItem("plate_big_chicken_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_CHICKEN_BIG_COOKED = regItem("plate_big_chicken_cooked", () -> new PlateChickenItem("plate_big_chicken_cooked", 14, 1.0F, TagDC.ItemTag.HAC_PLATE_MEAL));
	public static final RegistryObject<Item> PLATE_FISH_RAW = regItem("plate_fish_raw", () -> new PlateFishItem("plate_fish_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_FISH_COOKED = regItem("plate_fish_cooked", () -> new PlateFishItem("plate_fish_cooked", 10, 0.6F, TagDC.ItemTag.HAC_PLATE_MEAL));

	public static final RegistryObject<Item> SWEETPOTATO_RAW = regItem("sweetpotato_raw", () -> new CookedSweetpotatoItem("sweetpotato_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> SWEETPOTATO_COOKED = regItem("sweetpotato_cooked", () -> new CookedSweetpotatoItem("sweetpotato_cooked", 6, 0.2F, TagDC.ItemTag.HAC_SALAD));

	// パン
	public static final RegistryObject<Item> BREAD_ROUND_RAW_ITEM = regItem("bread_round_raw", () -> new BreadRoundItem("bread_round_raw", 0, 0F, TagDC.ItemTag.DOUGH).setRawFood());
	public static final RegistryObject<Item> BREAD_ROUND_BAKED_ITEM = regItem("bread_round_baked", () -> new BreadRoundItem("bread_round_baked", 4, 0.4F, TagDC.ItemTag.HAC_BREAD_PLANE));
	public static final RegistryObject<Item> BREAD_SQUARE_RAW_ITEM = regItem("bread_square_raw", () -> new BreadSquareItem("bread_square_raw", 0, 0F, TagDC.ItemTag.DOUGH).setRawFood());
	public static final RegistryObject<Item> BREAD_SQUARE_BAKED_ITEM = regItem("bread_square_baked", () -> new BreadSquareItem("bread_square_baked", 4, 0.4F, TagDC.ItemTag.HAC_BREAD_PLANE));
	public static final RegistryObject<Item> BREAD_NUTS_RAW_ITEM = regItem("bread_nuts_raw", () -> new BreadRoundItem("bread_nuts_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_NUTS_BAKED_ITEM = regItem("bread_nuts_baked", () -> new BreadRoundItem("bread_nuts_baked", 6, 0.4F, TagDC.ItemTag.HAC_BREAD));
	public static final RegistryObject<Item> BREAD_CINNAMON_RAW_ITEM = regItem("bread_cinnamon_raw", () -> new BreadRoundItem("bread_cinnamon_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_CINNAMON_BAKED_ITEM = regItem("bread_cinnamon_baked", () -> new BreadRoundItem("bread_cinnamon_baked", 6, 0.4F, TagDC.ItemTag.HAC_BREAD));
	public static final RegistryObject<Item> BREAD_ANKO_RAW_ITEM = regItem("bread_anko_raw", () -> new BreadRoundItem("bread_anko_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_ANKO_BAKED_ITEM = regItem("bread_anko_baked", () -> new BreadRoundItem("bread_anko_baked", 7, 0.4F, TagDC.ItemTag.HAC_BREAD));
	public static final RegistryObject<Item> BREAD_CREAM_RAW_ITEM = regItem("bread_cream_raw", () -> new BreadCreamItem("bread_cream_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_CREAM_BAKED_ITEM = regItem("bread_cream_baked", () -> new BreadCreamItem("bread_cream_baked", 6, 0.4F, TagDC.ItemTag.HAC_BREAD));
	public static final RegistryObject<Item> BREAD_SAUSAGE_RAW_ITEM = regItem("bread_sausage_raw", () -> new BreadSausageItem("bread_sausage_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_SAUSAGE_BAKED_ITEM = regItem("bread_sausage_baked", () -> new BreadSausageItem("bread_sausage_baked", 7, 0.6F, TagDC.ItemTag.HAC_BREAD));
	// public static final RegistryObject<Item> BREAD_PITA_RAW_ITEM = regItem("bread_pita_raw", () -> new BreadRoundItem("bread_pita_raw", 0, 0F, null).setRawFood());
	// public static final RegistryObject<Item> BREAD_PITA_BAKED_ITEM = regItem("bread_pita_baked", () -> new BreadRoundItem("bread_pita_baked", 2, 0.15F, null));
	// public static final RegistryObject<Item> BREAD_TORTILLA_RAW_ITEM = regItem("bread_tortilla_raw", () -> new BreadRoundItem("bread_tortilla_raw", 0, 0F, null).setRawFood());
	// public static final RegistryObject<Item> BREAD_TORTILLA_BAKED_ITEM = regItem("bread_tortilla_baked", () -> new BreadRoundItem("bread_tortilla_baked", 2, 0.15F, null));

	// キャセロール
	public static final RegistryObject<Item> CASSEROLE_GRATIN_SHRIMP_RAW_ITEM = regItem("casserole_gratin_shrimp_raw", () -> new CasseroleItem("casserole_gratin_shrimp_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> CASSEROLE_GRATIN_SHRIMP_BAKED_ITEM = regItem("casserole_gratin_shrimp_baked", () -> new CasseroleItem("casserole_gratin_shrimp_baked", 14, 0.4F, TagDC.ItemTag.HAC_CASSEROLE));
	public static final RegistryObject<Item> CASSEROLE_SHEPHERDS_PIE_RAW_ITEM = regItem("casserole_shepherds_pie_raw", () -> new CasseroleItem("casserole_shepherds_pie_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> CASSEROLE_SHEPHERDS_PIE_BAKED_ITEM = regItem("casserole_shepherds_pie_baked", () -> new CasseroleItem("casserole_shepherds_pie_baked", 14, 0.4F, TagDC.ItemTag.HAC_CASSEROLE));
	public static final RegistryObject<Item> CASSEROLE_DORIA_RAW_ITEM = regItem("casserole_doria_raw", () -> new CasseroleItem("casserole_doria_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> CASSEROLE_DORIA_BAKED_ITEM = regItem("casserole_doria_baked", () -> new CasseroleItem("casserole_doria_baked", 12, 0.6F, TagDC.ItemTag.HAC_CASSEROLE));
	public static final RegistryObject<Item> CASSEROLE_JANSSONS_FRESTELESE_RAW_ITEM = regItem("casserole_janssons_frestelese_raw", () -> new CasseroleItem("casserole_janssons_frestelese_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> CASSEROLE_JANSSONS_FRESTELESE_BAKED_ITEM = regItem("casserole_janssons_frestelese_baked",
		() -> new CasseroleItem("casserole_janssons_frestelese_baked", 12, 0.4F, TagDC.ItemTag.HAC_CASSEROLE));
	public static final RegistryObject<Item> CASSEROLE_PARMIGIANA_RAW_ITEM = regItem("casserole_parmigiana_raw", () -> new CasseroleItem("casserole_parmigiana_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> CASSEROLE_PARMIGIANA_BAKED_ITEM = regItem("casserole_parmigiana_baked", () -> new CasseroleItem("casserole_parmigiana_baked", 10, 0.4F, TagDC.ItemTag.HAC_CASSEROLE));
	public static final RegistryObject<Item> CASSEROLE_MOUSSAKA_RAW_ITEM = regItem("casserole_moussaka_raw", () -> new CasseroleItem("casserole_moussaka_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> CASSEROLE_MOUSSAKA_BAKED_ITEM = regItem("casserole_moussaka_baked", () -> new CasseroleItem("casserole_moussaka_baked", 10, 0.4F, TagDC.ItemTag.HAC_CASSEROLE));

	// タルト
	public static final RegistryObject<Item> TART_APPLE_RAW_ITEM = regItem("tart_apple_raw", () -> new TartItem("tart_apple_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> TART_APPLE_BAKED_ITEM = regItem("tart_apple_baked", () -> new TartItem("tart_apple_baked", 10, 0.2F, TagDC.ItemTag.HAC_TART));
	public static final RegistryObject<Item> TART_BERRY_RAW_ITEM = regItem("tart_berry_raw", () -> new TartItem("tart_berry_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> TART_BERRY_BAKED_ITEM = regItem("tart_berry_baked", () -> new TartItem("tart_berry_baked", 10, 0.2F, TagDC.ItemTag.HAC_TART));
	public static final RegistryObject<Item> TART_PEACH_RAW_ITEM = regItem("tart_peach_raw", () -> new TartItem("tart_peach_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> TART_PEACH_BAKED_ITEM = regItem("tart_peach_baked", () -> new TartItem("tart_peach_baked", 12, 0.3F, TagDC.ItemTag.HAC_TART));
	public static final RegistryObject<Item> TART_LEMON_RAW_ITEM = regItem("tart_lemon_raw", () -> new TartItem("tart_lemon_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> TART_LEMON_BAKED_ITEM = regItem("tart_lemon_baked", () -> new TartItem("tart_lemon_baked", 12, 0.3F, TagDC.ItemTag.HAC_TART));
	public static final RegistryObject<Item> TART_COCOA_RAW_ITEM = regItem("tart_cocoa_raw", () -> new TartItem("tart_cocoa_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> TART_COCOA_BAKED_ITEM = regItem("tart_cocoa_baked", () -> new TartItem("tart_cocoa_baked", 12, 0.3F, TagDC.ItemTag.HAC_TART));
	public static final RegistryObject<Item> TART_PISTACHIO_RAW_ITEM = regItem("tart_pistachio_raw", () -> new TartItem("tart_pistachio_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> TART_PISTACHIO_BAKED_ITEM = regItem("tart_pistachio_baked", () -> new TartItem("tart_pistachio_baked", 14, 0.3F, TagDC.ItemTag.HAC_TART));
	public static final RegistryObject<Item> TART_QUICHE_RAW_ITEM = regItem("tart_quiche_raw", () -> new TartItem("tart_quiche_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> TART_QUICHE_BAKED_ITEM = regItem("tart_quiche_baked", () -> new TartItem("tart_quiche_baked", 14, 0.3F, TagDC.ItemTag.HAC_TART));

	// サンドイッチ
	public static final RegistryObject<Item> SANDWICH_FRUIT_ITEM = regItem("sandwich_fruit", () -> new SandwichItem("sandwich_fruit", 5, 0.3F, TagDC.ItemTag.HAC_BREAD_SANDWICH));
	public static final RegistryObject<Item> SANDWICH_MARMALADE_ITEM = regItem("sandwich_marmalade", () -> new SandwichItem("sandwich_marmalade", 5, 0.3F, TagDC.ItemTag.HAC_BREAD_SANDWICH));
	public static final RegistryObject<Item> SANDWICH_EGG_ITEM = regItem("sandwich_egg", () -> new SandwichItem("sandwich_egg", 6, 0.4F, TagDC.ItemTag.HAC_BREAD_SANDWICH));
	public static final RegistryObject<Item> SANDWICH_SALAD_ITEM = regItem("sandwich_salad", () -> new SandwichItem("sandwich_salad", 6, 0.4F, TagDC.ItemTag.HAC_BREAD_SANDWICH));
	public static final RegistryObject<Item> SANDWICH_SALMON_ITEM = regItem("sandwich_salmon", () -> new SandwichItem("sandwich_salmon", 6, 0.4F, TagDC.ItemTag.HAC_BREAD_SANDWICH));

	// ケーキ
	public static final RegistryObject<Item> CAKE_BUTTER = regItem("cake_butter", () -> new CakeItem("cake_butter", 4, 0.3F, TagDC.ItemTag.HAC_SWEETS));
	public static final RegistryObject<Item> CAKE_BERRY = regItem("cake_berry", () -> new CakeItem("cake_berry", 6, 0.4F, TagDC.ItemTag.HAC_SWEETS));
	public static final RegistryObject<Item> CAKE_CHOCOLATE = regItem("cake_chocolate", () -> new CakeItem("cake_chocolate", 6, 0.4F, TagDC.ItemTag.HAC_SWEETS));
	public static final RegistryObject<Item> CAKE_GREENTEA = regItem("cake_greentea", () -> new CakeItem("cake_greentea", 6, 0.4F, TagDC.ItemTag.HAC_SWEETS));

	// おかゆ
	public static final RegistryObject<Item> PORRIDGE = regItem("porridge_simple", () -> new PorridgeItem("porridge_simple", 4, 0.6F, true, TagDC.ItemTag.HAC_PORRIDGE));
	public static final RegistryObject<Item> PORRIDGE_MILK = regItem("porridge_milk", () -> new PorridgeItem("porridge_milk", 6, 0.6F, true, TagDC.ItemTag.HAC_PORRIDGE));
	public static final RegistryObject<Item> MUESLI = regItem("muesli", () -> new PorridgeItem("muesli", 12, 0.4F, true, TagDC.ItemTag.HAC_PORRIDGE));
	public static final RegistryObject<Item> PORRIDGE_SAFFRON = regItem("porridge_saffron", () -> new PorridgeItem("porridge_saffron", 10, 0.6F, false, TagDC.ItemTag.HAC_PORRIDGE));
	public static final RegistryObject<Item> PORRIDGE_SQUID = regItem("porridge_squid", () -> new PorridgeItem("porridge_squid", 10, 0.6F, false, TagDC.ItemTag.HAC_PORRIDGE));

	// スープ
	public static final RegistryObject<Item> SOUP_CREAM_POTATO = regItem("soup_cream_potato", () -> new SoupItem("soup_cream_potato", 8, 0.5F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CREAM_PUMPKIN = regItem("soup_cream_pumpkin", () -> new SoupItem("soup_cream_pumpkin", 8, 0.5F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CREAM_CORN = regItem("soup_cream_corn", () -> new SoupItem("soup_cream_corn", 8, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CREAM_SPINACH = regItem("soup_cream_spinach", () -> new SoupItem("soup_cream_spinach", 8, 0.5F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CREAM_PARSNIP = regItem("soup_cream_parsnip", () -> new SoupItem("soup_cream_parsnip", 8, 0.5F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CREAM_SHRIMP = regItem("soup_cream_shrimp", () -> new SoupItem("soup_cream_shrimp", 10, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CHINESE_EGG = regItem("soup_chinese_egg", () -> new SoupItem("soup_chinese_egg", 8, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CHINESE_CRAB = regItem("soup_chinese_crab", () -> new SoupItem("soup_chinese_crab", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_JUTE = regItem("soup_jute", () -> new SoupItem("soup_jute", 8, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_GASPACHO = regItem("soup_gaspacho", () -> new SoupItem("soup_gaspacho", 8, 0.6F, true, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_TARATOR = regItem("soup_tarator", () -> new SoupItem("soup_tarator", 6, 0.6F, true, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_MINESTRONE = regItem("soup_minestrone", () -> new SoupItem("soup_minestrone", 10, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_CHILIBEANS = regItem("soup_chilibeans", () -> new SoupItem("soup_chilibeans", 12, 0.8F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> SOUP_SORREL = regItem("soup_sorrel", () -> new SoupItem("soup_sorrel", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));

	public static final RegistryObject<Item> STEW_BORSCH = regItem("stew_borsch", () -> new PorridgeItem("stew_borsch", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_CONSOMME_VEGETABLE = regItem("stew_consomme_vegetable", () -> new PorridgeItem("stew_consomme_vegetable", 10, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_IRISH = regItem("stew_irish", () -> new PorridgeItem("stew_irish", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_CREAM_MUSHROOM = regItem("stew_cream_mushroom", () -> new PorridgeItem("stew_cream_mushroom", 10, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_CREAM_SALMON = regItem("stew_cream_salmon", () -> new PorridgeItem("stew_cream_salmon", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_CREAM_SHRIMP = regItem("stew_cream_shrimp", () -> new PorridgeItem("stew_cream_shrimp", 10, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_CULLEN_SKINK = regItem("stew_cullenskink", () -> new PorridgeItem("stew_cullenskink", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_KHARCHO = regItem("stew_kharcho", () -> new PorridgeItem("stew_kharcho", 14, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_ERWTEN = regItem("stew_erwten", () -> new PorridgeItem("stew_erwten", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_LAMPREDOTTO = regItem("stew_lampredotto", () -> new PorridgeItem("stew_lampredotto", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_TOMYUMGOONG = regItem("stew_tomyumgoong", () -> new PorridgeItem("stew_tomyumgoong", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_TOMYUMPLA = regItem("stew_tomyumpla", () -> new PorridgeItem("stew_tomyumpla", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_TOMYUMGAI = regItem("stew_tomyumgai", () -> new PorridgeItem("stew_tomyumgai", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_BAKKUTTEH = regItem("stew_bakkutteh", () -> new PorridgeItem("stew_bakkutteh", 14, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_MOTU = regItem("stew_offal", () -> new PorridgeItem("stew_offal", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_MISO_TOFU = regItem("stew_miso_tofu", () -> new PorridgeItem("stew_miso_tofu", 8, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_MISO_EGGPLANT = regItem("stew_miso_eggplant", () -> new PorridgeItem("stew_miso_eggplant", 8, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_MISO_MUSHROOM = regItem("stew_miso_mushroom", () -> new PorridgeItem("stew_miso_mushroom", 8, 0.6F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> STEW_MISO_PORK = regItem("stew_miso_pork", () -> new PorridgeItem("stew_miso_pork", 12, 0.6F, false, TagDC.ItemTag.HAC_SOUP));

	// カレー
	public static final RegistryObject<Item> CURRY_VEGI = regItem("curry_vegi", () -> new CurryItem("curry_vegi", 8, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_BEANS = regItem("curry_beans", () -> new CurryItem("curry_beans", 8, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_SPINACH = regItem("curry_spinach", () -> new CurryItem("curry_spinach", 11, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_FISH = regItem("curry_fish", () -> new CurryItem_Fish("curry_fish", 12, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_BUTTER_CHICKEN = regItem("curry_butter_chicken", () -> new CurryItem("curry_butter_chicken", 14, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_VINDALOO = regItem("curry_vindaloo", () -> new CurryItem("curry_vindaloo", 14, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_BIRIYANI = regItem("curry_biriyani", () -> new CurryItem("curry_biriyani", 15, 0.6F, false, TagDC.ItemTag.HAC_RICE_MEAL));
	public static final RegistryObject<Item> CURRY_RICE = regItem("curry_rice", () -> new CurryItem_Rice("curry_rice", 15, 0.6F, false, TagDC.ItemTag.HAC_RICE_MEAL));

	public static final RegistryObject<Item> CURRY_GREEN = regItem("curry_green", () -> new CurryItem("curry_green", 12, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_RED = regItem("curry_red", () -> new CurryItem("curry_red", 12, 0.6F, false, TagDC.ItemTag.HAC_CURRY));
	public static final RegistryObject<Item> CURRY_MASSAMAN = regItem("curry_massaman", () -> new CurryItem("curry_massaman", 12, 0.6F, false, TagDC.ItemTag.HAC_CURRY));

	// 煮込み
	public static final RegistryObject<Item> LARGE_BOWL_ACQUA_PAZZA = regItem("large_bowl_acqua_pazza", () -> new CurryItem_Fish("large_bowl_acqua_pazza", 12, 0.4F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> LARGE_BOWL_FISH = regItem("large_bowl_fish", () -> new CurryItem_Meat("large_bowl_fish", 14, 0.4F, false, TagDC.ItemTag.HAC_SOUP));
	public static final RegistryObject<Item> LARGE_BOWL_CABBAGE = regItem("large_bowl_cabbage", () -> new CurryItem_Meat("large_bowl_cabbage", 10, 0.4F, false, TagDC.ItemTag.HAC_MEAT_MEAL));
	public static final RegistryObject<Item> LARGE_BOWL_LAMB = regItem("large_bowl_lamb", () -> new CurryItem_Meat("large_bowl_lamb", 12, 0.4F, false, TagDC.ItemTag.HAC_MEAT_MEAL));
	public static final RegistryObject<Item> LARGE_BOWL_BEEF = regItem("large_bowl_beef", () -> new CurryItem_Meat("large_bowl_beef", 12, 0.4F, false, TagDC.ItemTag.HAC_MEAT_MEAL));

	// 刺身
	public static final RegistryObject<Item> LARGE_BOWL_CARPACCIO = regItem("large_bowl_carpaccio", () -> new CurryItem_Sashimi("large_bowl_carpaccio", 16, 0.8F, false, TagDC.ItemTag.HAC_MEAT_MEAL));
	public static final RegistryObject<Item> LARGE_BOWL_FISH_CARPACCIO = regItem("large_bowl_fish_carpaccio", () -> new CurryItem_Sashimi("large_bowl_fish_carpaccio", 16, 0.8F, false, TagDC.ItemTag.HAC_FISH_MEAL));
	public static final RegistryObject<Item> LARGE_BOWL_CAPRESE = regItem("large_bowl_caprese", () -> new CurryItem_Sashimi("large_bowl_caprese", 12, 0.8F, false, TagDC.ItemTag.HAC_SALAD));

	public static final RegistryObject<Item> SASHIMI_WHITE = regItem("sashimi_white", () -> new SquareSashimiItem("sashimi_white", 8, 0.6F, TagDC.ItemTag.HAC_FISH_MEAL));
	public static final RegistryObject<Item> SASHIMI_SALMON = regItem("sashimi_salmon", () -> new SquareSashimiItem("sashimi_salmon", 8, 0.6F, TagDC.ItemTag.HAC_FISH_MEAL));
	public static final RegistryObject<Item> SASHIMI_BLUE = regItem("sashimi_blue", () -> new SquareSashimiItem("sashimi_blue", 8, 0.6F, TagDC.ItemTag.HAC_FISH_MEAL));
	public static final RegistryObject<Item> SASHIMI_TUNA = regItem("sashimi_tuna", () -> new SquareSashimiItem("sashimi_tuna", 8, 0.6F, TagDC.ItemTag.HAC_FISH_MEAL));
	public static final RegistryObject<Item> SASHIMI_SQUID = regItem("sashimi_squid", () -> new SquareSashimiItem("sashimi_squid", 8, 0.6F, TagDC.ItemTag.HAC_FISH_MEAL));

	public static final RegistryObject<Item> FISH_MEUNIERE = regItem("fish_meuniere", () -> new SquareFishItem("fish_meuniere", 10, 0.6F, TagDC.ItemTag.HAC_FISH_MEAL));
	public static final RegistryObject<Item> SIMMERD_FISH = regItem("simmered_fish", () -> new SquareFishItem("simmered_fish", 10, 0.6F, TagDC.ItemTag.HAC_FISH_MEAL));

	// 揚げ物

	// ごはん
	public static final RegistryObject<Item> BOILED_RICE = regItem("rice_boiled", () -> new RiceBowlItem("rice_boiled", 6, 0.4F, TagDC.ItemTag.BOILED_RICE));
	public static final RegistryObject<Item> RICE_BARLEY = regItem("rice_barley", () -> new RiceBowlItem("rice_barley", 6, 0.4F, TagDC.ItemTag.BOILED_RICE));
	public static final RegistryObject<Item> RICE_NAPA = regItem("rice_napa", () -> new RiceBowlItem("rice_napa", 8, 0.4F, TagDC.ItemTag.HAC_RICE_MEAL));
	public static final RegistryObject<Item> RICE_SEKI = regItem("rice_seki", () -> new RiceBowlItem("rice_seki", 10, 0.4F, TagDC.ItemTag.HAC_RICE_MEAL));
	public static final RegistryObject<Item> RICE_FISH = regItem("rice_fish", () -> new RiceBowlItem("rice_fish", 10, 0.4F, TagDC.ItemTag.HAC_RICE_MEAL));

	public static final RegistryObject<Item> CHAZUKE_UME = regItem("chazuke_ume", () -> new ChazukeItem("chazuke_ume", 10, 0.4F, TagDC.ItemTag.HAC_RICE_MEAL));
	public static final RegistryObject<Item> CHAZUKE_SAKE = regItem("chazuke_sake", () -> new ChazukeItem("chazuke_sake", 12, 0.4F, TagDC.ItemTag.HAC_RICE_MEAL));
	public static final RegistryObject<Item> CHAZUKE_TARAKO = regItem("chazuke_tarako", () -> new ChazukeItem("chazuke_tarako", 12, 0.4F, TagDC.ItemTag.HAC_RICE_MEAL));

	// 麺
	// そば

	// サラダ
	public static final RegistryObject<Item> SALAD_GREEN = regItem("salad_green", () -> new SaladItem("salad_green", 6, 0.4F, TagDC.ItemTag.HAC_SALAD));
	public static final RegistryObject<Item> SALAD_POTATO = regItem("salad_potato", () -> new SaladItem("salad_potato", 10, 0.4F, TagDC.ItemTag.HAC_SALAD));
	public static final RegistryObject<Item> SALAD_NUTS = regItem("salad_nuts", () -> new SaladItem("salad_nuts", 8, 0.4F, TagDC.ItemTag.HAC_SALAD));
	public static final RegistryObject<Item> SALAD_MELON = regItem("salad_melon", () -> new SaladItem("salad_melon", 7, 0.4F, TagDC.ItemTag.HAC_SALAD));

	// ドリンク
	public static final RegistryObject<Item> DRINK_APPLE = regItem("drink_apple", () -> new DrinkColdItem("drink_apple", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_BERRY = regItem("drink_berry", () -> new DrinkColdItem("drink_berry", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_MELON = regItem("drink_melon", () -> new DrinkColdItem("drink_melon", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_GRAPE = regItem("drink_grape", () -> new DrinkColdItem("drink_grape", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_GRAPE_WHITE = regItem("drink_grape_white", () -> new DrinkColdItem("drink_grape_white", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_PLUM = regItem("drink_plum", () -> new DrinkColdItem("drink_plum", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_PEACH = regItem("drink_peach", () -> new DrinkColdItem("drink_peach", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_POMELO = regItem("drink_pomelo", () -> new DrinkColdItem("drink_pomelo", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_MANDARIN = regItem("drink_mandarin", () -> new DrinkColdItem("drink_mandarin", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_LEMON = regItem("drink_lemon", () -> new DrinkColdItem("drink_lemon", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_MANGO = regItem("drink_mango", () -> new DrinkColdItem("drink_mango", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_GUAVA = regItem("drink_guava", () -> new DrinkColdItem("drink_guava", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_VEGETABLE = regItem("drink_vegetable", () -> new DrinkColdItem("drink_vegetable", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_MILK_SHAKE = regItem("drink_milkshake", () -> new DrinkColdItem("drink_milkshake", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_LASSI_PLANE = regItem("drink_lassi_plane", () -> new DrinkColdItem("drink_lassi_plane", 4, 0F, 0, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_LASSI_MANGO = regItem("drink_lassi_mango", () -> new DrinkColdItem("drink_lassi_mango", 4, 0F, 0, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_LASSI_CITRUS = regItem("drink_lassi_citrus", () -> new DrinkColdItem("drink_lassi_citrus", 4, 0F, 0, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_TEA_BARLEY = regItem("drink_tea_barley", () -> new DrinkColdItem("drink_tea_barley", 4, 0F, 2, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_TONIC = regItem("drink_soda_blue", () -> new DrinkColdItem("drink_soda_blue", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_TEA_SODA = regItem("drink_soda_tea", () -> new DrinkColdItem("drink_soda_tea", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_APPLE_SODA = regItem("drink_soda_apple", () -> new DrinkColdItem("drink_soda_apple", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_BERRY_SODA = regItem("drink_soda_berry", () -> new DrinkColdItem("drink_soda_berry", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_MELON_SODA = regItem("drink_soda_melon", () -> new DrinkColdItem("drink_soda_melon", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_GRAPE_SODA = regItem("drink_soda_grape", () -> new DrinkColdItem("drink_soda_grape", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_GRAPE_WHITE_SODA = regItem("drink_soda_grape_white", () -> new DrinkColdItem("drink_soda_grape_white", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_PLUM_SODA = regItem("drink_soda_plum", () -> new DrinkColdItem("drink_soda_plum", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_PEACH_SODA = regItem("drink_soda_peach", () -> new DrinkColdItem("drink_soda_peach", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_CITRUS_SODA = regItem("drink_soda_citrus", () -> new DrinkColdItem("drink_soda_citrus", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_MANGO_SODA = regItem("drink_soda_mango", () -> new DrinkColdItem("drink_soda_mango", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));
	public static final RegistryObject<Item> DRINK_GUAVA_SODA = regItem("drink_soda_guava", () -> new DrinkColdItem("drink_soda_guava", 4, 0F, 5, TagDC.ItemTag.HAC_DRINK_COLD));

	public static final RegistryObject<Item> TEA_GREEN = regItem("cup_tea_green", () -> new DrinkCupItem("cup_tea_green", 4, 0F, 1, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_BLUE = regItem("cup_tea_blue", () -> new DrinkCupItem("cup_tea_blue", 4, 0F, 1, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_BLACK = regItem("cup_tea_black", () -> new DrinkCupItem("cup_tea_black", 4, 0F, 1, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_BLACK_LEMON = regItem("cup_tea_lemon", () -> new DrinkCupItem("cup_tea_lemon", 4, 0F, 3, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_APPLE = regItem("cup_tea_apple", () -> new DrinkCupItem("cup_tea_apple", 4, 0F, 3, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_BERRY = regItem("cup_tea_berry", () -> new DrinkCupItem("cup_tea_berry", 4, 0F, 3, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_CHAI = regItem("cup_tea_chai", () -> new DrinkCupItem("cup_tea_chai", 4, 0F, 4, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_COCOA = regItem("cup_cocoa", () -> new DrinkCupItem("cup_cocoa", 4, 0F, 1, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_MINT = regItem("cup_mint", () -> new DrinkCupItem("cup_mint", 4, 0F, 5, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_MALLOW = regItem("cup_mallow", () -> new DrinkCupItem("cup_mallow", 4, 0F, 6, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_MALLOW_LEMON = regItem("cup_mallow_lemon", () -> new DrinkCupItem("cup_mallow_lemon", 6, 0F, 6, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_SAFFRON = regItem("cup_saffron", () -> new DrinkCupItem("cup_saffron", 4, 0F, 7, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_ROSEHIP = regItem("cup_rosehip", () -> new DrinkCupItem("cup_rosehip", 4, 0F, 8, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_HIBISCUS = regItem("cup_hibiscus", () -> new DrinkCupItem("cup_hibiscus", 4, 0F, 8, false, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_GREEN_MILK = regItem("cup_tea_green_milk", () -> new DrinkCupItem("cup_tea_green_milk", 4, 0F, 1, true, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_BLUE_MILK = regItem("cup_tea_blue_milk", () -> new DrinkCupItem("cup_tea_blue_milk", 4, 0F, 1, true, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_BLACK_MILK = regItem("cup_tea_black_milk", () -> new DrinkCupItem("cup_tea_black_milk", 4, 0F, 1, true, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_APPLE_MILK = regItem("cup_tea_apple_milk", () -> new DrinkCupItem("cup_tea_apple_milk", 4, 0F, 3, true, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_BERRY_MILK = regItem("cup_tea_berry_milk", () -> new DrinkCupItem("cup_tea_berry_milk", 4, 0F, 3, true, TagDC.ItemTag.HAC_DRINK_HOT));
	public static final RegistryObject<Item> TEA_COCOA_MILK = regItem("cup_cocoa_milk", () -> new DrinkCupItem("cup_cocoa_milk", 4, 0F, 1, true, TagDC.ItemTag.HAC_DRINK_HOT));

	public static final RegistryObject<Item> BOTTLE_BEER = regItem("bottle_beer", () -> new BottleBeerItem("bottle_beer", 4, 0F, TagDC.ItemTag.HAC_LIQUOR));
	public static final RegistryObject<Item> BOTTLE_SAKE = regItem("bottle_sake", () -> new BottleBeerItem("bottle_sake", 4, 0F, TagDC.ItemTag.HAC_LIQUOR));
	public static final RegistryObject<Item> BOTTLE_WINE = regItem("bottle_wine", () -> new BottleWineItem("bottle_wine", 4, 0F, TagDC.ItemTag.HAC_LIQUOR));
	public static final RegistryObject<Item> BOTTLE_WINE_WHITE = regItem("bottle_wine_white", () -> new BottleWineItem("bottle_wine_white", 4, 0F, TagDC.ItemTag.HAC_LIQUOR));

	/* 食材系 */

	// mill
	public static final RegistryObject<Item> FOOD_AMARANTH = regItem("food_amaranth", () -> new FoodMaterialItemDC(FOOD, "food_amaranth", TagDC.ItemTag.DUST_AMARANTH).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_OAT = regItem("food_oat", () -> new FoodMaterialItemDC(FOOD, "food_oat", TagDC.ItemTag.DUST_OAT).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_RYE = regItem("food_rye", () -> new FoodMaterialItemDC(FOOD, "food_rye", TagDC.ItemTag.DUST_RYE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_BARLEY = regItem("food_barley", () -> new FoodMaterialItemDC(FOOD, "food_barley", TagDC.ItemTag.DUST_BARLEY).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_BUCKWHEAT = regItem("food_buckwheat", () -> new FoodMaterialItemDC(FOOD, "food_buckwheat", TagDC.ItemTag.DUST_BUCKWHEAT).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SORGHUM = regItem("food_sorghum", () -> new FoodMaterialItemDC(FOOD, "food_sorghum", TagDC.ItemTag.DUST_SORGHUM).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_MASA = regItem("food_masa", () -> new FoodMaterialItemDC(FOOD, "food_masa", TagDC.ItemTag.DUST_MASA).setDomain("food"));
	public static final RegistryObject<Item> FOOD_ZIZANIA = regItem("food_zizania", () -> new FoodMaterialItemDC(FOOD, "food_zizania", TagDC.ItemTag.DUST_ZIZANIA).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_MAKOMOTAKE = regItem("food_makomotake", () -> new FoodMaterialItemDC(FOOD, "food_makomotake", TagDC.ItemTag.MAKOMOTAKE).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_RICE = regItem("food_rice", () -> new FoodMaterialItemDC(FOOD, "food_rice", TagDC.ItemTag.DUST_RICE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_AROMA_RICE = regItem("food_aroma_rice", () -> new FoodMaterialItemDC(FOOD, "food_aroma_rice", TagDC.ItemTag.DUST_AROMA_RICE).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_WHEAT = regItem("food_wheat", () -> new FoodMaterialItemDC(FOOD, "food_wheat", TagDC.ItemTag.DUST_WHEAT).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_STARCH = regItem("food_starch", () -> new FoodMaterialItemDC(FOOD, "food_starch", TagDC.ItemTag.DUST_STARCH).setDomain("food"));
	public static final RegistryObject<Item> FOOD_AGAR = regItem("food_agar", () -> new FoodMaterialItemDC(FOOD, "food_agar", TagDC.ItemTag.AGAR).setDomain("food"));

	// meat
	public static final RegistryObject<Item> FOOD_FROG = regItem("food_raw_frog", () -> new FoodMaterialItemDC(FOOD, "food_raw_frog", TagDC.ItemTag.FROG).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_PLANT_MEAT = regItem("food_plantbase_meat", () -> new FoodMaterialItemDC(FOOD, "food_plantbase_meat", TagDC.ItemTag.RAW_PLANT_MEAT).setDomain("food"));
	public static final RegistryObject<Item> BONE_COW = regItem("bone_cow", () -> new FoodMaterialItemDC(FOOD, "bone_cow", TagDC.ItemTag.BONE_COW).setDomain("food"));
	public static final RegistryObject<Item> BONE_PIG = regItem("bone_pig", () -> new FoodMaterialItemDC(FOOD, "bone_pig", TagDC.ItemTag.BONE_PIG).setDomain("food"));
	public static final RegistryObject<Item> BONE_CHICKEN = regItem("bone_chicken", () -> new FoodMaterialItemDC(FOOD, "bone_chicken", TagDC.ItemTag.BONE_CHICKEN).setDomain("food"));
	public static final RegistryObject<Item> SKIN_PIG = regItem("skin_pig", () -> new MaterialItemDC(FOOD, "skin_pig", Tags.Items.LEATHER).setDomain("food"));

	// fish
	public static final RegistryObject<Item> FOOD_MULLET = regItem("fish_mullet", () -> new RawFishItem("fish_mullet", Rarity.COMMON, TagDC.ItemTag.MULLET).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SMELT = regItem("fish_smelt", () -> new RawFishItem("fish_smelt", Rarity.COMMON, TagDC.ItemTag.SMELT).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SARDINE = regItem("fish_sardine", () -> new RawFishItem("fish_sardine", Rarity.COMMON, TagDC.ItemTag.SARDINE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_FLATHEAD = regItem("fish_flathead", () -> new RawFishItem("fish_flathead", Rarity.COMMON, TagDC.ItemTag.FLATHEAD).setDomain("food"));
	public static final RegistryObject<Item> FOOD_TROUT = regItem("fish_trout", () -> new RawFishItem("fish_trout", Rarity.UNCOMMON, TagDC.ItemTag.TROUT).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CARP = regItem("fish_carp", () -> new RawFishItem("fish_carp", Rarity.UNCOMMON, TagDC.ItemTag.CARP).setDomain("food"));
	public static final RegistryObject<Item> FOOD_MACKEREL = regItem("fish_mackerel", () -> new RawFishItem("fish_mackerel", Rarity.UNCOMMON, TagDC.ItemTag.MACKEREL).taste(2).setDomain("food"));
	public static final RegistryObject<Item> FOOD_ROCKFISH = regItem("fish_rockfish", () -> new RawFishItem("fish_rockfish", Rarity.UNCOMMON, TagDC.ItemTag.ROCKFISH).setDomain("food"));
	public static final RegistryObject<Item> FOOD_GURNARD = regItem("fish_gurnard", () -> new RawFishItem("fish_gurnard", Rarity.UNCOMMON, TagDC.ItemTag.GURNARD).taste(2).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SEABREAM = regItem("fish_seabream", () -> new RawFishItem("fish_seabream", Rarity.RARE, TagDC.ItemTag.SEABREAM).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_FLOUNDER = regItem("fish_flounder", () -> new RawFishItem("fish_flounder", Rarity.RARE, TagDC.ItemTag.FLOUNDER).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_GROUPER = regItem("fish_grouper", () -> new RawFishItem("fish_grouper", Rarity.RARE, TagDC.ItemTag.GROUPER).taste(2).setDomain("food"));
	public static final RegistryObject<Item> FOOD_TUNA = regItem("fish_tuna", () -> new RawFishItem("fish_tuna", Rarity.RARE, TagDC.ItemTag.TUNA).taste(2).setDomain("food"));
	public static final RegistryObject<Item> FOOD_KRILL = regItem("fish_krill", () -> new RawFishItem("fish_krill", Rarity.COMMON, TagDC.ItemTag.KRILL).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_PRAWN = regItem("fish_prawn", () -> new RawFishItem("fish_prawn", Rarity.COMMON, TagDC.ItemTag.PRAWN).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CRAB = regItem("fish_crab", () -> new RawFishItem("fish_crab", Rarity.UNCOMMON, TagDC.ItemTag.CRAB).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SQUID = regItem("fish_squid", () -> new RawFishItem("fish_squid", Rarity.COMMON, TagDC.ItemTag.SQUID).taste(1).setDomain("food"));

	public static final RegistryObject<Item> FOOD_ROE = regItem("food_raw_roe", () -> new FoodMaterialItemDC(FOOD, "food_raw_roe", TagDC.ItemTag.ROE).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_FISH_POWDER = regItem("dust_fish_powder", () -> new FoodMaterialItemDC(FOOD, "dust_fish_powder", TagDC.ItemTag.FISH_POWDER).setDomain("food"));

	// dairy
	public static final RegistryObject<Item> FOOD_BUTTER = regItem("food_butter", () -> new FoodMaterialItemDC(FOOD, "food_butter", TagDC.ItemTag.BUTTER).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_MARGARINE = regItem("food_margarine", () -> new FoodMaterialItemDC(FOOD, "food_margarine", TagDC.ItemTag.MARGARINE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CHEESE = regItem("food_cheese", () -> new FoodMaterialItemDC(FOOD, "food_cheese", TagDC.ItemTag.CHEESE).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_OFFAL = regItem("food_offal", () -> new FoodMaterialItemDC(FOOD, "food_offal", TagDC.ItemTag.OFFAL).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_GELATINE = regItem("food_gelatine", () -> new FoodMaterialItemDC(FOOD, "food_gelatine", TagDC.ItemTag.GELATINE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_RENNET = regItem("food_rennet", () -> new FoodMaterialItemDC(FOOD, "food_rennet", TagDC.ItemTag.RENNET).setDomain("food"));

	// processed
	public static final RegistryObject<Item> NOODLE_WHEAT = regItem("food_noodle_wheat", () -> new FoodMaterialItemDC(FOOD, "food_noodle_wheat", TagDC.ItemTag.PASTA).setDomain("food"));
	public static final RegistryObject<Item> NOODLE_BRINE = regItem("food_noodle_brine", () -> new FoodMaterialItemDC(FOOD, "food_noodle_brine", TagDC.ItemTag.NOODLE).setDomain("food"));
	public static final RegistryObject<Item> NOODLE_BUCKWHEAT = regItem("food_noodle_buckwheat", () -> new FoodMaterialItemDC(FOOD, "food_noodle_buckwheat", TagDC.ItemTag.NOODLE).setDomain("food"));
	public static final RegistryObject<Item> NOODLE_RICE = regItem("food_noodle_rice", () -> new FoodMaterialItemDC(FOOD, "food_noodle_rice", TagDC.ItemTag.NOODLE).setDomain("food"));

	public static final RegistryObject<Item> FOOD_PASTRY = regItem("food_pastry", () -> new FoodMaterialItemDC(FOOD, "food_pastry", TagDC.ItemTag.PASTRY).setDomain("food"));

	public static final RegistryObject<Item> FOOD_JAM = regItem("food_fruit_jam", () -> new FoodMaterialItemDC(FOOD, "food_fruit_jam", TagDC.ItemTag.JAM).setDomain("food"));
	public static final RegistryObject<Item> FOOD_MARMALADE = regItem("food_marmalade", () -> new FoodMaterialItemDC(FOOD, "food_marmalade", TagDC.ItemTag.MARMALADE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CUSTARD = regItem("food_custard", () -> new FoodMaterialItemDC(FOOD, "food_custard", TagDC.ItemTag.CUSTARD).setDomain("food"));
	public static final RegistryObject<Item> FOOD_BOLOGNESE_SAUCE = regItem("food_bolognese_sauce", () -> new FoodMaterialItemDC(FOOD, "food_bolognese_sauce", null).setDomain("food"));
	public static final RegistryObject<Item> FOOD_BECHAMEL_SAUCE = regItem("food_bechamel_sauce", () -> new FoodMaterialItemDC(FOOD, "food_bechamel_sauce", null).setDomain("food"));
	public static final RegistryObject<Item> FOOD_YOGULT = regItem("food_yogult", () -> new EdibleMaterialItem("food_yogult", 4, 0.2F, TagDC.ItemTag.YOGULT));
	public static final RegistryObject<Item> FOOD_ANKO = regItem("food_anko", () -> new FoodMaterialItemDC(FOOD, "food_anko", TagDC.ItemTag.ANKO).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SAUERKRAUT = regItem("food_sauerkraut", () -> new EdibleMaterialItem("food_sauerkraut", 2, 0.3F, null));
	public static final RegistryObject<Item> FOOD_TOFU = regItem("food_tofu", () -> new FoodMaterialItemDC(FOOD, "food_tofu", TagDC.ItemTag.TOFU).setDomain("food"));
	public static final RegistryObject<Item> FOOD_UMEBOSHI = regItem("food_umeboshi", () -> new EdibleMaterialItem("food_umeboshi", 2, 0.3F, null));
	public static final RegistryObject<Item> FOOD_TSUKEMONO = regItem("food_tsukemono", () -> new EdibleMaterialItem("food_tsukemono", 4, 0.2F, null));
	public static final RegistryObject<Item> FOOD_KIMCHI = regItem("food_kimchi", () -> new EdibleMaterialItem("food_kimchi", 4, 0.2F, null));
	public static final RegistryObject<Item> FOOD_MALT = regItem("food_malt", () -> new FoodMaterialItemDC(FOOD, "food_malt", null).setDomain("food"));
	public static final RegistryObject<Item> FOOD_KOJI = regItem("food_koji", () -> new FoodMaterialItemDC(FOOD, "food_koji", null).setDomain("food"));

	public static final RegistryObject<Item> GREEN_TEA_LEAVES = regItem("food_tea_green", () -> new FoodMaterialItemDC(FOOD, "food_tea_green", TagDC.ItemTag.TEA_LEAVES_GREEN).setDomain("food"));
	public static final RegistryObject<Item> OOLONG_TEA_LEAVES = regItem("food_tea_oolong", () -> new FoodMaterialItemDC(FOOD, "food_tea_oolong", TagDC.ItemTag.TEA_LEAVES_OOLONG).setDomain("food"));
	public static final RegistryObject<Item> BLACK_TEA_LEAVES = regItem("food_tea_black", () -> new FoodMaterialItemDC(FOOD, "food_tea_black", TagDC.ItemTag.TEA_LEAVES_BLACK).setDomain("food"));

	public static final RegistryObject<Item> RAW_SAUSAGE = regItem("food_sausage_raw", () -> new FoodMaterialItemDC(FOOD, "food_sausage_raw", TagDC.ItemTag.RAW_SAUSAGE).setDomain("food"));
	public static final RegistryObject<Item> SMOKED_SAUSAGE = regItem("food_sausage_smoked", () -> new FoodMaterialItemDC(FOOD, "food_sausage_smoked", TagDC.ItemTag.COOKED_SAUSAGE).setDomain("food"));

	public static final RegistryObject<Item> BASESOUP_VEGI = regItem("food_basesoup_vegi", () -> new FoodMaterialItemDC(FOOD, "food_basesoup_vegi", TagDC.ItemTag.BASESOUP).setDomain("food"));
	public static final RegistryObject<Item> BASESOUP_BEEF = regItem("food_basesoup_beef", () -> new FoodMaterialItemDC(FOOD, "food_basesoup_beef", TagDC.ItemTag.BASESOUP).setDomain("food"));
	public static final RegistryObject<Item> BASESOUP_PORK = regItem("food_basesoup_pork", () -> new FoodMaterialItemDC(FOOD, "food_basesoup_pork", TagDC.ItemTag.BASESOUP).setDomain("food"));
	public static final RegistryObject<Item> BASESOUP_CHICKEN = regItem("food_basesoup_chicken", () -> new FoodMaterialItemDC(FOOD, "food_basesoup_chicken", TagDC.ItemTag.BASESOUP).setDomain("food"));
	public static final RegistryObject<Item> BASESOUP = regItem("food_basesoup", () -> new FoodMaterialItemDC(FOOD, "food_basesoup", TagDC.ItemTag.BASESOUP).setDomain("food"));

	public static final RegistryObject<Item> FOOD_FISH_SAUSE = regItem("food_fish_sauce", () -> new FoodMaterialItemDC(FOOD, "food_fish_sauce", null).setDomain("food"));
	public static final RegistryObject<Item> FOOD_ANCHOVY = regItem("food_anchovy", () -> new FoodMaterialItemDC(FOOD, "food_anchovy", null).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SHRIMP_PASTE = regItem("food_shrimp_paste", () -> new FoodMaterialItemDC(FOOD, "food_shrimp_paste", null).setDomain("food"));
	public static final RegistryObject<Item> FOOD_TOMYUM_PASTE = regItem("food_tomyum_paste", () -> new FoodMaterialItemDC(FOOD, "food_tomyum_paste", null).setDomain("food"));
	public static final RegistryObject<Item> FOOD_THAI_CURRY_PASTE = regItem("food_thai_curry_paste", () -> new FoodMaterialItemDC(FOOD, "food_thai_curry_paste", null).setDomain("food"));

	// seasoning
	public static final RegistryObject<Item> FOOD_SOYSAUCE = regItem("food_soysauce", () -> new FoodMaterialItemDC(FOOD, "food_soysauce", TagDC.ItemTag.SOYSAUCE).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_MISO = regItem("food_miso", () -> new FoodMaterialItemDC(FOOD, "food_miso", TagDC.ItemTag.MISO).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_HERB_SALT = regItem("food_herb_salt", () -> new FoodMaterialItemDC(FOOD, "food_herb_salt", TagDC.ItemTag.HERB_SALT).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_MIXED_SPICES = regItem("food_mixed_spices", () -> new FoodMaterialItemDC(FOOD, "food_mixed_spices", TagDC.ItemTag.MIXED_SPICES).taste(2).seasoning().setDomain(
		"food"));
	public static final RegistryObject<Item> FOOD_KETCHUP = regItem("food_ketchup", () -> new FoodMaterialItemDC(FOOD, "food_ketchup", TagDC.ItemTag.KETCHUP).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_MAYONNAISE = regItem("food_mayonnaise", () -> new FoodMaterialItemDC(FOOD, "food_mayonnaise", TagDC.ItemTag.MAYONNAISE).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_HOT_SAUSE = regItem("food_hotsauce", () -> new FoodMaterialItemDC(FOOD, "food_hotsauce", TagDC.ItemTag.HOT_SAUSE).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_VINEGER = regItem("food_vinegar", () -> new FoodMaterialItemDC(FOOD, "food_vinegar", TagDC.ItemTag.VINEGAR).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_HUMMUS = regItem("food_sauce_hummus", () -> new FoodMaterialItemDC(FOOD, "food_sauce_hummus", TagDC.ItemTag.HUMMUS).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_SALSA = regItem("food_sauce_salsa", () -> new FoodMaterialItemDC(FOOD, "food_sauce_salsa", TagDC.ItemTag.SALSA).taste(2).seasoning().setDomain("food"));

	// pack
	public static final RegistryObject<Item> FOOD_MILK = regItem("pack_milk", () -> new FoodMaterialItemDC(FOOD, "pack_milk", TagDC.ItemTag.COW_MILK).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SOY_MILK = regItem("pack_soy_milk", () -> new FoodMaterialItemDC(FOOD, "pack_soy_milk", TagDC.ItemTag.SOY_MILK).setDomain("food"));
	public static final RegistryObject<Item> FOOD_COCONUT_MILK = regItem("pack_coconut_milk", () -> new FoodMaterialItemDC(FOOD, "pack_coconut_milk", TagDC.ItemTag.COCONUT_MILK).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CREAM = regItem("pack_cream", () -> new FoodMaterialItemDC(FOOD, "pack_cream", TagDC.ItemTag.CREAM).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_WHIP = regItem("pack_whip", () -> new FoodMaterialItemDC(FOOD, "pack_whip", TagDC.ItemTag.WHIP).setDomain("food"));
	public static final RegistryObject<Item> FOOD_HONEY = regItem("pack_honey", () -> new FoodMaterialItemDC(FOOD, "pack_honey", TagDC.ItemTag.HONEY).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_SYRUP = regItem("pack_syrup", () -> new FoodMaterialItemDC(FOOD, "pack_syrup", TagDC.ItemTag.SYRUP).taste(1).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_PLANT_OIL = regItem("pack_plant_oil", () -> new FoodMaterialItemDC(FOOD, "pack_plant_oil", TagDC.ItemTag.PLANT_OIL).setDomain("food"));
	public static final RegistryObject<Item> FOOD_WATER = regItem("pack_water", () -> new FoodMaterialItemDC(FOOD, "pack_water", TagDC.ItemTag.WATER).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SPARKLING = regItem("pack_sparkling", () -> new FoodMaterialItemDC(FOOD, "pack_sparkling", TagDC.ItemTag.SPARKLING).setDomain("food"));
	public static final RegistryObject<Item> FOOD_EMPTY_PACK = regItem("pack_empty", () -> new EmptyPackItem(FOOD, "pack_empty", TagDC.ItemTag.EMPTY_PACK).setDomain("food"));

	// agri
	public static final RegistryObject<Item> SORGHUM_STICK = regItem("stick_sorghum", () -> new MaterialItemDC(AGRI, "stick_sorghum", TagDC.ItemTag.STICK_SORGHUM).setDomain("food"));
	public static final RegistryObject<Item> FOOD_PALM_FLOWER = regItem("food_palm_flower", () -> new FoodMaterialItemDC(AGRI, "food_palm_flower", TagDC.ItemTag.PALM_FLOWER).setDomain("food"));
	public static final RegistryObject<Item> MALLOW_CALYCES = regItem("food_mallow_calyces", () -> new FoodMaterialItemDC(AGRI, "food_mallow_calyces", TagDC.ItemTag.MALLOW_CALYCES).taste(1).setDomain("food"));
	public static final RegistryObject<Item> CURED_VANILLA = regItem("food_vanilla_cured", () -> new FoodMaterialItemDC(AGRI, "food_vanilla_cured", TagDC.ItemTag.VANILLA_CURED).taste(2).setDomain("food"));
	public static final RegistryObject<Item> CASHEW_NUTS = regItem("food_cashew_nuts", () -> new FoodMaterialItemDC(AGRI, "food_cashew_nuts", TagDC.ItemTag.CASHEW_NUTS).taste(1).setDomain("food"));
	public static final RegistryObject<Item> SPROUT = regItem("food_sprout", () -> new FoodMaterialItemDC(AGRI, "food_sprout", TagDC.ItemTag.SPROUT).setDomain("food"));

	public static final RegistryObject<Item> VINE = regItem("food_vine", () -> new MaterialItemDC(AGRI, "food_vine", TagDC.ItemTag.VINE).setDomain("food"));
	public static final RegistryObject<Item> FIBER_PLANT = regItem("fiber_plant", () -> new MaterialItemDC(AGRI, "fiber_plant", TagDC.ItemTag.FIBER_PLANT).setDomain("food"));
	public static final RegistryObject<Item> FIBER_WOOD = regItem("fiber_wood", () -> new MaterialItemDC(AGRI, "fiber_wood", TagDC.ItemTag.FIBER_WOOD).setDomain("food"));
	public static final RegistryObject<Item> BEESWAX = regItem("food_beeswax", () -> new MaterialItemDC(AGRI, "food_beeswax", TagDC.ItemTag.FOOD_WAX).setDomain("food"));
	public static final RegistryObject<Item> TREEWAX = regItem("food_treewax", () -> new MaterialItemDC(AGRI, "food_treewax", TagDC.ItemTag.FOOD_WAX).setDomain("food"));
	public static final RegistryObject<Item> CAMPHOR = regItem("food_camphor", () -> new MaterialItemDC(AGRI, "food_camphor", TagDC.ItemTag.CAMPHOR).setDomain("food"));

	public static final RegistryObject<Item> SAP_SWEET = regItem("sap_sweet", () -> new FoodMaterialItemDC(AGRI, "sap_sweet", TagDC.ItemTag.SAP_SWEET).setDomain("food"));
	public static final RegistryObject<Item> SAP_RESIN = regItem("sap_resin", () -> new MaterialItemDC(AGRI, "sap_resin", TagDC.ItemTag.SAP_RESIN).setDomain("food"));
	public static final RegistryObject<Item> SAP_LATEX = regItem("sap_latex", () -> new MaterialItemDC(AGRI, "sap_latex", TagDC.ItemTag.SAP_LATEX).setDomain("food"));
	public static final RegistryObject<Item> SAP_SUMAC = regItem("sap_sumac", () -> new MaterialItemDC(AGRI, "sap_sumac", TagDC.ItemTag.SAP_LACQUER).setDomain("food"));

	// clothes
	public static final RegistryObject<Item> STRING_PLANT = regItem("string_plant", () -> new MaterialItemDC(CoreInit.CLOTH, "string_plant", TagDC.ItemTag.STRING_PLANT).setDomain("food"));
	public static final RegistryObject<Item> STRING_TREE = regItem("string_tree", () -> new MaterialItemDC(CoreInit.CLOTH, "string_tree", TagDC.ItemTag.STRING_PLANT).setDomain("food"));
	public static final RegistryObject<Item> STRING_COTTON = regItem("string_cotton", () -> new MaterialItemDC(CoreInit.CLOTH, "string_cotton", TagDC.ItemTag.STRING_COTTON).setDomain("food"));
	public static final RegistryObject<Item> STRING_WOOL = regItem("string_wool", () -> new MaterialItemDC(CoreInit.CLOTH, "string_wool", TagDC.ItemTag.STRING_WOOL).setDomain("food"));

	public static final RegistryObject<Item> CLOTH_PLANT = regItem("cloth_plant", () -> new MaterialItemDC(CoreInit.CLOTH, "cloth_plant", TagDC.ItemTag.CLOTH_PLANT).setDomain("food"));
	public static final RegistryObject<Item> CLOTH_TREE = regItem("cloth_tree", () -> new MaterialItemDC(CoreInit.CLOTH, "cloth_tree", TagDC.ItemTag.CLOTH_PLANT).setDomain("food"));
	public static final RegistryObject<Item> CLOTH_COTTON = regItem("cloth_cotton", () -> new MaterialItemDC(CoreInit.CLOTH, "cloth_cotton", TagDC.ItemTag.CLOTH_COTTON).setDomain("food"));
	public static final RegistryObject<Item> CLOTH_WOOL = regItem("cloth_wool", () -> new MaterialItemDC(CoreInit.CLOTH, "cloth_wool", TagDC.ItemTag.CLOTH_WOOL).setDomain("food"));

	// feed
	public static final RegistryObject<Item> FOOD_DEFATTED_SOY = regItem("dust_defatted_soy", () -> new MaterialItemDC(AGRI, "dust_defatted_soy", TagDC.ItemTag.DEFATTED_SOY).setDomain("food"));
	public static final RegistryObject<Item> FOOD_PRESS_CAKE = regItem("dust_press_cake", () -> new MaterialItemDC(AGRI, "dust_press_cake", TagDC.ItemTag.PRESS_CAKE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_BRAN = regItem("dust_bran", () -> new MaterialItemDC(AGRI, "dust_bran", TagDC.ItemTag.BRAN).setDomain("food"));
	public static final RegistryObject<Item> FOOD_BAGASSE = regItem("dust_bagasse", () -> new MaterialItemDC(AGRI, "dust_bagasse", TagDC.ItemTag.BAGASSE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_LEAF_MOLD = regItem("dust_leaf_mold", () -> new MaterialItemDC(AGRI, "dust_leaf_mold", TagDC.ItemTag.LEAF_MOLD).setDomain("food"));

	public static final RegistryObject<Item> FEED_HAY = regItem("animalfeed_hay", () -> new MaterialItemDC(AGRI, "animalfeed_hay", TagDC.ItemTag.FEED_HAY).setDomain("food"));
	public static final RegistryObject<Item> FEED_STRAW = regItem("animalfeed_straw", () -> new MaterialItemDC(AGRI, "animalfeed_straw", TagDC.ItemTag.FEED_STRAW).setDomain("food"));
	public static final RegistryObject<Item> FEED_COMPOUND = regItem("animalfeed_compound", () -> new MaterialItemDC(AGRI, "animalfeed_compound", TagDC.ItemTag.FEED_COMPOUND).setDomain("food"));

	public static final RegistryObject<Item> FERTILIZER_MIXED = regItem("fertilizer_mixed", () -> new FertilizerItemDC(AGRI, "fertilizer_mixed", TagDC.ItemTag.FERTILIZER_ADV).setDomain("food"));

	public static final RegistryObject<Item> BIOMASS_PELLET = regItem("bio_briquet_raw", () -> new MaterialItemDC(AGRI, "bio_briquet_raw", null).setDomain("food"));
	public static final RegistryObject<Item> BIOMASS_BRIQUET = regItem("bio_briquet", () -> new MaterialItemDC(AGRI, "bio_briquet", null).setDomain("food"));

	// crops
	public static final RegistryObject<Item> CROP_AL_WILD = regCrop(CropTier.WILD, CropType.ALLIUM, TagDC.ItemTag.CROP_CHIVES);
	public static final RegistryObject<Item> CROP_AL_ONION = regCrop(CropTier.COMMON, CropType.ALLIUM, TagDC.ItemTag.CROP_ONION);
	public static final RegistryObject<Item> CROP_AL_GARLIC = regCrop(CropTier.RARE, CropType.ALLIUM, TagDC.ItemTag.CROP_GARLIC);
	public static final RegistryObject<Item> CROP_AM_GOOSEFOOT = regCrop(CropTier.WILD, CropType.AMARANTH, TagDC.ItemTag.CROP_GOOSEFOOT);
	public static final RegistryObject<Item> CROP_AM_GLASSWORT = regCrop(CropTier.COMMON, CropType.AMARANTH, TagDC.ItemTag.CROP_GLASSWORT);
	public static final RegistryObject<Item> CROP_AM_SPINACH = regCrop(CropTier.RARE, CropType.AMARANTH, TagDC.ItemTag.CROP_SPINACH);
	public static final RegistryObject<Item> CROP_AP_CELERY = regCrop(CropTier.WILD, CropType.APIUM, TagDC.ItemTag.CROP_CELERY, 1, 0F);
	public static final RegistryObject<Item> CROP_AP_FENNEL = regCrop(CropTier.COMMON, CropType.APIUM, TagDC.ItemTag.CROP_FENNEL);
	public static final RegistryObject<Item> CROP_AP_PARSNIP = regCrop(CropTier.RARE, CropType.APIUM, TagDC.ItemTag.CROP_PARSNIP);
	public static final RegistryObject<Item> CROP_AP_CORIANDER = regCrop(CropTier.EPIC, CropType.APIUM, TagDC.ItemTag.CROP_CORIANDER);
	public static final RegistryObject<Item> CROP_AS_ARTEMISIA = regCrop(CropTier.WILD, CropType.ASTER, TagDC.ItemTag.CROP_ARTEMISIA);
	public static final RegistryObject<Item> CROP_AS_LETTUCE = regCrop(CropTier.COMMON, CropType.ASTER, TagDC.ItemTag.CROP_LETTUCE);
	public static final RegistryObject<Item> CROP_AS_PYRETHRUM = regPoisonCrop(CropTier.RARE, CropType.ASTER, TagDC.ItemTag.CROP_PYRETHRUM);
	public static final RegistryObject<Item> CROP_AS_FLOWER = regCrop(CropTier.EPIC, CropType.ASTER, TagDC.ItemTag.CROP_CHRYSANTHEMUM, -1);
	public static final RegistryObject<Item> CROP_BR_RAPESEED = regCrop(CropTier.WILD, CropType.BRASSICA, TagDC.ItemTag.CROP_RAPESEED);
	public static final RegistryObject<Item> CROP_BR_GREEN = regCrop(CropTier.COMMON, CropType.BRASSICA, TagDC.ItemTag.CROP_NAPA);
	public static final RegistryObject<Item> CROP_BR_CABBAGE = regCrop(CropTier.RARE, CropType.BRASSICA, TagDC.ItemTag.CROP_CABBAGE, 1, 0F);
	public static final RegistryObject<Item> CROP_BR_RADISH = regCrop(CropTier.EPIC, CropType.BRASSICA, TagDC.ItemTag.CROP_RADISH);
	public static final RegistryObject<Item> CROP_CA_CHILI = regCrop(CropTier.WILD, CropType.CAPSICUM, TagDC.ItemTag.CROP_CHILI);
	public static final RegistryObject<Item> CROP_CA_BELL = regCrop(CropTier.COMMON, CropType.CAPSICUM, TagDC.ItemTag.CROP_BELL);
	public static final RegistryObject<Item> CROP_CA_PAPRIKA = regCrop(CropTier.RARE, CropType.CAPSICUM, TagDC.ItemTag.CROP_PAPRIKA, 1, 0F);
	public static final RegistryObject<Item> CROP_CR_OAT = regCrop(CropTier.WILD, CropType.CEREALS, TagDC.ItemTag.CROP_OAT);
	public static final RegistryObject<Item> CROP_CR_RYE = regCrop(CropTier.COMMON, CropType.CEREALS, TagDC.ItemTag.CROP_RYE);
	public static final RegistryObject<Item> CROP_CR_BARLEY = regCrop(CropTier.RARE, CropType.CEREALS, TagDC.ItemTag.CROP_BARLEY);
	public static final RegistryObject<Item> CROP_GN_COMMON = regCrop(CropTier.WILD, CropType.GINGER, TagDC.ItemTag.CROP_GINGER);
	public static final RegistryObject<Item> CROP_GN_CARDAMOM = regCrop(CropTier.COMMON, CropType.GINGER, TagDC.ItemTag.CROP_CARDAMOM);
	public static final RegistryObject<Item> CROP_GN_TURMERIC = regCrop(CropTier.RARE, CropType.GINGER, TagDC.ItemTag.CROP_TURMERIC);
	public static final RegistryObject<Item> CROP_GO_CALABASH = regCrop(CropTier.WILD, CropType.GOURD, TagDC.ItemTag.CROP_CALABASH);
	public static final RegistryObject<Item> CROP_GO_CUCUMBER = regCrop(CropTier.COMMON, CropType.GOURD, TagDC.ItemTag.CROP_CUCUMBER, 1, 0F);
	public static final RegistryObject<Item> CROP_GO_CANTALOUP = regCrop(CropTier.RARE, CropType.GOURD, TagDC.ItemTag.CROP_CANTALOUP, 2, 0F);
	public static final RegistryObject<Item> CROP_GR_WILD = regCrop(CropTier.WILD, CropType.GRAPE, TagDC.ItemTag.CROP_WILD_GRAPE, 1, 0F);
	public static final RegistryObject<Item> CROP_GR_COMMON = regCrop(CropTier.COMMON, CropType.GRAPE, TagDC.ItemTag.CROP_RED_GRAPE, 2, 0F);
	public static final RegistryObject<Item> CROP_GR_WHITE = regCrop(CropTier.RARE, CropType.GRAPE, TagDC.ItemTag.CROP_WHITE_GRAPE, 2, 0F);
	public static final RegistryObject<Item> CROP_HB_MINT = regCrop(CropTier.WILD, CropType.HERB, TagDC.ItemTag.CROP_MINT);
	public static final RegistryObject<Item> CROP_HB_BASIL = regCrop(CropTier.COMMON, CropType.HERB, TagDC.ItemTag.CROP_BASIL);
	public static final RegistryObject<Item> CROP_HB_PERILLA = regCrop(CropTier.RARE, CropType.HERB, TagDC.ItemTag.CROP_PERILLA);
	public static final RegistryObject<Item> CROP_HB_LAVENDER = regCrop(CropTier.EPIC, CropType.HERB, TagDC.ItemTag.CROP_LAVENDER);
	public static final RegistryObject<Item> CROP_IR_CROCUS = regCrop(CropTier.WILD, CropType.IRIS, TagDC.ItemTag.CROP_CROCUS);
	public static final RegistryObject<Item> CROP_IR_SAFFRON = regCrop(CropTier.COMMON, CropType.IRIS, TagDC.ItemTag.CROP_SAFFRON);
	public static final RegistryObject<Item> CROP_IR_IRIS = regPoisonCrop(CropTier.RARE, CropType.IRIS, TagDC.ItemTag.CROP_IRIS);
	public static final RegistryObject<Item> CROP_KN_SORREL = regCrop(CropTier.WILD, CropType.KNOTWEED, TagDC.ItemTag.CROP_SORREL);
	public static final RegistryObject<Item> CROP_KN_BUCKWHEAT = regCrop(CropTier.COMMON, CropType.KNOTWEED, TagDC.ItemTag.CROP_BUCKWHEAT);
	public static final RegistryObject<Item> CROP_KN_INDIGO = regCrop(CropTier.RARE, CropType.KNOTWEED, TagDC.ItemTag.CROP_INDIGO, 0);
	public static final RegistryObject<Item> CROP_ML_JUTE = regCrop(CropTier.WILD, CropType.MALLOW, TagDC.ItemTag.CROP_JUTE);
	public static final RegistryObject<Item> CROP_ML_COTTON = regCrop(CropTier.COMMON, CropType.MALLOW, TagDC.ItemTag.CROP_COTTON);
	public static final RegistryObject<Item> CROP_ML_BLUE = regCrop(CropTier.RARE, CropType.MALLOW, TagDC.ItemTag.CROP_BLUE_MALLOW);
	public static final RegistryObject<Item> CROP_ML_TROPICAL = regCrop(CropTier.EPIC, CropType.MALLOW, TagDC.ItemTag.CROP_TROPICAL);
	public static final RegistryObject<Item> CROP_MO_BINDWEED = regCrop(CropTier.WILD, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_BINDWEED);
	public static final RegistryObject<Item> CROP_MO_WATER = regCrop(CropTier.COMMON, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_WATER_SPINACH, 1, 0F);
	public static final RegistryObject<Item> CROP_MO_POTATO = regCrop(CropTier.RARE, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_SWEET_POTATO);
	public static final RegistryObject<Item> CROP_MO_FLOWER = regPoisonCrop(CropTier.EPIC, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_MORNING_GLORY);
	public static final RegistryObject<Item> CROP_OR_SPIRANTHES = regCrop(CropTier.WILD, CropType.ORCHID, TagDC.ItemTag.CROP_SPIRANTHES);
	public static final RegistryObject<Item> CROP_OR_CYMBIDIUM = regPoisonCrop(CropTier.COMMON, CropType.ORCHID, TagDC.ItemTag.CROP_CYMBIDIUM);
	public static final RegistryObject<Item> CROP_OR_VANILLA = regCrop(CropTier.RARE, CropType.ORCHID, TagDC.ItemTag.CROP_VANILLA);
	public static final RegistryObject<Item> CROP_OR_CATTLEYA = regPoisonCrop(CropTier.EPIC, CropType.ORCHID, TagDC.ItemTag.CROP_CATTLEYA);
	public static final RegistryObject<Item> CROP_PD_ROGERIA = regCrop(CropTier.WILD, CropType.PEDALIA, TagDC.ItemTag.CROP_ROGERIA);
	public static final RegistryObject<Item> CROP_PD_SESAMI = regCrop(CropTier.COMMON, CropType.PEDALIA, TagDC.ItemTag.CROP_SESAMI);
	public static final RegistryObject<Item> CROP_PD_DEVILSCLAW = regCrop(CropTier.RARE, CropType.PEDALIA, TagDC.ItemTag.CROP_DEVILSCLAW, 0);
	public static final RegistryObject<Item> CROP_PE_GREEN = regCrop(CropTier.WILD, CropType.PEAS, TagDC.ItemTag.CROP_GREEN_PEAS);
	public static final RegistryObject<Item> CROP_PE_GARBANZO = regCrop(CropTier.COMMON, CropType.PEAS, TagDC.ItemTag.CROP_GARBANZO);
	public static final RegistryObject<Item> CROP_PE_SOY = regCrop(CropTier.RARE, CropType.PEAS, TagDC.ItemTag.CROP_SOY);
	public static final RegistryObject<Item> CROP_PE_ADZUKI = regCrop(CropTier.EPIC, CropType.PEAS, TagDC.ItemTag.CROP_ADZUKI);
	public static final RegistryObject<Item> CROP_RA_ANEMONE = regCrop(CropTier.WILD, CropType.RANUNCULUS, TagDC.ItemTag.CROP_ANEMONE);
	public static final RegistryObject<Item> CROP_RA_DELPHINIUM = regPoisonCrop(CropTier.COMMON, CropType.RANUNCULUS, TagDC.ItemTag.CROP_DELPHINIUM);
	public static final RegistryObject<Item> CROP_RA_CLEMATIS = regPoisonCrop(CropTier.RARE, CropType.RANUNCULUS, TagDC.ItemTag.CROP_CLEMATIS);
	public static final RegistryObject<Item> CROP_RA_MONKSHOOD = regPoisonCrop(CropTier.EPIC, CropType.RANUNCULUS, TagDC.ItemTag.CROP_MONKSHOOD);
	public static final RegistryObject<Item> CROP_RE_COMMON = regCrop(CropTier.WILD, CropType.REED, TagDC.ItemTag.CROP_REED);
	public static final RegistryObject<Item> CROP_RE_SORGHUM = regCrop(CropTier.COMMON, CropType.REED, TagDC.ItemTag.CROP_SORGHUM);
	public static final RegistryObject<Item> CROP_RE_CORN = regCrop(CropTier.RARE, CropType.REED, TagDC.ItemTag.CROP_CORN, 1, 0F);
	public static final RegistryObject<Item> CROP_RI_ZIZANIA = regCrop(CropTier.WILD, CropType.RICE, TagDC.ItemTag.CROP_WILD_RICE);
	public static final RegistryObject<Item> CROP_RI_SHORT = regCrop(CropTier.COMMON, CropType.RICE, TagDC.ItemTag.CROP_RICE);
	public static final RegistryObject<Item> CROP_RI_AROMA = regCrop(CropTier.RARE, CropType.RICE, TagDC.ItemTag.CROP_AROMA_RICE);
	public static final RegistryObject<Item> CROP_SL_NIGHTSHADE = regPoisonCrop(CropTier.WILD, CropType.SOLANUM, TagDC.ItemTag.CROP_NIGHTSHADE);
	public static final RegistryObject<Item> CROP_SL_EGGPLANT = regCrop(CropTier.COMMON, CropType.SOLANUM, TagDC.ItemTag.CROP_EGGPLANT);
	public static final RegistryObject<Item> CROP_SL_TOMATO = regCrop(CropTier.RARE, CropType.SOLANUM, TagDC.ItemTag.CROP_TOMATO, 2, 0F);
	public static final RegistryObject<Item> CROP_SL_LANTERN = regCrop(CropTier.EPIC, CropType.SOLANUM, TagDC.ItemTag.CROP_LANTERN, 2, 0F);

	public static final RegistryObject<Item> CROP_BH_COMMON = regCrop(CropTier.WILD, CropType.BEECH, TagDC.ItemTag.CROP_BEECH);
	public static final RegistryObject<Item> CROP_BH_WALNUT = regCrop(CropTier.COMMON, CropType.BEECH, TagDC.ItemTag.CROP_WALNUT);
	public static final RegistryObject<Item> CROP_BH_SWEET = regCrop(CropTier.RARE, CropType.BEECH, TagDC.ItemTag.CROP_ACORN, 0);
	public static final RegistryObject<Item> CROP_CH_WILD = regCrop(CropTier.WILD, CropType.CHERRY, TagDC.ItemTag.CROP_CHERRY);
	public static final RegistryObject<Item> CROP_CH_PLUM = regCrop(CropTier.COMMON, CropType.CHERRY, TagDC.ItemTag.CROP_PLUM, 1, 0F);
	public static final RegistryObject<Item> CROP_CH_PEACH = regCrop(CropTier.RARE, CropType.CHERRY, TagDC.ItemTag.CROP_PEACH, 2, 0F);
	public static final RegistryObject<Item> CROP_CM_OIL = regCrop(CropTier.WILD, CropType.CAMELLIA, TagDC.ItemTag.CROP_CAMELLIA);
	public static final RegistryObject<Item> CROP_CM_SCHIMA = regPoisonCrop(CropTier.COMMON, CropType.CAMELLIA, TagDC.ItemTag.CROP_SCHIMA);
	public static final RegistryObject<Item> CROP_CM_TEA = regCrop(CropTier.RARE, CropType.CAMELLIA, TagDC.ItemTag.CROP_TEA);
	public static final RegistryObject<Item> CROP_CN_CINNAMON = regCrop(CropTier.COMMON, CropType.CINNAMON, TagDC.ItemTag.CROP_CINNAMON);
	public static final RegistryObject<Item> CROP_CN_AVOCADO = regCrop(CropTier.RARE, CropType.CINNAMON, TagDC.ItemTag.CROP_AVOCADO, 1, 0F);
	public static final RegistryObject<Item> CROP_CT_POMELO = regCrop(CropTier.WILD, CropType.CITRUS, TagDC.ItemTag.CROP_POMELO, 2, 0F);
	public static final RegistryObject<Item> CROP_CT_MANDARIN = regCrop(CropTier.COMMON, CropType.CITRUS, TagDC.ItemTag.CROP_MANDARIN, 2, 0F);
	public static final RegistryObject<Item> CROP_CT_LEMON = regCrop(CropTier.RARE, CropType.CITRUS, TagDC.ItemTag.CROP_LEMON, 1, 0F);
	public static final RegistryObject<Item> CROP_CT_PEPPER = regCrop(CropTier.EPIC, CropType.CITRUS, TagDC.ItemTag.CROP_SICHUAN_PEPPER);
	public static final RegistryObject<Item> CROP_ER_HEATH = regCrop(CropTier.WILD, CropType.ERICA, TagDC.ItemTag.CROP_HEATH);
	public static final RegistryObject<Item> CROP_ER_RHODODENDRON = regPoisonCrop(CropTier.COMMON, CropType.ERICA, TagDC.ItemTag.CROP_RHODODENDRON);
	public static final RegistryObject<Item> CROP_ER_BLUEBERRY = regCrop(CropTier.RARE, CropType.ERICA, TagDC.ItemTag.CROP_BLUEBERRY, 2, 0F);
	public static final RegistryObject<Item> CROP_MR_MULBERRY = regCrop(CropTier.WILD, CropType.MORUS, TagDC.ItemTag.CROP_MULBERRY, 1, 0F, 0);
	public static final RegistryObject<Item> CROP_MR_PAPER = regCrop(CropTier.COMMON, CropType.MORUS, TagDC.ItemTag.CROP_KAJI, 1, 0F);
	public static final RegistryObject<Item> CROP_MR_RUBBER = regPoisonCrop(CropTier.RARE, CropType.MORUS, TagDC.ItemTag.CROP_RUBBER);
	public static final RegistryObject<Item> CROP_MY_GUAVA = regCrop(CropTier.COMMON, CropType.MYRTLE, TagDC.ItemTag.CROP_GUAVA);
	public static final RegistryObject<Item> CROP_MY_CLOVE = regCrop(CropTier.RARE, CropType.MYRTLE, TagDC.ItemTag.CROP_CLOVE);
	public static final RegistryObject<Item> CROP_OL_ASH = regCrop(CropTier.WILD, CropType.OLIVE, TagDC.ItemTag.CROP_ASH);
	public static final RegistryObject<Item> CROP_OL_OLIVE = regCrop(CropTier.COMMON, CropType.OLIVE, TagDC.ItemTag.CROP_OLIVE);
	public static final RegistryObject<Item> CROP_OL_OSMANTHUS = regCrop(CropTier.RARE, CropType.OLIVE, TagDC.ItemTag.CROP_OSUMANTHUS, -1);
	public static final RegistryObject<Item> CROP_PL_COCONUT = regCrop(CropTier.WILD, CropType.PALM, TagDC.ItemTag.CROP_COCONUT, 2, 0F, 0);
	public static final RegistryObject<Item> CROP_PL_DATE = regCrop(CropTier.COMMON, CropType.PALM, TagDC.ItemTag.CROP_DATE, 2, 0F);
	public static final RegistryObject<Item> CROP_PL_OIL = regCrop(CropTier.RARE, CropType.PALM, TagDC.ItemTag.CROP_OIL_PALM, 0);
	public static final RegistryObject<Item> CROP_RO_RUGOSA = regCrop(CropTier.WILD, CropType.ROSE, TagDC.ItemTag.CROP_RUGOSA);
	public static final RegistryObject<Item> CROP_RO_RASPBERRY = regCrop(CropTier.COMMON, CropType.ROSE, TagDC.ItemTag.CROP_RASPBERRY, 1, 0F);
	public static final RegistryObject<Item> CROP_RO_DAMASCHENA = regCrop(CropTier.RARE, CropType.ROSE, TagDC.ItemTag.CROP_DAMASCHENA);
	public static final RegistryObject<Item> CROP_SU_LACQUER = regPoisonCrop(CropTier.WILD, CropType.SUMAC, TagDC.ItemTag.CROP_LACQUER);
	public static final RegistryObject<Item> CROP_SU_MANGO = regCrop(CropTier.COMMON, CropType.SUMAC, TagDC.ItemTag.CROP_MANGO, 2, 0F);
	public static final RegistryObject<Item> CROP_SU_CASHEW = regCrop(CropTier.RARE, CropType.SUMAC, TagDC.ItemTag.CROP_CASHEW, 2, 0F);
	public static final RegistryObject<Item> CROP_SU_PISTACHIO = regCrop(CropTier.EPIC, CropType.SUMAC, TagDC.ItemTag.CROP_PISTACHIO);

	public static final RegistryObject<Block> BLOCK_AL_WILD = regSeed(CropTier.WILD, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.WILD), TagDC.ItemTag.SEED_CHIVES);
	public static final RegistryObject<Block> BLOCK_AL_ONION = regSeed(CropTier.COMMON, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.COMMON), TagDC.ItemTag.SEED_ONION);
	public static final RegistryObject<Block> BLOCK_AL_GARLIC = regSeed(CropTier.RARE, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.RARE), TagDC.ItemTag.SEED_GARLIC);
	public static final RegistryObject<Block> BLOCK_AM_GOOSEFOOT = regSeed(CropTier.WILD, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.WILD), TagDC.ItemTag.SEED_GOOSEFOOT);
	public static final RegistryObject<Block> BLOCK_AM_GLASSWORT = regSeed(CropTier.COMMON, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.COMMON), TagDC.ItemTag.SEED_GLASSWORT);
	public static final RegistryObject<Block> BLOCK_AM_SPINACH = regSeed(CropTier.RARE, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.RARE), TagDC.ItemTag.SEED_SPINACH);
	public static final RegistryObject<Block> BLOCK_AP_CELERY = regSeed(CropTier.WILD, CropType.APIUM, () -> new CropBlockApium(CropTier.WILD), TagDC.ItemTag.SEED_CELERY);
	public static final RegistryObject<Block> BLOCK_AP_FENNEL = regSeed(CropTier.COMMON, CropType.APIUM, () -> new CropBlockApium(CropTier.COMMON), TagDC.ItemTag.SEED_FENNEL);
	public static final RegistryObject<Block> BLOCK_AP_PARSNIP = regSeed(CropTier.RARE, CropType.APIUM, () -> new CropBlockApium(CropTier.RARE), TagDC.ItemTag.SEED_PARSNIP);
	public static final RegistryObject<Block> BLOCK_AP_CORIANDER = regSeed(CropTier.EPIC, CropType.APIUM, () -> new CropBlockApium(CropTier.EPIC), TagDC.ItemTag.SEED_CORIANDER);
	public static final RegistryObject<Block> BLOCK_AS_ARTEMISIA = regSeed(CropTier.WILD, CropType.ASTER, () -> new CropBlockAster(CropTier.WILD), TagDC.ItemTag.SEED_ARTEMISIA);
	public static final RegistryObject<Block> BLOCK_AS_LETTUCE = regSeed(CropTier.COMMON, CropType.ASTER, () -> new CropBlockAster(CropTier.COMMON), TagDC.ItemTag.SEED_LETTUCE);
	public static final RegistryObject<Block> BLOCK_AS_PYRETHRUM = regSeed(CropTier.RARE, CropType.ASTER, () -> new CropBlockAster(CropTier.RARE), TagDC.ItemTag.SEED_PYRETHRUM);
	public static final RegistryObject<Block> BLOCK_AS_FLOWER = regSeed(CropTier.EPIC, CropType.ASTER, () -> new CropBlockAster(CropTier.EPIC), TagDC.ItemTag.SEED_CHRYSANTHEMUM);
	public static final RegistryObject<Block> BLOCK_BR_RAPESEED = regSeed(CropTier.WILD, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.WILD), TagDC.ItemTag.SEED_RAPESEED);
	public static final RegistryObject<Block> BLOCK_BR_GREEN = regSeed(CropTier.COMMON, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.COMMON), TagDC.ItemTag.SEED_NAPA);
	public static final RegistryObject<Block> BLOCK_BR_CABBAGE = regSeed(CropTier.RARE, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.RARE), TagDC.ItemTag.SEED_CABBAGE);
	public static final RegistryObject<Block> BLOCK_BR_RADISH = regSeed(CropTier.EPIC, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.EPIC), TagDC.ItemTag.SEED_RADISH);
	public static final RegistryObject<Block> BLOCK_CA_CHILI = regSeed(CropTier.WILD, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.WILD), TagDC.ItemTag.SEED_CHILI);
	public static final RegistryObject<Block> BLOCK_CA_BELL = regSeed(CropTier.COMMON, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.COMMON), TagDC.ItemTag.SEED_BELL);
	public static final RegistryObject<Block> BLOCK_CA_PAPRIKA = regSeed(CropTier.RARE, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.RARE), TagDC.ItemTag.SEED_PAPRIKA);
	public static final RegistryObject<Block> BLOCK_CR_OAT = regSeed(CropTier.WILD, CropType.CEREALS, () -> new CropBlockCereals(CropTier.WILD), TagDC.ItemTag.SEED_OAT);
	public static final RegistryObject<Block> BLOCK_CR_RYE = regSeed(CropTier.COMMON, CropType.CEREALS, () -> new CropBlockCereals(CropTier.COMMON), TagDC.ItemTag.SEED_RYE);
	public static final RegistryObject<Block> BLOCK_CR_BARLEY = regSeed(CropTier.RARE, CropType.CEREALS, () -> new CropBlockCereals(CropTier.RARE), TagDC.ItemTag.SEED_BARLEY);
	public static final RegistryObject<Block> BLOCK_GN_COMMON = regSeed(CropTier.WILD, CropType.GINGER, () -> new CropBlockGinger(CropTier.WILD), TagDC.ItemTag.SEED_GINGER);
	public static final RegistryObject<Block> BLOCK_GN_CARDAMOM = regSeed(CropTier.COMMON, CropType.GINGER, () -> new CropBlockGinger(CropTier.COMMON), TagDC.ItemTag.SEED_CARDAMOM);
	public static final RegistryObject<Block> BLOCK_GN_TURMERIC = regSeed(CropTier.RARE, CropType.GINGER, () -> new CropBlockGinger(CropTier.RARE), TagDC.ItemTag.SEED_TURMERIC);
	public static final RegistryObject<Block> BLOCK_GO_CALABASH = regSeed(CropTier.WILD, CropType.GOURD, () -> new CropBlockGourd(CropTier.WILD), TagDC.ItemTag.SEED_CALABASH);
	public static final RegistryObject<Block> BLOCK_GO_CUCUMBER = regSeed(CropTier.COMMON, CropType.GOURD, () -> new CropBlockGourd(CropTier.COMMON), TagDC.ItemTag.SEED_CUCUMBER);
	public static final RegistryObject<Block> BLOCK_GO_CANTALOUP = regSeed(CropTier.RARE, CropType.GOURD, () -> new CropBlockGourd(CropTier.RARE), TagDC.ItemTag.SEED_CANTALOUP);
	public static final RegistryObject<Block> BLOCK_GR_WILD = regSeed(CropTier.WILD, CropType.GRAPE, () -> new CropBlockGrape(CropTier.WILD), TagDC.ItemTag.SEED_WILD_GRAPE);
	public static final RegistryObject<Block> BLOCK_GR_COMMON = regSeed(CropTier.COMMON, CropType.GRAPE, () -> new CropBlockGrape(CropTier.COMMON), TagDC.ItemTag.SEED_RED_GRAPE);
	public static final RegistryObject<Block> BLOCK_GR_WHITE = regSeed(CropTier.RARE, CropType.GRAPE, () -> new CropBlockGrape(CropTier.RARE), TagDC.ItemTag.SEED_WHITE_GRAPE);
	public static final RegistryObject<Block> BLOCK_HB_MINT = regSeed(CropTier.WILD, CropType.HERB, () -> new CropBlockHerb(CropTier.WILD), TagDC.ItemTag.SEED_MINT);
	public static final RegistryObject<Block> BLOCK_HB_BASIL = regSeed(CropTier.COMMON, CropType.HERB, () -> new CropBlockHerb(CropTier.COMMON), TagDC.ItemTag.SEED_BASIL);
	public static final RegistryObject<Block> BLOCK_HB_PERILLA = regSeed(CropTier.RARE, CropType.HERB, () -> new CropBlockHerb(CropTier.RARE), TagDC.ItemTag.SEED_PERILLA);
	public static final RegistryObject<Block> BLOCK_HB_LAVENDER = regSeed(CropTier.EPIC, CropType.HERB, () -> new CropBlockHerb(CropTier.EPIC), TagDC.ItemTag.SEED_LAVENDER);
	public static final RegistryObject<Block> BLOCK_IR_CROCUS = regSeed(CropTier.WILD, CropType.IRIS, () -> new CropBlockIris(CropTier.WILD), TagDC.ItemTag.SEED_CROCUS);
	public static final RegistryObject<Block> BLOCK_IR_SAFFRON = regSeed(CropTier.COMMON, CropType.IRIS, () -> new CropBlockIris(CropTier.COMMON), TagDC.ItemTag.SEED_SAFFRON);
	public static final RegistryObject<Block> BLOCK_IR_IRIS = regSeed(CropTier.RARE, CropType.IRIS, () -> new CropBlockIris(CropTier.RARE), TagDC.ItemTag.SEED_IRIS);
	public static final RegistryObject<Block> BLOCK_KN_SORREL = regSeed(CropTier.WILD, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.WILD), TagDC.ItemTag.SEED_SORREL);
	public static final RegistryObject<Block> BLOCK_KN_BUCKWHEAT = regSeed(CropTier.COMMON, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.COMMON), TagDC.ItemTag.SEED_BUCKWHEAT);
	public static final RegistryObject<Block> BLOCK_KN_INDIGO = regSeed(CropTier.RARE, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.RARE), TagDC.ItemTag.SEED_INDIGO);
	public static final RegistryObject<Block> BLOCK_ML_JUTE = regSeed(CropTier.WILD, CropType.MALLOW, () -> new CropBlockMallow(CropTier.WILD), TagDC.ItemTag.SEED_JUTE);
	public static final RegistryObject<Block> BLOCK_ML_COTTON = regSeed(CropTier.COMMON, CropType.MALLOW, () -> new CropBlockMallow(CropTier.COMMON), TagDC.ItemTag.SEED_COTTON);
	public static final RegistryObject<Block> BLOCK_ML_BLUE = regSeed(CropTier.RARE, CropType.MALLOW, () -> new CropBlockMallow(CropTier.RARE), TagDC.ItemTag.SEED_BLUE_MALLOW);
	public static final RegistryObject<Block> BLOCK_ML_TROPICAL = regSeed(CropTier.EPIC, CropType.MALLOW, () -> new CropBlockMallow(CropTier.EPIC), TagDC.ItemTag.SEED_TROPICAL);
	public static final RegistryObject<Block> BLOCK_MO_BINDWEED = regSeed(CropTier.WILD, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.WILD), TagDC.ItemTag.SEED_BINDWEED);
	public static final RegistryObject<Block> BLOCK_MO_WATER = regSeed(CropTier.COMMON, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.COMMON), TagDC.ItemTag.SEED_WATER_SPINACH);
	public static final RegistryObject<Block> BLOCK_MO_POTATO = regSeed(CropTier.RARE, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.RARE), TagDC.ItemTag.SEED_SWEET_POTATO);
	public static final RegistryObject<Block> BLOCK_MO_FLOWER = regSeed(CropTier.EPIC, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.EPIC), TagDC.ItemTag.SEED_MORNING_GLORY);
	public static final RegistryObject<Block> BLOCK_OR_SPIRANTHES = regSeed(CropTier.WILD, CropType.ORCHID, () -> new CropBlockOrchid(CropTier.WILD), TagDC.ItemTag.SEED_SPIRANTHES);
	public static final RegistryObject<Block> BLOCK_OR_CYMBIDIUM = regSeed(CropTier.COMMON, CropType.ORCHID, () -> new CropBlockOrchid(CropTier.COMMON), TagDC.ItemTag.SEED_CYMBIDIUM);
	public static final RegistryObject<Block> BLOCK_OR_VANILLA = regSeed(CropTier.RARE, CropType.ORCHID, () -> new CropBlockOrchid_Epiphyte(CropTier.RARE), TagDC.ItemTag.SEED_VANILLA);
	public static final RegistryObject<Block> BLOCK_OR_CATTLEYA = regSeed(CropTier.EPIC, CropType.ORCHID, () -> new CropBlockOrchid_Epiphyte(CropTier.EPIC), TagDC.ItemTag.SEED_CATTLEYA);
	public static final RegistryObject<Block> BLOCK_PD_ROGERIA = regSeed(CropTier.WILD, CropType.PEDALIA, () -> new CropBlockPedalia(CropTier.WILD), TagDC.ItemTag.SEED_ROGERIA);
	public static final RegistryObject<Block> BLOCK_PD_SESAMI = regSeed(CropTier.COMMON, CropType.PEDALIA, () -> new CropBlockPedalia(CropTier.COMMON), TagDC.ItemTag.SEED_SESAMI);
	public static final RegistryObject<Block> BLOCK_PD_DEVILSCLAW = regSeed(CropTier.RARE, CropType.PEDALIA, () -> new CropBlockPedalia(CropTier.RARE), TagDC.ItemTag.SEED_DEVILSCLAW);
	public static final RegistryObject<Block> BLOCK_PE_GREEN = regSeed(CropTier.WILD, CropType.PEAS, () -> new CropBlockPeas(CropTier.WILD), TagDC.ItemTag.SEED_GREEN_PEAS);
	public static final RegistryObject<Block> BLOCK_PE_GARBANZO = regSeed(CropTier.COMMON, CropType.PEAS, () -> new CropBlockPeas(CropTier.COMMON), TagDC.ItemTag.SEED_GARBANZO);
	public static final RegistryObject<Block> BLOCK_PE_SOY = regSeed(CropTier.RARE, CropType.PEAS, () -> new CropBlockPeas(CropTier.RARE), TagDC.ItemTag.SEED_SOY);
	public static final RegistryObject<Block> BLOCK_PE_ADZUKI = regSeed(CropTier.EPIC, CropType.PEAS, () -> new CropBlockPeas(CropTier.EPIC), TagDC.ItemTag.SEED_ADZUKI);
	public static final RegistryObject<Block> BLOCK_RA_ANEMONE = regSeed(CropTier.WILD, CropType.RANUNCULUS, () -> new CropBlockRanunculus(CropTier.WILD), TagDC.ItemTag.SEED_ANEMONE);
	public static final RegistryObject<Block> BLOCK_RA_DELPHINIUM = regSeed(CropTier.COMMON, CropType.RANUNCULUS, () -> new CropBlockRanunculus_Delphinium(CropTier.COMMON), TagDC.ItemTag.SEED_DELPHINIUM);
	public static final RegistryObject<Block> BLOCK_RA_CLEMATIS = regSeed(CropTier.RARE, CropType.RANUNCULUS, () -> new CropBlockRanunculus_Clematis(CropTier.RARE), TagDC.ItemTag.SEED_CLEMATIS);
	public static final RegistryObject<Block> BLOCK_RA_MONKSHOOD = regSeed(CropTier.EPIC, CropType.RANUNCULUS, () -> new CropBlockRanunculus(CropTier.EPIC), TagDC.ItemTag.SEED_MONKSHOOD);
	public static final RegistryObject<Block> BLOCK_RE_COMMON = regSeed(CropTier.WILD, CropType.REED, () -> new CropBlockReed(CropTier.WILD), TagDC.ItemTag.SEED_REED);
	public static final RegistryObject<Block> BLOCK_RE_SORGHUM = regSeed(CropTier.COMMON, CropType.REED, () -> new CropBlockReed(CropTier.COMMON), TagDC.ItemTag.SEED_SORGHUM);
	public static final RegistryObject<Block> BLOCK_RE_CORN = regSeed(CropTier.RARE, CropType.REED, () -> new CropBlockReed(CropTier.RARE), TagDC.ItemTag.SEED_CORN);
	public static final RegistryObject<Block> BLOCK_RI_ZIZANIA = regSeed(CropTier.WILD, CropType.RICE, () -> new CropBlockRice_Zizania(CropTier.WILD), TagDC.ItemTag.SEED_WILD_RICE);
	public static final RegistryObject<Block> BLOCK_RI_SHORT = regSeed(CropTier.COMMON, CropType.RICE, () -> new CropBlockRice(CropTier.COMMON), TagDC.ItemTag.SEED_RICE);
	public static final RegistryObject<Block> BLOCK_RI_AROMA = regSeed(CropTier.RARE, CropType.RICE, () -> new CropBlockRice(CropTier.RARE), TagDC.ItemTag.SEED_AROMA_RICE);
	public static final RegistryObject<Block> BLOCK_SL_NIGHTSHADE = regSeed(CropTier.WILD, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.WILD), TagDC.ItemTag.SEED_NIGHTSHADE);
	public static final RegistryObject<Block> BLOCK_SL_EGGPLANT = regSeed(CropTier.COMMON, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.COMMON), TagDC.ItemTag.SEED_EGGPLANT);
	public static final RegistryObject<Block> BLOCK_SL_TOMATO = regSeed(CropTier.RARE, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.RARE), TagDC.ItemTag.SEED_TOMATO);
	public static final RegistryObject<Block> BLOCK_SL_LANTERN = regSeed(CropTier.EPIC, CropType.SOLANUM, () -> new CropBlockSolanum(CropTier.EPIC), TagDC.ItemTag.SEED_LANTERN);

	public static final RegistryObject<Block> BLOCK_BH_COMMON = regCropBlock(CropTier.WILD, CropType.BEECH, () -> new SaplingBeech(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_BH_WALNUT = regCropBlock(CropTier.COMMON, CropType.BEECH, () -> new SaplingBeech(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_BH_SWEET = regCropBlock(CropTier.RARE, CropType.BEECH, () -> new SaplingBeech(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CH_WILD = regCropBlock(CropTier.WILD, CropType.CHERRY, () -> new SaplingCherry(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CH_PLUM = regCropBlock(CropTier.COMMON, CropType.CHERRY, () -> new SaplingCherry(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CH_PEACH = regCropBlock(CropTier.RARE, CropType.CHERRY, () -> new SaplingCherry(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CM_OIL = regCropBlock(CropTier.WILD, CropType.CAMELLIA, () -> new SaplingCamellia(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CM_SCHIMA = regCropBlock(CropTier.COMMON, CropType.CAMELLIA, () -> new SaplingCamellia(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CM_TEA = regCropBlock(CropTier.RARE, CropType.CAMELLIA, () -> new SaplingCamellia(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CN_CAMPHOR = regCropBlock(CropTier.WILD, CropType.CINNAMON, () -> new SaplingCinnamon(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CN_CINNAMON = regCropBlock(CropTier.COMMON, CropType.CINNAMON, () -> new SaplingCinnamon(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CN_AVOCADO = regCropBlock(CropTier.RARE, CropType.CINNAMON, () -> new SaplingCinnamon(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CT_POMELO = regCropBlock(CropTier.WILD, CropType.CITRUS, () -> new SaplingCitrus(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CT_MANDARIN = regCropBlock(CropTier.COMMON, CropType.CITRUS, () -> new SaplingCitrus(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CT_LEMON = regCropBlock(CropTier.RARE, CropType.CITRUS, () -> new SaplingCitrus(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_CT_PEPPER = regCropBlock(CropTier.EPIC, CropType.CITRUS, () -> new SaplingCitrus(CropTier.EPIC), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_ER_HEATH = regCropBlock(CropTier.WILD, CropType.ERICA, () -> new SaplingErica(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_ER_RHODODENDRON = regCropBlock(CropTier.COMMON, CropType.ERICA, () -> new SaplingErica(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_ER_BLUEBERRY = regCropBlock(CropTier.RARE, CropType.ERICA, () -> new SaplingErica(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MR_MULBERRY = regCropBlock(CropTier.WILD, CropType.MORUS, () -> new SaplingMorus(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MR_PAPER = regCropBlock(CropTier.COMMON, CropType.MORUS, () -> new SaplingMorus(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MR_RUBBER = regCropBlock(CropTier.RARE, CropType.MORUS, () -> new SaplingMorus(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MY_EUCALYPTUS = regCropBlock(CropTier.WILD, CropType.MYRTLE, () -> new SaplingMyrtle(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MY_GUAVA = regCropBlock(CropTier.COMMON, CropType.MYRTLE, () -> new SaplingMyrtle(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MY_CLOVE = regCropBlock(CropTier.RARE, CropType.MYRTLE, () -> new SaplingMyrtle(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_OL_ASH = regCropBlock(CropTier.WILD, CropType.OLIVE, () -> new SaplingOlive(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_OL_OLIVE = regCropBlock(CropTier.COMMON, CropType.OLIVE, () -> new SaplingOlive(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_OL_OSMANTHUS = regCropBlock(CropTier.RARE, CropType.OLIVE, () -> new SaplingOlive(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_PL_COCONUT = regCropBlock(CropTier.WILD, CropType.PALM, () -> new SaplingPalm(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_PL_DATE = regCropBlock(CropTier.COMMON, CropType.PALM, () -> new SaplingPalm(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_PL_OIL = regCropBlock(CropTier.RARE, CropType.PALM, () -> new SaplingPalm(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_RO_RUGOSA = regCropBlock(CropTier.WILD, CropType.ROSE, () -> new SaplingRose(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_RO_RASPBERRY = regCropBlock(CropTier.COMMON, CropType.ROSE, () -> new SaplingRose(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_RO_DAMASCHENA = regCropBlock(CropTier.RARE, CropType.ROSE, () -> new SaplingRose(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_SU_LACQUER = regCropBlock(CropTier.WILD, CropType.SUMAC, () -> new SaplingSumac(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_SU_MANGO = regCropBlock(CropTier.COMMON, CropType.SUMAC, () -> new SaplingSumac(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_SU_CASHEW = regCropBlock(CropTier.RARE, CropType.SUMAC, () -> new SaplingSumac(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_SU_PISTACHIO = regCropBlock(CropTier.EPIC, CropType.SUMAC, () -> new SaplingSumac(CropTier.EPIC), "sapling", ItemTags.SAPLINGS);

	public static final RegistryObject<Block> LEAVES_BH_COMMON = regBlock("leaves_beech_common", () -> new LeavesBeech(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_BH_WALNUT = regBlock("leaves_beech_walnut", () -> new LeavesWalnut(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_BH_SWEET = regBlock("leaves_beech_sweet", () -> new LeavesSweet(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CH_WILD = regBlock("leaves_cherry_wild", () -> new LeavesCherry(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CH_PLUM = regBlock("leaves_cherry_plum", () -> new LeavesPlum(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CH_PEACH = regBlock("leaves_cherry_peach", () -> new LeavesPeach(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CM_OIL = regBlock("leaves_camellia_oil", () -> new LeavesCamellia(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CM_SCHIMA = regBlock("leaves_camellia_schima", () -> new LeavesSchima(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CM_TEA = regBlock("leaves_camellia_tea", () -> new LeavesTea(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CN_CAMPHOR = regBlock("leaves_cinnamon_camphor", () -> new LeavesCamphor(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CN_CINNAMON = regBlock("leaves_cinnamon_true", () -> new LeavesCinnamon(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CN_AVOCADO = regBlock("leaves_cinnamon_avocado", () -> new LeavesAvocado(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CT_POMELO = regBlock("leaves_citrus_pomelo", () -> new LeavesPomelo(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CT_MANDARIN = regBlock("leaves_citrus_mandarin", () -> new LeavesMandarin(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CT_LEMON = regBlock("leaves_citrus_lemon", () -> new LeavesLemon(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_CT_PEPPER = regBlock("leaves_citrus_pepper", () -> new LeavesSichuanPepper(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_ER_HEATH = regBlock("leaves_erica_heath", () -> new LeavesHeath(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_ER_RHODODENDRON = regBlock("leaves_erica_rhododendron", () -> new LeavesRhododendron(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_ER_BLUEBERRY = regBlock("leaves_erica_blueberry", () -> new LeavesBlueberry(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MR_MULBERRY = regBlock("leaves_morus_mulberry", () -> new LeavesMulberry(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MR_PAPER = regBlock("leaves_morus_paper", () -> new LeavesPaper(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MR_RUBBER = regBlock("leaves_morus_rubber", () -> new LeavesRubber(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MY_EUCALYPTUS = regBlock("leaves_myrtle_eucalyptus", () -> new LeavesEucalyptus(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MY_GUAVA = regBlock("leaves_myrtle_guava", () -> new LeavesGuava(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MY_CLOVE = regBlock("leaves_myrtle_clove", () -> new LeavesClove(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_OL_ASH = regBlock("leaves_olive_ash", () -> new LeavesAsh(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_OL_OLIVE = regBlock("leaves_olive_olive", () -> new LeavesOlive(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_OL_OSMANTHUS = regBlock("leaves_olive_osmanthus", () -> new LeavesOsmanthus(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_PL_COMMON = regBlock("leaves_palm", () -> new LeavesPalm(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_RO_RUGOSA = regBlock("leaves_rose_rugosa", () -> new LeavesRugosa(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_RO_RASPBERRY = regBlock("leaves_rose_raspberry", () -> new LeavesRaspberry(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_RO_DAMASCHENA = regBlock("leaves_rose_damaschena", () -> new LeavesDamaschena(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_SU_LACQUER = regBlock("leaves_sumac_lacquer", () -> new LeavesLacquer(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_SU_MANGO = regBlock("leaves_sumac_mango", () -> new LeavesMango(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_SU_CASHEW = regBlock("leaves_sumac_cashew", () -> new LeavesCashew(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_SU_PISTACHIO = regBlock("leaves_sumac_pistachio", () -> new LeavesPistachio(), ItemTags.LEAVES);

	public static final RegistryObject<Block> CROPBLOCK_PL_COCONUT = regSeed(CropTier.WILD, CropType.PALM, () -> new CropBlockPalm(CropTier.WILD), null);
	public static final RegistryObject<Block> CROPBLOCK_PL_DATE = regSeed(CropTier.COMMON, CropType.PALM, () -> new CropBlockPalm(CropTier.COMMON), null);
	public static final RegistryObject<Block> CROPBLOCK_PL_OIL = regSeed(CropTier.RARE, CropType.PALM, () -> new CropBlockPalm(CropTier.RARE), null);

	public static final RegistryObject<Block> LOG_BH_COMMON = regBlock("log_beech_common", () -> new LogBlockDC("beech_common"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_BH_WALNUT = regBlock("log_beech_walnut", () -> new LogBlockDC("beech_walnut"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_BH_SWEET = regBlock("log_beech_sweet", () -> new LogBlockDC("beech_sweet"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_CH_WILD = regBlock("log_cherry_wild", () -> new LogBlockDC("cherry_wild"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_CN_CAMPHOR = regBlock("log_cinnamon_camphor", () -> new LogBlockDC("cinnamon_camphor"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_CN_CINNAMON = regBlock("log_cinnamon_true", () -> new LogBlockDC("cinnamon_true"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_CT_POMELO = regBlock("log_citrus_pomelo", () -> new LogBlockDC("citrus_pomelo"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_MR_MULBERRY = regBlock("log_morus_mulberry", () -> new LogBlockDC("morus_mulberry"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_MR_PAPER = regBlock("log_morus_paper", () -> new LogBlockDC("morus_paper"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_MR_RUBBER = regBlock("log_morus_rubber", () -> new LogBlockDC("morus_rubber"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_MY_EUCALYPTUS = regBlock("log_myrtle_eucalyptus", () -> new LogBlockDC("myrtle_eucalyptus"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_OL_ASH = regBlock("log_olive_ash", () -> new LogBlockDC("olive_ash"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_PL_COCONUT = regBlock("log_palm_coconut", () -> new LogBlockDC("palm_coconut"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_SU_LACQUER = regBlock("log_sumac_lacquer", () -> new LogBlockDC("sumac_lacquer"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_SU_MANGO = regBlock("log_sumac_mango", () -> new LogBlockDC("sumac_mango"), ItemTags.LOGS_THAT_BURN);

	public static final RegistryObject<Block> PLANK_BH_COMMON = regBlock("plank_beech_common", () -> new PlankBlockDC("plank_beech_common"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_BH_WALNUT = regBlock("plank_beech_walnut", () -> new PlankBlockDC("plank_beech_walnut"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_BH_SWEET = regBlock("plank_beech_sweet", () -> new PlankBlockDC("plank_beech_sweet"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_CH_WILD = regBlock("plank_cherry_wild", () -> new PlankBlockDC("plank_cherry_wild"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_CN_CAMPHOR = regBlock("plank_cinnamon_camphor", () -> new PlankBlockDC("plank_cinnamon_camphor"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_CT_POMELO = regBlock("plank_citrus_pomelo", () -> new PlankBlockDC("plank_citrus_pomelo"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_MR_MULBERRY = regBlock("plank_morus_mulberry", () -> new PlankBlockDC("plank_morus_mulberry"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_MY_EUCALYPTUS = regBlock("plank_myrtle_eucalyptus", () -> new PlankBlockDC("plank_myrtle_eucalyptus"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_OL_ASH = regBlock("plank_olive_ash", () -> new PlankBlockDC("plank_olive_ash"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_PL_COCONUT = regBlock("plank_palm_coconut", () -> new PlankBlockDC("plank_palm_coconut"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_SU_LACQUER = regBlock("plank_sumac_lacquer", () -> new PlankBlockDC("plank_sumac_lacquer"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_RE_SORGHUM = regBlock("plank_reed_sorghum", () -> new PlankBlockDC("plank_reed_sorghum"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_LACQUERWARE = regBlock("plank_lacquerware", () -> new PlankBlockDC("plank_lacquerware"), ItemTags.PLANKS);

	public static final RegistryObject<Block> FERTILE = regBlock("fertile", () -> new FertileBlock(), null);

	public static final RegistryObject<Block> CONT_LEAVES = regCont("container_leaves", () -> new LeavesContBlock("container_leaves"), TagDC.ItemTag.CONT_LEAVES);

	public static final RegistryObject<Block> CONT_LOG_OAK = regCont("logcont_oak", () -> new LogContBlock("oak"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_BIRCH = regCont("logcont_birch", () -> new LogContBlock("birch"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_SPRUCE = regCont("logcont_spruce", () -> new LogContBlock("spruce"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_JUNGLE = regCont("logcont_jungle", () -> new LogContBlock("jungle"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_ACACIA = regCont("logcont_acacia", () -> new LogContBlock("acacia"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_DARKOAK = regCont("logcont_darkoak", () -> new LogContBlock("darkoak"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_MANGROVE = regCont("logcont_mangrove", () -> new LogContBlock("mangrove"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_CRIMSON = regCont("logcont_crimson", () -> new LogContBlock("crimson"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_WARPED = regCont("logcont_warped", () -> new LogContBlock("warped"), TagDC.ItemTag.CONT_LOGS);

	public static final RegistryObject<Block> CONT_LOG_BH_COMMON = regCont("logcont_beech_common", () -> new LogContBlock("beech_common"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_BH_WALNUT = regCont("logcont_beech_walnut", () -> new LogContBlock("beech_walnut"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_BH_SWEET = regCont("logcont_beech_sweet", () -> new LogContBlock("beech_sweet"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_CH_WILD = regCont("logcont_cherry_wild", () -> new LogContBlock("cherry_wild"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_CN_CAMPHOR = regCont("logcont_cinnamon_camphor", () -> new LogContBlock("cinnamon_camphor"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_CN_CINNAMON = regCont("logcont_cinnamon_true", () -> new LogContBlock("cinnamon_true"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_CT_POMELO = regCont("logcont_citrus_pomelo", () -> new LogContBlock("citrus_pomelo"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_MR_MULBERRY = regCont("logcont_morus_mulberry", () -> new LogContBlock("morus_mulberry"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_MR_PAPER = regCont("logcont_morus_paper", () -> new LogContBlock("morus_paper"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_MR_RUBBER = regCont("logcont_morus_rubber", () -> new LogContBlock("morus_rubber"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_MY_EUCALYPTUS = regCont("logcont_myrtle_eucalyptus", () -> new LogContBlock("myrtle_eucalyptus"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_OL_ASH = regCont("logcont_olive_ash", () -> new LogContBlock("olive_ash"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_PL_COCONUT = regCont("logcont_palm_coconut", () -> new LogContBlock("palm_coconut"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_SU_LACQUER = regCont("logcont_sumac_lacquer", () -> new LogContBlock("sumac_lacquer"), TagDC.ItemTag.CONT_LOGS);
	public static final RegistryObject<Block> CONT_LOG_SU_MANGO = regCont("logcont_sumac_mango", () -> new LogContBlock("sumac_mango"), TagDC.ItemTag.CONT_LOGS);

	public static final RegistryObject<Block> CONT_LOG_CHARCOAL = regCont("logcont_charcoal", () -> new LogContBlock("charcoal"), TagDC.ItemTag.CONT_CHARCOAL);

	public static final RegistryObject<Block> CONT_CROP_APPLE = regCont("woodbox_apple", () -> new CropContBlock("woodbox", "apple"), TagDC.ItemTag.CONT_APPLE);
	public static final RegistryObject<Block> CONT_CROP_CARROT = regCont("woodbox_carrot", () -> new CropContBlock("woodbox", "carrot"), TagDC.ItemTag.CONT_CARROT);
	public static final RegistryObject<Block> CONT_CROP_POTATO = regCont("woodbox_potato", () -> new CropContBlock("woodbox", "potato"), TagDC.ItemTag.CONT_POTATO);
	public static final RegistryObject<Block> CONT_CROP_BEET = regCont("woodbox_beet", () -> new CropContBlock("woodbox", "beet"), TagDC.ItemTag.CONT_BEET);
	public static final RegistryObject<Block> CONT_CROP_PUMPKIN = regCont("woodbox_pumpkin", () -> new CropContBlock("woodbox", "pumpkin"), TagDC.ItemTag.CONT_PUMPKIN);
	public static final RegistryObject<Block> CONT_CROP_MELON = regCont("woodbox_melon", () -> new CropContBlock("woodbox", "melon"), TagDC.ItemTag.CONT_MELON);
	public static final RegistryObject<Block> CONT_CROP_CACTUS = regCont("woodbox_cactus", () -> new CropContBlock("woodbox", "cactus"), TagDC.ItemTag.CONT_CACTUS);
	public static final RegistryObject<Block> CONT_CROP_SUGARCANE = regCont("woodbox_sugarcane", () -> new CropContBlock("woodbox", "sugarcane"), TagDC.ItemTag.CONT_SUGARCANE);
	public static final RegistryObject<Block> CONT_CROP_COCOA = regCont("woodbox_cocoa", () -> new CropContBlock("woodbox", "cocoa"), TagDC.ItemTag.CONT_COCOA);
	public static final RegistryObject<Block> CONT_CROP_BUSHBERRY = regCont("woodbox_bushberry", () -> new CropContBlock("woodbox", "bushberry"), TagDC.ItemTag.CONT_BUSHBERRY);

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag));
	}

	public static RegistryObject<Item> regPoisonCrop(CropTier tier, CropType type, TagKey<Item> tag) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag, true));
	}

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag, int f, float s) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag, f, s));
	}

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag, int taste) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag).taste(taste));
	}

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag, int f, float s, int taste) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag, f, s).taste(taste));
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("food/" + name, item);
	}

	public static RegistryObject<Item> regContItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("container/" + name, item);
	}

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("food/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(AGRI), tag));
		return obj;
	}

	public static RegistryObject<Block> regCont(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("container/" + name, block);
		regContItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(CONT), tag));
		return obj;
	}

	public static RegistryObject<Block> regSeed(CropTier tier, CropType type, Supplier<Block> block, TagKey<Item> tag) {
		return regCropBlock(tier, type, block, "cropblock", tag);
	}

	public static RegistryObject<Block> regCropBlock(CropTier tier, CropType type, Supplier<Block> block, String s, TagKey<Item> tag) {
		String name = s + "_" + type.toString() + "_" + tier.toString();
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("food/" + name, block);
		regItem(name, () -> new SeedItemDC(tier, type, obj.get(), name, tag));
		return obj;
	}

}
