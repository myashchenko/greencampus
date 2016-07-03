'use strict';
var App = angular.module('greenApp.courses',[]);

App.controller('CoursesController', [ '$http', function($http){
    var campus = this;
    campus.courses = [ ];
    $http.get('/api/course/').success(function (data) {
        campus.courses = chunk(data.entities, 3);
    });

    function chunk(arr, size) {
        var newArr = [];
        for (var i = 0; i < arr.length; i += size) {
            newArr.push(arr.slice(i, i + size));
        }
        return newArr;
    }
}]);