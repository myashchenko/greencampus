'use strict';

var App = angular.module('greenApp.course', []);

App.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        "enabled": true,
        "requireBase": false,
        "rewriteLinks": false
    });
});

App.controller('CourseController', ['$http', '$location', function ($http, $location) {
    var campus = this;
    campus.course = [];
    var id = $location.path();
    console.log(id);
    id = id.replace('/course/', '');
    $http.get('/api/course/' + id).success(function (data) {
        campus.course = data.entity;
        console.log(data.entity);
    });
}]);

App.controller('CreateCourseCtrl', ['$http', '$scope', function ($http, $scope) {
    $scope.createCourse = function () {
        var course = JSON.stringify({title: $scope.title, description: $scope.description});
        $http.post("/api/course/", course)
            .success(function (data) {
                console.log(data);
                var formEl = angular.element(document.querySelector(".form-creating-course"));
                formEl.empty();
                var main = angular.element(document.querySelector("#main"));
                main.append("<h3>Course created successfully!</h3>");
                main.append("<a class='btn-default btn' href='/theme/" + data.entity.id + "/create'>Create theme</a>");
            })
            .error(function (data) {
                console.log(data);
            });
    }
}]);

