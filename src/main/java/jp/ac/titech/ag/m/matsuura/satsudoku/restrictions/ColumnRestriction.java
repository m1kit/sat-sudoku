package jp.ac.titech.ag.m.matsuura.satsudoku.restrictions;

import jp.ac.titech.ag.m.matsuura.satsudoku.Variable;
import jp.ac.titech.ag.m.matsuura.satsudoku.VariableMapper;
import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

public class ColumnRestriction extends Restriction {
    public void apply(ISolver solver, VariableMapper mapper) throws ContradictionException {
        for (int x = 0; x < mapper.size(); x++) {
            for (int y = 0; y < mapper.size(); y++) {
                int[] literals = new int[mapper.size()];
                for (int i = 0; i < mapper.size(); i++) {
                    literals[i] = mapper.encode(new Variable(x, y, i + 1));
                }
                solver.addClause(new VecInt(literals));
            }
        }
    }
}
