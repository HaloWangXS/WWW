package com.halooing.factory.Factory;


import com.halooing.factory.simpleFactory.Desk;

public class test {
    public static void main(String[] args) {
        WoodenFactory woodenFactory = new WoodenFactory();
        Desk desk = woodenFactory.createDesk();
        System.out.println(desk.getType());
    }
}