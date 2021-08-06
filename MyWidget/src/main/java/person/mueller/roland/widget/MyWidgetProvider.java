package person.mueller.roland.widget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import person.mueller.roland.mywidget.*;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
	private static final String ACTION_CLICK = "ACTION_CLICK";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		int[] allWidgetsIds = getWidgets(context, appWidgetManager);
		Log.w("WidgetExample",
				"appWidgetIds="+ intArrayToString(appWidgetIds) 
				+ " allWidgetIds=" + intArrayToString(allWidgetsIds));
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		for (int widgetId : allWidgetsIds) {
			String value = getValue();
			Log.w("WidgetExample", value);
			remoteViews.setTextViewText(R.id.update, value);

			// register onClickListener
			Intent intent = new Intent(context, MyWidgetProvider.class);
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
	
	private int[] getWidgets(Context context, AppWidgetManager appWidgetManager) {
		ComponentName thisWidget = new ComponentName(context,
				MyWidgetProvider.class);
		int[] allWidgetsIds = appWidgetManager.getAppWidgetIds(thisWidget);
		return allWidgetsIds;
	}

	private String getValue() {
		// create some random data to show in widget
		String number = String.valueOf(new Random().nextInt(6) + 1);
		return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date())
			+ "\n" + number;
	}

	String intArrayToString(int[] intArray) {
		String outs = "[ ";
		for (int i : intArray)
			outs += i + " ";
		outs += "]";
		return outs;
	}

}
