package defeatedcrow.hac.magic.material;

import java.util.function.Supplier;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.entity.ChairEntity;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Magic;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.client.gui.BoringMenu;
import defeatedcrow.hac.magic.material.block.MagicSmallLight;
import defeatedcrow.hac.magic.material.entity.ArrowBindPlant;
import defeatedcrow.hac.magic.material.entity.ArrowBlack;
import defeatedcrow.hac.magic.material.entity.ArrowBlue;
import defeatedcrow.hac.magic.material.entity.ArrowGreen;
import defeatedcrow.hac.magic.material.entity.ArrowRed;
import defeatedcrow.hac.magic.material.entity.ArrowRobber;
import defeatedcrow.hac.magic.material.entity.ArrowWhite;
import defeatedcrow.hac.magic.material.entity.CrowTurretEntity;
import defeatedcrow.hac.magic.material.entity.PhoenixLightEntity;
import defeatedcrow.hac.magic.material.entity.SilkyFairyEntity;
import defeatedcrow.hac.magic.material.item.BoringSurveyItem;
import defeatedcrow.hac.magic.material.item.ColorSeedBagItem;
import defeatedcrow.hac.magic.material.item.InertElementItem;
import defeatedcrow.hac.magic.material.item.MagicElementItem;
import defeatedcrow.hac.magic.material.item.MagicMaterialItemDC;
import defeatedcrow.hac.magic.material.item.arrow.BlackArrowItem;
import defeatedcrow.hac.magic.material.item.arrow.BlueArrowItem;
import defeatedcrow.hac.magic.material.item.arrow.GreenArrowItem;
import defeatedcrow.hac.magic.material.item.arrow.RedArrowItem;
import defeatedcrow.hac.magic.material.item.arrow.WhiteArrowItem;
import defeatedcrow.hac.magic.material.item.card.CardBlackRed;
import defeatedcrow.hac.magic.material.item.card.CardBlackT1;
import defeatedcrow.hac.magic.material.item.card.CardBlackT2;
import defeatedcrow.hac.magic.material.item.card.CardBlackWhite;
import defeatedcrow.hac.magic.material.item.card.CardBlueBlack;
import defeatedcrow.hac.magic.material.item.card.CardBlueGreen;
import defeatedcrow.hac.magic.material.item.card.CardBlueT1;
import defeatedcrow.hac.magic.material.item.card.CardBlueT2;
import defeatedcrow.hac.magic.material.item.card.CardGreenBlack;
import defeatedcrow.hac.magic.material.item.card.CardGreenT1;
import defeatedcrow.hac.magic.material.item.card.CardGreenT2;
import defeatedcrow.hac.magic.material.item.card.CardGreenWhite;
import defeatedcrow.hac.magic.material.item.card.CardRedBlue;
import defeatedcrow.hac.magic.material.item.card.CardRedGreen;
import defeatedcrow.hac.magic.material.item.card.CardRedT1;
import defeatedcrow.hac.magic.material.item.card.CardRedT2;
import defeatedcrow.hac.magic.material.item.card.CardWhiteBlue;
import defeatedcrow.hac.magic.material.item.card.CardWhiteRed;
import defeatedcrow.hac.magic.material.item.card.CardWhiteT1;
import defeatedcrow.hac.magic.material.item.card.CardWhiteT2;
import defeatedcrow.hac.magic.material.item.jems.GoldPendant;
import defeatedcrow.hac.magic.material.item.jems.GoldRing;
import defeatedcrow.hac.magic.material.item.jems.SilverBadge;
import defeatedcrow.hac.magic.material.item.jems.SilverPendant;
import defeatedcrow.hac.magic.material.item.jems.SilverRing;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;

public class MagicInit {

	public static final CreativeModeTab MAGIC = new CreativeTabClimate_Magic("magic");

	public static void init() {}

