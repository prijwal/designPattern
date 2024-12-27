package decorator.pattern.pizza.Decorator;

import decorator.pattern.pizza.Base.BasePizza;

public class ExtraCheese extends ToppingDecorator {

    BasePizza bp;

    public ExtraCheese(BasePizza bp){
        this.bp = bp;
    }
    @Override
    public int cost() {
        return bp.cost() + 20;
    }
}
