# quick_actions

This Flutter plugin allows you to manage and interact with the application's
home screen quick actions.

Quick actions refer to the [eponymous
concept](https://developer.apple.com/ios/human-interface-guidelines/extensions/home-screen-actions)
on iOS and to the [App
Shortcuts](https://developer.android.com/guide/topics/ui/shortcuts.html) APIs on
Android (introduced in Android 7.1 / API level 25). It is safe to run this plugin
with earlier versions of Android as it will produce a noop.

## Need add

In `ios/Runner/AppDelegate.swift`, need add the next code:

```swift
  @available(iOS 9.0, *)
  override func application(
    _ application: UIApplication,
    performActionFor shortcutItem: UIApplicationShortcutItem,
    completionHandler: @escaping (Bool) -> Void) {
    let controller = window.rootViewController as? FlutterViewController
    let channel = FlutterMethodChannel(name: "plugins.flutter.io/quick_actions_ot", binaryMessenger: controller!)
    channel.invokeMethod("launch", arguments: shortcutItem.type)
  }
```

In `android/app/src/main/java/com/example/app/MainActivity.java`, need add the next code:

```java
package com.example.app

import android.content.Intent;
import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.plugins.quickactions.QuickActionsPlugin;

public class MainActivity extends FlutterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        QuickActionsPlugin.bindActivity(this.getClass(), getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        QuickActionsPlugin.bindActivity(this.getClass(), intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QuickActionsPlugin.unBindActivity();
    }
}
```

## Usage in Dart

Initialize the library early in your application's lifecycle by providing a
callback, which will then be called whenever the user launches the app via a
quick action.

```dart
quickActions.initialize((shortcutType) {
  if (shortcutType == 'action_main') {
    print('The user tapped on the "Main view" action.');
  }
  // More handling code...
});
```

Finally, manage the app's quick actions, for instance:

```dart
quickActions.setShortcutItems(<ShortcutItem>[
  quickActions.ShortcutItem(type: 'action_main', localizedTitle: 'Main view', icon: 'icon_main'),
  quickActions.ShortcutItem(type: 'action_help', localizedTitle: 'Help', icon: 'icon_help')
]);
```

Please note, that the `type` argument should be unique within your application
(among all the registered shortcut items). The optional `icon` should be the
name of the native resource (xcassets on iOS or drawable on Android) that the app will display for the
quick action.

## Getting Started

For help getting started with Flutter, view our online
[documentation](http://flutter.io/).

For help on editing plugin code, view the [documentation](https://flutter.io/platform-plugins/#edit-code).
