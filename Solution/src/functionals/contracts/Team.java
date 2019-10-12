package functionals.contracts;

import functionals.models.BoardImpl;
import functionals.models.MemberImpl;
import functionals.models.PersonImpl;

import java.util.List;

public interface Team extends Functionals {

    void addMember(Person member);

    void removeMember(Person member);

    List showTeamMembers();

    void addBoard(Board board);

    void removeBoard(Board board);

    String showBoards();

    void addActivity(String activity);

}
