package defeatedcrow.hac.core.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SimpleParticleDC extends BaseAshSmokeParticle {

	protected SimpleParticleDC(ClientLevel level, double x, double y, double z, double sx, double sy, double sz, float f, SpriteSet sprite) {
		super(level, x, y, z, 0.1F, -0.1F, 0.1F, sx, sy, sz, f, sprite, 1.0F, 8, 0F, true);
		this.rCol = 1.0F;
		this.gCol = 1.0F;
		this.bCol = 1.0F;
		this.lifetime = 20;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@Override
	public float getQuadSize(float f) {
		return this.quadSize;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet sprite) {
			this.sprites = sprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double sx, double sy, double sz) {
			return new SimpleParticleDC(level, x, y, z, sx, sy, sz, 2.0F, this.sprites);
		}
	}

}
