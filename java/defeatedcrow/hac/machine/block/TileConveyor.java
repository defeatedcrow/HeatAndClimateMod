package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.energy.TileTorqueLockable;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileConveyor extends TileTorqueLockable implements ISidedInventory {

	// 内容物の移動を制御するカウント。
	public int moveF = 0;
	public int moveB = 0;
	public int prevMoveF = 0;
	public int prevMoveB = 0;
	public static final int MAX_MOVE = 8;

	@Override
	public void updateTile() {
		super.updateTile(); // superはよばない
	}

	@Override
	public void onTickUpdate() {

	}

	@Override
	protected void onServerUpdate() {
		boolean flag = false;
		boolean flag2 = false;
		// Bがさき
		if (inv[1] == null) {
			moveB = 0;
			prevMoveB = 0;

			if (inv[0] != null && moveF >= MAX_MOVE) {
				inv[1] = inv[0].copy();
				onSmelting();
				inv[0] = null;
				moveF = 0;
				prevMoveF = 0;
				flag = true;
			}
		} else {
			if (moveB < MAX_MOVE) {
				prevMoveB = moveB;
				moveB++;
			} else {
				// 送り出し
				if (releaseItem()) {
					flag = true;
				}
			}
		}

		// つぎにF
		if (inv[0] == null) {
			moveF = 0;
			prevMoveF = 0;

			// 吸引処理
			if (insertItem()) {
				flag = true;
			}
		} else {
			if (moveF < MAX_MOVE) {
				prevMoveF = moveF;
				moveF++;
			} else {
				prevMoveF = MAX_MOVE;
				moveF = MAX_MOVE;
			}
		}

		if (flag) {
			this.markDirty();
		}

		if (moveB != prevMoveB || moveF != prevMoveF) {
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
	}

	private int stay1 = 0;

	protected boolean insertItem() {
		if (inv[0] != null) {
			return false;
		}
		if (stay1 < 0) {
			EnumFacing side = getBaseSide().getOpposite();
			TileEntity target = worldObj.getTileEntity(getPos().offset(side));
			if (target != null
					&& target.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite())) {
				IItemHandler tInv = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						side.getOpposite());
				for (int i = 0; i < tInv.getSlots(); i++) {
					if (tInv.extractItem(i, 1, true) != null) {
						inv[0] = tInv.extractItem(i, 1, false).copy();
						DCLogger.debugLog(
								"convayor inserting:" + inv[0].getDisplayName() + ", size:" + inv[0].stackSize);
						return true;
					}
				}
			}
			if (inv[0] == null) {
				double x1 = getPos().getX() - 0D;
				double x2 = getPos().getX() + 1D;
				double y1 = getPos().getY() - 1D;
				double y2 = getPos().getY() + 1D;
				double z1 = getPos().getZ() - 0D;
				double z2 = getPos().getZ() + 1D;
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(null,
						new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

				for (int i = 0; i < list.size(); ++i) {
					Entity entity = (Entity) list.get(i);
					if (inv[0] == null && entity != null && entity instanceof EntityItem) {
						EntityItem drop = (EntityItem) entity;
						if (drop.getEntityItem() != null && drop.getEntityItem().stackSize > 0) {
							inv[0] = drop.getEntityItem().splitStack(1);
							moveF = 0;
							prevMoveF = 0;
							this.markDirty();
							if (drop.getEntityItem() == null || drop.getEntityItem().stackSize <= 0) {
								drop.setDead();
							}
							DCLogger.debugLog("convayor drop picking item:" + inv[0].getDisplayName() + ", size:"
									+ inv[0].stackSize);
							break;
						}
					}
				}
			}

			if (inv[0] == null) {
				stay1 = 3;
			}
		} else {
			stay1--;
		}
		return false;
	}

	private int stay2 = 0;

	protected boolean releaseItem() {
		if (inv[1] == null) {
			return false;
		}
		boolean flag = false;
		EnumFacing side = getBaseSide();
		TileEntity target = worldObj.getTileEntity(getPos().offset(side));
		boolean skip = false;
		if (target != null && target.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite())) {
			if (target instanceof TileConveyor) {
				skip = true;
				TileConveyor tConv = (TileConveyor) target;
				if (tConv.inv[0] == null) {
					tConv.inv[0] = inv[1].copy();
					inv[1] = null;
					flag = true;
				}
			} else {
				IItemHandler tInv = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						side.getOpposite());
				for (int i = 0; i < tInv.getSlots(); i++) {
					if (tInv.getStackInSlot(i) == null) {
						DCLogger.debugLog("convayor sending:" + inv[1].getDisplayName() + ", size:" + inv[1].stackSize);
						ItemStack ret = tInv.insertItem(i, inv[1].copy(), false);
						inv[1] = null;
						flag = true;
						break;
					} else {
						ItemStack get = tInv.getStackInSlot(i);
						if (DCUtil.isSameItem(get, inv[1]) && get.stackSize < 64) {
							DCLogger.debugLog(
									"convayor sending:" + inv[1].getDisplayName() + ", size:" + inv[1].stackSize);
							tInv.getStackInSlot(i).stackSize++;
							inv[1] = null;
							flag = true;
							break;
						}
					}
				}
			}
		}
		if (!flag && inv[1] != null && !skip) {
			BlockPos next = getPos().offset(side);
			EntityItem drop = new EntityItem(worldObj, next.getX() + 0.5D, next.getY() + 0.5D, next.getZ() + 0.5D,
					inv[1].copy());
			if (worldObj.spawnEntityInWorld(drop)) {
				inv[1] = null;
				flag = true;
			}
		}
		return flag;
	}

	protected void onSmelting() {
		if (inv[1] != null && current != null) {
			ItemStack target = inv[1].copy();
			IClimateSmelting recipe = RecipeAPI.registerSmelting.getRecipe(current, target);
			if (recipe != null) {
				ItemStack ret = recipe.getOutput();
				worldObj.playSound((EntityPlayer) null, getPos().getX() + 0.5D, getPos().getY() + 0.5D,
						getPos().getZ() + 0.5D, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.25F, 0.85F);
				inv[1] = ret;
				DCLogger.debugLog("convayor smelting:" + inv[1].getDisplayName() + ", size:" + inv[1].stackSize);
			} else if (current.getHeat().getID() > DCHeatTier.KILN.getID()) {
				ItemStack burnt = FurnaceRecipes.instance().getSmeltingResult(inv[1]);
				if (burnt != null) {
					worldObj.playSound((EntityPlayer) null, getPos().getX() + 0.5D, getPos().getY() + 0.5D,
							getPos().getZ() + 0.5D, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.25F,
							0.85F);
					inv[1] = burnt;
					DCLogger.debugLog("convayor smelting:" + inv[1].getDisplayName() + ", size:" + inv[1].stackSize);
				}
			}
		}
	}

	@Override
	public float maxTorque() {
		return 128.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	/* === inventory === */

	/*
	 * slotは2つのみ
	 * 前半・後半に分かれており、前半は受け入れ専用、後半は排出専用である
	 */

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		this.inv = new ItemStack[11];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = tag1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inv.length) {
				this.inv[b0] = ItemStack.loadItemStackFromNBT(tag1);
			}
		}

		this.moveF = tag.getInteger("FowCount");
		this.moveB = tag.getInteger("BackCount");
		this.prevMoveF = tag.getInteger("PrevFowCount");
		this.prevMoveB = tag.getInteger("PrevBackCount");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("FowCount", this.moveF);
		tag.setInteger("BackCount", this.moveB);
		tag.setInteger("PrevFowCount", this.prevMoveF);
		tag.setInteger("PrevBackCount", this.prevMoveB);

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

		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("FowCount", this.moveF);
		tag.setInteger("BackCount", this.moveB);
		tag.setInteger("PrevFowCount", this.prevMoveF);
		tag.setInteger("PrevBackCount", this.prevMoveB);

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
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		this.inv = new ItemStack[11];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = tag1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inv.length) {
				this.inv[b0] = ItemStack.loadItemStackFromNBT(tag1);
			}
		}

		this.moveF = tag.getInteger("FowCount");
		this.moveB = tag.getInteger("BackCount");
		this.prevMoveF = tag.getInteger("PrevFowCount");
		this.prevMoveB = tag.getInteger("PrevBackCount");
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

	/* === inventory === */

	protected int[] slotsTop() {
		return new int[] {
				0 };
	};

	protected int[] slotsBottom() {
		return new int[] {
				1 };
	};

	protected int[] slotsSides() {
		return new int[] {
				0 };
	};

	public ItemStack[] inv = new ItemStack[2];

	public ItemStack getInput() {
		return inv[0];
	}

	public ItemStack getOutput() {
		return inv[1];
	}

	private boolean canIncrStack(ItemStack incr, int slot) {
		ItemStack check = getStackInSlot(slot);
		if (check == null) {
			return true;
		} else {
			return false;
		}
	}

	public int incrStackSize(int i, ItemStack get) {
		if (i < 0 || i >= this.getSizeInventory() || get == null)
			return 0;
		if (this.getStackInSlot(i) != null) {
			return 0;
		} else {
			if (get.stackSize > 1) {
				ItemStack ret = get.splitStack(1);
				this.setInventorySlotContents(i, ret);
			} else {
				this.setInventorySlotContents(i, get);
			}
			return 1;
		}
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		if (i >= 0 && i < 2) {
			return this.inv[i];
		} else {
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		if (i < 0 || i >= this.getSizeInventory())
			return null;
		if (this.getStackInSlot(i) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(i).stackSize <= num) {
				itemstack = this.getStackInSlot(i);
				this.setInventorySlotContents(i, null);
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(i).splitStack(num);
				if (this.getStackInSlot(i).stackSize == 0) {
					this.setInventorySlotContents(i, null);
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
			if (this.getStackInSlot(i) != null) {
				ItemStack itemstack = this.getStackInSlot(i);
				this.setInventorySlotContents(i, null);
				return itemstack;
			}
		}
		return null;
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		if (i < 0 || i >= this.getSizeInventory()) {
			return;
		} else {
			this.inv[i] = stack;

			if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
				stack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return getWorld().getTileEntity(this.pos) != this ? false
				: player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 0) {
			return stack != null && stack.getItem() != null;
		} else {
			return false;
		}
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
		inv[0] = null;
		inv[1] = null;
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.convayor";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsBottom() : (side == EnumFacing.UP ? slotsTop() : slotsSides());
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStack) && inv[0] == null;
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index != 1) {
			return false;
		}

		return true;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getName());
	}

	IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			if (facing == EnumFacing.DOWN)
				return (T) handlerBottom;
			else if (facing == EnumFacing.UP)
				return (T) handlerTop;
			else
				return (T) handlerSide;
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return null;
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.convayor";
	}

}
