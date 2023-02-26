package defeatedcrow.hac.food.material;

import java.util.function.Supplier;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Agri;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Food;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.block.CropBlockAllium;
import defeatedcrow.hac.food.material.block.CropBlockAmaranth;
import defeatedcrow.hac.food.material.block.CropBlockApium;
import defeatedcrow.hac.food.material.block.CropBlockBrassica;
import defeatedcrow.hac.food.material.block.CropBlockCapsicum;
import defeatedcrow.hac.food.material.block.CropBlockCereals;
import defeatedcrow.hac.food.material.block.CropBlockGinger;
import defeatedcrow.hac.food.material.block.CropBlockHerb;
import defeatedcrow.hac.food.material.block.CropBlockKnotweed;
import defeatedcrow.hac.food.material.block.CropBlockMallow;
import defeatedcrow.hac.food.material.block.CropBlockMorningGlory;
import defeatedcrow.hac.food.material.block.CropBlockPalm;
import defeatedcrow.hac.food.material.block.CropBlockPeas;
import defeatedcrow.hac.food.material.block.CropBlockPedaria;
import defeatedcrow.hac.food.material.block.CropBlockReed;
import defeatedcrow.hac.food.material.block.CropBlockRice;
import defeatedcrow.hac.food.material.block.CropBlockSolanum;
import defeatedcrow.hac.food.material.block.FertileBlock;
import defeatedcrow.hac.food.material.block.LeavesAsh;
import defeatedcrow.hac.food.material.block.LeavesAvocado;
import defeatedcrow.hac.food.material.block.LeavesBeech;
import defeatedcrow.hac.food.material.block.LeavesBlueberry;
import defeatedcrow.hac.food.material.block.LeavesCamellia;
import defeatedcrow.hac.food.material.block.LeavesCamphor;
import defeatedcrow.hac.food.material.block.LeavesCherry;
import defeatedcrow.hac.food.material.block.LeavesCinnamon;
import defeatedcrow.hac.food.material.block.LeavesHeath;
import defeatedcrow.hac.food.material.block.LeavesLemon;
import defeatedcrow.hac.food.material.block.LeavesMandarin;
import defeatedcrow.hac.food.material.block.LeavesMulberry;
import defeatedcrow.hac.food.material.block.LeavesOlive;
import defeatedcrow.hac.food.material.block.LeavesOsmanthus;
import defeatedcrow.hac.food.material.block.LeavesPalm;
import defeatedcrow.hac.food.material.block.LeavesPaper;
import defeatedcrow.hac.food.material.block.LeavesPeach;
import defeatedcrow.hac.food.material.block.LeavesPlum;
import defeatedcrow.hac.food.material.block.LeavesPomelo;
import defeatedcrow.hac.food.material.block.LeavesRhododendron;
import defeatedcrow.hac.food.material.block.LeavesRubber;
import defeatedcrow.hac.food.material.block.LeavesSchima;
import defeatedcrow.hac.food.material.block.LeavesSweet;
import defeatedcrow.hac.food.material.block.LeavesTea;
import defeatedcrow.hac.food.material.block.LeavesWalnut;
import defeatedcrow.hac.food.material.block.LogBlockDC;
import defeatedcrow.hac.food.material.block.PlankBlockDC;
import defeatedcrow.hac.food.material.block.SaplingBeech;
import defeatedcrow.hac.food.material.block.SaplingCamellia;
import defeatedcrow.hac.food.material.block.SaplingCherry;
import defeatedcrow.hac.food.material.block.SaplingCinnamon;
import defeatedcrow.hac.food.material.block.SaplingCitrus;
import defeatedcrow.hac.food.material.block.SaplingErica;
import defeatedcrow.hac.food.material.block.SaplingMorus;
import defeatedcrow.hac.food.material.block.SaplingOlive;
import defeatedcrow.hac.food.material.block.SaplingPalm;
import defeatedcrow.hac.food.material.entity.BreadCreamItem;
import defeatedcrow.hac.food.material.entity.BreadRoundItem;
import defeatedcrow.hac.food.material.entity.BreadSausageItem;
import defeatedcrow.hac.food.material.entity.BreadSquareItem;
import defeatedcrow.hac.food.material.entity.FoodEntityBase;
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
import defeatedcrow.hac.food.material.item.EmptyPackItem;
import defeatedcrow.hac.food.material.item.FoodMaterialItemDC;
import defeatedcrow.hac.food.material.item.ItemCropDC;
import defeatedcrow.hac.food.material.item.SeedItemDC;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

public class FoodInit {

	public static final CreativeModeTab FOOD = new CreativeTabClimate_Food("food");
	public static final CreativeModeTab AGRI = new CreativeTabClimate_Agri("agri");

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

