package defeatedcrow.hac.machine.block;

import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
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

public class BlockHandCrank extends BlockTorqueBase {

	public BlockHandCrank(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileHandCrank();
	}

	// 手回し式
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (DCUtil.isEmpty(heldItem)) {
				if (!world.isRemote) {
					TileEntity tile = world.getTileEntity(pos);
					if (tile instanceof TileHandCrank) {
						TileHandCrank crank = ((TileHandCrank) tile);
						float add = crank.currentTorque + 0.5F;
						if (add > crank.maxTorque()) {
							add = crank.maxTorque();
						}
						crank.currentTorque = add;
					}
				}
				return true;
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

}
