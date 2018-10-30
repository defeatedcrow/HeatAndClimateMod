package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
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
					int i = 1 + magicSuitCount(player);
					AxisAlignedBB bb = player.getEntityBoundingBox().grow(5, 1, 5);
					List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, bb);
					for (EntityLivingBase target : list) {
						if (target instanceof IMob) {
							continue;
						}
						if (target.isPotionActive(MobEffects.POISON)) {
							target.removePotionEffect(MobEffects.POISON);
						}
						if (target.isPotionActive(MobEffects.WITHER)) {
							target.removePotionEffect(MobEffects.WITHER);
						}
						if (target.isPotionActive(MobEffects.BLINDNESS)) {
							target.removePotionEffect(MobEffects.BLINDNESS);
						}
						if (target.isPotionActive(MobEffects.NAUSEA)) {
							target.removePotionEffect(MobEffects.NAUSEA);
						}
						if (target.isPotionActive(MobEffects.HUNGER)) {
							target.removePotionEffect(MobEffects.HUNGER);
						}
						if (target.isPotionActive(MobEffects.LEVITATION)) {
							target.removePotionEffect(MobEffects.LEVITATION);
						}
						if (target.isPotionActive(MobEffects.MINING_FATIGUE)) {
							target.removePotionEffect(MobEffects.MINING_FATIGUE);
						}
						if (target.isPotionActive(MobEffects.SLOWNESS)) {
							target.removePotionEffect(MobEffects.SLOWNESS);
						}
						if (target.isPotionActive(MobEffects.UNLUCK)) {
							target.removePotionEffect(MobEffects.UNLUCK);
						}
						if (target.isPotionActive(MobEffects.WEAKNESS)) {
							target.removePotionEffect(MobEffects.WEAKNESS);
						}
						target.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600 * i, i));
					}
				}

				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 0.65F, 1.0F / (itemRand
						.nextFloat() * 0.4F + 1.2F) + 0.5F);

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
			tooltip.add(I18n.format("dcs.tip.mace2") + " " + I18n.format("dcs.tip.mace.req.moon"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.tip.mace.moon"));
		}
	}

}
