package defeatedcrow.hac.magic.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.item.entity.ThrownSolidOrb;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class EXPSolidOrbItem extends MagicMaterialItemDC {

	public EXPSolidOrbItem() {
		super(new Item.Properties().rarity(Rarity.UNCOMMON), MagicColor.WHITE, "exp_solid_orb", null);
		defeatedcrow.hac.core.material.tabs.CreativeTabDC.add(MagicInit.MAGIC, this);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player owner, InteractionHand hand) {
		ItemStack itemstack = owner.getItemInHand(hand);
		level.playSound((Player) null, owner.getX(), owner.getY(), owner.getZ(), SoundEvents.EXPERIENCE_BOTTLE_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!level.isClientSide) {
			ThrownSolidOrb thrown = new ThrownSolidOrb(level, owner);
			thrown.setItem(itemstack);
			thrown.shootFromRotation(owner, owner.getXRot(), owner.getYRot(), -20.0F, 0.7F, 1.0F);
			level.addFreshEntity(thrown);
		}
		owner.awardStat(Stats.ITEM_USED.get(this));
		if (!owner.getAbilities().instabuild) {
			itemstack.shrink(1);
		}

		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent t1 = Component.translatable("dcs.tip.exp_solid_orb").withStyle(ChatFormatting.AQUA);
		list.add(t1);
	}

}
