'use strict';

var App = angular.module('greenApp.user',['ngRoute']);
App.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        "enabled": true,
        "requireBase": false,
        "rewriteLinks": false
    });
});

App.controller('UserController', ['$http', '$location', function ($http, $location) {
    var campus = this;
    campus.course = [];
    var id = $location.path();
    console.log(id);
    id = id.replace('/user/', '');
    $http.get('/api/user/' + id).success(function (data) {
        campus.user = data.entity;
    });
}]);

