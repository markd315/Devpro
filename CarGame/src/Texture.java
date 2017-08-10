import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
    private int width;
    private int height, id;

    public Texture(String string) throws IOException {
	BufferedImage bi;
	bi = ImageIO.read(new File(string));
	this.width = bi.getWidth();
	this.height = bi.getHeight();
	int[] pixels_raw = bi.getRGB(0, 0, this.width, this.height, null, 0, this.width);
	ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
	for (int i = 0; i < width; i++) {
	    for (int j = 0; j < height; j++) {
		int pixel = pixels_raw[i*width + j];
		pixels.put((byte) ((pixel >> 16) & 0xFF));
		pixels.put((byte) ((pixel >> 8) & 0xFF));
		pixels.put((byte) (pixel & 0xFF));
		pixels.put((byte) ((pixel >> 24) & 0xFF));
		
	    }
	}
	pixels.flip();
	id = glGenTextures();
	glBindTexture(GL_TEXTURE_2D, id);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
    }
    public void bind(){
	glBindTexture(GL_TEXTURE_2D, id);
    }
}
