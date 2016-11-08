package defeatedcrow.hac.magic.block;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMaceOcean extends ItemBlockMace {

	public ItemBlockMaceOcean(Block block) {
		super(block);
	}

	@Override
	protected void doUsingEffect(ItemStack stack, EntityPlayer player, World world) {
		if (stack != null && player != null) {
			boolean hasAcv = ClimateMain.proxy.hasAchivement(player, AchievementClimate.MAGIC_MASTER);
			boolean flag = player.capabilities.isCreativeMode;

			if (hasAcv || flag) {
				if (!world.isRemote) {
					player.addPotionEffect(new PotionEffect(MainInit.ocean, 3600, 0));
				}

				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP,
						SoundCategory.PLAYERS, 0.65F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

			} else {
				world.playSound(player, player.posX, player.posY, player.posZ,
						SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, SoundCategory.PLAYERS, 0.65F, 1.0F);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + "Require to be underwater");
		}
	}

}
