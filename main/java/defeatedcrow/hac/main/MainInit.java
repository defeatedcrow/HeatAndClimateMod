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
	@Deprecated
	public static Block ores;
	@Deprecated
	public static Block ores_2;
	public static Block dustBlock;
	public static Block dustBlock_2;
	public static Block gemBlock;
	public static Block metalBlockNew;
	public static Block metalBlockAlloy;
	public static Block heatedMetalBlock;
	public static Block skarnBlock;

	public static Block oreNew;
	public static Block layerNew;
	public static Block skarnOre;

	// cont
	public static Block logCont;
	public static Block cropCont;
	public static Block cropJute;
	public static Block dropCont;
	public static Block miscCont;
	public static Block cardboard;
	public static Block cropBasket;
	public static Block dustBags;
	public static Block dustCake;

	// building
	public static Block bricks;
	public static Block clayBricks;
	public static Block selenite;
	public static Block stairsGlass;
	public static Block stairsGypsum;
	public static Block stairsMarble;
	public static Block stairsSerpentine;
	public static Block stairsBedrock;
	public static Block stairsDirtbrick;
	public static Block stairsSkarn;
	public static Block stairsGreisen;
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

	public static Block desiccant;
	public static Block freezepack;
	public static Block hotPack;
	public static Block waterPack;

	public static Block scaffold;

	public static Block pressureChal;
	public static Block pressureOlivine;

	/* building advanced */

	public static Block awning;
	public static Block lampCarbide;
	public static Block lampGas;
	public static Block chain;

	public static Block fenceLadderSteel;
	public static Block fenceSteel;
	public static Block fenceNetSteel;
	public static Block pillarSteel;

	public static Block markingPanel;
	public static Block lightOrb;

	public static Block asphalt;
	public static Block fireproofs;
	public static Block fireproofhalfs;
	public static Block fireproofStairs;

	public static Block hedgeSpring;
	public static Block hedgeSummer;
	public static Block hedgeAutumn;
	public static Block hedgeWinter;

	public static Item flowerPot;

	// furniture
	public static Block chalLamp;
	public static Block wallLamp;
	public static Block oilLamp;

	/* building advanced */

	public static Block chandelierGypsum;
	public static Block chandelierChal;
	public static Block chainCopper;

	public static Block tableMarble;
	public static Block tableGypsum;
	public static Block tableWood;
	public static Block tableDark;
	public static Block squaretableWood;
	public static Block squaretableMarble;
	public static Block squaretableSkarn;
	public static Block squaretableGreisen;
	public static Block squaretableChecker;
	public static Block squaretableBlack;
	public static Block stoolBlack;
	public static Block stoolRed;
	public static Block sofaBlack;
	public static Block sofaRed;
	public static Block chairWood;
	public static Block chairMarble;
	public static Block chairSkarn;
	public static Block chairGreisen;
	public static Block chairChecker;
	public static Block chairBlack;
	public static Block carpetRed;
	public static Block carpetWhite;
	public static Block carpetGray;
	public static Block carpetLinen;

	public static Block curtainWhite;
	public static Block curtainGray;
	public static Block curtainRed;
	public static Block curtainBlue;

	public static Block sinkMetal;
	public static Block sinkChest;

	public static Block chestMarble;
	public static Block chestSkarn;
	public static Block chestGreisen;
	public static Block chestWood;
	public static Block chestChecker;
	public static Block chestBlack;
	public static Block chestMetal;
	public static Block chestMagnet;
	public static Block chestVillage;

	public static Block wallshelfMarble;
	public static Block wallshelfSkarn;
	public static Block wallshelfGreisen;
	public static Block wallshelfWood;
	public static Block wallshelfChecker;
	public static Block wallshelfBlack;

	public static Block displayStand;
	public static Block displayShelf;
	public static Block flowerVase;
	public static Block planting;

	public static Block doorMarble;
	public static Block doorGreisen;
	public static Block doorGypsum;
	public static Block doorSteel;
	public static Item itemDoorMarble;
	public static Item itemDoorGreisen;
	public static Item itemDoorGypsum;
	public static Item itemDoorSteel;

	public static Block bed;
	public static Block bedWhite;
	public static Block bedRattan;
	public static Block bedFuton;
	public static Block bedHammock;
	public static Item itemBed;

	public static Block realtimeClock;
	public static Block realtimeClock_L;
	public static Block mcClock_L;

	public static Item cushionGray;

	// vine
	public static Block fenceRattan;
	public static Block squaretableRattan;
	public static Block stoolRattan;
	public static Block chairRattan;
	public static Block chestRattan;
	public static Block wallshelfRattan;

	// device
	public static Block swedishTorch;
	public static Block firestand;
	public static Block chamber;
	public static Block shitirin;
	public static Block fuelStove;
	public static Block stevenson_screen;
	public static Block bellow;
	public static Block thermometer;
	public static Block windvane;
	public static Block pail;

	public static Block geyser;

	// item
	// ores
	public static Item oreItem;
	public static Item oreDust;
	public static Item oreIngot;
	@Deprecated
	public static Item gems;
	public static Item gems_blue;
	public static Item gems_green;
	public static Item gems_red;
	public static Item gems_black;
	public static Item gems_white;
	public static Item gems_layer;

	public static Item miscDust;
	public static Item foodDust;
	public static Item silkworm;
	public static Item gears;
	public static Item clothes;

	// tool
	public static Item stoneYagen;
	public static Item brassYagen;
	public static Item handSpindle;
	public static Item crowDrill;
	public static Item tinder;
	public static Item bucketClay;

	public static Item repairPutty;
	public static Item wrench;
	public static Item scope;
	public static Item entityScope;

	public static Item[] dcAxe = new Item[10];
	public static Item[] dcPickaxe = new Item[10];
	public static Item[] dcSpade = new Item[10];
	public static Item[] dcSword = new Item[9];
	public static Item[] dcScythe = new Item[6];
	public static Item toolsteelRake;

	public static Item earthSpade;
	public static Item earthRake;

	public static Item shieldTitanium;
	public static Item shieldSynthetic;

	public static Item handNet;

	// armor
	public static Item[] brassArmor = new Item[4];
	public static Item[] steelArmor = new Item[4];
	public static Item[] chalcArmor = new Item[4];
	public static Item[] sapphireArmor = new Item[4];
	public static Item[] silverArmor = new Item[4];

	public static Item steelBoots;

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
	// public static Item workerDress;
	public static Item workerSuit;

	// public static Item workerWear;

	// silk
	public static Item blackCoat;
	public static Item blackSuit;

	// dress
	public static Item silkDress;
	public static Item silkCape;
	// public static Item silkKimono;

	public static Item silkSkirt;

	// public static Item silkWear;

	// wool
	public static Item peaCoat;
	public static Item modsCoat;
	public static Item woolJacket;

	public static Item woolBoots;

	public static Item woolWear;
	public static Item furWear;
	public static Item furCape;

	// synthetic
	// public static Item blouson;

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
	public static Item throwingArrow;

	public static Item gemShield;
	public static Item riotShield;

	// food
	public static Item bakedApple;
	public static Item foodMaterials;
	public static Item animalFeed;

	// potion
	public static Potion gravity;
	public static PotionType gravityType;

	public static Potion bird;
	public static PotionType birdType;

	public static Potion ocean;
	public static PotionType oceanType;

	public static Potion heavyboots;
	public static PotionType heavybootsType;

	public static Potion nimble;
	public static PotionType nimbleType;

	public static Potion wideMining;
	public static PotionType wideMiningType;

	public static Potion digestive;
	public static PotionType digestiveType;

	public static Potion immunity;
	public static PotionType immunityType;

	public static Potion warp;
	public static PotionType warpType;

	public static Potion projectileResistant;
	public static PotionType projectileResistantType;

	public static Potion reflexion;
	public static PotionType reflexionType;

	public static Potion absorptionEXP;
	public static PotionType absorptionEXPType;

	public static Potion unrepair;
	public static PotionType unrepairType;

	public static Potion clairvoyance;
	public static PotionType clairvoyanceType;

	public static Enchantment venom;
	public static Enchantment corrosion;
	public static Enchantment robber;

	public static VillagerProfession trader;
	public static VillagerProfession agri;
	public static VillagerProfession engineer;
	public static VillagerProfession tailor;

	// plugin
	public static Item circuit;
	public static Item metalMaterials;

	public static Fluid milk;
	public static Block milkBlock;

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
	public static Fluid steam;
	public static Fluid oxygen;

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
	public static Block steamBlock;
	public static Block oxygenBlock;

}
