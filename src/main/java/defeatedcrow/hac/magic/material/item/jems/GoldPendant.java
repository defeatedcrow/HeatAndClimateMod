package defeatedcrow.hac.magic.material.item.jems;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GoldPendant extends MagicJewelBase {

	private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> recipeChecker = RecipeManager.createCheck(RecipeType.SMELTING);

	public GoldPendant(MagicColor c) {
		super("pendant_g_" + c.toString(), c, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_PENDANT);
	}

	@Override
	public CharmType getCharmType() {
		MagicColor c = getColor();
		if (c.isWhite) {
			return CharmType.DEFFENCE;
		}
		if (c.isBlue) {
			return CharmType.SPECIAL;
		}
		if (c.isBlack) {
			return CharmType.ATTACK;
		}
		return CharmType.DIG;
	}

	@Override
	public boolean onAttacking(LivingEntity owner, LivingEntity target, DamageSource source, float damage, ItemStack charm) {
		if (getColor().isBlack) {
			int f = charm.getCount();
			if (owner != null && target != null) {
				float l = Mth.floor(damage * 0.25F);
				float eff = f * l;
				if (owner instanceof Player player) {
					if (!player.level.isClientSide)
						player.getFoodData().eat(f, 1.0F);
				}
				owner.heal(f * eff);
				return false;
			}
		}
		return false;
	}

	@Override
	public float reduceDamage(LivingEntity owner, DamageSource source, float damage, ItemStack charm) {
		if (getColor().isWhite && source.isExplosion()) {
			return (float) Math.pow(0.5D, charm.getCount());
		}
		return 1F;
	}

	@Override
	public boolean onToolUsing(LivingEntity owner, BlockPos pos, BlockState state, ItemStack charm) {
		if (state == null || charm.isEmpty())
			return false;
		if (getColor().isGreen) {
			ItemStack item = new ItemStack(state.getBlock());
			if (state.is(BlockTags.LOGS)) {
				if (!owner.level.isClientSide) {
					int lim = ConfigCommonBuilder.INSTANCE.vTimberLimit.get();
					List<BlockPos> set = DCUtil.findLog(owner.level, pos, state.getBlock(), lim, ConfigCommonBuilder.INSTANCE.enTimberBreakLeaves.get());
					if (set.isEmpty()) {
						set = ImmutableList.of(pos);
					}
					int count = 0;
					for (BlockPos p2 : set) {
						owner.level.setBlock(p2, Blocks.AIR.defaultBlockState(), 3);
						count++;
					}

					while (count > 0) {
						int i = 0;
						if (count > 64) {
							i = 64;
						} else {
							i = count;
						}
						count -= i;
						ItemStack drop = item.copy();
						drop.setCount(i);
						ItemEntity dropE = new ItemEntity(owner.level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, drop);
						if (owner.level.addFreshEntity(dropE))
							owner.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					}

					if (owner instanceof Player player) {
						ItemStack held = player.getMainHandItem();
						ItemStack cop = held.copy();
						held.mineBlock(owner.level, state, pos, player);
						if (held.isEmpty() && !cop.isEmpty())
							net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, cop, InteractionHand.MAIN_HAND);
					}
				}
				return true;
			}
		} else if (getColor().isRed) {
			if (!owner.level.isClientSide && owner.level instanceof ServerLevel server) {
				List<ItemStack> drops = Block.getDrops(state, server, pos, server.getBlockEntity(pos));
				if (!drops.isEmpty()) {
					for (ItemStack item : drops) {
						Optional<SmeltingRecipe> recipe = server.getServer().getRecipeManager().getAllRecipesFor(RecipeType.SMELTING).stream()
								.filter((r) -> matchRecipe(r, item)).findFirst();
						recipe.ifPresentOrElse(r -> {
							ItemStack out = r.getResultItem();
							ItemEntity dropE = new ItemEntity(owner.level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, out.copy());
							owner.level.addFreshEntity(dropE);
						}, () -> {
							ItemEntity dropE = new ItemEntity(owner.level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, item.copy());
							owner.level.addFreshEntity(dropE);
						});
					}
					owner.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

					if (owner instanceof Player player) {
						ItemStack held = player.getMainHandItem();
						ItemStack cop = held.copy();
						held.mineBlock(owner.level, state, pos, player);
						if (held.isEmpty() && !cop.isEmpty())
							net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, cop, InteractionHand.MAIN_HAND);
					}

					return true;
				}
			}
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
		MutableComponent itemName = Component.translatable("dcs.tip.pendant_g.name." + getColor().toString());
		itemName.withStyle(getColor().chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
		if (ClimateCore.proxy.keyShiftPushed()) {
			MutableComponent itemTip = Component.translatable("dcs.tip.pendant_g.desc." + getColor().toString());
			list.add(itemTip);

			if (ConfigCommonBuilder.INSTANCE.enMagicCost.get()) {
				if (!getColor().isBlue) {
					int i = this.getMagicCostEXP(item);
					MutableComponent cost = Component.literal("COST: " + i + "Xp");
					list.add(cost);
				}
			}
		}
		super.appendHoverText(item, level, list, flag);
	}

}
