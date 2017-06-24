package defeatedcrow.hac.machine.block;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.gui.ContainerSpinning;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.oredict.OreDictionary;

public class TileSpinningMachine extends TileTorqueProcessor implements ITorqueReceiver {

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide().rotateY();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return false;
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side.getOpposite());
	}

	@Override
	public float receiveTorque(float amount, EnumFacing side, boolean sim) {
		float f = maxTorque() - currentTorque;
		float ret = Math.min(amount, f);
		if (!sim) {
			currentTorque += ret;
		}
		return ret;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	@Override
	public int getProcessTime() {
		return 64;
	}

	@Override
	public boolean isRecipeMaterial() {
		ItemStack in = this.getStackInSlot(0);
		SpinningRecipe recipe = getRecipe(in);
		return recipe != null;
	}

	@Override
	public boolean canStartProcess() {
		// in, out両方をチェックする
		ItemStack in = this.getStackInSlot(0);
		SpinningRecipe recipe = getRecipe(in);
		if (recipe != null) {
			ItemStack out = this.getStackInSlot(1);
			return recipe.matchOutput(out);
		}
		return false;
	}

	@Override
	public boolean onProcess() {
		// in, out両方をチェックする
		ItemStack in = this.getStackInSlot(0);
		ItemStack out = this.getStackInSlot(1);
		SpinningRecipe recipe = getRecipe(in);
		if (recipe != null) {
			if (recipe.matchOutput(out)) {
				ItemStack out2 = recipe.getOutput();
				int i1 = this.insertResult(out2);
				if (i1 > 0) {
					this.decrStackSize(0, recipe.count);
					return true;
				}
			}
		}
		return false;
	}

	/* inventory */

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.spinning";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerSpinning(this, playerInventory);
	}

	public static SpinningRecipe getRecipe(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return null;
		if (!recipes.isEmpty()) {
			for (SpinningRecipe recipe : recipes) {
				if (recipe.match(item))
					return recipe;
			}
		}
		return null;
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.spinning_machine";
	}

	public static final List<SpinningRecipe> recipes = Lists.newArrayList();

	public class SpinningRecipe {
		public final String inputName;
		public final ItemStack output;
		public final int count;

		public SpinningRecipe(String n, int i, ItemStack o) {
			inputName = n;
			output = o;
			count = i;
		}

		public ItemStack getOutput() {
			return output.copy();
		}

		public boolean match(ItemStack item) {
			if (DCUtil.isEmpty(item))
				return false;

			int[] ids = OreDictionary.getOreIDs(item);
			if (OreDictionary.doesOreNameExist(inputName) && ids != null) {
				for (int i : ids) {
					if (i == OreDictionary.getOreID(inputName) && item.stackSize >= count)
						return true;
				}
			}
			return false;
		}

		public boolean matchOutput(ItemStack item) {
			if (DCUtil.isEmpty(item))
				return true;

			if (DCUtil.isStackable(item, output))
				return true;
			return false;
		}
	}

	public static void registerRecipe(String s, int i, ItemStack o) {
		if (s != null && i > 0 && !DCUtil.isEmpty(o)) {
			recipes.add(instance.new SpinningRecipe(s, i, o));
		}
	}

	private static final TileSpinningMachine instance = new TileSpinningMachine();

}
