package defeatedcrow.hac.main.worldgen.vein;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.OreSet;
import defeatedcrow.hac.main.api.orevein.VeinTable;
import defeatedcrow.hac.main.api.orevein.VeinTableRegisterEvent;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;

public class VeinTableJson {

	private VeinTableJson() {};

	public static VeinTableJson INSTANCE = new VeinTableJson();

	private static final Map<EnumVein, Map<String, Object>> jsonMap = new HashMap<EnumVein, Map<String, Object>>();

	private static Path parent = null;

	private static void loadVeins() {
		if (!INSTANCE.jsonMap.isEmpty()) {
			for (Entry<EnumVein, Map<String, Object>> e1 : INSTANCE.jsonMap.entrySet()) {
				EnumVein type = e1.getKey();

				BlockSet layer = STONE;
				List<OreSet> list = Lists.newArrayList();

				Map<String, Object> map = e1.getValue();
				if (map != null && !map.isEmpty()) {

					if (map.containsKey("layer")) {
						Object o1 = map.get("layer");
						if (o1 instanceof String) {
							String sl = (String) o1;
							BlockSet bl = MainUtil.getBlockSetFromString(sl);
							if (!bl.equals(OreSetDC.AIR)) {
								layer = bl;
							}
						}
					}

					if (map.containsKey("entries")) {
						Object o2 = map.get("entries");
						if (o2 instanceof List) {
							List<Object> l2 = (List<Object>) o2;
							for (Object o3 : l2) {
								if (o3 instanceof Map) {
									try {
										Map<String, Object> ores = (Map<String, Object>) o3;
										BlockSet b1 = STONE;
										BlockSet b2 = null;
										int weight = 10;
										int chance = 0;

										if (ores.containsKey("ore")) {
											Object o4 = ores.get("ore");
											if (o4 instanceof String) {
												String s1 = (String) o4;
												b1 = MainUtil.getBlockSetFromString(s1);
											}
										}

										if (ores.containsKey("secondary")) {
											Object o5 = ores.get("secondary");
											if (o5 instanceof String) {
												String s2 = (String) o5;
												b2 = MainUtil.getBlockSetFromString(s2);
											}
										}

										if (ores.containsKey("weight")) {
											Number n1 = (Number) ores.get("weight");
											if (n1 instanceof Integer) {
												weight = n1.intValue();
											}
											if (n1 instanceof Double) {
												weight = MathHelper.floor(n1.doubleValue());
											}

										}

										if (ores.containsKey("secondary_chance")) {
											Number n2 = (Number) ores.get("secondary_chance");
											if (n2 instanceof Integer) {
												chance = n2.intValue();
											}
											if (n2 instanceof Double) {
												chance = MathHelper.floor(n2.doubleValue());
											}
										}

										if (b1 != null && weight > 0) {
											OreSetDC set = null;
											if (b2 != null && chance > 0) {
												set = new OreSetDC(weight, b1, b2, chance);
											} else {
												set = new OreSetDC(weight, b1);
											}
											if (set != null)
												list.add(set);
										}

									} catch (Error e) {
										DCLogger.warnLog("VainTableJson : Error entry found. This entry is ignored.");
										continue;
									} catch (Exception e) {
										DCLogger.warnLog("VainTableJson : Error entry found. This entry is ignored. ");
										continue;
									}
								}
							}
						}
					}

					VeinTable table = new VeinTable(type, new OreSetDC(100, layer));
					if (list.isEmpty()) {
						list.add(new OreSetDC(100, layer));
					}

					table.addOres(list);

					if (VeinTableRegister.INSTANCE.list.add(table)) {
						DCLogger.debugInfoLog("VainTableJson : Successfilly loaded: " + type + " count" + table.tableCount);
					}
				}
			}

			VeinTableRegisterEvent event = new VeinTableRegisterEvent(VeinTableRegister.INSTANCE);
			MinecraftForge.EVENT_BUS.post(event);
		}
	}

