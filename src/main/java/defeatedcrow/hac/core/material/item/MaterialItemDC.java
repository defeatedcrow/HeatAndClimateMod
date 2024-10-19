package defeatedcrow.hac.core.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MaterialItemDC extends ItemDC {

	protected final String name;
	protected String domain = "main";

	public MaterialItemDC(String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(CoreInit.CORE), pair);
		name = s;
	}

	public MaterialItemDC(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(tab), pair);
		name = s;
	}

	public MaterialItemDC(Properties prop, String s, TagKey<Item> pair) {
		super(prop, pair);
		name = s;
	}

	public MaterialItemDC setDomain(String s) {
		domain = s;
		return this;
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/" + domain + "/" + name));
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(item) && item.is(TagDC.ItemTag.SAPS)) {
			MutableComponent text1 = Component.translatable("dcs.tip.sap");
			text1.withStyle(ChatFormatting.GRAY);
			list.add(text1);
		}
		super.appendHoverText(item, level, list, flag);
	}

}
