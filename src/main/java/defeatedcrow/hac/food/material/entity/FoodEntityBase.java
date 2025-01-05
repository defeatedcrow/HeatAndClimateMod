package defeatedcrow.hac.food.material.entity;

import java.util.Optional;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.entity.ObjectEntityBaseDC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class FoodEntityBase extends ObjectEntityBaseDC {

	private int count = 0;
	private IClimateSmelting currentRecipe;

	public FoodEntityBase(EntityType<? extends FoodEntityBase> type, Level level) {
		super(type, level);
	}

	public boolean isRaw() {
		return !getItem().isEmpty() && getItem().is(TagDC.ItemTag.RAW_FOOD);
	}

	@Override
	public void tick() {
		super.tick();

		if (isRaw()) {
			ClimateSupplier supplier = new ClimateSupplier(level, blockPosition());
			IClimate clm = supplier.get();

			if (currentRecipe != null) {
				if (currentRecipe.matcheInput(getItem()) && currentRecipe.matchClimate(clm) && currentRecipe.additionalRequire(getLevel(), blockPosition())) {
					if (count > 0) {
						count--;
					} else if (count <= 0) {
						ItemStack ret = currentRecipe.getOutput().copy();
						int taste = copyTaste(ret, getItem());
						this.setItem(ret);
						level.playSound(null, this, SoundEvents.LAVA_EXTINGUISH, SoundSource.AMBIENT, 1.0F, 1.0F);
						level.addParticle(ParticleTypes.SMOKE, this.getRandomX(0.1D), this.getRandomY() * 0.5D, this.getRandomZ(0.1D), 0D, 0.003D, 0D);
						float exp = 10.0F + 2.0F * (1.0F + level.random.nextFloat()) * taste;
						if (exp > 0.0F) {
							ExperienceOrb orb = new ExperienceOrb(level, this.getX(), this.getY(), this.getZ(), Mth.ceil(exp));
							level.addFreshEntity(orb);
						}
					}

				} else {
					currentRecipe = null;
					count = 0;
				}
			} else {
				Optional<IClimateSmelting> recipe = DCRecipes.getSmeltingRecipe(clm, getItem());
				if (recipe.isPresent()) {
					currentRecipe = recipe.get();
					count = recipe.get().recipeFrequency();
				}
			}
		}

		BlockEntity under = getLevel().getBlockEntity(getOnPos());
		BlockState underState = getLevel().getBlockState(getOnPos());
		if (under != null && under instanceof Hopper && !DCState.getBool(underState, DCState.FLAG)) {
			this.dropItem(this.position());
			this.kill();
		}

		if (getLevel().random.nextInt(5) == 0 && !isRaw() && !getItem().isEmpty() && getItem().getItem() instanceof ItemEntityFood food) {
			if (food.isHotFood()) {
				double d0 = this.position().x + getLevel().random.nextDouble() * 0.4D - 0.2D;
				double d1 = this.position().y + 0.25D;
				double d2 = this.position().z + getLevel().random.nextDouble() * 0.4D - 0.2D;
				level.addParticle(CoreInit.SMOKE_SMALL.get(), d0, d1, d2, 0.0D, 0.01D, 0.0D);
			}
		}
	}

	private int copyTaste(ItemStack ret, ItemStack in) {
		if (!ret.isEmpty() && !in.isEmpty() && in.getTag() != null) {
			if (in.getItem() instanceof IFoodTaste && ret.getItem() instanceof IFoodTaste) {
				int taste = DCUtil.getFoodTaste(in);
				DCUtil.setFoodTaste(ret, taste);
				return taste;
			}
		}
		return 0;
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!getItem().isEmpty() && player != null) {
			if (player.getItemInHand(hand).is(TagDC.ItemTag.CUTLERY)) {
				ItemStack food = getItem().copy();
				if (food.isEdible()) {
					player.eat(getLevel(), food);
					getLevel().playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5F, getLevel().random.nextFloat() * 0.2F + 0.8F);
					this.kill();
				}
			} else if (getItem().getItem() == FoodInit.BREAD_TORTILLA_BAKED_ITEM.get() && player.getItemInHand(hand).is(TagDC.ItemTag.CHEESE)) {
				ClimateSupplier supplier = new ClimateSupplier(level, blockPosition());
				IClimate clm = supplier.get();
				if (clm.getHeat().getTier() > DCHeatTier.BOIL.getTier()) {
					FoodEntityBase food = FoodInit.QUESADILLA.get().create(getLevel());
					food.setPos(this.getEyePosition());
					food.setDeltaMovement(0D, 0D, 0D);
					food.setYRot(this.yRotO);
					food.setOwner(this.getOwner());
					player.getItemInHand(hand).split(1);
					ItemStack quesadilla = new ItemStack(FoodInit.TACO_QUESADILLA.get());
					int taste = DCUtil.getFoodTaste(player.getItemInHand(hand)) + DCUtil.getFoodTaste(getItem());
					DCUtil.setFoodTaste(quesadilla, taste);
					food.setItem(quesadilla);
					if (level.addFreshEntity(food)) {
						level.playSound(null, this, SoundEvents.LAVA_EXTINGUISH, SoundSource.AMBIENT, 1.0F, 1.0F);
						this.kill();
						level.gameEvent(food, GameEvent.ENTITY_PLACE, food.getPosition(0F));
					}
				}
			} else if (getItem().getItem() == FoodInit.STICK_CHICKEN_COOKED.get()) {
				if (player.getLevel().dimension() == Level.NETHER) {
					ClimateCore.proxy.triggerAdvancement(player, "main/nether_chicken");
				}
			}
		}

		return super.interact(player, hand);
	}

	@Override
	protected Component getTypeName() {
		if (!DCUtil.isEmpty(getDropItem())) {
			return Component.translatable(getDropItem().getDescriptionId());
		}
		return super.getTypeName();
	}

}
