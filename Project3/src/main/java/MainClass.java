import javax.swing.*;

public class MainClass {

    private static void initWindow() {

        JFrame window = new JFrame("Human Vs Goblins");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Scene scene = new Scene();
        window.add(scene);
        window.addKeyListener(scene);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainClass::initWindow);
    }
}