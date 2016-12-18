package defeatedcrow.hac.machine.block;

import java.util.List;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.api.recipe.IMillRecipe;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.machine.gui.ContainerStoneMill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class TileStoneMill extends TileTorqueProcessor implements ITorqueReceiver {

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == getBaseSide();
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return side == getBaseSide().getOpposite();
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque()) {
			return false;
		}
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
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	@Override
	public int getProcessTime(ItemStack item) {
		return 20;
	}

	@Override
	public boolean isRecipeMaterial(ItemStack item) {
		IMillRecipe recipe = RecipeAPI.registerMills.getRecipe(item);
		return recipe != null;
	}

	@Override
	public boolean canStartProcess() {
		// in, out両方をチェックする
		ItemStack in = this.getStackInSlot(0);
		IMillRecipe recipe = RecipeAPI.registerMills.getRecipe(in);
		if (recipe != null) {
			List<ItemStack> outs = this.getOutputs();
			return recipe.matchOutput(outs, in, 2);
		}
		return false;
	}

	@Override
	public boolean onProcess() {
		// in, out両方をチェックする
		ItemStack in = this.getStackInSlot(0);
		IMillRecipe recipe = RecipeAPI.registerMills.getRecipe(in);
		if (recipe != null) {
			List<ItemStack> outs = this.getOutputs();
			if (recipe.matchOutput(outs, in, 2)) {
				ItemStack out = recipe.getOutput();
				ItemStack sec = recipe.getSecondary();
				ItemStack cont = recipe.getContainerItem(in);
				float chance = recipe.getSecondaryChance();
				int r = (int) (chance * 100);
				int i1 = this.insertResult(out);
				if (this.worldObj.rand.nextInt(100) < r) {
					int i2 = this.insertResult(sec);
				}
				int i3 = this.insertResult(cont);
				return true;
			}
		}
		return false;
	}

	/* inventory */

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.mill";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerStoneMill(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.mill";
	}

}
