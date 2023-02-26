package defeatedcrow.hac.food.event;

import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDropEventDC {

	@SubscribeEvent
	public static void onDrop(LivingDropsEvent event) {
		if (event.getEntity() != null && event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player) {
			LivingEntity target = event.getEntity();
			if (target instanceof Cow || target instanceof Pig || target instanceof Sheep || target instanceof Goat) {
				if (target.getLevel().getRandom().nextInt(100) < 10) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.FOOD_OFFAL.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Squid) {
				if (target.getLevel().getRandom().nextInt(100) < 50) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.FOOD_SQUID.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Frog) {
				if (target.getLevel().getRandom().nextInt(100) < 50) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.FOOD_SQUID.get()));
					event.getDrops().add(drop);
				}
			}
		}
	}

}
