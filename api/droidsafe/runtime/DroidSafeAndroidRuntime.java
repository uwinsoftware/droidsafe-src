package droidsafe.runtime;

import java.util.Random;

//Droidsafe Imports
import droidsafe.annotations.*;
import android.app.ContextImpl;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Application;



/**
 * This class will simulate the android runtime system by making any calls or creating 
 * any globals required.
 * 
 * 
 * @author mgordon
 *
 */
public class DroidSafeAndroidRuntime {
	public static boolean control = new Random().nextBoolean();
	public static int switchControl = new Random().nextInt();
	private static Application mApplication;
	
	@DSModeled(DSC.BAN)
	/**
	 * This method will be called automatically by the droidsafe harness class before all
	 * application code.
	 * 	
	 * @param args
	 */
	public static void main() {
		
	}
	
	/**
	 * create any associated state and call init methods on an activity
	 * 
	 * call any life cycle events for the activity
	 * 
	 * @param activity
	 */
	@DSModeled(DSC.BAN)
	public static void modelActivity(android.app.Activity activity) {
		ContextImpl context = new ContextImpl();
		
		if (mApplication != null)
			activity.setApplication(mApplication);
		
		while (true) {
			Bundle b = new Bundle();
			activity.performCreate(b, context);

			activity.onAttachedToWindow();
			activity.onWindowFocusChanged(true);

			// Call all public methods with no params
			activity.onBackPressed();
			activity.onContentChanged();
			activity.onCreateDescription();
			activity.onLowMemory();
			activity.onRetainNonConfigurationInstance();
			activity.onSearchRequested();
			activity.onUserInteraction();
			
			activity.droidsafeOnSavedInstanceState(new Bundle());
			
			activity.droidsafeOnResume();
			activity.droidsafeOnPause();
			activity.droidsafeOnStop();
			activity.droidsafeOnDestroy();
			activity.onDetachedFromWindow();
		}
		
		//code
	}
	
	public static void modelService(android.app.Service service) {
		if (mApplication != null)
			service.setApplication(mApplication);

		service.onCreate();
		Intent bindIntent = new Intent();
		service.onBind(bindIntent);
		service.onConfigurationChanged(new Configuration());
		service.onRebind(new Intent());
		service.onStart(new Intent(), 0);
		service.onStartCommand(new Intent(), 0, 0);
		service.onTaskRemoved(new Intent());
		service.onLowMemory();
		service.onTrimMemory(0);
		service.onUnbind(bindIntent);
		service.stopSelf(0);
		service.onDestroy();
	}
	
	@DSModeled(DSC.SPEC)
	public static void modelContentProvider(android.content.ContentProvider contentProvider) {
		contentProvider.onCreate();
		contentProvider.onConfigurationChanged(new Configuration());
		contentProvider.onLowMemory();
		contentProvider.onTrimMemory(0);
	}
	
	@DSModeled(DSC.SPEC)
	public static void modelBroadCastReceiver(BroadcastReceiver receiver) {
		// No-op onReceive called from registerReceiver in ContextImpl
	}
	
	@DSModeled(DSC.SPEC)
	public static void modelApplication(android.app.Application app) {
		mApplication = app;
		while (true) {
			app.droidsafeOnCreate();
			app.droidsafeOnTerminate();
			app.droidsafeOnEverythingElse();
		}
		//code
	}
}
