package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.machine.IPowerSource;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.OwnableBaseTileDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;

public class HydroTurbineTile extends OwnableBaseTileDC implements IPowerSource, IRenderBlockData {

	public HydroTurbineTile(BlockPos pos, BlockState state) {
		super(MachineInit.HYDRO_TURBINE_TILE.get(), pos, state);
	}

	public HydroTurbineTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, HydroTurbineTile tile) {
		tile.onTickProcess(level, pos, state);
	}

	public float lastRotation = 0F;
	public float rotation = 0F;
	public float speed = 0F;
	public float lastSpeed = 0F;

	public static void clientTick(Level level, BlockPos pos, BlockState state, HydroTurbineTile tile) {
		tile.lastSpeed = tile.speed;
		tile.lastRotation = tile.rotation;
		int h = DCState.getInt(state, DCState.STAGE9);
		if (h > 0) {
			tile.speed += h;
		} else {
			tile.speed -= 4;
		}
		tile.speed = Mth.clamp(tile.speed, 0F, h * 5F);
		tile.rotation += tile.lastSpeed;
		if (tile.rotation > 360F) {
			tile.rotation -= 360F;
			tile.lastRotation -= 360F;
		}
	}

	int count = 9;
	int lastHeight = 0;
	int hydroHeight = 0;
	private int lastPow = 0;
	private int power = 0;

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {

		power = lastPow * 2;

		if (count > 0) {
			count--;
		} else {
			count = 9;
			// fluid
			Direction dir = DCState.getFace(state, DCState.FACING);
			BlockPos front = pos.relative(dir);
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			// check1
			int height = 0;
			for (int h = 1; h < 32; h++) {
				mpos.setWithOffset(pos, 0, h, 0);
				if (mpos.getY() >= level.getMaxBuildHeight() || level.getBlockState(mpos).getBlock() instanceof HydroTurbineBlock) {
					break;
				}
				height = h;
			}

			// check2
			for (int h = 0; h < height; h++) {
				mpos.setWithOffset(front, 0, h, 0);
				FluidState fluidstate = level.getFluidState(mpos);
				if (fluidstate.isEmpty() || fluidstate.isSource()) {
					break;
				}
				hydroHeight = h;
			}

			if (lastHeight != hydroHeight) {
				lastHeight = hydroHeight;
				lastPow = hydroHeight;
				int p = Mth.floor(hydroHeight / 4F);
				if (p == 0 && hydroHeight > 0) {
					p = 1;
				}
				HydroTurbineBlock.changeLitState(level, pos, p);
			}
		}
		return false;
	}

	@Override
	public int getSteam() {
		return power;
	}

	@Override
	public int consumeSteam(int i) {
		int ret = Math.min(i, power);
		power -= ret;
		return ret;
	}

	@Override
	public AABB getRenderBoundingBox() {
		AABB bb = INFINITE_EXTENT_AABB;
		BlockPos pos = getBlockPos();
		bb = new AABB(pos.offset(-1, -1, -1), pos.offset(2, 2, 2));
		return bb;
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		lastPow = tag.getInt("dcs.last.power");
		lastHeight = tag.getInt("dcs.last.height");
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		tag.putInt("dcs.last.power", lastPow);
		tag.putInt("dcs.last.height", lastHeight);
	}

	// cap

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return null;
	}

	@Override
	public boolean hasMenu() {
		return false;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.machine.with_owner", this.ownerName) : Component.translatable("dcs.container.machine");
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		return NORMAL;
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("tile/hydro_turbine_tile", 1F, -0.5F);

}
