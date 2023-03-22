package defeatedcrow.hac.magic.material;

import java.util.function.Supplier;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Magic;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.block.MagicSmallLight;
import defeatedcrow.hac.magic.material.entity.ArrowBlack;
import defeatedcrow.hac.magic.material.entity.ArrowBlue;
import defeatedcrow.hac.magic.material.entity.ArrowGreen;
import defeatedcrow.hac.magic.material.entity.ArrowRed;
import defeatedcrow.hac.magic.material.entity.ArrowWhite;
import defeatedcrow.hac.magic.material.item.BlackArrowItem;
import defeatedcrow.hac.magic.material.item.BlueArrowItem;
import defeatedcrow.hac.magic.material.item.GreenArrowItem;
import defeatedcrow.hac.magic.material.item.MagicMaterialItemDC;
import defeatedcrow.hac.magic.material.item.RedArrowItem;
import defeatedcrow.hac.magic.material.item.WhiteArrowItem;
import defeatedcrow.hac.magic.material.item.card.CardBlackT1;
import defeatedcrow.hac.magic.material.item.card.CardBlueT1;
import defeatedcrow.hac.magic.material.item.card.CardGreenT1;
import defeatedcrow.hac.magic.material.item.card.CardRedT1;
import defeatedcrow.hac.magic.material.item.card.CardWhiteT1;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

	public static final RegistryObject<Item> DROP_WHITE = regItem("drop_w", () -> new MagicMaterialItemDC(MagicColor.WHITE, "drop_w", TagDC.ItemTag.DROP_WHITE).setDomain("magic"));
	public static final RegistryObject<Item> DROP_BLUE = regItem("drop_u", () -> new MagicMaterialItemDC(MagicColor.BLUE, "drop_u", TagDC.ItemTag.DROP_BLUE).setDomain("magic"));
	public static final RegistryObject<Item> DROP_BLACK = regItem("drop_b", () -> new MagicMaterialItemDC(MagicColor.BLACK, "drop_b", TagDC.ItemTag.DROP_BLACK).setDomain("magic"));
	public static final RegistryObject<Item> DROP_RED = regItem("drop_r", () -> new MagicMaterialItemDC(MagicColor.RED, "drop_r", TagDC.ItemTag.DROP_RED).setDomain("magic"));
	public static final RegistryObject<Item> DROP_GREEN = regItem("drop_g", () -> new MagicMaterialItemDC(MagicColor.GREEN, "drop_g", TagDC.ItemTag.DROP_GREEN).setDomain("magic"));

	public static final RegistryObject<Item> EXTRACT_WHITE = regItem("extract_w", () -> new MagicMaterialItemDC(MagicColor.WHITE, "extract_w", TagDC.ItemTag.EXTRACT_WHITE).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_BLUE = regItem("extract_u", () -> new MagicMaterialItemDC(MagicColor.BLUE, "extract_u", TagDC.ItemTag.EXTRACT_BLUE).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_BLACK = regItem("extract_b", () -> new MagicMaterialItemDC(MagicColor.BLACK, "extract_b", TagDC.ItemTag.EXTRACT_BLACK).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_RED = regItem("extract_r", () -> new MagicMaterialItemDC(MagicColor.RED, "extract_r", TagDC.ItemTag.EXTRACT_RED).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_GREEN = regItem("extract_g", () -> new MagicMaterialItemDC(MagicColor.GREEN, "extract_g", TagDC.ItemTag.EXTRACT_GREEN).setDomain("magic"));

	public static final RegistryObject<Item> PIGMENT_WHITE = regItem("pigment_w", () -> new MagicMaterialItemDC(MagicColor.WHITE, "pigment_w", TagDC.ItemTag.PIGMENT_WHITE).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_BLUE = regItem("pigment_u", () -> new MagicMaterialItemDC(MagicColor.BLUE, "pigment_u", TagDC.ItemTag.PIGMENT_BLUE).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_BLACK = regItem("pigment_b", () -> new MagicMaterialItemDC(MagicColor.BLACK, "pigment_b", TagDC.ItemTag.PIGMENT_BLACK).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_RED = regItem("pigment_r", () -> new MagicMaterialItemDC(MagicColor.RED, "pigment_r", TagDC.ItemTag.PIGMENT_RED).setDomain("magic"));
	public static final RegistryObject<Item> PIGMENT_GREEN = regItem("pigment_g", () -> new MagicMaterialItemDC(MagicColor.GREEN, "pigment_g", TagDC.ItemTag.PIGMENT_GREEN).setDomain("magic"));

	public static final RegistryObject<Item> DROP_MANA = regItem("drop_mana", () -> new MagicMaterialItemDC(MagicColor.NONE, "drop_mana", TagDC.ItemTag.MANA_DROPS).setDomain("magic"));
	public static final RegistryObject<Item> EXTRACT_MANA = regItem("extract_mana", () -> new MagicMaterialItemDC(MagicColor.NONE, "extract_mana", TagDC.ItemTag.MANA_EXTRACT).setDomain("magic"));

	public static final RegistryObject<Item> ARROW_WHITE = regItem("arrow_white", () -> new WhiteArrowItem());
	public static final RegistryObject<Item> ARROW_BLUE = regItem("arrow_blue", () -> new BlueArrowItem());
	public static final RegistryObject<Item> ARROW_BLACK = regItem("arrow_black", () -> new BlackArrowItem());
	public static final RegistryObject<Item> ARROW_RED = regItem("arrow_red", () -> new RedArrowItem());
	public static final RegistryObject<Item> ARROW_GREEN = regItem("arrow_green", () -> new GreenArrowItem());

	public static final RegistryObject<Item> CARD_WHITE_1 = regItem("card_white_common", () -> new CardWhiteT1());
	public static final RegistryObject<Item> CARD_BLUE_1 = regItem("card_blue_common", () -> new CardBlueT1());
	public static final RegistryObject<Item> CARD_BLACK_1 = regItem("card_black_common", () -> new CardBlackT1());
	public static final RegistryObject<Item> CARD_RED_1 = regItem("card_red_common", () -> new CardRedT1());
	public static final RegistryObject<Item> CARD_GREEN_1 = regItem("card_green_common", () -> new CardGreenT1());

	public static final RegistryObject<Block> SMALL_LIGHT = regBlock("magic_small_light", () -> new MagicSmallLight(), null);

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("magic/" + name, item);
	}

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("magic/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(MAGIC), tag));
		return obj;
	}

}
