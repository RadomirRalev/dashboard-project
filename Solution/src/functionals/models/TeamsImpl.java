package functionals.models;

import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamsImpl implements Team {

    private String name;
    private List<Person> members;
    private List<Board> boards;

    public TeamsImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void addMember(Person member) {
        members.add(member);
    }

    @Override
    public void removeMember(Person member) {
        members.remove(member);
    }

    @Override
    public List showTeamMembers() {
        return members;
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
    }

    @Override
    public void removeBoard(Board board) {
        boards.remove(board);
    }

    @Override
    public String showBoards() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Team - %s has the following boards:\n", getName()));

        for (Board board: boards) {
            str.append(board + System.lineSeparator());
        }
        return str.toString().trim();
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
