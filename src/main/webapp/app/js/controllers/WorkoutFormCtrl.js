 'use strict';

 /* Controllers */

 var gymworkoutControllers = angular.module('gymworkoutApp');

 gymworkoutControllers.controller('WorkoutFormCtrl', function ($scope, $http) {
    $scope.names = {};
    $http.get('../api/name/').success(function (response) {
        $scope.names = response;
	});

    $scope.submit = function (formWorkout) {
        var workout = null;
        if (angular.isDefined(formWorkout) &&
            angular.isDefined(formWorkout.exercise) &&
            angular.isDefined(formWorkout.weight) &&
            angular.isDefined(formWorkout.date)) {
            workout = {
                name : formWorkout.exercise.label,
                series : formWorkout.series,
                reps : formWorkout.reps,
                weight : formWorkout.weight,
                date : formWorkout.date
                };
        }

        if ($scope.form.$valid) {
            $http.post('../api/exercise/', workout)
                .success(function (data) {
                    $scope.reset();
                    console.log(angular.toJson(data));
                })
                .error(function (error) {
                    console.log(angular.toJson(error));
                });
        }
    };

    $scope.reset = function () {
        $scope.form.$submitted = false;
        $scope.data = {};
        $scope.form.exercise.$dirty = !$scope.form.exercise.$dirty;
        $scope.form.weight.$dirty = !$scope.form.weight.$dirty;
        $scope.form.date.$dirty = !$scope.form.date.$dirty;

    }
});