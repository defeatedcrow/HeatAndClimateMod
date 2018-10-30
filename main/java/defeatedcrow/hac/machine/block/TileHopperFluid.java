package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.gui.ContainerHopperFluid;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageSingleTank;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.wrappers.BlockLiquidWrapper;
import net.minecraftforge.fluids.capability.wrappers.FluidBlockWrapper;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileHopperFluid extends DCLockableTE implements IHopper, ISidedInventory {

	private DCInventory inv = new DCInventory(2);
	public DCTank inputT = new DCTank(5000);
	private int cooldown = 5;
	private int lastCount = 0;
	private int lastInT = 0;
	private int count = 20;

	public static int flowrate = 200;

	@Override
	public void onServerUpdate() {
		if (cooldown <= 0) {
			cooldown = 5;
			if (isActive()) {
				if (!extractFluid() && !extractEntityFluid()) {
					extractItem();
				}

				processTank();

				if (!suctionFluidBlock() && !suctionFluid() && !suctionEntityFluid()) {
					if (!suctionItem()) {
						suctionDrop();
					}
				}
			}

			boolean flag = false;
			if (FluidIDRegisterDC.getID(inputT.getFluidType()) + inputT.getFluidAmount() != lastInT) {
				flag = true;
				lastInT = FluidIDRegisterDC.getID(inputT.getFluidType()) + inputT.getFluidAmount();
			}

			if (flag) {
				DCMainPacket.INSTANCE.sendToAll(new MessageSingleTank(pos, FluidIDRegisterDC.getID(inputT
						.getFluidType()), inputT.getFluidAmount()));
			}
		} else {
			cooldown--;
		}
	}

	private boolean isActive() {
		IBlockState state = this.world.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockHopperFluid) {
			boolean flag = DCState.getBool(state, DCState.POWERED);
			return flag;
		}
		return true;
	}

	private boolean extractItem() {
		EnumFacing face = EnumFacing.DOWN;
		if (face != null) {
			TileEntity tile = world.getTileEntity(pos.offset(face));
			if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite())) {
				IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face
						.getOpposite());
				if (target != null) {
					boolean b = false;
					ItemStack item = inv.getStackInSlot(1);
					if (!DCUtil.isEmpty(item)) {
						ItemStack ins = item.copy();
						ins.setCount(1);
						for (int j = 0; j < target.getSlots(); j++) {
							ItemStack ret = target.insertItem(j, ins, false);
							if (DCUtil.isEmpty(ret)) {
								this.decrStackSize(1, 1);
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

	private boolean extractFluid() {
		int cap = inputT.getCapacity();
		int amo = inputT.getFluidAmount();
		int mov = flowrate; // 200mBずつ

		if (inputT.isEmpty() || amo <= 0)
			return false;

		TileEntity tile = world.getTileEntity(getPos().down());
		if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
			IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
			if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
				int i = Math.min(mov, amo);
				FluidStack ret = new FluidStack(inputT.getFluidType(), i);
				int fill = tank.fill(ret, false);
				if (fill > 0) {
					ret = inputT.drain(fill, true);
					tank.fill(ret, true);
					tile.markDirty();
					this.markDirty();
					return true;
				}
			}
		}
		return false;
	}

	private boolean extractEntityFluid() {
		int cap = inputT.getCapacity();
		int amo = inputT.getFluidAmount();
		int mov = flowrate; // 200mBずつ

		if (inputT.isEmpty() || amo <= 0)
			return false;

		AxisAlignedBB bb = new AxisAlignedBB(pos.add(-1, 0, -1), pos.add(2, 2, 2));
		List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bb);
		if (!entities.isEmpty()) {
			for (Entity e : entities) {
				if (e != null && e.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
					IFluidHandler tank = e
							.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
					if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
						int i = Math.min(mov, amo);
						FluidStack ret = new FluidStack(inputT.getFluidType(), i);
						int fill = tank.fill(ret, false);
						if (fill > 0) {
							ret = inputT.drain(fill, true);
							tank.fill(ret, true);
							this.markDirty();
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private boolean suctionItem() {
		EnumFacing face = EnumFacing.UP;
		TileEntity tile = world.getTileEntity(pos.offset(face));
		if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite())) {
			IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite());
			if (target != null) {
				boolean b = false;
				for (int i = 0; i < target.getSlots(); i++) {
					ItemStack item = target.extractItem(i, 1, true);
					if (!DCUtil.isEmpty(item) && this.isItemValidForSlot(0, item)) {
						ItemStack cur = this.getStackInSlot(0);
						if (this.isItemStackable(item, cur) > 0) {
							this.incrStackInSlot(0, item);
							target.extractItem(i, 1, false);
							this.markDirty();
							tile.markDirty();
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean suctionFluid() {
		int cap = inputT.getCapacity();
		int amo = inputT.getFluidAmount();
		int mov = flowrate; // 200mBずつ

		if (amo >= cap)
			return false;

		TileEntity tile = world.getTileEntity(getPos().up());
		if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);
			if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
				FluidStack target = tank.getTankProperties()[0].getContents();
				if (target != null && target.getFluid() != null) {
					int i = Math.min(mov, cap - amo);
					FluidStack ret = tank.drain(i, false);
					int fill = inputT.fill(ret, false);
					if (fill > 0) {
						ret = tank.drain(fill, true);
						inputT.fill(ret, true);
						tile.markDirty();
						this.markDirty();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean suctionEntityFluid() {
		int cap = inputT.getCapacity();
		int amo = inputT.getFluidAmount();
		int mov = flowrate; // 200mBずつ

		if (amo >= cap)
			return false;

		AxisAlignedBB bb = new AxisAlignedBB(pos.add(-1, 0, -1), pos.add(2, 2, 2));
		List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bb);
		if (!entities.isEmpty()) {
			for (Entity e : entities) {
				if (e != null && e.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
					IFluidHandler tank = e
							.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
					if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
						FluidStack target = tank.getTankProperties()[0].getContents();
						if (target != null && target.getFluid() != null) {
							int i = Math.min(mov, cap - amo);
							FluidStack ret = tank.drain(i, false);
							int fill = inputT.fill(ret, false);
							if (fill > 0) {
								ret = tank.drain(fill, true);
								inputT.fill(ret, true);
								this.markDirty();
								return true;
							}
						}
					}
				} else if (e instanceof EntityCow) {
					FluidStack target = null;
					Fluid milk = FluidRegistry.getFluid("milk");
					if (milk != null) {
						target = new FluidStack(milk, 50);
					} else {
						target = new FluidStack(MainInit.cream, 10);
					}

					int fill = inputT.fill(target, false);
					if (fill > 0) {
						inputT.fill(target, true);
						this.markDirty();
					}
				}
			}
		}
		return false;
	}

	private boolean suctionFluidBlock() {
		int cap = inputT.getCapacity();
		int amo = inputT.getFluidAmount();
		int mov = 1000; // 1000mBずつ

		if (amo + flowrate > cap)
			return false;

		IBlockState state = world.getBlockState(pos.up());

		if (state != null && state.getBlock() != null && state.getBlock() instanceof BlockLiquid) {
			IFluidHandler tank = new BlockLiquidWrapper((BlockLiquid) state.getBlock(), world, pos.up());
			if (tank != null && tank.getTankProperties() != null && tank.getTankProperties().length > 0) {
				FluidStack target = tank.getTankProperties()[0].getContents();
				if (target != null && target.getFluid() != null) {
					int i = Math.min(mov, cap - amo);
					FluidStack ret = tank.drain(i, false);
					int fill = inputT.fill(ret, false);
					if (fill == 1000) {
						ret = tank.drain(fill, true);
						inputT.fill(ret, true);
						this.markDirty();
						return true;
					}
				}
			}
		} else if (state != null && state.getBlock() != null && state.getBlock() instanceof IFluidBlock) {
			IFluidHandler tank = new FluidBlockWrapper((IFluidBlock) state.getBlock(), world, pos.up());
			if (tank != null && tank.getTankProperties() != null) {
				FluidStack target = tank.getTankProperties()[0].getContents();
				if (target != null && target.getFluid() != null) {
					int i = Math.min(mov, cap - amo);
					FluidStack ret = tank.drain(i, false);
					int fill = inputT.fill(ret, false);
					if (fill > 0) {
						ret = tank.drain(fill, true);
						inputT.fill(ret, true);
						this.markDirty();
						return true;
					}
				}
			}
		}
		return false;
	}

	protected void processTank() {
		if (onDrainTank(inputT, 0, 1)) {
			this.markDirty();
		} else if (onFillTank(inputT, 0, 1))
			this.markDirty();
	}

	protected boolean onFillTank(DCTank tank, int slot1, int slot2) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (DCUtil.isEmpty(in))
			return false;

		IFluidHandlerItem dummy = null;
		ItemStack in2 = in.copy();
		if (in2.getCount() > 1) {
			in2.setCount(1);
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandlerItem) {
			dummy = (IFluidHandlerItem) in2.getItem();
		}

		if (dummy != null && dummy.getTankProperties() != null && dummy.getTankProperties().length > 0) {
			boolean loose = false;
			ItemStack ret = ItemStack.EMPTY;

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			// 流入の場合
			if (fc != null && fc.amount > 0 && tank.canFillTarget(fc)) {
				ret = ItemStack.EMPTY;
				loose = false;
				boolean b = false;
				int rem = tank.getCapacity() - tank.getFluidAmount();
				fc = dummy.drain(rem, false);
				if (fc != null && fc.amount <= rem) {
					FluidStack fill = null;
					fill = dummy.drain(rem, true);
					ret = dummy.getContainer();

					if (fill != null && (DCUtil.isEmpty(ret) || this.isItemStackable(ret, inv
							.getStackInSlot(slot2)) > 0)) {
						loose = true;
						tank.fill(fill, true);
					}
				}
			}

			if (loose) {
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				return true;
			}
		}
		return false;
	}

	protected boolean onDrainTank(DCTank tank, int slot1, int slot2) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (DCUtil.isEmpty(in))
			return false;

		IFluidHandlerItem dummy = null;
		ItemStack in2 = in.copy();
		if (in2.getCount() > 1) {
			in2.setCount(1);
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandlerItem) {
			dummy = (IFluidHandlerItem) in2.getItem();
		}

		if (tank.getFluidAmount() > 0 && dummy != null && dummy.getTankProperties() != null && dummy
				.getTankProperties().length > 0) {
			boolean loose = false;
			ItemStack ret = ItemStack.EMPTY;

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			boolean b = false;
			int rem = max;
			if (fc == null || fc.amount == 0) {
				b = true;
			} else {
				rem = max - fc.amount;
				if (tank.getFluidAmount() <= rem) {
					b = true;
				}
			}
			// 排出の場合
			if (b) {
				FluidStack drain = tank.drain(rem, false);
				int fill = 0;
				fill = dummy.fill(drain, true);
				ret = dummy.getContainer();

				if (fill > 0 && (DCUtil.isEmpty(ret) || this.isItemStackable(ret, inv.getStackInSlot(slot2)) > 0)) {
					loose = true;
					tank.drain(fill, true);
				}
			}

			if (loose) {
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				return true;
			}
		}
		return false;
	}

	private boolean suctionDrop() {
		double x1 = getPos().getX() - 0D;
		double x2 = getPos().getX() + 1D;
		double y1 = getPos().getY() + 0.5D;
		double y2 = getPos().getY() + 2D;
		double z1 = getPos().getZ() - 0D;
		double z2 = getPos().getZ() + 1D;
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
						ItemStack cur = this.getStackInSlot(0);
						int count = this.isItemStackable(ins, cur);
						if (count > 0) {
							ins.setCount(count);
							this.incrStackInSlot(0, ins);
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
		return 2;
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
		if (!DCUtil.isEmpty(stack))
			return stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		return false;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.inputT.getFluidType() == null ? -1 : FluidIDRegisterDC.getID(inputT.getFluidType());
		case 1:
			return this.inputT.getFluidAmount();
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			inputT.setFluidById(value);
			break;
		case 1:
			this.inputT.setAmount(value);
			break;
		}
	}

	@Override
	public int getFieldCount() {
		return 2;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	@Override
	public String getName() {
		return "dcs.gui.device.hopper.fluid";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerHopperFluid(this, playerIn);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.hopper.fluid";
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

	protected int[] slotsTop() {
		return new int[] {
				0
		};
	};

	protected int[] slotsBottom() {
		return new int[] {
				1
		};
	};

	protected int[] slotsSides() {
		return new int[] {
				0,
				1
		};
	};

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsBottom() : (side == EnumFacing.UP ? slotsTop() : slotsSides());
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return index == 0 && this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index > 0)
			return true;
		return false;
	}

	IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) inputT;
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

		inputT = inputT.readFromNBT(tag, "Tank");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("Cooldown", this.cooldown);

		// アイテムの書き込み
		inv.writeToNBT(tag);
		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("Cooldown", this.cooldown);

		// アイテムの書き込み
		inv.writeToNBT(tag);
		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);

		this.cooldown = tag.getInteger("Cooldown");

		inputT = inputT.readFromNBT(tag, "Tank");
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
	public void markDirty() {
		inv.markDirty();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

}
