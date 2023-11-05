package defeatedcrow.hac.food.material.fluid;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class WaterTypeFluidDC {

	private final String name;
	private final boolean isWaterType;
	private final int color;
	private final ResourceLocation tex;
	protected final RegistryObject<FluidType> type;

	protected RegistryObject<ForgeFlowingFluid> still;
	protected RegistryObject<ForgeFlowingFluid> flow;
	protected RegistryObject<LiquidBlock> block;
	protected RegistryObject<Item> bucket;

	public WaterTypeFluidDC(String s, int color) {
		this(s, color, getWaterProp(298));
	}

	public WaterTypeFluidDC(String s, int color, int temp) {
		this(s, color, getWaterProp(temp));
	}

	public WaterTypeFluidDC(String s, int c, FluidType.Properties prop) {
		name = s;
		isWaterType = false;
		color = c;
		tex = new ResourceLocation(ClimateCore.MOD_ID, "fluid/" + name + "_still");

		type = CoreInit.FLUID_TYPES.register(name, () -> new FluidType(prop) {
			@Override
			public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
				consumer.accept(new IClientFluidTypeExtensions() {
					private static final ResourceLocation UNDERWATER_LOCATION = new ResourceLocation("textures/misc/underwater.png"),
							WATER_STILL = new ResourceLocation("block/water_still"),
							WATER_FLOW = new ResourceLocation("block/water_flow"),
							WATER_OVERLAY = new ResourceLocation("block/water_overlay");

					@Override
					public ResourceLocation getStillTexture() {
						return WATER_STILL;
					}

					@Override
					public ResourceLocation getFlowingTexture() {
						return WATER_FLOW;
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
					if (this.getFluid().getFluidType().getTemperature() > 350 && rand.nextInt(3) == 0) {
						double d0 = pos.getX() + 0.1D + rand.nextDouble() * 0.8D;
						double d1 = pos.getY() + 1.25D;
						double d2 = pos.getZ() + 0.1D + rand.nextDouble() * 0.8D;
						level.addParticle(CoreInit.SMOKE.get(), d0, d1, d2, 0.0D, 0.005D, 0.0D);
					}
				}
			});
		bucket = CoreInit.ITEMS.register("fluid/bucket_" + name,
			() -> new BucketItem(getStillFluid(), (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(CoreInit.CORE)));
	}

	public WaterTypeFluidDC(String s, int c, FluidType.Properties prop, String texName) {
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
	protected static FluidType.Properties getWaterProp(int temp) {
		return FluidType.Properties.create()
			.fallDistanceModifier(0F)
			.canExtinguish(true)
			.canConvertToSource(true)
			.supportsBoating(true)
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
			.canHydrate(true)
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
