package demo.android.com.viewdemo.opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class AirHockeyRenderer {
    private static final int BYTES_PER_FLOAT = 4;
    private final FloatBuffer vertexDate;
    private static final int POSITION_COMPONENT_COUNT = 2;

    public AirHockeyRenderer() {
        float[] tableVertices = {
                0f, 0f,
                0f, 14f,
                9f,  14f,
                9f, 0f
        };
        float[] tableVerticesWithTrangles = {
                // triangle 1
                0f, 0f,
                9f, 14f,
                0f, 14f,

                // triangle 2
                0f, 0f,
                9f,  0f,
                9f, 14f,

                // Line 1
                0f, 7f,
                9f, 7f,

                // Mallets
                4.5f, 2f,
                4.5f, 12f
        };

        this.vertexDate = ByteBuffer
                .allocateDirect(tableVerticesWithTrangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexDate.put(tableVerticesWithTrangles);
    }
}
