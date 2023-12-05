package handball.core;

import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;
import handball.repositories.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import static handball.common.ConstantMessages.*;
import static handball.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository equipment;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        equipment = new EquipmentRepository();
        gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        switch (gameplayType) {
            case "Indoor":
                gameplays.add(new Indoor(gameplayName));
                break;
            case "Outdoor":
                gameplays.add(new Outdoor(gameplayName));
                break;
            default:
                throw new NullPointerException(INVALID_GAMEPLAY_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        switch (equipmentType) {
            case "Kneepad":
                equipment.add(new Kneepad());
                break;
            case "ElbowPad":
                equipment.add(new ElbowPad());
                break;
            default:
                throw new IllegalArgumentException(INVALID_EQUIPMENT_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment currentEquipment = equipment.findByType(equipmentType);
        if (currentEquipment == null) {
            throw new IllegalArgumentException(String.format(NO_EQUIPMENT_FOUND, equipmentType));
        }

        for (Gameplay gameplay : gameplays) {
            if (gameplay.getName().equals(gameplayName)) {
                gameplay.addEquipment(currentEquipment);
            }
        }

        equipment.remove(currentEquipment);

        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {

        switch (teamType) {
            case "Bulgaria":
                for (Gameplay gameplay : gameplays) {
                    if (gameplay.getName().equals(gameplayName)) {
                        String gameplayType = gameplay.getClass().getSimpleName();
                        if (gameplayType.equals("Outdoor")) {
                            gameplay.addTeam(new Bulgaria(teamName, country, advantage));
                            return String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
                        } else {
                            return GAMEPLAY_NOT_SUITABLE;
                        }
                    }
                }
                break;
            case "Germany":
                for (Gameplay gameplay : gameplays) {
                    if (gameplay.getName().equals(gameplayName)) {
                        String gameplayType = gameplay.getClass().getSimpleName();
                        if (gameplayType.equals("Indoor")) {
                            gameplay.addTeam(new Germany(teamName, country, advantage));
                            return String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
                        } else {
                            return GAMEPLAY_NOT_SUITABLE;
                        }
                    }
                }
                break;
            default:
                throw new IllegalArgumentException(INVALID_TEAM_TYPE);
        }


        return null;
    }

    @Override
    public String playInGameplay(String gameplayName) {
        int size = 0;
        for (Gameplay gameplay : gameplays) {
            if (gameplay.getName().equals(gameplayName)) {
                gameplay.teamsInGameplay();
                size = gameplay.getTeam().size();
            }
        }
        return String.format(TEAMS_PLAYED, size);
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        long sum = 0;
        for (Gameplay gameplay : gameplays) {
            if (gameplay.getName().equals(gameplayName)) {
                Collection<Team> teams = gameplay.getTeam();
                for (Team team : teams) {
                    sum += team.getAdvantage();
                }
            }
        }

        return String.format(ADVANTAGE_GAMEPLAY, gameplayName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Gameplay gameplay : gameplays) {
            stringBuilder.append(gameplay.toString());
        }

        return stringBuilder.toString();
    }
}
