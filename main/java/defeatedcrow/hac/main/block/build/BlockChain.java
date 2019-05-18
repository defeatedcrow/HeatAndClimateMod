package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChain extends DCSimpleBlock {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

	public BlockChain(Material m, String s) {
		super(m, s, 1, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(30.0F);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public int getMaxMeta() {
		return 1;
	}

	private static String[] names = { "copper", "steel" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
		return true;
	}

	// 伸張
	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack held = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(held) && held.getItem() == Item.getItemFromBlock(this)) {
				if (!world.isRemote) {
					BlockPos target = null;
					for (int i = 1; i < 16; i++) {
						BlockPos check = pos.up(i);
						if (hitY < 0.5F) {
							check = pos.down(i);
						}
						if (world.getBlockState(check).getBlock().isReplaceable(world, check)) {
							target = check;
							break;
						}
					}
					if (target != null) {
						int i = held.getItemDamage();
						IBlockState set = this.getDefaultState().withProperty(DCState.TYPE16, i);
						world.setBlockState(target, set, 3);
						DCUtil.reduceStackSize(held, 1);
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		IBlockState upper = world.getBlockState(pos.up());
		return upper.getBlock() == this || upper.getBlock() instanceof BlockFence || upper.isSideSolid(world, pos
				.up(), EnumFacing.DOWN);
	}

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		if (!this.canBlockStay(world, pos, state)) {
			world.destroyBlock(pos, true);
		}
	}
}
