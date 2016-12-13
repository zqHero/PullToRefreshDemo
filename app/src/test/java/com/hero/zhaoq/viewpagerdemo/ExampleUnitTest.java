package com.hero.zhaoq.viewpagerdemo;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests,
 * switch the Test Artifact in the Build Variants view.
 *
 *  1.Android测试，测试activity，使用InstrumentationTestCase类
 *  2.测试后台逻辑，使用AndroidTestCase类
 *   需要注意的是，测试activity继承InstrumentationTestCase，需要新建一个Android Test Project，
 *   而测试逻辑继承AndroidTestCase则是在原项目中进行，可以测试代码统一放到一个yourpackage.test包下。
 */
public class ExampleUnitTest extends InstrumentationTestCase{
    @Test
    public void test_addition_isCorrect() throws Exception {

        Log.i("info","--------");
        assertEquals(4, 2 + 2);
    }
}