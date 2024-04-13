package defeatedcrow.hac.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.extensions.IForgeLivingEntity;
import net.minecraftforge.fluids.FluidType;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements IForgeLivingEntity {

	@Inject(method = "baseTick", at = @At(value = "TAIL"))
	public void onBaseTick(CallbackInfo callback) {
		LivingEntity liv = LivingEntity.class.cast(this);
		if (liv.isAlive() && ConfigCommonBuilder.INSTANCE.enTightEffect.get() && liv.getType().getCategory() != MobCategory.MONSTER && !(liv instanceof Bat) && !(liv instanceof WaterAnimal)) {
			BlockPos pos = new BlockPos(liv.getX(), liv.getEyeY(), liv.getZ());
			IClimate clm = new ClimateSupplier(liv.getLevel(), pos).get();
			if (clm.getAirflow() == DCAirflow.TIGHT) {
				boolean flag = liv instanceof Player;
				boolean flag1 = liv.canDrownInFluidType(liv.getEyeInFluidType()) && !MobEffectUtil.hasWaterBreathing(liv) && (!flag || !((Player) liv).getAbilities().invulnerable);
				if (flag1) {
					int i = EnchantmentHelper.getRespiration(liv);
					int air = liv.getAirSupply();
					if (i < 1 || liv.getRandom().nextInt(i + 1) == 0) {
						air -= 1;
					}
					liv.setAirSupply(air);
					if (liv.getAirSupply() == -20) {
						liv.setAirSupply(0);
						liv.hurt(DamageSource.DROWN, 2.0F);
					}
				}
			}
		}
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		LivingEntity liv = LivingEntity.class.cast(this);
		if (liv.isAlive() && ConfigCommonBuilder.INSTANCE.enTightEffect.get() && liv.getType().getCategory() != MobCategory.MONSTER && !(liv instanceof Bat) && !(liv instanceof WaterAnimal)) {
			BlockPos pos = new BlockPos(liv.getX(), liv.getEyeY(), liv.getZ());
			IClimate clm = new ClimateSupplier(liv.getLevel(), pos).get();
			if (clm.getAirflow() == DCAirflow.TIGHT) {
				return true;
			}
		}
		if (type == ForgeMod.WATER_TYPE.get())
			return !liv.canBreatheUnderwater();
		return type.canDrownIn(self());
	}

}
