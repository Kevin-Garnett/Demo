package com.hsbc.demo.java8;

import java.util.function.Supplier;

public class TypeDeduceSample<T> {

    public static TypeDeduceSample create(Supplier<TypeDeduceSample> typeDeduceSampleSupplier)
    {
        return typeDeduceSampleSupplier.get();
    }

    public static<T> T defaultValue() {
        System.out.println("defaultValue");
        return null;
    }

    public T getValueOrDefaultValue(T value, T defaultValue) {
        System.out.println("getValueOrDefaultValue");
        return value != null ? value : defaultValue;
    }

    public static void main(String []args)
    {
        TypeDeduceSample typeDeduceSample = create(TypeDeduceSample::new);
        Object object = typeDeduceSample.getValueOrDefaultValue("77", TypeDeduceSample.defaultValue());
        System.out.println(object);

        object = typeDeduceSample.getValueOrDefaultValue(new Integer(8), TypeDeduceSample.defaultValue());
        System.out.println(object);

        object = typeDeduceSample.getValueOrDefaultValue(null, TypeDeduceSample.defaultValue());
        System.out.println(object);
    }
}
