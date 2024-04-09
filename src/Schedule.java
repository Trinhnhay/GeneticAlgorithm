import java.util.*;
public class Schedule {

    public Room room = new Room();
    public TimeSlot timeSlot = new TimeSlot();
    public List <Activity> activityList = new ArrayList<>();
    public Facilitator facilitators = new Facilitator();

    public Schedule(){
        createNewSchedule();
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void createNewSchedule (){
        Random random = new Random();

        List <String> preferredFacilitators=List.of("Glen"," Lock", "Banks", "Zeldin");
        List <String> otherFacilitators=List.of("Numen", "Richards");
        String[] RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        String time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        String facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA100A",50,preferredFacilitators, otherFacilitators,
                                    RandomRoom,time, facilitator));

        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA100B",50,preferredFacilitators, otherFacilitators,
                RandomRoom,time, facilitator));

        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA191A",50,preferredFacilitators, otherFacilitators,
                RandomRoom,time, facilitator));

        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA191B",50,preferredFacilitators, otherFacilitators,
                RandomRoom,time, facilitator));

        preferredFacilitators=List.of("Glen", "Banks", "Zeldin", "Shaw");
        otherFacilitators=List.of("Numen", "Richards", "Singer");
        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA201", 50, preferredFacilitators,otherFacilitators,RandomRoom,time, facilitator));

        preferredFacilitators=List.of("Lock", "Banks", "Zeldin", "Singer");
        otherFacilitators=List.of("Numen", "Richards", "Shaw", "Tyler");
        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA291", 50, preferredFacilitators,otherFacilitators,RandomRoom,time, facilitator));

        preferredFacilitators=List.of("Glen",  "Zeldin", "Banks");
        otherFacilitators=List.of("Numen", "Singer", "Shaw");
        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA303", 60, preferredFacilitators,otherFacilitators,RandomRoom,time, facilitator));

        preferredFacilitators=List.of("Glen", "Banks", "Tyler");
        otherFacilitators=List.of("Numen", "Singer", "Shaw","Richards", "Uther","Zeldin");
        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA304", 25, preferredFacilitators,otherFacilitators,RandomRoom,time, facilitator));

        preferredFacilitators=List.of( "Tyler", "Singer");
        otherFacilitators=List.of("Richards", "Zeldin");
        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA394", 20, preferredFacilitators,otherFacilitators,RandomRoom,time, facilitator));

        preferredFacilitators=List.of( "Tyler","Singer","Shaw");
        otherFacilitators=List.of( "Uther","Zeldin");
        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA449", 20, preferredFacilitators,otherFacilitators,RandomRoom,time, facilitator));

        preferredFacilitators=List.of( "Tyler","Singer","Shaw");
        otherFacilitators=List.of("Richards", "Uther","Zeldin","Banks");
        RandomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];
        activityList.add(new Activity("SLA451", 100, preferredFacilitators,otherFacilitators,RandomRoom,time, facilitator));

    }
    public void displaySchedule(){
        for(Activity activity:activityList) {
            activity.printActivity();
        }
    }
    public String writeSchedule(){
        StringBuilder schedule= new StringBuilder();
        int i= 0;
        for(Activity activity:activityList){
            schedule.append(i).append(". ").append(activity.writeActivity());
            i++;
        }
        return schedule + " ";
    }

}
