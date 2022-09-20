package defeatedcrow.hac.core.climate.register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IHeatBlockRegister;
import defeatedcrow.hac.api.util.BlockSet;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.JsonFileFilter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockClimateRegister implements IHeatBlockRegister {

	private Map<BlockSet, DCHeatTier> heats;
	private Map<BlockSet, DCHumidity> hums;
	private Map<BlockSet, DCAirflow> airs;

	public BlockClimateRegister() {
		this.heats = new HashMap<BlockSet, DCHeatTier>();
		this.hums = new HashMap<BlockSet, DCHumidity>();
		this.airs = new HashMap<BlockSet, DCAirflow>();
		init();
	}

	private void init() {
		registerHeatBlock(new BlockSet(Blocks.CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.BLACK_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.BLUE_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.BROWN_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.CYAN_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.GRAY_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.GREEN_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.LIGHT_BLUE_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.LIGHT_GRAY_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.LIME_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.MAGENTA_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.ORANGE_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.PINK_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.PURPLE_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.RED_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.WHITE_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.YELLOW_CANDLE, "lit", ImmutableList.of("true")), DCHeatTier.WARM);
		registerHeatBlock(new BlockSet(Blocks.CAMPFIRE, "lit", ImmutableList.of("true")), DCHeatTier.BOIL);
		registerHeatBlock(new BlockSet(Blocks.FURNACE, "lit", ImmutableList.of("true")), DCHeatTier.OVEN);
		registerHeatBlock(new BlockSet(Blocks.SMOKER, "lit", ImmutableList.of("true")), DCHeatTier.OVEN);
		registerHeatBlock(new BlockSet(Blocks.BLAST_FURNACE, "lit", ImmutableList.of("true")), DCHeatTier.KILN);
	}

	@Override
	public void registerHeatBlock(BlockSet block, DCHeatTier temp) {
		if (block != null) {
			heats.put(block, temp);
			// registerEffectiveHeat(block,temp);
		}
	}

	@Override
	public void registerHumBlock(BlockSet block, DCHumidity hum) {
		if (block != null) {
			hums.put(block, hum);
			// registerEffectiveHum(block, hum);
		}
	}

	@Override
	public void registerAirBlock(BlockSet block, DCAirflow air) {
		if (block != null) {
			airs.put(block, air);
			// registerEffectiveAir(block, air);
		}
	}

	@Override
	public Optional<DCHeatTier> getHeatTier(BlockState block) {
		Set<BlockSet> s = heats.keySet();
		DCHeatTier heat = null;
		BlockSet b = this.include(s, block);
		if (b != null) {
			return Optional.ofNullable(heats.get(b));
		}
		return BlockClimateData.getData(block.getBlock()).map(ret -> ret.getHeat());

	}

	@Override
	public Optional<DCHumidity> getHumidity(BlockState block) {
		Set<BlockSet> s = hums.keySet();
		DCHumidity hum = null;
		BlockSet b = this.include(s, block);
		if (b != null) {
			return Optional.ofNullable(hums.get(b));
		}
		return BlockClimateData.getData(block.getBlock()).map(ret -> ret.getHumidity());
	}

	@Override
	public Optional<DCAirflow> getAirflow(BlockState block) {
		Set<BlockSet> s = airs.keySet();
		DCAirflow air = null;
		BlockSet b = this.include(s, block);
		if (b != null) {
			return Optional.ofNullable(airs.get(b));
		}
		return BlockClimateData.getData(block.getBlock()).map(ret -> ret.getAirflow());
	}

	@Override
	public boolean isRegisteredHeat(BlockState block) {
		Set<BlockSet> s = heats.keySet();
		return this.include(s, block) != null || BlockClimateData.getData(block.getBlock()).isPresent();
	}

	@Override
	public boolean isRegisteredHum(BlockState block) {
		Set<BlockSet> s = hums.keySet();
		return this.include(s, block) != null || BlockClimateData.getData(block.getBlock()).isPresent();
	}

	@Override
	public boolean isRegisteredAir(BlockState block) {
		Set<BlockSet> s = airs.keySet();
		return this.include(s, block) != null || BlockClimateData.getData(block.getBlock()).isPresent();
	}

	@Override
	public Map<BlockSet, DCHeatTier> getHeatList() {
		return heats;
	}

	@Override
	public Map<BlockSet, DCHumidity> getHumList() {
		return hums;
	}

	@Override
	public Map<BlockSet, DCAirflow> getAirList() {
		return airs;
	}

	private BlockSet include(Set<BlockSet> list, BlockState target) {
		BlockSet ret = null;
		for (BlockSet b : list) {
			if (b.match(target)) {
				ret = b;
			}
		}
		return ret;
	}

	// private void registerEffectiveHeat(BlockSet block, int meta, DCHeatTier t) {
	// if (DCsJEIPluginLists.climate.isEmpty()) {
	// DCsJEIPluginLists.climate.add(new ClimateEffectiveTile(block, meta, t, null, null));
	// } else {
	// boolean flag = false;
	// for (ClimateEffectiveTile tile : DCsJEIPluginLists.climate) {
	// if (tile.isSameBlock(block) && (tile.getInputMeta() == OreDictionary.WILDCARD_VALUE || tile
	// .getInputMeta() == meta)) {
	// tile.setHeat(t);
	// flag = true;
	// break;
	// }
	// }
	// if (!flag) {
	// DCsJEIPluginLists.climate.add(new ClimateEffectiveTile(block, meta, t, null, null));
	// }
	// }
	// }
	//
	// private void registerEffectiveHum(BlockSet block, int meta, DCHumidity h) {
	// if (DCsJEIPluginLists.climate.isEmpty()) {
	// DCsJEIPluginLists.climate.add(new ClimateEffectiveTile(block, meta, null, h, null));
	// } else {
	// boolean flag = false;
	// for (ClimateEffectiveTile tile : DCsJEIPluginLists.climate) {
	// if (tile.isSameBlock(block) && (tile.getInputMeta() == OreDictionary.WILDCARD_VALUE || tile
	// .getInputMeta() == meta)) {
	// tile.setHumidity(h);
	// flag = true;
	// break;
	// }
	// }
	// if (!flag) {
	// DCsJEIPluginLists.climate.add(new ClimateEffectiveTile(block, meta, null, h, null));
	// }
	// }
	// }
	//
	// private void registerEffectiveAir(BlockSet block, int meta, DCAirflow a) {
	// if (DCsJEIPluginLists.climate.isEmpty()) {
	// DCsJEIPluginLists.climate.add(new ClimateEffectiveTile(block, meta, null, null, a));
	// } else {
	// boolean flag = false;
	// for (ClimateEffectiveTile tile : DCsJEIPluginLists.climate) {
	// if (tile.isSameBlock(block) && (tile.getInputMeta() == OreDictionary.WILDCARD_VALUE || tile
	// .getInputMeta() == meta)) {
	// tile.setAirflow(a);
	// flag = true;
	// break;
	// }
	// }
	// if (!flag) {
	// DCsJEIPluginLists.climate.add(new ClimateEffectiveTile(block, meta, null, null, a));
	// }
	// }
	// }

	/* json */
	public static void registerBlockClimate(ParamBlock param) {
		if (param != null && param.blockName != null) {
			ResourceLocation res = new ResourceLocation(param.blockName);
			Block b = ForgeRegistries.BLOCKS.getValue(res);
			if (b != null && b != Blocks.AIR) {
				BlockSet set = new BlockSet(b, param.property, param.values);

				DCLogger.infoLog("register target block climate from json: " + b.toString());
				if (param.heat != DCHeatTier.NORMAL) {
					ClimateAPI.registerBlock.registerHeatBlock(set, param.heat);
					DCLogger.infoLog("* HeatTier: " + param.heat);
				}
				if (param.humidity != DCHumidity.NORMAL) {
					ClimateAPI.registerBlock.registerHumBlock(set, param.humidity);
					DCLogger.infoLog("* Humidity: " + param.humidity);
				}
				if (param.airflow != DCAirflow.TIGHT) {
					ClimateAPI.registerBlock.registerAirBlock(set, param.airflow);
					DCLogger.infoLog("* Airflow: " + param.airflow);
				}
			} else {
				DCLogger.warnLog("fail to register target block from json: " + param.blockName);
				return;
			}
		}
	}

	private static File dir = null;

	public static void pre() {
		if (dir != null) {
			try {
				File parent = dir.getParentFile();
				if (!parent.exists() && !parent.createNewFile() && !parent.isDirectory()) {
					return;
				}

				File[] files = parent.listFiles(new JsonFileFilter());
				for (File file : files) {
					if (file.canRead()) {
						FileInputStream fis = new FileInputStream(file.getPath());
						InputStreamReader isr = new InputStreamReader(fis);
						JsonReader jsr = new JsonReader(isr);
						Gson gson = new Gson();
						ParamBlock get = gson.fromJson(jsr, ParamBlock.class);

						isr.close();
						fis.close();
						jsr.close();

						if (get != null) {
							registerBlockClimate(get);
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 生成は初回のみ
	public static void post() {

		if (dir != null) {
			try {
				if (!dir.exists() && !dir.createNewFile()) {
					return;
				}

				ParamBlock ret = new ParamBlock("sampleModID:sampleBlockName", "PropertyName",
						ImmutableList.of("sampleValue"), DCHeatTier.HOT, DCHumidity.NORMAL, DCAirflow.TIGHT);

				if (dir.canWrite()) {
					FileOutputStream fos = new FileOutputStream(dir.getPath());
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					JsonWriter jsw = new JsonWriter(osw);
					jsw.setIndent(" ");
					Gson gson = new Gson();
					gson.toJson(ret, ParamBlock.class, jsw);

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
		dir = new File(file, "blockdata/block_climate_sample.json");
		if (dir.getParentFile() != null) {
			dir.getParentFile().mkdirs();
		}
	}

}
