package defeatedcrow.hac.food;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.entity.BeefStickEntity;
import defeatedcrow.hac.food.entity.EntityTeaCupSilver;
import defeatedcrow.hac.food.entity.EntityTeaCupWhite;
import defeatedcrow.hac.food.entity.FishStickEntity;
import defeatedcrow.hac.food.entity.PorkStickEntity;
import defeatedcrow.hac.food.entity.RoundBreadEntity;
import defeatedcrow.hac.food.entity.SquareBreadEntity;
import defeatedcrow.hac.food.entity.YakitoriStickEntity;
import defeatedcrow.hac.main.ClimateMain;

public class FoodCommonProxy {

	public static void loadTE() {
		GameRegistry.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot");
		GameRegistry.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot");
	}

	public static void loadEntity() {
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
		EntityRegistry.registerModEntity(EntityTeaCupSilver.class, ClimateCore.PACKAGE_BASE + "entity.food.cup_silver",
				7, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityTeaCupWhite.class, ClimateCore.PACKAGE_BASE + "entity.food.cup_white",
				8, ClimateMain.instance, 128, 5, true);
	}

}
