package defeatedcrow.hac.main.item.tool;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHoeDC extends ItemHoe implements ITexturePath {

	private final float attackDam;
	private final String tex;

	public ItemHoeDC(ToolMaterial m, String t) {
		super(m);
		tex = t;
		this.attackDam = 1.0F + m.getAttackDamage();
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/rake_" + tex;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

		if (slot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Weapon modifier", this.attackDam - 2.0F, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Weapon modifier", -3.0D, 0));
		}

		return multimap;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		if (target == null || target.world.isRemote) {
			return false;
		}
		if (target.isBeingRidden()) {
			target.getRidingEntity().dismountRidingEntity();
		} else if (target.isRiding()) {
			target.dismountRidingEntity();
		}
		return true;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		} else {
			boolean suc = false;
			for (int z = -1; z <= 1; z++) {
				for (int x = -1; x <= 1; x++) {
					BlockPos p = pos.add(x, 0, z);
					int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, player, world, p);
					if (hook != 0) {
						suc = hook > 0;
					} else {
						IBlockState state = world.getBlockState(p);
						Block block = state.getBlock();
						if (facing != EnumFacing.DOWN && world.isAirBlock(p.up())) {
							if (block == Blocks.GRASS || block == Blocks.GRASS_PATH || block == Blocks.DIRT) {
								this.setBlock(stack, player, world, p, Blocks.FARMLAND.getDefaultState());
								suc = true;
							}
						}
					}
				}
			}
			if (suc) {
				if (!player.capabilities.isCreativeMode && (player.world
						.getDifficulty() != EnumDifficulty.PEACEFUL || CoreConfigDC.peacefulDam)) {
					player.addExhaustion(0.1F);
				}
			}
			return suc ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(I18n.format("dcs.tip.toolsteel.hoe1"));
		tooltip.add(EnumFixedName.RIGHT_CLICK.getLocalizedName() + ": " + I18n.format("dcs.tip.toolsteel.hoe2"));
	}

}
