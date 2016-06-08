'use strict';
var App = angular.module('greenApp.home-courses',[]);

App.controller('HomeCoursesController', [ '$http', function($http){
    var campus = this;
    campus.courses = [ ];
    $http.get('/api/course/?size=3').success(function (data) {
        campus.courses = data.entities;
    });
}]);
