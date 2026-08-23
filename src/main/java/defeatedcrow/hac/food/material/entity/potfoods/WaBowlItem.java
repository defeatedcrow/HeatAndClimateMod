package defeatedcrow.hac.food.material.entity.potfoods;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.material.CoreInit;
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

public class WaBowlItem extends ItemEntityFood implements IPotFoods {

	private final boolean isCold;

	public WaBowlItem(String s, int nut, float sat, boolean cold, TagKey<Item> pair) {
		super(s, prop(nut, sat, cold), !cold, pair);
		isCold = cold;
	}

	private static Properties prop(int nut, float sat, boolean isCold) {
		if (isCold)
			return new Item.Properties().tab(FoodInit.FOOD)
			    .food(new FoodProperties.Builder().nutrition(nut)
			        .saturationMod(sat)
			        .alwaysEat()
			        .build());
		else
			return new Item.Properties().tab(FoodInit.FOOD)
			    .food(new FoodProperties.Builder().nutrition(nut)
			        .saturationMod(sat)
			        .alwaysEat()
			        .effect(hot(), 1.0F)
			        .build());
	}

	private static Supplier<MobEffectInstance> hot() {
		return () -> new MobEffectInstance(CoreInit.COLD_RESISTANCE.get(), 600);
	}

	private static Supplier<MobEffectInstance> cold() {
		return () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.WA_BOWL.get();
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(item)) {
			ItemStack stack = item.copy();
			int taste = getTaste(stack);
			MobEffectInstance effect = isCold ? cold().get() : hot().get();
			PotionUtils.setCustomEffects(stack, ImmutableList.of(effect));
			PotionUtils.addPotionTooltip(stack, list, 1.0F);
		}
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.WABOWL_MISO_TOFU.get())
			return MISO_TOFU;
		if (item == FoodInit.WABOWL_MISO_AGE.get())
			return MISO_AGE;
		if (item == FoodInit.WABOWL_MISO_EGGPLANT.get())
			return MISO_EGGPLANT;
		if (item == FoodInit.WABOWL_MISO_MUSHROOM.get())
			return MISO_MUSHROOM;
		if (item == FoodInit.WABOWL_MISO_PORK.get())
			return MISO_PORK;
		if (item == FoodInit.WABOWL_MISO_CARP.get())
			return MISO_CARP;
		if (item == FoodInit.WABOWL_SHIRUKO.get())
			return SHIRUKO;
		return MISO_TOFU;
	}

	@Override
	public EntityRenderData getPotTexture(Item item) {
		if (item == FoodInit.WABOWL_MISO_TOFU.get())
			return MISO_TOFU_LAYER;
		if (item == FoodInit.WABOWL_MISO_AGE.get())
			return MISO_AGE_LAYER;
		if (item == FoodInit.WABOWL_MISO_EGGPLANT.get())
			return MISO_EGGPLANT_LAYER;
		if (item == FoodInit.WABOWL_MISO_MUSHROOM.get())
			return MISO_MUSHROOM_LAYER;
		if (item == FoodInit.WABOWL_MISO_PORK.get())
			return MISO_PORK_LAYER;
		if (item == FoodInit.WABOWL_MISO_CARP.get())
			return MISO_CARP_LAYER;
		if (item == FoodInit.WABOWL_SHIRUKO.get())
			return SHIRUKO_LAYER;
		return MISO_TOFU_LAYER;
	}

	@Override
	public LayerType getPotLayerModel(Item item) {
		return LayerType.LAYER;
	}

	public static final EntityRenderData MISO_TOFU = new EntityRenderData("food/wabowl_miso_tofu", 0.65F, 0F);
	public static final EntityRenderData MISO_AGE = new EntityRenderData("food/wabowl_miso_age", 0.65F, 0F);
	public static final EntityRenderData MISO_EGGPLANT = new EntityRenderData("food/wabowl_miso_eggplant", 0.65F, 0F);
	public static final EntityRenderData MISO_MUSHROOM = new EntityRenderData("food/wabowl_miso_mushroom", 0.65F, 0F);
	public static final EntityRenderData MISO_PORK = new EntityRenderData("food/wabowl_miso_pork", 0.65F, 0F);
	public static final EntityRenderData MISO_CARP = new EntityRenderData("food/wabowl_miso_carp", 0.65F, 0F);
	public static final EntityRenderData SHIRUKO = new EntityRenderData("food/wabowl_shiruko", 0.65F, 0F);

	public static final EntityRenderData MISO_TOFU_LAYER = new EntityRenderData("food/layer/wabowl_miso_tofu_layer", 0.75F, 0F);
	public static final EntityRenderData MISO_AGE_LAYER = new EntityRenderData("food/layer/wabowl_miso_age_layer", 0.75F, 0F);
	public static final EntityRenderData MISO_EGGPLANT_LAYER = new EntityRenderData("food/layer/wabowl_miso_eggplant_layer", 0.75F, 0F);
	public static final EntityRenderData MISO_MUSHROOM_LAYER = new EntityRenderData("food/layer/wabowl_miso_mushroom_layer", 0.75F, 0F);
	public static final EntityRenderData MISO_PORK_LAYER = new EntityRenderData("food/layer/wabowl_miso_pork_layer", 0.75F, 0F);
	public static final EntityRenderData MISO_CARP_LAYER = new EntityRenderData("food/layer/wabowl_miso_carp_layer", 0.75F, 0F);
	public static final EntityRenderData SHIRUKO_LAYER = new EntityRenderData("food/layer/wabowl_shiruko_layer", 0.75F, 0F);

}
