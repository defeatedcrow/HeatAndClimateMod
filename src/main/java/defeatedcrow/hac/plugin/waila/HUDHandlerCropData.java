package defeatedcrow.hac.plugin.waila;

import static mcp.mobius.waila.api.TooltipPosition.*;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.block.FertileBlock;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.component.PairComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class HUDHandlerCropData implements IBlockComponentProvider {

	private static final HUDHandlerCropData INSTANCE = new HUDHandlerCropData();

	@Override
	public void appendTail(ITooltip tooltip, IBlockAccessor level, IPluginConfig config) {
		if (level.getBlock() == Blocks.AIR)
			return;

		if (config.getBoolean(CROP) && level.getBlock() instanceof ClimateCropBaseBlock) {
			ClimateCropBaseBlock crop = (ClimateCropBaseBlock) level.getBlock();
			int stage5 = DCState.getInt(level.getBlockState(), DCState.STAGE5);
			if (stage5 >= 0) {
				float stage = stage5 / 4.0F;
				tooltip.addLine(new PairComponent(Component.translatable("dcs.tip.waila.crop_stage"), Component.translatable(String.format("%.0f %%", stage * 100F))));
			}
			if (!crop.isSuitableForGrowing(level.getWorld(), level.getPosition(), level.getBlockState())) {
				tooltip.addLine(Component.translatable("dcs.tip.waila.crop_bad_environment").withStyle(ChatFormatting.RED));
			}
			if (crop.canHarvest(level.getBlockState())) {
				tooltip.addLine(Component.translatable("dcs.tip.waila.crop_harvest").withStyle(ChatFormatting.AQUA));
			}
			if (DCState.getBool(level.getBlockState(), DCState.WILD)) {
				tooltip.addLine(Component.literal("WILD CROP").withStyle(ChatFormatting.GOLD));
			} else {
				if (level.getBlockState().is(TagDC.BlockTag.CROP_GREEN_MANURES) && stage5 > 1) {
					BlockState below = level.getWorld().getBlockState(level.getPosition().below());
					if ((below.is(BlockTags.DIRT) || below.is(TagDC.BlockTag.FARMLAND)) && FertileBlock.getFertile(level.getWorld(), level.getPosition().below(), below) < 3)
						tooltip.addLine(Component.translatable("dcs.tip.waila.crop_green_matures").withStyle(ChatFormatting.AQUA));
				} else {
					tooltip.addLine(Component.translatable("dcs.tip.waila.crop_hoe"));
				}
			}

			return;
		}

		if (config.getBoolean(FERTILE) && level.getBlock() instanceof FertileBlock) {
			int f = FertileBlock.getFertile(level.getWorld(), level.getPosition(), level.getBlockState());
			if (f >= 0) {
				float stage = f / 3.0F;
				tooltip.addLine(new PairComponent(Component.translatable("dcs.tip.waila.fertile_block"), Component.translatable(String.format("%.0f %%", stage * 100F))));
			}
			return;
		}
	}

	public static void register(IRegistrar registrar) {

		try {
			registrar.addFeatureConfig(CROP, true);
			registrar.addFeatureConfig(FERTILE, true);
		} catch (Exception e) {}

		registrar.addComponent(INSTANCE, TAIL, ClimateCropBaseBlock.class);
		registrar.addComponent(INSTANCE, TAIL, FertileBlock.class);

	}

	public static final ResourceLocation CROP = new ResourceLocation("dcs_climate", "show_cropdata");
	public static final ResourceLocation FARMLAND = new ResourceLocation("dcs_climate", "show_farmland_moisture");
	public static final ResourceLocation FERTILE = new ResourceLocation("dcs_climate", "show_farmland_fertile");

}
