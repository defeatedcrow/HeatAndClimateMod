package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class RodGreen extends MagicJewelBase {

	public RodGreen() {
		super("rod_green_white", MagicColor.GREEN_WHITE, Rarity.EPIC, TagDC.ItemTag.MAGIC_STUFF);
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
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult res = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (res.getType() == HitResult.Type.BLOCK) {
			return onBlockHit(level, player, hand, itemstack, (BlockHitResult) res);
		} else {
			return InteractionResultHolder.pass(itemstack);
		}
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack charm, BlockHitResult res) {
		if (!DCUtil.isEmpty(charm) && level instanceof ServerLevel serverLevel) {
			BlockPos p1 = res.getBlockPos().relative(res.getDirection());
			BlockState target = level.getBlockState(res.getBlockPos());
			if (!level.isOutsideBuildHeight(p1) && level.getBlockState(p1).getMaterial().isReplaceable()) {
				BlockPlaceContext cont = new BlockPlaceContext(level, player, hand, new ItemStack(MagicInit.SCAFFOLDING.get()), res);
				BlockState light = MagicInit.SCAFFOLDING.get().getStateForPlacement(cont);
				level.setBlock(p1, light, 3);
				MsgEffectToC.sendToClient(serverLevel, p1, 40);
			}
		}
		return InteractionResultHolder.success(charm);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.rod.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);

		if (flag) {
			MutableComponent itemTip = Component.translatable("dcs.tip.rod.desc." + getColor().toString());
			list.add(itemTip);

			if (ConfigCommonBuilder.INSTANCE.enFlavorText.get()) {
				MutableComponent itemTip2 = Component.translatable("dcs.tip.rod.flavor." + getColor().toString());
				itemTip2.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY);
				list.add(itemTip2);
			}
		}
	}

}
