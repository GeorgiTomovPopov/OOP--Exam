package handball.entities.gameplay;

import handball.entities.equipment.Equipment;
import handball.entities.team.Team;

import java.util.ArrayList;
import java.util.Collection;

import static handball.common.ExceptionMessages.GAMEPLAY_NAME_NULL_OR_EMPTY;


public class BaseGameplay implements Gameplay{

    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private Collection<Team> teams;


    public BaseGameplay(String name, int capacity) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(GAMEPLAY_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
        this.capacity = capacity;
        equipments = new ArrayList<>();
        teams = new ArrayList<>();
    }

    @Override
    public int allProtection() {
        int sum = 0;

        for (Equipment equipment:
             equipments) {
            sum += equipment.getProtection();
        }

        return sum;
    }

    @Override
    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public void teamsInGameplay() {
        for (Team team :
                teams) {
            team.play();
        }
    }

    @Override
    public Collection<Team> getTeam() {
        return this.teams;
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return this.equipments;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
            String gameplayName = this.getName();
            String gameplayType = this.getClass().getSimpleName();

            StringBuilder sb = new StringBuilder();
            sb.append(gameplayName).append(" ").append(gameplayType).append(System.lineSeparator());
            sb.append("Team:");
            if (teams.isEmpty()) {
                sb.append(" none");
                sb.append(System.lineSeparator());
            } else {
                for (Team team :
                        teams) {
                    sb.append(" ").append(team.getName());
                }
            }
            sb.append(System.lineSeparator());
            sb.append("Equipment: ").append(equipments.size()).append(", ");
            sb.append("Protection: ").append(allProtection());
            sb.append(System.lineSeparator());




        return sb.toString();
    }
}
