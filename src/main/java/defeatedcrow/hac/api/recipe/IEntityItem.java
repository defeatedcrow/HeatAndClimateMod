package defeatedcrow.hac.api.recipe;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * EntityとItemを紐付けるインターフェイス。
 */
public interface IEntityItem {

	boolean canSpawnHere(Level world, BlockPos pos);

	boolean spawnPlacementEntity(Level world, Entity entity);

	Entity getPlacementEntity(Level world, Player player, double x, double y, double z, ItemStack item);

}
