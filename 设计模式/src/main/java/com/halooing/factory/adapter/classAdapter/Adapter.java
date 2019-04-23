package com.halooing.factory.adapter.classAdapter;

/**
 * 类适配器:
 *  案例: 接口A -> 方法a    /接口B
 *  适配器实现方式:
 *
 */
public class Adapter extends Usber implements Ps4{
    public void idPs4() {
        System.out.println("这是PS4口");
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.isUsb();
        adapter.idPs4();
    }
}
