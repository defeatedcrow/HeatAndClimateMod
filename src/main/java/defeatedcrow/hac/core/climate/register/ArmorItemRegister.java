package defeatedcrow.hac.core.climate.register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.api.damage.IArmorItemRegister;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.core.util.JsonFileFilter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ArmorItemRegister implements IArmorItemRegister {

	public static List<ParamArmorItem> regList;

	public ArmorItemRegister() {
		regList = Lists.newArrayList();
		init();
	}

	private void init() {
		registerArmorItem(Items.LEATHER_HORSE_ARMOR, 2.0F, 2.0F);
		registerArmorItem(Items.DIAMOND_HORSE_ARMOR, 2.0F, 2.0F);
	}

	@Override
	public void registerArmorItem(Item item, float heat, float cold) {
		if (item == null)
			return;
		Optional<ResourceLocation> res = DCUtil.getRes(item);
		if (res.isPresent()) {
			ParamArmorItem reg = new ParamArmorItem(res.toString(), heat, cold);
			if (!regList.contains(reg)) {
				regList.add(reg);
				DCLogger.infoLog("Registered armor item: " + res.toString() + "... heat " + heat + " / cold " + cold);
			}
		}
	}

	@Override
	public float getHeatPreventAmount(ItemStack item) {
		if (item.isEmpty())
			return 0.0F;
		Optional<ResourceLocation> res = DCUtil.getRes(item.getItem());
		Optional<ParamArmorItem> ret = regList.stream().filter((p) -> p.itemName.equals((res.orElse(DCUtil.DUMMY)).toString())).findAny();
		return ret.map(p -> p.heatResistance).orElse(0.0F);
	}

	@Override
	public float getColdPreventAmount(ItemStack item) {
		if (item.isEmpty())
			return 0.0F;
		Optional<ResourceLocation> res = DCUtil.getRes(item.getItem());
		Optional<ParamArmorItem> ret = regList.stream().filter((p) -> p.itemName.equals((res.orElse(DCUtil.DUMMY)).toString())).findAny();
		return ret.map(p -> p.coldResistance).orElse(0.0F);
	}

	public static void loadFiles() {
		if (ClimateCore.configDir != null) {

			File dir = new File(ClimateCore.configDir, "/armor_resistance/");
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
							ParamArmorItem get = gson.fromJson(jsr, ParamArmorItem.class);

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

			File dir = new File(ClimateCore.configDir, "/armor_resistance/sample.json");
			if (dir.getParentFile() != null) {
				dir.getParentFile().mkdirs();
			}

			try {
				if (!dir.exists() && !dir.createNewFile()) {
					return;
				}

				ParamArmorItem ret = new ParamArmorItem("sample_mod_id:sample_armor_name", 2.0F, 2.0F);

				if (dir.canWrite()) {
					FileOutputStream fos = new FileOutputStream(dir.getPath());
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					JsonWriter jsw = new JsonWriter(osw);
					jsw.setIndent(" ");
					Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
					gson.toJson(ret, ParamArmorItem.class, jsw);

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
