package defeatedcrow.hac.main;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class DCFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel != null) {
			if (fuel.getItem() == Item.getItemFromBlock(MainInit.logCont)) {
				int i = fuel.getMetadata();
				if (i == 6)
					return 14400;
				else
					return 2700;
			}
		}
		return 0;
	}

}
