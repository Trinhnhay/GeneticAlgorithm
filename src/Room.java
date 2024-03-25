import java.util.*;

public class Room {
    public List <String[]> roomList = new ArrayList<>();
    public Room (){
        initializeRoom();
    }
    public void initializeRoom() {
        roomList.add(new String[] {"Slater 003", "45"});
        roomList.add(new String[] {"Roman 216", "30"});
        roomList.add(new String[] {"Loft 206", "75"});
        roomList.add(new String[] {"Roman 201", "50"});
        roomList.add(new String[] {"Loft 310", "108"});
        roomList.add(new String[] {"Beach 201", "60"});
        roomList.add(new String[] {"Beach 301", "75"});
        roomList.add(new String[] {"Logos 325", "450"});
        roomList.add(new String[] {"Frank 119", "60"});

    }

}
