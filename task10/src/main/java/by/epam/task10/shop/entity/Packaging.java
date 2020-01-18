package by.epam.task10.shop.entity;

public class Packaging {

    private PackageSize size;
    private PackageColor color;

    public Packaging(PackageSize size, PackageColor color) {
        this.size = size;
        this.color = color;
    }



    public enum PackageSize {
        SMALL(25), MIDDLE(50), BIG(100);
        private int size;

        PackageSize(int size) {
            this.size = size;
        }
    }

    public enum PackageColor {
        RED, BLUE, WHITE, GREEN;
    }

    public PackageSize getSize() {
        return size;
    }


    public PackageColor getColor() {
        return color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Packaging packaging = (Packaging) o;

        if (size != packaging.size) return false;
        return color == packaging.color;
    }

    @Override
    public int hashCode() {
        int result = size != null ? size.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Packaging{" +
                "size=" + size +
                ", color=" + color +
                '}';
    }
}
