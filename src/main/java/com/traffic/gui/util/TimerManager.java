package com.traffic.utils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TimerManager {
    private Timer timer;

    public TimerManager(int delay, ActionListener action) {
        timer = new Timer(delay, action);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}