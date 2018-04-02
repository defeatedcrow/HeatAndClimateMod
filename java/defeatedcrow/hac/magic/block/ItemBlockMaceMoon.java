package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMaceMoon extends ItemBlockMace {

	public ItemBlockMaceMoon(Block block) {
		super(block);
	}

	@Override
	protected void doUsingEffect(ItemStack stack, EntityPlayer player, World world) {
		if (!DCUtil.isEmpty(stack) && player != null) {
			boolean hasAcv = true;
			boolean flag = player.capabilities.isCreativeMode;

			if (hasAcv || flag) {
				if (!world.isRemote) {
					if (player.isPotionActive(MobEffects.POISON)) {
						player.removePotionEffect(MobEffects.POISON);
					}
					if (player.isPotionActive(MobEffects.WITHER)) {
						player.removePotionEffect(MobEffects.WITHER);
					}
					if (player.isPotionActive(MobEffects.BLINDNESS)) {
						player.removePotionEffect(MobEffects.BLINDNESS);
					}
					if (player.isPotionActive(MobEffects.NAUSEA)) {
						player.removePotionEffect(MobEffects.NAUSEA);
					}
					if (player.isPotionActive(MobEffects.HUNGER)) {
						player.removePotionEffect(MobEffects.HUNGER);
					}
					if (player.isPotionActive(MobEffects.LEVITATION)) {
						player.removePotionEffect(MobEffects.LEVITATION);
					}
					if (player.isPotionActive(MobEffects.MINING_FATIGUE)) {
						player.removePotionEffect(MobEffects.MINING_FATIGUE);
					}
					if (player.isPotionActive(MobEffects.SLOWNESS)) {
						player.removePotionEffect(MobEffects.SLOWNESS);
					}
					if (player.isPotionActive(MobEffects.UNLUCK)) {
						player.removePotionEffect(MobEffects.UNLUCK);
					}
					if (player.isPotionActive(MobEffects.WEAKNESS)) {
						player.removePotionEffect(MobEffects.WEAKNESS);
					}
					int i = 1 + magicSuitCount(player);
					player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600 * i, i));
				}

				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP,
						SoundCategory.PLAYERS, 0.65F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

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
			tooltip.add(TextFormatting.YELLOW.toString() + "Require the moonlight");
		}
	}

}
