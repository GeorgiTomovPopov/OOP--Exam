package handball.entities.team;
//I can only play Indoor
public class Germany extends BaseTeam{
    public Germany(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        advantage += 145;
    }
}
