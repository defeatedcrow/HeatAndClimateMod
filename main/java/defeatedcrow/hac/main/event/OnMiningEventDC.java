package defeatedcrow.hac.main.event;

import java.util.Random;

import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.magic.event.MagicCommonEvent;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.block.device.BlockFirestand;
import defeatedcrow.hac.main.item.tool.ItemScytheDC;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.CreateFluidSourceEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnMiningEventDC {

	private final Random rand = new Random();

	@SubscribeEvent
	public void preMining(PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer() != null) {
			if (DCUtil.hasCharmItem(event.getEntityPlayer(), new ItemStack(MagicInit.colorPendant, 1, 2))) {
				float lv = 2.0F + MainUtil.getCharmLevel(event.getEntityPlayer(), new ItemStack(MagicInit.colorPendant,
						1, 2));
				event.setNewSpeed(event.getNewSpeed() * lv);
			} else {
				if (event.getEntityPlayer().isInsideOfMaterial(Material.WATER)) {
					float lv = 1.0F;
					if (event.getEntityPlayer().getActivePotionEffect(MainInit.ocean) != null) {
						lv += event.getEntityPlayer().getActivePotionEffect(MainInit.ocean).getAmplifier() * 1.0F;

					}
					int c = MainUtil.getCharmLevel(event.getEntityPlayer(), new ItemStack(MagicInit.colorPendant2, 1,
							0));
					lv += c * 1F;
					event.setNewSpeed(event.getNewSpeed() * lv);
				}
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
			if (state.getBlock() instanceof BlockBush && (held.getItem() instanceof ItemShears || held
					.getItem() instanceof ItemSword)) {
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
		if (player != null && !DCUtil.isEmpty(stack)) {
			if (stack.getItem() instanceof ItemScytheDC) {
				// jewel
				if (MagicCommonEvent.getOffhandJewelColor(player) == MagicColor.BLUE)
					return;
				if (!player.world.isRemote) {
					boolean b = false;
					int area = ((ItemScytheDC) stack.getItem()).range;
					for (int x = -area; x <= area; x++) {
						for (int z = -area; z <= area; z++) {
							for (int y = -area; y <= area; y++) {
								BlockPos p1 = pos.add(x, y, z);
								IBlockState target = player.world.getBlockState(p1);
								if (target.getBlock() instanceof IClimateCrop) {
									((IClimateCrop) target.getBlock()).harvest(player.world, p1, target, player);
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

				player.world
						.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.5F, 1.5F / (player.world.rand
								.nextFloat() * 0.4F + 1.2F) + 0.5F);

			} else if (player.isSneaking() && stack.getItem() instanceof ItemPickaxe) {
				ItemPickaxe pic = (ItemPickaxe) stack.getItem();
				IBlockState state = event.getWorld().getBlockState(pos);
				if (pos.getY() > 0 && pic.getHarvestLevel(stack, "pickaxe", player, state) >= 4) {
					if (state != null && state.getBlock() == Blocks.BEDROCK && player
							.canPlayerEdit(pos, EnumFacing.UP, stack)) {
						ItemStack item = new ItemStack(Blocks.BEDROCK);
						EntityItem drop = new EntityItem(event.getWorld(), pos.getX() + 0.5D, pos.getY() + 0.5D, pos
								.getZ() + 0.5D, item);
						if (!event.getWorld().isRemote) {
							event.getWorld().setBlockToAir(pos);
							event.getWorld().spawnEntity(drop);
							stack.damageItem(1, event.getEntityLiving());
							event.setUseBlock(Result.ALLOW);
						}
						player.world
								.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.PLAYERS, 1.5F, 1.0F / (player.world.rand
										.nextFloat() * 0.4F + 1.2F) + 0.5F);
					}
				}
			}
		}
	}

	// 無限水源化
	@SubscribeEvent
	public void canCreateSource(CreateFluidSourceEvent event) {
		IBlockState fluid = event.getWorld().getBlockState(event.getPos());
		if (fluid.getBlock() == MainInit.hotSpringBlock) {
			event.setResult(Result.ALLOW);
		}
	}

	@SubscribeEvent
	public void onFirePlace(BlockEvent.PlaceEvent event) {
		IBlockState placed = event.getPlacedBlock();
		IBlockState against = event.getPlacedAgainst();
		if (placed.getBlock() == Blocks.FIRE && against.getBlock() == MainInit.firestand) {
			if (!event.getWorld().isRemote) {
				BlockPos p = null;
				for (EnumFacing f : EnumFacing.VALUES) {
					if (event.getWorld().getBlockState(event.getPos().offset(f)).getBlock() == MainInit.firestand) {
						p = event.getPos().offset(f);
					}
				}
				if (p != null) {
					BlockFirestand.changeLitState(event.getWorld(), p, true);
				}
				event.getPlayer().playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 0.8F, 1.0F);
			}
			event.setCanceled(true);
		}
	}

}
