package defeatedcrow.hac.food.item.brewing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.EnumHabitat;
import defeatedcrow.hac.main.api.brewing.EnumMedium;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import defeatedcrow.hac.main.api.brewing.IMicrobeRegister;

public class MicrobeRegister implements IMicrobeRegister {

	public IMicrobeRegister instance() {
		return MainAPIManager.microbeRegister;
	}

	private static List<IMicrobe> list;

	public MicrobeRegister() {
		list = new ArrayList<IMicrobe>();
	}

	@Override
	public List<IMicrobe> getList() {
		return list;
	}

	@Override
	public boolean registerSpecies(IMicrobe species) {
		if (species != null) {
			for (IMicrobe check : getList()) {
				if (check.getName().equalsIgnoreCase(species.getName())) {
					return false;
				}
			}
			list.add(species);
			DCLogger.debugInfoLog("Register microbe : " + species.getName());
			return true;
		}
		return false;
	}

	@Override
	public IMicrobe getSpecies(String name) {
		if (name != null) {
			for (IMicrobe check : getList()) {
				if (check.getName().equalsIgnoreCase(name)) {
					return check;
				}
			}
		}
		return null;
	}

	@Override
	public IMicrobe collectSpecies(EnumHabitat habitat, EnumMedium medium) {
		int total = 1;
		Random rand = new Random();
		List<IMicrobe> set = Lists.newArrayList();
		DCLogger.debugInfoLog("check 4 List size " + getList().size());
		for (IMicrobe check : getList()) {
			if (check.getChance(habitat) > 0 && check.getMediums().contains(medium)) {
				total += check.getChance(habitat);
				set.add(check);
			}
		}
		if (!set.isEmpty()) {
			if (set.size() < 2) {
				DCLogger.debugInfoLog("check 4 chance " + set.get(0).getName() + "/" + total);
				return set.get(0);
			} else {
				rand.nextInt(total);
				int r = rand.nextInt(total);
				int count = 1;
				for (IMicrobe check2 : set) {
					count += check2.getChance(habitat);
					if (count >= r) {
						DCLogger.debugInfoLog("check 4 chance " + check2.getName() + " " + r + "/" + total);
						return check2;
					}
				}
				return set.get(0);
			}
		}
		return null;
	}

}
