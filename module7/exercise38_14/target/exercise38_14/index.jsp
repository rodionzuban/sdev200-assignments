<%@ page import="java.util.Random" %>
<%
    Random rand = new Random();
    int numProblems = 10;
    int[] nums1 = new int[numProblems];
    int[] nums2 = new int[numProblems];

    for (int i = 0; i < numProblems; i++) {
        nums1[i] = rand.nextInt(30 - 20) + 20; // 20..29
        nums2[i] = rand.nextInt(11 - 1) + 1;   // 1..10
    }

    session.setAttribute("nums1", nums1);
    session.setAttribute("nums2", nums2);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Addition Quiz</title>
</head>
<body>
    <form action="gradeQuiz" method="post">
        <% for (int i = 0; i < numProblems; i++) { %>
            <%= nums1[i] %> + <%= nums2[i] %> = 
            <input type="text" name="answer<%= i %>"><br>
        <% } %>
        <input type="submit" value="Submit">
        <p>Click the browser's refresh button to get a new quiz</p>
    </form>
</body>
</html>