package com.hsbc.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ReferenceSample
{

    public static ReferenceSample create (final Supplier<ReferenceSample> referenceSampleSupplier)
    {
        return referenceSampleSupplier.get();
    }

    public static void method1(ReferenceSample referenceSample)
    {
        System.out.println("Method1" + referenceSample.toString());
    }

    public void method2(ReferenceSample referenceSample)
    {
        System.out.println("Method2" + referenceSample.toString());
    }

    public void method3()
    {
        System.out.println("Method3");
    }

    public static void main(String[]args)
    {
        ReferenceSample referenceSample = create(ReferenceSample::new); //Constructor reference
        referenceSample.method3();

        List<ReferenceSample> list = Arrays.asList(referenceSample);
        list.forEach(ReferenceSample::method1); //Have parameter //Static method reference

        list.forEach(ReferenceSample::method3); // Can refer the non-static method without parameter

        // How to refer the non-static method with parameter?
        final ReferenceSample referenceSample1 = ReferenceSample.create( ReferenceSample::new );
        list = Arrays.asList(referenceSample1);
        list.forEach(referenceSample1::method2);


    }




}
