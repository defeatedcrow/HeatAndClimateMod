package defeatedcrow.hac.main;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
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
			} else if (fuel.getItem() == FoodInit.paperPack) {
				int i = fuel.getMetadata();
				if (i == 4)
					return 1600;
				else
					return 0;
			} else if (fuel.getItem() == MachineInit.reagent) {
				int i = fuel.getMetadata();
				if (i == 0)
					return 1600;
				else if (i == 1)
					return 800;
				else if (i == 9)
					return 6400;
				else
					return 0;
			}
		}
		return 0;
	}

}
