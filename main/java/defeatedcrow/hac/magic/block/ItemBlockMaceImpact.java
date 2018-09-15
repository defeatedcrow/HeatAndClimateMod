package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMaceImpact extends ItemBlockMace {

	public ItemBlockMaceImpact(Block block) {
		super(block);
	}

	@Override
	protected void doUsingEffect(ItemStack stack, EntityPlayer player, World world) {
		if (!DCUtil.isEmpty(stack) && player != null) {
			boolean hasAcv = true;
			boolean flag = player.capabilities.isCreativeMode;

			if (hasAcv || flag) {
				if (!world.isRemote) {
					int i = 6 + magicSuitCount(player) * 2;
					// 成長
					AxisAlignedBB aabb = player.getEntityBoundingBox().grow(i, 1D, i);
					List<EntityLiving> list = world.getEntitiesWithinAABB(EntityLiving.class, aabb);
					if (!list.isEmpty())
						for (EntityLiving liv : list) {
							if (!(liv instanceof EntityTameable)) {
								double dx = player.posX - liv.posX;
								double ax = Math.abs(dx);
								dx /= ax;
								double dz = player.posZ - liv.posZ;
								double az = Math.abs(dz);
								dz /= az;
								liv.motionY += 0.05D;
								liv.knockBack(player, 3.0F, dx, dz);
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
			tooltip.add(I18n.format("dcs.tip.mace2") + " " + I18n.format("dcs.tip.mace.req.impact"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.tip.mace.impact"));
		}
	}

}
