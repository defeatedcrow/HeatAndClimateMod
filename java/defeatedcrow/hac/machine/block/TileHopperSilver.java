package defeatedcrow.hac.machine.block;

import java.util.List;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.machine.block.cont.BlockFuelCont;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.block.container.BlockCardboard;
import defeatedcrow.hac.main.block.container.BlockCropBasket;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockDustBag;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.ores.BlockDusts;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileHopperSilver extends TileHopperFilter {

	@Override
	public int getCoolTime() {
		return 8;
	}

	@Override
	protected boolean suctionDrop() {
		return false;
	}

	@Override
	public boolean isFilterd() {
		return false;
	}

	@Override
	protected boolean suctionItem() {
		EnumFacing face = getCurrentFacing() == EnumFacing.UP ? EnumFacing.DOWN : EnumFacing.UP;
		TileEntity tile = world.getTileEntity(pos.offset(face));
		if (tile != null && tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IItemHandler target = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
			if (target != null) {
				boolean b = false;
				for (int i = 0; i < target.getSlots(); i++) {
					ItemStack item = target.extractItem(i, 9, true);
					ReturnPair pair = getReturnItem(item);
					if (pair != null) {
						int min = pair.count;
						ItemStack ret = pair.item.copy();
						if (item.getCount() >= min) {
							for (int j = 0; j < this.getSizeInventory(); j++) {
								ItemStack cur = this.getStackInSlot(j);
								if (this.isItemStackable(ret, cur) > 0) {
									this.incrStackInSlot(j, ret);
									target.extractItem(i, min, false);
									this.markDirty();
									tile.markDirty();
									return true;
								}
							}
						}
					} else if (!DCUtil.isEmpty(item)) {
						for (int j = 0; j < this.getSizeInventory(); j++) {
							ItemStack cur = this.getStackInSlot(j);
							int min = this.isItemStackable(item, cur);
							if (min > 0) {
								if (min > 8) {
									min = 8;
								}
								item.setCount(min);
								this.incrStackInSlot(j, item);
								target.extractItem(i, min, false);
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

	@Override
	protected boolean extractItem() {
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
						if (!DCUtil.isEmpty(item) && DCUtil.getSize(item) > 0) {
							ItemStack ins = item.copy();
							if (ins.getCount() > 8) {
								ins.setCount(8);
							}
							int min = ins.getCount();
							for (int j = 0; j < target.getSlots(); j++) {
								ItemStack ret = target.insertItem(j, ins, false);
								if (DCUtil.isEmpty(ret)) {
									this.decrStackSize(i, min);
									this.markDirty();
									tile.markDirty();
									return true;
								} else if (ret.getCount() < min) {
									this.decrStackSize(i, 8 - ret.getCount());
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

	// 疑似クラフト
	private ReturnPair getReturnItem(ItemStack item) {
		if (DCUtil.isEmpty(item)) {
			return null;
		}

		if (item.getItem() == Items.WHEAT) {
			return new ReturnPair(new ItemStack(Blocks.HAY_BLOCK), 9);
		}
		if (item.getItem() == Items.SLIME_BALL) {
			return new ReturnPair(new ItemStack(Blocks.SLIME_BLOCK), 9);
		}
		if (item.getItem() == Items.REDSTONE) {
			return new ReturnPair(new ItemStack(Blocks.REDSTONE_BLOCK), 9);
		}

		int[] ids = OreDictionary.getOreIDs(item);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (name.contains("ingot")) {
				String split = getIngotName(name);
				if (split.length() > 0) {
					List<ItemStack> ores = OreDictionary.getOres("block" + split);
					if (ores != null && !ores.isEmpty()) {
						return new ReturnPair(ores.get(0).copy(), 9);
					}
				}
				return null;
			} else if (name.contains("gem")) {
				String split = getGemName(name);
				if (split.length() > 0) {
					List<ItemStack> ores = OreDictionary.getOres("block" + split);
					if (ores != null && !ores.isEmpty()) {
						if (ores.get(0).getItem() == Item.getItemFromBlock(MainInit.gemBlock)) {
							return new ReturnPair(ores.get(0).copy(), 4);
						}
						return new ReturnPair(ores.get(0).copy(), 9);
					}
				}
				return null;
			}
		}

		ItemStack[] dust = BlockDusts.containedItem();
		for (int i = 0; i < dust.length; i++) {
			ItemStack check = dust[i];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.dustBlock, 1, i), 9);
			}
		}

		ItemStack[] cont1 = BlockCardboard.containedItem();
		for (int i1 = 0; i1 < cont1.length; i1++) {
			ItemStack check = cont1[i1];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.cardboard, 1, i1), 8);
			}
		}

		ItemStack[] cont2 = BlockCropBasket.containedItem();
		for (int i2 = 0; i2 < cont2.length; i2++) {
			ItemStack check = cont2[i2];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.cropBasket, 1, i2), 8);
			}
		}

		ItemStack[] cont3 = BlockCropCont.containedItem();
		for (int i3 = 0; i3 < cont3.length; i3++) {
			ItemStack check = cont3[i3];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.cropCont, 1, i3), 8);
			}
		}

		ItemStack[] cont4 = BlockDustBag.containedItem();
		for (int i4 = 0; i4 < cont4.length; i4++) {
			ItemStack check = cont4[i4];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.dustBags, 1, i4), 8);
			}
		}

		ItemStack[] cont5 = BlockEnemyCont.containedItem();
		for (int i5 = 0; i5 < cont5.length; i5++) {
			ItemStack check = cont5[i5];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.dropCont, 1, i5), 8);
			}
		}

		ItemStack[] cont6 = BlockLogCont.containedItem();
		for (int i6 = 0; i6 < cont6.length; i6++) {
			ItemStack check = cont6[i6];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.logCont, 1, i6), 8);
			}
		}

		ItemStack[] cont7 = BlockMiscCont.containedItem();
		for (int i7 = 0; i7 < cont7.length; i7++) {
			ItemStack check = cont7[i7];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MainInit.miscCont, 1, i7), 8);
			}
		}

		ItemStack[] cont8 = BlockFuelCont.containedItem();
		for (int i8 = 0; i8 < cont8.length; i8++) {
			ItemStack check = cont8[i8];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return new ReturnPair(new ItemStack(MachineInit.fuelCont, 1, i8), 9);
			}
		}

		return null;
	}

	private String getIngotName(String s) {
		String split = s.substring(5);
		if (split != null) {
			return split;
		}
		return "";
	}

	private String getGemName(String s) {
		String split = s.substring(3);
		if (split != null) {
			return split;
		}
		return "";
	}

	private class ReturnPair {
		final ItemStack item;
		final int count;

		private ReturnPair(ItemStack itemIn, int countIn) {
			item = itemIn;
			count = countIn;
		}
	}
}
