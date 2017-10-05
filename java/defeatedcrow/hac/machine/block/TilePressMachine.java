package defeatedcrow.hac.machine.block;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.energy.ITorqueReceiver;
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
		if (item == null || item.getItem() == null)
			return -1;
		for (int i = 0; i < 9; i++) {
			ItemStack check = inv[i + 2];
			if (check == null || check.getItem() == null) {
				continue;
			} else if (DCUtil.isIntegratedItem(check, item, false)) {
				if (check.stackSize >= item.stackSize)
					return i; // 一致
			} else if (item.getItem().isDamageable() && item.getItem() == check.getItem() && item.getItemDamage() > 0) {
				if (check.stackSize >= item.stackSize)
					return i; // damageableの場合、ダメージ値を問わない
			} else {
				int[] tarDic = OreDictionary.getOreIDs(check);
				int[] itemDic = OreDictionary.getOreIDs(item);
				if (tarDic.length > 0 && itemDic.length > 0) {
					for (int a = 0; a < tarDic.length; a++) {
						for (int b = 0; b < itemDic.length; b++) {
							if (tarDic[a] == itemDic[b]) {
								// 同一の辞書IDを持っている
								if (check.stackSize >= item.stackSize)
									return i; // 強制的に辞書レシピ化
							} else if (itemDic[b] == OreDictionary.getOreID("dustIron")) {
								// iron dust のみの特例措置
								if (tarDic[a] == OreDictionary.getOreID("dustMagnetite")) {
									if (check.stackSize >= item.stackSize)
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
			currentProgressTime += MathHelper.floor_float(prevTorque);
			if (currentProgressTime >= MAX_PROGRESS_TIME) {
				// レシピ処理
				ItemStack output = null;
				ItemStack moldItem = this.getStackInSlot(0);
				if (moldItem != null && moldItem.getItem() != null && moldItem.getItem() instanceof IPressMold) {
					IPressMold mold = (IPressMold) moldItem.getItem();
					output = mold.getOutput(moldItem);
				}

				if (output != null && canIncrStack(output, 1)) {
					ItemStack[] reqs = new ItemStack[9];
					ItemStack[] rets = new ItemStack[9];
					int[] reduce = new int[9];
					int countQ = 0;
					int countR = 0;
					for (int i = 0; i < 9; i++) {
						if (display[i] != null) {
							reqs[i] = display[i];
							countQ += display[i].stackSize;
						}
						if (inv[i + 2] != null) {
							rets[i] = inv[i + 2];
							countR += inv[i + 2].stackSize;
						}
					}

					// 何か型によるレシピが表示されている時
					boolean flag = false;
					if (countQ > 0 && countR >= countQ) {
						for (int j = 0; j < 9; j++) {
							ItemStack target = reqs[j];
							int slo = getMatchSlot(target, rets);
							if (slo >= 0) {
								reduce[slo] += target.stackSize;
								countQ -= target.stackSize;
								flag = true;
							}
						}
					}

					// ぴったりなくなった
					if (flag && countQ == 0) {
						if (this.inv[1] == null) {
							this.inv[1] = output;
						} else {
							this.inv[1].stackSize += output.stackSize;
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
				if (this.display[i] != null) {
					prevCount += display[i].stackSize;
				}
			}
			if (moldItem != null && moldItem.getItem() != null && moldItem.getItem() instanceof IPressMold) {
				IPressMold mold = (IPressMold) moldItem.getItem();
				List<ItemStack> reqs = mold.getInputs(moldItem);
				if (!reqs.isEmpty()) {
					for (int i = 0; i < reqs.size(); i++) {
						display[i] = reqs.get(i);
					}
				} else {
					for (int i = 0; i < 9; i++) {
						this.display[i] = null;
					}
				}
			} else {
				for (int i = 0; i < 9; i++) {
					this.display[i] = null;
				}
			}
			int postCount = 0;
			for (int i = 0; i < 9; i++) {
				if (this.display[i] != null) {
					postCount += display[i].stackSize;
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

		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		this.inv = new ItemStack[11];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = tag1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inv.length) {
				this.inv[b0] = ItemStack.loadItemStackFromNBT(tag1);
			}
		}

		NBTTagList nbttaglist2 = tag.getTagList("DispItems", 10);
		this.display = new ItemStack[9];

		for (int i = 0; i < nbttaglist2.tagCount(); ++i) {
			NBTTagCompound tag2 = nbttaglist2.getCompoundTagAt(i);
			byte b0 = tag2.getByte("Slot");

			if (b0 >= 0 && b0 < this.display.length) {
				this.display[b0] = ItemStack.loadItemStackFromNBT(tag2);
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

		NBTTagList nbttaglist2 = new NBTTagList();
		for (int i = 0; i < display.length; ++i) {
			if (display[i] != null) {
				NBTTagCompound tag2 = new NBTTagCompound();
				tag2.setByte("Slot", (byte) i);
				display[i].writeToNBT(tag2);
				nbttaglist2.appendTag(tag2);
			}
		}
		tag.setTag("DispItems", nbttaglist2);

		return tag;
	}

	@Override
	public NBTTagCompound getNBT(NBTTagCompound tag) {
		super.getNBT(tag);
		// 燃焼時間や調理時間などの書き込み
		tag.setInteger("BurnTime", this.currentProgressTime);

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

		NBTTagList nbttaglist2 = new NBTTagList();
		for (int i = 0; i < display.length; ++i) {
			if (display[i] != null) {
				NBTTagCompound tag2 = new NBTTagCompound();
				tag2.setByte("Slot", (byte) i);
				display[i].writeToNBT(tag2);
				nbttaglist2.appendTag(tag2);
			}
		}
		tag.setTag("DispItems", nbttaglist2);
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

		NBTTagList nbttaglist2 = tag.getTagList("DispItems", 10);
		this.display = new ItemStack[9];

		for (int i = 0; i < nbttaglist2.tagCount(); ++i) {
			NBTTagCompound tag2 = nbttaglist2.getCompoundTagAt(i);
			byte b0 = tag2.getByte("Slot");

			if (b0 >= 0 && b0 < this.display.length) {
				this.display[b0] = ItemStack.loadItemStackFromNBT(tag2);
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
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10
		};
	};

	protected int[] slotsBottom() {
		return new int[] {
				1
		};
	};

	protected int[] slotsSides() {
		return new int[] {
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10
		};
	};

	public ItemStack[] inv = new ItemStack[11];
	public ItemStack[] display = new ItemStack[9];

	public List<ItemStack> getInputs() {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		for (int i = 2; i < 11; i++) {
			if (inv[i] != null) {
				ret.add(inv[i]);
			}
		}
		return ret;
	}

	public List<ItemStack> getOutputs() {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		if (inv[1] != null) {
			ret.add(inv[1]);
		}
		return ret;
	}

	// reqsの減少と容器返却を同時に行う
	private void reduceReqItem(int slot, int amo) {
		if (slot < 0 || slot > 8 || amo <= 0)
			return;
		ItemStack target = inv[slot + 2];
		if (target != null) {
			ItemStack ret = target.getItem().getContainerItem(target);
			if (ret != null) {
				ret.stackSize = amo;
				if (amo >= target.stackSize) {
					inv[slot + 2] = ret;
				} else {
					target.stackSize -= amo;
					for (int i = 0; i < 9; i++) {
						if (i == slot) {
							continue;
						}
						int a = incrStackSize(i + 2, ret);
						if (a < ret.stackSize) {
							ret.stackSize -= a;
						} else {
							break;
						}
					}
				}
			} else {
				if (target.stackSize <= amo) {
					inv[slot + 2] = null;
				} else {
					inv[slot + 2].stackSize -= amo;
				}
			}
		}
	}

	private boolean canIncrStack(ItemStack incr, int slot) {
		ItemStack check = getStackInSlot(slot);
		if (check == null || incr == null)
			return true;
		else if (incr.getItem() == check.getItem() && incr.getItemDamage() == check.getItemDamage())
			return incr.stackSize + check.stackSize <= 64;
		else
			return false;
	}

	public int incrStackSize(int i, ItemStack get) {
		if (i < 0 || i >= this.getSizeInventory() || get == null)
			return 0;
		if (this.getStackInSlot(i) != null) {
			if (getStackInSlot(i).getItem() != get.getItem()
					|| getStackInSlot(i).getItemDamage() != get.getItemDamage())
				return 0;
			int ret = 64 - this.getStackInSlot(i).stackSize;

			if (ret >= get.stackSize) {
				this.getStackInSlot(i).stackSize += get.stackSize;
				return get.stackSize;
			} else {
				this.getStackInSlot(i).stackSize = 64;
				return ret;
			}
		} else {
			this.setInventorySlotContents(i, get);
			return get.stackSize;
		}
	}

	@Override
	public int getSizeInventory() {
		return 20;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		if (i >= 0 && i < getSizeInventory())
			if (i < 11)
				return this.inv[i];
			else
				return this.display[i - 11];
		else
			return null;
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
		if (i < 0 || i >= this.getSizeInventory())
			return;
		else {
			if (i < 11) {
				this.inv[i] = stack;

				if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
					stack.stackSize = this.getInventoryStackLimit();
				}
			} else {
				this.display[i - 11] = stack;

				if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
					stack.stackSize = this.getInventoryStackLimit();
				}
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
	public boolean isUseableByPlayer(EntityPlayer player) {
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
			return stack != null && stack.getItem() != null && stack.getItem() instanceof IPressMold;
		else if (i == 1 || i > 10)
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
		for (int i = 0; i < 20; ++i) {
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
		if (index != 1)
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
		return new ContainerPressMachine(this, playerInventory);
	}

	@Override
	public String getGuiID() {
		return "dcs.gui.device.pressmachine";
	}

}
