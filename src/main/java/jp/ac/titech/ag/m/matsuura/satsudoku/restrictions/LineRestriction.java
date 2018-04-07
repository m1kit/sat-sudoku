package jp.ac.titech.ag.m.matsuura.satsudoku.restrictions;

import jp.ac.titech.ag.m.matsuura.satsudoku.Variable;
import jp.ac.titech.ag.m.matsuura.satsudoku.VariableMapper;
import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

public class LineRestriction extends Restriction {

    private final Direction direction;

    public LineRestriction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void apply(ISolver solver, VariableMapper mapper) throws ContradictionException {
        for (int d1 = 0; d1 < mapper.size(); d1++) {
            for (int i = 0; i < mapper.size(); i++) {
                for (int d2 = 0; d2 < mapper.size(); d2++) {
                    for (int d3 = 0; d3 < d2; d3 ++) {
                        int one, other;
                        switch (direction) {
                            case HORIZONTAL:
                                one = mapper.encode(new Variable(d2, d1, i + 1));
                                other = mapper.encode(new Variable(d3, d1, i + 1));
                                break;
                            case VERTICAL:
                                one = mapper.encode(new Variable(d2, d1, i + 1));
                                other = mapper.encode(new Variable(d3, d1, i + 1));
                                break;
                            default:
                                throw new UnsupportedOperationException("Unknown direction");
                        }
                        solver.addClause(new VecInt(new int[]{-one, -other}));
                    }
                }
            }
        }
    }

    public enum Direction {
        HORIZONTAL,
        VERTICAL,
    }
}
