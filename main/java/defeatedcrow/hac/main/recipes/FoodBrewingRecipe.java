package defeatedcrow.hac.main.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IRecipePanel;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.brewing.IBrewingRecipeDC;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class FoodBrewingRecipe implements IBrewingRecipeDC {

	private final Object[] input;
	private final FluidStack inputF;
	private ArrayList<Object> processedInput = new ArrayList<Object>();
	private ArrayList<Object> inputList = new ArrayList<Object>();
	private final ItemStack output;
	private final FluidStack outputF;
	private static final ArrayList<Object> EMPTY = new ArrayList<Object>();

	public FoodBrewingRecipe(ItemStack o, FluidStack oF, FluidStack iF, Object... inputs) {
		input = inputs;
		inputF = iF;
		output = o;
		outputF = oF;
		if (inputs != null && inputs.length > 0) {
			for (int i = 0; i < inputs.length; i++) {
				if (inputs[i] instanceof String) {
					List<ItemStack> ret = new ArrayList<ItemStack>();
					ret.addAll(OreDictionary.getOres((String) inputs[i]));
					processedInput.add(ret);
					inputList.add(inputs[i]);
				} else if (inputs[i] instanceof ItemStack) {
					if (!DCUtil.isEmpty((ItemStack) inputs[i])) {
						ItemStack ret = ((ItemStack) inputs[i]).copy();
						processedInput.add(ret);
						inputList.add(ret);
					}
				} else if (inputs[i] instanceof Item) {
					ItemStack ret = new ItemStack((Item) inputs[i], 1, 0);
					processedInput.add(ret);
					inputList.add(ret);
				} else if (inputs[i] instanceof Block) {
					ItemStack ret = new ItemStack((Block) inputs[i], 1, 0);
					processedInput.add(ret);
					inputList.add(ret);
				} else {
					throw new IllegalArgumentException("Unknown Object passed to recipe!");
				}
			}
		}
	}

	@Override
	public FluidStack getInputFluid() {
		return this.inputF;
	}

	@Override
	public FluidStack getOutputFluid() {
		return this.outputF;
	}

	@Override
	public Object[] getInput() {
		return input;
	}

	@Override
	public ItemStack getOutput() {
		return DCUtil.isEmpty(output) ? ItemStack.EMPTY : output.copy();
	}

	@Override
	public List<Object> getProcessedInput() {
		if (processedInput == null || this.processedInput.isEmpty()) {
			return EMPTY;
		} else {
			return new ArrayList<Object>(this.processedInput);
		}
	}

	@Override
	public boolean matches(List<ItemStack> items, FluidStack fluid) {
		boolean b1 = false;
		if (this.inputF == null) {
			if (fluid == null)
				b1 = true;
		} else if (fluid != null) {
			if (inputF.getFluid() == fluid.getFluid() || FluidDictionaryDC.matchFluid(fluid.getFluid(), inputF
					.getFluid())) {
				b1 = inputF.amount <= fluid.amount;
			}
		}

		if (b1) {
			ArrayList<Object> required = new ArrayList<Object>(this.inputList);

			if (required.isEmpty()) {
				for (int x = 0; x < items.size(); x++) {
					ItemStack slot = items.get(x);
					if (!DCUtil.isEmpty(slot)) {
						return false;
					}
				}
				return true;
			}

			for (int x = 0; x < items.size(); x++) {
				ItemStack slot = items.get(x);
				if (DCUtil.isEmpty(slot) || slot.getItem() instanceof IRecipePanel || required.isEmpty()) {
					continue;
				}

				boolean inRecipe = false;
				Iterator<Object> req = required.iterator();

				while (req.hasNext()) {
					boolean match = false;

					Object next = req.next();
					if (next == null) {
						continue;
					}

					if (next instanceof ItemStack) {
						match = DCUtil.isSameItem((ItemStack) next, slot, false);
					} else if (next instanceof String) {
						match = DCUtil.matchDicName((String) next, slot);
					}

					if (match) {
						inRecipe = true;
						required.remove(next);
						break;
					}
				}

				req = null;

				if (!inRecipe) {
					return false;
				}
			}

			return required.isEmpty();
		} else {
			return false;
		}
	}

	@Override
	public boolean matchOutput(List<ItemStack> items, FluidStack fluid) {
		boolean b1 = false;
		if (this.outputF == null || fluid == null) {
			b1 = true;
		} else {
			b1 = outputF.getFluid() == fluid.getFluid();
		}

		if (b1) {
			if (items != null && !items.isEmpty()) {
				if (DCUtil.isEmpty(getOutput()))
					return true;
				else {
					for (int i = 0; i < items.size(); i++) {
						ItemStack get = items.get(i);
						if (DCUtil.canInsert(getOutput(), get)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean matchClimate(IClimate climate) {
		if (climate != null) {
			if (climate.getHeat().getID() > DCHeatTier.HOT.getID() || climate.getHeat().getID() < DCHeatTier.COLD
					.getID()) {
				return false;
			}
			if (climate.getHumidity().getID() > DCHumidity.WET.getID() || climate.getHumidity()
					.getID() < DCHumidity.NORMAL.getID()) {
				return false;
			}
			if (climate.getAirflow().getID() > DCAirflow.NORMAL.getID()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean additionalRequire(World world, BlockPos pos) {
		return true;
	}

}
