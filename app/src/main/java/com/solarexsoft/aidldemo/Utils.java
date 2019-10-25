package com.solarexsoft.aidldemo;

import android.app.ActivityManager;
import android.content.Context;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 22:24/2019-10-25
 *    Desc:
 * </pre>
 */

public class Utils {
    public static String getAppNameByPID(Context context, int pid){
        ActivityManager manager
                = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for(ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()){
            if(processInfo.pid == pid){
                return processInfo.processName;
            }
        }
        return "";
    }
}
