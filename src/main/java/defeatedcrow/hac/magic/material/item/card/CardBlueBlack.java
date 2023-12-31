package defeatedcrow.hac.magic.material.item.card;

import java.util.List;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraftforge.common.Tags;

public class CardBlueBlack extends MagicCardBase {

	public CardBlueBlack() {
		super(MagicColor.BLUE_BLACK, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		if (level instanceof ServerLevel sl) {
			List<? extends Mob> list = sl.getEntities(EntityTypeTest.forClass(Mob.class), EntitySelector.ENTITY_STILL_ALIVE);
			list.stream().filter(mob -> mob instanceof Enemy && !mob.getType().is(Tags.EntityTypes.BOSSES) && !mob.getType().is(EntityTypeTags.RAIDERS)).forEach((mob) -> {
				mob.discard();
			});
		}
		return true;
	}
}
