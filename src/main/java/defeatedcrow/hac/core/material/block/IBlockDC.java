package defeatedcrow.hac.core.material.block;

import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public interface IBlockDC {

	/* Json生成用 */
	public ItemStack getMainDrop();

	/* 通常処理をキャンセルする */
	public ItemStack getSilkyDrop();

	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity);

	/* 通常処理をキャンセルする */
	public ToolType getToolType();

	public int getToolTier();

	public static enum ToolType {
		AXE,
		PICKAXE,
		HOE,
		SHOVEL,
		NONE;
	}

}
