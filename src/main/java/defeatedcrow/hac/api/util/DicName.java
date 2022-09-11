package defeatedcrow.hac.api.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;

public class DicName {

	private final String domain;
	private final String name;

	public DicName(String d, String n) {
		domain = d == null ? "none" : d;
		name = n;
	}

	public String getDomain() {
		return domain;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DicName))
			return false;
		DicName target = (DicName) obj;
		return target.getDomain().equals(domain) && target.getName().equals(name);
	}

	public boolean match(Item item) {
		IReverseTag<Item> tags = ForgeRegistries.ITEMS.tags().getReverseTag(item).orElse(null);
		if (tags != null) {
			for (TagKey<Item> t : tags.getTagKeys().toList()) {
				String s = t.location().toString();
				if ((s.contains(domain) || domain.equals("none")) && s.contains(name))
					return true;
			}
		}
		return false;
	}

}
