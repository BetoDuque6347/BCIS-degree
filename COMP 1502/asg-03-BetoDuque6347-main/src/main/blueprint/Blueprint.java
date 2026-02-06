package main.blueprint;

import main.space.Space;

public class Blueprint {
    private final String BLUEPRINT_CARD;

    public Blueprint(String card) {
        BLUEPRINT_CARD = card.replace(" ", "");
    }

    public boolean isOpenSpace(Space space) {
        int coordinate = findCoordinate(space);

        if (BLUEPRINT_CARD.charAt(coordinate) == 'X') {
            return false;
        }

        return true;
    }

    public boolean isProhibitedSpace(Space space) {
        if (!isOpenSpace(space)) {
            return true;
        }

        return false;
    }

    public int heightTargetAt(Space space) {
        int coordinate = findCoordinate(space);
        int targetHeight = 0;

        if (BLUEPRINT_CARD.charAt(coordinate) != 'X')
            targetHeight = Character.getNumericValue(BLUEPRINT_CARD.charAt(coordinate));

        return targetHeight;
    }

    public int findCoordinate(Space space) {
        //Adjust for base-0 indexing
        int row = space.rowVal() - 1;
        int col = space.colVal() - 1;

        // If you change the row in a blueprint, you change the index by two
        // If you change the column in a blueprint, you change the index by one
        int coordinate = (2 * row) + col;

        return coordinate;
    }

    public String toString() {
        String formattedBlueprint = "";

        for (int i = 0; i < BLUEPRINT_CARD.length(); i++) {
            // Recreate the blueprint as a string from stratch
            formattedBlueprint += BLUEPRINT_CARD.charAt(i);

            // Add a newline character every 2 spaces
            if (i % 2 == 1)
                formattedBlueprint += "\n";
        }

        formattedBlueprint = formattedBlueprint.trim();

        return formattedBlueprint;
    }
}
