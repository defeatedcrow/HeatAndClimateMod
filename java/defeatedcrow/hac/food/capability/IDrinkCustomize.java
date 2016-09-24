package defeatedcrow.hac.food.capability;

/**
 * 飲み物Entity、ItemStack共通のインターフェイス。<br>
 * いずれもNBTによって保持している
 */
public interface IDrinkCustomize {

	DrinkMilk getMilk();

	DrinkSugar getSugar();

	boolean setMilk(DrinkMilk milk);

	boolean setSugar(DrinkSugar sugar);

}
