package com.halooing.factory.simpleFactory;

public class test {
    public static void main(String[] args) {
        Desk desk = DeskFactory.createDesk(Type.PLASTIC);
        System.out.println(desk.getType());
    }
}
