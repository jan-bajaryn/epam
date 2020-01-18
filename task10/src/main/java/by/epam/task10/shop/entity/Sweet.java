package by.epam.task10.shop.entity;

public class Sweet {
    private String name;
    private int size;
    private int count;

    public Sweet(String name, int size, int count) {
        this.name = name;
        this.size = size;
        this.count = count;
    }

    public Sweet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sweet sweet = (Sweet) o;

        if (size != sweet.size) return false;
        if (count != sweet.count) return false;
        return name != null ? name.equals(sweet.name) : sweet.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + size;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "Sweet{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", count=" + count +
                '}';
    }
}
