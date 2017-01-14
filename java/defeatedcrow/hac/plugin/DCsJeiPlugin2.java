package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.plugin.DCsJEIPluginLists;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.item.ItemStack;

public class DCsJEIPlugin2 {

	public static final DCsJEIPlugin2 INSTANCE = new DCsJEIPlugin2();

	private DCsJEIPlugin2() {}

	public static void load() {

		DCsJEIPluginLists.excluder.add(new ItemStack(MagicInit.clusterIce));
		DCsJEIPluginLists.excluder.add(new ItemStack(MagicInit.infernalFlame));

		DCsJEIPluginLists.millstones.add(new ItemStack(MachineInit.stonemill));
		DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.potteryPot));
		DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.steelPot));
		DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.teaPot));
		DCsJEIPluginLists.crops.add(new ItemStack(FoodInit.cropRice, 1, 3));

	}

}