	public static void pre() {
		for (EnumVein type : EnumVein.VALUES) {
			String fName = type.toString().toLowerCase();
			File file = new File(parent + "/" + fName + ".json");

			if (file != null) {
				try {
					if (!file.exists()) {
						continue;
					}

					if (file.canRead()) {
						FileInputStream fis = new FileInputStream(file.getPath());
						InputStreamReader isr = new InputStreamReader(fis);
						JsonReader jsr = new JsonReader(isr);
						Gson gson = new Gson();
						Map get = gson.fromJson(jsr, Map.class);

						isr.close();
						fis.close();
						jsr.close();

						if (get != null && !get.isEmpty()) {
							INSTANCE.jsonMap.put(type, get);

						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (!INSTANCE.jsonMap.isEmpty()) {
			loadVeins();
		}
	}

	// 生成は初回のみ
	public static void post() {
		boolean b = registerVeins();

		if (b) {

			for (Entry<EnumVein, Map<String, Object>> entry : INSTANCE.jsonMap.entrySet()) {
				String fName = entry.getKey().toString().toLowerCase();
				File file = new File(parent + "/" + fName + ".json");
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				try {
					if (!file.exists() && !file.createNewFile()) {
						continue;
					}

					if (file.canWrite()) {
						FileOutputStream fos = new FileOutputStream(file.getPath());
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						JsonWriter jsw = new JsonWriter(osw);
						jsw.setIndent("    ");
						Gson gson = new Gson();
						gson.toJson(entry.getValue(), Map.class, jsw);

						osw.close();
						fos.close();
						jsw.close();
					}

				} catch (IOException e) {
					DCLogger.debugInfoLog("VainTableJson : Failed to create json file: " + fName);
					e.printStackTrace();
				}
			}

			loadVeins();

		} else {
			DCLogger.debugInfoLog("VainTableJson : Orevein custom data json is already exists.");
			return;
		}

	}

	public static boolean registerVeins() {

		boolean ret = false;

		if (!INSTANCE.jsonMap.containsKey(EnumVein.RED)) {
			Map<String, Object> red = new HashMap<String, Object>();
			red.put("layer", blockString(new BlockSet(MainInit.layerNew, 1)));
			List<Map<String, Object>> redMap = Lists.newArrayList();
			redMap.add(getEntry(80, new BlockSet(MainInit.oreNew, 0), new BlockSet(MainInit.oreNew, 5), 5));
			redMap.add(getEntry(20, new BlockSet(MainInit.layerNew, 3)));
			redMap.add(getEntry(40, new BlockSet(MainInit.layerNew, 1)));
			redMap.add(getEntry(5, new BlockSet(MainInit.oreNew, 5)));
			red.put("entries", redMap);
			INSTANCE.jsonMap.put(EnumVein.RED, red);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.HIGH_RED)) {
			Map<String, Object> high_red = new HashMap<String, Object>();
			high_red.put("layer", blockString(new BlockSet(MainInit.layerNew, 1)));
			List<Map<String, Object>> high_redMap = Lists.newArrayList();
			high_redMap.add(getEntry(60, new BlockSet(MainInit.oreNew, 0), new BlockSet(MainInit.oreNew, 5), 5));
			high_redMap.add(getEntry(20, new BlockSet(MainInit.layerNew, 3)));
			high_redMap.add(getEntry(50, new BlockSet(MainInit.layerNew, 1)));
			high_redMap.add(getEntry(20, new BlockSet(MainInit.oreNew, 5)));
			high_red.put("entries", high_redMap);
			INSTANCE.jsonMap.put(EnumVein.HIGH_RED, high_red);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.GREEN)) {
			Map<String, Object> green = new HashMap<String, Object>();
			green.put("layer", blockString(new BlockSet(MainInit.layerNew, 6)));
			List<Map<String, Object>> greenMap = Lists.newArrayList();
			greenMap.add(getEntry(80, new BlockSet(MainInit.oreNew, 1), new BlockSet(MainInit.oreNew, 6), 5));
			greenMap.add(getEntry(30, new BlockSet(MainInit.layerNew, 6), new BlockSet(Blocks.EMERALD_ORE, 0), 20));
			greenMap.add(getEntry(5, new BlockSet(MainInit.oreNew, 6)));
			greenMap.add(getEntry(10, new BlockSet(Blocks.CLAY, 0)));
			green.put("entries", greenMap);
			INSTANCE.jsonMap.put(EnumVein.GREEN, green);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.BLUE)) {
			Map<String, Object> blue = new HashMap<String, Object>();
			blue.put("layer", blockString(new BlockSet(MainInit.layerNew, 0)));
			List<Map<String, Object>> blueMap = Lists.newArrayList();
			blueMap.add(getEntry(80, new BlockSet(MainInit.oreNew, 2), new BlockSet(MainInit.oreNew, 7), 5));
			blueMap.add(getEntry(40, new BlockSet(MainInit.layerNew, 0)));
			blueMap.add(getEntry(5, new BlockSet(Blocks.LAPIS_ORE, 0)));
			blueMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 7)));
			blue.put("entries", blueMap);
			INSTANCE.jsonMap.put(EnumVein.BLUE, blue);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.WHITE)) {
			Map<String, Object> white = new HashMap<String, Object>();
			white.put("layer", blockString(new BlockSet(MainInit.gemBlock, 6)));
			List<Map<String, Object>> whiteMap = Lists.newArrayList();
			whiteMap.add(getEntry(80, new BlockSet(MainInit.oreNew, 3), new BlockSet(MainInit.oreNew, 8), 5));
			whiteMap.add(getEntry(30, new BlockSet(MainInit.layerNew, 5)));
			whiteMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 8)));
			whiteMap.add(getEntry(10, new BlockSet(MainInit.gemBlock, 6)));
			whiteMap.add(getEntry(5, new BlockSet(MainInit.skarnOre, 8)));
			white.put("entries", whiteMap);
			INSTANCE.jsonMap.put(EnumVein.WHITE, white);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.BLACK)) {
			Map<String, Object> black = new HashMap<String, Object>();
			black.put("layer", blockString(new BlockSet(Blocks.STONE, 0)));
			List<Map<String, Object>> blackMap = Lists.newArrayList();
			blackMap.add(getEntry(80, new BlockSet(MainInit.oreNew, 4), new BlockSet(MainInit.oreNew, 9), 5));
			blackMap.add(getEntry(30, new BlockSet(MainInit.layerNew, 4)));
			blackMap.add(getEntry(20, new BlockSet(MainInit.oreNew, 9)));
			blackMap.add(getEntry(5, new BlockSet(Blocks.REDSTONE_ORE, 0)));
			blackMap.add(getEntry(5, new BlockSet(Blocks.COAL_ORE, 0)));
			black.put("entries", blackMap);
			INSTANCE.jsonMap.put(EnumVein.BLACK, black);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.GUANO)) {
			Map<String, Object> guano = new HashMap<String, Object>();
			guano.put("layer", blockString(new BlockSet(Blocks.GRAVEL, 0)));
			List<Map<String, Object>> guanoMap = Lists.newArrayList();
			guanoMap.add(getEntry(80, new BlockSet(MainInit.layerNew, 2), new BlockSet(Blocks.GRAVEL, 0), 30));
			guano.put("entries", guanoMap);
			INSTANCE.jsonMap.put(EnumVein.GUANO, guano);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.SKARN_IRON)) {
			Map<String, Object> skarn = new HashMap<String, Object>();
			skarn.put("layer", blockString(new BlockSet(MainInit.skarnBlock, 0)));
			List<Map<String, Object>> skarnMap = Lists.newArrayList();
			skarnMap.add(getEntry(20, new BlockSet(MainInit.oreNew, 1), new BlockSet(MainInit.oreNew, 6), 15));
			skarnMap.add(getEntry(30, new BlockSet(MainInit.oreNew, 2), new BlockSet(MainInit.oreNew, 7), 15));
			skarnMap.add(getEntry(20, new BlockSet(MainInit.oreNew, 4), new BlockSet(MainInit.oreNew, 9), 15));
			skarnMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 6)));
			skarnMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 7)));
			skarnMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 9)));
			skarn.put("entries", skarnMap);
			INSTANCE.jsonMap.put(EnumVein.SKARN_IRON, skarn);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.SKARN_COPPER)) {
			Map<String, Object> skarn_under = new HashMap<String, Object>();
			skarn_under.put("layer", blockString(new BlockSet(MainInit.skarnBlock, 2)));
			List<Map<String, Object>> skarn_underMap = Lists.newArrayList();
			skarn_underMap.add(getEntry(40, new BlockSet(MainInit.oreNew, 0), new BlockSet(MainInit.oreNew, 5), 15));
			skarn_underMap.add(getEntry(20, new BlockSet(MainInit.oreNew, 3), new BlockSet(MainInit.oreNew, 8), 15));
			skarn_underMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 5)));
			skarn_underMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 6)));
			skarn_underMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 8)));
			skarn_underMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 9)));
			skarn_under.put("entries", skarn_underMap);
			INSTANCE.jsonMap.put(EnumVein.SKARN_COPPER, skarn_under);
			ret = true;
		}

		if (!INSTANCE.jsonMap.containsKey(EnumVein.NETHER)) {
			Map<String, Object> nether = new HashMap<String, Object>();
			nether.put("layer", blockString(new BlockSet(MainInit.oreNew, 10)));
			List<Map<String, Object>> netherMap = Lists.newArrayList();
			netherMap.add(getEntry(40, new BlockSet(MainInit.oreNew, 10), new BlockSet(MainInit.oreNew, 11), 20));
			netherMap.add(getEntry(40, new BlockSet(MainInit.oreNew, 12), new BlockSet(MainInit.oreNew, 13), 20));
			netherMap.add(getEntry(20, new BlockSet(MainInit.oreNew, 14)));
			netherMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 11)));
			netherMap.add(getEntry(10, new BlockSet(MainInit.oreNew, 13)));
			nether.put("entries", netherMap);
			INSTANCE.jsonMap.put(EnumVein.NETHER, nether);
			ret = true;
		}

		return ret;
	}

	public static void setDir(File file) {
		parent = file.toPath().resolve("defeatedcrow\\climate\\veins\\");
	}

	/* Entry */

	public static String blockString(BlockSet b1) {
		if (b1 == null || b1 == OreSetDC.AIR) {
			return "empty";
		}
		String ore = b1.block.getRegistryName().toString() + ":" + b1.meta;
		return ore;
	}

	public static Map<String, Object> getEntry(int w, BlockSet b1) {
		Map<String, Object> ret = new TreeMap<String, Object>();
		ret.put("weight", w);
		ret.put("ore", blockString(b1));

		return ret;
	}

	public static Map<String, Object> getEntry(int w, BlockSet b1, BlockSet b2, int c) {
		Map<String, Object> ret = new TreeMap<String, Object>();
		ret.put("weight", w);
		ret.put("ore", blockString(b1));
		ret.put("secondary", blockString(b2));
		ret.put("secondary_chance", c);

		return ret;
	}

	public static final BlockSet STONE = new BlockSet(Blocks.STONE, 0);
}
