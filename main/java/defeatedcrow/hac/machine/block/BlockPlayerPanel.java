package defeatedcrow.hac.machine.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCTileBlockFaced;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlayerPanel extends DCTileBlockFaced {
	private static BlockPos lastPos = null;
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

	public BlockPlayerPanel(String s) {
		super(Material.CLAY, s, 3);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TilePlayerPanel();
	}
	/* pos登録 */

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			if (MainUtil.isHeldWrench(player, hand)) {
				TileEntity tile = world.getTileEntity(pos);
				// achievement
				if (tile != null && tile instanceof TilePlayerPanel) {
					if (!world.isRemote) {
						if (lastPos == null) {
							lastPos = pos;
							String mes1 = "Stored this coordinate: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
							player.sendMessage(new TextComponentString(mes1));
						} else {
							((TilePlayerPanel) tile).setPairPos(new BlockPos(lastPos.getX(), lastPos.getY(), lastPos.getZ()));
							if (world.getTileEntity(lastPos) instanceof TilePlayerPanel) {
								if (((TilePlayerPanel) world.getTileEntity(lastPos)).getPairPos() == null) {
									((TilePlayerPanel) world.getTileEntity(lastPos)).setPairPos(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
								}
							}
							String mes2 = "Registered the coordinate: " + lastPos.getX() + ", " + lastPos.getY() + ", " + lastPos.getZ();
							player.sendMessage(new TextComponentString(mes2));
							lastPos = null;
						}
					}
					return true;
				}
			}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	/* Tick更新処理 */

	@Override
	public int tickRate(World worldIn) {
		return 20;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		world.scheduleUpdate(pos, this, this.tickRate(world));
		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && state != null && state.getBlock() != null) {
			DCHeatTier heat = ClimateAPI.calculator.getAverageTemp(world, pos);
			int step = DCState.getInt(state, DCState.TYPE4);
			boolean cool = heat.getTier() < 0;
			int count = 0;
			if (cool) {
				count += 1;
			}
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TilePlayerPanel && ((TilePlayerPanel) tile).isTargetActive()) {
				count += 2;
			}
			if (step != count) {
				changeLitState(world, pos, count);
			}
			world.scheduleUpdate(pos, this, this.tickRate(world));
		}
	}

	public static void changeLitState(World world, BlockPos pos, int i) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof BlockPlayerPanel) {
			world.setBlockState(pos, state.withProperty(DCState.TYPE4, i), 3);
			world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		int step = DCState.getInt(state, DCState.TYPE4);
		if (step == 3) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TilePlayerPanel) {
				TilePlayerPanel panel = (TilePlayerPanel) world.getTileEntity(pos);
				if (entity != null && entity instanceof EntityPlayer) {
					if (!world.isRemote) {
						if (panel.cooltime > 0) {
							panel.cooltime--;
						} else {
							BlockPos p = panel.getWarpPos();
							BlockPos pair = panel.getPairPos();
							if (p != null) {
								entity.setPositionAndUpdate(p.getX() + 0.5D, p.getY() + 0.25D, p.getZ() + 0.5D);
								entity.fallDistance = 0.0F;
							}
							panel.cooltime = 40;
						}
					} else {
						int c = ClimateMain.proxy.getParticleCount();
						if (ClimateMain.proxy.getParticleCount() > 0) {
							for (int i = 0; i < 3; i++) {
								double x = pos.getX() + rand.nextDouble();
								double y = pos.getY() + rand.nextDouble() * 0.5D;
								double z = pos.getZ() + rand.nextDouble();
								Particle p = new ParticleBlink.Factory().createParticle(0, world, x, y, z, 0.0D, 0.15D, 0.0D, new int[0]);
								FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);
							}
						}
					}
				} else if (panel.cooltime != 40) {
					panel.cooltime = 40;
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.BLUE.toString() + "COOL-");
			tooltip.add(I18n.format("dcs.tip.adapter"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}
	// drop

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		world.removeTileEntity(pos);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}
}
