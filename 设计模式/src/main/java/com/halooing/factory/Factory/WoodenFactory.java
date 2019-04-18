package com.halooing.factory.Factory;

import com.halooing.factory.simpleFactory.Desk;
import com.halooing.factory.simpleFactory.PlasticDesk;
import com.halooing.factory.simpleFactory.WoodenDesk;

public class WoodenFactory implements DeskFactory{
    public Desk createDesk() {
        return new WoodenDesk();
    }
}
