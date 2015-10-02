/**
 * Created by varshini on 29/9/15.
 */
var MyPlayApp = angular.module('MyPlayApp', []);

MyPlayApp.controller("IndexController",function($scope,$http,$rootScope,$window){

    $scope.user = {};

    $scope.userLogin = function(){
            $http.post("/user/login",$scope.user)
                .success(function(data, status, headers, config) {
                    console.log(data);
                    $rootScope.login = data;
                }).
                error(function(data, status, headers, config) {
                    console.log("fail");
                    $rootScope.loginSuccess = "login failed"               // called asynchronously if an error occurs
                    // or server returns response with an error status.
                });
    }

    $scope.userSignUp = function(){
        $http.post("/user",$scope.user)
            .success(function(data, status, headers, config) {
                console.log(data);
                $rootScope.login = data;
                $window.location.reload();
            }).
            error(function(data, status, headers, config) {
                console.log("fail");
                $rootScope.loginSuccess = "login failed"
                $window.location.reload();
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    }


});