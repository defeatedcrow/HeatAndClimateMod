package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class GoldRing extends MagicJewelBase {

	public GoldRing(MagicColor c) {
		super("ring_g_" + c.toString(), c, Rarity.COMMON, TagDC.ItemTag.MAGIC_RING);
	}

	@Override
	public CharmType getCharmType() {
		MagicColor c = getColor();
		if (c.isBlack) {
			return CharmType.SPECIAL;
		}
		if (c.isGreen) {
			return CharmType.KEY;
		}
		return CharmType.CONSTANT;
	}

	@Override
	public void constantEffect(LivingEntity owner, ItemStack charm) {
		if (owner != null && !owner.level.isClientSide) {
			int i = DCUtil.isEmpty(charm) ? 0 : charm.getCount() - 1;
			MagicColor color = getColor();
			if (color.isWhite) {
				DCUtil.removeBadPotion(owner);
				if (owner.isOnFire()) {
					owner.clearFire();
				}
			} else if (color.isBlue) {
				owner.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 300));
			} else if (color.isRed) {
				owner.addEffect(new MobEffectInstance(CoreInit.NIMBLE.get(), 300, i));
				owner.addEffect(new MobEffectInstance(CoreInit.HEAVY.get(), 300, i));
			}
		}
	}

	@Override
	public boolean onUsing(ServerPlayer owner, ItemStack charm) {
		MagicColor c = getColor();
		if (c.isGreen) {
			DCLogger.debugInfoLog("### OreSearch system activated ###");
			BlockPos pos = owner.blockPosition();
			int y = pos.getY();
			if (y <= owner.level.getMinBuildHeight())
				return false;
			if (y > owner.level.getMaxBuildHeight())
				y = owner.level.getMaxBuildHeight();
			BlockState ret = Blocks.AIR.defaultBlockState();
			BlockPos.MutableBlockPos mp = new BlockPos.MutableBlockPos();
			while (y > owner.level.getMinBuildHeight() && ret.getBlock() == Blocks.AIR) {
				mp.set(pos.getX(), y, pos.getZ());
				BlockState ore = owner.level.getBlockState(mp);
				if (new ItemStack(ore.getBlock()).is(Tags.Items.ORES)) {
					ret = ore;
					break;
				} else {
					y--;
				}
			}
			if (ret.getBlock() != Blocks.AIR) {

				MutableComponent n = ret.getBlock().getName();
				MutableComponent mes = Component.literal("Find ");
				mes.append(n);
				mes.append(" Y=" + y);
				owner.sendSystemMessage(mes);
			} else {
				owner.sendSystemMessage(Component.literal("No ore found..."));
			}
		}
		return false;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.ring_g.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.ring_g.desc." + getColor().toString());
			list.add(itemTip);
			if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
				if (getColor().isGreen) {
					int i = this.getMagicCostEXP(item);
					MutableComponent cost = Component.literal("COST: " + i + "Xp");
					list.add(cost);
				}
			}
		}
		super.appendHoverText(item, level, list, flag);
	}

}
