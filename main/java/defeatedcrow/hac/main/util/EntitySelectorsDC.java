package defeatedcrow.hac.main.util;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
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
}
