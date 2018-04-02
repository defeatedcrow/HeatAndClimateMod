package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.proj.EntityProjIceSpit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMaceIce extends ItemBlockMace {

	public ItemBlockMaceIce(Block block) {
		super(block);
	}

	@Override
	protected void doUsingEffect(ItemStack stack, EntityPlayer player, World world) {
		if (!DCUtil.isEmpty(stack) && player != null) {
			boolean hasAcv = true;
			boolean flag = player.capabilities.isCreativeMode;

			if (hasAcv || flag) {
				if (!world.isRemote) {
					int i = 1 + magicSuitCount(player);
					EntityProjIceSpit entityarrow = new EntityProjIceSpit(world, player);
					entityarrow.damage = 4.0F + 4.0F * i;
					entityarrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
					world.spawnEntityInWorld(entityarrow);
				}

				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
						SoundCategory.PLAYERS, 0.65F, 2.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

			} else {
				world.playSound(player, player.posX, player.posY, player.posZ,
						SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, SoundCategory.PLAYERS, 0.65F, 1.0F);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		super.addInformation2(stack, world, tooltip);
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + "Require low temperature");
		}
	}

}
