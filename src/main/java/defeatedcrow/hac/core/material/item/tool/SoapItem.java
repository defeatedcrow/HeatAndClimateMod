package defeatedcrow.hac.core.material.item.tool;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class SoapItem extends MaterialItemDC {

	public SoapItem(String s, TagKey<Item> pair) {
		super(new Item.Properties().tab(CoreInit.MACHINE), s, pair);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand) {
		if (entity instanceof Sheep sheep) {
			if (sheep.isAlive() && !sheep.isSheared() && sheep.getColor() != DyeColor.WHITE) {
				sheep.level.playSound(playerIn, sheep, SoundEvents.DYE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
				if (!playerIn.level.isClientSide) {
					sheep.setColor(DyeColor.WHITE);
					stack.shrink(1);
				}
			}
			return InteractionResult.sidedSuccess(playerIn.level.isClientSide);
		}
		return InteractionResult.PASS;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (item.is(TagDC.ItemTag.SOAP_OIL)) {
			MutableComponent tasteName = Component.translatable("dcs.tip.soap_oil").withStyle(ChatFormatting.GRAY);
			list.add(tasteName);
		} else if (item.is(TagDC.ItemTag.SOAP_MAGIC)) {
			MutableComponent tasteName = Component.translatable("dcs.tip.soap_magic").withStyle(ChatFormatting.GRAY);
			list.add(tasteName);
		}
		super.appendHoverText(item, level, list, flag);
	}
}
