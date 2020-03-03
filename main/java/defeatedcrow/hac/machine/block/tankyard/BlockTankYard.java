package defeatedcrow.hac.machine.block.tankyard;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTankYard extends DCTileBlock {

	public BlockTankYard(String s) {
		super(Material.CLAY, s, 0);
		this.setResistance(120.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileTankYard();
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (hand == EnumHand.MAIN_HAND) {

				TileEntity tile = world.getTileEntity(pos);
				if (!world.isRemote && tile instanceof TileTankYard) {
					if (!DCUtil.isEmpty(heldItem)) {
						if (heldItem.getItem() instanceof ItemBlock) {
							return false;
						} else if (heldItem.getItem() instanceof IWrenchDC) {
							((TileTankYard) tile).setRequest(player.isSneaking());
						} else if (DCFluidUtil.onActivateDCTank(tile, heldItem, world, state, side, player)) {
							world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
						}
					} else {
						FluidStack f = ((TileTankYard) tile).getTank().getFluid();
						if (f != null) {
							String name = f.getLocalizedName();
							int lim = ((TileTankYard) tile).getLimit();
							int i = f.amount;
							String mes1 = "Stored Fluid: " + name + " " + i + "/" + lim + "mB";
							player.sendMessage(new TextComponentString(mes1));
						} else {
							int lim = ((TileTankYard) tile).getLimit();
							String mes1 = "Stored Fluid: EMPTY 0/" + lim + "mB";
							player.sendMessage(new TextComponentString(mes1));
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
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
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
