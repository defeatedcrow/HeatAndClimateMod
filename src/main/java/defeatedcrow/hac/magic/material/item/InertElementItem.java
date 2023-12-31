package defeatedcrow.hac.magic.material.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class InertElementItem extends MagicMaterialItemDC {

	public InertElementItem(MagicColor c, String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(MagicInit.MAGIC).rarity(Rarity.RARE).stacksTo(1), c, s, pair);
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return true;
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		int amo = 0;
		if (tag.contains(TagKeyDC.ENERGY)) {
			amo = tag.getInt(TagKeyDC.ENERGY);
		}
		return Math.round(amo * 13.0F / 1000F);
	}

	@Override
	public int getBarColor(ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		int amo = 0;
		if (tag.contains(TagKeyDC.ENERGY)) {
			amo = tag.getInt(TagKeyDC.ENERGY);
		}
		float f = Math.max(0.0F, amo / 1000F);
		return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
	}

	public boolean isSuitablePlace(Player player) {
		if (getColor().isWhite)
			return !player.isInWater() && player.getLevel().canSeeSky(player.blockPosition().above()) && DCTimeHelper.isDayTime(player.getLevel());
		if (getColor().isBlue)
			return player.isInWater();
		if (getColor().isBlack)
			return player.blockPosition().getY() < 0 && !player.getLevel().canSeeSky(player.blockPosition().above()) && player.getLevel().getLightEmission(player.blockPosition()) < 5.0F;
		if (getColor().isRed)
			return new ClimateSupplier(player.getLevel(), player.blockPosition()).get().getHeat().getTier() > 3;
		if (getColor().isGreen) {
			return isGreenEnvironment(player.getLevel(), player.blockPosition().above());
		}
		return false;
	}

	private boolean isGreenEnvironment(Level level, BlockPos pos) {
		int count = 0;
		for (BlockPos p : BlockPos.withinManhattan(pos, 1, 1, 1)) {
			BlockState state = level.getBlockState(p);
			if (state.is(BlockTags.LEAVES) || state.is(BlockTags.FLOWERS) || state.is(BlockTags.CROPS) || state.is(TagDC.BlockTag.WEED))
				count++;
		}
		return count > 2;
	}

	public boolean charge(int e, ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		int amo = 0;
		if (tag.contains(TagKeyDC.ENERGY)) {
			amo = tag.getInt(TagKeyDC.ENERGY);
		}
		amo += e;
		amo = Math.min(amo, 1000);
		tag.putInt(TagKeyDC.ENERGY, amo);
		stack.setTag(tag);
		return amo == 1000;

	}

	public Supplier<Item> getActivatedElement() {
		if (getColor().isWhite)
			return MagicInit.ELEMENT_WHITE;
		if (getColor().isBlue)
			return MagicInit.ELEMENT_BLUE;
		if (getColor().isBlack)
			return MagicInit.ELEMENT_BLACK;
		if (getColor().isRed)
			return MagicInit.ELEMENT_RED;
		if (getColor().isGreen)
			return MagicInit.ELEMENT_GREEN;
		return () -> Items.AIR;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent t1 = Component.translatable("dcs.tip.inert_element");
		list.add(t1);
		MutableComponent t2 = Component.translatable("dcs.tip.inert_element." + getColor().toString()).withStyle(getColor().chatColor);
		list.add(t2);
		super.appendHoverText(item, level, list, flag);
	}

}
