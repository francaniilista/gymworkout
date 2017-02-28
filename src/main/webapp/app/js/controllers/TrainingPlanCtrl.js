'use strict';

/* Controllers */
var gymworkoutControllers = angular.module('gymworkoutApp');

gymworkoutControllers
    .constant("trainingUrl", "../api/trainingPlan/")
    .controller("TrainingPlanCtrl", function ($scope, $http, $resource, trainingUrl) {
        $scope.msg = "Training Plan:";
        $scope.trainingResource = $resource(trainingUrl + ":id", { id: "@id" },{
            "update" : {method: "PUT"}
        });

        $scope.submit = function (formTraining) {
            var training = null;
            if (angular.isDefined(formTraining) &&
                angular.isDefined(formTraining.name)) {
                training = {
                    name : formTraining.name,
                    workouts : null
                };
            }

            if ($scope.form.$valid) {
                new $scope.trainingResource(training).$save().then(function (newTraining) {
                    $scope.trainings.push(newTraining);
                    $scope.reset();
                });
            }
        };
});