package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.machine.MachineTier;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockHighTier extends DCItemBlock {

	public final int tier;

	public ItemBlockHighTier(Block block, int i) {
		super(block);
		tier = i;
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (player != null) {
			boolean hasAcv = MachineTier.canHandleTier(player, tier);
			boolean flag = player.capabilities.isCreativeMode;
			if (hasAcv || flag) {
				super.onItemUse2(player, world, pos, hand, facing, hitX, hitY, hitZ);
			} else {
				return EnumActionResult.PASS;
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.BOLD.toString() + "Tier " + tier);
		block.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL);
	}

}
