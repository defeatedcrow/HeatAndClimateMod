package defeatedcrow.hac.core.climate.register;

import java.util.Optional;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ParamArmorItem {
	public final String itemName;
	public final float heatResistance;
	public final float coldResistance;

	public ParamArmorItem(String item, float heat, float cold) {
		itemName = item;
		heatResistance = heat;
		coldResistance = cold;
	}

	public Optional<Item> getItem() {
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
		if (item != null) {
			return Optional.of(item);
		}
		return Optional.empty();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof ParamArmorItem) {
			ParamArmorItem target = (ParamArmorItem) obj;
			return itemName.equals(target.itemName) && heatResistance == target.heatResistance && coldResistance == target.coldResistance;
		}
		return false;
	}
}
