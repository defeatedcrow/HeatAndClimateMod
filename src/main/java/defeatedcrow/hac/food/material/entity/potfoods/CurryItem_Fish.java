package defeatedcrow.hac.food.material.entity.potfoods;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

public class CurryItem_Fish extends ItemEntityFood {

	public CurryItem_Fish(String s, int nut, float sat, boolean curry, TagKey<Item> pair) {
		super(s, prop(nut, sat, curry), curry, pair);
	}

	private static Properties prop(int nut, float sat, boolean isCurry) {
		return new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().effect(hot(), 1.0F).build());
	}

	private static Supplier<MobEffectInstance> hot() {
		return () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.CURRY_FISH_MODEL.get();
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(item)) {
			ItemStack stack = item.copy();
			int taste = getTaste(stack);
			MobEffectInstance effect = hot().get();
			PotionUtils.setCustomEffects(stack, ImmutableList.of(effect));
			PotionUtils.addPotionTooltip(stack, list, 1.0F);
		}
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.CURRY_FISH.get())
			return CURRY_FISH;
		if (item == FoodInit.LARGE_BOWL_ACQUA_PAZZA.get())
			return LARGE_BOWL_ACQUA_PAZZA;

		return CURRY_FISH;
	}

	public static final EntityRenderData CURRY_FISH = new EntityRenderData("food/curry_fish", 0.85F, -0.1F);

	public static final EntityRenderData LARGE_BOWL_ACQUA_PAZZA = new EntityRenderData("food/large_bowl_acqua_pazza", 0.85F, -0.1F);

}
