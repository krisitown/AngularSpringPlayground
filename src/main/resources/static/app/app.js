var app = angular.module('app', [ 'ngRoute' ])
    .config(function($routeProvider) {

        $routeProvider.when('/', {
            templateUrl: '/app/views/home.html'
        }).when('/login', {
            templateUrl : '/app/views/login.html',
            controller : 'LoginController'
        }).when('/register', {
            templateUrl : '/app/views/register.html',
            controller : 'RegisterController'
        }).when('/logout', {
            controller: 'LogoutController',
            template: ''
        }).when('/error', {
            templateUrl: '/app/views/error.html'
        }).otherwise({
            redirectTo: '/error'
        })
    })

.controller('LoginController',

    function($rootScope, $scope, $http, $location) {

        $scope.login = function() {
           $http.post('/login', {username: $scope.username, password: $scope.password})
                .then(function success(){
                    $rootScope.authenticated = true;
                    $location.path('/');
                }, function fail(){
                    $rootScope.authenticated = false;
                    $location.path('/login');
                });
       };
    })

    .controller('RegisterController', function ($location, $scope, $rootScope, $http) {
        $scope.register = function() {
            $http.post('/register', {username: $scope.username, password: $scope.password, confirmPassword: $scope.confirmPassword})
                .then(function success(){
                    $location.path("/login");
                }, function fail(){
                    $location.path("/register");
                });
        }
    })

    .controller('LogoutController', function ($location, $http){
        var logout = function() {
            $http.post('/logout', {}).then(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }, function() {
                $rootScope.authenticated = false;
                $location.path("/");
            });
        }
        logout();
    });