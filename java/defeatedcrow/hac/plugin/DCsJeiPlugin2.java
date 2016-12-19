package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.plugin.DCsJEIPlugin;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.item.ItemStack;

public class DCsJEIPlugin2 {

	public static final DCsJEIPlugin2 INSTANCE = new DCsJEIPlugin2();

	private DCsJEIPlugin2() {
	}

	public static void load() {

		DCsJEIPlugin.excluder.add(new ItemStack(MagicInit.clusterIce));
		DCsJEIPlugin.excluder.add(new ItemStack(MagicInit.infernalFlame));

		DCsJEIPlugin.millstones.add(new ItemStack(MachineInit.stonemill));
		DCsJEIPlugin.fluidcrafters.add(new ItemStack(FoodInit.potteryPot));
		DCsJEIPlugin.fluidcrafters.add(new ItemStack(FoodInit.steelPot));
		DCsJEIPlugin.fluidcrafters.add(new ItemStack(FoodInit.teaPot));

	}

}
