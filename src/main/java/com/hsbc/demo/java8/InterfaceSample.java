package com.hsbc.demo.java8;

public interface InterfaceSample {

    default String defaultMethod() {
        return "This is default method";
    }
}

class ImplementationSample1 implements InterfaceSample
{

}

class ImplementationSample2 implements InterfaceSample
{
    @Override
    public String defaultMethod() {
        return "This is override method";
    }
}

class Main{

    public static void main(String[]args)
    {
        InterfaceSample interfaceSample1 = InterfaceFactorySample.create(ImplementationSample1::new);
        System.out.println(interfaceSample1.defaultMethod());

        InterfaceSample interfaceSample2 = InterfaceFactorySample.create(ImplementationSample2::new);
        System.out.println(interfaceSample2.defaultMethod());
    }
}

