package defeatedcrow.hac.core.material.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.client.armor.RenderArmorDC;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ArmorItemDC extends ArmorItem implements IJsonDataDC, IItemDC {

	final String name;
	final ModelType type;
	final ArmorMaterial material;
	private String armorName;

	public ArmorItemDC(String n, ArmorMaterial mat, EquipmentSlot slot, ModelType t) {
		super(mat, slot, (new Item.Properties()).tab(CoreInit.CLOTH));
		name = n;
		type = t;
		material = mat;
		armorName = name;
	}

	public ArmorItemDC setArmorName() {
		armorName = "armor_" + material.getName();
		return this;
	}

	public ArmorItemDC setArmorName(String s) {
		armorName = s;
		return this;
	}

	@Override
	public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
		return stack.is(CoreInit.BOOTS_SAFETY.get());
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (item.getItem() == CoreInit.HAT_SAFETY.get()) {
			MutableComponent s = Component.translatable("dcs.tip.helmet_safety");
			s.withStyle(ChatFormatting.GRAY);
			list.add(s);
		}
		if (item.getItem() == CoreInit.LEGGINS_WADERS.get()) {
			MutableComponent s = Component.translatable("dcs.tip.waders_rubber");
			s.withStyle(ChatFormatting.GRAY);
			list.add(s);
		}
		if (item.getItem() == CoreInit.BOOTS_SAFETY.get()) {
			MutableComponent s = Component.translatable("dcs.tip.boots_safety");
			s.withStyle(ChatFormatting.GRAY);
			list.add(s);
		}
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
		consumer.accept(RenderArmorDC.RENDERER);
	}

	@Override
	@Nullable
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String typeIn) {
		return "dcs_climate:textures/entity/armor/" + armorName + ".png";
	}

	@Override
	public String getRegistryName() {
		return "main/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/armor/" + name));
	}

	@Override
	public BlockType getDropType() {
		return BlockType.ITEM;
	}

	@Override
	public TagKey<Item> getPairTag() {
		return TagDC.ItemTag.DUMMY;
	}

	public ModelType getType() {
		return type;
	}

	public enum ModelType {
		BOOTS,
		SKIRT,
		LEGGINS,
		SHIRT,
		JACKET,
		OVERSUITS,
		SUITS,
		HAT,
		LONG,
		DEFAULT
	}

}
