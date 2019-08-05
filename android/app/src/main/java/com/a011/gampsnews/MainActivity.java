package com.a011.gampsnews;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterView;

public class MainActivity extends FlutterActivity {


  // declaring unique channelID for app
  public static final String CHANNEL_ID = "com.a011.gampsnews";

  // holds video ID
  public String videoId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    new MethodChannel(getFlutterView(),CHANNEL_ID).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
      @Override
      public void onMethodCall(MethodCall methodCall,MethodChannel.Result result) {
        if(methodCall.method.equals("playVideo")){

          // fetching youtube specific videoID from method call
          videoId = methodCall.argument("vidId");

          // calling function to launch youtube
          watchYoutubeVideo(videoId);

        }
      }
    });

  }

  // plays video in app or browser
  public void watchYoutubeVideo(String id){

    // creating intent for youtube app
    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
    // forcing full screen in youtube
    //appIntent.putExtra("force_fullscreen",true);

    // creating intent for youtube website
    Intent webIntent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=" + videoId));

    // playing video in browser if app intent fails
    try {
      startActivity(appIntent);
    } catch (ActivityNotFoundException ex) {
      startActivity(webIntent);
    }
  }
}
