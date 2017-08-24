package org.vikulin.smarttouchwallet;

import android.support.multidex.MultiDexApplication;

import com.onesignal.OneSignal;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

/**
 * Created by vadym on 09.12.16.
 */
@ReportsCrashes(
        formUri = "https://vikulin.cloudant.com/acra-smarttouchwallet/_design/acra-storage/_update/report",
        reportType = HttpSender.Type.JSON,
        httpMethod = HttpSender.Method.POST,
        formUriBasicAuthLogin = "ssiretiaturadersinightfu",
        formUriBasicAuthPassword = "8add91e0b96acc94f656db77c4991ca9a5164ed3",
        //formKey = "", // This is required for backward compatibility but not used
        customReportContent = {
                ReportField.APP_VERSION_CODE,
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.PACKAGE_NAME,
                ReportField.REPORT_ID,
                ReportField.BUILD,
                ReportField.STACK_TRACE,
                ReportField.DUMPSYS_MEMINFO,
                ReportField.TOTAL_MEM_SIZE
        },
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.toast_crash
)

public class MainApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
        OneSignal.startInit(this).init();
    }
}