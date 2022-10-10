package defeatedcrow.hac.food.material;

import java.util.function.Supplier;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Food;
import defeatedcrow.hac.food.material.block.CropBlockAllium;
import defeatedcrow.hac.food.material.block.CropBlockAmaranth;
import defeatedcrow.hac.food.material.block.CropBlockBrassica;
import defeatedcrow.hac.food.material.block.FertileBlock;
import defeatedcrow.hac.food.material.item.ItemCropDC;
import defeatedcrow.hac.food.material.item.SeedItemDC;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class FoodInit {

	public static final CreativeModeTab FOOD = new CreativeTabClimate_Food("food");

	public static void init() {}

	public static final RegistryObject<Item> CROP_AL_WILD = regCrop("crop_allium_wild", CropTier.WILD, CropType.ALLIUM);
	public static final RegistryObject<Item> CROP_AL_ONION = regCrop("crop_allium_common", CropTier.COMMON, CropType.ALLIUM);
	public static final RegistryObject<Item> CROP_AL_GARLIC = regCrop("crop_allium_rare", CropTier.RARE, CropType.ALLIUM);
	public static final RegistryObject<Item> CROP_AM_GOOSEFOOT = regCrop("crop_amaranth_wild", CropTier.WILD, CropType.AMARANTH);
	public static final RegistryObject<Item> CROP_AM_GLASSWORT = regCrop("crop_amaranth_common", CropTier.COMMON, CropType.AMARANTH);
	public static final RegistryObject<Item> CROP_AM_SPINACH = regCrop("crop_amaranth_rare", CropTier.RARE, CropType.AMARANTH);
	public static final RegistryObject<Item> CROP_BR_RAPESEED = regCrop("crop_brassica_wild", CropTier.WILD, CropType.BRASSICA);
	public static final RegistryObject<Item> CROP_BR_GREEN = regCrop("crop_brassica_common", CropTier.COMMON, CropType.BRASSICA);
	public static final RegistryObject<Item> CROP_BR_CABAGGE = regCrop("crop_brassica_rare", CropTier.RARE, CropType.BRASSICA);
	public static final RegistryObject<Item> CROP_BR_RADISH = regCrop("crop_brassica_epic", CropTier.EPIC, CropType.BRASSICA);

	public static final RegistryObject<Block> BLOCK_AL_WILD = regSeed("cropblock_allium_wild", CropTier.WILD, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.WILD));
	public static final RegistryObject<Block> BLOCK_AL_ONION = regSeed("cropblock_allium_common", CropTier.COMMON, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.COMMON));
	public static final RegistryObject<Block> BLOCK_AL_GARLIC = regSeed("cropblock_allium_rare", CropTier.RARE, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.RARE));
	public static final RegistryObject<Block> BLOCK_AM_GOOSEFOOT = regSeed("cropblock_amaranth_wild", CropTier.WILD, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.WILD));
	public static final RegistryObject<Block> BLOCK_AM_GLASSWORT = regSeed("cropblock_amaranth_common", CropTier.COMMON, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.COMMON));
	public static final RegistryObject<Block> BLOCK_AM_SPINACH = regSeed("cropblock_amaranth_rare", CropTier.RARE, CropType.AMARANTH, () -> new CropBlockAmaranth(CropTier.RARE));
	public static final RegistryObject<Block> BLOCK_BR_RAPESEED = regSeed("cropblock_brassica_wild", CropTier.WILD, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.WILD));
	public static final RegistryObject<Block> BLOCK_BR_GREEN = regSeed("cropblock_brassica_common", CropTier.COMMON, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.COMMON));
	public static final RegistryObject<Block> BLOCK_BR_CABAGGE = regSeed("cropblock_brassica_rare", CropTier.RARE, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.RARE));
	public static final RegistryObject<Block> BLOCK_BR_RADISH = regSeed("cropblock_brassica_epic", CropTier.EPIC, CropType.BRASSICA, () -> new CropBlockBrassica(CropTier.EPIC));

	public static final RegistryObject<Block> FERTILE = regBlock("fertile", () -> new FertileBlock());

	public static RegistryObject<Item> regCrop(String name, CropTier tier, CropType type) {
		return CoreInit.ITEMS.register("food/" + name, () -> new ItemCropDC(tier, type, name));
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("food/" + name, item);
	}

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("food/" + name, block);
		regItem(name, () -> new BlockItem(obj.get(), new Item.Properties().tab(FOOD)));
		return obj;
	}

	public static RegistryObject<Block> regSeed(String name, CropTier tier, CropType type, Supplier<Block> block) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("food/" + name, block);
		regItem(name, () -> new SeedItemDC(tier, type, obj.get(), name));
		return obj;
	}

}
