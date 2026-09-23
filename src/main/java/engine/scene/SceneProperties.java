package engine.scene;

import java.awt.Color;

public record SceneProperties(
        String name,
        int screenWidth,
        int screenHeight,
        Color bgColor,
        int fps
) {}
