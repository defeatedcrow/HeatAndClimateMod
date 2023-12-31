package defeatedcrow.hac.magic.material.item.card;

import java.util.List;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class CardWhiteT1 extends MagicCardBase {

	public CardWhiteT1() {
		super(MagicColor.WHITE, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		float boost = 1F + (f * 0.5F);
		DCUtil.removeBadPotion(player);
		player.heal(10.0F * boost);
		List<Entity> list = level.getEntities(player, new AABB(pos.getX() - 8D, pos.getY() - 2D, pos.getZ() - 8D, pos.getX() + 8D, pos.getY() + 2D, pos.getZ() + 8D),
			EntitySelector.LIVING_ENTITY_STILL_ALIVE);
		if (list != null && !list.isEmpty()) {
			for (Entity entity : list) {
				if (entity instanceof LivingEntity living && !(entity instanceof Enemy)) {
					DCUtil.removeBadPotion(living);
					living.heal(10.0F * boost);
					level.levelEvent(1505, living.blockPosition().above(), 0);
				}
			}
		}
		return true;
	}

}
