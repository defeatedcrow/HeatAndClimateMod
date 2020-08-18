package defeatedcrow.hac.main.block.build;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBedDC extends DCItem {

	private final BlockBedDC bedBlock;

	public ItemBedDC(BlockBedDC pair) {
		bedBlock = pair;
	}

	@Override
	public int getMaxMeta() {
		return 4;
	}

	private static String[] names = { "iron", "white", "rattan", "futon", "hammock" };

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/block/bed_" + names[meta] + "_item";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	private Block getBed(int meta) {
		switch (meta) {
		case 0:
			return MainInit.bed;
		case 1:
			return MainInit.bedWhite;
		case 2:
			return MainInit.bedRattan;
		case 3:
			return MainInit.bedFuton;
		case 4:
			return MainInit.bedHammock;
		}
		return MainInit.bed;
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return EnumActionResult.SUCCESS;
		} else {
			BlockPos head = null;
			BlockPos foot = null;
			EnumFacing headF = facing;
			if (facing == EnumFacing.DOWN) {
				pos = pos.down();
			}
			if (facing == EnumFacing.UP) {
				pos = pos.up();
			}
			if (headF == EnumFacing.DOWN || headF == EnumFacing.UP) {
				int i = MathHelper.floor(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
				headF = EnumFacing.getHorizontal(i);
			}

			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			boolean flag = block.isReplaceable(world, pos);
			if (flag) {
				head = pos;
			} else {
				head = pos.offset(headF);
			}
			foot = head.offset(headF);

			ItemStack item = player.getHeldItem(hand);

			if (!player.canPlayerEdit(head, facing, item) || !player.canPlayerEdit(foot, facing, item)) {
				return EnumActionResult.FAIL;
			}

			IBlockState headState = world.getBlockState(head);
			IBlockState footState = world.getBlockState(foot);

			if (!headState.getBlock().isReplaceable(world, head) && !world.isAirBlock(head)) {
				return EnumActionResult.FAIL;
			}
			if (!footState.getBlock().isReplaceable(world, foot) && !world.isAirBlock(foot)) {
				return EnumActionResult.FAIL;
			}

			IBlockState headNew = getBed(item.getItemDamage()).getDefaultState().withProperty(BlockBed.OCCUPIED, Boolean
					.valueOf(false)).withProperty(BlockBed.FACING, headF)
					.withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);

			world.setBlockState(head, headNew, 10);
			world.setBlockState(foot, headNew.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 10);
			world.playSound(player, pos, SoundType.WOOD.getPlaceSound(), SoundCategory.BLOCKS, 0.8F, 0.8F);

			world.notifyNeighborsRespectDebug(head, headState.getBlock(), false);
			world.notifyNeighborsRespectDebug(foot, footState.getBlock(), false);

			if (player instanceof EntityPlayerMP) {
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, head, item);
			}

			item.shrink(1);
			return EnumActionResult.SUCCESS;

		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.bed"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

}
