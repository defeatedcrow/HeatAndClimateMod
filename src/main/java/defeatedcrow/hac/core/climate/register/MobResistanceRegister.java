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

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.IMobHeatResistance;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.JsonFileFilter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

public class MobResistanceRegister implements IMobHeatResistance {

	public static List<ParamEntity> regList;

	public MobResistanceRegister() {
		regList = Lists.newArrayList();
		init();
	}

	private void init() {
		registerEntityResistance(EntityType.DOLPHIN, 2.0F, 2.0F);
	}

	@Override
	public float getHeatResistance(ResourceLocation name, DCHeatTier temp) {
		if (name != null) {
			Optional<ParamEntity> ret = regList.stream().filter((p) -> p.entityName.equals(name.toString())).findAny();
			float f = 2.0F;
			if (ret.isPresent()) {
				f = temp.isCold() ? ret.map(p -> p.coldResistance).orElse(2.0F) : ret.map(p -> p.heatResistance).orElse(2.0F);
			}
			EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(name);
			Optional<MobClimateData> data = MobClimateData.getData(type);
			return temp.isCold() ? data.map(d -> d.getColdResistance()).orElse(f) : data.map(d -> d.getHeatResistance()).orElse(f);
		}
		return 2.0F;
	}

	@Override
	public float getHeatResistance(Entity entity, DCHeatTier temp) {
		if (entity != null) {
			Optional<ParamEntity> ret = regList.stream().filter((p) -> (p.getEntityType().orElse(EntityType.PLAYER).tryCast(entity) != null)).findAny();
			float f = 2.0F;
			if (ret.isPresent()) {
				f = temp.isCold() ? ret.map(p -> p.coldResistance).orElse(2.0F) : ret.map(p -> p.heatResistance).orElse(2.0F);
			}
			Optional<MobClimateData> data = MobClimateData.getData(entity);
			return temp.isCold() ? data.map(d -> d.getColdResistance()).orElse(f) : data.map(d -> d.getHeatResistance()).orElse(f);
		}
		return 2.0F;
	}

	@Override
	public void registerEntityResistance(ResourceLocation name, float heatResistance, float coldResistance) {
		if (name == null)
			return;
		ParamEntity reg = new ParamEntity(name.toString(), heatResistance, coldResistance);
		if (!regList.contains(reg)) {
			regList.add(reg);
			DCLogger.infoLog("Registered entity: " + name.toString() + "... heat " + heatResistance + " / cold " + coldResistance);
		}
	}

	@Override
	public void registerEntityResistance(EntityType<?> type, float heatResistance, float coldResistance) {
		if (type == null)
			return;
		ResourceLocation name = ForgeRegistries.ENTITY_TYPES.getKey(type);
		registerEntityResistance(name, heatResistance, coldResistance);
	}

	public static void loadFiles() {
		if (ClimateCore.configDir != null) {

			File dir = new File(ClimateCore.configDir, "/entity_resistance/");
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
							ParamEntity get = gson.fromJson(jsr, ParamEntity.class);

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

			File dir = new File(ClimateCore.configDir, "/entity_resistance/sample.json");
			if (dir.getParentFile() != null) {
				dir.getParentFile().mkdirs();
			}

			try {
				if (!dir.exists() && !dir.createNewFile()) {
					return;
				}

				ParamEntity ret = new ParamEntity("sample_mod_id:sample_entity_type", 2.0F, 2.0F);

				if (dir.canWrite()) {
					FileOutputStream fos = new FileOutputStream(dir.getPath());
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					JsonWriter jsw = new JsonWriter(osw);
					jsw.setIndent(" ");
					Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
					gson.toJson(ret, ParamEntity.class, jsw);

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