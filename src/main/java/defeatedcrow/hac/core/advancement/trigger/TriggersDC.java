package defeatedcrow.hac.core.advancement.trigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;

public class TriggersDC {

	public static void init() {}

	public static final PlayerTrigger HEAT_DAMAGE = register(new PlayerTrigger(new ResourceLocation("heat_damage")));

	public static <T extends CriterionTrigger<?>> T register(T crit) {
		return CriteriaTriggers.register(crit);
	}

}
