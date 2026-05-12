package defeatedcrow.hac.food.material.item;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class FlowerCropItem extends MaterialItemDC {

	private int taste = 0;
	private boolean seasoning = false;
	private final Supplier<Block> cropBlock;

	public FlowerCropItem(CreativeModeTab tab, String s, TagKey<Item> pair, Supplier<Block> crop) {
		super(tab, s, pair);
		setDomain("food");
		cropBlock = crop;
	}

	public FlowerCropItem(Properties prop, String s, TagKey<Item> pair, Supplier<Block> crop) {
		super(prop, s, pair);
		setDomain("food");
		cropBlock = crop;
	}

	public Block getCropBlock() {
		return cropBlock.get();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/material/" + name));
	}

}
