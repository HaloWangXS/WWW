package com.halooing.factory.simpleFactory;

/**
 * 简单工厂方法模式，就是为目标类创建一个工厂，当有多个目标实现的时候，在这个工厂内部进行逻辑判断来根据条件创建不同的目标实例。
 * 缺点:
 *     当目标实现增多时，我们不得不去修改工厂类的方法，使其兼容新的实现类型，这明显违背了开闭原则，所以出现了工厂方法模式。
 */
public class DeskFactory {
    public static Desk createDesk(Type type){
        switch (type) {
            case WOODEN:
                return new WoodenDesk();
            case PLASTIC:
                return new PlasticDesk();
            default:
                return null;
        }
    }
}
