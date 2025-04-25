package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.material.block.InventoryItemDC;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicUtil;
import defeatedcrow.hac.magic.client.gui.BlackRodMenu;
import defeatedcrow.hac.magic.material.item.card.MagicCardBase;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.network.NetworkHooks;

public class RodBlack extends MagicJewelBase implements MenuProvider {

	public RodBlack() {
		super("rod_black_white", MagicColor.BLACK_WHITE, Rarity.EPIC, TagDC.ItemTag.MAGIC_STUFF);
	}

	@Override
	public CharmType getCharmType() {
		return CharmType.SPECIAL;
	}

	@Override
	public MagicType getMagicType() {
		return MagicType.MAINHAND;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (player == null || !player.isAlive()) {
			return InteractionResultHolder.fail(ItemStack.EMPTY);
		}
		ItemStack itemstack = player.getItemInHand(hand);
		if (player != null && player.isCrouching() && hand == InteractionHand.MAIN_HAND) {
			player.startUsingItem(hand);
			if (level.isClientSide) {
				return InteractionResultHolder.success(itemstack);
			} else {
				if (itemstack.getItem() instanceof RodBlack document && player instanceof ServerPlayer) {
					NetworkHooks.openScreen((ServerPlayer) player, document, player.blockPosition());
				}
				return InteractionResultHolder.success(itemstack);
			}
		} else {
			switchSelectNum(itemstack, false);
			return InteractionResultHolder.success(itemstack);
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		return true;
	}

	public InteractionResultHolder<ItemStack> onBlockHit(ServerLevel level, Player player, InteractionHand hand, ItemStack itemstack, BlockPos pos, Direction dir) {
		player.startUsingItem(hand);
		if (!level.isClientSide && isActive(player, itemstack)) {
			int num = getSelectNum(itemstack);
			NonNullList<ItemStack> inv = getItemData(itemstack);
			ItemStack cardItem = inv.get(num);
			float boost = MagicUtil.getMagicBooster(player) + 1.0F;

			if (!DCUtil.isEmpty(cardItem) && cardItem.getItem() instanceof MagicCardBase card) {
				if (card.onUsing(level, player, pos, dir, cardItem, boost)) {
					MsgEffectToC.sendToClient(level, pos, 31);
					if (!player.getAbilities().instabuild) {
						consumeCardItem(inv, itemstack, num);
						this.onConsumeResource(player, itemstack);
						player.getInventory().setChanged();
					}
					player.awardStat(Stats.ITEM_USED.get(this));
					player.swing(hand, true);
				}
			}
			return InteractionResultHolder.success(itemstack);
		}
		return InteractionResultHolder.success(itemstack);
	}

	public InteractionResultHolder<ItemStack> onEmptyHit(ServerLevel level, Player player, InteractionHand hand, ItemStack itemstack) {
		player.startUsingItem(hand);
		if (!level.isClientSide && isActive(player, itemstack)) {
			int num = getSelectNum(itemstack);
			NonNullList<ItemStack> inv = getItemData(itemstack);
			ItemStack cardItem = inv.get(num);
			float boost = MagicUtil.getMagicBooster(player) + 1.0F;

			if (!DCUtil.isEmpty(cardItem) && cardItem.getItem() instanceof MagicCardBase card) {
				if (card.onUsing(level, player, player.blockPosition(), player.getDirection(), cardItem, boost)) {
					MsgEffectToC.sendToClient(level, player.position().add(0D, 1.0D, 0D), 31);
					if (!player.getAbilities().instabuild) {
						consumeCardItem(inv, itemstack, num);
						this.onConsumeResource(player, itemstack);
						player.getInventory().setChanged();
					}
					player.awardStat(Stats.ITEM_USED.get(this));
					player.swing(hand, true);
				}
			}
			return InteractionResultHolder.success(itemstack);
		}
		return InteractionResultHolder.success(itemstack);
	}

	// menu

	@Override
	public AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
		ItemStack held = player.getMainHandItem();
		return BlackRodMenu.getMenu(i, held, player);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("dcs.container.black_rod");
	}

	public static int getSelectNum(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTag()) {
			CompoundTag tag = item.getTag();
			if (tag.contains(TagKeyDC.COUNTER)) {
				int num = tag.getInt(TagKeyDC.COUNTER);
				return num;
			}
		}
		return 0;
	}

	public static ItemStack switchSelectNum(ItemStack item, boolean rev) {
		if (!DCUtil.isEmpty(item)) {
			CompoundTag tag = item.getOrCreateTag();
			if (tag.contains(TagKeyDC.COUNTER)) {
				int num = tag.getInt(TagKeyDC.COUNTER);
				if (rev) {
					num--;
				} else {
					num++;
					if (num > 4)
						num = 0;
				}
				num = Mth.clamp(num, 0, 4);
				tag.putInt(TagKeyDC.COUNTER, num);
				item.setTag(tag);
				return item;
			} else {
				tag.putInt(TagKeyDC.COUNTER, 1);
				item.setTag(tag);
				return item;
			}
		}
		return item;
	}

	public static NonNullList<ItemStack> getItemData(ItemStack item) {
		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
		if (!DCUtil.isEmpty(item) && item.getItem() instanceof RodBlack) {
			CompoundTag tag = item.getOrCreateTag();
			ContainerHelper.loadAllItems(tag, inv);
		}
		return inv;
	}

	public static void consumeCardItem(NonNullList<ItemStack> inv, ItemStack rod, int num) {
		if (num < 0 || num > 4) {
			num = 0;
		}
		if (!DCUtil.isEmpty(rod) && rod.getItem() instanceof RodBlack) {
			CompoundTag tag = rod.getOrCreateTag();
			ItemStack item = inv.get(num);
			if (!DCUtil.isEmpty(item) && item.getCount() > 1) {
				item.shrink(1);
				inv.set(num, item);
			} else {
				inv.set(num, ItemStack.EMPTY);
			}
			ContainerHelper.saveAllItems(tag, inv);
			rod.setTag(tag);
		}
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
			int i = this.getMagicCostEXP(item);
			MutableComponent cost = Component.literal("COST: " + i + "Xp");
			list.add(cost);
		}
		MutableComponent itemName = Component.translatable("dcs.tip.rod.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (flag) {
			MutableComponent itemTip = Component.translatable("dcs.tip.rod.desc." + getColor().toString());
			list.add(itemTip);
			MutableComponent itemTip3 = Component.translatable("dcs.tip.rod.desc." + getColor().toString() + "_2");
			list.add(itemTip3);
			MutableComponent itemTip4 = Component.translatable("dcs.tip.rod.desc." + getColor().toString() + "_3");
			list.add(itemTip4);

			if (ConfigCommonBuilder.INSTANCE.enFlavorText.get()) {
				MutableComponent itemTip2 = Component.translatable("dcs.tip.rod.flavor." + getColor().toString());
				itemTip2.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY);
				list.add(itemTip2);
			}
		}
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag tag) {
		return new InventoryItemDC(stack);
	}

}
