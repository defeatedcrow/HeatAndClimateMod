package defeatedcrow.hac.main.api.brewing;

import net.minecraft.item.ItemStack;

public interface IMediumItem {

	public EnumMedium getMedium(ItemStack item);

	public ItemStack getMediumItem(EnumMedium medium);

}
