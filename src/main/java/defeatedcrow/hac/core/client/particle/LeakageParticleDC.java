package defeatedcrow.hac.core.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LeakageParticleDC extends BaseAshSmokeParticle {

	private final double movY;

	protected LeakageParticleDC(ClientLevel level, double x, double y, double z, double sx, double sy, double sz, float size, SpriteSet sprite) {
		super(level, x, y, z, 0.0F, 0.0F, 0.0F, sx, sy, sz, size, sprite, 1.0F, 8, 0F, true);
		this.rCol = 1.0F;
		this.gCol = 1.0F;
		this.bCol = 1.0F;
		this.alpha = 0.8F;
		this.lifetime = 16 + level.random.nextInt(5);
		movY = this.yd;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@Override
	public float getQuadSize(float p) {
		float r = this.random.nextFloat() * 0.01F;
		return (this.quadSize * 1.25F) + r;
	}

	@Override
	public int getLightColor(float p) {
		float f = 0.9F + this.random.nextFloat() * 0.1F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		int i = super.getLightColor(p);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (15.0F * 16.0F);
		if (j > 240) {
			j = 240;
		}

		return j | k << 16;
	}

	@Override
	public void tick() {
		super.tick();
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet sprite) {
			this.sprites = sprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double sx, double sy, double sz) {
			return new LeakageParticleDC(level, x, y, z, sx, sy, sz, 2.0F, this.sprites);
		}
	}

}
