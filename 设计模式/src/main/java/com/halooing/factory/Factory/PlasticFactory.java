package com.halooing.factory.Factory;

import com.halooing.factory.simpleFactory.Desk;
import com.halooing.factory.simpleFactory.PlasticDesk;

public class PlasticFactory implements DeskFactory{
    public Desk createDesk() {
        return new PlasticDesk();
    }
}
