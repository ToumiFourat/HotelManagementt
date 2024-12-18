import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Hotel;
import dao.Hoteldao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Servlet implementation class AddHotelServlet
 */
@MultipartConfig
public class AddHotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    private Hoteldao hoteldao;

    public AddHotelServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        hoteldao = new Hoteldao();
    }

    /**
     * Handles GET requests
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read parameters from the form
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String starsStr = request.getParameter("stars");
        String description = request.getParameter("description");
        Part file=request.getPart("image");
        String imageFileName=file.getSubmittedFileName();

        try {
            int stars = Integer.parseInt(starsStr);

            // Create Hotel object
            Hotel hotel = new Hotel(name, city, stars, description, imageFileName);

            // Add hotel to the database
            boolean success = hoteldao.addHotel(hotel);
            String uploadPath="C:/Users/foura/Desktop/Jakarta/Tp/Hotell/Hotell/src/main/webapp/uploads/"+imageFileName;
            
            try {
            FileOutputStream fos=new FileOutputStream(uploadPath);
            InputStream is= file.getInputStream();
            
            byte[] data=new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
            } catch(Exception e) {
            	e.printStackTrace();
            	
            }
            
            
            
            
            System.out.println(success);

            if (success) {
                // Set success notification
                request.setAttribute("message", "Hotel added successfully!");
                request.setAttribute("messageType", "success");
            } else {
                // Set failure notification
                request.setAttribute("message", "Failed to add hotel.");
                request.setAttribute("messageType", "error");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid number format for stars.");
            request.setAttribute("messageType", "error");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }

        // Forward back to the add hotel page
        request.getRequestDispatcher("views/addHotel.jsp").forward(request, response);
    }

    /**
     * Handles POST requests
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
