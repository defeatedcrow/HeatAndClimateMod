package defeatedcrow.hac.api.placeable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * EntityとItemを紐付けるインターフェイス。
 */
public interface IEntityItem {

	boolean canSpawnHere(World world, BlockPos pos);

	Entity getPlacementEntity(World world, EntityPlayer player, BlockPos pos, ItemStack item);

	boolean spawnPlacementEntity(World world, Entity entity);

}
