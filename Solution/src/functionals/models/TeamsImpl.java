package functionals.models;

import functionals.contracts.Board;
import functionals.contracts.Person;
import functionals.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamsImpl implements Team {

    private String name;
    private List<MemberImpl> members;
    private List<Board> boards;

    public TeamsImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
    }

    public void setName(String name) {
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
    public void addBoard() {

    }

    @Override
    public void removeBoard() {

    }

    @Override
    public String getName() {
        return name;
    }
}
