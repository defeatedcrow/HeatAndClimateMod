package defeatedcrow.hac.food.block;

import java.util.List;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;

public class TileSteelPot extends TileFluidProcessorBase {

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
		super.onServerUpdate();
		boolean flag = false;
		if (lastCap != cap) {
			flag = true;
			lastCap = cap;
		}

		if (flag) {
			if (!this.hasWorld())
				return;
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
			int ret = 20 - i * 4;
			if (ret < 2) {
				ret = 2;
			}
			return ret;
		}
		return 20;
	}

	@Override
	public boolean isSuitableClimate() {
		// 問わず
		return current != null;
	}

	@Override
	public String climateSuitableMassage() {
		if (current == null)
			return "dcs.gui.message.nullclimate";
		else
			return "dcs.gui.message.suitableclimate";
	}

	// @Override
	// protected void onServerUpdate() {
	// super.onServerUpdate();
	// boolean lit = !this.outputT.isEmpty();
	// if (BlockSteelPot.isLit(getWorld(), getPos()) != lit) {
	// BlockSteelPot.changeLitState(getWorld(), getPos(), lit);
	// }
	// }

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
		return "dcs_climate:fluidprocessor_steel";
	}

}
