import dao.HotelRoomDAO;
import model.HotelRoom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String city = request.getParameter("city");
        int stars = Integer.parseInt(request.getParameter("stars"));
        String roomType = request.getParameter("roomType");
        int maxCapacity = Integer.parseInt(request.getParameter("capacity"));
        double minPrice = Double.parseDouble(request.getParameter("minPrice"));
        double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));

        HotelRoomDAO hotelDAO = new HotelRoomDAO();
        List<HotelRoom> hotelRooms = hotelDAO.getHotelsWithDetails(city, stars, roomType, maxCapacity, minPrice, maxPrice);

        request.setAttribute("hotelResults", hotelRooms);
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
