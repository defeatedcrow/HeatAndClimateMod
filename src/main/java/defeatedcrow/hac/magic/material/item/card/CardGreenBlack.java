package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;

public class CardGreenBlack extends MagicCardBase {

	public CardGreenBlack() {
		super(MagicColor.GREEN_BLACK, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		BlockPos p1 = player.blockPosition();
		boolean onGround = true;
		if (level.canSeeSky(player.blockPosition().above()))
			onGround = true;
		else {
			for (int i = 1; i < 64 && i + p1.getY() < level.getMaxBuildHeight(); i++) {
				BlockState state = level.getBlockState(p1.above(i));
				if (state.getMaterial().isSolidBlocking() || state.getMaterial().getPushReaction() == PushReaction.BLOCK) {
					onGround = false;
					break;
				}
			}
		}
		// DCLogger.debugInfoLog("test 1: onGround " + onGround);
		if (!onGround) {
			boolean isDangerBlock = false;
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			// 上から
			for (int i = level.getMaxBuildHeight() - 1; i > p1.getY(); i--) {
				mpos.set(p1.getX(), i, p1.getZ());
				BlockState state = level.getBlockState(mpos);
				if (!state.getMaterial().isReplaceable() || state.getMaterial().isLiquid()) {
					if (state.getMaterial() == Material.LAVA || state.getMaterial() == Material.CACTUS) {
						isDangerBlock = true;
						break;
					} else if (level.getBlockState(mpos.above()).getMaterial().isReplaceable() && level.getBlockState(mpos.above(2)).getMaterial().isReplaceable()) {
						if (level.getBlockState(mpos.above()).getMaterial() == Material.POWDER_SNOW || state.getMaterial() == Material.CACTUS || state.getMaterial() == Material.FIRE) {
							isDangerBlock = true;
						}
						break;
					} else {
						continue;
					}
				}
			}
			// DCLogger.debugInfoLog("test 2-1: y " + mpos.getY());
			if (isDangerBlock) {
				player.displayClientMessage(Component.translatable("dcs.tip.magic_card.danger_alart").withStyle(ChatFormatting.RED), true);
				return false;
			} else {
				Vec3 vec3 = Vec3.atBottomCenterOf(mpos.above());
				player.teleportToWithTicket(vec3.x, vec3.y + 0.5D, vec3.z);
				player.resetFallDistance();
				return true;
			}
		} else {
			boolean isDangerBlock = false;
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			// 下から
			for (int i = level.getMinBuildHeight() + 1; i < p1.getY(); i++) {
				mpos.set(p1.getX(), i, p1.getZ());
				BlockState state = level.getBlockState(mpos);
				if (state.getMaterial().isReplaceable() && level.getBlockState(mpos.above()).getMaterial().isReplaceable()) {
					if (state.getMaterial() == Material.POWDER_SNOW || state.getMaterial() == Material.CACTUS || state.getMaterial() == Material.FIRE) {
						isDangerBlock = true;
					} else if (level.getBlockState(mpos.below()).getMaterial() == Material.LAVA || level.getBlockState(mpos.below()).getMaterial() == Material.CACTUS) {
						isDangerBlock = true;
					}
					break;
				}
			}
			// DCLogger.debugInfoLog("test 2-2: y " + mpos.getY());
			if (isDangerBlock) {
				player.displayClientMessage(Component.translatable("dcs.tip.magic_card.danger_alart").withStyle(ChatFormatting.RED), true);
				return false;
			} else {
				Vec3 vec3 = Vec3.atBottomCenterOf(mpos);
				player.teleportToWithTicket(vec3.x, vec3.y + 0.5D, vec3.z);
				player.resetFallDistance();
				return true;
			}
		}
	}
}
