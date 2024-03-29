package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Model;
import model.offers.Offer;
import servlets.helper.Helper;

@WebServlet(name = "ProfilesSortAndFilterServlet", urlPatterns = {"/sortAndFilterProf"})
public class ProfilesSortAndFilterServlet extends HttpServlet {

	Model model;
	int first, last, number;

	public ProfilesSortAndFilterServlet() {
		model = Model.getModel();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plaintext;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			int min = Helper.getIntValueOf(request.getParameter("arg1"));
			int max = Helper.getIntValueOf(request.getParameter("arg2"));
			int sort = Helper.getIntValueOf(request.getParameter("arg3"));
			String search = request.getParameter("arg4");
			String arg = request.getParameter("arg5");
			ArrayList<Offer> offers;

			offers = switch (arg) {
				case "1" ->
					model.offerInterface.getSortedAndFilteredProfiles(min, max, sort, search, last, number, 1);
				case "-1" ->
					model.offerInterface.getSortedAndFilteredProfiles(min, max, sort, search, first, 0, -1);
				default ->
					model.offerInterface.getSortedAndFilteredProfiles(min, max, sort, search, 0, 0, 0);
			};
			number = offers.size();

			StringBuilder jsonOffers = new StringBuilder("[");
			if (!offers.isEmpty()) {
				for (int i = 0; i < offers.size(); i++) {
					Offer offer = offers.get(i);

					jsonOffers.append("{")
							.append("\"id_person\": \"").append(offer.getIdPerson()).append("\",")
							.append("\"title\": \"").append(offer.getTitle()).append("\",")
							.append("\"content\": \"").append(offer.getContent()).append("\",")
							.append("\"rating\": \"").append(offer.getRating()).append("\"")
							.append("}");
					if (i < offers.size() - 1) {
						jsonOffers.append(",");
					}
					else if (i == offers.size() - 1) {
						last = offer.getIdOffer();
					}
					if (i == 0) {
						first = offer.getIdOffer();
					}
				}
			}
			else {
				jsonOffers.append("{")
						.append("\"title\": \"").append("Offer not found!").append("\"")
						.append("}");
			}
			jsonOffers.append("]");

			out.println(jsonOffers.toString());
		}
		catch (Exception exp) {
			System.out.println(exp);
		}
	}
}
