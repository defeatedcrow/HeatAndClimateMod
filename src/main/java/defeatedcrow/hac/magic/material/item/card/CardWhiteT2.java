package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CardWhiteT2 extends MagicCardBase {

	public CardWhiteT2() {
		super(MagicColor.WHITE, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, ItemStack card) {
		if (!level.isClientSide()) {
			player.addEffect(new MobEffectInstance(CoreInit.BIRD.get(), 12000, 0));
		}
		return true;
	}

}
