var app = angular.module('tennisfolks.controllers', []);

app.controller('UserServiceController', ['$scope', 'User', function ($scope, User) {
    User.get({}, function (user) {
        $scope.users = user.users;
    })
}]);