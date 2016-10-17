package defeatedcrow.hac.main;

import defeatedcrow.hac.core.ClimateCore;

public class MainCreativeTabRegister {

	private MainCreativeTabRegister() {
	}

	public static void load() {
		// block
		// ores
		MainInit.ores.setCreativeTab(ClimateCore.climate);
		MainInit.ores_2.setCreativeTab(ClimateCore.climate);
		MainInit.dustBlock.setCreativeTab(ClimateCore.climate);
		MainInit.gemBlock.setCreativeTab(ClimateCore.climate);
		MainInit.metalBlock.setCreativeTab(ClimateCore.climate);

		// cont
		MainInit.logCont.setCreativeTab(ClimateMain.build);
		MainInit.cropCont.setCreativeTab(ClimateMain.build);
		MainInit.dropCont.setCreativeTab(ClimateMain.build);
		MainInit.miscCont.setCreativeTab(ClimateMain.build);
		MainInit.cardboard.setCreativeTab(ClimateMain.build);
		MainInit.cropBasket.setCreativeTab(ClimateMain.build);
		MainInit.dustBags.setCreativeTab(ClimateMain.build);

		// building
		MainInit.bricks.setCreativeTab(ClimateMain.build);
		MainInit.selenite.setCreativeTab(ClimateMain.build);
		MainInit.stairsGlass.setCreativeTab(ClimateMain.build);
		MainInit.stairsGypsum.setCreativeTab(ClimateMain.build);
		MainInit.halfSlab.setCreativeTab(ClimateMain.build);
		MainInit.builds.setCreativeTab(ClimateMain.build);
		MainInit.plate.setCreativeTab(ClimateMain.build);

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
		MainInit.chestMetal.setCreativeTab(ClimateMain.build);
		MainInit.chestMagnet.setCreativeTab(ClimateMain.build);
		MainInit.sinkMetal.setCreativeTab(ClimateMain.build);
		MainInit.sinkChest.setCreativeTab(ClimateMain.build);

		// device
		MainInit.chamber.setCreativeTab(ClimateMain.machine);
		MainInit.shitirin.setCreativeTab(ClimateMain.machine);
		MainInit.fuelStove.setCreativeTab(ClimateMain.machine);
		MainInit.stevenson_screen.setCreativeTab(ClimateCore.climate);

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
	}
}
