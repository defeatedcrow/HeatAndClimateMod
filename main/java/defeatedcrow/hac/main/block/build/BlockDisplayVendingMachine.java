package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.block.DCExclusiveTE;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDisplayVendingMachine extends BlockDisplayShelf {

	public BlockDisplayVendingMachine(Material m, String s) {
		super(m, s);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof DCExclusiveTE && placer instanceof EntityPlayer) {
			if (tile != null && tile instanceof ITagGetter) {
				NBTTagCompound tag = stack.getTagCompound();
				if (tag != null) {
					((ITagGetter) tile).setNBT(tag);
				}
			}
			DCExclusiveTE exclusive = (DCExclusiveTE) tile;
			if (exclusive.getOwner() == null) {
				EntityPlayer player = (EntityPlayer) placer;
				GameProfile profile = player.getGameProfile();
				exclusive.setOwner(profile.getId());
				exclusive.setOwnerName(player.getDisplayNameString());
			}
		}

		IBlockState upper = MainInit.displayVenderUpper.getDefaultState();
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face != null)
			upper.withProperty(DCState.FACING, face);
		world.setBlockState(pos.up(), upper);
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileDisplayVendingMachine) {
				if (((TileDisplayVendingMachine) tile).getOwner() != null) {
					if (((TileDisplayVendingMachine) tile).isOwnerOrOP(player) && player.isSneaking()) {
						player.openGui(ClimateMain.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
					} else {
						player.openGui(ClimateMain.instance, 2, world, pos.getX(), pos.getY(), pos.getZ());
					}
				}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDisplayVendingMachine();
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add(I18n.format("dcs.tip.exclusive"));
		tooltip.add(I18n.format("dcs.tip.displaycase"));
	}

	// 2ブロック合体管理
	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		IBlockState upper = world.getBlockState(pos.up());
		if (upper == null || upper.getBlock() != MainInit.displayVenderUpper) {
			world.destroyBlock(pos, true);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && world.getBlockState(pos.up()).getBlock().isReplaceable(world, pos.up());
	}

}
