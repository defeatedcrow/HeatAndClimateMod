package defeatedcrow.hac.main;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class MainInit {
	private MainInit() {}

	// block
	// ores
	public static Block ores;
	public static Block ores_2;
	public static Block dustBlock;
	public static Block dustBlock_2;
	public static Block gemBlock;
	public static Block metalBlock;

	// cont
	public static Block logCont;
	public static Block cropCont;
	public static Block cropJute;
	public static Block dropCont;
	public static Block miscCont;
	public static Block cardboard;
	public static Block cropBasket;
	public static Block dustBags;

	// building
	public static Block bricks;
	public static Block selenite;
	public static Block stairsGlass;
	public static Block stairsGypsum;
	public static Block stairsMarble;
	public static Block stairsSerpentine;
	public static Block stairsBedrock;
	public static Block stairsDirtbrick;
	public static Block halfSlab;
	public static Block halfSlab2;
	public static Block halfSlab3;
	public static Block builds;
	public static Block plate;
	public static Block syntheticBlock;
	public static Block linoleum;
	public static Block roofSlate;
	public static Block roofSlateRed;
	public static Block roofSlateBrown;
	public static Block roofSlateGreen;

	public static Block fenceGypsum;
	public static Block fenceMarble;
	public static Block fenceSerpentine;
	public static Block fenceBedrock;

	public static Block fenceAluminium;
	public static Block fenceNet;
	public static Block fenceGlass;
	public static Block fenceLadder;

	public static Block awning;
	public static Block lampCarbide;
	public static Block lampGas;
	public static Block achievementShield;

	public static Block fenceLadderSteel;
	public static Block fenceSteel;
	public static Block fenceNetSteel;
	public static Block pillarSteel;

	public static Block markingPanel;
	public static Block lightOrb;

	/* building advanced */

	public static Block asphalt;
	public static Block fireproofs;
	public static Block fireproofhalfs;
	public static Block fireproofStairs;

	public static Block hedgeSpring;
	public static Block hedgeSummer;
	public static Block hedgeAutumn;
	public static Block hedgeWinter;

	public static Block desiccant;
	public static Block freezepack;
	public static Block hotPack;
	public static Block waterPack;

	public static Item flowerPot;

	public static Block clayBricks;

	// furniture
	public static Block chalLamp;
	public static Block wallLamp;
	public static Block chandelierGypsum;
	public static Block chandelierChal;

	public static Block tableMarble;
	public static Block tableGypsum;
	public static Block tableWood;
	public static Block tableDark;
	public static Block squaretableWood;
	public static Block squaretableMarble;
	public static Block squaretableChecker;
	public static Block squaretableBlack;
	public static Block stoolBlack;
	public static Block stoolRed;
	public static Block sofaBlack;
	public static Block sofaRed;
	public static Block chairWood;
	public static Block chairMarble;
	public static Block chairChecker;
	public static Block chairBlack;

	public static Block carpetRed;
	public static Block carpetWhite;
	public static Block carpetGray;

	public static Block curtainWhite;

	public static Block sinkMetal;
	public static Block sinkChest;

	public static Block chestMarble;
	public static Block chestWood;
	public static Block chestChecker;
	public static Block chestBlack;
	public static Block chestMetal;
	public static Block chestMagnet;
	public static Block chestVillage;

	public static Block wallshelfMarble;
	public static Block wallshelfWood;
	public static Block wallshelfChecker;
	public static Block wallshelfBlack;

	public static Block doorMarble;
	public static Block doorSteel;
	public static Item itemDoorMarble;
	public static Item itemDoorSteel;

	public static Block realtimeClock;
	public static Block realtimeClock_L;
	public static Block mcClock_L;

	public static Item cushionGray;

	// device
	public static Block chamber;
	public static Block shitirin;
	public static Block fuelStove;
	public static Block stevenson_screen;
	public static Block bellow;
	public static Block thermometer;
	public static Block windvane;

	// item
	// ores
	public static Item oreDust;
	public static Item oreIngot;
	public static Item gems;
	public static Item miscDust;
	@Deprecated
	public static Item materials;
	public static Item foodDust;
	public static Item silkworm;
	public static Item gears;
	public static Item clothes;

	// tool
	public static Item stoneYagen;
	public static Item brassYagen;
	public static Item crowDrill;
	public static Item tinder;
	public static Item bucketClay;

	public static Item repairPutty;
	public static Item wrench;
	public static Item scope;
	public static Item entityScope;

	public static Item[] dcAxe = new Item[8];
	public static Item[] dcPickaxe = new Item[8];
	public static Item[] dcSpade = new Item[8];
	public static Item[] dcSword = new Item[8];
	public static Item[] dcScythe = new Item[5];

	public static Item earthSpade;
	public static Item earthRake;

	// armor
	public static Item[] brassArmor = new Item[4];
	public static Item[] steelArmor = new Item[4];
	public static Item[] chalcArmor = new Item[4];
	public static Item[] sapphireArmor = new Item[4];
	public static Item[] silverArmor = new Item[4];

	// clothes
	// linen
	public static Item linenCoat;
	public static Item linenJacket;

	public static Item linenUnder;
	public static Item linenShirt;

	public static Item linenBottom;
	public static Item flowerSkirt;

	// cotton
	public static Item clothCoat;
	public static Item clothJacket;
	public static Item hoodie;

	public static Item clothUnder;
	public static Item clothShirt;

	public static Item clothBottom;
	public static Item clothSkirt;

	public static Item cottonHat;

	// worker
	public static Item workerDress;
	public static Item workerSuit;

	public static Item workerWear;

	// silk
	public static Item blackCoat;
	public static Item blackSuit;

	// dress
	public static Item silkDress;
	public static Item silkCape;
	public static Item silkKimono;

	public static Item silkSkirt;

	public static Item silkWear;

	// wool
	public static Item peaCoat;
	public static Item modsCoat;
	public static Item woolJacket;

	public static Item woolBoots;

	public static Item woolWear;
	public static Item furWear;
	public static Item furCape;

	// synthetic
	public static Item blouson;

	public static Item trackSuit;
	public static Item combatDress;

	// magic
	public static Item magicCoat;
	public static Item magicUnder;

	// leather
	public static Item leatherHat;

	// pattern
	public static Item patternPaper;

	/* weapon advanced */

	public static Item[] titaniumArmor = new Item[4];

	public static Item crossbow;
	public static Item gun;
	public static Item ignis;
	public static Item cartridge;

	public static Item gemShield;
	public static Item riotShield;

	// food
	public static Item bakedApple;
	public static Item foodMaterials;

	// potion
	public static Potion gravity;
	public static PotionType gravityType;

	public static Potion bird;
	public static PotionType birdType;

	public static Potion ocean;
	public static PotionType oceanType;

	public static Potion heavyboots;
	public static PotionType heavybootsType;

	public static Enchantment venom;
	public static Enchantment corrosion;
	public static Enchantment robber;

	public static VillagerProfession trader;
	public static VillagerProfession agri;
	public static VillagerProfession engineer;

	// plugin
	public static Item circuit;
	public static Item metalMaterials;

	public static Fluid milk;

	public static Item iconItem;

	// fluid
	public static Fluid oil;
	public static Fluid greenTea;
	public static Fluid blackTea;
	public static Fluid coffee;
	public static Fluid cream;
	public static Fluid tomatoJuice;
	public static Fluid blackLiquor;
	public static Fluid hotSpring;
	public static Fluid stock;
	public static Fluid lemon;
	public static Fluid mazai;
	public static Fluid soyMilk;
	public static Fluid hydrogen;
	public static Fluid ammonia;
	public static Fluid nitricAcid;
	public static Fluid sulfuricAcid;
	public static Fluid fuelOil;
	public static Fluid fuelGas;
	public static Fluid nitrogen;
	public static Fluid ethanol;

	public static Block oilBlock;
	public static Block greenTeaBlock;
	public static Block blackTeaBlock;
	public static Block coffeeBlock;
	public static Block creamBlock;
	public static Block tomatoBlock;
	public static Block blackLiquorBlock;
	public static Block hotSpringBlock;
	public static Block stockBlock;
	public static Block lemonBlock;
	public static Block mazaiBlock;
	public static Block soyMilkBlock;
	public static Block hydrogenBlock;
	public static Block ammoniaBlock;
	public static Block nitricAcidBlock;
	public static Block sulfuricAcidBlock;
	public static Block fuelOilBlock;
	public static Block fuelGasBlock;
	public static Block nitrogenBlock;
	public static Block ethanolBlock;

}
