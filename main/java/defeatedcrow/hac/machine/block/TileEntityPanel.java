package defeatedcrow.hac.machine.block;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCTileEntity;
import defeatedcrow.hac.main.util.EntitySelectorsDC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityPanel extends DCTileEntity {

	@Override
	protected int getMaxCool() {
		return 5;
	}

	@Override
	protected void onServerUpdate() {
		super.updateTile();
		if (!world.isRemote) {
			double x1 = getPos().getX() - 3D;
			double x2 = getPos().getX() + 4D;
			double y1 = getPos().getY() - 3D;
			double y2 = getPos().getY() + 4D;
			double z1 = getPos().getZ() - 3D;
			double z2 = getPos().getZ() + 4D;
			AxisAlignedBB aabb = new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
			IBlockState state = world.getBlockState(getPos());
			int type = DCState.getInt(state, DCState.TYPE4);
			boolean b = DCState.getBool(state, DCState.POWERED);
			List<Entity> list = Lists.newArrayList();
			boolean empty = true;
			if (type == 0) {
				list.addAll(world.getEntitiesWithinAABB(EntityPlayer.class, aabb, EntitySelectors.NOT_SPECTATING));
				empty = list.isEmpty();
			} else if (type == 1) {
				list.addAll(world.getEntitiesWithinAABB(EntityLivingBase.class, aabb, EntitySelectorsDC.MOB));
				empty = list.isEmpty();
			} else if (type == 2) {
				list.addAll(world.getEntitiesWithinAABB(EntityAnimal.class, aabb, EntitySelectors.IS_ALIVE));
				list.addAll(world.getEntitiesWithinAABB(EntityTameable.class, aabb, EntitySelectors.IS_ALIVE));
				empty = list.isEmpty();
			} else {
				list.addAll(world.getEntitiesWithinAABB(EntityItem.class, aabb, EntitySelectors.IS_ALIVE));
				empty = list.isEmpty();

				if (empty) {
					BlockPos min = getPos().add(-2, -2, -2);
					BlockPos max = getPos().add(2, 2, 2);
					Iterable<BlockPos> itr = getPos().getAllInBox(min, max);

					for (BlockPos p : itr) {
						if (p.getY() < 0 || p.getY() > 255 || p.equals(pos)) {
							continue;
						}
						if (world.getTileEntity(p) instanceof TileConveyor) {
							TileConveyor con = (TileConveyor) world.getTileEntity(p);
							if (!con.inv.getStackInSlot(0).isEmpty() || !con.inv.getStackInSlot(1).isEmpty()) {
								empty = false;
							}
						}
					}
				}
			}

			if (b == empty) {
				BlockEntityPanel.changePowerState(getWorld(), getPos(), !b);
			}
		}
	}

}
