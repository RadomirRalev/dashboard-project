package functionals.models;

import functionals.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamsImpl implements Team {

    private String name;
    private List<MemberImpl> members;
    private List<BoardImpl> boards;

    public TeamsImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void addMember(MemberImpl member) {
        members.add(member);
    }

    @Override
    public void removeMember(MemberImpl member) {
        members.remove(member);
    }

    @Override
    public List showTeamMembers() {
        return members;
    }

    @Override
    public void addBoard(BoardImpl board) {
        boards.add(board);
    }

    @Override
    public void removeBoard(BoardImpl board) {
        boards.remove(board);
    }

    @Override
    public List showBoards() {
        return boards;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Team name: %s\n" +
                        " Members: %s\n" +
                        " Boards: %s",
                getName(),
                showTeamMembers(),
                showBoards());
    }
}
