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

@MultipartConfig
public class UpdateHotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Hoteldao hoteldao;

    public UpdateHotelServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        hoteldao = new Hoteldao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Read parameters from the form
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String city = request.getParameter("city");
            int stars = Integer.parseInt(request.getParameter("stars"));
            String description = request.getParameter("description");
            Part file=request.getPart("image");
            String imageFileName=file.getSubmittedFileName();
            
           
            	 // Create Hotel object
                Hotel hotel = new Hotel(name, city, stars, description, imageFileName,id);
                // Update hotel in the database
                boolean success = hoteldao.updateHotel(hotel);
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
            

           


            if (success) {
                request.setAttribute("message", "Hotel updated successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to update hotel.");
                request.setAttribute("messageType", "error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }

        // Forward back to the update hotel page
        request.getRequestDispatcher("views/editHotel.jsp").forward(request, response);
    }
}
