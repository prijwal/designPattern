package decorator.pattern.pizza.Decorator;

import decorator.pattern.pizza.Base.BasePizza;

public class WithCorn extends ToppingDecorator {

    BasePizza bp;

    public WithCorn(BasePizza bp){
        this.bp = bp;
    }
    @Override
    public int cost() {
        return bp.cost() + 25;
    }
}
