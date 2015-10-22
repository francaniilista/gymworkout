'use strict';

/* Controllers */
var gymworkoutControllers = angular.module('gymworkoutApp');

gymworkoutControllers
    .constant("nameURL", "../api/name/")
    .constant("exerciseUrl", "../api/exercise/")
    .controller("WorkoutFormCtrl", function ($scope, $http, $resource, exerciseUrl, nameURL) {
        $scope.names = {};
        $scope.exercisesResource = $resource(exerciseUrl + ":id", { id: "@id" });
        $scope.listExercises = function () {
            $scope.exercises = $scope.exercisesResource.query();
        }

        $scope.deleteExercise = function (exercise) {
            exercise.$remove().then(function () {
                $scope.exercises.splice($scope.exercises.indexOf(exercise), 1);
            });
        }

        $scope.createExercise = function (exercise) {
            new $scope.exercisesResource(exercise).$save().then(function (newExercise) {
                $scope.exercises.push(newExercise);
                $scope.editedExercise = null;
            });
        }

        $scope.updateExercise = function (exercise) {
            exercise.$save();
            $scope.editedExercise = null;
        }

        $scope.startEdit = function (exercise) {
            $scope.editedExercise = exercise;
        }

        $scope.cancelEdit = function () {
            $scope.editedExercise = null;
        }

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
                        $scope.exercises.push(data)
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

        $http.get('../api/name/').success(function (response) {
            $scope.names = response;
        });

        $scope.listExercises();

});