package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewel;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketCamera;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDebugGauntlet extends DCItem implements IJewel {

	private final int maxMeta;

	private static String[] names = {
			"ur"
	};

	public ItemDebugGauntlet() {
		super();
		maxMeta = 0;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity,
			EnumHand hand) {
		if (entity == null || player == null || DCUtil.isEmpty(stack)) {
			return false;
		}
		if (!player.world.isRemote && player instanceof EntityPlayerMP) {
			((EntityPlayerMP) player).connection.sendPacket(new SPacketCamera(entity));

			return true;
		}
		return true;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/gauntlet_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public CharmType getCharmType(int meta) {
		return CharmType.CONSTANT;
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.OFFHAND;
	}

	@Override
	public MagicColor getColor(int meta) {
		switch (meta) {
		default:
			return MagicColor.BLUE;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.debug_gauntlet." + meta));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.offhand_tool"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.debug_gauntlet." + meta));
			tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.BOLD.toString() + "============");
			tooltip.add(TextFormatting.GRAY.toString() + I18n.format("dcs.comment.flavor.debug_gauntlet." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}

	int count = 60;
	private boolean x_key = false;

	@SubscribeEvent
	public void onLiving(LivingEvent.LivingUpdateEvent event) {
		if (hasDebugGauntlet(event.getEntityLiving()) && event.getEntityLiving() instanceof EntityPlayer) {
			if (event.getEntityLiving().world.isRemote && ClimateCore.proxy.isWarpKeyDown()) {
				if (!x_key) {
					x_key = true;
					onKeyPushed((EntityPlayer) event.getEntityLiving());
				}
			} else {
				x_key = false;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void onKeyPushed(EntityPlayer player) {
		if (Minecraft.getMinecraft().getRenderViewEntity() != null && Minecraft.getMinecraft()
				.getRenderViewEntity() != Minecraft.getMinecraft().player) {
			Minecraft.getMinecraft().setRenderViewEntity(Minecraft.getMinecraft().player);
		}
	}

	public static boolean hasDebugGauntlet(EntityLivingBase player) {
		if (player == null || DCUtil.isEmpty(player.getHeldItem(EnumHand.OFF_HAND)))
			return false;

		ItemStack held = player.getHeldItem(EnumHand.OFF_HAND);
		if (held.getItem() == MagicInit.debugGauntlet) {
			return true;
		}

		return false;
	}
}
