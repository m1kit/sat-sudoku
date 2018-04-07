package jp.ac.titech.ag.m.matsuura.satsudoku;

import java.io.PrintStream;

public class ModelDecoder {

    private final int[] model;

    public ModelDecoder(int[] model) {
        this.model = model;
    }

    public void print(VariableMapper mapper, PrintStream out) {
        byte[][] result = decode(mapper);
        for (int y = 0; y < result.length; y++) {
            for (int x = 0; x < result[y].length; x++) {
                System.out.print(result[y][x]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public byte[][] decode(VariableMapper mapper) {
        byte[][] result = new byte[mapper.size()][];
        for (int y = 0; y < mapper.size(); y++) {
            result[y] = new byte[mapper.size()];
        }

        for (int code : model) {
            var v = mapper.decode(code);
            if (code > 0) {
                if (result[v.getY()][v.getX()] != 0) {
                    throw new IllegalStateException("Multiple numbers in the same column: "+code);
                }
                result[v.getY()][v.getX()] = v.getNumber();
            }
        }
        return result;
    }
}
