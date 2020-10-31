package defeatedcrow.hac.plugin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;

public class DrinkPotionType {

	public static final DrinkPotionType INSTANCE = new DrinkPotionType();

	private DrinkPotionType() {}

	private static final Map<String, PotionSet> effects = new HashMap<String, PotionSet>();

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
				return effects.get(name).potion;
			}
		}
		return null;
	}

	public static PotionSet getPotionSet(Fluid fluid) {
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
		registerPotion(name, INSTANCE.new PotionSet(potion, 0));
	}

	public static void registerPotion(String name, Potion potion, int amp) {
		if (name == null || potion == null)
			return;
		registerPotion(name, INSTANCE.new PotionSet(potion, amp));
	}

	public static void registerPotion(String name, PotionSet potion) {
		if (name == null || potion == null)
			return;
		if (!isRegistered(name)) {
			effects.put(name, potion);
		}
	}

	public class PotionSet {
		public final Potion potion;
		public final int amp;

		public PotionSet(@Nonnull Potion pIn, int aIn) {
			potion = pIn;
			amp = aIn;
		}

		@Override
		public int hashCode() {
			return potion.getIdFromPotion(potion) + amp * 127;

		}
	}

}
