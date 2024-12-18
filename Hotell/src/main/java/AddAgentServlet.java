import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import dao.AccountDAO;

import java.io.IOException;

/**
 * Servlet implementation class AddAgentServlet
 */
public class AddAgentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;

    public AddAgentServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        accountDAO = new AccountDAO();
    }

    /**
     * Handles GET requests
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read parameters from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = "Agent"; // Assuming default role for agents

        try {
            // Create Account object
            Account account = new Account(0, username, password, email, role);

            // Add agent to the database
            boolean success = accountDAO.addAccount(account);

            if (success) {
                // Set success notification
                request.setAttribute("message", "Agent added successfully!");
                request.setAttribute("messageType", "success");
            } else {
                // Set failure notification
                request.setAttribute("message", "Failed to add agent.");
                request.setAttribute("messageType", "error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }

        // Forward back to the add agent page
        request.getRequestDispatcher("views/addAgent.jsp").forward(request, response);
    }

    /**
     * Handles POST requests
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
