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
		if (EntityModelLoader.INSTANCE.MODEL_BOOTS != null && !DCUtil.isEmpty(stack) && stack.getItem() instanceof ArmorItemDC armor) {
			switch (armor.getType()) {
			case BOOTS:
				return EntityModelLoader.INSTANCE.MODEL_BOOTS;
			case SKIRT:
				return EntityModelLoader.INSTANCE.MODEL_SKIRT;
			case HAT:
				return EntityModelLoader.INSTANCE.MODEL_HAT;
			case JACKET:
				return EntityModelLoader.INSTANCE.MODEL_JACKET;
			case LEGGINS:
				return EntityModelLoader.INSTANCE.MODEL_LEGGINS;
			case SHIRT:
				return EntityModelLoader.INSTANCE.MODEL_SHIRT;
			case OVERSUITS:
				return EntityModelLoader.INSTANCE.MODEL_OVERSUITS;
			case SUITS:
				return EntityModelLoader.INSTANCE.MODEL_SUITS;
			case LONG:
				return EntityModelLoader.INSTANCE.MODEL_DRESS;
			default:
				return original;
			}
		}
		return original;
	}

	@Override
	@NotNull
	public Model getGenericArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
		HumanoidModel<?> rep = getHumanoidArmorModel(living, stack, slot, original);
		if (rep != original) {
			ForgeHooksClient.copyModelProperties(original, rep);
			if (rep == EntityModelLoader.INSTANCE.MODEL_SKIRT || rep == EntityModelLoader.INSTANCE.MODEL_LEGGINS) {
				rep.body.visible = true;
				rep.rightArm.visible = false;
				rep.leftArm.visible = false;
				rep.rightLeg.visible = true;
				rep.leftLeg.visible = true;
			}
			if (rep == EntityModelLoader.INSTANCE.MODEL_SHIRT) {
				rep.body.visible = true;
				rep.rightArm.visible = true;
				rep.leftArm.visible = true;
				rep.rightLeg.visible = false;
				rep.leftLeg.visible = false;
			}
			if (rep == EntityModelLoader.INSTANCE.MODEL_SUITS || rep == EntityModelLoader.INSTANCE.MODEL_OVERSUITS || rep == EntityModelLoader.INSTANCE.MODEL_DRESS) {
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
