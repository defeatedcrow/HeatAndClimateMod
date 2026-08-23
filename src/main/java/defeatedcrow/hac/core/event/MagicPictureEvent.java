package defeatedcrow.hac.core.event;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.event.GetSeasonEvent;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.entity.MagicPictureEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MagicPictureEvent {

	private static List<MagicPictureEntity> PictureList = Lists.newArrayList();

	public static List<MagicPictureEntity> getList() {
		return ImmutableList.copyOf(PictureList);
	}

	protected static void setPictureList(List<? extends MagicPictureEntity> list) {
		PictureList.clear();
		PictureList.addAll(list);
	}

	@SubscribeEvent
	public static void onCheckSeason(GetSeasonEvent event) {
		if (event.getWorld() != null) {
			Level level = event.getWorld();
			EnumSeason season = event.currentSeason();
			List<MagicPictureEntity> list = getList();

			if (list.stream()
			    .anyMatch(checkColor(MagicColor.WHITE_BLUE))) {
				season = EnumSeason.WINTER_LATE;
			} else if (list.stream()
			    .anyMatch(checkColor(MagicColor.WHITE_RED))) {
				season = EnumSeason.SUMMER_LATE;
			} else if (list.stream()
			    .anyMatch(checkColor(MagicColor.BLUE_GREEN))) {
				season = EnumSeason.FLOWER;
			} else if (list.stream()
			    .anyMatch(checkColor(MagicColor.RED_GREEN))) {
				season = EnumSeason.HARVEST;
			} else if (level.dimension() == Level.NETHER) {
				season = EnumSeason.SCORCHER;
			} else if (level.dimension() == Level.END) {
				season = EnumSeason.ABSOLUTE;
			}

			event.setNewSeason(season);
			event.setResult(Result.ALLOW);
		}
	}

	@SubscribeEvent
	public static void onPlaySound(PlayLevelSoundEvent.AtPosition event) {
		if (event.getLevel() != null) {
			Level level = event.getLevel();
			List<MagicPictureEntity> list = getList();

			if (list.stream()
			    .anyMatch(checkColor(MagicColor.BLUE_BLACK))) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayEntitySound(PlayLevelSoundEvent.AtEntity event) {
		if (event.getLevel() != null) {
			Level level = event.getLevel();
			List<MagicPictureEntity> list = getList();

			if (list.stream()
			    .anyMatch(checkColor(MagicColor.BLUE_BLACK))) {
				event.setCanceled(true);
			}
		}
	}

	public static Predicate<MagicPictureEntity> checkColor(MagicColor color) {
		return e -> (e.getColor() == color);
	}

}
