package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.item.BoringSurveyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CardGreenWhite extends MagicCardBase {

	public CardGreenWhite() {
		super(MagicColor.GREEN_WHITE, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		int[] blocks = new int[30];
		for (int i = 0; i < 30; i++) {
			BlockPos p = player.blockPosition().below(i + 1);
			if (p.getY() > level.getMinBuildHeight()) {
				BlockState below = level.getBlockState(p);
				int id = BoringSurveyItem.getBlockTypeId(below);
				blocks[i] = id;
			}
		}
		ItemStack document = new ItemStack(MagicInit.DOCUMENT_BORING.get());
		BoringSurveyItem.setBlockData(document, blocks);
		BoringSurveyItem.setPlayerData(document, player);
		Block.popResource(level, player.blockPosition().above(), document);

		return true;
	}
}
