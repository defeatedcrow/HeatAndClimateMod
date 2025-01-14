package defeatedcrow.hac.food.material.block;

import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FertileBlockTile extends BlockEntity {

	private CropType currentPlant = CropType.TRADING;
	private int continuousYear = 0;
	private int lastYear = 0;

	public FertileBlockTile(BlockPos pos, BlockState state) {
		super(FoodInit.FERTILE_TILE.get(), pos, state);
	}

	public CropType getCurrentPlant() {
		return currentPlant;
	}

	public int getYear() {
		return continuousYear;
	}

	public boolean isInContinuousCropping(IClimateCrop crop) {
		if (crop == null || getCurrentPlant() == CropType.TRADING)
			return false;
		if (getCurrentPlant() == crop.getFamily()) {
			return true;
		}
		return false;
	}

	public boolean isContinuousFailure(IClimateCrop crop) {
		if (crop == null || getCurrentPlant() == CropType.TRADING)
			return false;
		if (crop.getContinuousRegistance(crop.getTier()) >= 5)
			return false;
		if (getCurrentPlant() == crop.getFamily()) {
			return getYear() > crop.getContinuousRegistance(crop.getTier());
		}
		return false;
	}

	public void updateContinuous(BlockState state) {
		if (state.getBlock() instanceof IClimateCrop crop) {
			if (getCurrentPlant() == crop.getFamily()) {
				int year = DCTimeHelper.getYear(getLevel());
				if (year != lastYear) {
					lastYear = year;
					continuousYear++;
				}
			} else {
				if (continuousYear-- <= 0) {
					continuousYear = 0;
					currentPlant = crop.getFamily();
				}
			}
		} else {
			if (continuousYear-- <= 0) {
				resetContinuous();
			}
		}
	}

	public void resetContinuous() {
		currentPlant = CropType.TRADING;
		continuousYear = 0;
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		loadTag(tag);
	}

	public void loadTag(CompoundTag tag) {
		if (tag.contains(TagKeyDC.CROP)) {
			String name = tag.getString(TagKeyDC.CROP);
			currentPlant = CropType.getFromName(name);
		}
		if (tag.contains(TagKeyDC.CROP_CONTINUOUS)) {
			continuousYear = tag.getInt(TagKeyDC.CROP_CONTINUOUS);
		}
		if (tag.contains(TagKeyDC.YEAR)) {
			lastYear = tag.getInt(TagKeyDC.YEAR);
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		writeTag(tag);
	}

	public void writeTag(CompoundTag tag) {
		if (currentPlant != null) {
			tag.putString(TagKeyDC.CROP, currentPlant.toString());
		} else {
			tag.putString(TagKeyDC.CROP, "NONE");
		}
		tag.putInt(TagKeyDC.CROP_CONTINUOUS, continuousYear);
		tag.putInt(TagKeyDC.YEAR, lastYear);
	}

}
