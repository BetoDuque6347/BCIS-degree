package main.building;

import java.util.ArrayList;

import main.violations.RuleViolation;
import main.violations.ViolationList;

public class DiceStack {
    private ArrayList<Die> dice;
    private int height;
    private ViolationList violations;

    public DiceStack() {
        dice = new ArrayList<>();
        height = 0;
        violations = new ViolationList();
    }

    public DiceStack(DiceStack stack) {
        this.dice = new ArrayList<>();
        this.height = stack.height;
        this.violations = new ViolationList();

        for (Die die : stack.getDice()) {
            this.dice.add(die);
        }
    }

    public void add(Die die) {
        dice.add(die);
        height++;
    }

    public boolean isEmpty() {
        if (height == 0)
            return true;

        return false;
    }

    public boolean isValid() {
        // Loop through the stack and compare two faces
        for (int i = 0; i < dice.size() - 1; i++) {
            if (dice.get(i).getFace() > dice.get(i + 1).getFace()) {
                violations.add(RuleViolation.DESCENDING_DICE);
            }
        }

        if (height > 6) {
            violations.add(RuleViolation.STACK_OVERLARGE);
        }

        return !violations.hasViolations();
    }

    public Die getDie(int level) {
        if (level > height) {
            return null;
        }

        if (level > 0) {
            level--; // Adjust for base-0 indexing
            return dice.get(level);
        }

        return null;
    }

    public ArrayList<Die> getDice() {
        ArrayList<Die> diceToReturn = new ArrayList<>();

        for (Die die : dice) {
            diceToReturn.add(die);
        }

        return diceToReturn;
    }

    public int getHeight() {
        return height;
    }

    public ViolationList getViolations() {
        return violations;
    }

    public String toString() {
        String formattedDiceStack = dice.toString().replaceAll(",", "");
        return formattedDiceStack;
    }
}
