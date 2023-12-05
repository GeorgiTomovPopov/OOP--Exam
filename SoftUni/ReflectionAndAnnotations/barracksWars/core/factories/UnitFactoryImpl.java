package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import barracksWars.models.units.*;
import jdk.jshell.spi.ExecutionControl;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {

		Unit unit;
        switch (unitType) {
            case "Archer":
				unit = new Archer();
				return unit;
            case "Swordsman":
				unit = new Swordsman();
				return unit;
            case "Pikeman":
				unit = new Pikeman();
				return unit;
            case "Horseman":
				unit = new Horseman();
				return unit;
            case "Gunner":
				unit = new Gunner();
				return unit;
        }
        throw new ExecutionControl.NotImplementedException("message");
    }
}
