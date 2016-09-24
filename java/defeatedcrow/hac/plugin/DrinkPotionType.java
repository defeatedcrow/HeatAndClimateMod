package defeatedcrow.hac.plugin;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;

public class DrinkPotionType {

	public static final DrinkPotionType INSTANCE = new DrinkPotionType();

	private DrinkPotionType() {
	}

	private static final Map<String, Potion> effects = new HashMap<String, Potion>();

	public static boolean isRegistered(Fluid fluid) {
		if (fluid == null)
			return false;
		for (String name : effects.keySet()) {
			if (fluid.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isRegistered(String fluidName) {
		for (String name : effects.keySet()) {
			if (fluidName.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	public static Potion getPotion(Fluid fluid) {
		if (fluid == null)
			return null;
		for (String name : effects.keySet()) {
			if (fluid.getName().equalsIgnoreCase(name)) {
				return effects.get(name);
			}
		}
		return null;
	}

	public static void registerPotion(String name, Potion potion) {
		if (name == null || potion == null)
			return;
		if (!isRegistered(name)) {
			effects.put(name, potion);
		}
	}

}
