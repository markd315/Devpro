import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.*;

import org.lwjgl.opengl.GL;

public class Game {
    public static List<Entity> entities = new ArrayList<Entity>();

    public static void main(String[] args) {
	if (!glfwInit()) {
	    throw new IllegalStateException("glfw no initialize");
	}
	long window = glfwCreateWindow(1200, 900, "Car game", 0, 0);
	glfwShowWindow(window);
	glfwMakeContextCurrent(window);
	GL.createCapabilities();
	glEnable(GL_TEXTURE_2D);
	glClearColor(1f, 1f, 1f, 0f);
	long lastKeyTime = System.currentTimeMillis();
	Car c = null;
	try {
	    FileInputStream fis = new FileInputStream("savegame.ser");
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    entities = (ArrayList<Entity>) ois.readObject();
	    ois.close();
	    fis.close();
	} catch (Exception e) {
	    c = new Car(0, 0, Car.Colour.BLUE);
	    Entity x = new Entity(-100, -100, 3, 45);
	    entities.add(x);
	    entities.add(c);
	}
	while (!glfwWindowShouldClose(window)) {
	    if (System.currentTimeMillis() - lastKeyTime >= 50) {
		for (Entity e : entities) {
		    e.tickSecond();
		    if (e instanceof Car) {
			c = (Car) e;
		    }
		}
		controlEntity(c, window);
		lastKeyTime = System.currentTimeMillis();
		// System.out.println("50ms");
	    }
	    glfwPollEvents();
	    glClear(GL_COLOR_BUFFER_BIT);
	    try {
		for (Entity e : entities) {
		    render(e);
		}
	    } catch (Exception i) {
		i.printStackTrace();
	    }
	    glfwSwapBuffers(window);
	}
	glfwTerminate();
    }

    private static void controlEntity(Entity c, long window) {
	if (glfwGetKey(window, GLFW_KEY_W) == GL_TRUE) {
	    if (c instanceof Car) {
		Car x = (Car) c;
		x.gas();
	    }
	}
	if (glfwGetKey(window, GLFW_KEY_S) == GL_TRUE) {
	    if (c instanceof Car) {
		Car x = (Car) c;
		x.brake();
	    }
	}
	if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) {
	    if (c instanceof Car) {
		Car x = (Car) c;
		x.turnLeft();
	    }
	}
	if (glfwGetKey(window, GLFW_KEY_D) == GL_TRUE) {
	    if (c instanceof Car) {
		Car x = (Car) c;
		x.turnRight();
	    }
	}
	if (glfwGetKey(window, GLFW_KEY_E) == GL_TRUE) {
	    Random rng = new Random();
	    int x = rng.nextInt(201) - 100;
	    int y = rng.nextInt(201) - 100;
	    int vel = rng.nextInt(15);
	    int ang = rng.nextInt(360);
	    Entity newEntity = new Entity(x, y, vel, ang);
	    entities.add(newEntity);
	}
	if (glfwGetKey(window, GLFW_KEY_C) == GL_TRUE) {
	    if (c instanceof Car) {
		Car x = (Car) c;
		x.setCruiseControl(8);
	    }
	}
	if (glfwGetKey(window, GLFW_KEY_F) == GL_TRUE) {
	    if (c instanceof Car) {
		Car x = (Car) c;
		x.turnLeft(180);
	    }
	}
	if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
	    FileOutputStream fout;
	    ObjectOutputStream oos = null;
	    try {
		fout = new FileOutputStream("savegame.ser");
		oos = new ObjectOutputStream(fout);
		oos.writeObject(entities);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    glfwDestroyWindow(window);
	}
	if (glfwGetKey(window, GLFW_KEY_N) == GL_TRUE) {
	    entities.clear();
	    c = new Car(0, 0, Car.Colour.GREEN);
	    entities.add(new Entity(-100, -100, 3, 45));
	    entities.add(c);
	}
    }

    private static void render(Entity e) throws IOException {
	Texture tex = e.getTexture();
	tex.bind();
	List<Float> xyCoords = getCenterFromEntity(e);
	float x = xyCoords.get(0);
	float y = xyCoords.get(1);
	glBegin(GL_QUADS);
	// tl
	// glColor4f(0f, 0f, 0f, 0f);
	glTexCoord2f(0, 0);
	glVertex2f(x - .07f, y + .07f);
	// tr
	// glColor4f(0f, 0f, 0f, 0f);
	glTexCoord2f(1, 0);
	glVertex2f(x + .07f, y + .07f);
	// br
	// glColor4f(0f, 0f, 0f, 0f);
	glTexCoord2f(1, 1);
	glVertex2f(x + .07f, y - .07f);
	// bl
	// glColor4f(0f, 0f, 0f, 0f);
	glTexCoord2f(0, 1);
	glVertex2f(x - .07f, y - .07f);
	glEnd();
    }

    private static List<Float> getCenterFromEntity(Entity e) {
	List<Float> l = new ArrayList<Float>();
	double x = e.getXPosition();
	double y = e.getYPosition();
	l.add((float) (x / 100.0f));
	l.add((float) (y / 100.0f));
	return l;
    }
}
