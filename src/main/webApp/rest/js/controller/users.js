'use strict';
var App = angular.module('greenApp.users', []);

App.controller('UsersController', ['$http', function ($http) {
    var campus = this;
    campus.users = [];
    $http.get('/api/user/').success(function (data) {
        campus.users = chunk(data.entities, 4);
    });

    function chunk(arr, size) {
        var newArr = [];
        for (var i = 0; i < arr.length; i += size) {
            newArr.push(arr.slice(i, i + size));
        }
        return newArr;
    }
}]);