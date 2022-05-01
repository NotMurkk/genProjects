import org.junit.jupiter.api.BeforeAll;

import java.awt.*;

class SceneTest {

    static Scene scene;

    @BeforeAll
    static void setUpClass() {
        scene = new Scene();
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void generateGoblin() {
        scene.generateGoblin();
        assert(scene.goblins.size() >= 1);
    }

    @org.junit.jupiter.api.Test
    void getEntity() {
        creature g = scene.goblins.get(0);
        assert(scene.getEntity(g.pos) == g);
    }

    @org.junit.jupiter.api.Test
    void setEntity() {
        creature g = scene.goblins.get(0);
        Point pos = new Point(0, 0);
        scene.setEntity(pos, g);
        assert(scene.getEntity(pos) == g);
    }

    @org.junit.jupiter.api.Test
    void buildVec() {
        Point pos = new Point(0, 0);
        assert(pos.x == 0);
        assert(pos.y == 0);
    }
}