package interfacePackage;

import enumPackage.Dishes;
import myExceptionPackage.OverloadedTableException;

public interface SetTable{
    public void setTheTable(Dishes dishes) throws OverloadedTableException;
}


