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

public class PorridgeItem extends ItemEntityFood implements IPotFoods {

	private final boolean isCold;

	public PorridgeItem(String s, int nut, float sat, boolean cold, TagKey<Item> pair) {
		super(s, prop(nut, sat, cold), !cold, pair);
		isCold = cold;
	}

	private static Properties prop(int nut, float sat, boolean isCold) {
		if (isCold)
			return new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().build());
		else
			return new Item.Properties().tab(FoodInit.FOOD).food(new FoodProperties.Builder().nutrition(nut).saturationMod(sat).alwaysEat().effect(hot(), 1.0F).build());
	}

	private static Supplier<MobEffectInstance> hot() {
		return () -> new MobEffectInstance(CoreInit.COLD_RESISTANCE.get(), 600);
	}

	private static Supplier<MobEffectInstance> cold() {
		return () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.STEW.get();
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
		if (item == FoodInit.PORRIDGE.get())
			return PORRIDGE;
		if (item == FoodInit.PORRIDGE_MILK.get())
			return PORRIDGE_MILK;
		if (item == FoodInit.PORRIDGE_SAFFRON.get())
			return PORRIDGE_SAFFRON;
		if (item == FoodInit.PORRIDGE_SQUID.get())
			return PORRIDGE_SQUID;
		if (item == FoodInit.MUESLI.get())
			return MUESLI;
		if (item == FoodInit.STEW_BORSCH.get())
			return STEW_BORSCH;
		if (item == FoodInit.STEW_IRISH.get())
			return STEW_IRISH;
		if (item == FoodInit.STEW_CONSOMME_VEGETABLE.get())
			return STEW_CONSOMME_VEGETABLE;
		if (item == FoodInit.STEW_CREAM_MUSHROOM.get())
			return STEW_CREAM_MUSHROOM;
		if (item == FoodInit.STEW_CREAM_SALMON.get())
			return STEW_CREAM_SALMON;
		if (item == FoodInit.STEW_CREAM_SHRIMP.get())
			return STEW_CREAM_SHRIMP;
		if (item == FoodInit.STEW_CULLEN_SKINK.get())
			return STEW_CULLEN_SKINK;
		if (item == FoodInit.STEW_KHARCHO.get())
			return STEW_KHARCHO;
		if (item == FoodInit.STEW_TOMYUMGOONG.get())
			return STEW_TOMYUMGOONG;
		if (item == FoodInit.STEW_TOMYUMPLA.get())
			return STEW_TOMYUMPLA;
		if (item == FoodInit.STEW_TOMYUMGAI.get())
			return STEW_TOMYUMGAI;
		if (item == FoodInit.STEW_BAKKUTTEH.get())
			return STEW_BAKKUTTEH;
		if (item == FoodInit.STEW_MISO_TOFU.get())
			return STEW_MISO_TOFU;
		if (item == FoodInit.STEW_MISO_EGGPLANT.get())
			return STEW_MISO_EGGPLANT;
		if (item == FoodInit.STEW_MISO_MUSHROOM.get())
			return STEW_MISO_MUSHROOM;
		if (item == FoodInit.STEW_MISO_PORK.get())
			return STEW_MISO_PORK;
		if (item == FoodInit.STEW_ERWTEN.get())
			return STEW_ERWTEN;
		if (item == FoodInit.STEW_LAMPREDOTTO.get())
			return STEW_LAMPREDOTTO;
		if (item == FoodInit.STEW_MOTU.get())
			return STEW_OFFAL;
		return PORRIDGE;
	}

	@Override
	public EntityRenderData getPotTexture(Item item) {
		if (item == FoodInit.PORRIDGE.get())
			return PORRIDGE_LAYER;
		if (item == FoodInit.PORRIDGE_MILK.get())
			return PORRIDGE_MILK_LAYER;
		if (item == FoodInit.PORRIDGE_SAFFRON.get())
			return PORRIDGE_SAFFRON_LAYER;
		if (item == FoodInit.PORRIDGE_SQUID.get())
			return PORRIDGE_SQUID_LAYER;
		if (item == FoodInit.MUESLI.get())
			return MUESLI_LAYER;
		if (item == FoodInit.STEW_BORSCH.get())
			return STEW_BORSCH_LAYER;
		if (item == FoodInit.STEW_IRISH.get())
			return STEW_IRISH_LAYER;
		if (item == FoodInit.STEW_CONSOMME_VEGETABLE.get())
			return STEW_CONSOMME_VEGETABLE_LAYER;
		if (item == FoodInit.STEW_CREAM_MUSHROOM.get())
			return STEW_CREAM_MUSHROOM_LAYER;
		if (item == FoodInit.STEW_CREAM_SALMON.get())
			return STEW_CREAM_SALMON_LAYER;
		if (item == FoodInit.STEW_CREAM_SHRIMP.get())
			return STEW_CREAM_SHRIMP_LAYER;
		if (item == FoodInit.STEW_CULLEN_SKINK.get())
			return STEW_CULLEN_SKINK_LAYER;
		if (item == FoodInit.STEW_KHARCHO.get())
			return STEW_KHARCHO_LAYER;
		if (item == FoodInit.STEW_TOMYUMGOONG.get())
			return STEW_TOMYUMGOONG_LAYER;
		if (item == FoodInit.STEW_TOMYUMPLA.get())
			return STEW_TOMYUMPLA_LAYER;
		if (item == FoodInit.STEW_TOMYUMGAI.get())
			return STEW_TOMYUMGAI_LAYER;
		if (item == FoodInit.STEW_BAKKUTTEH.get())
			return STEW_BAKKUTTEH_LAYER;
		if (item == FoodInit.STEW_MISO_TOFU.get())
			return STEW_MISO_TOFU_LAYER;
		if (item == FoodInit.STEW_MISO_EGGPLANT.get())
			return STEW_MISO_EGGPLANT_LAYER;
		if (item == FoodInit.STEW_MISO_MUSHROOM.get())
			return STEW_MISO_MUSHROOM_LAYER;
		if (item == FoodInit.STEW_MISO_PORK.get())
			return STEW_MISO_PORK_LAYER;
		if (item == FoodInit.STEW_ERWTEN.get())
			return STEW_ERWTEN_LAYER;
		if (item == FoodInit.STEW_LAMPREDOTTO.get())
			return STEW_LAMPREDOTTO_LAYER;
		if (item == FoodInit.STEW_MOTU.get())
			return STEW_OFFAL_LAYER;
		return PORRIDGE_LAYER;
	}

	public static final EntityRenderData PORRIDGE = new EntityRenderData("food/porridge_simple", 0.75F, 0F, "outer");
	public static final EntityRenderData PORRIDGE_MILK = new EntityRenderData("food/porridge_milk", 0.75F, 0F, "outer");
	public static final EntityRenderData PORRIDGE_SAFFRON = new EntityRenderData("food/porridge_saffron", 0.75F, 0F, "outer");
	public static final EntityRenderData PORRIDGE_SQUID = new EntityRenderData("food/porridge_squid", 0.75F, 0F, "outer");
	public static final EntityRenderData MUESLI = new EntityRenderData("food/muesli", 0.75F, 0F, "outer");
	public static final EntityRenderData STEW_BORSCH = new EntityRenderData("food/stew_borsch", 0.75F, 0F);
	public static final EntityRenderData STEW_IRISH = new EntityRenderData("food/stew_irish", 0.75F, 0F);
	public static final EntityRenderData STEW_CONSOMME_VEGETABLE = new EntityRenderData("food/stew_consomme_vegetable", 0.75F, 0F);
	public static final EntityRenderData STEW_CREAM_MUSHROOM = new EntityRenderData("food/stew_cream_mushroom", 0.75F, 0F);
	public static final EntityRenderData STEW_CREAM_SALMON = new EntityRenderData("food/stew_cream_salmon", 0.75F, 0F);
	public static final EntityRenderData STEW_CREAM_SHRIMP = new EntityRenderData("food/stew_cream_shrimp", 0.75F, 0F);
	public static final EntityRenderData STEW_CULLEN_SKINK = new EntityRenderData("food/stew_cullenskink", 0.75F, 0F);
	public static final EntityRenderData STEW_KHARCHO = new EntityRenderData("food/stew_kharcho", 0.75F, 0F);
	public static final EntityRenderData STEW_TOMYUMGOONG = new EntityRenderData("food/stew_tomyumgoong", 0.75F, 0F);
	public static final EntityRenderData STEW_TOMYUMPLA = new EntityRenderData("food/stew_tomyumpla", 0.75F, 0F);
	public static final EntityRenderData STEW_TOMYUMGAI = new EntityRenderData("food/stew_tomyumgai", 0.75F, 0F);
	public static final EntityRenderData STEW_BAKKUTTEH = new EntityRenderData("food/stew_bakkutteh", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_TOFU = new EntityRenderData("food/stew_miso_tofu", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_EGGPLANT = new EntityRenderData("food/stew_miso_eggplant", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_MUSHROOM = new EntityRenderData("food/stew_miso_mushroom", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_PORK = new EntityRenderData("food/stew_miso_pork", 0.75F, 0F);
	public static final EntityRenderData STEW_ERWTEN = new EntityRenderData("food/stew_erwten", 0.75F, 0F);
	public static final EntityRenderData STEW_LAMPREDOTTO = new EntityRenderData("food/stew_lampredotto", 0.75F, 0F);
	public static final EntityRenderData STEW_OFFAL = new EntityRenderData("food/stew_offal", 0.75F, 0F, "outer");

	public static final EntityRenderData PORRIDGE_LAYER = new EntityRenderData("food/layer/porridge_simple_layer", 1F, 0F);
	public static final EntityRenderData PORRIDGE_MILK_LAYER = new EntityRenderData("food/layer/porridge_milk_layer", 1F, 0F);
	public static final EntityRenderData PORRIDGE_SAFFRON_LAYER = new EntityRenderData("food/layer/porridge_saffron_layer", 0.75F, 0F);
	public static final EntityRenderData PORRIDGE_SQUID_LAYER = new EntityRenderData("food/layer/porridge_squid_layer", 1F, 0F);
	public static final EntityRenderData MUESLI_LAYER = new EntityRenderData("food/layer/muesli_layer", 1F, 0F);
	public static final EntityRenderData STEW_BORSCH_LAYER = new EntityRenderData("food/layer/stew_borsch_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_IRISH_LAYER = new EntityRenderData("food/layer/stew_irish_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_CONSOMME_VEGETABLE_LAYER = new EntityRenderData("food/layer/stew_consomme_vegetable_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_CREAM_MUSHROOM_LAYER = new EntityRenderData("food/layer/stew_cream_mushroom_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_CREAM_SALMON_LAYER = new EntityRenderData("food/layer/stew_cream_salmon_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_CREAM_SHRIMP_LAYER = new EntityRenderData("food/layer/stew_cream_shrimp_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_CULLEN_SKINK_LAYER = new EntityRenderData("food/layer/stew_cullenskink_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_KHARCHO_LAYER = new EntityRenderData("food/layer/stew_kharcho_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_TOMYUMGOONG_LAYER = new EntityRenderData("food/layer/stew_tomyumgoong_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_TOMYUMPLA_LAYER = new EntityRenderData("food/layer/stew_tomyumpla_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_TOMYUMGAI_LAYER = new EntityRenderData("food/layer/stew_tomyumgai_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_BAKKUTTEH_LAYER = new EntityRenderData("food/layer/stew_bakkutteh_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_TOFU_LAYER = new EntityRenderData("food/layer/stew_miso_tofu_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_EGGPLANT_LAYER = new EntityRenderData("food/layer/stew_miso_eggplant_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_MUSHROOM_LAYER = new EntityRenderData("food/layer/stew_miso_mushroom_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_MISO_PORK_LAYER = new EntityRenderData("food/layer/stew_miso_pork_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_ERWTEN_LAYER = new EntityRenderData("food/layer/stew_erwten_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_LAMPREDOTTO_LAYER = new EntityRenderData("food/layer/stew_lampredotto_layer", 0.75F, 0F);
	public static final EntityRenderData STEW_OFFAL_LAYER = new EntityRenderData("food/layer/stew_offal_layer", 0.75F, 0F, "outer");

}
