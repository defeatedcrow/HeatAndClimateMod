package defeatedcrow.hac.main.item.tool;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemScytheDC extends ItemSword implements ITexturePath {

	private final float attackDam;
	private final String tex;
	public int range;

	public ItemScytheDC(ToolMaterial m, String t) {
		super(m);
		tex = t;
		this.attackDam = 3.0F + m.getAttackDamage();
		this.range = m.getHarvestLevel();
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/scythe_" + tex;
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return true;
	}

	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (slot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Weapon modifier", this.attackDam, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Weapon modifier", -2.5D, 0));
		}

		return multimap;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD && material != Material.WEB ?
				1.0F : 15.0F;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase living) {
		// 範囲収穫
		int area = this.range + 1;

		for (int x = -area + 1; x < area; x++) {
			for (int z = -area + 1; z < area; z++) {
				for (int y = -1; y < 2; y++) {
					BlockPos p1 = pos.add(x, y, z);
					IBlockState target = world.getBlockState(p1);
					ItemStack tool = stack.copy();
					int fl = EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FORTUNE, tool);
					if (living.isSneaking()) {
						tool = new ItemStack(Items.SHEARS);
					}

					if (getDestroySpeed(tool, target) > 1.0F && !(target.getBlock() instanceof BlockStem)) {
						boolean flag = false;
						if (target.getBlock() instanceof IShearable) {
							if (((IShearable) target.getBlock()).isShearable(tool, world, p1))
								flag = true;
						}

						if (target.getBlock() instanceof IGrowable) {
							if (!((IGrowable) target.getBlock()).canGrow(world, p1, target, false))
								flag = true;
						}

						if (target.getBlock() == Blocks.PUMPKIN || target.getBlock() == Blocks.MELON_BLOCK) {
							flag = true;
						}

						if (target.getBlock() == Blocks.REEDS || target.getBlock() == Blocks.CACTUS) {
							IBlockState under = world.getBlockState(p1.down());
							if (under.getBlock() == target.getBlock()) {
								flag = true;
							}
						}

						if (flag) {
							if (target.getBlock() instanceof BlockTallGrass) {
								if (living.isSneaking()) {
									List<ItemStack> drops = ((IShearable) target.getBlock())
											.onSheared(tool, world, p1, fl);
									if (!world.isRemote) {
										for (ItemStack i : drops) {
											EntityItem entityitem = new EntityItem(world, p1.getX() + 0.5D, p1
													.getY() + 0.5D, p1.getZ() + 0.5D, i);
											entityitem.setDefaultPickupDelay();
											world.spawnEntity(entityitem);
										}
									}
								}
								NonNullList<ItemStack> l1 = NonNullList.create();
								target.getBlock().getDrops(l1, world, p1, target, fl);
								if (!world.isRemote) {
									for (ItemStack i : l1) {
										EntityItem entityitem = new EntityItem(world, p1.getX() + 0.5D, p1
												.getY() + 0.5D, p1.getZ() + 0.5D, i);
										entityitem.setDefaultPickupDelay();
										world.spawnEntity(entityitem);
									}
								}
							} else if (target.getBlock() instanceof IShearable) {
								List<ItemStack> drops = ((IShearable) target.getBlock()).onSheared(tool, world, p1, fl);
								if (!world.isRemote) {
									for (ItemStack i : drops) {
										EntityItem entityitem = new EntityItem(world, p1.getX() + 0.5D, p1
												.getY() + 0.5D, p1.getZ() + 0.5D, i);
										entityitem.setDefaultPickupDelay();
										world.spawnEntity(entityitem);
									}
								}
							} else if (target.getBlock() instanceof IGrowable) {
								NonNullList<ItemStack> l1 = NonNullList.create();
								target.getBlock().getDrops(l1, world, p1, target, fl);
								if (!world.isRemote) {
									for (ItemStack i : l1) {
										EntityItem entityitem = new EntityItem(world, p1.getX() + 0.5D, p1
												.getY() + 0.5D, p1.getZ() + 0.5D, i);
										entityitem.setDefaultPickupDelay();
										world.spawnEntity(entityitem);
									}
								}
							} else if (living instanceof EntityPlayer) {
								target.getBlock().harvestBlock(world, (EntityPlayer) living, p1, target, null, tool);
							} else {
								target.getBlock().dropBlockAsItem(world, p1, target, 0);
							}
							if (living instanceof EntityPlayer) {
								target.getBlock().removedByPlayer(target, world, p1, (EntityPlayer) living, false);
							} else {
								world.setBlockToAir(p1);
							}
						}
					}
				}
			}
		}
		// block.removedByPlayerをつかう
		if (state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, living);
		}

		return true;
	}

	// 毛刈り
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity,
			EnumHand hand) {
		if (entity == null || entity.world.isRemote || player == null) {
			return false;
		}
		if (player.isSneaking() && entity instanceof IShearable) {
			IShearable target = (IShearable) entity;
			BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
			if (target.isShearable(itemstack, entity.world, pos)) {
				List<ItemStack> drops = target.onSheared(itemstack, entity.world, pos, EnchantmentHelper
						.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));

				Random rand = Item.itemRand;
				for (ItemStack stack : drops) {
					EntityItem ent = entity.entityDropItem(stack, 1.0F);
					ent.motionY += rand.nextFloat() * 0.05F;
					ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
					ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				}
				player.world.playSound(player, entity
						.getPosition(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.5F, 1.5F / (player.world.rand
								.nextFloat() * 0.4F + 1.2F) + 0.5F);
				itemstack.damageItem(1, entity);
			}
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(I18n.format("dcs.tip.scythe1"));
		tooltip.add("Range: " + (this.range + 1));
		tooltip.add(I18n.format("dcs.tip.scythe2"));
	}

	/* ガントレット連携 */

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (ModuleConfig.magic_advanced) {
			ItemStack main = playerIn.getHeldItem(EnumHand.MAIN_HAND);
			ItemStack off = playerIn.getHeldItem(EnumHand.OFF_HAND);
			if (MainUtil.isHeldOffhandTool(new ItemStack(MagicInit.colorGauntlet, 1, 0), playerIn) && !DCUtil
					.isEmpty(main) && main.getItem() == this) {
				playerIn.setActiveHand(handIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, main);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	@Override
	public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
		if (ModuleConfig.magic_advanced) {
			if (MainUtil.isHeldOffhandTool(new ItemStack(MagicInit.colorGauntlet, 1, 0), entity) && !DCUtil
					.isEmpty(stack) && stack.getItem() == this) {
				return true;
			}
		}
		return false;
	}
}
