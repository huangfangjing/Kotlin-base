package com.example.kotlindemo.design.factory;

public class PlaceFactory {

    public Factory sayBeijing() {
        return new BeijingFactory();
    }

    public Factory sayNanjing() {
        return new NanjingFactory();
    }

    public static void main(String[] args) {
        Factory factory = new PlaceFactory().sayBeijing();
        System.out.println(factory.sayPlace());
    }
}
