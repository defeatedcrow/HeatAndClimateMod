package defeatedcrow.hac.api.material;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IRapidCollectables {

	TagKey<Item> collectableToolTag();

	default boolean isCollectable(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return false;
		return item.is(collectableToolTag());
	}

	default boolean canCollect(Level level, BlockPos pos, BlockState state) {
		return true;
	}

	List<ItemStack> getDropList(Level level, BlockPos pos, BlockState state, ItemStack tool);

	boolean doCollect(Level level, BlockPos pos, BlockState state, @Nullable Player player, ItemStack tool);

}
