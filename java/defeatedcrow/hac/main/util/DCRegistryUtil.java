package defeatedcrow.hac.main.util;

import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class DCRegistryUtil {

	static int counter = 0;

	public static void addPotion(Potion potion, PotionType type, String name) {
		ForgeRegistries.POTIONS.register(potion.setRegistryName(ClimateMain.MOD_ID, "dcs.potion." + name));
		type = new PotionType("dcs." + name, new PotionEffect[] {
				new PotionEffect(potion, potion.isBadEffect() ? 1800 : 3600, 0)
		});
		ForgeRegistries.POTION_TYPES.register(type.setRegistryName(ClimateMain.MOD_ID, "dcs." + name));
	}

	public static void addEntity(Class<? extends Entity> regClass, String domein, String name) {
		String regName = "dcs." + domein + "." + name;
		EntityRegistry.registerModEntity(regClass, regName, counter, ClimateMain.instance, 128, 10, true);
		counter++;
	}

	public static void addEntity(Class<? extends Entity> regClass, String domein, String name, int f) {
		String regName = "dcs." + domein + "." + name;
		EntityRegistry.registerModEntity(regClass, regName, counter, ClimateMain.instance, 128, f, true);
		counter++;
	}

}
