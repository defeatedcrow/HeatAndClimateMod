package defeatedcrow.hac.machine.block;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
import defeatedcrow.hac.core.base.DCInventory;
import defeatedcrow.hac.core.energy.TileTorqueLockable;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.gui.ContainerPressMachine;
import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.oredict.OreDictionary;

public class TilePressMachine extends TileTorqueLockable implements ITorqueReceiver, ISidedInventory {

	public int prevProgressTime = 0;
	public int currentProgressTime = 0;
	public static final int MAX_PROGRESS_TIME = 512;

	private int loadCount = 4;

	@Override
	public void updateTile() {
		super.updateTile();
	}

	private int getMatchSlot(ItemStack item, ItemStack[] list) {
		if (DCUtil.isEmpty(item))
			return -1;
		for (int i = 0; i < 9; i++) {
			int i2 = i + 2;
			ItemStack check = inv.getStackInSlot(i2);
			if (DCUtil.isEmpty(check) || DCUtil.isEmpty(item)) {
				continue;
			} else if (DCUtil.isIntegratedItem(check, item, false)) {
				if (check.getCount() >= item.getCount())
					return i; // 一致
			} else if (item.getItem().isDamageable() && item.getItem() == check.getItem() && item.getItemDamage() > 0) {
				if (check.getCount() >= item.getCount())
					return i; // damageableの場合、ダメージ値を問わない
			} else {
				int[] tarDic = OreDictionary.getOreIDs(check);
				int[] itemDic = OreDictionary.getOreIDs(item);
				if (tarDic.length > 0 && itemDic.length > 0) {
					for (int a = 0; a < tarDic.length; a++) {
						for (int b = 0; b < itemDic.length; b++) {
							if (tarDic[a] == itemDic[b]) {
								// 同一の辞書IDを持っている
								if (check.getCount() >= item.getCount())
									return i; // 強制的に辞書レシピ化
							} else if (itemDic[b] == OreDictionary.getOreID("dustIron")) {
								// iron dust のみの特例措置
								if (tarDic[a] == OreDictionary.getOreID("dustMagnetite")) {
									if (check.getCount() >= item.getCount())
										return i; // 強制的に辞書レシピ化
								}
							}
						}
					}
				}
			}
		}
		return -1;
	}

	private boolean flag = false;
	private int tickCount = 5;

	@Override
	protected void onServerUpdate() {
		if (tickCount <= 0) {
			tickCount = 5;

			if (loadCount > 0) {
				loadCount--;
				return;
			}
			// recipe処理
			prevProgressTime = currentProgressTime;
			currentProgressTime += MathHelper.floor(prevTorque);
			if (currentProgressTime >= MAX_PROGRESS_TIME) {
				// レシピ処理
				ItemStack output = ItemStack.EMPTY;
				ItemStack moldItem = this.getStackInSlot(0);
				if (!DCUtil.isEmpty(moldItem) && moldItem.getItem() instanceof IPressMold) {
					IPressMold mold = (IPressMold) moldItem.getItem();
					output = mold.getOutput(moldItem);
				}

				if (!DCUtil.isEmpty(output) && canIncrStack(output, 1)) {
					ItemStack[] reqs = new ItemStack[9];
					ItemStack[] rets = new ItemStack[9];
					int[] reduce = new int[9];
					int countQ = 0;
					int countR = 0;
					for (int i = 0; i < 9; i++) {
						if (!DCUtil.isEmpty(display.getStackInSlot(i))) {
							reqs[i] = display.getStackInSlot(i);
							countQ += display.getStackInSlot(i).getCount();
						}
						if (!DCUtil.isEmpty(inv.getStackInSlot(i + 2))) {
							rets[i] = inv.getStackInSlot(i + 2);
							countR += inv.getStackInSlot(i + 2).getCount();
						}
					}

					// 何か型によるレシピが表示されている時
					boolean flag = false;
					if (countQ > 0 && countR >= countQ) {
						for (int j = 0; j < 9; j++) {
							ItemStack target = reqs[j];
							int slo = getMatchSlot(target, rets);
							if (slo >= 0) {
								reduce[slo] += target.getCount();
								countQ -= target.getCount();
								flag = true;
							}
						}
					}

					// ぴったりなくなった
					if (flag && countQ == 0) {
						if (DCUtil.isEmpty(inv.getStackInSlot(1))) {
							this.inv.setInventorySlotContents(1, output);
						} else {
							DCUtil.addStackSize(inv.getStackInSlot(1), output.getCount());
						}
						for (int k = 0; k < 9; k++) {
							int red = reduce[k];
							if (red > 0) {
								this.reduceReqItem(k, red);
							}
							this.markDirty();
						}
					}
				}
			}

			int ret = currentProgressTime % MAX_PROGRESS_TIME;
			// DCLogger.debugLog("PressMachine rprogress " + ret);
			currentProgressTime = ret;

			// moldの更新
			ItemStack moldItem = this.getStackInSlot(0);
			int prevCount = 0;
			for (int i = 0; i < 9; i++) {
				if (!DCUtil.isEmpty(display.getStackInSlot(i))) {
					prevCount += display.getStackInSlot(i).getCount();
				}
			}
			if (!DCUtil.isEmpty(moldItem) && moldItem.getItem() instanceof IPressMold) {
				IPressMold mold = (IPressMold) moldItem.getItem();
				List<ItemStack> reqs = mold.getInputs(moldItem);
				if (!reqs.isEmpty()) {
					for (int i = 0; i < reqs.size(); i++) {
						display.setInventorySlotContents(i, reqs.get(i));
						;
					}
				} else {
					display.clear();
				}
			} else {
				display.clear();
			}
			int postCount = 0;
			for (int i = 0; i < 9; i++) {
				if (!DCUtil.isEmpty(display.getStackInSlot(i))) {
					postCount += display.getStackInSlot(i).getCount();
					;
				}
			}
			if (prevCount != postCount) {
				this.markDirty();
			}
		} else {
			tickCount--;
		}

	}

