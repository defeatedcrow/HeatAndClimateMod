package defeatedcrow.hac.machine.block;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.energy.TileTorqueProcessor;
import defeatedcrow.hac.main.ClimateMain;

public class BlockStoneMill extends BlockTorqueBase {

	public BlockStoneMill(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileTorqueProcessor) {
			if (heldItem != null && heldItem.getItem() instanceof IWrenchDC) {
				((TileTorqueProcessor) tile).rotateFace();
			} else if (!player.worldObj.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
				player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileStoneMill();
	}

}
