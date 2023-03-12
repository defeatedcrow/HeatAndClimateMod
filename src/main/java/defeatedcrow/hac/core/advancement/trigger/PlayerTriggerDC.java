package defeatedcrow.hac.core.advancement.trigger;

import com.google.gson.JsonObject;

import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class PlayerTriggerDC extends SimpleCriterionTrigger<PlayerTriggerDC.TriggerInstance> {
	final ResourceLocation id;

	public PlayerTriggerDC(ResourceLocation res) {
		this.id = res;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public PlayerTriggerDC.TriggerInstance createInstance(JsonObject json, EntityPredicate.Composite com, DeserializationContext cont) {
		return new PlayerTriggerDC.TriggerInstance(this.id, com);
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, (trigger) -> {
			return true;
		});
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		public TriggerInstance(ResourceLocation res, EntityPredicate.Composite com) {
			super(res, com);
		}

		public static PlayerTriggerDC.TriggerInstance heatDamage() {
			return new PlayerTriggerDC.TriggerInstance(TriggersDC.HEAT_DAMAGE.getId(), EntityPredicate.Composite.ANY);
		}

	}

}
