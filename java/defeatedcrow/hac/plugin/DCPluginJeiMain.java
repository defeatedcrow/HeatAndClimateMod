package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.plugin.DCsJEIPluginLists;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;

public class DCPluginJeiMain {

	public static final DCPluginJeiMain INSTANCE = new DCPluginJeiMain();

	private DCPluginJeiMain() {}

	public static void load() {

		DCsJEIPluginLists.excluder.add(new ItemStack(MagicInit.clusterIce));
		DCsJEIPluginLists.excluder.add(new ItemStack(MagicInit.infernalFlame));
		DCsJEIPluginLists.excluder.add(new ItemStack(MainInit.markingPanel));

		DCsJEIPluginLists.climateIcons.add(new ItemStack(MainInit.stevenson_screen));
		DCsJEIPluginLists.millstones.add(new ItemStack(MachineInit.stonemill));
		DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.potteryPot));
		DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.steelPot));
		DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.teaPot));
		DCsJEIPluginLists.crops.add(new ItemStack(FoodInit.cropRice, 1, 3));
		DCsJEIPluginLists.reactors.add(new ItemStack(MachineInit.reactor, 1, 0));

	}

}
