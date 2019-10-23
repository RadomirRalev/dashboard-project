package functionals.models;

import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamsImpl implements Team {
    private static final Map<String, ArrayList<String>> teamsActivity = new HashMap<>();
    private String name;
    private List<Person> members;
    private List<Board> boards;

    public TeamsImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
    }

    public static Map<String, ArrayList<String>> getTeamsActivity() {
        return teamsActivity;
    }

    public static void addActivity(String activity, String name) {
        getTeamsActivity().get(name).add(activity);
    }

    @Override
    public void addMember(Person member) {
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
    public void addBoard(Board board) {
        ValidationHelper.checkIfBoardExistsInTeam(board, this);
        boards.add(board);
    }

    @Override
    public String showBoards() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Team - %s has the following boards:\n", getName()));

        for (Board board : boards) {
            str.append(board).append(System.lineSeparator());
        }
        return str.toString().trim();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public String toString() {
        return String.format("Team teamName: %s\n" +
                        " Members: %s\n" +
                        " Boards: %s",
                getName(),
                showTeamMembers(),
                showBoards());
    }

    private void setName(String name) {
        this.name = name;
    }
}
