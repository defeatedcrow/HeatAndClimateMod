package defeatedcrow.hac.core.material.tabs;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabClimate_Agri extends CreativeModeTab {

	final Component tabName;

	public CreativeTabClimate_Agri(String label) {
		super(label);
		tabName = Component.translatable("itemgroup.dcs." + label);
	}

	@Override
	public Component getDisplayName() {
		return tabName;
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(FoodInit.CROP_AL_WILD.get());
	}

	@Override
	public void fillItemList(NonNullList<ItemStack> l) {
		for (RegistryObject<Item> item : CoreInit.ITEMS.getEntries()) {
			item.get().fillItemCategory(this, l);
		}
	}

}
