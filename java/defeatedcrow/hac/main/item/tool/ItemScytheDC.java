package defeatedcrow.hac.main.item.tool;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
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
		this.attackDam = 3.0F + m.getDamageVsEntity();
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
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(),
					new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDam, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.5D, 0));
		}

		return multimap;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL
				&& material != Material.LEAVES && material != Material.GOURD ? 1.0F : 15.0F;
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
					if (getStrVsBlock(stack, target) >= 5.0F && !(target.getBlock() instanceof BlockStem)) {

						boolean flag = true;
						if (target.getBlock() instanceof IGrowable) {
							flag = ((IGrowable) target.getBlock()).canGrow(world, p1, target, false);
						}

						if (target.getBlock() instanceof IShearable) {
							flag = !((IShearable) target.getBlock()).isShearable(stack, world, p1);
						}

						if (!flag) {
							if (living != null && living instanceof EntityPlayer) {
								target.getBlock().harvestBlock(world, (EntityPlayer) living, p1, target, null, stack);
								target.getBlock().removedByPlayer(target, world, p1, (EntityPlayer) living, false);
							} else {
								target.getBlock().dropBlockAsItem(world, p1, target, 0);
								target.getBlock().removedByPlayer(target, world, p1, null, false);
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add("Range: " + (this.range + 1));
	}
}
