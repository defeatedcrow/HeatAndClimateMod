package defeatedcrow.hac.core.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.extensions.IForgeBlock;

@Mixin(Block.class)
public abstract class BlockClassMixin implements IForgeBlock {

	@Inject(method = "canSustainPlant", at = @At(value = "HEAD"), cancellable = true, remap = false)
	public void hookBlockPlantable(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable, @Nonnull CallbackInfoReturnable<Boolean> callback) {
		PlantType type = plantable.getPlantType(world, pos.relative(facing));
		boolean ret = false;
		if (PlantType.CROP.equals(type) && state.is(TagDC.BlockTag.FARMLAND)) {
			ret = true;
		} else if (PlantType.DESERT.equals(type) && state.is(BlockTags.SAND)) {
			ret = true;
		} else if (PlantType.PLAINS.equals(type) && state.is(TagDC.BlockTag.FARMLAND)) {
			ret = true;
		}
		if (ret)
			callback.setReturnValue(true);
	}

	@Override
	public boolean isFertile(BlockState state, BlockGetter level, BlockPos pos) {
		if (state.is(TagDC.BlockTag.FARMLAND))
			return DCState.getInt(state, FarmBlock.MOISTURE) > 0;

		return false;
	}

}
