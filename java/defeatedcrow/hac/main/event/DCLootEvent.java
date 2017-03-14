package defeatedcrow.hac.main.event;

import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DCLootEvent {

	@SubscribeEvent
	public void onEvent(LootTableLoadEvent event) {
		if (event.getTable() != null) {
			Item ingot = MainInit.oreIngot;
			Item gem = MainInit.gems;
			Item jerky = MainInit.bakedApple;
			if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(ingot, 10, 5, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(3, 4)),
							new SetCount(new LootCondition[0], new RandomValueRange(5, 8))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":desert_ingot_silver2"));
					pool.addEntry(new LootEntryItem(gem, 10, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(4)),
							new SetCount(new LootCondition[0], new RandomValueRange(1, 3))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":desert_gem_sapphire2"));
					pool.addEntry(new LootEntryItem(MainInit.leatherHat, 5, 1, new LootFunction[0],
							new LootCondition[0], ClimateMain.MOD_ID + ":desert_hat2"));
				}
				LootPool pool2 = loot.getPool("pool2");
				if (pool2 != null) {
					pool2.addEntry(new LootEntryItem(ingot, 10, 5, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(3, 4)),
							new SetCount(new LootCondition[0], new RandomValueRange(5, 8))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":desert_ingot_silver"));
					pool2.addEntry(new LootEntryItem(gem, 10, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(4)),
							new SetCount(new LootCondition[0], new RandomValueRange(1, 3))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":desert_gem_sapphire"));
					pool2.addEntry(new LootEntryItem(MainInit.leatherHat, 5, 1, new LootFunction[0],
							new LootCondition[0], ClimateMain.MOD_ID + ":desert_hat"));
				}
			} else if (event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(ingot, 10, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(3, 4)),
							new SetCount(new LootCondition[0], new RandomValueRange(3, 5))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_ingot_silver"));
					pool.addEntry(new LootEntryItem(gem, 5, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(4)),
							new SetCount(new LootCondition[0], new RandomValueRange(1, 3))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_gem_sapphire"));
					pool.addEntry(new LootEntryItem(gem, 5, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(7)),
							new SetCount(new LootCondition[0], new RandomValueRange(1, 3))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_gem_clam"));
					pool.addEntry(new LootEntryItem(jerky, 5, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(3)),
							new SetCount(new LootCondition[0], new RandomValueRange(3, 8))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_food_jerky"));
				}
				LootPool pool2 = loot.getPool("pool2");
				if (pool2 != null) {
					pool2.addEntry(new LootEntryItem(MainInit.wrench, 3, 1, new LootFunction[0], new LootCondition[0],
							ClimateMain.MOD_ID + ":mine_wranch"));
					pool2.addEntry(new LootEntryItem(MainInit.leatherHat, 2, 1, new LootFunction[0],
							new LootCondition[0], ClimateMain.MOD_ID + ":mine_hat"));
					pool2.addEntry(new LootEntryItem(MainInit.dcPickaxe[0], 5, 1, new LootFunction[0],
							new LootCondition[0], ClimateMain.MOD_ID + ":mine_pickaxe"));
				}
			} else if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(ingot, 10, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(3, 4)),
							new SetCount(new LootCondition[0], new RandomValueRange(3, 5))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_ingot_silver"));
					pool.addEntry(new LootEntryItem(gem, 5, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(4)),
							new SetCount(new LootCondition[0], new RandomValueRange(1, 3))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_gem_sapphire"));
					pool.addEntry(new LootEntryItem(gem, 5, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(7)),
							new SetCount(new LootCondition[0], new RandomValueRange(1, 3))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_gem_clam"));
					pool.addEntry(new LootEntryItem(jerky, 5, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(3)),
							new SetCount(new LootCondition[0], new RandomValueRange(3, 8))
					}, new LootCondition[0], ClimateMain.MOD_ID + ":mine_food_jerky"));
				}
			}
		}
	}

}
