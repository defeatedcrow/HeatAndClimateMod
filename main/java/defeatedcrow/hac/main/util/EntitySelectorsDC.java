package defeatedcrow.hac.main.util;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;

public class EntitySelectorsDC {

	public static final Predicate<Entity> NOT_TAMED = new Predicate<Entity>() {
		@Override
		public boolean apply(@Nullable Entity e) {
			if (e instanceof EntityPlayer || e instanceof EntityWaterMob)
				return false;
			return !(e instanceof EntityTameable) || !((EntityTameable) e).isTamed();
		}
	};

	public static final Predicate<Entity> MOB = new Predicate<Entity>() {
		@Override
		public boolean apply(@Nullable Entity e) {
			if (e == null || !e.isEntityAlive())
				return false;
			return e instanceof IMob;
		}
	};

	public static final Predicate<Entity> NOT_NAMED = new Predicate<Entity>() {
		@Override
		public boolean apply(@Nullable Entity e) {
			if (e == null)
				return false;
			return !e.hasCustomName();
		}
	};
}
