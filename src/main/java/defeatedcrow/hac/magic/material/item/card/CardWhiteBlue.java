package defeatedcrow.hac.magic.material.item.card;

import java.util.Optional;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class CardWhiteBlue extends MagicCardBase {

	public CardWhiteBlue() {
		super(MagicColor.WHITE_BLUE, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		if (level instanceof ServerLevel && player instanceof ServerPlayer playerS) {
			BlockPos spawnPos = playerS.getRespawnPosition();
			float angle = playerS.getRespawnAngle();
			ServerLevel serverlevel = playerS.server.getLevel(playerS.getRespawnDimension());
			Optional<Vec3> spawn;
			if (serverlevel != null && spawnPos != null) {
				spawn = Player.findRespawnPositionAndUseSpawnBlock(serverlevel, spawnPos, angle, false, false);
			} else {
				spawn = Optional.empty();
			}

			boolean flag = false;
			if (spawn.isPresent()) {
				BlockState state = serverlevel.getBlockState(spawnPos);
				boolean isAnchor = state.is(Blocks.RESPAWN_ANCHOR);
				Vec3 vec3 = spawn.get();
				float f1;
				if (!state.is(BlockTags.BEDS) && !isAnchor) {
					f1 = angle;
				} else {
					Vec3 vec31 = Vec3.atBottomCenterOf(spawnPos).subtract(vec3).normalize();
					f1 = (float) Mth.wrapDegrees(Mth.atan2(vec31.z, vec31.x) * (180F / (float) Math.PI) - 90.0D);
				}

				if (!serverlevel.dimension().equals(playerS.getLevel().dimension())) {
					serverlevel.getProfiler().push("portal");
					playerS.setPortalCooldown();
					playerS.changeDimension(serverlevel);
					serverlevel.getProfiler().pop();
				}
				playerS.teleportToWithTicket(vec3.x, vec3.y, vec3.z);
				playerS.resetFallDistance();
				flag = true;
			} else if (spawnPos != null) {
				if (!serverlevel.dimension().equals(playerS.getLevel().dimension())) {
					serverlevel.getProfiler().push("portal");
					playerS.setPortalCooldown();
					playerS.changeDimension(serverlevel);
					serverlevel.getProfiler().pop();
				}
				playerS.teleportToWithTicket(spawnPos.getX() + 0.5D, spawnPos.getY() + 0.5D, spawnPos.getZ() + 0.5D);
				playerS.resetFallDistance();
				flag = true;
			}

			if (flag) {
				while (!serverlevel.noCollision(playerS) && playerS.getY() < serverlevel.getMaxBuildHeight()) {
					playerS.setPos(playerS.getX(), playerS.getY() + 1.0D, playerS.getZ());
				}
			}
		}
		return true;
	}

}
