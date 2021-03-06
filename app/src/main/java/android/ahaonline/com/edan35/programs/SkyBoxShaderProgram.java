package android.ahaonline.com.edan35.programs;

/**
 * Created by Felix on 2016-12-06.
 */



import android.ahaonline.com.edan35.R;
import android.content.Context;
import static android.opengl.GLES30.*;


public class SkyBoxShaderProgram extends ShaderProgram {
    private final int uProjectionLocation;
    private final int uViewLocation;
    private final int uTextureUnitLocation;
    private final int aPositionLocation;

    public SkyBoxShaderProgram(Context context) {
        super(context, R.raw.skybox_vertex_shader,
                R.raw.skybox_fragment_shader);

        uProjectionLocation = glGetUniformLocation(program, "projection");
        uViewLocation = glGetUniformLocation(program, "view");

        uTextureUnitLocation = glGetUniformLocation(program, U_TEXTURE_UNIT);
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
    }

    public void setUniforms(float[] projection, float[] view, int textureId) {
        glUniformMatrix4fv(uProjectionLocation, 1, false, projection, 0);
        glUniformMatrix4fv(uViewLocation, 1, false, view, 0);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_CUBE_MAP, textureId);
        glUniform1i(uTextureUnitLocation, 0);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }
}
