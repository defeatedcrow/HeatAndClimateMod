package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.entity.CanoeEntity;
import defeatedcrow.hac.core.material.item.ItemDC;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class CanoeItem extends ItemDC {

	public CanoeItem() {
		super(new Item.Properties().stacksTo(1), ItemTags.BOATS);
		defeatedcrow.hac.core.material.tabs.CreativeTabDC.add(CoreInit.MACHINE, this);
	}

	@Override
	public String getRegistryName() {
		return "main/canoe_kukui";
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/handheld", ImmutableMap.of("layer0", "dcs_climate:item/tool/canoe_kukui_item"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
		if (hitresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			Vec3 vec3 = player.getViewVector(1.0F);
			double d0 = 5.0D;
			List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(d0)).inflate(1.0D), EntitySelector.NO_SPECTATORS.and(Entity::isPickable));
			if (!list.isEmpty()) {
				Vec3 vec31 = player.getEyePosition();
				for (Entity entity : list) {
					AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
					if (aabb.contains(vec31)) {
						return InteractionResultHolder.pass(itemstack);
					}
				}
			}

			if (hitresult.getType() == HitResult.Type.BLOCK) {
				Boat boat = new CanoeEntity(level, hitresult.getLocation().x, hitresult.getLocation().y, hitresult.getLocation().z);
				boat.setYRot(player.getYRot());
				if (!level.noCollision(boat, boat.getBoundingBox())) {
					return InteractionResultHolder.fail(itemstack);
				} else {
					if (!level.isClientSide) {
						level.addFreshEntity(boat);
						level.gameEvent(player, GameEvent.ENTITY_PLACE, hitresult.getLocation());
						if (!player.getAbilities().instabuild) {
							itemstack.shrink(1);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
				}
			} else {
				return InteractionResultHolder.pass(itemstack);
			}
		}
	}

	public static final EntityRenderData KUKUI = new EntityRenderData("canoe_kukui", 0.5F, 0F);

}
