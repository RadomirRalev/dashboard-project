package functionals.contracts;

import functionals.models.BoardImpl;
import functionals.models.MemberImpl;

import java.util.List;

public interface Team extends Functionals {

    void addMember(MemberImpl member);

    void removeMember(MemberImpl member);

    List showTeamMembers();

    void addBoard(Board board);

    void removeBoard(Board board);

    List showBoards();

}
