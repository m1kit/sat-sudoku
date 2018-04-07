package jp.ac.titech.ag.m.matsuura.satsudoku.restrictions;

import jp.ac.titech.ag.m.matsuura.satsudoku.Variable;
import jp.ac.titech.ag.m.matsuura.satsudoku.VariableMapper;
import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

public class BlockRestriction extends Restriction {
    public void apply(ISolver solver, VariableMapper mapper) throws ContradictionException {
        for (int i = 0; i < mapper.size(); i++) {//i is: number
            for (int bx = 0; bx < mapper.order(); bx++) {//bx is: x of block
                for (int by = 0; by < mapper.order(); by++) {
                    for (int j1 = 0; j1 < mapper.size(); j1++) {//j is: index in block
                        for (int j2 = 0; j2 < j1; j2++) {
                            int one = mapper.encode(new Variable(
                                    bx * mapper.order() + (j1 / mapper.order()),
                                    by * mapper.order() + (j1 % mapper.order()),
                                    i + 1
                            ));
                            int other = mapper.encode(new Variable(
                                    bx * mapper.order() + (j2 / mapper.order()),
                                    by * mapper.order() + (j2 % mapper.order()),
                                    i + 1
                            ));
                            solver.addClause(new VecInt(new int[]{-one, -other}));
                        }
                    }
                }
            }
        }
    }
}
