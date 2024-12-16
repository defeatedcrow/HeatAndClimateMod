package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RodRed extends MagicJewelBase {

	protected static GameProfile DEBUG = new GameProfile(UUID.fromString("590325BA-96AD-4E3C-950A-DA23A39006C1"), "DebugDoll");

	public RodRed() {
		super("rod_red_blue", MagicColor.RED_BLUE, Rarity.EPIC, TagDC.ItemTag.MAGIC_STUFF);
	}

	@Override
	public CharmType getCharmType() {
		return CharmType.SPECIAL;
	}

	@Override
	public MagicType getType() {
		return MagicType.MAINHAND;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult res = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		if (player != null && player.isCrouching() && res.getType() == HitResult.Type.BLOCK) {
			return onBlockHit(level, player, hand, itemstack, (BlockHitResult) res);
		} else {
			return onEmptyHit(level, player, hand, itemstack);
		}
	}

	public InteractionResultHolder<ItemStack> onBlockHit(Level level, Player player, InteractionHand hand, ItemStack charm, BlockHitResult res) {
		if (!DCUtil.isEmpty(charm) && level instanceof ServerLevel serverLevel) {
			Vec3 vec3 = Vec3.atCenterOf(res.getBlockPos());
			BlockPos p1 = new BlockPos(vec3);
			if (!level.getBlockState(p1).isAir()) {
				ResourceKey<Level> dim = serverLevel.dimension();
				CompoundTag tag = charm.getOrCreateTag();
				tag.putString(TagKeyDC.DIM_LOCATION, dim.location().toString());
				tag.putInt(TagKeyDC.POS_X, p1.getX());
				tag.putInt(TagKeyDC.POS_Y, p1.getY());
				tag.putInt(TagKeyDC.POS_Z, p1.getZ());
				tag.putInt(TagKeyDC.DIRECTION, res.getDirection().get3DDataValue());
				charm.setTag(tag);

				if (player instanceof ServerPlayer sp) {
					MutableComponent mes = Component.translatable("dcs.tip.coodinate");
					MutableComponent mes2 = Component.literal(" X:" + p1.getX());
					mes2.append(Component.literal(" Y:" + p1.getY()));
					mes2.append(Component.literal(" Z:" + p1.getZ()));
					sp.sendSystemMessage(mes);
					sp.sendSystemMessage(mes2);
				}
			}
		}
		return InteractionResultHolder.success(charm);
	}

	public InteractionResultHolder<ItemStack> onEmptyHit(Level level, Player player, InteractionHand hand, ItemStack charm) {
		if (!DCUtil.isEmpty(charm) && isActive(player, charm) && level instanceof ServerLevel serverLevel) {
			if (charm.hasTag() && charm.getTag().contains(TagKeyDC.DIM_LOCATION)) {
				CompoundTag tag = charm.getTag();
				String s1 = tag.getString(TagKeyDC.DIM_LOCATION);
				int dx = tag.getInt(TagKeyDC.POS_X);
				int dy = tag.getInt(TagKeyDC.POS_Y);
				int dz = tag.getInt(TagKeyDC.POS_Z);
				int d = tag.getInt(TagKeyDC.DIRECTION);
				Direction dir = Direction.from3DDataValue(d);
				ResourceKey<Level> dim = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(s1));
				if (!serverLevel.dimension().equals(dim)) {
					MutableComponent mes = Component.translatable("dcs.tip.rod.red.error1");
					return InteractionResultHolder.success(charm);
				} else {
					Vec3 vec = new Vec3(dx, dy, dz);
					BlockPos pos = new BlockPos(vec);
					if (level.isLoaded(pos)) {
						BlockState state = level.getBlockState(pos);
						if (state != null && !state.isAir()) {
							BlockHitResult res = new BlockHitResult(vec, dir, pos, true);
							state.getBlock().use(state, level, pos, player, hand, res);
							MsgEffectToC.sendToClient(serverLevel, player.getEyePosition(), 41);
							MsgEffectToC.sendToClient(serverLevel, pos, 41);
							this.onConsumeResource(player, charm);
							return InteractionResultHolder.success(charm);
						} else {
							MutableComponent mes = Component.translatable("dcs.tip.rod.red.error3");
						}
					} else {
						MutableComponent mes = Component.translatable("dcs.tip.rod.red.error2");
					}
				}
			}
		}
		return InteractionResultHolder.success(charm);
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

			if (item.hasTag() && item.getTag().contains(TagKeyDC.DIM_LOCATION)) {
				CompoundTag tag = item.getTag();
				String s1 = tag.getString(TagKeyDC.DIM_LOCATION);
				int dx = tag.getInt(TagKeyDC.POS_X);
				int dy = tag.getInt(TagKeyDC.POS_Y);
				int dz = tag.getInt(TagKeyDC.POS_Z);
				int d = tag.getInt(TagKeyDC.DIRECTION);
				Direction dir = Direction.from3DDataValue(d);
				list.add(Component.translatable("dcs.tip.coodinate").withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("DIM: " + s1).withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("X: " + dx).withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("Y: " + dy).withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("Z: " + dz).withStyle(ChatFormatting.GRAY));
				list.add(Component.literal("Direction: " + dir).withStyle(ChatFormatting.GRAY));
			}
		}
	}

}
