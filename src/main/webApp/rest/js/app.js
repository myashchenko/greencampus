'use strict';
var App = angular.module('greenApp',[]);

App.controller('CampusController', [ '$http', function($http){
    var campus = this;
    campus.users = [ ];
    $http.get('/api/user/users').success(function (data) {
       campus.users = data.entities;
        console.log("data " + data);
        console.log("users " + users);
    });
}]);
