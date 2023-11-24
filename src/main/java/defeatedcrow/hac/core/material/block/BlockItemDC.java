package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
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
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		if (!DCUtil.isEmpty(stack)) {
			if (stack.is(ItemTags.LOGS_THAT_BURN)) {
				return 300;
			}
			if (stack.is(TagDC.ItemTag.CONT_LOGS)) {
				return 3000;
			}
			if (stack.is(TagDC.ItemTag.CONT_CHARCOAL)) {
				return 16000;
			}
		}
		return -1;
	}

}
