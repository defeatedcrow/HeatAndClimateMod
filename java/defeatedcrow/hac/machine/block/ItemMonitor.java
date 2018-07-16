package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMonitor extends DCItemBlock {

	public ItemMonitor(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (player != null && player.isSneaking()) {
			ItemStack held = player.getHeldItem(hand);
			IBlockState state = world.getBlockState(pos);
			if (!world.isRemote && !DCUtil.isEmpty(held)) {
				String mes1 = "Stored coordinate: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ", " +
						facing;
				player.sendMessage(new TextComponentString(mes1));

				NBTTagCompound tag = held.getTagCompound();
				if (tag == null) {
					tag = new NBTTagCompound();
				}

				tag.setInteger("card.dim", world.provider.getDimension());
				tag.setInteger("card.X", pos.getX());
				tag.setInteger("card.Y", pos.getY());
				tag.setInteger("card.Z", pos.getZ());
				tag.setInteger("card.facing", facing.getIndex());

				held.setTagCompound(tag);

			}
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse2(player, world, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 3");
		if (!DCUtil.isEmpty(stack) && stack.hasTagCompound()) {
			int dim = getDim(stack);
			BlockPos pos = getPos(stack);
			EnumFacing face = getFacing(stack);
			if (pos != null && face != null) {
				tooltip.add(TextFormatting.BOLD.toString() + "=== Stored Data ===");
				tooltip.add("Dim: " + dim);
				tooltip.add("Pos: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
				tooltip.add("Side: " + face);
			}
		}
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(EnumFixedName.PAIRING.getLocalizedName());

			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			if (block instanceof BlockMonitorRedStone || block instanceof BlockMonitorComparator) {
				tooltip.add(EnumFixedName.RS.getLocalizedName() + " " + EnumFixedName.OUTPUT.getLocalizedName());
			} else {
				tooltip.add(I18n.format("dcs.tip.monitor"));
				tooltip.add(EnumFixedName.COMPARATOR.getLocalizedName() + " " +
						EnumFixedName.OUTPUT.getLocalizedName());
			}
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

	public BlockPos getPos(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("card.dim")) {
				int x = tag.getInteger("card.X");
				int y = tag.getInteger("card.Y");
				int z = tag.getInteger("card.Z");
				return new BlockPos(x, y, z);
			}
		}
		return null;
	}

	public int getDim(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("card.dim"))
				return tag.getInteger("card.dim");
		}
		return 0;
	}

	public EnumFacing getFacing(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("card.dim")) {
				int id = tag.getInteger("card.facing");
				if (id >= 0 && id < 6)
					return EnumFacing.getFront(id);
			}
		}
		return null;
	}

}
