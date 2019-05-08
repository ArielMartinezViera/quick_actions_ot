// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.quickactionsexample;

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
