import java.util.HashMap;

public class BaseballElimination {
    private String[] teams;
    private int numberOfTeams;

    private HashMap<String, Integer> teamsMap;

    private int[] wins;
    private int[] losses;
    private int[] remaining;

    private int[][] against;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);

        String s = in.readLine();
        numberOfTeams = Integer.parseInt(s);
        teams = new String[numberOfTeams];
        wins = new int[numberOfTeams];
        losses = new int[numberOfTeams];
        remaining = new int[numberOfTeams];
        against = new int[numberOfTeams][numberOfTeams];
        teamsMap = new HashMap<String, Integer>();

        for (int i = 0; i < numberOfTeams && !in.isEmpty(); i++) {
            s = in.readLine();
            s = s.trim();
            String[] ss = s.split("\\s+");
            
            teams[i] = ss[0];
            teamsMap.put(ss[0], i);
            wins[i] = Integer.parseInt(ss[1]);
            losses[i] = Integer.parseInt(ss[2]);
            remaining[i] = Integer.parseInt(ss[3]);

            for (int j = 0; j < numberOfTeams; j++) {
                against[i][j] = Integer.parseInt(ss[4 + j]);
            }

        }
    }

    // number of teams
    public int numberOfTeams() {
        return numberOfTeams;
    }

    // all teams
    public Iterable<String> teams() {
        Stack<String> s = new Stack<String>();
        for (int i = 0; i < numberOfTeams; i++) {
            s.push(teams[i]);
        }
        return s;
    }

    // number of wins for given team
    public int wins(String team) {
        if (!teamsMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }
        return wins[teamsMap.get(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        if (!teamsMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }
        return losses[teamsMap.get(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (!teamsMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }
        return remaining[teamsMap.get(team)];
    }
    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (!teamsMap.containsKey(team1) || !teamsMap.containsKey(team2)) {
            throw new IllegalArgumentException();
        }
        return against[teamsMap.get(team1)][teamsMap.get(team2)];
    }
    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (!teamsMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }

        int teamIDX = teamsMap.get(team);

        int gameVertices = 0;
        for (int i = 1; i < numberOfTeams - 1; i++) {
            gameVertices += i;
        }
        final int SOURCE = gameVertices + numberOfTeams;
        final int SINK = gameVertices + numberOfTeams + 1;
        final int INFINITY_CAPACITY = Integer.MAX_VALUE;

        FlowNetwork network = new FlowNetwork(gameVertices + numberOfTeams + 2);

        int sourceCapacity = 0;
        int k = numberOfTeams;
        for (int i = 0; i < numberOfTeams; i++) {
            if (i == teamIDX) {
                continue;
            }
            int sinkCapacity = wins[teamIDX] + remaining[teamIDX] - wins[i];
            if (sinkCapacity < 0) {
                return true;
            }
            for (int j = i + 1; j < numberOfTeams; j++) {
                if (j == teamIDX) {
                    continue;
                }

                //games left between teams
                network.addEdge(new FlowEdge(SOURCE, k, against[i][j])); 
                sourceCapacity += against[i][j];
                network.addEdge(new FlowEdge(k, i, INFINITY_CAPACITY));
                network.addEdge(new FlowEdge(k++, j, INFINITY_CAPACITY));
                
            }
            network.addEdge(new FlowEdge(i, SINK, sinkCapacity)); 
        }
        FordFulkerson ff = new FordFulkerson(network, SOURCE, SINK);
        
        if (sourceCapacity != ff.value()) {
            return true; 
        }
        return false;
    }
    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (!teamsMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }
        Stack<String> s = new Stack<String>();
        if (!teamsMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }

        int teamIDX = teamsMap.get(team);

        int gameVertices = 0;
        for (int i = 1; i < numberOfTeams - 1; i++) {
            gameVertices += i;
        }

        final int SOURCE = gameVertices + numberOfTeams;
        final int SINK = gameVertices + numberOfTeams + 1;
        final int INFINITY_CAPACITY = Integer.MAX_VALUE;

        FlowNetwork network = new FlowNetwork(gameVertices + numberOfTeams + 2);

        int sourceCapacity = 0;
        int k = numberOfTeams;
        for (int i = 0; i < numberOfTeams; i++) {
            if (i == teamIDX) {
                continue;
            }
            int sinkCapacity = wins[teamIDX] + remaining[teamIDX] - wins[i];
            if (sinkCapacity < 0) { 
                s.push(teams[i]);
                continue;
            }
            network.addEdge(new FlowEdge(i, SINK, sinkCapacity)); 

            for (int j = i + 1; j < numberOfTeams; j++) {
                if (j == teamIDX) {
                    continue;
                }
                network.addEdge(new FlowEdge(SOURCE, k, against[i][j])); 
                sourceCapacity += against[i][j];
                network.addEdge(new FlowEdge(k, i, INFINITY_CAPACITY));
                network.addEdge(new FlowEdge(k++, j, INFINITY_CAPACITY));   
            }
        }

        FordFulkerson ff = new FordFulkerson(network, SOURCE, SINK);
        for (int i = 0; i < numberOfTeams; i++) {
            if (ff.inCut(i)) {
                s.push(teams[i]);
            }
        }
        if (s.isEmpty()) {
            return null;
        }
        return s;
    }
}