package defeatedcrow.hac.core.client.item;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AltimeterPropertyFunction implements ClampedItemPropertyFunction {

	@Override
	public float unclampedCall(ItemStack stack, ClientLevel level, LivingEntity entity, int i) {
		Entity entity2 = entity != null ? entity : stack.getEntityRepresentation();
		if (entity2 == null) {
			return 0.0F;
		} else {
			return 0.0F;
		}
	}

}
