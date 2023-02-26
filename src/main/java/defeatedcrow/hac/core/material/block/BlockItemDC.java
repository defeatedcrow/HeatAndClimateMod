package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BlockItemDC extends BlockItem implements IJsonDataDC, IItemDC {

	final TagKey<Item> tag;
	final String name;

	public BlockItemDC(String regName, Block block, Properties prop, TagKey<Item> pair) {
		super(block, prop);
		tag = pair;
		name = regName;
	}

	@Override
	public String getRegistryName() {
		return "main/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public TagKey<Item> getPairTag() {
		return tag == null ? TagDC.ItemTag.DUMMY : tag;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (tag != null && flag.isAdvanced())
			list.add(Component.literal("Tag: " + tag.location().toString()));
	}

}
