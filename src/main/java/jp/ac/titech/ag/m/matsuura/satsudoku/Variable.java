package jp.ac.titech.ag.m.matsuura.satsudoku;

public class Variable {

    private final byte x;
    private final byte y;
    private final byte number;

    public Variable(int x, int y, int number) {
        this.x = (byte) x;
        this.y = (byte) y;
        this.number = (byte) number;
    }

    byte getX() {
        return x;
    }

    byte getY() {
        return y;
    }

    byte getNumber() {
        return number;
    }
}
