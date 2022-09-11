package defeatedcrow.hac.core.climate.register;

import java.util.List;
import java.util.Optional;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.util.BlockSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class ParamBlock {
	public final String blockName;
	public final String property;
	public final List<String> values;
	public final DCHeatTier heat;
	public final DCHumidity humidity;
	public final DCAirflow airflow;

	public ParamBlock(String key, String prop, List<String> val, DCHeatTier tier, DCHumidity hum, DCAirflow air) {
		blockName = key;
		property = prop;
		values = val;
		heat = tier;
		humidity = hum;
		airflow = air;
	}

	public Optional<BlockSet> getBlock() {
		Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));
		if (block != null) {
			return Optional.of(new BlockSet(block, property, values));
		}
		return Optional.empty();
	}
}
