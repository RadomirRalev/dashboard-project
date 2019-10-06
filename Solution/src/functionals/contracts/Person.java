package functionals.contracts;

import functionals.contracts.BoardAndPerson;

import java.util.List;

public interface Person extends Functionals {

    void assignWork();

    void unassignWork();

    List listAssignedWork();
}
