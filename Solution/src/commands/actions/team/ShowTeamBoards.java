//package commands.actions.team;
//
//import commands.contracts.Command;
//import core.FunctionalsRepositoryImpl;
//
//import java.util.List;
//import java.util.Set;
//import java.util.StringJoiner;
//
//import static commands.actions.CommandsConstants.INVALID_NUMBER_OF_ARGUMENTS;
//
//public class ShowTeamBoards implements Command {
//    //TODO Finish implementation of this class
//    private static final int CORRECT_NUMBER_OF_ARGUMENTS = 1;
//    private FunctionalsRepositoryImpl functionalsRepository;
//
//
//    public ShowTeamBoards(FunctionalsRepositoryImpl functionalsRepository) {
//        this.functionalsRepository = functionalsRepository;
//    }
//
//    @Override
//    public String execute(List<String> parameters) {
//        if (parameters.size() != CORRECT_NUMBER_OF_ARGUMENTS) {
//            throw new IllegalArgumentException(INVALID_NUMBER_OF_ARGUMENTS);
//        }
//        StringJoiner str = new StringJoiner(",\n");
//        Set<String> keys = functionalsRepository.getTeams().keySet();
//        for (String k : keys) {
//            str.add(k);
//        }
//        String teamsList = str.toString(); //in alphabetical order
//        return listTeams(teamsList);
//    }
//
//    private String listTeamBoards(String teamsList) {
//
//        if (functionalsRepository.getTeams().size() == 0) {
//            return TEAMSLIST_IS_EMPTY;
//        }
//        return String.format(TEAMSLIST_INCLUDES, teamsList);
//    }
//}
