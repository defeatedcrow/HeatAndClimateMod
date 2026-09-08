package defeatedcrow.hac.plugin.waila;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.block.FertileBlock;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.config.IPluginConfig;

public class HUDHandlerCropData implements IBlockComponentProvider {

	private static final HUDHandlerCropData INSTANCE = new HUDHandlerCropData();

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor level, IPluginConfig config) {
		if (level.getBlock() == Blocks.AIR)
			return;

		if (config.get(CROP) && level.getBlock() instanceof ClimateCropBaseBlock) {
			ClimateCropBaseBlock crop = (ClimateCropBaseBlock) level.getBlock();
			int stage6 = DCState.getInt(level.getBlockState(), DCState.STAGE6);
			if (stage6 >= 0 && stage6 < 5) {
				float stage = stage6 / 4.0F;
				tooltip.add(Component.translatable("dcs.tip.waila.crop_stage").append(Component.literal(" ")).append(Component.translatable(String.format("%.0f %%", stage * 100F))));
			} else if (stage6 == 5) {
				tooltip.add(Component.translatable("dcs.tip.waila.crop_failure").withStyle(ChatFormatting.RED));
			}
			if (!crop.isSuitableForGrowing(level.getLevel(), level.getPosition(), level.getBlockState())) {
				tooltip.add(Component.translatable("dcs.tip.waila.crop_bad_environment").withStyle(ChatFormatting.RED));
			}
			if (crop.canHarvest(level.getBlockState())) {
				tooltip.add(Component.translatable("dcs.tip.waila.crop_harvest").withStyle(ChatFormatting.AQUA));
			}
			if (DCState.getBool(level.getBlockState(), DCState.WILD)) {
				tooltip.add(Component.literal("WILD CROP").withStyle(ChatFormatting.GOLD));
			} else if (level.getBlockState().is(TagDC.BlockTag.CROP_GREEN_MANURES) && stage6 > 1) {
				BlockState below = level.getLevel().getBlockState(level.getPosition().below());
				if ((below.is(BlockTags.DIRT) || below.is(TagDC.BlockTag.FARMLAND)) && FertileBlock.getFertile(level.getLevel(), level.getPosition().below(), below) < 3)
					tooltip.add(Component.translatable("dcs.tip.waila.crop_green_matures").withStyle(ChatFormatting.AQUA));
			} else {
				tooltip.add(Component.translatable("dcs.tip.waila.crop_hoe"));
			}

			return;
		}

		if (config.get(FERTILE) && level.getBlock() instanceof FertileBlock) {
			int f = FertileBlock.getFertile(level.getLevel(), level.getPosition(), level.getBlockState());
			if (f >= 0) {
				float stage = f / 4.0F;
				tooltip.add(Component.translatable("dcs.tip.waila.fertile_block").append(Component.literal(" ")).append(Component.translatable(String.format("%.0f %%", stage * 100F))));
			}
		}
	}

	public static void register(IWailaClientRegistration registrar) {

		try {
			registrar.addConfig(CROP, true);
			registrar.addConfig(FERTILE, true);
		} catch (Exception e) {
		}

		registrar.registerBlockComponent(INSTANCE, ClimateCropBaseBlock.class);
		registrar.registerBlockComponent(INSTANCE, FertileBlock.class);

	}

	public static final ResourceLocation CROP = ResourceLocation.fromNamespaceAndPath("dcs_climate", "show_cropdata");
	public static final ResourceLocation FARMLAND = ResourceLocation.fromNamespaceAndPath("dcs_climate", "show_farmland_moisture");
	public static final ResourceLocation FERTILE = ResourceLocation.fromNamespaceAndPath("dcs_climate", "show_farmland_fertile");

	@Override
	public ResourceLocation getUid() {
		return CROP;
	}

}
