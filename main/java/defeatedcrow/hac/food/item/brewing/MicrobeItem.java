package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import net.minecraft.item.ItemStack;

public abstract class MicrobeItem extends DCItem {

	public abstract IMicrobe getSpecies(ItemStack item);

}
