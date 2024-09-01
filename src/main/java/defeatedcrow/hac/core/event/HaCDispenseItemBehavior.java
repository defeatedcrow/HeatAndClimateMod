package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.PositionImpl;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

public class HaCDispenseItemBehavior implements DispenseItemBehavior {

	@Override
	public final ItemStack dispense(BlockSource source, ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.getItem() instanceof ItemEntityFood food) {
			ItemStack result = this.execute(source, item, food);
			this.playSound(source);
			this.playAnimation(source, source.getBlockState().getValue(DispenserBlock.FACING));
			return result;
		}
		return item;
	}

	protected ItemStack execute(BlockSource source, ItemStack item, ItemEntityFood food) {
		Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
		Position pos = getDispensePosition(source, dir);
		double dx = pos.x();
		double dy = pos.y();
		double dz = pos.z();
		if (food.canSpawnHere(source.getLevel(), source.getPos().relative(dir)) && !source.getLevel().isClientSide) {
			food.spawnPlacementEntity(source.getLevel(), (Player) null, new Vec3(dx, dy, dz), item);
		}
		return item;
	}

	static Position getDispensePosition(BlockSource source, Direction dir) {
		double d0 = source.x() + 1.0D * dir.getStepX();
		double d1 = source.y() + 1.0D * dir.getStepY();
		double d2 = source.z() + 1.0D * dir.getStepZ();
		return new PositionImpl(d0, d1, d2);
	}

	protected void playSound(BlockSource source) {
		source.getLevel().levelEvent(1000, source.getPos(), 0);
	}

	protected void playAnimation(BlockSource source, Direction dir) {
		source.getLevel().levelEvent(2000, source.getPos(), dir.get3DDataValue());
	}

}
