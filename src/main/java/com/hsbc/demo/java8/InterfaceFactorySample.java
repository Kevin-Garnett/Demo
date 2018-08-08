package com.hsbc.demo.java8;

import java.util.function.Supplier;

public interface InterfaceFactorySample {

    static InterfaceSample create(Supplier<InterfaceSample> interfaceSampleSupplier)
    {
        return interfaceSampleSupplier.get();
    }
}
