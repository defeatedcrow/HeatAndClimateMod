package defeatedcrow.hac.plugin;

import defeatedcrow.hac.api.module.HaCModule;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DCPluginGVC {
	public static final DCPluginGVC INSTANCE = new DCPluginGVC();

	private DCPluginGVC() {}

	public static void load() {
		Item pra = Item.REGISTRY.getObject(new ResourceLocation("hmggvc", "i_pra"));
		if (pra != null) {
			DCRecipe.jsonShapedRecipe(HaCModule.getPlugin("hmggvc"), "plugin", new ItemStack(pra, 4, 0), new Object[] {
					"XX",
					'X',
					new ItemStack(MachineInit.reagent, 1, 2)
			});
		}
	}

}
