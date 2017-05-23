package defeatedcrow.hac.main;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MainCreativeTabRegister {

	private MainCreativeTabRegister() {}

	public static void load() {
		// block
		// ores
		MainInit.ores.setCreativeTab(ClimateCore.climate);
		MainInit.ores_2.setCreativeTab(ClimateCore.climate);
		MainInit.dustBlock.setCreativeTab(ClimateCore.climate);
		MainInit.gemBlock.setCreativeTab(ClimateCore.climate);
		MainInit.metalBlock.setCreativeTab(ClimateCore.climate);

		// building
		MainInit.bricks.setCreativeTab(ClimateMain.build);
		MainInit.selenite.setCreativeTab(ClimateMain.build);
		MainInit.stairsGlass.setCreativeTab(ClimateMain.build);
		MainInit.stairsGypsum.setCreativeTab(ClimateMain.build);
		MainInit.stairsMarble.setCreativeTab(ClimateMain.build);
		MainInit.stairsSerpentine.setCreativeTab(ClimateMain.build);
		MainInit.stairsBedrock.setCreativeTab(ClimateMain.build);
		MainInit.halfSlab.setCreativeTab(ClimateMain.build);
		MainInit.fenceGypsum.setCreativeTab(ClimateMain.build);
		MainInit.fenceMarble.setCreativeTab(ClimateMain.build);
		MainInit.fenceSerpentine.setCreativeTab(ClimateMain.build);
		MainInit.fenceBedrock.setCreativeTab(ClimateMain.build);
		MainInit.awning.setCreativeTab(ClimateMain.build);
		MainInit.builds.setCreativeTab(ClimateMain.build);

		// furniture
		MainInit.chalLamp.setCreativeTab(ClimateMain.build);
		MainInit.wallLamp.setCreativeTab(ClimateMain.build);
		MainInit.tableMarble.setCreativeTab(ClimateMain.build);
		MainInit.tableGypsum.setCreativeTab(ClimateMain.build);
		MainInit.tableWood.setCreativeTab(ClimateMain.build);
		MainInit.tableDark.setCreativeTab(ClimateMain.build);
		MainInit.stoolBlack.setCreativeTab(ClimateMain.build);
		MainInit.stoolRed.setCreativeTab(ClimateMain.build);
		MainInit.sofaBlack.setCreativeTab(ClimateMain.build);
		MainInit.sofaRed.setCreativeTab(ClimateMain.build);
		MainInit.carpetRed.setCreativeTab(ClimateMain.build);
		MainInit.carpetWhite.setCreativeTab(ClimateMain.build);
		MainInit.carpetGray.setCreativeTab(ClimateMain.build);
		MainInit.chestMarble.setCreativeTab(ClimateMain.build);
		MainInit.chestWood.setCreativeTab(ClimateMain.build);

		// device
		MainInit.chamber.setCreativeTab(ClimateMain.machine);
		MainInit.shitirin.setCreativeTab(ClimateMain.machine);
		MainInit.fuelStove.setCreativeTab(ClimateMain.machine);
		MainInit.stevenson_screen.setCreativeTab(ClimateCore.climate);
		MainInit.thermometer.setCreativeTab(ClimateCore.climate);
		MainInit.windvane.setCreativeTab(ClimateCore.climate);
		MainInit.bellow.setCreativeTab(ClimateMain.machine);

		// item
		// ores
		MainInit.oreDust.setCreativeTab(ClimateCore.climate);
		MainInit.oreIngot.setCreativeTab(ClimateCore.climate);
		MainInit.gems.setCreativeTab(ClimateCore.climate);
		MainInit.miscDust.setCreativeTab(ClimateCore.climate);
		MainInit.materials.setCreativeTab(ClimateCore.climate);

		// tool
		MainInit.stoneYagen.setCreativeTab(ClimateMain.tool);
		MainInit.brassYagen.setCreativeTab(ClimateMain.tool);
		MainInit.crowDrill.setCreativeTab(ClimateMain.tool);

		MainInit.repairPutty.setCreativeTab(ClimateMain.tool);
		MainInit.wrench.setCreativeTab(ClimateMain.machine);

		// food
		MainInit.bakedApple.setCreativeTab(ClimateMain.food);
		MainInit.foodMaterials.setCreativeTab(ClimateMain.food);

		if (ModuleConfig.build_advanced) {

			// building
			MainInit.fenceAluminium.setCreativeTab(ClimateMain.build);
			MainInit.fenceNet.setCreativeTab(ClimateMain.build);
			MainInit.fenceGlass.setCreativeTab(ClimateMain.build);
			MainInit.fenceLadder.setCreativeTab(ClimateMain.build);
			MainInit.syntheticBlock.setCreativeTab(ClimateMain.build);
			MainInit.clayBricks.setCreativeTab(ClimateMain.build);

			// furniture
			MainInit.chestMetal.setCreativeTab(ClimateMain.build);
			MainInit.chestMagnet.setCreativeTab(ClimateMain.build);
			MainInit.chestVillage.setCreativeTab(ClimateMain.build);
			MainInit.sinkMetal.setCreativeTab(ClimateMain.build);
			MainInit.sinkChest.setCreativeTab(ClimateMain.build);
			MainInit.flowerPot.setCreativeTab(ClimateMain.build);
			MainInit.plate.setCreativeTab(ClimateMain.build);
			MainInit.desiccant.setCreativeTab(ClimateMain.build);

			// cont
			MainInit.logCont.setCreativeTab(ClimateMain.build);
			MainInit.cropCont.setCreativeTab(ClimateMain.build);
			MainInit.dropCont.setCreativeTab(ClimateMain.build);
			MainInit.miscCont.setCreativeTab(ClimateMain.build);
			MainInit.cardboard.setCreativeTab(ClimateMain.build);
			MainInit.cropBasket.setCreativeTab(ClimateMain.build);
			MainInit.dustBags.setCreativeTab(ClimateMain.build);
		}

		if (ModuleConfig.weapon_advanced) {
			MainInit.cartridge.setCreativeTab(ClimateMain.tool);
			MainInit.crossbow.setCreativeTab(ClimateMain.tool);
		}

		if (DCIntegrationCore.loadedForestry) {
			// MainInit.circuit.setCreativeTab(ClimateCore.climate);
		}
		if (DCIntegrationCore.loadedMekanism) {
			MainInit.metalMaterials.setCreativeTab(ClimateCore.climate);
			OreDictionary.registerOre("dustDirtyZinc", new ItemStack(MainInit.metalMaterials, 1, 0));
			OreDictionary.registerOre("shardZinc", new ItemStack(MainInit.metalMaterials, 1, 1));
			OreDictionary.registerOre("chunkZinc", new ItemStack(MainInit.metalMaterials, 1, 2));
			OreDictionary.registerOre("crystalZinc", new ItemStack(MainInit.metalMaterials, 1, 3));
			OreDictionary.registerOre("dustDirtyNickel", new ItemStack(MainInit.metalMaterials, 1, 4));
			OreDictionary.registerOre("shardNickel", new ItemStack(MainInit.metalMaterials, 1, 5));
			OreDictionary.registerOre("chunkNickel", new ItemStack(MainInit.metalMaterials, 1, 6));
			OreDictionary.registerOre("crystalNickel", new ItemStack(MainInit.metalMaterials, 1, 7));
		}
	}
}
