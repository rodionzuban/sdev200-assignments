package com.rodionzuban;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/loan")
public class LoanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get all parameter values from loan form
        double amount = Double.parseDouble(request.getParameter("amount"));
        double rate = Double.parseDouble(request.getParameter("rate"));
        int years = Integer.parseInt(request.getParameter("years"));

        // calculate monthly and total payment (couldn't find Loan class)
        double monthlyRate = rate / 1200;
        int months = years * 12;

        double monthlyPayment = (amount * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -months));

        double totalPayment = monthlyPayment * months;

        // create a response containing loan data
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Loan Results</h2>");
        out.println("Loan amount: " + amount + "<br>");
        out.println("Annual interest rate: " + rate + "<br>");
        out.println("Number of years: " + years + "<br>");
        out.println("Monthly Payment: " + monthlyPayment + "<br>");
        out.println("Total Payment: " + totalPayment);
    }
}
