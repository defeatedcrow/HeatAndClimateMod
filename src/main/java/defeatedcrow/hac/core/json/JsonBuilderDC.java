package defeatedcrow.hac.core.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.material.block.IBlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class JsonBuilderDC {

	public static final JsonBuilderDC INSTANCE = new JsonBuilderDC();

	/**
	 * ItemのJSONモデル生成
	 */
	public static void BuildItemJsonModel(Item item) {
		// デバッグ環境でなければ実行しない
		if (!ClimateCore.isDebug || ClimateCore.assetsDir == null)
			return;

		// modelsフォルダに生成する
		File dir = new File(ClimateCore.assetsDir, "/models/item/");

		// 生成にIJsonDataDCインターフェイスを使うので、実装チェックをする。
		if (dir != null && item != null && item instanceof IJsonDataDC) {
			IJsonDataDC data = (IJsonDataDC) item;
			JsonModelSimpleDC model = data.getItemModel();

			File f = new File(dir, data.getRegistryName() + ".json");

			// すでにファイルが有る場合は何もしない。
			if (!f.exists()) {

				// ファイルを生成する。
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}

				try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// JSONファイルの生成。
				try {
					if (f.canWrite()) {
						// Streamは開けたら閉めるを徹底しよう。ご安全に!
						FileOutputStream fos = new FileOutputStream(f.getPath());
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						JsonWriter jsw = new JsonWriter(osw);

						// ここでインデントのスペースの数を調整でき、ファイルの内容が適宜改行されるようになる。
						jsw.setIndent("  ");
						Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
						gson.toJson(model, model.getClass(), jsw);

						osw.close();
						fos.close();
						jsw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				DCLogger.debugInfoLog("Generated item model: " + data.getRegistryName());
			}
		}
	}

	/**
	 * BlockのJSONモデル生成
	 */
	public static void BuildBlockJsonModel(Block block) {
		// デバッグ環境でなければ実行しない
		if (!ClimateCore.isDebug || ClimateCore.assetsDir == null)
			return;

		if (block != null && block instanceof IJsonDataDC) {
			IJsonDataDC data = (IJsonDataDC) block;

			/* DropLoot */
			File dir3 = new File(ClimateCore.dataDir, "/loot_tables/blocks/");

			// 生成にIJsonDataDCインターフェイスを使うので、実装チェックをする。
			if (dir3 != null && block instanceof IBlockDC) {
				IBlockDC blockDC = (IBlockDC) block;
				ItemStack drop = blockDC.getMainDrop();
				Optional<ResourceLocation> regName = DCUtil.getRes(drop.getItem());
				regName.ifPresent(res -> {
					String name = res.toString();

					File f = new File(dir3, data.getRegistryName() + ".json");

					// すでにファイルが有る場合は何もしない。
					if (!f.exists()) {
						// ファイルを生成する。
						if (!f.getParentFile().exists()) {
							f.getParentFile().mkdirs();
						}

						try {
							f.createNewFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						// JSONファイルの生成。
						try {
							if (f.canWrite()) {
								// Streamは開けたら閉めるを徹底しよう。ご安全に!
								FileOutputStream fos = new FileOutputStream(f.getPath());
								OutputStreamWriter osw = new OutputStreamWriter(fos);
								JsonWriter jsw = new JsonWriter(osw);

								// ここでインデントのスペースの数を調整でき、ファイルの内容が適宜改行されるようになる。
								jsw.setIndent("  ");
								Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

								if (data.getDropType() == IJsonDataDC.BlockType.NORMAL) {
									BlockLoot_Normal jsonObj = new BlockLoot_Normal(name);
									gson.toJson(jsonObj, BlockLoot_Normal.class, jsw);
								} else if (data.getDropType() == IJsonDataDC.BlockType.ENTITY_NBT) {
									BlockLoot_NBT jsonObj = new BlockLoot_NBT(name);
									gson.toJson(jsonObj, BlockLoot_NBT.class, jsw);
								}

								osw.close();
								fos.close();
								jsw.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				});
			}

			/* Block Model */
			// modelsフォルダに生成する
			File dir = new File(ClimateCore.assetsDir, "/models/block/");
			boolean flag = false;

			// 生成にIJsonDataDCインターフェイスを使うので、実装チェックをする。
			if (dir != null) {
				List<JsonModelDC> models = data.getBlockModel();
				Optional<String[]> suffix = data.getModelNameSuffix();

				int i = 0;
				for (JsonModelDC model : models) {
					String s = "";
					if (suffix.isPresent()) {
						if (suffix.get().length <= i) {
							i = suffix.get().length - 1;
						}
						s = "_" + suffix.get()[i];
					} else {
						s = "_" + i;
					}
					File f = new File(dir, data.getRegistryName() + s + ".json");
					i++;

					// すでにファイルが有る場合は何もしない。
					if (!f.exists()) {

						// ファイルを生成する。
						if (!f.getParentFile().exists()) {
							f.getParentFile().mkdirs();
						}

						try {
							f.createNewFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						// JSONファイルの生成。
						try {
							if (f.canWrite()) {
								// Streamは開けたら閉めるを徹底しよう。ご安全に!
								FileOutputStream fos = new FileOutputStream(f.getPath());
								OutputStreamWriter osw = new OutputStreamWriter(fos);
								JsonWriter jsw = new JsonWriter(osw);

								// ここでインデントのスペースの数を調整でき、ファイルの内容が適宜改行されるようになる。
								jsw.setIndent("  ");
								Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
								gson.toJson(model, model.getClass(), jsw);

								osw.close();
								fos.close();
								jsw.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}

						flag = true;
					}
				}
			}

			/* Item Model */
			// modelsフォルダに生成する
			File dir4 = new File(ClimateCore.assetsDir, "/models/item/");

			// 生成にIJsonDataDCインターフェイスを使うので、実装チェックをする。
			if (dir4 != null) {
				JsonModelSimpleDC model = data.getItemModel();

				File f = new File(dir4, data.getRegistryName() + ".json");

				// すでにファイルが有る場合は何もしない。
				if (!f.exists()) {

					// ファイルを生成する。
					if (!f.getParentFile().exists()) {
						f.getParentFile().mkdirs();
					}

					try {
						f.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// JSONファイルの生成。
					try {
						if (f.canWrite()) {
							// Streamは開けたら閉めるを徹底しよう。ご安全に!
							FileOutputStream fos = new FileOutputStream(f.getPath());
							OutputStreamWriter osw = new OutputStreamWriter(fos);
							JsonWriter jsw = new JsonWriter(osw);

							// ここでインデントのスペースの数を調整でき、ファイルの内容が適宜改行されるようになる。
							jsw.setIndent("  ");
							Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
							gson.toJson(model, model.getClass(), jsw);

							osw.close();
							fos.close();
							jsw.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

					flag = true;
				}
			}

			/* BlockState */
			// blockstateフォルダに生成する
			File dir2 = new File(ClimateCore.assetsDir, "/blockstates/");

			// 生成にIJsonDataDCインターフェイスを使うので、実装チェックをする。
			if (dir2 != null && data.requireStateJson()) {
				JsonBlockStateDC.Build build = new JsonBlockStateDC.Build(block, data);

				File f = new File(dir2, data.getRegistryName() + ".json");

				// すでにファイルが有る場合は何もしない。
				if (!f.exists()) {

					// ファイルを生成する。
					if (!f.getParentFile().exists()) {
						f.getParentFile().mkdirs();
					}

					try {
						f.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// JSONファイルの生成。
					try {
						if (f.canWrite()) {
							// Streamは開けたら閉めるを徹底しよう。ご安全に!
							FileOutputStream fos = new FileOutputStream(f.getPath());
							OutputStreamWriter osw = new OutputStreamWriter(fos);
							JsonWriter jsw = new JsonWriter(osw);

							// ここでインデントのスペースの数を調整でき、ファイルの内容が適宜改行されるようになる。
							jsw.setIndent("  ");
							Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
							gson.toJson(build, build.getClass(), jsw);

							osw.close();
							fos.close();
							jsw.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

					flag = true;
				}
			}

			if (flag)
				DCLogger.debugInfoLog("Generated block model: " + ((IJsonDataDC) block).getRegistryName());

		}
	}

	private static class BlockLoot_Normal {
		final String type = "minecraft:block";
		final List<Pool> pools = Lists.newArrayList();

		private BlockLoot_Normal(String name) {
			pools.add(new Pool(name));
		}
	}

	private static class BlockLoot_NBT {
		final String type = "minecraft:block";
		final List<Pool> pools = Lists.newArrayList();

		private BlockLoot_NBT(String name) {
			pools.add(new Pool(name));
		}
	}

	private static class Pool {
		final List<Map<String, String>> conditions = ImmutableList.of(ImmutableMap.of("condition", "minecraft:survives_explosion"));
		final List<Map<String, String>> entries = Lists.newArrayList();
		final int rolls = 1;

		private Pool(String item) {
			Map<String, String> map = new HashMap<>();
			map.put("type", "minecraft:item");
			map.put("name", item);
			entries.add(map);
		}
	}

	private static class Pool_NBT {
		final List<Map<String, String>> conditions = ImmutableList.of(ImmutableMap.of("condition", "minecraft:survives_explosion"));
		final List<Map<String, Object>> entries = Lists.newArrayList();
		final int rolls = 1;

		private Pool_NBT(String item) {
			Map<String, Object> map = new HashMap<>();
			map.put("functions", ImmutableList.of(ImmutableMap.of("function", "dcs_climate:nbt_tile")));
			map.put("type", "minecraft:item");
			map.put("name", item);
			entries.add(map);
		}
	}

}
