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

    /* Chart options */
    $scope.options = {
        chart: {
            type: 'lineChart',
            height: 450,
            margin : {
                top: 20,
                right: 20,
                bottom: 60,
                left: 40
            },
            transitionDuration: 500,
            xAxis: {
                axisLabel: 'Days',
                tickFormat: function(d){
                    return d3.time.format('%x')(new Date(d));
                }
            },
            yAxis: {
                axisLabel: 'Weight',
                tickFormat: function(d){
                    return d3.format(',.2f')(d);
                },
                rotateYLabel: false
            }
        }
    };


    /* Chart data */
    $scope.data = workoutData();

    /*Random Data Generator */
    function workoutData() {
        var data = [];

        //Data is represented as an array of {x,y} pairs.
        for (var i = 1; i < 30; i++) {
            var date = new Date("2015-03-" + i);
            data.push({x: date.getTime(), y: 10 + i});
        }

        //Line chart data should be sent as an array of series objects.
        return [{
                values: data,      //values - represents the array of {x,y} data points
                key: 'Weight Evo', //key  - the name of the series.
                color: '#ff7f0e'  //color - optional: choose your own line color.
            }];
    };

});