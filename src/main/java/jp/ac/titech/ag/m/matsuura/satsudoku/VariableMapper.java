package jp.ac.titech.ag.m.matsuura.satsudoku;

public class VariableMapper {

    private final byte order;

    public VariableMapper(byte order) {
        if (order <= 0) {
            throw new IllegalArgumentException("Illegal sudoku size");
        }
        this.order = order;
    }

    public int getMaxVariables() {
        return sizeCubed();
    }

    public int encode(Variable var) {
        return var.getX() * sizeSquared() + var.getY() * size() + var.getNumber();
    }

    public Variable decode(int code) {
        var x = (code - 1)  / sizeSquared();
        var y = (code - x * sizeSquared() - 1) / size();
        var number = code - x * sizeSquared() - y * size();
        return new Variable(x, y, number);
    }

    public byte order() {
        return order;
    }

    public int size() {
        return order * order;
    }

    public int sizeSquared() {
        return order * order * order * order;
    }

    public int sizeCubed() {
        return order * order * order * order * order * order;
    }
}
