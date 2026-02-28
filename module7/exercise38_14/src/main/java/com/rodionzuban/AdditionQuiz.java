package com.rodionzuban;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/gradeQuiz")
public class AdditionQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get quiz numbers (num1 + num2 were the problems)
        int[] nums1 = (int[]) request.getSession().getAttribute("nums1");
        int[] nums2 = (int[]) request.getSession().getAttribute("nums2");

        int[] userAnswers = new int[nums1.length];
        boolean[] correct = new boolean[nums1.length];
        int score = 0;

        // track user responses and whether or not they were correct for each question
        for (int i = 0; i < nums1.length; i++) {
            String answerString = request.getParameter("answer" + i);
            int userAnswer = -1;
            try {
                userAnswer = Integer.parseInt(answerString);
            } catch (NumberFormatException ignore) {

            }
            userAnswers[i] = userAnswer;

            if (userAnswer == (nums1[i] + nums2[i])) {
                correct[i] = true;
                score++;
            } else {
                correct[i] = false;
            }
        }

        // pass info to GradeQuiz and display
        request.setAttribute("nums1", nums1);
        request.setAttribute("nums2", nums2);
        request.setAttribute("userAnswers", userAnswers);
        request.setAttribute("correct", correct);
        request.setAttribute("score", score);
        request.setAttribute("total", nums1.length);

        request.getRequestDispatcher("GradeQuiz.jsp").forward(request, response);
    }
}
