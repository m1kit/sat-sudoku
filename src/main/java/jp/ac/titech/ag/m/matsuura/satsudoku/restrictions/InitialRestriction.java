package jp.ac.titech.ag.m.matsuura.satsudoku.restrictions;

import jp.ac.titech.ag.m.matsuura.satsudoku.Variable;
import jp.ac.titech.ag.m.matsuura.satsudoku.VariableMapper;
import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

public class InitialRestriction extends Restriction {

    private final byte[][] data;

    public InitialRestriction(byte[][] data) {
        this.data = data;
    }

    public void apply(ISolver solver, VariableMapper mapper) throws ContradictionException {
        for (int x = 0; x < mapper.size(); x++) {
            for (int y = 0; y < mapper.size(); y++) {
                if (data[y][x] == 0) {
                    continue;
                }
                solver.addClause(new VecInt(new int[]{
                        mapper.encode(new Variable(x, y, data[y][x]))
                }));
            }
        }
    }
}
