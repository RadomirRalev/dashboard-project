package functionals.contracts;

import functionals.models.MemberImpl;

import java.util.List;

public interface Team extends Functionals {

    void addMember(Person member);

    void removeMember(MemberImpl member);

    List showTeamMembers();

    void addBoard(Board board);

    String showBoards();

    List<Board> getBoards();
}
