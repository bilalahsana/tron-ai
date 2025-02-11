package com.tronai;

import com.tronai.config.GameConfig;
import com.tronai.view.ViewApplication;

public class Main {
    public static void main(String[] args) {
        ViewApplication.config = GameConfig.fromArgs(args);
        ViewApplication.launch(ViewApplication.class, args);
    }
}
