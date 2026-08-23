package defeatedcrow.hac.core.event;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.item.tool.HarpoonItem;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.magic.MagicUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.MagicPictureEntity;
import defeatedcrow.hac.magic.material.entity.OwnableMagicEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingEventDC {

	@SubscribeEvent
	public static void onDrop(LivingDropsEvent event) {
		if (event.getEntity() != null && event.getSource()
		    .getEntity() != null) {
			LivingEntity target = event.getEntity();
			int f = event.getLootingLevel() * 10;
			List<ItemStack> list = animalDropItem(target);
			RandomSource rand = target.getLevel()
			    .getRandom();
			if (!list.isEmpty()) {
				for (ItemStack item : list) {
					if (rand.nextInt(100) < 50 + f) {
						ItemEntity drop = new ItemEntity(target.getLevel(), target.getX(), target.getY() + 0.15D, target.getZ(), item);
						event.getDrops()
						    .add(drop);
					}
				}
			}

			if (event.getSource()
			    .getEntity() instanceof LivingEntity owner) {
				ItemStack held = owner.getItemBySlot(EquipmentSlot.MAINHAND);
				if (!held.isEmpty() && held.getItem() instanceof HarpoonItem) {
					event.getDrops()
					    .forEach(d -> d.setPos(owner.getX(), owner.getY() + 0.15D, owner.getZ()));
				}

				int count = MagicUtil.hasCharmItem(owner, new ItemStack(MagicInit.RING_GOLD_BLACK.get()));
				if (count > 0) {
					int r = count + 1;
					int s = Mth.floor(count / 2F) + 1;
					event.getDrops()
					    .stream()
					    .forEach(d -> {
						    if (rand.nextInt(r) > 0 && d.getItem()
						        .getCount() < d.getItem()
						            .getMaxStackSize())
							    d.getItem()
							        .grow(s);
					    });

				}
			}
		}
	}

	private static List<ItemStack> animalDropItem(LivingEntity target) {
		List<ItemStack> list = Lists.newArrayList();
		if (target instanceof Cow || target instanceof Pig || target instanceof Sheep || target instanceof Goat || target instanceof Horse) {
			list.add(new ItemStack(FoodInit.FOOD_OFFAL.get()));
			list.add(new ItemStack(FoodInit.FOOD_ANIMAL_FAT.get()));
		}
		if (target instanceof Cow) {
			list.add(new ItemStack(FoodInit.BONE_COW.get()));
		}
		if (target instanceof Pig) {
			list.add(new ItemStack(FoodInit.BONE_PIG.get()));
			list.add(new ItemStack(FoodInit.SKIN_PIG.get()));
		}
		if (target instanceof Chicken) {
			list.add(new ItemStack(FoodInit.FOOD_OFFAL.get()));
			list.add(new ItemStack(FoodInit.BONE_CHICKEN.get()));
		}
		if (target instanceof Squid) {
			list.add(new ItemStack(FoodInit.FOOD_SQUID.get()));
		}
		if (target instanceof Frog) {
			list.add(new ItemStack(FoodInit.FOOD_FROG.get()));
		}
		return list;
	}

	@SubscribeEvent
	public static void onSpawnCheck(LivingSpawnEvent.CheckSpawn event) {
		LivingEntity entity = event.getEntity();
		if (event.getLevel() instanceof ServerLevel && entity instanceof Enemy && !entity.getType()
		    .is(TagDC.EntityTag.SPAWN_SUPPRESSOR_BLACKLIST)
		    && (event.getSpawnReason() == MobSpawnType.NATURAL || event.getSpawnReason() == MobSpawnType.JOCKEY || event.getSpawnReason() == MobSpawnType.MOB_SUMMONED || event.getSpawnReason() == MobSpawnType.SPAWNER
		        || event.getSpawnReason() == MobSpawnType.EVENT)) {
			boolean flag = false;
			List<MagicPictureEntity> picList = MagicPictureEvent.getList();
			if (picList.stream()
			    .anyMatch(MagicPictureEvent.checkColor(MagicColor.BLACK_WHITE))) {
				flag = true;
			} else {
				ServerLevel ls = (ServerLevel) event.getLevel();
				List<? extends OwnableMagicEntity> list = MagicUtil.getMagicEntity(ls, MagicColor.WHITE);
				for (OwnableMagicEntity target : list) {
					Vec3 vec = Vec3.atCenterOf(target.chunkPosition()
					    .getMiddleBlockPosition(target.getBlockY()));
					double d = entity.position()
					    .distanceToSqr(vec);
					if (d <= 625D) {
						flag = true;
						break;
					}
				}
			}
			if (flag) {
				event.setResult(Result.DENY);
			}
		}
	}

}
