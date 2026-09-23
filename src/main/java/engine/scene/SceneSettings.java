package engine.scene;

import java.awt.Color;

public record SceneSettings(
        String name,
        int screenWidth,
        int screenHeight,
        Color bgColor,
        int fps
) {}
