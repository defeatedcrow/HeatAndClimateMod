package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemChickInEgg extends DCItem {

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/brewing/chick_in_egg";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(item)) {
				if (!world.isRemote) {

					EntityChicken chicken = new EntityChicken(world);
					chicken.setGrowingAge(-24000);
					chicken.setLocationAndAngles(player.posX + world.rand
							.nextDouble() * 0.5D - 0.25D, player.posY + 0.15D, player.posZ + world.rand
									.nextDouble() * 0.5D - 0.25D, player.rotationYaw, 0.0F);
					world.spawnEntity(chicken);

				}
				if (!player.capabilities.isCreativeMode) {
					DCUtil.redAndDel(item, 1);
				}
				player.playSound(SoundEvents.ENTITY_EGG_THROW, 0.75F, 1.25F);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, ItemStack.EMPTY);
	}

}
