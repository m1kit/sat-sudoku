package jp.ac.titech.ag.m.matsuura.satsudoku;

import jp.ac.titech.ag.m.matsuura.satsudoku.restrictions.BlockRestriction;
import jp.ac.titech.ag.m.matsuura.satsudoku.restrictions.ColumnRestriction;
import jp.ac.titech.ag.m.matsuura.satsudoku.restrictions.InitialRestriction;
import jp.ac.titech.ag.m.matsuura.satsudoku.restrictions.LineRestriction;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import java.util.stream.Stream;

public class Main {

    private static final byte ORDER = 3;
    private static final byte[][] INITIAL_STATE = {
            {0, 0, 0, 0, 0, 7, 0, 2, 0},
            {8, 0, 6, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 9, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 8, 5, 0, 0, 6},
            {0, 0, 0, 0, 0, 0, 4, 0, 0},
            {0, 0, 0, 0, 6, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 7, 0},
            {0, 1, 0, 0, 4, 0, 0, 0, 0},
    };

    public static void main(String[] args) {
        var solver = SolverFactory.newDefault();
        var mapper = new VariableMapper(ORDER);

        solver.newVar(mapper.getMaxVariables());

        Stream.of(
                new ColumnRestriction(),
                new LineRestriction(LineRestriction.Direction.HORIZONTAL),
                new LineRestriction(LineRestriction.Direction.VERTICAL),
                new BlockRestriction(),
                new InitialRestriction(INITIAL_STATE)
        ).forEach(restriction -> {
            try {
                restriction.apply(solver, mapper);
            } catch(ContradictionException ex) {
                ex.printStackTrace();
            }
        });

        try {
            var sat = solver.isSatisfiable();
            if (sat) {
                new ModelDecoder(solver.model()).print(mapper, System.out);
            } else {
                System.out.println("Not satisfiable");
            }
        } catch(TimeoutException ex) {
            ex.printStackTrace();
        }
    }

}
