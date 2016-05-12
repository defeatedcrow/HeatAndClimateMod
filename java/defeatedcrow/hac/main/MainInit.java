package defeatedcrow.hac.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class MainInit {
	private MainInit() {
	}

	// block
	// ores
	public static Block ores;
	public static Block dustBlock;
	public static Block gemBlock;
	public static Block metalBlock;

	// cont
	public static Block logCont;
	public static Block cropCont;
	public static Block dropCont;
	public static Block miscCont;
	public static Block cardboard;

	// building
	public static Block selenite;
	public static Block stairsGlass;
	public static Block stairsGypsum;
	public static Block halfSlab;

	// device
	public static Block chamber;

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

	public static Item repairPatty;

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

	// charms
	public static Item pendant;
	public static Item badge;

	// food
	public static Item bakedApple;

}
