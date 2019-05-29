package defeatedcrow.hac.main.recipes;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IDCInfoData;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class DCInfoData implements IDCInfoData {

	public final List<ItemStack> input = Lists.newArrayList();
	public final List<ItemStack> output = Lists.newArrayList();
	public final List<ItemStack> machine = Lists.newArrayList();
	public final List<ItemStack> input2 = Lists.newArrayList();
	public final List<ItemStack> output2 = Lists.newArrayList();
	public final List<FluidStack> inputF = Lists.newArrayList();

	public final String title;
	public final String disc;

	public DCInfoData(ItemStack in, ItemStack out, ItemStack mac, String t, String d) {
		title = t;
		disc = d;
		if (!DCUtil.isEmpty(in)) {
			input.add(in);
		}
		if (!DCUtil.isEmpty(out)) {
			output.add(out);
		}
		if (!DCUtil.isEmpty(mac)) {
			machine.add(mac);
		}
	}

	public DCInfoData setAdditionalInput(List<ItemStack> list) {
		if (!list.isEmpty()) {
			input2.addAll(list);
		}
		return this;
	}

	public DCInfoData setAdditionalFluid(FluidStack fluid) {
		if (fluid != null) {
			inputF.add(fluid);
		}
		return this;
	}

	public DCInfoData setAdditionalOutput(List<ItemStack> list) {
		if (!list.isEmpty()) {
			output2.addAll(list);
		}
		return this;
	}

	@Override
	public List<ItemStack> getInputs() {
		return input;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return output;
	}

	@Override
	public List<ItemStack> getMachines() {
		return machine;
	}

	@Override
	public List<FluidStack> getInputFluid() {
		return inputF;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDisc() {
		return disc;
	}

}
