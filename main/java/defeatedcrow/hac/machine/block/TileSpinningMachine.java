package defeatedcrow.hac.machine.block;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.energy.ITorqueProvider;
import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.api.recipe.ISpinningRecipe;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.machine.gui.ContainerSpinning;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileSpinningMachine extends TileTorqueProcessor implements ITorqueProvider, ITorqueReceiver {

	@Override
	public void updateTile() {
		super.updateTile();

		// provider
		for (EnumFacing side : getOutputSide()) {
			this.provideTorque(world, getPos().offset(side), side, false);
		}
	}

	@Override
	public boolean isInputSide(EnumFacing side) {
		if (getBaseSide().getAxis().isVertical()) {
			return side == EnumFacing.WEST;
		}
		return side == getBaseSide().rotateY().getOpposite();
	}

	@Override
	public List<EnumFacing> getOutputSide() {
		List<EnumFacing> ret = Lists.newArrayList();
		ret.add(getBaseSide().rotateY());
		return ret;
	}

	@Override
	public float getAmount() {
		return this.getCurrentTorque() * this.getFrictionalForce();
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side);
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
	public boolean canProvideTorque(World world, BlockPos outputPos, EnumFacing output) {
		TileEntity tile = world.getTileEntity(outputPos);
		float amo = getAmount();
		if (tile != null && tile instanceof ITorqueReceiver && amo > 0F)
			return ((ITorqueReceiver) tile).canReceiveTorque(amo, output.getOpposite());
		return false;
	}

	@Override
	public float provideTorque(World world, BlockPos outputPos, EnumFacing output, boolean sim) {
		float amo = getAmount();
		if (canProvideTorque(world, outputPos, output)) {
			ITorqueReceiver target = (ITorqueReceiver) world.getTileEntity(outputPos);
			float ret = target.receiveTorque(amo, output, sim);
			return ret;
		}
		return 0;
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
		ISpinningRecipe recipe = RecipeAPI.registerSpinningRecipes.getRecipe(in);
		return recipe != null;
	}

	@Override
	public boolean canStartProcess() {
		// in, out両方をチェックする
		ItemStack in = this.getStackInSlot(0);
		ISpinningRecipe recipe = RecipeAPI.registerSpinningRecipes.getRecipe(in);
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
		ISpinningRecipe recipe = RecipeAPI.registerSpinningRecipes.getRecipe(in);
		if (recipe != null) {
			if (recipe.matchOutput(out)) {
				ItemStack out2 = recipe.getOutput();
				int i1 = inventory.insertResult(out2, 1, 2);
				if (i1 > 0) {
					this.decrStackSize(0, recipe.getInputCount());
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

	@Override
	public String getGuiID() {
		return "dcs.gui.device.spinning_machine";
	}

	@Override
	public boolean isEmpty() {
		return inventory.isEmpty();
	}

}
