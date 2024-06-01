package defeatedcrow.hac.food.material.fluid;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class GasTypeFluidDC {

	private final String name;
	private final boolean isWaterType;
	private final int color;
	private final ResourceLocation tex;
	protected final RegistryObject<FluidType> type;

	protected RegistryObject<ForgeFlowingFluid> still;
	protected RegistryObject<ForgeFlowingFluid> flow;
	protected RegistryObject<LiquidBlock> block;
	protected RegistryObject<Item> bucket;

	public GasTypeFluidDC(String s, int color, boolean drown, String texName) {
		this(s, color, getWaterProp(298, drown), texName);
	}

	public GasTypeFluidDC(String s, int color, int temp, boolean drown, String texName) {
		this(s, color, getWaterProp(temp, false), texName);
	}

	public GasTypeFluidDC(String s, int c, FluidType.Properties prop, String texName) {
		name = s;
		isWaterType = false;
		color = c;
		tex = new ResourceLocation(ClimateCore.MOD_ID, texName);

		type = CoreInit.FLUID_TYPES.register(name, () -> new FluidType(prop) {
			@Override
			public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
				consumer.accept(new IClientFluidTypeExtensions() {

					private static final ResourceLocation UNDERWATER_LOCATION = new ResourceLocation("textures/misc/underwater.png"),
							WATER_OVERLAY = new ResourceLocation("block/water_overlay");

					@Override
					public ResourceLocation getStillTexture() {
						return tex;
					}

					@Override
					public ResourceLocation getFlowingTexture() {
						return tex;
					}

					@Nullable
					@Override
					public ResourceLocation getOverlayTexture() {
						return WATER_OVERLAY;
					}

					@Override
					public ResourceLocation getRenderOverlayTexture(net.minecraft.client.Minecraft mc) {
						return UNDERWATER_LOCATION;
					}

					@Override
					public int getTintColor() {
						return color;
					}
				});
			}
		});
		still = CoreInit.FLUIDS.register(name, () -> new ForgeFlowingFluid.Source(fluidProperties()));
		flow = CoreInit.FLUIDS.register(name + "_flowing", () -> new ForgeFlowingFluid.Flowing(fluidProperties()));

		block = CoreInit.BLOCKS.register("fluid/" + name,
				() -> new LiquidBlock(getStillFluid(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()) {
					@Override
					public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
						if (level.getBlockState(pos.above()).isAir() && this.getFluid().getFluidType().getTemperature() > 350 && rand.nextInt(3) == 0) {
							double d0 = pos.getX() + 0.1D + rand.nextDouble() * 0.8D;
							double d1 = pos.getY() + 1.125D;
							double d2 = pos.getZ() + 0.1D + rand.nextDouble() * 0.8D;
							level.addParticle(CoreInit.SMOKE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
						}
					}
				});
		bucket = CoreInit.ITEMS.register("fluid/bucket_" + name,
				() -> new BucketItem(getStillFluid(), (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(CoreInit.CORE)));
	}

	/* 基本データ */
	protected static FluidType.Properties getWaterProp(int temp, boolean drown) {
		return FluidType.Properties.create()
				.fallDistanceModifier(1F)
				.pathType(null)
				.adjacentPathType(null)
				.density(0)
				.viscosity(0)
				.canPushEntity(false)
				.canSwim(false)
				.canDrown(drown)
				.canHydrate(false)
				.temperature(temp);
	}

	public String getFluidName() {
		return ClimateCore.MOD_ID + "." + name;
	}

	protected ForgeFlowingFluid.Properties fluidProperties() {
		return new ForgeFlowingFluid.Properties(type, still, flow)
				.bucket(getBucket())
				.block(getStillBlock());
	}

	public Supplier<FluidType> getType() {
		return type;
	}

	public Supplier<ForgeFlowingFluid> getStillFluid() {
		return still;
	}

	public Supplier<ForgeFlowingFluid> getFlowingFluid() {
		return flow;
	}

	public Supplier<? extends LiquidBlock> getStillBlock() {
		return block;
	}

	public Supplier<Item> getBucket() {
		return bucket;
	}

	protected Supplier<ResourceLocation> getTexture() {
		return () -> tex;
	}

}
