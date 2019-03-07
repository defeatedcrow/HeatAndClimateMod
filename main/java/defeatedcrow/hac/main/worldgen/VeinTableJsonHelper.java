package defeatedcrow.hac.main.worldgen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.VeinTable;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class VeinTableJsonHelper {

	private VeinTableJsonHelper() {}

	public static final VeinTableJsonHelper INSTANCE = new VeinTableJsonHelper();

	public void registerOre(VeinTable table, String name, int i) {
		if (name != null) {
			String itemName = name;
			String modid = "minecraft";
			int meta = 0;
			if (name.contains(":")) {
				String[] n2 = name.split(":");
				if (n2 != null && n2.length > 0) {
					if (n2.length == 1) {
						itemName = n2[0];
					} else {
						modid = n2[0];
						itemName = n2[1];
						if (n2.length > 2) {
							Integer m = null;
							try {
								m = Integer.parseInt(n2[2]);
							} catch (NumberFormatException e) {
								DCLogger.debugLog("Tried to parse non Integer target: " + n2[2]);
							}
							if (m != null && m >= 0) {
								meta = m;
							}
						}
					}

				} else {
					DCLogger.infoLog("fail to register target block from json: " + name);
					return;
				}
			}

			Block block = Block.REGISTRY.getObject(new ResourceLocation(modid, itemName));
			if (block != null && block != Blocks.AIR) {
				DCLogger.infoLog("register target oregen data from json: " + modid + ":" + itemName + ", " + meta + " ; weight" + i + " ; " + table.vein);
				table.addOreToTable(i, block, meta);
			} else {
				DCLogger.infoLog("Failed find target: " + modid + ":" + itemName);
			}
		}
	}

	/* json */
	private static final Map<String, Object> jsonMap = new HashMap<String, Object>();
	protected static final Map<String, Map<String, Integer>> oreSetMap = new HashMap<String, Map<String, Integer>>();

	private static File dir = null;

	public static void startMap() {
		if (!jsonMap.isEmpty()) {
			for (Entry<String, Object> ent : jsonMap.entrySet()) {
				if (ent != null) {
					String name = ent.getKey();
					Object value = ent.getValue();
					EnumVein type = EnumVein.getType(name);
					VeinTable table = VeinTableRegister.INSTANCE.getTable(type);

					if (table != null && value instanceof Map) {
						Map<String, Double> get = (Map<String, Double>) value;
						for (Entry<String, Double> ore : get.entrySet()) {
							if (ore != null && ore.getKey() != null && ore.getValue() != null) {
								int i1 = MathHelper.floor(ore.getValue());
								INSTANCE.registerOre(table, ore.getKey(), i1);
							}
						}
					}

				}
			}
		} else {
			DCLogger.infoLog("VainTableJson : no orevein custom data.");
		}
	}

	public static void pre() {
		if (dir != null) {
			try {
				if (!dir.exists() && !dir.createNewFile()) {
					return;
				}

				if (dir.canRead()) {
					FileInputStream fis = new FileInputStream(dir.getPath());
					InputStreamReader isr = new InputStreamReader(fis);
					JsonReader jsr = new JsonReader(isr);
					Gson gson = new Gson();
					Map get = gson.fromJson(jsr, Map.class);

					isr.close();
					fis.close();
					jsr.close();

					if (get != null && !get.isEmpty()) {
						jsonMap.putAll(get);

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		startMap();
	}

	// 生成は初回のみ
	public static void post() {

		if (dir != null) {
			try {
				if (!dir.exists() && !dir.createNewFile()) {
					return;
				} else if (!jsonMap.isEmpty()) {
					DCLogger.infoLog("VainTableJson : orevein custom data json is already exists.");
					return;
				}

				if (dir.canWrite()) {

					if (oreSetMap.isEmpty()) {
						Map<String, Integer> test = ImmutableMap.of("modid:sampleblock:0", 20);
						oreSetMap.put("skarn", test);
					}

					FileOutputStream fos = new FileOutputStream(dir.getPath());
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					JsonWriter jsw = new JsonWriter(osw);
					jsw.setIndent(" ");
					Gson gson = new Gson();
					gson.toJson(oreSetMap, Map.class, jsw);

					osw.close();
					fos.close();
					jsw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setDir(File file) {
		dir = new File(file, "defeatedcrow/climate/orevein.json");
		if (dir.getParentFile() != null) {
			dir.getParentFile().mkdirs();
		}
	}
}
