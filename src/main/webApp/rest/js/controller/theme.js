'use strict';

var App = angular.module('greenApp.theme', []);

App.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        "enabled": true,
        "requireBase": false,
        "rewriteLinks": false
    });
});

App.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

App.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function (file, uploadUrl) {
        var fd = new FormData();
        fd.append('file', file);

        $http.post(uploadUrl, fd)

            .success(function () {

            })

            .error(function () {
            });
    }
}]);

App.controller('ThemeController', ['$http', '$location', function ($http, $location) {
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

App.controller('CreateThemeCtrl', ['$http', '$scope', '$location', 'fileUpload', function ($http, $scope, $location, fileUpload) {
    var courseId = $location.path();
    courseId = courseId.split('/');
    console.log(courseId);
    courseId = courseId[2];
    console.log(courseId);
    function uploadFile() {
        var file = $scope.file;

        console.log('file is ');
        console.dir(file);

        var uploadUrl = "/api/file/theme/4";
        fileUpload.uploadFileToUrl(file, uploadUrl);
    }

    $scope.themeCourse = function () {
        var theme = JSON.stringify({name: $scope.title, files: ['check']});
        uploadFile();
    };
    var filesCount = 0;
    $scope.attachOneMore = function () {
        var elem = angular.element(document.querySelector("#fileUploading"));
        elem.append('<div class="box"><input style="width: 0.1px; height: 0.1px;opacity: 0;overflow: hidden;position: absolute;z-index: -1;" type="file" name="file" id="file' + filesCount + '" class="inputfile js inputfile-2" data-multiple-caption="{count} files selected" multiple /><label for="file-2"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg><span>Choose a file&hellip;</span></label></div>');
        filesCount++;
        if (filesCount == 8) {
            console.log("uhuhuh");
            var button = angular.element(document.querySelector("#attachOneMore"));
            button.remove();
        }
    }
}]);

