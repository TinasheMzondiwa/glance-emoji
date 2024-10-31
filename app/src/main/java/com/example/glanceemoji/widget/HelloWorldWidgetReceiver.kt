package com.example.glanceemoji.widget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class HelloWorldWidgetReceiver(override val glanceAppWidget: GlanceAppWidget = HelloWorldWidget()) :
    GlanceAppWidgetReceiver()