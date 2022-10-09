package defeatedcrow.hac.core.material.tabs;

import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabClimate_Food extends CreativeModeTab {

	final Component tabName;

	public CreativeTabClimate_Food(String label) {
		super("dcs_climate:" + label);
		tabName = Component.translatable("itemgroup.dcs." + label);
	}

	@Override
	public Component getDisplayName() {
		return tabName;
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(FoodInit.CROP_AL_ONION.get());
	}

}
