package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMaceDry extends ItemBlockMace {

	public ItemBlockMaceDry(Block block) {
		super(block);
	}

	@Override
	protected void doUsingEffect(ItemStack stack, EntityPlayer player, World world) {
		if (!DCUtil.isEmpty(stack) && player != null) {
			boolean hasAcv = true;
			boolean flag = player.capabilities.isCreativeMode;

			if (hasAcv || flag) {
				if (!world.isRemote) {
					int i = 5 + magicSuitCount(player);
					// 成長
					// 5x5x3 の範囲
					BlockPos pos = player.getPosition();
					BlockPos min = new BlockPos(pos.add(-i, -2, -i));
					BlockPos max = new BlockPos(pos.add(i, 2, i));
					Iterable<BlockPos> itr = pos.getAllInBox(min, max);
					for (BlockPos p1 : itr) {
						IBlockState st = world.getBlockState(p1);
						int meta = st.getBlock().getMetaFromState(st);
						if (st.getBlock() == Blocks.WATER || st.getBlock() == Blocks.FLOWING_WATER) {
							world.setBlockState(p1, Blocks.AIR.getDefaultState(), 2);
						}
					}
				}

				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 0.65F, 1.0F /
						(itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

			} else {
				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, SoundCategory.PLAYERS, 0.65F, 1.0F);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		super.addInformation2(stack, world, tooltip);
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(I18n.format("dcs.tip.mace2") + " " + I18n.format("dcs.tip.mace.req.dry"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.tip.mace.dry"));
		}
	}

}
