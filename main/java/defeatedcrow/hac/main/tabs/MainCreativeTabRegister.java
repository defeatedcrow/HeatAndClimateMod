package defeatedcrow.hac.main.tabs;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MainCreativeTabRegister {

	private MainCreativeTabRegister() {}

	public static void load() {
		// block
		// core
		ClimateCore.climate.addSubItem(MainInit.oreNew);
		ClimateCore.climate.addSubItem(MainInit.layerNew);
		ClimateCore.climate.addSubItem(MainInit.skarnBlock);
		ClimateCore.climate.addSubItem(MainInit.skarnOre);
		ClimateCore.climate.addSubItem(MainInit.dustBlock);
		ClimateCore.climate.addSubItem(MainInit.dustBlock_2);
		ClimateCore.climate.addSubItem(MainInit.metalBlockNew);
		ClimateCore.climate.addSubItem(MainInit.metalBlockAlloy);
		ClimateCore.climate.addSubItem(MainInit.heatedMetalBlock);
		ClimateCore.climate.addSubItem(MainInit.gemBlock);

		ClimateCore.climate.addSubItem(MainInit.geyser);
		ClimateCore.climate.addSubItem(MainInit.stevenson_screen);
		ClimateCore.climate.addSubItem(MainInit.thermometer);
		ClimateCore.climate.addSubItem(MainInit.windvane);

		// building
		ClimateMain.build.addSubItem(MainInit.bricks);
		ClimateMain.build.addSubItem(MainInit.builds);
		ClimateMain.build.addSubItem(MainInit.wallpaper);
		ClimateMain.build.addSubItem(MainInit.selenite);
		ClimateMain.build.addSubItem(MainInit.clayBricks);
		ClimateMain.build.addSubItem(MainInit.stairsGlass);
		ClimateMain.build.addSubItem(MainInit.stairsGypsum);
		ClimateMain.build.addSubItem(MainInit.stairsMarble);
		ClimateMain.build.addSubItem(MainInit.stairsSerpentine);
		ClimateMain.build.addSubItem(MainInit.stairsBedrock);
		ClimateMain.build.addSubItem(MainInit.stairsDirtbrick);
		ClimateMain.build.addSubItem(MainInit.stairsSkarn);
		ClimateMain.build.addSubItem(MainInit.stairsGreisen);
		ClimateMain.build.addSubItem(MainInit.halfSlab);
		ClimateMain.build.addSubItem(MainInit.halfSlab2);
		ClimateMain.build.addSubItem(MainInit.halfSlab3);

		ClimateMain.build.addSubItem(MainInit.fenceGypsum);
		ClimateMain.build.addSubItem(MainInit.fenceMarble);
		ClimateMain.build.addSubItem(MainInit.fenceSerpentine);
		ClimateMain.build.addSubItem(MainInit.fenceBedrock);

		ClimateMain.build.addSubItem(MainInit.roofSlate);
		ClimateMain.build.addSubItem(MainInit.roofSlateRed);
		ClimateMain.build.addSubItem(MainInit.roofSlateGreen);
		ClimateMain.build.addSubItem(MainInit.roofSlateBrown);
		ClimateMain.build.addSubItem(MainInit.roofStraw);
		ClimateMain.build.addSubItem(MainInit.strawBlock);

		ClimateMain.build.addSubItem(MainInit.plate);
		ClimateMain.build.addSubItem(MainInit.scaffold);
		ClimateMain.build.addSubItem(MainInit.pressureChal);
		ClimateMain.build.addSubItem(MainInit.pressureOlivine);

		ClimateMain.build.addSubItem(MainInit.chalLamp);
		ClimateMain.build.addSubItem(MainInit.wallLamp);
		ClimateMain.build.addSubItem(MainInit.oilLamp);
		ClimateMain.build.addSubItem(MainInit.desiccant);
		ClimateMain.build.addSubItem(MainInit.freezepack);

		if (ModuleConfig.build_advanced) {

			// exterior
			ClimateMain.build.addSubItem(MainInit.syntheticBlock);
			ClimateMain.build.addSubItem(MainInit.linoleum);

			ClimateMain.build.addSubItem(MainInit.pillarSteel);
			ClimateMain.build.addSubItem(MainInit.fenceSteel);
			ClimateMain.build.addSubItem(MainInit.fenceNetSteel);
			ClimateMain.build.addSubItem(MainInit.fenceLadderSteel);
			ClimateMain.build.addSubItem(MainInit.fenceAluminium);
			ClimateMain.build.addSubItem(MainInit.fenceNet);
			ClimateMain.build.addSubItem(MainInit.fenceGlass);
			ClimateMain.build.addSubItem(MainInit.fenceRattan);
			ClimateMain.build.addSubItem(MainInit.fenceLadder);

			ClimateMain.build.addSubItem(MainInit.itemDoorMarble);
			ClimateMain.build.addSubItem(MainInit.itemDoorGreisen);
			ClimateMain.build.addSubItem(MainInit.itemDoorGypsum);
			ClimateMain.build.addSubItem(MainInit.itemDoorSteel);
			ClimateMain.build.addSubItem(MainInit.doorHikido);

			ClimateMain.build.addSubItem(MainInit.windowWood);
			ClimateMain.build.addSubItem(MainInit.awningWood);
			ClimateMain.build.addSubItem(MainInit.awningMetal);
			ClimateMain.build.addSubItem(MainInit.awningLinen);
			ClimateMain.build.addSubItem(MainInit.awningCloth);
			ClimateMain.build.addSubItem(MainInit.chain);
			ClimateMain.build.addSubItem(MainInit.wallDecoration);

			// interior
			ClimateMain.build.addSubItem(MainInit.carpetRed);
			ClimateMain.build.addSubItem(MainInit.carpetWhite);
			ClimateMain.build.addSubItem(MainInit.carpetGray);
			ClimateMain.build.addSubItem(MainInit.carpetLinen);
			ClimateMain.build.addSubItem(MainInit.carpetTatami);

			ClimateMain.build.addSubItem(MainInit.lampCarbide);
			ClimateMain.build.addSubItem(MainInit.lampGas);
			ClimateMain.build.addSubItem(MainInit.chandelierGypsum);

			ClimateMain.build.addSubItem(MainInit.chestMarble);
			ClimateMain.build.addSubItem(MainInit.chestSkarn);
			ClimateMain.build.addSubItem(MainInit.chestGreisen);
			ClimateMain.build.addSubItem(MainInit.chestWood);
			ClimateMain.build.addSubItem(MainInit.chestChecker);
			ClimateMain.build.addSubItem(MainInit.chestBlack);
			ClimateMain.build.addSubItem(MainInit.chestRattan);

			ClimateMain.build.addSubItem(MainInit.wallshelfMarble);
			ClimateMain.build.addSubItem(MainInit.wallshelfSkarn);
			ClimateMain.build.addSubItem(MainInit.wallshelfGreisen);
			ClimateMain.build.addSubItem(MainInit.wallshelfWood);
			ClimateMain.build.addSubItem(MainInit.wallshelfChecker);
			ClimateMain.build.addSubItem(MainInit.wallshelfBlack);
			ClimateMain.build.addSubItem(MainInit.wallshelfRattan);

			ClimateMain.build.addSubItem(MainInit.chestMetal);
			ClimateMain.build.addSubItem(MainInit.chestMagnet);
			ClimateMain.build.addSubItem(MainInit.chestVillage);
			ClimateMain.build.addSubItem(MainInit.chestHopper);

			ClimateMain.build.addSubItem(MainInit.displayShelf);
			ClimateMain.build.addSubItem(MainInit.displayStand);

			ClimateMain.build.addSubItem(MainInit.sinkMetal);
			ClimateMain.build.addSubItem(MainInit.sinkChest);
			ClimateMain.build.addSubItem(MainInit.craftingCounter);
			ClimateMain.build.addSubItem(MainInit.kitchenHood);

			ClimateMain.build.addSubItem(MainInit.tableMarble);
			ClimateMain.build.addSubItem(MainInit.tableGypsum);
			ClimateMain.build.addSubItem(MainInit.tableWood);
			ClimateMain.build.addSubItem(MainInit.tableDark);
			ClimateMain.build.addSubItem(MainInit.squaretableWood);
			ClimateMain.build.addSubItem(MainInit.squaretableMarble);
			ClimateMain.build.addSubItem(MainInit.squaretableSkarn);
			ClimateMain.build.addSubItem(MainInit.squaretableGreisen);
			ClimateMain.build.addSubItem(MainInit.squaretableChecker);
			ClimateMain.build.addSubItem(MainInit.squaretableBlack);
			ClimateMain.build.addSubItem(MainInit.squaretableRattan);

			ClimateMain.build.addSubItem(MainInit.stoolBlack);
			ClimateMain.build.addSubItem(MainInit.stoolRed);
			ClimateMain.build.addSubItem(MainInit.stoolRattan);

			ClimateMain.build.addSubItem(MainInit.chairWood);
			ClimateMain.build.addSubItem(MainInit.chairMarble);
			ClimateMain.build.addSubItem(MainInit.chairSkarn);
			ClimateMain.build.addSubItem(MainInit.chairGreisen);
			ClimateMain.build.addSubItem(MainInit.chairChecker);
			ClimateMain.build.addSubItem(MainInit.chairBlack);
			ClimateMain.build.addSubItem(MainInit.chairRattan);

			ClimateMain.build.addSubItem(MainInit.sofaBlack);
			ClimateMain.build.addSubItem(MainInit.sofaRed);
			ClimateMain.build.addSubItem(MainInit.sofaGreen);
			ClimateMain.build.addSubItem(MainInit.sofaPink);
			ClimateMain.build.addSubItem(MainInit.sofaBlue);
			ClimateMain.build.addSubItem(MainInit.sofaRattan);

			ClimateMain.build.addSubItem(MainInit.itemBed);

			ClimateMain.build.addSubItem(MainInit.realtimeClock);
			ClimateMain.build.addSubItem(MainInit.realtimeClock_L);
			ClimateMain.build.addSubItem(MainInit.mcClock_L);

			ClimateMain.build.addSubItem(MainInit.curtainWhite);
			ClimateMain.build.addSubItem(MainInit.curtainGray);
			ClimateMain.build.addSubItem(MainInit.curtainRed);
			ClimateMain.build.addSubItem(MainInit.curtainBlue);
			ClimateMain.build.addSubItem(MainInit.windowBlinds);

			// plants
			ClimateMain.build.addSubItem(MainInit.flowerVase);
			ClimateMain.build.addSubItem(MainInit.planting);

			ClimateMain.build.addSubItem(MainInit.hedgeSpring);
			ClimateMain.build.addSubItem(MainInit.hedgeSummer);
			ClimateMain.build.addSubItem(MainInit.hedgeAutumn);
			ClimateMain.build.addSubItem(MainInit.hedgeWinter);

			ClimateMain.build.addSubItem(MainInit.flowerPot);
			ClimateMain.build.addSubItem(MainInit.desktopAccessories);
			ClimateMain.build.addSubItem(MainInit.cushionGray);
		}

		// device
		if (!ModuleConfig.machine) {
			ClimateMain.machine.addSubItem(MainInit.wrench);
			ClimateMain.machine.addSubItem(MainInit.gears);
			ClimateMain.machine.addSubItem(MainInit.swedishTorch);
			ClimateMain.machine.addSubItem(MainInit.shitirin);
			ClimateMain.machine.addSubItem(MainInit.firestand);
			ClimateMain.machine.addSubItem(MainInit.chamber);
			ClimateMain.machine.addSubItem(MainInit.fuelStove);
			ClimateMain.machine.addSubItem(MainInit.bellow);
			ClimateMain.machine.addSubItem(MainInit.pail);
		}

		// item
		// ores
		ClimateCore.climate.addSubItem(MainInit.oreItem);
		ClimateCore.climate.addSubItem(MainInit.oreDust);
		ClimateCore.climate.addSubItem(MainInit.oreIngot);
		ClimateCore.climate.addSubItem(MainInit.gems_red);
		ClimateCore.climate.addSubItem(MainInit.gems_green);
		ClimateCore.climate.addSubItem(MainInit.gems_blue);
		ClimateCore.climate.addSubItem(MainInit.gems_white);
		ClimateCore.climate.addSubItem(MainInit.gems_black);
		ClimateCore.climate.addSubItem(MainInit.gems_layer);
		ClimateCore.climate.addSubItem(MainInit.miscDust);

		// tool
		ClimateMain.tool.addSubItem(MainInit.stoneYagen);
		ClimateMain.tool.addSubItem(MainInit.brassYagen);
		ClimateMain.tool.addSubItem(MainInit.handSpindle);
		ClimateMain.tool.addSubItem(MainInit.crowDrill);

		if (ModuleConfig.tool) {
			for (int j = 0; j < 10; j++) {
				ClimateMain.tool.addSubItem(MainInit.dcAxe[j]);
			}
			for (int j = 0; j < 10; j++) {
				ClimateMain.tool.addSubItem(MainInit.dcPickaxe[j]);
			}
			for (int j = 0; j < 10; j++) {
				ClimateMain.tool.addSubItem(MainInit.dcSpade[j]);
			}
			for (int j = 0; j < 8; j++) {
				ClimateMain.tool.addSubItem(MainInit.dcSword[j]);
			}
			ClimateMain.tool.addSubItem(MainInit.dcScythe[4]);
			ClimateMain.tool.addSubItem(MainInit.dcScythe[0]);
			ClimateMain.tool.addSubItem(MainInit.dcScythe[3]);
			ClimateMain.tool.addSubItem(MainInit.dcScythe[1]);
			ClimateMain.tool.addSubItem(MainInit.dcScythe[2]);
			ClimateMain.tool.addSubItem(MainInit.dcScythe[5]);

			ClimateMain.tool.addSubItem(MainInit.earthSpade);
			ClimateMain.tool.addSubItem(MainInit.earthRake);
			ClimateMain.tool.addSubItem(MainInit.toolsteelRake);
			ClimateMain.tool.addSubItem(MainInit.shieldTitanium);
			ClimateMain.tool.addSubItem(MainInit.shieldSynthetic);
		}

		ClimateMain.tool.addSubItem(MainInit.handNet);
		ClimateMain.tool.addSubItem(MainInit.scope);
		ClimateMain.tool.addSubItem(MainInit.entityScope);
		ClimateMain.tool.addSubItem(MainInit.colorChanger);
		ClimateMain.tool.addSubItem(MainInit.tinder);
		ClimateMain.tool.addSubItem(MainInit.repairPutty);

		if (ModuleConfig.weapon_advanced) {
			ClimateMain.tool.addSubItem(MainInit.cartridge);
			ClimateMain.tool.addSubItem(MainInit.crossbow);
			ClimateMain.tool.addSubItem(MainInit.gun);
			ClimateMain.tool.addSubItem(MainInit.throwingArrow);
		}

		// food
		if (!ModuleConfig.food) {
			ClimateMain.food.addSubItem(MainInit.bakedApple);
			ClimateMain.food.addSubItem(MainInit.foodMaterials);
			ClimateMain.food.addSubItem(MainInit.animalFeed);
			ClimateMain.food.addSubItem(MainInit.foodDust);
		}

		// cloth
		ClimateMain.cloth.addSubItem(MainInit.clothes);
		ClimateMain.cloth.addSubItem(MainInit.patternPaper);
		ClimateMain.cloth.addSubItem(MainInit.silkworm);

		if (ModuleConfig.tool) {
			for (int i = 0; i < 4; i++) {
				ClimateMain.cloth.addSubItem(MainInit.brassArmor[i]);
				ClimateMain.cloth.addSubItem(MainInit.steelArmor[i]);
				ClimateMain.cloth.addSubItem(MainInit.silverArmor[i]);
				ClimateMain.cloth.addSubItem(MainInit.titaniumArmor[i]);
				ClimateMain.cloth.addSubItem(MainInit.chalcArmor[i]);
				ClimateMain.cloth.addSubItem(MainInit.sapphireArmor[i]);
			}
		}

		ClimateMain.cloth.addSubItem(MainInit.leatherHat);
		ClimateMain.cloth.addSubItem(MainInit.linenUnder);
		ClimateMain.cloth.addSubItem(MainInit.linenCoat);

		if (ModuleConfig.clothes_advanced) {
			ClimateMain.cloth.addSubItem(MainInit.linenShirt);
			ClimateMain.cloth.addSubItem(MainInit.linenJacket);
			ClimateMain.cloth.addSubItem(MainInit.linenBottom);
			ClimateMain.cloth.addSubItem(MainInit.flowerSkirt);
		}

		// cotton
		ClimateMain.cloth.addSubItem(MainInit.cottonHat);
		ClimateMain.cloth.addSubItem(MainInit.clothUnder);
		ClimateMain.cloth.addSubItem(MainInit.clothCoat);

		if (ModuleConfig.clothes_advanced) {
			ClimateMain.cloth.addSubItem(MainInit.clothShirt);
			ClimateMain.cloth.addSubItem(MainInit.clothJacket);
			ClimateMain.cloth.addSubItem(MainInit.hoodie);
			ClimateMain.cloth.addSubItem(MainInit.clothBottom);
			ClimateMain.cloth.addSubItem(MainInit.clothSkirt);
			// worker
			ClimateMain.cloth.addSubItem(MainInit.workerSuit);
		}

		// silk
		ClimateMain.cloth.addSubItem(MainInit.blackSuit);
		ClimateMain.cloth.addSubItem(MainInit.blackCoat);

		if (ModuleConfig.clothes_advanced) {
			// dress
			ClimateMain.cloth.addSubItem(MainInit.silkDress);
			ClimateMain.cloth.addSubItem(MainInit.silkSkirt);
			ClimateMain.cloth.addSubItem(MainInit.silkCape);

			// wool
			ClimateMain.cloth.addSubItem(MainInit.peaCoat);
			ClimateMain.cloth.addSubItem(MainInit.modsCoat);
			ClimateMain.cloth.addSubItem(MainInit.woolJacket);
			ClimateMain.cloth.addSubItem(MainInit.furCape);
		}

		ClimateMain.cloth.addSubItem(MainInit.woolWear);
		ClimateMain.cloth.addSubItem(MainInit.furWear);
		ClimateMain.cloth.addSubItem(MainInit.woolBoots);

		// synthetic
		if (ModuleConfig.clothes_advanced && ModuleConfig.machine) {
			ClimateMain.cloth.addSubItem(MainInit.trackSuit);
			ClimateMain.cloth.addSubItem(MainInit.combatDress);
		}

		// magic
		if (ModuleConfig.clothes_advanced && ModuleConfig.magic) {
			ClimateMain.cloth.addSubItem(MainInit.magicUnder);
			ClimateMain.cloth.addSubItem(MainInit.magicCoat);
		}

		// cont
		ClimateMain.cont.addSubItem(MainInit.logCont);
		ClimateMain.cont.addSubItem(MainInit.cropCont);
		ClimateMain.cont.addSubItem(MainInit.dropCont);
		ClimateMain.cont.addSubItem(MainInit.miscCont);
		ClimateMain.cont.addSubItem(MainInit.cardboard);
		ClimateMain.cont.addSubItem(MainInit.cropBasket);
		ClimateMain.cont.addSubItem(MainInit.cropJute);
		ClimateMain.cont.addSubItem(MainInit.dustBags);
		ClimateMain.cont.addSubItem(MainInit.dustCake);
		if (ModuleConfig.machine) {
			ClimateMain.cont.addSubItem(MachineInit.fuelCont);
		}

		if (DCIntegrationCore.loadedMekanism) {
			ClimateCore.climate.addSubItem(MainInit.metalMaterials);
			OreDictionary.registerOre("dustDirtyZinc", new ItemStack(MainInit.metalMaterials, 1, 0));
			OreDictionary.registerOre("shardZinc", new ItemStack(MainInit.metalMaterials, 1, 1));
			OreDictionary.registerOre("clumpZinc", new ItemStack(MainInit.metalMaterials, 1, 2));
			OreDictionary.registerOre("crystalZinc", new ItemStack(MainInit.metalMaterials, 1, 3));
			OreDictionary.registerOre("dustDirtyNickel", new ItemStack(MainInit.metalMaterials, 1, 4));
			OreDictionary.registerOre("shardNickel", new ItemStack(MainInit.metalMaterials, 1, 5));
			OreDictionary.registerOre("clumpNickel", new ItemStack(MainInit.metalMaterials, 1, 6));
			OreDictionary.registerOre("crystalNickel", new ItemStack(MainInit.metalMaterials, 1, 7));
		}
	}
}
