package defeatedcrow.hac.main;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.food.entity.BeefStickEntity;
import defeatedcrow.hac.food.entity.FishStickEntity;
import defeatedcrow.hac.food.entity.PorkStickEntity;
import defeatedcrow.hac.food.entity.RoundBreadEntity;
import defeatedcrow.hac.food.entity.SquareBreadEntity;
import defeatedcrow.hac.food.entity.YakitoriStickEntity;

public class MainEntityRegister {
	private MainEntityRegister() {
	}

	public static void load() {
		EntityRegistry.registerModEntity(RoundBreadEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.r_bread", 1,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SquareBreadEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.s_bread", 2,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(FishStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.fish_stick", 3,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(YakitoriStickEntity.class, ClimateCore.PACKAGE_BASE
				+ "entity.food.yakitori_stick", 4, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(PorkStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.pork_stick", 5,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(BeefStickEntity.class, ClimateCore.PACKAGE_BASE + "entity.food.beef_stick", 6,
				ClimateMain.instance, 128, 5, true);
	}
}
