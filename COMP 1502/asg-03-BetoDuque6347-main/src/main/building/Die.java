package main.building;

public class Die {
    private final Material material;
    private final int faceValue;

    public Die(String dieType) {
        char dieMaterial = dieType.charAt(0);

        switch (dieMaterial) {
            case 'W':
                material = Material.WOOD;
                break;
            case 'R':
                material = Material.RECYCLED;
                break;
            case 'S':
                material = Material.STONE;
                break;
            case 'G':
                material = Material.GLASS;
                break;
            default:
                material = null; // Somehow, something went wrong.
        }

        faceValue = Character.getNumericValue(dieType.charAt(1));
    }

    public Die(Material material, int faceValue) {
        this.material = material;
        this.faceValue = faceValue;
    }

    public Die(Die die) {
        this.material = die.material;
        this.faceValue = die.faceValue;
    }

    public Material getMaterial() {
        return material;
    }

    public int getFace() {
        return faceValue;
    }

    public String toString() {
        String dieString = "";

        switch (material) {
            case WOOD:
                dieString = "W";
                break;
            case RECYCLED:
                dieString = "R";
                break;
            case STONE:
                dieString = "S";
                break;
            case GLASS:
                dieString = "G";
        }

        dieString += faceValue;
        return dieString;
    }
}