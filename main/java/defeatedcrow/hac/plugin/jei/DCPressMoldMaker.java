package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.api.IPressMold;
import defeatedcrow.hac.main.config.ModuleConfig;
import mezz.jei.api.IModRegistry;
import net.minecraft.item.ItemStack;

public final class DCPressMoldMaker {

	private DCPressMoldMaker() {}

	public static void register(IModRegistry registry) {
		List<MoldItem> l = getList();
		registry.addRecipes(l, "dcs_climate.pressmold");
	}

	private static List<MoldItem> getList() {
		List<MoldItem> ret = Lists.newArrayList();
		if (ModuleConfig.machine && ModuleConfig.machine_advanced) {
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 0), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 1), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 2), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 3), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 4), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 5), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 6), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 7), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 8), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAlloy, 1, 9), (IPressMold) MachineInit.moldAlloy));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAluminium, 1, 1),
					(IPressMold) MachineInit.moldAluminium));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAluminium, 1, 2),
					(IPressMold) MachineInit.moldAluminium));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAluminium, 1, 3),
					(IPressMold) MachineInit.moldAluminium));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAluminium, 1, 4),
					(IPressMold) MachineInit.moldAluminium));
			ret.add(new MoldItem(new ItemStack(MachineInit.moldAluminium, 1, 5),
					(IPressMold) MachineInit.moldAluminium));
		}
		return ret;
	}

}
