package defeatedcrow.hac.main.recipes;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IHeatTreatment;
import defeatedcrow.hac.main.api.IHeatTreatmentRegister;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.item.ItemStack;

public class DCHeatTreatmentRegister implements IHeatTreatmentRegister {

	public IHeatTreatmentRegister instance() {
		return MainAPIManager.heatTreatmentRegister;
	}

	private static List<IHeatTreatment> list;

	public DCHeatTreatmentRegister() {
		list = new ArrayList<IHeatTreatment>();
	}

	@Override
	public List<IHeatTreatment> getRecipeList() {
		return list;
	}

	@Override
	public void registerRecipe(IHeatTreatment recipe) {
		for (ItemStack i1 : recipe.getInput1()) {
			if (isRegisteredAsInput(i1)) {
				return;
			}
		}
		list.add(recipe);
	}

	@Override
	public boolean isRegisteredAsInput(ItemStack input) {
		for (IHeatTreatment r : list) {
			for (ItemStack i1 : r.getInput1()) {
				if (DCUtil.isSameItem(input, i1, false)) {
					return true;
				}
			}
			if (DCUtil.isSameItem(input, r.getInput2(), false)) {
				return true;
			}
			if (DCUtil.isSameItem(input, r.getInput3(), false)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IHeatTreatment getRecipe(ItemStack input) {
		IHeatTreatment ret = null;
		for (IHeatTreatment r : list) {
			for (ItemStack i1 : r.getInput1()) {
				if (DCUtil.isSameItem(input, i1, false)) {
					ret = r;
				}
			}
			if (DCUtil.isSameItem(input, r.getInput2(), false)) {
				ret = r;
			}
			if (DCUtil.isSameItem(input, r.getInput3(), false)) {
				ret = r;
			}
		}
		return ret;
	}
}
