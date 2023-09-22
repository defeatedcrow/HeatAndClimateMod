package defeatedcrow.hac.machine.material.fluid;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class DCFluidUtil {

	/* head */
	public static int getHead(FluidStack f) {
		if (f.hasTag() && f.getTag().contains(TagKeyDC.HEAD)) {
			int h = f.getTag().getInt(TagKeyDC.HEAD);
			return h > 0 ? h : 0;
		} else
			return 0;
	}

	public static FluidStack setHead(FluidStack f, int h) {
		CompoundTag tag = f.getOrCreateTag();
		tag.putInt(TagKeyDC.HEAD, h);
		if (!f.isEmpty())
			f.setTag(tag);
		return f;
	}

	public static FluidStack addHead(FluidStack f, int h) {
		if (f.isEmpty())
			return f;
		if (f.hasTag() && f.getTag().contains(TagKeyDC.HEAD)) {
			CompoundTag tag = f.getTag();
			int i = tag.getInt(TagKeyDC.HEAD) + h;
			if (i > 0) {
				tag.putInt(TagKeyDC.HEAD, i);
			} else {
				tag.remove(TagKeyDC.HEAD);
			}
			f.setTag(tag);
		} else if (h > 0) {
			CompoundTag tag = f.getOrCreateTag();
			tag.putInt(TagKeyDC.HEAD, h);
			f.setTag(tag);
		}
		return f;
	}

	public static FluidStack removeHead(FluidStack f) {
		if (f.hasTag() && f.getTag().contains(TagKeyDC.HEAD)) {
			CompoundTag tag = f.getTag();
			tag.remove(TagKeyDC.HEAD);
			f.setTag(tag);
		}
		return f;
	}

	public static int combineHead(FluidStack f1, FluidStack f2) {
		return Math.max(getHead(f1), getHead(f2));
	}

	public static boolean isSameFluid(FluidStack f1, FluidStack f2) {
		FluidStack check1 = removeHead(f1.copy());
		FluidStack check2 = removeHead(f2.copy());
		return check2.isFluidEqual(check1);
	}

	public static @Nullable CompoundTag combineTag(FluidStack f1, FluidStack f2) {
		if (isSameFluid(f1, f2)) {
			if (!f1.hasTag() && !f2.hasTag()) {
				return null;
			} else {
				CompoundTag t1 = f1.getTag();
				CompoundTag t2 = f2.getTag();
				if (t1 != null) {
					return t1;
				} else {
					return t2;
				}
			}
		}
		return null;
	}

	public static int getDirectionHead(Direction dir) {
		return -dir.getStepY();
	}

	public static boolean exchangeFluid(Level level, Vec3 pos, DCTank tank, ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			ItemStack copy = item.copy();
			copy.setCount(1);
			return !DCUtil.isEmpty(item) && FluidUtil.getFluidHandler(copy)
				.map(handler -> {
					FluidStack fluid = handler.getFluidInTank(0);
					if (fluid.isEmpty()) {
						int space = Math.min(tank.getFluidAmount(), handler.getTankCapacity(0));
						FluidStack drain = tank.drain(space, FluidAction.SIMULATE);
						int d = handler.fill(drain, FluidAction.EXECUTE);
						if (d > 0) {
							tank.drain(d, FluidAction.EXECUTE);
							ItemStack ret = handler.getContainer();
							item.shrink(1);
							if (!ret.isEmpty()) {
								ret.setCount(1);
								ItemEntity drop = new ItemEntity(level, pos.x, pos.y, pos.z, ret);
								level.addFreshEntity(drop);
							}
							return true;
						}
					} else if (handler.isFluidValid(tank.getSpace(), fluid)) {
						FluidStack drain = handler.drain(fluid, FluidAction.EXECUTE);
						int f = tank.fill(drain, FluidAction.SIMULATE);
						if (f > 0) {
							tank.fill(drain, FluidAction.EXECUTE);
							ItemStack ret = handler.getContainer().copy();
							item.shrink(1);
							if (!ret.isEmpty()) {
								ret.setCount(1);
								ItemEntity drop = new ItemEntity(level, pos.x, pos.y, pos.z, ret);
								level.addFreshEntity(drop);
							}
							return true;
						}
					}
					return false;
				}).orElse(false);
		}
		return false;
	}

}
