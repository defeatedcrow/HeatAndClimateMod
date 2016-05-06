package defeatedcrow.hac.main.block.device;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCTileBlock;

public class BlockNormalChamber extends DCTileBlock implements IHeatTile {

	public BlockNormalChamber(Material m, String s, int max) {
		super(m, s, max);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX,
			float hitY, float hitZ) {
		if (!world.isRemote && player != null) {
			TileEntity tile = world.getTileEntity(pos);
			ItemStack current = player.getCurrentEquippedItem();
			if (tile instanceof TileNormalChamber) {
				TileNormalChamber cham = (TileNormalChamber) tile;
				if (current != null) {
					int i = cham.isItemStackable(current, cham.getStackInSlot(0));
					if (cham.getBurnTime(current) > 0 && i > 0) {
						ItemStack set = current.copy();
						set.stackSize = 1;
						cham.incrStackInSlot(0, set);
						if (--current.stackSize <= 0) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
						}
						cham.markDirty();
						player.inventory.markDirty();
						return true;
					}
				} else if (cham.getStackInSlot(0) != null) {
					ItemStack ret = cham.getStackInSlot(0);
					EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, ret);
					world.spawnEntityInWorld(drop);
					cham.clear();
					cham.markDirty();
					return true;
				} else {

					int i = cham.getCurrentBurnTime();
					if (i < 50) {
						cham.setCurrentBurnTime(100);
					}
					i = cham.getCurrentBurnTime();
					cham.markDirty();
					DCLogger.debugLog("cham: " + i);
					return true;
				}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileNormalChamber();
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// block側の気候処理は無し
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		return false;
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileNormalChamber) {
			TileNormalChamber cham = (TileNormalChamber) tile;
			if (cham.isActive()) {
				return cham.getCurrentHeatTier();
			}
		}
		return DCHeatTier.SMELTING;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int meta = state.getValue(TYPE);
		return meta == 1 ? 15 : 0;
	}

	public void changeLitState(World world, BlockPos pos, IBlockState state, boolean f) {
		if (f) {
			world.setBlockState(pos, state.withProperty(TYPE, 1));
		} else {
			world.setBlockState(pos, state.withProperty(TYPE, 0));
		}
	}

}
