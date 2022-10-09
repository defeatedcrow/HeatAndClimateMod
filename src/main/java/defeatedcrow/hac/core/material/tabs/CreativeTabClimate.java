package defeatedcrow.hac.core.material.tabs;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabClimate extends CreativeModeTab {

	final Component tabName;

	public CreativeTabClimate(String label) {
		super("dcs_climate:" + label);
		tabName = Component.translatable("itemgroup.dcs." + label);
	}

	@Override
	public Component getDisplayName() {
		return tabName;
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(CoreInit.OREITEM_WHITE2.get());
	}

}
