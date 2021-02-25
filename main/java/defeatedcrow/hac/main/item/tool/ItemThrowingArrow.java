package defeatedcrow.hac.main.item.tool;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.entity.EntityThrowingArrow;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemThrowingArrow extends DCItem implements ITexturePath {

	public ItemThrowingArrow() {
		super();
		this.maxStackSize = 16;
		this.setFull3D();
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	private static String[] names = { "normal" };

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/misc/cartridge_arrow";
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (slot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Weapon modifier", 2.0D, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Weapon modifier", -1.8D, 0));
		}

		return multimap;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
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
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			boolean flag = player.capabilities.isCreativeMode;

			if (!DCUtil.isEmpty(stack)) {

				if (!world.isRemote) {
					EntityThrowingArrow entityarrow = new EntityThrowingArrow(world, player);
					entityarrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);

					PotionEffect eff = player.getActivePotionEffect(MobEffects.STRENGTH);

					if (eff != null) {
						float d = 6.0F + 3.0F * eff.getAmplifier();

						entityarrow.setDamage(d);
					}

					stack.damageItem(1, player);

					world.spawnEntity(entityarrow);
				}

				world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand
						.nextFloat() * 0.4F + 1.2F) + 0.5F);

				if (!flag) {
					DCUtil.reduceStackSize(stack, 1);
				}

				player.addStat(StatList.getObjectUseStats(this));
			}
		}
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add("Damage: " + MainCoreConfig.gun_damage * 0.5F);
		tooltip.add(I18n.format("dcs.tip.throwing_arrow"));
	}

}
