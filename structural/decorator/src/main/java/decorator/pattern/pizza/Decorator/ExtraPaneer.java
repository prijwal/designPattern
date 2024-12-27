package decorator.pattern.pizza.Decorator;

import decorator.pattern.pizza.Base.BasePizza;

public class ExtraPaneer extends ToppingDecorator {

    BasePizza bp;

    public ExtraPaneer(BasePizza bp){
        this.bp = bp;
    }
    @Override
    public int cost() {
        return bp.cost() + 50;
    }
}