	// @Override
	// public void onTickUpdate() {
	// super.onTickUpdate();
	// if (!worldObj.isRemote) {
	// if (prevRotation >= 220 && !flag) {
	// worldObj.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_BREAK,
	// SoundCategory.BLOCKS, 0.85F, 0.25F);
	// flag = true;
	// }
	// if (flag && prevRotation < 220) {
	// flag = false;
	// }
	// }
	// }

	@Override
	public boolean isInputSide(EnumFacing side) {
		return side == EnumFacing.DOWN;
	}

	@Override
	public boolean isOutputSide(EnumFacing side) {
		return false;
	}

	@Override
	public boolean canReceiveTorque(float amount, EnumFacing side) {
		if (this.currentTorque >= this.maxTorque())
			return false;
		return this.isInputSide(side.getOpposite());
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

	// tier
	@Override
	public float maxTorque() {
		return 512.0F;
	}

	@Override
	public float getGearTier() {
		return 16.0F;
	}

	@Override
	public float maxSpeed() {
		return 90.0F;
	}

	/* Packet,NBT */

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		inv.readFromNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("DispItems", 10);
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = tag1.getByte("Slot");
			if (b0 >= 0 && b0 < display.getSizeInventory()) {
				display.setInventorySlotContents(b0, new ItemStack(tag1));
			}
		}

