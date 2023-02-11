package defeatedcrow.hac.core.recipe.smelting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ClimateSmelting implements IClimateSmelting {

	private final Object input;
	private ArrayList<ItemStack> processedInput;
	private final ItemStack output;

	private final int frequency;
	private List<DCHeatTier> heat = new ArrayList<DCHeatTier>();
	private List<DCHumidity> hum = new ArrayList<DCHumidity>();
	private List<DCAirflow> air = new ArrayList<DCAirflow>();

	private final boolean active;

	public ClimateSmelting(boolean act, ItemStack o, DCHeatTier t, DCHumidity h, DCAirflow a, int freq,
			Object i) {
		active = act;
		input = i;
		output = o;
		if (t != null) {
			heat.add(t);
			if (t.getID() < DCHeatTier.INFERNO.getID()) {
				if (t.getID() == DCHeatTier.NORMAL.getID() || t.getID() == DCHeatTier.WARM.getID()) {
					heat.add(t.addTier(1));
					heat.add(t.addTier(-1));
				} else if (t.getID() > DCHeatTier.ABSOLUTE.getID() && t.getID() < DCHeatTier.NORMAL.getID()) {
					heat.add(t.addTier(-1));
				} else if (t.getID() > DCHeatTier.WARM.getID()) {
					heat.add(t.addTier(1));
				}
			}
		}
		if (h != null)
			hum.add(h);
		if (a != null)
			air.add(a);
		processedInput = DCItemUtil.getProcessedList(input);
		frequency = freq;
	}

	public ClimateSmelting(boolean act, ItemStack o, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, int freq,
			Object i) {
		active = act;
		input = i;
		output = o;
		if (t != null) {
			heat.addAll(t);
		}
		if (h != null)
			hum.addAll(h);
		if (a != null)
			air.addAll(a);
		processedInput = DCItemUtil.getProcessedList(input);
		frequency = freq;
	}

	@Override
	public Object getInput() {
		return input;
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
	}

	@Override
	public List<ItemStack> getProcessedInput() {
		return processedInput;
	}

	@Override
	public boolean matcheInput(ItemStack item) {
		ArrayList<ItemStack> required = new ArrayList<>(DCItemUtil.getProcessedList(input));
		if (!item.isEmpty() && !required.isEmpty()) {
			Iterator<ItemStack> itr = required.iterator();
			boolean match = false;
			while (itr.hasNext() && !match) {
				match = DCItemUtil.isSameItem(itr.next(), item, match);
			}
			itr = null;
			return match;
		}
		return false;
	}

	@Override
	public boolean matchClimate(int code) {
		IClimate clm = ClimateAPI.helper.getClimateFromInt(code);
		return matchClimate(clm);
	}

	@Override
	public boolean matchClimate(IClimate climate) {
		boolean t = requiredHeat().isEmpty() || requiredHeat().contains(climate.getHeat());
		boolean h = requiredHum().isEmpty() || requiredHum().contains(climate.getHumidity());
		boolean a = requiredAir().isEmpty() || requiredAir().contains(climate.getAirflow());
		return t && h && a;
	}

	@Override
	public boolean hasBlockProcess() {
		if (output.getItem() instanceof BlockItem) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasEntityProcess() {
		if (output.getItem() instanceof IEntityItem) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasDropItemProcess() {
		if (ConfigCommonBuilder.INSTANCE.enDropSmelting.get()) {
			return true;
		}
		return false;
	}

	@Override
	public List<DCHeatTier> requiredHeat() {
		return heat;
	}

	@Override
	public List<DCHumidity> requiredHum() {
		return hum;
	}

	@Override
	public List<DCAirflow> requiredAir() {
		return air;
	}

	@Override
	public int recipeFrequency() {
		return frequency;
	}

	@Override
	public boolean additionalRequire(Level level, BlockPos pos) {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClimateSmelting))
			return false;
		ClimateSmelting target = (ClimateSmelting) obj;
		return DCItemUtil.isSameItem(output, target.getOutput(), false) && target.getInput() == input;
	}

	@Override
	public boolean hasRandomTick() {
		if (processedInput.isEmpty())
			return false;
		if (processedInput.get(0).getItem() instanceof BlockItem) {
			Block block = ((BlockItem) processedInput.get(0).getItem()).getBlock();
			return block.isRandomlyTicking(block.defaultBlockState());
		} else if (processedInput.get(0).getItem() instanceof IEntityItem) {
			return true;
		}
		return true;
	}

	@Override
	public boolean isActive() {
		return active;
	}

}
