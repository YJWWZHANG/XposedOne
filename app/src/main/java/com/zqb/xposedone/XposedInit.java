package com.zqb.xposedone;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 创建时间:2018/7/12 18:08
 */
public class XposedInit implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.zqb.xposedone")) {
            XposedHelpers.findAndHookMethod("com.zqb.xposedone.MainActivity",
                    lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Class<?> c = lpparam.classLoader.loadClass("com.zqb.xposedone.MainActivity");
                            Field field = c.getDeclaredField("mTv1");
                            field.setAccessible(true);
                            TextView tv = (TextView) field.get(param.thisObject);
                            tv.setText("好像可以了");
                        }
                    });
        }

    }
}
