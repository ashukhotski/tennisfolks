var services = angular.module('tennisfolks.services', ['ngResource']);

services.factory('User', function ($resource) {
    return $resource('/tennisfolks/api/users', {}, {
    })
});