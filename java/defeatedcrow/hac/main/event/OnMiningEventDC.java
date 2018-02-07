package defeatedcrow.hac.main.event;

import java.util.Random;

import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.item.tool.ItemScytheDC;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnMiningEventDC {

	private final Random rand = new Random();

	@SubscribeEvent
	public void preMining(PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer() != null) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = event.getEntityPlayer().inventory.getStackInSlot(i);
				if (!DCUtil.isEmpty(check) && check.getItem() != null && check.getItem() == MagicInit.pendant) {
					int m = check.getMetadata();
					if (m == 9) {
						hasCharm = true;
					}
				}
			}
			if (hasCharm) {
				event.setNewSpeed(event.getNewSpeed() * 1.2F + 2.0F);
				event.setCanceled(false);
			}
		}
	}

	@SubscribeEvent
	public void onMining(BlockEvent.HarvestDropsEvent event) {
		if (event.getHarvester() != null && !event.getWorld().isRemote) {
			IBlockState state = event.getState();
			ItemStack held = event.getHarvester().getHeldItemMainhand();
			int level = event.getFortuneLevel() + 1;
			if (state == null || DCUtil.isEmpty(held))
				return;

			float f = event.getWorld().rand.nextFloat();
			if (state.getBlock() instanceof BlockBush
					&& (held.getItem() instanceof ItemShears || held.getItem() instanceof ItemSword)) {
				if (f < 0.10F * level) {
					event.getDrops().add(new ItemStack(FoodInit.crops, 1, 9));
				}
			}
		}
	}

	// 右クリック回収機構
	// Blockにターゲットした場合
	@SubscribeEvent
	public void onRightClick(PlayerInteractEvent.RightClickBlock event) {
		EntityPlayer player = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		ItemStack stack = event.getItemStack();
		if (player != null && stack != null) {
			if (stack.getItem() instanceof ItemScytheDC) {
				if (!player.worldObj.isRemote) {
					boolean b = false;
					int area = ((ItemScytheDC) stack.getItem()).range;
					for (int x = -area; x <= area; x++) {
						for (int z = -area; z <= area; z++) {
							for (int y = -area; y <= area; y++) {
								BlockPos p1 = pos.add(x, y, z);
								IBlockState target = player.worldObj.getBlockState(p1);
								if (target.getBlock() instanceof IClimateCrop) {
									((IClimateCrop) target.getBlock()).harvest(player.worldObj, p1, target, player);
									b = true;
								}
							}
						}
					}
					if (b) {
						stack.damageItem(1, player);
						event.setUseBlock(Result.ALLOW);
					}
				}

				player.worldObj.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SHEEP_SHEAR,
						SoundCategory.PLAYERS, 1.5F, 1.5F / (player.worldObj.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);

			} else if (player.isSneaking() && stack.getItem() instanceof ItemPickaxe) {
				ItemPickaxe pic = (ItemPickaxe) stack.getItem();
				if (pos.getY() > 1 && pic.getToolMaterial().getHarvestLevel() >= 4) {
					IBlockState state = event.getWorld().getBlockState(pos);
					if (state != null && state.getBlock() == Blocks.BEDROCK) {
						ItemStack item = new ItemStack(Blocks.BEDROCK);
						EntityItem drop = new EntityItem(event.getWorld(), pos.getX() + 0.5D, pos.getY() + 0.5D,
								pos.getZ() + 0.5D, item);
						if (!event.getWorld().isRemote) {
							event.getWorld().setBlockToAir(pos);
							event.getWorld().spawnEntityInWorld(drop);
							stack.damageItem(1, event.getEntityLiving());
							event.setUseBlock(Result.ALLOW);
						}
						player.worldObj.playSound(player, player.posX, player.posY, player.posZ,
								SoundEvents.BLOCK_STONE_BREAK, SoundCategory.PLAYERS, 1.5F,
								1.0F / (player.worldObj.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
					}
				}
			}
		}
	}

}
