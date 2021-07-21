package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class DCPluginOres {

	public static final DCPluginOres INSTANCE = new DCPluginOres();

	private DCPluginOres() {}

	public static void loadInit() {

		// add ore
		addOre("listAllbean", "cropPeas");
		addOre("listAllbean", "cropBean");
		addOre("listAllbean", "cropChickpea");

	}

	private static void addOre(String string, String ore) {
		NonNullList<ItemStack> items = NonNullList.create();
		items.addAll(OreDictionary.getOres(ore));
		for (ItemStack i : items) {
			if (!DCUtil.isEmpty(i)) {
				ResourceLocation reg = i.getItem().getRegistryName();
				if (reg.getResourceDomain().contains("minecraft") || reg.getResourceDomain().contains("dcs")) {
					return;
				}
				OreDictionary.registerOre(string, i);
			}
		}
	}

}
