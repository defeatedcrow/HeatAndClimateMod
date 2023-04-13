package defeatedcrow.hac.core.material.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.core.client.armor.RenderArmorDC;
import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.IItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ArmorItemDC extends ArmorItem implements IJsonDataDC, IItemDC {

	final String name;
	final ModelType type;
	final ArmorMaterial material;

	public ArmorItemDC(String n, ArmorMaterial mat, EquipmentSlot slot, ModelType t) {
		super(mat, slot, (new Item.Properties()).tab(CoreInit.CLOTH));
		name = n;
		type = t;
		material = mat;
	}

	@Override
	public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
		consumer.accept(RenderArmorDC.RENDERER);
	}

	@Override
	@Nullable
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String typeIn) {
		if (type == ModelType.DEFAULT) {
			return "dcs_climate:textures/entity/armor/armor_" + material.getName() + ".png";
		}
		return "dcs_climate:textures/entity/armor/" + name + ".png";
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
		LEGGINS,
		SHIRT,
		JACKET,
		SUITS,
		HAT,
		LONG,
		DEFAULT
	}

}
