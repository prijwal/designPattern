package decorator.pattern.pizza;

import decorator.pattern.pizza.Base.BasePizza;
import decorator.pattern.pizza.Base.VegDelight;
import decorator.pattern.pizza.Decorator.ExtraCheese;
import decorator.pattern.pizza.Decorator.ExtraPaneer;

public class PizzaApplication {

	public static void main(String[] args) {
		BasePizza bp;

		bp = new ExtraPaneer( new ExtraCheese( new VegDelight()));

		System.out.println("total cost is " + bp.cost());
	}

}
