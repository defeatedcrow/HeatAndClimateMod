package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.entity.ObjectEntityBaseDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public abstract class ItemEntityFood extends ItemFoodDC implements IEntityItem {

	final String name;

	public ItemEntityFood(String s, int nut, float sat, TagKey<Item> pair) {
		super(nut, sat, pair);
		name = s;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		Direction face = context.getClickedFace();
		Vec3 vec = context.getClickLocation();
		if (player.isCrouching() && canSpawnHere(level, pos)) {
			Vec3 place = new Vec3(vec.x + (face.getStepX() * 0.25D), vec.y + (face.getStepY() * 0.25D), vec.z + (face.getStepZ() * 0.25D));
			ItemStack item = context.getItemInHand();
			if (!level.isClientSide)
				spawnPlacementEntity(level, player, place, item);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public boolean canSpawnHere(Level level, BlockPos pos) {
		boolean b1 = level.getBlockState(pos).isFaceSturdy(level, pos, Direction.UP, SupportType.CENTER);
		boolean b2 = !level.getBlockState(pos.above()).getMaterial().blocksMotion();
		return true; // b1 && b2;
	}

	@Override
	public boolean spawnPlacementEntity(Level level, Player player, Vec3 vec, ItemStack item) {
		if (getType() == null)
			return false;
		Entity entity = getType().create(level);
		if (entity instanceof ObjectEntityBaseDC) {
			ObjectEntityBaseDC food = (ObjectEntityBaseDC) entity;
			food.setPos(vec);
			food.setDeltaMovement(0D, 0D, 0D);
			ItemStack set = item.split(1);
			food.setItem(set);
			if (player != null && player.getUUID() != null) {
				food.setYRot(player.yHeadRot);
				food.setOwner(player.getUUID());
			}
			level.addFreshEntity(food);
			level.gameEvent(food, GameEvent.ENTITY_PLACE, vec);
			return true;
		}
		return false;
	}

	@Override
	public String getRegistryName() {
		return "food/" + name;
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/food/" + name));
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent s = Component.translatable("dcs.tip.spawn_entity_food");
		list.add(s);
		super.appendHoverText(item, level, list, flag);
	}
}
