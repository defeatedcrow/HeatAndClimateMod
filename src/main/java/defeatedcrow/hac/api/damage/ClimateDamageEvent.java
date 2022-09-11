package defeatedcrow.hac.api.damage;

import defeatedcrow.hac.api.climate.IClimate;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * ClimateDamage発生直前に発火するEvent<br>
 * キャンセルした場合はDamage処理自体がなくなる。<br>
 * 2.0F未満では瀕死時にダメージが止まる。また、1.0Fを下回ると自動的にキャンセルされる。
 */
@Cancelable
public class ClimateDamageEvent extends LivingEvent {

	private DamageSource source;
	private float amount;
	private final IClimate climate;

	public ClimateDamageEvent(LivingEntity livingIn, DamageSource sourceIn, IClimate climateIn, float amountIn) {
		super(livingIn);
		source = sourceIn;
		amount = amountIn;
		climate = climateIn;
	}

	public DamageSet result() {
		MinecraftForge.EVENT_BUS.post(this);
		if (isCanceled()) {
			return new DamageSet(0, source);
		}

		return new DamageSet(amount, source);
	}

	public DamageSource getSource() {
		return source;
	}

	public float getAmount() {
		return amount;
	}

	public IClimate getClimate() {
		return climate;
	}

	public void setAmount(float amountIn) {
		this.amount = amountIn;
	}

	public void setDamageSource(DamageSource sourceIn) {
		source = sourceIn;
	}

	public final class DamageSet {

		public final float damage;
		public final DamageSource source;

		public DamageSet(float d, DamageSource s) {
			damage = d;
			source = s;
		}

	}
}
