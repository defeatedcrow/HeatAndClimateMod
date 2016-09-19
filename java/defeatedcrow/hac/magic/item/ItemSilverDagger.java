package defeatedcrow.hac.magic.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.magic.proj.EntityProjSilver;

public class ItemSilverDagger extends DCItem {

	public ItemSilverDagger() {
		this.setMaxStackSize(16);
		this.setFull3D();
	}

	/* 雪玉に似た動作をする */

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.FAIL;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			boolean flag = player.capabilities.isCreativeMode;

			// int i = this.getMaxItemUseDuration(stack) - timeLeft;

			if (stack != null || flag) {
				if (!world.isRemote) {
					EntityProjSilver entityarrow = new EntityProjSilver(world, living);
					entityarrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
					world.spawnEntityInWorld(entityarrow);
				}

				world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
						SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F,
						1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

				if (!flag) {
					--stack.stackSize;

					if (stack.stackSize == 0) {
						player.inventory.deleteStack(stack);
					}
				}

				player.addStat(StatList.getObjectUseStats(this));

			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		String s = "items/magic/dagger_silver";
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.BOLD.toString() + "DISPOSABLE");
	}

}
