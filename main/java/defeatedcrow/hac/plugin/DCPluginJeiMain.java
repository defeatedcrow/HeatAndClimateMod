package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.plugin.DCsJEIPluginLists;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.item.ItemStack;

public class DCPluginJeiMain {

	public static final DCPluginJeiMain INSTANCE = new DCPluginJeiMain();

	private DCPluginJeiMain() {}

	public static void load() {

		if (ModuleConfig.magic) {
			// DCsJEIPluginLists.excluder.add(new ItemStack(MagicInit.clusterIce));
			// DCsJEIPluginLists.excluder.add(new ItemStack(MagicInit.infernalFlame));
			DCsJEIPluginLists.excluder.add(new ItemStack(MainInit.markingPanel));
		}

		DCsJEIPluginLists.climateIcons.add(new ItemStack(MainInit.stevenson_screen));

		if (ModuleConfig.machine) {
			DCsJEIPluginLists.millstones.add(new ItemStack(MachineInit.stonemill));
			DCsJEIPluginLists.reactors.add(new ItemStack(MachineInit.reactor, 1, 0));
			DCsJEIPluginLists.reactors.add(new ItemStack(MachineInit.IBC_reactor, 1, 0));
			DCsJEIPluginLists.spinning.add(new ItemStack(MachineInit.spinning, 1, 0));
			DCsJEIPluginLists.crusher.add(new ItemStack(MachineInit.crusher, 1, 0));
		}

		if (ModuleConfig.food) {
			DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.potteryPot));
			DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.skillet));
			DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.steelPot));
			DCsJEIPluginLists.fluidcrafters.add(new ItemStack(FoodInit.teaPot));

			DCsJEIPluginLists.fluidcrafters_pot.add(new ItemStack(FoodInit.potteryPot));
			DCsJEIPluginLists.fluidcrafters_steel.add(new ItemStack(FoodInit.steelPot));
			DCsJEIPluginLists.fluidcrafters_drink.add(new ItemStack(FoodInit.teaPot));
			DCsJEIPluginLists.fluidcrafters_skillet.add(new ItemStack(FoodInit.skillet));

			DCsJEIPluginLists.crops.add(new ItemStack(FoodInit.cropRice, 1, 3));
		}

	}

}
