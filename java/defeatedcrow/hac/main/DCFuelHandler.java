package defeatedcrow.hac.main;

import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.machine.item.ItemReagents;
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
			} else if (fuel.getItem() == Item.getItemFromBlock(MachineInit.fuelCont)) {
				int i = fuel.getMetadata();
				if (i == 0)
					return 54000;
				else
					return 128000;
			} else if (fuel.getItem() == MachineInit.reagent)
				return ItemReagents.getItemBurnTime(fuel);
		}
		return 0;
	}

}
