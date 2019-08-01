package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.energy.TileTorqueLockable;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IHeatTreatment;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageConveyor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileConveyor extends TileTorqueLockable implements ISidedInventory {

	// 内容物の移動を制御するカウント。
	public byte move = 0;
	public byte prevMove = 0;
	public ItemStack disp = ItemStack.EMPTY;
	public static final byte MAX_MOVE = 16;

	private int count = 0;

	@Override
	protected void onServerUpdate() {
		boolean f = false;
		ItemStack item = inv.getStackInSlot(0);
		prevMove = move;
		disp = item;

		if (DCUtil.isEmpty(item)) {
			move = 0;
			if (insertItem()) {
				f = true;
			} else {
				if (count < 5) {
					count++;
				} else {
					if (insertDrop()) {
						f = true;
					}
					count = 0;
				}
			}
		} else {
			if (move < MAX_MOVE) {
				move++;
				if (move == 8) {
					onSmelting();
					f = true;
				}
			} else {
				// 送り出し
				if (releaseItem()) {
					f = true;
					move = 0;
				}
			}

			if (f) {
				this.markDirty();
			}

			if (DCUtil.isEmpty(getStackInSlot(0)) && inv.getStackInSlot(0).getCount() < 1) {
				inv.setInventorySlotContents(0, ItemStack.EMPTY);
			}

			if (move != prevMove) {
				DCMainPacket.INSTANCE.sendToAll(new MessageConveyor(pos, move, inv.getStackInSlot(0)));
			}

			// DCLogger.infoLog(
			// "I: " + move + " " + (inv.getStackInSlot(0) == null ? "null" : inv.getStackInSlot(0).toString()));
		}
	}

	private int stay1 = 0;

	protected boolean insertItem() {
		if (!DCUtil.isEmpty(inv.getStackInSlot(0)))
			return false;

		EnumFacing side = getBaseSide().getOpposite();
		TileEntity target = world.getTileEntity(getPos().offset(side));
		// DOWNからの搬出を偽装
		if (target != null && !(target instanceof TileConveyor) && target
				.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IItemHandler tInv = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

			if (target instanceof TileHopperFilter) {
				tInv = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
			}

			for (int i = 0; i < tInv.getSlots(); i++) {
				ItemStack c = tInv.extractItem(i, 1, true);
				if (!DCUtil.isEmpty(c)) {
					inv.setInventorySlotContents(0, c.copy());
					tInv.extractItem(i, 1, false);
					// DCLogger.debugLog("convayor inserting:" + inv[0].getDisplayName() + ", size:" +
					// inv[0].stackSize);
					target.markDirty();
					this.markDirty();
					return true;
				}
			}
		}
		return false;
	}

	protected boolean insertDrop() {
		if (!DCUtil.isEmpty(inv.getStackInSlot(0)))
			return false;

		double x1 = getPos().getX() - 0D;
		double x2 = getPos().getX() + 1D;
		double y1 = getPos().getY() - 1D;
		double y2 = getPos().getY() + 1D;
		double z1 = getPos().getZ() - 0D;
		double z2 = getPos().getZ() + 1D;
		List list = this.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);
			if (DCUtil.isEmpty(inv.getStackInSlot(0)) && entity != null && entity instanceof EntityItem) {
				EntityItem drop = (EntityItem) entity;
				if (!DCUtil.isEmpty(drop.getItem())) {
					inv.setInventorySlotContents(0, drop.getItem().splitStack(1));
					this.markDirty();
					if (DCUtil.isEmpty(drop.getItem())) {
						drop.setDead();
					}
					// DCLogger.debugLog("convayor drop picking item:" + inv[0].getDisplayName() + ", size:"
					// + inv[0].stackSize);
					break;
				}
			}
		}
		return false;
	}

	private int stay2 = 0;

	protected boolean releaseItem() {
		if (DCUtil.isEmpty(inv.getStackInSlot(0)))
			return false;
		boolean flag = false;
		EnumFacing side = getBaseSide();
		TileEntity target = world.getTileEntity(getPos().offset(side));
		boolean skip = false;
		if (target != null && target.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP)) {
			IItemHandler tInv = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);

			if (target instanceof TileHopperFilter) {
				tInv = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
			}

			for (int i = 0; i < tInv.getSlots(); i++) {
				if (DCUtil.isEmpty(tInv.insertItem(i, inv.getStackInSlot(0).copy(), true))) {
					ItemStack ret = tInv.insertItem(i, inv.getStackInSlot(0).copy(), false);
					inv.setInventorySlotContents(0, ItemStack.EMPTY);
					flag = true;
					target.markDirty();
					this.markDirty();
					break;
				}
			}
			skip = true;
		}
		if (!flag && !DCUtil.isEmpty(inv.getStackInSlot(0)) && !skip) {
			BlockPos next = getPos().offset(side);
			EntityItem drop = new EntityItem(world, next.getX() + 0.5D, next.getY() + 0.5D, next.getZ() + 0.5D, inv
					.getStackInSlot(0).copy());
			drop.motionX = 0.0D;
			drop.motionY = 0.0D;
			drop.motionZ = 0.0D;
			if (world.spawnEntity(drop)) {
				inv.setInventorySlotContents(0, ItemStack.EMPTY);
				this.markDirty();
				flag = true;
			}
		}
		return flag;
	}

	protected void onSmelting() {
		if (!DCUtil.isEmpty(inv.getStackInSlot(0)) && current != null) {
			ItemStack target = inv.getStackInSlot(0).copy();
			IClimateSmelting recipe = RecipeAPI.registerSmelting.getRecipe(current, target);
			IHeatTreatment recipe2 = MainAPIManager.heatTreatmentRegister.getRecipe(target);
			if (recipe != null && !DCUtil.isEmpty(recipe.getOutput())) {
				ItemStack ret = recipe.getOutput().copy();
				world.playSound((EntityPlayer) null, getPos().getX() + 0.5D, getPos().getY() + 0.5D, getPos()
						.getZ() + 0.5D, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.25F, 0.85F);
				inv.setInventorySlotContents(0, ret);
				// DCLogger.debugLog("convayor smelting:" + inv[1].getDisplayName() + ", size:" + inv[1].stackSize);
			} else if (recipe2 != null) {
				ActionResult<ItemStack> ret = recipe2.getCurrentOutput(target, current);
				if (ret.getType() == EnumActionResult.SUCCESS && !DCUtil.isEmpty(ret.getResult())) {
					world.playSound((EntityPlayer) null, getPos().getX() + 0.5D, getPos().getY() + 0.5D, getPos()
							.getZ() + 0.5D, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.25F, 0.85F);
					inv.setInventorySlotContents(0, ret.getResult());
				}
				// DCLogger.debugLog("convayor smelting:" + inv[1].getDisplayName() + ", size:" + inv[1].stackSize);
			} else if (current.getHumidity() != DCHumidity.UNDERWATER && current
					.getAirflow() == DCAirflow.TIGHT && current.getHeat().getID() > DCHeatTier.KILN.getID()) {
				ItemStack burnt = FurnaceRecipes.instance().getSmeltingResult(target);
				if (!DCUtil.isEmpty(burnt)) {
					ItemStack ret = burnt.copy();
					world.playSound((EntityPlayer) null, getPos().getX() + 0.5D, getPos().getY() + 0.5D, getPos()
							.getZ() + 0.5D, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.25F, 0.85F);
					inv.setInventorySlotContents(0, ret);
					// DCLogger.debugLog("convayor smelting:" + inv[1].getDisplayName() + ", size:" + inv[1].stackSize);
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
		setNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		getNBT(tag);

		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setByte("FowCount", this.move);
		tag.setByte("PrevFowCount", this.prevMove);

		// アイテムの書き込み
		inv.writeToNBT(tag);
		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);

		this.move = tag.getByte("FowCount");
		this.prevMove = tag.getByte("PrevFowCount");
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
		return new int[] { 0 };
	};

	protected int[] slotsBottom() {
		return new int[] { 0, 1 };
	};

	protected int[] slotsSides() {
		return new int[] { 0 };
	};

	public DCInventory inv = new DCInventory(2);

	public ItemStack getInput() {
		return inv.getStackInSlot(0);
	}

	public ItemStack getOutput() {
		return inv.getStackInSlot(1);
	}

	private boolean canIncrStack(ItemStack incr, int slot) {
		ItemStack check = getStackInSlot(slot);
		if (DCUtil.isEmpty(check))
			return true;
		else
			return false;
	}

	public int incrStackSize(int i, ItemStack get) {
		if (i < 0 || i >= this.getSizeInventory() || DCUtil.isEmpty(get))
			return 0;
		if (!DCUtil.isEmpty(this.getStackInSlot(i)))
			return 0;
		else {
			if (get.getCount() > 1) {
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

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return getWorld().getTileEntity(this.pos) != this ? false : player.getDistanceSq(this.pos
				.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 0)
			return !DCUtil.isEmpty(stack);
		else
			return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inv.clear();
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
		return this.isItemValidForSlot(index, itemStack) && DCUtil.isEmpty(inv.getStackInSlot(0));
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return move > 8;
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
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
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

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

}
