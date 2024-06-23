package defeatedcrow.hac.machine.material.block.monitor;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ServerLevelData;

public class StormglassTile extends BlockEntity implements IIntReceiver {

	public StormglassTile(BlockPos pos, BlockState state) {
		super(MachineInit.STORMGLASS_TILE.get(), pos, state);
	}

	public int amount = 0;
	private int last = 0;

	public int getCurrentAmount() {
		return amount;
	}

	@Override
	public void receiveInteger(int i) {
		amount = i;
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, StormglassTile tile) {
		tile.onTickProcess(level, pos, state);
	}

	private boolean next = false;

	int count = 20;

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 20;
			amount = 0;

			if (level instanceof ServerLevel server) {
				LevelData data = level.getLevelData();
				if (data instanceof ServerLevelData sData) {
					int clear = sData.getClearWeatherTime();
					int thunder = sData.getThunderTime();
					int rain = sData.getRainTime();
					boolean bt = (server.getThunderLevel(1.0F) > 0.01F && thunder > 1200) || (server.getThunderLevel(1.0F) <= 0.01F && thunder < 1200 && rain < 1200);
					boolean br = (server.getRainLevel(1.0F) > 0.01 && rain > 1200) || (server.getRainLevel(1.0F) <= 0.01F && rain < 1200);
					DCHeatTier temp = ClimateAPI.registerBiome.getHeatTier(level, pos);
					if (temp == null) {
						temp = DCHeatTier.NORMAL;
					}

					if (temp.getTier() < DCHeatTier.COOL.getTier()) {
						amount += 0b100000; // cold or warm
					}

					if (clear < 1200)
						if (bt || br) {
							amount += 0b001000; // rainy
							if (temp.getTier() < DCHeatTier.COOL.getTier()) {
								amount += 0b000010; // snow
							}
							if (bt) {
								amount += 0b000001; // storm
							}
						} else {
							amount += 0b000100; // bottom
							if (temp.getTier() < DCHeatTier.WARM.getTier()) {
								amount += 0b010000; // sun
							}
						}

				}
			}

			if (last != amount) {
				last = amount;
				if (level instanceof ServerLevel)
					MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, amount);
			}
		}
		return false;
	}

}
