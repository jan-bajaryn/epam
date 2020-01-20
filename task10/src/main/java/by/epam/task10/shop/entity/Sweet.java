package by.epam.task10.shop.entity;

public class Sweet {
    private String name;
    private int count;

    public Sweet(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Sweet() {
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public Sweet setCount(int count) {
        return new Sweet(this.name, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sweet sweet = (Sweet) o;
        return name != null ? name.equals(sweet.name) : sweet.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Sweet{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

}
