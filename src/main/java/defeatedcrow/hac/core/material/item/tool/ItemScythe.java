package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.api.material.ITierItem;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.TierDC;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.IForgeShearable;

public class ItemScythe extends ItemDC implements ITierItem, Vanishable {

	public final TierDC tier;
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public ItemScythe(TierDC tierIn, TagKey<Item> pair) {
		super(new Item.Properties().durability(tierIn.getUses()).tab(CoreInit.MACHINE), pair);
		tier = tierIn;
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", tier.getAttackDamageBonus() + 3.0D, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -3.0D, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}

	@Override
	@NotNull
	public AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
		return target.getBoundingBox().inflate(2.0D, 0.25D, 2.0D);
	}

	@Override
	public String getRegistryName() {
		return "main/scythe_" + tier.getTierName();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/handheld", ImmutableMap.of("layer0", "dcs_climate:item/tool/scythe_" + tier.getTierName()));
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity liv) {
		if (!level.isClientSide && !state.is(BlockTags.FIRE)) {
			stack.hurtAndBreak(1, liv, (entity) -> {
				entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			});
		}
		return state.is(TagDC.BlockTag.SCYTHE_BREAKABLE) || state.getBlock() instanceof IForgeShearable ? true : super.mineBlock(stack, level, state, pos, liv);
	}

	@Override
	public boolean isCorrectToolForDrops(BlockState state) {
		return state.is(TagDC.BlockTag.SCYTHE_BREAKABLE);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (state.is(TagDC.BlockTag.SCYTHE_BREAKABLE) || state.getBlock() instanceof IForgeShearable) {
			if (state.is(Blocks.COBWEB) || state.is(BlockTags.LEAVES)) {
				return 15.0F;
			} else {
				return 10.0F;
			}
		}
		return super.getDestroySpeed(stack, state);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand) {
		if (entity instanceof IForgeShearable target) {
			if (!playerIn.level.isClientSide) {
				BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());
				// 範囲毛刈り
				AABB aabb = new AABB(pos).inflate(tier.getLevel());
				List<LivingEntity> list = entity.getLevel().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, entity, aabb);
				boolean consume = false;
				ItemStack dummy = new ItemStack(Items.SHEARS);
				for (LivingEntity liv : list) {
					if (liv instanceof IForgeShearable target2)
						if (target2.isShearable(dummy, playerIn.level, pos)) {
							List<ItemStack> drops = target2.onSheared(playerIn, dummy, entity.getLevel(), pos,
								EnchantmentHelper.getTagEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack));
							RandomSource rand = playerIn.level.random;
							drops.forEach(d -> {
								ItemEntity ent = liv.spawnAtLocation(d, 1.0F);
								ent.setDeltaMovement(ent.getDeltaMovement().add((rand.nextFloat() - rand.nextFloat()) * 0.1F, rand.nextFloat() * 0.05F, (rand.nextFloat() - rand.nextFloat()) * 0.1F));
							});
							consume = true;
						}
				}
				if (consume) {
					stack.hurtAndBreak(1, playerIn, e -> e.broadcastBreakEvent(hand));
				}
			}
			return net.minecraft.world.InteractionResult.sidedSuccess(playerIn.level.isClientSide);
		}
		return net.minecraft.world.InteractionResult.PASS;
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
		return net.minecraftforge.common.ToolActions.DEFAULT_SHEARS_ACTIONS.contains(toolAction);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
	}

	public float getAttackDamage() {
		return 3.0F;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tasteName = Component.translatable("dcs.tip.scythe");
		tasteName.withStyle(ChatFormatting.YELLOW);
		list.add(tasteName);
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enc) {
		if (enc == Enchantments.SWEEPING_EDGE)
			return false;
		return enc.category == EnchantmentCategory.WEAPON;
	}

	@Override
	public Tier getTier() {
		return tier;
	}

}
