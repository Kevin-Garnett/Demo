package com.hsbc.demo.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class NashornJavascriptSample {

    public static void main(String[]args) throws Exception
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );


        System.out.println( "Test Alert: " + engine.eval("function test() { alert(\"test\"); }")); // No alert

    }

}
