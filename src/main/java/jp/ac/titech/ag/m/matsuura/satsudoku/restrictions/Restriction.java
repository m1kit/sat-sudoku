package jp.ac.titech.ag.m.matsuura.satsudoku.restrictions;

import jp.ac.titech.ag.m.matsuura.satsudoku.VariableMapper;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

public abstract class Restriction {

    public abstract void apply(ISolver solver, VariableMapper mapper) throws ContradictionException;

}