		this.currentProgressTime = tag.getInteger("BurnTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentProgressTime);

		// アイテムの書き込み
		inv.writeToNBT(tag);

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < display.getSizeInventory(); ++i) {
			if (!DCUtil.isEmpty(display.getStackInSlot(i))) {
				NBTTagCompound tag1 = new NBTTagCompound();
				tag1.setByte("Slot", (byte) i);
				display.getStackInSlot(i).writeToNBT(tag1);
				nbttaglist.appendTag(tag1);
			}
		}
		tag.setTag("DispItems", nbttaglist);

		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentProgressTime);

		// アイテムの書き込み
		inv.writeToNBT(tag);

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < display.getSizeInventory(); ++i) {
			if (!DCUtil.isEmpty(display.getStackInSlot(i))) {
				NBTTagCompound tag1 = new NBTTagCompound();
				tag1.setByte("Slot", (byte) i);
				display.getStackInSlot(i).writeToNBT(tag1);
				nbttaglist.appendTag(tag1);
			}
		}
		tag.setTag("DispItems", nbttaglist);

		return tag;
	}

	@Override
	public void setNBT(NBTTagCompound tag) {
		super.setNBT(tag);

		inv.readFromNBT(tag);

		NBTTagList nbttaglist = tag.getTagList("DispItems", 10);
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = tag1.getByte("Slot");
			if (b0 >= 0 && b0 < display.getSizeInventory()) {
				display.setInventorySlotContents(b0, new ItemStack(tag1));
			}
		}

		this.currentProgressTime = tag.getInteger("BurnTime");
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
				2, 3, 4, 5, 6, 7, 8, 9, 10
		};
	};

	protected int[] slotsBottom() {
		return new int[] {
				1, 11
		};
	};

	protected int[] slotsSides() {
		return new int[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};
	};

	public DCInventory inv = new DCInventory(12);
	public DCInventory display = new DCInventory(9);

	public List<ItemStack> getInputs() {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		for (int i = 2; i < 11; i++) {
			if (!DCUtil.isEmpty(inv.getStackInSlot(i))) {
				ret.add(inv.getStackInSlot(i));
			}
		}
		return ret;
	}

	public List<ItemStack> getOutputs() {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		if (!DCUtil.isEmpty(inv.getStackInSlot(1))) {
			ret.add(inv.getStackInSlot(1));
		}
		return ret;
	}

	// reqsの減少と容器返却を同時に行う
	private void reduceReqItem(int slot, int amo) {
		if (slot < 0 || slot > 8 || amo <= 0)
			return;
		ItemStack target = inv.getStackInSlot(slot + 2);
		if (!DCUtil.isEmpty(target)) {
			ItemStack ret = target.getItem().getContainerItem(target);
			if (DCUtil.isEmpty(ret)) {
				if (target.getCount() <= amo) {
					inv.setInventorySlotContents(slot + 2, ItemStack.EMPTY);
				} else {
					DCUtil.reduceStackSize(inv.getStackInSlot(slot + 2), amo);
				}
			}
			ret.setCount(amo);

			boolean b = target.getItem().isDamageable();
			if (!DCUtil.isEmpty(ret) && !b) {
				int red = 0;
				if (this.getStackInSlot(11).isEmpty()) {
					red = amo;
					this.setInventorySlotContents(11, ret);
				} else if (this.canIncrStack(ret, 11)) {
					red = this.incrStackSize(11, ret);
					if (red > 0) {
						getStackInSlot(11).grow(red);
					}
				}
				if (red > 0) {
					DCUtil.reduceStackSize(target, red);
					amo -= red;
					if (red >= amo) {
						ret = ItemStack.EMPTY;
					} else {
						ret.setCount(amo);
					}
				}
			}

			if (amo > 0) {
				if (!DCUtil.isEmpty(ret)) {
					if (amo >= target.getCount()) {
						inv.setInventorySlotContents(slot + 2, ret);
					} else {
						DCUtil.reduceStackSize(target, amo);
						for (int i = 0; i < 9; i++) {
							if (i == slot) {
								continue;
							}
							int a = incrStackSize(i + 2, ret);
							if (a < ret.getCount()) {
								DCUtil.reduceStackSize(ret, a);
							} else {
								break;
							}
						}
					}
				}
			}
		}
	}

	private boolean canIncrStack(ItemStack incr, int slot) {
		ItemStack check = getStackInSlot(slot);
		return DCUtil.isEmpty(check) || DCUtil.isStackable(incr, check);
	}

	public int incrStackSize(int i, ItemStack get) {
		if (i < 0 || i >= this.getSizeInventory() || DCUtil.isEmpty(get))
			return 0;
		if (DCUtil.isSameItem(get, inv.getStackInSlot(i), true)) {
			int i1 = inv.getStackInSlot(i).getCount() + get.getCount();
			if (i1 > inv.getStackInSlot(i).getMaxStackSize()) {
				i1 = inv.getStackInSlot(i).getMaxStackSize() - inv.getStackInSlot(i).getCount();
				return i1;
			}
			return get.getCount();
		}

		return 0;
	}

	@Override
	public int getSizeInventory() {
		return 21;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		if (i < 12)
			return inv.getStackInSlot(i);
		else
			return display.getStackInSlot(i - 12);
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		if (i < 12)
			return inv.decrStackSize(i, num);
		else
			return display.decrStackSize(i - 12, num);
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		if (i < 12)
			return inv.removeStackFromSlot(i);
		else
			return display.removeStackFromSlot(i - 12);
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		if (i < 0 || i >= this.getSizeInventory())
			return;
		else {
			if (i < 12) {
				inv.setInventorySlotContents(i, stack);
			} else {
				display.setInventorySlotContents(i - 12, stack);
			}

		}
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
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
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if (i == 0)
			return !DCUtil.isEmpty(stack) && stack.getItem() instanceof IPressMold;
		else if (i == 1 || i > 11)
			return false;
		else
			return true;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.currentProgressTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.currentProgressTime = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 1;
	}

	@Override
	public void clear() {
		for (int i = 0; i < 21; ++i) {
			this.setInventorySlotContents(i, null);
		}
	}

	// インベントリの名前
	@Override
	public String getName() {
		return "dcs.gui.device.pressmachine";
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
		return this.isItemValidForSlot(index, itemStack);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index != 1 && index != 11)
			return false;

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
		return new ContainerPressMachine(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.pressmachine";
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty() && display.isEmpty();
	}

}
