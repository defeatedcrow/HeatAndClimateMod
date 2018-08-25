package defeatedcrow.hac.plugin.forestry;

import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.core.base.ClimateCropBase;
import forestry.api.farming.ICrop;
import forestry.core.network.packets.PacketFXSignal;
import forestry.core.network.packets.PacketFXSignal.SoundFXType;
import forestry.core.network.packets.PacketFXSignal.VisualFXType;
import forestry.core.utils.NetworkUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DCCrop implements ICrop {

	protected final World world;
	protected final BlockPos pos;
	protected final IBlockState state;

	public DCCrop(World w, BlockPos p, IBlockState s) {
		world = w;
		pos = p;
		state = s;
	}

	@Override
	public BlockPos getPosition() {
		return pos;
	}

	protected boolean isCrop(World world, BlockPos pos) {
		if (state.getBlock() instanceof IClimateCrop) {
			IBlockState grown = ((IClimateCrop) state.getBlock()).getGrownState();
			GrowingStage stage = ((IClimateCrop) state.getBlock()).getCurrentStage(grown);
			return stage == GrowingStage.GROWN;
		}
		return false;
	}

	@Override
	public NonNullList<ItemStack> harvest() {
		NonNullList<ItemStack> ret = NonNullList.create();
		IBlockState target = world.getBlockState(pos);
		if (isCrop(world, pos)) {
			Block cropB = state.getBlock();
			if (cropB instanceof ClimateCropBase) {
				ClimateCropBase crop = (ClimateCropBase) cropB;
				ret.addAll(crop.getCropItems(target, 0));

				PacketFXSignal packet = new PacketFXSignal(VisualFXType.BLOCK_BREAK, SoundFXType.BLOCK_BREAK, pos, target);
				NetworkUtil.sendNetworkPacket(packet, pos, world);
				IBlockState next = crop.setGroundState(target);
				world.setBlockState(pos, next, 2);
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		return String.format("HeatAndClimate [ position: [ %s ]; block: %s]", new Object[] {
				pos.toString(),
				state
		});
	}

}
