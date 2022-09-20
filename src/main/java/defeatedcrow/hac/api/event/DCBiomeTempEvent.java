package defeatedcrow.hac.api.event;

import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.HasResult;

@HasResult
public class DCBiomeTempEvent extends Event {

	public final Biome biome;
	public final float defaultTemp;
	public float newTemp;

	public DCBiomeTempEvent(Biome biomeIn, float def) {
		this.biome = biomeIn;
		this.defaultTemp = def;
		this.newTemp = def;
	}

	public float result() {
		MinecraftForge.EVENT_BUS.post(this);
		if (hasResult() && getResult() == Result.ALLOW) {
			return newTemp;
		}

		return defaultTemp;
	}
}
