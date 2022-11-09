package defeatedcrow.hac.core.json;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;

public class IngPair {

	private final String type;
	private final String name;

	public IngPair(String d, String n) {
		type = d == null ? "none" : d;
		name = n;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IngPair))
			return false;
		IngPair target = (IngPair) obj;
		return target.getType().equals(type) && target.getName().equals(name);
	}

	public boolean match(Item item) {
		IReverseTag<Item> tags = ForgeRegistries.ITEMS.tags().getReverseTag(item).orElse(null);
		if (tags != null) {
			for (TagKey<Item> t : tags.getTagKeys().toList()) {
				String s = t.location().toString();
				if ((s.contains(type) || type.equals("none")) && s.contains(name))
					return true;
			}
		}
		return false;
	}

	public Object getItemObject() {
		if (type.equalsIgnoreCase("tag") || type.equalsIgnoreCase("dic_name")) {
			return name;
		} else if (type.equalsIgnoreCase("item")) {
			ResourceLocation res = new ResourceLocation(name);
			Item ret = ForgeRegistries.ITEMS.getValue(res);
			return ret;
		} else if (type.equalsIgnoreCase("block")) {
			ResourceLocation res = new ResourceLocation(name);
			Block ret = ForgeRegistries.BLOCKS.getValue(res);
			return ret;
		}
		return CoreInit.NULL_ITEM.get();
	}

	public ItemStack getItem() {
		if (type.equalsIgnoreCase("item")) {
			ResourceLocation res = new ResourceLocation(name);
			Item ret = ForgeRegistries.ITEMS.getValue(res);
			return new ItemStack(ret);
		} else if (type.equalsIgnoreCase("block")) {
			ResourceLocation res = new ResourceLocation(name);
			Block ret = ForgeRegistries.BLOCKS.getValue(res);
			return new ItemStack(ret);
		}
		return new ItemStack(CoreInit.NULL_ITEM.get());
	}

}
