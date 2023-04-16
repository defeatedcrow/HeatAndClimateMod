package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;

public class SilverPendant extends MagicJewelBase {

	private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> recipeChecker = RecipeManager.createCheck(RecipeType.SMELTING);

	public SilverPendant(MagicColor c) {
		super("pendant_s_" + c.toString(), c, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_PENDANT);
	}

	@Override
	public CharmType getCharmType() {
		MagicColor c = getColor();
		if (c.isWhite || c.isBlack) {
			return CharmType.ATTACK;
		}
		if (c.isBlue) {
			return CharmType.KEY;
		}
		if (c.isRed) {
			return CharmType.CONSTANT;
		}
		return CharmType.DIG;
	}

	@Override
	public void constantEffect(LivingEntity owner, ItemStack charm) {
		if (owner != null && !owner.level.isClientSide) {
			int i = DCUtil.isEmpty(charm) ? 0 : charm.getCount() - 1;
			MagicColor color = getColor();
			if (color.isRed) {
				owner.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 300, i));
			}
		}
	}

	@Override
	public float increaceDamage(LivingEntity owner, LivingEntity attackTarget, DamageSource source, float damage, ItemStack charm) {
		if (getColor().isBlack && attackTarget instanceof Mob mob) {
			if (mob.getTarget() != owner) {
				return 1.5F * charm.getCount();
			}
		}
		return 1F;
	}

	@Override
	public boolean onAttacking(LivingEntity owner, LivingEntity target, DamageSource source, float damage, ItemStack charm) {
		if (getColor().isWhite) {
			if (target == null || !(target instanceof Enemy)) {
				return true;
			} else if (owner instanceof TamableAnimal tamed) {
				return tamed.isOwnedBy(target);
			}
		}
		return false;
	}

	@Override
	public boolean onToolUsing(LivingEntity owner, BlockPos pos, BlockState state, ItemStack charm) {
		if (state == null || charm.isEmpty())
			return false;
		if (getColor().isGreen && owner instanceof Player player) {
			ItemStack held = player.getMainHandItem();
			boolean cons = false;
			Direction dir = owner.getDirection();
			if (owner.getViewXRot(1.0F) > 45F) {
				dir = Direction.UP;
			} else if (owner.getViewXRot(1.0F) < -45F) {
				dir = Direction.DOWN;
			}

			List<BlockPos> list = getTargetPos(pos, dir, charm.getCount());
			for (BlockPos p : list) {
				BlockState s = owner.level.getBlockState(p);
				if (s != null && !s.hasBlockEntity() && isTerrainBlock(s)) {
					boolean b1 = s.canHarvestBlock(player.level, p, player);
					boolean b2 = s.onDestroyedByPlayer(player.level, p, player, b1, player.level.getFluidState(p));
					if (b1 && b2) {
						Block block = s.getBlock();
						block.destroy(owner.level, p, s);
						block.playerDestroy(owner.level, player, p, s, owner.level.getBlockEntity(p), held);
						cons = true;
					}
				}
			}

			if (cons && charm.getItem() instanceof IJewelCharm c) {
				c.onConsumeResource(player, charm);

				ItemStack cop = held.copy();
				held.mineBlock(owner.level, state, pos, player);
				if (held.isEmpty() && !cop.isEmpty())
					net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, cop, InteractionHand.MAIN_HAND);
			}

		}
		return false;
	}

	boolean isTerrainBlock(BlockState state) {
		return state.is(Tags.Blocks.STONE) || state.is(Tags.Blocks.SAND) || state.is(BlockTags.DIRT) || state.is(Tags.Blocks.GRAVEL) || state.getMaterial() == Material.GRASS;
	}

	List<BlockPos> getTargetPos(BlockPos pos, Direction face, int r) {
		List<BlockPos> ret = Lists.newArrayList();
		if (face.getAxis() == Direction.Axis.X) {
			for (int z = -r; z <= r; z++) {
				for (int y = -r; y <= r; y++) {
					if (z == 0 && y == 0)
						continue;
					BlockPos p = pos.offset(0, y, z);
					ret.add(p);
				}
			}
			return ret;
		} else if (face.getAxis() == Direction.Axis.Z) {
			for (int x = -r; x <= r; x++) {
				for (int y = -r; y <= r; y++) {
					if (x == 0 && y == 0)
						continue;
					BlockPos p = pos.offset(x, y, 0);
					ret.add(p);
				}
			}
			return ret;
		} else {
			for (int z = -r; z <= r; z++) {
				for (int x = -r; x <= r; x++) {
					if (z == 0 && x == 0)
						continue;
					BlockPos p = pos.offset(x, 0, z);
					ret.add(p);
				}
			}
			return ret;
		}
	}

	@Override
	public boolean onUsing(ServerPlayer owner, ItemStack charm) {
		MagicColor c = getColor();
		if (c.isBlue) {
			double d = 8D + (4D * charm.getCount());
			DCLogger.debugInfoLog("### TargetAI Jammer activated ###");
			List<LivingEntity> list = owner.level.getEntitiesOfClass(LivingEntity.class, owner.getBoundingBox().inflate(d), EntitySelector.ENTITY_STILL_ALIVE);
			if (list != null && !list.isEmpty()) {
				for (LivingEntity liv : list) {
					if (liv instanceof NeutralMob n) {
						if (n.isAngryAt(owner)) {
							n.stopBeingAngry();
						}
					} else if (liv instanceof Monster monster) {
						monster.targetSelector.getRunningGoals().forEach(goal -> {
							goal.stop();
						});
					}
				}
			}
			owner.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 600 * charm.getCount()));
			return true;
		}
		return false;
	}

	private boolean matchRecipe(SmeltingRecipe recipe, ItemStack item) {
		NonNullList<Ingredient> ing = recipe.getIngredients();
		return ing.size() == 1 && ing.get(0).test(item) && !recipe.getResultItem().isEmpty();
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.translatable(getColor().name() + " " + item.getRarity());
		tier.withStyle(getColor().chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.pendant_s.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.pendant_s.desc." + getColor().toString());
			list.add(itemTip);

			if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
				if (getColor().isBlue || getColor().isBlack || getColor().isGreen) {
					int i = this.getMagicCostEXP(item);
					MutableComponent cost = Component.literal("COST: " + i + "Xp");
					list.add(cost);
				}
			}
		}
		super.appendHoverText(item, level, list, flag);
	}

}
