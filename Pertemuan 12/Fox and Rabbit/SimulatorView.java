import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SimulatorView extends JFrame
{
    private static final Color EMPTY_COLOR = Color.WHITE;
    private static final Color UNKNOWN_COLOR = Color.GRAY;

    private Field field;
    private int gridWidth;
    private int gridHeight;

    private JLabel stepLabel;
    private JLabel populationLabel;
    private FieldStats stats;
    private Map<Class, Color> colors;
    private FieldView fieldView;

    public SimulatorView(int height, int width)
    {
        stats = new FieldStats();
        colors = new HashMap<Class, Color>();

        gridHeight = height;
        gridWidth = width;
        field = new Field(height, width);

        stepLabel = new JLabel("Step: 0", JLabel.CENTER);
        populationLabel = new JLabel("", JLabel.CENTER);

        setLocation(20, 20);

        fieldView = new FieldView(height, width);

        Container contents = getContentPane();
        contents.setLayout(new BorderLayout());
        contents.add(stepLabel, BorderLayout.NORTH);
        contents.add(fieldView, BorderLayout.CENTER);
        contents.add(populationLabel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    private Color getColor(Class animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            return UNKNOWN_COLOR;
        } else {
            return col;
        }
    }

    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }

        stepLabel.setText("Step: " + step);
        stats.reset();

        this.field = field;
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object obj = field.getObjectAt(row, col);
                if(obj != null) {
                    stats.incrementCount(obj.getClass());
                    fieldView.drawMark(col, row, getColor(obj.getClass()));
                } else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }

        stats.countFinished();
        populationLabel.setText(stats.getPopulationDetails(field));
        fieldView.repaint();
    }

    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }

    private class FieldView extends JPanel
    {
        private int gridWidth, gridHeight;
        private int xScale, yScale;
        private Dimension size;
        private Graphics g;
        private Image fieldImage;

        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        public Dimension getPreferredSize()
        {
            return new Dimension(600, 400);
        }

        public void preparePaint()
        {
            if(!size.equals(getSize())) {
                size = getSize();
                fieldImage = createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) xScale = 1;
                yScale = size.height / gridHeight;
                if(yScale < 1) yScale = 1;
            }
        }

        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale, yScale);
        }

        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                g.drawImage(fieldImage, 0, 0, null);
            }
        }
    }
}
