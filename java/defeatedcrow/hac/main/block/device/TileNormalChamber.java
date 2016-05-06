package defeatedcrow.hac.main.block.device;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.core.base.TileChamberBase;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;

public class TileNormalChamber extends TileChamberBase {

	private int lastTier = 0;
	private int lastBurn = 0;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.getPos(), 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		return super.getNBT(tag);
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
	}

	@Override
	public void updateTile() {
		// 平面隣接4方向のみをチェックする特殊判定を使う
		DCAirflow[] air = new DCAirflow[4];
		air[0] = ClimateAPI.calculator.getAirflow(getWorld(), getPos().north(), 0, false);
		air[1] = ClimateAPI.calculator.getAirflow(getWorld(), getPos().south(), 0, false);
		air[2] = ClimateAPI.calculator.getAirflow(getWorld(), getPos().east(), 0, false);
		air[3] = ClimateAPI.calculator.getAirflow(getWorld(), getPos().west(), 0, false);
		DCAirflow check = DCAirflow.TIGHT;
		for (DCAirflow a : air) {
			if (a.getID() > check.getID())
				check = a;
		}

		if (check.getID() > 1) {
			this.currentHeatTier = 4;
		} else if (check.getID() == 1) {
			this.currentHeatTier = 3;
		} else {
			this.currentHeatTier = 2;
		}
	}

	@Override
	public void onTickUpdate() {
		if (this.currentBurnTime > 0) {
			this.currentBurnTime--;
		}
	}

	@Override
	protected void onServerUpdate() {
		if (this.currentBurnTime == 0) {
			if (this.getStackInSlot(0) != null && getBurnTime(this.getStackInSlot(0)) > 0) {
				ItemStack container = this.getStackInSlot(0).getItem().getContainerItem(this.getStackInSlot(0));
				this.currentBurnTime = getBurnTime(this.getStackInSlot(0));
				this.maxBurnTime = getBurnTime(this.getStackInSlot(0));
				this.decrStackSize(0, 1);
				if (this.getStackInSlot(0) == null && container != null) {
					this.setInventorySlotContents(0, container);
					this.markDirty();
				}
			}
		}

		// boolean f = false;
		// if (this.lastBurn != this.currentBurnTime) {
		// lastBurn = this.currentBurnTime;
		// f = true;
		// }
		// if (this.lastTier != this.currentHeatTier) {
		// lastTier = this.currentHeatTier;
		// f = true;
		// }
		// if (f) {
		// getWorld().markBlockForUpdate(getPos());
		// }

		if (this.getBlockType() instanceof BlockNormalChamber) {
			BlockNormalChamber cham = (BlockNormalChamber) this.getBlockType();
			IBlockState state = getWorld().getBlockState(getPos());
			cham.changeLitState(getWorld(), getPos(), state, isActive());
		}
	}

	public static int getBurnTime(ItemStack item) {
		int i = TileEntityFurnace.getItemBurnTime(item);
		int ret = i / 4;
		if (ret < 50)
			ret = 50;
		return ret;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	@Override
	protected int[] slotsTop() {
		return new int[] { 0 };
	}

	@Override
	protected int[] slotsBottom() {
		return new int[] { 0 };
	}

	@Override
	protected int[] slotsSides() {
		return new int[] { 0 };
	}

	@Override
	public String getGuiID() {
		return "dcs_climate:normal_chamber";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerNormalChamber(this, playerInventory);
	}

}
