package defeatedcrow.hac.main.api.brewing;

import java.util.List;

public interface IMicrobeRegister {

	List<IMicrobe> getList();

	boolean registerSpecies(IMicrobe species);

	IMicrobe getSpecies(String name);

	IMicrobe collectSpecies(EnumHabitat habitat, EnumMedium medium);

}
