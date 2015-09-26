package pascaltriangle;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ColorPicker;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * The {@code PascalTriangle_GUI} class provides for a user interface for the 
 * {@code pascaltriangle} package.
 * 
 * @author Oliver Abdulrahim
 */
public class PascalTriangle_GUI extends JFrame {

    private JCheckBox addSpacesCheckBox;
    private JButton buildButton;
    private JPanel buildPanel;
    private JButton clearButton;
    private JLabel depthLabel;
    private JSpinner depthSpinner;
    private JMenu fileMenu;
    private JMenuItem fileMenuItem;
    private JScrollPane triangleScrollPane;
    private JMenuBar menuBar;
    private JPanel propertiesPanel;
    private JPanel trianglePanel;
    private JTextArea triangleTextArea;
    private PascalTriangle triangle;
    
    /**
     * The cerealVersionUID for this class.
     */
    private static final long serialVersionUID = 389045089237L;

    /**
     * Creates new form PascalTriangle_GUI
     */
    public PascalTriangle_GUI() {
        initComponents();
        updateDisplay();
    }

    /**
     * Called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        trianglePanel = new JPanel();
        triangleScrollPane = new JScrollPane();
        triangleTextArea = new JTextArea();
        propertiesPanel = new JPanel();
        depthLabel = new JLabel();
        addSpacesCheckBox = new JCheckBox();
        depthSpinner = new JSpinner();
        buildPanel = new JPanel();
        buildButton = new JButton();
        clearButton = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        fileMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pascal's Triangle");
        setLocationByPlatform(true);
        setResizable(false);

        trianglePanel.setBorder(BorderFactory.createTitledBorder("Pascal's Triangle"));

        triangleTextArea.setColumns(20);
        triangleTextArea.setFont(new Font("Monospaced", 0, 10));
        triangleTextArea.setRows(5);
        triangleTextArea.setEditable(false);
        triangleScrollPane.setViewportView(triangleTextArea);

        GroupLayout trianglePanelLayout = new GroupLayout(trianglePanel);
        trianglePanel.setLayout(trianglePanelLayout);
        trianglePanelLayout.setHorizontalGroup(
            trianglePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(trianglePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(triangleScrollPane, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );
        trianglePanelLayout.setVerticalGroup(
            trianglePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(trianglePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(triangleScrollPane, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        propertiesPanel.setBorder(BorderFactory.createTitledBorder("Properties"));

        depthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        depthLabel.setText("Depth");

        addSpacesCheckBox.setSelected(true);
        addSpacesCheckBox.setText("Format with spaces?");
        addSpacesCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                updateDisplay();
            }
        });

        depthSpinner.setModel(new SpinnerNumberModel(PascalTriangle.DEFAULT_TRIANGLE_DEPTH, 1, 32, 1));
        depthSpinner.addChangeListener(e -> updateDisplay());

        GroupLayout propertiesPanelLayout = new GroupLayout(propertiesPanel);
        propertiesPanel.setLayout(propertiesPanelLayout);
        propertiesPanelLayout.setHorizontalGroup(
            propertiesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addSpacesCheckBox, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGroup(propertiesPanelLayout.createSequentialGroup()
                        .addComponent(depthLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(depthSpinner, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        propertiesPanelLayout.setVerticalGroup(
            propertiesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(depthLabel)
                    .addComponent(depthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addSpacesCheckBox)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        buildPanel.setBorder(BorderFactory.createTitledBorder("Build It!"));
        buildPanel.setToolTipText("");

        buildButton.setText("Build Triangle");
        buildButton.addActionListener(e -> updateDisplay());

        clearButton.setText("Clear Triangle");
        clearButton.addActionListener(e -> clear());

        GroupLayout buildPanelLayout = new GroupLayout(buildPanel);
        buildPanel.setLayout(buildPanelLayout);
        buildPanelLayout.setHorizontalGroup(
            buildPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buildPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buildPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buildButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        buildPanelLayout.setVerticalGroup(
            buildPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, buildPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buildButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        fileMenuItem.setText("Exit");
        fileMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(fileMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(propertiesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buildPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(trianglePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trianglePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buildPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(propertiesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {buildPanel, propertiesPanel});
        pack();
    }                       

    /**
     * Clears the triangle display.
     */
    private void clear() {
        triangleTextArea.setText("");
    }
    
    /**
     * Draws the triangle with the currently specified user arguments.
     */
    private void updateDisplay() {
        // No need to range-check this value: depthSpinner has minimum and 
        // maximum constraints
        int depth = (Integer) depthSpinner.getValue();
        boolean addSpaces = addSpacesCheckBox.isSelected();
        
        triangle = PascalTriangle.getTriangle(depth);
        triangleTextArea.setText(triangle.buildTriangle(addSpaces));
    }
    
    /**
     * The main method for this package. Creates and displays a 
     * {@code PascalTriangle_GUI} form.
     * 
     * @param args The command-line arguments.
     */
    public static void main(String args[]) {
        // Sets the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        } catch (ClassNotFoundException | InstantiationException 
               | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ColorPicker.class.getName())
                    .log(Level.SEVERE, 
                            "Error with look and feel settings. "
                          + "Check if look and feels are installed correctly",
                            ex);
        }
        SwingUtilities.invokeLater(() -> {
            new PascalTriangle_GUI().setVisible(true);
        });
    }
    
}
