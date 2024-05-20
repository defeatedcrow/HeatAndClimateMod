package defeatedcrow.hac.food.material.entity;

import java.util.Optional;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.entity.ObjectEntityBaseDC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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
						copyTaste(ret, getItem());
						this.setItem(ret);
						level.playSound(null, this, SoundEvents.LAVA_EXTINGUISH, SoundSource.AMBIENT, 1.0F, 1.0F);
						level.addParticle(ParticleTypes.SMOKE, this.getRandomX(0.1D), this.getRandomY() * 0.5D, this.getRandomZ(0.1D), 0D, 0.003D, 0D);
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
	}

	private void copyTaste(ItemStack ret, ItemStack in) {
		if (!ret.isEmpty() && !in.isEmpty() && in.getTag() != null) {
			if (in.getItem() instanceof IFoodTaste && ret.getItem() instanceof IFoodTaste) {
				((IFoodTaste) ret.getItem()).setTaste(ret, ((IFoodTaste) in.getItem()).getTaste(in));
			}
		}
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!getItem().isEmpty() && player != null && player.getItemInHand(hand).is(TagDC.ItemTag.CUTLERY)) {
			ItemStack food = getItem().copy();
			if (food.isEdible()) {
				player.eat(getLevel(), food);
				getLevel().playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5F, getLevel().random.nextFloat() * 0.2F + 0.8F);
				this.kill();
			}
		}
		if (!getItem().isEmpty() && getItem().getItem() == FoodInit.STICK_CHICKEN_COOKED.get() && player != null) {
			if (player.getLevel().dimension() == Level.NETHER) {
				ClimateCore.proxy.triggerAdvancement(player, "main/nether_chicken");
			}
		}
		return super.interact(player, hand);
	}

}
