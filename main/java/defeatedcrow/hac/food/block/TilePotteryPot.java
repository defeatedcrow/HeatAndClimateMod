package defeatedcrow.hac.food.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class TilePotteryPot extends TileFluidProcessorBase {

	private boolean cap = false;
	private boolean lastCap = false;

	public boolean hasCap() {
		return cap;
	}

	public void setCap(boolean f) {
		cap = f;
	}

	@Override
	protected void onServerUpdate() {
		boolean flag = false;
		if (lastCap != cap) {
			flag = true;
			lastCap = cap;
		}

		if (flag) {
			if (!this.hasWorld())
				return;
			@SuppressWarnings("unchecked")
			List<EntityPlayer> list = this.getWorld().playerEntities;
			for (EntityPlayer player : list) {
				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
				}
			}
		}
	}

	@Override
	public int getProcessTime() {
		if (current != null) {
			DCHeatTier tier = current.getHeat();
			int i = tier.getTier();
			if (i < 0) {
				i *= -1;
			}
			return 100 - i * 10;
		}
		return 100;
	}

	@Override
	public boolean canRecipeProcess() {
		if (!isSuitableClimate())
			return false;
		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		if (currentRecipe == null) {
			return false;
		} else {
			if (currentRecipe.matchClimate(current) && currentRecipe.matches(ins, inf)) {
				int outAmo = currentRecipe.getOutputFluid() == null ? 0 : currentRecipe.getOutputFluid().amount;
				return currentRecipe.matchOutput(outs, outf, 3) && outputT.getFluidAmount() + outAmo <= outputT
						.getCapacity();
			}
		}
		return false;
	}

	@Override
	public boolean canStartProcess() {
		if (!isSuitableClimate())
			return false;

		FluidStack inf = inputT.getFluid();
		List<ItemStack> ins = new ArrayList<ItemStack>(this.getInputs());
		FluidStack outf = outputT.getFluid();
		List<ItemStack> outs = new ArrayList<ItemStack>(this.getOutputs());
		currentRecipe = RecipeAPI.registerFluidRecipes.getRecipe(current, ins, inf);
		return currentRecipe != null && currentRecipe.matchOutput(outs, outf, 3);
	}

	@Override
	public boolean onProcess() {
		if (currentRecipe != null) {
			ItemStack out = currentRecipe.getOutput();
			ItemStack sec = currentRecipe.getSecondary();
			float chance = MathHelper.ceil(currentRecipe.getSecondaryChance() * 100);
			FluidStack inF = currentRecipe.getInputFluid();
			FluidStack outF = currentRecipe.getOutputFluid();

			if (outF != null) {
				int c1 = outputT.fill(outF, false);
				if (c1 < outF.amount)
					return false;
			}

			List<Object> required = new ArrayList<Object>(currentRecipe.getProcessedInput());
			if (!required.isEmpty()) {
				for (int i = 4; i < 7; i++) {
					ItemStack slot = this.getStackInSlot(i);
					if (!DCUtil.isEmpty(slot)) {
						boolean inRecipe = false;
						Iterator<Object> req = required.iterator();

						// 9スロットについて、要求材料の数だけ回す
						while (req.hasNext()) {
							boolean match = false;
							Object next = req.next();
							int count = 1;

							if (next instanceof ItemStack) {
								count = ((ItemStack) next).getCount();
								match = OreDictionary.itemMatches((ItemStack) next, slot, false) && slot
										.getCount() >= count;
							} else if (next instanceof List) {
								List<ItemStack> list = new ArrayList<ItemStack>((List<ItemStack>) next);
								if (list != null && !list.isEmpty()) {
									for (ItemStack item : list) {
										boolean f = OreDictionary.itemMatches(item, slot, false) && slot.getCount() > 0;
										if (f) {
											match = true;
										}
									}
								}
							}

							if (match) {
								inRecipe = true;
								required.remove(next);
								this.decrStackSize(i, 1);
								break;
							}
						}

						if (!inRecipe) {
							break;// 中断
						}
					}
				}
				if (!required.isEmpty()) {
					return false;
				}
			}

			if (inF != null) {
				inputT.drain(inF.amount, true);
			}

			if (outF != null) {
				outputT.fill(outF, true);
			}

			if (!DCUtil.isEmpty(out)) {
				this.insertResult(out.copy(), 7, 9);
			}

			if (!DCUtil.isEmpty(sec) && world.rand.nextInt(100) < chance) {
				this.insertResult(sec.copy(), 7, 9);
			}

			this.markDirty();
			return true;
		}
		return false;
	}

	@Override
	public boolean isSuitableClimate() {
		// potteryは高温に耐えられない
		return current != null && current.getHeat().getTier() < DCHeatTier.SMELTING.getTier() && current.getHeat()
				.getTier() > DCHeatTier.FROSTBITE.getTier();
	}

	@Override
	public String climateSuitableMassage() {
		if (current == null)
			return "dcs.gui.message.nullclimate";
		else {
			if (current.getHeat().getTier() > DCHeatTier.KILN.getTier())
				return "dcs.gui.message.pottery.toohot";
			else if (current.getHeat().getTier() < DCHeatTier.COLD.getTier())
				return "dcs.gui.message.pottery.toocold";
			else
				return "dcs.gui.message.suitableclimate";
		}
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		cap = tag.getBoolean("HasCap");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setBoolean("HasCap", cap);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setBoolean("HasCap", cap);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		cap = tag.getBoolean("HasCap");
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerFluidProcessor(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:fluidprocessor_pottery";
	}

}
