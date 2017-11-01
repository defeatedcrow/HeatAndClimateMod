package defeatedcrow.hac.main.item.misc;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRepairPutty extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"putty",
			"abrasive",
			"soap"
	};

	public ItemRepairPutty() {
		super();
		maxMeta = 2;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack != null) {
			int m = stack.getItemDamage();
			if (m == 0) {
				tooltip.add(I18n.translateToLocal("dcs.tip.repair_putty.description"));
			} else if (m == 1) {
				tooltip.add(I18n.translateToLocal("dcs.tip.abrasive.description"));
			} else if (m == 2) {
				tooltip.add(I18n.translateToLocal("dcs.tip.soap.description"));
			}
		}
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player != null && stack != null && !DCUtil.isEmpty(stack) && stack.getItemDamage() == 2) {
			IBlockState state = world.getBlockState(pos);
			if (state != null && state.getBlock() instanceof BlockColored) {
				if (!world.isRemote) {
					BlockColored target = (BlockColored) state.getBlock();
					EnumDyeColor col = state.getValue(BlockColored.COLOR);
					world.setBlockState(pos, state.withProperty(BlockColored.COLOR, EnumDyeColor.WHITE), 3);
				}
				if (!player.capabilities.isCreativeMode) {
					DCUtil.reduceStackSize(stack, 1);
				}
				return EnumActionResult.SUCCESS;
			}

		}
		return EnumActionResult.PASS;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target,
			EnumHand hand) {
		if (target != null && target instanceof EntitySheep) {
			EntitySheep entitysheep = (EntitySheep) target;
			EnumDyeColor enumdyecolor = EnumDyeColor.WHITE;

			if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor) {
				entitysheep.setFleeceColor(enumdyecolor);
				DCUtil.reduceStackSize(stack, 1);
			}
			return true;
		} else
			return false;
	}

}
