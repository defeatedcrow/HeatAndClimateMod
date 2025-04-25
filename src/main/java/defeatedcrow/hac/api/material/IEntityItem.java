package defeatedcrow.hac.api.material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * EntityとItemを紐付けるインターフェイス。
 */
public interface IEntityItem {

	EntityType<?> getType();

	boolean canSpawnHere(Level world, BlockPos pos);

	@Deprecated
	boolean spawnPlacementEntity(Level world, Player player, Vec3 vec, ItemStack item);

	default boolean spawnPlacementEntity(Level world, Player player, Vec3 vec, Direction targetDir, ItemStack item) {
		return spawnPlacementEntity(world, player, vec, item);
	}

	EntityRenderData getRenderData(Item item);

}
