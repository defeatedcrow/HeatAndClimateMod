package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.gui.ContainerHopperFilter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileHopperFilter extends DCLockableTE implements IHopper, ISidedInventory {

	private DCInventory inv = new DCInventory(5);
	private int cooldown = -1;
	private int lastCount = 0;

	public int getCoolTime() {
		return 4;
	}

	public boolean isFilterd() {
		return true;
	}

	@Override
	public void onServerUpdate() {
		if (cooldown <= 0) {
			cooldown = getCoolTime();
			if (isActive()) {
				extractItem();
				if (!suctionItem()) {
					suctionDrop();
				}
			}
		} else {
			cooldown--;
		}
	}

	@Nullable
	private EnumFacing getCurrentFacing() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockHopperFilter) {
			EnumSide side = DCState.getSide(state, DCState.SIDE);
			return side != null ? side.getFacing() : null;
		}
		return null;
	}

	private boolean isActive() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockHopperFilter) {
			boolean flag = DCState.getBool(state, DCState.POWERED);
			return flag;
		}
		return true;
	}

	private boolean extractItem() {
		EnumFacing face = getCurrentFacing();
		if (face != null) {
			TileEntity tile = world.getTileEntity(pos.offset(face));
			if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite())) {
				IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						face.getOpposite());
				if (target != null) {
					boolean b = false;
					for (int i = 0; i < this.getSizeInventory(); i++) {
						ItemStack item = inv.getStackInSlot(i);
						int min = isFilterd() ? 1 : 0;
						if (!DCUtil.isEmpty(item) && DCUtil.getSize(item) > min) {
							ItemStack ins = item.copy();
							ins.setCount(1);
							for (int j = 0; j < target.getSlots(); j++) {
								ItemStack ret = target.insertItem(j, ins, false);
								if (DCUtil.isEmpty(ret)) {
									this.decrStackSize(i, 1);
									this.markDirty();
									tile.markDirty();
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean suctionItem() {
		EnumFacing face = getCurrentFacing() == EnumFacing.UP ? EnumFacing.DOWN : EnumFacing.UP;
		TileEntity tile = world.getTileEntity(pos.offset(face));
		if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
			if (target != null) {
				boolean b = false;
				for (int i = 0; i < target.getSlots(); i++) {
					ItemStack item = target.extractItem(i, 1, true);
					if (!DCUtil.isEmpty(item)) {
						for (int j = 0; j < this.getSizeInventory(); j++) {
							ItemStack cur = this.getStackInSlot(j);
							if (this.isItemStackable(item, cur) > 0) {
								this.incrStackInSlot(j, item);
								target.extractItem(i, 1, false);
								this.markDirty();
								tile.markDirty();
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	protected boolean suctionDrop() {
		double x1 = getPos().getX() - 0D;
		double x2 = getPos().getX() + 1D;
		double y1 = getPos().getY() + 0.5D;
		double y2 = getPos().getY() + 2D;
		double z1 = getPos().getZ() - 0D;
		double z2 = getPos().getZ() + 1D;
		if (getCurrentFacing() == EnumFacing.UP) {
			y1 = getPos().getY() - 2D;
			y2 = getPos().getY() + 0.5D;
		}
		List list = this.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
		if (list == null || list.isEmpty())
			return false;

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);
			if (entity != null) {
				if (entity instanceof EntityItem) {
					EntityItem drop = (EntityItem) entity;
					if (!DCUtil.isEmpty(drop.getItem())) {
						ItemStack ins = drop.getItem().copy();
						for (int j = 0; j < this.getSizeInventory(); j++) {
							ItemStack cur = this.getStackInSlot(j);
							int count = this.isItemStackable(ins, cur);
							if (count > 0) {
								ins.setCount(count);
								this.incrStackInSlot(j, ins);
								drop.getItem().splitStack(count);
								this.markDirty();
								if (DCUtil.isEmpty(drop.getItem())) {
									drop.setDead();
								}
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/* === 追加メソッド === */

	public static int isItemStackable(ItemStack target, ItemStack current) {
		if (DCUtil.isSameItem(target, current, true)) {
			int i = current.getCount() + target.getCount();
			if (i > current.getMaxStackSize()) {
				i = current.getMaxStackSize() - current.getCount();
				return i;
			}
			return target.getCount();
		}

		return 0;
	}

	public void incrStackInSlot(int i, ItemStack input) {
		inv.incrStackInSlot(i, input);
	}

	/* === IInventory === */

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		return inv.decrStackSize(i, num);
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		return inv.removeStackFromSlot(i);
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		inv.setInventorySlotContents(i, stack);
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (getWorld().getTileEntity(this.pos) != this || player == null)
			return false;
		else
			return Math.sqrt(player.getDistanceSq(pos)) < 256D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {

		return 0;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	@Override
	public String getName() {
		return "dcs.gui.device.hopper.filter";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerHopperFilter(this, playerIn);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.hopper.filter";
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

	/* === Hopper === */

	@Override
	public double getXPos() {
		return this.pos.getX() + 0.5D;
	}

	@Override
	public double getYPos() {
		return this.pos.getY() + 0.5D;
	}

	@Override
	public double getZPos() {
		return this.pos.getZ() + 0.5D;
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	/* === SidedInventory === */

	protected int[] slotsSides() {
		return new int[] {
				0, 1, 2, 3, 4, 5
		};
	};

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return slotsSides();
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (!DCUtil.isEmpty(inv.getStackInSlot(index)))
			return true;
		return false;
	}

	IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			if (facing == EnumFacing.DOWN)
				return (T) handlerBottom;
			else if (facing == EnumFacing.UP)
				return (T) handlerTop;
			else
				return (T) handlerSide;
		return super.getCapability(capability, facing);
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		inv.readFromNBT(tag);

		this.cooldown = tag.getInteger("Cooldown");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("Cooldown", this.cooldown);

		// アイテムの書き込み
		inv.writeToNBT(tag);
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("Cooldown", this.cooldown);

		// アイテムの書き込み
		inv.writeToNBT(tag);

		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);

		this.cooldown = tag.getInteger("Cooldown");
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new SPacketUpdateTileEntity(pos, -50, nbtTagCompound);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void markDirty() {}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

}
