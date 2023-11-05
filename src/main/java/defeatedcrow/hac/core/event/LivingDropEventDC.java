package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.material.item.tool.HarpoonItem;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDropEventDC {

	@SubscribeEvent
	public static void onDrop(LivingDropsEvent event) {
		if (event.getEntity() != null && event.getSource().getEntity() != null) {
			LivingEntity target = event.getEntity();
			int f = event.getLootingLevel() * 10;
			if (target instanceof Cow || target instanceof Pig || target instanceof Sheep || target instanceof Goat) {
				if (target.getLevel().getRandom().nextInt(100) < 30 + f) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.FOOD_OFFAL.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Cow) {
				if (target.getLevel().getRandom().nextInt(100) < 30 + f) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.BONE_COW.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Pig) {
				if (target.getLevel().getRandom().nextInt(100) < 30 + f) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.BONE_PIG.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Pig) {
				if (target.getLevel().getRandom().nextInt(100) < 30 + f) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.SKIN_PIG.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Chicken) {
				if (target.getLevel().getRandom().nextInt(100) < 30 + f) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.BONE_CHICKEN.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Squid) {
				if (target.getLevel().getRandom().nextInt(100) < 50 + f) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.FOOD_SQUID.get()));
					event.getDrops().add(drop);
				}
			}
			if (target instanceof Frog) {
				if (target.getLevel().getRandom().nextInt(100) < 50 + f) {
					ItemEntity drop = new ItemEntity(target.getLevel(), target.position().x(), target.position().y() + 0.15D, target.position().z(), new ItemStack(FoodInit.FOOD_FROG.get()));
					event.getDrops().add(drop);
				}
			}
			if (event.getSource().getEntity() instanceof LivingEntity owner) {
				ItemStack held = owner.getItemBySlot(EquipmentSlot.MAINHAND);
				if (!held.isEmpty() && held.getItem() instanceof HarpoonItem) {
					event.getDrops().forEach(d -> d.setPos(owner.getX(), owner.getY() + 0.15D, owner.getZ()));
				}

				int count = DCItemUtil.hasCharmItem(owner, new ItemStack(MagicInit.RING_GOLD_BLACK.get()));
				if (count > 0) {
					int r = count + 1;
					int s = (count / 3) + 1;
					event.getDrops().stream().forEach((d) -> {
						if (target.getLevel().getRandom().nextInt(r) > 0 && d.getItem().getCount() < d.getItem().getMaxStackSize())
							d.getItem().grow(s);
					});

				}
			}
		}
	}

}
