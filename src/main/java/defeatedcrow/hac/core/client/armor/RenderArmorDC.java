package defeatedcrow.hac.core.client.armor;

import org.jetbrains.annotations.NotNull;

import defeatedcrow.hac.core.client.entity.EntityModelLoader;
import defeatedcrow.hac.core.material.item.armor.ArmorItemDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class RenderArmorDC implements IClientItemExtensions {

	public static final RenderArmorDC RENDERER = new RenderArmorDC();

	@Override
	@NotNull
	public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
		if (EntityModelLoader.MODEL_BOOTS != null && !DCUtil.isEmpty(stack) && stack.getItem() instanceof ArmorItemDC armor) {
			return switch (armor.getType()) {
			case BOOTS -> EntityModelLoader.MODEL_BOOTS;
			case SKIRT -> EntityModelLoader.MODEL_SKIRT;
			case HAT -> EntityModelLoader.MODEL_HAT;
			case HAIR -> EntityModelLoader.MODEL_HAIR;
			case HAIR2 -> EntityModelLoader.MODEL_HAIR2;
			case JACKET -> EntityModelLoader.MODEL_JACKET;
			case TUNIC -> EntityModelLoader.MODEL_TUNIC;
			case LEGGINS -> EntityModelLoader.MODEL_LEGGINS;
			case SHIRT -> EntityModelLoader.MODEL_SHIRT;
			case OVERSUITS -> EntityModelLoader.MODEL_OVERSUITS;
			case SUITS -> EntityModelLoader.MODEL_SUITS;
			case LONG -> EntityModelLoader.MODEL_DRESS;
			default -> original;
			};
		}
		return original;
	}

	@Override
	@NotNull
	public Model getGenericArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
		HumanoidModel<?> rep = getHumanoidArmorModel(living, stack, slot, original);
		if (rep != original) {
			ForgeHooksClient.copyModelProperties(original, rep);
			if (rep == EntityModelLoader.MODEL_SKIRT || rep == EntityModelLoader.MODEL_LEGGINS) {
				rep.body.visible = true;
				rep.rightArm.visible = false;
				rep.leftArm.visible = false;
				rep.rightLeg.visible = true;
				rep.leftLeg.visible = true;
			}
			if (rep == EntityModelLoader.MODEL_SHIRT) {
				rep.body.visible = true;
				rep.rightArm.visible = true;
				rep.leftArm.visible = true;
				rep.rightLeg.visible = false;
				rep.leftLeg.visible = false;
			}
			if (rep == EntityModelLoader.MODEL_SUITS || rep == EntityModelLoader.MODEL_TUNIC ||
			    rep == EntityModelLoader.MODEL_OVERSUITS || rep == EntityModelLoader.MODEL_DRESS) {
				rep.body.visible = true;
				rep.rightArm.visible = true;
				rep.leftArm.visible = true;
				rep.rightLeg.visible = true;
				rep.leftLeg.visible = true;
			}
			return rep;
		}
		return original;
	}

}
