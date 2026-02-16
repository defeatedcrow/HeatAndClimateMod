package defeatedcrow.hac.api.material;

import java.util.Optional;

import defeatedcrow.hac.api.magic.MagicColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

/* 魔法の色素で設置後にカラー変更できるBlock */
public interface IColordBlock {

	MagicColor getAvaiableColor(ItemStack item);

	Optional<Block> getReplaceBlock(MagicColor color);

	void replace(Level level, BlockPos pos, Player player, ItemStack held, MagicColor color);

}
