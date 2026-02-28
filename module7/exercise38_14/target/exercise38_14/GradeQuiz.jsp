<%
    int[] nums1 = (int[]) request.getAttribute("nums1");
    int[] nums2 = (int[]) request.getAttribute("nums2");
    int[] userAnswers = (int[]) request.getAttribute("userAnswers");
    boolean[] correct = (boolean[]) request.getAttribute("correct");
    int total = (Integer) request.getAttribute("total");
    int score = (Integer) request.getAttribute("score");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Addition Quiz Results</title>
</head>
<body>
    <% for (int i = 0; i < total; i ++) { %>
        <%= nums1[i] %> + <%= nums2[i] %> = <%= userAnswers[i] %>
        <% if (correct[i])  { %>
            Correct
        <% } else { %>
            Wrong
        <% } %>
        <br>
    <% } %>
    The total correct count is <%= score %>
</body>
</html>