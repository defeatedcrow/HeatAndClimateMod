package defeatedcrow.hac.magic.material.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.client.gui.BoringMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;

public class BoringSurveyItem extends ItemDC implements MenuProvider {

	protected String domain = "magic";
	private int[] blocks = new int[30];

	public BoringSurveyItem() {
		super(new Item.Properties().tab(CoreInit.MACHINE).stacksTo(1), TagDC.ItemTag.DUMMY);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		player.startUsingItem(hand);
		ItemStack item = player.getMainHandItem();
		if (level.isClientSide) {
			return InteractionResultHolder.success(item);
		} else {
			if (item.getItem() instanceof BoringSurveyItem document && player instanceof ServerPlayer) {
				NetworkHooks.openScreen((ServerPlayer) player, document, player.blockPosition());
			}
			return InteractionResultHolder.success(item);
		}
	}

	@Override
	public String getRegistryName() {
		return "magic/document_boring_survey";
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/magic/document_item_boring_survey"));
	}

	@Override
	public AbstractContainerMenu createMenu(int i, Inventory inv, Player player) {
		ItemStack held = player.getMainHandItem();
		return BoringMenu.getMenu(i, held, player);
	}

	@Override
	public Component getDisplayName() {
		return Component.empty();
	}

	public static int[] getBlockArray(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTag()) {
			CompoundTag tag = item.getTag();
			if (tag.contains("dcs.boring.int_array")) {
				int[] blocks = tag.getIntArray("dcs.boring.int_array");
				return blocks;
			}
		}
		return new int[30];
	}

	public static void setBlockData(ItemStack item, int[] array) {
		if (!DCUtil.isEmpty(item) && item.getItem() instanceof BoringSurveyItem) {
			CompoundTag tag = item.getOrCreateTag();
			tag.putIntArray("dcs.boring.int_array", array);
			item.setTag(tag);
		}
	}

	public static void setPlayerData(ItemStack item, Player player) {
		if (!DCUtil.isEmpty(item) && item.getItem() instanceof BoringSurveyItem) {
			CompoundTag tag = item.getOrCreateTag();
			tag.putString("dcs.boring.date", DCTimeHelper.getDate(player.getLevel()));
			tag.putInt("dcs.boring.x", player.blockPosition().getX());
			tag.putInt("dcs.boring.y", player.blockPosition().getY());
			tag.putInt("dcs.boring.z", player.blockPosition().getZ());
			item.setTag(tag);
		}
	}

	public static int getBlockTypeId(BlockState state) {
		if (state.is(Blocks.LAVA) || state.getMaterial() == Material.LAVA) {
			return 1;
		} else if (state.is(Blocks.BEDROCK)) {
			return 2;
		} else if (state.getMaterial() == Material.DIRT || state.getMaterial() == Material.GRASS || state.getMaterial() == Material.SAND) {
			return 4;
		} else if (state.is(Tags.Blocks.ORES_IRON)) {
			return 5;
		} else if (state.is(Tags.Blocks.ORES_COPPER)) {
			return 6;
		} else if (state.is(Tags.Blocks.ORES_GOLD)) {
			return 7;
		} else if (state.is(Tags.Blocks.ORES_COAL)) {
			return 8;
		} else if (state.is(Tags.Blocks.ORES_QUARTZ)) {
			return 9;
		} else if (state.is(Tags.Blocks.ORES_LAPIS)) {
			return 10;
		} else if (state.is(Tags.Blocks.ORES_EMERALD)) {
			return 11;
		} else if (state.is(Tags.Blocks.ORES_REDSTONE)) {
			return 12;
		} else if (state.is(Tags.Blocks.ORES_DIAMOND)) {
			return 13;
		} else if (state.is(TagDC.BlockTag.ORES_WHITE) || state.is(TagDC.BlockTag.ORES_WHITE_DEEP)) {
			return 14;
		} else if (state.is(TagDC.BlockTag.ORES_BLUE) || state.is(TagDC.BlockTag.ORES_BLUE_DEEP)) {
			return 15;
		} else if (state.is(TagDC.BlockTag.ORES_BLACK) || state.is(TagDC.BlockTag.ORES_BLACK_DEEP)) {
			return 16;
		} else if (state.is(TagDC.BlockTag.ORES_RED) || state.is(TagDC.BlockTag.ORES_RED_DEEP)) {
			return 17;
		} else if (state.is(TagDC.BlockTag.ORES_GREEN) || state.is(TagDC.BlockTag.ORES_GREEN_DEEP)) {
			return 18;
		} else {
			if (state.isAir() || state.getFluidState().isSource() || state.getMaterial().isReplaceable()) {
				return 0;
			} else if (state.is(Tags.Blocks.ORES)) {
				return 19;
			} else if (state.getMaterial() == Material.STONE) {
				return 3;
			} else {
				return 19;
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		CompoundTag tag = item.getTag();
		if (tag != null) {
			if (tag.contains("dcs.boring.date")) {
				String date = tag.getString("dcs.boring.date");
				MutableComponent s1 = Component.literal(date);
				s1.withStyle(ChatFormatting.GREEN);
				list.add(s1);
			}
			if (tag.contains("dcs.boring.x")) {
				int x = tag.getInt("dcs.boring.x");
				int y = tag.getInt("dcs.boring.y");
				int z = tag.getInt("dcs.boring.z");
				String pos = "pos: " + x + ", " + y + ", " + z;
				MutableComponent s2 = Component.literal(pos);
				s2.withStyle(ChatFormatting.GREEN);
				list.add(s2);
			}
		}
		super.appendHoverText(item, level, list, flag);
	}

}
