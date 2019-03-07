package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.util.DCToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSpadeEarth extends ItemSpade implements ITexturePath {

	public ItemSpadeEarth() {
		super(DCToolMaterial.DC_CHALCEDONY);
		this.setMaxDamage(132);
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/spade_malachite";
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase living) {
		// 整地
		int area = 4;

		int e = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack);
		area += e;

		// block.removedByPlayerをつかう
		if (state.getBlockHardness(world, pos) > 0.0D) {
			if (!world.isRemote) {
				for (int x = -area + 1; x < area; x++) {
					for (int z = -area + 1; z < area; z++) {
						for (int y = 0; y < area; y++) {
							BlockPos p1 = pos.add(x, y, z);
							if (p1.getY() < 1) {
								continue;
							}
							IBlockState target = world.getBlockState(p1);
							if (!target.getBlock().hasTileEntity(target)) {
								if (living != null && living instanceof EntityPlayer) {
									target.getBlock().harvestBlock(world, (EntityPlayer) living, p1, target, null,
											stack);
									target.getBlock().removedByPlayer(target, world, p1, (EntityPlayer) living, false);
								} else {
									target.getBlock().dropBlockAsItem(world, p1, target, 0);
									target.getBlock().removedByPlayer(target, world, p1, null, false);
								}
							}
						}
					}
				}
			}

			stack.damageItem(1, living);
		}

		return true;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		} else {
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();

			if (facing == EnumFacing.UP) {
				int area = 4;
				int e = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack);
				area += e;

				if (state.getBlockHardness(world, pos) > 0.0D) {
					if (!world.isRemote) {
						for (int x = -area + 1; x < area; x++) {
							for (int z = -area + 1; z < area; z++) {
								for (int y = -area; y < 1; y++) {
									BlockPos p1 = pos.add(x, y, z);
									if (p1.getY() < 1) {
										continue;
									}
									IBlockState target = world.getBlockState(p1);
									if (world.isAirBlock(p1) || target.getMaterial().isReplaceable() ||
											target.getMaterial() == Material.PLANTS) {
										world.setBlockState(p1, Blocks.DIRT.getDefaultState());
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
