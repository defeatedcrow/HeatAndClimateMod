package defeatedcrow.hac.core.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class ItemDC extends Item implements IJsonDataDC, IItemDC {

	final TagKey<Item> tag;

	public ItemDC(Properties prop, TagKey<Item> pair) {
		super(prop);
		tag = pair;
	}

	@Override
	public abstract String getRegistryName();

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public abstract JsonModelSimpleDC getItemModel();

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
