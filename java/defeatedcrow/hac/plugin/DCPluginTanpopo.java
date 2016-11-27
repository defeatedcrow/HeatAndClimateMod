package defeatedcrow.hac.plugin;

import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class DCPluginTanpopo {

	public static final DCPluginTanpopo INSTANCE = new DCPluginTanpopo();

	private DCPluginTanpopo() {
	}

	public static void load() {
		Item fluff = Item.REGISTRY.getObject(new ResourceLocation("schr0tanpopo", "material.fluff"));
		if (fluff != null) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MainInit.materials, 1, 1), new Object[] {
					"XXX",
					"XYX",
					"XXX",
					'X',
					new ItemStack(fluff, 1, 0),
					'Y',
					"stickWood" }));
		}

		Fluid f = FluidRegistry.getFluid("fluid_essence");
		if (f != null) {
			DrinkPotionType.registerPotion(f.getName(), MobEffects.HEALTH_BOOST);
			MainAPIManager.fuelRegister.registerFuel(f.getName(), 60);
		}

	}

}
