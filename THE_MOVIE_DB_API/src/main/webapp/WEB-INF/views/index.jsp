<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Popular Movies</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
</head>
<body>
    <h1>Popular Movies</h1>
    
    <c:if test="${not empty list}">
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Overview</th>
                    <th>Release Date</th>
                    <th>Popularity</th>
                    <th>Vote Average</th>
                    <th>Movie ID</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iterate over the list of movies -->
                <c:forEach var="movie" items="${list.results}">
                    <tr>
                       <td class="movieTitle">
                         <a href="/movie/detail/${movie.id}"><c:out value="${movie.title}" /></a>
						</td>
                        <td><c:out value="${movie.overview}" /></td>
                        <td><c:out value="${movie.release_date}" /></td>
                        <td><c:out value="${movie.popularity}" /></td>
                        <td><c:out value="${movie.vote_average}" /></td>         
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <div>
            <p>Page: ${list.page} of ${list.total_pages}</p>
            <p>Total Results: ${list.total_results}</p>
        </div>
    </c:if>


     <c:if test="${empty movieResponse}">
        <p>No movies available.</p>
    </c:if>
</body>
</html>
