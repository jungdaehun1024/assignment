
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 로컬환경에서 웹페이지 한글 깨짐현상 발생해서 추가 -->


<!DOCTYPE html>
<html>
<head>
    <title>Movie Details</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Movie Details</h1>

    <table>
        <tr>
            <th>Field</th>
            <th>Information</th>
        </tr>
        <tr>
            <td>Title</td>
            <td>${movie.title}</td>
        </tr>
        <tr>
            <td>Original Title</td>
            <td>${movie.original_title}</td>
        </tr>
        <tr>
            <td>Overview</td>
            <td>${movie.overview}</td>
        </tr>
        <tr>
            <td>Release Date</td>
            <td>${movie.release_date}</td>
        </tr>
        <tr>
            <td>Genres</td>
            <td>
                <c:forEach var="genre" items="${movie.genres}">
                    ${genre.name}<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Production Companies</td>
            <td>
                <c:forEach var="company" items="${movie.production_companies}">
                    ${company.name}<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Poster</td>
            <td><img src="https://image.tmdb.org/t/p/original${movie.poster_path}" alt="Poster" width="200"/></td>
        </tr>
        <tr>
            <td>Average Vote</td>
            <td>${movie.vote_average}</td>
        </tr>
        <tr>
            <td>Vote Count</td>
            <td>${movie.vote_count}</td>
        </tr>
    </table>
    
    <h1>추천 영화 목록</h1>
    <table>
        <thead>
            <tr>
                <th>포스터</th>
                <th>영화 제목</th>
                <th>개요</th>
                <th>평점</th>
                <th>상세 정보</th>
            </tr>
        </thead>
        <tbody>
        
        <!-- recommend 객체에서 results 배열을 순회하며 각 영화를 출력 -->
        <c:forEach var="movie" items="${recommend.results}">
            <tr>
                <td><img src="https://image.tmdb.org/t/p/original${movie.poster_path}" alt="${movie.title}"  width="200"></td>
                <td>${movie.title} (${movie.release_date})</td>
                <td class="overview">${movie.overview}</td>
                <td>${movie.vote_average} / 10</td>
                <td><a href="/movie/detail/${movie.id}">상세 보기</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