	public static final RegistryObject<EntityType<ArrowWhite>> ARROW_WHITE_ENTITY = CoreInit.ENTITIES.register("arrow_white", () -> EntityType.Builder.<ArrowWhite>of(ArrowWhite::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("arrow_white"));
	public static final RegistryObject<EntityType<ArrowBlue>> ARROW_BLUE_ENTITY = CoreInit.ENTITIES.register("arrow_blue", () -> EntityType.Builder.<ArrowBlue>of(ArrowBlue::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("arrow_blue"));
	public static final RegistryObject<EntityType<ArrowBlack>> ARROW_BLACK_ENTITY = CoreInit.ENTITIES.register("arrow_black", () -> EntityType.Builder.<ArrowBlack>of(ArrowBlack::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("arrow_black"));
	public static final RegistryObject<EntityType<ArrowRed>> ARROW_RED_ENTITY = CoreInit.ENTITIES.register("arrow_red", () -> EntityType.Builder.<ArrowRed>of(ArrowRed::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("arrow_red"));
	public static final RegistryObject<EntityType<ArrowGreen>> ARROW_GREEN_ENTITY = CoreInit.ENTITIES.register("arrow_green", () -> EntityType.Builder.<ArrowGreen>of(ArrowGreen::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("arrow_green"));

	public static final RegistryObject<EntityType<ArrowRobber>> ARROW_ROBBER_ENTITY = CoreInit.ENTITIES.register("arrow_robber", () -> EntityType.Builder.<ArrowRobber>of(ArrowRobber::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("arrow_robber"));
	public static final RegistryObject<EntityType<ArrowBindPlant>> ARROW_BIND_ENTITY = CoreInit.ENTITIES.register("arrow_bind", () -> EntityType.Builder.<ArrowBindPlant>of(ArrowBindPlant::new, MobCategory.MISC)
		.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("arrow_bind"));

	public static final RegistryObject<EntityType<ChairEntity>> BIND_PLANT_ENTITY = CoreInit.ENTITIES.register("bind_plant", () -> EntityType.Builder.<ChairEntity>of(ChairEntity::new, MobCategory.MISC)
		.sized(1.0F, 0.25F).clientTrackingRange(4).updateInterval(20).build("bind_plant"));
	public static final RegistryObject<EntityType<ChairEntity>> BIND_ELECTRIC_ENTITY = CoreInit.ENTITIES.register("bind_electric", () -> EntityType.Builder.<ChairEntity>of(ChairEntity::new, MobCategory.MISC)
		.sized(1.0F, 0.25F).clientTrackingRange(8).updateInterval(20).build("bind_electric"));

	public static final RegistryObject<EntityType<PhoenixLightEntity>> PHOENIX_LIGHT_ENTITY = CoreInit.ENTITIES.register("phoenix_light", () -> EntityType.Builder.<PhoenixLightEntity>of(PhoenixLightEntity::new,
		MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(12).updateInterval(20).build("phoenix_light"));

	public static final RegistryObject<EntityType<SilkyFairyEntity>> SILKY_FAIRY_ENTITY = CoreInit.ENTITIES.register("silky_fairy", () -> EntityType.Builder.<SilkyFairyEntity>of(SilkyFairyEntity::new,
		MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(8).updateInterval(5).build("silky_fairy"));

	public static final RegistryObject<EntityType<CrowTurretEntity>> CROW_TURRET = CoreInit.ENTITIES.register("crow_turret", () -> EntityType.Builder.<CrowTurretEntity>of(CrowTurretEntity::new,
		MobCategory.CREATURE).sized(1.0F, 1.0F).clientTrackingRange(32).updateInterval(5).build("crow_turret"));

	public static final RegistryObject<Item> DROP_WHITE = regItem("drop_w", () -> new MagicMaterialItemDC(MagicColor.WHITE, "drop_w", Rarity.COMMON, TagDC.ItemTag.DROP_WHITE).setDomain("magic"));
	public static final RegistryObject<Item> DROP_BLUE = regItem("drop_u", () -> new MagicMaterialItemDC(MagicColor.BLUE, "drop_u", Rarity.COMMON, TagDC.ItemTag.DROP_BLUE).setDomain("magic"));
	public static final RegistryObject<Item> DROP_BLACK = regItem("drop_b", () -> new MagicMaterialItemDC(MagicColor.BLACK, "drop_b", Rarity.COMMON, TagDC.ItemTag.DROP_BLACK).setDomain("magic"));
	public static final RegistryObject<Item> DROP_RED = regItem("drop_r", () -> new MagicMaterialItemDC(MagicColor.RED, "drop_r", Rarity.COMMON, TagDC.ItemTag.DROP_RED).setDomain("magic"));
	public static final RegistryObject<Item> DROP_GREEN = regItem("drop_g", () -> new MagicMaterialItemDC(MagicColor.GREEN, "drop_g", Rarity.COMMON, TagDC.ItemTag.DROP_GREEN).setDomain("magic"));

	public static final RegistryObject<Item> EXTRACT_WHITE = regItem("extract_w", () -> new MagicMaterialItemDC(MagicColor.WHITE, "extract_w", Rarity.COMMON, TagDC.ItemTag.EXTRACT_WHITE).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_BLUE = regItem("extract_u", () -> new MagicMaterialItemDC(MagicColor.BLUE, "extract_u", Rarity.COMMON, TagDC.ItemTag.EXTRACT_BLUE).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_BLACK = regItem("extract_b", () -> new MagicMaterialItemDC(MagicColor.BLACK, "extract_b", Rarity.COMMON, TagDC.ItemTag.EXTRACT_BLACK).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_RED = regItem("extract_r", () -> new MagicMaterialItemDC(MagicColor.RED, "extract_r", Rarity.COMMON, TagDC.ItemTag.EXTRACT_RED).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_GREEN = regItem("extract_g", () -> new MagicMaterialItemDC(MagicColor.GREEN, "extract_g", Rarity.COMMON, TagDC.ItemTag.EXTRACT_GREEN).setDomain("magic"));

	public static final RegistryObject<Item> PIGMENT_WHITE = regItem("pigment_w", () -> new MagicMaterialItemDC(MagicColor.WHITE, "pigment_w", Rarity.UNCOMMON, TagDC.ItemTag.PIGMENT_WHITE).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_BLUE = regItem("pigment_u", () -> new MagicMaterialItemDC(MagicColor.BLUE, "pigment_u", Rarity.UNCOMMON, TagDC.ItemTag.PIGMENT_BLUE).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_BLACK = regItem("pigment_b", () -> new MagicMaterialItemDC(MagicColor.BLACK, "pigment_b", Rarity.UNCOMMON, TagDC.ItemTag.PIGMENT_BLACK).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_RED = regItem("pigment_r", () -> new MagicMaterialItemDC(MagicColor.RED, "pigment_r", Rarity.UNCOMMON, TagDC.ItemTag.PIGMENT_RED).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_GREEN = regItem("pigment_g", () -> new MagicMaterialItemDC(MagicColor.GREEN, "pigment_g", Rarity.UNCOMMON, TagDC.ItemTag.PIGMENT_GREEN).setDomain("magic"));

	public static final RegistryObject<Item> ELEMENT_WHITE_INERT = regItem("element_w_inert", () -> new InertElementItem(MagicColor.WHITE, "element_w_inert", TagDC.ItemTag.ELEMENT_INERT).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_BLUE_INERT = regItem("element_u_inert", () -> new InertElementItem(MagicColor.BLUE, "element_u_inert", TagDC.ItemTag.ELEMENT_INERT).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_BLACK_INERT = regItem("element_b_inert", () -> new InertElementItem(MagicColor.BLACK, "element_b_inert", TagDC.ItemTag.ELEMENT_INERT).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_RED_INERT = regItem("element_r_inert", () -> new InertElementItem(MagicColor.RED, "element_r_inert", TagDC.ItemTag.ELEMENT_INERT).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_GREEN_INERT = regItem("element_g_inert", () -> new InertElementItem(MagicColor.GREEN, "element_g_inert", TagDC.ItemTag.ELEMENT_INERT).setDomain("magic"));

	public static final RegistryObject<Item> ELEMENT_WHITE = regItem("element_w", () -> new MagicElementItem(MagicColor.WHITE, "element_w", TagDC.ItemTag.ELEMENT_WHITE).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_BLUE = regItem("element_u", () -> new MagicElementItem(MagicColor.BLUE, "element_u", TagDC.ItemTag.ELEMENT_BLUE).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_BLACK = regItem("element_b", () -> new MagicElementItem(MagicColor.BLACK, "element_b", TagDC.ItemTag.ELEMENT_BLACK).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_RED = regItem("element_r", () -> new MagicElementItem(MagicColor.RED, "element_r", TagDC.ItemTag.ELEMENT_RED).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_GREEN = regItem("element_g", () -> new MagicElementItem(MagicColor.GREEN, "element_g", TagDC.ItemTag.ELEMENT_GREEN).setDomain("magic"));

	public static final RegistryObject<Item> DROP_MANA = regItem("drop_mana", () -> new MagicMaterialItemDC(MagicColor.NONE, "drop_mana", Rarity.COMMON, TagDC.ItemTag.MANA_DROPS).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_MANA = regItem("extract_mana", () -> new MagicMaterialItemDC(MagicColor.NONE, "extract_mana", Rarity.UNCOMMON, TagDC.ItemTag.MANA_EXTRACT).setDomain("magic"));
	public static final RegistryObject<Item> ELEMENT_MANA = regItem("element_mana", () -> new MagicMaterialItemDC(MagicColor.NONE, "element_mana", Rarity.RARE, TagDC.ItemTag.MANA_ELEMENT).setDomain("magic"));

	public static final RegistryObject<Item> ARROW_WHITE = regItem("arrow_white", () -> new WhiteArrowItem());
	public static final RegistryObject<Item> ARROW_BLUE = regItem("arrow_blue", () -> new BlueArrowItem());
	public static final RegistryObject<Item> ARROW_BLACK = regItem("arrow_black", () -> new BlackArrowItem());
	public static final RegistryObject<Item> ARROW_RED = regItem("arrow_red", () -> new RedArrowItem());
	public static final RegistryObject<Item> ARROW_GREEN = regItem("arrow_green", () -> new GreenArrowItem());

	public static final RegistryObject<Item> SEEDBAG_WHITE = regItem("seedbag_white", () -> new ColorSeedBagItem(MagicColor.WHITE));
	public static final RegistryObject<Item> SEEDBAG_BLUE = regItem("seedbag_blue", () -> new ColorSeedBagItem(MagicColor.BLUE));
	public static final RegistryObject<Item> SEEDBAG_BLACK = regItem("seedbag_black", () -> new ColorSeedBagItem(MagicColor.BLACK));
	public static final RegistryObject<Item> SEEDBAG_RED = regItem("seedbag_red", () -> new ColorSeedBagItem(MagicColor.RED));
	public static final RegistryObject<Item> SEEDBAG_GREEN = regItem("seedbag_green", () -> new ColorSeedBagItem(MagicColor.GREEN));

	public static final RegistryObject<Item> CARD_WHITE_1 = regItem("card_white_common", () -> new CardWhiteT1());
	public static final RegistryObject<Item> CARD_BLUE_1 = regItem("card_blue_common", () -> new CardBlueT1());
	public static final RegistryObject<Item> CARD_BLACK_1 = regItem("card_black_common", () -> new CardBlackT1());
	public static final RegistryObject<Item> CARD_RED_1 = regItem("card_red_common", () -> new CardRedT1());
	public static final RegistryObject<Item> CARD_GREEN_1 = regItem("card_green_common", () -> new CardGreenT1());

	public static final RegistryObject<Item> CARD_WHITE_2 = regItem("card_white_uncommon", () -> new CardWhiteT2());
	public static final RegistryObject<Item> CARD_BLUE_2 = regItem("card_blue_uncommon", () -> new CardBlueT2());
	public static final RegistryObject<Item> CARD_BLACK_2 = regItem("card_black_uncommon", () -> new CardBlackT2());
	public static final RegistryObject<Item> CARD_RED_2 = regItem("card_red_uncommon", () -> new CardRedT2());
	public static final RegistryObject<Item> CARD_GREEN_2 = regItem("card_green_uncommon", () -> new CardGreenT2());

	public static final RegistryObject<Item> CARD_WR = regItem("card_white_red", () -> new CardWhiteRed());
	public static final RegistryObject<Item> CARD_UG = regItem("card_blue_green", () -> new CardBlueGreen());
	public static final RegistryObject<Item> CARD_BW = regItem("card_black_white", () -> new CardBlackWhite());
	public static final RegistryObject<Item> CARD_RU = regItem("card_red_blue", () -> new CardRedBlue());
	public static final RegistryObject<Item> CARD_GB = regItem("card_green_black", () -> new CardGreenBlack());

	public static final RegistryObject<Item> CARD_WU = regItem("card_white_blue", () -> new CardWhiteBlue());
	public static final RegistryObject<Item> CARD_UB = regItem("card_blue_black", () -> new CardBlueBlack());
	public static final RegistryObject<Item> CARD_BR = regItem("card_black_red", () -> new CardBlackRed());
	public static final RegistryObject<Item> CARD_RG = regItem("card_red_green", () -> new CardRedGreen());
	public static final RegistryObject<Item> CARD_GW = regItem("card_green_white", () -> new CardGreenWhite());

	public static final RegistryObject<Item> RING_SILVER_WHITE = regItem("ring_s_white", () -> new SilverRing(MagicColor.WHITE));
	public static final RegistryObject<Item> RING_SILVER_BLUE = regItem("ring_s_blue", () -> new SilverRing(MagicColor.BLUE));
	public static final RegistryObject<Item> RING_SILVER_BLACK = regItem("ring_s_black", () -> new SilverRing(MagicColor.BLACK));
	public static final RegistryObject<Item> RING_SILVER_RED = regItem("ring_s_red", () -> new SilverRing(MagicColor.RED));
	public static final RegistryObject<Item> RING_SILVER_GREEN = regItem("ring_s_green", () -> new SilverRing(MagicColor.GREEN));

	public static final RegistryObject<Item> RING_GOLD_WHITE = regItem("ring_g_white", () -> new GoldRing(MagicColor.WHITE));
	public static final RegistryObject<Item> RING_GOLD_BLUE = regItem("ring_g_blue", () -> new GoldRing(MagicColor.BLUE));
	public static final RegistryObject<Item> RING_GOLD_BLACK = regItem("ring_g_black", () -> new GoldRing(MagicColor.BLACK));
	public static final RegistryObject<Item> RING_GOLD_RED = regItem("ring_g_red", () -> new GoldRing(MagicColor.RED));
	public static final RegistryObject<Item> RING_GOLD_GREEN = regItem("ring_g_green", () -> new GoldRing(MagicColor.GREEN));

	public static final RegistryObject<Item> PENDANT_SILVER_WHITE = regItem("pendant_s_white", () -> new SilverPendant(MagicColor.WHITE));
	public static final RegistryObject<Item> PENDANT_SILVER_BLUE = regItem("pendant_s_blue", () -> new SilverPendant(MagicColor.BLUE));
	public static final RegistryObject<Item> PENDANT_SILVER_BLACK = regItem("pendant_s_black", () -> new SilverPendant(MagicColor.BLACK));
	public static final RegistryObject<Item> PENDANT_SILVER_RED = regItem("pendant_s_red", () -> new SilverPendant(MagicColor.RED));
	public static final RegistryObject<Item> PENDANT_SILVER_GREEN = regItem("pendant_s_green", () -> new SilverPendant(MagicColor.GREEN));

	public static final RegistryObject<Item> PENDANT_GOLD_WHITE = regItem("pendant_g_white", () -> new GoldPendant(MagicColor.WHITE));
	public static final RegistryObject<Item> PENDANT_GOLD_BLUE = regItem("pendant_g_blue", () -> new GoldPendant(MagicColor.BLUE));
	public static final RegistryObject<Item> PENDANT_GOLD_BLACK = regItem("pendant_g_black", () -> new GoldPendant(MagicColor.BLACK));
	public static final RegistryObject<Item> PENDANT_GOLD_RED = regItem("pendant_g_red", () -> new GoldPendant(MagicColor.RED));
	public static final RegistryObject<Item> PENDANT_GOLD_GREEN = regItem("pendant_g_green", () -> new GoldPendant(MagicColor.GREEN));

	public static final RegistryObject<Item> BADGE_SILVER_WHITE = regItem("badge_s_white", () -> new SilverBadge(MagicColor.WHITE));
	public static final RegistryObject<Item> BADGE_SILVER_BLUE = regItem("badge_s_blue", () -> new SilverBadge(MagicColor.BLUE));
	public static final RegistryObject<Item> BADGE_SILVER_BLACK = regItem("badge_s_black", () -> new SilverBadge(MagicColor.BLACK));
	public static final RegistryObject<Item> BADGE_SILVER_RED = regItem("badge_s_red", () -> new SilverBadge(MagicColor.RED));
	public static final RegistryObject<Item> BADGE_SILVER_GREEN = regItem("badge_s_green", () -> new SilverBadge(MagicColor.GREEN));

	public static final RegistryObject<Item> DOCUMENT_BORING = regItem("document_boring_survey", () -> new BoringSurveyItem());

	public static final RegistryObject<Block> SMALL_LIGHT = regBlock("magic_small_light", () -> new MagicSmallLight(), null);

	public static final RegistryObject<MenuType<BoringMenu>> BORING_SURVEY_MENU = register("dcs_boring_menu", (IContainerFactory<BoringMenu>) (id, playerInv, data) -> {
		ItemStack held = playerInv.player.getMainHandItem();
		return BoringMenu.getMenu(id, held, playerInv.player);
	});

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("magic/" + name, item);
	}

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("magic/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(MAGIC), tag));
		return obj;
	}

	public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, MenuType.MenuSupplier<T> supplier) {
		return CoreInit.MENU_TYPE.register(name, () -> new MenuType<>(supplier));
	}

}
