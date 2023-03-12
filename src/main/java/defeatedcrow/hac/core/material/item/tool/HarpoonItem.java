package defeatedcrow.hac.core.material.item.tool;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.entity.proj.ThrownHarpoon;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.util.TierDC;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HarpoonItem extends ItemDC {

	public final TierDC tier;
	public final String name;
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public HarpoonItem(String nameIn, TierDC tierIn, TagKey<Item> pair) {
		super(new Item.Properties().durability(tierIn.getUses()).tab(CoreInit.MACHINE), pair);
		tier = tierIn;
		name = nameIn;
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", tier.getAttackDamageBonus() + 3.0D, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.3D, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}

	@Override
	public String getRegistryName() {
		return "main/harpoon_" + name;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/handheld", ImmutableMap.of("layer0", "dcs_climate:item/tool/harpoon_" + name));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int useTime) {
		if (living instanceof Player player) {
			int i = this.getUseDuration(stack) - useTime;
			if (i >= 0) {
				if (!level.isClientSide) {
					stack.hurtAndBreak(1, player, (p) -> {
						p.broadcastBreakEvent(player.getUsedItemHand());
					});
					ThrownHarpoon thrown = new ThrownHarpoon(level, player, stack);
					if (player.getAbilities().instabuild) {
						thrown.pickup = AbstractArrow.Pickup.DISALLOWED;
					}
					thrown.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
					level.addFreshEntity(thrown);

					level.playSound((Player) null, thrown, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
					if (!player.getAbilities().instabuild) {
						player.getInventory().removeItem(stack);
					}
				}
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(itemstack);
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity living, LivingEntity owner) {
		stack.hurtAndBreak(1, owner, (liv) -> {
			liv.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		});
		return true;
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity living) {
		if (state.getDestroySpeed(level, pos) != 0.0D) {
			stack.hurtAndBreak(1, living, (liv) -> {
				liv.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			});
		}

		return true;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}

	@Override
	public int getEnchantmentValue() {
		return 1;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enc) {
		if (enc == Enchantments.IMPALING)
			return true;
		if (enc == Enchantments.PIERCING)
			return true;
		return enc.category == EnchantmentCategory.WEAPON;
	}

	public static final EntityRenderData FLINT = new EntityRenderData("harpoon_flint", 1.0F, 0F);
	public static final EntityRenderData STEEL = new EntityRenderData("harpoon_steel", 1.0F, 0F);

}
