package main.building;

import java.util.ArrayList;

import main.scorers.GlassScorer;
import main.scorers.RecycledScorer;
import main.scorers.StoneScorer;
import main.scorers.WoodScorer;
import main.space.Space;
import main.violations.RuleViolation;
import main.violations.ViolationList;

/**
 * Represents a Building as shown in Blueprints by Yves Tourigny
 * 
 * <ul>
 * <li>Uses a 2D Array to hold each Row and Column
 * <li>May or may not be a valid building
 * </ul>
 */
public class Building {
    private DiceStack[][] building;
    private int height;
    private boolean isValid;
    private int numberOfDice;
    private ViolationList violations;

    public Building() {
        building = new DiceStack[3][2];
        height = 0;
        isValid = true;
        numberOfDice = 0;
        violations = new ViolationList();

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 2; k++) {
                building[i][k] = new DiceStack();
            }
        }
    }

    public Building(Building b) {
        this.building = new DiceStack[3][2];
        this.height = b.getHeight();
        this.isValid = b.isValid();
        this.numberOfDice = b.getNumDice();
        this.violations = new ViolationList();

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 2; k++) {
                building[i][k] = new DiceStack();
            }
        }
    }

    public void add(Die die, Space space) {
        // adjust for base-0 indexing
        int row = space.rowVal() - 1;
        int col = space.colVal() - 1;

        building[row][col].add(die);
        numberOfDice++;

        // Re calculate height after.
        int stackHeight = building[0][0].getHeight();

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 2; k++) {
                if (building[i][k].getHeight() > stackHeight) {
                    stackHeight = building[i][k].getHeight();
                }
            }
        }

        height = stackHeight;
    }

    public void add(DiceStack stack, Space space) {
        // adjust for base-0 indexing
        int row = space.rowVal() - 1;
        int col = space.colVal() - 1;

        for (Die die : stack.getDice()) {
            building[row][col].add(die);
        }

        if (stack.getHeight() > height) {
            height = stack.getHeight();
        }
    }

    public boolean isEmpty() {
        return !(height > 0);
    }

    public boolean isValid() {
        if (height > 6) {
            violations.add(RuleViolation.STACK_OVERLARGE);
            isValid = false;
        }

        return isValid;
    }

    public Die getDie(Space space, int level) {
        // adjust for base-0 indexing
        int row = space.rowVal() - 1;
        int col = space.colVal() - 1;
        level--;

        Die die = building[row][col].getDie(level);
        return die;
    }

    public DiceStack[][] getStack(Space space, int col) {
        return building;
    }

    public int getNumDice() {
        return numberOfDice;
    }

    public int getHeight() {
        return height;
    }

    public ViolationList getViolations() {
        return violations;
    }

    public RecycledScorer recycledScorer() {
        return new RecycledScorer(this);
    }

    public StoneScorer stoneScorer() {
        return new StoneScorer(this);
    }

    public GlassScorer glassScorer() {
        return new GlassScorer(this);
    }

    public WoodScorer woodScorer() {
        return new WoodScorer(this);
    }

    public ArrayList<Die> all(Material material) {
        ArrayList<Die> stackOfMaterial = new ArrayList<>();

        for (int row = 0; row < building.length; row++) {

            for (int col = 0; col < building[col].length; col++) {

                ArrayList<Die> currentStack = building[row][col].getDice();

                for (Die die : currentStack) {

                    if (die.getMaterial() == material) {
                        stackOfMaterial.add(die);
                    }
                }
            }
        }

        return stackOfMaterial;
    }

    public ArrayList<Die> allOnLevel(Material material, int height) {
        return null;
    }

    public ArrayList<Die> AllAdjacentTo(Material material) {
        return null;
    }

    /**
     * Made ScoringResultsFileWriter.java easier to write to
     */
    public String toString() {
        String formattedBuilding = "";

        // Loop through rows (z axis)
        for (int i = 0; i < building.length; i++) {

            // Loop through each stack (y axis)
            for (int k = height - 1; k >= 0; k--) {

                // Loop through the columns (x axis)
                for (int j = 0; j < building[0].length; j++) {

                    // The selected element to inspect
                    Die die = building[i][j].getDie(k + 1);

                    if (die == null) {
                        formattedBuilding += "--";
                    } else {
                        formattedBuilding += die;
                    }

                    // Add to separate columns and heights from each other
                    if (j < building[0].length - 1) {
                        formattedBuilding += "|";
                    }
                }

                formattedBuilding += "\n";
            }

            if (i < building.length - 1) {
                formattedBuilding += "==+==\n";
            }
        }

        return formattedBuilding;
    }
}
