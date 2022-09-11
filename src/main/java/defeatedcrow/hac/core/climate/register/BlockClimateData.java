package defeatedcrow.hac.core.climate.register;

import java.util.Optional;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public enum BlockClimateData {
	LIT_PUMPKIN(Blocks.JACK_O_LANTERN, DCHeatTier.WARM, DCHumidity.NORMAL, DCAirflow.TIGHT),
	LANTERN(Blocks.LANTERN, DCHeatTier.WARM, DCHumidity.NORMAL, DCAirflow.TIGHT),
	TORCH(Blocks.TORCH, DCHeatTier.HOT, DCHumidity.NORMAL, DCAirflow.TIGHT),
	WALL_TORCH(Blocks.WALL_TORCH, DCHeatTier.HOT, DCHumidity.NORMAL, DCAirflow.TIGHT),
	MAGMA_BLOCK(Blocks.MAGMA_BLOCK, DCHeatTier.OVEN, DCHumidity.NORMAL, DCAirflow.TIGHT),
	FIRE(Blocks.FIRE, DCHeatTier.KILN, DCHumidity.NORMAL, DCAirflow.TIGHT),
	LAVA(Blocks.LAVA, DCHeatTier.KILN, DCHumidity.NORMAL, DCAirflow.TIGHT),
	LAVA_CAULDRON(Blocks.LAVA_CAULDRON, DCHeatTier.KILN, DCHumidity.NORMAL, DCAirflow.TIGHT),
	END_LOD(Blocks.END_ROD, DCHeatTier.COOL, DCHumidity.NORMAL, DCAirflow.TIGHT),
	SNOW_BLOCK(Blocks.SNOW_BLOCK, DCHeatTier.COOL, DCHumidity.NORMAL, DCAirflow.TIGHT),
	SNOW(Blocks.SNOW, DCHeatTier.COOL, DCHumidity.NORMAL, DCAirflow.TIGHT),
	POWDER_SNOW(Blocks.POWDER_SNOW, DCHeatTier.COOL, DCHumidity.NORMAL, DCAirflow.TIGHT),
	POWDER_SNOW_CAULDRON(Blocks.POWDER_SNOW_CAULDRON, DCHeatTier.COOL, DCHumidity.NORMAL, DCAirflow.TIGHT),
	ICE(Blocks.ICE, DCHeatTier.COLD, DCHumidity.NORMAL, DCAirflow.TIGHT),
	BLUE_ICE(Blocks.BLUE_ICE, DCHeatTier.COLD, DCHumidity.NORMAL, DCAirflow.TIGHT),
	FROSTED_ICE(Blocks.FROSTED_ICE, DCHeatTier.COLD, DCHumidity.NORMAL, DCAirflow.TIGHT),
	PACKED_ICE(Blocks.PACKED_ICE, DCHeatTier.COLD, DCHumidity.NORMAL, DCAirflow.TIGHT),
	SPONGE(Blocks.SPONGE, DCHeatTier.NORMAL, DCHumidity.DRY, DCAirflow.TIGHT),
	SOUL_SAND(Blocks.SOUL_SAND, DCHeatTier.NORMAL, DCHumidity.DRY, DCAirflow.TIGHT),
	WET_SPONGE(Blocks.WET_SPONGE, DCHeatTier.NORMAL, DCHumidity.WET, DCAirflow.TIGHT),
	SLIME_BLOCK(Blocks.SLIME_BLOCK, DCHeatTier.NORMAL, DCHumidity.WET, DCAirflow.TIGHT),
	WATER(Blocks.WATER, DCHeatTier.NORMAL, DCHumidity.UNDERWATER, DCAirflow.TIGHT),
	WATER_CAULDRON(Blocks.WATER_CAULDRON, DCHeatTier.NORMAL, DCHumidity.UNDERWATER, DCAirflow.TIGHT);

	private final Block block;
	private final DCHeatTier heat;
	private final DCHumidity hum;
	private final DCAirflow air;

	private BlockClimateData(Block b, DCHeatTier t, DCHumidity h, DCAirflow a) {
		block = b;
		heat = t;
		hum = h;
		air = a;
	}

	public static Optional<BlockClimateData> getData(Block blockIn) {
		for (BlockClimateData data : BlockClimateData.values()) {
			if (data.block.equals(blockIn)) {
				return Optional.of(data);
			}
		}
		return Optional.empty();
	}

	public DCHeatTier getHeat() {
		return heat;
	}

	public DCHumidity getHumidity() {
		return hum;
	}

	public DCAirflow getAirflow() {
		return air;
	}
}
