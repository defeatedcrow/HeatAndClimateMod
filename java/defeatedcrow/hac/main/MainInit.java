package defeatedcrow.hac.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;

public class MainInit {
	private MainInit() {
	}

	// block
	// ores
	public static Block ores;
	public static Block ores_2;
	public static Block dustBlock;
	public static Block gemBlock;
	public static Block metalBlock;

	// cont
	public static Block logCont;
	public static Block cropCont;
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
	public static Block halfSlab;
	public static Block builds;

	// furniture
	public static Block chalLamp;
	public static Block tableMarble;
	public static Block tableGypsum;
	public static Block tableWood;
	public static Block tableDark;
	public static Block stoolBlack;
	public static Block stoolRed;
	public static Block sofaBlack;
	public static Block sofaRed;
	public static Block carpetRed;
	public static Block carpetWhite;
	public static Block carpetGray;

	// device
	public static Block chamber;
	public static Block shitirin;
	public static Block stevenson_screen;

	// item
	// ores
	public static Item oreDust;
	public static Item oreIngot;
	public static Item gems;
	public static Item miscDust;
	public static Item materials;

	// tool
	public static Item stoneYagen;
	public static Item brassYagen;
	public static Item crowDrill;

	public static Item repairPutty;
	public static Item wrench;

	public static Item[] dcAxe = new Item[6];
	public static Item[] dcPickaxe = new Item[6];
	public static Item[] dcSpade = new Item[6];
	public static Item[] dcSword = new Item[6];

	// armor
	public static Item[] brassArmor = new Item[4];
	public static Item[] steelArmor = new Item[4];
	public static Item[] chalcArmor = new Item[4];
	public static Item[] sapphireArmor = new Item[4];

	public static Item linenUnder;
	public static Item linenCourt;
	public static Item clothUnder;

	public static Item workerSuit;
	public static Item blackSuit;

	// food
	public static Item bakedApple;
	public static Item foodMaterials;

	// potion
	public static Potion gravity;
	public static PotionType gravityType;

}
