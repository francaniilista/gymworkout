'use strict';

/* Controllers */

var gymworkoutControllers = angular.module('gymworkoutApp');

gymworkoutControllers
    .constant("activeClass", "active")
    .controller('WelcomeCtrl', function ($scope, activeClass) {

    var selectedOption = null;
    $scope.msg = '';

    $scope.selectMenuOption = function (option) {
        selectedOption = option;
    }

    $scope.getMenuOption = function (option) {
        return selectedOption == option ? activeClass : "";
    }
});