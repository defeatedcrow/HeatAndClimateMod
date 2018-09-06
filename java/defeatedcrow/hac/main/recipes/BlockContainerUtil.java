package defeatedcrow.hac.main.recipes;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCSidedBlock;
import defeatedcrow.hac.core.base.DCSimpleBlock;
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
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockContainerUtil {

	public static BlockContainerUtil INS = new BlockContainerUtil();
	static final List<IBlockState> cont1 = Lists.newArrayList();
	static final List<IBlockState> cont2 = Lists.newArrayList();

	public static IBlockState getRondomContainer1(Random rand) {
		if (!cont1.isEmpty() && cont1.size() > 0) {
			int r = rand.nextInt(cont1.size());
			return cont1.get(r);
		}
		return Blocks.AIR.getDefaultState();
	}

	public static IBlockState getRondomContainer2(Random rand) {
		if (!cont2.isEmpty() && cont2.size() > 0) {
			int r = rand.nextInt(cont2.size());
			return cont2.get(r);
		}
		return Blocks.AIR.getDefaultState();
	}

	// 疑似クラフト
	public static ReturnPair getReturnItem(ItemStack item) {
		if (DCUtil.isEmpty(item)) {
			return null;
		}

		if (item.getItem() == Items.WHEAT) {
			return INS.new ReturnPair(new ItemStack(Blocks.HAY_BLOCK), 9);
		}
		if (item.getItem() == Items.SLIME_BALL) {
			return INS.new ReturnPair(new ItemStack(Blocks.SLIME_BLOCK), 9);
		}
		if (item.getItem() == Items.REDSTONE) {
			return INS.new ReturnPair(new ItemStack(Blocks.REDSTONE_BLOCK), 9);
		}

		int[] ids = OreDictionary.getOreIDs(item);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (name.contains("ingot")) {
				String split = getIngotName(name);
				if (split.length() > 0) {
					List<ItemStack> ores = OreDictionary.getOres("block" + split);
					if (ores != null && !ores.isEmpty()) {
						return INS.new ReturnPair(ores.get(0).copy(), 9);
					}
				}
				return null;
			} else if (name.contains("gem")) {
				String split = getGemName(name);
				if (split.length() > 0) {
					List<ItemStack> ores = OreDictionary.getOres("block" + split);
					if (ores != null && !ores.isEmpty()) {
						if (ores.get(0).getItem() == Item.getItemFromBlock(MainInit.gemBlock)) {
							return INS.new ReturnPair(ores.get(0).copy(), 4);
						}
						return INS.new ReturnPair(ores.get(0).copy(), 9);
					}
				}
				return null;
			}
		}

		ItemStack[] dust = BlockDusts.containedItem();
		for (int i = 0; i < dust.length; i++) {
			ItemStack check = dust[i];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.dustBlock, 1, i), 9);
			}
		}

		ItemStack[] cont1 = BlockCardboard.containedItem();
		for (int i1 = 0; i1 < cont1.length; i1++) {
			ItemStack check = cont1[i1];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.cardboard, 1, i1), 8);
			}
		}

		ItemStack[] cont2 = BlockCropBasket.containedItem();
		for (int i2 = 0; i2 < cont2.length; i2++) {
			ItemStack check = cont2[i2];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.cropBasket, 1, i2), 8);
			}
		}

		ItemStack[] cont3 = BlockCropCont.containedItem();
		for (int i3 = 0; i3 < cont3.length; i3++) {
			ItemStack check = cont3[i3];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.cropCont, 1, i3), 8);
			}
		}

		ItemStack[] cont4 = BlockDustBag.containedItem();
		for (int i4 = 0; i4 < cont4.length; i4++) {
			ItemStack check = cont4[i4];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.dustBags, 1, i4), 8);
			}
		}

		ItemStack[] cont5 = BlockEnemyCont.containedItem();
		for (int i5 = 0; i5 < cont5.length; i5++) {
			ItemStack check = cont5[i5];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.dropCont, 1, i5), 8);
			}
		}

		ItemStack[] cont6 = BlockLogCont.containedItem();
		for (int i6 = 0; i6 < cont6.length; i6++) {
			ItemStack check = cont6[i6];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.logCont, 1, i6), 8);
			}
		}

		ItemStack[] cont7 = BlockMiscCont.containedItem();
		for (int i7 = 0; i7 < cont7.length; i7++) {
			ItemStack check = cont7[i7];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MainInit.miscCont, 1, i7), 8);
			}
		}

		ItemStack[] cont8 = BlockFuelCont.containedItem();
		for (int i8 = 0; i8 < cont8.length; i8++) {
			ItemStack check = cont8[i8];
			if (DCUtil.isSameItem(item, check, false) || MainUtil.hasSameDic(item, check)) {
				return INS.new ReturnPair(new ItemStack(MachineInit.fuelCont, 1, i8), 9);
			}
		}

		return null;
	}

	public static String getIngotName(String s) {
		String split = s.substring(5);
		if (split != null) {
			return split;
		}
		return "";
	}

	public static String getGemName(String s) {
		String split = s.substring(3);
		if (split != null) {
			return split;
		}
		return "";
	}

	public class ReturnPair {
		public final ItemStack item;
		public final int count;

		private ReturnPair(ItemStack itemIn, int countIn) {
			item = itemIn;
			count = countIn;
		}
	}

	public static void init() {
		cont1.add(Blocks.REDSTONE_LAMP.getDefaultState());
		cont1.add(Blocks.BOOKSHELF.getDefaultState());
		cont1.add(Blocks.PUMPKIN.getDefaultState());
		cont1.add(Blocks.JUKEBOX.getDefaultState());
		cont1.add(Blocks.SPONGE.getDefaultState());
		cont1.add(Blocks.CAULDRON.getDefaultState());
		cont1.add(Blocks.ANVIL.getDefaultState());
		cont1.add(Blocks.DISPENSER.getDefaultState());
		if (MachineInit.fuelCont != null && ModuleConfig.machine) {
			for (int i = 0; i < ((DCSimpleBlock) MachineInit.fuelCont).maxMeta; i++) {
				cont1.add(MachineInit.fuelCont.getDefaultState().withProperty(DCState.TYPE16, i));
			}
		}
		if (MainInit.miscCont != null) {
			for (int i = 0; i < ((DCSimpleBlock) MainInit.miscCont).maxMeta; i++) {
				cont1.add(MainInit.miscCont.getDefaultState().withProperty(DCState.TYPE16, i));
			}
		}
		if (MainInit.dropCont != null) {
			for (int i = 0; i < ((DCSimpleBlock) MainInit.dropCont).maxMeta; i++) {
				cont1.add(MainInit.dropCont.getDefaultState().withProperty(DCState.TYPE16, i));
			}
		}

		if (MainInit.cropCont != null) {
			for (int i = 0; i < ((DCSimpleBlock) MainInit.cropCont).maxMeta; i++) {
				cont2.add(MainInit.cropCont.getDefaultState().withProperty(DCState.TYPE16, i));
			}
		}
		if (MainInit.cropBasket != null && ModuleConfig.food) {
			for (int i = 0; i < ((DCSimpleBlock) MainInit.cropBasket).maxMeta; i++) {
				cont2.add(MainInit.cropBasket.getDefaultState().withProperty(DCState.TYPE16, i));
			}
		}
		if (MainInit.cardboard != null) {
			for (int i = 0; i < ((DCSidedBlock) MainInit.cardboard).maxMeta; i++) {
				cont2.add(MainInit.cardboard.getDefaultState().withProperty(DCState.TYPE8, i));
			}
		}
	}

}