	public static final RegistryObject<EntityType<FoodEntityBase>> STICK_BEEF = CoreInit.ENTITIES.register("stick_beef", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.5F).updateInterval(5).build("stick_beef"));
	public static final RegistryObject<EntityType<FoodEntityBase>> STICK_MEAT = CoreInit.ENTITIES.register("stick_meat", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.375F, 0.5F).updateInterval(5).build("stick_meat"));

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

	public static final RegistryObject<EntityType<FoodEntityBase>> SALAD = CoreInit.ENTITIES.register("salad", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).updateInterval(5).build("salad"));

	public static final RegistryObject<EntityType<FoodEntityBase>> SOUP = CoreInit.ENTITIES.register("soup", () -> EntityType.Builder.<FoodEntityBase>of(FoodEntityBase::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).updateInterval(5).build("soup"));

	// パン
	public static final RegistryObject<Item> BREAD_ROUND_RAW_ITEM = regItem("bread_round_raw", () -> new BreadRoundItem("bread_round_raw", 0, 0F, TagDC.ItemTag.DOUGH).setRawFood());
	public static final RegistryObject<Item> BREAD_ROUND_BAKED_ITEM = regItem("bread_round_baked", () -> new BreadRoundItem("bread_round_baked", 2, 0.2F, TagDC.ItemTag.BREAD));
	public static final RegistryObject<Item> BREAD_SQUARE_RAW_ITEM = regItem("bread_square_raw", () -> new BreadSquareItem("bread_square_raw", 0, 0F, TagDC.ItemTag.DOUGH).setRawFood());
	public static final RegistryObject<Item> BREAD_SQUARE_BAKED_ITEM = regItem("bread_square_baked", () -> new BreadSquareItem("bread_square_baked", 2, 0.2F, TagDC.ItemTag.BREAD));
	public static final RegistryObject<Item> BREAD_NUTS_RAW_ITEM = regItem("bread_nuts_raw", () -> new BreadRoundItem("bread_nuts_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_NUTS_BAKED_ITEM = regItem("bread_nuts_baked", () -> new BreadRoundItem("bread_nuts_baked", 4, 0.2F, null));
	public static final RegistryObject<Item> BREAD_CINNAMON_RAW_ITEM = regItem("bread_cinnamon_raw", () -> new BreadRoundItem("bread_cinnamon_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_CINNAMON_BAKED_ITEM = regItem("bread_cinnamon_baked", () -> new BreadRoundItem("bread_cinnamon_baked", 4, 0.2F, null));
	public static final RegistryObject<Item> BREAD_ANKO_RAW_ITEM = regItem("bread_anko_raw", () -> new BreadRoundItem("bread_anko_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_ANKO_BAKED_ITEM = regItem("bread_anko_baked", () -> new BreadRoundItem("bread_anko_baked", 4, 0.3F, null));
	public static final RegistryObject<Item> BREAD_CREAM_RAW_ITEM = regItem("bread_cream_raw", () -> new BreadCreamItem("bread_cream_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_CREAM_BAKED_ITEM = regItem("bread_cream_baked", () -> new BreadCreamItem("bread_cream_baked", 5, 0.2F, null));
	public static final RegistryObject<Item> BREAD_SAUSAGE_RAW_ITEM = regItem("bread_sausage_raw", () -> new BreadSausageItem("bread_sausage_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> BREAD_SAUSAGE_BAKED_ITEM = regItem("bread_sausage_baked", () -> new BreadSausageItem("bread_sausage_baked", 6, 0.3F, null));
	// public static final RegistryObject<Item> BREAD_PITA_RAW_ITEM = regItem("bread_pita_raw", () -> new BreadRoundItem("bread_pita_raw", 0, 0F, null).setRawFood());
	// public static final RegistryObject<Item> BREAD_PITA_BAKED_ITEM = regItem("bread_pita_baked", () -> new BreadRoundItem("bread_pita_baked", 2, 0.15F, null));
	// public static final RegistryObject<Item> BREAD_TORTILLA_RAW_ITEM = regItem("bread_tortilla_raw", () -> new BreadRoundItem("bread_tortilla_raw", 0, 0F, null).setRawFood());
	// public static final RegistryObject<Item> BREAD_TORTILLA_BAKED_ITEM = regItem("bread_tortilla_baked", () -> new BreadRoundItem("bread_tortilla_baked", 2, 0.15F, null));

	public static final RegistryObject<Item> SANDWICH_FRUIT_ITEM = regItem("sandwich_fruit", () -> new SandwichItem("sandwich_fruit", 4, 0.3F, null));
	public static final RegistryObject<Item> SANDWICH_MARMALADE_ITEM = regItem("sandwich_marmalade", () -> new SandwichItem("sandwich_marmalade", 4, 0.3F, null));
	public static final RegistryObject<Item> SANDWICH_EGG_ITEM = regItem("sandwich_egg", () -> new SandwichItem("sandwich_egg", 5, 0.4F, null));
	public static final RegistryObject<Item> SANDWICH_SALAD_ITEM = regItem("sandwich_salad", () -> new SandwichItem("sandwich_salad", 5, 0.4F, null));
	public static final RegistryObject<Item> SANDWICH_SALMON_ITEM = regItem("sandwich_salmon", () -> new SandwichItem("sandwich_salmon", 5, 0.4F, null));

	public static final RegistryObject<Item> PORRIDGE = regItem("porridge_simple", () -> new PorridgeItem("porridge_simple", 3, 0.4F, null));
	public static final RegistryObject<Item> PORRIDGE_MILK = regItem("porridge_milk", () -> new PorridgeItem("porridge_milk", 5, 0.4F, null));
	public static final RegistryObject<Item> MUESLI = regItem("muesli", () -> new PorridgeItem("muesli", 6, 0.4F, null));
	// ごはん

	// 麺
	// そば

	// 串焼き
	public static final RegistryObject<Item> STICK_BEEF_RAW = regItem("stick_beef_raw", () -> new StickBeefItem("stick_beef_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_BEEF_COOKED = regItem("stick_beef_cooked", () -> new StickBeefItem("stick_beef_cooked", 10, 0.4F, null));
	public static final RegistryObject<Item> STICK_PORK_RAW = regItem("stick_pork_raw", () -> new StickBeefItem("stick_pork_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_PORK_COOKED = regItem("stick_pork_cooked", () -> new StickBeefItem("stick_pork_cooked", 10, 0.4F, null));
	public static final RegistryObject<Item> STICK_MUTTON_RAW = regItem("stick_mutton_raw", () -> new StickMeatItem("stick_mutton_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_MUTTON_COOKED = regItem("stick_mutton_cooked", () -> new StickMeatItem("stick_mutton_cooked", 8, 0.3F, null));
	public static final RegistryObject<Item> STICK_CHICKEN_RAW = regItem("stick_chicken_raw", () -> new StickMeatItem("stick_chicken_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_CHICKEN_COOKED = regItem("stick_chicken_cooked", () -> new StickMeatItem("stick_chicken_cooked", 8, 0.3F, null));
	public static final RegistryObject<Item> STICK_OFFAL_RAW = regItem("stick_offal_raw", () -> new StickMeatItem("stick_offal_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> STICK_OFFAL_COOKED = regItem("stick_offal_cooked", () -> new StickMeatItem("stick_offal_cooked", 8, 0.3F, null));

	// 鉄板焼
	public static final RegistryObject<Item> PLATE_BEEF_RAW = regItem("plate_beef_raw", () -> new PlateBeefItem("plate_beef_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_BEEF_COOKED = regItem("plate_beef_cooked", () -> new PlateBeefItem("plate_beef_cooked", 14, 1.0F, null));
	public static final RegistryObject<Item> PLATE_MEAT_RAW = regItem("plate_meat_raw", () -> new PlateMeatItem("plate_meat_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_MEAT_COOKED = regItem("plate_meat_cooked", () -> new PlateMeatItem("plate_meat_cooked", 12, 0.8F, null));
	public static final RegistryObject<Item> PLATE_LEGS_RAW = regItem("plate_legs_raw", () -> new PlateLegsItem("plate_legs_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_LEGS_COOKED = regItem("plate_legs_cooked", () -> new PlateLegsItem("plate_legs_cooked", 10, 0.6F, null));
	public static final RegistryObject<Item> PLATE_GARLIC_RAW = regItem("plate_big_garlic_raw", () -> new PlateGarlicItem("plate_big_garlic_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_GARLIC_COOKED = regItem("plate_big_garlic_cooked", () -> new PlateGarlicItem("plate_big_garlic_cooked", 16, 0.8F, null));
	public static final RegistryObject<Item> PLATE_CHICKEN_BIG_RAW = regItem("plate_big_chicken_raw", () -> new PlateChickenItem("plate_big_chicken_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_CHICKEN_BIG_COOKED = regItem("plate_big_chicken_cooked", () -> new PlateChickenItem("plate_big_chicken_cooked", 14, 1.0F, null));
	public static final RegistryObject<Item> PLATE_FISH_RAW = regItem("plate_fish_raw", () -> new PlateFishItem("plate_fish_raw", 0, 0F, null).setRawFood());
	public static final RegistryObject<Item> PLATE_FISH_COOKED = regItem("plate_fish_cooked", () -> new PlateFishItem("plate_fish_cooked", 10, 0.6F, null));

	// サラダ
	public static final RegistryObject<Item> SALAD_GREEN = regItem("salad_green", () -> new SaladItem("salad_green", 6, 0.4F, null));
	public static final RegistryObject<Item> SALAD_POTATO = regItem("salad_potato", () -> new SaladItem("salad_potato", 10, 0.4F, null));
	public static final RegistryObject<Item> SALAD_NUTS = regItem("salad_nuts", () -> new SaladItem("salad_nuts", 8, 0.4F, null));
	public static final RegistryObject<Item> SALAD_MELON = regItem("salad_melon", () -> new SaladItem("salad_melon", 7, 0.4F, null));

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

	// dairy
	public static final RegistryObject<Item> FOOD_BUTTER = regItem("food_butter", () -> new FoodMaterialItemDC(FOOD, "food_butter", TagDC.ItemTag.BUTTER).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_MARGARINE = regItem("food_margarine", () -> new FoodMaterialItemDC(FOOD, "food_margarine", TagDC.ItemTag.MARGARINE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CHEESE = regItem("food_cheese", () -> new FoodMaterialItemDC(FOOD, "food_cheese", TagDC.ItemTag.CHEESE).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_OFFAL = regItem("food_offal", () -> new FoodMaterialItemDC(FOOD, "food_offal", TagDC.ItemTag.OFFAL).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_GELATINE = regItem("food_gelatine", () -> new FoodMaterialItemDC(FOOD, "food_gelatine", TagDC.ItemTag.GELATINE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_RENNET = regItem("food_rennet", () -> new FoodMaterialItemDC(FOOD, "food_rennet", TagDC.ItemTag.RENNET).setDomain("food"));

	// meat
	public static final RegistryObject<Item> FOOD_FROG = regItem("food_raw_frog", () -> new FoodMaterialItemDC(FOOD, "food_raw_frog", TagDC.ItemTag.FROG).taste(-1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_PRAWN = regItem("food_raw_prawn", () -> new FoodMaterialItemDC(FOOD, "food_raw_prawn", TagDC.ItemTag.PRAWN).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SQUID = regItem("food_raw_squid", () -> new FoodMaterialItemDC(FOOD, "food_raw_squid", TagDC.ItemTag.SQUID).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_ROE = regItem("food_raw_roe", () -> new FoodMaterialItemDC(FOOD, "food_raw_roe", TagDC.ItemTag.ROE).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_FISH_POWDER = regItem("dust_fish_powder", () -> new FoodMaterialItemDC(FOOD, "dust_fish_powder", TagDC.ItemTag.FISH_POWDER).setDomain("food"));

	// processed
	public static final RegistryObject<Item> FOOD_JAM = regItem("food_fruit_jam", () -> new FoodMaterialItemDC(FOOD, "food_fruit_jam", TagDC.ItemTag.JAM).setDomain("food"));
	public static final RegistryObject<Item> FOOD_MARMALADE = regItem("food_marmalade", () -> new FoodMaterialItemDC(FOOD, "food_marmalade", TagDC.ItemTag.MARMALADE).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CUSTARD = regItem("food_custard", () -> new FoodMaterialItemDC(FOOD, "food_custard", TagDC.ItemTag.CUSTARD).setDomain("food"));
	public static final RegistryObject<Item> FOOD_ANKO = regItem("food_anko", () -> new FoodMaterialItemDC(FOOD, "food_anko", TagDC.ItemTag.ANKO).setDomain("food"));

	public static final RegistryObject<Item> RAW_SAUSAGE = regItem("food_sausage_raw", () -> new FoodMaterialItemDC(FOOD, "food_sausage_raw", TagDC.ItemTag.RAW_SAUSAGE).setDomain("food"));
	public static final RegistryObject<Item> SMOKED_SAUSAGE = regItem("food_sausage_smoked", () -> new FoodMaterialItemDC(FOOD, "food_sausage_smoked", TagDC.ItemTag.COOKED_SAUSAGE).setDomain("food"));

	// seasoning
	public static final RegistryObject<Item> FOOD_SOYSAUCE = regItem("food_soysauce", () -> new FoodMaterialItemDC(FOOD, "food_soysauce", TagDC.ItemTag.SOYSAUCE).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_MISO = regItem("food_miso", () -> new FoodMaterialItemDC(FOOD, "food_miso", TagDC.ItemTag.MISO).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_MIXED_SPICES = regItem("food_mixed_spices", () -> new FoodMaterialItemDC(FOOD, "food_mixed_spices", TagDC.ItemTag.MIXED_SPICES).taste(2).seasoning().setDomain(
		"food"));

	// ケチャップ
	// マヨ
	// タバスコ
	// エビペースト
	// カレー粉

	public static final RegistryObject<Item> FOOD_MILK = regItem("pack_milk", () -> new FoodMaterialItemDC(FOOD, "pack_milk", TagDC.ItemTag.COW_MILK).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_SOY_MILK = regItem("pack_soy_milk", () -> new FoodMaterialItemDC(FOOD, "pack_soy_milk", TagDC.ItemTag.SOY_MILK).setDomain("food"));
	public static final RegistryObject<Item> FOOD_COCONUT_MILK = regItem("pack_coconut_milk", () -> new FoodMaterialItemDC(FOOD, "pack_coconut_milk", TagDC.ItemTag.COCONUT_MILK).setDomain("food"));
	public static final RegistryObject<Item> FOOD_CREAM = regItem("pack_cream", () -> new FoodMaterialItemDC(FOOD, "pack_cream", TagDC.ItemTag.CREAM).taste(1).setDomain("food"));
	public static final RegistryObject<Item> FOOD_WHIP = regItem("pack_whip", () -> new FoodMaterialItemDC(FOOD, "pack_whip", TagDC.ItemTag.WHIP).setDomain("food"));
	public static final RegistryObject<Item> FOOD_HONEY = regItem("pack_honey", () -> new FoodMaterialItemDC(FOOD, "pack_honey", TagDC.ItemTag.HONEY).taste(2).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_SYRUP = regItem("pack_syrup", () -> new FoodMaterialItemDC(FOOD, "pack_syrup", TagDC.ItemTag.SYRUP).taste(1).seasoning().setDomain("food"));
	public static final RegistryObject<Item> FOOD_PLANT_OIL = regItem("pack_plant_oil", () -> new FoodMaterialItemDC(FOOD, "pack_plant_oil", TagDC.ItemTag.PLANT_OIL).setDomain("food"));
	public static final RegistryObject<Item> FOOD_WATER = regItem("pack_water", () -> new FoodMaterialItemDC(FOOD, "pack_water", TagDC.ItemTag.WATER).setDomain("food"));
	public static final RegistryObject<Item> FOOD_EMPTY_PACK = regItem("pack_empty", () -> new EmptyPackItem(FOOD, "pack_empty", TagDC.ItemTag.EMPTY_PACK).setDomain("food"));

	// agri

	public static final RegistryObject<Item> SORGHUM_STICK = regItem("stick_sorghum", () -> new MaterialItemDC(AGRI, "stick_sorghum", TagDC.ItemTag.STICK_SORGHUM).setDomain("food"));
	public static final RegistryObject<Item> FOOD_PALM_FLOWER = regItem("food_palm_flower", () -> new FoodMaterialItemDC(AGRI, "food_palm_flower", TagDC.ItemTag.PALM_FLOWER).setDomain("food"));
	public static final RegistryObject<Item> VINE = regItem("food_vine", () -> new MaterialItemDC(AGRI, "food_vine", TagDC.ItemTag.VINE).setDomain("food"));
	public static final RegistryObject<Item> FIBER_PLANT = regItem("fiber_plant", () -> new MaterialItemDC(AGRI, "fiber_plant", TagDC.ItemTag.FIBER_PLANT).setDomain("food"));
	public static final RegistryObject<Item> FIBER_WOOD = regItem("fiber_wood", () -> new MaterialItemDC(AGRI, "fiber_wood", TagDC.ItemTag.FIBER_WOOD).setDomain("food"));
	public static final RegistryObject<Item> BEESWAX = regItem("food_beeswax", () -> new MaterialItemDC(AGRI, "food_beeswax", TagDC.ItemTag.FOOD_WAX).setDomain("food"));

	public static final RegistryObject<Item> STRING_PLANT = regItem("string_plant", () -> new MaterialItemDC(AGRI, "string_plant", Tags.Items.STRING).setDomain("food"));
	public static final RegistryObject<Item> STRING_TREE = regItem("string_tree", () -> new MaterialItemDC(AGRI, "string_tree", Tags.Items.STRING).setDomain("food"));
	public static final RegistryObject<Item> STRING_COTTON = regItem("string_cotton", () -> new MaterialItemDC(AGRI, "string_cotton", Tags.Items.STRING).setDomain("food"));
	public static final RegistryObject<Item> STRING_WOOL = regItem("string_wool", () -> new MaterialItemDC(AGRI, "string_wool", Tags.Items.STRING).setDomain("food"));

	public static final RegistryObject<Item> FOOD_DEFATTED_SOY = regItem("dust_defatted_soy", () -> new MaterialItemDC(AGRI, "dust_defatted_soy", TagDC.ItemTag.DEFATTED_SOY).setDomain("food"));
	public static final RegistryObject<Item> FOOD_PRESS_CAKE = regItem("dust_press_cake", () -> new MaterialItemDC(AGRI, "dust_press_cake", TagDC.ItemTag.PRESS_CAKE).setDomain("food"));

	public static final RegistryObject<Item> FEED_HAY = regItem("animalfeed_hay", () -> new MaterialItemDC(AGRI, "animalfeed_hay", TagDC.ItemTag.FEED_HAY).setDomain("food"));
	public static final RegistryObject<Item> FEED_STRAW = regItem("animalfeed_straw", () -> new MaterialItemDC(AGRI, "animalfeed_straw", TagDC.ItemTag.FEED_STRAW).setDomain("food"));
	public static final RegistryObject<Item> FEED_COMPOUND = regItem("animalfeed_compound", () -> new MaterialItemDC(AGRI, "animalfeed_compound", TagDC.ItemTag.FEED_COMPOUND).setDomain("food"));

	public static final RegistryObject<Item> FERTILIZER_MIXED = regItem("fertilizer_mixed", () -> new MaterialItemDC(AGRI, "fertilizer_mixed", TagDC.ItemTag.FERTILIZER_ADV).setDomain("food"));

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
	public static final RegistryObject<Item> CROP_BR_RAPESEED = regCrop(CropTier.WILD, CropType.BRASSICA, TagDC.ItemTag.CROP_RAPESEED);
	public static final RegistryObject<Item> CROP_BR_GREEN = regCrop(CropTier.COMMON, CropType.BRASSICA, TagDC.ItemTag.CROP_NAPA);
	public static final RegistryObject<Item> CROP_BR_CABAGGE = regCrop(CropTier.RARE, CropType.BRASSICA, TagDC.ItemTag.CROP_CABAGGE, 1, 0F);
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
	public static final RegistryObject<Item> CROP_HB_MINT = regCrop(CropTier.WILD, CropType.HERB, TagDC.ItemTag.CROP_MINT);
	public static final RegistryObject<Item> CROP_HB_BASIL = regCrop(CropTier.COMMON, CropType.HERB, TagDC.ItemTag.CROP_BASIL);
	public static final RegistryObject<Item> CROP_HB_PERILLA = regCrop(CropTier.RARE, CropType.HERB, TagDC.ItemTag.CROP_PERILLA);
	public static final RegistryObject<Item> CROP_HB_LAVENDER = regCrop(CropTier.EPIC, CropType.HERB, TagDC.ItemTag.CROP_LAVENDER);
	public static final RegistryObject<Item> CROP_KN_SORREL = regCrop(CropTier.WILD, CropType.KNOTWEED, TagDC.ItemTag.CROP_SORREL);
	public static final RegistryObject<Item> CROP_KN_BUCKWHEAT = regCrop(CropTier.COMMON, CropType.KNOTWEED, TagDC.ItemTag.CROP_BUCKWHEAT);
	public static final RegistryObject<Item> CROP_KN_INDIGO = regCrop(CropTier.RARE, CropType.KNOTWEED, TagDC.ItemTag.CROP_INDIGO, 0);
	public static final RegistryObject<Item> CROP_ML_JUTE = regCrop(CropTier.WILD, CropType.MALLOW, TagDC.ItemTag.CROP_JUTE);
	public static final RegistryObject<Item> CROP_ML_COTTON = regCrop(CropTier.COMMON, CropType.MALLOW, TagDC.ItemTag.CROP_COTTON);
	public static final RegistryObject<Item> CROP_ML_BLUE = regCrop(CropTier.RARE, CropType.MALLOW, TagDC.ItemTag.CROP_BLUE_MALLOW);
	public static final RegistryObject<Item> CROP_MO_BINDWEED = regCrop(CropTier.WILD, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_BINDWEED);
	public static final RegistryObject<Item> CROP_MO_WATER = regCrop(CropTier.COMMON, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_WATER_SPINACH, 1, 0F);
	public static final RegistryObject<Item> CROP_MO_POTATO = regCrop(CropTier.RARE, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_SWEET_POTATO);
	public static final RegistryObject<Item> CROP_MO_FLOWER = regCrop(CropTier.EPIC, CropType.MORNINGGLORY, TagDC.ItemTag.CROP_MORNING_GLORY, -1);
	public static final RegistryObject<Item> CROP_PD_ROGERIA = regCrop(CropTier.WILD, CropType.PEDARIA, TagDC.ItemTag.CROP_ROGERIA);
	public static final RegistryObject<Item> CROP_PD_SESAMI = regCrop(CropTier.COMMON, CropType.PEDARIA, TagDC.ItemTag.CROP_SESAMI);
	public static final RegistryObject<Item> CROP_PD_DEVILSCLAW = regCrop(CropTier.RARE, CropType.PEDARIA, TagDC.ItemTag.CROP_DEVILSCLAW, 0);
	public static final RegistryObject<Item> CROP_PE_GREEN = regCrop(CropTier.WILD, CropType.PEAS, TagDC.ItemTag.CROP_GARBANZO);
	public static final RegistryObject<Item> CROP_PE_GARBANZO = regCrop(CropTier.COMMON, CropType.PEAS, TagDC.ItemTag.CROP_GREEN_PEAS);
	public static final RegistryObject<Item> CROP_PE_SOY = regCrop(CropTier.RARE, CropType.PEAS, TagDC.ItemTag.CROP_SOY);
	public static final RegistryObject<Item> CROP_PE_ADZUKI = regCrop(CropTier.EPIC, CropType.PEAS, TagDC.ItemTag.CROP_ADZUKI);
	public static final RegistryObject<Item> CROP_RE_COMMON = regCrop(CropTier.WILD, CropType.REED, TagDC.ItemTag.CROP_REED);
	public static final RegistryObject<Item> CROP_RE_SORGHUM = regCrop(CropTier.COMMON, CropType.REED, TagDC.ItemTag.CROP_SORGHUM);
	public static final RegistryObject<Item> CROP_RE_CORN = regCrop(CropTier.RARE, CropType.REED, TagDC.ItemTag.CROP_CORN, 1, 0F);
	public static final RegistryObject<Item> CROP_RI_ZIZANIA = regCrop(CropTier.WILD, CropType.RICE, TagDC.ItemTag.CROP_WILD_RICE);
	public static final RegistryObject<Item> CROP_RI_SHORT = regCrop(CropTier.COMMON, CropType.RICE, TagDC.ItemTag.CROP_RICE);
	public static final RegistryObject<Item> CROP_RI_AROMA = regCrop(CropTier.RARE, CropType.RICE, TagDC.ItemTag.CROP_AROMA_RICE);
	public static final RegistryObject<Item> CROP_SL_NIGHTSHADE = regCrop(CropTier.WILD, CropType.SOLANUM, TagDC.ItemTag.CROP_NIGHTSHADE);
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
	public static final RegistryObject<Item> CROP_CM_SCHIMA = regCrop(CropTier.COMMON, CropType.CAMELLIA, TagDC.ItemTag.CROP_SCHIMA, -1);
	public static final RegistryObject<Item> CROP_CM_TEA = regCrop(CropTier.RARE, CropType.CAMELLIA, TagDC.ItemTag.CROP_TEA);
	public static final RegistryObject<Item> CROP_CN_CINNAMON = regCrop(CropTier.COMMON, CropType.CINNAMON, TagDC.ItemTag.CROP_CINNAMON);
	public static final RegistryObject<Item> CROP_CN_AVOCADO = regCrop(CropTier.RARE, CropType.CINNAMON, TagDC.ItemTag.CROP_AVOCADO, 1, 0F);
	public static final RegistryObject<Item> CROP_CT_POMELO = regCrop(CropTier.WILD, CropType.CITRUS, TagDC.ItemTag.CROP_POMELO, 2, 0F);
	public static final RegistryObject<Item> CROP_CT_MANDARIN = regCrop(CropTier.COMMON, CropType.CITRUS, TagDC.ItemTag.CROP_MANDARIN, 2, 0F);
	public static final RegistryObject<Item> CROP_CT_LEMON = regCrop(CropTier.RARE, CropType.CITRUS, TagDC.ItemTag.CROP_LEMON, 1, 0F);
	public static final RegistryObject<Item> CROP_ER_HEATH = regCrop(CropTier.WILD, CropType.ERICA, TagDC.ItemTag.CROP_HEATH);
	public static final RegistryObject<Item> CROP_ER_RHODODENDRON = regCrop(CropTier.COMMON, CropType.ERICA, TagDC.ItemTag.CROP_RHODODENDRON);
	public static final RegistryObject<Item> CROP_ER_BLUEBERRY = regCrop(CropTier.RARE, CropType.ERICA, TagDC.ItemTag.CROP_BLUEBERRY, 2, 0F);
	public static final RegistryObject<Item> CROP_MR_MULBERRY = regCrop(CropTier.WILD, CropType.MORUS, TagDC.ItemTag.CROP_MULBERRY, 0);
	public static final RegistryObject<Item> CROP_MR_PAPER = regCrop(CropTier.COMMON, CropType.MORUS, TagDC.ItemTag.CROP_KAJI);
	public static final RegistryObject<Item> CROP_MR_RUBBER = regCrop(CropTier.RARE, CropType.MORUS, TagDC.ItemTag.CROP_RUBBER, 0);
	public static final RegistryObject<Item> CROP_OL_ASH = regCrop(CropTier.WILD, CropType.OLIVE, TagDC.ItemTag.CROP_ASH);
	public static final RegistryObject<Item> CROP_OL_OLIVE = regCrop(CropTier.COMMON, CropType.OLIVE, TagDC.ItemTag.CROP_OLIVE);
	public static final RegistryObject<Item> CROP_OL_OSMANTHUS = regCrop(CropTier.RARE, CropType.OLIVE, TagDC.ItemTag.CROP_OSUMANTHUS, -1);
	public static final RegistryObject<Item> CROP_PL_COCONUT = regCrop(CropTier.WILD, CropType.PALM, TagDC.ItemTag.CROP_COCONUT, 2, 0F);
	public static final RegistryObject<Item> CROP_PL_DATE = regCrop(CropTier.COMMON, CropType.PALM, TagDC.ItemTag.CROP_DATE, 2, 0F);
	public static final RegistryObject<Item> CROP_PL_OIL = regCrop(CropTier.RARE, CropType.PALM, TagDC.ItemTag.CROP_OIL_PALM, 0);

	public static final RegistryObject<Block> BLOCK_AL_WILD = regSeed(CropTier.WILD, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.WILD), TagDC.ItemTag.SEED_CHIVES);
	public static final RegistryObject<Block> BLOCK_AL_ONION = regSeed(CropTier.COMMON, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.COMMON), TagDC.ItemTag.SEED_ONION);
	public static final RegistryObject<Block> BLOCK_AL_GARLIC = regSeed(CropTier.RARE, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.RARE), TagDC.ItemTag.SEED_GARLIC);
	public static final RegistryObject<Block> BLOCK_AM_GOOSEFOOT = regSeed(CropTier.WILD, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.WILD), TagDC.ItemTag.SEED_GOOSEFOOT);
	public static final RegistryObject<Block> BLOCK_AM_GLASSWORT = regSeed(CropTier.COMMON, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.COMMON), TagDC.ItemTag.SEED_GLASSWORT);
	public static final RegistryObject<Block> BLOCK_AM_SPINACH = regSeed(CropTier.RARE, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.RARE), TagDC.ItemTag.SEED_SPINACH);
	public static final RegistryObject<Block> BLOCK_AP_CELERY = regSeed(CropTier.WILD, CropType.APIUM, () -> new CropBlockApium(CropTier.WILD), TagDC.ItemTag.SEED_CELERY);
	public static final RegistryObject<Block> BLOCK_AP_FENNEL = regSeed(CropTier.COMMON, CropType.APIUM, () -> new CropBlockApium(CropTier.COMMON), TagDC.ItemTag.SEED_FENNEL);
	public static final RegistryObject<Block> BLOCK_AP_PARSNIP = regSeed(CropTier.RARE, CropType.APIUM, () -> new CropBlockApium(CropTier.RARE), TagDC.ItemTag.SEED_PARSNIP);
	public static final RegistryObject<Block> BLOCK_BR_RAPESEED = regSeed(CropTier.WILD, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.WILD), TagDC.ItemTag.SEED_RAPESEED);
	public static final RegistryObject<Block> BLOCK_BR_GREEN = regSeed(CropTier.COMMON, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.COMMON), TagDC.ItemTag.SEED_NAPA);
	public static final RegistryObject<Block> BLOCK_BR_CABAGGE = regSeed(CropTier.RARE, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.RARE), TagDC.ItemTag.SEED_CABAGGE);
	public static final RegistryObject<Block> BLOCK_BR_RADISH = regSeed(CropTier.EPIC, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.EPIC), TagDC.ItemTag.SEED_RADISH);
	public static final RegistryObject<Block> BLOCK_CA_CHILI = regSeed(CropTier.WILD, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.WILD), TagDC.ItemTag.SEED_CHILI);
	public static final RegistryObject<Block> BLOCK_CA_BELL = regSeed(CropTier.COMMON, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.RARE), TagDC.ItemTag.SEED_BELL);
	public static final RegistryObject<Block> BLOCK_CA_PAPRIKA = regSeed(CropTier.RARE, CropType.CAPSICUM, () -> new CropBlockCapsicum(CropTier.COMMON), TagDC.ItemTag.SEED_PAPRIKA);
	public static final RegistryObject<Block> BLOCK_CR_OAT = regSeed(CropTier.WILD, CropType.CEREALS, () -> new CropBlockCereals(CropTier.WILD), TagDC.ItemTag.SEED_OAT);
	public static final RegistryObject<Block> BLOCK_CR_RYE = regSeed(CropTier.COMMON, CropType.CEREALS, () -> new CropBlockCereals(CropTier.COMMON), TagDC.ItemTag.SEED_RYE);
	public static final RegistryObject<Block> BLOCK_CR_BARLEY = regSeed(CropTier.RARE, CropType.CEREALS, () -> new CropBlockCereals(CropTier.RARE), TagDC.ItemTag.SEED_BARLEY);
	public static final RegistryObject<Block> BLOCK_GN_COMMON = regSeed(CropTier.WILD, CropType.GINGER, () -> new CropBlockGinger(CropTier.WILD), TagDC.ItemTag.SEED_GINGER);
	public static final RegistryObject<Block> BLOCK_GN_CARDAMOM = regSeed(CropTier.COMMON, CropType.GINGER, () -> new CropBlockGinger(CropTier.COMMON), TagDC.ItemTag.SEED_CARDAMOM);
	public static final RegistryObject<Block> BLOCK_GN_TURMERIC = regSeed(CropTier.RARE, CropType.GINGER, () -> new CropBlockGinger(CropTier.RARE), TagDC.ItemTag.SEED_TURMERIC);
	public static final RegistryObject<Block> BLOCK_HB_MINT = regSeed(CropTier.WILD, CropType.HERB, () -> new CropBlockHerb(CropTier.WILD), TagDC.ItemTag.SEED_MINT);
	public static final RegistryObject<Block> BLOCK_HB_BASIL = regSeed(CropTier.COMMON, CropType.HERB, () -> new CropBlockHerb(CropTier.COMMON), TagDC.ItemTag.SEED_BASIL);
	public static final RegistryObject<Block> BLOCK_HB_PERILLA = regSeed(CropTier.RARE, CropType.HERB, () -> new CropBlockHerb(CropTier.RARE), TagDC.ItemTag.SEED_PERILLA);
	public static final RegistryObject<Block> BLOCK_HB_LAVENDER = regSeed(CropTier.EPIC, CropType.HERB, () -> new CropBlockHerb(CropTier.EPIC), TagDC.ItemTag.SEED_LAVENDER);
	public static final RegistryObject<Block> BLOCK_KN_SORREL = regSeed(CropTier.WILD, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.WILD), TagDC.ItemTag.SEED_SORREL);
	public static final RegistryObject<Block> BLOCK_KN_BUCKWHEAT = regSeed(CropTier.COMMON, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.COMMON), TagDC.ItemTag.SEED_BUCKWHEAT);
	public static final RegistryObject<Block> BLOCK_KN_INDIGO = regSeed(CropTier.RARE, CropType.KNOTWEED, () -> new CropBlockKnotweed(CropTier.RARE), TagDC.ItemTag.SEED_INDIGO);
	public static final RegistryObject<Block> BLOCK_ML_JUTE = regSeed(CropTier.WILD, CropType.MALLOW, () -> new CropBlockMallow(CropTier.WILD), TagDC.ItemTag.SEED_JUTE);
	public static final RegistryObject<Block> BLOCK_ML_COTTON = regSeed(CropTier.COMMON, CropType.MALLOW, () -> new CropBlockMallow(CropTier.COMMON), TagDC.ItemTag.SEED_COTTON);
	public static final RegistryObject<Block> BLOCK_ML_BLUE = regSeed(CropTier.RARE, CropType.MALLOW, () -> new CropBlockMallow(CropTier.RARE), TagDC.ItemTag.SEED_BLUE_MALLOW);
	public static final RegistryObject<Block> BLOCK_MO_BINDWEED = regSeed(CropTier.WILD, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.WILD), TagDC.ItemTag.SEED_BINDWEED);
	public static final RegistryObject<Block> BLOCK_MO_WATER = regSeed(CropTier.COMMON, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.COMMON), TagDC.ItemTag.SEED_WATER_SPINACH);
	public static final RegistryObject<Block> BLOCK_MO_POTATO = regSeed(CropTier.RARE, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.RARE), TagDC.ItemTag.SEED_SWEET_POTATO);
	public static final RegistryObject<Block> BLOCK_MO_FLOWER = regSeed(CropTier.EPIC, CropType.MORNINGGLORY, () -> new CropBlockMorningGlory(CropTier.EPIC), TagDC.ItemTag.SEED_MORNING_GLORY);
	public static final RegistryObject<Block> BLOCK_PE_GREEN = regSeed(CropTier.WILD, CropType.PEAS, () -> new CropBlockPeas(CropTier.WILD), TagDC.ItemTag.SEED_GREEN_PEAS);
	public static final RegistryObject<Block> BLOCK_PD_ROGERIA = regSeed(CropTier.WILD, CropType.PEDARIA, () -> new CropBlockPedaria(CropTier.WILD), TagDC.ItemTag.SEED_ROGERIA);
	public static final RegistryObject<Block> BLOCK_PD_SESAMI = regSeed(CropTier.COMMON, CropType.PEDARIA, () -> new CropBlockPedaria(CropTier.COMMON), TagDC.ItemTag.SEED_SESAMI);
	public static final RegistryObject<Block> BLOCK_PD_DEVILSCLAW = regSeed(CropTier.RARE, CropType.PEDARIA, () -> new CropBlockPedaria(CropTier.RARE), TagDC.ItemTag.SEED_DEVILSCLAW);
	public static final RegistryObject<Block> BLOCK_PE_GARBANZO = regSeed(CropTier.COMMON, CropType.PEAS, () -> new CropBlockPeas(CropTier.COMMON), TagDC.ItemTag.SEED_GARBANZO);
	public static final RegistryObject<Block> BLOCK_PE_SOY = regSeed(CropTier.RARE, CropType.PEAS, () -> new CropBlockPeas(CropTier.RARE), TagDC.ItemTag.SEED_SOY);
	public static final RegistryObject<Block> BLOCK_PE_ADZUKI = regSeed(CropTier.EPIC, CropType.PEAS, () -> new CropBlockPeas(CropTier.EPIC), TagDC.ItemTag.SEED_ADZUKI);
	public static final RegistryObject<Block> BLOCK_RE_COMMON = regSeed(CropTier.WILD, CropType.REED, () -> new CropBlockReed(CropTier.WILD), TagDC.ItemTag.SEED_REED);
	public static final RegistryObject<Block> BLOCK_RE_SORGHUM = regSeed(CropTier.COMMON, CropType.REED, () -> new CropBlockReed(CropTier.COMMON), TagDC.ItemTag.SEED_SORGHUM);
	public static final RegistryObject<Block> BLOCK_RE_CORN = regSeed(CropTier.RARE, CropType.REED, () -> new CropBlockReed(CropTier.RARE), TagDC.ItemTag.SEED_CORN);
	public static final RegistryObject<Block> BLOCK_RI_ZIZANIA = regSeed(CropTier.WILD, CropType.RICE, () -> new CropBlockRice(CropTier.WILD), TagDC.ItemTag.SEED_WILD_RICE);
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
	public static final RegistryObject<Block> BLOCK_ER_HEATH = regCropBlock(CropTier.WILD, CropType.ERICA, () -> new SaplingErica(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_ER_RHODODENDRON = regCropBlock(CropTier.COMMON, CropType.ERICA, () -> new SaplingErica(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_ER_BLUEBERRY = regCropBlock(CropTier.RARE, CropType.ERICA, () -> new SaplingErica(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MR_MULBERRY = regCropBlock(CropTier.WILD, CropType.MORUS, () -> new SaplingMorus(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MR_PAPER = regCropBlock(CropTier.COMMON, CropType.MORUS, () -> new SaplingMorus(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_MR_RUBBER = regCropBlock(CropTier.RARE, CropType.MORUS, () -> new SaplingMorus(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_OL_ASH = regCropBlock(CropTier.WILD, CropType.OLIVE, () -> new SaplingOlive(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_OL_OLIVE = regCropBlock(CropTier.COMMON, CropType.OLIVE, () -> new SaplingOlive(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_OL_OSMANTHUS = regCropBlock(CropTier.RARE, CropType.OLIVE, () -> new SaplingOlive(CropTier.RARE), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_PL_COCONUT = regCropBlock(CropTier.WILD, CropType.PALM, () -> new SaplingPalm(CropTier.WILD), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_PL_DATE = regCropBlock(CropTier.COMMON, CropType.PALM, () -> new SaplingPalm(CropTier.COMMON), "sapling", ItemTags.SAPLINGS);
	public static final RegistryObject<Block> BLOCK_PL_OIL = regCropBlock(CropTier.RARE, CropType.PALM, () -> new SaplingPalm(CropTier.RARE), "sapling", ItemTags.SAPLINGS);

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
	public static final RegistryObject<Block> LEAVES_ER_HEATH = regBlock("leaves_erica_heath", () -> new LeavesHeath(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_ER_RHODODENDRON = regBlock("leaves_erica_rhododendron", () -> new LeavesRhododendron(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_ER_BLUEBERRY = regBlock("leaves_erica_blueberry", () -> new LeavesBlueberry(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MR_MULBERRY = regBlock("leaves_morus_mulberry", () -> new LeavesMulberry(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MR_PAPER = regBlock("leaves_morus_paper", () -> new LeavesPaper(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_MR_RUBBER = regBlock("leaves_morus_rubber", () -> new LeavesRubber(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_OL_ASH = regBlock("leaves_olive_ash", () -> new LeavesAsh(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_OL_OLIVE = regBlock("leaves_olive_olive", () -> new LeavesOlive(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_OL_OSMANTHUS = regBlock("leaves_olive_osmanthus", () -> new LeavesOsmanthus(), ItemTags.LEAVES);
	public static final RegistryObject<Block> LEAVES_PL_COMMON = regBlock("leaves_palm", () -> new LeavesPalm(), ItemTags.LEAVES);

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
	public static final RegistryObject<Block> LOG_OL_ASH = regBlock("log_olive_ash", () -> new LogBlockDC("olive_ash"), ItemTags.LOGS_THAT_BURN);
	public static final RegistryObject<Block> LOG_PL_COCONUT = regBlock("log_palm_coconut", () -> new LogBlockDC("palm_coconut"), ItemTags.LOGS_THAT_BURN);

	public static final RegistryObject<Block> PLANK_BH_COMMON = regBlock("plank_beech_common", () -> new PlankBlockDC("plank_beech_common"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_BH_WALNUT = regBlock("plank_beech_walnut", () -> new PlankBlockDC("plank_beech_walnut"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_BH_SWEET = regBlock("plank_beech_sweet", () -> new PlankBlockDC("plank_beech_sweet"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_CH_WILD = regBlock("plank_cherry_wild", () -> new PlankBlockDC("plank_cherry_wild"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_CN_CAMPHOR = regBlock("plank_cinnamon_camphor", () -> new PlankBlockDC("plank_cinnamon_camphor"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_CT_POMELO = regBlock("plank_citrus_pomelo", () -> new PlankBlockDC("plank_citrus_pomelo"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_MR_MULBERRY = regBlock("plank_morus_mulberry", () -> new PlankBlockDC("plank_morus_mulberry"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_OL_ASH = regBlock("plank_olive_ash", () -> new PlankBlockDC("plank_olive_ash"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_PL_COCONUT = regBlock("plank_palm_coconut", () -> new PlankBlockDC("plank_palm_coconut"), ItemTags.PLANKS);
	public static final RegistryObject<Block> PLANK_RE_SORGHUM = regBlock("plank_reed_sorghum", () -> new PlankBlockDC("plank_reed_sorghum"), ItemTags.PLANKS);

	public static final RegistryObject<Block> FERTILE = regBlock("fertile", () -> new FertileBlock(), null);

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag));
	}

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag, int f, float s) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag, f, s));
	}

	public static RegistryObject<Item> regCrop(CropTier tier, CropType type, TagKey<Item> tag, int taste) {
		String name = "crop_" + type.toString() + "_" + tier.toString();
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name, tag).taste(taste));
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("food/" + name, item);
	}

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("food/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(AGRI), tag));
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
