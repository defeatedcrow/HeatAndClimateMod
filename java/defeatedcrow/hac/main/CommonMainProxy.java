package defeatedcrow.hac.main;

import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.main.recipes.BasicRecipeRegister;
import defeatedcrow.hac.main.recipes.MachineRecipeRegister;
import defeatedcrow.hac.main.recipes.OreDicRegister;
import defeatedcrow.hac.main.worldgen.WorldGenOres;

public class CommonMainProxy {

	public void loadMaterial() {
		MainMaterialRegister.load();
	}

	public void loadRecipes() {
		OreDicRegister.load();
		BasicRecipeRegister.load();
		MachineRecipeRegister.load();
	}

	public void loadTE() {
		MainTileRegister.load();
	}

	public void loadWorldGen() {
		// gen
		GameRegistry.registerWorldGenerator(new WorldGenOres(), 2);
	}

}
