package defeatedcrow.hac.core.config;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.climate.register.ArmorItemRegister;
import defeatedcrow.hac.core.climate.register.ArmorMaterialRegister;
import defeatedcrow.hac.core.climate.register.BlockClimateRegister;
import defeatedcrow.hac.core.climate.register.MobResistanceRegister;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class CoreConfigDC {
	private CoreConfigDC() {}

	public static final CoreConfigDC INSTANCE = new CoreConfigDC();

	// debug
	public static String debugPass = "Input the password here";
	// private final String BR = System.getProperty("line.separator");
	public static EnumSeason debugForceSeason = null;

	public static String[] entityBlackList = new String[] { "minecraft:squid", "minecraft:bat", "minecraft:villager", "ModID:entityRegistryName" };
	public static final List<EntityType<?>> blackListEntity = Lists.newArrayList();

	public static String[] updateBlackList = new String[] { "minecraft:leaves", "minecraft:leaves2", "minecraft:tallgrass", "ModID:sampleBlock" };
	public static final List<Block> blackListBlock = Lists.newArrayList();

	public static void leadBlockNames() {
		blackListBlock.clear();
		blackListEntity.clear();
		blackListBlock.addAll(DCItemUtil.getBlockListFromStrings(updateBlackList, "Tick Update Invalid List"));
		blackListEntity.addAll(DCItemUtil.getEntityListFromStrings(entityBlackList, "Climate Damage Invalid List"));
	}

	public static void loadConfig() {
		BlockClimateRegister.initFile();
		ArmorItemRegister.initFile();
		ArmorMaterialRegister.initFile();
		MobResistanceRegister.initFile();
		VeinTableConfig.initFile();

		BlockClimateRegister.loadFiles();
		ArmorItemRegister.loadFiles();
		ArmorMaterialRegister.loadFiles();
		MobResistanceRegister.loadFiles();
		VeinTableConfig.loadFiles();
	}
}
