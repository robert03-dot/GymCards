package Gym_cards;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Gym extends Thread{
    private static HashSet<GymCard> members;
    public Predicate<GymCard> p;
    public List<ICard> expiredCards;

    public Gym(HashSet<GymCard> members) {
        this.members = new HashSet<>();
    }
    public void addMmember(GymCard add_gymcard){
        this.members.add(add_gymcard);
    }
    public void deleteMember(GymCard remove_gymvard){
        this.members.remove(remove_gymvard);
    }

    public HashSet<GymCard> getMembers() {
        return members;
    }
    public static void saveData(String file){
        try(ObjectOutputStream obj = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            obj.writeObject(members);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void reloadDate(String file){
        try(ObjectInputStream obj = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
            this.members = (HashSet<GymCard>) obj.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<GymCard> getGoldMmebers(){
        p = gymCard -> gymCard.getMemberShipType().equals(CardType.GOLD);
        return members.stream().filter(p).collect(Collectors.toList());
    }

    @Override
    public void run() {
        for(GymCard x:members){
            if(x.isExpired()){
                this.expiredCards.add(x);
            }
        }
    }
    public JSONArray serializeJSON(){
        JSONArray jsonArray = new JSONArray();
        for(GymCard x:members){
            JSONObject object = new JSONObject();
            object.put("id",GymCard.getId());
            object.put("cardholder",x.getCardHolder());
            object.put("loyalty_points",x.getLoyaltyPoints());
            object.put("expiry_date",x.getExpiryDate());
            object.put("membership_type",x.getMemberShipType().toString());
            jsonArray.add(object);
        }
        return jsonArray;
    }
}
//expresii lambda + predicate