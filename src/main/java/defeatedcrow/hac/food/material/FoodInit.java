package defeatedcrow.hac.food.material;

import java.util.function.Supplier;

import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.tabs.CreativeTabClimate_Food;
import defeatedcrow.hac.food.material.block.CropBlockAllium;
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

	public static final RegistryObject<Block> BLOCK_AL_WILD = regSeed("cropblock_allium_wild", CropTier.WILD, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.WILD));
	public static final RegistryObject<Block> BLOCK_AL_ONION = regSeed("cropblock_allium_common", CropTier.COMMON, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.COMMON));
	public static final RegistryObject<Block> BLOCK_AL_GARLIC = regSeed("cropblock_allium_rare", CropTier.RARE, CropType.ALLIUM, () -> new CropBlockAllium(CropTier.RARE));

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
