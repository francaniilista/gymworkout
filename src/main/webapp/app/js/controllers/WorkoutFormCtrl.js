'use strict';

/* Controllers */
var gymworkoutControllers = angular.module('gymworkoutApp');

gymworkoutControllers
    .constant("nameURL", "../api/name/")
    .constant("exerciseUrl", "../api/exercise/")
    .controller("WorkoutFormCtrl", function ($scope, $http, $resource, exerciseUrl, nameURL) {
        $scope.msg = "Daily workout entry:";
        $scope.names = {};
        $scope.exercisesResource = $resource(exerciseUrl + ":id", { id: "@id" },{
            "update" : {method: "PUT"}
        });

        $scope.listExercises = function () {
            $scope.exercises = $scope.exercisesResource.query();
        }

        $scope.deleteExercise = function (exercise) {
            exercise.$remove().then(function () {
                $scope.exercises.splice($scope.exercises.indexOf(exercise), 1);
            });
        }

        $scope.updateExercise = function (exercise) {
            exercise.$update({id:exercise.id}).then(function (newExercise) {
               $scope.formExercise = null;
               $scope.reset();
            }, function(error) {
                console.log(error);
            });
        }

        $scope.startEdit = function (exercise) {
            $scope.formExercise = exercise;
            $scope.formExercise.date = new Date(exercise.date);
        }

        $scope.cancelEdit = function () {
            $scope.formExercise = null;
        }

        $scope.submit = function (formWorkout) {
            var workout = null;
            if (angular.isDefined(formWorkout) &&
                angular.isDefined(formWorkout.name) &&
                angular.isDefined(formWorkout.weight) &&
                angular.isDefined(formWorkout.date)) {
                workout = {
                    name : formWorkout.name,
                    series : formWorkout.series,
                    reps : formWorkout.reps,
                    weight : formWorkout.weight,
                    date : formWorkout.date
                    };
            }

            if ($scope.form.$valid) {
                new $scope.exercisesResource(workout).$save().then(function (newExercise) {
                    $scope.exercises.push(newExercise);
                    $scope.editedExercise = null;
                    $scope.reset();
                });
            }
        };

        $scope.reset = function () {
            $scope.form.$submitted = false;
            $scope.formExercise = {};
        }

        $http.get(nameURL).success(function (response) {
            $scope.names = response;
        });

        $scope.listExercises();

});