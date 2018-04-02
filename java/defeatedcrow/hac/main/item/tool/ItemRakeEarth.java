package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.util.DCToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRakeEarth extends ItemSpade implements ITexturePath {

	public ItemRakeEarth() {
		super(DCToolMaterial.DC_CHALCEDONY);
		this.setMaxDamage(132);
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/rake_malachite";
	}

	@Override
	public EnumActionResult onItemUse(ItemStack item, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, stack))
			return EnumActionResult.FAIL;
		else {
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();

			if (facing == EnumFacing.UP) {
				int area = 2;
				if (state.getBlockHardness(world, pos) > 0.0D) {
					if (!world.isRemote) {
						for (int x = -area + 1; x < area; x++) {
							for (int z = -area + 1; z < area; z++) {
								for (int y = -area + 1; y < area; y++) {
									BlockPos p1 = pos.add(x, y, z);
									if (p1.getY() < 1) {
										continue;
									}
									IBlockState target = world.getBlockState(p1);
									if (y < 0) {
										if (world.isAirBlock(p1) || target.getMaterial().isReplaceable()
												|| target.getMaterial() == Material.PLANTS) {
											world.setBlockState(p1, Blocks.DIRT.getDefaultState());
										}
									} else if (y == 0) {
										if (!target.getBlock().hasTileEntity(target)) {
											world.setBlockState(p1, Blocks.GRASS_PATH.getDefaultState());
										}
									} else {
										if (!target.getBlock().hasTileEntity(target)) {
											target.getBlock().harvestBlock(world, player, p1, target, null, stack);
											target.getBlock().removedByPlayer(target, world, p1, player, false);
										}
									}

								}
							}
						}
					}

					world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
					stack.damageItem(1, player);
				}

			}
			return EnumActionResult.SUCCESS;
		}
	}

}
