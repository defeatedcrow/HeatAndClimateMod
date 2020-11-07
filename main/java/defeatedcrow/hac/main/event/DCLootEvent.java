package defeatedcrow.hac.main.event;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
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
			Item meat = FoodInit.meat;
			Item gear = MainInit.gears;
			Item cloth = MainInit.clothes;

			// 村
			if (event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(ingot, 20, 8, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(4, 6)),
						new SetCount(new LootCondition[0], new RandomValueRange(5, 8)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":vil_ingot_brass1"));
					pool.addEntry(new LootEntryItem(gear, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(1)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":vil_gear_brass1"));
					pool.addEntry(new LootEntryItem(cloth, 20, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(2)),
						new SetCount(new LootCondition[0], new RandomValueRange(2, 4)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":vil_clothes1"));
					if (ModuleConfig.tool) {
						pool.addEntry(new LootEntryItem(MainInit.dcScythe[1], 5, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":vil_scythe1"));
					}
					pool.addEntry(new LootEntryItem(MainInit.wrench, 3, 1, new LootFunction[0], new LootCondition[0],
							ClimateMain.MOD_ID + ":vil_wranch"));
				}
			}
			// ダンジョン
			else if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(ingot, 20, 5, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(4, 5)),
						new SetCount(new LootCondition[0], new RandomValueRange(5, 8)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":dungeon_ingot_brass1"));
					pool.addEntry(new LootEntryItem(gear, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(1)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":dungeon_gear_brass1"));
					pool.addEntry(new LootEntryItem(ingot, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(6)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 5)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":dungeon_ingot_ns"));
					pool.addEntry(new LootEntryItem(ingot, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(5)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 5)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":dungeon_ingot_steel"));
					pool.addEntry(new LootEntryItem(jerky, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(0, 3)),
						new SetCount(new LootCondition[0], new RandomValueRange(3, 8)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":dungeon_food_jerky"));
				}
			}
			// ピラミッド 赤白
			else if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(ingot, 10, 4, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(3)),
						new SetCount(new LootCondition[0], new RandomValueRange(2, 6)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":desert_ingot_silver1"));
					pool.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(13)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":desert_gem_per"));
					pool.addEntry(new LootEntryItem(gem, 8, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(5)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":desert_gem_mal"));
					pool.addEntry(new LootEntryItem(MainInit.leatherHat, 5, 1, new LootFunction[0],
							new LootCondition[0], ClimateMain.MOD_ID + ":desert_hat1"));
				}
				LootPool pool2 = loot.getPool("pool2");
				if (pool2 != null) {
					pool2.addEntry(new LootEntryItem(cloth, 20, 5, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(2)),
						new SetCount(new LootCondition[0], new RandomValueRange(4, 6)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":desert_cloth"));
					pool2.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(21)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":desert_gem_kun"));
					pool2.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(6)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":desert_gem_cel"));
				}
			}
			// 廃坑 白緑
			else if (event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(ingot, 10, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(3, 4)),
						new SetCount(new LootCondition[0], new RandomValueRange(3, 5)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":mine_ingot_brass"));
					pool.addEntry(new LootEntryItem(gem, 10, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(4)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":mine_gem_sapphire"));
					pool.addEntry(new LootEntryItem(gem, 10, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(19)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":mine_gem_jed"));
					pool.addEntry(new LootEntryItem(jerky, 5, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(3)),
						new SetCount(new LootCondition[0], new RandomValueRange(3, 8)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":mine_food_jerky"));
				}
				LootPool pool2 = loot.getPool("pool2");
				if (pool2 != null) {
					pool2.addEntry(new LootEntryItem(gem, 10, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(6)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":mine_gem_cel"));
					pool2.addEntry(new LootEntryItem(gem, 10, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(19)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":mine_gem_jed2"));
					if (ModuleConfig.clothes_advanced) {
						pool2.addEntry(new LootEntryItem(MainInit.workerSuit, 2, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":mine_suit"));
					}
					if (ModuleConfig.tool) {
						pool2.addEntry(new LootEntryItem(MainInit.dcPickaxe[1], 5, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":mine_pickaxe"));
					}
					pool2.addEntry(new LootEntryItem(Item.getItemFromBlock(MainInit.logCont), 10, 3,
							new LootFunction[0], new LootCondition[0], ClimateMain.MOD_ID + ":mine_logs"));
				}
			}
			// 要塞 黒青
			else if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(22)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":stronghold_gem_opal"));
					pool.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(20)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":stronghold_gem_moon"));
					if (ModuleConfig.tool) {
						pool.addEntry(new LootEntryItem(MainInit.dcPickaxe[1], 5, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":stronghold_pickaxe"));
					}
				}
			} else if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					if (ModuleConfig.magic_advanced) {
						pool.addEntry(new LootEntryItem(MagicInit.magicCard, 1, 9, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":stronghold_card9"));
						pool.addEntry(new LootEntryItem(MagicInit.magicCard, 1, 14, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":stronghold_card14"));
						pool.addEntry(new LootEntryItem(MagicInit.magicCard, 1, 15, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":stronghold_card15"));
					}
				}
			}
			// ネザー 赤黒
			else if (event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(21)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":nether_gem_kun"));
					pool.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(22)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":nether_gem_opal"));
					pool.addEntry(new LootEntryItem(jerky, 10, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(1)),
						new SetCount(new LootCondition[0], new RandomValueRange(3, 8)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":nether_food_egg"));
				}
			}
			// ジャングル寺院 青緑
			else if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(19)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":jungle_gem_jed"));
					pool.addEntry(new LootEntryItem(gem, 15, 3, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(20)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":jungle_gem_moon"));
					pool.addEntry(new LootEntryItem(cloth, 15, 1, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(8, 9)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":jungle_cloth_magic"));
					if (ModuleConfig.magic) {
						pool.addEntry(new LootEntryItem(MagicInit.magicCard, 15, 3, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(0, 9)),
							new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
								ClimateMain.MOD_ID + ":jungle_card"));
					}
					if (ModuleConfig.weapon_advanced) {
						pool.addEntry(new LootEntryItem(MainInit.crossbow, 10, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":jungle_bow"));
					}
					if (ModuleConfig.clothes_advanced) {
						pool.addEntry(new LootEntryItem(MainInit.magicCoat, 5, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":jungle_coat"));
					}
				}
			}
			// 洋館
			else if (event.getName().equals(LootTableList.CHESTS_WOODLAND_MANSION)) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(cloth, 25, 1, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(6, 7)),
						new SetCount(new LootCondition[0], new RandomValueRange(1, 3)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":forest_cloth_silk"));
					if (ModuleConfig.clothes_advanced) {
						pool.addEntry(new LootEntryItem(MainInit.blackSuit, 10, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":forest_suit"));
						pool.addEntry(new LootEntryItem(MainInit.combatDress, 10, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":forest_combat"));
						pool.addEntry(new LootEntryItem(MainInit.modsCoat, 10, 1, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":forest_mods"));
					}
					if (ModuleConfig.weapon_advanced) {
						pool.addEntry(new LootEntryItem(MainInit.gun, 15, 1, new LootFunction[0], new LootCondition[0],
								ClimateMain.MOD_ID + ":forest_gun_musket"));
						pool.addEntry(new LootEntryItem(MainInit.cartridge, 15, 16, new LootFunction[] {
							new SetMetadata(new LootCondition[0], new RandomValueRange(1, 3)),
							new SetCount(new LootCondition[0], new RandomValueRange(16, 32)) }, new LootCondition[0],
								ClimateMain.MOD_ID + ":forest_gun_cartridge"));
					}
					if (ModuleConfig.magic_advanced) {
						pool.addEntry(new LootEntryItem(MagicInit.colorGauntlet, 10, 2, new LootFunction[0],
								new LootCondition[0], ClimateMain.MOD_ID + ":forest_gauntret"));
						pool.addEntry(new LootEntryItem(Item.getItemFromBlock(MagicInit.pictureGreen), 5, 0,
								new LootFunction[0], new LootCondition[0], ClimateMain.MOD_ID + ":forest_picture1"));
						pool.addEntry(new LootEntryItem(Item.getItemFromBlock(MagicInit.pictureWhite), 5, 0,
								new LootFunction[0], new LootCondition[0], ClimateMain.MOD_ID + ":forest_picture2"));
					}
				}
			}
			// 釣り
			else if (event.getName().equals(LootTableList.GAMEPLAY_FISHING_FISH) && ModuleConfig.food) {
				LootTable loot = event.getTable();
				LootPool pool = loot.getPool("main");
				if (pool != null) {
					pool.addEntry(new LootEntryItem(meat, 10, 1, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(2)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":fish_ika"));
					pool.addEntry(new LootEntryItem(meat, 15, 1, new LootFunction[] {
						new SetMetadata(new LootCondition[0], new RandomValueRange(9)) }, new LootCondition[0],
							ClimateMain.MOD_ID + ":fish_ebi"));
				}
			}
		}
	}

}
