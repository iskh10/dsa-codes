import javax.swing.*;
import java.awt.*;

public class CivilizationGameGUI extends JFrame {
    private JPanel mapPanel, controlPanel, infoPanel;
    private JLabel mapLabel, infoLabel, woodLabel, stoneLabel, goldLabel, cityLabel, technologyLabel, cityImprovementLabel, populationLabel, taxLabel, currencyLabel, vehicleLabel, houseLabel;
    private JButton buildCityButton, gatherResourcesButton, researchButton, buildImprovementButton, adjustTaxButton, recruitVehicleButton, buildHouseButton;
    private JTextArea logTextArea;

    private int wood, stone, gold; // Resources
    private int cityLevel; // City level
    private int technologyLevel; // Technology level
    private int cityImprovementLevel; // City improvement level
    private int population; // Population count
    private double taxRate; // Tax rate
    private int currency; // Currency amount
    private int vehicleCount; // Number of vehicles
    private int houseCount; // Number of houses

    public CivilizationGameGUI() {
        super("Civilization Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        // Initialize components
        mapLabel = new JLabel(new ImageIcon("map.jpg"));
        infoLabel = new JLabel("City Level: 0 | Technology Level: 1");
        woodLabel = new JLabel("Wood: 100");
        stoneLabel = new JLabel("Stone: 50");
        goldLabel = new JLabel("Gold: 20");
        cityLabel = new JLabel("City: Not Built", new ImageIcon("city.png"), SwingConstants.LEFT);
        technologyLabel = new JLabel("Technology: Not Researched", new ImageIcon("technology.png"), SwingConstants.LEFT);
        cityImprovementLabel = new JLabel("City Improvement: None", new ImageIcon("improvement.png"), SwingConstants.LEFT);
        populationLabel = new JLabel("Population: 0", new ImageIcon("population.png"), SwingConstants.LEFT);
        taxLabel = new JLabel("Tax Rate: 10%", new ImageIcon("tax.png"), SwingConstants.LEFT);
        currencyLabel = new JLabel("Currency: 0", new ImageIcon("currency.png"), SwingConstants.LEFT);
        vehicleLabel = new JLabel("Vehicles: 0", new ImageIcon("vehicle.png"), SwingConstants.LEFT);
        houseLabel = new JLabel("Houses: 0", new ImageIcon("house.png"), SwingConstants.LEFT);
        logTextArea = new JTextArea(10, 30);

        buildCityButton = new JButton("Build City");
        gatherResourcesButton = new JButton("Gather Resources");
        researchButton = new JButton("Research Technology");
        buildImprovementButton = new JButton("Build Improvement");
        adjustTaxButton = new JButton("Adjust Tax Rate");
        recruitVehicleButton = new JButton("Recruit Vehicle");
        buildHouseButton = new JButton("Build House");

        mapPanel = new JPanel(new BorderLayout());
        mapPanel.add(mapLabel, BorderLayout.CENTER);

        controlPanel = new JPanel(new GridLayout(8, 1));
        JPanel cityPanel = new JPanel(new FlowLayout());
        cityPanel.add(buildCityButton);
        controlPanel.add(cityPanel);
        JPanel resourcePanel = new JPanel(new FlowLayout());
        resourcePanel.add(gatherResourcesButton);
        controlPanel.add(resourcePanel);
        JPanel researchPanel = new JPanel(new FlowLayout());
        researchPanel.add(researchButton);
        controlPanel.add(researchPanel);
        JPanel improvementPanel = new JPanel(new FlowLayout());
        improvementPanel.add(buildImprovementButton);
        controlPanel.add(improvementPanel);
        JPanel taxPanel = new JPanel(new FlowLayout());
        taxPanel.add(adjustTaxButton);
        controlPanel.add(taxPanel);
        JPanel vehiclePanel = new JPanel(new FlowLayout());
        vehiclePanel.add(recruitVehicleButton);
        controlPanel.add(vehiclePanel);
        JPanel housePanel = new JPanel(new FlowLayout());
        housePanel.add(buildHouseButton);
        controlPanel.add(housePanel);

        infoPanel = new JPanel(new GridLayout(10, 2));
        infoPanel.add(infoLabel);
        infoPanel.add(new JLabel()); // Spacer
        infoPanel.add(woodLabel);
        infoPanel.add(stoneLabel);
        infoPanel.add(goldLabel);
        infoPanel.add(cityLabel);
        infoPanel.add(technologyLabel);
        infoPanel.add(cityImprovementLabel);
        infoPanel.add(populationLabel);
        infoPanel.add(taxLabel);
        infoPanel.add(currencyLabel);
        infoPanel.add(vehicleLabel);
        infoPanel.add(houseLabel);

        // Add components to the frame
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(mapPanel, BorderLayout.CENTER);
        c.add(controlPanel, BorderLayout.WEST);
        c.add(infoPanel, BorderLayout.EAST);
        c.add(new JScrollPane(logTextArea), BorderLayout.SOUTH);

        // Set up button actions
        buildCityButton.addActionListener(e -> buildCity());
        gatherResourcesButton.addActionListener(e -> gatherResources());
        researchButton.addActionListener(e -> researchTechnology());
        buildImprovementButton.addActionListener(e -> buildImprovement());
        adjustTaxButton.addActionListener(e -> adjustTaxRate());
        recruitVehicleButton.addActionListener(e -> recruitVehicle());
        buildHouseButton.addActionListener(e -> buildHouse());

        // Initialize game state
        wood = 100;
        stone = 50;
        gold = 20;
        cityLevel = 0;
        technologyLevel = 1;
        cityImprovementLevel = 0;
        population = 0;
        taxRate = 0.1; // Initial tax rate set to 10%
        currency = 0;
        vehicleCount = 0;
        houseCount = 0;

        updateInfoLabels();

        pack();
        setVisible(true);
    }

    private void buildCity() {
        if (wood >= 50 && stone >= 20 && gold >= 10) {
            wood -= 50;
            stone -= 20;
            gold -= 10;
            cityLevel++;
            cityLabel.setText("City: Built (Level " + cityLevel + ")");
            logTextArea.append("City built! Level: " + cityLevel + "\n");
            population += 10; // Increase population when building a city
        } else {
            logTextArea.append("Not enough resources to build a city.\n");
        }
        updateInfoLabels();
    }

    private void gatherResources() {
        wood += 10;
        stone += 5;
        gold += 2;
        logTextArea.append("Resources gathered.\n");
        updateInfoLabels();
    }

    private void researchTechnology() {
        if (gold >= 50) {
            gold -= 50;
            technologyLevel++;
            technologyLabel.setText("Technology: Researched (Level " + technologyLevel + ")");
            logTextArea.append("Technology researched! Level: " + technologyLevel + "\n");
        } else {
            logTextArea.append("Not enough gold to research technology.\n");
        }
        updateInfoLabels();
    }

    private void buildImprovement() {
        if (cityLevel > 0) {
            if (wood >= 30 && stone >= 20) {
                wood -= 30;
                stone -= 20;
                cityImprovementLevel++;
                cityImprovementLabel.setText("City Improvement: Built (Level " + cityImprovementLevel + ")");
                logTextArea.append("City improvement built! Level: " + cityImprovementLevel + "\n");
            } else {
                logTextArea.append("Not enough resources to build a city improvement.\n");
            }
        } else {
            logTextArea.append("You need to build a city first.\n");
        }
        updateInfoLabels();
    }

    private void adjustTaxRate() {
        String input = JOptionPane.showInputDialog(this, "Enter new tax rate (in percentage):");
        try {
            double newTaxRate = Double.parseDouble(input) / 100;
            if (newTaxRate >= 0 && newTaxRate <= 1) {
                taxRate = newTaxRate;
                taxLabel.setText("Tax Rate: " + (int) (taxRate * 100) + "%");
                logTextArea.append("Tax rate adjusted to " + (int) (taxRate * 100) + "%\n");
            } else {
                logTextArea.append("Invalid tax rate. Please enter a value between 0 and 100.\n");
            }
        } catch (NumberFormatException e) {
            logTextArea.append("Invalid input. Please enter a valid number.\n");
        }
    }

    private void recruitVehicle() {
        // Assuming recruiting a vehicle costs resources
        if (wood >= 50 && stone >= 20 && gold >= 30) {
            wood -= 50;
            stone -= 20;
            gold -= 30;
            vehicleCount++;
            vehicleLabel.setText("Vehicles: " + vehicleCount);
            logTextArea.append("Vehicle recruited!\n");
        } else {
            logTextArea.append("Not enough resources to recruit a vehicle.\n");
        }
        updateInfoLabels();
    }

    private void buildHouse() {
        // Assuming building a house costs resources and increases population capacity
        if (wood >= 30 && stone >= 10) {
            wood -= 30;
            stone -= 10;
            houseCount++;
            population += 5; // Increase population capacity by 5
            houseLabel.setText("Houses: " + houseCount);
            logTextArea.append("House built!\n");
        } else {
            logTextArea.append("Not enough resources to build a house.\n");
        }
        updateInfoLabels();
    }

    private void updateInfoLabels() {
        infoLabel.setText("City Level: " + cityLevel + " | Technology Level: " + technologyLevel);
        woodLabel.setText("Wood: " + wood);
        stoneLabel.setText("Stone: " + stone);
        goldLabel.setText("Gold: " + gold);
        populationLabel.setText("Population: " + population);
        taxLabel.setText("Tax Rate: " + (int) (taxRate * 100) + "%");
        currencyLabel.setText("Currency: " + currency);
        vehicleLabel.setText("Vehicles: " + vehicleCount);
        houseLabel.setText("Houses: " + houseCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CivilizationGameGUI::new);
    }
}
