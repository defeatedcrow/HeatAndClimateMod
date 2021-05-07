package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDisplayStand extends BlockDisplayShelf {

	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.25D, 0.9375D);

	public BlockDisplayStand(Material m, String s) {
		super(m, s);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileDisplayStand) {
				TileDisplayStand stand = (TileDisplayStand) tile;
				if (DCUtil.isEmpty(stand.getStackInSlot(0)) && !DCUtil.isEmpty(player.getHeldItemMainhand())) {
					stand.setInventorySlotContents(0, player.getHeldItemMainhand().splitStack(1));
					stand.setDate();
					stand.markDirty();
					player.inventory.markDirty();
				} else if (!DCUtil.isEmpty(stand.getStackInSlot(0)) && player.isSneaking()) {
					ItemStack drop = stand.getStackInSlot(0).copy();
					stand.decrStackSize(0, 1);
					EntityItem entityitem = new EntityItem(world, player.posX, player.posY + 0.125D, player.posZ, drop);
					world.spawnEntity(entityitem);
					stand.markDirty();
				} else {
					this.onRightClickByOthers(world, pos, state, player, hand, side, hitX, hitY, hitZ);
				}
			}
		}
		return true;
	}

	@Override
	public boolean onRightClickByOthers(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileDisplayStand) {
				TileDisplayStand stand = (TileDisplayStand) tile;
				if (!DCUtil.isEmpty(stand.getStackInSlot(0))) {
					String mes1 = stand.getStackInSlot(0).getDisplayName();
					player.sendMessage(new TextComponentString(mes1));
					String mes2 = "Places on " + stand.getDate();
					player.sendMessage(new TextComponentString(mes2));
					if (stand.getOwner() != null) {
						String mes3 = "Owner: " + stand.getOwnerName();
						player.sendMessage(new TextComponentString(mes3));
					}
				}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDisplayStand();
	}

}
