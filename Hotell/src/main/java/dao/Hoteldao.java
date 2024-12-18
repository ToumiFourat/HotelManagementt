package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Hotel;
import model.Roomtype;

public class Hoteldao {

    private Connection connection;

    public Hoteldao() {
        connection = SingleConnection.getInstance();
    }

    public boolean addHotel(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO hotel.hotel (name, city, stars, description, image) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getCity());
            statement.setInt(3, hotel.getStars());
            statement.setString(4, hotel.getDescription());
            statement.setString(5, hotel.getImage());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateHotel(Hotel hotel) throws SQLException {
        String sql = "UPDATE hotel.hotel SET name = ?, city = ?, stars = ?, description = ?, image = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getCity());
            statement.setInt(3, hotel.getStars());
            statement.setString(4, hotel.getDescription());
            statement.setString(5, hotel.getImage());
            statement.setInt(6, hotel.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteHotel(int hotelId) throws SQLException {
        String sql = "DELETE FROM hotel.hotel WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            return statement.executeUpdate() > 0;
        }
    }
    


    
    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotel.hotel";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = (ResultSet) statement.executeQuery()) {
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setCity(rs.getString("city"));
                hotel.setStars(rs.getInt("stars"));
                hotel.setDescription(rs.getString("description"));
                hotel.setImage(rs.getString("image"));
                hotels.add(hotel);
            }
        }
        return hotels;
    }
    
  


    //GetHotelIdByNAME
    

}
