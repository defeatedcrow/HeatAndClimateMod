package defeatedcrow.hac.main.potion;

import java.util.UUID;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class DCAttributeUtil {

	public static final IAttribute MINING_RANGE = new RangedAttribute(null, "dcs.attribute.miningRange", 0.0D, 0.0D,
			5.0D).setShouldWatch(true);

	public static final UUID SWIM_SPEED_MODIFIER = UUID.fromString("312214a3-5479-4651-8c58-0de53d82012d");
	public static final UUID NOCKBACK_MODIFIER = UUID.fromString("BD6D692F-B923-4221-B2A8-3500022D2A9C");
	public static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("8195234b-0d1f-4920-b21e-1a632660969f");
	public static final UUID MINING_RANGE_MODIFIRE = UUID.fromString("5336587d-8210-8ff6-a603-318d270f44ff");

}
