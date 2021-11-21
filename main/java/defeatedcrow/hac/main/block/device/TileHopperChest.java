package defeatedcrow.hac.main.block.device;

import javax.annotation.Nullable;

import defeatedcrow.hac.machine.block.TileHopperFilter;
import defeatedcrow.hac.main.client.gui.ContainerHopperChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;

public class TileHopperChest extends TileHopperFilter {

	public byte facing = 4;

	@Nullable
	protected EnumFacing getCurrentFacing() {
		return getFaceSide();
	}

	@Nullable
	protected EnumFacing getInsertSide() {
		return EnumFacing.UP;
	}

	public EnumFacing getFaceSide() {
		return this.faceFromID();
	}

	public void setFaceSide(EnumFacing side) {
		this.facing = this.faceID(side);
	}

	public void rotateFace() {
		switch (facing) {
		case 0:
			facing = 1;
			break;
		case 1:
			facing = 2;
			break;
		case 2:
			facing = 3;
			break;
		case 3:
			facing = 4;
			break;
		case 4:
			facing = 0;
			break;
		default:
			facing = 4;
			break;
		}
	}

	public byte faceID(EnumFacing face) {
		switch (face) {
		case NORTH:
			return 0;
		case EAST:
			return 1;
		case SOUTH:
			return 2;
		case WEST:
			return 3;
		case DOWN:
			return 4;
		default:
			return 4;
		}
	}

	public EnumFacing faceFromID() {
		switch (facing) {
		case 0:
			return EnumFacing.NORTH;
		case 1:
			return EnumFacing.EAST;
		case 2:
			return EnumFacing.SOUTH;
		case 3:
			return EnumFacing.WEST;
		case 4:
			return EnumFacing.DOWN;
		default:
			return EnumFacing.DOWN;
		}
	}

	@Override
	public int getCoolTime() {
		return 0;
	}

	protected boolean suctionItem() {
		return false;
	}

	@Override
	protected boolean suctionDrop() {
		return false;
	}

	@Override
	public boolean isFilterd() {
		return false;
	}

	/* Iinventory */

	@Override
	public int getSizeInventory() {
		return 54;
	}

	protected int[] slotsSides() {
		return new int[] {
				0,
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				11,
				12,
				13,
				14,
				15,
				16,
				17,
				18,
				19,
				20,
				21,
				22,
				23,
				24,
				25,
				26,
				27,
				28,
				29,
				30,
				31,
				32,
				33,
				34,
				35,
				36,
				37,
				38,
				39,
				40,
				41,
				42,
				43,
				44,
				45,
				46,
				47,
				48,
				49,
				50,
				51,
				52,
				53
		};
	};

	@Override
	public String getName() {
		return "dcs.gui.device.hopper.chest";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerHopperChest(this, playerIn);
	}

	/* open count */

	public boolean isOpen = false;
	public int numPlayersUsing = 0;

	@Override
	public void updateTile() {
		int x = this.pos.getX();
		int y = this.pos.getY();
		int z = this.pos.getZ();

		if (!world.isRemote && this.numPlayersUsing != 0) {
			this.numPlayersUsing = 0;

			for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(
					x - 5.0F, y - 5.0F, z - 5.0F, x + 1 + 5.0F, y + 1 + 5.0F, z + 1 + 5.0F))) {
				if (entityplayer.openContainer instanceof ContainerHopperChest) {
					IInventory iinventory = ((ContainerHopperChest) entityplayer.openContainer).tile;

					if (iinventory == this) {
						++this.numPlayersUsing;
					}
				}
			}
		}
	}

	@Override
	public void onTickUpdate() {
		int x = this.pos.getX();
		int y = this.pos.getY();
		int z = this.pos.getZ();
		if (this.numPlayersUsing > 0 && !isOpen) {
			isOpen = true;
			// DCLogger.debugLog("open");
			this.world
					.playSound((EntityPlayer) null, x + 0.5D, y + 0.5D, z + 0.5D, getOpenSound(), SoundCategory.BLOCKS, 0.5F, this.world.rand
							.nextFloat() * 0.1F + 0.9F);
		}

		if (this.numPlayersUsing == 0 && isOpen) {
			isOpen = false;
			// DCLogger.debugLog("close");
			this.world
					.playSound((EntityPlayer) null, x + 0.5D, y + 0.5D, z + 0.5D, getCloseSound(), SoundCategory.BLOCKS, 0.5F, this.world.rand
							.nextFloat() * 0.1F + 0.9F);
		}
	}

	protected SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_PISTON_EXTEND;
	}

	protected SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_PISTON_CONTRACT;
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.numPlayersUsing = type;
			return true;
		} else
			return super.receiveClientEvent(id, type);
	}

	@Override
	public void openInventory(EntityPlayer player) {
		if (!player.isSpectator()) {
			if (this.numPlayersUsing < 0) {
				this.numPlayersUsing = 0;
			}

			++this.numPlayersUsing;
			this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
			this.world.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType(), false);
		}
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		if (!player.isSpectator() && this.getBlockType() instanceof BlockHopperChest) {
			--this.numPlayersUsing;
			this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
			this.world.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType(), false);
		}
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.facing = tag.getByte("dcs.face");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setByte("dcs.face", facing);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		tag.setByte("dcs.face", facing);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);
		this.facing = tag.getByte("dcs.face");
	}

}
