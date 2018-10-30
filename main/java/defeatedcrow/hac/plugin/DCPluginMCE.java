package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;
import shift.mceconomy3.api.MCEconomyAPI;

public class DCPluginMCE {

	public static void load() {
		// metal
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 0), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 1), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 2), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 3), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 4), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 5), 600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 6), 550);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 7), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 8), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 9), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 10), 900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 11), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 12), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 13), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 14), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreIngot, 1, 15), 300);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 1), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 2), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 3), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 4), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 5), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 6), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 7), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 8), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 9), 800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 10), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 11), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 12), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.oreDust, 1, 13), 150);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 0), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 1), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 2), 2700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 3), 9000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 4), 2250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 5), 5400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 6), 4950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 7), 9000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 8), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 9), 2250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 10), 8100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 11), 10800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 12), 2700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 13), 4500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 14), 9000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.metalBlock, 1, 15), 2700);

		// gem
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 1), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 2), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 3), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 4), 1500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 5), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 6), 3000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 7), 3000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 8), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 9), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 10), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 11), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 12), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 13), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 14), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 15), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 16), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 17), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 18), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 19), 8000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 20), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gems, 1, 21), 10000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 0), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 1), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 2), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 3), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 4), 6000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 5), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 6), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 7), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 8), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 9), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 10), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 11), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 12), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gemBlock, 1, 13), 10);

		// dusts
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 1), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 2), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 3), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 4), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 5), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 6), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 7), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 8), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 9), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 10), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 11), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscDust, 1, 12), 500);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodDust, 1, 0), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodDust, 1, 1), 1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodDust, 1, 2), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodDust, 1, 3), 10);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 1), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 2), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 3), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 4), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 5), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 6), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 7), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 8), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothes, 1, 9), 1500);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.silkworm, 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.silkworm, 1, 1), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.silkworm, 1, 2), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.silkworm, 1, 3), 100);

		// foods
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodMaterials, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodMaterials, 1, 1), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodMaterials, 1, 2), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.foodMaterials, 1, 3), 10);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bakedApple, 1, 0), 15);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bakedApple, 1, 1), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bakedApple, 1, 2), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bakedApple, 1, 3), 75);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bakedApple, 1, 4), 30);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 0), 4);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 1), 6);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 2), 6);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 3), 8);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 4), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 5), 8);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 6), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 7), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 8), 8);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 9), 1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 10), 4);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.crops, 1, 11), 2);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 0), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 1), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 2), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 3), 4);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 4), 4);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 5), 4);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 6), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 7), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.seeds, 1, 8), 2);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.teaLeaves, 1, 0), 12);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.teaLeaves, 1, 1), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.teaLeaves, 1, 2), 15);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.petals, 1, 0), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.petals, 1, 1), 50);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.meat, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.meat, 1, 1), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.meat, 1, 2), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.meat, 1, 3), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.meat, 1, 4), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.meat, 1, 5), 60);

		// fluid
		MCEconomyAPI.addPurchaseFluid(MainInit.oil, 0.08); // 80
		MCEconomyAPI.addPurchaseFluid(MainInit.cream, 0.2); // 200
		MCEconomyAPI.addPurchaseFluid(MainInit.greenTea, 0.03); // 30
		MCEconomyAPI.addPurchaseFluid(MainInit.blackTea, 0.03); // 30
		MCEconomyAPI.addPurchaseFluid(MainInit.coffee, 0.03); // 30
		MCEconomyAPI.addPurchaseFluid(MainInit.lemon, 0.005); // 5
		MCEconomyAPI.addPurchaseFluid(MainInit.stock, 0.1); // 100
		MCEconomyAPI.addPurchaseFluid(MainInit.tomatoJuice, 0.02); // 20
		MCEconomyAPI.addPurchaseFluid(MainInit.mazai, 0.3); // 300
		MCEconomyAPI.addPurchaseFluid(MainInit.hotSpring, 0.001); // 1
		MCEconomyAPI.addPurchaseFluid(MainInit.blackLiquor, 0.01); // 10

		MCEconomyAPI.addPurchaseFluid(MainInit.fuelOil, 0.12); // 120
		MCEconomyAPI.addPurchaseFluid(MainInit.fuelGas, 0.09); // 90
		MCEconomyAPI.addPurchaseFluid(MainInit.hydrogen, 0.08); // 80
		MCEconomyAPI.addPurchaseFluid(MainInit.ammonia, 0.2); // 200
		MCEconomyAPI.addPurchaseFluid(MainInit.nitricAcid, 0.25); // 250
		MCEconomyAPI.addPurchaseFluid(MainInit.sulfuricAcid, 0.05); // 50
		MCEconomyAPI.addPurchaseFluid(MainInit.nitrogen, 0.15); // 150
		MCEconomyAPI.addPurchaseFluid(MainInit.ethanol, 0.06); // 60

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.dropCream, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.dropOil, 1, 0), 20);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 1), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 2), 25);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 3), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 4), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 5), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 6), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 7), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 8), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 9), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 10), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 11), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.paperPack, 1, 12), 50);

		// machine
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.machimeMaterials, 1, 0), 2700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.machimeMaterials, 1, 1), 4250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.machimeMaterials, 1, 2), 4850);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.machimeMaterials, 1, 3), 18200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.machimeMaterials, 1, 4), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.machimeMaterials, 1, 5), 9000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 0), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 1), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 2), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 3), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 4), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 5), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 6), 95);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 7), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 8), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 9), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 10), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 11), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 12), 140);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reagent, 1, 13), 50);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.synthetic, 1, 0), 180);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.synthetic, 1, 1), 750);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.synthetic, 1, 2), 180);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gears, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gears, 1, 1), 1050);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gears, 1, 2), 2250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gears, 1, 3), 2450);

		// magic
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.elestial, 1, 0), 450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.expGem, 1, 0), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.gemcore, 1, 0), 6200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.gemcore, 1, 1), 5000);

		// cont
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.logCont, 1, 32767), 80);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 0), 112);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 1), 96);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 2), 128);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 3), 64);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 4), 88);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 5), 480);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 6), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 7), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 8), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 9), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropCont, 1, 10), 192);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 0), 32);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 1), 48);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 2), 48);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 3), 64);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 4), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 5), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 6), 16);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 7), 16);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 8), 64);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 9), 32);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 10), 16);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 11), 800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cropBasket, 1, 12), 16);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dropCont, 1, 0), 1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dropCont, 1, 1), 1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dropCont, 1, 2), 1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dropCont, 1, 3), 160);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dropCont, 1, 4), 32);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscCont, 1, 0), 240);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscCont, 1, 1), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscCont, 1, 2), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.miscCont, 1, 3), 80);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dustBags, 1, 0), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dustBags, 1, 1), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dustBags, 1, 2), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dustBags, 1, 3), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dustBags, 1, 4), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dustBags, 1, 5), 8);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cardboard, 1, 0), 720);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cardboard, 1, 1), 160);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cardboard, 1, 2), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cardboard, 1, 3), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cardboard, 1, 4), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cardboard, 1, 5), 480);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.fuelCont, 1, 0), 450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.fuelCont, 1, 1), 630);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.fuelCont, 1, 2), 2250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.fuelCont, 1, 3), 1350);

		// tools
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCInit.climate_checker, 1, 0), 4080);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stevenson_screen, 1, 0), 4100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.thermometer, 1, 0), 4150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.windvane, 1, 0), 5100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stoneYagen, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.brassYagen, 1, 0), 1550);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.crowDrill, 1, 0), -1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.torqueChecker, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.entityScope, 1, 0), 3500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.scope, 1, 0), 7000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.repairPutty, 1, 0), 270);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.repairPutty, 1, 1), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.repairPutty, 1, 2), 60);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wrench, 1, 0), 1800);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[0], 1, 0), 375);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[1], 1, 0), 900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[2], 1, 0), 1500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[3], 1, 0), 825);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[4], 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[5], 1, 0), 2250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[6], 1, 0), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcAxe[7], 1, 0), 150);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[0], 1, 0), 375);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[1], 1, 0), 900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[2], 1, 0), 1500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[3], 1, 0), 825);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[4], 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[5], 1, 0), 2250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[6], 1, 0), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcPickaxe[7], 1, 0), 150);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[0], 1, 0), 125);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[1], 1, 0), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[2], 1, 0), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[3], 1, 0), 275);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[4], 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[5], 1, 0), 750);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[6], 1, 0), 600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSpade[7], 1, 0), 50);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[0], 1, 0), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[1], 1, 0), 600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[2], 1, 0), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[3], 1, 0), 550);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[4], 1, 0), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[5], 1, 0), 1500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[6], 1, 0), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcSword[7], 1, 0), 100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcScythe[0], 1, 0), 375);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcScythe[1], 1, 0), 900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcScythe[2], 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.dcScythe[3], 1, 0), 150);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.earthSpade, 1, 0), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.earthRake, 1, 0), 450);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.crossbow, 1, 0), 650);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.gun, 1, 0), 700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 1), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 2), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 3), 160);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 4), 125);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 5), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 6), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cartridge, 1, 7), 120);

		int[] num = {
				5,
				7,
				6,
				4
		};
		for (int i = 0; i < 4; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.brassArmor[i], 1, 0), 125 * i + 50);
			MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.steelArmor[i], 1, 0), 300 * i + 50);
			MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalcArmor[i], 1, 0), 20 * i + 50);
			MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.sapphireArmor[i], 1, 0), 750 * i + 50);
			MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.titaniumArmor[i], 1, 0), 600 * i + 50);
		}

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.linenUnder, 1, 0), 450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.linenCoat, 1, 0), 450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothUnder, 1, 0), 800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clothCoat, 1, 0), 800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.workerSuit, 1, 0), 900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.blackSuit, 1, 0), 7000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.trackSuit, 1, 0), 5300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.combatDress, 1, 0), 5500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.blackCoat, 1, 0), 7000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.hoodie, 1, 0), 800);
		// MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.hoodieB, 1, 0), 800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.peaCoat, 1, 0), 550);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.magicUnder, 1, 0), 12600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.magicCoat, 1, 0), 12600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.leatherHat, 1, 0), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cottonHat, 1, 0), 580);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.woolWear, 1, 0), 380);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.furWear, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.woolBoots, 1, 0), 250);

		// builds
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.flowerPot, 1, 0), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.flowerPot, 1, 1), 800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cushionGray, 1, 0), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.cushionGray, 1, 1), 250);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bricks, 1, 0), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bricks, 1, 1), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bricks, 1, 2), 2);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 0), 25);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 1), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 2), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 3), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 4), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 5), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 6), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 7), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 8), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 9), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.builds, 1, 10), 20);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.linoleum, 1, 32767), 30);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.selenite, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.selenite, 1, 1), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.selenite, 1, 2), 180);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.selenite, 1, 3), 40);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 0), 460);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 1), 140);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 2), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 3), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 4), 580);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 5), 260);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 6), 220);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 7), 240);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 8), 460);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 9), 140);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 10), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 11), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 12), 660);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 13), 340);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 14), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chalLamp, 1, 15), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallLamp, 1, 0), 660);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallLamp, 1, 1), 340);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallLamp, 1, 2), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallLamp, 1, 3), 320);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.syntheticBlock, 1, 32767), 180);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.clayBricks, 1, 32767), 130);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stairsGypsum, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stairsGlass, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stairsMarble, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stairsBedrock, 1, 0), 600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stairsSerpentine, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stairsDirtbrick, 1, 0), 30);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab, 1, 0), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab, 1, 1), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab, 1, 2), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab, 1, 3), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab, 1, 4), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab, 1, 5), 10);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 230);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.halfSlab2, 1, 0), 60);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceMarble, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceGypsum, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceSerpentine, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceBedrock, 1, 0), 420);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceSteel, 1, 0), 580);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceNetSteel, 1, 0), 480);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceAluminium, 1, 0), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceGlass, 1, 0), 230);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceNet, 1, 0), 280);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceLadder, 1, 0), 350);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fenceLadderSteel, 1, 0), 580);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.awning, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.awning, 1, 1), 920);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.awning, 1, 2), 160);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.awning, 1, 3), 320);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.tableWood, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.tableDark, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.tableGypsum, 1, 0), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.tableMarble, 1, 0), 100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stoolRed, 1, 0), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.stoolBlack, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.sofaRed, 1, 0), 350);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.sofaBlack, 1, 0), 350);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.carpetRed, 1, 0), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.carpetWhite, 1, 0), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.carpetGray, 1, 0), 80);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.squaretableWood, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.squaretableMarble, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.squaretableChecker, 1, 0), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.squaretableBlack, 1, 0), 2000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chairWood, 1, 0), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chairMarble, 1, 0), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chairChecker, 1, 0), 450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chairBlack, 1, 0), 2050);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chestWood, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chestMarble, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chestChecker, 1, 0), 480);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chestBlack, 1, 0), 2400);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallshelfWood, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallshelfMarble, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallshelfChecker, 1, 0), 480);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.wallshelfBlack, 1, 0), 2400);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chestMetal, 1, 0), 4880);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chestMagnet, 1, 0), 8100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chestVillage, 1, 0), 28400);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.pillarSteel, 1, 0), 600);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.plate, 1, 0), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.plate, 1, 1), 200);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.hedgeSpring, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.hedgeSummer, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.hedgeAutumn, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.hedgeWinter, 1, 0), 20);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chandelierGypsum, 1, 0), 350);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.itemDoorMarble, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.itemDoorSteel, 1, 0), 2800);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.sinkMetal, 1, 0), 2750);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.sinkChest, 1, 0), 2750);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.curtainWhite, 1, 0), 220);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.realtimeClock, 1, 0), 16500);

		// device
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.chamber, 1, 0), 1100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.shitirin, 1, 0), 280);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.fuelStove, 1, 0), 3850);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.burner, 1, 0), 8200);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MainInit.bellow, 1, 0), 3750);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.dish, 1, 0), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.dish, 1, 1), 4000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.potteryPot, 1, 0), 980);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steelPot, 1, 0), 3250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.teaPot, 1, 0), 3700);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.silkwormBox, 1, 0), 50);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cupSilver, 1, 0), 2750);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cupSilver, 1, 1), 650);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cupSilver, 1, 2), 150);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.plateMeal, 1, 0), 750);

		// magic

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.lotusCandle, 1, 0), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.lotusCandleBlack, 1, 0), 1000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.macehandle, 1, 0), 9000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 0), 16600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 1), 19000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 2), 20500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 3), 17000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 4), 23100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 5), 16800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 6), 17300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceStarItem, 1, 7), 42500);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceSun, 1, 0), 25600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceMoon, 1, 0), 28000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceBird, 1, 0), 29500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceIce, 1, 0), 26000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceOcean, 1, 0), 32100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceBurn, 1, 0), 25800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceFlower, 1, 0), 26300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.maceGlory, 1, 0), 51500);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 0), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 1), 1700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 2), 1700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 3), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 4), 3200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 5), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 6), 4700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 7), 4700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 8), 1950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 9), 11700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 10), 2200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 11), 1700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 12), 1950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 13), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 14), 2150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 15), 2200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 16), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 17), 9700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 18), 2700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.pendant, 1, 19), 11700);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 0), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 1), 1700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 2), 1700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 3), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 4), 3200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 5), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 6), 4700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 7), 4700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 8), 1950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 9), 11700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 10), 2200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 11), 1700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 12), 1950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 13), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 14), 2150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 15), 2200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 16), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 17), 9700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 18), 2700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.badge, 1, 19), 11700);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerSilver, 1, 0), 300);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 0), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 1), 1800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 2), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 3), 1900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 4), 330);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 5), 1850);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 6), 900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 7), 3600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 8), 1820);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 9), 820);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 10), 1020);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 11), 820);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 12), 3950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 13), 3800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 14), 21300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.daggerMagic, 1, 15), 11500);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.amulet, 1, 0), 2620);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.amulet, 1, 1), 2750);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.amulet, 1, 2), 3270);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.amulet, 1, 3), 6720);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.amulet, 1, 4), 3600);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.gemBootsBird, 1, 0), 27200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.gemBootsFish, 1, 0), 31300);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.timeCage, 1, 0), 9400);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.biomeOrb, 1, 0), 18000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.biomeOrb, 1, 1), 18700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.biomeOrb, 1, 2), 18200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MagicInit.biomeOrb, 1, 3), 18400);

		// food_adv
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.dairy, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.dairy, 1, 1), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.dairy, 1, 2), 70);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastry, 1, 0), 60);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 1), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 2), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 3), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 4), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 5), 90);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 6), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 7), 320);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bread, 1, 8), 100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 0), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 1), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 2), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 3), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 4), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 5), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 6), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 7), 110);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 8), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 9), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 10), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sticks, 1, 11), 50);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 0), 180);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 1), 190);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 2), 110);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 3), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 4), 420);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 5), 430);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 6), 450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 7), 460);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 8), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastryRound, 1, 9), 160);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 90);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 140);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 130);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 90);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 90);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 130);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.pastrySquare, 1, 2), 140);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sandwich, 1, 0), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sandwich, 1, 1), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sandwich, 1, 2), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.sandwich, 1, 3), 60);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.clubsandwich, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.clubsandwich, 1, 0), 100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.ricebowl, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.ricebowl, 1, 0), 40);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 0), 130);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 1), 140);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 2), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 3), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 4), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 5), 90);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 6), 90);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.steakplate, 1, 7), 100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.plateSoup, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.plateSoup, 1, 1), 130);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.plateSoup, 1, 2), 280);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.plateSoup, 1, 3), 290);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 1), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 2), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 3), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 4), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 5), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 6), 70);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 7), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 8), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 9), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.bowlSoup, 1, 10), 50);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.salad, 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.salad, 1, 1), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.salad, 1, 2), 40);

		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 0), 140);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 1), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 2), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 3), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 4), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 5), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 6), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 7), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 8), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(FoodInit.cake, 1, 9), 30);

		// machine
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.windmill, 1, 0), 1100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.windmill_l, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.handcrank, 1, 0), 2000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft_s, 1, 0), 1600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft_l, 1, 0), 4250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft_t_a, 1, 0), 5850);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft_t_b, 1, 0), 5850);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft_x, 1, 0), 7450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.gearbox, 1, 0), 8450);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft2_s, 1, 0), 4250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft2_l, 1, 0), 10950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft2_t_a, 1, 0), 15200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft2_t_b, 1, 0), 15200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft2_x, 1, 0), 19450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.gearbox2, 1, 0), 15050);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft3_s, 1, 0), 3650);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft3_l, 1, 0), 9750);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft3_t_a, 1, 0), 13400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft3_t_b, 1, 0), 13400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.shaft3_t_b, 1, 0), 17050);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.fan, 1, 0), 3200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.spinning, 1, 0), 2600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.piston, 1, 0), 5850);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.watermill, 1, 0), 2300);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.redbox, 1, 0), 2600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.conveyor, 1, 0), 460);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.stonemill, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.catapult, 1, 0), 1200);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.hopperFilter, 1, 0), 2800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.hopperFilterG, 1, 0), 18600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.hopperGold, 1, 0), 18800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.hopperSilver, 1, 0), 10100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.faucet, 1, 0), 4500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.IBC, 1, 0), 2100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.hopperFluid, 1, 0), 2800);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.waterPump, 1, 0), 5950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.heatPump, 1, 0), 5800);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.boilerTurbine, 1, 0), 15800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.dieselEngine, 1, 0), 25800);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.motor, 1, 0), 12200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.dynamo, 1, 0), 12800);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.freezer, 1, 0), 23000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.crusher, 1, 0), 9850);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.pressMachine, 1, 0), 23900);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.reactor, 1, 0), 28900);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.creativeBox, 1, 0), -1);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.adapterPanel, 1, 0), 3850);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.acceptorPanel, 1, 0), 3450);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.adapterFluidPanel, 1, 0), 3950);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.acceptorFluidPanel, 1, 0), 3550);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.adapterCard, 1, 0), 1050);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.adapterCard, 1, 1), 1350);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.adapterCard, 1, 2), 1050);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.adapterCard, 1, 3), 1350);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.wirelessPortal, 1, 0), 29950);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.monitorRS, 1, 0), 1860);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.monitorRF, 1, 0), 6100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.monitorFluid, 1, 0), 2000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.monitorTorque, 1, 0), 4280);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.monitorItem, 1, 0), 1800);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.motorMinecart, 1, 0), 19000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.scooter, 1, 32767), 23600);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.magneticHover, 1, 0), 46000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.mold, 1, 0), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAluminium, 1, 0), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAluminium, 1, 1), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAluminium, 1, 2), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAluminium, 1, 3), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAlloy, 1, 0), 1100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAlloy, 1, 1), 1100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAlloy, 1, 2), 1100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.moldAlloy, 1, 3), 1100);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.catalyst, 1, 0), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.catalyst, 1, 1), 1200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.catalyst, 1, 2), 700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.catalyst, 1, 3), 700);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.dynamite, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.dynamite, 1, 1), 120);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 0), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 1), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 2), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 3), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 4), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 5), 2100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 6), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 7), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 8), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.platingChrome, 1, 9), 1000);

		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.rotaryBlade, 1, 0), 4700);
		MCEconomyAPI.addPurchaseItem(new ItemStack(MachineInit.rotaryBlade, 1, 1), 5300);

	}

	/*
	 * HaCの価格算出式
	 * .
	 * 基本素材:
	 * Tier1金属 < 鉄 < Tier2金属 < 銀 くらい
	 * 合金は平均値+Tier加算
	 * Tier1: 50, Tier2:100, Tier3:300
	 * ダストは半値
	 * 銀は本来、金の半値だが、HaCは産出が多いので1/4
	 * .
	 * 宝石:
	 * 生成量を考慮して任意
	 * レアドロップのみの場合は価格が上がる
	 * .
	 * 手間賃:
	 * 手動クラフト: +20
	 * 精錬: +10
	 * Tier1マシン: +10 (ただし寒冷条件だと*2)
	 * Tier2マシン: +20
	 * Tier3マシン: +30
	 * .
	 * 端数は切り上げ
	 */

}
