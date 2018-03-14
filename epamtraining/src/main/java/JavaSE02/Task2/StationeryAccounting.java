package JavaSE02.Task2;

import JavaSE02.Task3.Stationery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationeryAccounting {
    private HashMap<Integer, List<Stationery>> userToStationery = new HashMap<>();

    @SuppressWarnings("StreamToLoop")
    public int getStationeryPriceForUser(int userId){
        return userToStationery.get(userId).stream().map(Stationery::getPrice).mapToInt(Integer::intValue).sum();
    }

    public void addStationeryToUser(int userId, Stationery stationery){
    List<Stationery> userStationery = userToStationery.get(userId);

    if(userStationery == null) {
        userStationery = new ArrayList<>();
        userToStationery.put(userId, userStationery);
    }
    userStationery.add(stationery);
    }


    }

