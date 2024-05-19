package defeatedcrow.hac.core.climate.register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.api.damage.IArmorMaterialRegister;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.JsonFileFilter;
import defeatedcrow.hac.core.util.MaterialsDC;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;

public class ArmorMaterialRegister implements IArmorMaterialRegister {

	public static List<ParamMaterial> regList;

	public ArmorMaterialRegister() {
		regList = Lists.newArrayList();
		init();
	}

	private void init() {
		registerMaterial(ArmorMaterials.LEATHER, 2.0F, 2.0F);
		registerMaterial(ArmorMaterials.DIAMOND, 1.0F, 1.0F);
		registerMaterial(ArmorMaterials.TURTLE, 1.0F, 1.0F);
		registerMaterial(ArmorMaterials.NETHERITE, 3.0F, 0.0F);
		registerMaterial(MaterialsDC.LINEN, 2.0F, 1.0F);
		registerMaterial(MaterialsDC.CLOTH, 2.0F, 2.0F);
		registerMaterial(MaterialsDC.WOOL, 0.0F, 3.0F);
		registerMaterial(MaterialsDC.SILK, 3.0F, 0.0F);
		registerMaterial(MaterialsDC.RUBBER, 1.0F, 1.0F);
	}

	@Override
	public void registerMaterial(ArmorMaterial material, float heat, float cold) {
		if (material == null)
			return;
		ParamMaterial reg = new ParamMaterial(material, heat, cold);
		if (!regList.contains(reg)) {
			regList.add(reg);
			DCLogger.infoLog("Registered armor material: " + material + "... heat " + heat + " / cold " + cold);
		}
	}

	@Override
	public float getHeatPreventAmount(ArmorMaterial material) {
		Optional<ParamMaterial> ret = regList.stream().filter((p) -> p.materialName.equals(material.getName())).findAny();
		return ret.map(p -> p.heatResistance).orElse(0.25F);
	}

	@Override
	public float getColdPreventAmount(ArmorMaterial material) {
		Optional<ParamMaterial> ret = regList.stream().filter((p) -> p.materialName.equals(material.getName())).findAny();
		return ret.map(p -> p.coldResistance).orElse(0.25F);
	}

	public static void loadFiles() {
		if (ClimateCore.configDir != null) {

			File dir = new File(ClimateCore.configDir, "/material_resistance/");
			if (dir.getParentFile() != null) {
				dir.getParentFile().mkdirs();
			}

			try {
				if (!dir.exists() && !dir.mkdirs() && !dir.isDirectory()) {
					return;
				}

				File[] files = dir.listFiles(new JsonFileFilter());
				if (files != null)
					for (File file : files) {
						if (file.canRead()) {
							FileInputStream fis = new FileInputStream(file.getPath());
							InputStreamReader isr = new InputStreamReader(fis);
							JsonReader jsr = new JsonReader(isr);
							Gson gson = new Gson();
							ParamMaterial get = gson.fromJson(jsr, ParamMaterial.class);

							isr.close();
							fis.close();
							jsr.close();

							if (get != null) {
								regList.add(get);
							}
						}
					}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 生成は初回のみ
	public static void initFile() {
		if (ClimateCore.configDir != null) {

			File dir = new File(ClimateCore.configDir, "/material_resistance/sample.json");
			if (dir.getParentFile() != null) {
				dir.getParentFile().mkdirs();
			}

			try {
				if (!dir.exists() && !dir.createNewFile()) {
					return;
				}

				ParamMaterial ret = new ParamMaterial(ArmorMaterials.LEATHER, 2.0F, 2.0F);

				if (dir.canWrite()) {
					FileOutputStream fos = new FileOutputStream(dir.getPath());
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					JsonWriter jsw = new JsonWriter(osw);
					jsw.setIndent(" ");
					Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
					gson.toJson(ret, ParamMaterial.class, jsw);

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
