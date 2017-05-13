package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.machine.gui.ContainerHopperFluid;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.wrappers.BlockLiquidWrapper;
import net.minecraftforge.fluids.capability.wrappers.FluidBlockWrapper;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileHopperFluid extends DCLockableTE implements IHopper, ISidedInventory {

	private ItemStack[] inv = new ItemStack[2];
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
				if (!extractFluid()) {
					extractItem();
				}

				processTank();

				if (!suctionFluidBlock() && !suctionFluid()) {
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
				if (!this.hasWorldObj())
					return;
				@SuppressWarnings("unchecked")
				List<EntityPlayer> list = this.getWorld().playerEntities;
				for (EntityPlayer player : list) {
					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
					}
				}
			}
		} else {
			cooldown--;
		}
	}

	private boolean isActive() {
		IBlockState state = this.worldObj.getBlockState(pos);
		if (state != null && state.getBlock() instanceof BlockHopperFluid) {
			boolean flag = DCState.getBool(state, DCState.POWERED);
			return flag;
		}
		return true;
	}

	private boolean extractItem() {
		EnumFacing face = EnumFacing.DOWN;
		if (face != null) {
			TileEntity tile = worldObj.getTileEntity(pos.offset(face));
			if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite())) {
				IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						face.getOpposite());
				if (target != null) {
					boolean b = false;
					ItemStack item = inv[1];
					if (item != null && item.stackSize > 1) {
						ItemStack ins = item.copy();
						ins.stackSize = 1;
						for (int j = 0; j < target.getSlots(); j++) {
							ItemStack ret = target.insertItem(j, ins, false);
							if (ret == null) {
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

		TileEntity tile = worldObj.getTileEntity(getPos().down());
		if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)) {
			IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
			if (tank != null) {
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

	private boolean suctionItem() {
		EnumFacing face = EnumFacing.UP;
		TileEntity tile = worldObj.getTileEntity(pos.offset(face));
		if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite())) {
			IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite());
			if (target != null) {
				boolean b = false;
				for (int i = 0; i < target.getSlots(); i++) {
					ItemStack item = target.extractItem(i, 1, true);
					if (item != null) {
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

		TileEntity tile = worldObj.getTileEntity(getPos().up());
		if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IFluidHandler tank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);
			if (tank != null && tank.getTankProperties() != null) {
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

	private boolean suctionFluidBlock() {
		int cap = inputT.getCapacity();
		int amo = inputT.getFluidAmount();
		int mov = 1000; // 1000mBずつ

		if (amo + flowrate > cap)
			return false;

		IBlockState state = worldObj.getBlockState(pos.up());

		if (state != null && state.getBlock() != null && state.getBlock() instanceof BlockLiquid) {
			IFluidHandler tank = new BlockLiquidWrapper((BlockLiquid) state.getBlock(), worldObj, pos.up());
			if (tank != null && tank.getTankProperties() != null) {
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
			IFluidHandler tank = new FluidBlockWrapper((IFluidBlock) state.getBlock(), worldObj, pos.up());
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
		if (!this.onDrainTank(inputT, 0, 1)) {
			this.onFillTank(inputT, 0, 1);
		}
	}

	protected boolean onFillTank(DCTank tank, int slot1, int slot2) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (in == null)
			return false;

		IFluidHandler cont = null;
		IFluidHandler dummy = null;
		ItemStack in2 = new ItemStack(in.getItem(), 1, in.getItemDamage());
		if (in.getTagCompound() != null) {
			in2.setTagCompound(in.getTagCompound().copy());
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
			cont = in.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandler) {
			cont = (IFluidHandler) in.getItem();
			dummy = (IFluidHandler) in2.getItem();
		}

		if (dummy != null && dummy.getTankProperties() != null) {
			boolean loose = false;
			ItemStack ret = null;

			int max = dummy.getTankProperties()[0].getCapacity();
			FluidStack fc = dummy.drain(max, false);
			// 流入の場合
			if (fc != null && fc.amount > 0 && tank.canFillTarget(fc)) {
				ret = null;
				loose = false;
				boolean b = false;
				int rem = tank.getCapacity() - tank.getFluidAmount();
				fc = dummy.drain(rem, false);
				if (fc != null && fc.amount <= rem) {
					FluidStack fill = null;
					if (in.getItem() instanceof IFluidHandler) {
						fill = ((IFluidHandler) in2.getItem()).drain(rem, true);
						ret = in2;
					} else {
						fill = dummy.drain(rem, true);
						ret = in2;
					}

					if (ret.stackSize <= 0) {
						ret = null;
					}

					if (fill != null && (ret == null || this.isItemStackable(ret, inv[slot2]) > 0)) {
						loose = true;
						tank.fill(fill, true);
					}
				}
			}

			if (loose) {
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				this.markDirty();
				return true;
			}
		}
		return false;
	}

	protected boolean onDrainTank(DCTank tank, int slot1, int slot2) {
		ItemStack in = this.getStackInSlot(slot1);
		ItemStack out = this.getStackInSlot(slot2);
		if (in == null)
			return false;

		IFluidHandler cont = null;
		IFluidHandler dummy = null;
		ItemStack in2 = new ItemStack(in.getItem(), 1, in.getItemDamage());
		if (in.getTagCompound() != null) {
			in2.setTagCompound(in.getTagCompound().copy());
		}
		if (in.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
			cont = in.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
			dummy = in2.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		} else if (in.getItem() instanceof IFluidHandler) {
			cont = (IFluidHandler) in.getItem();
			dummy = (IFluidHandler) in2.getItem();
		}

		if (tank.getFluidAmount() > 0 && dummy != null && dummy.getTankProperties() != null) {
			boolean loose = false;
			ItemStack ret = null;

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
				if (in.getItem() instanceof IFluidHandler) {
					fill = ((IFluidHandler) in2.getItem()).fill(drain, true);
					ret = in2;
				} else {
					fill = dummy.fill(drain, true);
					ret = in2;
				}

				if (ret.stackSize <= 0) {
					ret = null;
				}

				if (fill > 0 && (ret == null || this.isItemStackable(ret, inv[slot2]) > 0)) {
					loose = true;
					tank.drain(fill, true);
				}
			}

			if (loose) {
				this.decrStackSize(slot1, 1);
				this.incrStackInSlot(slot2, ret);
				this.markDirty();
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
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));
		if (list == null || list.isEmpty())
			return false;

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);
			if (entity != null) {
				if (entity instanceof EntityItem) {
					EntityItem drop = (EntityItem) entity;
					if (drop.getEntityItem() != null && drop.getEntityItem().stackSize > 0) {
						ItemStack ins = drop.getEntityItem().copy();
						ItemStack cur = this.getStackInSlot(0);
						int count = this.isItemStackable(ins, cur);
						if (count > 0) {
							ins.stackSize = count;
							this.incrStackInSlot(0, ins);
							drop.getEntityItem().splitStack(count);
							this.markDirty();
							if (drop.getEntityItem() == null || drop.getEntityItem().stackSize <= 0) {
								drop.setDead();
							}
							return true;
						}
					}
				} else if (entity instanceof EntityCow) {
					EntityCow cow = (EntityCow) entity;
					if (MainInit.milk != null && !cow.isChild()) {
						FluidStack milk = new FluidStack(MainInit.milk, 10);
						if (inputT.fill(milk, false) > 0) {
							inputT.fill(milk, true);
						}
					}
				}
			}
		}
		return false;
	}

	/* === 追加メソッド === */

	public static int isItemStackable(ItemStack target, ItemStack current) {
		if (target == null)
			return 0;
		else if (current == null)
			return target.stackSize;

		if (target.getItem() == current.getItem() && target.getMetadata() == current.getMetadata()
				&& ItemStack.areItemStackTagsEqual(target, current)) {
			int i = current.stackSize + target.stackSize;
			if (i > current.getMaxStackSize()) {
				i = current.getMaxStackSize() - current.stackSize;
				return i;
			}
			return target.stackSize;
		}

		return 0;
	}

	public void incrStackInSlot(int i, ItemStack input) {
		if (i < this.getSizeInventory() && input != null) {
			if (this.inv[i] != null) {
				if (this.inv[i].getItem() == input.getItem() && this.inv[i].getMetadata() == input.getMetadata()
						&& ItemStack.areItemStackTagsEqual(this.inv[i], input)) {
					this.inv[i].stackSize += input.stackSize;
					if (this.inv[i].stackSize > this.getInventoryStackLimit()) {
						this.inv[i].stackSize = this.getInventoryStackLimit();
					}
				}
			} else {
				this.setInventorySlotContents(i, input);
			}
		}
	}

	/* === IInventory === */

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i < getSizeInventory())
			return this.inv[i];
		else
			return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		if (i < 0 || i >= this.getSizeInventory())
			return null;
		if (this.inv[i] != null) {
			ItemStack itemstack;

			if (this.inv[i].stackSize <= num) {
				itemstack = this.inv[i];
				this.inv[i] = null;
				return itemstack;
			} else {
				itemstack = this.inv[i].splitStack(num);
				if (this.inv[i].stackSize == 0) {
					this.inv[i] = null;
				}
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < inv.length) {
			if (this.inv[i] != null) {
				ItemStack itemstack = this.inv[i];
				this.inv[i] = null;
				return itemstack;
			}
		}
		return null;
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		if (i < 0 || i >= this.getSizeInventory())
			return;
		else {
			this.inv[i] = stack;

			if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
				stack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return getWorld().getTileEntity(this.pos) != this ? false
				: player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (stack != null)
			return stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
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
		for (int i = 0; i < this.inv.length; ++i) {
			this.inv[i] = null;
		}
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
		return this.worldObj;
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
		if (facing != null && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) inputT;
		if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
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

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = tag1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inv.length) {
				this.inv[b0] = ItemStack.loadItemStackFromNBT(tag1);
			}
		}

		this.cooldown = tag.getInteger("Cooldown");

		inputT = inputT.readFromNBT(tag, "Tank");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("Cooldown", this.cooldown);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inv.length; ++i) {
			if (inv[i] != null) {
				NBTTagCompound tag1 = new NBTTagCompound();
				tag1.setByte("Slot", (byte) i);
				inv[i].writeToNBT(tag1);
				nbttaglist.appendTag(tag1);
			}
		}
		tag.setTag("InvItems", nbttaglist);
		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("Cooldown", this.cooldown);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inv.length; ++i) {
			if (inv[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inv[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("InvItems", nbttaglist);
		inputT.writeToNBT(tag, "Tank");
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inv.length) {
				this.inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

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
	public void markDirty() {}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

}
