import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Account;
import model.Hotel;
import model.Roomtype;
import dao.AccountDAO;
import dao.Hoteldao;
import dao.RoomTypeDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;
    private Hoteldao hoteldao;
    private RoomTypeDAO roomdao;

    @Override
    public void init() throws ServletException {
        accountDAO = new AccountDAO();
        hoteldao = new Hoteldao();
        roomdao = new RoomTypeDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve email and password from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Authenticate the account
            Account account = accountDAO.authenticate(email, password);

            if (account != null) {
                // Create a session for the logged-in user
                HttpSession session = request.getSession();
                session.setAttribute("user", account);

                if ("Agent".equals(account.getRole())) {
                    // Fetch hotels for the agent
                    try {
                        List<Hotel> hotels = hoteldao.getAllHotels();
                        List<Roomtype> rooms = roomdao.getAllRoomTypes();

                        // Add hotels to the request scope
                        request.setAttribute("hotels", hotels);
                     // Add rooms to the request scope
                        request.setAttribute("rooms", rooms);

                        // Forward to the agent dashboard
                        request.getRequestDispatcher("views/agent_dashboard.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching hotels.");
                    }
                } else if ("admin".equals(account.getRole())) {
                	// Fetch agents for the agent
                    try {
                        List<Account> agents = accountDAO.getAgents();

                        // Add agents to the request scope
                        request.setAttribute("agents", agents);

                        // Forward to the admin dashboard
                        request.getRequestDispatcher("views/admin_dashboard.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching hotels.");
                    }
                } else {
                    // Handle unexpected roles
                    request.setAttribute("message", "Unexpected role detected.");
                    request.getRequestDispatcher("views/login.jsp").forward(request, response);
                }
            } else {
                // Login failed, redirect to login page with an error message
                request.setAttribute("message", "Invalid email or password.");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
}
