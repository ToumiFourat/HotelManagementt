package dao;

import model.HotelRoom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelRoomDAO {

    private Connection connection;

    public HotelRoomDAO() {
        connection = SingleConnection.getInstance();
    }

    // Méthode pour rechercher les hôtels avec critères dynamiques
    public List<HotelRoom> getHotelsWithDetails(String city, int minStars, String roomType, int maxCapacity, double minPrice, double maxPrice) {
        List<HotelRoom> hotelRooms = new ArrayList<>();

        String query = "SELECT " +
                       "    h.name AS hotel_name, " +
                       "    h.stars AS number_of_stars, " +
                       "    h.city AS city, " +
                       "    rt.label AS room_type, " +
                       "    rt.prix AS price, " +
                       "    rt.capacity AS capacity, " +
                       "    h.image AS image_url " +
                       "FROM " +
                       "    Hotel h " +
                       "JOIN " +
                       "    HotelRoom hr ON h.id = hr.hotel_id " +
                       "JOIN " +
                       "    RoomType rt ON hr.room_type_id = rt.id " +
                       "WHERE " +
                       "    h.city = ? AND " +
                       "    h.stars >= ? AND " +
                       "    rt.capacity <= ? AND " +
                       "    rt.label = ? AND " +
                       "    rt.prix BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, city);
            stmt.setInt(2, minStars);
            stmt.setInt(3, maxCapacity);
            stmt.setString(4, roomType);
            stmt.setDouble(5, minPrice);
            stmt.setDouble(6, maxPrice);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HotelRoom hotelRoom = new HotelRoom(
                    rs.getString("hotel_name"),
                    rs.getInt("number_of_stars"),
                    rs.getString("city"),
                    rs.getString("room_type"),
                    rs.getDouble("price"),
                    rs.getInt("capacity"),
                    rs.getString("image_url")
                );

                hotelRooms.add(hotelRoom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelRooms;
    }
    
    public void updateHotelIdByRoomTypeId(int roomTypeId, int hotelId) throws Exception {
        String query = "UPDATE hotelroom SET hotel_id = ? WHERE room_type_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) { // Suppression du point-virgule ici
            stmt.setInt(1, hotelId);
            stmt.setInt(2, roomTypeId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Renvoyer l'exception pour que l'appelant puisse gérer l'erreur
        }
    }

}
