package defeatedcrow.hac.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.main.recipes.BasicRecipeRegister;
import defeatedcrow.hac.main.recipes.MachineRecipeRegister;
import defeatedcrow.hac.main.recipes.OreDicRegister;
import defeatedcrow.hac.main.worldgen.WorldGenOres;

public class CommonMainProxy {

	public void loadConst() {
	}

	public void loadMaterial() {
		MainMaterialRegister.load();
		GameRegistry.registerFuelHandler((IFuelHandler) MainInit.logCont);
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

	public void addSidedBlock(Block block) {
	}

	public void addTBBlock(Block block) {
	}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {
	}

}
