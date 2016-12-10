package android.ahaonline.com.edan35.Objects;

/**
 * Created by Felix on 2016-12-06.
 */


import android.ahaonline.com.edan35.data.VertexArray;
import android.ahaonline.com.edan35.programs.SkyBoxShaderProgram;

import java.nio.ByteBuffer;
import static android.opengl.GLES30.*;

public class SkyBox {
    private static final int POSITION_COMPONENT_COUNT = 3;
    private final VertexArray vertexArray;
    private final ByteBuffer indexArray;
    private SkyBoxShaderProgram shader;

    public SkyBox() {
        // Create a unit cube.
        vertexArray = new VertexArray(new float[] {
                -1,  1,  1,     // (0) Top-left near
                1,  1,  1,     // (1) Top-right near
                -1, -1,  1,     // (2) Bottom-left near
                1, -1,  1,     // (3) Bottom-right near
                -1,  1, -1,     // (4) Top-left far
                1,  1, -1,     // (5) Top-right far
                -1, -1, -1,     // (6) Bottom-left far
                1, -1, -1      // (7) Bottom-right far
        });

        // 6 indices per cube side
        indexArray =  ByteBuffer.allocateDirect(6 * 6)
                .put(new byte[] {
                        // Front
                        1, 3, 0,
                        0, 3, 2,

                        // Back
                        4, 6, 5,
                        5, 6, 7,

                        // Left
                        0, 2, 4,
                        4, 2, 6,

                        // Right
                        5, 7, 1,
                        1, 7, 3,

                        // Top
                        5, 1, 4,
                        4, 1, 0,

                        // Bottom
                        6, 2, 7,
                        7, 2, 3
                });
        indexArray.position(0);
    }
    public void bindData(SkyBoxShaderProgram skyboxProgram) {
        this.shader = skyboxProgram;
        vertexArray.setVertexAttribPointer(0,
                skyboxProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT, 0);
    }

    public void draw() {
        glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_BYTE, indexArray);
        glDisableVertexAttribArray(shader.getPositionAttributeLocation());
    }
}