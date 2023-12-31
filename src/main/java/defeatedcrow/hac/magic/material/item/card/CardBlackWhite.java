package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CardBlackWhite extends MagicCardBase {

	public CardBlackWhite() {
		super(MagicColor.BLACK_WHITE, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		player.addEffect(new MobEffectInstance(CoreInit.CLAIR.get(), 6000, Mth.floor(f)));
		return true;
	}

}
