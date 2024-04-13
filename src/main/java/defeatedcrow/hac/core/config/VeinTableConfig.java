package defeatedcrow.hac.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.JsonFileFilter;
import defeatedcrow.hac.core.worldgen.vein.VeinTable;
import defeatedcrow.hac.core.worldgen.vein.VeinTableRegister;

public class VeinTableConfig {

	public static void initFile() {

		// 生成は最初のみ
		if (ClimateCore.configDir == null)
			return;

		VeinTableRegister.initRegistry();

		// configフォルダに生成する
		File dir = new File(ClimateCore.configDir, "/ore_deposits/");

		for (VeinTable table : VeinTableRegister.INSTANCE.list) {
			File f = new File(dir, table.getName().toLowerCase() + ".json");

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
						gson.toJson(table, VeinTable.class, jsw);

						osw.close();
						fos.close();
						jsw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void loadFiles() {

		if (ClimateCore.configDir == null)
			return;

		File dir = new File(ClimateCore.configDir, "/ore_deposits/");
		File[] files = dir.listFiles(new JsonFileFilter());

		if (dir != null && files != null) {
			try {
				for (File file : files) {
					if (file.canRead()) {
						FileInputStream fis = new FileInputStream(file.getPath());
						InputStreamReader isr = new InputStreamReader(fis);
						JsonReader jsr = new JsonReader(isr);
						Gson gson = new Gson();
						VeinTable get = gson.fromJson(jsr, VeinTable.class);

						isr.close();
						fis.close();
						jsr.close();

						if (get != null) {
							VeinTableRegister.INSTANCE.list.add(get);
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
